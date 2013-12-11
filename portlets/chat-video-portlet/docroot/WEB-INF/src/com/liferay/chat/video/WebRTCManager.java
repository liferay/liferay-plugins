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

import java.util.List;

/**
 * @author Philippe Proulx
 */
public class WebRTCManager {

	public List<Long> getWebRTCClientIds() {
		return null;
	}

	public WebRTCClient getWebRTCClient(long userId) {
		return null;
	}

	public boolean hasWebRTCClient(long userId) {
		return false;
	}

	public void removeWebRTCClient(long userId) {
	}

	protected void addWebRTCClient(long userId) {
	}

	protected WebRTCClient doGetWebRTCClient(long userId) {
		return null;
	}

}