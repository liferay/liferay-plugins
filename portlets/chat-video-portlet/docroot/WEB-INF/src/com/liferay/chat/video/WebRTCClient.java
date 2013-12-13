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

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Philippe Proulx
 */
public class WebRTCClient {

	public WebRTCClient(long userId) {
		_userId = userId;

		updatePresenceTime();
	}

	public void addWebRTCConnection(
		WebRTCClient otherWebRTCClient, WebRTCConnection webRTCConnection) {

		_webRTCConnections.put(otherWebRTCClient, webRTCConnection);
	}

	public Set<WebRTCClient> getConnectedWebRTCClients() {
		return _webRTCConnections.keySet();
	}

	public WebRTCConnection getWebRTCConnection(
		WebRTCClient otherWebRTCClient) {

		return _webRTCConnections.get(otherWebRTCClient);
	}

	public long getPresenceTime() {
		return _presenceTime;
	}

	public long getUserId() {
		return _userId;
	}

	public boolean isAvailable() {
		return _available;
	}

	public void removeBilateralWebRTCConnections() {
		for (WebRTCClient otherWebRTCClient : _webRTCConnections.keySet()) {
			otherWebRTCClient.removeUnilateralWebRTCConnection(this);
		}

		_webRTCConnections.clear();
	}

	public void removeBilateralWebRTCConnection(WebRTCClient otherWebRTCClient) {
		otherWebRTCClient.removeUnilateralWebRTCConnection(this);
		removeUnilateralWebRTCConnection(otherWebRTCClient);
	}

	public void reset() {
		setAvailable(false);
		removeBilateralWebRTCConnections();
	}

	public void setAvailable(boolean available) {
		_available = available;
	}

	public void updatePresenceTime() {
		_presenceTime = System.currentTimeMillis();
	}

	protected void removeUnilateralWebRTCConnection(WebRTCClient otherWebRTCClient) {
		_webRTCConnections.remove(otherWebRTCClient);
	}

	private boolean _available;
	private Map<WebRTCClient, WebRTCConnection> _webRTCConnections =
		new ConcurrentHashMap<WebRTCClient, WebRTCConnection>();
	private long _presenceTime;
	private long _userId;

}