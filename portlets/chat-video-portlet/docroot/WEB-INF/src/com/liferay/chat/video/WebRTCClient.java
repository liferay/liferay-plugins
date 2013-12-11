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
public class WebRTCClient {

	public WebRTCClient(long userId) {
		_userId = userId;

		updatePresence();
	}

	public boolean isAvailable() {
		return _isAvailable;
	}

	public void setIsAvailable(boolean available) {
		_isAvailable = available;
	}

	public synchronized void updatePresence() {
		_ts = System.currentTimeMillis();
	}

	private boolean _isAvailable = false;
	private long _ts;
	private final long _userId;

}