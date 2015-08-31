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

package com.liferay.sync.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for SyncDLObject. This utility wraps
 * {@link com.liferay.sync.service.impl.SyncDLObjectLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLObjectLocalService
 * @see com.liferay.sync.service.base.SyncDLObjectLocalServiceBaseImpl
 * @see com.liferay.sync.service.impl.SyncDLObjectLocalServiceImpl
 * @generated
 */
public class SyncDLObjectLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.sync.service.impl.SyncDLObjectLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the sync d l object to the database. Also notifies the appropriate model listeners.
	*
	* @param syncDLObject the sync d l object
	* @return the sync d l object that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject addSyncDLObject(
		com.liferay.sync.model.SyncDLObject syncDLObject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addSyncDLObject(syncDLObject);
	}

	/**
	* Creates a new sync d l object with the primary key. Does not add the sync d l object to the database.
	*
	* @param syncDLObjectId the primary key for the new sync d l object
	* @return the new sync d l object
	*/
	public static com.liferay.sync.model.SyncDLObject createSyncDLObject(
		long syncDLObjectId) {
		return getService().createSyncDLObject(syncDLObjectId);
	}

	/**
	* Deletes the sync d l object with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param syncDLObjectId the primary key of the sync d l object
	* @return the sync d l object that was removed
	* @throws PortalException if a sync d l object with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject deleteSyncDLObject(
		long syncDLObjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSyncDLObject(syncDLObjectId);
	}

	/**
	* Deletes the sync d l object from the database. Also notifies the appropriate model listeners.
	*
	* @param syncDLObject the sync d l object
	* @return the sync d l object that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject deleteSyncDLObject(
		com.liferay.sync.model.SyncDLObject syncDLObject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSyncDLObject(syncDLObject);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.sync.model.SyncDLObject fetchSyncDLObject(
		long syncDLObjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchSyncDLObject(syncDLObjectId);
	}

	/**
	* Returns the sync d l object with the primary key.
	*
	* @param syncDLObjectId the primary key of the sync d l object
	* @return the sync d l object
	* @throws PortalException if a sync d l object with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject getSyncDLObject(
		long syncDLObjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSyncDLObject(syncDLObjectId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the sync d l objects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @return the range of sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> getSyncDLObjects(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSyncDLObjects(start, end);
	}

	/**
	* Returns the number of sync d l objects.
	*
	* @return the number of sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static int getSyncDLObjectsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSyncDLObjectsCount();
	}

	/**
	* Updates the sync d l object in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param syncDLObject the sync d l object
	* @return the sync d l object that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject updateSyncDLObject(
		com.liferay.sync.model.SyncDLObject syncDLObject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSyncDLObject(syncDLObject);
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

	public static com.liferay.sync.model.SyncDLObject addSyncDLObject(
		long companyId, long userId, java.lang.String userName,
		long modifiedTime, long repositoryId, long parentFolderId,
		java.lang.String treePath, java.lang.String name,
		java.lang.String extension, java.lang.String mimeType,
		java.lang.String description, java.lang.String changeLog,
		java.lang.String extraSettings, java.lang.String version,
		long versionId, long size, java.lang.String checksum,
		java.lang.String event, java.util.Date lockExpirationDate,
		long lockUserId, java.lang.String lockUserName, java.lang.String type,
		long typePK, java.lang.String typeUuid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addSyncDLObject(companyId, userId, userName, modifiedTime,
			repositoryId, parentFolderId, treePath, name, extension, mimeType,
			description, changeLog, extraSettings, version, versionId, size,
			checksum, event, lockExpirationDate, lockUserId, lockUserName,
			type, typePK, typeUuid);
	}

	public static void deleteSyncDLObjects(java.lang.String version,
		java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteSyncDLObjects(version, type);
	}

	public static com.liferay.sync.model.SyncDLObject fetchSyncDLObject(
		java.lang.String type, long typePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchSyncDLObject(type, typePK);
	}

	public static long getLatestModifiedTime()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLatestModifiedTime();
	}

	public static java.util.List<com.liferay.sync.model.SyncDLObject> getSyncDLObjects(
		long repositoryId, long parentFolderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSyncDLObjects(repositoryId, parentFolderId);
	}

	public static void clearService() {
		_service = null;
	}

	public static SyncDLObjectLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SyncDLObjectLocalService.class.getName());

			if (invokableLocalService instanceof SyncDLObjectLocalService) {
				_service = (SyncDLObjectLocalService)invokableLocalService;
			}
			else {
				_service = new SyncDLObjectLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SyncDLObjectLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(SyncDLObjectLocalService service) {
	}

	private static SyncDLObjectLocalService _service;
}