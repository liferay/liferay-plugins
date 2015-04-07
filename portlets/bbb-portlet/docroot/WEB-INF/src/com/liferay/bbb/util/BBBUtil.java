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

package com.liferay.bbb.util;

import com.liferay.bbb.model.BBBMeeting;
import com.liferay.bbb.model.BBBParticipant;
import com.liferay.bbb.model.BBBParticipantConstants;
import com.liferay.bbb.service.BBBMeetingLocalServiceUtil;
import com.liferay.bbb.service.BBBParticipantLocalServiceUtil;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.ContentUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.mail.internet.InternetAddress;

import javax.portlet.ActionRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shinn Lok
 */
public class BBBUtil {

	public static List<BBBParticipant> getBBBParticipants(
		ActionRequest actionRequest) {

		String bbbParticipantsIndexesString = actionRequest.getParameter(
			"bbbParticipantIndexes");

		if (bbbParticipantsIndexesString == null) {
			return Collections.emptyList();
		}

		List<BBBParticipant> bbbParticipants = new ArrayList<>();

		int[] bbbParticipantsIndexes = StringUtil.split(
			bbbParticipantsIndexesString, 0);

		for (int bbbParticipantsIndex : bbbParticipantsIndexes) {
			long bbbParticipantId = ParamUtil.getLong(
				actionRequest, "bbbParticipantId" + bbbParticipantsIndex);

			String name = ParamUtil.getString(
				actionRequest, "bbbParticipantName" + bbbParticipantsIndex);
			String emailAddress = ParamUtil.getString(
				actionRequest,
				"bbbParticipantEmailAddress" + bbbParticipantsIndex);

			if (Validator.isNull(name) && Validator.isNull(emailAddress)) {
				continue;
			}

			int type = ParamUtil.getInteger(
				actionRequest, "bbbParticipantType" + bbbParticipantsIndex);

			BBBParticipant bbbParticipant =
				BBBParticipantLocalServiceUtil.createBBBParticipant(
					bbbParticipantId);

			bbbParticipant.setName(name);
			bbbParticipant.setEmailAddress(emailAddress);
			bbbParticipant.setType(type);

			bbbParticipants.add(bbbParticipant);
		}

		return bbbParticipants;
	}

	public static String getHash(BBBParticipant bbbParticipant)
		throws Exception {

		BBBMeeting bbbMeeting = BBBMeetingLocalServiceUtil.getBBBMeeting(
			bbbParticipant.getBbbMeetingId());

		return DigesterUtil.digestHex(
			Digester.SHA_1, String.valueOf(bbbMeeting.getBbbMeetingId()),
			String.valueOf(bbbParticipant.getBbbParticipantId()),
			String.valueOf(bbbParticipant.getCreateDate()),
			bbbParticipant.getEmailAddress());
	}

	public static String getInvitationURL(
			BBBParticipant bbbParticipant, HttpServletRequest request)
		throws Exception {

		StringBundler sb = new StringBundler(7);

		Group group = GroupLocalServiceUtil.getGroup(
			bbbParticipant.getCompanyId(), GroupConstants.GUEST);

		Layout layout = LayoutLocalServiceUtil.getLayout(
			group.getDefaultPublicPlid());

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		sb.append(PortalUtil.getLayoutURL(layout, themeDisplay));

		sb.append(Portal.FRIENDLY_URL_SEPARATOR);
		sb.append("meetings");
		sb.append(StringPool.SLASH);
		sb.append(bbbParticipant.getBbbParticipantId());
		sb.append(StringPool.SLASH);
		sb.append(getHash(bbbParticipant));

		return sb.toString();
	}

	public static void sendEmail(
			long bbbMeetingId, ServiceContext serviceContext)
		throws Exception {

		BBBMeeting bbbMeeting = BBBMeetingLocalServiceUtil.getBBBMeeting(
			bbbMeetingId);

		List<BBBParticipant> bbbParticipants =
			BBBParticipantLocalServiceUtil.getBBBParticipants(bbbMeetingId);

		for (BBBParticipant bbbParticipant : bbbParticipants) {
			if (bbbParticipant.getStatus() ==
					BBBParticipantConstants.STATUS_INVITED) {

				continue;
			}

			sendEmail(bbbMeeting, bbbParticipant, serviceContext);

			BBBParticipantLocalServiceUtil.updateStatus(
				bbbParticipant.getBbbParticipantId(),
				BBBParticipantConstants.STATUS_INVITED);
		}
	}

	protected static void sendEmail(
			BBBMeeting bbbMeeting, BBBParticipant bbbParticipant,
			ServiceContext serviceContext)
		throws Exception {

		Company company = CompanyLocalServiceUtil.getCompany(
			serviceContext.getCompanyId());

		InternetAddress fromInternetAddress = new InternetAddress(
			company.getEmailAddress());
		InternetAddress toInternetAddress = new InternetAddress(
			bbbParticipant.getEmailAddress(), bbbParticipant.getName());

		String subject = ContentUtil.get(
			"com/liferay/bbb/dependencies" +
				"/meeting_scheduled_notification_subject.tmpl");

		subject = StringUtil.replace(
			subject,
			new String[] {
				"[$COMPANY_NAME$]", "[$MEETING_NAME$]"
			},
			new String[] {
				company.getName(), bbbMeeting.getName()
			});

		String body = ContentUtil.get(
			"com/liferay/bbb/dependencies" +
				"/meeting_scheduled_notification_body.tmpl");

		body = StringUtil.replace(
			body,
			new String[] {
				"[$MEETING_DESCRIPTION$]", "[$MEETING_NAME$]", "[$MEETING_URL$]"
			},
			new String[] {
				bbbMeeting.getDescription(), bbbMeeting.getName(),
				getInvitationURL(bbbParticipant, serviceContext.getRequest())
			});

		MailMessage mailMessage = new MailMessage(
			fromInternetAddress, toInternetAddress, subject, body, true);

		MailServiceUtil.sendEmail(mailMessage);
	}

}