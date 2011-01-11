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

package com.liferay.portal.workflow.kaleo.definition;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Michael C. Han
 */
public class Transition extends DefinitionNode {

	public void configureParent(DefinitionNode parentNode) {
		Transitions transitions = (Transitions)parentNode;

		transitions.add(this);
	}

	public Condition getCondition() {
		return _condition;
	}

	public String getName() {
		return _name;
	}

	public Node getSourceNode() {
		return _sourceNode;
	}

	public String getTargetName() {
		return _targetName;
	}

	public Node getTargetNode() {
		return _targetNode;
	}

	public boolean isDefault() {
		return _default;
	}

	public void setCondition(Condition condition) {
		_condition = condition;
	}

	public void setDefaultValue(String defaultValue) {
		_default = GetterUtil.getBoolean(defaultValue);
	}

	public void setName(String name) {
		_name = name;
	}

	public void setSourceNode(Node sourceNode) {
		_sourceNode = sourceNode;
	}

	public void setTargetName(String targetName) {
		_targetName = targetName;
	}

	public void setTargetNode(Node targetNode) {
		_targetNode = targetNode;
	}

	private Condition _condition;
	private boolean _default;
	private String _name;
	private String _targetName;
	private Node _sourceNode;
	private Node _targetNode;

}