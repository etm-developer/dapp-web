package com.entanmo.dapp.test;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;


/**
 * 开发调试使用的 Jetty Server
 * @author WuChao
 */
public class JettyServer {

    public static void main(String[] args) throws Exception {
        Server server = buildNormalServer(9090, "/");
        server.start();


//		DynamicRemoteService dynamicRemoteService = new DynamicRemoteServiceImpl();
//		
//		try{
//			
//			Dynamic dynamic = new Dynamic();
//			dynamic.setArea_name("深圳");
//
//			
//			String []pics = {"aa.jpg","bb.jpg"};
//			
//			dynamic.setPics(pics);
//
//			
//			dynamic.setQd_id("30");
//
//			dynamic.setTopic_id("1");
//
//			JSONObject json = JSONObject.fromObject(dynamic);
//			
//			System.err.println("cccccc:"+json.toString());	
//
//			
//			dynamicRemoteService.saveDynamic("1111","30",json.toString());
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}

    }

    /**
     * AdminNetbarServiceImpl
     * 创建用于正常运行调试的Jetty Server, 以src/main/webapp为Web应用目录.
     */
    public static Server buildNormalServer(int port, String contextPath) {
        Server server = new Server(port);
        WebAppContext webContext = new org.mortbay.jetty.webapp.WebAppContext("src/main/webapp", contextPath);
//		 WebAppContext webContext = new
//		 org.mortbay.jetty.webapp.WebAppContext("D:/lianleWiFi/lianle_web/lianle_java_server/trunk/lianle-api/target/lianle-api-0.0.1.war",
//		 contextPath);
        webContext.setClassLoader(Thread.currentThread()
                .getContextClassLoader());
        webContext.setMaxFormContentSize(-1);
        server.setHandler(webContext);
        server.setStopAtShutdown(true);
        return server;
    }

}
