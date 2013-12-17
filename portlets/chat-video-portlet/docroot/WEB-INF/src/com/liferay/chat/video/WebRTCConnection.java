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

	public WebRTCConnection(WebRTCClient webRTCClient) {
		_webRTCClient = webRTCClient;
	}

	public long getInitatedTsMs() {
		if (_initiatedTimeMs == 0) {
			return 0;
		}

		return System.currentTimeMillis() - _initiatedTimeMs;
	}

	public State getState() {
		return _state;
	}

	public WebRTCClient getWebRTCClient() {
		return _webRTCClient;
	}

	public void setState(State state) {
		_state = state;

		if (state == State.INITIATED) {
			_initiatedTimeMs = System.currentTimeMillis();
		}
		else {
			_initiatedTimeMs = 0;
		}
	}

	public enum State {

		CONNECTED, INITIATED, DISCONNECTED

	}

	private long _initiatedTimeMs;
	private State _state = State.DISCONNECTED;
	private WebRTCClient _webRTCClient;

}