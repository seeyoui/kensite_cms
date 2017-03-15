package com.seeyoui.kensite.webservice.cfx.impl;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class CFXinvoke {
	public static void main(String[] args) {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();  
		Client client = dcf.createClient("http://localhost:8080/kensite/webservice/webSerciceTest_endpoint?wsdl");  
		try {
			System.out.println(client.invoke("getUserByName", "123")[0]);
//			System.out.println(client.invoke("test3")[0]);
			System.out.println("getuser"+client.invoke("getuser", "123", "456")[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
		Client client = factory.createClient("http://localhost:8080/kensite/webservice/webSerciceTest_endpoint?wsdl"); // 记得要加入"?wsdl"
		QName opName = new QName("http://service.com/", "getUserByName"); // 指定到接口类所在包
		Object[] res = client.invoke(opName, "猪打天下");
		System.out.println("Say: " + res[0]);
		 */
	}
}
