/**
 * 
 */
package com.seeyoui.kensite.common.dao;

import java.io.Serializable;
import java.sql.DatabaseMetaData;
import java.util.List;
import java.util.Map;

import com.seeyoui.kensite.common.base.domain.BaseEntity;

/**
 * 数据操作接口，提供针对实体对象的CRUD操作
 *
 * @author zouxuemo
 */
public interface Dao {
	// 数据库标识名称常量定义
	public final static String DATABASE_ORACLE = "oracle";
	public final static String DATABASE_POSTGRESQL = "postgresql";
	public final static String DATABASE_DB2 = "db2";
	public final static String DATABASE_MSSQL = "mssql";
	public final static String DATABASE_MYSQL = "mysql";
	public final static String DATABASE_HSQL = "hsql";
	public final static String DATABASE_SAP = "sap";
	public final static String DATABASE_FIREBIRD = "firebird";
	public final static String DATABASE_SYBASE = "sybase";
	public final static String DATABASE_UNKONW = "unknown";
	
	/**
	 * 检索给定ID的给定类对象，如果给定id的实体对象不存在，返回null
	 * 
	 * @param clazz 要检索的实体类
	 * @param id 要检索的实体类id
	 * @return 
	 */
	public <T extends BaseEntity> T get(final Class<T> clazz, final Serializable id);
	
	/**
	 * 检索给定给定ID数组的给定类对象集合，如果ID数组的某个id对应的实体对象不存在，则忽略；如果都不存在，返回空数据的列表集合(size为0)
	 * 
	 * 
	 * @param clazz 要检索的实体类
	 * @param ids 要检索的实体类id数组
	 * @return 
	 */
	public <T extends BaseEntity> List<T> gets(final Class<T> clazz, final Serializable[] ids);
	
	/**
	 * 检索给定查询条件的给定类对象集合
	 * 
	 * 
	 * @param clazz 要检索的实体类
	 * @param condition 包含查询条件排序条件和分页信息
	 * @return 
	 */
	public <T extends BaseEntity> List<T> query(final Class<T> clazz, final Condition condition);
	
	/**
	 * 执行给定的HQL语句查询，返回查询结果集
	 * 
	 * @param hql
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	public <T extends BaseEntity> List<T> query(final String hql, final Object... values);
	
	/**
	 * 执行给定的HQL语句查询，返回查询结果集
	 * 
	 * @param hql
	 * @param values 命名参数,按名称绑定.
	 * @return
	 */
	public <T extends BaseEntity> List<T> query(final String hql, final Map<String, ?> values);
	
	/**
	 * 执行给定的HQL语句查询，返回指定指定起始条和限定条数的查询结果集
	 * 
	 * @param hql
	 * @param start
	 * @param limit
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	public <T extends BaseEntity> List<T> pageQuery(final String hql, final int start, final int limit, final Object... values);
	
	/**
	 * 执行给定的HQL语句查询，返回指定指定起始条和限定条数的查询结果集
	 * 
	 * @param hql
	 * @param start
	 * @param limit
	 * @param values 命名参数,按名称绑定.
	 * @return
	 */
	public <T extends BaseEntity> List<T> pageQuery(final String hql, final int start, final int limit, final Map<String, ?> values);
	
	/**
	 * 统计给定查询条件的给定类对象记录条数
	 * 
	 * @param clazz 要统计的实体类
	 * @param condition 给定的查询条件（忽略查询条件中的分页参数和排序参数）
	 * @return 返回记录条数
	 */
	public long count(final Class<?> clazz, final Condition condition);
	
	/**
	 * 分页查询，提供查询条件，返回总条数和查询结果集合
	 * 
	 * @param clazz
	 * @param condition
	 * @return
	 */
	public <T extends BaseEntity> Result<T> queryResult(final Class<T> clazz, final Condition condition);
	
	/**
	 * 统计给定查询条件的给定类对象某个字段的合计值
	 * 
	 * @param clazz 要统计的实体类
	 * @param field 要统计的属性名，字段类型必须是数值型（byte, short, int, long, float, double及其包装类）
	 * @param condition 给定的查询条件（忽略查询条件中的分页参数和排序参数）
	 * @return 返回合计值，对于字段为byte, short, int, long类型，返回long，对于字段为float, double，返回double
	 */
	public Number sum(final Class<?> clazz, String field, final Condition condition);
	
