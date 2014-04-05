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

package com.liferay.portal.workflow.kaleo.runtime.graph;

import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;

import java.io.Serializable;

/**
 * @author Michael C. Han
 */
public class PathElement implements Serializable {

	public PathElement(
		KaleoNode startKaleoNode, KaleoNode targetKaleoNode,
		ExecutionContext executionContext) {

		_startNode = startKaleoNode;
		_targetNode = targetKaleoNode;
		_executionContext = executionContext;
	}

	public ExecutionContext getExecutionContext() {
		return _executionContext;
	}

	public KaleoNode getStartNode() {
		return _startNode;
	}

	public KaleoNode getTargetNode() {
		return _targetNode;
	}

	private ExecutionContext _executionContext;
	private KaleoNode _startNode;
	private KaleoNode _targetNode;

}