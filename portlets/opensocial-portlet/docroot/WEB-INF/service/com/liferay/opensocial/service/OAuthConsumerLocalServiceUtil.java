/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.opensocial.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the o auth consumer local service. This utility wraps {@link com.liferay.opensocial.service.impl.OAuthConsumerLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OAuthConsumerLocalService
 * @see com.liferay.opensocial.service.base.OAuthConsumerLocalServiceBaseImpl
 * @see com.liferay.opensocial.service.impl.OAuthConsumerLocalServiceImpl
 * @generated
 */
public class OAuthConsumerLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.opensocial.service.impl.OAuthConsumerLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the o auth consumer to the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthConsumer the o auth consumer to add
	* @return the o auth consumer that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer addOAuthConsumer(
		com.liferay.opensocial.model.OAuthConsumer oAuthConsumer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addOAuthConsumer(oAuthConsumer);
	}

	/**
	* Creates a new o auth consumer with the primary key. Does not add the o auth consumer to the database.
	*
	* @param oAuthConsumerId the primary key for the new o auth consumer
	* @return the new o auth consumer
	*/
	public static com.liferay.opensocial.model.OAuthConsumer createOAuthConsumer(
		long oAuthConsumerId) {
		return getService().createOAuthConsumer(oAuthConsumerId);
	}

	/**
	* Deletes the o auth consumer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthConsumerId the primary key of the o auth consumer to delete
	* @throws PortalException if a o auth consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteOAuthConsumer(long oAuthConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteOAuthConsumer(oAuthConsumerId);
	}

	/**
	* Deletes the o auth consumer from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthConsumer the o auth consumer to delete
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteOAuthConsumer(
		com.liferay.opensocial.model.OAuthConsumer oAuthConsumer)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteOAuthConsumer(oAuthConsumer);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
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
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
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
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
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
	* Gets the o auth consumer with the primary key.
	*
	* @param oAuthConsumerId the primary key of the o auth consumer to get
	* @return the o auth consumer
	* @throws PortalException if a o auth consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer getOAuthConsumer(
		long oAuthConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getOAuthConsumer(oAuthConsumerId);
	}

	/**
	* Gets a range of all the o auth consumers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of o auth consumers to return
	* @param end the upper bound of the range of o auth consumers to return (not inclusive)
	* @return the range of o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.OAuthConsumer> getOAuthConsumers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOAuthConsumers(start, end);
	}

	/**
	* Gets the number of o auth consumers.
	*
	* @return the number of o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public static int getOAuthConsumersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOAuthConsumersCount();
	}

	/**
	* Updates the o auth consumer in the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthConsumer the o auth consumer to update
	* @return the o auth consumer that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer updateOAuthConsumer(
		com.liferay.opensocial.model.OAuthConsumer oAuthConsumer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateOAuthConsumer(oAuthConsumer);
	}

	/**
	* Updates the o auth consumer in the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthConsumer the o auth consumer to update
	* @param merge whether to merge the o auth consumer with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the o auth consumer that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.OAuthConsumer updateOAuthConsumer(
		com.liferay.opensocial.model.OAuthConsumer oAuthConsumer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateOAuthConsumer(oAuthConsumer, merge);
	}

	public static com.liferay.opensocial.model.OAuthConsumer addOAuthConsumer(
		long companyId, long gadgetId, java.lang.String serviceName,
		java.lang.String consumerKey, java.lang.String consumerSecret,
		java.lang.String keyType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addOAuthConsumer(companyId, gadgetId, serviceName,
			consumerKey, consumerSecret, keyType);
	}

	public static void deleteOAuthConsumers(long gadgetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteOAuthConsumers(gadgetId);
	}

	public static com.liferay.opensocial.model.OAuthConsumer getOAuthConsumer(
		long gadgetId, java.lang.String serviceName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getOAuthConsumer(gadgetId, serviceName);
	}

	public static java.util.List<com.liferay.opensocial.model.OAuthConsumer> getOAuthConsumers(
		long gadgetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOAuthConsumers(gadgetId);
	}

	public static java.util.List<com.liferay.opensocial.model.OAuthConsumer> getOAuthConsumers(
		long gadgetId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOAuthConsumers(gadgetId, start, end);
	}

	public static int getOAuthConsumersCount(long gadgetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOAuthConsumersCount(gadgetId);
	}

	public static com.liferay.opensocial.model.OAuthConsumer updateOAuthConsumer(
		long oAuthConsumerId, java.lang.String consumerKey,
		java.lang.String consumerSecret, java.lang.String keyType,
		java.lang.String keyName, java.lang.String callbackURL)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateOAuthConsumer(oAuthConsumerId, consumerKey,
			consumerSecret, keyType, keyName, callbackURL);
	}

	public static void clearService() {
		_service = null;
	}

	public static OAuthConsumerLocalService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					OAuthConsumerLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					OAuthConsumerLocalService.class.getName(),
					portletClassLoader);

			_service = new OAuthConsumerLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(OAuthConsumerLocalServiceUtil.class,
				"_service");
			MethodCache.remove(OAuthConsumerLocalService.class);
		}

		return _service;
	}

	public void setService(OAuthConsumerLocalService service) {
		MethodCache.remove(OAuthConsumerLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(OAuthConsumerLocalServiceUtil.class,
			"_service");
		MethodCache.remove(OAuthConsumerLocalService.class);
	}

	private static OAuthConsumerLocalService _service;
}