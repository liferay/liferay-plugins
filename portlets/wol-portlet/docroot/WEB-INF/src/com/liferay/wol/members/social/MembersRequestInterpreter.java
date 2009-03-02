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

package com.liferay.wol.members.social;

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
import com.liferay.portlet.social.model.SocialRequestFeedEntry;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

/**
 * <a href="MembersRequestInterpreter.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author M Murali Krishna Reddy
 *
 */
public class MembersRequestInterpreter extends BaseSocialRequestInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	protected SocialRequestFeedEntry doInterpret(
			SocialRequest request, ThemeDisplay themeDisplay)
		throws Exception {

		String creatorUserName = getUserName(
			request.getUserId(), themeDisplay);

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
			sb.append(themeDisplay.getURLPortal());
			sb.append(themeDisplay.getPathFriendlyURLPublic());
			sb.append(StringPool.SLASH);
			sb.append(creatorUser.getScreenName());
			sb.append("/profile\">");
			sb.append(creatorUserName);
			sb.append("</a>");

			String creatorUserNameURL = sb.toString();

			sb = new StringBuilder();

			sb.append("<a href=\"");
			sb.append(themeDisplay.getURLPortal());
			sb.append(themeDisplay.getPathFriendlyURLPublic());
			sb.append(group.getFriendlyURL());
			sb.append("/profile\">");
			sb.append(group.getDescriptiveName());
			sb.append("</a>");

			String organizationNameURL = sb.toString();

			title = themeDisplay.translate(
				"request-wol-summary-join-organization",
				new Object[] {creatorUserNameURL, organizationNameURL});
		}

		// Body

		String body = StringPool.BLANK;

		return new SocialRequestFeedEntry(title, body);
	}

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
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return true;
	}

	private static final String[] _CLASS_NAMES = new String[] {
		Group.class.getName(), Organization.class.getName()
	};

	private static Log _log =
		LogFactoryUtil.getLog(MembersRequestInterpreter.class);

}