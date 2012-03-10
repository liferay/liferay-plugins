/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.GroupNameException;
import com.liferay.portal.kernel.dao.search.DAOParamUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.GroupServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.LayoutSetPrototypeServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.permission.GroupPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.comparator.GroupNameComparator;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.so.service.FavoriteSiteLocalServiceUtil;
import com.liferay.so.sites.util.SitesUtil;
import com.liferay.so.util.WebKeys;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
				else if (e instanceof GroupNameException) {
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

		boolean directory = ParamUtil.getBoolean(resourceRequest, "directory");
		int end = ParamUtil.getInteger(resourceRequest, "end");
		String keywords = DAOParamUtil.getLike(resourceRequest, "keywords");
		int maxResultSize = ParamUtil.getInteger(
			resourceRequest, "maxResultSize", 10);
		String name = ParamUtil.getString(resourceRequest, "keywords");
		String searchTab = ParamUtil.getString(resourceRequest, "searchTab");
		int start = ParamUtil.getInteger(resourceRequest, "start");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject optionsJSONObject = JSONFactoryUtil.createJSONObject();

		optionsJSONObject.put("directory", directory);
		optionsJSONObject.put("end", end);
		optionsJSONObject.put("keywords", keywords);
		optionsJSONObject.put("maxResultSize", maxResultSize);
		optionsJSONObject.put("start", start);

		jsonObject.put("options", optionsJSONObject);

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		List<Group> groups = null;
		int groupsCount = 0;

		if (directory) {
			if (searchTab.equals("my-favorites")) {
				groups = SitesUtil.getFavoriteSitesGroups(
					themeDisplay.getUserId(), keywords, start, end);
				groupsCount = SitesUtil.getFavoriteSitesGroupsCount(
					themeDisplay.getUserId(), keywords);
			}
			else {
				LinkedHashMap<String, Object> params =
					new LinkedHashMap<String, Object>();

				if (searchTab.equals("my-sites")) {
					params.put("usersGroups", themeDisplay.getUserId());
				}
				else {
					List<Integer> types = new ArrayList<Integer>();

					types.add(GroupConstants.TYPE_SITE_OPEN);
					types.add(GroupConstants.TYPE_SITE_RESTRICTED);

					params.put("types", types);
				}

				groups = GroupLocalServiceUtil.search(
					themeDisplay.getCompanyId(), keywords, null, params, start,
					end, new GroupNameComparator(true));
				groupsCount = GroupLocalServiceUtil.searchCount(
					themeDisplay.getCompanyId(), keywords, null, params);
			}
		}
		else {
			if (searchTab.equals("my-sites")) {
				groups = SitesUtil.getVisibleSites(
					themeDisplay.getCompanyId(), themeDisplay.getUserId(),
					keywords, true, maxResultSize);
				groupsCount = SitesUtil.getVisibleSitesCount(
					themeDisplay.getCompanyId(), themeDisplay.getUserId(),
					keywords, true);
			}
			else if (searchTab.equals("my-favorites")) {
				groups = SitesUtil.getFavoriteSitesGroups(
					themeDisplay.getUserId(), keywords, 0, maxResultSize);
				groupsCount = SitesUtil.getFavoriteSitesGroupsCount(
					themeDisplay.getUserId(), keywords);
			}
			else {
				groups = SitesUtil.getVisibleSites(
					themeDisplay.getCompanyId(), themeDisplay.getUserId(),
					keywords, false, maxResultSize);
				groupsCount = SitesUtil.getVisibleSitesCount(
					themeDisplay.getCompanyId(), themeDisplay.getUserId(),
					keywords, false);
			}
		}

		jsonObject.put("count", groupsCount);

		LiferayPortletResponse liferayPortletResponse =
			(LiferayPortletResponse)resourceResponse;

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (Group group : groups) {
			JSONObject groupJSONObject = JSONFactoryUtil.createJSONObject();

			groupJSONObject.put("description", group.getDescription());
			groupJSONObject.put(
				"name", group.getDescriptiveName(themeDisplay.getLocale()));

			if (group.hasPrivateLayouts() || group.hasPublicLayouts()) {
				Layout layout = themeDisplay.getLayout();

				PortletURL portletURL = liferayPortletResponse.createActionURL(
					PortletKeys.MY_SITES);

				portletURL.setWindowState(WindowState.NORMAL);

				portletURL.setParameter("struts_action", "/my_sites/view");
				portletURL.setParameter(
					"groupId", String.valueOf(group.getGroupId()));
				portletURL.setParameter(
					"privateLayout", String.valueOf(!group.hasPublicLayouts()));

				groupJSONObject.put("url", portletURL.toString());
			}

			ExpandoBridge expandoBridge = group.getExpandoBridge();

			boolean socialOfficeEnabled = GetterUtil.getBoolean(
				expandoBridge.getAttribute("socialOfficeEnabled"));

			groupJSONObject.put("socialOfficeEnabled", socialOfficeEnabled);

			Layout layout = themeDisplay.getLayout();

			PortletURL siteAssignmentsPortletURL =
				liferayPortletResponse.createActionURL(PortletKeys.SITES_ADMIN);

			siteAssignmentsPortletURL.setWindowState(WindowState.NORMAL);

			siteAssignmentsPortletURL.setParameter(
				"struts_action", "/sites_admin/edit_site_assignments");
			siteAssignmentsPortletURL.setParameter(
				Constants.CMD, "group_users");
			siteAssignmentsPortletURL.setParameter(
				"redirect", themeDisplay.getURLCurrent());
			siteAssignmentsPortletURL.setParameter(
				"groupId", String.valueOf(group.getGroupId()));

			if (!GroupLocalServiceUtil.hasUserGroup(
					themeDisplay.getUserId(), group.getGroupId()) &&
				(group.getType() == GroupConstants.TYPE_SITE_OPEN)) {

				siteAssignmentsPortletURL.setParameter(
					"addUserIds", String.valueOf(themeDisplay.getUserId()));

				groupJSONObject.put(
					"joinUrl", siteAssignmentsPortletURL.toString());
			}
			else if (GroupLocalServiceUtil.hasUserGroup(
						themeDisplay.getUserId(), group.getGroupId())) {

				siteAssignmentsPortletURL.setParameter(
					"removeUserIds", String.valueOf(themeDisplay.getUserId()));

				groupJSONObject.put(
					"leaveUrl", siteAssignmentsPortletURL.toString());
			}

			if (GroupPermissionUtil.contains(
					themeDisplay.getPermissionChecker(), group.getGroupId(),
					ActionKeys.DELETE)) {

				PortletURL deletePortletURL =
					liferayPortletResponse.createActionURL(
						PortletKeys.SITES_ADMIN);

				deletePortletURL.setWindowState(WindowState.NORMAL);

				deletePortletURL.setParameter(
					"struts_action", "/sites_admin/edit_site");
				deletePortletURL.setParameter(Constants.CMD, Constants.DELETE);
				deletePortletURL.setParameter(
					"redirect", themeDisplay.getURLCurrent());
				deletePortletURL.setParameter(
					"groupId", String.valueOf(group.getGroupId()));

				groupJSONObject.put("deleteURL", deletePortletURL.toString());
			}

			PortletURL favoritePortletURL = resourceResponse.createActionURL();

			favoritePortletURL.setWindowState(WindowState.NORMAL);

			favoritePortletURL.setParameter(
				ActionRequest.ACTION_NAME, "updateFavorites");
			favoritePortletURL.setParameter(
				"redirect", themeDisplay.getURLCurrent());
			favoritePortletURL.setParameter(
				"groupId", String.valueOf(group.getGroupId()));

			if (!FavoriteSiteLocalServiceUtil.isFavoriteSite(
					themeDisplay.getUserId(), group.getGroupId())) {

				favoritePortletURL.setParameter(Constants.CMD, Constants.ADD);

				groupJSONObject.put(
					"favoriteURL", favoritePortletURL.toString());
			}
			else {
				favoritePortletURL.setParameter(
					Constants.CMD, Constants.DELETE);

				groupJSONObject.put(
					"unfavoriteURL", favoritePortletURL.toString());
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
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String id = resourceRequest.getResourceID();

			if (id.equals("getLayoutSetPrototypeDescription")) {
				getLayoutSetPrototypeDescription(
					resourceRequest, resourceResponse);
			}
			else if (id.equals("getSites")) {
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
			GroupServiceUtil.getGroup(groupId);
		}
		catch (Exception e) {
			jsonObject.put("result", "failure");

			writeJSON(actionRequest, actionResponse, jsonObject);

			return;
		}

		if (cmd.equals(Constants.ADD)) {
			FavoriteSiteLocalServiceUtil.addFavoriteSite(
				themeDisplay.getUserId(), groupId);
		}
		else if (cmd.equals(Constants.DELETE)) {
			FavoriteSiteLocalServiceUtil.deleteFavoriteSite(
				themeDisplay.getUserId(), groupId);
		}

		jsonObject.put("result", "success");

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	protected void doAddSite(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");
		int type = ParamUtil.getInteger(actionRequest, "type");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Group.class.getName(), actionRequest);

		Group group = GroupServiceUtil.addGroup(
			name, description, type, StringPool.BLANK, true, true,
			serviceContext);

		long layoutSetPrototypeId = ParamUtil.getLong(
			actionRequest, "layoutSetPrototypeId");

		boolean privateLayout = false;

		if (type != GroupConstants.TYPE_SITE_OPEN) {
			privateLayout = true;
		}

		long publicLayoutSetPrototypeId = 0;
		long privateLayoutSetPrototypeId = 0;

		if (privateLayout) {
			privateLayoutSetPrototypeId = layoutSetPrototypeId;
		}
		else {
			publicLayoutSetPrototypeId = layoutSetPrototypeId;
		}

		PortalClassInvoker.invoke(
			true, _updateLayoutSetPrototypesMethodKey, group,
			publicLayoutSetPrototypeId, privateLayoutSetPrototypeId,
			!privateLayout, privateLayout);

		LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
				group.getGroupId(), privateLayout);

		PortalClassInvoker.invoke(
			true, _mergeLayoutSetProtypeLayoutsMethodKey, group, layoutSet);

		long[] deleteLayoutIds = getLongArray(actionRequest, "deleteLayoutIds");

		List<Layout> layouts = new ArrayList<Layout>(deleteLayoutIds.length);

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

	protected void setCustomJspServletContextName(Group group)
		throws Exception {

		UnicodeProperties typeSettingsProperties =
			group.getTypeSettingsProperties();

		typeSettingsProperties.setProperty(
			"customJspServletContextName", "so-hook");

		GroupLocalServiceUtil.updateGroup(
			group.getGroupId(), typeSettingsProperties.toString());
	}

	private static final String _CLASS_NAME =
		"com.liferay.portlet.sites.util.SitesUtil";

	private static MethodKey _mergeLayoutSetProtypeLayoutsMethodKey =
		new MethodKey(
			_CLASS_NAME, "mergeLayoutSetProtypeLayouts", Group.class,
			LayoutSet.class);
	private static MethodKey _updateLayoutSetPrototypesMethodKey =
		new MethodKey(
			_CLASS_NAME, "updateLayoutSetPrototypesLinks", Group.class,
			long.class, long.class, boolean.class, boolean.class);

}