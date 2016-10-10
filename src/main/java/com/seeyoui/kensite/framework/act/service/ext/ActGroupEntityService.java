package com.seeyoui.kensite.framework.act.service.ext;

import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.seeyoui.kensite.common.util.SpringContextHolder;
import com.seeyoui.kensite.framework.act.util.ActUtils;
import com.seeyoui.kensite.framework.system.domain.SysRole;
import com.seeyoui.kensite.framework.system.domain.SysUser;
import com.seeyoui.kensite.framework.system.service.SysRoleService;

@Service
public class ActGroupEntityService extends GroupEntityManager {

	private SysRoleService sysRoleService;

	public SysRoleService getSysRoleService() {
		if (sysRoleService == null){
			sysRoleService = SpringContextHolder.getBean(SysRoleService.class);
		}
		return sysRoleService;
	}
	
	public Group createNewGroup(String groupId) {
		return new GroupEntity(groupId);
	}

	public void insertGroup(Group group) {
//		getDbSqlSession().insert((PersistentObject) group);
		throw new RuntimeException("not implement method.");
	}

	public void updateGroup(GroupEntity updatedGroup) {
//		CommandContext commandContext = Context.getCommandContext();
//		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
//		dbSqlSession.update(updatedGroup);
		throw new RuntimeException("not implement method.");
	}

	public void deleteGroup(String groupId) {
//		GroupEntity group = getDbSqlSession().selectById(GroupEntity.class, groupId);
//		getDbSqlSession().delete("deleteMembershipsByGroupId", groupId);
//		getDbSqlSession().delete(group);
		throw new RuntimeException("not implement method.");
	}

	public GroupQuery createNewGroupQuery() {
//		return new GroupQueryImpl(Context.getProcessEngineConfiguration().getCommandExecutorTxRequired());
		throw new RuntimeException("not implement method.");
	}

//	@SuppressWarnings("unchecked")
	public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
//		return getDbSqlSession().selectList("selectGroupByQueryCriteria", query, page);
		throw new RuntimeException("not implement method.");
	}

	public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
//		return (Long) getDbSqlSession().selectOne("selectGroupCountByQueryCriteria", query);
		throw new RuntimeException("not implement method.");
	}

	public List<Group> findGroupsByUser(String userId) {
//		return getDbSqlSession().selectList("selectGroupsByUserId", userId);
		List<Group> list = Lists.newArrayList();
		SysUser sysUser = new SysUser();
		sysUser.setUserName(userId);
		List<SysRole> sysRoleList = getSysRoleService().findSysUserList(sysUser);
		for (SysRole role : sysRoleList){
			list.add(ActUtils.toActivitiGroup(role));
		}
		return list;
	}

	public List<Group> findGroupsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
//		return getDbSqlSession().selectListWithRawParameter("selectGroupByNativeQuery", parameterMap, firstResult, maxResults);
		throw new RuntimeException("not implement method.");
	}

	public long findGroupCountByNativeQuery(Map<String, Object> parameterMap) {
//		return (Long) getDbSqlSession().selectOne("selectGroupCountByNativeQuery", parameterMap);
		throw new RuntimeException("not implement method.");
	}

}