	/**
	 * 统计给定查询条件的给定类对象某个字段平均值
	 * 
	 * @param clazz 要统计的实体类
	 * @param field 要统计的属性名，字段类型必须是数值型（byte, short, int, long, float, double及其包装类）
	 * @param condition 给定的查询条件（忽略查询条件中的分页参数和排序参数）
	 * @return 返回平均值
	 */
	public double avg(final Class<?> clazz, String field, final Condition condition);
	
	/**
	 * 统计给定查询条件的给定类对象某个字段的最大值
	 * 
	 * @param clazz 要统计的实体类
	 * @param field 要统计的属性名
	 * @param condition 给定的查询条件（忽略查询条件中的分页参数和排序参数）
	 * @return 返回最大值，返回值类型与字段类型相同
	 */
	public <X> X max(final Class<?> clazz, String field, final Condition condition);
	
	/**
	 * 统计给定查询条件的给定类对象某个字段的最小值
	 * 
	 * @param clazz 要统计的实体类
	 * @param field 要统计的属性名
	 * @param condition 给定的查询条件（忽略查询条件中的分页参数和排序参数）
	 * @return 返回最小值，返回值类型与字段类型相同
	 */
	public <X> X min(final Class<?> clazz, String field, final Condition condition);
	
	/**
	 * 获取给定查询条件下给定字段的唯一值<BR>
	 * 请确保返回值类型与实际数据库表对应字段类型一致
	 * 
	 * @param clazz 要检索的实体类
	 * @param field 要检索的属性名，也可以是表达式，例如：field1*10
	 * @param condition 给定的查询条件（忽略查询条件中的分页参数）
	 * @return 返回查找到的唯一值
	 */
	public <X> X unique(final Class<?> clazz, String field, final Condition condition);
	
	/**
	 * 通过执行hql语句，返回唯一值，支持参数传递，例如：select count(*) from Module where code like ?<BR>
	 * 请确保返回值类型与实际数据库类型一致
	 * 
	 * @param hql
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	public <X> X unique(final String hql, final Object... values);
	
	/**
	 * 通过执行hql语句，返回唯一值，支持参数传递，例如：select count(*) from Module where code like :code<BR>
	 * 请确保返回值类型与实际数据库类型一致
	 * 
	 * @param hql
	 * @param values 命名参数,按名称绑定.
	 * @return
	 */
	public <X> X unique(final String hql, final Map<String, ?> values);
	
	/**
	 * 获取满足给定查询条件的给定字段的所有值集合<BR>
	 * 请确保返回集合内值类型与实际数据库表对应字段类型一致
	 * 
	 * @param clazz 要检索的实体类
	 * @param field 要检索的属性名，也可以是表达式，例如：field1*10
	 * @param condition 给定的查询条件
	 * @return
	 */
	public <X> List<X> values(final Class<?> clazz, String field, final Condition condition);
	
	/**
	 * 获取满足给定查询条件的给定多个字段的多个值集合<BR>
	 * 
	 * @param clazz 要检索的实体类
	 * @param fields 要检索的属性名数组，也可以是表达式，例如：field1*10
	 * @param condition 给定的查询条件
	 * @return
	 */
	public List<Object[]> multiValues(final Class<?> clazz, String[] fields, final Condition condition);
	
	/**
	 * 刷新缓存
	 *
	 */
	public void flush();
	
	/**
	 * 重新装入类对象（重新刷新缓存）
	 * 
	 * @param entity
	 */
	public <T extends BaseEntity> void reload(T entity);
	
	/**
	 * 根据实体类的ID值是否为0进行保存或更新类对象操作
	 * 
	 * @param entity
	 */
	public <T extends BaseEntity> void saveOrUpdate(final T entity);
	
	/**
	 * 保存数组中所有类对象，根据实体类的ID值是否为0进行保存或更新类对象操作
	 * 
	 * @param entitys
	 */
	public <T extends BaseEntity> void batchSaveOrUpdate(final T[] entitys);
	
	/**
	 * 删除给定实体对象
	 * 
	 * @param entity
	 */
	public <T extends BaseEntity> void remove(final T entity);
	
