package test;

import java.io.IOException;  
import java.nio.charset.Charset;  
  
import org.apache.commons.logging.Log;  
import org.apache.commons.logging.LogFactory;  
import org.apache.http.HttpResponse;  
import org.apache.http.HttpStatus;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.entity.StringEntity;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.util.EntityUtils;  
  
import com.google.gson.JsonArray;  
import com.google.gson.JsonObject;  
  
public class APIHttpClient {  
  
    // 接口地址  
    private static String apiURL = "http://159.220.50.34/api/TokenManagement/TokenManagement.svc/REST/TokenManagement/CreateServiceToken_1";  
    private Log logger = LogFactory.getLog(this.getClass());  
    private static final String pattern = "yyyy-MM-dd HH:mm:ss:SSS";  
    private HttpClient httpClient = null;  
    private HttpPost method = null;  
    private long startTime = 0L;  
    private long endTime = 0L;  
    private int status = 0;  
  
    /** 
     * 接口地址 
     *  
     * @param url 
     */  
    public APIHttpClient(String url) {  
  
        if (url != null) {  
            this.apiURL = url;  
        }  
        if (apiURL != null) {  
            httpClient = new DefaultHttpClient();  
            method = new HttpPost(apiURL);  
  
        }  
    }  
  
    /** 
     * 调用    API    
     *  
     * @param parameters 
     * @return 
     */  
    public String post(String parameters) {  
        String body = null;  
        System.out.println("parameters:" + parameters);  
  
        if (method != null & parameters != null  
                && !"".equals(parameters.trim())) {  
            try {  
  
                // 建立一个NameValuePair数组，用于存储欲传送的参数  
                method.addHeader("Content-type","application/json; charset=utf-8");  
                method.setHeader("Accept", "application/json");  
              //  method.addHeader("X-Trkd-Auth-Token","C7E72312B425182E6C7314FBE8A7BDBC45579DE6663C5945FD0695826B0229B4F65FAB1F9098E367742BE08C487AACDD78A249BE3700BF1A37A964E19819BB89B5C91255F5BA1E81DE214EF65A6496BDC6E954CD678444705544A4A35867385C");
                method.addHeader("X-Trkd-Auth-ApplicationID","trkddemoappwm");
                
                method.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));  
                startTime = System.currentTimeMillis();  
  
                HttpResponse response = httpClient.execute(method);  
                  
                endTime = System.currentTimeMillis();  
                int statusCode = response.getStatusLine().getStatusCode();  
                  
                System.out.println("statusCode:" + statusCode);  
                System.out.println("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));  
                if (statusCode != HttpStatus.SC_OK) {  
                    logger.error("Method failed:" + response.getStatusLine());  
                    status = 1;  
                }  
  
                // Read the response body  
                body = EntityUtils.toString(response.getEntity());  
  
            } catch (IOException e) {  
                // 网络错误  
                status = 3;  
                e.printStackTrace();
            } finally {  
                System.out.println("调用接口状态：" + status);  
            }  
  
        }  
        return body;  
    }  
  
    public static void main(String[] args) {  
        APIHttpClient ac = new APIHttpClient(apiURL);  
        /*
        JsonArray arry = new JsonArray();  
        JsonObject j = new JsonObject();  
        j.addProperty("orderId", "中文");  
        j.addProperty("createTimeOrder", "2015-08-11");  
        arry.add(j);  
        */
        JsonObject j = new JsonObject();
        
        //System.out.println(ac.post(arry.toString()));  
        System.out.println(ac.post("{\"CreateServiceToken_Request_1\":{\"ApplicationID\":\"trkddemoappwm\",\"Username\":\"trkd-demo-wm@thomsonreuters.com\",\"Password\":\"y1r2o36pr\"}}"));
    }  
  
    /** 
     * 0.成功 1.执行方法失败 2.协议错误 3.网络错误 
     *  
     * @return the status 
     */  
    public int getStatus() {  
        return status;  
    }  
  
    /** 
     * @param status 
     *            the status to set 
     */  
    public void setStatus(int status) {  
        this.status = status;  
    }  
  
    /** 
     * @return the startTime 
     */  
    public long getStartTime() {  
        return startTime;  
    }  
  
    /** 
     * @return the endTime 
     */  
    public long getEndTime() {  
        return endTime;  
    }  
}  
