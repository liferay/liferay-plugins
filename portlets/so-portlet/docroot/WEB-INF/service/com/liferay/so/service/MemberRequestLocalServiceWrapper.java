/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.so.service;

/**
 * <a href="MemberRequestLocalServiceWrapper.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class MemberRequestLocalServiceWrapper
	implements MemberRequestLocalService {
	public MemberRequestLocalServiceWrapper(
		MemberRequestLocalService memberRequestLocalService) {
		_memberRequestLocalService = memberRequestLocalService;
	}

	public com.liferay.so.model.MemberRequest addMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest)
		throws com.liferay.portal.SystemException {
		return _memberRequestLocalService.addMemberRequest(memberRequest);
	}

	public com.liferay.so.model.MemberRequest createMemberRequest(
		long memberRequestId) {
		return _memberRequestLocalService.createMemberRequest(memberRequestId);
	}

	public void deleteMemberRequest(long memberRequestId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_memberRequestLocalService.deleteMemberRequest(memberRequestId);
	}

	public void deleteMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest)
		throws com.liferay.portal.SystemException {
		_memberRequestLocalService.deleteMemberRequest(memberRequest);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _memberRequestLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _memberRequestLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public com.liferay.so.model.MemberRequest getMemberRequest(
		long memberRequestId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _memberRequestLocalService.getMemberRequest(memberRequestId);
	}

	public java.util.List<com.liferay.so.model.MemberRequest> getMemberRequests(
		int start, int end) throws com.liferay.portal.SystemException {
		return _memberRequestLocalService.getMemberRequests(start, end);
	}

	public int getMemberRequestsCount()
		throws com.liferay.portal.SystemException {
		return _memberRequestLocalService.getMemberRequestsCount();
	}

	public com.liferay.so.model.MemberRequest updateMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest)
		throws com.liferay.portal.SystemException {
		return _memberRequestLocalService.updateMemberRequest(memberRequest);
	}

	public com.liferay.so.model.MemberRequest updateMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest, boolean merge)
		throws com.liferay.portal.SystemException {
		return _memberRequestLocalService.updateMemberRequest(memberRequest,
			merge);
	}

	public com.liferay.so.model.MemberRequest addMemberRequest(long userId,
		long groupId, long receiverUserId,
		java.lang.String receiverEmailAddress,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _memberRequestLocalService.addMemberRequest(userId, groupId,
			receiverUserId, receiverEmailAddress, themeDisplay);
	}

	public void addMemberRequests(long userId, long groupId,
		long[] receiverUserIds,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_memberRequestLocalService.addMemberRequests(userId, groupId,
			receiverUserIds, themeDisplay);
	}

	public void addMemberRequests(long userId, long groupId,
		java.lang.String[] emailAddresses,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_memberRequestLocalService.addMemberRequests(userId, groupId,
			emailAddresses, themeDisplay);
	}

	public com.liferay.so.model.MemberRequest getMemberRequest(long groupId,
		long receiverUserId, int status)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _memberRequestLocalService.getMemberRequest(groupId,
			receiverUserId, status);
	}

	public java.util.List<com.liferay.so.model.MemberRequest> getReceiverMemberRequest(
		long receiverUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		return _memberRequestLocalService.getReceiverMemberRequest(receiverUserId,
			start, end);
	}

	public int getReceiverMemberRequestCount(long receiverUserId)
		throws com.liferay.portal.SystemException {
		return _memberRequestLocalService.getReceiverMemberRequestCount(receiverUserId);
	}

	public java.util.List<com.liferay.so.model.MemberRequest> getReceiverStatusMemberRequest(
		long receiverUserId, int status, int start, int end)
		throws com.liferay.portal.SystemException {
		return _memberRequestLocalService.getReceiverStatusMemberRequest(receiverUserId,
			status, start, end);
	}

	public int getReceiverStatusMemberRequestCount(long receiverUserId,
		int status) throws com.liferay.portal.SystemException {
		return _memberRequestLocalService.getReceiverStatusMemberRequestCount(receiverUserId,
			status);
	}

	public boolean hasPendingMemberRequest(long groupId, long receiverUserId)
		throws com.liferay.portal.SystemException {
		return _memberRequestLocalService.hasPendingMemberRequest(groupId,
			receiverUserId);
	}

	public com.liferay.so.model.MemberRequest updateMemberRequest(long userId,
		long memberRequestId, int status) throws java.lang.Exception {
		return _memberRequestLocalService.updateMemberRequest(userId,
			memberRequestId, status);
	}

	public com.liferay.so.model.MemberRequest updateMemberRequest(
		java.lang.String key, long receiverUserId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _memberRequestLocalService.updateMemberRequest(key,
			receiverUserId);
	}

	public MemberRequestLocalService getWrappedMemberRequestLocalService() {
		return _memberRequestLocalService;
	}

	private MemberRequestLocalService _memberRequestLocalService;
}