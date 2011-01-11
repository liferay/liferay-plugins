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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class Definition extends DefinitionNode {

	public void addNode(Node node) {
		_nodesMap.put(node.getName(), node);
	}

	public void configureParent(DefinitionNode parentNode) {
	}

	public String getDescription() {
		return _description;
	}

	public State getInitialState() {
		return _initialState;
	}

	public String getName() {
		return _name;
	}

	public Node getNode(String name) {
		return _nodesMap.get(name);
	}

	public Collection<Node> getNodes() {
		return Collections.unmodifiableCollection(_nodesMap.values());
	}

	public int getVersion() {
		return _version;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setVersion(String version) {
		_version = GetterUtil.getInteger(version);
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setInitialState(State initialState) {
		_initialState = initialState;
	}

	private String _description;
	private State _initialState;
	private String _name;
	private Map<String, Node> _nodesMap = new HashMap<String, Node>();
	private int _version;

}