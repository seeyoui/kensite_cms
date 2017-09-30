/**
 * 
 */
package com.seeyoui.kensite.common.dao.simple;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.seeyoui.kensite.common.base.domain.BaseEntity;
import com.seeyoui.kensite.common.dao.Condition;
import com.seeyoui.kensite.common.dao.Dao;
import com.seeyoui.kensite.common.dao.Result;
import com.seeyoui.kensite.common.dao.SqlFieldMetaData;
import com.seeyoui.kensite.common.dao.exception.ServiceException;
import com.seeyoui.kensite.common.dao.hibernate.BatchSqlExecuteByParamListWork;
import com.seeyoui.kensite.common.dao.hibernate.BatchSqlExecuteWork;
import com.seeyoui.kensite.common.dao.hibernate.SqlExecuteWork;
import com.seeyoui.kensite.common.dao.hibernate.SqlQueryMetaDataWork;
import com.seeyoui.kensite.common.dao.hibernate.SqlQueryWork;
import com.seeyoui.kensite.common.dao.hibernate.Work;
import com.seeyoui.kensite.common.dao.support.ConvertToQuestionParamSqlHelper;
import com.seeyoui.kensite.common.dao.support.SqlUtils;
import com.seeyoui.kensite.common.dao.util.FieldTokenProcessor;

/**
 * 简单DAO借口实现，传入DataSource对象，针对DataSource对象提供SQL操作
 * 
 * 
 * @author zouxuemo
 *
 */
public class SmartDao implements Dao {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	private DataSource dataSource = null;
	private JdbcTemplate jdbcTemplate = null;
	
	/**
	 * 对于要求分解成多次执行的sql语句，这里定义每个sql语句之间的分隔符
	 */
	private char multiSqlDelim = ';';
	
	/**
	 * 连接数据库标识，支持标识见Dao接口类的数据库标识名称常量定义
	 */
	private String databaseIdentity;
	
	/**
	 * 连接数据库版本号
	 */
	private String databaseVersion;
	
	private FieldTokenProcessor colonParamProcessor = new FieldTokenProcessor(':', FieldTokenProcessor.NO_VARIABLE_SYMBOL);

	public SmartDao() {
		
	}
	
