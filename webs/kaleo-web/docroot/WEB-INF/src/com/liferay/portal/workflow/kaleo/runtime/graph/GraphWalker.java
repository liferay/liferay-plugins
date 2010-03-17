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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;

import java.io.Serializable;

import java.util.Map;

/**
 * <a href="DefaultGraphWalker.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public interface GraphWalker {

	public ExecutionContext completeTask(
			String comments, ExecutionContext executionContext)
		throws PortalException, SystemException;

	public ExecutionContext initialize(
			String workflowDefinitionName, Integer workflowDefinitionVersion,
			Map<String, Serializable> context, ServiceContext serviceContext)
		throws PortalException, SystemException;

	public void signalEntry(
			String transitionName, ExecutionContext executionContext)
		throws PortalException, SystemException;

	public void signalExit(
			String transitionName, ExecutionContext executionContext)
		throws PortalException, SystemException;

}