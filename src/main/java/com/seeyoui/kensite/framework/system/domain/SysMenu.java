/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.system.domain;  

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysMenu extends DataEntity<SysMenu> {
    private static final long serialVersionUID = 5454155825314635342L;  
      
    private String parentId;
    private String name;
    private String url;
    private Long sequence;
    private String icon;
    private String target;
    private String state;
 
    public void setParentId(String parentId) {  
        this.parentId = parentId;  
    }  
      
    public String getParentId() {  
        return this.parentId;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
      
    public String getName() {  
        return this.name;  
    }  
    public void setUrl(String url) {  
        this.url = url;  
    }  
      
    public String getUrl() {  
        return this.url;  
    }  
    public void setSequence(Long sequence) {  
        this.sequence = sequence;  
    }  
      
    public Long getSequence() {  
        return this.sequence;  
    }  
    public void setIcon(String icon) {  
        this.icon = icon;  
    }  
      
    public String getIcon() {  
        return this.icon;  
    }  
    public void setTarget(String target) {  
        this.target = target;  
    }  
      
    public String getTarget() {  
        return this.target;  
    }

	public String getState() {
		return "closed";
	}

	public void setState(String state) {
		this.state = state;
	}  
}