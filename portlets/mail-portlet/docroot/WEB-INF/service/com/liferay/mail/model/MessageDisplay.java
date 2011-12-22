/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.mail.model;

import com.liferay.mail.model.Attachment;
import com.liferay.mail.model.Message;

import java.util.List;

/**
 * @author Scott Lee
 */
public class MessageDisplay {

	public MessageDisplay(
		Message message, List<Attachment> attachments, int messageCount) {

		_message = message;
		_attachments = attachments;
		_messageCount = messageCount;
	}

	public List<Attachment> getAttachments() {
		return _attachments;
	}

	public Message getMessage() {
		return _message;
	}

	public int getMessageCount() {
		return _messageCount;
	}

	private List<Attachment> _attachments;
	private Message _message;
	private int _messageCount;

}