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

package com.liferay.knowledgebase.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link KBFolderService}.
 *
 * @author Brian Wing Shun Chan
 * @see KBFolderService
 * @generated
 */
public class KBFolderServiceWrapper implements KBFolderService,
	ServiceWrapper<KBFolderService> {
	public KBFolderServiceWrapper(KBFolderService kbFolderService) {
		_kbFolderService = kbFolderService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _kbFolderService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kbFolderService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _kbFolderService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public com.liferay.knowledgebase.model.KBFolder addKBFolder(long groupId,
		long parentResourceClassNameId, long parentResourcePrimKey,
		java.lang.String name, java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbFolderService.addKBFolder(groupId, parentResourceClassNameId,
			parentResourcePrimKey, name, description, serviceContext);
	}

	@Override
	public com.liferay.knowledgebase.model.KBFolder deleteKBFolder(
		long kbFolderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbFolderService.deleteKBFolder(kbFolderId);
	}

	@Override
	public com.liferay.knowledgebase.model.KBFolder fetchKBFolderByUrlTitle(
		long groupId, long parentKbFolderId, java.lang.String urlTitle)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbFolderService.fetchKBFolderByUrlTitle(groupId,
			parentKbFolderId, urlTitle);
	}

	@Override
	public com.liferay.knowledgebase.model.KBFolder getKBFolder(long kbFolderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbFolderService.getKBFolder(kbFolderId);
	}

	@Override
	public com.liferay.knowledgebase.model.KBFolder getKBFolderByUrlTitle(
		long groupId, long parentKbFolderId, java.lang.String urlTitle)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbFolderService.getKBFolderByUrlTitle(groupId,
			parentKbFolderId, urlTitle);
	}

	@Override
	public java.util.List<com.liferay.knowledgebase.model.KBFolder> getKBFolders(
		long groupId, long parentKBFolderId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbFolderService.getKBFolders(groupId, parentKBFolderId, start,
			end);
	}

	@Override
	public int getKBFoldersCount(long groupId, long parentKBFolderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbFolderService.getKBFoldersCount(groupId, parentKBFolderId);
	}

	@Override
	public void moveKBFolder(long kbFolderId, long parentKBFolderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbFolderService.moveKBFolder(kbFolderId, parentKBFolderId);
	}

	@Override
	public com.liferay.knowledgebase.model.KBFolder updateKBFolder(
		long parentResourceClassNameId, long parentResourcePrimKey,
		long kbFolderId, java.lang.String name, java.lang.String description)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbFolderService.updateKBFolder(parentResourceClassNameId,
			parentResourcePrimKey, kbFolderId, name, description);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public KBFolderService getWrappedKBFolderService() {
		return _kbFolderService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedKBFolderService(KBFolderService kbFolderService) {
		_kbFolderService = kbFolderService;
	}

	@Override
	public KBFolderService getWrappedService() {
		return _kbFolderService;
	}

	@Override
	public void setWrappedService(KBFolderService kbFolderService) {
		_kbFolderService = kbFolderService;
	}

	private KBFolderService _kbFolderService;
}