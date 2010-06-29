/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.so.hook.events;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletApp;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletPreferencesThreadLocal;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.so.util.PortletPropsValues;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * <a href="StartupAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 *
 */
public class StartupAction extends SimpleAction {

	public void run(String[] ids) throws ActionException {
		try {
			doRun(GetterUtil.getLong(ids[0]));
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun(long companyId) throws Exception {
		try {
			PortletPreferencesThreadLocal.setStrict(false);

			setupRuntime(companyId);

			if (isFirstRun(companyId)) {
				return;
			}

			setupCompany(companyId);
			setupPermissions(companyId);
			setupLayouts(companyId);
			setupUsers(companyId);
		}
		finally {
			PortletPreferencesThreadLocal.setStrict(true);
		}
	}

	protected boolean isFirstRun(long companyId) throws Exception {
		Group group = GroupLocalServiceUtil.getGroup(
			companyId, GroupConstants.GUEST);

		Layout layout = LayoutLocalServiceUtil.getLayout(
			group.getGroupId(), false, 1);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		if (!layoutTypePortlet.hasPortletId("47")) {
			return true;
		}

		return false;
	}

	protected void setupCompany(long companyId) throws Exception {
		String authType = CompanyConstants.AUTH_TYPE_SN;
		boolean autoLogin = true;
		boolean sendPassword = true;
		boolean strangers = true;
		boolean strangersWithMx = true;
		boolean strangersVerify = false;
		boolean communityLogo = false;

		CompanyLocalServiceUtil.updateSecurity(
			companyId, authType, autoLogin, sendPassword, strangers,
			strangersWithMx, strangersVerify, communityLogo);
	}

	protected void setupLayouts(long companyId) throws Exception {
		Group group = GroupLocalServiceUtil.getGroup(
			companyId, GroupConstants.GUEST);

		Layout layout = LayoutLocalServiceUtil.getLayout(
			group.getGroupId(), false, 1);

		long defaultUserId = UserLocalServiceUtil.getDefaultUserId(
			layout.getCompanyId());

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(0, "1_column", false);
		layoutTypePortlet.removePortletId(defaultUserId, "47");

		LayoutLocalServiceUtil.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());

		LayoutLocalServiceUtil.updateFriendlyURL(layout.getPlid(), "/login");

		LayoutSetLocalServiceUtil.updateLookAndFeel(
			layout.getGroupId(), false, "so_WAR_sotheme", "01", "", false);
	}

	protected void setupPermissions(long companyId) throws Exception {

		// Community Member - Blogs

		Role role = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.COMMUNITY_MEMBER);

		long roleId = role.getRoleId();
		String name = "com.liferay.portlet.blogs";
		int scope = ResourceConstants.SCOPE_GROUP_TEMPLATE;
		String primKey = String.valueOf(GroupConstants.DEFAULT_PARENT_GROUP_ID);
		String[] actionIds = new String[] {ActionKeys.ADD_ENTRY};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Calendar

		name = "com.liferay.portlet.calendar";
		actionIds = new String[] {
			ActionKeys.ADD_EVENT, ActionKeys.EXPORT_ALL_EVENTS
		};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Calendar Event

		name = CalEvent.class.getName();
		actionIds = new String[] {ActionKeys.VIEW};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Community

		name = Group.class.getName();
		actionIds = new String[] {ActionKeys.ASSIGN_MEMBERS};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Document Library

		name = "com.liferay.portlet.documentlibrary";
		actionIds = new String[] {
			ActionKeys.ADD_FOLDER, ActionKeys.ADD_DOCUMENT
		};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Document Library Document

		name = DLFileEntry.class.getName();
		actionIds = new String[] {
			ActionKeys.ADD_DISCUSSION, ActionKeys.UPDATE, ActionKeys.VIEW
		};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Document Library Folder

		name = DLFolder.class.getName();
		actionIds = new String[] {
			ActionKeys.ADD_DOCUMENT, ActionKeys.ADD_SUBFOLDER, ActionKeys.VIEW
		};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Message Boards

		name = "com.liferay.portlet.messageboards";
		actionIds = new String[] {ActionKeys.ADD_CATEGORY};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Message Boards Category

		name = MBCategory.class.getName();
		actionIds = new String[] {
			ActionKeys.ADD_FILE, ActionKeys.ADD_MESSAGE,
			ActionKeys.ADD_SUBCATEGORY, ActionKeys.MOVE_THREAD,
			ActionKeys.REPLY_TO_MESSAGE, ActionKeys.SUBSCRIBE, ActionKeys.VIEW
		};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Message Boards Message

		name = MBMessage.class.getName();
		actionIds = new String[] {ActionKeys.VIEW};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Wiki

		name = "com.liferay.portlet.wiki";
		actionIds = new String[] {ActionKeys.ADD_NODE};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Wiki Node

		name = WikiNode.class.getName();
		actionIds = new String[] {
			ActionKeys.ADD_ATTACHMENT, ActionKeys.ADD_PAGE,
			ActionKeys.SUBSCRIBE, ActionKeys.VIEW
		};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Member - Wiki Page

		name = WikiPage.class.getName();
		actionIds = new String[] {
			ActionKeys.ADD_DISCUSSION, ActionKeys.DELETE, ActionKeys.SUBSCRIBE,
			ActionKeys.UPDATE, ActionKeys.VIEW
		};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Community Owner - Announcements

		role = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.COMMUNITY_OWNER);

