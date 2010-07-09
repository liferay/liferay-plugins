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

package com.liferay.chat.model;

import com.liferay.portal.kernel.annotation.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the Chat_Entry table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Entry
 * @see       com.liferay.chat.model.impl.EntryImpl
 * @see       com.liferay.chat.model.impl.EntryModelImpl
 * @generated
 */
public interface EntryModel extends BaseModel<Entry> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getEntryId();

	public void setEntryId(long entryId);

	public long getCreateDate();

	public void setCreateDate(long createDate);

	public long getFromUserId();

	public void setFromUserId(long fromUserId);

	public String getFromUserUuid() throws SystemException;

	public void setFromUserUuid(String fromUserUuid);

	public long getToUserId();

	public void setToUserId(long toUserId);

	public String getToUserUuid() throws SystemException;

	public void setToUserUuid(String toUserUuid);

	@AutoEscape
	public String getContent();

	public void setContent(String content);

	public Entry toEscapedModel();

	public boolean isNew();

	public boolean setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public void setEscapedModel(boolean escapedModel);

	public Serializable getPrimaryKeyObj();

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(Entry entry);

	public int hashCode();

	public String toString();

	public String toXmlString();
}