	/**
	 * 删除给定ID的给定类对象
	 * 
	 * @param clazz
	 * @param id
	 */
	public <T extends BaseEntity> void remove(final Class<T> clazz, final Serializable id);
	
	/**
	 * 批量删除数组中所有ID对应的类对象，返回删除的记录条数
	 * 
	 * @param clazz
	 * @param ids
	 */
	public <T extends BaseEntity> int batchRemove(final Class<T> clazz, final Serializable[] ids);
	
	/**
	 * 批量删除给定查询条件的所有给定类对象集合，返回删除的记录条数
	 * 
	 * @param clazz
	 * @param condition
	 */
	public <T extends BaseEntity> int batchRemove(final Class<T> clazz, final Condition condition);
	
	/**
	 * 批量更新给定查询条件的数据，返回更新的记录条数
	 * 
	 * @param clazz
	 * @param fields
	 * @param values
	 * @param condition
	 */
	public <T extends BaseEntity> int batchUpdate(final Class<T> clazz, final String[] fields, final Object[] values, final Condition condition);
	
	/**
	 * 执行给定的HQL语句，返回影响的记录条数
	 * 
	 * @param hql
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	public int execute(final String hql, final Object... values);
	
	/**
	 * 执行给定的HQL语句，返回影响的记录条数
	 * 
	 * @param hql
	 * @param values 命名参数,按名称绑定.
	 * @return
	 */
	public int execute(final String hql, final Map<String, ?> values);
	
	/**
	 * 执行SQL查询，返回存放字段名－字段值映射的Map的List集合<br>
	 * 通过分析Condition的查询条件和排序条件数据，并追加到sql语句中，形成完整的sql语句
	 * 
	 * @param sql 可以包含where条件，但不能包含order by排序条件的sql语句
	 * @param condition 包含查询条件、排序条件和分页信息，要求给定condition中的查询条件值要和查询字段的类型相匹配
	 * @return
	 */
	public List<Map<String, Object>> sqlQuery(final String sql, final Condition condition);
	
	/**
	 * 执行SQL查询，返回存放字段名－字段值映射的Map的List集合<br>
	 * 对于带参数查询，要求SQL字符串中通过"?"标识变量，例如：select * from t where a = ? and b = ?
	 * 
	 * @param sql 要执行的SQL语句
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	public List<Map<String, Object>> sqlQuery(final String sql, final Object... values);
	
	/**
	 * 执行SQL查询，返回存放字段名－字段值映射的Map的List集合<br>
	 * 对于带参数查询，要求SQL字符串中通过":"标识变量，例如：select * from t where a = :a and b = :b<br>
	 * 参数值要求是基本类型，对于in查询，允许传入的参数值为数组或者Collection对象，例如：param.put("status", new Integer[]{1, 2, 3});
	 * 
	 * @param sql 要执行的SQL语句
	 * @param params 变量Map，Map的KEY对应SQL中的以":"开头的变量名
	 * @return
	 */
	public List<Map<String, Object>> sqlQuery(final String sql, final Map<String, Object> params);
	
	/**
	 * 执行SQL查询，返回存放字段名－字段值映射的Map的List集合<br>
	 * 对于带参数查询，要求SQL字符串中通过"?"标识变量，例如：select * from t where a = ? and b = ?
	 * 
	 * @param sql 要执行的SQL语句
	 * @param start 分页参数 - 起始记录，从0开始
	 * @param limit 分页参数 - 返回记录条数
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	public List<Map<String, Object>> sqlPageQuery(final String sql, final int start, final int limit, final Object... values);
	
	/**
	 * 执行SQL查询，返回存放字段名－字段值映射的Map的List集合<br>
	 * 对于带参数查询，要求SQL字符串中通过":"标识变量，例如：select * from t where a = :a and b = :b<br>
	 * 参数值要求是基本类型，对于in查询，允许传入的参数值为数组或者Collection对象，例如：param.put("status", new Integer[]{1, 2, 3});
	 * 
	 * @param sql 要执行的SQL语句
	 * @param start 分页参数 - 起始记录，从0开始
	 * @param limit 分页参数 - 返回记录条数
	 * @param params 变量Map，Map的KEY对应SQL中的以":"开头的变量名
	 * @return
	 */
	public List<Map<String, Object>> sqlPageQuery(final String sql, final int start, final int limit, final Map<String, Object> params);
	
