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
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Philippe Proulx
 */
public class WebRTCManager {

	public WebRTCManager() {
		_webRTCManagers.add(this);
	}

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
		WebRTCClient webRTCClient = getWebRTCClient(userId);

		if (webRTCClient == null) {
			return;
		}

		webRTCClient.removeBilateralWebRTCConnections();

		_webRTCClients.remove(userId);
	}

	public void updateWebRTCClientPresence(long userId) {
		WebRTCClient webRTCClient = getWebRTCClient(userId);

		if (webRTCClient == null) {
			return;
		}

		webRTCClient.updatePresenceTime();
	}

	protected static void notifyWebRTCClientLostConnection(WebRTCClient destWebRTCClient, WebRTCClient sourceWebRTCClient, String reason) {
		String formatString = "{\"type\": \"status\", \"status\": \"lost\", \"reason\": \"%s\"}";
		ConnectionStateWebRTCMail connectionStateWebRTCMail =
			new ConnectionStateWebRTCMail(sourceWebRTCClient.getUserId(),
				String.format(formatString, reason));
		WebRTCMailbox destWebRTCMailbox = destWebRTCClient.getOutgoingWebRTCMailbox();

		destWebRTCMailbox.pushWebRTCMail(connectionStateWebRTCMail);
	}

	protected void addWebRTCClient(long userId) {
		if (!_webRTCClients.containsKey(userId)) {
			_webRTCClients.put(userId, new WebRTCClient(userId));
		}
	}

	protected void checkWebRTCConnectionsStates() {
		for (WebRTCClient webRTCClient : _webRTCClients.values()) {
			for (WebRTCClient otherWebRTCClient : webRTCClient.getWebRTCClients()) {
				WebRTCConnection webRTCConnection =
					webRTCClient.getWebRTCConnection(otherWebRTCClient);

				// If the (WebRTC client -> other WebRTC client) connection is initiated

				if (webRTCConnection.getState() == WebRTCConnection.State.INITIATED) {
					long durationTime = webRTCConnection.getInitiatedDurationTime();

					// Timeout?

					if (durationTime > _CONNECTION_TIMEOUT_TIME) {

						// Disconnect both clients

						webRTCClient.removeBilateralWebRTCConnection(otherWebRTCClient);
						notifyWebRTCClientLostConnection(webRTCClient,
							otherWebRTCClient, _TIMEOUT_REASON);
						notifyWebRTCClientLostConnection(otherWebRTCClient,
							webRTCClient, _TIMEOUT_REASON);
					}
				}
			}
		}
	}

	private static long _CONNECTION_TIMEOUT_TIME = 60000;

	private static String _TIMEOUT_REASON = "timeout";

	private static List<WebRTCManager> _webRTCManagers =
		new CopyOnWriteArrayList<WebRTCManager>();

	private Map<Long, WebRTCClient> _webRTCClients =
		new ConcurrentHashMap<Long, WebRTCClient>();

}