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

	/**
	* Gets the primary key of this s v n revision.
	*
	* @return the primary key of this s v n revision
	*/
	public long getPrimaryKey() {
		return _svnRevision.getPrimaryKey();
	}

	/**
	* Sets the primary key of this s v n revision
	*
	* @param pk the primary key of this s v n revision
	*/
	public void setPrimaryKey(long pk) {
		_svnRevision.setPrimaryKey(pk);
	}

	/**
	* Gets the svn revision id of this s v n revision.
	*
	* @return the svn revision id of this s v n revision
	*/
	public long getSvnRevisionId() {
		return _svnRevision.getSvnRevisionId();
	}

	/**
	* Sets the svn revision id of this s v n revision.
	*
	* @param svnRevisionId the svn revision id of this s v n revision
	*/
	public void setSvnRevisionId(long svnRevisionId) {
		_svnRevision.setSvnRevisionId(svnRevisionId);
	}

	/**
	* Gets the svn user id of this s v n revision.
	*
	* @return the svn user id of this s v n revision
	*/
	public java.lang.String getSvnUserId() {
		return _svnRevision.getSvnUserId();
	}

	/**
	* Sets the svn user id of this s v n revision.
	*
	* @param svnUserId the svn user id of this s v n revision
	*/
	public void setSvnUserId(java.lang.String svnUserId) {
		_svnRevision.setSvnUserId(svnUserId);
	}

	/**
	* Gets the create date of this s v n revision.
	*
	* @return the create date of this s v n revision
	*/
	public java.util.Date getCreateDate() {
		return _svnRevision.getCreateDate();
	}

	/**
	* Sets the create date of this s v n revision.
	*
	* @param createDate the create date of this s v n revision
	*/
	public void setCreateDate(java.util.Date createDate) {
		_svnRevision.setCreateDate(createDate);
	}

	/**
	* Gets the svn repository id of this s v n revision.
	*
	* @return the svn repository id of this s v n revision
	*/
	public long getSvnRepositoryId() {
		return _svnRevision.getSvnRepositoryId();
	}

	/**
	* Sets the svn repository id of this s v n revision.
	*
	* @param svnRepositoryId the svn repository id of this s v n revision
	*/
	public void setSvnRepositoryId(long svnRepositoryId) {
		_svnRevision.setSvnRepositoryId(svnRepositoryId);
	}

	/**
	* Gets the revision number of this s v n revision.
	*
	* @return the revision number of this s v n revision
	*/
	public long getRevisionNumber() {
		return _svnRevision.getRevisionNumber();
	}

	/**
	* Sets the revision number of this s v n revision.
	*
	* @param revisionNumber the revision number of this s v n revision
	*/
	public void setRevisionNumber(long revisionNumber) {
		_svnRevision.setRevisionNumber(revisionNumber);
	}

	/**
	* Gets the comments of this s v n revision.
	*
	* @return the comments of this s v n revision
	*/
	public java.lang.String getComments() {
		return _svnRevision.getComments();
	}

	/**
	* Sets the comments of this s v n revision.
	*
	* @param comments the comments of this s v n revision
	*/
	public void setComments(java.lang.String comments) {
		_svnRevision.setComments(comments);
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
		return new SVNRevisionWrapper((SVNRevision)_svnRevision.clone());
	}

	public int compareTo(com.liferay.socialcoding.model.SVNRevision svnRevision) {
		return _svnRevision.compareTo(svnRevision);
	}

	public int hashCode() {
		return _svnRevision.hashCode();
	}

	public com.liferay.socialcoding.model.SVNRevision toEscapedModel() {
		return new SVNRevisionWrapper(_svnRevision.toEscapedModel());
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