	/**
	 * 执行SQL查询，返回给定实体对象的List集合<br>
	 * 支持对象属性名与数据库字段名之间使用单词之间大写自动转下划线小写映射模式，即：如果名称为createDate的属性，对应的名称为create_date的数据库字段，则执行sql语句后会自动映射<br>
	 * 通过分析Condition的查询条件和排序条件数据，并追加到sql语句中，形成完整的sql语句
	 * 
	 * @param clazz
	 * @param sql 可以包含where条件，但不能包含order by排序条件的sql语句
	 * @param condition 包含查询条件、排序条件和分页信息，要求给定condition中的查询条件值要和查询字段的类型相匹配
	 * @return
	 */
	public <T> List<T> sqlQuery(final Class<T> clazz, final String sql, final Condition condition);
	
	/**
	 * 执行SQL查询，返回给定实体对象的List集合<br>
	 * 支持对象属性名与数据库字段名之间使用单词之间大写自动转下划线小写映射模式，即：如果名称为createDate的属性，对应的名称为create_date的数据库字段，则执行sql语句后会自动映射<br>
	 * 对于带参数查询，要求SQL字符串中通过"?"标识变量，例如：select * from t where a = ? and b = ?
	 * 
	 * @param clazz
	 * @param sql
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	public <T> List<T> sqlQuery(final Class<T> clazz, final String sql, final Object... values);
	
	/**
	 * 执行SQL查询，返回给定实体对象的List集合<br>
	 * 支持对象属性名与数据库字段名之间使用单词之间大写自动转下划线小写映射模式，即：如果名称为createDate的属性，对应的名称为create_date的数据库字段，则执行sql语句后会自动映射<br>
	 * 对于带参数查询，要求SQL字符串中通过":"标识变量，例如：select * from t where a = :a and b = :b<br>
	 * 参数值要求是基本类型，对于in查询，允许传入的参数值为数组或者Collection对象，例如：param.put("status", new Integer[]{1, 2, 3});
	 * 
	 * @param clazz
	 * @param sql
	 * @param params 变量Map，Map的KEY对应SQL中的以":"开头的变量名
	 * @return
	 */
	public <T> List<T> sqlQuery(final Class<T> clazz, final String sql, final Map<String, Object> params);
	
	/**
	 * 执行SQL查询，返回给定实体对象的List集合<br>
	 * 支持对象属性名与数据库字段名之间使用单词之间大写自动转下划线小写映射模式，即：如果名称为createDate的属性，对应的名称为create_date的数据库字段，则执行sql语句后会自动映射<br>
	 * 对于带参数查询，要求SQL字符串中通过"?"标识变量，例如：select * from t where a = ? and b = ?
	 * 
	 * @param clazz
	 * @param sql
	 * @param start
	 * @param limit
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	public <T> List<T> sqlPageQuery(final Class<T> clazz, final String sql, final int start, final int limit, final Object... values);
	
	/**
	 * 执行SQL查询，返回给定实体对象的List集合<br>
	 * 支持对象属性名与数据库字段名之间使用单词之间大写自动转下划线小写映射模式，即：如果名称为createDate的属性，对应的名称为create_date的数据库字段，则执行sql语句后会自动映射<br>
	 * 对于带参数查询，要求SQL字符串中通过":"标识变量，例如：select * from t where a = :a and b = :b<br>
	 * 参数值要求是基本类型，对于in查询，允许传入的参数值为数组或者Collection对象，例如：param.put("status", new Integer[]{1, 2, 3});
	 * 
	 * @param clazz
	 * @param sql
	 * @param start
	 * @param limit
	 * @param params 变量Map，Map的KEY对应SQL中的以":"开头的变量名
	 * @return
	 */
	public <T> List<T> sqlPageQuery(final Class<T> clazz, final String sql, final int start, final int limit, final Map<String, Object> params);
	
	/**
	 * 执行SQL查询，返回List集合的总条数<br>
	 * 通过分析Condition的查询条件，并追加到sql语句中，形成完整的sql语句
	 * 
	 * @param sql 可以包含where条件，但不能包含order by排序条件的sql语句
	 * @param condition 包含查询条件，要求给定condition中的查询条件值要和查询字段的类型相匹配
	 * @return
	 */
	public long sqlCount(final String sql, final Condition condition);
	
