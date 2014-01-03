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

package com.liferay.marketplace.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AppLocalService}.
 *
 * @author Ryan Park
 * @see AppLocalService
 * @generated
 */
public class AppLocalServiceWrapper implements AppLocalService,
	ServiceWrapper<AppLocalService> {
	public AppLocalServiceWrapper(AppLocalService appLocalService) {
		_appLocalService = appLocalService;
	}

	/**
	* Adds the app to the database. Also notifies the appropriate model listeners.
	*
	* @param app the app
	* @return the app that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.marketplace.model.App addApp(
		com.liferay.marketplace.model.App app)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appLocalService.addApp(app);
	}

	/**
	* Creates a new app with the primary key. Does not add the app to the database.
	*
	* @param appId the primary key for the new app
	* @return the new app
	*/
	@Override
	public com.liferay.marketplace.model.App createApp(long appId) {
		return _appLocalService.createApp(appId);
	}

	/**
	* Deletes the app with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appId the primary key of the app
	* @return the app that was removed
	* @throws PortalException if a app with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.marketplace.model.App deleteApp(long appId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appLocalService.deleteApp(appId);
	}

	/**
	* Deletes the app from the database. Also notifies the appropriate model listeners.
	*
	* @param app the app
	* @return the app that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.marketplace.model.App deleteApp(
		com.liferay.marketplace.model.App app)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appLocalService.deleteApp(app);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _appLocalService.dynamicQuery();
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
		return _appLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _appLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _appLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _appLocalService.dynamicQueryCount(dynamicQuery);
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
		return _appLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.marketplace.model.App fetchApp(long appId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appLocalService.fetchApp(appId);
	}

	/**
	* Returns the app with the matching UUID and company.
	*
	* @param uuid the app's UUID
	* @param companyId the primary key of the company
	* @return the matching app, or <code>null</code> if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.marketplace.model.App fetchAppByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appLocalService.fetchAppByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the app with the primary key.
	*
	* @param appId the primary key of the app
	* @return the app
	* @throws PortalException if a app with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.marketplace.model.App getApp(long appId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appLocalService.getApp(appId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the app with the matching UUID and company.
	*
	* @param uuid the app's UUID
	* @param companyId the primary key of the company
	* @return the matching app
	* @throws PortalException if a matching app could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.marketplace.model.App getAppByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appLocalService.getAppByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of all the apps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.AppModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @return the range of apps
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.marketplace.model.App> getApps(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appLocalService.getApps(start, end);
	}

	/**
	* Returns the number of apps.
	*
	* @return the number of apps
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getAppsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appLocalService.getAppsCount();
	}

	/**
	* Updates the app in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param app the app
	* @return the app that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.marketplace.model.App updateApp(
		com.liferay.marketplace.model.App app)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appLocalService.updateApp(app);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _appLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_appLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _appLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public void clearInstalledAppsCache() {
		_appLocalService.clearInstalledAppsCache();
	}

	@Override
	public com.liferay.marketplace.model.App fetchRemoteApp(long remoteAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appLocalService.fetchRemoteApp(remoteAppId);
	}

	@Override
	public java.util.List<com.liferay.marketplace.model.App> getApps(
		java.lang.String category)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appLocalService.getApps(category);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.String> getBundledApps() {
		return _appLocalService.getBundledApps();
	}

	@Override
	public java.util.List<com.liferay.marketplace.model.App> getInstalledApps()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appLocalService.getInstalledApps();
	}

	@Override
	public void installApp(long remoteAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_appLocalService.installApp(remoteAppId);
	}

	@Override
	public void processMarketplaceProperties(java.util.Properties properties)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_appLocalService.processMarketplaceProperties(properties);
	}

	@Override
	public void uninstallApp(long remoteAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_appLocalService.uninstallApp(remoteAppId);
	}

	@Override
	public com.liferay.marketplace.model.App updateApp(long userId,
		long remoteAppId, java.lang.String version, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appLocalService.updateApp(userId, remoteAppId, version, file);
	}

	@Override
	public com.liferay.marketplace.model.App updateApp(long userId,
		long remoteAppId, java.lang.String title, java.lang.String description,
		java.lang.String category, java.lang.String iconURL,
		java.lang.String version, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appLocalService.updateApp(userId, remoteAppId, title,
			description, category, iconURL, version, file);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public AppLocalService getWrappedAppLocalService() {
		return _appLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedAppLocalService(AppLocalService appLocalService) {
		_appLocalService = appLocalService;
	}

	@Override
	public AppLocalService getWrappedService() {
		return _appLocalService;
	}

	@Override
	public void setWrappedService(AppLocalService appLocalService) {
		_appLocalService = appLocalService;
	}

	private AppLocalService _appLocalService;
}