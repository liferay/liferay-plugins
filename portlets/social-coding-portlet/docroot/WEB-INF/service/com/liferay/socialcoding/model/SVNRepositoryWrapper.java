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
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
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

	public long getPrimaryKey() {
		return _svnRepository.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_svnRepository.setPrimaryKey(pk);
	}

	public long getSvnRepositoryId() {
		return _svnRepository.getSvnRepositoryId();
	}

	public void setSvnRepositoryId(long svnRepositoryId) {
		_svnRepository.setSvnRepositoryId(svnRepositoryId);
	}

	public java.lang.String getUrl() {
		return _svnRepository.getUrl();
	}

	public void setUrl(java.lang.String url) {
		_svnRepository.setUrl(url);
	}

	public long getRevisionNumber() {
		return _svnRepository.getRevisionNumber();
	}

	public void setRevisionNumber(long revisionNumber) {
		_svnRepository.setRevisionNumber(revisionNumber);
	}

	public com.liferay.socialcoding.model.SVNRepository toEscapedModel() {
		return _svnRepository.toEscapedModel();
	}

	public boolean isNew() {
		return _svnRepository.isNew();
	}

	public boolean setNew(boolean n) {
		return _svnRepository.setNew(n);
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

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _svnRepository.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_svnRepository.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _svnRepository.clone();
	}

	public int compareTo(
		com.liferay.socialcoding.model.SVNRepository svnRepository) {
		return _svnRepository.compareTo(svnRepository);
	}

	public int hashCode() {
		return _svnRepository.hashCode();
	}

	public java.lang.String toString() {
		return _svnRepository.toString();
	}

	public java.lang.String toXmlString() {
		return _svnRepository.toXmlString();
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

	private SVNRepository _svnRepository;
}