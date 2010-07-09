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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * {@link AssetLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetLocalService
 * @generated
 */
public class AssetLocalServiceUtil {
	public static com.liferay.ams.model.Asset addAsset(
		com.liferay.ams.model.Asset asset)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAsset(asset);
	}

	public static com.liferay.ams.model.Asset createAsset(long assetId) {
		return getService().createAsset(assetId);
	}

	public static void deleteAsset(long assetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAsset(assetId);
	}

	public static void deleteAsset(com.liferay.ams.model.Asset asset)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAsset(asset);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.ams.model.Asset getAsset(long assetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAsset(assetId);
	}

	public static java.util.List<com.liferay.ams.model.Asset> getAssets(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssets(start, end);
	}

	public static int getAssetsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetsCount();
	}

	public static com.liferay.ams.model.Asset updateAsset(
		com.liferay.ams.model.Asset asset)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAsset(asset);
	}

	public static com.liferay.ams.model.Asset updateAsset(
		com.liferay.ams.model.Asset asset, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAsset(asset, merge);
	}

	public static void clearService() {
		_service = null;
	}

	public static AssetLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					AssetLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					AssetLocalService.class.getName(), portletClassLoader);

			_service = new AssetLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(AssetLocalService service) {
		_service = service;
	}

	private static AssetLocalService _service;
}