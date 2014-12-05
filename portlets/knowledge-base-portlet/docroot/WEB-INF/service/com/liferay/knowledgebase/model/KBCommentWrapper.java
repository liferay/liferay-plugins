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

package com.liferay.knowledgebase.model;

import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link KBComment}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBComment
 * @generated
 */
public class KBCommentWrapper implements KBComment, ModelWrapper<KBComment> {
	public KBCommentWrapper(KBComment kbComment) {
		_kbComment = kbComment;
	}

	@Override
	public Class<?> getModelClass() {
		return KBComment.class;
	}

	@Override
	public String getModelClassName() {
		return KBComment.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("kbCommentId", getKbCommentId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("content", getContent());
		attributes.put("userRating", getUserRating());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long kbCommentId = (Long)attributes.get("kbCommentId");

		if (kbCommentId != null) {
			setKbCommentId(kbCommentId);
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

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		Integer userRating = (Integer)attributes.get("userRating");

		if (userRating != null) {
			setUserRating(userRating);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	/**
	* Returns the primary key of this k b comment.
	*
	* @return the primary key of this k b comment
	*/
	@Override
	public long getPrimaryKey() {
		return _kbComment.getPrimaryKey();
	}

	/**
	* Sets the primary key of this k b comment.
	*
	* @param primaryKey the primary key of this k b comment
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kbComment.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this k b comment.
	*
	* @return the uuid of this k b comment
	*/
	@Override
	public java.lang.String getUuid() {
		return _kbComment.getUuid();
	}

	/**
	* Sets the uuid of this k b comment.
	*
	* @param uuid the uuid of this k b comment
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_kbComment.setUuid(uuid);
	}

	/**
	* Returns the kb comment ID of this k b comment.
	*
	* @return the kb comment ID of this k b comment
	*/
	@Override
	public long getKbCommentId() {
		return _kbComment.getKbCommentId();
	}

	/**
	* Sets the kb comment ID of this k b comment.
	*
	* @param kbCommentId the kb comment ID of this k b comment
	*/
	@Override
	public void setKbCommentId(long kbCommentId) {
		_kbComment.setKbCommentId(kbCommentId);
	}

	/**
	* Returns the group ID of this k b comment.
	*
	* @return the group ID of this k b comment
	*/
	@Override
	public long getGroupId() {
		return _kbComment.getGroupId();
	}

	/**
	* Sets the group ID of this k b comment.
	*
	* @param groupId the group ID of this k b comment
	*/
	@Override
	public void setGroupId(long groupId) {
		_kbComment.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this k b comment.
	*
	* @return the company ID of this k b comment
	*/
	@Override
	public long getCompanyId() {
		return _kbComment.getCompanyId();
	}

	/**
	* Sets the company ID of this k b comment.
	*
	* @param companyId the company ID of this k b comment
	*/
	@Override
	public void setCompanyId(long companyId) {
		_kbComment.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this k b comment.
	*
	* @return the user ID of this k b comment
	*/
	@Override
	public long getUserId() {
		return _kbComment.getUserId();
	}

	/**
	* Sets the user ID of this k b comment.
	*
	* @param userId the user ID of this k b comment
	*/
	@Override
	public void setUserId(long userId) {
		_kbComment.setUserId(userId);
	}

	/**
	* Returns the user uuid of this k b comment.
	*
	* @return the user uuid of this k b comment
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbComment.getUserUuid();
	}

	/**
	* Sets the user uuid of this k b comment.
	*
	* @param userUuid the user uuid of this k b comment
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_kbComment.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this k b comment.
	*
	* @return the user name of this k b comment
	*/
	@Override
	public java.lang.String getUserName() {
		return _kbComment.getUserName();
	}

	/**
	* Sets the user name of this k b comment.
	*
	* @param userName the user name of this k b comment
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_kbComment.setUserName(userName);
	}

	/**
	* Returns the create date of this k b comment.
	*
	* @return the create date of this k b comment
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _kbComment.getCreateDate();
	}

	/**
	* Sets the create date of this k b comment.
	*
	* @param createDate the create date of this k b comment
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_kbComment.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this k b comment.
	*
	* @return the modified date of this k b comment
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _kbComment.getModifiedDate();
	}

	/**
	* Sets the modified date of this k b comment.
	*
	* @param modifiedDate the modified date of this k b comment
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kbComment.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the fully qualified class name of this k b comment.
	*
	* @return the fully qualified class name of this k b comment
	*/
	@Override
	public java.lang.String getClassName() {
		return _kbComment.getClassName();
	}

	@Override
	public void setClassName(java.lang.String className) {
		_kbComment.setClassName(className);
	}

	/**
	* Returns the class name ID of this k b comment.
	*
	* @return the class name ID of this k b comment
	*/
	@Override
	public long getClassNameId() {
		return _kbComment.getClassNameId();
	}

	/**
	* Sets the class name ID of this k b comment.
	*
	* @param classNameId the class name ID of this k b comment
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_kbComment.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this k b comment.
	*
	* @return the class p k of this k b comment
	*/
	@Override
	public long getClassPK() {
		return _kbComment.getClassPK();
	}

	/**
	* Sets the class p k of this k b comment.
	*
	* @param classPK the class p k of this k b comment
	*/
	@Override
	public void setClassPK(long classPK) {
		_kbComment.setClassPK(classPK);
	}

	/**
	* Returns the content of this k b comment.
	*
	* @return the content of this k b comment
	*/
	@Override
	public java.lang.String getContent() {
		return _kbComment.getContent();
	}

	/**
	* Sets the content of this k b comment.
	*
	* @param content the content of this k b comment
	*/
	@Override
	public void setContent(java.lang.String content) {
		_kbComment.setContent(content);
	}

	/**
	* Returns the user rating of this k b comment.
	*
	* @return the user rating of this k b comment
	*/
	@Override
	public int getUserRating() {
		return _kbComment.getUserRating();
	}

	/**
	* Sets the user rating of this k b comment.
	*
	* @param userRating the user rating of this k b comment
	*/
	@Override
	public void setUserRating(int userRating) {
		_kbComment.setUserRating(userRating);
	}

	/**
	* Returns the status of this k b comment.
	*
	* @return the status of this k b comment
	*/
	@Override
	public int getStatus() {
		return _kbComment.getStatus();
	}

	/**
	* Sets the status of this k b comment.
	*
	* @param status the status of this k b comment
	*/
	@Override
	public void setStatus(int status) {
		_kbComment.setStatus(status);
	}

	@Override
	public boolean isNew() {
		return _kbComment.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_kbComment.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _kbComment.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kbComment.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _kbComment.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _kbComment.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kbComment.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kbComment.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_kbComment.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_kbComment.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kbComment.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KBCommentWrapper((KBComment)_kbComment.clone());
	}

	@Override
	public int compareTo(com.liferay.knowledgebase.model.KBComment kbComment) {
		return _kbComment.compareTo(kbComment);
	}

	@Override
	public int hashCode() {
		return _kbComment.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.knowledgebase.model.KBComment> toCacheModel() {
		return _kbComment.toCacheModel();
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment toEscapedModel() {
		return new KBCommentWrapper(_kbComment.toEscapedModel());
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment toUnescapedModel() {
		return new KBCommentWrapper(_kbComment.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kbComment.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _kbComment.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kbComment.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KBCommentWrapper)) {
			return false;
		}

		KBCommentWrapper kbCommentWrapper = (KBCommentWrapper)obj;

		if (Validator.equals(_kbComment, kbCommentWrapper._kbComment)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _kbComment.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public KBComment getWrappedKBComment() {
		return _kbComment;
	}

	@Override
	public KBComment getWrappedModel() {
		return _kbComment;
	}

	@Override
	public void resetOriginalValues() {
		_kbComment.resetOriginalValues();
	}

	private KBComment _kbComment;
}