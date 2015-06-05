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

package com.liferay.portal.workflow.kaleo.runtime.notification.recipient;

import com.liferay.portal.workflow.kaleo.definition.RecipientType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class DefaultNotificationRecipientBuilderRegistry
	implements NotificationRecipientBuilderRegistry {

	@Override
	public NotificationRecipientBuilder
		getNotificationRecipientBuilder(RecipientType recipientType) {

		NotificationRecipientBuilder notificationRecipientBuilder =
			_notificationRecipientBuilders.get(recipientType);

		if (notificationRecipientBuilder == null) {
			throw new IllegalArgumentException(
				"No notification recipient builder for " + recipientType);
		}

		return notificationRecipientBuilder;
	}

	public void setNotificationRecipientBuilders(
		Map<RecipientType, NotificationRecipientBuilder>
			notificationRecipientBuilders) {

		_notificationRecipientBuilders = notificationRecipientBuilders;
	}

	private Map<RecipientType, NotificationRecipientBuilder>
		_notificationRecipientBuilders = new HashMap<>();

}