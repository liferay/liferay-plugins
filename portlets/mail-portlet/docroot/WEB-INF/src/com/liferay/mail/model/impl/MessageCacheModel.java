/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.mail.model.impl;

import com.liferay.mail.model.Message;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Message in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Message
 * @generated
 */
public class MessageCacheModel implements CacheModel<Message>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{messageId=");
		sb.append(messageId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", accountId=");
		sb.append(accountId);
		sb.append(", folderId=");
		sb.append(folderId);
		sb.append(", sender=");
		sb.append(sender);
		sb.append(", to=");
		sb.append(to);
		sb.append(", cc=");
		sb.append(cc);
		sb.append(", bcc=");
		sb.append(bcc);
		sb.append(", sentDate=");
		sb.append(sentDate);
		sb.append(", subject=");
		sb.append(subject);
		sb.append(", preview=");
		sb.append(preview);
		sb.append(", body=");
		sb.append(body);
		sb.append(", flags=");
		sb.append(flags);
		sb.append(", size=");
		sb.append(size);
		sb.append(", remoteMessageId=");
		sb.append(remoteMessageId);
		sb.append("}");

		return sb.toString();
	}

	public Message toEntityModel() {
		MessageImpl messageImpl = new MessageImpl();

		messageImpl.setMessageId(messageId);
		messageImpl.setCompanyId(companyId);
		messageImpl.setUserId(userId);

		if (userName == null) {
			messageImpl.setUserName(StringPool.BLANK);
		}
		else {
			messageImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			messageImpl.setCreateDate(null);
		}
		else {
			messageImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			messageImpl.setModifiedDate(null);
		}
		else {
			messageImpl.setModifiedDate(new Date(modifiedDate));
		}

		messageImpl.setAccountId(accountId);
		messageImpl.setFolderId(folderId);

		if (sender == null) {
			messageImpl.setSender(StringPool.BLANK);
		}
		else {
			messageImpl.setSender(sender);
		}

		if (to == null) {
			messageImpl.setTo(StringPool.BLANK);
		}
		else {
			messageImpl.setTo(to);
		}

		if (cc == null) {
			messageImpl.setCc(StringPool.BLANK);
		}
		else {
			messageImpl.setCc(cc);
		}

		if (bcc == null) {
			messageImpl.setBcc(StringPool.BLANK);
		}
		else {
			messageImpl.setBcc(bcc);
		}

		if (sentDate == Long.MIN_VALUE) {
			messageImpl.setSentDate(null);
		}
		else {
			messageImpl.setSentDate(new Date(sentDate));
		}

		if (subject == null) {
			messageImpl.setSubject(StringPool.BLANK);
		}
		else {
			messageImpl.setSubject(subject);
		}

		if (preview == null) {
			messageImpl.setPreview(StringPool.BLANK);
		}
		else {
			messageImpl.setPreview(preview);
		}

		if (body == null) {
			messageImpl.setBody(StringPool.BLANK);
		}
		else {
			messageImpl.setBody(body);
		}

		if (flags == null) {
			messageImpl.setFlags(StringPool.BLANK);
		}
		else {
			messageImpl.setFlags(flags);
		}

		messageImpl.setSize(size);
		messageImpl.setRemoteMessageId(remoteMessageId);

		messageImpl.resetOriginalValues();

		return messageImpl;
	}

	public long messageId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long accountId;
	public long folderId;
	public String sender;
	public String to;
	public String cc;
	public String bcc;
	public long sentDate;
	public String subject;
	public String preview;
	public String body;
	public String flags;
	public long size;
	public long remoteMessageId;
}