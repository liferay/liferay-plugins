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

package com.liferay.opensocial.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import com.liferay.portlet.exportimport.lar.StagedModelType;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Gadget}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Gadget
 * @generated
 */
@ProviderType
public class GadgetWrapper implements Gadget, ModelWrapper<Gadget> {
	public GadgetWrapper(Gadget gadget) {
		_gadget = gadget;
	}

	@Override
	public Class<?> getModelClass() {
		return Gadget.class;
	}

	@Override
	public String getModelClassName() {
		return Gadget.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("gadgetId", getGadgetId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("url", getUrl());
		attributes.put("portletCategoryNames", getPortletCategoryNames());
		attributes.put("lastPublishDate", getLastPublishDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long gadgetId = (Long)attributes.get("gadgetId");

		if (gadgetId != null) {
			setGadgetId(gadgetId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		String portletCategoryNames = (String)attributes.get(
				"portletCategoryNames");

		if (portletCategoryNames != null) {
			setPortletCategoryNames(portletCategoryNames);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new GadgetWrapper((Gadget)_gadget.clone());
	}

	@Override
	public int compareTo(com.liferay.opensocial.model.Gadget gadget) {
		return _gadget.compareTo(gadget);
	}

	/**
	* Returns the company ID of this gadget.
	*
	* @return the company ID of this gadget
	*/
	@Override
	public long getCompanyId() {
		return _gadget.getCompanyId();
	}

	/**
	* Returns the create date of this gadget.
	*
	* @return the create date of this gadget
	*/
	@Override
	public Date getCreateDate() {
		return _gadget.getCreateDate();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _gadget.getExpandoBridge();
	}

	/**
	* Returns the gadget ID of this gadget.
	*
	* @return the gadget ID of this gadget
	*/
	@Override
	public long getGadgetId() {
		return _gadget.getGadgetId();
	}

	/**
	* Returns the last publish date of this gadget.
	*
	* @return the last publish date of this gadget
	*/
	@Override
	public Date getLastPublishDate() {
		return _gadget.getLastPublishDate();
	}

	/**
	* Returns the modified date of this gadget.
	*
	* @return the modified date of this gadget
	*/
	@Override
	public Date getModifiedDate() {
		return _gadget.getModifiedDate();
	}

	/**
	* Returns the name of this gadget.
	*
	* @return the name of this gadget
	*/
	@Override
	public java.lang.String getName() {
		return _gadget.getName();
	}

	/**
	* Returns the portlet category names of this gadget.
	*
	* @return the portlet category names of this gadget
	*/
	@Override
	public java.lang.String getPortletCategoryNames() {
		return _gadget.getPortletCategoryNames();
	}

	/**
	* Returns the primary key of this gadget.
	*
	* @return the primary key of this gadget
	*/
	@Override
	public long getPrimaryKey() {
		return _gadget.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _gadget.getPrimaryKeyObj();
	}

	/**
	* Returns the url of this gadget.
	*
	* @return the url of this gadget
	*/
	@Override
	public java.lang.String getUrl() {
		return _gadget.getUrl();
	}

	/**
	* Returns the uuid of this gadget.
	*
	* @return the uuid of this gadget
	*/
	@Override
	public java.lang.String getUuid() {
		return _gadget.getUuid();
	}

	@Override
	public int hashCode() {
		return _gadget.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _gadget.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _gadget.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _gadget.isNew();
	}

	@Override
	public void persist() {
		_gadget.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_gadget.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this gadget.
	*
	* @param companyId the company ID of this gadget
	*/
	@Override
	public void setCompanyId(long companyId) {
		_gadget.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this gadget.
	*
	* @param createDate the create date of this gadget
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_gadget.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_gadget.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_gadget.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_gadget.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gadget ID of this gadget.
	*
	* @param gadgetId the gadget ID of this gadget
	*/
	@Override
	public void setGadgetId(long gadgetId) {
		_gadget.setGadgetId(gadgetId);
	}

	/**
	* Sets the last publish date of this gadget.
	*
	* @param lastPublishDate the last publish date of this gadget
	*/
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_gadget.setLastPublishDate(lastPublishDate);
	}

	/**
	* Sets the modified date of this gadget.
	*
	* @param modifiedDate the modified date of this gadget
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_gadget.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this gadget.
	*
	* @param name the name of this gadget
	*/
	@Override
	public void setName(java.lang.String name) {
		_gadget.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_gadget.setNew(n);
	}

	/**
	* Sets the portlet category names of this gadget.
	*
	* @param portletCategoryNames the portlet category names of this gadget
	*/
	@Override
	public void setPortletCategoryNames(java.lang.String portletCategoryNames) {
		_gadget.setPortletCategoryNames(portletCategoryNames);
	}

	/**
	* Sets the primary key of this gadget.
	*
	* @param primaryKey the primary key of this gadget
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_gadget.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_gadget.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the url of this gadget.
	*
	* @param url the url of this gadget
	*/
	@Override
	public void setUrl(java.lang.String url) {
		_gadget.setUrl(url);
	}

	/**
	* Sets the uuid of this gadget.
	*
	* @param uuid the uuid of this gadget
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_gadget.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.opensocial.model.Gadget> toCacheModel() {
		return _gadget.toCacheModel();
	}

	@Override
	public com.liferay.opensocial.model.Gadget toEscapedModel() {
		return new GadgetWrapper(_gadget.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _gadget.toString();
	}

	@Override
	public com.liferay.opensocial.model.Gadget toUnescapedModel() {
		return new GadgetWrapper(_gadget.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _gadget.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GadgetWrapper)) {
			return false;
		}

		GadgetWrapper gadgetWrapper = (GadgetWrapper)obj;

		if (Validator.equals(_gadget, gadgetWrapper._gadget)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _gadget.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public Gadget getWrappedGadget() {
		return _gadget;
	}

	@Override
	public Gadget getWrappedModel() {
		return _gadget;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _gadget.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _gadget.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_gadget.resetOriginalValues();
	}

	private final Gadget _gadget;
}