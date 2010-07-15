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

package com.liferay.so.model;


/**
 * <p>
 * This class is a wrapper for {@link MemberRequest}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MemberRequest
 * @generated
 */
public class MemberRequestWrapper implements MemberRequest {
	public MemberRequestWrapper(MemberRequest memberRequest) {
		_memberRequest = memberRequest;
	}

	public long getPrimaryKey() {
		return _memberRequest.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_memberRequest.setPrimaryKey(pk);
	}

	public long getMemberRequestId() {
		return _memberRequest.getMemberRequestId();
	}

	public void setMemberRequestId(long memberRequestId) {
		_memberRequest.setMemberRequestId(memberRequestId);
	}

	public long getGroupId() {
		return _memberRequest.getGroupId();
	}

	public void setGroupId(long groupId) {
		_memberRequest.setGroupId(groupId);
	}

	public long getCompanyId() {
		return _memberRequest.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_memberRequest.setCompanyId(companyId);
	}

	public long getUserId() {
		return _memberRequest.getUserId();
	}

	public void setUserId(long userId) {
		_memberRequest.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequest.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_memberRequest.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _memberRequest.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_memberRequest.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _memberRequest.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_memberRequest.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _memberRequest.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_memberRequest.setModifiedDate(modifiedDate);
	}

	public java.lang.String getKey() {
		return _memberRequest.getKey();
	}

	public void setKey(java.lang.String key) {
		_memberRequest.setKey(key);
	}

	public long getReceiverUserId() {
		return _memberRequest.getReceiverUserId();
	}

	public void setReceiverUserId(long receiverUserId) {
		_memberRequest.setReceiverUserId(receiverUserId);
	}

	public java.lang.String getReceiverUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequest.getReceiverUserUuid();
	}

	public void setReceiverUserUuid(java.lang.String receiverUserUuid) {
		_memberRequest.setReceiverUserUuid(receiverUserUuid);
	}

	public long getInvitedRoleId() {
		return _memberRequest.getInvitedRoleId();
	}

	public void setInvitedRoleId(long invitedRoleId) {
		_memberRequest.setInvitedRoleId(invitedRoleId);
	}

	public long getInvitedTeamId() {
		return _memberRequest.getInvitedTeamId();
	}

	public void setInvitedTeamId(long invitedTeamId) {
		_memberRequest.setInvitedTeamId(invitedTeamId);
	}

	public int getStatus() {
		return _memberRequest.getStatus();
	}

	public void setStatus(int status) {
		_memberRequest.setStatus(status);
	}

	public com.liferay.so.model.MemberRequest toEscapedModel() {
		return _memberRequest.toEscapedModel();
	}

	public boolean isNew() {
		return _memberRequest.isNew();
	}

	public void setNew(boolean n) {
		_memberRequest.setNew(n);
	}

	public boolean isCachedModel() {
		return _memberRequest.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_memberRequest.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _memberRequest.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_memberRequest.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _memberRequest.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _memberRequest.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_memberRequest.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _memberRequest.clone();
	}

	public int compareTo(com.liferay.so.model.MemberRequest memberRequest) {
		return _memberRequest.compareTo(memberRequest);
	}

	public int hashCode() {
		return _memberRequest.hashCode();
	}

	public java.lang.String toString() {
		return _memberRequest.toString();
	}

	public java.lang.String toXmlString() {
		return _memberRequest.toXmlString();
	}

	public MemberRequest getWrappedMemberRequest() {
		return _memberRequest;
	}

	private MemberRequest _memberRequest;
}