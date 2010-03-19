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

package com.liferay.portal.workflow.kaleo.runtime.calendar;

import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.workflow.kaleo.definition.DueDateDuration;
import com.liferay.portal.workflow.kaleo.definition.DurationScale;

import java.util.Calendar;
import java.util.Date;

/**
 * <a href="DefaultDueDateCalculator.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class DefaultDueDateCalculator implements DueDateCalculator {

	public Date getDueDate(Date startDate, DueDateDuration dueDateDuration) {
		Calendar cal = CalendarFactoryUtil.getCalendar();

		cal.setTime(startDate);

		DurationScale durationScale = dueDateDuration.getDurationScale();

		int duration = (int)Math.round(dueDateDuration.getDuration());

		if (durationScale.equals(DurationScale.SECONDS)) {
			cal.add(Calendar.SECOND, duration);
		}
		else if (durationScale.equals(DurationScale.MINUTES)) {
			cal.add(Calendar.MINUTE, duration);
		}
		else if (durationScale.equals(DurationScale.HOURS)) {
			cal.add(Calendar.HOUR, duration);
		}
		else if (durationScale.equals(DurationScale.DAYS)) {
			cal.add(Calendar.DAY_OF_YEAR, duration);
		}
		else if (durationScale.equals(DurationScale.MONTHS)) {
			cal.add(Calendar.MONTH, duration);
		}
		else if (durationScale.equals(DurationScale.YEARS)) {
			cal.add(Calendar.YEAR, duration);
		}

		return cal.getTime();
	}

}