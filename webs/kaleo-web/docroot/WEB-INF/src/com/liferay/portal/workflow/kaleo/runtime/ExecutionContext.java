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

import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class ExecutionContext implements Serializable {

	public ExecutionContext(
		KaleoInstanceToken kaleoInstanceToken,
		KaleoTaskInstanceToken kaleoTaskInstanceToken,
		Map<String, Serializable> workflowContext,
		ServiceContext serviceContext) {

		this(kaleoInstanceToken, workflowContext, serviceContext);

		_kaleoTaskInstanceToken = kaleoTaskInstanceToken;
	}

	public ExecutionContext(
		KaleoInstanceToken kaleoInstanceToken,
		KaleoTimerInstanceToken kaleoTimerInstanceToken,
		Map<String, Serializable> workflowContext,
		ServiceContext serviceContext) {

		this(kaleoInstanceToken, workflowContext, serviceContext);

		_kaleoTimerInstanceToken = kaleoTimerInstanceToken;
	}

	public ExecutionContext(
		KaleoInstanceToken kaleoInstanceToken,
		Map<String, Serializable> workflowContext,
		ServiceContext serviceContext) {

		_kaleoInstanceToken = kaleoInstanceToken;
		_workflowContext = new HashMap<String, Serializable>(workflowContext);
		_serviceContext = serviceContext;
	}

	public KaleoInstanceToken getKaleoInstanceToken() {
		return _kaleoInstanceToken;
	}

	public KaleoTaskInstanceToken getKaleoTaskInstanceToken() {
		return _kaleoTaskInstanceToken;
	}

	public KaleoTimerInstanceToken getKaleoTimerInstanceToken() {
		return _kaleoTimerInstanceToken;
	}

	public ServiceContext getServiceContext() {
		return _serviceContext;
	}

	public String getTransitionName() {
		return _transitionName;
	}

	public Map<String, Serializable> getWorkflowContext() {
		return _workflowContext;
	}

	public void setKaleoTaskInstanceToken(
		KaleoTaskInstanceToken kaleoTaskInstanceToken) {

		_kaleoTaskInstanceToken = kaleoTaskInstanceToken;
	}

	public void setKaleoTimerInstanceToken(
		KaleoTimerInstanceToken kaleoTimerInstanceToken) {

		_kaleoTimerInstanceToken = kaleoTimerInstanceToken;
	}

	public void setTransitionName(String transitionName) {
		_transitionName = transitionName;
	}

	private KaleoInstanceToken _kaleoInstanceToken;
	private KaleoTaskInstanceToken _kaleoTaskInstanceToken;
	private KaleoTimerInstanceToken _kaleoTimerInstanceToken;
	private ServiceContext _serviceContext;
	private String _transitionName;
	private Map<String, Serializable> _workflowContext;

}