	/**
	 * 执行SQL查询，返回List集合的总条数<br>
	 * 对于带参数查询，要求SQL字符串中通过"?"标识变量，例如：select * from t where a = ? and b = ?
	 * 
	 * @param sql
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	public long sqlCount(final String sql, final Object... values);
	
	/**
	 * 执行SQL查询，返回List集合的总条数<br>
	 * 对于带参数查询，要求SQL字符串中通过":"标识变量，例如：select * from t where a = :a and b = :b<br>
	 * 参数值要求是基本类型，对于in查询，允许传入的参数值为数组或者Collection对象，例如：param.put("status", new Integer[]{1, 2, 3});
	 * 
	 * @param sql
	 * @param params 变量Map，Map的KEY对应SQL中的以":"开头的变量名
	 * @return
	 */
	public long sqlCount(final String sql, final Map<String, Object> params);
	
	/**
	 * 分页查询，提供查询条件，返回总条数和查询结果集合
	 * 
	 * @param sql
	 * @param condition
	 * @return
	 */
	public Result<Map<String, Object>> sqlQueryResult(final String sql, final Condition condition);
	
	/**
	 * 分页查询，提供查询条件，返回总条数和查询结果集合
	 * 
	 * @param clazz
	 * @param sql
	 * @param condition
	 * @return
	 */
	public <T> Result<T> sqlQueryResult(final Class<T> clazz, final String sql, final Condition condition);
	
	/**
	 * 执行SQL查询（例如：求合计值等统计操作的SQL），返回唯一的查询结果值<br>
	 * 通过分析Condition的查询条件，并追加到sql语句中，形成完整的sql语句
	 * 
	 * @param sql 包含唯一一个查询字段的唯一一个结果的SQL，SQL中可以包含where条件，但不能包含order by排序条件的sql语句
	 * @param condition 包含查询条件，要求给定condition中的查询条件值要和查询字段的类型相匹配
	 * @return 返回查询结果值，如果sql包含多个查询字段，抛出异常，如果查询没有结果，返回null
	 */
	public <X> X sqlUnique(final String sql, final Condition condition);
	
	/**
	 * 执行SQL查询（例如：求合计值等统计操作的SQL），返回唯一的查询结果值<br>
	 * 对于带参数查询，要求SQL字符串中通过"?"标识变量，例如：select * from t where a = ? and b = ?
	 * 
	 * @param sql 包含唯一一个查询字段的唯一一个结果的SQL
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return 返回查询结果值，如果sql包含多个查询字段，抛出异常，如果查询没有结果，返回null
	 */
	public <X> X sqlUnique(final String sql, final Object... values);
	
	/**
	 * 执行SQL查询（例如：求合计值等统计操作的SQL），返回唯一的查询结果值<br>
	 * 对于带参数查询，要求SQL字符串中通过":"标识变量，例如：select * from t where a = :a and b = :b<br>
	 * 参数值要求是基本类型，对于in查询，允许传入的参数值为数组或者Collection对象，例如：param.put("status", new Integer[]{1, 2, 3});
	 * 
	 * @param sql 包含唯一一个查询字段的唯一一个结果的SQL
	 * @param params 变量Map，Map的KEY对应SQL中的以":"开头的变量名
	 * @return 返回查询结果值，如果sql包含多个查询字段，抛出异常，如果查询没有结果，返回null
	 */
	public <X> X sqlUnique(final String sql, final Map<String, Object> params);
	
	/**
	 * 执行SQL查询（例如：select field from table where ...），返回唯一查询字段的结果集合<br>
	 * 通过分析Condition的查询条件，并追加到sql语句中，形成完整的sql语句
	 * 
	 * @param sql 包含唯一一个查询字段的SQL，SQL中可以包含where条件，但不能包含order by排序条件的sql语句
	 * @param condition 包含查询条件，要求给定condition中的查询条件值要和查询字段的类型相匹配
	 * @return 返回查询结果集合，如果sql包含多个查询字段，抛出异常，如果查询没有结果，返回空集合
	 */
	public <X> List<X> sqlValues(final String sql, final Condition condition);
	
