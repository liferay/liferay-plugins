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

package com.liferay.portal.workflow.kaleo.definition;

/**
 * @author Michael C. Han
 */
public class Transition {

	public Transition(
		String name, Node sourceNode, Node targetNode, boolean defaultValue) {

		_name = name;
		_sourceNode = sourceNode;
		_targetNode = targetNode;
		_default = defaultValue;
	}

	public String getName() {
		return _name;
	}

	public Node getSourceNode() {
		return _sourceNode;
	}

	public Node getTargetNode() {
		return _targetNode;
	}

	public Timer getTimer() {
		return _timer;
	}

	public boolean isDefault() {
		return _default;
	}

	public void setTimers(Timer timer) {
		_timer = timer;
	}

	private boolean _default;
	private String _name;
	private Node _sourceNode;
	private Node _targetNode;
	private Timer _timer;

}