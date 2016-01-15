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

package com.liferay.mail.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Scott Lee
 */
public class MailException extends PortalException {

	public static final int ACCOUNT_ALREADY_EXISTS = 1;

	public static final int ACCOUNT_CONNECTIONS_FAILED = 2;

	public static final int ACCOUNT_INCOMING_CONNECTION_FAILED = 3;

	public static final int ACCOUNT_OUTGOING_CONNECTION_FAILED = 4;

	public static final int FOLDER_ALREADY_EXISTS = 5;

	public static final int FOLDER_CREATE_FAILED = 6;

	public static final int FOLDER_DELETE_FAILED = 7;

	public static final int FOLDER_DOES_NOT_EXIST = 8;

	public static final int FOLDER_INVALID_DESTINATION = 9;

	public static final int FOLDER_PAGE_DOES_NOT_EXIST = 10;

	public static final int FOLDER_RENAME_FAILED = 11;

	public static final int FOLDER_REQUIRED = 12;

	public static final int MESSAGE_HAS_NO_RECIPIENTS = 13;

	public static final int MESSAGE_INVALID_ADDRESS = 14;

	public static final int MESSAGE_INVALID_FLAG = 15;

	public static final int MESSAGE_NOT_FOUND_ON_SERVER = 16;

	public static final int MESSAGE_NOT_SELECTED = 17;

	public MailException() {
	}

	public MailException(int type) {
		_type = type;
	}

	public MailException(int type, String value) {
		_type = type;
		_value = value;
	}

	public MailException(int type, Throwable cause) {
		super(cause);

		_type = type;
	}

	public MailException(int type, Throwable cause, String value) {
		super(cause);

		_type = type;
		_value = value;
	}

	public MailException(String msg) {
		super(msg);
	}

	public MailException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public MailException(Throwable cause) {
		super(cause);
	}

	public int getType() {
		return _type;
	}

	public String getValue() {
		return _value;
	}

	private int _type;
	private String _value;

}