package com.entanmo.dapp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	public static String readPostContent(HttpServletRequest req) throws Exception{
		InputStream is = req.getInputStream();
		byte[] buffer = new byte[req.getContentLength()];
		is.read(buffer);
		return new String(buffer, "utf-8");
	}
	
	public static void doPut() {
		String urlStr = "http://118.24.135.98:4196/api/dapps";
		String body = "name=cctime&category=1&description=aaa";
		try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            OutputStream os = conn.getOutputStream();     
            os.write(body.getBytes("utf-8")); 
            os.flush();
            os.close();         
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            while( (line =br.readLine()) != null ){
                result += line;
            }
            System.out.println(result);
            br.close();
       } catch (Exception e) {
           System.out.println("Error in pushing policy now");
           e.printStackTrace();  
       }
	}
	
	protected static String encodeUrl(String url)throws IOException{
        try{
            URL uri = new URL(url);

            return new java.net.URI(uri.getProtocol(), uri.getUserInfo(), uri.getHost(), uri.getPort(),
                    uri.getPath(), uri.getQuery(), null)
                    .toString();
        }
        catch(Exception ex){
            throw new IOException("invalid url: "+ url, ex);
        }
    }
	
	public static String doPut(String url, String requestBody, String charset ) throws IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut put = new HttpPut(encodeUrl(url));

        StringEntity  entity = new StringEntity(requestBody, charset);
        entity.setContentType("application/json");
        put.setEntity(entity);
 
        HttpResponse response = httpClient.execute(put);
        return EntityUtils.toString(response.getEntity());
    }
	
	protected static String getResponseContent(HttpResponse response) throws IOException{

        if (null == response)
            throw new IOException("Get response failed");

        String content = EntityUtils.toString(response.getEntity());


        if (!content.contains("\"success\":")){
            throw new IOException(String.format("Http server response failed, code:%d, reason:%s.\n content:%s",
                    response.getStatusLine().getStatusCode(),
                    response.getStatusLine().getReasonPhrase(),
                    content));
        }

        return content;
    }
	public static void main(String[] args) {
		doPut();
	}
}
