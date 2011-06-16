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

package com.liferay.portal.workflow.kaleo.runtime.timer.messaging;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.KaleoSignaler;
import com.liferay.portal.workflow.kaleo.runtime.WorkflowEngine;
import com.liferay.portal.workflow.kaleo.service.KaleoTimerInstanceTokenLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.util.WorkflowContextUtil;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Marcellus Tavares
 */
public class TimerMessageListener extends BaseMessageListener {

	public void setKaleoSignaler(KaleoSignaler kaleoSignaler) {
		_kaleoSignaler = kaleoSignaler;
	}

	public void setWorkflowEngine(WorkflowEngine workflowEngine) {
		_workflowEngine = workflowEngine;
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		long kaleoTimerInstanceTokenId = message.getLong(
			"kaleoTimerInstanceTokenId");

		KaleoTimerInstanceToken kaleoTimerInstanceToken =
			getKaleoTimerInstanceToken(message);

		KaleoInstanceToken kaleoInstanceToken =
			kaleoTimerInstanceToken.getKaleoInstanceToken();

		Map<String, Serializable> workflowContext = WorkflowContextUtil.convert(
			kaleoTimerInstanceToken.getWorkflowContext());

		ServiceContext serviceContext = (ServiceContext)workflowContext.get(
			WorkflowConstants.CONTEXT_SERVICE_CONTEXT);

		ExecutionContext executionContext =
			_workflowEngine.executeTimerWorkflowInstance(
				kaleoTimerInstanceTokenId, serviceContext, workflowContext);

		KaleoNode currentKaleoNode = kaleoInstanceToken.getCurrentKaleoNode();

		_kaleoSignaler.signalExecute(currentKaleoNode, executionContext);
	}

	protected KaleoTimerInstanceToken getKaleoTimerInstanceToken(
			Message message)
		throws PortalException, SystemException {

		long kaleoTimerInstanceTokenId = message.getLong(
			"kaleoTimerInstanceTokenId");

		KaleoTimerInstanceToken kaleoTimerInstanceToken =
			KaleoTimerInstanceTokenLocalServiceUtil.getKaleoTimerInstanceToken(
				kaleoTimerInstanceTokenId);

		return kaleoTimerInstanceToken;
	}

	private KaleoSignaler _kaleoSignaler;
	private WorkflowEngine _workflowEngine;

}