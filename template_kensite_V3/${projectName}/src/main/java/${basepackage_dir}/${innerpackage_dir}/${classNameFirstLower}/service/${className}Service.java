<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className> 
<#assign classNameLower = className?uncap_first>
package ${basepackage}.${innerpackage}.${table.classNameFirstLower}.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${basepackage}.common.base.service.BaseService;

import java.util.*;

import ${basepackage}.common.base.domain.EasyUIDataGrid;
import ${basepackage}.common.base.service.BaseService;
import ${basepackage}.common.exception.CRUDException;
import ${basepackage}.common.util.*;
import ${basepackage}.common.constants.StringConstant;
import ${basepackage}.${innerpackage}.${table.classNameFirstLower}.domain.${className};
import ${basepackage}.${innerpackage}.${table.classNameFirstLower}.persistence.${className}Mapper;

<#include "/java_imports.include">

@Service
public class ${className}Service extends BaseService {
	
	@Autowired
	private ${className}Mapper ${classNameLower}Mapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public ${className} findOne(String id) throws CRUDException{
		return ${classNameLower}Mapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param ${classNameLower}
	 * @return
	 * @throws CRUDException
	 */
	public List<${className}> findList(${className} ${classNameLower}) throws CRUDException {
		return ${classNameLower}Mapper.findList(${classNameLower});
	}
	
	/**
	 * 查询所有数据集合
	 * @param ${classNameLower}
	 * @return
	 * @throws CRUDException
	 */
	public List<${className}> findAll(${className} ${classNameLower}) throws CRUDException {
		return ${classNameLower}Mapper.findAll(${classNameLower});
	}
	
	/**
	 * 查询数据总数
	 * @param ${classNameLower}
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(${className} ${classNameLower}) throws CRUDException {
		return ${classNameLower}Mapper.findTotal(${classNameLower});
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param ${classNameLower}
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(${className} ${classNameLower}) throws CRUDException {
		return ${classNameLower}Mapper.findExTotal(${classNameLower});
	}
	
	/**
	 * 数据新增
	 * @param ${classNameLower}
	 * @throws CRUDException
	 */
	public void save(${className} ${classNameLower}) throws CRUDException{
		${classNameLower}.preInsert();
		${classNameLower}Mapper.save(${classNameLower});
	}
	
	/**
	 * 数据修改
	 * @param ${classNameLower}
	 * @throws CRUDException
	 */
	public void update(${className} ${classNameLower}) throws CRUDException{
		${classNameLower}.preUpdate();
		${classNameLower}Mapper.update(${classNameLower});			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		${classNameLower}Mapper.delete(listId);
	}
	
	/**
	 * 数据假删除
	 * @param ${classNameLower}
	 * @throws CRUDException
	 */
	public void remove(${className} ${classNameLower}) throws CRUDException{
		${classNameLower}.preUpdate();
		${classNameLower}Mapper.remove(${classNameLower});			
	}
	
	<#if (lucene=="Y") >
	/**
	 * 全文检索查询所有数据集合
	 * @param listId
	 * @return
	 * @throws CRUDException
	 */
	public List<${className}> findLucene(List<String> listId) throws CRUDException {
		return ${classNameLower}Mapper.findLucene(listId);
	}
	</#if>
}