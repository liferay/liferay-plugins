/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SVNRevision}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SVNRevision
 * @generated
 */
public class SVNRevisionWrapper implements SVNRevision,
	ModelWrapper<SVNRevision> {
	public SVNRevisionWrapper(SVNRevision svnRevision) {
		_svnRevision = svnRevision;
	}

	@Override
	public Class<?> getModelClass() {
		return SVNRevision.class;
	}

	@Override
	public String getModelClassName() {
		return SVNRevision.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("svnRevisionId", getSvnRevisionId());
		attributes.put("svnUserId", getSvnUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("svnRepositoryId", getSvnRepositoryId());
		attributes.put("revisionNumber", getRevisionNumber());
		attributes.put("comments", getComments());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long svnRevisionId = (Long)attributes.get("svnRevisionId");

		if (svnRevisionId != null) {
			setSvnRevisionId(svnRevisionId);
		}

		String svnUserId = (String)attributes.get("svnUserId");

		if (svnUserId != null) {
			setSvnUserId(svnUserId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long svnRepositoryId = (Long)attributes.get("svnRepositoryId");

		if (svnRepositoryId != null) {
			setSvnRepositoryId(svnRepositoryId);
		}

		Long revisionNumber = (Long)attributes.get("revisionNumber");

		if (revisionNumber != null) {
			setRevisionNumber(revisionNumber);
		}

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}
	}

	/**
	* Returns the primary key of this s v n revision.
	*
	* @return the primary key of this s v n revision
	*/
	@Override
	public long getPrimaryKey() {
		return _svnRevision.getPrimaryKey();
	}

	/**
	* Sets the primary key of this s v n revision.
	*
	* @param primaryKey the primary key of this s v n revision
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_svnRevision.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the svn revision ID of this s v n revision.
	*
	* @return the svn revision ID of this s v n revision
	*/
	@Override
	public long getSvnRevisionId() {
		return _svnRevision.getSvnRevisionId();
	}

	/**
	* Sets the svn revision ID of this s v n revision.
	*
	* @param svnRevisionId the svn revision ID of this s v n revision
	*/
	@Override
	public void setSvnRevisionId(long svnRevisionId) {
		_svnRevision.setSvnRevisionId(svnRevisionId);
	}

	/**
	* Returns the svn user ID of this s v n revision.
	*
	* @return the svn user ID of this s v n revision
	*/
	@Override
	public java.lang.String getSvnUserId() {
		return _svnRevision.getSvnUserId();
	}

	/**
	* Sets the svn user ID of this s v n revision.
	*
	* @param svnUserId the svn user ID of this s v n revision
	*/
	@Override
	public void setSvnUserId(java.lang.String svnUserId) {
		_svnRevision.setSvnUserId(svnUserId);
	}

	/**
	* Returns the create date of this s v n revision.
	*
	* @return the create date of this s v n revision
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _svnRevision.getCreateDate();
	}

	/**
	* Sets the create date of this s v n revision.
	*
	* @param createDate the create date of this s v n revision
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_svnRevision.setCreateDate(createDate);
	}

	/**
	* Returns the svn repository ID of this s v n revision.
	*
	* @return the svn repository ID of this s v n revision
	*/
	@Override
	public long getSvnRepositoryId() {
		return _svnRevision.getSvnRepositoryId();
	}

	/**
	* Sets the svn repository ID of this s v n revision.
	*
	* @param svnRepositoryId the svn repository ID of this s v n revision
	*/
	@Override
	public void setSvnRepositoryId(long svnRepositoryId) {
		_svnRevision.setSvnRepositoryId(svnRepositoryId);
	}

	/**
	* Returns the revision number of this s v n revision.
	*
	* @return the revision number of this s v n revision
	*/
	@Override
	public long getRevisionNumber() {
		return _svnRevision.getRevisionNumber();
	}

	/**
	* Sets the revision number of this s v n revision.
	*
	* @param revisionNumber the revision number of this s v n revision
	*/
	@Override
	public void setRevisionNumber(long revisionNumber) {
		_svnRevision.setRevisionNumber(revisionNumber);
	}

	/**
	* Returns the comments of this s v n revision.
	*
	* @return the comments of this s v n revision
	*/
	@Override
	public java.lang.String getComments() {
		return _svnRevision.getComments();
	}

	/**
	* Sets the comments of this s v n revision.
	*
	* @param comments the comments of this s v n revision
	*/
	@Override
	public void setComments(java.lang.String comments) {
		_svnRevision.setComments(comments);
	}

	@Override
	public boolean isNew() {
		return _svnRevision.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_svnRevision.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _svnRevision.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_svnRevision.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _svnRevision.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _svnRevision.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_svnRevision.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _svnRevision.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_svnRevision.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_svnRevision.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_svnRevision.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SVNRevisionWrapper((SVNRevision)_svnRevision.clone());
	}

	@Override
	public int compareTo(com.liferay.socialcoding.model.SVNRevision svnRevision) {
		return _svnRevision.compareTo(svnRevision);
	}

	@Override
	public int hashCode() {
		return _svnRevision.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.socialcoding.model.SVNRevision> toCacheModel() {
		return _svnRevision.toCacheModel();
	}

	@Override
	public com.liferay.socialcoding.model.SVNRevision toEscapedModel() {
		return new SVNRevisionWrapper(_svnRevision.toEscapedModel());
	}

	@Override
	public com.liferay.socialcoding.model.SVNRevision toUnescapedModel() {
		return new SVNRevisionWrapper(_svnRevision.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _svnRevision.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _svnRevision.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_svnRevision.persist();
	}

	@Override
	public java.lang.Object[] getJIRAIssueAndComments() {
		return _svnRevision.getJIRAIssueAndComments();
	}

	@Override
	public com.liferay.socialcoding.model.SVNRepository getSVNRepository() {
		return _svnRevision.getSVNRepository();
	}

	@Override
	public java.lang.String getWebRevisionNumberURL() {
		return _svnRevision.getWebRevisionNumberURL();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SVNRevisionWrapper)) {
			return false;
		}

		SVNRevisionWrapper svnRevisionWrapper = (SVNRevisionWrapper)obj;

		if (Validator.equals(_svnRevision, svnRevisionWrapper._svnRevision)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public SVNRevision getWrappedSVNRevision() {
		return _svnRevision;
	}

	@Override
	public SVNRevision getWrappedModel() {
		return _svnRevision;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _svnRevision.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _svnRevision.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_svnRevision.resetOriginalValues();
	}

	private SVNRevision _svnRevision;
}