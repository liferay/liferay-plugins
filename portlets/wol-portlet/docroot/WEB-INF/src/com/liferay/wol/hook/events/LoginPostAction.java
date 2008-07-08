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

package com.liferay.wol.hook.events;

import com.liferay.portal.LayoutFriendlyURLException;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <a href="LoginPostAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class LoginPostAction extends Action {

	public static final int ORGANIZATION_COMMUNITY_AFFILIATE_ID = 810671;

	public static final int ORGANIZATION_INDVIDUAL_CONTRIBUTOR_ID = 813865;

	public static final int ORGANIZATION_LIFERAY_INC_ID = 21;

	public static final int ORGANIZATION_SERVICES_PARTNER_ID = 810694;

	public static final int ORGANIZATION_TECHNOLOGY_PARTNER_ID = 810690;

	public static final int USER_DEFAULT_ADMIN_ID = 2;

	public static void addJiraUserId(User user) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(_GET_JIRA_USER_ID);

			ps.setString(1, user.getEmailAddress());

			rs = ps.executeQuery();

			while (rs.next()) {
				String jiraUserId = rs.getString(1);

				ExpandoValueLocalServiceUtil.addValue(
					User.class.getName(), "WOL", "jiraUserId", user.getUserId(),
					jiraUserId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	public static void addLayout(
			long userId, long groupId, String name, String friendlyURL)
		throws Exception {

		try {
			LayoutLocalServiceUtil.addLayout(
				userId, groupId, false,
				LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, name,
				StringPool.BLANK, StringPool.BLANK,
				LayoutConstants.TYPE_PORTLET, false, friendlyURL);
		}
		catch (LayoutFriendlyURLException lfurle) {
		}
	}

	public static void addOrganizationLayoutsAdvanced(Organization organization)
		throws Exception {

		if ((organization.getParentOrganizationId() !=
				ORGANIZATION_SERVICES_PARTNER_ID) &&
			(organization.getParentOrganizationId() !=
				ORGANIZATION_TECHNOLOGY_PARTNER_ID)) {

			return;
		}

		int publicLayoutsPageCount = organization.getPublicLayoutsPageCount();

		if (publicLayoutsPageCount > 0) {
			return;
		}

		long userId = USER_DEFAULT_ADMIN_ID;
		long groupId = organization.getGroup().getGroupId();

		addLayout(userId, groupId, "Profile", "/profile");
		addLayout(userId, groupId, "Blog", "/blog");

		updateLookAndFeel(groupId);
	}

	public static void addOrganizationLayoutsSimple(Organization organization)
		throws Exception {

		if (organization.getParentOrganizationId() !=
				ORGANIZATION_COMMUNITY_AFFILIATE_ID) {

			return;
		}

		int publicLayoutsPageCount = organization.getPublicLayoutsPageCount();

		if (publicLayoutsPageCount > 0) {
			return;
		}

		long userId = USER_DEFAULT_ADMIN_ID;
		long groupId = organization.getGroup().getGroupId();

		addLayout(userId, groupId, "Profile", "/profile");

		updateLookAndFeel(groupId);
	}

	public static void addOrganizationsLayouts(List<Organization> organizations)
		throws Exception {

		for (Organization organization : organizations) {
			addOrganizationLayoutsAdvanced(organization);
			addOrganizationLayoutsSimple(organization);
		}
	}

	public static void addUserLayoutsAdvanced(
			User user, List<Organization> organizations)
		throws Exception {

		boolean addUserLayoutsAdvanced = false;

		for (Organization organization : organizations) {
			if ((organization.getOrganizationId() ==
					ORGANIZATION_INDVIDUAL_CONTRIBUTOR_ID) ||
				(organization.getOrganizationId() ==
					ORGANIZATION_LIFERAY_INC_ID) ||
				(organization.getParentOrganizationId() ==
					ORGANIZATION_SERVICES_PARTNER_ID) ||
				(organization.getParentOrganizationId() ==
					ORGANIZATION_TECHNOLOGY_PARTNER_ID)) {

				addUserLayoutsAdvanced = true;

				break;
			}
		}

		if (!addUserLayoutsAdvanced) {
			return;
		}

		int publicLayoutsPageCount = user.getPublicLayoutsPageCount();

		if (publicLayoutsPageCount > 0) {
			return;
		}

		long userId = user.getUserId();
		long groupId = user.getGroup().getGroupId();

		addLayout(userId, groupId, "Profile", "/profile");
		addLayout(userId, groupId, "Friends", "/friends");
		addLayout(userId, groupId, "Blog", "/blog");
		addLayout(userId, groupId, "Documents", "/documents");

		updateLookAndFeel(groupId);
	}

	public static void addUserLayoutsSimple(User user) throws Exception {
		int publicLayoutsPageCount = user.getPublicLayoutsPageCount();

		if (publicLayoutsPageCount > 0) {
			return;
		}

		long userId = user.getUserId();
		long groupId = user.getGroup().getGroupId();

		addLayout(userId, groupId, "Profile", "/profile");
		addLayout(userId, groupId, "Friends", "/friends");

		updateLookAndFeel(groupId);
	}

	public static void updateLookAndFeel(long groupId) throws Exception {
		LayoutSetLocalServiceUtil.updateLookAndFeel(
			groupId, false, "liferayjedi_WAR_liferayjeditheme", "01", "",
			false);
	}

	public void run(HttpServletRequest req, HttpServletResponse res)
		throws ActionException {

		try {
			doRun(req, res);
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun(HttpServletRequest req, HttpServletResponse res)
		throws Exception {

		User user = PortalUtil.getUser(req);

		List<Organization> organizations = user.getOrganizations();

		addUserLayoutsAdvanced(user, organizations);
		addUserLayoutsSimple(user);

		addJiraUserId(user);

		addOrganizationsLayouts(organizations);

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		jsonObj.put("ipAddress", user.getLastLoginIP());
	}

	private static final String _GET_JIRA_USER_ID =
		"select jira.userbase.username from jira.userbase inner join " +
			"jira.propertyentry on jira.propertyentry.entity_id = " +
				"jira.userbase.id inner join jira.propertystring on " +
					"jira.propertystring.id = jira.propertyentry.id where " +
						"lower(jira.propertystring.propertyvalue) = ?";

}