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
 * This class is a wrapper for {@link WSRPConsumer}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WSRPConsumer
 * @generated
 */
public class WSRPConsumerWrapper implements WSRPConsumer {
	public WSRPConsumerWrapper(WSRPConsumer wsrpConsumer) {
		_wsrpConsumer = wsrpConsumer;
	}

	public long getPrimaryKey() {
		return _wsrpConsumer.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_wsrpConsumer.setPrimaryKey(pk);
	}

	public long getWsrpConsumerId() {
		return _wsrpConsumer.getWsrpConsumerId();
	}

	public void setWsrpConsumerId(long wsrpConsumerId) {
		_wsrpConsumer.setWsrpConsumerId(wsrpConsumerId);
	}

	public long getCompanyId() {
		return _wsrpConsumer.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_wsrpConsumer.setCompanyId(companyId);
	}

	public java.util.Date getCreateDate() {
		return _wsrpConsumer.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_wsrpConsumer.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _wsrpConsumer.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_wsrpConsumer.setModifiedDate(modifiedDate);
	}

	public java.lang.String getName() {
		return _wsrpConsumer.getName();
	}

	public void setName(java.lang.String name) {
		_wsrpConsumer.setName(name);
	}

	public java.lang.String getUrl() {
		return _wsrpConsumer.getUrl();
	}

	public void setUrl(java.lang.String url) {
		_wsrpConsumer.setUrl(url);
	}

	public java.lang.String getWsdl() {
		return _wsrpConsumer.getWsdl();
	}

	public void setWsdl(java.lang.String wsdl) {
		_wsrpConsumer.setWsdl(wsdl);
	}

	public java.lang.String getRegistrationContextString() {
		return _wsrpConsumer.getRegistrationContextString();
	}

	public void setRegistrationContextString(
		java.lang.String registrationContextString) {
		_wsrpConsumer.setRegistrationContextString(registrationContextString);
	}

	public java.lang.String getRegistrationPropertiesString() {
		return _wsrpConsumer.getRegistrationPropertiesString();
	}

	public void setRegistrationPropertiesString(
		java.lang.String registrationPropertiesString) {
		_wsrpConsumer.setRegistrationPropertiesString(registrationPropertiesString);
	}

	public com.liferay.wsrp.model.WSRPConsumer toEscapedModel() {
		return _wsrpConsumer.toEscapedModel();
	}

	public boolean isNew() {
		return _wsrpConsumer.isNew();
	}

	public boolean setNew(boolean n) {
		return _wsrpConsumer.setNew(n);
	}

	public boolean isCachedModel() {
		return _wsrpConsumer.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_wsrpConsumer.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _wsrpConsumer.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_wsrpConsumer.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _wsrpConsumer.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _wsrpConsumer.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_wsrpConsumer.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _wsrpConsumer.clone();
	}

	public int compareTo(com.liferay.wsrp.model.WSRPConsumer wsrpConsumer) {
		return _wsrpConsumer.compareTo(wsrpConsumer);
	}

	public int hashCode() {
		return _wsrpConsumer.hashCode();
	}

	public java.lang.String toString() {
		return _wsrpConsumer.toString();
	}

	public java.lang.String toXmlString() {
		return _wsrpConsumer.toXmlString();
	}

	public oasis.names.tc.wsrp.v2.types.RegistrationContext getRegistrationContext() {
		return _wsrpConsumer.getRegistrationContext();
	}

	public com.liferay.portal.kernel.util.UnicodeProperties getRegistrationProperties() {
		return _wsrpConsumer.getRegistrationProperties();
	}

	public void setRegistrationContext(
		oasis.names.tc.wsrp.v2.types.RegistrationContext registrationContext) {
		_wsrpConsumer.setRegistrationContext(registrationContext);
	}

	public void setRegistrationProperties(
		com.liferay.portal.kernel.util.UnicodeProperties registrationProperties) {
		_wsrpConsumer.setRegistrationProperties(registrationProperties);
	}

	public WSRPConsumer getWrappedWSRPConsumer() {
		return _wsrpConsumer;
	}

	private WSRPConsumer _wsrpConsumer;
}