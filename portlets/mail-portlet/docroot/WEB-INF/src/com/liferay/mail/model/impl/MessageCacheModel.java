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

package com.liferay.mail.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.mail.model.Message;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Message in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Message
 * @generated
 */
@ProviderType
public class MessageCacheModel implements CacheModel<Message>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MessageCacheModel)) {
			return false;
		}

		MessageCacheModel messageCacheModel = (MessageCacheModel)obj;

		if (messageId == messageCacheModel.messageId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, messageId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

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
		sb.append(", contentType=");
		sb.append(contentType);
		sb.append("}");

		return sb.toString();
	}

	@Override
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

		if (contentType == null) {
			messageImpl.setContentType(StringPool.BLANK);
		}
		else {
			messageImpl.setContentType(contentType);
		}

		messageImpl.resetOriginalValues();

		return messageImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		messageId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		accountId = objectInput.readLong();

		folderId = objectInput.readLong();
		sender = objectInput.readUTF();
		to = objectInput.readUTF();
		cc = objectInput.readUTF();
		bcc = objectInput.readUTF();
		sentDate = objectInput.readLong();
		subject = objectInput.readUTF();
		preview = objectInput.readUTF();
		body = objectInput.readUTF();
		flags = objectInput.readUTF();

		size = objectInput.readLong();

		remoteMessageId = objectInput.readLong();
		contentType = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(messageId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(accountId);

		objectOutput.writeLong(folderId);

		if (sender == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sender);
		}

		if (to == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(to);
		}

		if (cc == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cc);
		}

		if (bcc == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(bcc);
		}

		objectOutput.writeLong(sentDate);

		if (subject == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(subject);
		}

		if (preview == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(preview);
		}

		if (body == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(body);
		}

		if (flags == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(flags);
		}

		objectOutput.writeLong(size);

		objectOutput.writeLong(remoteMessageId);

		if (contentType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(contentType);
		}
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
	public String contentType;
}