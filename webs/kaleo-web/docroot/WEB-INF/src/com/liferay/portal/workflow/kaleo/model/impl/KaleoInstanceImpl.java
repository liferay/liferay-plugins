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

package com.liferay.portal.workflow.kaleo.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceTokenLocalServiceUtil;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoInstanceImpl
	extends KaleoInstanceBaseImpl {

	public KaleoInstanceImpl() {
	}

	public KaleoDefinition getKaleoDefinition()
		throws PortalException, SystemException {

		return KaleoDefinitionLocalServiceUtil.getKaleoDefinition(
			getKaleoDefinitionId());
	}

	public KaleoInstanceToken getRootKaleoInstanceToken(
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return KaleoInstanceTokenLocalServiceUtil.getRootKaleoInstanceToken(
			getKaleoInstanceId(), workflowContext, serviceContext);
	}

	public KaleoInstanceToken getRootKaleoInstanceToken(
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return getRootKaleoInstanceToken(null, serviceContext);
	}

}