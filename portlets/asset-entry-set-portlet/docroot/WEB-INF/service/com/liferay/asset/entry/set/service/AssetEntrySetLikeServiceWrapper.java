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
 * Provides a wrapper for {@link AssetEntrySetLikeService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetLikeService
 * @generated
 */
public class AssetEntrySetLikeServiceWrapper implements AssetEntrySetLikeService,
	ServiceWrapper<AssetEntrySetLikeService> {
	public AssetEntrySetLikeServiceWrapper(
		AssetEntrySetLikeService assetEntrySetLikeService) {
		_assetEntrySetLikeService = assetEntrySetLikeService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _assetEntrySetLikeService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetEntrySetLikeService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetEntrySetLikeService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getLikedParticipantFullNames(
		long assetEntrySetId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLikeService.getLikedParticipantFullNames(assetEntrySetId,
			start, end);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public AssetEntrySetLikeService getWrappedAssetEntrySetLikeService() {
		return _assetEntrySetLikeService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedAssetEntrySetLikeService(
		AssetEntrySetLikeService assetEntrySetLikeService) {
		_assetEntrySetLikeService = assetEntrySetLikeService;
	}

	@Override
	public AssetEntrySetLikeService getWrappedService() {
		return _assetEntrySetLikeService;
	}

	@Override
	public void setWrappedService(
		AssetEntrySetLikeService assetEntrySetLikeService) {
		_assetEntrySetLikeService = assetEntrySetLikeService;
	}

	private AssetEntrySetLikeService _assetEntrySetLikeService;
}