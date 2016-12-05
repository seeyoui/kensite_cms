package com.seeyoui.kensite.framework.websocket.domain;

public class WebMsg {
	private WebUser fromUser;
	private WebUser toUser;
	private String sendType;
	private String sendTo;
	private String message;
	
	public WebUser getFromUser() {
		return fromUser;
	}
	public void setFromUser(WebUser fromUser) {
		this.fromUser = fromUser;
	}
	public WebUser getToUser() {
		return toUser;
	}
	public void setToUser(WebUser toUser) {
		this.toUser = toUser;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public String getSendTo() {
		return sendTo;
	}
	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
