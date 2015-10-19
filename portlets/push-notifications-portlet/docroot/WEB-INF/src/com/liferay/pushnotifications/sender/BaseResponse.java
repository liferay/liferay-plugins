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

package com.liferay.pushnotifications.sender;

/**
 * @author Bruno Farache
 */
public class BaseResponse implements Response {

	public BaseResponse(String platform) {
		this(platform, null);
	}

	public BaseResponse(String platform, Exception exception) {
		this.platform = platform;

		if (exception != null) {
			succeeded = false;
			status = exception.getMessage();
		}
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getPayload() {
		return payload;
	}

	@Override
	public String getPlatform() {
		return platform;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public String getToken() {
		return token;
	}

	@Override
	public boolean isSucceeded() {
		return succeeded;
	}

	protected String id;
	protected String payload;
	protected String platform;
	protected String status;
	protected boolean succeeded;
	protected String token;

}