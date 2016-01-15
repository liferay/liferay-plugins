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

package com.liferay.so.sites.portlet;

import com.liferay.portal.DuplicateGroupException;
import com.liferay.portal.GroupKeyException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ClassResolverUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.MembershipRequestConstants;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.GroupServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.LayoutSetPrototypeServiceUtil;
import com.liferay.portal.service.MembershipRequestLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.permission.GroupPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.so.service.FavoriteSiteLocalServiceUtil;
import com.liferay.so.service.SocialOfficeServiceUtil;
import com.liferay.so.sites.util.SitesUtil;
import com.liferay.so.util.GroupConstants;
import com.liferay.so.util.PortletKeys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.WindowState;

/**
 * @author Ryan Park
 * @author Jonathan Lee
 * @author Evan Thibodeau
 */
public class SitesPortlet extends MVCPortlet {

	public void addSite(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		if (Validator.isNotNull(redirect)) {
			doAddSite(actionRequest, actionResponse);
		}
		else {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			try {
				doAddSite(actionRequest, actionResponse);

				jsonObject.put("result", "success");
			}
			catch (Exception e) {
				jsonObject.put("result", "failure");

				String message = null;

				if (e instanceof DuplicateGroupException) {
					message = "please-enter-a-unique-name";
				}
				else if (e instanceof GroupKeyException) {
					message = "please-enter-a-valid-name";
				}
				else {
					message = "your-request-failed-to-complete";
				}

				ThemeDisplay themeDisplay =
					(ThemeDisplay)actionRequest.getAttribute(
						WebKeys.THEME_DISPLAY);

				jsonObject.put("message", themeDisplay.translate(message));
			}

			writeJSON(actionRequest, actionResponse, jsonObject);
		}
	}

