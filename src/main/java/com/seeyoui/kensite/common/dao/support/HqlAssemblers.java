package com.seeyoui.kensite.common.dao.support;

public class HqlAssemblers {
	private StringBuffer buf = new StringBuffer();
	
	private boolean first = true;
	
	private String start;
	
	private String connector;
	
	public HqlAssemblers(String start, String connector) {
		this.start = start;
		this.connector = connector;
	}
	
	public void append(String fragment) {
		if (fragment == null)
			return;
		
		if (first) {
			buf.append(start);
			
			first = false;
		} else
			buf.append(connector);
		
		buf.append(fragment);
	}
	
	public String toString() {
		return buf.toString();
	}
}