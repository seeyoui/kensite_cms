package com.seeyoui.kensite.webservice.cfx;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface WebserviceTest {
    public String getUserByName(@WebParam(name = "username") String username);
    public void setUser(String username);
    public boolean getuser(String name, String password);
    public boolean test3();
}