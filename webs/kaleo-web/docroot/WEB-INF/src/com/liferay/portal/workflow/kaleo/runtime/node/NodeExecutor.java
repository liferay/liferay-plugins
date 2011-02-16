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

package com.liferay.portal.workflow.kaleo.runtime.node;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.graph.PathElement;

import java.util.List;

/**
 * @author Michael C. Han
 */
public interface NodeExecutor {

	/**
	 * Provide any specific logic to be handle for entry into a node
	 *
	 * @param currentKaleoNode
	 * @param executionContext
	 * @throws PortalException
	 * @throws SystemException
	 */
	public void enter(
			KaleoNode currentKaleoNode, ExecutionContext executionContext)
		throws PortalException, SystemException;

	/**
	 * Provide logic to be executed while in the node.  This includes
	 * calculating the paths to take (if any) for exiting the node.
	 *
	 * @param currentKaleoNode
	 * @param executionContext
	 * @param remainingPathElement
	 * @throws PortalException
	 * @throws SystemException
	 */
	public void execute(
			KaleoNode currentKaleoNode, ExecutionContext executionContext,
			List<PathElement> remainingPathElement)
		throws PortalException, SystemException;

	/**
	 * Perform any actions necessary to exit a node.
	 *
	 * @param currentKaleoNode
	 * @param executionContext
	 * @param remainingPathElement
	 * @throws PortalException
	 * @throws SystemException
	 */
	public void exit(
			KaleoNode currentKaleoNode, ExecutionContext executionContext,
			List<PathElement> remainingPathElement)
		throws PortalException, SystemException;

}