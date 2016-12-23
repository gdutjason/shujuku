import com.qg.entity.DUser;
import com.qg.entity.MedicalRecord;
import com.qg.entity.PUser;
import com.qg.hx.Client;
import com.qg.hx.RegisterBody;
import com.qg.param.*;
import com.qg.util.Cache;
import com.qg.util.GsonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.net.www.http.HttpClient;

import java.io.*;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static com.qg.hx.Client.httpclient;

/**
 * Created by jason on 16-10-18.
 */
public class test {
    public static  String url = "http://119.29.201.182:8080/RecoveryTraining/PUser/getMR";
    //public static  String url = "http://localhost:8080/RecoveryTraining/PUser/updatePUser";
    public static org.apache.http.client.HttpClient httpclient = HttpClients.createDefault();
    public static void main(String[] args) {
      
        HttpPost httpPost = new HttpPost(url
                );
        httpPost.setHeader("Content-Type", "application/json");
        GetMRParam param = new GetMRParam();
        param.pUserId = 1;
        //param.pUserId =
        DUser dUser = new DUser();
        //dUser.setId(1);
//        dUser.setAge(29);
//        dUser.setName("两只蚝毛起来嗨");
//        dUser.setSex(1);
//        dUser.setDepartment("慢性牙科");
//        dUser.setJobTitle("顶级牙医");
//        dUser.setPhone("13111123456");
//        dUser.setPassword("qgmobile");
//        dUser.setHospital("省中医");
//        param.dUser = dUser;
//        PUser pUser = new PUser();
//        pUser.setName("gdutjason");
//        pUser.setId(1);
//        pUser.setAge(123);
//        pUser.setSex(0);
//        pUser.setBirth(new Date());
//        param.pUser = pUser;
//        System.out.println(GsonUtil.gson.toJson(param));
            StringEntity se = null;
            try {
                se = new StringEntity(GsonUtil.gson.toJson(param),"UTF-8");
                httpPost.setEntity(se);
                HttpResponse response = httpclient.execute(httpPost);
                HttpEntity he = response.getEntity();
                String charset = EntityUtils.getContentCharSet(he);
                String line;
                StringBuilder sb = new StringBuilder();
                BufferedReader rd = new BufferedReader(new InputStreamReader(he.getContent(),charset));
                while ((line =rd.readLine()) != null){
                    sb.append(line);
                    System.out.println(line);
                }
                //System.out.println(sb);
                httpPost.abort();

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
