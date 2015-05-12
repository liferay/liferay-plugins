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
 * Provides a wrapper for {@link AssetEntrySetLikeLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetLikeLocalService
 * @generated
 */
public class AssetEntrySetLikeLocalServiceWrapper
	implements AssetEntrySetLikeLocalService,
		ServiceWrapper<AssetEntrySetLikeLocalService> {
	public AssetEntrySetLikeLocalServiceWrapper(
		AssetEntrySetLikeLocalService assetEntrySetLikeLocalService) {
		_assetEntrySetLikeLocalService = assetEntrySetLikeLocalService;
	}

	/**
	* Adds the asset entry set like to the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySetLike the asset entry set like
	* @return the asset entry set like that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySetLike addAssetEntrySetLike(
		com.liferay.asset.entry.set.model.AssetEntrySetLike assetEntrySetLike)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLikeLocalService.addAssetEntrySetLike(assetEntrySetLike);
	}

	/**
	* Creates a new asset entry set like with the primary key. Does not add the asset entry set like to the database.
	*
	* @param assetEntrySetLikePK the primary key for the new asset entry set like
	* @return the new asset entry set like
	*/
	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySetLike createAssetEntrySetLike(
		com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK assetEntrySetLikePK) {
		return _assetEntrySetLikeLocalService.createAssetEntrySetLike(assetEntrySetLikePK);
	}

	/**
	* Deletes the asset entry set like with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySetLikePK the primary key of the asset entry set like
	* @return the asset entry set like that was removed
	* @throws PortalException if a asset entry set like with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySetLike deleteAssetEntrySetLike(
		com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK assetEntrySetLikePK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLikeLocalService.deleteAssetEntrySetLike(assetEntrySetLikePK);
	}

	/**
	* Deletes the asset entry set like from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySetLike the asset entry set like
	* @return the asset entry set like that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySetLike deleteAssetEntrySetLike(
		com.liferay.asset.entry.set.model.AssetEntrySetLike assetEntrySetLike)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLikeLocalService.deleteAssetEntrySetLike(assetEntrySetLike);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetEntrySetLikeLocalService.dynamicQuery();
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
		return _assetEntrySetLikeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetLikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _assetEntrySetLikeLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetLikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _assetEntrySetLikeLocalService.dynamicQuery(dynamicQuery, start,
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
		return _assetEntrySetLikeLocalService.dynamicQueryCount(dynamicQuery);
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
		return _assetEntrySetLikeLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySetLike fetchAssetEntrySetLike(
		com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK assetEntrySetLikePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLikeLocalService.fetchAssetEntrySetLike(assetEntrySetLikePK);
	}

	/**
	* Returns the asset entry set like with the primary key.
	*
	* @param assetEntrySetLikePK the primary key of the asset entry set like
	* @return the asset entry set like
	* @throws PortalException if a asset entry set like with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySetLike getAssetEntrySetLike(
		com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK assetEntrySetLikePK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLikeLocalService.getAssetEntrySetLike(assetEntrySetLikePK);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLikeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset entry set likes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetLikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry set likes
	* @param end the upper bound of the range of asset entry set likes (not inclusive)
	* @return the range of asset entry set likes
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySetLike> getAssetEntrySetLikes(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLikeLocalService.getAssetEntrySetLikes(start, end);
	}

	/**
	* Returns the number of asset entry set likes.
	*
	* @return the number of asset entry set likes
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getAssetEntrySetLikesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLikeLocalService.getAssetEntrySetLikesCount();
	}

	/**
	* Updates the asset entry set like in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySetLike the asset entry set like
	* @return the asset entry set like that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySetLike updateAssetEntrySetLike(
		com.liferay.asset.entry.set.model.AssetEntrySetLike assetEntrySetLike)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLikeLocalService.updateAssetEntrySetLike(assetEntrySetLike);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _assetEntrySetLikeLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetEntrySetLikeLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetEntrySetLikeLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySetLike fetchAssetEntrySetLike(
		long assetEntrySetId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLikeLocalService.fetchAssetEntrySetLike(assetEntrySetId,
			classNameId, classPK);
	}

	@Override
	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySetLike> getAssetEntrySetLikes(
		long assetEntrySetId, long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLikeLocalService.getAssetEntrySetLikes(assetEntrySetId,
			classNameId, classPK, start, end);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getLikedParticipantFullNames(
		long userId, long assetEntrySetId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySetLikeLocalService.getLikedParticipantFullNames(userId,
			assetEntrySetId, start, end);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public AssetEntrySetLikeLocalService getWrappedAssetEntrySetLikeLocalService() {
		return _assetEntrySetLikeLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedAssetEntrySetLikeLocalService(
		AssetEntrySetLikeLocalService assetEntrySetLikeLocalService) {
		_assetEntrySetLikeLocalService = assetEntrySetLikeLocalService;
	}

	@Override
	public AssetEntrySetLikeLocalService getWrappedService() {
		return _assetEntrySetLikeLocalService;
	}

	@Override
	public void setWrappedService(
		AssetEntrySetLikeLocalService assetEntrySetLikeLocalService) {
		_assetEntrySetLikeLocalService = assetEntrySetLikeLocalService;
	}

	private AssetEntrySetLikeLocalService _assetEntrySetLikeLocalService;
}