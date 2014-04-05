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

package com.liferay.portal.workflow.kaleo.runtime.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.workflow.kaleo.definition.ExecutionType;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.util.ClassLoaderUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoActionLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoLogLocalServiceUtil;

import java.util.List;

/**
 * @author Michael C. Han
 */
public class ActionExecutorUtil {

	public static void executeKaleoActions(
			String kaleoClassName, long kaleoClassPK,
			ExecutionType executionType, ExecutionContext executionContext)
		throws PortalException, SystemException {

		List<KaleoAction> kaleoActions =
			KaleoActionLocalServiceUtil.getKaleoActions(
				kaleoClassName, kaleoClassPK, executionType.getValue());

		for (KaleoAction kaleoAction : kaleoActions) {
			long startTime = System.currentTimeMillis();

			String comment = _COMMENT_ACTION_SUCCESS;

			try {
				String[] scriptRequiredContexts = StringUtil.split(
					kaleoAction.getScriptRequiredContexts());

				ClassLoader[] classLoaders = ClassLoaderUtil.getClassLoaders(
					scriptRequiredContexts);

				ActionExecutor actionExecutor =
					ActionExecutorFactory.getActionExecutor(
						kaleoAction.getScriptLanguage());

				actionExecutor.execute(
					kaleoAction, executionContext, classLoaders);

				KaleoInstanceToken kaleoInstanceToken =
					executionContext.getKaleoInstanceToken();

				KaleoInstanceLocalServiceUtil.updateKaleoInstance(
					kaleoInstanceToken.getKaleoInstanceId(),
					executionContext.getWorkflowContext(),
					executionContext.getServiceContext());
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}

				comment = e.getMessage();
			}
			finally {
				KaleoLogLocalServiceUtil.addActionExecutionKaleoLog(
					executionContext.getKaleoInstanceToken(), kaleoAction,
					startTime, System.currentTimeMillis(), comment,
					executionContext.getServiceContext());
			}
		}
	}

	private static final String _COMMENT_ACTION_SUCCESS =
		"Action completed successfully.";

	private static Log _log = LogFactoryUtil.getLog(ActionExecutorUtil.class);

}