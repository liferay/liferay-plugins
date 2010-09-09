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

	public long getPrimaryKey() {
		return _comment.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_comment.setPrimaryKey(pk);
	}

	public java.lang.String getUuid() {
		return _comment.getUuid();
	}

	public void setUuid(java.lang.String uuid) {
		_comment.setUuid(uuid);
	}

	public long getCommentId() {
		return _comment.getCommentId();
	}

	public void setCommentId(long commentId) {
		_comment.setCommentId(commentId);
	}

	public long getGroupId() {
		return _comment.getGroupId();
	}

	public void setGroupId(long groupId) {
		_comment.setGroupId(groupId);
	}

	public long getCompanyId() {
		return _comment.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_comment.setCompanyId(companyId);
	}

	public long getUserId() {
		return _comment.getUserId();
	}

	public void setUserId(long userId) {
		_comment.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _comment.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_comment.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _comment.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_comment.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _comment.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_comment.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _comment.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_comment.setModifiedDate(modifiedDate);
	}

	public java.lang.String getClassName() {
		return _comment.getClassName();
	}

	public long getClassNameId() {
		return _comment.getClassNameId();
	}

	public void setClassNameId(long classNameId) {
		_comment.setClassNameId(classNameId);
	}

	public long getClassPK() {
		return _comment.getClassPK();
	}

	public void setClassPK(long classPK) {
		_comment.setClassPK(classPK);
	}

	public java.lang.String getContent() {
		return _comment.getContent();
	}

	public void setContent(java.lang.String content) {
		_comment.setContent(content);
	}

	public boolean getHelpful() {
		return _comment.getHelpful();
	}

	public boolean isHelpful() {
		return _comment.isHelpful();
	}

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