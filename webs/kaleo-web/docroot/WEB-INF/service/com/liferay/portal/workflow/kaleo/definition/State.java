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
public class State extends Node {

	public State () {
		super(NodeType.STATE);
	}

	public boolean isInitial() {
		return _initial;
	}

	public boolean isTerminal() {
		if (getTransitions().isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}

	public void setInitial(String initial) {
		_initial = GetterUtil.getBoolean(initial);
	}

	private boolean _initial;

}