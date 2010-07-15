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

package com.liferay.portal.workflow.kaleo.model;

import com.liferay.portal.kernel.annotation.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * This interface is a model that represents the Kaleo_KaleoNotificationRecipient table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoNotificationRecipient
 * @see       com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientImpl
 * @see       com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientModelImpl
 * @generated
 */
public interface KaleoNotificationRecipientModel extends BaseModel<KaleoNotificationRecipient> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getKaleoNotificationRecipientId();

	public void setKaleoNotificationRecipientId(
		long kaleoNotificationRecipientId);

	public long getGroupId();

	public void setGroupId(long groupId);

	public long getCompanyId();

	public void setCompanyId(long companyId);

	public long getUserId();

	public void setUserId(long userId);

	public String getUserUuid() throws SystemException;

	public void setUserUuid(String userUuid);

	@AutoEscape
	public String getUserName();

	public void setUserName(String userName);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public Date getModifiedDate();

	public void setModifiedDate(Date modifiedDate);

	public long getKaleoDefinitionId();

	public void setKaleoDefinitionId(long kaleoDefinitionId);

	public long getKaleoNotificationId();

	public void setKaleoNotificationId(long kaleoNotificationId);

	@AutoEscape
	public String getRecipientClassName();

	public void setRecipientClassName(String recipientClassName);

	public long getRecipientClassPK();

	public void setRecipientClassPK(long recipientClassPK);

	public int getRecipientRoleType();

	public void setRecipientRoleType(int recipientRoleType);

	@AutoEscape
	public String getAddress();

	public void setAddress(String address);

	public KaleoNotificationRecipient toEscapedModel();

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

	public int compareTo(KaleoNotificationRecipient kaleoNotificationRecipient);

	public int hashCode();

	public String toString();

	public String toXmlString();
}