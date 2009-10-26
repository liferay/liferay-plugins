/**
 * Copyright (c) 2008-2009 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package	com.liferay.so.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.so.MemberRequestAlreadyUsedException;
import com.liferay.so.MemberRequestInvalidUserException;
import com.liferay.so.invitemembers.util.InviteMembersConstants;
import com.liferay.so.model.MemberRequest;
import com.liferay.so.service.base.MemberRequestLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

import javax.mail.internet.InternetAddress;

/**
 * <a href="MemberRequestLocalServiceImpl.java.html"><b><i>View	Source</i></b>
 * </a>
 *
 * @author Ryan	Park
 *
 */
public class MemberRequestLocalServiceImpl
	extends MemberRequestLocalServiceBaseImpl {

	public MemberRequest addMemberRequest(
			long userId, long groupId, long receiverUserId,
			String receiverEmailAddress, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUserById(userId);

		Date now = new Date();

		long memberRequestId = CounterLocalServiceUtil.increment();

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
		memberRequest.setStatus(InviteMembersConstants.STATUS_PENDING);

		memberRequestPersistence.update(memberRequest);

		// Email

		try {
			sendEmail(receiverEmailAddress, memberRequest, themeDisplay);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}

		return memberRequest;
	}

	public void addMemberRequests(
			long userId, long groupId, long[] receiverUserIds,
			ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		for (long receiverUserId : receiverUserIds) {
			if (hasPendingMemberRequest(groupId, receiverUserId)) {
				continue;
			}

			User user = UserLocalServiceUtil.getUser(receiverUserId);

			String emailAddress = user.getEmailAddress();

			addMemberRequest(
				userId, groupId, receiverUserId, emailAddress, themeDisplay);
		}
	}

	public void addMemberRequests(
			long userId, long groupId, String[] emailAddresses,
			ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		for (String emailAddress : emailAddresses) {
			if (!Validator.isEmailAddress(emailAddress)) {
				continue;
			}

			addMemberRequest(userId, groupId, 0, emailAddress, themeDisplay);
		}
	}

	public MemberRequest getMemberRequest(
			long groupId, long receiverUserId, int status)
		throws PortalException, SystemException {

		return memberRequestPersistence.findByG_R_S(
			groupId, receiverUserId, status);
	}

	public List<MemberRequest> getReceiverMemberRequest(
			long receiverUserId, int start, int end)
		throws SystemException {

		return memberRequestPersistence.findByReceiverUserId(receiverUserId);
	}

	public int getReceiverMemberRequestCount(long receiverUserId)
		throws SystemException {

		return memberRequestPersistence.countByReceiverUserId(receiverUserId);
	}

	public List<MemberRequest> getReceiverStatusMemberRequest(
			long receiverUserId, int status, int start, int end)
		throws SystemException {

		return memberRequestPersistence.findByR_S(
			receiverUserId, status, start, end);
	}

	public int getReceiverStatusMemberRequestCount(
			long receiverUserId, int status)
		throws SystemException {

		return memberRequestPersistence.countByR_S(receiverUserId, status);
	}

	public boolean hasPendingMemberRequest(long groupId, long receiverUserId)
		throws SystemException {

		MemberRequest memberRequest =
			memberRequestPersistence.fetchByG_R_S(
				groupId, receiverUserId, InviteMembersConstants.STATUS_PENDING);

		return (memberRequest != null);
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
			UserLocalServiceUtil.addGroupUsers(
				memberRequest.getGroupId(),
				new long[] {memberRequest.getReceiverUserId()});
		}

		return memberRequest;
	}

	public MemberRequest updateMemberRequest(String key, long receiverUserId)
		throws PortalException, SystemException {

		MemberRequest memberRequest = memberRequestPersistence.findByKey(key);

		validate(memberRequest, 0);

		memberRequest.setModifiedDate(new Date());
		memberRequest.setReceiverUserId(receiverUserId);

		memberRequestPersistence.update(memberRequest);

		return memberRequest;
	}

	protected String getMemberRequestCreateAccountURL(
			String key, ThemeDisplay themeDisplay)
		throws Exception {

		return getMemberRequestURL(key, themeDisplay) +
			"&p_p_id=58&_58_struts_action=%2Flogin%2Fcreate_account";
	}

	protected String getMemberRequestURL(String key, ThemeDisplay themeDisplay)
		throws Exception {

		Group group = GroupLocalServiceUtil.getGroup(
			themeDisplay.getCompanyId(), GroupConstants.GUEST);

		long plid = PortalUtil.getPlidFromPortletId(
			group.getGroupId(), PortletKeys.LOGIN);

		Layout layout = LayoutLocalServiceUtil.getLayout(plid);

		return PortalUtil.getLayoutFriendlyURL(layout, themeDisplay) +
			"?key=" + key;
	}

	protected void sendEmail(
			String emailAddress, MemberRequest memberRequest,
			ThemeDisplay themeDisplay)
		throws Exception {

		long companyId = memberRequest.getCompanyId();

		String url = getMemberRequestURL(memberRequest.getKey(), themeDisplay);
		String createAccountUrl = getMemberRequestCreateAccountURL(
			memberRequest.getKey(), themeDisplay);

		Group group =
			GroupLocalServiceUtil.getGroup(memberRequest.getGroupId());

		User user = UserLocalServiceUtil.getUser(memberRequest.getUserId());

		User receiverUser = null;

		if (memberRequest.getReceiverUserId() > 0) {
			receiverUser =
				UserLocalServiceUtil.getUser(memberRequest.getReceiverUserId());
		}

		String fromName = PrefsPropsUtil.getString(
			companyId, "admin.email.from.name");
		String fromAddress = PrefsPropsUtil.getString(
			companyId, "admin.email.from.address");

		String toName = StringPool.BLANK;
		String toAddress = emailAddress;

		if (receiverUser != null) {
			toName = receiverUser.getFullName();
		}

		ClassLoader classLoader = getClass().getClassLoader();

		String subject = StringUtil.read(
			classLoader,
			"com/liferay/so/invitemembers/dependencies/subject.tmpl");
		String body = StringPool.BLANK;

		if (memberRequest.getReceiverUserId() > 0) {
			body = StringUtil.read(
				classLoader,
				"com/liferay/so/invitemembers/dependencies/body.tmpl");
		}
		else {
			body = StringUtil.read(
				classLoader,
				"com/liferay/so/invitemembers/dependencies/" +
					"body_create_account.tmpl");
		}

		subject = StringUtil.replace(
			subject,
			new String[] {
				"[$MEMBER_REQUEST_GROUP$]",
				"[$MEMBER_REQUEST_USER$]"
			},
			new String[] {
				group.getDescriptiveName(),
				user.getFullName()
			});

		body = StringUtil.replace(
			body,
			new String[] {
				"[$ADMIN_ADDRESS$]",
				"[$ADMIN_NAME$]",
				"[$MEMBER_REQUEST_GROUP$]",
				"[$MEMBER_REQUEST_USER$]",
				"[$MEMBER_REQUEST_URL$]",
				"[$MEMBER_REQUEST_CREATE_ACCOUNT_URL$]"
			},
			new String[] {
				fromAddress,
				fromName,
				group.getDescriptiveName(),
				user.getFullName(),
				url,
				createAccountUrl
			});

		InternetAddress from = new InternetAddress(fromAddress, fromName);

		InternetAddress to = new InternetAddress(toAddress, toName);

		MailMessage mailMessage = new MailMessage(
			from, to, subject, body, true);

		MailServiceUtil.sendEmail(mailMessage);
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