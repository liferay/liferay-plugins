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

package com.liferay.asset.sharing.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AssetSharingEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetSharingEntryService
 * @generated
 */
@ProviderType
public class AssetSharingEntryServiceWrapper implements AssetSharingEntryService,
	ServiceWrapper<AssetSharingEntryService> {
	public AssetSharingEntryServiceWrapper(
		AssetSharingEntryService assetSharingEntryService) {
		_assetSharingEntryService = assetSharingEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _assetSharingEntryService.getBeanIdentifier();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetSharingEntryService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetSharingEntryService.setBeanIdentifier(beanIdentifier);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public AssetSharingEntryService getWrappedAssetSharingEntryService() {
		return _assetSharingEntryService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedAssetSharingEntryService(
		AssetSharingEntryService assetSharingEntryService) {
		_assetSharingEntryService = assetSharingEntryService;
	}

	@Override
	public AssetSharingEntryService getWrappedService() {
		return _assetSharingEntryService;
	}

	@Override
	public void setWrappedService(
		AssetSharingEntryService assetSharingEntryService) {
		_assetSharingEntryService = assetSharingEntryService;
	}

	private AssetSharingEntryService _assetSharingEntryService;
}