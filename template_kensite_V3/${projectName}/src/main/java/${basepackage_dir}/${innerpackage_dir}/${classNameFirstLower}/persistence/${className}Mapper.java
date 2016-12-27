<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className> 
<#assign classNameLower = className?uncap_first>

package ${basepackage}.${innerpackage}.${table.classNameFirstLower}.persistence;

import ${basepackage}.common.base.domain.EasyUIDataGrid;
import ${basepackage}.${innerpackage}.${table.classNameFirstLower}.domain.${className};

import java.util.*;

import com.seeyoui.kensite.common.exception.CRUDException;

<#include "/java_imports.include">

public interface ${className}Mapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public ${className} findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param ${classNameLower}
	 * @return
	 */
	public List<${className}> findList(${className} ${classNameLower});
	
	/**
	 * 查询所有数据集合
	 * @param ${classNameLower}
	 * @return
	 */
	public List<${className}> findAll(${className} ${classNameLower});
	
	/**
	 * 查询数据总数
	 * @param ${classNameLower}
	 * @return
	 */
	public int findTotal(${className} ${classNameLower});

	/**
	 * 查询数据总数排除当前数据
	 * @param ${classNameLower}
	 * @return
	 */
	public int findExTotal(${className} ${classNameLower});
	
	/**
	 * 数据新增
	 * @param ${classNameLower}
	 */
	public void save(${className} ${classNameLower});
	
	/**
	 * 数据修改
	 * @param ${classNameLower}
	 */
	public void update(${className} ${classNameLower});
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
	<#if (lucene=="Y") >
	/**
	 * 全文检索查询所有数据集合
	 * @param listId
	 * @return
	 * @throws CRUDException
	 */
	public List<${className}> findLucene(List<String> listId);
	</#if>
}