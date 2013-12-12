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

		updatePresenceTime();
	}

	public boolean isAvailable() {
		return _available;
	}

	public void setAvailable(boolean available) {
		_available = available;
	}

	public void updatePresenceTime() {
		_presenceTime = System.currentTimeMillis();
	}

	private boolean _available;
	private long _presenceTime;
	private final long _userId;

}