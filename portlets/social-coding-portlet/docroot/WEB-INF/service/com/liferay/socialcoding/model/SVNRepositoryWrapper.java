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

import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SVNRepository}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SVNRepository
 * @generated
 */
public class SVNRepositoryWrapper implements SVNRepository,
	ModelWrapper<SVNRepository> {
	public SVNRepositoryWrapper(SVNRepository svnRepository) {
		_svnRepository = svnRepository;
	}

	@Override
	public Class<?> getModelClass() {
		return SVNRepository.class;
	}

	@Override
	public String getModelClassName() {
		return SVNRepository.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("svnRepositoryId", getSvnRepositoryId());
		attributes.put("url", getUrl());
		attributes.put("revisionNumber", getRevisionNumber());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long svnRepositoryId = (Long)attributes.get("svnRepositoryId");

		if (svnRepositoryId != null) {
			setSvnRepositoryId(svnRepositoryId);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		Long revisionNumber = (Long)attributes.get("revisionNumber");

		if (revisionNumber != null) {
			setRevisionNumber(revisionNumber);
		}
	}

	/**
	* Returns the primary key of this s v n repository.
	*
	* @return the primary key of this s v n repository
	*/
	@Override
	public long getPrimaryKey() {
		return _svnRepository.getPrimaryKey();
	}

	/**
	* Sets the primary key of this s v n repository.
	*
	* @param primaryKey the primary key of this s v n repository
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_svnRepository.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the svn repository ID of this s v n repository.
	*
	* @return the svn repository ID of this s v n repository
	*/
	@Override
	public long getSvnRepositoryId() {
		return _svnRepository.getSvnRepositoryId();
	}

	/**
	* Sets the svn repository ID of this s v n repository.
	*
	* @param svnRepositoryId the svn repository ID of this s v n repository
	*/
	@Override
	public void setSvnRepositoryId(long svnRepositoryId) {
		_svnRepository.setSvnRepositoryId(svnRepositoryId);
	}

	/**
	* Returns the url of this s v n repository.
	*
	* @return the url of this s v n repository
	*/
	@Override
	public java.lang.String getUrl() {
		return _svnRepository.getUrl();
	}

	/**
	* Sets the url of this s v n repository.
	*
	* @param url the url of this s v n repository
	*/
	@Override
	public void setUrl(java.lang.String url) {
		_svnRepository.setUrl(url);
	}

	/**
	* Returns the revision number of this s v n repository.
	*
	* @return the revision number of this s v n repository
	*/
	@Override
	public long getRevisionNumber() {
		return _svnRepository.getRevisionNumber();
	}

	/**
	* Sets the revision number of this s v n repository.
	*
	* @param revisionNumber the revision number of this s v n repository
	*/
	@Override
	public void setRevisionNumber(long revisionNumber) {
		_svnRepository.setRevisionNumber(revisionNumber);
	}

	@Override
	public boolean isNew() {
		return _svnRepository.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_svnRepository.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _svnRepository.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_svnRepository.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _svnRepository.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _svnRepository.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_svnRepository.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _svnRepository.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_svnRepository.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_svnRepository.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_svnRepository.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SVNRepositoryWrapper((SVNRepository)_svnRepository.clone());
	}

	@Override
	public int compareTo(
		com.liferay.socialcoding.model.SVNRepository svnRepository) {
		return _svnRepository.compareTo(svnRepository);
	}

	@Override
	public int hashCode() {
		return _svnRepository.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.socialcoding.model.SVNRepository> toCacheModel() {
		return _svnRepository.toCacheModel();
	}

	@Override
	public com.liferay.socialcoding.model.SVNRepository toEscapedModel() {
		return new SVNRepositoryWrapper(_svnRepository.toEscapedModel());
	}

	@Override
	public com.liferay.socialcoding.model.SVNRepository toUnescapedModel() {
		return new SVNRepositoryWrapper(_svnRepository.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _svnRepository.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _svnRepository.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_svnRepository.persist();
	}

	@Override
	public java.lang.String getName() {
		return _svnRepository.getName();
	}

	@Override
	public java.lang.String getShortURL() {
		return _svnRepository.getShortURL();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public SVNRepository getWrappedSVNRepository() {
		return _svnRepository;
	}

	@Override
	public SVNRepository getWrappedModel() {
		return _svnRepository;
	}

	@Override
	public void resetOriginalValues() {
		_svnRepository.resetOriginalValues();
	}

	private SVNRepository _svnRepository;
}