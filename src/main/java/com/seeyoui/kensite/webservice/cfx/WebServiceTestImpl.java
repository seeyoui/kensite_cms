package com.seeyoui.kensite.webservice.cfx;


public class WebServiceTestImpl implements WebserviceTest {

	@Override
	public String getUserByName(String username) {
		return "Hello:" + username;
	}

	@Override
	public void setUser(String username) {
		System.out.println("username:" + username);
	}

	@Override
	public boolean getuser(String name, String password) {
		return false;
	}

	@Override
	public boolean test3() {
		return false;
	}

}