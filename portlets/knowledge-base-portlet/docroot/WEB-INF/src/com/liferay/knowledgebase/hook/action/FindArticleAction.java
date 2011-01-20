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

package com.liferay.knowledgebase.hook.action;

import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.admin.util.AdminUtil;
import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.permission.ArticlePermission;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.security.auth.AuthTokenUtil;
import com.liferay.portal.security.permission.ActionKeys;
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
public class FindArticleAction extends BaseStrutsAction {

	public String execute(
			StrutsAction originalStrutsAction, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourcePrimKey = ParamUtil.getLong(request, "resourcePrimKey");
		long selPlid = ParamUtil.getLong(request, "selPlid");

		if (!isValidPlid(selPlid)) {
			selPlid = themeDisplay.getPlid();
		}

		String redirect = null;

		Article article = getArticle(resourcePrimKey, themeDisplay);

		if (article == null) {
			redirect = getDisplayPortletURL(resourcePrimKey, selPlid, request);
		}

		if (redirect == null) {
			redirect = getArticleURL(false, selPlid, article, themeDisplay);
		}

		if (redirect == null) {
			redirect = getArticleURL(true, selPlid, article, themeDisplay);
		}

		if (redirect == null) {
			redirect = getAdminPortletURL(article, themeDisplay);
		}

		if (redirect == null) {
			redirect = getDisplayPortletURL(resourcePrimKey, selPlid, request);
		}

		response.sendRedirect(redirect);

		return null;
	}

	protected String getAdminPortletURL(
			Article article, ThemeDisplay themeDisplay)
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
			article.getGroupId(), article.getResourcePrimKey());
	}

	protected Article getArticle(
			long resourcePrimKey, ThemeDisplay themeDisplay)
		throws Exception {

		Article article = null;

		try {
			article = ArticleLocalServiceUtil.getLatestArticle(
				resourcePrimKey, WorkflowConstants.STATUS_ANY);
		}
		catch (NoSuchArticleException nsae) {
			return null;
		}

		if (!ArticlePermission.contains(
				themeDisplay.getPermissionChecker(), article,
				ActionKeys.VIEW)) {

			return null;
		}

		return article;
	}

	protected String getArticleURL(
			long resourcePrimKey, long selPlid, String selPortletId,
			ThemeDisplay themeDisplay)
		throws Exception {

		Layout selLayout = LayoutLocalServiceUtil.getLayout(selPlid);

		String selLayoutActualURL = PortalUtil.getLayoutActualURL(selLayout);

		String articleURL = themeDisplay.getPortalURL() + selLayoutActualURL;

		String namespace = PortalUtil.getPortletNamespace(selPortletId);

		articleURL = HttpUtil.setParameter(articleURL, "p_p_id", selPortletId);
		articleURL = HttpUtil.setParameter(
			articleURL, namespace + "resourcePrimKey", resourcePrimKey);

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				selLayout, selPortletId, StringPool.BLANK);

		Map<String, String> preferencesMap =
			KnowledgeBaseUtil.initPortletPreferencesMap(
				selPortletId, preferences);

		String p_p_state = null;
		String jspPage = null;

		String rootPortletId = PortletConstants.getRootPortletId(selPortletId);

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

		articleURL = HttpUtil.setParameter(articleURL, "p_p_state", p_p_state);
		articleURL = HttpUtil.setParameter(
			articleURL, namespace + "jspPage", jspPage);

		return articleURL;
	}

	protected String getArticleURL(
			boolean privateLayout, long selPlid, Article article,
			ThemeDisplay themeDisplay)
		throws Exception {

		Layout selLayout = LayoutLocalServiceUtil.getLayout(selPlid);

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			article.getGroupId(), privateLayout, LayoutConstants.TYPE_PORTLET);

		if ((selLayout.getGroupId() == article.getGroupId()) &&
			(selLayout.isTypePortlet())) {

			layouts = ListUtil.copy(layouts);

			layouts.remove(selLayout);
			layouts.add(0, selLayout);
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
						article.getResourcePrimKey())) {

					return getArticleURL(
						article.getResourcePrimKey(), layout.getPlid(),
						portlet.getPortletId(), themeDisplay);
				}
			}
		}

		return null;
	}

	protected String getDisplayPortletURL(
			long resourcePrimKey, long selPlid, HttpServletRequest request)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String selPortletId =
			PortletKeys.KNOWLEDGE_BASE_DISPLAY +
				PortletConstants.INSTANCE_SEPARATOR + "0000";

		String articleURL = getArticleURL(
			resourcePrimKey, selPlid, selPortletId, themeDisplay);

		articleURL = HttpUtil.setParameter(
			articleURL, "p_p_state", WindowState.MAXIMIZED.toString());

		String portletAddDefaultResourceCheckEnabled = PropsUtil.get(
			PropsKeys.PORTLET_ADD_DEFAULT_RESOURCE_CHECK_ENABLED);

		if (!GetterUtil.getBoolean(portletAddDefaultResourceCheckEnabled)) {
			return articleURL;
		}

		articleURL = HttpUtil.setParameter(
			articleURL, "p_p_auth",
			AuthTokenUtil.getToken(request, selPlid, selPortletId));

		return articleURL;
	}

	protected boolean isValidPlid(long selPlid) throws Exception {
		try {
			LayoutLocalServiceUtil.getLayout(selPlid);
		}
		catch (NoSuchLayoutException nsle) {
			return false;
		}

		return true;
	}

}