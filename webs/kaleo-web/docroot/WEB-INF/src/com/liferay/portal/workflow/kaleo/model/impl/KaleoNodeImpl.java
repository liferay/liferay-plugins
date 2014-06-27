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
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;
import com.liferay.portal.workflow.kaleo.service.KaleoTransitionLocalServiceUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoNodeImpl extends KaleoNodeBaseImpl {

	public KaleoNodeImpl() {
	}

	@Override
	public KaleoTransition getDefaultKaleoTransition() throws PortalException {
		return KaleoTransitionLocalServiceUtil.getDefaultKaleoTransition(
			getKaleoNodeId());
	}

	@Override
	public KaleoTransition getKaleoTransition(String name)
		throws PortalException {

		return KaleoTransitionLocalServiceUtil.getKaleoTransition(
			getKaleoNodeId(), name);
	}

	@Override
	public List<KaleoTransition> getKaleoTransitions() {
		return KaleoTransitionLocalServiceUtil.getKaleoTransitions(
			getKaleoNodeId());
	}

	@Override
	public boolean hasKaleoTransition() {
		int count = KaleoTransitionLocalServiceUtil.getKaleoTransitionsCount(
			getKaleoNodeId());

		if (count > 0) {
			return true;
		}
		else {
			return false;
		}
	}

}