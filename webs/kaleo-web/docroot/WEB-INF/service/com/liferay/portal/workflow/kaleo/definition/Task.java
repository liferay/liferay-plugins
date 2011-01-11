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
import com.liferay.portal.kernel.util.Validator;

import java.util.Set;

/**
 * @author Michael C. Han
 */
public class Task extends Node {

	public Task() {
		super(NodeType.TASK);
	}

	public Set<Assignment> getAssignments() {
		return _assignments;
	}

	public State getStartState() {
		return _startState;
	}

	public State getTargetState() {
		return _targetState;
	}

	public void setAssignments(Set<Assignment> assignments) {
		_assignments = assignments;
	}

	public double getDuration() {
		return _duration;
	}

	public DurationScale getDurationScale() {
		if (Validator.isNotNull(_scale)) {
			return DurationScale.parse(_scale);
		}

		return null;
	}

	public void setStartState(State startState) {
		_startState = startState;
	}

	public void setTargetState(State targetState) {
		_targetState = targetState;
	}

	public void setDuration(String duration) {
		_duration = GetterUtil.getLong(duration);
	}

	public void setScale(String scale) {
		_scale = scale;
	}

	private Set<Assignment> _assignments;
	private double _duration;
	private String _scale;
	private State _startState;
	private State _targetState;

}