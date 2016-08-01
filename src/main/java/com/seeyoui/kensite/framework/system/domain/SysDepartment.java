/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.system.domain;  

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysDepartment extends DataEntity<SysDepartment> {
    private static final long serialVersionUID = 5454155825314635342L;
    
    private String parentId;
//    @Range(min = 1, max = 100, message="长度不对")
    @Range(min = 1, max = 100)
    private Long sequence;
    private String name;
    @Length(min = 6, max = 8)
    private String code;
    
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    
    public String getParentId() {
        return this.parentId;
    }
    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }
    
    public Long getSequence() {
        return this.sequence;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getCode() {
        return this.code;
    }
}