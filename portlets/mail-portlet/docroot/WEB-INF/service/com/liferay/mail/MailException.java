/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.mail;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * <a href="MailException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 */
public class MailException extends PortalException {

	// Account

	public static final int ACCOUNT_CONNECTIONS_FAILED = 1;

	public static final int ACCOUNT_INCOMING_CONNECTION_FAILED = 2;

	public static final int ACCOUNT_OUTGOING_CONNECTION_FAILED = 3;

	// Folder

	public static final int FOLDER_DENIED_BY_SERVER = 4;

	public static final int FOLDER_ALREADY_EXISTS = 5;

	public static final int FOLDER_DOES_NOT_EXISTS = 6;

	public static final int FOLDER_PAGE_DOES_NOT_EXIST = 7;

	public static final int FOLDER_REQUIRED = 8;

	public static final int FOLDER_INVALID_DESTINATION = 9;

	// Message

	public static final int MESSAGE_INVALID_ADDRESS = 10;

	public static final int MESSAGE_INVALID_FLAG = 11;

	public static final int MESSAGE_NOT_FOUND_ON_SERVER = 12;

	public static final int MESSAGE_HAS_NO_RECIPIENTS = 13;

	public static final int MESSAGE_NOT_SELECTED = 14;

	public MailException() {
		super();
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

	public MailException(int type) {
		_type = type;
	}

	public MailException(int type, Throwable cause) {
		super(cause);

		_type = type;
	}

	public MailException(int type, String value) {
		_type = type;
		_value = value;
	}

	public MailException(int type, Throwable cause, String value) {
		super(cause);

		_type = type;
		_value = value;
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