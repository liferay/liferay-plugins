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

import com.liferay.portal.kernel.exception.PortalException;
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

	@Override
	public KaleoInstanceToken getKaleoInstanceToken() throws PortalException {
		return KaleoInstanceTokenLocalServiceUtil.getKaleoInstanceToken(
			getKaleoInstanceTokenId());
	}

	@Override
	public KaleoTaskInstanceToken getKaleoTaskInstanceToken() {
		return KaleoTaskInstanceTokenLocalServiceUtil.
			fetchKaleoTaskInstanceToken(getKaleoTaskInstanceTokenId());
	}

	@Override
	public KaleoTimer getKaleoTimer() throws PortalException {
		return KaleoTimerLocalServiceUtil.getKaleoTimer(getKaleoTimerId());
	}

}