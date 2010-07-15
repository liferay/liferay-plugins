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
 * This interface is a model that represents the SC_SVNRevision table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SVNRevision
 * @see       com.liferay.socialcoding.model.impl.SVNRevisionImpl
 * @see       com.liferay.socialcoding.model.impl.SVNRevisionModelImpl
 * @generated
 */
public interface SVNRevisionModel extends BaseModel<SVNRevision> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getSvnRevisionId();

	public void setSvnRevisionId(long svnRevisionId);

	@AutoEscape
	public String getSvnUserId();

	public void setSvnUserId(String svnUserId);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public long getSvnRepositoryId();

	public void setSvnRepositoryId(long svnRepositoryId);

	public long getRevisionNumber();

	public void setRevisionNumber(long revisionNumber);

	@AutoEscape
	public String getComments();

	public void setComments(String comments);

	public SVNRevision toEscapedModel();

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

	public int compareTo(SVNRevision svnRevision);

	public int hashCode();

	public String toString();

	public String toXmlString();
}