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

package com.liferay.ams.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * The utility for the definition local service. This utility wraps {@link com.liferay.ams.service.impl.DefinitionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * Never modify this class directly. Add custom service methods to {@link com.liferay.ams.service.impl.DefinitionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DefinitionLocalService
 * @see com.liferay.ams.service.base.DefinitionLocalServiceBaseImpl
 * @see com.liferay.ams.service.impl.DefinitionLocalServiceImpl
 * @generated
 */
public class DefinitionLocalServiceUtil {
	/**
	* Adds the definition to the database. Also notifies the appropriate model listeners.
	*
	* @param definition the definition to add
	* @return the definition that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.ams.model.Definition addDefinition(
		com.liferay.ams.model.Definition definition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addDefinition(definition);
	}

	/**
	* Creates a new definition with the primary key. Does not add the definition to the database.
	*
	* @param definitionId the primary key for the new definition
	* @return the new definition
	*/
	public static com.liferay.ams.model.Definition createDefinition(
		long definitionId) {
		return getService().createDefinition(definitionId);
	}

	/**
	* Deletes the definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param definitionId the primary key of the definition to delete
	* @throws PortalException if a definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDefinition(long definitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteDefinition(definitionId);
	}

	/**
	* Deletes the definition from the database. Also notifies the appropriate model listeners.
	*
	* @param definition the definition to delete
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDefinition(
		com.liferay.ams.model.Definition definition)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteDefinition(definition);
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
	* @param orderByComparator the comparator to order the results by
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
	* Gets the definition with the primary key.
	*
	* @param definitionId the primary key of the definition to get
	* @return the definition
	* @throws PortalException if a definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.ams.model.Definition getDefinition(
		long definitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getDefinition(definitionId);
	}

	/**
	* Gets a range of all the definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of definitions to return
	* @param end the upper bound of the range of definitions to return (not inclusive)
	* @return the range of definitions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.ams.model.Definition> getDefinitions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDefinitions(start, end);
	}

	/**
	* Gets the number of definitions.
	*
	* @return the number of definitions
	* @throws SystemException if a system exception occurred
	*/
	public static int getDefinitionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDefinitionsCount();
	}

	/**
	* Updates the definition in the database. Also notifies the appropriate model listeners.
	*
	* @param definition the definition to update
	* @return the definition that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.ams.model.Definition updateDefinition(
		com.liferay.ams.model.Definition definition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateDefinition(definition);
	}

	/**
	* Updates the definition in the database. Also notifies the appropriate model listeners.
	*
	* @param definition the definition to update
	* @param merge whether to merge the definition with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the definition that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.ams.model.Definition updateDefinition(
		com.liferay.ams.model.Definition definition, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateDefinition(definition, merge);
	}

	public static void clearService() {
		_service = null;
	}

	public static DefinitionLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					DefinitionLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new DefinitionLocalServiceClp(DefinitionLocalService.class.getName(),
					classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(DefinitionLocalService service) {
		_service = service;
	}

	private static DefinitionLocalService _service;
}