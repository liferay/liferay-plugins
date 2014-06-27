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

package com.liferay.portal.workflow.kaleo.runtime.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.service.KaleoLogLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoTimerInstanceTokenLocalServiceUtil;

import java.util.List;

/**
 * @author Marcellus Tavares
 */
public class ExecutionUtil {

	public static void checkKaleoInstanceComplete(
			ExecutionContext executionContext)
		throws PortalException {

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		if (!kaleoInstanceToken.isCompleted()) {
			return;
		}

		KaleoInstance kaleoInstance = kaleoInstanceToken.getKaleoInstance();

		if (!kaleoInstance.isCompleted()) {
			return;
		}

		KaleoLogLocalServiceUtil.addWorkflowInstanceEndKaleoLog(
			kaleoInstanceToken, executionContext.getServiceContext());
	}

	public static void completeKaleoTimerInstances(
			ExecutionContext executionContext)
		throws PortalException {

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		List<KaleoTimerInstanceToken> kaleoTimerInstanceTokens =
			KaleoTimerInstanceTokenLocalServiceUtil.getKaleoTimerInstanceTokens(
				kaleoInstanceToken.getKaleoInstanceTokenId(), false, false,
				executionContext.getServiceContext());

		KaleoTimerInstanceTokenLocalServiceUtil.
			completeKaleoTimerInstanceTokens(
				kaleoTimerInstanceTokens, executionContext.getServiceContext());
	}

	public static boolean isKaleoInstanceBlocked(
		ExecutionContext executionContext) {

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		int count =
			KaleoTimerInstanceTokenLocalServiceUtil.
				getKaleoTimerInstanceTokensCount(
					kaleoInstanceToken.getKaleoInstanceTokenId(), false, true,
					executionContext.getServiceContext());

		if (count > 0) {
			return true;
		}

		return false;
	}

}