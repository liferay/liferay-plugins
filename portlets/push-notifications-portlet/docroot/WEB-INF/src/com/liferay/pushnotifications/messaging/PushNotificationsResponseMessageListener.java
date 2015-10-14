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

package com.liferay.pushnotifications.messaging;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.pushnotifications.sender.Response;

/**
 * @author Bruno Farache
 */
public class PushNotificationsResponseMessageListener
	implements MessageListener {

	@Override
	public void receive(Message message) {
		Response response = (Response)message.getPayload();

		String json = JSONFactoryUtil.serialize(response);

		if (!response.isSucceeded()) {
			_log.error(json);
		}
		else if (_log.isDebugEnabled()) {
			_log.debug(json);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PushNotificationsResponseMessageListener.class);

}