	public void getLayoutSetPrototypeDescription(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		int layoutSetPrototypeId = ParamUtil.getInteger(
			resourceRequest, "layoutSetPrototypeId");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (layoutSetPrototypeId <= 0) {
			jsonObject.put("layoutSetPrototypeId", layoutSetPrototypeId);
			jsonObject.put("name", themeDisplay.translate("none"));
			jsonObject.put("description", StringPool.BLANK);

			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			jsonObject.put("layouts", jsonArray);
		}
		else {
			LayoutSetPrototype layoutSetPrototype =
				LayoutSetPrototypeServiceUtil.getLayoutSetPrototype(
					layoutSetPrototypeId);

			jsonObject.put("layoutSetPrototypeId", layoutSetPrototypeId);
			jsonObject.put(
				"name", layoutSetPrototype.getName(themeDisplay.getLocale()));
			jsonObject.put("description", layoutSetPrototype.getDescription());

			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			Group layoutSetPrototypeGroup = layoutSetPrototype.getGroup();

			List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
				layoutSetPrototypeGroup.getGroupId(), true, 0);

			for (Layout layout : layouts) {
				JSONObject layoutJSONObject =
					JSONFactoryUtil.createJSONObject();

				layoutJSONObject.put("layoutId", layout.getLayoutId());
				layoutJSONObject.put(
					"name", layout.getName(themeDisplay.getLocale()));

				jsonArray.put(layoutJSONObject);
			}

			jsonObject.put("layouts", jsonArray);
		}

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	public void getSites(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		boolean directory = ParamUtil.getBoolean(resourceRequest, "directory");
		int end = ParamUtil.getInteger(resourceRequest, "end", 10);
		String keywords = ParamUtil.getString(resourceRequest, "keywords");
		int maxResultSize = ParamUtil.getInteger(
			resourceRequest, "maxResultSize", 10);
		String searchTab = ParamUtil.getString(resourceRequest, "searchTab");
		int start = ParamUtil.getInteger(resourceRequest, "start");

		updateUserPreferences(themeDisplay, searchTab);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject optionsJSONObject = JSONFactoryUtil.createJSONObject();

		optionsJSONObject.put("directory", directory);
		optionsJSONObject.put("end", end);
		optionsJSONObject.put("keywords", keywords);
		optionsJSONObject.put("maxResultSize", maxResultSize);
		optionsJSONObject.put("searchTab", searchTab);
		optionsJSONObject.put("start", start);

		jsonObject.put("options", optionsJSONObject);

		List<Group> groups = null;
		int groupsCount = 0;

		if (searchTab.equals("my-sites")) {
			groups = SitesUtil.getVisibleSites(
				themeDisplay.getCompanyId(), themeDisplay.getUserId(), keywords,
				true, start, end);
			groupsCount = SitesUtil.getVisibleSitesCount(
				themeDisplay.getCompanyId(), themeDisplay.getUserId(), keywords,
				true);
		}
		else if (searchTab.equals("my-favorites")) {
			groups = SitesUtil.getFavoriteSitesGroups(
				themeDisplay.getUserId(), keywords, start, end);
			groupsCount = SitesUtil.getFavoriteSitesGroupsCount(
				themeDisplay.getUserId(), keywords);
		}
		else {
			groups = SitesUtil.getVisibleSites(
				themeDisplay.getCompanyId(), themeDisplay.getUserId(), keywords,
				false, start, end);
			groupsCount = SitesUtil.getVisibleSitesCount(
				themeDisplay.getCompanyId(), themeDisplay.getUserId(), keywords,
				false);
		}

		jsonObject.put("count", groupsCount);

		LiferayPortletResponse liferayPortletResponse =
			(LiferayPortletResponse)resourceResponse;

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (Group group : groups) {
			JSONObject groupJSONObject = JSONFactoryUtil.createJSONObject();

			groupJSONObject.put(
				"description", HtmlUtil.escape(group.getDescription()));
			groupJSONObject.put(
				"name",
				HtmlUtil.escape(
					group.getDescriptiveName(themeDisplay.getLocale())));

			boolean member = GroupLocalServiceUtil.hasUserGroup(
				themeDisplay.getUserId(), group.getGroupId());

			if (group.hasPrivateLayouts() && member) {
				groupJSONObject.put(
					"privateLayoutsURL",
					group.getDisplayURL(themeDisplay, true));
			}

			if (group.hasPublicLayouts()) {
				groupJSONObject.put(
					"publicLayoutsURL",
					group.getDisplayURL(themeDisplay, false));
			}

			boolean socialOfficeGroup =
				SocialOfficeServiceUtil.isSocialOfficeGroup(group.getGroupId());

			groupJSONObject.put("socialOfficeGroup", socialOfficeGroup);

			PortletURL siteAssignmentsPortletURL =
				PortletProviderUtil.getPortletURL(
					resourceRequest, MembershipRequest.class.getName(),
					PortletProvider.Action.EDIT);

			siteAssignmentsPortletURL.setParameter(
				"redirect", themeDisplay.getURLCurrent());
			siteAssignmentsPortletURL.setParameter(
				"groupId", String.valueOf(group.getGroupId()));
			siteAssignmentsPortletURL.setWindowState(WindowState.NORMAL);

			PermissionChecker permissionChecker =
				themeDisplay.getPermissionChecker();

			if (!member && (group.getType() == GroupConstants.TYPE_SITE_OPEN)) {
				siteAssignmentsPortletURL.setParameter(
					"addUserIds", String.valueOf(themeDisplay.getUserId()));

				groupJSONObject.put(
					"joinURL", siteAssignmentsPortletURL.toString());
			}
			else if (!member &&
					 (group.getType() == GroupConstants.TYPE_SITE_RESTRICTED)) {

				if (!MembershipRequestLocalServiceUtil.hasMembershipRequest(
						themeDisplay.getUserId(), group.getGroupId(),
						MembershipRequestConstants.STATUS_PENDING)) {

					PortletURL membershipRequestURL =
						liferayPortletResponse.createActionURL(
							PortletKeys.SITE_ADMIN);

					membershipRequestURL.setParameter(
						"javax.portlet.action", "postMembershipRequest");
					membershipRequestURL.setParameter(
						"redirect", themeDisplay.getURLCurrent());
					membershipRequestURL.setParameter(
						"groupId", String.valueOf(group.getGroupId()));

					User user = UserLocalServiceUtil.getUser(
						themeDisplay.getUserId());

					String comments = LanguageUtil.format(
						themeDisplay.getLocale(), "x-wishes-to-join-x",
						new Object[] {
							user.getFullName(), group.getDescriptiveName()
						},
						false);

					membershipRequestURL.setParameter("comments", comments);
					membershipRequestURL.setWindowState(WindowState.NORMAL);

					groupJSONObject.put(
						"requestUrl", membershipRequestURL.toString());
				}
				else {
					groupJSONObject.put("membershipRequested", true);
				}
			}
			else if (member &&
					 !isOrganizationOrUserGroupMember(
						 themeDisplay.getUserId(), group)) {

				siteAssignmentsPortletURL.setParameter(
					"removeUserIds", String.valueOf(themeDisplay.getUserId()));

				if ((group.getType() != GroupConstants.TYPE_SITE_PRIVATE) ||
					GroupPermissionUtil.contains(
						permissionChecker, group.getGroupId(),
						ActionKeys.ASSIGN_MEMBERS)) {

					groupJSONObject.put(
						"leaveURL", siteAssignmentsPortletURL.toString());
				}
			}

			if (GroupPermissionUtil.contains(
					permissionChecker, group.getGroupId(), ActionKeys.DELETE)) {

				if (group.getGroupId() == themeDisplay.getSiteGroupId()) {
					groupJSONObject.put("deleteURL", StringPool.FALSE);
				}
				else {
					PortletURL deletePortletURL =
						liferayPortletResponse.createActionURL(
							PortletKeys.SITE_ADMIN);

					deletePortletURL.setParameter(
						"javax.portlet.action", "deleteGroups");
					deletePortletURL.setParameter(
						"redirect", themeDisplay.getURLCurrent());
					deletePortletURL.setParameter(
						"groupId", String.valueOf(group.getGroupId()));
					deletePortletURL.setWindowState(WindowState.NORMAL);

					groupJSONObject.put(
						"deleteURL", deletePortletURL.toString());
				}
			}

			PortletURL favoritePortletURL = resourceResponse.createActionURL();

			favoritePortletURL.setParameter(
				ActionRequest.ACTION_NAME, "updateFavorites");
			favoritePortletURL.setParameter(
				"redirect", themeDisplay.getURLCurrent());
			favoritePortletURL.setParameter(
				"groupId", String.valueOf(group.getGroupId()));
			favoritePortletURL.setWindowState(WindowState.NORMAL);

			if (!member && !group.hasPublicLayouts()) {
				groupJSONObject.put("favoriteURL", StringPool.BLANK);
			}
			else {
				if (!FavoriteSiteLocalServiceUtil.isFavoriteSite(
						themeDisplay.getUserId(), group.getGroupId())) {

					favoritePortletURL.setParameter(
						Constants.CMD, Constants.ADD);

					groupJSONObject.put(
						"favoriteURL", favoritePortletURL.toString());
				}
				else {
					favoritePortletURL.setParameter(
						Constants.CMD, Constants.DELETE);

					groupJSONObject.put(
						"unfavoriteURL", favoritePortletURL.toString());
				}
			}

			jsonArray.put(groupJSONObject);
		}

		jsonObject.put("sites", jsonArray);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	public void hideNotice(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		Group group = user.getGroup();

		PortletPreferences portletPreferences =
			PortletPreferencesLocalServiceUtil.getPreferences(
				user.getCompanyId(), group.getGroupId(),
				PortletKeys.PREFS_OWNER_TYPE_GROUP, 0, "5_WAR_soportlet");

		portletPreferences.setValue("hide-notice", Boolean.TRUE.toString());

		portletPreferences.store();
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		try {
			String actionName = ParamUtil.getString(
				actionRequest, ActionRequest.ACTION_NAME);

			if (actionName.equals("addSite")) {
				addSite(actionRequest, actionResponse);
			}
			else {
				super.processAction(actionRequest, actionResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("getLayoutSetPrototypeDescription")) {
				getLayoutSetPrototypeDescription(
					resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("getSites")) {
				getSites(resourceRequest, resourceResponse);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void updateFavorites(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long groupId = ParamUtil.getLong(actionRequest, "groupId");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			if (cmd.equals(Constants.ADD)) {
				FavoriteSiteLocalServiceUtil.addFavoriteSite(
					themeDisplay.getUserId(), groupId);
			}
			else if (cmd.equals(Constants.DELETE)) {
				FavoriteSiteLocalServiceUtil.deleteFavoriteSites(
					themeDisplay.getUserId(), groupId);
			}
		}
		catch (Exception e) {
			jsonObject.put("result", "failure");

			writeJSON(actionRequest, actionResponse, jsonObject);

			return;
		}

		jsonObject.put("result", "success");

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	protected void doAddSite(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Map<Locale, String> nameMap = new HashMap<>();

		String name = ParamUtil.getString(actionRequest, "name");

		nameMap.put(themeDisplay.getLocale(), name);

		Map<Locale, String> descriptionMap = new HashMap<>();

		String description = ParamUtil.getString(actionRequest, "description");

		descriptionMap.put(themeDisplay.getLocale(), description);

		long layoutSetPrototypeId = ParamUtil.getLong(
			actionRequest, "layoutSetPrototypeId");

		int type = ParamUtil.getInteger(actionRequest, "type");

		boolean privateLayout = false;

		if (type == GroupConstants.TYPE_SITE_PRIVATE_RESTRICTED) {
			type = GroupConstants.TYPE_SITE_RESTRICTED;

			privateLayout = true;
		}
		else if (type == GroupConstants.TYPE_SITE_PUBLIC_RESTRICTED) {
			type = GroupConstants.TYPE_SITE_RESTRICTED;
		}
		else if (type == GroupConstants.TYPE_SITE_PRIVATE) {
			privateLayout = true;
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Group.class.getName(), actionRequest);

		Group group = GroupServiceUtil.addGroup(
			GroupConstants.DEFAULT_PARENT_GROUP_ID,
			GroupConstants.DEFAULT_LIVE_GROUP_ID, nameMap, descriptionMap, type,
			true, GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION,
			StringPool.BLANK, true, true, serviceContext);

		long publicLayoutSetPrototypeId = 0;
		long privateLayoutSetPrototypeId = 0;

		if (privateLayout) {
			privateLayoutSetPrototypeId = layoutSetPrototypeId;
		}
		else {
			publicLayoutSetPrototypeId = layoutSetPrototypeId;
		}

		PortalClassInvoker.invoke(
			_updateLayoutSetPrototypesMethodKey, group,
			publicLayoutSetPrototypeId, privateLayoutSetPrototypeId,
			!privateLayout, privateLayout);

		LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
			group.getGroupId(), privateLayout);

		PortalClassInvoker.invoke(
			_mergeLayoutSetPrototypeLayoutsMethodKey, group, layoutSet);

		long[] deleteLayoutIds = getLongArray(actionRequest, "deleteLayoutIds");

		List<Layout> layouts = new ArrayList<>(deleteLayoutIds.length);

		for (long deleteLayoutId : deleteLayoutIds) {
			Layout layout = LayoutLocalServiceUtil.getLayout(
				group.getGroupId(), privateLayout, deleteLayoutId);

			layouts.add(layout);
		}

		for (Layout layout : layouts) {
			LayoutLocalServiceUtil.deleteLayout(layout, true, serviceContext);
		}

		setCustomJspServletContextName(group);
	}

	protected long[] getLongArray(PortletRequest portletRequest, String name) {
		String value = portletRequest.getParameter(name);

		if (value == null) {
			return null;
		}

		return StringUtil.split(GetterUtil.getString(value), 0L);
	}

	protected boolean isOrganizationOrUserGroupMember(long userId, Group group)
		throws Exception {

		if (group.isOrganization()) {
			return true;
		}

		List<Organization> organizations =
			OrganizationLocalServiceUtil.getGroupOrganizations(
				group.getGroupId());

		for (Organization organization : organizations) {
			if (OrganizationLocalServiceUtil.hasUserOrganization(
					userId, organization.getOrganizationId())) {

				return true;
			}
		}

		List<UserGroup> userGroups =
			UserGroupLocalServiceUtil.getGroupUserGroups(group.getGroupId());

		for (UserGroup userGroup : userGroups) {
			if (UserGroupLocalServiceUtil.hasUserUserGroup(
					userId, userGroup.getUserGroupId())) {

				return true;
			}
		}

		return false;
	}

	protected void setCustomJspServletContextName(Group group)
		throws Exception {

		UnicodeProperties typeSettingsProperties =
			group.getTypeSettingsProperties();

		typeSettingsProperties.setProperty(
			"customJspServletContextName", "so-hook");

		GroupLocalServiceUtil.updateGroup(
			group.getGroupId(), typeSettingsProperties.toString());
	}

	protected void updateUserPreferences(
			ThemeDisplay themeDisplay, String searchTab)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesLocalServiceUtil.getPreferences(
				themeDisplay.getCompanyId(), themeDisplay.getUserId(),
				PortletKeys.PREFS_OWNER_TYPE_USER, LayoutConstants.DEFAULT_PLID,
				PortletKeys.SO_SITES);

		portletPreferences.setValue("defaultSearchTab", searchTab);

		portletPreferences.store();
	}

	private static final String _CLASS_NAME =
		"com.liferay.sites.kernel.util.SitesUtil";

	private static MethodKey _mergeLayoutSetPrototypeLayoutsMethodKey =
		new MethodKey(
			ClassResolverUtil.resolveByPortalClassLoader(_CLASS_NAME),
			"mergeLayoutSetPrototypeLayouts", Group.class, LayoutSet.class);
	private static MethodKey _updateLayoutSetPrototypesMethodKey =
		new MethodKey(
			ClassResolverUtil.resolveByPortalClassLoader(_CLASS_NAME),
			"updateLayoutSetPrototypesLinks", Group.class, long.class,
			long.class, boolean.class, boolean.class);

}