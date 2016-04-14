/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SVNRepository}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SVNRepository
 * @generated
 */
@ProviderType
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

	@Override
	public boolean isCachedModel() {
		return _svnRepository.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _svnRepository.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _svnRepository.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _svnRepository.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<com.liferay.socialcoding.model.SVNRepository> toCacheModel() {
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
	public int compareTo(
		com.liferay.socialcoding.model.SVNRepository svnRepository) {
		return _svnRepository.compareTo(svnRepository);
	}

	@Override
	public int hashCode() {
		return _svnRepository.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _svnRepository.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SVNRepositoryWrapper((SVNRepository)_svnRepository.clone());
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
	* Returns the url of this s v n repository.
	*
	* @return the url of this s v n repository
	*/
	@Override
	public java.lang.String getUrl() {
		return _svnRepository.getUrl();
	}

	@Override
	public java.lang.String toString() {
		return _svnRepository.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _svnRepository.toXmlString();
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
	* Returns the revision number of this s v n repository.
	*
	* @return the revision number of this s v n repository
	*/
	@Override
	public long getRevisionNumber() {
		return _svnRepository.getRevisionNumber();
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

	@Override
	public void persist() {
		_svnRepository.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_svnRepository.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_svnRepository.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_svnRepository.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_svnRepository.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_svnRepository.setNew(n);
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

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_svnRepository.setPrimaryKeyObj(primaryKeyObj);
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
	* Sets the url of this s v n repository.
	*
	* @param url the url of this s v n repository
	*/
	@Override
	public void setUrl(java.lang.String url) {
		_svnRepository.setUrl(url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SVNRepositoryWrapper)) {
			return false;
		}

		SVNRepositoryWrapper svnRepositoryWrapper = (SVNRepositoryWrapper)obj;

		if (Validator.equals(_svnRepository, svnRepositoryWrapper._svnRepository)) {
			return true;
		}

		return false;
	}

	@Override
	public SVNRepository getWrappedModel() {
		return _svnRepository;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _svnRepository.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _svnRepository.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_svnRepository.resetOriginalValues();
	}

	private final SVNRepository _svnRepository;
}