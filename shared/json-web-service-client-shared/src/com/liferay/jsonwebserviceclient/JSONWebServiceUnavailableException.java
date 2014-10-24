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

package com.liferay.jsonwebserviceclient;

import java.io.IOException;

/**
 * @author Ivica Cardic
 */
public class JSONWebServiceUnavailableException extends IOException {

	public JSONWebServiceUnavailableException() {
	}

	public JSONWebServiceUnavailableException(int status) {
		_status = status;
	}

	public JSONWebServiceUnavailableException(String message) {
		super(message);
	}

	public JSONWebServiceUnavailableException(String message, Throwable cause) {
		super(message, cause);
	}

	public JSONWebServiceUnavailableException(Throwable cause) {
		super(cause);
	}

	public int getStatus() {
		return _status;
	}

	private int _status;

}