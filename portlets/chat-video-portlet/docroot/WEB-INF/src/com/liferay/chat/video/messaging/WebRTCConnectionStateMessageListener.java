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

package com.liferay.chat.video.messaging;

import com.liferay.chat.video.WebRTCManager;
import com.liferay.chat.video.WebRTCManagerFactory;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import java.util.List;

/**
 * Verifies the connection states of all WebRTC managers at a regular interval.
 *
 * @author Philippe Proulx
 */
public class WebRTCConnectionStateMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		List<WebRTCManager> webRTCManagers =
			WebRTCManagerFactory.getWebRTCManagers();

		for (WebRTCManager webRTCManager : webRTCManagers) {
			webRTCManager.checkWebRTCConnectionsStates();
		}
	}

}