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

package com.liferay.ams.service;


/**
 * <a href="AMSAssetLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link AMSAssetLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSAssetLocalService
 * @generated
 */
public class AMSAssetLocalServiceWrapper implements AMSAssetLocalService {
	public AMSAssetLocalServiceWrapper(
		AMSAssetLocalService amsAssetLocalService) {
		_amsAssetLocalService = amsAssetLocalService;
	}

	public com.liferay.ams.model.AMSAsset addAMSAsset(
		com.liferay.ams.model.AMSAsset amsAsset)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsAssetLocalService.addAMSAsset(amsAsset);
	}

	public com.liferay.ams.model.AMSAsset createAMSAsset(long amsAssetId) {
		return _amsAssetLocalService.createAMSAsset(amsAssetId);
	}

	public void deleteAMSAsset(long amsAssetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_amsAssetLocalService.deleteAMSAsset(amsAssetId);
	}

	public void deleteAMSAsset(com.liferay.ams.model.AMSAsset amsAsset)
		throws com.liferay.portal.kernel.exception.SystemException {
		_amsAssetLocalService.deleteAMSAsset(amsAsset);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsAssetLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _amsAssetLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsAssetLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsAssetLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.ams.model.AMSAsset getAMSAsset(long amsAssetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _amsAssetLocalService.getAMSAsset(amsAssetId);
	}

	public java.util.List<com.liferay.ams.model.AMSAsset> getAMSAssets(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsAssetLocalService.getAMSAssets(start, end);
	}

	public int getAMSAssetsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsAssetLocalService.getAMSAssetsCount();
	}

	public com.liferay.ams.model.AMSAsset updateAMSAsset(
		com.liferay.ams.model.AMSAsset amsAsset)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsAssetLocalService.updateAMSAsset(amsAsset);
	}

	public com.liferay.ams.model.AMSAsset updateAMSAsset(
		com.liferay.ams.model.AMSAsset amsAsset, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsAssetLocalService.updateAMSAsset(amsAsset, merge);
	}

	public AMSAssetLocalService getWrappedAMSAssetLocalService() {
		return _amsAssetLocalService;
	}

	private AMSAssetLocalService _amsAssetLocalService;
}