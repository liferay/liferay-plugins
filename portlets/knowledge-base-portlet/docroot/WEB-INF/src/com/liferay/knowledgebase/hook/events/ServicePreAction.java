/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.hook.events;

import com.liferay.knowledgebase.admin.util.AdminUtil;
import com.liferay.knowledgebase.util.Constants;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.security.auth.AuthTokenUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.ControlPanelEntry;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.WindowState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Peter Shin
 */
public class ServicePreAction extends Action {

	public static final String NAMESPACE = "spa_knowledge_base_portlet_";

	public void run(HttpServletRequest request, HttpServletResponse response) {
		if (_log.isDebugEnabled()) {
			_log.debug("Current url " + PortalUtil.getCurrentURL(request));
		}

		try {
			doRun(request, response);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void doRun(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String cmd = ParamUtil.getString(request, NAMESPACE + Constants.CMD);

		String redirect = null;

		if (cmd.equals(Constants.FIND_ARTICLE)) {
			redirect = getArticleURL(request);
		}
		else if (hasStalePortletAuthenticationToken(request)) {

			// A guest user that signs in will cause the original portlet
			// authentication token to become stale. See SessionAuthToken.

			redirect = setPortletAuthenticationToken(request);
		}

		if (Validator.isNotNull(redirect)) {
			response.sendRedirect(redirect);
		}
	}

	protected String getAdminPortletURL(
			long resourcePrimKey, ThemeDisplay themeDisplay)
		throws Exception {

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			themeDisplay.getCompanyId(), PortletKeys.KNOWLEDGE_BASE_ADMIN);

		ControlPanelEntry controlPanelEntry =
			portlet.getControlPanelEntryInstance();

		if (controlPanelEntry == null) {
			controlPanelEntry = (ControlPanelEntry)PortalClassInvoker.invoke(
				false, "com.liferay.portlet.DefaultControlPanelEntryFactory",
				"getInstance", new String[0]);
		}

		String controlPanelEntryCategory =
			portlet.getControlPanelEntryCategory();

		if (!controlPanelEntry.isVisible(
				portlet, controlPanelEntryCategory, themeDisplay)) {

			return null;
		}

		return AdminUtil.getArticleURL(
			themeDisplay.getScopeGroupId(), resourcePrimKey);
	}

	protected String getArticleURL(HttpServletRequest request)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourcePrimKey = ParamUtil.getLong(
			request, NAMESPACE + "resourcePrimKey");

		if (resourcePrimKey <= 0) {
			return null;
		}

		String articleURL = getArticleURL(false, resourcePrimKey, themeDisplay);

		if (articleURL == null) {
			articleURL = getArticleURL(true, resourcePrimKey, themeDisplay);
		}

		if (articleURL == null) {
			articleURL = getAdminPortletURL(resourcePrimKey, themeDisplay);
		}

		if (articleURL == null) {
			articleURL = getDisplayPortletURL(resourcePrimKey, request);
		}

		return articleURL;
	}

	protected String getArticleURL(
			boolean privateLayout, long resourcePrimKey,
			ThemeDisplay themeDisplay)
		throws Exception {

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			themeDisplay.getScopeGroupId(), privateLayout,
			LayoutConstants.TYPE_PORTLET);

		if (themeDisplay.getLayout().isTypePortlet()) {
			layouts = ListUtil.copy(layouts);

			layouts.remove(themeDisplay.getLayout());
			layouts.add(0, themeDisplay.getLayout());
		}

		String[] rootPortletIds = PortletKeys.KNOWLEDGE_BASE_PORTLETS;

		for (Layout layout : layouts) {
			LayoutTypePortlet layoutTypePortlet =
				(LayoutTypePortlet)layout.getLayoutType();

			List<Portlet> portlets = layoutTypePortlet.getAllPortlets();

			for (Portlet portlet : portlets) {
				String rootPortletId = PortletConstants.getRootPortletId(
					portlet.getPortletId());

				if (!ArrayUtil.contains(rootPortletIds, rootPortletId)) {
					continue;
				}

				if (KnowledgeBaseUtil.hasPortletPreferencesArticle(
						layout.getPlid(), portlet.getPortletId(),
						resourcePrimKey)) {

					return getArticleURL(
						layout.getPlid(), portlet.getPortletId(),
						resourcePrimKey, themeDisplay.getPortalURL());
				}
			}
		}

