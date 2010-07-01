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

package com.liferay.mail.model.impl;

import com.liferay.mail.model.Attachment;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

/**
 * <a href="AttachmentModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the Mail_Attachment table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AttachmentImpl
 * @see       com.liferay.mail.model.Attachment
 * @see       com.liferay.mail.model.AttachmentModel
 * @generated
 */
public class AttachmentModelImpl extends BaseModelImpl<Attachment> {
	public static final String TABLE_NAME = "Mail_Attachment";
	public static final Object[][] TABLE_COLUMNS = {
			{ "attachmentId", new Integer(Types.BIGINT) },
			{ "companyId", new Integer(Types.BIGINT) },
			{ "userId", new Integer(Types.BIGINT) },
			{ "accountId", new Integer(Types.BIGINT) },
			{ "folderId", new Integer(Types.BIGINT) },
			{ "messageId", new Integer(Types.BIGINT) },
			{ "contentPath", new Integer(Types.VARCHAR) },
			{ "fileName", new Integer(Types.VARCHAR) },
			{ "size_", new Integer(Types.BIGINT) }
		};
	public static final String TABLE_SQL_CREATE = "create table Mail_Attachment (attachmentId LONG not null primary key,companyId LONG,userId LONG,accountId LONG,folderId LONG,messageId LONG,contentPath VARCHAR(75) null,fileName VARCHAR(75) null,size_ LONG)";
	public static final String TABLE_SQL_DROP = "drop table Mail_Attachment";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.mail.model.Attachment"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.mail.model.Attachment"),
			true);
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.mail.model.Attachment"));

	public AttachmentModelImpl() {
	}

	public long getPrimaryKey() {
		return _attachmentId;
	}

	public void setPrimaryKey(long pk) {
		setAttachmentId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_attachmentId);
	}

	public long getAttachmentId() {
		return _attachmentId;
	}

	public void setAttachmentId(long attachmentId) {
		_attachmentId = attachmentId;
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

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public long getAccountId() {
		return _accountId;
	}

	public void setAccountId(long accountId) {
		_accountId = accountId;
	}

	public long getFolderId() {
		return _folderId;
	}

	public void setFolderId(long folderId) {
		_folderId = folderId;
	}

	public long getMessageId() {
		return _messageId;
	}

	public void setMessageId(long messageId) {
		_messageId = messageId;
	}

	public String getContentPath() {
		if (_contentPath == null) {
			return StringPool.BLANK;
		}
		else {
			return _contentPath;
		}
	}

	public void setContentPath(String contentPath) {
		_contentPath = contentPath;
	}

	public String getFileName() {
		if (_fileName == null) {
			return StringPool.BLANK;
		}
		else {
			return _fileName;
		}
	}

	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	public long getSize() {
		return _size;
	}

	public void setSize(long size) {
		_size = size;
	}

	public Attachment toEscapedModel() {
		if (isEscapedModel()) {
			return (Attachment)this;
		}
		else {
			return (Attachment)Proxy.newProxyInstance(Attachment.class.getClassLoader(),
				new Class[] { Attachment.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					Attachment.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		AttachmentImpl clone = new AttachmentImpl();

		clone.setAttachmentId(getAttachmentId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setAccountId(getAccountId());
		clone.setFolderId(getFolderId());
		clone.setMessageId(getMessageId());
		clone.setContentPath(getContentPath());
		clone.setFileName(getFileName());
		clone.setSize(getSize());

		return clone;
	}

	public int compareTo(Attachment attachment) {
		long pk = attachment.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		Attachment attachment = null;

		try {
			attachment = (Attachment)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = attachment.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{attachmentId=");
		sb.append(getAttachmentId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", accountId=");
		sb.append(getAccountId());
		sb.append(", folderId=");
		sb.append(getFolderId());
		sb.append(", messageId=");
		sb.append(getMessageId());
		sb.append(", contentPath=");
		sb.append(getContentPath());
		sb.append(", fileName=");
		sb.append(getFileName());
		sb.append(", size=");
		sb.append(getSize());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.mail.model.Attachment");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>attachmentId</column-name><column-value><![CDATA[");
		sb.append(getAttachmentId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accountId</column-name><column-value><![CDATA[");
		sb.append(getAccountId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>folderId</column-name><column-value><![CDATA[");
		sb.append(getFolderId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>messageId</column-name><column-value><![CDATA[");
		sb.append(getMessageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>contentPath</column-name><column-value><![CDATA[");
		sb.append(getContentPath());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileName</column-name><column-value><![CDATA[");
		sb.append(getFileName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>size</column-name><column-value><![CDATA[");
		sb.append(getSize());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _attachmentId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private long _accountId;
	private long _folderId;
	private long _messageId;
	private String _contentPath;
	private String _fileName;
	private long _size;
	private transient ExpandoBridge _expandoBridge;
}