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

package com.liferay.so.service;


/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link MemberRequestLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MemberRequestLocalService
 * @generated
 */
public class MemberRequestLocalServiceWrapper
	implements MemberRequestLocalService {
	public MemberRequestLocalServiceWrapper(
		MemberRequestLocalService memberRequestLocalService) {
		_memberRequestLocalService = memberRequestLocalService;
	}

	public com.liferay.so.model.MemberRequest addMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.addMemberRequest(memberRequest);
	}

	public com.liferay.so.model.MemberRequest createMemberRequest(
		long memberRequestId) {
		return _memberRequestLocalService.createMemberRequest(memberRequestId);
	}

	public void deleteMemberRequest(long memberRequestId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_memberRequestLocalService.deleteMemberRequest(memberRequestId);
	}

	public void deleteMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest)
		throws com.liferay.portal.kernel.exception.SystemException {
		_memberRequestLocalService.deleteMemberRequest(memberRequest);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.so.model.MemberRequest getMemberRequest(
		long memberRequestId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.getMemberRequest(memberRequestId);
	}

	public java.util.List<com.liferay.so.model.MemberRequest> getMemberRequests(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.getMemberRequests(start, end);
	}

	public int getMemberRequestsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.getMemberRequestsCount();
	}

	public com.liferay.so.model.MemberRequest updateMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.updateMemberRequest(memberRequest);
	}

	public com.liferay.so.model.MemberRequest updateMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.updateMemberRequest(memberRequest,
			merge);
	}

	public com.liferay.so.model.MemberRequest addMemberRequest(long userId,
		long groupId, long receiverUserId,
		java.lang.String receiverEmailAddress, long invitedRoleId,
		long invitedTeamId, com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.addMemberRequest(userId, groupId,
			receiverUserId, receiverEmailAddress, invitedRoleId, invitedTeamId,
			themeDisplay);
	}

	public void addMemberRequests(long userId, long groupId,
		long[] receiverUserIds, long invitedRoleId, long invitedTeamId,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_memberRequestLocalService.addMemberRequests(userId, groupId,
			receiverUserIds, invitedRoleId, invitedTeamId, themeDisplay);
	}

	public void addMemberRequests(long userId, long groupId,
		java.lang.String[] emailAddresses, long invitedRoleId,
		long invitedTeamId, com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_memberRequestLocalService.addMemberRequests(userId, groupId,
			emailAddresses, invitedRoleId, invitedTeamId, themeDisplay);
	}

	public com.liferay.so.model.MemberRequest getMemberRequest(long groupId,
		long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.getMemberRequest(groupId,
			receiverUserId, status);
	}

	public java.util.List<com.liferay.so.model.MemberRequest> getReceiverMemberRequest(
		long receiverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.getReceiverMemberRequest(receiverUserId,
			start, end);
	}

	public int getReceiverMemberRequestCount(long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.getReceiverMemberRequestCount(receiverUserId);
	}

	public java.util.List<com.liferay.so.model.MemberRequest> getReceiverStatusMemberRequest(
		long receiverUserId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.getReceiverStatusMemberRequest(receiverUserId,
			status, start, end);
	}

	public int getReceiverStatusMemberRequestCount(long receiverUserId,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.getReceiverStatusMemberRequestCount(receiverUserId,
			status);
	}

	public boolean hasPendingMemberRequest(long groupId, long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
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
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.updateMemberRequest(key,
			receiverUserId);
	}

	public MemberRequestLocalService getWrappedMemberRequestLocalService() {
		return _memberRequestLocalService;
	}

	private MemberRequestLocalService _memberRequestLocalService;
}