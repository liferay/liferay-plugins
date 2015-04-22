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
 * Provides a wrapper for {@link AssetEntrySetLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetLocalService
 * @generated
 */
public class AssetEntrySetLocalServiceWrapper
	implements AssetEntrySetLocalService,
		ServiceWrapper<AssetEntrySetLocalService> {
	public AssetEntrySetLocalServiceWrapper(
		AssetEntrySetLocalService assetEntrySetLocalService) {
		_assetEntrySetLocalService = assetEntrySetLocalService;
	}

	/**
	* Adds the asset entry set to the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySet the asset entry set
	* @return the asset entry set that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySet addAssetEntrySet(
		com.liferay.asset.entry.set.model.AssetEntrySet assetEntrySet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.addAssetEntrySet(assetEntrySet);
	}

	/**
	* Creates a new asset entry set with the primary key. Does not add the asset entry set to the database.
	*
	* @param assetEntrySetId the primary key for the new asset entry set
	* @return the new asset entry set
	*/
	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySet createAssetEntrySet(
		long assetEntrySetId) {
		return _assetEntrySetLocalService.createAssetEntrySet(assetEntrySetId);
	}

	/**
	* Deletes the asset entry set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySetId the primary key of the asset entry set
	* @return the asset entry set that was removed
	* @throws PortalException if a asset entry set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySet deleteAssetEntrySet(
		long assetEntrySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.deleteAssetEntrySet(assetEntrySetId);
	}

	/**
	* Deletes the asset entry set from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySet the asset entry set
	* @return the asset entry set that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySet deleteAssetEntrySet(
		com.liferay.asset.entry.set.model.AssetEntrySet assetEntrySet)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.deleteAssetEntrySet(assetEntrySet);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetEntrySetLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySet fetchAssetEntrySet(
		long assetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.fetchAssetEntrySet(assetEntrySetId);
	}

	/**
	* Returns the asset entry set with the primary key.
	*
	* @param assetEntrySetId the primary key of the asset entry set
	* @return the asset entry set
	* @throws PortalException if a asset entry set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySet getAssetEntrySet(
		long assetEntrySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.getAssetEntrySet(assetEntrySetId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> getAssetEntrySets(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.getAssetEntrySets(start, end);
	}

	/**
	* Returns the number of asset entry sets.
	*
	* @return the number of asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getAssetEntrySetsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.getAssetEntrySetsCount();
	}

	/**
	* Updates the asset entry set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySet the asset entry set
	* @return the asset entry set that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySet updateAssetEntrySet(
		com.liferay.asset.entry.set.model.AssetEntrySet assetEntrySet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.updateAssetEntrySet(assetEntrySet);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _assetEntrySetLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetEntrySetLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetEntrySetLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySet addAssetEntrySet(
		long userId, long parentAssetEntrySetId, long creatorClassNameId,
		long creatorClassPK,
		com.liferay.portal.kernel.json.JSONObject payloadJSONObject,
		boolean privateAssetEntrySet)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.addAssetEntrySet(userId,
			parentAssetEntrySetId, creatorClassNameId, creatorClassPK,
			payloadJSONObject, privateAssetEntrySet);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject addFileAttachment(
		long userId, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.addFileAttachment(userId, file);
	}

	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySet fetchAssetEntrySet(
		long userId, long assetEntrySetId, int childAssetEntrySetsLimit,
		int likedParticipantsLimit)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.fetchAssetEntrySet(userId,
			assetEntrySetId, childAssetEntrySetsLimit, likedParticipantsLimit);
	}

	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySet getAssetEntrySet(
		long userId, long assetEntrySetId, int childAssetEntrySetsLimit,
		int likedParticipantsLimit)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.getAssetEntrySet(userId,
			assetEntrySetId, childAssetEntrySetsLimit, likedParticipantsLimit);
	}

	@Override
	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> getNewAssetEntrySets(
		long userId, long createTime, long parentAssetEntrySetId,
		com.liferay.portal.kernel.json.JSONArray sharedToJSONArray,
		java.lang.String[] assetTagNames, int childAssetEntrySetsLimit,
		int likedParticipantsLimit, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.getNewAssetEntrySets(userId,
			createTime, parentAssetEntrySetId, sharedToJSONArray,
			assetTagNames, childAssetEntrySetsLimit, likedParticipantsLimit,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> getNewChildAssetEntrySets(
		long userId, long createTime, long parentAssetEntrySetId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.getNewChildAssetEntrySets(userId,
			createTime, parentAssetEntrySetId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> getOldAssetEntrySets(
		long userId, long createTime, long parentAssetEntrySetId,
		com.liferay.portal.kernel.json.JSONArray sharedToJSONArray,
		java.lang.String[] assetTagNames, int childAssetEntrySetsLimit,
		int likedParticipantsLimit, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.getOldAssetEntrySets(userId,
			createTime, parentAssetEntrySetId, sharedToJSONArray,
			assetTagNames, childAssetEntrySetsLimit, likedParticipantsLimit,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> getOldChildAssetEntrySets(
		long userId, long createTime, long parentAssetEntrySetId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.getOldChildAssetEntrySets(userId,
			createTime, parentAssetEntrySetId, start, end, orderByComparator);
	}

	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySet likeAssetEntrySet(
		long userId, long assetEntrySetId, int childAssetEntrySetsLimit,
		int likedParticipantsLimit)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.likeAssetEntrySet(userId,
			assetEntrySetId, childAssetEntrySetsLimit, likedParticipantsLimit);
	}

	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySet unlikeAssetEntrySet(
		long userId, long assetEntrySetId, int childAssetEntrySetsLimit,
		int likedParticipantsLimit)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.unlikeAssetEntrySet(userId,
			assetEntrySetId, childAssetEntrySetsLimit, likedParticipantsLimit);
	}

	@Override
	public void updateAssetEntry(long assetEntrySetId,
		java.lang.String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_assetEntrySetLocalService.updateAssetEntry(assetEntrySetId,
			assetTagNames);
	}

	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySet updateAssetEntrySet(
		long assetEntrySetId,
		com.liferay.portal.kernel.json.JSONObject payloadJSONObject,
		boolean privateAssetEntrySet)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLocalService.updateAssetEntrySet(assetEntrySetId,
			payloadJSONObject, privateAssetEntrySet);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public AssetEntrySetLocalService getWrappedAssetEntrySetLocalService() {
		return _assetEntrySetLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedAssetEntrySetLocalService(
		AssetEntrySetLocalService assetEntrySetLocalService) {
		_assetEntrySetLocalService = assetEntrySetLocalService;
	}

	@Override
	public AssetEntrySetLocalService getWrappedService() {
		return _assetEntrySetLocalService;
	}

	@Override
	public void setWrappedService(
		AssetEntrySetLocalService assetEntrySetLocalService) {
		_assetEntrySetLocalService = assetEntrySetLocalService;
	}

	private AssetEntrySetLocalService _assetEntrySetLocalService;
}