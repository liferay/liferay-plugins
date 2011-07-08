/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.workflow.jbpm.dao;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.workflow.jbpm.ProcessInstanceExtensionImpl;
import com.liferay.portal.workflow.jbpm.TaskInstanceExtensionImpl;
import com.liferay.portal.workflow.jbpm.WorkflowDefinitionExtensionImpl;
import com.liferay.portal.workflow.jbpm.WorkflowLogImpl;
import com.liferay.portal.workflow.jbpm.util.RoleRetrievalUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import org.jbpm.JbpmContext;
import org.jbpm.JbpmException;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 */
public class CustomSession {

	public static String COUNT_LATEST_PROCESS_DEFINITIONS =
		CustomSession.class.getName() + ".countLatestProcessDefinitions";

	public static String COUNT_PROCESS_DEFINITIONS_BY_NAME =
		CustomSession.class.getName() + ".countProcessDefinitionsByName";

	public CustomSession(JbpmContext jbpmContext) {
		_session = jbpmContext.getSession();
	}

	public void close() {
		if (_session != null) {
			_session.close();
		}
	}

	public int countProcessDefinitions(String name, boolean latest) {
		try {
			Criteria criteria = _session.createCriteria(
				ProcessDefinition.class);

			if (latest) {
				criteria.setProjection(Projections.countDistinct("name"));
			}
			else {
				criteria.setProjection(Projections.rowCount());
			}

			if (name != null) {
				criteria.add(Restrictions.eq("name", name));
			}

			Number count = (Number)criteria.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	public int countProcessInstances(
		long processDefinitionId, Boolean completed) {

		try {
			Criteria criteria = _session.createCriteria(
				ProcessInstance.class);

			criteria.setProjection(Projections.countDistinct("id"));

			criteria.add(
				Restrictions.eq("processDefinition.id", processDefinitionId));

			if (completed != null) {
				if (completed.booleanValue()) {
					criteria.add(Restrictions.isNotNull("end"));
				}
				else {
					criteria.add(Restrictions.isNull("end"));
				}
			}

			Number count = (Number)criteria.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	public int countProcessInstances(
		long companyId, Long userId, String assetClassName,
		Long assetClassPK, Boolean completed) {

		try {
			Criteria criteria = buildProcessInstanceExtensionSearchCriteria(
				companyId, userId, assetClassName, assetClassPK, completed);

			return criteriaCount(criteria);
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	public int countTaskInstances(
		long processInstanceId, long tokenId, String[] actorIds,
		boolean pooledActors, Boolean completed) {

		if ((actorIds != null) && (actorIds.length == 0)) {
			return 0;
		}

		try {
			Criteria criteria = _session.createCriteria(TaskInstance.class);

			criteria.setProjection(Projections.countDistinct("id"));

			if (processInstanceId > 0) {
				criteria.add(
					Restrictions.eq("processInstance.id", processInstanceId));
			}
			else if (tokenId > 0) {
				criteria.add(Restrictions.eq("token.id", tokenId));
			}
			else if (actorIds != null) {
				if (pooledActors) {
					Criteria subcriteria = criteria.createCriteria(
						"pooledActors");

					subcriteria.add(Restrictions.in("actorId", actorIds));
				}
				else {
					criteria.add(Restrictions.in("actorId", actorIds));
				}
			}

			if (completed != null) {
				if (completed.booleanValue()) {
					criteria.add(Restrictions.isNotNull("end"));
				}
				else {
					criteria.add(Restrictions.isNull("end"));
				}
			}

			Number count = (Number)criteria.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	public int countTaksInstancesBySubmittingUser(
		long companyId, long userId, Boolean completed) {

		List<TaskInstance> tasksInstances = getTasksInstancesBySubmittingUser(
			companyId, userId, completed, 0, -1, null);

		return tasksInstances.size();
	}

	public void deleteProcessInstanceExtension(long processInstanceId) {
		ProcessInstanceExtensionImpl processInstanceExtensionImpl =
			findProcessInstanceExtension(processInstanceId);

		_session.delete(processInstanceExtensionImpl);
	}

	public void deleteTaskInstanceExtensions(long processInstanceId) {
		List<TaskInstance> taskInstances = findTaskInstances(
			processInstanceId, 1, null, false, null, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		for (TaskInstance taskInstance : taskInstances) {
			TaskInstanceExtensionImpl taskInstanceExtensionImpl =
				findTaskInstanceExtension(taskInstance.getId());

			_session.delete(taskInstanceExtensionImpl);
		}
	}

	public void deleteWorkflowDefinitionExtension(long processDefinitionId) {
		WorkflowDefinitionExtensionImpl workflowDefinitionExtension =
			findWorkflowDefinitonExtension(processDefinitionId);

		_session.delete(workflowDefinitionExtension);
	}

	public void deleteWorkflowLogs(long processInstanceId) {
		List<TaskInstance> taskInstances = findTaskInstances(
			processInstanceId, 1, null, false, null, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		for (TaskInstance taskInstance : taskInstances) {
			Criteria criteria = _session.createCriteria(WorkflowLogImpl.class);

			criteria.add(
				Restrictions.eq("taskInstance.id", taskInstance.getId()));

			List<WorkflowLog> workflowLogs = criteria.list();

			for (WorkflowLog workflowLog : workflowLogs) {
				_session.delete(workflowLog);
			}
		}
	}

	public List<ProcessDefinition> findProcessDefinitions(
		String name, boolean latest, int start, int end,
		OrderByComparator orderByComparator) {

		try {
			Criteria criteria = _session.createCriteria(
				ProcessDefinition.class);

			if (latest) {
				ProjectionList projectionList = Projections.projectionList();

				projectionList.add(Projections.groupProperty("name"));
				projectionList.add(Projections.max("version"));

				criteria.setProjection(projectionList);

				addOrder(criteria, orderByComparator, "version");
			}

			if (name != null) {
				criteria.add(Restrictions.eq("name", name));

				addOrder(criteria, orderByComparator, "name");
			}

			if (latest == false && name == null) {
				addOrder(criteria, orderByComparator);
			}

			addPagination(criteria, start, end);

			if (latest) {
				List<Object[]> list = criteria.list();

				List<String> names = new ArrayList<String>(list.size());

				for (Object[] array : list) {
					names.add((String)array[0]);
				}

				return findProcessDefinitions(names);
			}
			else {
				return criteria.list();
			}
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	public List<ProcessInstance> findProcessInstances(
		long processDefinitionId, Boolean completed, int start, int end,
		OrderByComparator orderByComparator) {

		try {
			Criteria criteria = _session.createCriteria(
				ProcessInstance.class);

			criteria.add(
				Restrictions.eq("processDefinition.id", processDefinitionId));

			if (completed != null) {
				if (completed.booleanValue()) {
					criteria.add(Restrictions.isNotNull("end"));
				}
				else {
					criteria.add(Restrictions.isNull("end"));
				}
			}

			addPagination(criteria, start, end);
			addOrder(criteria, orderByComparator);

			return criteria.list();
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	public ProcessInstanceExtensionImpl findProcessInstanceExtension(
		long processInstanceId) {

		try {
			Criteria criteria = _session.createCriteria(
				ProcessInstanceExtensionImpl.class);

			criteria.add(
				Restrictions.eq("processInstance.id", processInstanceId));

			return (ProcessInstanceExtensionImpl)criteria.uniqueResult();
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	public List<ProcessInstance> findProcessInstances(
		long companyId, Long userId, String assetClassName,
		Long assetClassPK, Boolean completed, int start, int end,
		OrderByComparator orderByComparator) {

		try {
			Criteria criteria = buildProcessInstanceExtensionSearchCriteria(
				companyId, userId, assetClassName, assetClassPK, completed);

			addPagination(criteria, start, end);
			addOrder(criteria, orderByComparator);

			return toProcessIntances(criteria.list());
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	public List<TaskInstance> findTaskInstances(
		long processInstanceId, long tokenId, String[] actorIds,
		boolean pooledActors, Boolean completed, int start, int end,
		OrderByComparator orderByComparator) {

		if ((actorIds != null) && (actorIds.length == 0)) {
			return Collections.emptyList();
		}

		try {
			Criteria criteria = _session.createCriteria(TaskInstance.class);

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			if (processInstanceId > 0) {
				criteria.add(
					Restrictions.eq("processInstance.id", processInstanceId));
			}
			else if (tokenId > 0) {
				criteria.add(Restrictions.eq("token.id", tokenId));
			}

			if (actorIds != null) {
				if (pooledActors) {
					Criteria subcriteria = criteria.createCriteria(
						"pooledActors");

					subcriteria.add(Restrictions.in("actorId", actorIds));

					criteria.add(Restrictions.isNull("actorId"));
				}
				else {
					criteria.add(Restrictions.in("actorId", actorIds));
				}
			}

			if (completed != null) {
				if (completed.booleanValue()) {
					criteria.add(Restrictions.isNotNull("end"));
				}
				else {
					criteria.add(Restrictions.isNull("end"));
				}
			}

			addPagination(criteria, start, end);
			addOrder(criteria, orderByComparator);

			return criteria.list();
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	public List<TaskInstance> findTaskInstancesBySubmittingUser(
		long companyId, long userId, Boolean completed, int start, int end,
		OrderByComparator orderByComparator) {

		return getTasksInstancesBySubmittingUser(
			companyId, userId, completed, start, end, orderByComparator);
	}

	public TaskInstanceExtensionImpl findTaskInstanceExtension(
		long taskInstanceId) {

		try {
			Criteria criteria = _session.createCriteria(
				TaskInstanceExtensionImpl.class);

			criteria.add(
				Restrictions.eq("taskInstance.id", taskInstanceId));

			return (TaskInstanceExtensionImpl)criteria.uniqueResult();
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	public WorkflowDefinitionExtensionImpl findWorkflowDefinitonExtension(
		long processDefinitionId) {

		try {
			Criteria criteria = _session.createCriteria(
				WorkflowDefinitionExtensionImpl.class);

			criteria.add(
				Restrictions.eq("processDefinition.id", processDefinitionId));

			return (WorkflowDefinitionExtensionImpl)criteria.uniqueResult();
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	public int searchCountTaskInstances(
		String taskName, String assetType, Long[] assetPrimaryKeys,
		Date dueDateGT, Date dueDateLT, Boolean completed,
		Boolean searchByUserRoles, boolean andOperator,
		ServiceContext serviceContext) {

		try {
			Criteria criteria = buildTaskInstanceExtensionSearchCriteria(
				taskName, assetType, assetPrimaryKeys, dueDateGT, dueDateLT,
				completed, searchByUserRoles, andOperator, serviceContext);

			return criteriaCount(criteria);
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	public List<TaskInstance> searchTaskInstances(
		String taskName, String assetType, Long[] assetPrimaryKeys,
		Date dueDateGT, Date dueDateLT, Boolean completed,
		Boolean searchByUserRoles, boolean andOperator, int start, int end,
		OrderByComparator orderByComparator, ServiceContext serviceContext) {

		try {
			Criteria criteria = buildTaskInstanceExtensionSearchCriteria(
				taskName, assetType, assetPrimaryKeys, dueDateGT, dueDateLT,
				completed, searchByUserRoles, andOperator, serviceContext);

			addPagination(criteria, start, end);
			addOrder(criteria, orderByComparator);

			return toTaskIntances(criteria.list());
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	protected void addOrder(
		Criteria criteria, OrderByComparator orderByComparator,
		String... skipFields) {

		if (orderByComparator == null) {
			return;
		}

		String[] orderByFields = orderByComparator.getOrderByFields();

		Arrays.sort(skipFields);

		for (String orderByField : orderByFields) {
			Order order = null;

			String jbpmField = _fieldMap.get(orderByField);

			if (jbpmField == null) {
				jbpmField = orderByField;
			}

			if (Arrays.binarySearch(skipFields, jbpmField) < 0) {
				if (orderByComparator.isAscending()) {
					order = Order.asc(jbpmField);
				}
				else {
					order = Order.desc(jbpmField);
				}

				criteria.addOrder(order);
			}
		}
	}

	protected void addPagination(Criteria criteria, int start, int end) {
		if ((start != QueryUtil.ALL_POS) && (end != QueryUtil.ALL_POS)) {
			criteria.setFirstResult(start);
			criteria.setMaxResults(end - start);
		}
	}

	protected void addSearchByUserRolesCriterion(
		Criteria criteria, Boolean searchByUserRoles,
		ServiceContext serviceContext) throws SystemException {

		if (searchByUserRoles == null) {
			return;
		}

		Criteria assigneesCriteria = criteria.createCriteria("assignees");

		if (!searchByUserRoles) {
			assigneesCriteria.add(
				Restrictions.eq("assigneeClassName", User.class.getName()));
			assigneesCriteria.add(
				Restrictions.eq("assigneeClassPK", serviceContext.getUserId()));

			return;
		}

		List<Long> roleIds = RoleRetrievalUtil.getRoleIds(serviceContext);

		List<UserGroupRole> userGroupRoles =
			UserGroupRoleLocalServiceUtil.getUserGroupRoles(
				serviceContext.getUserId());

		if (userGroupRoles.isEmpty()) {
			assigneesCriteria.add(
				Restrictions.eq("assigneeClassName", Role.class.getName()));
			assigneesCriteria.add(
				Restrictions.in("assigneeClassPK",
					roleIds.toArray(new Long[roleIds.size()])));
		}
		else {
			Junction junction = Restrictions.disjunction();

			junction.add(
				Restrictions.and(
				Restrictions.eq("assigneeClassName", Role.class.getName()),
				Restrictions.in("assigneeClassPK",
					roleIds.toArray(new Long[roleIds.size()]))));

			for (UserGroupRole userGroupRole : userGroupRoles) {
				junction.add(
					Restrictions.and(
						Restrictions.eq("groupId", userGroupRole.getGroupId()),
						Restrictions.and(
							Restrictions.eq("assigneeClassName",
								Role.class.getName()),
							Restrictions.eq("assigneeClassPK",
								userGroupRole.getRoleId()))));
			}

			assigneesCriteria.add(junction);
		}
	}

	protected Criteria buildTaskInstanceExtensionSearchCriteria(
			String taskName, String assetType, Long[] assetPrimaryKeys,
			Date dueDateGT, Date dueDateLT, Boolean completed,
			Boolean searchByUserRoles, boolean andOperator,
			ServiceContext serviceContext)
		throws SystemException {

		Criteria criteria = _session.createCriteria(
			TaskInstanceExtensionImpl.class);

		criteria.createAlias("taskInstance", "taskInstance");

		criteria.add(
			Restrictions.eq("companyId" , serviceContext.getCompanyId()));

		if (Validator.isNotNull(taskName) || Validator.isNotNull(assetType) ||
			(dueDateGT != null) || (dueDateLT != null)) {

			Junction junction = null;

			if (andOperator) {
				junction = Restrictions.conjunction();
			}
			else {
				junction = Restrictions.disjunction();
			}

			if (Validator.isNotNull(taskName)) {
				String[] taskNameKeywords = StringUtil.split(
					taskName, StringPool.SPACE);

				for (String taskNameKeyword : taskNameKeywords) {
					junction.add(
						Restrictions.like(
							"taskInstance.name", "%" + taskNameKeyword + "%"));
				}
			}

			if (Validator.isNotNull(assetType)) {
				String[] assetTypeKeywords = StringUtil.split(
					assetType, StringPool.SPACE);

				for (String assetTypeKeyword : assetTypeKeywords) {
					junction.add(
						Restrictions.like(
							"workflowContext",
							"%\"entryType\":\"%" + assetTypeKeyword + "%\"%"));
				}
			}

			if (Validator.isNotNull(assetPrimaryKeys)) {
				for (Long assetPrimaryKey : assetPrimaryKeys) {
					junction.add(
						Restrictions.like(
							"workflowContext",
							"%\"entryClassPK\":\"%" + assetPrimaryKey + "%\"%"));
				}
			}

			if (dueDateGT != null) {
				junction.add(
					Restrictions.ge("taskInstance.dueDate", dueDateGT));
			}

			if (dueDateLT != null) {
				junction.add(
					Restrictions.lt("taskInstance.dueDate", dueDateGT));
			}

			criteria.add(junction);
		}

		addSearchByUserRolesCriterion(
			criteria, searchByUserRoles, serviceContext);

		if (completed != null) {
			if (completed.booleanValue()) {
				criteria.add(Restrictions.isNotNull("taskInstance.end"));
			}
			else {
				criteria.add(Restrictions.isNull("taskInstance.end"));
			}
		}

		return criteria;
	}

	protected Criteria buildProcessInstanceExtensionSearchCriteria(
		long companyId, Long userId, String assetClassName, Long assetClassPK,
		Boolean completed) {

		Criteria criteria = _session.createCriteria(
			ProcessInstanceExtensionImpl.class);

		criteria.add(Restrictions.eq("companyId", companyId));

		if (userId != null) {
			criteria.add(Restrictions.eq("userId", userId));
		}

		if (Validator.isNotNull(assetClassName)) {
			criteria.add(Restrictions.like("className", assetClassName));
		}

		if (Validator.isNotNull(assetClassPK)) {
			criteria.add(Restrictions.eq("classPK", assetClassPK));
		}

		if (completed != null) {
			Criteria completionCriteria =
				criteria.createCriteria("processInstance");

			if (completed) {
				completionCriteria.add(Restrictions.isNotNull("end"));
			}
			else {
				completionCriteria.add(Restrictions.isNull("end"));
			}
		}

		return criteria;
	}

	protected int criteriaCount(Criteria criteria) {
		criteria.setProjection(Projections.rowCount());

		List<Integer> results = criteria.list();

		if (results.isEmpty()) {
			return 0;
		}
		else {
			return results.get(0);
		}
	}

	protected ProcessDefinition findProcessDefinition(String name) {
		try {
			Criteria criteria = _session.createCriteria(
				ProcessDefinition.class);

			criteria.add(Restrictions.eq("name", name));

			Order order = Order.desc("version");

			criteria.addOrder(order);

			criteria.setFirstResult(0);
			criteria.setMaxResults(1);

			return (ProcessDefinition)criteria.uniqueResult();
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	protected List<ProcessDefinition> findProcessDefinitions(
		List<String> names) {

		try {
			List<ProcessDefinition> processDefinitions =
				new ArrayList<ProcessDefinition>();

			for (String name : names) {
				ProcessDefinition processDefinition = findProcessDefinition(
					name);

				processDefinitions.add(processDefinition);
			}

			return processDefinitions;
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}
	}

	protected List<TaskInstance> getTasksInstancesBySubmittingUser(
		long companyId, long userId, Boolean completed, int start, int end,
		OrderByComparator orderByComparator) {

		List<TaskInstance> taskInstances = new ArrayList<TaskInstance>();

		try {
			Criteria criteria = _session.createCriteria(TaskInstance.class);

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			if (completed != null) {
				if (completed.booleanValue()) {
					criteria.add(Restrictions.isNotNull("end"));
				}
				else {
					criteria.add(Restrictions.isNull("end"));
				}
			}

			addOrder(criteria, orderByComparator);

			for (TaskInstance taskInstance :
					(List<TaskInstance>)criteria.list()) {

				ProcessInstance processInstance =
					taskInstance.getProcessInstance();

				ContextInstance contextInstance =
					processInstance.getContextInstance();

				long taskInstanceUserId = GetterUtil.getLong(
					(String)contextInstance.getVariable("userId"));

				if (userId == taskInstanceUserId) {
					taskInstances.add(taskInstance);
				}
			}
		}
		catch (Exception e) {
			throw new JbpmException(e);
		}

		if ((end != QueryUtil.ALL_POS) && (taskInstances.size() > end)) {
			taskInstances = ListUtil.subList(taskInstances, start, end);
		}

		return taskInstances;
	}

	protected List<ProcessInstance> toProcessIntances(
		List<ProcessInstanceExtensionImpl> processInstanceExtensionImpls) {

		List<ProcessInstance> processInstances = new ArrayList<ProcessInstance>(
			processInstanceExtensionImpls.size());

		for (ProcessInstanceExtensionImpl workflowInstanceExtensionImpl :
				processInstanceExtensionImpls) {

			processInstances.add(
				workflowInstanceExtensionImpl.getProcessInstance());
		}

		return processInstances;
	}

	protected List<TaskInstance> toTaskIntances(
		List<TaskInstanceExtensionImpl> taskInstanceExtensionImpls) {

		List<TaskInstance> taskInstances = new ArrayList<TaskInstance>(
			taskInstanceExtensionImpls.size());

		for (TaskInstanceExtensionImpl taskInstanceExtensionImpl :
				taskInstanceExtensionImpls) {

			taskInstances.add(taskInstanceExtensionImpl.getTaskInstance());
		}

		return taskInstances;
	}

	private static Map<String, String> _fieldMap =
		new HashMap<String, String>();

	static {
		_fieldMap.put("completionDate", "end");
		_fieldMap.put("createDate", "create");
		_fieldMap.put("endDate", "end");
		_fieldMap.put("startDate", "start");
		_fieldMap.put("state", "currentNodeName");
		_fieldMap.put("userId", "actorId");
		_fieldMap.put("workflowDefinitionId", "id");
		_fieldMap.put("workflowLogId", "id");
		_fieldMap.put("workflowInstanceId", "id");
		_fieldMap.put("workflowTaskId", "id");
	}

	private Session _session;

}