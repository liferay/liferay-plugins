/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.runtime.graph;

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.workflow.kaleo.BaseKaleoBean;
import com.liferay.portal.workflow.kaleo.definition.ActionType;
import com.liferay.portal.workflow.kaleo.definition.NodeType;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoNotification;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.action.ActionExecutor;
import com.liferay.portal.workflow.kaleo.runtime.action.ActionExecutorFactory;
import com.liferay.portal.workflow.kaleo.runtime.node.NodeExecutor;
import com.liferay.portal.workflow.kaleo.runtime.node.NodeExecutorFactory;
import com.liferay.portal.workflow.kaleo.runtime.notification.NotificationMessageGenerator;
import com.liferay.portal.workflow.kaleo.runtime.notification.NotificationMessageGeneratorFactory;
import com.liferay.portal.workflow.kaleo.runtime.notification.NotificationSender;
import com.liferay.portal.workflow.kaleo.runtime.notification.NotificationSenderFactory;
import com.liferay.portal.workflow.kaleo.service.KaleoActionLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoLogLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoNotificationLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoNotificationRecipientLocalService;

import java.util.List;

/**
 * <a href="DefaultGraphWalker.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
@Transactional(
	isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
	rollbackFor = {PortalException.class, SystemException.class})
public class DefaultGraphWalker extends BaseKaleoBean implements GraphWalker {

	public void follow(
			KaleoNode sourceKaleoNode, KaleoNode targetKaleoNode,
			List<PathElement> remainingPathElement,
			ExecutionContext executionContext)
		throws PortalException, SystemException {

		if (sourceKaleoNode != null) {
			NodeExecutor nodeExecutor = NodeExecutorFactory.getNodeExecutor(
				NodeType.valueOf(sourceKaleoNode.getType()));

			nodeExecutor.exit(
				sourceKaleoNode, executionContext, remainingPathElement);

			executeKaleoActions(
				sourceKaleoNode.getKaleoNodeId(), ActionType.ON_EXIT,
				executionContext);

			sendKaleoNotifications(
				sourceKaleoNode.getKaleoNodeId(), ActionType.ON_EXIT,
				executionContext);
		}

		if (targetKaleoNode != null) {
			kaleoLogLocalService.addNodeEntryKaleoLog(
				executionContext.getKaleoInstanceToken(), sourceKaleoNode,
				targetKaleoNode, executionContext.getServiceContext());

			executeKaleoActions(
				targetKaleoNode.getKaleoNodeId(), ActionType.ON_ENTRY,
				executionContext);

			sendKaleoNotifications(
				targetKaleoNode.getKaleoNodeId(), ActionType.ON_ENTRY,
				executionContext);

			NodeExecutor nodeExecutor = NodeExecutorFactory.getNodeExecutor(
				NodeType.valueOf(targetKaleoNode.getType()));

			nodeExecutor.enter(
				targetKaleoNode, executionContext, remainingPathElement);
		}

		checkKaleoInstanceComplete(executionContext);
	}

	protected void checkKaleoInstanceComplete(ExecutionContext executionContext)
		throws PortalException, SystemException {

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		if (!kaleoInstanceToken.isCompleted()) {
			return;
		}

		KaleoInstance kaleoInstance = kaleoInstanceToken.getKaleoInstance();

		if (!kaleoInstance.isCompleted()) {
			return;
		}

		kaleoLogLocalService.addWorkflowInstanceEndKaleoLog(
			kaleoInstanceToken, executionContext.getServiceContext());
	}

	protected void executeKaleoActions(
			long kaleoNodeId, ActionType actionType,
			ExecutionContext executionContext)
		throws PortalException, SystemException {

		List<KaleoAction> kaleoActions =
			kaleoActionLocalService.getKaleoActions(
				kaleoNodeId, actionType.getValue());

		for (KaleoAction kaleoAction : kaleoActions) {
			long startTime = System.currentTimeMillis();
			String comment = _COMMENT_ACTION_SUCCESS;

			try {
				ActionExecutor actionExecutor =
					ActionExecutorFactory.getActionExecutor(
						kaleoAction.getLanguage());

				actionExecutor.execute(kaleoAction, executionContext);
			}
			catch (Exception e) {
				comment = e.getMessage();
			}
			finally {
				kaleoLogLocalService.addActionExecutionKaleoLog(
					executionContext.getKaleoInstanceToken(), kaleoAction,
					startTime, System.currentTimeMillis(), comment,
					executionContext.getServiceContext());
			}
		}
	}

	protected void sendKaleoNotifications(
			long kaleoNodeId, ActionType actionType,
			ExecutionContext executionContext)
		throws SystemException {

		List<KaleoNotification> kaleoNotifications=
			kaleoNotificationLocalService.getKaleoNotifications(
				kaleoNodeId, actionType.getValue());

		for (KaleoNotification kaleoNotification : kaleoNotifications) {

			try {
				NotificationMessageGenerator notificationMessageGenerator =
					NotificationMessageGeneratorFactory.
						getNotificationMessageGenerator(
							kaleoNotification.getLanguage());

				String notificationMessage =
					notificationMessageGenerator.generateMessage(
						kaleoNotification.getName(),
						kaleoNotification.getKaleoNodeId(),
						kaleoNotification.getTemplate(), executionContext);

				String notificationSubject = kaleoNotification.getDescription();

				String[] notificationTypes = StringUtil.split(
					kaleoNotification.getNotificationTypes());

				List<KaleoNotificationRecipient> notificationRecipients =
					kaleoNotificationRecipientLocalService.
						getKaleoNotificationRecipients(
							kaleoNotification.getKaleoNotificationId());

				if (notificationRecipients.isEmpty()) {
					if (_log.isInfoEnabled()) {
						_log.info(
							"No recipients found to notify with message " +
								kaleoNotification.getName() + " " +
									notificationMessage);
					}
					return;
				}

				for (String notificationType : notificationTypes) {
					try {
						NotificationSender notificationSender =
							NotificationSenderFactory.getNotificationSender(
								notificationType);

						notificationSender.sendNotification(
							notificationRecipients, notificationSubject,
							notificationMessage, executionContext);
					}
					catch (WorkflowException e) {
						if (_log.isErrorEnabled()) {
							_log.error("Unable to send notifications", e);
						}
					}
				}
			}
			catch (Exception e) {
				if (_log.isErrorEnabled()) {
					_log.error("Unable to send notifications", e);
				}
			}
		}
	}

	@BeanReference(type = KaleoActionLocalService.class)
	protected KaleoActionLocalService kaleoActionLocalService;

	@BeanReference(type = KaleoLogLocalService.class)
	protected KaleoLogLocalService kaleoLogLocalService;

	@BeanReference(type = KaleoNotificationLocalService.class)
	protected KaleoNotificationLocalService kaleoNotificationLocalService;

	@BeanReference(type = KaleoNotificationRecipientLocalService.class)
	protected KaleoNotificationRecipientLocalService
		kaleoNotificationRecipientLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		DefaultGraphWalker.class);

	private static final String _COMMENT_ACTION_SUCCESS =
		"Action completed successfully.";

}