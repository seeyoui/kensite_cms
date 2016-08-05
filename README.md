# kensite_cms内容发布系统完整开源版J2EE代码

## 平台简介

KenSite是基于多个优秀的开源项目，高度整合封装而成的高效，高性能，强安全性的**开源**Java EE快速开发平台。

KenSite本身是以Spring Framework为核心容器，Spring MVC为模型视图控制器，MyBatis为数据访问层，
Apache Shiro为权限授权层，Ehcahe对常用数据进行缓存，Quartz为定时任务计划管理。

KenSite 提供了常用工具进行封装，包括日志工具、缓存工具、服务器端验证、数据字典、当前组织机构数据以及其它常用小工具等。另外还提供一个强大的在线 **代码生成** 工具，直接生成controller、service、domain、mapper、mybatis xml、jsp等文件，
前端显示基于easyui，一键生成就可以直接使用包括新增修改删除等常规操作。
如果你使用了KenSite基础框架，就可以很高效的快速开发出，优秀的信息管理系统。

## Kensite_cms 内容发布系统简介

Kensite cms 内容发布系统是基于Kensite开发平台搭建，支持多站点，自带两个演示实例，一个是社区示例，一个是论坛示例。后台拥有站点、导航、文章、评论、收藏、留言板、标签云等模块，并支持标签模版，实现前端与后台业务分离，前端所有数据的展现均可通过Kensite cms 自定义标签实现，新增修改删除等操作通过ajax调用接口实现。

## 内置功能

1.	用户管理：用户是系统操作者，该功能主要完成系统用户配置。
2.	部门管理：配置系统组织机构，无限层级，树结构展现，可随意调整上下级。
4.	菜单管理：配置系统菜单，操作权限，按钮权限标识等。
5.	角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。
6.	字典管理：对系统中经常使用的一些较为固定的数据进行维护，如：是否、男女、类别、级别等。
7.	操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。
8.	连接池监视：监视当期系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。
9.  数据库建模：在线创建数据库表及字段，并可进行可视化配置各字段前端显示组件，form表单即可显示对应组件。

## 为何选择KenSite

1. 使用 [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0) 协议，源代码完全开源，无商业限制。
2. 使用目前主流的Java EE开发框架，简单易学，学习成本低。
3. 数据库无限制，目前支持MySql、Oracle，可扩充SQL Server、PostgreSQL、H2等。
4. 模块化设计，层次结构清晰。内置一系列内容发布系统基础功能。
5. 操作权限控制精密细致，对所有管理链接都进行权限验证，可控制到按钮。
6. 数据权限控制精密细致，对指定数据集权限进行过滤，七种数据权限可供选择。
7. 提供在线功能代码生成工具，提高开发效率及质量。
8. 提供常用工具类封装，日志、缓存、验证、字典、组织机构等，常用标签（taglib），获取当前组织机构、字典等数据。
9. 兼容目前最流行浏览器（IE7+、Chrome、Firefox）IE6也支持，但体验效果差。
10.Kensite是我们公司本身也在使用的开发框架，经过了各类项目的实战历练，高效稳定。

## 技术选型

1、后端

* 核心框架：Spring Framework 4.0
* 安全框架：Apache Shiro 1.2
* 视图框架：Spring MVC 4.0
* 服务端验证：Hibernate Validator 5.1
* 定时任务计划：Quartz
* 持久层框架：MyBatis 3.2
* 数据库连接池：Alibaba Druid 1.0
* 缓存框架：Ehcache 2.6、Redis
* 日志管理：SLF4J 1.7、Log4j
* 工具类：Apache Commons、Jackson 2.2、Xstream 1.4、Dozer 5.3、POI 3.9

2、前端

* JS框架：jQuery 1.9
* CSS框架：Twitter Bootstrap 2.3.1
* 前端组件：easyui
* 客户端验证：JQuery Validation Plugin 1.11
* 富文本：Ueditor
* 对话框：layer、jquery-ui
* 上传空间：uploadify
* 树结构控件：jQuery zTree
* 日期控件： My97DatePicker
* web excel插件：spreadjs
* 统计图表：echarts

