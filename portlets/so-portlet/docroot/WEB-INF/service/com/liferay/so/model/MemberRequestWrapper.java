/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.so.model;

/**
 * <a href="MemberRequestWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
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

	public boolean setNew(boolean n) {
		return _memberRequest.setNew(n);
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