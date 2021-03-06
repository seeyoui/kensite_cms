--DROP TABLE OA_LEAVE CASCADE CONSTRAINTS;
CREATE TABLE OA_LEAVE (
ID CHAR(32) not null ,
CREATE_DATE DATE,
CREATE_USER VARCHAR2(50),
UPDATE_DATE DATE,
UPDATE_USER VARCHAR2(50),
REMARKS VARCHAR2(255),
DEL_FLAG CHAR(1),
BIND_ID CHAR(32),
REASON VARCHAR2(255),
START_TIME DATE,
END_TIME DATE,
LEAVE_TYPE VARCHAR2(255)
);
ALTER TABLE OA_LEAVE ADD CONSTRAINT OA_LEAVE_ID PRIMARY KEY (ID);
COMMENT ON TABLE OA_LEAVE IS '请假审批';
COMMENT ON COLUMN OA_LEAVE.ID IS '主键';
COMMENT ON COLUMN OA_LEAVE.CREATE_DATE IS '创建日期';
COMMENT ON COLUMN OA_LEAVE.CREATE_USER IS '创建用户';
COMMENT ON COLUMN OA_LEAVE.UPDATE_DATE IS '修改日期';
COMMENT ON COLUMN OA_LEAVE.UPDATE_USER IS '修改用户';
COMMENT ON COLUMN OA_LEAVE.REMARKS IS '备注信息';
COMMENT ON COLUMN OA_LEAVE.DEL_FLAG IS '删除标记';
COMMENT ON COLUMN OA_LEAVE.BIND_ID IS '流程主键';
COMMENT ON COLUMN OA_LEAVE.REASON IS '请假原因';
COMMENT ON COLUMN OA_LEAVE.START_TIME IS '开始时间';
COMMENT ON COLUMN OA_LEAVE.END_TIME IS '结束时间';
COMMENT ON COLUMN OA_LEAVE.LEAVE_TYPE IS '请假类型';

INSERT INTO SYS_MENU (ID,PARENT_ID,NAME,URL,SEQUENCE,ICON,TARGET) 
VALUES ('5c0c01825b124302aa131123178ec112','b48d762c01c54dce95c31fb42204d126','流程建模','/',30,'fa fa-random','_blank');

INSERT INTO SYS_MENU (ID,PARENT_ID,NAME,URL,SEQUENCE,ICON,TARGET) 
VALUES ('9fd6e4ab8e8646439447a2a1f6258737','5c0c01825b124302aa131123178ec112','流程模型','/actModel/list',10,'fa fa-tasks','_blank');

INSERT INTO SYS_MENU (ID,PARENT_ID,NAME,URL,SEQUENCE,ICON,TARGET) 
VALUES ('172ca37c99c74c88be3d8a3d73022190','5c0c01825b124302aa131123178ec112','流程定义','/actProcess/list',20,'fa fa-share-alt','_blank');

INSERT INTO SYS_MENU (ID,PARENT_ID,NAME,URL,SEQUENCE,ICON,TARGET) 
VALUES ('f5b9037921aa4461b61cf002c2779499','5c0c01825b124302aa131123178ec112','运行中实例','/actProcess/running',30,'fa fa-history','_blank');

INSERT INTO SYS_MENU (ID,PARENT_ID,NAME,URL,SEQUENCE,ICON,TARGET) 
VALUES ('665a638d8ba9446c8da9fae0d8dd50e2','00000000000000000000000000000000','OA办公','/',77,'fa fa-street-view','_blank');


INSERT INTO SYS_MENU (ID,PARENT_ID,NAME,URL,SEQUENCE,ICON,TARGET) 
VALUES ('d2e35919919f48c382ea13bc92963d02','665a638d8ba9446c8da9fae0d8dd50e2','请假待办','/oa/leave/list/task',10,'fa fa-male','_blank');


INSERT INTO SYS_MENU (ID,PARENT_ID,NAME,URL,SEQUENCE,ICON,TARGET) 
VALUES ('e9a8f90e5eef4ef8a403c692c05a2107','665a638d8ba9446c8da9fae0d8dd50e2','请假已办','/oa/leave/list',20,'fa fa-user','_blank');

INSERT INTO SYS_ROLE_MENU (ROLE_ID,MENU_ID) 
VALUES ('d3f34652eb03447b9cc8bb7375df675d','5c0c01825b124302aa131123178ec112');

INSERT INTO SYS_ROLE_MENU (ROLE_ID,MENU_ID) 
VALUES ('d3f34652eb03447b9cc8bb7375df675d','9fd6e4ab8e8646439447a2a1f6258737');

INSERT INTO SYS_ROLE_MENU (ROLE_ID,MENU_ID) 
VALUES ('d3f34652eb03447b9cc8bb7375df675d','172ca37c99c74c88be3d8a3d73022190');

INSERT INTO SYS_ROLE_MENU (ROLE_ID,MENU_ID) 
VALUES ('d3f34652eb03447b9cc8bb7375df675d','f5b9037921aa4461b61cf002c2779499');

INSERT INTO SYS_ROLE VALUES ('bdba2cb597f74e5c83080c82f2e28b49', '部门经理', 'depart');

INSERT INTO SYS_USER_ROLE VALUES ('355222f869db4f4fb8a22e6888aabe48', 'bdba2cb597f74e5c83080c82f2e28b49');

INSERT INTO SYS_ROLE_MENU (ROLE_ID,MENU_ID) 
VALUES ('bdba2cb597f74e5c83080c82f2e28b49','665a638d8ba9446c8da9fae0d8dd50e2');

INSERT INTO SYS_ROLE_MENU (ROLE_ID,MENU_ID) 
VALUES ('bdba2cb597f74e5c83080c82f2e28b49','d2e35919919f48c382ea13bc92963d02');

INSERT INTO SYS_ROLE_MENU (ROLE_ID,MENU_ID) 
VALUES ('bdba2cb597f74e5c83080c82f2e28b49','e9a8f90e5eef4ef8a403c692c05a2107');