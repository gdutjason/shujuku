package com.qg.util;

import com.qg.dao.ActionDao;
import com.qg.dao.DUserDao;
import com.qg.dao.PUserDao;
import com.qg.dao.RcStageDao;
import com.qg.entity.Action;
import com.qg.entity.DUser;
import com.qg.entity.PUser;
import com.qg.entity.RcStage;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jason on 16-10-23.
 */

public class Cache {
    private static ActionDao actionDao;
    private static RcStageDao rcStageDao;
    private static DUserDao dUserDao;
    private static PUserDao pUserDao;

    public static Map<Integer ,String> puserIdToRcStage = new ConcurrentHashMap<Integer, String>();
    public static Map<Integer,ArrayList<RcStage>> mrIdToRcStages = new ConcurrentHashMap<Integer, ArrayList<RcStage>>();
    public static Map<String,DUser> phoneToDUser = new ConcurrentHashMap<String, DUser>();
    public static Map<String,PUser> phoneToPUser = new ConcurrentHashMap<String, PUser>();
    public static Map<String,Action> nameToAction = new ConcurrentHashMap<String, Action>();
    public static boolean isFirst = true;

    static {
        ApplicationContext cxt = new ClassPathXmlApplicationContext("spring/spring-dao.xml");
        rcStageDao = (RcStageDao) cxt.getBean("rcStageDao");
        dUserDao = (DUserDao) cxt.getBean("DUserDao");
        pUserDao = (PUserDao) cxt.getBean("PUserDao");
        actionDao = (ActionDao) cxt.getBean("actionDao");
        if (isFirst) {
            init();
            Refresh();
        }
    }

    /**
     * 这是一个缓存方法，每次缓存先清空数据，然后将数据填入
     */
    public static void init() {
        isFirst = false;
        puserIdToRcStage.clear();//全部数据清空
        phoneToDUser.clear();
        phoneToPUser.clear();
        nameToAction.clear();
        mrIdToRcStages.clear();

        ArrayList<RcStage> rcStages;//获取所有康复阶段数据
        rcStages = rcStageDao.queryAll();

        ArrayList<DUser> dUsers;//获取所有医生数据
        dUsers = dUserDao.queryAll();

        ArrayList<PUser> pUsers;//获取所有的病患数据
        pUsers = pUserDao.queryAll();

        ArrayList<Action> actions;//获得所有动作数据
        actions = actionDao.queryAll();

        //pc 端需要康复列表,根据病人id
        for (RcStage rcStage : rcStages){
            String s = puserIdToRcStage.get(rcStage.getPuserId());
            if(s == null){
                s = rcStage.getId()+","+rcStage.getActionName();
                puserIdToRcStage.put(rcStage.getPuserId(),s);
            }else {
                s = s +","+rcStage.getId()+","+rcStage.getActionName();
                puserIdToRcStage.put(rcStage.getPuserId(),s);
            }



            //康复阶段的列表，根据病历id
            ArrayList list = mrIdToRcStages.get(rcStage.getMrId());
            if(list == null){
                list = new ArrayList<RcStage>();
                mrIdToRcStages.put(rcStage.getMrId(),list);
            }
            list.add(rcStage);
        }
        //康复阶段的列表，根据病历id
        //将医生数据放入map
        for(DUser dUser : dUsers){
            phoneToDUser.put(dUser.getPhone(),dUser);
        }
        //将病患数据放入map
        for(PUser pUser : pUsers){
            phoneToPUser.put(pUser.getPhone(),pUser);
        }

        //动作数据放入对应map
        for(Action action : actions){
            nameToAction.put(action.getName(),action);
        }
    }

    /**
     * 更新最新数据，每5分钟更新一次缓存
     */
    public static void Refresh() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                long time = System.currentTimeMillis();
                init();
                System.out.println("更新一次用时:"
                        + (System.currentTimeMillis() - time) / 1000);
            }
        }, 5 * 60 * 1000, 5 * 60 * 1000);
    }

    /**
     * 每当康复阶段有所更新，就必须刷新一次
     */
    public static void refreshRcStage(){
        puserIdToRcStage.clear();
        mrIdToRcStages.clear();
        ArrayList<RcStage> rcStages;
        rcStages = rcStageDao.queryAll();
        //pc 端需要康复列表,根据病人id
        for (RcStage rcStage : rcStages){
            String s = puserIdToRcStage.get(rcStage.getPuserId());
            if(s == null){
                s = rcStage.getId()+","+rcStage.getActionName();
                puserIdToRcStage.put(rcStage.getPuserId(),s);
            }else {
                s = s +","+rcStage.getId()+","+rcStage.getActionName();
                puserIdToRcStage.put(rcStage.getPuserId(),s);
            }


            //康复阶段的列表，根据病历id
            ArrayList list = mrIdToRcStages.get(rcStage.getMrId());
            if(list == null){
                list = new ArrayList<RcStage>();
                mrIdToRcStages.put(rcStage.getMrId(),list);
            }
            list.add(rcStage);
        }
    }

    /**
     * 有更新医生时，需要刷新
     */
    public static void refreshDUser(){
        phoneToDUser.clear();
        ArrayList<DUser> dUsers;//获取所有医生数据
        dUsers = dUserDao.queryAll();
        //将医生数据放入map
        for(DUser dUser : dUsers){
            phoneToDUser.put(dUser.getPhone(),dUser);
        }
    }

    /**
     * 有更新病患时,需要刷新
     */
    public static void refreshPUser(){
        phoneToPUser.clear();
        ArrayList<PUser> pUsers;//获取所有的病患数据
        pUsers = pUserDao.queryAll();
        //将病患数据放入map
        for(PUser pUser : pUsers){
            phoneToPUser.put(pUser.getPhone(),pUser);
        }
    }

    /**
     * 有数据变化时更新
     */
    public static void refreshAction(){
        nameToAction.clear();
        ArrayList<Action> actions;//获得所有动作数据
        actions = actionDao.queryAll();
        //
        for(Action action : actions){
            nameToAction.put(action.getName(),action);
        }
    }
}
