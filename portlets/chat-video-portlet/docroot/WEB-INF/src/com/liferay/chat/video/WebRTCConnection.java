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

/**
 * @author Philippe Proulx
 */
public class WebRTCConnection {

	public WebRTCConnection(WebRTCClient callerWebRTCClient) {
		_callerWebRTCClient = callerWebRTCClient;
	}

	public WebRTCClient getCallerWebRTCClient() {
		return _callerWebRTCClient;
	}

	public long getInitatedTsMs() {
		if (_initiatedTimeMs == -1) {
			return -1;
		}

		return System.currentTimeMillis() - _initiatedTimeMs;
	}

	public State getState() {
		return _currentState;
	}

	public void setState(State state) {
		_currentState = state;

		if (state == State.INITIATED) {
			_initiatedTimeMs = System.currentTimeMillis();
		} else {
			_initiatedTimeMs = -1;
		}
	}

	public enum State {
		INITIATED, CONNECTED, DISCONNECTED
	}

	private WebRTCClient _callerWebRTCClient;
	private State _currentState = State.DISCONNECTED;
	private long _initiatedTimeMs = -1;

}