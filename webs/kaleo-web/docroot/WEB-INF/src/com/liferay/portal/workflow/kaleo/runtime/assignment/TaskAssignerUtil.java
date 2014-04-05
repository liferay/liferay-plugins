/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.runtime.assignment;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.util.ClassLoaderUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskAssignmentInstanceLocalServiceUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Marcellus Tavares
 */
public class TaskAssignerUtil {

	public static void reassignKaleoTask(
			List<KaleoTaskAssignment> kaleoTaskAssignments,
			ExecutionContext executionContext)
		throws PortalException, SystemException {

		List<KaleoTaskAssignment> reassignedKaleoTaskAssignments =
			new ArrayList<KaleoTaskAssignment>();

		for (KaleoTaskAssignment kaleoTaskAssignment : kaleoTaskAssignments) {
			String[] assigneeScriptRequiredContexts = StringUtil.split(
				kaleoTaskAssignment.getAssigneeScriptRequiredContexts());

			ClassLoader[] classLoaders = ClassLoaderUtil.getClassLoaders(
				assigneeScriptRequiredContexts);

			Collection<KaleoTaskAssignment> calculatedKaleoTaskAssignments =
				_taskAssignmentSelector.calculateTaskAssignments(
					kaleoTaskAssignment, executionContext, classLoaders);

			reassignedKaleoTaskAssignments.addAll(
				calculatedKaleoTaskAssignments);
		}

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			executionContext.getKaleoTaskInstanceToken();

		KaleoTaskAssignmentInstanceLocalServiceUtil.
			deleteKaleoTaskAssignmentInstances(kaleoTaskInstanceToken);

		KaleoTaskAssignmentInstanceLocalServiceUtil.addTaskAssignmentInstances(
			kaleoTaskInstanceToken, reassignedKaleoTaskAssignments,
			executionContext.getWorkflowContext(),
			executionContext.getServiceContext());
	}

	public void setTaskAssignmentSelector(
		TaskAssignmentSelector taskAssignmentSelector) {

		_taskAssignmentSelector = taskAssignmentSelector;
	}

	private static TaskAssignmentSelector _taskAssignmentSelector;

}