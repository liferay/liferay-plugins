/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the app local service. This utility wraps {@link com.liferay.marketplace.service.impl.AppLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Ryan Park
 * @see AppLocalService
 * @see com.liferay.marketplace.service.base.AppLocalServiceBaseImpl
 * @see com.liferay.marketplace.service.impl.AppLocalServiceImpl
 * @generated
 */
public class AppLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.marketplace.service.impl.AppLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the app to the database. Also notifies the appropriate model listeners.
	*
	* @param app the app
	* @return the app that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App addApp(
		com.liferay.marketplace.model.App app)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addApp(app);
	}

	/**
	* Creates a new app with the primary key. Does not add the app to the database.
	*
	* @param appId the primary key for the new app
	* @return the new app
	*/
	public static com.liferay.marketplace.model.App createApp(long appId) {
		return getService().createApp(appId);
	}

	/**
	* Deletes the app with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appId the primary key of the app
	* @return the app that was removed
	* @throws PortalException if a app with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App deleteApp(long appId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteApp(appId);
	}

	/**
	* Deletes the app from the database. Also notifies the appropriate model listeners.
	*
	* @param app the app
	* @return the app that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App deleteApp(
		com.liferay.marketplace.model.App app)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteApp(app);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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

	public static com.liferay.marketplace.model.App fetchApp(long appId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchApp(appId);
	}

	/**
	* Returns the app with the primary key.
	*
	* @param appId the primary key of the app
	* @return the app
	* @throws PortalException if a app with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App getApp(long appId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getApp(appId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the apps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of apps
	* @param end the upper bound of the range of apps (not inclusive)
	* @return the range of apps
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.App> getApps(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getApps(start, end);
	}

	/**
	* Returns the number of apps.
	*
	* @return the number of apps
	* @throws SystemException if a system exception occurred
	*/
	public static int getAppsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppsCount();
	}

	/**
	* Updates the app in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param app the app
	* @return the app that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App updateApp(
		com.liferay.marketplace.model.App app)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateApp(app);
	}

	/**
	* Updates the app in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param app the app
	* @param merge whether to merge the app with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the app that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.App updateApp(
		com.liferay.marketplace.model.App app, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateApp(app, merge);
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

	public static com.liferay.marketplace.model.App addApp(long userId,
		long remoteAppId, java.lang.String version,
		java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addApp(userId, remoteAppId, version, inputStream);
	}

	public static com.liferay.marketplace.model.App fetchRemoteApp(
		long remoteAppId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchRemoteApp(remoteAppId);
	}

	public static void installApp(long remoteAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().installApp(remoteAppId);
	}

	public static void processMarketplaceProperties(
		java.util.Properties properties)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().processMarketplaceProperties(properties);
	}

	public static void uninstallApp(long remoteAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().uninstallApp(remoteAppId);
	}

	public static com.liferay.marketplace.model.App updateApp(long appId,
		java.lang.String version, java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateApp(appId, version, inputStream);
	}

	public static void clearService() {
		_service = null;
	}

	public static AppLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AppLocalService.class.getName());

			if (invokableLocalService instanceof AppLocalService) {
				_service = (AppLocalService)invokableLocalService;
			}
			else {
				_service = new AppLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AppLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AppLocalService service) {
	}

	private static AppLocalService _service;
}