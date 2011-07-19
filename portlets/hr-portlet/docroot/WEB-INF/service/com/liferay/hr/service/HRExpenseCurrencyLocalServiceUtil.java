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

package com.liferay.hr.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the h r expense currency local service. This utility wraps {@link com.liferay.hr.service.impl.HRExpenseCurrencyLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Wesley Gong
 * @see HRExpenseCurrencyLocalService
 * @see com.liferay.hr.service.base.HRExpenseCurrencyLocalServiceBaseImpl
 * @see com.liferay.hr.service.impl.HRExpenseCurrencyLocalServiceImpl
 * @generated
 */
public class HRExpenseCurrencyLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.hr.service.impl.HRExpenseCurrencyLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the h r expense currency to the database. Also notifies the appropriate model listeners.
	*
	* @param hrExpenseCurrency the h r expense currency
	* @return the h r expense currency that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseCurrency addHRExpenseCurrency(
		com.liferay.hr.model.HRExpenseCurrency hrExpenseCurrency)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addHRExpenseCurrency(hrExpenseCurrency);
	}

	/**
	* Creates a new h r expense currency with the primary key. Does not add the h r expense currency to the database.
	*
	* @param hrExpenseCurrencyId the primary key for the new h r expense currency
	* @return the new h r expense currency
	*/
	public static com.liferay.hr.model.HRExpenseCurrency createHRExpenseCurrency(
		long hrExpenseCurrencyId) {
		return getService().createHRExpenseCurrency(hrExpenseCurrencyId);
	}

	/**
	* Deletes the h r expense currency with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrExpenseCurrencyId the primary key of the h r expense currency
	* @throws PortalException if a h r expense currency with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteHRExpenseCurrency(long hrExpenseCurrencyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteHRExpenseCurrency(hrExpenseCurrencyId);
	}

	/**
	* Deletes the h r expense currency from the database. Also notifies the appropriate model listeners.
	*
	* @param hrExpenseCurrency the h r expense currency
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteHRExpenseCurrency(
		com.liferay.hr.model.HRExpenseCurrency hrExpenseCurrency)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteHRExpenseCurrency(hrExpenseCurrency);
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

	/**
	* Returns the h r expense currency with the primary key.
	*
	* @param hrExpenseCurrencyId the primary key of the h r expense currency
	* @return the h r expense currency
	* @throws PortalException if a h r expense currency with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseCurrency getHRExpenseCurrency(
		long hrExpenseCurrencyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getHRExpenseCurrency(hrExpenseCurrencyId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the h r expense currencies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r expense currencies
	* @param end the upper bound of the range of h r expense currencies (not inclusive)
	* @return the range of h r expense currencies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRExpenseCurrency> getHRExpenseCurrencies(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getHRExpenseCurrencies(start, end);
	}

	/**
	* Returns the number of h r expense currencies.
	*
	* @return the number of h r expense currencies
	* @throws SystemException if a system exception occurred
	*/
	public static int getHRExpenseCurrenciesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getHRExpenseCurrenciesCount();
	}

	/**
	* Updates the h r expense currency in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param hrExpenseCurrency the h r expense currency
	* @return the h r expense currency that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseCurrency updateHRExpenseCurrency(
		com.liferay.hr.model.HRExpenseCurrency hrExpenseCurrency)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateHRExpenseCurrency(hrExpenseCurrency);
	}

	/**
	* Updates the h r expense currency in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param hrExpenseCurrency the h r expense currency
	* @param merge whether to merge the h r expense currency with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the h r expense currency that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseCurrency updateHRExpenseCurrency(
		com.liferay.hr.model.HRExpenseCurrency hrExpenseCurrency, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateHRExpenseCurrency(hrExpenseCurrency, merge);
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

	public static void clearService() {
		_service = null;
	}

	public static HRExpenseCurrencyLocalService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					HRExpenseCurrencyLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					HRExpenseCurrencyLocalService.class.getName(),
					portletClassLoader);

			_service = new HRExpenseCurrencyLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(HRExpenseCurrencyLocalServiceUtil.class,
				"_service");
			MethodCache.remove(HRExpenseCurrencyLocalService.class);
		}

		return _service;
	}

	public void setService(HRExpenseCurrencyLocalService service) {
		MethodCache.remove(HRExpenseCurrencyLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(HRExpenseCurrencyLocalServiceUtil.class,
			"_service");
		MethodCache.remove(HRExpenseCurrencyLocalService.class);
	}

	private static HRExpenseCurrencyLocalService _service;
}