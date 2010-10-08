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

package com.liferay.knowledgebase.model;

/**
 * <p>
 * This class is a wrapper for {@link Comment}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Comment
 * @generated
 */
public class CommentWrapper implements Comment {
	public CommentWrapper(Comment comment) {
		_comment = comment;
	}

	/**
	* Gets the primary key of this comment.
	*
	* @return the primary key of this comment
	*/
	public long getPrimaryKey() {
		return _comment.getPrimaryKey();
	}

	/**
	* Sets the primary key of this comment
	*
	* @param pk the primary key of this comment
	*/
	public void setPrimaryKey(long pk) {
		_comment.setPrimaryKey(pk);
	}

	/**
	* Gets the uuid of this comment.
	*
	* @return the uuid of this comment
	*/
	public java.lang.String getUuid() {
		return _comment.getUuid();
	}

	/**
	* Sets the uuid of this comment.
	*
	* @param uuid the uuid of this comment
	*/
	public void setUuid(java.lang.String uuid) {
		_comment.setUuid(uuid);
	}

	/**
	* Gets the comment id of this comment.
	*
	* @return the comment id of this comment
	*/
	public long getCommentId() {
		return _comment.getCommentId();
	}

	/**
	* Sets the comment id of this comment.
	*
	* @param commentId the comment id of this comment
	*/
	public void setCommentId(long commentId) {
		_comment.setCommentId(commentId);
	}

	/**
	* Gets the group id of this comment.
	*
	* @return the group id of this comment
	*/
	public long getGroupId() {
		return _comment.getGroupId();
	}

	/**
	* Sets the group id of this comment.
	*
	* @param groupId the group id of this comment
	*/
	public void setGroupId(long groupId) {
		_comment.setGroupId(groupId);
	}

	/**
	* Gets the company id of this comment.
	*
	* @return the company id of this comment
	*/
	public long getCompanyId() {
		return _comment.getCompanyId();
	}

	/**
	* Sets the company id of this comment.
	*
	* @param companyId the company id of this comment
	*/
	public void setCompanyId(long companyId) {
		_comment.setCompanyId(companyId);
	}

	/**
	* Gets the user id of this comment.
	*
	* @return the user id of this comment
	*/
	public long getUserId() {
		return _comment.getUserId();
	}

	/**
	* Sets the user id of this comment.
	*
	* @param userId the user id of this comment
	*/
	public void setUserId(long userId) {
		_comment.setUserId(userId);
	}

	/**
	* Gets the user uuid of this comment.
	*
	* @return the user uuid of this comment
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _comment.getUserUuid();
	}

	/**
	* Sets the user uuid of this comment.
	*
	* @param userUuid the user uuid of this comment
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_comment.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this comment.
	*
	* @return the user name of this comment
	*/
	public java.lang.String getUserName() {
		return _comment.getUserName();
	}

	/**
	* Sets the user name of this comment.
	*
	* @param userName the user name of this comment
	*/
	public void setUserName(java.lang.String userName) {
		_comment.setUserName(userName);
	}

	/**
	* Gets the create date of this comment.
	*
	* @return the create date of this comment
	*/
	public java.util.Date getCreateDate() {
		return _comment.getCreateDate();
	}

	/**
	* Sets the create date of this comment.
	*
	* @param createDate the create date of this comment
	*/
	public void setCreateDate(java.util.Date createDate) {
		_comment.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this comment.
	*
	* @return the modified date of this comment
	*/
	public java.util.Date getModifiedDate() {
		return _comment.getModifiedDate();
	}

	/**
	* Sets the modified date of this comment.
	*
	* @param modifiedDate the modified date of this comment
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_comment.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the class name of the model instance this comment is polymorphically associated with.
	*
	* @return the class name of the model instance this comment is polymorphically associated with
	*/
	public java.lang.String getClassName() {
		return _comment.getClassName();
	}

	/**
	* Gets the class name id of this comment.
	*
	* @return the class name id of this comment
	*/
	public long getClassNameId() {
		return _comment.getClassNameId();
	}

	/**
	* Sets the class name id of this comment.
	*
	* @param classNameId the class name id of this comment
	*/
	public void setClassNameId(long classNameId) {
		_comment.setClassNameId(classNameId);
	}

	/**
	* Gets the class p k of this comment.
	*
	* @return the class p k of this comment
	*/
	public long getClassPK() {
		return _comment.getClassPK();
	}

	/**
	* Sets the class p k of this comment.
	*
	* @param classPK the class p k of this comment
	*/
	public void setClassPK(long classPK) {
		_comment.setClassPK(classPK);
	}

	/**
	* Gets the content of this comment.
	*
	* @return the content of this comment
	*/
	public java.lang.String getContent() {
		return _comment.getContent();
	}

	/**
	* Sets the content of this comment.
	*
	* @param content the content of this comment
	*/
	public void setContent(java.lang.String content) {
		_comment.setContent(content);
	}

	/**
	* Gets the helpful of this comment.
	*
	* @return the helpful of this comment
	*/
	public boolean getHelpful() {
		return _comment.getHelpful();
	}

	/**
	* Determines if this comment is helpful.
	*
	* @return <code>true</code> if this comment is helpful; <code>false</code> otherwise
	*/
	public boolean isHelpful() {
		return _comment.isHelpful();
	}

	/**
	* Sets whether this comment is helpful.
	*
	* @param helpful the helpful of this comment
	*/
	public void setHelpful(boolean helpful) {
		_comment.setHelpful(helpful);
	}

	public boolean isNew() {
		return _comment.isNew();
	}

	public void setNew(boolean n) {
		_comment.setNew(n);
	}

	public boolean isCachedModel() {
		return _comment.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_comment.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _comment.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_comment.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _comment.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _comment.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_comment.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _comment.clone();
	}

	public int compareTo(com.liferay.knowledgebase.model.Comment comment) {
		return _comment.compareTo(comment);
	}

	public int hashCode() {
		return _comment.hashCode();
	}

	public com.liferay.knowledgebase.model.Comment toEscapedModel() {
		return _comment.toEscapedModel();
	}

	public java.lang.String toString() {
		return _comment.toString();
	}

	public java.lang.String toXmlString() {
		return _comment.toXmlString();
	}

	public Comment getWrappedComment() {
		return _comment;
	}

	private Comment _comment;
}