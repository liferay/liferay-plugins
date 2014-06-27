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

package com.liferay.portal.workflow.kaleo.model.impl;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTimer;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskAssignmentLocalServiceUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoTimerImpl extends KaleoTimerBaseImpl {

	public KaleoTimerImpl() {
	}

	@Override
	public List<KaleoTaskAssignment> getKaleoTaskReassignments() {
		return KaleoTaskAssignmentLocalServiceUtil.getKaleoTaskAssignments(
			KaleoTimer.class.getName(), getKaleoTimerId());
	}

	@Override
	public boolean isRecurring() {
		if (Validator.isNotNull(getRecurrenceScale())) {
			return true;
		}
		else {
			return false;
		}
	}

}