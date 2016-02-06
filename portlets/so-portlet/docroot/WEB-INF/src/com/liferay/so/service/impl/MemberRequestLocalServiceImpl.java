/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.service.impl;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.MembershipRequestConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationManagerUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.so.exception.MemberRequestAlreadyUsedException;
import com.liferay.so.exception.MemberRequestInvalidUserException;
import com.liferay.so.invitemembers.util.InviteMembersConstants;
import com.liferay.so.model.MemberRequest;
import com.liferay.so.service.base.MemberRequestLocalServiceBaseImpl;
import com.liferay.so.util.PortletKeys;

import java.util.Date;
import java.util.List;

import javax.mail.internet.InternetAddress;

/**
 * @author Ryan Park
 * @author Jonathan Lee
 */
public class MemberRequestLocalServiceImpl
	extends MemberRequestLocalServiceBaseImpl {

	public MemberRequest addMemberRequest(
			long userId, long groupId, long receiverUserId,
			String receiverEmailAddress, long invitedRoleId, long invitedTeamId,
			ServiceContext serviceContext)
		throws PortalException {

		// Member request

		User user = userLocalService.getUserById(userId);

		try {
			User receiverUser = userLocalService.getUserByEmailAddress(
				serviceContext.getCompanyId(), receiverEmailAddress);

			receiverUserId = receiverUser.getUserId();
		}
		catch (NoSuchUserException nsue) {
		}

		Date now = new Date();

		long memberRequestId = counterLocalService.increment();

		MemberRequest memberRequest = memberRequestPersistence.create(
			memberRequestId);

		memberRequest.setGroupId(groupId);
		memberRequest.setCompanyId(user.getCompanyId());
		memberRequest.setUserId(userId);
		memberRequest.setUserName(user.getFullName());
		memberRequest.setCreateDate(now);
		memberRequest.setModifiedDate(now);
		memberRequest.setKey(PortalUUIDUtil.generate());
		memberRequest.setReceiverUserId(receiverUserId);
		memberRequest.setInvitedRoleId(invitedRoleId);
		memberRequest.setInvitedTeamId(invitedTeamId);
		memberRequest.setStatus(InviteMembersConstants.STATUS_PENDING);

		memberRequestPersistence.update(memberRequest);

		// Email

		try {
			sendEmail(receiverEmailAddress, memberRequest, serviceContext);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}

		// Notifications

		if (receiverUserId > 0) {
			sendNotificationEvent(memberRequest);
		}

		return memberRequest;
	}

	public void addMemberRequests(
			long userId, long groupId, long[] receiverUserIds,
			long invitedRoleId, long invitedTeamId,
			ServiceContext serviceContext)
		throws PortalException {

		for (long receiverUserId : receiverUserIds) {
			if (hasPendingMemberRequest(groupId, receiverUserId)) {
				continue;
			}

			User user = userLocalService.getUser(receiverUserId);

			String emailAddress = user.getEmailAddress();

			addMemberRequest(
				userId, groupId, receiverUserId, emailAddress, invitedRoleId,
				invitedTeamId, serviceContext);
		}
	}

	public void addMemberRequests(
			long userId, long groupId, String[] emailAddresses,
			long invitedRoleId, long invitedTeamId,
			ServiceContext serviceContext)
		throws PortalException {

		for (String emailAddress : emailAddresses) {
			if (!Validator.isEmailAddress(emailAddress)) {
				continue;
			}

			addMemberRequest(
				userId, groupId, 0, emailAddress, invitedRoleId, invitedTeamId,
				serviceContext);
		}
	}

	public MemberRequest getMemberRequest(
			long groupId, long receiverUserId, int status)
		throws PortalException {

		return memberRequestPersistence.findByG_R_S(
			groupId, receiverUserId, status);
	}

	public List<MemberRequest> getReceiverMemberRequest(
		long receiverUserId, int start, int end) {

		return memberRequestPersistence.findByReceiverUserId(receiverUserId);
	}

	public int getReceiverMemberRequestCount(long receiverUserId) {
		return memberRequestPersistence.countByReceiverUserId(receiverUserId);
	}

	public List<MemberRequest> getReceiverStatusMemberRequest(
		long receiverUserId, int status, int start, int end) {

		return memberRequestPersistence.findByR_S(
			receiverUserId, status, start, end);
	}

	public int getReceiverStatusMemberRequestCount(
		long receiverUserId, int status) {

		return memberRequestPersistence.countByR_S(receiverUserId, status);
	}

	public boolean hasPendingMemberRequest(long groupId, long receiverUserId) {
		MemberRequest memberRequest = memberRequestPersistence.fetchByG_R_S(
			groupId, receiverUserId, InviteMembersConstants.STATUS_PENDING);

		if (memberRequest != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public MemberRequest updateMemberRequest(
			long userId, long memberRequestId, int status)
		throws Exception {

		MemberRequest memberRequest = memberRequestPersistence.findByPrimaryKey(
			memberRequestId);

		validate(memberRequest, userId);

		memberRequest.setModifiedDate(new Date());
		memberRequest.setStatus(status);

		memberRequestPersistence.update(memberRequest);

		if (status == InviteMembersConstants.STATUS_ACCEPTED) {
			userLocalService.addGroupUsers(
				memberRequest.getGroupId(),
				new long[] {memberRequest.getReceiverUserId()});

			if (memberRequest.getInvitedRoleId() > 0) {
				userGroupRoleLocalService.addUserGroupRoles(
					new long[] {memberRequest.getReceiverUserId()},
					memberRequest.getGroupId(),
					memberRequest.getInvitedRoleId());
			}

			if (memberRequest.getInvitedTeamId() > 0) {
				userLocalService.addTeamUsers(
					memberRequest.getInvitedTeamId(),
					new long[] {memberRequest.getReceiverUserId()});
			}
		}

		return memberRequest;
	}

	public MemberRequest updateMemberRequest(String key, long receiverUserId)
		throws PortalException {

		MemberRequest memberRequest = memberRequestPersistence.findByKey(key);

		validate(memberRequest, 0);

		memberRequest.setModifiedDate(new Date());
		memberRequest.setReceiverUserId(receiverUserId);

		memberRequestPersistence.update(memberRequest);

		if (receiverUserId > 0) {
			sendNotificationEvent(memberRequest);
		}

		return memberRequest;
	}

	protected static String addParameterWithPortletNamespace(
		String url, String name, String value) {

		String portletId = HttpUtil.getParameter(url, "p_p_id", false);

		if (Validator.isNotNull(portletId)) {
			name = PortalUtil.getPortletNamespace(portletId) + name;
		}

		return HttpUtil.addParameter(url, name, value);
	}

	protected String getCreateAccountURL(
			MemberRequest memberRequest, ServiceContext serviceContext)
		throws PortalException {

		String createAccountURL = (String)serviceContext.getAttribute(
			"createAccountURL");

		if (Validator.isNull(createAccountURL)) {
			createAccountURL = serviceContext.getPortalURL();
		}

		if (!WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
				memberRequest.getCompanyId(),
				WorkflowConstants.DEFAULT_GROUP_ID, User.class.getName(), 0)) {

			String redirectURL = getRedirectURL(serviceContext);

			redirectURL = addParameterWithPortletNamespace(
				redirectURL, "key", memberRequest.getKey());

			createAccountURL = addParameterWithPortletNamespace(
				createAccountURL, "redirect", redirectURL);
		}

		return createAccountURL;
	}

	protected String getLoginURL(
		MemberRequest memberRequest, ServiceContext serviceContext) {

		String loginURL = (String)serviceContext.getAttribute("loginURL");

		if (Validator.isNull(loginURL)) {
			loginURL = serviceContext.getPortalURL();
		}

		String redirectURL = getRedirectURL(serviceContext);

		redirectURL = addParameterWithPortletNamespace(
			redirectURL, "key", memberRequest.getKey());

		return HttpUtil.addParameter(loginURL, "redirect", redirectURL);
	}

	protected String getRedirectURL(ServiceContext serviceContext) {
		String redirectURL = (String)serviceContext.getAttribute("redirectURL");

		if (Validator.isNull(redirectURL)) {
			redirectURL = serviceContext.getCurrentURL();
		}

		return redirectURL;
	}

	protected void sendEmail(
			String emailAddress, MemberRequest memberRequest,
			ServiceContext serviceContext)
		throws Exception {

		long companyId = memberRequest.getCompanyId();

		Group group = groupLocalService.getGroup(memberRequest.getGroupId());

		User user = userLocalService.getUser(memberRequest.getUserId());

		User receiverUser = null;

		if (memberRequest.getReceiverUserId() > 0) {
			receiverUser = userLocalService.getUser(
				memberRequest.getReceiverUserId());
		}

		String fromName = PrefsPropsUtil.getString(
			companyId, PropsKeys.ADMIN_EMAIL_FROM_NAME);
		String fromAddress = PrefsPropsUtil.getString(
			companyId, PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);

		String toName = StringPool.BLANK;
		String toAddress = emailAddress;

		if (receiverUser != null) {
			toName = receiverUser.getFullName();
		}

		String subject = StringUtil.read(
			getClassLoader(),
			"com/liferay/so/invitemembers/dependencies/subject.tmpl");

		String body = StringPool.BLANK;

		if (memberRequest.getReceiverUserId() > 0) {
			body = StringUtil.read(
				getClassLoader(),
				"com/liferay/so/invitemembers/dependencies/" +
					"existing_user_body.tmpl");
		}
		else {
			body = StringUtil.read(
				getClassLoader(),
				"com/liferay/so/invitemembers/dependencies/" +
					"new_user_body.tmpl");
		}

		subject = StringUtil.replace(
			subject,
			new String[] {
				"[$MEMBER_REQUEST_GROUP$]", "[$MEMBER_REQUEST_USER$]"
			},
			new String[] {
				group.getDescriptiveName(serviceContext.getLocale()),
				user.getFullName()
			});

		body = StringUtil.replace(
			body,
			new String[] {
				"[$ADMIN_ADDRESS$]", "[$ADMIN_NAME$]",
				"[$MEMBER_REQUEST_CREATE_ACCOUNT_URL$]",
				"[$MEMBER_REQUEST_GROUP$]", "[$MEMBER_REQUEST_LOGIN_URL$]",
				"[$MEMBER_REQUEST_USER$]"
			},
			new String[] {
				fromAddress, fromName,
				getCreateAccountURL(memberRequest, serviceContext),
				group.getDescriptiveName(serviceContext.getLocale()),
				getLoginURL(memberRequest, serviceContext), user.getFullName()
			});

		InternetAddress from = new InternetAddress(fromAddress, fromName);

		InternetAddress to = new InternetAddress(toAddress, toName);

		MailMessage mailMessage = new MailMessage(
			from, to, subject, body, true);

		MailServiceUtil.sendEmail(mailMessage);
	}

	protected void sendNotificationEvent(MemberRequest memberRequest)
		throws PortalException {

		if (UserNotificationManagerUtil.isDeliver(
				memberRequest.getReceiverUserId(),
				PortletKeys.SO_INVITE_MEMBERS, 0,
				MembershipRequestConstants.STATUS_PENDING,
				UserNotificationDeliveryConstants.TYPE_WEBSITE)) {

			JSONObject notificationEventJSONObject =
				JSONFactoryUtil.createJSONObject();

			notificationEventJSONObject.put(
				"classPK", memberRequest.getMemberRequestId());
			notificationEventJSONObject.put(
				"userId", memberRequest.getUserId());

			UserNotificationEventLocalServiceUtil.sendUserNotificationEvents(
				memberRequest.getReceiverUserId(),
				PortletKeys.SO_INVITE_MEMBERS,
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true,
				notificationEventJSONObject);
		}
	}

	protected void validate(MemberRequest memberRequest, long userId)
		throws PortalException {

		if (memberRequest.getStatus() !=
				InviteMembersConstants.STATUS_PENDING) {

			throw new MemberRequestAlreadyUsedException();
		}
		else if (memberRequest.getReceiverUserId() != userId) {
			throw new MemberRequestInvalidUserException();
		}
	}

}