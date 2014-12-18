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

package com.liferay.asset.entry.set.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AssetEntrySetService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetService
 * @generated
 */
public class AssetEntrySetServiceWrapper implements AssetEntrySetService,
	ServiceWrapper<AssetEntrySetService> {
	public AssetEntrySetServiceWrapper(
		AssetEntrySetService assetEntrySetService) {
		_assetEntrySetService = assetEntrySetService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _assetEntrySetService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetEntrySetService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetEntrySetService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySet addAssetEntrySet(
		long parentAssetEntrySetId, java.lang.String payload)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetService.addAssetEntrySet(parentAssetEntrySetId,
			payload);
	}

	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySet addAssetEntrySet(
		java.lang.String payload)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetService.addAssetEntrySet(payload);
	}

	@Override
	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> getAssetEntrySets(
		long parentAssetEntrySetId, long lastAccessTime, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetService.getAssetEntrySets(parentAssetEntrySetId,
			lastAccessTime, start, end);
	}

	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySet likeAssetEntrySet(
		long assetEntrySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetService.likeAssetEntrySet(assetEntrySetId);
	}

	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySet unlikeAssetEntrySet(
		long assetEntrySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetService.unlikeAssetEntrySet(assetEntrySetId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public AssetEntrySetService getWrappedAssetEntrySetService() {
		return _assetEntrySetService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedAssetEntrySetService(
		AssetEntrySetService assetEntrySetService) {
		_assetEntrySetService = assetEntrySetService;
	}

	@Override
	public AssetEntrySetService getWrappedService() {
		return _assetEntrySetService;
	}

	@Override
	public void setWrappedService(AssetEntrySetService assetEntrySetService) {
		_assetEntrySetService = assetEntrySetService;
	}

	private AssetEntrySetService _assetEntrySetService;
}