	/**
	 * 执行SQL查询（例如：select field from table where ...），返回唯一查询字段的结果集合<br>
	 * 对于带参数查询，要求SQL字符串中通过"?"标识变量，例如：select * from t where a = ? and b = ?
	 * 
	 * @param sql 包含唯一一个查询字段的SQL
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return 返回查询结果集合，如果sql包含多个查询字段，抛出异常，如果查询没有结果，返回空集合
	 */
	public <X> List<X> sqlValues(final String sql, final Object... values);
	
	/**
	 * 执行SQL查询（例如：select field from table where ...），返回唯一查询字段的结果集合<br>
	 * 对于带参数查询，要求SQL字符串中通过":"标识变量，例如：select * from t where a = :a and b = :b<br>
	 * 参数值要求是基本类型，对于in查询，允许传入的参数值为数组或者Collection对象，例如：param.put("status", new Integer[]{1, 2, 3});
	 * 
	 * @param sql 包含唯一一个查询字段的SQL
	 * @param params 变量Map，Map的KEY对应SQL中的以":"开头的变量名
	 * @return 返回查询结果集合，如果sql包含多个查询字段，抛出异常，如果查询没有结果，返回空集合
	 */
	public <X> List<X> sqlValues(final String sql, final Map<String, Object> params);
	
	/**
	 * 执行SQL查询（例如：select field1, field2,... from table where ...），返回多个查询字段的结果集合<br>
	 * 通过分析Condition的查询条件，并追加到sql语句中，形成完整的sql语句
	 * 
	 * @param sql 包含多个查询字段的SQL，SQL中可以包含where条件，但不能包含order by排序条件的sql语句
	 * @param condition 包含查询条件，要求给定condition中的查询条件值要和查询字段的类型相匹配
	 * @return 返回查询结果集合，如果查询没有结果，返回空集合
	 */
	public List<Object[]> sqlMultiValues(final String sql, final Condition condition);
	
	/**
	 * 执行SQL查询（例如：select field1, field2,... from table where ...），返回多个查询字段的结果集合<br>
	 * 对于带参数查询，要求SQL字符串中通过"?"标识变量，例如：select * from t where a = ? and b = ?
	 * 
	 * @param sql 包含多个查询字段的SQL
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return 返回查询结果集合，如果查询没有结果，返回空集合
	 */
	public List<Object[]> sqlMultiValues(final String sql, final Object... values);
	
	/**
	 * 执行SQL查询（例如：select field1, field2,... from table where ...），返回多个查询字段的结果集合<br>
	 * 对于带参数查询，要求SQL字符串中通过":"标识变量，例如：select * from t where a = :a and b = :b<br>
	 * 参数值要求是基本类型，对于in查询，允许传入的参数值为数组或者Collection对象，例如：param.put("status", new Integer[]{1, 2, 3});
	 * 
	 * @param sql 包含多个查询字段的SQL
	 * @param params 变量Map，Map的KEY对应SQL中的以":"开头的变量名
	 * @return 返回查询结果集合，如果查询没有结果，返回空集合
	 */
	public List<Object[]> sqlMultiValues(final String sql, final Map<String, Object> params);
	
	/**
	 * 执行SQL操作语句<br>
	 * 通过分析Condition的查询条件数据，并追加到sql语句中，形成完整的sql语句
	 * 
	 * @param sql 可以包含where条件，但不能包含order by排序条件的sql语句
	 * @param condition condition 包含查询条件（忽略查询条件中的分页参数和排序参数），要求给定condition中的查询条件值要和查询字段的类型相匹配
	 */
	public void sqlExecute(final String sql, final Condition condition);
	
	/**
	 * 执行SQL操作语句<br>
	 * 对于带参数查询，要求SQL字符串中通过"?"标识变量，例如：select * from t where a = ? and b = ?
	 * 
	 * @param sql
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	public void sqlExecute(final String sql, final Object... values);
	
	/**
	 * 执行SQL操作语句<br>
	 * 对于带参数查询，要求SQL字符串中通过":"标识变量，例如：select * from t where a = :a and b = :b<br>
	 * 参数值要求是基本类型，对于in查询，允许传入的参数值为数组或者Collection对象，例如：param.put("status", new Integer[]{1, 2, 3});
	 * 
	 * @param sql
	 * @param params 变量Map，Map的KEY对应SQL中的以":"开头的变量名
	 */
	public void sqlExecute(final String sql, final Map<String, Object> params);

