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

package com.liferay.opensocial.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link Gadget}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Gadget
 * @generated
 */
public class GadgetWrapper implements Gadget, ModelWrapper<Gadget> {
	public GadgetWrapper(Gadget gadget) {
		_gadget = gadget;
	}

	public Class<?> getModelClass() {
		return Gadget.class;
	}

	public String getModelClassName() {
		return Gadget.class.getName();
	}

	/**
	* Returns the primary key of this gadget.
	*
	* @return the primary key of this gadget
	*/
	public long getPrimaryKey() {
		return _gadget.getPrimaryKey();
	}

	/**
	* Sets the primary key of this gadget.
	*
	* @param primaryKey the primary key of this gadget
	*/
	public void setPrimaryKey(long primaryKey) {
		_gadget.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this gadget.
	*
	* @return the uuid of this gadget
	*/
	public java.lang.String getUuid() {
		return _gadget.getUuid();
	}

	/**
	* Sets the uuid of this gadget.
	*
	* @param uuid the uuid of this gadget
	*/
	public void setUuid(java.lang.String uuid) {
		_gadget.setUuid(uuid);
	}

	/**
	* Returns the gadget ID of this gadget.
	*
	* @return the gadget ID of this gadget
	*/
	public long getGadgetId() {
		return _gadget.getGadgetId();
	}

	/**
	* Sets the gadget ID of this gadget.
	*
	* @param gadgetId the gadget ID of this gadget
	*/
	public void setGadgetId(long gadgetId) {
		_gadget.setGadgetId(gadgetId);
	}

	/**
	* Returns the company ID of this gadget.
	*
	* @return the company ID of this gadget
	*/
	public long getCompanyId() {
		return _gadget.getCompanyId();
	}

	/**
	* Sets the company ID of this gadget.
	*
	* @param companyId the company ID of this gadget
	*/
	public void setCompanyId(long companyId) {
		_gadget.setCompanyId(companyId);
	}

	/**
	* Returns the create date of this gadget.
	*
	* @return the create date of this gadget
	*/
	public java.util.Date getCreateDate() {
		return _gadget.getCreateDate();
	}

	/**
	* Sets the create date of this gadget.
	*
	* @param createDate the create date of this gadget
	*/
	public void setCreateDate(java.util.Date createDate) {
		_gadget.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this gadget.
	*
	* @return the modified date of this gadget
	*/
	public java.util.Date getModifiedDate() {
		return _gadget.getModifiedDate();
	}

	/**
	* Sets the modified date of this gadget.
	*
	* @param modifiedDate the modified date of this gadget
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_gadget.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this gadget.
	*
	* @return the name of this gadget
	*/
	public java.lang.String getName() {
		return _gadget.getName();
	}

	/**
	* Sets the name of this gadget.
	*
	* @param name the name of this gadget
	*/
	public void setName(java.lang.String name) {
		_gadget.setName(name);
	}

	/**
	* Returns the url of this gadget.
	*
	* @return the url of this gadget
	*/
	public java.lang.String getUrl() {
		return _gadget.getUrl();
	}

	/**
	* Sets the url of this gadget.
	*
	* @param url the url of this gadget
	*/
	public void setUrl(java.lang.String url) {
		_gadget.setUrl(url);
	}

	/**
	* Returns the portlet category names of this gadget.
	*
	* @return the portlet category names of this gadget
	*/
	public java.lang.String getPortletCategoryNames() {
		return _gadget.getPortletCategoryNames();
	}

	/**
	* Sets the portlet category names of this gadget.
	*
	* @param portletCategoryNames the portlet category names of this gadget
	*/
	public void setPortletCategoryNames(java.lang.String portletCategoryNames) {
		_gadget.setPortletCategoryNames(portletCategoryNames);
	}

	public boolean isNew() {
		return _gadget.isNew();
	}

	public void setNew(boolean n) {
		_gadget.setNew(n);
	}

	public boolean isCachedModel() {
		return _gadget.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_gadget.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _gadget.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _gadget.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_gadget.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _gadget.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_gadget.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new GadgetWrapper((Gadget)_gadget.clone());
	}

	public int compareTo(com.liferay.opensocial.model.Gadget gadget) {
		return _gadget.compareTo(gadget);
	}

	@Override
	public int hashCode() {
		return _gadget.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.opensocial.model.Gadget> toCacheModel() {
		return _gadget.toCacheModel();
	}

	public com.liferay.opensocial.model.Gadget toEscapedModel() {
		return new GadgetWrapper(_gadget.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _gadget.toString();
	}

	public java.lang.String toXmlString() {
		return _gadget.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_gadget.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Gadget getWrappedGadget() {
		return _gadget;
	}

	public Gadget getWrappedModel() {
		return _gadget;
	}

	public void resetOriginalValues() {
		_gadget.resetOriginalValues();
	}

	private Gadget _gadget;
}