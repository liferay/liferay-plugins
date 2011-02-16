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

import java.util.Set;

/**
 * @author Marcellus Tavares
 */
public class Timer extends Node {

	public Timer(
		String name, String description, boolean defaultValue,
		boolean required) {

		super(NodeType.TIMER, name, description);

		_default = defaultValue;
		_required = required;
	}

	public DelayDuration getDelayDuration() {
		return _delayDuration;
	}

	public Set<Assignment> getReassignments() {
		return _reassignments;
	}

	public boolean isDefault() {
		return _default;
	}

	public boolean isRequired() {
		return _required;
	}

	public void setDelayDuration(DelayDuration delayDuration) {
		_delayDuration = delayDuration;
	}

	public void setReassignments(Set<Assignment> reassignments) {
		_reassignments = reassignments;
	}

	private boolean _default;
	private DelayDuration _delayDuration;
	private Set<Assignment> _reassignments;
	private boolean _required;

}