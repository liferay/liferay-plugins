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

package com.liferay.portal.workflow.kaleo.definition;

/**
 * <a href="Transition.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class Transition {

	public Transition(String name, Node sourceNode) {
		_name = name;
		_sourceNode = sourceNode;
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

	public boolean isDefault() {
		return _default;
	}

	public void setDefault(boolean defaultValue) {
		_default = defaultValue;
	}

	public void setTargetNode(Node targetNode) {
		_targetNode = targetNode;
	}

	private boolean _default;
	private String _name;
	private Node _sourceNode;
	private Node _targetNode;

}