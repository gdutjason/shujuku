package com.qg.hx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;

public class Client {
    public static Gson gson = new Gson();
    // 创建HttpClient实例
    public static HttpClient httpclient = HttpClients.createDefault();

    public static ResponseBody getToken() {
        ResponseBody rb = null;
        try {
            // 创建post方法实例
            HttpPost httpPost = new HttpPost(
                    "https://a1.easemob.com/1192161017115311/kinect-app/token");
            httpPost.setHeader("Content-Type", "application/json");
            StringEntity se = new StringEntity(gson.toJson(new RequestBody()));
            httpPost.setEntity(se);
            rb = getResponseBody(httpPost, ResponseBody.class);
            httpPost.abort();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rb;
    }

    /**
     * 注册一个环信账号，返回一个状态字符串
     *
     * @param phone 注册的账号
     * @param type  注册的账号类型，如果是医生为D 如果是病患为P
     * @return 如果是200则为成功
     */
    public static String register(String phone, String type) {
        String responseStatus = null;
        ResponseBody rb = getToken();
        System.out.println(rb.access_token);
        HttpPost httpPost = new HttpPost(
                "https://a1.easemob.com/1192161017115311/kinect-app/users");
        RegisterBody registerBody = new RegisterBody(phone, type);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "Bearer " + rb.getAccess_token());
        try {
            StringEntity se = new StringEntity(gson.toJson(registerBody));
            httpPost.setEntity(se);
            HttpResponse response = httpclient.execute(httpPost);
            responseStatus = response.getStatusLine().toString()
                    .substring(9, 12);
            httpPost.abort();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseStatus;
    }

    /**
     * 病患添加医生为好友
     * @param pUser 病患名字
     * @param dUser 医生名字
     * @return  状态码
     */
    public static String addFriend(String pUser, String dUser) {
        String responseStatus = null;
        ResponseBody rb = getToken();
        System.out.println(rb.access_token);
        HttpPost httpPost = new HttpPost(
                "https://a1.easemob.com/1192161017115311/kinect-app/users/p" + pUser + "/contacts/users/d" + dUser);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "Bearer " + rb.getAccess_token());
        try {
            HttpResponse response = httpclient.execute(httpPost);
            responseStatus = response.getStatusLine().toString()
                    .substring(9, 12);
            httpPost.abort();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseStatus;
    }

    /**
     * 删除好友，传入病患手机，医生手机，其中病患是好友关系的拥有者
     * @param pUser
     * @param dUser
     * @return
     */
    public static String delFriend(String pUser,String dUser){
        String responseStatus = null;
        ResponseBody rb = getToken();
        System.out.println(rb.access_token);
        HttpDelete httpDelete = new HttpDelete(
                "https://a1.easemob.com/1192161017115311/kinect-app/users/" + pUser + "/contacts/users/" + dUser);
        httpDelete.setHeader("Content-Type", "application/json");
        httpDelete.setHeader("Authorization", "Bearer " + rb.getAccess_token());
        try {
            HttpResponse response = httpclient.execute(httpDelete);
            responseStatus = response.getStatusLine().toString()
                    .substring(9, 12);
            httpDelete.abort();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseStatus;
    }

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static <E> E getResponseBody(HttpPost httpPost, Class<E> e) {
        E obj = null;
        try {
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instreams = entity.getContent();
                String json = convertStreamToString(instreams);
                obj = gson.fromJson(json, e);
                httpPost.abort();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return obj;
    }
}
