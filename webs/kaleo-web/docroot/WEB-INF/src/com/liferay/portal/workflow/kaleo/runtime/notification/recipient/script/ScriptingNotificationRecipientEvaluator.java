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

package com.liferay.portal.workflow.kaleo.runtime.notification.recipient.script;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.scripting.ScriptingUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.util.ScriptingContextBuilderUtil;
import com.liferay.portal.workflow.kaleo.util.WorkflowContextUtil;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Michael C. Han
 */
public class ScriptingNotificationRecipientEvaluator
	implements NotificationRecipientEvaluator {

	@Override
	public Map<String, ?> evaluate(
			KaleoNotificationRecipient kaleoNotificationRecipient,
			ExecutionContext executionContext)
		throws PortalException {

		String[] recipientScriptRequiredContexts = StringUtil.split(
			kaleoNotificationRecipient.getRecipientScriptRequiredContexts());

		Map<String, Object> inputObjects =
			ScriptingContextBuilderUtil.buildScriptingContext(executionContext);

		return ScriptingUtil.eval(
			null, inputObjects, _outputNames,
			kaleoNotificationRecipient.getRecipientScriptLanguage(),
			kaleoNotificationRecipient.getRecipientScript(),
			recipientScriptRequiredContexts);
	}

	private static final Set<String> _outputNames = new HashSet<>();

	static {
		_outputNames.add(
			ScriptingNotificationRecipientConstants.ROLES_RECIPIENT);
		_outputNames.add(
			ScriptingNotificationRecipientConstants.USER_RECIPIENT);
		_outputNames.add(WorkflowContextUtil.WORKFLOW_CONTEXT_NAME);
	}

}