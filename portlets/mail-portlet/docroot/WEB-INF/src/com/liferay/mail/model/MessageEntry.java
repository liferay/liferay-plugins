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

package com.liferay.mail.model;

import java.util.Date;

/**
 * <a href="MessageEntry.java.html"><b><i>View Source</i></b></a>
 *
 * @author    Scott Lee
 *
 */
public class MessageEntry {

	public MessageEntry(
			long companyId, long userId, String emailAddress, String folderName,
			long messageUid) {

		setCompanyId(companyId);
		setUserId(userId);
		setEmailAddress(emailAddress);
		setFolderName(folderName);
		setMessageUid(messageUid);
	}

	public MessageEntry(
			long companyId, long groupId, long userId, String emailAddress,
			String folderName, long messageUid, String title, String content) {

		setCompanyId(companyId);
		setGroupId(groupId);
		setUserId(userId);
		setEmailAddress(emailAddress);
		setFolderName(folderName);
		setMessageUid(messageUid);
		setTitle(title);
		setContent(content);
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getMessageUid() {
		return _messageUid;
	}

	public void setMessageUid(long messageUid) {
		_messageUid = messageUid;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public String getFolderName() {
		return _folderName;
	}

	public void setFolderName(String folderName) {
		_folderName = folderName;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	private long _groupId;
	private long _companyId;
	private long _userId;
	private Date _modifiedDate;
	private long _messageUid;
	private String _emailAddress;
	private String _folderName;
	private String _title;
	private String _content;

}