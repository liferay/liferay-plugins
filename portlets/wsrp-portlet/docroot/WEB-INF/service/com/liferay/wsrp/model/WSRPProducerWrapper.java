/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link WSRPProducer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPProducer
 * @generated
 */
public class WSRPProducerWrapper implements WSRPProducer,
	ModelWrapper<WSRPProducer> {
	public WSRPProducerWrapper(WSRPProducer wsrpProducer) {
		_wsrpProducer = wsrpProducer;
	}

	@Override
	public Class<?> getModelClass() {
		return WSRPProducer.class;
	}

	@Override
	public String getModelClassName() {
		return WSRPProducer.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("wsrpProducerId", getWsrpProducerId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("version", getVersion());
		attributes.put("portletIds", getPortletIds());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long wsrpProducerId = (Long)attributes.get("wsrpProducerId");

		if (wsrpProducerId != null) {
			setWsrpProducerId(wsrpProducerId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		String version = (String)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		String portletIds = (String)attributes.get("portletIds");

		if (portletIds != null) {
			setPortletIds(portletIds);
		}
	}

	/**
	* Returns the primary key of this w s r p producer.
	*
	* @return the primary key of this w s r p producer
	*/
	@Override
	public long getPrimaryKey() {
		return _wsrpProducer.getPrimaryKey();
	}

	/**
	* Sets the primary key of this w s r p producer.
	*
	* @param primaryKey the primary key of this w s r p producer
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_wsrpProducer.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this w s r p producer.
	*
	* @return the uuid of this w s r p producer
	*/
	@Override
	public java.lang.String getUuid() {
		return _wsrpProducer.getUuid();
	}

	/**
	* Sets the uuid of this w s r p producer.
	*
	* @param uuid the uuid of this w s r p producer
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_wsrpProducer.setUuid(uuid);
	}

	/**
	* Returns the wsrp producer ID of this w s r p producer.
	*
	* @return the wsrp producer ID of this w s r p producer
	*/
	@Override
	public long getWsrpProducerId() {
		return _wsrpProducer.getWsrpProducerId();
	}

	/**
	* Sets the wsrp producer ID of this w s r p producer.
	*
	* @param wsrpProducerId the wsrp producer ID of this w s r p producer
	*/
	@Override
	public void setWsrpProducerId(long wsrpProducerId) {
		_wsrpProducer.setWsrpProducerId(wsrpProducerId);
	}

	/**
	* Returns the group ID of this w s r p producer.
	*
	* @return the group ID of this w s r p producer
	*/
	@Override
	public long getGroupId() {
		return _wsrpProducer.getGroupId();
	}

	/**
	* Sets the group ID of this w s r p producer.
	*
	* @param groupId the group ID of this w s r p producer
	*/
	@Override
	public void setGroupId(long groupId) {
		_wsrpProducer.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this w s r p producer.
	*
	* @return the company ID of this w s r p producer
	*/
	@Override
	public long getCompanyId() {
		return _wsrpProducer.getCompanyId();
	}

	/**
	* Sets the company ID of this w s r p producer.
	*
	* @param companyId the company ID of this w s r p producer
	*/
	@Override
	public void setCompanyId(long companyId) {
		_wsrpProducer.setCompanyId(companyId);
	}

	/**
	* Returns the create date of this w s r p producer.
	*
	* @return the create date of this w s r p producer
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _wsrpProducer.getCreateDate();
	}

	/**
	* Sets the create date of this w s r p producer.
	*
	* @param createDate the create date of this w s r p producer
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_wsrpProducer.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this w s r p producer.
	*
	* @return the modified date of this w s r p producer
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _wsrpProducer.getModifiedDate();
	}

	/**
	* Sets the modified date of this w s r p producer.
	*
	* @param modifiedDate the modified date of this w s r p producer
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_wsrpProducer.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this w s r p producer.
	*
	* @return the name of this w s r p producer
	*/
	@Override
	public java.lang.String getName() {
		return _wsrpProducer.getName();
	}

	/**
	* Sets the name of this w s r p producer.
	*
	* @param name the name of this w s r p producer
	*/
	@Override
	public void setName(java.lang.String name) {
		_wsrpProducer.setName(name);
	}

	/**
	* Returns the version of this w s r p producer.
	*
	* @return the version of this w s r p producer
	*/
	@Override
	public java.lang.String getVersion() {
		return _wsrpProducer.getVersion();
	}

	/**
	* Sets the version of this w s r p producer.
	*
	* @param version the version of this w s r p producer
	*/
	@Override
	public void setVersion(java.lang.String version) {
		_wsrpProducer.setVersion(version);
	}

	/**
	* Returns the portlet IDs of this w s r p producer.
	*
	* @return the portlet IDs of this w s r p producer
	*/
	@Override
	public java.lang.String getPortletIds() {
		return _wsrpProducer.getPortletIds();
	}

	/**
	* Sets the portlet IDs of this w s r p producer.
	*
	* @param portletIds the portlet IDs of this w s r p producer
	*/
	@Override
	public void setPortletIds(java.lang.String portletIds) {
		_wsrpProducer.setPortletIds(portletIds);
	}

	@Override
	public boolean isNew() {
		return _wsrpProducer.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_wsrpProducer.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _wsrpProducer.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_wsrpProducer.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _wsrpProducer.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _wsrpProducer.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_wsrpProducer.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _wsrpProducer.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_wsrpProducer.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_wsrpProducer.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_wsrpProducer.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new WSRPProducerWrapper((WSRPProducer)_wsrpProducer.clone());
	}

	@Override
	public int compareTo(com.liferay.wsrp.model.WSRPProducer wsrpProducer) {
		return _wsrpProducer.compareTo(wsrpProducer);
	}

	@Override
	public int hashCode() {
		return _wsrpProducer.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.wsrp.model.WSRPProducer> toCacheModel() {
		return _wsrpProducer.toCacheModel();
	}

	@Override
	public com.liferay.wsrp.model.WSRPProducer toEscapedModel() {
		return new WSRPProducerWrapper(_wsrpProducer.toEscapedModel());
	}

	@Override
	public com.liferay.wsrp.model.WSRPProducer toUnescapedModel() {
		return new WSRPProducerWrapper(_wsrpProducer.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _wsrpProducer.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _wsrpProducer.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_wsrpProducer.persist();
	}

	@Override
	public java.lang.String getURL(java.lang.String portalURL) {
		return _wsrpProducer.getURL(portalURL);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WSRPProducerWrapper)) {
			return false;
		}

		WSRPProducerWrapper wsrpProducerWrapper = (WSRPProducerWrapper)obj;

		if (Validator.equals(_wsrpProducer, wsrpProducerWrapper._wsrpProducer)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _wsrpProducer.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public WSRPProducer getWrappedWSRPProducer() {
		return _wsrpProducer;
	}

	@Override
	public WSRPProducer getWrappedModel() {
		return _wsrpProducer;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _wsrpProducer.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _wsrpProducer.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_wsrpProducer.resetOriginalValues();
	}

	private WSRPProducer _wsrpProducer;
}