/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.wsrp.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * The utility for the w s r p consumer local service. This utility wraps {@link com.liferay.wsrp.service.impl.WSRPConsumerLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * Never modify this class directly. Add custom service methods to {@link com.liferay.wsrp.service.impl.WSRPConsumerLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerLocalService
 * @see com.liferay.wsrp.service.base.WSRPConsumerLocalServiceBaseImpl
 * @see com.liferay.wsrp.service.impl.WSRPConsumerLocalServiceImpl
 * @generated
 */
public class WSRPConsumerLocalServiceUtil {
	/**
	* Adds the w s r p consumer to the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumer the w s r p consumer to add
	* @return the w s r p consumer that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumer addWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addWSRPConsumer(wsrpConsumer);
	}

	/**
	* Creates a new w s r p consumer with the primary key. Does not add the w s r p consumer to the database.
	*
	* @param wsrpConsumerId the primary key for the new w s r p consumer
	* @return the new w s r p consumer
	*/
	public static com.liferay.wsrp.model.WSRPConsumer createWSRPConsumer(
		long wsrpConsumerId) {
		return getService().createWSRPConsumer(wsrpConsumerId);
	}

	/**
	* Deletes the w s r p consumer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumerId the primary key of the w s r p consumer to delete
	* @throws PortalException if a w s r p consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteWSRPConsumer(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteWSRPConsumer(wsrpConsumerId);
	}

	/**
	* Deletes the w s r p consumer from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumer the w s r p consumer to delete
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteWSRPConsumer(wsrpConsumer);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("unchecked")
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
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("unchecked")
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
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the w s r p consumer with the primary key.
	*
	* @param wsrpConsumerId the primary key of the w s r p consumer to get
	* @return the w s r p consumer
	* @throws PortalException if a w s r p consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumer getWSRPConsumer(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getWSRPConsumer(wsrpConsumerId);
	}

	/**
	* Gets a range of all the w s r p consumers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of w s r p consumers to return
	* @param end the upper bound of the range of w s r p consumers to return (not inclusive)
	* @return the range of w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> getWSRPConsumers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWSRPConsumers(start, end);
	}

	/**
	* Gets the number of w s r p consumers.
	*
	* @return the number of w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public static int getWSRPConsumersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWSRPConsumersCount();
	}

	/**
	* Updates the w s r p consumer in the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumer the w s r p consumer to update
	* @return the w s r p consumer that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumer updateWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateWSRPConsumer(wsrpConsumer);
	}

	/**
	* Updates the w s r p consumer in the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumer the w s r p consumer to update
	* @param merge whether to merge the w s r p consumer with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the w s r p consumer that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumer updateWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateWSRPConsumer(wsrpConsumer, merge);
	}

	public static com.liferay.wsrp.model.WSRPConsumer addWSRPConsumer(
		long companyId, java.lang.String adminPortletId, java.lang.String name,
		java.lang.String url, java.lang.String userToken)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addWSRPConsumer(companyId, adminPortletId, name, url,
			userToken);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> getWSRPConsumers(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWSRPConsumers(companyId, start, end);
	}

	public static int getWSRPConsumersCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWSRPConsumersCount(companyId);
	}

	public static com.liferay.wsrp.model.WSRPConsumer registerWSRPConsumer(
		long wsrpConsumerId, java.lang.String adminPortletId,
		com.liferay.portal.kernel.util.UnicodeProperties registrationProperties,
		java.lang.String registrationHandle, java.lang.String userToken)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .registerWSRPConsumer(wsrpConsumerId, adminPortletId,
			registrationProperties, registrationHandle, userToken);
	}

	public static void restartConsumer(long wsrpConsumerId,
		java.lang.String userToken)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().restartConsumer(wsrpConsumerId, userToken);
	}

	public static void updateServiceDescription(long wsrpConsumerId,
		java.lang.String userToken)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateServiceDescription(wsrpConsumerId, userToken);
	}

	public static com.liferay.wsrp.model.WSRPConsumer updateWSRPConsumer(
		long wsrpConsumerId, java.lang.String adminPortletId,
		java.lang.String name, java.lang.String url, java.lang.String userToken)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateWSRPConsumer(wsrpConsumerId, adminPortletId, name,
			url, userToken);
	}

	public static void clearService() {
		_service = null;
	}

	public static WSRPConsumerLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					WSRPConsumerLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					WSRPConsumerLocalService.class.getName(), portletClassLoader);

			_service = new WSRPConsumerLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(WSRPConsumerLocalService service) {
		_service = service;
	}

	private static WSRPConsumerLocalService _service;
}