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

import com.liferay.portal.kernel.json.JSONObject;

/**
 * Represents WebRTC mail, which is sent from a source WebRTC client to a
 * destination WebRTC client.
 *
 * @author Philippe Proulx
 */
public abstract class WebRTCMail {

	public WebRTCMail(long sourceUserId, JSONObject messageJSONObject) {
		_sourceUserId = sourceUserId;
		_messageJSONObject = messageJSONObject;
	}

	public WebRTCMail(WebRTCMail webRTCMail) {
		_sourceUserId = webRTCMail._sourceUserId;
		_messageJSONObject = webRTCMail._messageJSONObject;
	}

	public JSONObject getMessageJSONObject() {
		return _messageJSONObject;
	}

	public abstract String getMessageType();

	public long getSourceUserId() {
		return _sourceUserId;
	}

	private final JSONObject _messageJSONObject;
	private final long _sourceUserId;

}