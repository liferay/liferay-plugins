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

package com.liferay.wsrp.model;

/**
 * <p>
 * This class is a wrapper for {@link WSRPConsumerPortlet}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WSRPConsumerPortlet
 * @generated
 */
public class WSRPConsumerPortletWrapper implements WSRPConsumerPortlet {
	public WSRPConsumerPortletWrapper(WSRPConsumerPortlet wsrpConsumerPortlet) {
		_wsrpConsumerPortlet = wsrpConsumerPortlet;
	}

	public Class<?> getModelClass() {
		return WSRPConsumerPortlet.class;
	}

	public String getModelClassName() {
		return WSRPConsumerPortlet.class.getName();
	}

	/**
	* Returns the primary key of this w s r p consumer portlet.
	*
	* @return the primary key of this w s r p consumer portlet
	*/
	public long getPrimaryKey() {
		return _wsrpConsumerPortlet.getPrimaryKey();
	}

	/**
	* Sets the primary key of this w s r p consumer portlet.
	*
	* @param primaryKey the primary key of this w s r p consumer portlet
	*/
	public void setPrimaryKey(long primaryKey) {
		_wsrpConsumerPortlet.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this w s r p consumer portlet.
	*
	* @return the uuid of this w s r p consumer portlet
	*/
	public java.lang.String getUuid() {
		return _wsrpConsumerPortlet.getUuid();
	}

	/**
	* Sets the uuid of this w s r p consumer portlet.
	*
	* @param uuid the uuid of this w s r p consumer portlet
	*/
	public void setUuid(java.lang.String uuid) {
		_wsrpConsumerPortlet.setUuid(uuid);
	}

	/**
	* Returns the wsrp consumer portlet ID of this w s r p consumer portlet.
	*
	* @return the wsrp consumer portlet ID of this w s r p consumer portlet
	*/
	public long getWsrpConsumerPortletId() {
		return _wsrpConsumerPortlet.getWsrpConsumerPortletId();
	}

	/**
	* Sets the wsrp consumer portlet ID of this w s r p consumer portlet.
	*
	* @param wsrpConsumerPortletId the wsrp consumer portlet ID of this w s r p consumer portlet
	*/
	public void setWsrpConsumerPortletId(long wsrpConsumerPortletId) {
		_wsrpConsumerPortlet.setWsrpConsumerPortletId(wsrpConsumerPortletId);
	}

	/**
	* Returns the company ID of this w s r p consumer portlet.
	*
	* @return the company ID of this w s r p consumer portlet
	*/
	public long getCompanyId() {
		return _wsrpConsumerPortlet.getCompanyId();
	}

	/**
	* Sets the company ID of this w s r p consumer portlet.
	*
	* @param companyId the company ID of this w s r p consumer portlet
	*/
	public void setCompanyId(long companyId) {
		_wsrpConsumerPortlet.setCompanyId(companyId);
	}

	/**
	* Returns the create date of this w s r p consumer portlet.
	*
	* @return the create date of this w s r p consumer portlet
	*/
	public java.util.Date getCreateDate() {
		return _wsrpConsumerPortlet.getCreateDate();
	}

	/**
	* Sets the create date of this w s r p consumer portlet.
	*
	* @param createDate the create date of this w s r p consumer portlet
	*/
	public void setCreateDate(java.util.Date createDate) {
		_wsrpConsumerPortlet.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this w s r p consumer portlet.
	*
	* @return the modified date of this w s r p consumer portlet
	*/
	public java.util.Date getModifiedDate() {
		return _wsrpConsumerPortlet.getModifiedDate();
	}

	/**
	* Sets the modified date of this w s r p consumer portlet.
	*
	* @param modifiedDate the modified date of this w s r p consumer portlet
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_wsrpConsumerPortlet.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the wsrp consumer ID of this w s r p consumer portlet.
	*
	* @return the wsrp consumer ID of this w s r p consumer portlet
	*/
	public long getWsrpConsumerId() {
		return _wsrpConsumerPortlet.getWsrpConsumerId();
	}

	/**
	* Sets the wsrp consumer ID of this w s r p consumer portlet.
	*
	* @param wsrpConsumerId the wsrp consumer ID of this w s r p consumer portlet
	*/
	public void setWsrpConsumerId(long wsrpConsumerId) {
		_wsrpConsumerPortlet.setWsrpConsumerId(wsrpConsumerId);
	}

	/**
	* Returns the name of this w s r p consumer portlet.
	*
	* @return the name of this w s r p consumer portlet
	*/
	public java.lang.String getName() {
		return _wsrpConsumerPortlet.getName();
	}

	/**
	* Sets the name of this w s r p consumer portlet.
	*
	* @param name the name of this w s r p consumer portlet
	*/
	public void setName(java.lang.String name) {
		_wsrpConsumerPortlet.setName(name);
	}

	/**
	* Returns the portlet handle of this w s r p consumer portlet.
	*
	* @return the portlet handle of this w s r p consumer portlet
	*/
	public java.lang.String getPortletHandle() {
		return _wsrpConsumerPortlet.getPortletHandle();
	}

	/**
	* Sets the portlet handle of this w s r p consumer portlet.
	*
	* @param portletHandle the portlet handle of this w s r p consumer portlet
	*/
	public void setPortletHandle(java.lang.String portletHandle) {
		_wsrpConsumerPortlet.setPortletHandle(portletHandle);
	}

	public boolean isNew() {
		return _wsrpConsumerPortlet.isNew();
	}

	public void setNew(boolean n) {
		_wsrpConsumerPortlet.setNew(n);
	}

	public boolean isCachedModel() {
		return _wsrpConsumerPortlet.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_wsrpConsumerPortlet.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _wsrpConsumerPortlet.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_wsrpConsumerPortlet.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _wsrpConsumerPortlet.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_wsrpConsumerPortlet.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _wsrpConsumerPortlet.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_wsrpConsumerPortlet.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new WSRPConsumerPortletWrapper((WSRPConsumerPortlet)_wsrpConsumerPortlet.clone());
	}

	public int compareTo(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet) {
		return _wsrpConsumerPortlet.compareTo(wsrpConsumerPortlet);
	}

	@Override
	public int hashCode() {
		return _wsrpConsumerPortlet.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.wsrp.model.WSRPConsumerPortlet> toCacheModel() {
		return _wsrpConsumerPortlet.toCacheModel();
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet toEscapedModel() {
		return new WSRPConsumerPortletWrapper(_wsrpConsumerPortlet.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _wsrpConsumerPortlet.toString();
	}

	public java.lang.String toXmlString() {
		return _wsrpConsumerPortlet.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_wsrpConsumerPortlet.persist();
	}

	public WSRPConsumerPortlet getWrappedWSRPConsumerPortlet() {
		return _wsrpConsumerPortlet;
	}

	public void resetOriginalValues() {
		_wsrpConsumerPortlet.resetOriginalValues();
	}

	private WSRPConsumerPortlet _wsrpConsumerPortlet;
}