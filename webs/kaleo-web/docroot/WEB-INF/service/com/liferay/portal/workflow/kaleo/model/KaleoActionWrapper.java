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
 * This class is a wrapper for {@link KaleoAction}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoAction
 * @generated
 */
@ProviderType
public class KaleoActionWrapper implements KaleoAction,
	ModelWrapper<KaleoAction> {
	public KaleoActionWrapper(KaleoAction kaleoAction) {
		_kaleoAction = kaleoAction;
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoAction.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoAction.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoActionId", getKaleoActionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoClassName", getKaleoClassName());
		attributes.put("kaleoClassPK", getKaleoClassPK());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoNodeName", getKaleoNodeName());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("executionType", getExecutionType());
		attributes.put("script", getScript());
		attributes.put("scriptLanguage", getScriptLanguage());
		attributes.put("scriptRequiredContexts", getScriptRequiredContexts());
		attributes.put("priority", getPriority());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoActionId = (Long)attributes.get("kaleoActionId");

		if (kaleoActionId != null) {
			setKaleoActionId(kaleoActionId);
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

		String kaleoClassName = (String)attributes.get("kaleoClassName");

		if (kaleoClassName != null) {
			setKaleoClassName(kaleoClassName);
		}

		Long kaleoClassPK = (Long)attributes.get("kaleoClassPK");

		if (kaleoClassPK != null) {
			setKaleoClassPK(kaleoClassPK);
		}

		Long kaleoDefinitionId = (Long)attributes.get("kaleoDefinitionId");

		if (kaleoDefinitionId != null) {
			setKaleoDefinitionId(kaleoDefinitionId);
		}

		String kaleoNodeName = (String)attributes.get("kaleoNodeName");

		if (kaleoNodeName != null) {
			setKaleoNodeName(kaleoNodeName);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String executionType = (String)attributes.get("executionType");

		if (executionType != null) {
			setExecutionType(executionType);
		}

		String script = (String)attributes.get("script");

		if (script != null) {
			setScript(script);
		}

		String scriptLanguage = (String)attributes.get("scriptLanguage");

		if (scriptLanguage != null) {
			setScriptLanguage(scriptLanguage);
		}

		String scriptRequiredContexts = (String)attributes.get(
				"scriptRequiredContexts");

		if (scriptRequiredContexts != null) {
			setScriptRequiredContexts(scriptRequiredContexts);
		}

		Integer priority = (Integer)attributes.get("priority");

		if (priority != null) {
			setPriority(priority);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoActionWrapper((KaleoAction)_kaleoAction.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoAction kaleoAction) {
		return _kaleoAction.compareTo(kaleoAction);
	}

	/**
	* Returns the company ID of this kaleo action.
	*
	* @return the company ID of this kaleo action
	*/
	@Override
	public long getCompanyId() {
		return _kaleoAction.getCompanyId();
	}

	/**
	* Returns the create date of this kaleo action.
	*
	* @return the create date of this kaleo action
	*/
	@Override
	public Date getCreateDate() {
		return _kaleoAction.getCreateDate();
	}

	/**
	* Returns the description of this kaleo action.
	*
	* @return the description of this kaleo action
	*/
	@Override
	public java.lang.String getDescription() {
		return _kaleoAction.getDescription();
	}

	/**
	* Returns the execution type of this kaleo action.
	*
	* @return the execution type of this kaleo action
	*/
	@Override
	public java.lang.String getExecutionType() {
		return _kaleoAction.getExecutionType();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoAction.getExpandoBridge();
	}

	/**
	* Returns the group ID of this kaleo action.
	*
	* @return the group ID of this kaleo action
	*/
	@Override
	public long getGroupId() {
		return _kaleoAction.getGroupId();
	}

	/**
	* Returns the kaleo action ID of this kaleo action.
	*
	* @return the kaleo action ID of this kaleo action
	*/
	@Override
	public long getKaleoActionId() {
		return _kaleoAction.getKaleoActionId();
	}

	/**
	* Returns the kaleo class name of this kaleo action.
	*
	* @return the kaleo class name of this kaleo action
	*/
	@Override
	public java.lang.String getKaleoClassName() {
		return _kaleoAction.getKaleoClassName();
	}

	/**
	* Returns the kaleo class p k of this kaleo action.
	*
	* @return the kaleo class p k of this kaleo action
	*/
	@Override
	public long getKaleoClassPK() {
		return _kaleoAction.getKaleoClassPK();
	}

	/**
	* Returns the kaleo definition ID of this kaleo action.
	*
	* @return the kaleo definition ID of this kaleo action
	*/
	@Override
	public long getKaleoDefinitionId() {
		return _kaleoAction.getKaleoDefinitionId();
	}

	/**
	* Returns the kaleo node name of this kaleo action.
	*
	* @return the kaleo node name of this kaleo action
	*/
	@Override
	public java.lang.String getKaleoNodeName() {
		return _kaleoAction.getKaleoNodeName();
	}

	/**
	* Returns the modified date of this kaleo action.
	*
	* @return the modified date of this kaleo action
	*/
	@Override
	public Date getModifiedDate() {
		return _kaleoAction.getModifiedDate();
	}

	/**
	* Returns the name of this kaleo action.
	*
	* @return the name of this kaleo action
	*/
	@Override
	public java.lang.String getName() {
		return _kaleoAction.getName();
	}

	/**
	* Returns the primary key of this kaleo action.
	*
	* @return the primary key of this kaleo action
	*/
	@Override
	public long getPrimaryKey() {
		return _kaleoAction.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoAction.getPrimaryKeyObj();
	}

	/**
	* Returns the priority of this kaleo action.
	*
	* @return the priority of this kaleo action
	*/
	@Override
	public int getPriority() {
		return _kaleoAction.getPriority();
	}

	/**
	* Returns the script of this kaleo action.
	*
	* @return the script of this kaleo action
	*/
	@Override
	public java.lang.String getScript() {
		return _kaleoAction.getScript();
	}

	/**
	* Returns the script language of this kaleo action.
	*
	* @return the script language of this kaleo action
	*/
	@Override
	public java.lang.String getScriptLanguage() {
		return _kaleoAction.getScriptLanguage();
	}

	/**
	* Returns the script required contexts of this kaleo action.
	*
	* @return the script required contexts of this kaleo action
	*/
	@Override
	public java.lang.String getScriptRequiredContexts() {
		return _kaleoAction.getScriptRequiredContexts();
	}

	/**
	* Returns the user ID of this kaleo action.
	*
	* @return the user ID of this kaleo action
	*/
	@Override
	public long getUserId() {
		return _kaleoAction.getUserId();
	}

	/**
	* Returns the user name of this kaleo action.
	*
	* @return the user name of this kaleo action
	*/
	@Override
	public java.lang.String getUserName() {
		return _kaleoAction.getUserName();
	}

	/**
	* Returns the user uuid of this kaleo action.
	*
	* @return the user uuid of this kaleo action
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _kaleoAction.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _kaleoAction.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _kaleoAction.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _kaleoAction.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _kaleoAction.isNew();
	}

	@Override
	public void persist() {
		_kaleoAction.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kaleoAction.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this kaleo action.
	*
	* @param companyId the company ID of this kaleo action
	*/
	@Override
	public void setCompanyId(long companyId) {
		_kaleoAction.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this kaleo action.
	*
	* @param createDate the create date of this kaleo action
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_kaleoAction.setCreateDate(createDate);
	}

	/**
	* Sets the description of this kaleo action.
	*
	* @param description the description of this kaleo action
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_kaleoAction.setDescription(description);
	}

	/**
	* Sets the execution type of this kaleo action.
	*
	* @param executionType the execution type of this kaleo action
	*/
	@Override
	public void setExecutionType(java.lang.String executionType) {
		_kaleoAction.setExecutionType(executionType);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_kaleoAction.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_kaleoAction.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoAction.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this kaleo action.
	*
	* @param groupId the group ID of this kaleo action
	*/
	@Override
	public void setGroupId(long groupId) {
		_kaleoAction.setGroupId(groupId);
	}

	/**
	* Sets the kaleo action ID of this kaleo action.
	*
	* @param kaleoActionId the kaleo action ID of this kaleo action
	*/
	@Override
	public void setKaleoActionId(long kaleoActionId) {
		_kaleoAction.setKaleoActionId(kaleoActionId);
	}

	/**
	* Sets the kaleo class name of this kaleo action.
	*
	* @param kaleoClassName the kaleo class name of this kaleo action
	*/
	@Override
	public void setKaleoClassName(java.lang.String kaleoClassName) {
		_kaleoAction.setKaleoClassName(kaleoClassName);
	}

	/**
	* Sets the kaleo class p k of this kaleo action.
	*
	* @param kaleoClassPK the kaleo class p k of this kaleo action
	*/
	@Override
	public void setKaleoClassPK(long kaleoClassPK) {
		_kaleoAction.setKaleoClassPK(kaleoClassPK);
	}

	/**
	* Sets the kaleo definition ID of this kaleo action.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo action
	*/
	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoAction.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Sets the kaleo node name of this kaleo action.
	*
	* @param kaleoNodeName the kaleo node name of this kaleo action
	*/
	@Override
	public void setKaleoNodeName(java.lang.String kaleoNodeName) {
		_kaleoAction.setKaleoNodeName(kaleoNodeName);
	}

	/**
	* Sets the modified date of this kaleo action.
	*
	* @param modifiedDate the modified date of this kaleo action
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_kaleoAction.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this kaleo action.
	*
	* @param name the name of this kaleo action
	*/
	@Override
	public void setName(java.lang.String name) {
		_kaleoAction.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_kaleoAction.setNew(n);
	}

	/**
	* Sets the primary key of this kaleo action.
	*
	* @param primaryKey the primary key of this kaleo action
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kaleoAction.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoAction.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the priority of this kaleo action.
	*
	* @param priority the priority of this kaleo action
	*/
	@Override
	public void setPriority(int priority) {
		_kaleoAction.setPriority(priority);
	}

	/**
	* Sets the script of this kaleo action.
	*
	* @param script the script of this kaleo action
	*/
	@Override
	public void setScript(java.lang.String script) {
		_kaleoAction.setScript(script);
	}

	/**
	* Sets the script language of this kaleo action.
	*
	* @param scriptLanguage the script language of this kaleo action
	*/
	@Override
	public void setScriptLanguage(java.lang.String scriptLanguage) {
		_kaleoAction.setScriptLanguage(scriptLanguage);
	}

	/**
	* Sets the script required contexts of this kaleo action.
	*
	* @param scriptRequiredContexts the script required contexts of this kaleo action
	*/
	@Override
	public void setScriptRequiredContexts(
		java.lang.String scriptRequiredContexts) {
		_kaleoAction.setScriptRequiredContexts(scriptRequiredContexts);
	}

	/**
	* Sets the user ID of this kaleo action.
	*
	* @param userId the user ID of this kaleo action
	*/
	@Override
	public void setUserId(long userId) {
		_kaleoAction.setUserId(userId);
	}

	/**
	* Sets the user name of this kaleo action.
	*
	* @param userName the user name of this kaleo action
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_kaleoAction.setUserName(userName);
	}

	/**
	* Sets the user uuid of this kaleo action.
	*
	* @param userUuid the user uuid of this kaleo action
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoAction.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoAction> toCacheModel() {
		return _kaleoAction.toCacheModel();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoAction toEscapedModel() {
		return new KaleoActionWrapper(_kaleoAction.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoAction.toString();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoAction toUnescapedModel() {
		return new KaleoActionWrapper(_kaleoAction.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _kaleoAction.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoActionWrapper)) {
			return false;
		}

		KaleoActionWrapper kaleoActionWrapper = (KaleoActionWrapper)obj;

		if (Validator.equals(_kaleoAction, kaleoActionWrapper._kaleoAction)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public KaleoAction getWrappedKaleoAction() {
		return _kaleoAction;
	}

	@Override
	public KaleoAction getWrappedModel() {
		return _kaleoAction;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _kaleoAction.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _kaleoAction.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_kaleoAction.resetOriginalValues();
	}

	private final KaleoAction _kaleoAction;
}