/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.plugin.dict.domain;  

import com.seeyoui.kensite.common.base.domain.DataEntity;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
public class Dict extends DataEntity<Dict> {
	private static final long serialVersionUID = 5454155825314635342L;
    
	private String value;
	private String label;
	private String category;
	private String description;
	private Long sequence;
	private String parentId;
	private String defaultKey;

	public void setValue(String value) {
		this.value = value;
	}
    
	public String getValue() {
		return this.value;
	}
	public void setLabel(String label) {
		this.label = label;
	}
    
	public String getLabel() {
		return this.label;
	}
	public void setCategory(String category) {
		this.category = category;
	}
    
	public String getCategory() {
		return this.category;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
	public String getDescription() {
		return this.description;
	}
	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}
    
	public Long getSequence() {
		return this.sequence;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
    
	public String getParentId() {
		return this.parentId;
	}

	public String getDefaultKey() {
		return defaultKey;
	}

	public void setDefaultKey(String defaultKey) {
		this.defaultKey = defaultKey;
	}
}