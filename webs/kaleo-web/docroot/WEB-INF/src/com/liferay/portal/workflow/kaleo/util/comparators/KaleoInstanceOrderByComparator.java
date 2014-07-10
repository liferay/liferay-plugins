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

package com.liferay.portal.workflow.kaleo.util.comparators;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorAdapter;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.util.WorkflowModelUtil;

/**
 * @author William Newbury
 */
public class KaleoInstanceOrderByComparator
	extends OrderByComparatorAdapter<KaleoInstance, WorkflowInstance> {

	public static OrderByComparator<KaleoInstance> getOrderByComparator(
		OrderByComparator<WorkflowInstance> orderByComparator,
		ServiceContext serviceContext) {

		if (orderByComparator == null) {
			return null;
		}

		return new KaleoInstanceOrderByComparator(
			orderByComparator, serviceContext);
	}

	public WorkflowInstance adapt(KaleoInstance kaleoInstance) {
		try {
			KaleoInstanceToken rootKaleoInstanceToken =
				kaleoInstance.getRootKaleoInstanceToken(_serviceContext);

			return WorkflowModelUtil.toWorkflowInstance(
				kaleoInstance, rootKaleoInstanceToken);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	private KaleoInstanceOrderByComparator(
		OrderByComparator<WorkflowInstance> orderByComparator,
		ServiceContext serviceContext) {

		super(orderByComparator);

		_serviceContext = serviceContext;
	}

	private ServiceContext _serviceContext;

}