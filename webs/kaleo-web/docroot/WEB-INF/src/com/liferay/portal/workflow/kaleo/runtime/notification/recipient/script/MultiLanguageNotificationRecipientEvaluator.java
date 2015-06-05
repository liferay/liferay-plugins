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
import com.liferay.portal.workflow.kaleo.definition.ScriptLanguage;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class MultiLanguageNotificationRecipientEvaluator
	implements NotificationRecipientEvaluator {

	@Override
	public Map<String, ?> evaluate(
			KaleoNotificationRecipient kaleoNotificationRecipient,
			ExecutionContext executionContext)
		throws PortalException {

		ScriptLanguage scriptLanguage = ScriptLanguage.parse(
			kaleoNotificationRecipient.getRecipientScriptLanguage());

		NotificationRecipientEvaluator notificationRecipientEvaluator =
			_notificationRecipientEvaluators.get(scriptLanguage);

		if (notificationRecipientEvaluator == null) {
			throw new IllegalArgumentException(
				"No notification recipient evaluator for script language " +
					scriptLanguage);
		}

		return notificationRecipientEvaluator.evaluate(
			kaleoNotificationRecipient, executionContext);
	}

	public void setNotificationRecipientEvaluators(
		Map<String, NotificationRecipientEvaluator>
			notificationRecipientEvaluators) {

		for (Map.Entry<String, NotificationRecipientEvaluator> entry :
				notificationRecipientEvaluators.entrySet()) {

			ScriptLanguage scriptLanguage = ScriptLanguage.parse(
				entry.getKey());

			_notificationRecipientEvaluators.put(
				scriptLanguage, entry.getValue());
		}
	}

	private Map<ScriptLanguage, NotificationRecipientEvaluator>
		_notificationRecipientEvaluators = new HashMap<>();

}