	/**
	 * 执行SQL的INSERT语句，返回自动生成的ID
	 * 对于带参数查询，要求SQL字符串中通过"?"标识变量，例如：select * from t where a = ? and b = ?
	 * 
	 * @param sql
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return 返回自动生成的ID
	 */
	public long sqlInsert(final String sql, final Object... values);
	
	/**
	 * 执行SQL的INSERT语句，返回生成的ID<br>
	 * 对于带参数查询，要求SQL字符串中通过":"标识变量，例如：select * from t where a = :a and b = :b<br>
	 * 参数值要求是基本类型，对于in查询，允许传入的参数值为数组或者Collection对象，例如：param.put("status", new Integer[]{1, 2, 3});
	 * 
	 * @param sql
	 * @param params 变量Map，Map的KEY对应SQL中的以":"开头的变量名
	 * @return 返回自动生成的ID
	 */
	public long sqlInsert(final String sql, final Map<String, Object> params);
	
	/**
	 * 批量执行SQL操作语句<br>
	 * 
	 * @param sqls
	 */
	public void batchSqlExecute(final String[] sqls);
	
	/**
	 * 批量执行SQL操作语句<br>
	 * 对于带参数查询，要求SQL字符串中通过":"标识变量，例如：select * from t where a = :a and b = :b<br>
	 * 参数值要求是基本类型，对于in查询，允许传入的参数值为数组或者Collection对象，例如：param.put("status", new Integer[]{1, 2, 3});
	 * 
	 * @param sqls
	 * @param params 变量Map，Map的KEY对应SQL中的以":"开头的变量名
	 */
	public void batchSqlExecute(final String[] sqls, final Map<String, Object> params);
	
	/**
	 * 批量执行SQL操作语句，多个SQL语句之间用";"分隔<br>
	 * 
	 * @param sql 用";"分隔了多条sql语句字符串
	 */
	public void batchSqlExecute(final String sql);
	
	/**
	 * 批量执行SQL操作语句，多个SQL语句之间用";"分隔<br>
	 * 对于带参数查询，要求SQL字符串中通过":"标识变量，例如：select * from t where a = :a and b = :b<br>
	 * 参数值要求是基本类型，对于in查询，允许传入的参数值为数组或者Collection对象，例如：param.put("status", new Integer[]{1, 2, 3});
	 * 
	 * @param sql 用";"分隔了多条sql语句字符串
	 * @param params 变量Map，Map的KEY对应SQL中的以":"开头的变量名
	 */
	public void batchSqlExecute(final String sql, final Map<String, Object> params);
	
	/**
	 * 对同一条SQL操作语句通过传入不同参数批量执行（传入参数数组，按照SQL中定义的"?"先后顺序设置参数值）<br>
	 * 本方法一般用于对某个指定表进行批量数据插入或者批量数据更新操作，对于大数据量处理，这个方法将比Hibernate方式提高约10倍的执行效率<br>
	 * 示例：<br>
	 * &nbsp;String sql = "INSERT INTO module(cnt, code, create_date, default_module, des, name, num) VALUES(?, ?, ?, ?, ?, ?, ?);";<br>
	 * &nbsp;<br>
	 * &nbsp;List&lt;Object[]&gt; paramList = new ArrayList&lt;Object[]&gt;();<br>
	 * &nbsp;for (int i = 0; i &lt; 1000; i++)<br>
	 * &nbsp;&nbsp;&nbsp;paramList.add(new Object[]{1000 + i, "code" + i, new java.sql.Date(System.currentTimeMillis()), 128, "des" + i, "name" + i, i + 0.234});<br>
	 * &nbsp;<br>
	 * &nbsp;dao.batchSqlExecuteByParamIndexList(sql, paramList);<br>
	 * 
	 * @param sql 要执行的SQL语句，语句中通过"?"定义执行参数，参数列表将按照"?"先后顺序设置每个参数值
	 * @param paramList 参数列表的集合，将通过循环集合中的每组参数列表，执行同一个SQL语句
	 */
	public void batchSqlExecuteByParamIndexList(final String sql, List<Object[]> paramList);
	
