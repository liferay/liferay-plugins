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

package com.liferay.portal.workflow.kaleo.runtime.assignment;

import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Michael C. Han
 */
public class DefaultTaskAssignmentSelector implements TaskAssignmentSelector {

	@Override
	public Collection<KaleoTaskAssignment> calculateTaskAssignments(
		KaleoTaskAssignment kaleoTaskAssignment,
		ExecutionContext executionContext, ClassLoader... classLoaders) {

		List<KaleoTaskAssignment> taskAssignments = new ArrayList<>(1);

		taskAssignments.add(kaleoTaskAssignment);

		return taskAssignments;
	}

}