package com.demo;

import javax.xml.ws.Endpoint;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.log4j.Logger;

public class webServiceApp {
	private static Logger log = Logger.getLogger(webServiceApp.class);

	/**
	 * 完善上面的execute cyk
	 * 
	 * @param function
	 * @param url
	 * @param arg
	 * @return
	 */
	public static String execute(String function, String url, Object[] arg) {
		try {
			// JaxWsDynamicClientFactory dcf=JaxWsDynamicClientFactory.newInstance();
			DynamicClientFactory dcf = DynamicClientFactory.newInstance();
			Client client = dcf.createClient(url);// http://bss.csjscm.com/ws/oms?wsdl
			Object[] reply = client.invoke(function, new Object[] { "ni" });
			return (String) reply[0];
		} catch (Exception e) {
			log.error("WebService.execute(" + function + ")调用异常:", e);
		}
		return null;
	}



	public static void main(String[] args) {
		System.out.println(webServiceApp.execute("sayHi",
				"http://localhost:8080/webservice/helloWorld?wsdl", new
				Object[] { "ni" }));

	     System.out.println("web service start");
         HelloWorldImpl implementor= new HelloWorldImpl();
         String address="http://localhost:8088/webservice/helloWorld";
         Endpoint.publish(address, implementor);
         System.out.println("web service started");

		// 创建WebService客户端代理工厂
	/*	JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		// 注册WebService接口
		factoryBean.setServiceClass(HelloWorld.class);
		factoryBean.setAddress("http://10.86.88.136:8000/PSIGW/PeopleSoftServiceListeningConnector/G_JX_PEROID.1");
		HelloWorld readerService = (HelloWorld) factoryBean.create();
		String reader = readerService.sayHi("123");
		System.out.println("Reader:" + reader);*/
	}
}