	/**
	 * 对同一条SQL操作语句通过传入不同参数批量执行（传入参数Map，按照SQL中定义的参数变量设置参数值）<br>
	 * 本方法一般用于对某个指定表进行批量数据插入或者批量数据更新操作，对于大数据量处理，这个方法将比Hibernate方式提高约10倍的执行效率<br>
	 * 示例：<br>
	 * &nbsp;String sql = "UPDATE module SET name = :name, create_date = :create_date, num = :num WHERE code = :code;";<br>
	 * &nbsp;<br>
	 * &nbsp;List&lt;Map&lt;String, Object&gt;&gt; paramList = new ArrayList&lt;Map&lt;String, Object&gt;&gt;();<br>
	 * &nbsp;for (int i = 0; i &lt; 1000; i++) {<br>
	 * &nbsp;&nbsp;&nbsp;Map&lt;String, Object&gt; map = new HashMap&lt;String, Object&gt;();<br>
	 * &nbsp;&nbsp;&nbsp;map.put("code", "code" + i);<br>
	 * &nbsp;&nbsp;&nbsp;map.put("name", "update" + i);<br>
	 * &nbsp;&nbsp;&nbsp;map.put("create_date", new java.sql.Date(System.currentTimeMillis()));<br>
	 * &nbsp;&nbsp;&nbsp;map.put("num", i + 0.567);<br>
	 * &nbsp;<br>
	 * &nbsp;&nbsp;&nbsp;paramList.add(map);<br>
	 * &nbsp;}<br>
	 * &nbsp;<br>
	 * &nbsp;dao.batchSqlExecuteByParamIndexList(sql, paramList);<br>
	 * 
	 * @param sql 要执行的SQL语句，语句中通过以"{"和"}"标识变量名称，参数Map将设置对应变量名的参数值
	 * @param paramList 参数Map的集合，将通过循环集合中的每组参数列表，执行同一个SQL语句
	 */
	public void batchSqlExecuteByParamNameList(final String sql, List<Map<String, Object>> paramList);
	
	/**
	 * 获取SQL查询的结果字段结构集合<br>
	 * 通过分析Condition的查询条件和排序条件数据，并追加到sql语句中，形成完整的sql语句
	 * 
	 * @param sql 可以包含where条件，但不能包含order by排序条件的sql语句
	 * @param condition 包含查询条件、排序条件，要求给定condition中的查询条件值要和查询字段的类型相匹配
	 * @return
	 */
	public List<SqlFieldMetaData> sqlQueryMetaData(final String sql, final Condition condition);
	
	/**
	 * 获取SQL查询的结果字段结构集合<br>
	 * 对于带参数查询，要求SQL字符串中通过"?"标识变量，例如：select * from t where a = ? and b = ?
	 * 
	 * @param sql 要执行的SQL语句
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	public List<SqlFieldMetaData> sqlQueryMetaData(String sql, Object... values);
	
	/**
	 * 获取SQL查询的结果字段结构集合<br>
	 * 对于带参数查询，要求SQL字符串中通过":"标识变量，例如：select * from t where a = :a and b = :b<br>
	 * 参数值要求是基本类型，对于in查询，允许传入的参数值为数组或者Collection对象，例如：param.put("status", new Integer[]{1, 2, 3});
	 * 
	 * @param sql 要执行的SQL语句
	 * @param params 变量Map，Map的KEY对应SQL中的以":"开头的变量名
	 * @return
	 */
	public List<SqlFieldMetaData> sqlQueryMetaData(final String sql, final Map<String, Object> params);
	
	/**
	 * 取得当前数据库连接的元数据信息
	 * 
	 * @return
	 */
	public DatabaseMetaData getMetaData();
	
	/**
	 * 取得当前数据库连接的数据库标识
	 * <p>可选的返回值包括：mssql、oracle、db2、sybase、mysql、postgresql、hsql、sap、firebird
	 * 
	 * @return 返回数据库标识，如果不可识别，则返回unknown
	 */
	public String getDatabaseIdentity();
	
	/**
	 * 返回当前数据库连接的数据库版本号
	 * 
	 * @return
	 */
	public String getDatabaseVersion();
	
	/**
	 * 清除并关闭当前线程创建的Session（可能在getSession方法中创建的Session）
	 * 
	 */
	public void clearThreadSession();
	
}
