/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class Definition {

	public Definition(
		String name, String description, String content, int version) {

		_name = name;
		_description = description;
		_content = content;
		_version = version;
	}

	public void addNode(Node node) {
		if (_nodesMap.containsKey(node.getName())) {
			throw new IllegalArgumentException(
				"Duplicate node " + node.getName());
		}

		_nodesMap.put(node.getName(), node);

		if (node instanceof State) {
			State state = (State)node;

			if (state.isInitial()) {
				_initialState = state;
			}
		}
	}

	public String getContent() {
		return _content;
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

	private String _content;
	private String _description;
	private State _initialState;
	private String _name;
	private Map<String, Node> _nodesMap = new HashMap<String, Node>();
	private int _version;

}