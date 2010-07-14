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

package com.liferay.wsrp.model;


/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
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

	public long getPrimaryKey() {
		return _wsrpConsumerPortlet.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_wsrpConsumerPortlet.setPrimaryKey(pk);
	}

	public long getWsrpConsumerPortletId() {
		return _wsrpConsumerPortlet.getWsrpConsumerPortletId();
	}

	public void setWsrpConsumerPortletId(long wsrpConsumerPortletId) {
		_wsrpConsumerPortlet.setWsrpConsumerPortletId(wsrpConsumerPortletId);
	}

	public long getCompanyId() {
		return _wsrpConsumerPortlet.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_wsrpConsumerPortlet.setCompanyId(companyId);
	}

	public java.util.Date getCreateDate() {
		return _wsrpConsumerPortlet.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_wsrpConsumerPortlet.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _wsrpConsumerPortlet.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_wsrpConsumerPortlet.setModifiedDate(modifiedDate);
	}

	public long getWsrpConsumerId() {
		return _wsrpConsumerPortlet.getWsrpConsumerId();
	}

	public void setWsrpConsumerId(long wsrpConsumerId) {
		_wsrpConsumerPortlet.setWsrpConsumerId(wsrpConsumerId);
	}

	public java.lang.String getName() {
		return _wsrpConsumerPortlet.getName();
	}

	public void setName(java.lang.String name) {
		_wsrpConsumerPortlet.setName(name);
	}

	public java.lang.String getPortletHandle() {
		return _wsrpConsumerPortlet.getPortletHandle();
	}

	public void setPortletHandle(java.lang.String portletHandle) {
		_wsrpConsumerPortlet.setPortletHandle(portletHandle);
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet toEscapedModel() {
		return _wsrpConsumerPortlet.toEscapedModel();
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

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _wsrpConsumerPortlet.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_wsrpConsumerPortlet.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _wsrpConsumerPortlet.clone();
	}

	public int compareTo(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet) {
		return _wsrpConsumerPortlet.compareTo(wsrpConsumerPortlet);
	}

	public int hashCode() {
		return _wsrpConsumerPortlet.hashCode();
	}

	public java.lang.String toString() {
		return _wsrpConsumerPortlet.toString();
	}

	public java.lang.String toXmlString() {
		return _wsrpConsumerPortlet.toXmlString();
	}

	public WSRPConsumerPortlet getWrappedWSRPConsumerPortlet() {
		return _wsrpConsumerPortlet;
	}

	private WSRPConsumerPortlet _wsrpConsumerPortlet;
}