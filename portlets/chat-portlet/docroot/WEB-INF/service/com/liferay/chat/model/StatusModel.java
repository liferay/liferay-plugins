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
 * This interface is a model that represents the Chat_Status table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Status
 * @see       com.liferay.chat.model.impl.StatusImpl
 * @see       com.liferay.chat.model.impl.StatusModelImpl
 * @generated
 */
public interface StatusModel extends BaseModel<Status> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getStatusId();

	public void setStatusId(long statusId);

	public long getUserId();

	public void setUserId(long userId);

	public String getUserUuid() throws SystemException;

	public void setUserUuid(String userUuid);

	public long getModifiedDate();

	public void setModifiedDate(long modifiedDate);

	public boolean getOnline();

	public boolean isOnline();

	public void setOnline(boolean online);

	public boolean getAwake();

	public boolean isAwake();

	public void setAwake(boolean awake);

	@AutoEscape
	public String getActivePanelId();

	public void setActivePanelId(String activePanelId);

	@AutoEscape
	public String getMessage();

	public void setMessage(String message);

	public boolean getPlaySound();

	public boolean isPlaySound();

	public void setPlaySound(boolean playSound);

	public Status toEscapedModel();

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public void setEscapedModel(boolean escapedModel);

	public Serializable getPrimaryKeyObj();

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(Status status);

	public int hashCode();

	public String toString();

	public String toXmlString();
}