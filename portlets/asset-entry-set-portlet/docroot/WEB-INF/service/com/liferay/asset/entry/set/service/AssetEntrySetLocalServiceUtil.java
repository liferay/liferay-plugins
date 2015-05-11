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
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for AssetEntrySet. This utility wraps
 * {@link com.liferay.asset.entry.set.service.impl.AssetEntrySetLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetLocalService
 * @see com.liferay.asset.entry.set.service.base.AssetEntrySetLocalServiceBaseImpl
 * @see com.liferay.asset.entry.set.service.impl.AssetEntrySetLocalServiceImpl
 * @generated
 */
public class AssetEntrySetLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.asset.entry.set.service.impl.AssetEntrySetLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the asset entry set to the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySet the asset entry set
	* @return the asset entry set that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet addAssetEntrySet(
		com.liferay.asset.entry.set.model.AssetEntrySet assetEntrySet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAssetEntrySet(assetEntrySet);
	}

	/**
	* Creates a new asset entry set with the primary key. Does not add the asset entry set to the database.
	*
	* @param assetEntrySetId the primary key for the new asset entry set
	* @return the new asset entry set
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet createAssetEntrySet(
		long assetEntrySetId) {
		return getService().createAssetEntrySet(assetEntrySetId);
	}

	/**
	* Deletes the asset entry set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySetId the primary key of the asset entry set
	* @return the asset entry set that was removed
	* @throws PortalException if a asset entry set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet deleteAssetEntrySet(
		long assetEntrySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAssetEntrySet(assetEntrySetId);
	}

	/**
	* Deletes the asset entry set from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySet the asset entry set
	* @return the asset entry set that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet deleteAssetEntrySet(
		com.liferay.asset.entry.set.model.AssetEntrySet assetEntrySet)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAssetEntrySet(assetEntrySet);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
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
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.asset.entry.set.model.AssetEntrySet fetchAssetEntrySet(
		long assetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAssetEntrySet(assetEntrySetId);
	}

	/**
	* Returns the asset entry set with the primary key.
	*
	* @param assetEntrySetId the primary key of the asset entry set
	* @return the asset entry set
	* @throws PortalException if a asset entry set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet getAssetEntrySet(
		long assetEntrySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetEntrySet(assetEntrySetId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset entry sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> getAssetEntrySets(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetEntrySets(start, end);
	}

	/**
	* Returns the number of asset entry sets.
	*
	* @return the number of asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static int getAssetEntrySetsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetEntrySetsCount();
	}

	/**
	* Updates the asset entry set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySet the asset entry set
	* @return the asset entry set that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet updateAssetEntrySet(
		com.liferay.asset.entry.set.model.AssetEntrySet assetEntrySet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAssetEntrySet(assetEntrySet);
	}

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

	public static com.liferay.asset.entry.set.model.AssetEntrySet addAssetEntrySet(
		long userId, long parentAssetEntrySetId, long creatorClassNameId,
		long creatorClassPK,
		com.liferay.portal.kernel.json.JSONObject payloadJSONObject,
		boolean privateAssetEntrySet)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addAssetEntrySet(userId, parentAssetEntrySetId,
			creatorClassNameId, creatorClassPK, payloadJSONObject,
			privateAssetEntrySet);
	}

	public static com.liferay.portal.kernel.json.JSONObject addFileAttachment(
		long userId, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addFileAttachment(userId, file);
	}

	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> getNewAssetEntrySets(
		long userId, long createTime, long parentAssetEntrySetId,
		com.liferay.portal.kernel.json.JSONArray sharedToJSONArray,
		java.lang.String[] assetTagNames, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getNewAssetEntrySets(userId, createTime,
			parentAssetEntrySetId, sharedToJSONArray, assetTagNames, start, end);
	}

	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> getNewChildAssetEntrySets(
		long userId, long createTime, long parentAssetEntrySetId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getNewChildAssetEntrySets(userId, createTime,
			parentAssetEntrySetId, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> getOldAssetEntrySets(
		long userId, long createTime, long parentAssetEntrySetId,
		com.liferay.portal.kernel.json.JSONArray sharedToJSONArray,
		java.lang.String[] assetTagNames, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOldAssetEntrySets(userId, createTime,
			parentAssetEntrySetId, sharedToJSONArray, assetTagNames, start, end);
	}

	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> getOldChildAssetEntrySets(
		long userId, long createTime, long parentAssetEntrySetId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOldChildAssetEntrySets(userId, createTime,
			parentAssetEntrySetId, start, end, orderByComparator);
	}

	public static com.liferay.asset.entry.set.model.AssetEntrySet likeAssetEntrySet(
		long userId, long assetEntrySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().likeAssetEntrySet(userId, assetEntrySetId);
	}

	public static com.liferay.asset.entry.set.model.AssetEntrySet unlikeAssetEntrySet(
		long userId, long assetEntrySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().unlikeAssetEntrySet(userId, assetEntrySetId);
	}

	public static void updateAssetEntry(long assetEntrySetId,
		java.lang.String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateAssetEntry(assetEntrySetId, assetTagNames);
	}

	public static com.liferay.asset.entry.set.model.AssetEntrySet updateAssetEntrySet(
		long assetEntrySetId,
		com.liferay.portal.kernel.json.JSONObject payloadJSONObject,
		boolean privateAssetEntrySet)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateAssetEntrySet(assetEntrySetId, payloadJSONObject,
			privateAssetEntrySet);
	}

	public static void clearService() {
		_service = null;
	}

	public static AssetEntrySetLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AssetEntrySetLocalService.class.getName());

			if (invokableLocalService instanceof AssetEntrySetLocalService) {
				_service = (AssetEntrySetLocalService)invokableLocalService;
			}
			else {
				_service = new AssetEntrySetLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AssetEntrySetLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(AssetEntrySetLocalService service) {
	}

	private static AssetEntrySetLocalService _service;
}