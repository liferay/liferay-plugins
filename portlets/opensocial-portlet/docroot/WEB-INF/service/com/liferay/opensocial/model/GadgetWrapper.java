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

package com.liferay.opensocial.model;

/**
 * <p>
 * This class is a wrapper for {@link Gadget}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Gadget
 * @generated
 */
public class GadgetWrapper implements Gadget {
	public GadgetWrapper(Gadget gadget) {
		_gadget = gadget;
	}

	/**
	* Gets the primary key of this gadget.
	*
	* @return the primary key of this gadget
	*/
	public long getPrimaryKey() {
		return _gadget.getPrimaryKey();
	}

	/**
	* Sets the primary key of this gadget
	*
	* @param pk the primary key of this gadget
	*/
	public void setPrimaryKey(long pk) {
		_gadget.setPrimaryKey(pk);
	}

	/**
	* Gets the gadget id of this gadget.
	*
	* @return the gadget id of this gadget
	*/
	public long getGadgetId() {
		return _gadget.getGadgetId();
	}

	/**
	* Sets the gadget id of this gadget.
	*
	* @param gadgetId the gadget id of this gadget
	*/
	public void setGadgetId(long gadgetId) {
		_gadget.setGadgetId(gadgetId);
	}

	/**
	* Gets the company id of this gadget.
	*
	* @return the company id of this gadget
	*/
	public long getCompanyId() {
		return _gadget.getCompanyId();
	}

	/**
	* Sets the company id of this gadget.
	*
	* @param companyId the company id of this gadget
	*/
	public void setCompanyId(long companyId) {
		_gadget.setCompanyId(companyId);
	}

	/**
	* Gets the create date of this gadget.
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
	* Gets the modified date of this gadget.
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
	* Gets the name of this gadget.
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
	* Gets the url of this gadget.
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

	public void setEscapedModel(boolean escapedModel) {
		_gadget.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _gadget.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _gadget.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_gadget.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _gadget.clone();
	}

	public int compareTo(com.liferay.opensocial.model.Gadget gadget) {
		return _gadget.compareTo(gadget);
	}

	public int hashCode() {
		return _gadget.hashCode();
	}

	public com.liferay.opensocial.model.Gadget toEscapedModel() {
		return _gadget.toEscapedModel();
	}

	public java.lang.String toString() {
		return _gadget.toString();
	}

	public java.lang.String toXmlString() {
		return _gadget.toXmlString();
	}

	public Gadget getWrappedGadget() {
		return _gadget;
	}

	private Gadget _gadget;
}