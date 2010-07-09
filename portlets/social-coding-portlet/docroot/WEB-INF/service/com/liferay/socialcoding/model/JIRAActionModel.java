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

package com.liferay.socialcoding.model;

import com.liferay.portal.kernel.annotation.AutoEscape;
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
 * This interface is a model that represents the jiraaction table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JIRAAction
 * @see       com.liferay.socialcoding.model.impl.JIRAActionImpl
 * @see       com.liferay.socialcoding.model.impl.JIRAActionModelImpl
 * @generated
 */
public interface JIRAActionModel extends BaseModel<JIRAAction> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getJiraActionId();

	public void setJiraActionId(long jiraActionId);

	@AutoEscape
	public String getJiraUserId();

	public void setJiraUserId(String jiraUserId);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public Date getModifiedDate();

	public void setModifiedDate(Date modifiedDate);

	public long getJiraIssueId();

	public void setJiraIssueId(long jiraIssueId);

	@AutoEscape
	public String getType();

	public void setType(String type);

	@AutoEscape
	public String getBody();

	public void setBody(String body);

	@AutoEscape
	public String getJiraGroupName();

	public void setJiraGroupName(String jiraGroupName);

	public JIRAAction toEscapedModel();

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

	public int compareTo(JIRAAction jiraAction);

	public int hashCode();

	public String toString();

	public String toXmlString();
}