4、平台

* 服务器中间件：在Java EE 5规范（Servlet 2.5、JSP 2.1）下开发，支持应用服务器中间件
有Tomcat 6、Jboss 7、WebLogic 10、WebSphere 8。
* 数据库支持：目前仅提供MySql和Oracle数据库的支持，但不限于数据库，平台留有其它数据库支持接口，
可方便更改为其它数据库，如：SqlServer 2008、MySql 5.5、H2等
* 开发环境：Java EE、Eclipse、Maven、Git

## 安全考虑

1. 开发语言：系统采用Java 语言开发，具有卓越的通用性、高效性、平台移植性和安全性。
2. 分层设计：（数据库层，数据访问层，业务逻辑层，展示层）层次清楚，低耦合，各层必须通过接口才能接入并进行参数校验（如：在展示层不可直接操作数据库），保证数据操作的安全。
3. 双重验证：用户表单提交双验证：包括服务器端验证及客户端验证，防止用户通过浏览器恶意修改（如不可写文本域、隐藏变量篡改、上传非法文件等），跳过客户端验证操作数据库。
4. 安全编码：用户表单提交所有数据，在服务器端都进行安全编码，防止用户提交非法脚本及SQL注入获取敏感数据等，确保数据安全。
5. 密码加密：登录用户密码进行MD5加密，此加密方法是不可逆的。保证密文泄露后的安全问题。
6. 强制访问：系统对所有管理端链接都进行用户身份权限验证，防止用户直接填写url进行访问。

## 快速体验

1. 具备运行环境：JDK1.7+、Maven3.0+、MySql5+或Oracle10g+。
2. 修改src\main\resources\kensite.properties文件中的数据库设置参数。
3. 根据修改参数创建对应MySql或Oracle数据库用户和参数。
4. 找到src\main\resources\db\sys目录，执行对应数据库初始化sql文件。
5. 运行bin\run-tomcat7.bat或bin\run-jetty.bat，启动Web服务器（第一次运行，需要下载依赖jar包，请耐心等待）。
6. 最高管理员账号，用户名：system 密码：system。

## 常见问题

1. 快速启动：详细说明请参考src\main\webapp\DOC\doc\doc.html，这里一步一步的详细的介绍了如何搭建kensite平台及平台所需环境，以及如何启动等。

## 如何交流、反馈？

* QQ： 2624030701
* E-mail：seeyouiken@163.com
* GitHub：<https://github.com/seeyoui/kensite_cms>
* 开源中国：<http://git.oschina.net/seeyoui/kensite_cms>

## 版权声明

本软件使用 [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0) 协议，请严格遵照协议内容：

1. 需要给代码的用户一份Apache Licence。
2. 如果你修改了代码，需要在被修改的文件中说明。
3. **在延伸的代码中（修改和有源代码衍生的代码中）需要带有原来代码中的协议，商标，专利声明和其他原来作者规定需要包含的说明。**
4. 如果再发布的产品中包含一个Notice文件，则在Notice文件中需要带有Apache Licence。你可以在Notice中增加自己的许可，但不可以表现为对Apache Licence构成更改。
3. Apache Licence也是对商业应用友好的许可。使用者也可以在需要的时候修改代码来满足需要并作为开源或商业产品发布/销售

#系统美图
![输入图片说明](https://github.com/seeyoui/kensite_cms/raw/master/picture/1.png)
![输入图片说明](https://github.com/seeyoui/kensite_cms/raw/master/picture/2.png)
![输入图片说明](https://github.com/seeyoui/kensite_cms/raw/master/picture/3.png)
![输入图片说明](https://github.com/seeyoui/kensite_cms/raw/master/picture/4.png)
![输入图片说明](https://github.com/seeyoui/kensite_cms/raw/master/picture/5.png)
![输入图片说明](https://github.com/seeyoui/kensite_cms/raw/master/picture/6.png)
