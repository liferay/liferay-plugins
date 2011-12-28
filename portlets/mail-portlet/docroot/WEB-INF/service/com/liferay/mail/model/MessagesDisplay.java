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

import java.util.List;

/**
 * @author Scott Lee
 */
public class MessagesDisplay {

	public MessagesDisplay(
		List<Message> messages, int pageNumber, int messagesPerPage,
		int messageCount) {

		_messages = messages;
		_pageNumber = pageNumber;
		_messagesPerPage = messagesPerPage;
		_messageCount = messageCount;
	}

	public int getEndMessageNumber() {
		int messageNumber = _pageNumber * _messagesPerPage;

		if (messageNumber > _messageCount) {
			return _messageCount;
		}
		else {
			return messageNumber;
		}
	}

	public int getMessageCount() {
		return _messageCount;
	}

	public List<Message> getMessages() {
		return _messages;
	}

	public int getPageCount() {
		return (int)(Math.ceil(_messageCount / (double)_messagesPerPage));
	}

	public int getPageNumber() {
		return _pageNumber;
	}

	public int getStartMessageNumber() {
		if (_messageCount == 0) {
			return 0;
		}

		return ((_pageNumber - 1) * _messagesPerPage) + 1;
	}

	private int _messageCount;
	private List<Message> _messages;
	private int _messagesPerPage;
	private int _pageNumber;

}