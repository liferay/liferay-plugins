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

package com.liferay.portal.workflow.kaleo.runtime;

import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;

import java.io.Serializable;

import java.util.Map;

/**
 * <a href="ExecutionContext.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class ExecutionContext {

	public ExecutionContext(
		KaleoInstanceToken kaleoInstanceToken,
		Map<String, Serializable> context, ServiceContext serviceContext) {

		_kaleoInstanceToken = kaleoInstanceToken;
		_serviceContext = serviceContext;
		_context = context;
	}

	public ExecutionContext(
		KaleoInstanceToken kaleoInstanceToken,
		KaleoTaskInstanceToken kaleoTaskInstanceToken,
		KaleoTaskInstanceAssignment kaleoTaskInstanceAssigment,
		Map<String, Serializable> context, ServiceContext serviceContext) {

		_kaleoInstanceToken = kaleoInstanceToken;
		_kaleoTaskInstanceToken = kaleoTaskInstanceToken;
		_kaleoTaskInstanceAssigment = kaleoTaskInstanceAssigment;
		_context = context;
		_serviceContext = serviceContext;
	}

	public Map<String, Serializable> getContext() {
		return _context;
	}

	public KaleoInstanceToken getKaleoInstanceToken() {
		return _kaleoInstanceToken;
	}

	public KaleoTaskInstanceAssignment getKaleoTaskInstanceAssigment() {
		return _kaleoTaskInstanceAssigment;
	}

	public KaleoTaskInstanceToken getKaleoTaskInstanceToken() {
		return _kaleoTaskInstanceToken;
	}

	public ServiceContext getServiceContext() {
		return _serviceContext;
	}

	public String getTransitionName() {
		return _transitionName;
	}

	public void setKaleoTaskInstanceAssigment(
		KaleoTaskInstanceAssignment kaleoTaskInstanceAssigment) {
		_kaleoTaskInstanceAssigment = kaleoTaskInstanceAssigment;
	}

	public void setKaleoTaskInstanceToken(
		KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		_kaleoTaskInstanceToken = kaleoTaskInstanceToken;
	}

	public void setTransitionName(String transitionName) {
		_transitionName = transitionName;
	}

	private Map<String, Serializable> _context;
	private KaleoInstanceToken _kaleoInstanceToken;
	private KaleoTaskInstanceAssignment _kaleoTaskInstanceAssigment;
	private KaleoTaskInstanceToken _kaleoTaskInstanceToken;
	private ServiceContext _serviceContext;
	private String _transitionName;

}