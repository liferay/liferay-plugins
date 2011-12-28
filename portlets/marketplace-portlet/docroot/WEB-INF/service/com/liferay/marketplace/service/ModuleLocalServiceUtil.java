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
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the module local service. This utility wraps {@link com.liferay.marketplace.service.impl.ModuleLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Ryan Park
 * @see ModuleLocalService
 * @see com.liferay.marketplace.service.base.ModuleLocalServiceBaseImpl
 * @see com.liferay.marketplace.service.impl.ModuleLocalServiceImpl
 * @generated
 */
public class ModuleLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.marketplace.service.impl.ModuleLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the module to the database. Also notifies the appropriate model listeners.
	*
	* @param module the module
	* @return the module that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module addModule(
		com.liferay.marketplace.model.Module module)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addModule(module);
	}

	/**
	* Creates a new module with the primary key. Does not add the module to the database.
	*
	* @param moduleId the primary key for the new module
	* @return the new module
	*/
	public static com.liferay.marketplace.model.Module createModule(
		long moduleId) {
		return getService().createModule(moduleId);
	}

	/**
	* Deletes the module with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param moduleId the primary key of the module
	* @throws PortalException if a module with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteModule(long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteModule(moduleId);
	}

	/**
	* Deletes the module from the database. Also notifies the appropriate model listeners.
	*
	* @param module the module
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteModule(com.liferay.marketplace.model.Module module)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteModule(module);
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

	public static com.liferay.marketplace.model.Module fetchModule(
		long moduleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchModule(moduleId);
	}

	/**
	* Returns the module with the primary key.
	*
	* @param moduleId the primary key of the module
	* @return the module
	* @throws PortalException if a module with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module getModule(long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getModule(moduleId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the modules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @return the range of modules
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.marketplace.model.Module> getModules(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getModules(start, end);
	}

	/**
	* Returns the number of modules.
	*
	* @return the number of modules
	* @throws SystemException if a system exception occurred
	*/
	public static int getModulesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getModulesCount();
	}

	/**
	* Updates the module in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param module the module
	* @return the module that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module updateModule(
		com.liferay.marketplace.model.Module module)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateModule(module);
	}

	/**
	* Updates the module in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param module the module
	* @param merge whether to merge the module with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the module that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.marketplace.model.Module updateModule(
		com.liferay.marketplace.model.Module module, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateModule(module, merge);
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

	public static com.liferay.marketplace.model.Module addModule(long userId,
		long appId, java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addModule(userId, appId, contextName);
	}

	public static com.liferay.marketplace.model.Module fetchModule(long appId,
		java.lang.String contextName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchModule(appId, contextName);
	}

	public static java.util.List<com.liferay.marketplace.model.Module> getModules(
		long appId) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getModules(appId);
	}

	public static void clearService() {
		_service = null;
	}

	public static ModuleLocalService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ModuleLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					ModuleLocalService.class.getName(), portletClassLoader);

			_service = new ModuleLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(ModuleLocalServiceUtil.class,
				"_service");
			MethodCache.remove(ModuleLocalService.class);
		}

		return _service;
	}

	public void setService(ModuleLocalService service) {
		MethodCache.remove(ModuleLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(ModuleLocalServiceUtil.class,
			"_service");
		MethodCache.remove(ModuleLocalService.class);
	}

	private static ModuleLocalService _service;
}