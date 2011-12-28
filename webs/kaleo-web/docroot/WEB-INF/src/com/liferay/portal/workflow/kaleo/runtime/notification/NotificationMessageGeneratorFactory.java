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

package com.liferay.portal.workflow.kaleo.runtime.notification;

import com.liferay.portal.kernel.workflow.WorkflowException;

import java.util.Map;

/**
 * @author Michael C. Han
 */
public class NotificationMessageGeneratorFactory {

	public static NotificationMessageGenerator getNotificationMessageGenerator(
			String templateLanguage)
		throws WorkflowException {

		NotificationMessageGenerator notificationMessageGenerator =
			_notificationMessageGenerators.get(templateLanguage);

		if (notificationMessageGenerator == null) {
			throw new WorkflowException(
				"Invalid template language " + templateLanguage);
		}

		return notificationMessageGenerator;
	}

	public void setNotificationMessageGenerators(
		Map<String, NotificationMessageGenerator>
		notificationMessageGenerators) {

		_notificationMessageGenerators = notificationMessageGenerators;
	}

	public static Map<String, NotificationMessageGenerator>
		_notificationMessageGenerators;

}