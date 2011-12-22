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

package com.liferay.ipgeocoder.messaging;

import com.liferay.ipgeocoder.model.IPInfo;
import com.liferay.ipgeocoder.util.IPGeocoderUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Brian Wing Shun Chan
 */
public class IPGeocoderMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		String ipAddress = (String)message.getPayload();

		IPInfo ipInfo = IPGeocoderUtil.getIPInfo(ipAddress);

		Object payload = StringPool.BLANK;

		if (ipInfo != null) {
			payload = JSONFactoryUtil.createJSONObject(
				JSONFactoryUtil.serialize(ipInfo));
		}

		message.setPayload(payload);

		MessageBusUtil.sendMessage(
			message.getResponseDestinationName(), message);
	}

}