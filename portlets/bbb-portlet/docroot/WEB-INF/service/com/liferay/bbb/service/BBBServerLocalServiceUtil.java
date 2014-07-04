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

package com.liferay.bbb.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for BBBServer. This utility wraps
 * {@link com.liferay.bbb.service.impl.BBBServerLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Shinn Lok
 * @see BBBServerLocalService
 * @see com.liferay.bbb.service.base.BBBServerLocalServiceBaseImpl
 * @see com.liferay.bbb.service.impl.BBBServerLocalServiceImpl
 * @generated
 */
public class BBBServerLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.bbb.service.impl.BBBServerLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the b b b server to the database. Also notifies the appropriate model listeners.
	*
	* @param bbbServer the b b b server
	* @return the b b b server that was added
	*/
	public static com.liferay.bbb.model.BBBServer addBBBServer(
		com.liferay.bbb.model.BBBServer bbbServer) {
		return getService().addBBBServer(bbbServer);
	}

	public static com.liferay.bbb.model.BBBServer addBBBServer(long userId,
		java.lang.String name, java.lang.String url, java.lang.String secret,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addBBBServer(userId, name, url, secret, serviceContext);
	}

	public static void checkBBBServers()
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().checkBBBServers();
	}

	/**
	* Creates a new b b b server with the primary key. Does not add the b b b server to the database.
	*
	* @param bbbServerId the primary key for the new b b b server
	* @return the new b b b server
	*/
	public static com.liferay.bbb.model.BBBServer createBBBServer(
		long bbbServerId) {
		return getService().createBBBServer(bbbServerId);
	}

	/**
	* Deletes the b b b server from the database. Also notifies the appropriate model listeners.
	*
	* @param bbbServer the b b b server
	* @return the b b b server that was removed
	*/
	public static com.liferay.bbb.model.BBBServer deleteBBBServer(
		com.liferay.bbb.model.BBBServer bbbServer) {
		return getService().deleteBBBServer(bbbServer);
	}

	/**
	* Deletes the b b b server with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param bbbServerId the primary key of the b b b server
	* @return the b b b server that was removed
	* @throws PortalException if a b b b server with the primary key could not be found
	*/
	public static com.liferay.bbb.model.BBBServer deleteBBBServer(
		long bbbServerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteBBBServer(bbbServerId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.bbb.model.BBBServer fetchBBBServer(
		long bbbServerId) {
		return getService().fetchBBBServer(bbbServerId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the b b b server with the primary key.
	*
	* @param bbbServerId the primary key of the b b b server
	* @return the b b b server
	* @throws PortalException if a b b b server with the primary key could not be found
	*/
	public static com.liferay.bbb.model.BBBServer getBBBServer(long bbbServerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getBBBServer(bbbServerId);
	}

	public static java.util.List<com.liferay.bbb.model.BBBServer> getBBBServers(
		boolean active) {
		return getService().getBBBServers(active);
	}

	/**
	* Returns a range of all the b b b servers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of b b b servers
	* @param end the upper bound of the range of b b b servers (not inclusive)
	* @return the range of b b b servers
	*/
	public static java.util.List<com.liferay.bbb.model.BBBServer> getBBBServers(
		int start, int end) {
		return getService().getBBBServers(start, end);
	}

	public static java.util.List<com.liferay.bbb.model.BBBServer> getBBBServers(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService().getBBBServers(start, end, obc);
	}

	/**
	* Returns the number of b b b servers.
	*
	* @return the number of b b b servers
	*/
	public static int getBBBServersCount() {
		return getService().getBBBServersCount();
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	/**
	* Updates the b b b server in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param bbbServer the b b b server
	* @return the b b b server that was updated
	*/
	public static com.liferay.bbb.model.BBBServer updateBBBServer(
		com.liferay.bbb.model.BBBServer bbbServer) {
		return getService().updateBBBServer(bbbServer);
	}

	public static com.liferay.bbb.model.BBBServer updateBBBServer(
		long bbbServerId, java.lang.String name, java.lang.String url,
		java.lang.String secret,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateBBBServer(bbbServerId, name, url, secret,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static BBBServerLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					BBBServerLocalService.class.getName());

			if (invokableLocalService instanceof BBBServerLocalService) {
				_service = (BBBServerLocalService)invokableLocalService;
			}
			else {
				_service = new BBBServerLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(BBBServerLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(BBBServerLocalService service) {
	}

	private static BBBServerLocalService _service;
}