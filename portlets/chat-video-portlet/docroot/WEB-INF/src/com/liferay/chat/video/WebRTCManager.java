/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.chat.video;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Philippe Proulx
 */
public class WebRTCManager {

	public List<Long> getAvailableWebRTCClientIds() {
		List<Long> availableUserIds = new ArrayList<Long>();

		for (long userId : _webRTCClients.keySet()) {
			if (hasAvailableWebRTCClient(userId)) {
				availableUserIds.add(userId);
			}
		}

		return availableUserIds;
	}

	public WebRTCClient getWebRTCClient(long userId) {
		return _webRTCClients.get(userId);
	}

	public boolean hasAvailableWebRTCClient(long userId) {
		WebRTCClient webRTCClient = _webRTCClients.get(userId);

		if (webRTCClient == null) {
			return false;
		}

		return webRTCClient.isAvailable();
	}

	public void removeWebRTCClient(long userId) {
	}

	protected void addWebRTCClient(long userId) {
		if (!_webRTCClients.containsKey(userId)) {
			_webRTCClients.put(userId, new WebRTCClient(userId));
		}
	}

	private Map<Long, WebRTCClient> _webRTCClients =
		new ConcurrentHashMap<Long, WebRTCClient>();

}