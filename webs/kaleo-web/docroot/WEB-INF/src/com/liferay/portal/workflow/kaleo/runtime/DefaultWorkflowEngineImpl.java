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

package com.liferay.portal.workflow.kaleo.runtime;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.BaseKaleoBean;
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.deployment.WorkflowDeployer;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;
import com.liferay.portal.workflow.kaleo.parser.WorkflowModelParser;
import com.liferay.portal.workflow.kaleo.parser.WorkflowValidator;
import com.liferay.portal.workflow.kaleo.runtime.node.NodeExecutor;
import com.liferay.portal.workflow.kaleo.runtime.node.NodeExecutorFactory;
import com.liferay.portal.workflow.kaleo.util.WorkflowModelUtil;
import com.liferay.portal.workflow.kaleo.util.comparators.KaleoInstanceOrderByComparator;

import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author Michael C. Han
 */
@Transactional(
	isolation = Isolation.PORTAL, propagation = Propagation.REQUIRED,
	rollbackFor = {Exception.class}
)
public class DefaultWorkflowEngineImpl
	extends BaseKaleoBean implements WorkflowEngine {

	@Override
	public void deleteWorkflowDefinition(
			String name, int version, ServiceContext serviceContext)
		throws WorkflowException {

		try {
			kaleoDefinitionLocalService.deleteKaleoDefinition(
				name, version, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public void deleteWorkflowInstance(
			long workflowInstanceId, ServiceContext serviceContext)
		throws WorkflowException {

		try {
			kaleoInstanceLocalService.deleteKaleoInstance(workflowInstanceId);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public WorkflowDefinition deployWorkflowDefinition(
			String title, InputStream inputStream,
			ServiceContext serviceContext)
		throws WorkflowException {

		try {
			Definition definition = _workflowModelParser.parse(inputStream);

			if (_workflowValidator != null) {
				_workflowValidator.validate(definition);
			}

			WorkflowDefinition workflowDefinition = _workflowDeployer.deploy(
				title, definition, serviceContext);

			return workflowDefinition;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public ExecutionContext executeTimerWorkflowInstance(
			long kaleoTimerInstanceTokenId, ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws WorkflowException {

		try {
			KaleoTimerInstanceToken kaleoTimerInstanceToken =
				kaleoTimerInstanceTokenLocalService.getKaleoTimerInstanceToken(
					kaleoTimerInstanceTokenId);

			KaleoInstanceToken kaleoInstanceToken =
				kaleoTimerInstanceToken.getKaleoInstanceToken();

			final ExecutionContext executionContext = new ExecutionContext(
				kaleoInstanceToken, kaleoTimerInstanceToken, workflowContext,
				serviceContext);

			KaleoTaskInstanceToken kaleoTaskInstanceToken =
				kaleoTimerInstanceToken.getKaleoTaskInstanceToken();

			executionContext.setKaleoTaskInstanceToken(kaleoTaskInstanceToken);

			final KaleoNode currentKaleoNode =
				kaleoInstanceToken.getCurrentKaleoNode();

			NodeExecutor nodeExecutor = NodeExecutorFactory.getNodeExecutor(
				currentKaleoNode.getType());

			nodeExecutor.executeTimer(currentKaleoNode, executionContext);

			TransactionCommitCallbackUtil.registerCallback(
				new Callable<Void>() {

					@Override
					public Void call() throws Exception {
						_kaleoSignaler.signalExecute(
							currentKaleoNode, executionContext);

						return null;
					}

				}
			);

			return executionContext;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public List<String> getNextTransitionNames(
			long workflowInstanceId, ServiceContext serviceContext)
		throws WorkflowException {

		try {
			KaleoInstance kaleoInstance =
				kaleoInstanceLocalService.getKaleoInstance(workflowInstanceId);

			KaleoInstanceToken rootKaleoInstanceToken =
				kaleoInstance.getRootKaleoInstanceToken(null, serviceContext);

			List<String> transitionNames = new ArrayList<>();

			getNextTransitionNames(rootKaleoInstanceToken, transitionNames);

			return transitionNames;
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public WorkflowInstance getWorkflowInstance(
			long workflowInstanceId, ServiceContext serviceContext)
		throws WorkflowException {

		try {
			KaleoInstance kaleoInstance =
				kaleoInstanceLocalService.getKaleoInstance(workflowInstanceId);

			KaleoInstanceToken rootKaleoInstanceToken =
				kaleoInstance.getRootKaleoInstanceToken(serviceContext);

			return WorkflowModelUtil.toWorkflowInstance(
				kaleoInstance, rootKaleoInstanceToken);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public int getWorkflowInstanceCount(
			Long userId, String assetClassName, Long assetClassPK,
			Boolean completed, ServiceContext serviceContext)
		throws WorkflowException {

		try {
			return kaleoInstanceLocalService.getKaleoInstancesCount(
				userId, assetClassName, assetClassPK, completed,
				serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public int getWorkflowInstanceCount(
			Long userId, String[] assetClassNames, Boolean completed,
			ServiceContext serviceContext)
		throws WorkflowException {

		try {
			return kaleoInstanceLocalService.getKaleoInstancesCount(
				userId, assetClassNames, completed, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public int getWorkflowInstanceCount(
			String workflowDefinitionName, int workflowDefinitionVersion,
			boolean completed, ServiceContext serviceContext)
		throws WorkflowException {

		try {
			return kaleoInstanceLocalService.getKaleoInstancesCount(
				workflowDefinitionName, workflowDefinitionVersion, completed,
				serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public List<WorkflowInstance> getWorkflowInstances(
			Long userId, String assetClassName, Long assetClassPK,
			Boolean completed, int start, int end,
			OrderByComparator<WorkflowInstance> orderByComparator,
			ServiceContext serviceContext)
		throws WorkflowException {

		try {
			List<KaleoInstance> kaleoInstances =
				kaleoInstanceLocalService.getKaleoInstances(
					userId, assetClassName, assetClassPK, completed, start, end,
					KaleoInstanceOrderByComparator.getOrderByComparator(
						orderByComparator, serviceContext),
					serviceContext);

			return toWorkflowInstances(kaleoInstances, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public List<WorkflowInstance> getWorkflowInstances(
			Long userId, String[] assetClassNames, Boolean completed, int start,
			int end, OrderByComparator<WorkflowInstance> orderByComparator,
			ServiceContext serviceContext)
		throws WorkflowException {

		try {
			List<KaleoInstance> kaleoInstances =
				kaleoInstanceLocalService.getKaleoInstances(
					userId, assetClassNames, completed, start, end,
					KaleoInstanceOrderByComparator.getOrderByComparator(
						orderByComparator, serviceContext),
					serviceContext);

			return toWorkflowInstances(kaleoInstances, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public List<WorkflowInstance> getWorkflowInstances(
			String workflowDefinitionName, int workflowDefinitionVersion,
			boolean completed, int start, int end,
			OrderByComparator<WorkflowInstance> orderByComparator,
			ServiceContext serviceContext)
		throws WorkflowException {

		try {
			List<KaleoInstance> kaleoInstances =
				kaleoInstanceLocalService.getKaleoInstances(
					workflowDefinitionName, workflowDefinitionVersion,
					completed, start, end,
					KaleoInstanceOrderByComparator.getOrderByComparator(
						orderByComparator, serviceContext),
					serviceContext);

			return toWorkflowInstances(kaleoInstances, serviceContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public void setKaleoSignaler(KaleoSignaler kaleoSignaler) {
		_kaleoSignaler = kaleoSignaler;
	}

	public void setWorkflowDeployer(WorkflowDeployer workflowDeployer) {
		_workflowDeployer = workflowDeployer;
	}

	public void setWorkflowModelParser(
		WorkflowModelParser workflowModelParser) {

		_workflowModelParser = workflowModelParser;
	}

	public void setWorkflowValidator(WorkflowValidator workflowValidator) {
		_workflowValidator = workflowValidator;
	}

	@Override
	public WorkflowInstance signalWorkflowInstance(
			long workflowInstanceId, final String transitionName,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws WorkflowException {

		try {
			KaleoInstance kaleoInstance = doUpdateContext(
				workflowInstanceId, workflowContext, serviceContext);

			KaleoInstanceToken kaleoInstanceToken =
				kaleoInstance.getRootKaleoInstanceToken(serviceContext);

			if (Validator.isNotNull(transitionName)) {

				// Validate that the transition actually exists before moving
				// forward

				KaleoNode currentKaleoNode =
					kaleoInstanceToken.getCurrentKaleoNode();

				currentKaleoNode.getKaleoTransition(transitionName);
			}

			serviceContext.setScopeGroupId(kaleoInstanceToken.getGroupId());

			final ExecutionContext executionContext = new ExecutionContext(
				kaleoInstanceToken, workflowContext, serviceContext);

			TransactionCommitCallbackUtil.registerCallback(
				new Callable<Void>() {

					@Override
					public Void call() throws Exception {
						try {
							_kaleoSignaler.signalExit(
								transitionName, executionContext);
						}
						catch (Exception e) {
							throw new WorkflowException(
								"Unable to signal next transition", e);
						}

						return null;
					}

				}
			);

			return WorkflowModelUtil.toWorkflowInstance(
				kaleoInstance, kaleoInstanceToken, workflowContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public WorkflowInstance startWorkflowInstance(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			final String transitionName,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws WorkflowException {

		try {
			KaleoDefinition kaleoDefinition =
				kaleoDefinitionLocalService.getKaleoDefinition(
					workflowDefinitionName, workflowDefinitionVersion,
					serviceContext);

			if (!kaleoDefinition.isActive()) {
				throw new WorkflowException(
					"Inactive workflow definition with name " +
						workflowDefinitionName + " and version " +
							workflowDefinitionVersion);
			}

			KaleoNode kaleoStartNode = kaleoDefinition.getKaleoStartNode();

			if (Validator.isNotNull(transitionName)) {

				// Validate that the transition actually exists before moving
				// forward

				kaleoStartNode.getKaleoTransition(transitionName);
			}

			long scopeGroupId = serviceContext.getScopeGroupId();

			if (scopeGroupId != WorkflowConstants.DEFAULT_GROUP_ID) {
				Group group = GroupLocalServiceUtil.getGroup(scopeGroupId);

				if (group.isLayout()) {
					group = GroupLocalServiceUtil.getGroup(
						group.getParentGroupId());

					serviceContext.setScopeGroupId(group.getGroupId());
				}
			}

			KaleoInstance kaleoInstance =
				kaleoInstanceLocalService.addKaleoInstance(
					kaleoDefinition.getKaleoDefinitionId(),
					kaleoDefinition.getName(), kaleoDefinition.getVersion(),
					workflowContext, serviceContext);

			KaleoInstanceToken rootKaleoInstanceToken =
				kaleoInstance.getRootKaleoInstanceToken(
					workflowContext, serviceContext);

			rootKaleoInstanceToken.setCurrentKaleoNode(kaleoStartNode);

			kaleoLogLocalService.addWorkflowInstanceStartKaleoLog(
				rootKaleoInstanceToken, serviceContext);

			final ExecutionContext executionContext = new ExecutionContext(
				rootKaleoInstanceToken, workflowContext, serviceContext);

			TransactionCommitCallbackUtil.registerCallback(
				new Callable<Void>() {

					@Override
					public Void call() throws Exception {
						try {
							_kaleoSignaler.signalEntry(
								transitionName, executionContext);
						}
						catch (Exception e) {
							throw new WorkflowException(
								"Unable to start workflow", e);
						}

						return null;
					}

				}
			);

			return WorkflowModelUtil.toWorkflowInstance(
				kaleoInstance, rootKaleoInstanceToken, workflowContext);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public WorkflowInstance updateContext(
			long workflowInstanceId, Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws WorkflowException {

		try {
			KaleoInstance kaleoInstance = doUpdateContext(
				workflowInstanceId, workflowContext, serviceContext);

			KaleoInstanceToken rootKaleoInstanceToken =
				kaleoInstance.getRootKaleoInstanceToken(serviceContext);

			return WorkflowModelUtil.toWorkflowInstance(
				kaleoInstance, rootKaleoInstanceToken);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public void validateWorkflowDefinition(InputStream inputStream)
		throws WorkflowException {

		try {
			Definition definition = _workflowModelParser.parse(inputStream);

			if (_workflowValidator != null) {
				_workflowValidator.validate(definition);
			}
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	protected KaleoInstance doUpdateContext(
			long workflowInstanceId, Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws Exception {

		return kaleoInstanceLocalService.updateKaleoInstance(
			workflowInstanceId, workflowContext, serviceContext);
	}

	protected void getNextTransitionNames(
			KaleoInstanceToken kaleoInstanceToken, List<String> transitionNames)
		throws Exception {

		if (kaleoInstanceToken.hasIncompleteChildrenKaleoInstanceToken()) {
			List<KaleoInstanceToken> incompleteChildrenKaleoInstanceTokens =
				kaleoInstanceToken.getIncompleteChildrenKaleoInstanceTokens();

			for (KaleoInstanceToken incompleteChildrenKaleoInstanceToken :
					incompleteChildrenKaleoInstanceTokens) {

				getNextTransitionNames(
					incompleteChildrenKaleoInstanceToken, transitionNames);
			}
		}
		else {
			KaleoNode kaleoNode = kaleoInstanceToken.getCurrentKaleoNode();

			List<KaleoTransition> kaleoTransitions =
				kaleoNode.getKaleoTransitions();

			for (KaleoTransition kaleoTransition : kaleoTransitions) {
				transitionNames.add(kaleoTransition.getName());
			}
		}
	}

	protected List<WorkflowInstance> toWorkflowInstances(
			List<KaleoInstance> kaleoInstances, ServiceContext serviceContext)
		throws PortalException {

		List<WorkflowInstance> workflowInstances = new ArrayList<>(
			kaleoInstances.size());

		for (KaleoInstance kaleoInstance : kaleoInstances) {
			KaleoInstanceToken rootKaleoInstanceToken =
				kaleoInstance.getRootKaleoInstanceToken(serviceContext);

			workflowInstances.add(
				WorkflowModelUtil.toWorkflowInstance(
					kaleoInstance, rootKaleoInstanceToken));
		}

		return workflowInstances;
	}

	private KaleoSignaler _kaleoSignaler;
	private WorkflowDeployer _workflowDeployer;
	private WorkflowModelParser _workflowModelParser;
	private WorkflowValidator _workflowValidator;

}