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

package com.liferay.asset.sharing.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AssetSharingEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetSharingEntryLocalService
 * @generated
 */
public class AssetSharingEntryLocalServiceWrapper
	implements AssetSharingEntryLocalService,
		ServiceWrapper<AssetSharingEntryLocalService> {
	public AssetSharingEntryLocalServiceWrapper(
		AssetSharingEntryLocalService assetSharingEntryLocalService) {
		_assetSharingEntryLocalService = assetSharingEntryLocalService;
	}

	/**
	* Adds the asset sharing entry to the database. Also notifies the appropriate model listeners.
	*
	* @param assetSharingEntry the asset sharing entry
	* @return the asset sharing entry that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.asset.sharing.model.AssetSharingEntry addAssetSharingEntry(
		com.liferay.asset.sharing.model.AssetSharingEntry assetSharingEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetSharingEntryLocalService.addAssetSharingEntry(assetSharingEntry);
	}

	/**
	* Creates a new asset sharing entry with the primary key. Does not add the asset sharing entry to the database.
	*
	* @param assetSharingEntryPK the primary key for the new asset sharing entry
	* @return the new asset sharing entry
	*/
	@Override
	public com.liferay.asset.sharing.model.AssetSharingEntry createAssetSharingEntry(
		com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK assetSharingEntryPK) {
		return _assetSharingEntryLocalService.createAssetSharingEntry(assetSharingEntryPK);
	}

	/**
	* Deletes the asset sharing entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetSharingEntryPK the primary key of the asset sharing entry
	* @return the asset sharing entry that was removed
	* @throws PortalException if a asset sharing entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.asset.sharing.model.AssetSharingEntry deleteAssetSharingEntry(
		com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK assetSharingEntryPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetSharingEntryLocalService.deleteAssetSharingEntry(assetSharingEntryPK);
	}

	/**
	* Deletes the asset sharing entry from the database. Also notifies the appropriate model listeners.
	*
	* @param assetSharingEntry the asset sharing entry
	* @return the asset sharing entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.asset.sharing.model.AssetSharingEntry deleteAssetSharingEntry(
		com.liferay.asset.sharing.model.AssetSharingEntry assetSharingEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetSharingEntryLocalService.deleteAssetSharingEntry(assetSharingEntry);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetSharingEntryLocalService.dynamicQuery();
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
		return _assetSharingEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.sharing.model.impl.AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _assetSharingEntryLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.sharing.model.impl.AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _assetSharingEntryLocalService.dynamicQuery(dynamicQuery, start,
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
		return _assetSharingEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _assetSharingEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.asset.sharing.model.AssetSharingEntry fetchAssetSharingEntry(
		com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK assetSharingEntryPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetSharingEntryLocalService.fetchAssetSharingEntry(assetSharingEntryPK);
	}

	/**
	* Returns the asset sharing entry with the primary key.
	*
	* @param assetSharingEntryPK the primary key of the asset sharing entry
	* @return the asset sharing entry
	* @throws PortalException if a asset sharing entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.asset.sharing.model.AssetSharingEntry getAssetSharingEntry(
		com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK assetSharingEntryPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetSharingEntryLocalService.getAssetSharingEntry(assetSharingEntryPK);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetSharingEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset sharing entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.sharing.model.impl.AssetSharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset sharing entries
	* @param end the upper bound of the range of asset sharing entries (not inclusive)
	* @return the range of asset sharing entries
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.asset.sharing.model.AssetSharingEntry> getAssetSharingEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetSharingEntryLocalService.getAssetSharingEntries(start, end);
	}

	/**
	* Returns the number of asset sharing entries.
	*
	* @return the number of asset sharing entries
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getAssetSharingEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetSharingEntryLocalService.getAssetSharingEntriesCount();
	}

	/**
	* Updates the asset sharing entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetSharingEntry the asset sharing entry
	* @return the asset sharing entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.asset.sharing.model.AssetSharingEntry updateAssetSharingEntry(
		com.liferay.asset.sharing.model.AssetSharingEntry assetSharingEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetSharingEntryLocalService.updateAssetSharingEntry(assetSharingEntry);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _assetSharingEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetSharingEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetSharingEntryLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public void addAssetSharingEntries(long classNameId, long classPK,
		java.util.Map<java.lang.Long, java.util.Set<java.lang.Long>> sharedToClassPKsMap)
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetSharingEntryLocalService.addAssetSharingEntries(classNameId,
			classPK, sharedToClassPKsMap);
	}

	@Override
	public void addAssetSharingEntry(long classNameId, long classPK,
		long sharedToClassNameId, long sharedToClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetSharingEntryLocalService.addAssetSharingEntry(classNameId,
			classPK, sharedToClassNameId, sharedToClassPK);
	}

	@Override
	public void deleteAssetSharingEntries(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetSharingEntryLocalService.deleteAssetSharingEntries(classNameId,
			classPK);
	}

	@Override
	public java.util.List<com.liferay.asset.sharing.model.AssetSharingEntry> getAssetSharingEntries(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetSharingEntryLocalService.getAssetSharingEntries(classNameId,
			classPK);
	}

	@Override
	public java.util.List<com.liferay.asset.sharing.model.AssetSharingEntry> getAssetSharingEntries(
		long classNameId, long classPK, long sharedToClassNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetSharingEntryLocalService.getAssetSharingEntries(classNameId,
			classPK, sharedToClassNameId);
	}

	@Override
	public java.util.List<com.liferay.asset.sharing.model.AssetSharingEntry> getSharedToAssetSharingEntries(
		long sharedToClassNameId, long sharedToClassPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetSharingEntryLocalService.getSharedToAssetSharingEntries(sharedToClassNameId,
			sharedToClassPK, start, end);
	}

	@Override
	public java.util.List<com.liferay.asset.sharing.model.AssetSharingEntry> getSharedToAssetSharingEntries(
		long classNameId, long sharedToClassNameId, long sharedToClassPK,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetSharingEntryLocalService.getSharedToAssetSharingEntries(classNameId,
			sharedToClassNameId, sharedToClassPK, start, end);
	}

	@Override
	public int getSharedToAssetSharingEntriesCount(long sharedToClassNameId,
		long sharedToClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetSharingEntryLocalService.getSharedToAssetSharingEntriesCount(sharedToClassNameId,
			sharedToClassPK);
	}

	@Override
	public int getSharedToAssetSharingEntriesCount(long classNameId,
		long sharedToClassNameId, long sharedToClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetSharingEntryLocalService.getSharedToAssetSharingEntriesCount(classNameId,
			sharedToClassNameId, sharedToClassPK);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public AssetSharingEntryLocalService getWrappedAssetSharingEntryLocalService() {
		return _assetSharingEntryLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedAssetSharingEntryLocalService(
		AssetSharingEntryLocalService assetSharingEntryLocalService) {
		_assetSharingEntryLocalService = assetSharingEntryLocalService;
	}

	@Override
	public AssetSharingEntryLocalService getWrappedService() {
		return _assetSharingEntryLocalService;
	}

	@Override
	public void setWrappedService(
		AssetSharingEntryLocalService assetSharingEntryLocalService) {
		_assetSharingEntryLocalService = assetSharingEntryLocalService;
	}

	private AssetSharingEntryLocalService _assetSharingEntryLocalService;
}