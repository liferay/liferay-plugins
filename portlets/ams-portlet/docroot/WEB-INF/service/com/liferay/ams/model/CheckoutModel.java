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
 * This interface is a model that represents the AMS_Checkout table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Checkout
 * @see       com.liferay.ams.model.impl.CheckoutImpl
 * @see       com.liferay.ams.model.impl.CheckoutModelImpl
 * @generated
 */
public interface CheckoutModel extends BaseModel<Checkout> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getCheckoutId();

	public void setCheckoutId(long checkoutId);

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

	public long getAssetId();

	public void setAssetId(long assetId);

	public Date getCheckOutDate();

	public void setCheckOutDate(Date checkOutDate);

	public Date getExpectedCheckInDate();

	public void setExpectedCheckInDate(Date expectedCheckInDate);

	public Date getActualCheckInDate();

	public void setActualCheckInDate(Date actualCheckInDate);

	public Checkout toEscapedModel();

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

	public int compareTo(Checkout checkout);

	public int hashCode();

	public String toString();

	public String toXmlString();
}