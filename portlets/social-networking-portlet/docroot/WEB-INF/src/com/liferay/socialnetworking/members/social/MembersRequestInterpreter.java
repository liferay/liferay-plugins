/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.socialnetworking.members.social;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialRequestInterpreter;
import com.liferay.portlet.social.model.SocialRequest;
import com.liferay.portlet.social.model.SocialRequestConstants;
import com.liferay.portlet.social.model.SocialRequestFeedEntry;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 * @author M Murali Krishna Reddy
 */
public class MembersRequestInterpreter extends BaseSocialRequestInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected SocialRequestFeedEntry doInterpret(
			SocialRequest request, ThemeDisplay themeDisplay)
		throws Exception {

		String creatorUserName = getUserName(request.getUserId(), themeDisplay);

		User creatorUser = UserLocalServiceUtil.getUserById(
			request.getUserId());

		int requestType = request.getType();

		Group group = null;

		String className = request.getClassName();

		if (className.equals(Group.class.getName())) {
			group = GroupLocalServiceUtil.getGroup(request.getClassPK());
		}
		else {
			Organization organization =
				OrganizationLocalServiceUtil.getOrganization(
					request.getClassPK());

			group = organization.getGroup();
		}

		// Title

		String title = StringPool.BLANK;

		if (requestType == MembersRequestKeys.ADD_MEMBER) {
			StringBuilder sb = new StringBuilder();

			sb.append("<a href=\"");
			sb.append(themeDisplay.getPortalURL());
			sb.append(themeDisplay.getPathFriendlyURLPublic());
			sb.append(StringPool.SLASH);
			sb.append(creatorUser.getScreenName());
			sb.append("/profile\">");
			sb.append(creatorUserName);
			sb.append("</a>");

			String creatorUserNameURL = sb.toString();

			sb = new StringBuilder();

			sb.append("<a href=\"");
			sb.append(themeDisplay.getPortalURL());
			sb.append(themeDisplay.getPathFriendlyURLPublic());
			sb.append(group.getFriendlyURL());
			sb.append("/profile\">");
			sb.append(group.getDescriptiveName(themeDisplay.getLocale()));
			sb.append("</a>");

			String organizationNameURL = sb.toString();

			title = themeDisplay.translate(
				"request-social-networking-summary-join-organization",
				new Object[] {creatorUserNameURL, organizationNameURL});
		}

		// Body

		String body = StringPool.BLANK;

		return new SocialRequestFeedEntry(title, body);
	}

	@Override
	protected boolean doProcessConfirmation(
		SocialRequest request, ThemeDisplay themeDisplay) {

		try {
			String className = request.getClassName();

			if (className.equals(Group.class.getName())) {
				UserLocalServiceUtil.addGroupUsers(
					request.getClassPK(), new long[] {request.getUserId()});
			}
			else {
				UserLocalServiceUtil.addOrganizationUsers(
					request.getClassPK(), new long[] {request.getUserId()});
			}

			SocialActivityLocalServiceUtil.addActivity(
				request.getUserId(), 0, className, request.getClassPK(),
				MembersActivityKeys.ADD_MEMBER, StringPool.BLANK, 0);

			processDuplicateRequestsFromUser(
				request, SocialRequestConstants.STATUS_PENDING);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return true;
	}

	private static final String[] _CLASS_NAMES = new String[] {
		Group.class.getName(), Organization.class.getName()
	};

	private static Log _log = LogFactoryUtil.getLog(
		MembersRequestInterpreter.class);

}