	public SmartDao(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
		DatabaseMetaData metaData = getMetaData();
		
		try {
			String databaseName = metaData.getDatabaseProductName().toLowerCase();
			if (databaseName == null) { 
				databaseIdentity = DATABASE_UNKONW;
			} else if (databaseName.indexOf("oracle") != -1) { 
				databaseIdentity = DATABASE_ORACLE;
			} else if (databaseName.indexOf("postgresql") != -1) { 
				databaseIdentity = DATABASE_POSTGRESQL;
			} else if (databaseName.toLowerCase().indexOf("db2") != -1) { 
				databaseIdentity = DATABASE_DB2;
			} else if (databaseName.indexOf("sql server") != -1) { 
				databaseIdentity = DATABASE_MSSQL;
			} else if (databaseName.indexOf("mysql") != -1) { // "MySQL" 
				databaseIdentity = DATABASE_MYSQL;
			} else if (databaseName.indexOf("hsql") != -1) { 
				databaseIdentity = DATABASE_HSQL;
			} else if (databaseName.indexOf("sap") != -1) { // "SAP DB" 
				databaseIdentity = DATABASE_SAP;
			} else if (databaseName.indexOf("firebird") != -1) { // "firebird" 
				databaseIdentity = DATABASE_FIREBIRD;
			} else if (databaseName.indexOf("adaptive") != -1) { // "DATABASE_SYBASE" 
				databaseIdentity = DATABASE_SYBASE;
			} else { 
				databaseIdentity = DATABASE_UNKONW;
			}
		} catch (SQLException e) {
			databaseIdentity = DATABASE_UNKONW;
		} 
		
		try {
			databaseVersion = metaData.getDatabaseProductVersion();
		} catch (SQLException e) {
			databaseVersion = DATABASE_UNKONW;
		}
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#get(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public <T extends BaseEntity> T get(Class<T> clazz, Serializable id) {
		throw new ServiceException("{get(Class<T> clazz, Serializable id)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#gets(java.lang.Class, java.io.Serializable[])
	 */
	@Override
	public <T extends BaseEntity> List<T> gets(Class<T> clazz,
			Serializable[] ids) {
		throw new ServiceException("{gets(Class<T> clazz, Serializable[] ids)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#query(java.lang.Class, com.ieslab.dap.dao.Condition)
	 */
	@Override
	public <T extends BaseEntity> List<T> query(Class<T> clazz,
			Condition condition) {
		throw new ServiceException("{query(Class<T> clazz, Condition condition)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#query(java.lang.String, java.lang.Object[])
	 */
	@Override
	public <T extends BaseEntity> List<T> query(String hql, Object... values) {
		throw new ServiceException("{query(String hql, Object... values)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#query(java.lang.String, java.util.Map)
	 */
	@Override
	public <T extends BaseEntity> List<T> query(String hql,
			Map<String, ?> values) {
		throw new ServiceException("{query(String hql, Map<String, ?> values)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#pageQuery(java.lang.String, int, int, java.lang.Object[])
	 */
	@Override
	public <T extends BaseEntity> List<T> pageQuery(String hql, int start,
			int limit, Object... values) {
		throw new ServiceException("{pageQuery(String hql, int start, int limit, Object... values)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#pageQuery(java.lang.String, int, int, java.util.Map)
	 */
	@Override
	public <T extends BaseEntity> List<T> pageQuery(String hql, int start,
			int limit, Map<String, ?> values) {
		throw new ServiceException("{pageQuery(String hql, int start, int limit, Map<String, ?> values)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#count(java.lang.Class, com.ieslab.dap.dao.Condition)
	 */
	@Override
	public long count(Class<?> clazz, Condition condition) {
		throw new ServiceException("{count(Class<?> clazz, Condition condition)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#queryResult(java.lang.Class, com.ieslab.dap.dao.Condition)
	 */
	@Override
	public <T extends BaseEntity> Result<T> queryResult(Class<T> clazz,
			Condition condition) {
		throw new ServiceException("{queryResult(Class<T> clazz, Condition condition)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sum(java.lang.Class, java.lang.String, com.ieslab.dap.dao.Condition)
	 */
	@Override
	public Number sum(Class<?> clazz, String field, Condition condition) {
		throw new ServiceException("{sum(Class<?> clazz, String field, Condition condition)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#avg(java.lang.Class, java.lang.String, com.ieslab.dap.dao.Condition)
	 */
	@Override
	public double avg(Class<?> clazz, String field, Condition condition) {
		throw new ServiceException("{avg(Class<?> clazz, String field, Condition condition)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#max(java.lang.Class, java.lang.String, com.ieslab.dap.dao.Condition)
	 */
	@Override
	public <X> X max(Class<?> clazz, String field, Condition condition) {
		throw new ServiceException("{max(Class<?> clazz, String field, Condition condition)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#min(java.lang.Class, java.lang.String, com.ieslab.dap.dao.Condition)
	 */
	@Override
	public <X> X min(Class<?> clazz, String field, Condition condition) {
		throw new ServiceException("{min(Class<?> clazz, String field, Condition condition)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#unique(java.lang.Class, java.lang.String, com.ieslab.dap.dao.Condition)
	 */
	@Override
	public <X> X unique(Class<?> clazz, String field, Condition condition) {
		throw new ServiceException("{unique(Class<?> clazz, String field, Condition condition)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#unique(java.lang.String, java.lang.Object[])
	 */
	@Override
	public <X> X unique(String hql, Object... values) {
		throw new ServiceException("{unique(String hql, Object... values)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#unique(java.lang.String, java.util.Map)
	 */
	@Override
	public <X> X unique(String hql, Map<String, ?> values) {
		throw new ServiceException("{unique(String hql, Map<String, ?> values)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#values(java.lang.Class, java.lang.String, com.ieslab.dap.dao.Condition)
	 */
	@Override
	public <X> List<X> values(Class<?> clazz, String field, Condition condition) {
		throw new ServiceException("{values(Class<?> clazz, String field, Condition condition)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#multiValues(java.lang.Class, java.lang.String[], com.ieslab.dap.dao.Condition)
	 */
	@Override
	public List<Object[]> multiValues(Class<?> clazz, String[] fields,
			Condition condition) {
		throw new ServiceException("{multiValues(Class<?> clazz, String[] fields, Condition condition)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#flush()
	 */
	@Override
	public void flush() {
		throw new ServiceException("{flush()}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#reload(com.ieslab.dap.entity.BaseEntity)
	 */
	@Override
	public <T extends BaseEntity> void reload(T entity) {
		throw new ServiceException("{reload(T entity)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#saveOrUpdate(com.ieslab.dap.entity.BaseEntity)
	 */
	@Override
	public <T extends BaseEntity> void saveOrUpdate(T entity) {
		throw new ServiceException("{saveOrUpdate(T entity)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#batchSaveOrUpdate(com.ieslab.dap.entity.BaseEntity[])
	 */
	@Override
	public <T extends BaseEntity> void batchSaveOrUpdate(T[] entitys) {
		throw new ServiceException("{batchSaveOrUpdate(T[] entitys)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#remove(com.ieslab.dap.entity.BaseEntity)
	 */
	@Override
	public <T extends BaseEntity> void remove(T entity) {
		throw new ServiceException("{remove(T entity)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#remove(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public <T extends BaseEntity> void remove(Class<T> clazz, Serializable id) {
		throw new ServiceException("{remove(Class<T> clazz, Serializable id)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#batchRemove(java.lang.Class, java.io.Serializable[])
	 */
	@Override
	public <T extends BaseEntity> int batchRemove(Class<T> clazz,
			Serializable[] ids) {
		throw new ServiceException("{batchRemove(Class<T> clazz, Serializable[] ids)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#batchRemove(java.lang.Class, com.ieslab.dap.dao.Condition)
	 */
	@Override
	public <T extends BaseEntity> int batchRemove(Class<T> clazz,
			Condition condition) {
		throw new ServiceException("{batchRemove(Class<T> clazz, Condition condition)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#batchUpdate(java.lang.Class, java.lang.String[], java.lang.Object[], com.ieslab.dap.dao.Condition)
	 */
	@Override
	public <T extends BaseEntity> int batchUpdate(Class<T> clazz,
			String[] fields, Object[] values, Condition condition) {
		throw new ServiceException("{batchUpdate(Class<T> clazz, String[] fields, Object[] values, Condition condition)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#execute(java.lang.String, java.lang.Object[])
	 */
	@Override
	public int execute(String hql, Object... values) {
		throw new ServiceException("{execute(String hql, Object... values)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#execute(java.lang.String, java.util.Map)
	 */
	@Override
	public int execute(String hql, Map<String, ?> values) {
		throw new ServiceException("{execute(String hql, Map<String, ?> values)}方法不支持！");
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlQuery(java.lang.String, com.ieslab.dap.dao.Condition)
	 */
	@Override
	public List<Map<String, Object>> sqlQuery(String sql, Condition condition) {
		ConvertToQuestionParamSqlHelper helper = new ConvertToQuestionParamSqlHelper(databaseIdentity, databaseVersion, sql, condition, true);
		return sqlPageQuery(helper.getSql(), condition.getStart(), condition.getLimit(), helper.getValues());
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlQuery(java.lang.String, java.lang.Object[])
	 */
	@Override
	public List<Map<String, Object>> sqlQuery(String sql, Object... values) {
		return sqlPageQuery(sql, 0, 0, values);
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlQuery(java.lang.String, java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> sqlQuery(String sql,
			Map<String, Object> params) {
		ConvertToQuestionParamSqlHelper helper = new ConvertToQuestionParamSqlHelper(databaseIdentity, databaseVersion, sql, params);
		return sqlPageQuery(helper.getSql(), 0, 0, helper.getValues());
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlQueryMetaData(java.lang.String, com.ieslab.dap.dao.Condition)
	 */
	@Override
	public List<SqlFieldMetaData> sqlQueryMetaData(String sql,
			Condition condition) {
		ConvertToQuestionParamSqlHelper helper = new ConvertToQuestionParamSqlHelper(databaseIdentity, databaseVersion, sql, condition, true);
		return sqlQueryMetaData(helper.getSql(), helper.getValues());
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlQueryMetaData(java.lang.String, java.util.Map)
	 */
	@Override
	public List<SqlFieldMetaData> sqlQueryMetaData(String sql,
			Map<String, Object> params) {
		ConvertToQuestionParamSqlHelper helper = new ConvertToQuestionParamSqlHelper(databaseIdentity, databaseVersion, sql, params);
		return sqlQueryMetaData(helper.getSql(), helper.getValues());
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlQueryMetaData(java.lang.String, java.lang.Object[])
	 */
	@Override
	public List<SqlFieldMetaData> sqlQueryMetaData(String sql, Object... values) {
		Assert.hasText(sql, "sql不能为空");
		
		SqlQueryMetaDataWork work = new SqlQueryMetaDataWork(sql, values);

		try {
			executeWork(work);
		} catch (SQLException e) {
			throw new ServiceException("执行SQL查询失败 - " + sql, e);
		}
		
		return work.getFieldMetaDatas();
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlPageQuery(java.lang.String, int, int, java.lang.Object[])
	 */
	@Override
	public List<Map<String, Object>> sqlPageQuery(String sql, int start,
			int limit, Object... values) {
		ColumnMapRowMapper mapper = new ColumnMapRowMapper();
		return sqlPageQuery(sql, mapper, start, limit, values);
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlPageQuery(java.lang.String, int, int, java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> sqlPageQuery(String sql, int start,
			int limit, Map<String, Object> params) {
		ConvertToQuestionParamSqlHelper helper = new ConvertToQuestionParamSqlHelper(databaseIdentity, databaseVersion, sql, params);
		return sqlPageQuery(helper.getSql(), start, limit, helper.getValues());
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlQuery(java.lang.Class, java.lang.String, com.ieslab.dap.dao.Condition)
	 */
	@Override
	public <T> List<T> sqlQuery(Class<T> clazz, String sql, Condition condition) {
		ConvertToQuestionParamSqlHelper helper = new ConvertToQuestionParamSqlHelper(databaseIdentity, databaseVersion, sql, condition, true);
		return sqlPageQuery(clazz, helper.getSql(), condition.getStart(), condition.getLimit(), helper.getValues());
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlQuery(java.lang.Class, java.lang.String, java.lang.Object[])
	 */
	@Override
	public <T> List<T> sqlQuery(Class<T> clazz, String sql, Object... values) {
		return sqlPageQuery(clazz, sql, 0, 0, values);
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlQuery(java.lang.Class, java.lang.String, java.util.Map)
	 */
	@Override
	public <T> List<T> sqlQuery(Class<T> clazz, String sql,
			Map<String, Object> params) {
		ConvertToQuestionParamSqlHelper helper = new ConvertToQuestionParamSqlHelper(databaseIdentity, databaseVersion, sql, params);
		return sqlPageQuery(clazz, helper.getSql(), 0, 0, helper.getValues());
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlPageQuery(java.lang.Class, java.lang.String, int, int, java.lang.Object[])
	 */
	@Override
	public <T> List<T> sqlPageQuery(Class<T> clazz, String sql, int start,
			int limit, Object... values) {
		BeanPropertyRowMapper<T> mapper = new BeanPropertyRowMapper<T>(clazz);
		mapper.setPrimitivesDefaultedForNullValue(true);
		
		return sqlPageQuery(sql, mapper, start, limit, values);
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlPageQuery(java.lang.Class, java.lang.String, int, int, java.util.Map)
	 */
	@Override
	public <T> List<T> sqlPageQuery(Class<T> clazz, String sql, int start,
			int limit, Map<String, Object> params) {
		ConvertToQuestionParamSqlHelper helper = new ConvertToQuestionParamSqlHelper(databaseIdentity, databaseVersion, sql, params);
		return sqlPageQuery(clazz, helper.getSql(), start, limit, helper.getValues());
	}

	protected <T> List<T> sqlPageQuery(String sql, RowMapper<T> mapper, int start,
			int limit, Object... values) {
		Assert.hasText(sql, "sql不能为空");
		Assert.isTrue(start >= 0, "start必须为0或者正数");
		Assert.isTrue(limit >= 0, "limit必须为0或者正数");
		
		if(logger.isDebugEnabled())
			logger.debug("query sql{{}}, param: {}, start: {}, limit: {}", new Object[]{sql, values, start, limit});
		
		SqlQueryWork<T> work = new SqlQueryWork<T>(sql, mapper, start, limit, values);
		
		try {
			executeWork(work);
		} catch (SQLException e) {
			throw new ServiceException("执行SQL查询失败 - " + sql, e);
		}
		
		return work.getResult();
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlCount(java.lang.String, com.ieslab.dap.dao.Condition)
	 */
	@Override
	public long sqlCount(String sql, Condition condition) {
		Assert.hasText(sql, "sql不能为空");

		ConvertToQuestionParamSqlHelper helper = new ConvertToQuestionParamSqlHelper(databaseIdentity, databaseVersion, sql, condition, false);
		
		sql = "select count(0) as cnt from (" + helper.getSql() + ") mytable";
		List<Map<String, Object>> result = sqlPageQuery(sql, 0, 0, helper.getValues());
		
		return ((Number)result.get(0).get("cnt")).longValue();
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlCount(java.lang.String, java.lang.Object[])
	 */
	@Override
	public long sqlCount(String sql, Object... values) {
		Assert.hasText(sql, "sql不能为空");
		
		int index = StringUtils.lastIndexOf(sql, "order by");
		if (index >= 0)
			sql = sql.substring(0, index);
		
		sql = "select count(0) as cnt from (" + sql + ") mytable";
		List<Map<String, Object>> result = sqlPageQuery(sql, 0, 0, values);
		
		return ((Number)result.get(0).get("cnt")).longValue();
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlCount(java.lang.String, java.util.Map)
	 */
	@Override
	public long sqlCount(String sql, Map<String, Object> params) {
		Assert.hasText(sql, "sql不能为空");
		
		ConvertToQuestionParamSqlHelper helper = new ConvertToQuestionParamSqlHelper(databaseIdentity, databaseVersion, sql, params);
		return sqlCount(helper.getSql(), helper.getValues());
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlQueryResult(java.lang.String, com.ieslab.dap.dao.Condition)
	 */
	public Result<Map<String, Object>> sqlQueryResult(String sql,
			Condition condition) {
		long count = sqlCount(sql, condition);
		List<Map<String, Object>> data = sqlQuery(sql, condition);
		
		return new Result<Map<String, Object>>(count, condition.getStart(), condition.getLimit(), data);
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlQueryResult(java.lang.Class, java.lang.String, com.ieslab.dap.dao.Condition)
	 */
	public <T> Result<T> sqlQueryResult(Class<T> clazz, String sql,
			Condition condition) {
		long count = sqlCount(sql, condition);
		List<T> data = sqlQuery(clazz, sql, condition);
		
		return new Result<T>(count, condition.getStart(), condition.getLimit(), data);
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlUnique(java.lang.String, com.ieslab.dap.dao.Condition)
	 */
	public <X> X sqlUnique(String sql, Condition condition) {
		Assert.hasText(sql, "sql不能为空");
		
		ConvertToQuestionParamSqlHelper helper = new ConvertToQuestionParamSqlHelper(databaseIdentity, databaseVersion, sql, condition, true);
		return sqlUnique(helper.getSql(), helper.getValues());
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlUnique(java.lang.String, java.util.Map)
	 */
	public <X> X sqlUnique(String sql, Map<String, Object> params) {
		Assert.hasText(sql, "sql不能为空");
		
		ConvertToQuestionParamSqlHelper helper = new ConvertToQuestionParamSqlHelper(databaseIdentity, databaseVersion, sql, params);
		return sqlUnique(helper.getSql(), helper.getValues());
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlUnique(java.lang.String, java.lang.Object[])
	 */
	public <X> X sqlUnique(String sql, Object... values) {
		Assert.hasText(sql, "sql不能为空");
		
		List<Map<String, Object>> result = sqlPageQuery(sql, 0, 0, values);
		if (result.size() == 0)
			return (X)null;
		
		Map<String, Object> record = result.get(0);
		if (record.size() != 1)
			throw new RuntimeException("要检索的数据结果字段不是唯一字段！");
		
		return (X)record.values().iterator().next();
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlValues(java.lang.String, com.ieslab.dap.dao.Condition)
	 */
	public <X> List<X> sqlValues(String sql, Condition condition) {
		Assert.hasText(sql, "sql不能为空");
		
		ConvertToQuestionParamSqlHelper helper = new ConvertToQuestionParamSqlHelper(databaseIdentity, databaseVersion, sql, condition, true);
		return sqlValues(helper.getSql(), helper.getValues());
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlValues(java.lang.String, java.lang.Object[])
	 */
	public <X> List<X> sqlValues(String sql, Object... values) {
		Assert.hasText(sql, "sql不能为空");
		
		List<X> data = new ArrayList<X>();
		
		List<Map<String, Object>> result = sqlPageQuery(sql, 0, 0, values);
		if (result.size() == 0)
			return data;
		
		if (result.get(0).size() != 1)
			throw new RuntimeException("要检索的数据结果字段不是唯一字段！");

		for (Map<String, Object> record : result)
			data.add((X)record.values().iterator().next());
		
		return data;
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlValues(java.lang.String, java.util.Map)
	 */
	public <X> List<X> sqlValues(String sql, Map<String, Object> params) {
		Assert.hasText(sql, "sql不能为空");
		
		ConvertToQuestionParamSqlHelper helper = new ConvertToQuestionParamSqlHelper(databaseIdentity, databaseVersion, sql, params);
		return sqlValues(helper.getSql(), helper.getValues());
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlMultiValues(java.lang.String, com.ieslab.dap.dao.Condition)
	 */
	public List<Object[]> sqlMultiValues(String sql, Condition condition) {
		Assert.hasText(sql, "sql不能为空");
		
		ConvertToQuestionParamSqlHelper helper = new ConvertToQuestionParamSqlHelper(databaseIdentity, databaseVersion, sql, condition, true);
		return sqlMultiValues(helper.getSql(), helper.getValues());
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlMultiValues(java.lang.String, java.lang.Object[])
	 */
	public List<Object[]> sqlMultiValues(String sql, Object... values) {
		Assert.hasText(sql, "sql不能为空");
		
		List<Object[]> data = new ArrayList<Object[]>();
		
		List<Map<String, Object>> result = sqlPageQuery(sql, 0, 0, values);
		if (result.size() == 0)
			return data;

		for (Map<String, Object> record : result)
			data.add(record.values().toArray());
		
		return data;
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlMultiValues(java.lang.String, java.util.Map)
	 */
	public List<Object[]> sqlMultiValues(String sql, Map<String, Object> params) {
		Assert.hasText(sql, "sql不能为空");
		
		ConvertToQuestionParamSqlHelper helper = new ConvertToQuestionParamSqlHelper(databaseIdentity, databaseVersion, sql, params);
		return sqlMultiValues(helper.getSql(), helper.getValues());
	}
	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlExecute(java.lang.String, com.ieslab.dap.dao.Condition)
	 */
	public void sqlExecute(String sql, Condition condition) {
		Assert.hasText(sql, "sql不能为空");
		
		ConvertToQuestionParamSqlHelper helper = new ConvertToQuestionParamSqlHelper(databaseIdentity, databaseVersion, sql, condition, false);
		sqlExecute(helper.getSql(), helper.getValues());
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlExecute(java.lang.String, java.lang.Object[])
	 */
	public void sqlExecute(String sql, Object... values) {
		Assert.hasText(sql, "sql不能为空");
		
		SqlExecuteWork work = new SqlExecuteWork(sql, values);
		
		if(logger.isDebugEnabled())
			logger.debug("execute sql{{}}, param: {}", sql, values);
		
		try {
			executeWork(work);
		} catch (SQLException e) {
			throw new ServiceException("执行SQL操作失败 - " + sql, e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlExecute(java.lang.String, java.util.Map)
	 */
	public void sqlExecute(String sql, Map<String, Object> params) {
		Assert.hasText(sql, "sql不能为空");
		
		ConvertToQuestionParamSqlHelper helper = new ConvertToQuestionParamSqlHelper(databaseIdentity, databaseVersion, sql, params);
		sqlExecute(helper.getSql(), helper.getValues());
	}

	public long sqlInsert(String sql, Object... values) {
		Assert.hasText(sql, "sql不能为空");
		Assert.isTrue(sql.length() > 6 && sql.substring(0, 6).toLowerCase().equals("insert"), "sql必须是INSERT语句");
		
		SqlExecuteWork work = new SqlExecuteWork(sql, values);
		work.setDatabaseIdentity(getDatabaseIdentity());
		work.setNeedReturnGeneratedKey(true);
		
		if(logger.isDebugEnabled())
			logger.debug("insert sql{{}}, param: {}", sql, values);
		
		try {
			executeWork(work);
		} catch (SQLException e) {
			throw new ServiceException("执行SQL Insert操作失败 - " + sql, e);
		}
		
		return work.getAutoIncKey();
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#sqlInsert(java.lang.String, java.util.Map)
	 */
	@Override
	public long sqlInsert(String sql, Map<String, Object> params) {
		Assert.hasText(sql, "sql不能为空");
		
		ConvertToQuestionParamSqlHelper helper = new ConvertToQuestionParamSqlHelper(databaseIdentity, databaseVersion, sql, params);
		return sqlInsert(helper.getSql(), helper.getValues());
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#batchSqlExecute(java.lang.String[])
	 */
	@Override
	public void batchSqlExecute(String[] sqls) {
		Assert.notEmpty(sqls, "sql不能为空");
		
		BatchSqlExecuteWork work = new BatchSqlExecuteWork(sqls);
		
		if(logger.isDebugEnabled())
			logger.debug("batch execute sql{{}}", sqls);
		
		try {
			executeWork(work);
		} catch (SQLException e) {
		throw new ServiceException("执行SQL批量操作失败 - " + StringUtils.join(sqls, "\r\n"), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#batchSqlExecute(java.lang.String[], java.util.Map)
	 */
	@Override
	public void batchSqlExecute(String[] sqls, Map<String, Object> params) {
		Assert.notEmpty(sqls, "sql不能为空");
		
		for (String sql : sqls) {
			sqlExecute(sql, params);
		}
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#batchSqlExecute(java.lang.String)
	 */
	@Override
	public void batchSqlExecute(String sql) {
		Assert.hasText(sql, "sql不能为空");
		
		List<String> statements = new ArrayList<String>();
		SqlUtils.splitSqlScript(sql, multiSqlDelim, statements);
		
		batchSqlExecute(statements.toArray(new String[0]));
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#batchSqlExecute(java.lang.String, java.util.Map)
	 */
	@Override
	public void batchSqlExecute(String sql, Map<String, Object> params) {
		Assert.hasText(sql, "sql不能为空");
		
		List<String> statements = new ArrayList<String>();
		SqlUtils.splitSqlScript(sql, multiSqlDelim, statements);
		
		batchSqlExecute(statements.toArray(new String[0]), params);
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#batchSqlExecuteByParamIndexList(java.lang.String, java.util.List)
	 */
	@Override
	public void batchSqlExecuteByParamIndexList(String sql,
			List<Object[]> paramList) {
		Assert.hasText(sql, "sql不能为空");
		
		BatchSqlExecuteByParamListWork work = new BatchSqlExecuteByParamListWork(sql, paramList);
		
		if(logger.isDebugEnabled())
			logger.debug("batch execute sql{{}}, param: {}", sql, paramList);
		
		try {
			executeWork(work);
		} catch (SQLException e) {
			throw new ServiceException("执行SQL批量操作失败 - " + sql, e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#batchSqlExecuteByParamNameList(java.lang.String, java.util.List)
	 */
	@Override
	public void batchSqlExecuteByParamNameList(String sql,
			List<Map<String, Object>> paramList) {
		Assert.hasText(sql, "sql不能为空");
		
		//搜索所有以":"开头的参数变量名集合
		List<String> list = colonParamProcessor.searchFieldToken(sql, false);
		String[] paramNames = list.toArray(new String[0]);
		int length = paramNames.length;
		
		//把sql中所有以":"开头的参数变量名替换为"?"
		Object[] replaces = new String[length];
		for (int index = 0; index < length; index++)
			replaces[index] = "?";
		sql = colonParamProcessor.replaceFieldToken(sql, replaces);
		
		BatchSqlExecuteByParamListWork work = new BatchSqlExecuteByParamListWork(sql);
		
		//循环变量值Map集合，根据搜索到的参数变量名集合，生成对应的变量值集合
		for (Map<String, Object> map : paramList) {
			Object[] params = new Object[length];
			
			for (int index = 0; index < length; index++)
				params[index] = map.get(paramNames[index]);
			
			work.addParam(params);
		}
		
		if(logger.isDebugEnabled())
			logger.debug("batch execute sql{{}}, param: {}", sql, paramList);
		
		try {
			executeWork(work);
		} catch (SQLException e) {
			throw new ServiceException("执行SQL批量操作失败 - " + sql, e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#getMetaData()
	 */
	@Override
	public DatabaseMetaData getMetaData() {
		Connection connection = null;
		try {
			connection = DataSourceUtils.getConnection(dataSource);
			
			return connection.getMetaData();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
	}
	
//	private Connection getConnection() throws SQLException {
//		// TODO 需要考虑连接失效问题，及连接事务问题
//		
//		return dataSource.getConnection();
//	}
	
	private void executeWork(Work work) throws SQLException {
		Connection connection = DataSourceUtils.getConnection(dataSource);
		
		try {
			work.execute(connection);
		} finally {
			DataSourceUtils.releaseConnection(connection, dataSource);
		}
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#getDatabaseIdentity()
	 */
	@Override
	public String getDatabaseIdentity() {
		return databaseIdentity;
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#getDatabaseVersion()
	 */
	@Override
	public String getDatabaseVersion() {
		return databaseVersion;
	}

	/* (non-Javadoc)
	 * @see com.ieslab.dap.dao.Dao#clearThreadSession()
	 */
	@Override
	public void clearThreadSession() {
		throw new ServiceException("{clearThreadSession()}方法不支持！");
	}

}
