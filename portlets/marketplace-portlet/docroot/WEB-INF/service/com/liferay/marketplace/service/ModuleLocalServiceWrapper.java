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

package com.liferay.marketplace.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ModuleLocalService}.
 *
 * @author Ryan Park
 * @see ModuleLocalService
 * @generated
 */
public class ModuleLocalServiceWrapper implements ModuleLocalService,
	ServiceWrapper<ModuleLocalService> {
	public ModuleLocalServiceWrapper(ModuleLocalService moduleLocalService) {
		_moduleLocalService = moduleLocalService;
	}

	/**
	* Adds the module to the database. Also notifies the appropriate model listeners.
	*
	* @param module the module
	* @return the module that was added
	*/
	@Override
	public com.liferay.marketplace.model.Module addModule(
		com.liferay.marketplace.model.Module module) {
		return _moduleLocalService.addModule(module);
	}

	@Override
	public com.liferay.marketplace.model.Module addModule(long userId,
		long appId, java.lang.String contextName) {
		return _moduleLocalService.addModule(userId, appId, contextName);
	}

	/**
	* Creates a new module with the primary key. Does not add the module to the database.
	*
	* @param moduleId the primary key for the new module
	* @return the new module
	*/
	@Override
	public com.liferay.marketplace.model.Module createModule(long moduleId) {
		return _moduleLocalService.createModule(moduleId);
	}

	/**
	* Deletes the module from the database. Also notifies the appropriate model listeners.
	*
	* @param module the module
	* @return the module that was removed
	*/
	@Override
	public com.liferay.marketplace.model.Module deleteModule(
		com.liferay.marketplace.model.Module module) {
		return _moduleLocalService.deleteModule(module);
	}

	/**
	* Deletes the module with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param moduleId the primary key of the module
	* @return the module that was removed
	* @throws PortalException if a module with the primary key could not be found
	*/
	@Override
	public com.liferay.marketplace.model.Module deleteModule(long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _moduleLocalService.deleteModule(moduleId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _moduleLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _moduleLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _moduleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _moduleLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _moduleLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _moduleLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _moduleLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.marketplace.model.Module fetchModule(long appId,
		java.lang.String contextName) {
		return _moduleLocalService.fetchModule(appId, contextName);
	}

	@Override
	public com.liferay.marketplace.model.Module fetchModule(long moduleId) {
		return _moduleLocalService.fetchModule(moduleId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _moduleLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _moduleLocalService.getBeanIdentifier();
	}

	/**
	* Returns the module with the primary key.
	*
	* @param moduleId the primary key of the module
	* @return the module
	* @throws PortalException if a module with the primary key could not be found
	*/
	@Override
	public com.liferay.marketplace.model.Module getModule(long moduleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _moduleLocalService.getModule(moduleId);
	}

	@Override
	public java.util.List<com.liferay.marketplace.model.Module> getModules(
		long appId) {
		return _moduleLocalService.getModules(appId);
	}

	/**
	* Returns a range of all the modules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.marketplace.model.impl.ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of modules
	* @param end the upper bound of the range of modules (not inclusive)
	* @return the range of modules
	*/
	@Override
	public java.util.List<com.liferay.marketplace.model.Module> getModules(
		int start, int end) {
		return _moduleLocalService.getModules(start, end);
	}

	/**
	* Returns the number of modules.
	*
	* @return the number of modules
	*/
	@Override
	public int getModulesCount() {
		return _moduleLocalService.getModulesCount();
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _moduleLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _moduleLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_moduleLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	* Updates the module in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param module the module
	* @return the module that was updated
	*/
	@Override
	public com.liferay.marketplace.model.Module updateModule(
		com.liferay.marketplace.model.Module module) {
		return _moduleLocalService.updateModule(module);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public ModuleLocalService getWrappedModuleLocalService() {
		return _moduleLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedModuleLocalService(
		ModuleLocalService moduleLocalService) {
		_moduleLocalService = moduleLocalService;
	}

	@Override
	public ModuleLocalService getWrappedService() {
		return _moduleLocalService;
	}

	@Override
	public void setWrappedService(ModuleLocalService moduleLocalService) {
		_moduleLocalService = moduleLocalService;
	}

	private ModuleLocalService _moduleLocalService;
}