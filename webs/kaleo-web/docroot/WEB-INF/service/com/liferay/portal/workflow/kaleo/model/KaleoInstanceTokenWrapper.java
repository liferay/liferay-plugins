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

package com.liferay.portal.workflow.kaleo.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link KaleoInstanceToken}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoInstanceToken
 * @generated
 */
public class KaleoInstanceTokenWrapper implements KaleoInstanceToken,
	ModelWrapper<KaleoInstanceToken> {
	public KaleoInstanceTokenWrapper(KaleoInstanceToken kaleoInstanceToken) {
		_kaleoInstanceToken = kaleoInstanceToken;
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoInstanceToken.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoInstanceToken.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoInstanceTokenId", getKaleoInstanceTokenId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoInstanceId", getKaleoInstanceId());
		attributes.put("parentKaleoInstanceTokenId",
			getParentKaleoInstanceTokenId());
		attributes.put("currentKaleoNodeId", getCurrentKaleoNodeId());
		attributes.put("currentKaleoNodeName", getCurrentKaleoNodeName());
		attributes.put("className", getClassName());
		attributes.put("classPK", getClassPK());
		attributes.put("completed", getCompleted());
		attributes.put("completionDate", getCompletionDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoInstanceTokenId = (Long)attributes.get("kaleoInstanceTokenId");

		if (kaleoInstanceTokenId != null) {
			setKaleoInstanceTokenId(kaleoInstanceTokenId);
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

		Long kaleoInstanceId = (Long)attributes.get("kaleoInstanceId");

		if (kaleoInstanceId != null) {
			setKaleoInstanceId(kaleoInstanceId);
		}

		Long parentKaleoInstanceTokenId = (Long)attributes.get(
				"parentKaleoInstanceTokenId");

		if (parentKaleoInstanceTokenId != null) {
			setParentKaleoInstanceTokenId(parentKaleoInstanceTokenId);
		}

		Long currentKaleoNodeId = (Long)attributes.get("currentKaleoNodeId");

		if (currentKaleoNodeId != null) {
			setCurrentKaleoNodeId(currentKaleoNodeId);
		}

		String currentKaleoNodeName = (String)attributes.get(
				"currentKaleoNodeName");

		if (currentKaleoNodeName != null) {
			setCurrentKaleoNodeName(currentKaleoNodeName);
		}

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Boolean completed = (Boolean)attributes.get("completed");

		if (completed != null) {
			setCompleted(completed);
		}

		Date completionDate = (Date)attributes.get("completionDate");

		if (completionDate != null) {
			setCompletionDate(completionDate);
		}
	}

	/**
	* Returns the primary key of this kaleo instance token.
	*
	* @return the primary key of this kaleo instance token
	*/
	@Override
	public long getPrimaryKey() {
		return _kaleoInstanceToken.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo instance token.
	*
	* @param primaryKey the primary key of this kaleo instance token
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kaleoInstanceToken.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the kaleo instance token ID of this kaleo instance token.
	*
	* @return the kaleo instance token ID of this kaleo instance token
	*/
	@Override
	public long getKaleoInstanceTokenId() {
		return _kaleoInstanceToken.getKaleoInstanceTokenId();
	}

	/**
	* Sets the kaleo instance token ID of this kaleo instance token.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID of this kaleo instance token
	*/
	@Override
	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_kaleoInstanceToken.setKaleoInstanceTokenId(kaleoInstanceTokenId);
	}

	/**
	* Returns the group ID of this kaleo instance token.
	*
	* @return the group ID of this kaleo instance token
	*/
	@Override
	public long getGroupId() {
		return _kaleoInstanceToken.getGroupId();
	}

	/**
	* Sets the group ID of this kaleo instance token.
	*
	* @param groupId the group ID of this kaleo instance token
	*/
	@Override
	public void setGroupId(long groupId) {
		_kaleoInstanceToken.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this kaleo instance token.
	*
	* @return the company ID of this kaleo instance token
	*/
	@Override
	public long getCompanyId() {
		return _kaleoInstanceToken.getCompanyId();
	}

	/**
	* Sets the company ID of this kaleo instance token.
	*
	* @param companyId the company ID of this kaleo instance token
	*/
	@Override
	public void setCompanyId(long companyId) {
		_kaleoInstanceToken.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this kaleo instance token.
	*
	* @return the user ID of this kaleo instance token
	*/
	@Override
	public long getUserId() {
		return _kaleoInstanceToken.getUserId();
	}

	/**
	* Sets the user ID of this kaleo instance token.
	*
	* @param userId the user ID of this kaleo instance token
	*/
	@Override
	public void setUserId(long userId) {
		_kaleoInstanceToken.setUserId(userId);
	}

	/**
	* Returns the user uuid of this kaleo instance token.
	*
	* @return the user uuid of this kaleo instance token
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceToken.getUserUuid();
	}

	/**
	* Sets the user uuid of this kaleo instance token.
	*
	* @param userUuid the user uuid of this kaleo instance token
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoInstanceToken.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this kaleo instance token.
	*
	* @return the user name of this kaleo instance token
	*/
	@Override
	public java.lang.String getUserName() {
		return _kaleoInstanceToken.getUserName();
	}

	/**
	* Sets the user name of this kaleo instance token.
	*
	* @param userName the user name of this kaleo instance token
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_kaleoInstanceToken.setUserName(userName);
	}

	/**
	* Returns the create date of this kaleo instance token.
	*
	* @return the create date of this kaleo instance token
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _kaleoInstanceToken.getCreateDate();
	}

	/**
	* Sets the create date of this kaleo instance token.
	*
	* @param createDate the create date of this kaleo instance token
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_kaleoInstanceToken.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this kaleo instance token.
	*
	* @return the modified date of this kaleo instance token
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _kaleoInstanceToken.getModifiedDate();
	}

	/**
	* Sets the modified date of this kaleo instance token.
	*
	* @param modifiedDate the modified date of this kaleo instance token
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoInstanceToken.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the kaleo definition ID of this kaleo instance token.
	*
	* @return the kaleo definition ID of this kaleo instance token
	*/
	@Override
	public long getKaleoDefinitionId() {
		return _kaleoInstanceToken.getKaleoDefinitionId();
	}

	/**
	* Sets the kaleo definition ID of this kaleo instance token.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo instance token
	*/
	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoInstanceToken.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the kaleo instance ID of this kaleo instance token.
	*
	* @return the kaleo instance ID of this kaleo instance token
	*/
	@Override
	public long getKaleoInstanceId() {
		return _kaleoInstanceToken.getKaleoInstanceId();
	}

	/**
	* Sets the kaleo instance ID of this kaleo instance token.
	*
	* @param kaleoInstanceId the kaleo instance ID of this kaleo instance token
	*/
	@Override
	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoInstanceToken.setKaleoInstanceId(kaleoInstanceId);
	}

	/**
	* Returns the parent kaleo instance token ID of this kaleo instance token.
	*
	* @return the parent kaleo instance token ID of this kaleo instance token
	*/
	@Override
	public long getParentKaleoInstanceTokenId() {
		return _kaleoInstanceToken.getParentKaleoInstanceTokenId();
	}

	/**
	* Sets the parent kaleo instance token ID of this kaleo instance token.
	*
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID of this kaleo instance token
	*/
	@Override
	public void setParentKaleoInstanceTokenId(long parentKaleoInstanceTokenId) {
		_kaleoInstanceToken.setParentKaleoInstanceTokenId(parentKaleoInstanceTokenId);
	}

	/**
	* Returns the current kaleo node ID of this kaleo instance token.
	*
	* @return the current kaleo node ID of this kaleo instance token
	*/
	@Override
	public long getCurrentKaleoNodeId() {
		return _kaleoInstanceToken.getCurrentKaleoNodeId();
	}

	/**
	* Sets the current kaleo node ID of this kaleo instance token.
	*
	* @param currentKaleoNodeId the current kaleo node ID of this kaleo instance token
	*/
	@Override
	public void setCurrentKaleoNodeId(long currentKaleoNodeId) {
		_kaleoInstanceToken.setCurrentKaleoNodeId(currentKaleoNodeId);
	}

	/**
	* Returns the current kaleo node name of this kaleo instance token.
	*
	* @return the current kaleo node name of this kaleo instance token
	*/
	@Override
	public java.lang.String getCurrentKaleoNodeName() {
		return _kaleoInstanceToken.getCurrentKaleoNodeName();
	}

	/**
	* Sets the current kaleo node name of this kaleo instance token.
	*
	* @param currentKaleoNodeName the current kaleo node name of this kaleo instance token
	*/
	@Override
	public void setCurrentKaleoNodeName(java.lang.String currentKaleoNodeName) {
		_kaleoInstanceToken.setCurrentKaleoNodeName(currentKaleoNodeName);
	}

	/**
	* Returns the class name of this kaleo instance token.
	*
	* @return the class name of this kaleo instance token
	*/
	@Override
	public java.lang.String getClassName() {
		return _kaleoInstanceToken.getClassName();
	}

	/**
	* Sets the class name of this kaleo instance token.
	*
	* @param className the class name of this kaleo instance token
	*/
	@Override
	public void setClassName(java.lang.String className) {
		_kaleoInstanceToken.setClassName(className);
	}

	/**
	* Returns the class p k of this kaleo instance token.
	*
	* @return the class p k of this kaleo instance token
	*/
	@Override
	public long getClassPK() {
		return _kaleoInstanceToken.getClassPK();
	}

	/**
	* Sets the class p k of this kaleo instance token.
	*
	* @param classPK the class p k of this kaleo instance token
	*/
	@Override
	public void setClassPK(long classPK) {
		_kaleoInstanceToken.setClassPK(classPK);
	}

	/**
	* Returns the completed of this kaleo instance token.
	*
	* @return the completed of this kaleo instance token
	*/
	@Override
	public boolean getCompleted() {
		return _kaleoInstanceToken.getCompleted();
	}

	/**
	* Returns <code>true</code> if this kaleo instance token is completed.
	*
	* @return <code>true</code> if this kaleo instance token is completed; <code>false</code> otherwise
	*/
	@Override
	public boolean isCompleted() {
		return _kaleoInstanceToken.isCompleted();
	}

	/**
	* Sets whether this kaleo instance token is completed.
	*
	* @param completed the completed of this kaleo instance token
	*/
	@Override
	public void setCompleted(boolean completed) {
		_kaleoInstanceToken.setCompleted(completed);
	}

	/**
	* Returns the completion date of this kaleo instance token.
	*
	* @return the completion date of this kaleo instance token
	*/
	@Override
	public java.util.Date getCompletionDate() {
		return _kaleoInstanceToken.getCompletionDate();
	}

	/**
	* Sets the completion date of this kaleo instance token.
	*
	* @param completionDate the completion date of this kaleo instance token
	*/
	@Override
	public void setCompletionDate(java.util.Date completionDate) {
		_kaleoInstanceToken.setCompletionDate(completionDate);
	}

	@Override
	public boolean isNew() {
		return _kaleoInstanceToken.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_kaleoInstanceToken.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _kaleoInstanceToken.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kaleoInstanceToken.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _kaleoInstanceToken.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoInstanceToken.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoInstanceToken.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoInstanceToken.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_kaleoInstanceToken.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_kaleoInstanceToken.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoInstanceToken.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoInstanceTokenWrapper((KaleoInstanceToken)_kaleoInstanceToken.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken) {
		return _kaleoInstanceToken.compareTo(kaleoInstanceToken);
	}

	@Override
	public int hashCode() {
		return _kaleoInstanceToken.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> toCacheModel() {
		return _kaleoInstanceToken.toCacheModel();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken toEscapedModel() {
		return new KaleoInstanceTokenWrapper(_kaleoInstanceToken.toEscapedModel());
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken toUnescapedModel() {
		return new KaleoInstanceTokenWrapper(_kaleoInstanceToken.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoInstanceToken.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _kaleoInstanceToken.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoInstanceToken.persist();
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> getChildrenKaleoInstanceTokens()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceToken.getChildrenKaleoInstanceTokens();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoNode getCurrentKaleoNode()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceToken.getCurrentKaleoNode();
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> getIncompleteChildrenKaleoInstanceTokens()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceToken.getIncompleteChildrenKaleoInstanceTokens();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstance getKaleoInstance()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceToken.getKaleoInstance();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getParentKaleoInstanceToken()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceToken.getParentKaleoInstanceToken();
	}

	@Override
	public boolean hasIncompleteChildrenKaleoInstanceToken()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceToken.hasIncompleteChildrenKaleoInstanceToken();
	}

	@Override
	public void setCurrentKaleoNode(
		com.liferay.portal.workflow.kaleo.model.KaleoNode kaleoNode)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoInstanceToken.setCurrentKaleoNode(kaleoNode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoInstanceTokenWrapper)) {
			return false;
		}

		KaleoInstanceTokenWrapper kaleoInstanceTokenWrapper = (KaleoInstanceTokenWrapper)obj;

		if (Validator.equals(_kaleoInstanceToken,
					kaleoInstanceTokenWrapper._kaleoInstanceToken)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public KaleoInstanceToken getWrappedKaleoInstanceToken() {
		return _kaleoInstanceToken;
	}

	@Override
	public KaleoInstanceToken getWrappedModel() {
		return _kaleoInstanceToken;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _kaleoInstanceToken.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _kaleoInstanceToken.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_kaleoInstanceToken.resetOriginalValues();
	}

	private KaleoInstanceToken _kaleoInstanceToken;
}