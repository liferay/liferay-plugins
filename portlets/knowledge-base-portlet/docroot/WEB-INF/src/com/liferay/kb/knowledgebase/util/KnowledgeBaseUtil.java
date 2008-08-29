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

package com.liferay.kb.knowledgebase.util;

import com.liferay.kb.util.PortletPrefsPropsUtil;
import com.liferay.kb.util.PortletPropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * <a href="KnowledgeBaseUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 *
 */
public class KnowledgeBaseUtil {

	public static String getEmailBody(
			long companyId, ClassLoader classLoader, boolean update)
		throws Exception {

		String name;

		if (update) {
			name = PortletPropsKeys.ADMIN_EMAIL_ARTICLE_UPDATED_BODY;
		}
		else {
			name = PortletPropsKeys.ADMIN_EMAIL_ARTICLE_ADDED_BODY;
		}

		return PortletPrefsPropsUtil.getContent(
			companyId, name, classLoader);
	}

	public static String getEmailSignature(
			long companyId, ClassLoader classLoader, boolean update)
		throws Exception {

		String name;

		if (update) {
			name = PortletPropsKeys.ADMIN_EMAIL_ARTICLE_UPDATED_SIGNATURE;
		}
		else {
			name = PortletPropsKeys.ADMIN_EMAIL_ARTICLE_ADDED_SIGNATURE;
		}

		return PortletPrefsPropsUtil.getContent(
			companyId, name, classLoader);
	}

	public static String getEmailSubject(
			long companyId, ClassLoader classLoader, boolean update)
		throws Exception {

		String name;

		if (update) {
			name = PortletPropsKeys.ADMIN_EMAIL_ARTICLE_UPDATED_SUBJECT;
		}
		else {
			name = PortletPropsKeys.ADMIN_EMAIL_ARTICLE_ADDED_SUBJECT;
		}

		return PortletPrefsPropsUtil.getContent(
			companyId, name, classLoader);
	}

	public static String getGroupName(Group group)
		throws Exception {

		String name = StringPool.BLANK;

		if (group.isCommunity()) {
			name = group.getName();
		}
		else if (group.isOrganization()) {
			Organization organization =
				OrganizationLocalServiceUtil.getOrganization(
					group.getClassPK());

			name = organization.getName();
		}
		else if (group.isUser() || group.isUserGroup()) {
			User user = UserLocalServiceUtil.getUserById(group.getClassPK());

			name = user.getFullName();
		}

		return name;
	}

}