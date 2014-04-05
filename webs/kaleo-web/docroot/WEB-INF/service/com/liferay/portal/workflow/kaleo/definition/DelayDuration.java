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
public class DelayDuration {

	public DelayDuration(double duration, DurationScale durationScale) {
		_duration = duration;
		_durationScale = durationScale;
	}

	public double getDuration() {
		return _duration;
	}

	public DurationScale getDurationScale() {
		return _durationScale;
	}

	private double _duration;
	private DurationScale _durationScale;

}