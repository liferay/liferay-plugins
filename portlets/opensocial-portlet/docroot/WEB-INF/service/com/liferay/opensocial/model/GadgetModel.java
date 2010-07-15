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

package com.liferay.opensocial.model;

import com.liferay.portal.kernel.annotation.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * This interface is a model that represents the OpenSocial_Gadget table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Gadget
 * @see       com.liferay.opensocial.model.impl.GadgetImpl
 * @see       com.liferay.opensocial.model.impl.GadgetModelImpl
 * @generated
 */
public interface GadgetModel extends BaseModel<Gadget> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getGadgetId();

	public void setGadgetId(long gadgetId);

	public long getCompanyId();

	public void setCompanyId(long companyId);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public Date getModifiedDate();

	public void setModifiedDate(Date modifiedDate);

	@AutoEscape
	public String getName();

	public void setName(String name);

	@AutoEscape
	public String getUrl();

	public void setUrl(String url);

	public Gadget toEscapedModel();

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

	public int compareTo(Gadget gadget);

	public int hashCode();

	public String toString();

	public String toXmlString();
}