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
 * Provides the local service utility for SyncDLFileVersionDiff. This utility wraps
 * {@link com.liferay.sync.service.impl.SyncDLFileVersionDiffLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLFileVersionDiffLocalService
 * @see com.liferay.sync.service.base.SyncDLFileVersionDiffLocalServiceBaseImpl
 * @see com.liferay.sync.service.impl.SyncDLFileVersionDiffLocalServiceImpl
 * @generated
 */
public class SyncDLFileVersionDiffLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.sync.service.impl.SyncDLFileVersionDiffLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the sync d l file version diff to the database. Also notifies the appropriate model listeners.
	*
	* @param syncDLFileVersionDiff the sync d l file version diff
	* @return the sync d l file version diff that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLFileVersionDiff addSyncDLFileVersionDiff(
		com.liferay.sync.model.SyncDLFileVersionDiff syncDLFileVersionDiff)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addSyncDLFileVersionDiff(syncDLFileVersionDiff);
	}

	/**
	* Creates a new sync d l file version diff with the primary key. Does not add the sync d l file version diff to the database.
	*
	* @param syncDLFileVersionDiffId the primary key for the new sync d l file version diff
	* @return the new sync d l file version diff
	*/
	public static com.liferay.sync.model.SyncDLFileVersionDiff createSyncDLFileVersionDiff(
		long syncDLFileVersionDiffId) {
		return getService().createSyncDLFileVersionDiff(syncDLFileVersionDiffId);
	}

	/**
	* Deletes the sync d l file version diff with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param syncDLFileVersionDiffId the primary key of the sync d l file version diff
	* @return the sync d l file version diff that was removed
	* @throws PortalException if a sync d l file version diff with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLFileVersionDiff deleteSyncDLFileVersionDiff(
		long syncDLFileVersionDiffId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSyncDLFileVersionDiff(syncDLFileVersionDiffId);
	}

	/**
	* Deletes the sync d l file version diff from the database. Also notifies the appropriate model listeners.
	*
	* @param syncDLFileVersionDiff the sync d l file version diff
	* @return the sync d l file version diff that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLFileVersionDiff deleteSyncDLFileVersionDiff(
		com.liferay.sync.model.SyncDLFileVersionDiff syncDLFileVersionDiff)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSyncDLFileVersionDiff(syncDLFileVersionDiff);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLFileVersionDiffModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLFileVersionDiffModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.sync.model.SyncDLFileVersionDiff fetchSyncDLFileVersionDiff(
		long syncDLFileVersionDiffId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchSyncDLFileVersionDiff(syncDLFileVersionDiffId);
	}

	/**
	* Returns the sync d l file version diff with the primary key.
	*
	* @param syncDLFileVersionDiffId the primary key of the sync d l file version diff
	* @return the sync d l file version diff
	* @throws PortalException if a sync d l file version diff with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLFileVersionDiff getSyncDLFileVersionDiff(
		long syncDLFileVersionDiffId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSyncDLFileVersionDiff(syncDLFileVersionDiffId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the sync d l file version diffs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLFileVersionDiffModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync d l file version diffs
	* @param end the upper bound of the range of sync d l file version diffs (not inclusive)
	* @return the range of sync d l file version diffs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLFileVersionDiff> getSyncDLFileVersionDiffs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSyncDLFileVersionDiffs(start, end);
	}

	/**
	* Returns the number of sync d l file version diffs.
	*
	* @return the number of sync d l file version diffs
	* @throws SystemException if a system exception occurred
	*/
	public static int getSyncDLFileVersionDiffsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSyncDLFileVersionDiffsCount();
	}

	/**
	* Updates the sync d l file version diff in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param syncDLFileVersionDiff the sync d l file version diff
	* @return the sync d l file version diff that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLFileVersionDiff updateSyncDLFileVersionDiff(
		com.liferay.sync.model.SyncDLFileVersionDiff syncDLFileVersionDiff)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSyncDLFileVersionDiff(syncDLFileVersionDiff);
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

	public static com.liferay.sync.model.SyncDLFileVersionDiff addSyncDLFileVersionDiff(
		long fileEntryId, long sourceFileVersionId, long targetFileVersionId,
		java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addSyncDLFileVersionDiff(fileEntryId, sourceFileVersionId,
			targetFileVersionId, file);
	}

	public static void deleteExpiredSyncDLFileVersionDiffs()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteExpiredSyncDLFileVersionDiffs();
	}

	public static void deleteSyncDLFileVersionDiffs(long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteSyncDLFileVersionDiffs(fileEntryId);
	}

	public static com.liferay.sync.model.SyncDLFileVersionDiff fetchSyncDLFileVersionDiff(
		long fileEntryId, long sourceFileVersionId, long targetFileVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchSyncDLFileVersionDiff(fileEntryId,
			sourceFileVersionId, targetFileVersionId);
	}

	public static void refreshExpirationDate(long syncDLFileVersionDiffId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().refreshExpirationDate(syncDLFileVersionDiffId);
	}

	public static void clearService() {
		_service = null;
	}

	public static SyncDLFileVersionDiffLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SyncDLFileVersionDiffLocalService.class.getName());

			if (invokableLocalService instanceof SyncDLFileVersionDiffLocalService) {
				_service = (SyncDLFileVersionDiffLocalService)invokableLocalService;
			}
			else {
				_service = new SyncDLFileVersionDiffLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SyncDLFileVersionDiffLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(SyncDLFileVersionDiffLocalService service) {
	}

	private static SyncDLFileVersionDiffLocalService _service;
}