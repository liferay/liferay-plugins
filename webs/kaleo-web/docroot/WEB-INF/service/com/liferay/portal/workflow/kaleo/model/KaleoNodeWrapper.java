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

package com.liferay.portal.workflow.kaleo.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link KaleoNode}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNode
 * @generated
 */
@ProviderType
public class KaleoNodeWrapper implements KaleoNode, ModelWrapper<KaleoNode> {
	public KaleoNodeWrapper(KaleoNode kaleoNode) {
		_kaleoNode = kaleoNode;
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoNode.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoNode.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoNodeId", getKaleoNodeId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("name", getName());
		attributes.put("metadata", getMetadata());
		attributes.put("description", getDescription());
		attributes.put("type", getType());
		attributes.put("initial", getInitial());
		attributes.put("terminal", getTerminal());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoNodeId = (Long)attributes.get("kaleoNodeId");

		if (kaleoNodeId != null) {
			setKaleoNodeId(kaleoNodeId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long kaleoDefinitionId = (Long)attributes.get("kaleoDefinitionId");

		if (kaleoDefinitionId != null) {
			setKaleoDefinitionId(kaleoDefinitionId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String metadata = (String)attributes.get("metadata");

		if (metadata != null) {
			setMetadata(metadata);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Boolean initial = (Boolean)attributes.get("initial");

		if (initial != null) {
			setInitial(initial);
		}

		Boolean terminal = (Boolean)attributes.get("terminal");

		if (terminal != null) {
			setTerminal(terminal);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoNodeWrapper((KaleoNode)_kaleoNode.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoNode kaleoNode) {
		return _kaleoNode.compareTo(kaleoNode);
	}

	/**
	* Returns the company ID of this kaleo node.
	*
	* @return the company ID of this kaleo node
	*/
	@Override
	public long getCompanyId() {
		return _kaleoNode.getCompanyId();
	}

	/**
	* Returns the create date of this kaleo node.
	*
	* @return the create date of this kaleo node
	*/
	@Override
	public Date getCreateDate() {
		return _kaleoNode.getCreateDate();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition getDefaultKaleoTransition()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoNode.getDefaultKaleoTransition();
	}

	/**
	* Returns the description of this kaleo node.
	*
	* @return the description of this kaleo node
	*/
	@Override
	public java.lang.String getDescription() {
		return _kaleoNode.getDescription();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoNode.getExpandoBridge();
	}

	/**
	* Returns the group ID of this kaleo node.
	*
	* @return the group ID of this kaleo node
	*/
	@Override
	public long getGroupId() {
		return _kaleoNode.getGroupId();
	}

	/**
	* Returns the initial of this kaleo node.
	*
	* @return the initial of this kaleo node
	*/
	@Override
	public boolean getInitial() {
		return _kaleoNode.getInitial();
	}

	/**
	* Returns the kaleo definition ID of this kaleo node.
	*
	* @return the kaleo definition ID of this kaleo node
	*/
	@Override
	public long getKaleoDefinitionId() {
		return _kaleoNode.getKaleoDefinitionId();
	}

	/**
	* Returns the kaleo node ID of this kaleo node.
	*
	* @return the kaleo node ID of this kaleo node
	*/
	@Override
	public long getKaleoNodeId() {
		return _kaleoNode.getKaleoNodeId();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition getKaleoTransition(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoNode.getKaleoTransition(name);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> getKaleoTransitions() {
		return _kaleoNode.getKaleoTransitions();
	}

	/**
	* Returns the metadata of this kaleo node.
	*
	* @return the metadata of this kaleo node
	*/
	@Override
	public java.lang.String getMetadata() {
		return _kaleoNode.getMetadata();
	}

	/**
	* Returns the modified date of this kaleo node.
	*
	* @return the modified date of this kaleo node
	*/
	@Override
	public Date getModifiedDate() {
		return _kaleoNode.getModifiedDate();
	}

	/**
	* Returns the name of this kaleo node.
	*
	* @return the name of this kaleo node
	*/
	@Override
	public java.lang.String getName() {
		return _kaleoNode.getName();
	}

	/**
	* Returns the primary key of this kaleo node.
	*
	* @return the primary key of this kaleo node
	*/
	@Override
	public long getPrimaryKey() {
		return _kaleoNode.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoNode.getPrimaryKeyObj();
	}

	/**
	* Returns the terminal of this kaleo node.
	*
	* @return the terminal of this kaleo node
	*/
	@Override
	public boolean getTerminal() {
		return _kaleoNode.getTerminal();
	}

	/**
	* Returns the type of this kaleo node.
	*
	* @return the type of this kaleo node
	*/
	@Override
	public java.lang.String getType() {
		return _kaleoNode.getType();
	}

	/**
	* Returns the user ID of this kaleo node.
	*
	* @return the user ID of this kaleo node
	*/
	@Override
	public long getUserId() {
		return _kaleoNode.getUserId();
	}

	/**
	* Returns the user name of this kaleo node.
	*
	* @return the user name of this kaleo node
	*/
	@Override
	public java.lang.String getUserName() {
		return _kaleoNode.getUserName();
	}

	/**
	* Returns the user uuid of this kaleo node.
	*
	* @return the user uuid of this kaleo node
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _kaleoNode.getUserUuid();
	}

	@Override
	public boolean hasKaleoTransition() {
		return _kaleoNode.hasKaleoTransition();
	}

	@Override
	public int hashCode() {
		return _kaleoNode.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _kaleoNode.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _kaleoNode.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this kaleo node is initial.
	*
	* @return <code>true</code> if this kaleo node is initial; <code>false</code> otherwise
	*/
	@Override
	public boolean isInitial() {
		return _kaleoNode.isInitial();
	}

	@Override
	public boolean isNew() {
		return _kaleoNode.isNew();
	}

	/**
	* Returns <code>true</code> if this kaleo node is terminal.
	*
	* @return <code>true</code> if this kaleo node is terminal; <code>false</code> otherwise
	*/
	@Override
	public boolean isTerminal() {
		return _kaleoNode.isTerminal();
	}

	@Override
	public void persist() {
		_kaleoNode.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kaleoNode.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this kaleo node.
	*
	* @param companyId the company ID of this kaleo node
	*/
	@Override
	public void setCompanyId(long companyId) {
		_kaleoNode.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this kaleo node.
	*
	* @param createDate the create date of this kaleo node
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_kaleoNode.setCreateDate(createDate);
	}

	/**
	* Sets the description of this kaleo node.
	*
	* @param description the description of this kaleo node
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_kaleoNode.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_kaleoNode.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_kaleoNode.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoNode.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this kaleo node.
	*
	* @param groupId the group ID of this kaleo node
	*/
	@Override
	public void setGroupId(long groupId) {
		_kaleoNode.setGroupId(groupId);
	}

	/**
	* Sets whether this kaleo node is initial.
	*
	* @param initial the initial of this kaleo node
	*/
	@Override
	public void setInitial(boolean initial) {
		_kaleoNode.setInitial(initial);
	}

	/**
	* Sets the kaleo definition ID of this kaleo node.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo node
	*/
	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoNode.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Sets the kaleo node ID of this kaleo node.
	*
	* @param kaleoNodeId the kaleo node ID of this kaleo node
	*/
	@Override
	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoNode.setKaleoNodeId(kaleoNodeId);
	}

	/**
	* Sets the metadata of this kaleo node.
	*
	* @param metadata the metadata of this kaleo node
	*/
	@Override
	public void setMetadata(java.lang.String metadata) {
		_kaleoNode.setMetadata(metadata);
	}

	/**
	* Sets the modified date of this kaleo node.
	*
	* @param modifiedDate the modified date of this kaleo node
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_kaleoNode.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this kaleo node.
	*
	* @param name the name of this kaleo node
	*/
	@Override
	public void setName(java.lang.String name) {
		_kaleoNode.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_kaleoNode.setNew(n);
	}

	/**
	* Sets the primary key of this kaleo node.
	*
	* @param primaryKey the primary key of this kaleo node
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kaleoNode.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoNode.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this kaleo node is terminal.
	*
	* @param terminal the terminal of this kaleo node
	*/
	@Override
	public void setTerminal(boolean terminal) {
		_kaleoNode.setTerminal(terminal);
	}

	/**
	* Sets the type of this kaleo node.
	*
	* @param type the type of this kaleo node
	*/
	@Override
	public void setType(java.lang.String type) {
		_kaleoNode.setType(type);
	}

	/**
	* Sets the user ID of this kaleo node.
	*
	* @param userId the user ID of this kaleo node
	*/
	@Override
	public void setUserId(long userId) {
		_kaleoNode.setUserId(userId);
	}

	/**
	* Sets the user name of this kaleo node.
	*
	* @param userName the user name of this kaleo node
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_kaleoNode.setUserName(userName);
	}

	/**
	* Sets the user uuid of this kaleo node.
	*
	* @param userUuid the user uuid of this kaleo node
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoNode.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoNode> toCacheModel() {
		return _kaleoNode.toCacheModel();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoNode toEscapedModel() {
		return new KaleoNodeWrapper(_kaleoNode.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoNode.toString();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoNode toUnescapedModel() {
		return new KaleoNodeWrapper(_kaleoNode.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _kaleoNode.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoNodeWrapper)) {
			return false;
		}

		KaleoNodeWrapper kaleoNodeWrapper = (KaleoNodeWrapper)obj;

		if (Validator.equals(_kaleoNode, kaleoNodeWrapper._kaleoNode)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public KaleoNode getWrappedKaleoNode() {
		return _kaleoNode;
	}

	@Override
	public KaleoNode getWrappedModel() {
		return _kaleoNode;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _kaleoNode.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _kaleoNode.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_kaleoNode.resetOriginalValues();
	}

	private final KaleoNode _kaleoNode;
}