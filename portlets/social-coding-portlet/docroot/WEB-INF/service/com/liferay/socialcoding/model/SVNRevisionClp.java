/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.socialcoding.service.SVNRevisionLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class SVNRevisionClp extends BaseModelImpl<SVNRevision>
	implements SVNRevision {
	public SVNRevisionClp() {
	}

	public Class<?> getModelClass() {
		return SVNRevision.class;
	}

	public String getModelClassName() {
		return SVNRevision.class.getName();
	}

	public long getPrimaryKey() {
		return _svnRevisionId;
	}

	public void setPrimaryKey(long primaryKey) {
		setSvnRevisionId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_svnRevisionId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getSvnRevisionId() {
		return _svnRevisionId;
	}

	public void setSvnRevisionId(long svnRevisionId) {
		_svnRevisionId = svnRevisionId;
	}

	public String getSvnUserId() {
		return _svnUserId;
	}

	public void setSvnUserId(String svnUserId) {
		_svnUserId = svnUserId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public long getSvnRepositoryId() {
		return _svnRepositoryId;
	}

	public void setSvnRepositoryId(long svnRepositoryId) {
		_svnRepositoryId = svnRepositoryId;
	}

	public long getRevisionNumber() {
		return _revisionNumber;
	}

	public void setRevisionNumber(long revisionNumber) {
		_revisionNumber = revisionNumber;
	}

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	public com.liferay.socialcoding.model.SVNRepository getSVNRepository() {
		throw new UnsupportedOperationException();
	}

	public java.lang.String getWebRevisionNumberURL() {
		throw new UnsupportedOperationException();
	}

	public java.lang.Object[] getJIRAIssueAndComments() {
		throw new UnsupportedOperationException();
	}

	public void persist() throws SystemException {
		SVNRevisionLocalServiceUtil.updateSVNRevision(this);
	}

	@Override
	public SVNRevision toEscapedModel() {
		return (SVNRevision)Proxy.newProxyInstance(SVNRevision.class.getClassLoader(),
			new Class[] { SVNRevision.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SVNRevisionClp clone = new SVNRevisionClp();

		clone.setSvnRevisionId(getSvnRevisionId());
		clone.setSvnUserId(getSvnUserId());
		clone.setCreateDate(getCreateDate());
		clone.setSvnRepositoryId(getSvnRepositoryId());
		clone.setRevisionNumber(getRevisionNumber());
		clone.setComments(getComments());

		return clone;
	}

	public int compareTo(SVNRevision svnRevision) {
		int value = 0;

		if (getRevisionNumber() < svnRevision.getRevisionNumber()) {
			value = -1;
		}
		else if (getRevisionNumber() > svnRevision.getRevisionNumber()) {
			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		SVNRevisionClp svnRevision = null;

		try {
			svnRevision = (SVNRevisionClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = svnRevision.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{svnRevisionId=");
		sb.append(getSvnRevisionId());
		sb.append(", svnUserId=");
		sb.append(getSvnUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", svnRepositoryId=");
		sb.append(getSvnRepositoryId());
		sb.append(", revisionNumber=");
		sb.append(getRevisionNumber());
		sb.append(", comments=");
		sb.append(getComments());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.liferay.socialcoding.model.SVNRevision");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>svnRevisionId</column-name><column-value><![CDATA[");
		sb.append(getSvnRevisionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>svnUserId</column-name><column-value><![CDATA[");
		sb.append(getSvnUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>svnRepositoryId</column-name><column-value><![CDATA[");
		sb.append(getSvnRepositoryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>revisionNumber</column-name><column-value><![CDATA[");
		sb.append(getRevisionNumber());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>comments</column-name><column-value><![CDATA[");
		sb.append(getComments());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _svnRevisionId;
	private String _svnUserId;
	private Date _createDate;
	private long _svnRepositoryId;
	private long _revisionNumber;
	private String _comments;
}