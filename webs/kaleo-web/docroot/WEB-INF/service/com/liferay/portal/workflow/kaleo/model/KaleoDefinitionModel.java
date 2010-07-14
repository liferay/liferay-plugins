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
import java.util.Locale;
import java.util.Map;

/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the Kaleo_KaleoDefinition table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoDefinition
 * @see       com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionImpl
 * @see       com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl
 * @generated
 */
public interface KaleoDefinitionModel extends BaseModel<KaleoDefinition> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getKaleoDefinitionId();

	public void setKaleoDefinitionId(long kaleoDefinitionId);

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

	@AutoEscape
	public String getName();

	public void setName(String name);

	public String getTitle();

	public String getTitle(Locale locale);

	public String getTitle(Locale locale, boolean useDefault);

	public String getTitle(String languageId);

	public String getTitle(String languageId, boolean useDefault);

	public Map<Locale, String> getTitleMap();

	public void setTitle(String title);

	public void setTitle(Locale locale, String title);

	public void setTitleMap(Map<Locale, String> titleMap);

	@AutoEscape
	public String getDescription();

	public void setDescription(String description);

	public int getVersion();

	public void setVersion(int version);

	public boolean getActive();

	public boolean isActive();

	public void setActive(boolean active);

	public long getStartKaleoNodeId();

	public void setStartKaleoNodeId(long startKaleoNodeId);

	public KaleoDefinition toEscapedModel();

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

	public int compareTo(KaleoDefinition kaleoDefinition);

	public int hashCode();

	public String toString();

	public String toXmlString();
}