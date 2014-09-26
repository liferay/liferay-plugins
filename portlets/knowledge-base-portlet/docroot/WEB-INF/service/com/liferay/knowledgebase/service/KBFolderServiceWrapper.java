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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link KBFolderService}.
 *
 * @author Brian Wing Shun Chan
 * @see KBFolderService
 * @generated
 */
@ProviderType
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

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _kbFolderService.invokeMethod(name, parameterTypes, arguments);
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

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public KBFolderService getWrappedKBFolderService() {
		return _kbFolderService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
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