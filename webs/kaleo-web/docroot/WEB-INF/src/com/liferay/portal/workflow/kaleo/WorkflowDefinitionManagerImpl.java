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

package com.liferay.portal.workflow.kaleo;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.comparator.WorkflowComparatorFactoryUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.runtime.WorkflowEngine;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.util.WorkflowModelUtil;
import com.liferay.portal.workflow.kaleo.util.comparators.KaleoDefinitionOrderByComparator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael C. Han
 * @author Eduardo Lundgren
 */
public class WorkflowDefinitionManagerImpl
	implements WorkflowDefinitionManager {

	@Override
	public WorkflowDefinition deployWorkflowDefinition(
			long companyId, long userId, String title, byte[] bytes)
		throws WorkflowException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);
		serviceContext.setUserId(userId);

		return _workflowEngine.deployWorkflowDefinition(
			title, new UnsyncByteArrayInputStream(bytes), serviceContext);
	}

	@Override
	public int getActiveWorkflowDefinitionCount(long companyId)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

			return KaleoDefinitionLocalServiceUtil.getKaleoDefinitionsCount(
				true, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public int getActiveWorkflowDefinitionCount(long companyId, String name)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

			return KaleoDefinitionLocalServiceUtil.getKaleoDefinitionsCount(
				name, true, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public List<WorkflowDefinition> getActiveWorkflowDefinitions(
			long companyId, int start, int end,
			OrderByComparator<WorkflowDefinition> orderByComparator)
		throws WorkflowException {

		try {
			if (orderByComparator == null) {
				orderByComparator =
					WorkflowComparatorFactoryUtil.getDefinitionNameComparator(
						true);
			}

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

			List<KaleoDefinition> kaleoDefinitions =
				KaleoDefinitionLocalServiceUtil.getKaleoDefinitions(
					true, start, end,
					KaleoDefinitionOrderByComparator.getOrderByComparator(
						orderByComparator),
					serviceContext);

			return toWorkflowDefinitions(kaleoDefinitions);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public List<WorkflowDefinition> getActiveWorkflowDefinitions(
			long companyId, String name, int start, int end,
			OrderByComparator<WorkflowDefinition> orderByComparator)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

			List<KaleoDefinition> kaleoDefinitions =
				KaleoDefinitionLocalServiceUtil.getKaleoDefinitions(
					name, true, start, end,
					KaleoDefinitionOrderByComparator.getOrderByComparator(
						orderByComparator),
					serviceContext);

			return toWorkflowDefinitions(kaleoDefinitions);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public WorkflowDefinition getLatestKaleoDefinition(
			long companyId, String name)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

			KaleoDefinition kaleoDefinition =
				KaleoDefinitionLocalServiceUtil.getLatestKaleoDefinition(
					name, serviceContext);

			return WorkflowModelUtil.toWorkflowDefinition(kaleoDefinition);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public WorkflowDefinition getWorkflowDefinition(
			long companyId, String name, int version)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

			KaleoDefinition kaleoDefinition =
				KaleoDefinitionLocalServiceUtil.getKaleoDefinition(
					name, version, serviceContext);

			return WorkflowModelUtil.toWorkflowDefinition(kaleoDefinition);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public int getWorkflowDefinitionCount(long companyId)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

			return KaleoDefinitionLocalServiceUtil.getKaleoDefinitionsCount(
				serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public int getWorkflowDefinitionCount(long companyId, String name)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

			return KaleoDefinitionLocalServiceUtil.getKaleoDefinitionsCount(
				name, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public List<WorkflowDefinition> getWorkflowDefinitions(
			long companyId, int start, int end,
			OrderByComparator<WorkflowDefinition> orderByComparator)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

			List<KaleoDefinition> kaleoDefinitions =
				KaleoDefinitionLocalServiceUtil.getKaleoDefinitions(
					start, end,
					KaleoDefinitionOrderByComparator.getOrderByComparator(
						orderByComparator),
					serviceContext);

			return toWorkflowDefinitions(kaleoDefinitions);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public List<WorkflowDefinition> getWorkflowDefinitions(
			long companyId, String name, int start, int end,
			OrderByComparator<WorkflowDefinition> orderByComparator)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);

			List<KaleoDefinition> kaleoDefinitions =
				KaleoDefinitionLocalServiceUtil.getKaleoDefinitions(
					name, start, end,
					KaleoDefinitionOrderByComparator.getOrderByComparator(
						orderByComparator),
					serviceContext);

			return toWorkflowDefinitions(kaleoDefinitions);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public void setWorkflowEngine(WorkflowEngine workflowEngine) {
		_workflowEngine = workflowEngine;
	}

	@Override
	public void undeployWorkflowDefinition(
			long companyId, long userId, String name, int version)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);
			serviceContext.setUserId(userId);

			_workflowEngine.deleteWorkflowDefinition(
				name, version, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public WorkflowDefinition updateActive(
			long companyId, long userId, String name, int version,
			boolean active)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);
			serviceContext.setUserId(userId);

			if (active) {
				KaleoDefinitionLocalServiceUtil.activateKaleoDefinition(
					name, version, serviceContext);
			}
			else {
				KaleoDefinitionLocalServiceUtil.deactivateKaleoDefinition(
					name, version, serviceContext);
			}

			return getWorkflowDefinition(companyId, name, version);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public WorkflowDefinition updateTitle(
			long companyId, long userId, String name, int version, String title)
		throws WorkflowException {

		try {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);
			serviceContext.setUserId(userId);

			KaleoDefinition kaleoDefinition =
				KaleoDefinitionLocalServiceUtil.updateTitle(
					name, version, title, serviceContext);

			return WorkflowModelUtil.toWorkflowDefinition(kaleoDefinition);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public void validateWorkflowDefinition(byte[] bytes)
		throws WorkflowException {

		_workflowEngine.validateWorkflowDefinition(
			new UnsyncByteArrayInputStream(bytes));
	}

	protected List<WorkflowDefinition> toWorkflowDefinitions(
		List<KaleoDefinition> kaleoDefinitions) {

		List<WorkflowDefinition> workflowDefinitions = new ArrayList<>(
			kaleoDefinitions.size());

		for (KaleoDefinition kaleoDefinition : kaleoDefinitions) {
			workflowDefinitions.add(
				WorkflowModelUtil.toWorkflowDefinition(kaleoDefinition));
		}

		return workflowDefinitions;
	}

	private WorkflowEngine _workflowEngine;

}