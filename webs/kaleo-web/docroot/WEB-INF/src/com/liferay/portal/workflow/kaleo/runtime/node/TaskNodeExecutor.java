/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.runtime.node;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.definition.DelayDuration;
import com.liferay.portal.workflow.kaleo.definition.DurationScale;
import com.liferay.portal.workflow.kaleo.definition.ExecutionType;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTimer;
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentImpl;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.action.ActionExecutorUtil;
import com.liferay.portal.workflow.kaleo.runtime.assignment.TaskAssignerUtil;
import com.liferay.portal.workflow.kaleo.runtime.assignment.TaskAssignmentSelector;
import com.liferay.portal.workflow.kaleo.runtime.calendar.DueDateCalculator;
import com.liferay.portal.workflow.kaleo.runtime.graph.PathElement;
import com.liferay.portal.workflow.kaleo.runtime.notification.NotificationUtil;
import com.liferay.portal.workflow.kaleo.runtime.util.ClassLoaderUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author Michael C. Han
 */
public class TaskNodeExecutor extends BaseNodeExecutor {

	public void setDueDateCalculator(DueDateCalculator dueDateCalculator) {
		_dueDateCalculator = dueDateCalculator;
	}

	public void setTaskAssignmentSelector(
		TaskAssignmentSelector taskAssignmentSelector) {

		_taskAssignmentSelector = taskAssignmentSelector;
	}

	protected Date calculateDueDate(KaleoTask kaleoTask)
		throws SystemException {

		List<KaleoTimer> kaleoTimers = kaleoTimerLocalService.getKaleoTimers(
			KaleoNode.class.getName(), kaleoTask.getKaleoNodeId());

		if (kaleoTimers.isEmpty()) {
			return null;
		}

		TreeSet<Date> sortedDueDates = new TreeSet<Date>();

		for (KaleoTimer kaleoTimer : kaleoTimers) {
			DelayDuration delayDuration = new DelayDuration(
				kaleoTimer.getDuration(),
				DurationScale.parse(kaleoTimer.getScale()));

			Date dueDate = _dueDateCalculator.getDueDate(
				new Date(), delayDuration);

			sortedDueDates.add(dueDate);
		}

		return sortedDueDates.first();
	}

	protected KaleoTaskInstanceToken createTaskInstanceToken(
			ExecutionContext executionContext,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext,
			KaleoInstanceToken kaleoInstanceToken, KaleoTask kaleoTask,
			Date dueDate)
		throws PortalException, SystemException {
		Collection<KaleoTaskAssignment> configuredKaleoTaskAssignments =
			kaleoTask.getKaleoTaskAssignments();

		Collection<KaleoTaskAssignment> kaleoTaskAssignments =
			new ArrayList<KaleoTaskAssignment>();

		for (KaleoTaskAssignment configuredKaleoTaskAssignment :
				configuredKaleoTaskAssignments) {

			String[] assigneeScriptRequiredContexts = StringUtil.split(
				configuredKaleoTaskAssignment.
					getAssigneeScriptRequiredContexts());

			ClassLoader[] classLoaders = ClassLoaderUtil.getClassLoaders(
				assigneeScriptRequiredContexts);

			Collection<KaleoTaskAssignment> calculatedKaleoTaskAssignments =
				_taskAssignmentSelector.calculateTaskAssignments(
					configuredKaleoTaskAssignment, executionContext,
					classLoaders);

			kaleoTaskAssignments.addAll(calculatedKaleoTaskAssignments);
		}

		if (kaleoTaskAssignments.isEmpty()) {
			Collection<KaleoTaskAssignment> organizationKaleoTaskAssignments =
				getOrganizationKaleoTaskAssignments(
					configuredKaleoTaskAssignments, executionContext);

			kaleoTaskAssignments.addAll(organizationKaleoTaskAssignments);
		}

		return kaleoTaskInstanceTokenLocalService.addKaleoTaskInstanceToken(
			kaleoInstanceToken.getKaleoInstanceTokenId(),
			kaleoTask.getKaleoTaskId(), kaleoTask.getName(),
			kaleoTaskAssignments, dueDate, workflowContext, serviceContext);
	}

