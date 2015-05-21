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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for AssetEntrySetLike. This utility wraps
 * {@link com.liferay.asset.entry.set.service.impl.AssetEntrySetLikeServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetLikeService
 * @see com.liferay.asset.entry.set.service.base.AssetEntrySetLikeServiceBaseImpl
 * @see com.liferay.asset.entry.set.service.impl.AssetEntrySetLikeServiceImpl
 * @generated
 */
public class AssetEntrySetLikeServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.asset.entry.set.service.impl.AssetEntrySetLikeServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySetLike> getAssetEntrySetLikes(
		long assetEntrySetId, long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetEntrySetLikes(assetEntrySetId, classNameId,
			classPK, start, end);
	}

	public static void clearService() {
		_service = null;
	}

	public static AssetEntrySetLikeService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AssetEntrySetLikeService.class.getName());

			if (invokableService instanceof AssetEntrySetLikeService) {
				_service = (AssetEntrySetLikeService)invokableService;
			}
			else {
				_service = new AssetEntrySetLikeServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(AssetEntrySetLikeServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(AssetEntrySetLikeService service) {
	}

	private static AssetEntrySetLikeService _service;
}