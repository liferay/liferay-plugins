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

package com.liferay.socialnetworking.summary.portlet;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.permission.UserPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.portlet.social.model.SocialRelationConstants;
import com.liferay.portlet.social.service.SocialRelationLocalServiceUtil;
import com.liferay.portlet.social.service.SocialRequestLocalServiceUtil;
import com.liferay.socialnetworking.friends.social.FriendsRequestKeys;
import com.liferay.socialnetworking.members.social.MembersRequestKeys;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.util.LinkedHashMap;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class SummaryPortlet extends MVCPortlet {

	public void addFriend(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Group group = GroupLocalServiceUtil.getGroup(
			themeDisplay.getScopeGroupId());

		User user = UserLocalServiceUtil.getUserById(group.getClassPK());

		SocialRequestLocalServiceUtil.addRequest(
			themeDisplay.getUserId(), 0, User.class.getName(),
			themeDisplay.getUserId(), FriendsRequestKeys.ADD_FRIEND,
			StringPool.BLANK, user.getUserId());
	}

	public void deleteFriend(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Group group = GroupLocalServiceUtil.getGroup(
			themeDisplay.getScopeGroupId());

		User user = UserLocalServiceUtil.getUserById(group.getClassPK());

		SocialRelationLocalServiceUtil.deleteRelation(
			themeDisplay.getUserId(), user.getUserId(),
			SocialRelationConstants.TYPE_BI_FRIEND);
	}

	public void joinGroup(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Group group = GroupLocalServiceUtil.getGroup(
			themeDisplay.getScopeGroupId());

		if (group.getType() == GroupConstants.TYPE_SITE_OPEN) {
			UserLocalServiceUtil.addGroupUsers(
				group.getGroupId(), new long[] {themeDisplay.getUserId()});
		}
		else {
			Role role = RoleLocalServiceUtil.getRole(
				themeDisplay.getCompanyId(), RoleConstants.SITE_ADMINISTRATOR);

			LinkedHashMap<String, Object> userParams =
				new LinkedHashMap<String, Object>();

			userParams.put(
				"userGroupRole",
				new Long[] {new Long(group.getGroupId()),
				new Long(role.getRoleId())});

			List<User> users = UserLocalServiceUtil.search(
				themeDisplay.getCompanyId(), null,
				WorkflowConstants.STATUS_APPROVED, userParams,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, (OrderByComparator) null);

			for (User user : users) {
				SocialRequestLocalServiceUtil.addRequest(
					themeDisplay.getUserId(), 0, Group.class.getName(),
					group.getGroupId(), MembersRequestKeys.ADD_MEMBER,
					StringPool.BLANK, user.getUserId());
			}
		}
	}

	public void joinOrganization(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Group group = GroupLocalServiceUtil.getGroup(
			themeDisplay.getScopeGroupId());

		Organization organization =
			OrganizationLocalServiceUtil.getOrganization(group.getClassPK());

		Role role = RoleLocalServiceUtil.getRole(
			themeDisplay.getCompanyId(), "Organization Administrator");

		LinkedHashMap<String, Object> userParams =
			new LinkedHashMap<String, Object>();

		userParams.put(
			"userGroupRole",
			new Long[] {new Long(group.getGroupId()),
			new Long(role.getRoleId())});

		List<User> users = UserLocalServiceUtil.search(
			themeDisplay.getCompanyId(), null,
			WorkflowConstants.STATUS_APPROVED, userParams,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, (OrderByComparator) null);

		for (User user : users) {
			SocialRequestLocalServiceUtil.addRequest(
				themeDisplay.getUserId(), 0, Organization.class.getName(),
				organization.getOrganizationId(), MembersRequestKeys.ADD_MEMBER,
				StringPool.BLANK, user.getUserId());
		}
	}

	public void leaveGroup(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		UserLocalServiceUtil.unsetGroupUsers(
			themeDisplay.getScopeGroupId(),
			new long[] {themeDisplay.getUserId()}, serviceContext);
	}

	public void leaveOrganization(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Group group = GroupLocalServiceUtil.getGroup(
			themeDisplay.getScopeGroupId());

		UserLocalServiceUtil.unsetOrganizationUsers(
			group.getClassPK(), new long[] {themeDisplay.getUserId()});
	}

	public void updateSummary(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		Group group = GroupLocalServiceUtil.getGroup(
			themeDisplay.getScopeGroupId());

		User user = null;

		if (group.isUser()) {
			user = UserLocalServiceUtil.getUserById(group.getClassPK());
		}
		else {
			return;
		}

		if (!UserPermissionUtil.contains(
				themeDisplay.getPermissionChecker(), user.getUserId(),
				ActionKeys.UPDATE)) {

			return;
		}

		String jobTitle = ParamUtil.getString(actionRequest, "jobTitle");

		UserLocalServiceUtil.updateJobTitle(user.getUserId(), jobTitle);

		String aboutMe = ParamUtil.getString(actionRequest, "aboutMe");

		ExpandoValueLocalServiceUtil.addValue(
			themeDisplay.getCompanyId(), User.class.getName(), "SN", "aboutMe",
			user.getUserId(), aboutMe);
	}

	private static Log _log = LogFactoryUtil.getLog(SummaryPortlet.class);

}