/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.testblob.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BlobEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see BlobEntryLocalService
 * @generated
 */
public class BlobEntryLocalServiceWrapper implements BlobEntryLocalService,
	ServiceWrapper<BlobEntryLocalService> {
	public BlobEntryLocalServiceWrapper(
		BlobEntryLocalService blobEntryLocalService) {
		_blobEntryLocalService = blobEntryLocalService;
	}

	/**
	* Adds the blob entry to the database. Also notifies the appropriate model listeners.
	*
	* @param blobEntry the blob entry
	* @return the blob entry that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.testblob.model.BlobEntry addBlobEntry(
		com.liferay.testblob.model.BlobEntry blobEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _blobEntryLocalService.addBlobEntry(blobEntry);
	}

	/**
	* Creates a new blob entry with the primary key. Does not add the blob entry to the database.
	*
	* @param blobEntryId the primary key for the new blob entry
	* @return the new blob entry
	*/
	@Override
	public com.liferay.testblob.model.BlobEntry createBlobEntry(
		long blobEntryId) {
		return _blobEntryLocalService.createBlobEntry(blobEntryId);
	}

	/**
	* Deletes the blob entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param blobEntryId the primary key of the blob entry
	* @return the blob entry that was removed
	* @throws PortalException if a blob entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.testblob.model.BlobEntry deleteBlobEntry(
		long blobEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _blobEntryLocalService.deleteBlobEntry(blobEntryId);
	}

	/**
	* Deletes the blob entry from the database. Also notifies the appropriate model listeners.
	*
	* @param blobEntry the blob entry
	* @return the blob entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.testblob.model.BlobEntry deleteBlobEntry(
		com.liferay.testblob.model.BlobEntry blobEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _blobEntryLocalService.deleteBlobEntry(blobEntry);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _blobEntryLocalService.dynamicQuery();
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
		return _blobEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.BlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _blobEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.BlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _blobEntryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _blobEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _blobEntryLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.testblob.model.BlobEntry fetchBlobEntry(long blobEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _blobEntryLocalService.fetchBlobEntry(blobEntryId);
	}

	/**
	* Returns the blob entry with the primary key.
	*
	* @param blobEntryId the primary key of the blob entry
	* @return the blob entry
	* @throws PortalException if a blob entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.testblob.model.BlobEntry getBlobEntry(long blobEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _blobEntryLocalService.getBlobEntry(blobEntryId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _blobEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the blob entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.BlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of blob entries
	* @param end the upper bound of the range of blob entries (not inclusive)
	* @return the range of blob entries
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.testblob.model.BlobEntry> getBlobEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _blobEntryLocalService.getBlobEntries(start, end);
	}

	/**
	* Returns the number of blob entries.
	*
	* @return the number of blob entries
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getBlobEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _blobEntryLocalService.getBlobEntriesCount();
	}

	/**
	* Updates the blob entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param blobEntry the blob entry
	* @return the blob entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.testblob.model.BlobEntry updateBlobEntry(
		com.liferay.testblob.model.BlobEntry blobEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _blobEntryLocalService.updateBlobEntry(blobEntry);
	}

	@Override
	public com.liferay.testblob.model.BlobEntryBlobFieldBlobModel getBlobFieldBlobModel(
		java.io.Serializable primaryKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _blobEntryLocalService.getBlobFieldBlobModel(primaryKey);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _blobEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_blobEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _blobEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public BlobEntryLocalService getWrappedBlobEntryLocalService() {
		return _blobEntryLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedBlobEntryLocalService(
		BlobEntryLocalService blobEntryLocalService) {
		_blobEntryLocalService = blobEntryLocalService;
	}

	@Override
	public BlobEntryLocalService getWrappedService() {
		return _blobEntryLocalService;
	}

	@Override
	public void setWrappedService(BlobEntryLocalService blobEntryLocalService) {
		_blobEntryLocalService = blobEntryLocalService;
	}

	private BlobEntryLocalService _blobEntryLocalService;
}