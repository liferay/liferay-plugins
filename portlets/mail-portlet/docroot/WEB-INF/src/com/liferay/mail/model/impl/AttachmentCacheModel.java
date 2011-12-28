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

package com.liferay.mail.model.impl;

import com.liferay.mail.model.Attachment;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing Attachment in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Attachment
 * @generated
 */
public class AttachmentCacheModel implements CacheModel<Attachment>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{attachmentId=");
		sb.append(attachmentId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", accountId=");
		sb.append(accountId);
		sb.append(", folderId=");
		sb.append(folderId);
		sb.append(", messageId=");
		sb.append(messageId);
		sb.append(", contentPath=");
		sb.append(contentPath);
		sb.append(", fileName=");
		sb.append(fileName);
		sb.append(", size=");
		sb.append(size);
		sb.append("}");

		return sb.toString();
	}

	public Attachment toEntityModel() {
		AttachmentImpl attachmentImpl = new AttachmentImpl();

		attachmentImpl.setAttachmentId(attachmentId);
		attachmentImpl.setCompanyId(companyId);
		attachmentImpl.setUserId(userId);
		attachmentImpl.setAccountId(accountId);
		attachmentImpl.setFolderId(folderId);
		attachmentImpl.setMessageId(messageId);

		if (contentPath == null) {
			attachmentImpl.setContentPath(StringPool.BLANK);
		}
		else {
			attachmentImpl.setContentPath(contentPath);
		}

		if (fileName == null) {
			attachmentImpl.setFileName(StringPool.BLANK);
		}
		else {
			attachmentImpl.setFileName(fileName);
		}

		attachmentImpl.setSize(size);

		attachmentImpl.resetOriginalValues();

		return attachmentImpl;
	}

	public long attachmentId;
	public long companyId;
	public long userId;
	public long accountId;
	public long folderId;
	public long messageId;
	public String contentPath;
	public String fileName;
	public long size;
}