		roleId = role.getRoleId();
		name = PortletKeys.ANNOUNCEMENTS;
		actionIds = new String[] {ActionKeys.ADD_ENTRY};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Power User - Add Community

		role = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.POWER_USER);

		roleId = role.getRoleId();
		name = PortletKeys.PORTAL;
		scope = ResourceConstants.SCOPE_COMPANY;
		primKey = String.valueOf(companyId);
		actionIds = new String[] {ActionKeys.ADD_COMMUNITY};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Power User - Directory

		name = PortletKeys.DIRECTORY;
		actionIds = new String[] {ActionKeys.VIEW};

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);

		// Power User - Document Library

		name = "com.liferay.portlet.documentlibrary";

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, name, scope, primKey, roleId, actionIds);
	}

	protected void setupRuntime(long companyId) throws Exception {
		Set<String> applicationsAllowed = SetUtil.fromArray(
			PortletPropsValues.APPLICATIONS_ALLOWED);
		Set<String> controlPanelItems = SetUtil.fromArray(
			PortletPropsValues.CONTROL_PANEL_ITEMS);

		List<Portlet> portlets = PortletLocalServiceUtil.getPortlets();

		for (Portlet portlet : portlets) {
			PortletApp portletApp = portlet.getPortletApp();

			if (portletApp.isWARFile()) {
				continue;
			}

			String portletId = portlet.getPortletId();

			if (applicationsAllowed.contains(portletId)) {
				portlet.setSystem(false);
			}
			else {
				portlet.setSystem(true);
			}

			if (!controlPanelItems.contains(portletId)) {
				portlet.setControlPanelEntryCategory(StringPool.BLANK);
			}
		}

		// Communities

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			PortletKeys.COMMUNITIES);

		portlet.setAddDefaultResource(true);

		// Directory

		portlet = PortletLocalServiceUtil.getPortletById(
			PortletKeys.DIRECTORY);

		portlet.setAddDefaultResource(true);

		// Clear cache

		PortletLocalServiceUtil.clearCache();
	}

	protected void setupUsers(long companyId) throws Exception {
		List<User> users = UserLocalServiceUtil.search(
			companyId, null, null, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			(OrderByComparator)null);

		for (User user : users) {
			UserLocalServiceUtil.deleteUser(user.getUserId());
		}

		long creatorUserId = 0;
		boolean autoPassword = false;
		String password1 = "admin";
		String password2 = password1;
		boolean autoScreenName = false;
		String screenName = "admin";
		String emailAddress = "admin@admin.com";
		long facebookId = 0;
		String openId = StringPool.BLANK;
		Locale locale = Locale.US;
		String firstName = "Admin";
		String middleName = StringPool.BLANK;
		String lastName = "Admin";
		int prefixId = 0;
		int suffixId = 0;
		boolean male = true;
		int birthdayMonth = Calendar.JANUARY;
		int birthdayDay = 1;
		int birthdayYear = 1970;
		String jobTitle = StringPool.BLANK;
		long[] groupIds = null;
		long[] organizationIds = null;
		long[] userGroupIds = null;

		Role adminRole = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.ADMINISTRATOR);

		Role powerUserRole = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.POWER_USER);

		long[] roleIds = new long[] {
			adminRole.getRoleId(), powerUserRole.getRoleId()
		};

		boolean sendEmail = false;
		ServiceContext serviceContext = null;

		UserLocalServiceUtil.addUser(
			creatorUserId, companyId, autoPassword, password1, password2,
			autoScreenName, screenName, emailAddress, facebookId, openId,
			locale, firstName, middleName, lastName, prefixId, suffixId, male,
			birthdayMonth, birthdayDay, birthdayYear, jobTitle, groupIds,
			organizationIds, roleIds, userGroupIds, sendEmail, serviceContext);
	}

}