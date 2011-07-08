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

package com.liferay.portal.workflow.kaleo.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTimer;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceTokenLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceTokenLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoTimerLocalServiceUtil;

/**
 * @author Marcellus Tavares
 */
public class KaleoTimerInstanceTokenImpl
	extends KaleoTimerInstanceTokenBaseImpl {

	public KaleoTimerInstanceTokenImpl() {
	}

	public KaleoInstanceToken getKaleoInstanceToken()
		throws PortalException, SystemException {

		return KaleoInstanceTokenLocalServiceUtil.getKaleoInstanceToken(
			getKaleoInstanceTokenId());
	}

	public KaleoTaskInstanceToken getKaleoTaskInstanceToken()
		throws SystemException {

		return KaleoTaskInstanceTokenLocalServiceUtil.
			fetchKaleoTaskInstanceToken(getKaleoTaskInstanceTokenId());
	}

	public KaleoTimer getKaleoTimer() throws PortalException, SystemException {
		return KaleoTimerLocalServiceUtil.getKaleoTimer(getKaleoTimerId());
	}

}