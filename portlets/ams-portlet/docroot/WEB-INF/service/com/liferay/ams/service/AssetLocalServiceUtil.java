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
 * The utility for the asset local service. This utility wraps {@link com.liferay.ams.service.impl.AssetLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * Never modify this class directly. Add custom service methods to {@link com.liferay.ams.service.impl.AssetLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetLocalService
 * @see com.liferay.ams.service.base.AssetLocalServiceBaseImpl
 * @see com.liferay.ams.service.impl.AssetLocalServiceImpl
 * @generated
 */
public class AssetLocalServiceUtil {
	/**
	* Adds the asset to the database. Also notifies the appropriate model listeners.
	*
	* @param asset the asset to add
	* @return the asset that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.ams.model.Asset addAsset(
		com.liferay.ams.model.Asset asset)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAsset(asset);
	}

	/**
	* Creates a new asset with the primary key. Does not add the asset to the database.
	*
	* @param assetId the primary key for the new asset
	* @return the new asset
	*/
	public static com.liferay.ams.model.Asset createAsset(long assetId) {
		return getService().createAsset(assetId);
	}

	/**
	* Deletes the asset with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetId the primary key of the asset to delete
	* @throws PortalException if a asset with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteAsset(long assetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAsset(assetId);
	}

	/**
	* Deletes the asset from the database. Also notifies the appropriate model listeners.
	*
	* @param asset the asset to delete
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteAsset(com.liferay.ams.model.Asset asset)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAsset(asset);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the asset with the primary key.
	*
	* @param assetId the primary key of the asset to get
	* @return the asset
	* @throws PortalException if a asset with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.ams.model.Asset getAsset(long assetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAsset(assetId);
	}

	/**
	* Gets a range of all the assets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of assets to return
	* @param end the upper bound of the range of assets to return (not inclusive)
	* @return the range of assets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.ams.model.Asset> getAssets(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssets(start, end);
	}

	/**
	* Gets the number of assets.
	*
	* @return the number of assets
	* @throws SystemException if a system exception occurred
	*/
	public static int getAssetsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetsCount();
	}

	/**
	* Updates the asset in the database. Also notifies the appropriate model listeners.
	*
	* @param asset the asset to update
	* @return the asset that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.ams.model.Asset updateAsset(
		com.liferay.ams.model.Asset asset)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAsset(asset);
	}

	/**
	* Updates the asset in the database. Also notifies the appropriate model listeners.
	*
	* @param asset the asset to update
	* @param merge whether to merge the asset with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset that was updated
	* @throws SystemException if a system exception occurred
	*/
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
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					AssetLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
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