		return null;
	}

	protected String getArticleURL(
			long plid, String portletId, long resourcePrimKey, String portalURL)
		throws Exception {

		Layout layout = LayoutLocalServiceUtil.getLayout(plid);

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				layout, portletId, StringPool.BLANK);

		Map<String, String> preferencesMap =
			KnowledgeBaseUtil.initPortletPreferencesMap(portletId, preferences);

		String p_p_state = null;
		String jspPage = null;

		String rootPortletId = PortletConstants.getRootPortletId(portletId);

		if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_AGGREGATOR)) {
			p_p_state = preferencesMap.get("articleWindowState");
			jspPage = "/aggregator/view_article.jsp";
		}
		else if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) {
			p_p_state = WindowState.NORMAL.toString();
			jspPage = "/display/view_article.jsp";
		}
		else if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_LIST)) {
			p_p_state = preferencesMap.get("articleWindowState");
			jspPage = "/list/view_article.jsp";
		}
		else if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_SEARCH)) {
			p_p_state = WindowState.MAXIMIZED.toString();
			jspPage = "/search/view_article.jsp";
		}

		String articleURL = portalURL + PortalUtil.getLayoutActualURL(layout);

		String namespace = PortalUtil.getPortletNamespace(portletId);

		articleURL = HttpUtil.setParameter(articleURL, "p_p_id", portletId);
		articleURL = HttpUtil.setParameter(articleURL, "p_p_state", p_p_state);
		articleURL = HttpUtil.setParameter(
			articleURL, namespace + "jspPage", jspPage);
		articleURL = HttpUtil.setParameter(
			articleURL, namespace + "resourcePrimKey", resourcePrimKey);

		return articleURL;
	}

	protected String getDisplayPortletURL(
			long resourcePrimKey, HttpServletRequest request)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String articleURL = getArticleURL(
			themeDisplay.getPlid(), _PORTLET_ID, resourcePrimKey,
			themeDisplay.getPortalURL());

		String portletAddDefaultResourceCheckEnabled = PropsUtil.get(
			PropsKeys.PORTLET_ADD_DEFAULT_RESOURCE_CHECK_ENABLED);

		if (GetterUtil.getBoolean(portletAddDefaultResourceCheckEnabled)) {
			String p_p_auth = AuthTokenUtil.getToken(
				request, themeDisplay.getPlid(), _PORTLET_ID);

			articleURL = HttpUtil.setParameter(
				articleURL, "p_p_auth", p_p_auth);
		}

		articleURL = HttpUtil.setParameter(
			articleURL, "p_p_state", WindowState.MAXIMIZED.toString());

		return articleURL;
	}

	protected boolean hasStalePortletAuthenticationToken(
		HttpServletRequest request) {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String portletAddDefaultResourceCheckEnabled = PropsUtil.get(
			PropsKeys.PORTLET_ADD_DEFAULT_RESOURCE_CHECK_ENABLED);

		if (!GetterUtil.getBoolean(portletAddDefaultResourceCheckEnabled)) {
			return false;
		}

		if (!themeDisplay.isLifecycleRender()) {
			return false;
		}

		String portletId = PortalUtil.getPortletId(request);

		if ((portletId != null) && !portletId.equals(_PORTLET_ID)) {
			return false;
		}

		String request_p_p_auth = ParamUtil.getString(request, "p_p_auth");

		if (Validator.isNull(request_p_p_auth)) {
			return false;
		}

		String actual_p_p_auth = AuthTokenUtil.getToken(
			request, themeDisplay.getPlid(), _PORTLET_ID);

		if (request_p_p_auth.equals(actual_p_p_auth)) {
			return false;
		}

		return true;
	}

	protected String setPortletAuthenticationToken(HttpServletRequest request) {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String p_p_auth = AuthTokenUtil.getToken(
			request, themeDisplay.getPlid(), _PORTLET_ID);

		return HttpUtil.setParameter(
			themeDisplay.getURLCurrent(), "p_p_auth", p_p_auth);
	}

	private static final String _PORTLET_ID =
		PortletKeys.KNOWLEDGE_BASE_DISPLAY.concat(
			PortletConstants.INSTANCE_SEPARATOR).concat("0000");

	private static Log _log = LogFactoryUtil.getLog(ServicePreAction.class);

}