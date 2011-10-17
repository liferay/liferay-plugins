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

package com.liferay.marketplace.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link Module}.
 * </p>
 *
 * @author    Ryan Park
 * @see       Module
 * @generated
 */
public class ModuleWrapper implements Module, ModelWrapper<Module> {
	public ModuleWrapper(Module module) {
		_module = module;
	}

	public Class<?> getModelClass() {
		return Module.class;
	}

	public String getModelClassName() {
		return Module.class.getName();
	}

	/**
	* Returns the primary key of this module.
	*
	* @return the primary key of this module
	*/
	public long getPrimaryKey() {
		return _module.getPrimaryKey();
	}

	/**
	* Sets the primary key of this module.
	*
	* @param primaryKey the primary key of this module
	*/
	public void setPrimaryKey(long primaryKey) {
		_module.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this module.
	*
	* @return the uuid of this module
	*/
	public java.lang.String getUuid() {
		return _module.getUuid();
	}

	/**
	* Sets the uuid of this module.
	*
	* @param uuid the uuid of this module
	*/
	public void setUuid(java.lang.String uuid) {
		_module.setUuid(uuid);
	}

	/**
	* Returns the module ID of this module.
	*
	* @return the module ID of this module
	*/
	public long getModuleId() {
		return _module.getModuleId();
	}

	/**
	* Sets the module ID of this module.
	*
	* @param moduleId the module ID of this module
	*/
	public void setModuleId(long moduleId) {
		_module.setModuleId(moduleId);
	}

	/**
	* Returns the app ID of this module.
	*
	* @return the app ID of this module
	*/
	public long getAppId() {
		return _module.getAppId();
	}

	/**
	* Sets the app ID of this module.
	*
	* @param appId the app ID of this module
	*/
	public void setAppId(long appId) {
		_module.setAppId(appId);
	}

	/**
	* Returns the context name of this module.
	*
	* @return the context name of this module
	*/
	public java.lang.String getContextName() {
		return _module.getContextName();
	}

	/**
	* Sets the context name of this module.
	*
	* @param contextName the context name of this module
	*/
	public void setContextName(java.lang.String contextName) {
		_module.setContextName(contextName);
	}

	public boolean isNew() {
		return _module.isNew();
	}

	public void setNew(boolean n) {
		_module.setNew(n);
	}

	public boolean isCachedModel() {
		return _module.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_module.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _module.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _module.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_module.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _module.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_module.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ModuleWrapper((Module)_module.clone());
	}

	public int compareTo(com.liferay.marketplace.model.Module module) {
		return _module.compareTo(module);
	}

	@Override
	public int hashCode() {
		return _module.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.marketplace.model.Module> toCacheModel() {
		return _module.toCacheModel();
	}

	public com.liferay.marketplace.model.Module toEscapedModel() {
		return new ModuleWrapper(_module.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _module.toString();
	}

	public java.lang.String toXmlString() {
		return _module.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_module.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Module getWrappedModule() {
		return _module;
	}

	public Module getWrappedModel() {
		return _module;
	}

	public void resetOriginalValues() {
		_module.resetOriginalValues();
	}

	private Module _module;
}