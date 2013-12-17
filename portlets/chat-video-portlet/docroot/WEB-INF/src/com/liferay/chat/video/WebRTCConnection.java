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

	public State getState() {
		return _state;
	}

	public WebRTCClient getWebRTCClient() {
		return _webRTCClient;
	}

	public void setState(State state) {
		_state = state;

		if (state == State.INITIATED) {
		}
		else {
		}
	}

	public enum State {

		CONNECTED, INITIATED, DISCONNECTED

	}

	private State _state = State.DISCONNECTED;
	private WebRTCClient _webRTCClient;

}