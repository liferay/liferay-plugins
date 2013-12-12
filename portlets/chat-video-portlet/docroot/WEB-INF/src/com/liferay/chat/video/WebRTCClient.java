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

	public void addConnection(WebRTCClient otherWebRTCClient, WebRTCConnection connection) {
		_connections.put(otherWebRTCClient, connection);
	}

	public Set<WebRTCClient> getConnectedWebRTCClients() {
		return _connections.keySet();
	}

	public WebRTCConnection getConnection(WebRTCClient otherWebRTCClient) {
		return _connections.get(otherWebRTCClient);
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

	public void removeAllBilateralConnections() {
		for (WebRTCClient otherWebRTCClient : _connections.keySet()) {
			otherWebRTCClient.removeUnilateralConnection(this);
		}

		_connections.clear();
	}

	public void removeBilateralConnection(WebRTCClient otherWebRTCClient) {
		otherWebRTCClient.removeUnilateralConnection(this);
		removeUnilateralConnection(otherWebRTCClient);
	}

	public void reset() {
		setAvailable(false);
		removeAllBilateralConnections();
	}

	public void setAvailable(boolean available) {
		_available = available;
	}

	public void updatePresenceTime() {
		_presenceTime = System.currentTimeMillis();
	}

	protected void removeUnilateralConnection(WebRTCClient otherWebRTCClient) {
		_connections.remove(otherWebRTCClient);
	}

	private boolean _available;
	private Map<WebRTCClient, WebRTCConnection> _connections =
		new ConcurrentHashMap<WebRTCClient, WebRTCConnection>();
	private long _presenceTime;
	private long _userId;

}