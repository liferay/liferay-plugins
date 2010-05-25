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
 * <a href="AMSAssetLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * {@link AMSAssetLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSAssetLocalService
 * @generated
 */
public class AMSAssetLocalServiceUtil {
	public static com.liferay.ams.model.AMSAsset addAMSAsset(
		com.liferay.ams.model.AMSAsset amsAsset)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAMSAsset(amsAsset);
	}

	public static com.liferay.ams.model.AMSAsset createAMSAsset(long amsAssetId) {
		return getService().createAMSAsset(amsAssetId);
	}

	public static void deleteAMSAsset(long amsAssetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAMSAsset(amsAssetId);
	}

	public static void deleteAMSAsset(com.liferay.ams.model.AMSAsset amsAsset)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAMSAsset(amsAsset);
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

	public static com.liferay.ams.model.AMSAsset getAMSAsset(long amsAssetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAMSAsset(amsAssetId);
	}

	public static java.util.List<com.liferay.ams.model.AMSAsset> getAMSAssets(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAMSAssets(start, end);
	}

	public static int getAMSAssetsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAMSAssetsCount();
	}

	public static com.liferay.ams.model.AMSAsset updateAMSAsset(
		com.liferay.ams.model.AMSAsset amsAsset)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAMSAsset(amsAsset);
	}

	public static com.liferay.ams.model.AMSAsset updateAMSAsset(
		com.liferay.ams.model.AMSAsset amsAsset, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAMSAsset(amsAsset, merge);
	}

	public static void clearService() {
		_service = null;
	}

	public static AMSAssetLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					AMSAssetLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					AMSAssetLocalService.class.getName(), portletClassLoader);

			_service = new AMSAssetLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(AMSAssetLocalService service) {
		_service = service;
	}

	private static AMSAssetLocalService _service;
}