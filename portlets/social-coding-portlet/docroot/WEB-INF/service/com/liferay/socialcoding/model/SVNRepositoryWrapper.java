/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
 * This class is a wrapper for {@link SVNRepository}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SVNRepository
 * @generated
 */
public class SVNRepositoryWrapper implements SVNRepository {
	public SVNRepositoryWrapper(SVNRepository svnRepository) {
		_svnRepository = svnRepository;
	}

	public Class<?> getModelClass() {
		return SVNRepository.class;
	}

	public String getModelClassName() {
		return SVNRepository.class.getName();
	}

	/**
	* Returns the primary key of this s v n repository.
	*
	* @return the primary key of this s v n repository
	*/
	public long getPrimaryKey() {
		return _svnRepository.getPrimaryKey();
	}

	/**
	* Sets the primary key of this s v n repository.
	*
	* @param primaryKey the primary key of this s v n repository
	*/
	public void setPrimaryKey(long primaryKey) {
		_svnRepository.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the svn repository ID of this s v n repository.
	*
	* @return the svn repository ID of this s v n repository
	*/
	public long getSvnRepositoryId() {
		return _svnRepository.getSvnRepositoryId();
	}

	/**
	* Sets the svn repository ID of this s v n repository.
	*
	* @param svnRepositoryId the svn repository ID of this s v n repository
	*/
	public void setSvnRepositoryId(long svnRepositoryId) {
		_svnRepository.setSvnRepositoryId(svnRepositoryId);
	}

	/**
	* Returns the url of this s v n repository.
	*
	* @return the url of this s v n repository
	*/
	public java.lang.String getUrl() {
		return _svnRepository.getUrl();
	}

	/**
	* Sets the url of this s v n repository.
	*
	* @param url the url of this s v n repository
	*/
	public void setUrl(java.lang.String url) {
		_svnRepository.setUrl(url);
	}

	/**
	* Returns the revision number of this s v n repository.
	*
	* @return the revision number of this s v n repository
	*/
	public long getRevisionNumber() {
		return _svnRepository.getRevisionNumber();
	}

	/**
	* Sets the revision number of this s v n repository.
	*
	* @param revisionNumber the revision number of this s v n repository
	*/
	public void setRevisionNumber(long revisionNumber) {
		_svnRepository.setRevisionNumber(revisionNumber);
	}

	public boolean isNew() {
		return _svnRepository.isNew();
	}

	public void setNew(boolean n) {
		_svnRepository.setNew(n);
	}

	public boolean isCachedModel() {
		return _svnRepository.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_svnRepository.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _svnRepository.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_svnRepository.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _svnRepository.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_svnRepository.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _svnRepository.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_svnRepository.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SVNRepositoryWrapper((SVNRepository)_svnRepository.clone());
	}

	public int compareTo(
		com.liferay.socialcoding.model.SVNRepository svnRepository) {
		return _svnRepository.compareTo(svnRepository);
	}

	@Override
	public int hashCode() {
		return _svnRepository.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.socialcoding.model.SVNRepository> toCacheModel() {
		return _svnRepository.toCacheModel();
	}

	public com.liferay.socialcoding.model.SVNRepository toEscapedModel() {
		return new SVNRepositoryWrapper(_svnRepository.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _svnRepository.toString();
	}

	public java.lang.String toXmlString() {
		return _svnRepository.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_svnRepository.persist();
	}

	public java.lang.String getName() {
		return _svnRepository.getName();
	}

	public java.lang.String getShortURL() {
		return _svnRepository.getShortURL();
	}

	public SVNRepository getWrappedSVNRepository() {
		return _svnRepository;
	}

	public void resetOriginalValues() {
		_svnRepository.resetOriginalValues();
	}

	private SVNRepository _svnRepository;
}