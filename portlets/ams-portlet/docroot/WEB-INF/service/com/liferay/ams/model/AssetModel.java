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

package com.liferay.ams.model;

import com.liferay.portal.kernel.annotation.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the AMS_Asset table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Asset
 * @see       com.liferay.ams.model.impl.AssetImpl
 * @see       com.liferay.ams.model.impl.AssetModelImpl
 * @generated
 */
public interface AssetModel extends BaseModel<Asset> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getAssetId();

	public void setAssetId(long assetId);

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

	public long getDefinitionId();

	public void setDefinitionId(long definitionId);

	@AutoEscape
	public String getSerialNumber();

	public void setSerialNumber(String serialNumber);

	public Date getInactiveDate();

	public void setInactiveDate(Date inactiveDate);

	public boolean getActive();

	public boolean isActive();

	public void setActive(boolean active);

	public Asset toEscapedModel();

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

	public int compareTo(Asset asset);

	public int hashCode();

	public String toString();

	public String toXmlString();
}