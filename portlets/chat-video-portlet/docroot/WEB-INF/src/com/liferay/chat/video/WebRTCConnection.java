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

package com.liferay.chat.video;

/**
 * Represents a <a href="http://en.wikipedia.org/wiki/WebRTC">Web Real-Time
 * Communication</a> (WebRTC) connection between two clients. It is owned by
 * both connected clients, but still holds a reference to the original (source)
 * caller. The connections are thread-safe.
 *
 * @author Philippe Proulx
 */
public class WebRTCConnection {

	public WebRTCConnection(WebRTCClient sourceWebRTCClient) {
		_sourceWebRTCClient = sourceWebRTCClient;
	}

	public long getInitiatedDurationTime() {
		if (_initiatedTime == 0) {
			return 0;
		}

		return System.currentTimeMillis() - _initiatedTime;
	}

	public WebRTCClient getSourceWebRTCClient() {
		return _sourceWebRTCClient;
	}

	public State getState() {
		return _state;
	}

	public void setState(State state) {
		_state = state;

		if (state == State.INITIATED) {
			_initiatedTime = System.currentTimeMillis();
		}
		else {
			_initiatedTime = 0;
		}
	}

	public enum State {

		CONNECTED, INITIATED, DISCONNECTED

	}

	private long _initiatedTime = 0;
	private WebRTCClient _sourceWebRTCClient;
	private State _state = State.DISCONNECTED;

}