	@Override
	protected void doEnter(
			KaleoNode currentKaleoNode, ExecutionContext executionContext)
		throws PortalException, SystemException {

		Map<String, Serializable> workflowContext =
			executionContext.getWorkflowContext();
		ServiceContext serviceContext = executionContext.getServiceContext();

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		KaleoTask kaleoTask = kaleoTaskLocalService.getKaleoNodeKaleoTask(
			currentKaleoNode.getKaleoNodeId());

		Date dueDate = calculateDueDate(kaleoTask);

		KaleoTaskInstanceToken kaleoTaskInstanceToken = createTaskInstanceToken(
			executionContext, workflowContext, serviceContext,
			kaleoInstanceToken, kaleoTask, dueDate);

		executionContext.setKaleoTaskInstanceToken(kaleoTaskInstanceToken);

		ActionExecutorUtil.executeKaleoActions(
			KaleoNode.class.getName(), currentKaleoNode.getKaleoNodeId(),
			ExecutionType.ON_ASSIGNMENT, executionContext);

		NotificationUtil.sendKaleoNotifications(
			KaleoNode.class.getName(), currentKaleoNode.getKaleoNodeId(),
			ExecutionType.ON_ASSIGNMENT, executionContext);

		kaleoLogLocalService.addTaskAssignmentKaleoLog(
			null, kaleoTaskInstanceToken, "Assigned initial task.",
			workflowContext, serviceContext);
	}

	@Override
	protected void doExecute(
		KaleoNode currentKaleoNode, ExecutionContext executionContext,
		List<PathElement> remainingPathElements) {
	}

	@Override
	protected void doExecuteTimer(
			KaleoNode currentKaleoNode, KaleoTimer kaleoTimer,
			ExecutionContext executionContext)
		throws PortalException, SystemException {

		List<KaleoTaskAssignment> kaleoTaskReassignments =
			kaleoTimer.getKaleoTaskReassignments();

		if (kaleoTaskReassignments.isEmpty()) {
			return;
		}

		TaskAssignerUtil.reassignKaleoTask(
			kaleoTaskReassignments, executionContext);
	}

	@Override
	protected void doExit(
			KaleoNode currentKaleoNode, ExecutionContext executionContext,
			List<PathElement> remainingPathElements)
		throws PortalException, SystemException {

		Map<String, Serializable> workflowContext =
			executionContext.getWorkflowContext();
		ServiceContext serviceContext = executionContext.getServiceContext();

		String transitionName = executionContext.getTransitionName();

		KaleoTransition kaleoTransition = null;

		if (Validator.isNull(transitionName)) {
			kaleoTransition = currentKaleoNode.getDefaultKaleoTransition();
		}
		else {
			kaleoTransition = currentKaleoNode.getKaleoTransition(
				transitionName);
		}

		ExecutionContext newExecutionContext = new ExecutionContext(
			executionContext.getKaleoInstanceToken(), workflowContext,
			serviceContext);

		PathElement pathElement = new PathElement(
			null, kaleoTransition.getTargetKaleoNode(), newExecutionContext);

		remainingPathElements.add(pathElement);
	}

	protected Collection<KaleoTaskAssignment>
			getOrganizationKaleoTaskAssignments(
				Collection<KaleoTaskAssignment> kaleoTaskAssignments,
				ExecutionContext executionContext)
		throws PortalException, SystemException {

		long userId = executionContext.getKaleoInstanceToken().getUserId();

		User user = UserLocalServiceUtil.getUser(userId);

		List<Organization> organizations = user.getOrganizations();

		Collection<KaleoTaskAssignment> organizationKaleoTaskAssignments =
			new HashSet<KaleoTaskAssignment>();

		for (KaleoTaskAssignment kaleoTaskAssignment : kaleoTaskAssignments) {
			String assigneeClassName =
				kaleoTaskAssignment.getAssigneeClassName();

			if (!assigneeClassName.equals(Role.class.getName())) {
				continue;
			}

			long roleId = kaleoTaskAssignment.getAssigneeClassPK();

			Role role = RoleLocalServiceUtil.getRole(roleId);

			if (role.getType() != RoleConstants.TYPE_ORGANIZATION) {
				continue;
			}

			for (Organization organization : organizations) {
				KaleoTaskAssignment organizationKaleoTaskAssignment =
					new KaleoTaskAssignmentImpl();

				organizationKaleoTaskAssignment.setGroupId(
					organization.getGroup().getGroupId());
				organizationKaleoTaskAssignment.setCompanyId(
					kaleoTaskAssignment.getCompanyId());
				organizationKaleoTaskAssignment.setAssigneeClassName(
					kaleoTaskAssignment.getAssigneeClassName());
				organizationKaleoTaskAssignment.setAssigneeClassPK(
					kaleoTaskAssignment.getAssigneeClassPK());

				organizationKaleoTaskAssignments.add(
					organizationKaleoTaskAssignment);
			}
		}

		return organizationKaleoTaskAssignments;
	}

	private DueDateCalculator _dueDateCalculator;
	private TaskAssignmentSelector _taskAssignmentSelector;

}