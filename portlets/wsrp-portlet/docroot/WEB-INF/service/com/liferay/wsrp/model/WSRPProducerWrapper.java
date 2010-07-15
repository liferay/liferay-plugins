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
 * This class is a wrapper for {@link WSRPProducer}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WSRPProducer
 * @generated
 */
public class WSRPProducerWrapper implements WSRPProducer {
	public WSRPProducerWrapper(WSRPProducer wsrpProducer) {
		_wsrpProducer = wsrpProducer;
	}

	public long getPrimaryKey() {
		return _wsrpProducer.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_wsrpProducer.setPrimaryKey(pk);
	}

	public long getWsrpProducerId() {
		return _wsrpProducer.getWsrpProducerId();
	}

	public void setWsrpProducerId(long wsrpProducerId) {
		_wsrpProducer.setWsrpProducerId(wsrpProducerId);
	}

	public long getGroupId() {
		return _wsrpProducer.getGroupId();
	}

	public void setGroupId(long groupId) {
		_wsrpProducer.setGroupId(groupId);
	}

	public long getCompanyId() {
		return _wsrpProducer.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_wsrpProducer.setCompanyId(companyId);
	}

	public java.util.Date getCreateDate() {
		return _wsrpProducer.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_wsrpProducer.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _wsrpProducer.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_wsrpProducer.setModifiedDate(modifiedDate);
	}

	public java.lang.String getName() {
		return _wsrpProducer.getName();
	}

	public void setName(java.lang.String name) {
		_wsrpProducer.setName(name);
	}

	public java.lang.String getPortletIds() {
		return _wsrpProducer.getPortletIds();
	}

	public void setPortletIds(java.lang.String portletIds) {
		_wsrpProducer.setPortletIds(portletIds);
	}

	public com.liferay.wsrp.model.WSRPProducer toEscapedModel() {
		return _wsrpProducer.toEscapedModel();
	}

	public boolean isNew() {
		return _wsrpProducer.isNew();
	}

	public void setNew(boolean n) {
		_wsrpProducer.setNew(n);
	}

	public boolean isCachedModel() {
		return _wsrpProducer.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_wsrpProducer.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _wsrpProducer.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_wsrpProducer.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _wsrpProducer.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _wsrpProducer.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_wsrpProducer.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _wsrpProducer.clone();
	}

	public int compareTo(com.liferay.wsrp.model.WSRPProducer wsrpProducer) {
		return _wsrpProducer.compareTo(wsrpProducer);
	}

	public int hashCode() {
		return _wsrpProducer.hashCode();
	}

	public java.lang.String toString() {
		return _wsrpProducer.toString();
	}

	public java.lang.String toXmlString() {
		return _wsrpProducer.toXmlString();
	}

	public WSRPProducer getWrappedWSRPProducer() {
		return _wsrpProducer;
	}

	private WSRPProducer _wsrpProducer;
}