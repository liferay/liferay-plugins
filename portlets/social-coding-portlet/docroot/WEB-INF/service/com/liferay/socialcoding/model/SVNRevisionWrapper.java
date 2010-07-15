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


/**
 * <p>
 * This class is a wrapper for {@link SVNRevision}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SVNRevision
 * @generated
 */
public class SVNRevisionWrapper implements SVNRevision {
	public SVNRevisionWrapper(SVNRevision svnRevision) {
		_svnRevision = svnRevision;
	}

	public long getPrimaryKey() {
		return _svnRevision.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_svnRevision.setPrimaryKey(pk);
	}

	public long getSvnRevisionId() {
		return _svnRevision.getSvnRevisionId();
	}

	public void setSvnRevisionId(long svnRevisionId) {
		_svnRevision.setSvnRevisionId(svnRevisionId);
	}

	public java.lang.String getSvnUserId() {
		return _svnRevision.getSvnUserId();
	}

	public void setSvnUserId(java.lang.String svnUserId) {
		_svnRevision.setSvnUserId(svnUserId);
	}

	public java.util.Date getCreateDate() {
		return _svnRevision.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_svnRevision.setCreateDate(createDate);
	}

	public long getSvnRepositoryId() {
		return _svnRevision.getSvnRepositoryId();
	}

	public void setSvnRepositoryId(long svnRepositoryId) {
		_svnRevision.setSvnRepositoryId(svnRepositoryId);
	}

	public long getRevisionNumber() {
		return _svnRevision.getRevisionNumber();
	}

	public void setRevisionNumber(long revisionNumber) {
		_svnRevision.setRevisionNumber(revisionNumber);
	}

	public java.lang.String getComments() {
		return _svnRevision.getComments();
	}

	public void setComments(java.lang.String comments) {
		_svnRevision.setComments(comments);
	}

	public com.liferay.socialcoding.model.SVNRevision toEscapedModel() {
		return _svnRevision.toEscapedModel();
	}

	public boolean isNew() {
		return _svnRevision.isNew();
	}

	public void setNew(boolean n) {
		_svnRevision.setNew(n);
	}

	public boolean isCachedModel() {
		return _svnRevision.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_svnRevision.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _svnRevision.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_svnRevision.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _svnRevision.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _svnRevision.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_svnRevision.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _svnRevision.clone();
	}

	public int compareTo(com.liferay.socialcoding.model.SVNRevision svnRevision) {
		return _svnRevision.compareTo(svnRevision);
	}

	public int hashCode() {
		return _svnRevision.hashCode();
	}

	public java.lang.String toString() {
		return _svnRevision.toString();
	}

	public java.lang.String toXmlString() {
		return _svnRevision.toXmlString();
	}

	public com.liferay.socialcoding.model.SVNRepository getSVNRepository() {
		return _svnRevision.getSVNRepository();
	}

	public java.lang.String getWebRevisionNumberURL() {
		return _svnRevision.getWebRevisionNumberURL();
	}

	public java.lang.Object[] getJIRAIssueAndComments() {
		return _svnRevision.getJIRAIssueAndComments();
	}

	public SVNRevision getWrappedSVNRevision() {
		return _svnRevision;
	}

	private SVNRevision _svnRevision;
}