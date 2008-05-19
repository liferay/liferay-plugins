/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.util.StringMaker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialRequestInterpreter;
import com.liferay.portlet.social.model.SocialRequest;
import com.liferay.portlet.social.model.SocialRequestFeedEntry;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="MembersRequestInterpreter.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
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

		// Title

		Organization organization =
			OrganizationLocalServiceUtil.getOrganization(request.getClassPK());

		Group group = organization.getGroup();

		String title = StringPool.BLANK;

		if (requestType == MembersRequestKeys.ADD_MEMBER) {
			StringMaker sm = new StringMaker();

			sm.append("<a href=\"");
			sm.append(themeDisplay.getURLPortal());
			sm.append(themeDisplay.getPathFriendlyURLPublic());
			sm.append(StringPool.SLASH);
			sm.append(creatorUser.getScreenName());
			sm.append("/profile\">");
			sm.append(creatorUserName);
			sm.append("</a>");

			String creatorUserNameURL = sm.toString();

			sm = new StringMaker();

			sm.append("<a href=\"");
			sm.append(themeDisplay.getURLPortal());
			sm.append(themeDisplay.getPathFriendlyURLPublic());
			sm.append(StringPool.SLASH);
			sm.append(group.getFriendlyURL());
			sm.append("/profile\">");
			sm.append(organization.getName());
			sm.append("</a>");

			String organizationNameURL = sm.toString();

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
			UserLocalServiceUtil.addOrganizationUsers(
				request.getClassPK(), new long[] {request.getUserId()});

			SocialActivityLocalServiceUtil.addActivity(
				request.getUserId(), 0, Organization.class.getName(),
				request.getClassPK(), MembersActivityKeys.ADD_MEMBER,
				StringPool.BLANK, 0);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return true;
	}

	private static final String[] _CLASS_NAMES = new String[] {
		Organization.class.getName()
	};

	private static Log _log =
		LogFactory.getLog(MembersRequestInterpreter.class);

}