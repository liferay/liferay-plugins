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
import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.permission.ArticlePermission;
import com.liferay.knowledgebase.util.ActionKeys;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
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
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.PortletURLFactoryUtil;

import java.util.List;

import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Peter Shin
 */
public class FindArticleAction extends BaseStrutsAction {

	public static final String PORTLET_ID =
		PortletKeys.KNOWLEDGE_BASE_ARTICLE +
			PortletConstants.INSTANCE_SEPARATOR + "0000";

	public String execute(
			StrutsAction originalStrutsAction, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		long plid = ParamUtil.getLong(request, "plid");
		long resourcePrimKey = ParamUtil.getLong(request, "resourcePrimKey");
		boolean maximized = ParamUtil.getBoolean(request, "maximized");

		if (!isValidPlid(plid)) {
			plid = themeDisplay.getPlid();
		}

		PortletURL portletURL = null;

		Article article = getArticle(resourcePrimKey);

		if (article == null) {
			portletURL = getDisplayPortletURL(plid, request);
		}

		if (portletURL == null) {
			portletURL = getArticleURL(plid, false, article, request);
		}

		if (portletURL == null) {
			portletURL = getArticleURL(plid, true, article, request);
		}

		if (portletURL == null) {
			portletURL = getDisplayPortletURL(plid, request);
		}

		if (maximized) {
			portletURL.setWindowState(LiferayWindowState.MAXIMIZED);
		}

		response.sendRedirect(portletURL.toString());

		return null;
	}

	protected Article getArticle(long resourcePrimKey) throws Exception {
		Article article = null;

		try {
			article = ArticleLocalServiceUtil.getLatestArticle(
				resourcePrimKey, WorkflowConstants.STATUS_ANY);
		}
		catch (NoSuchArticleException nsae) {
			return null;
		}

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (!ArticlePermission.contains(
				permissionChecker, article, ActionKeys.VIEW)) {

			return null;
		}

		return article;
	}

	protected PortletURL getArticleURL(
			long plid, String portletId, HttpServletRequest request)
		throws Exception {

		long resourcePrimKey = ParamUtil.getLong(request, "resourcePrimKey");

		String jspPage = null;

		String rootPortletId = PortletConstants.getRootPortletId(portletId);

		if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_ARTICLE)) {
			jspPage = "/article/view_article.jsp";
		}
		else if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) {
			jspPage = "/display/view_article.jsp";
		}

		PortletURL portletURL = PortletURLFactoryUtil.create(
			request, portletId, plid, PortletRequest.RENDER_PHASE);

		portletURL.setWindowState(LiferayWindowState.NORMAL);
		portletURL.setPortletMode(PortletMode.VIEW);

		portletURL.setParameter("jspPage", jspPage);
		portletURL.setParameter(
			"resourcePrimKey", String.valueOf(resourcePrimKey));

		return portletURL;
	}

	protected PortletURL getArticleURL(
			long plid, boolean privateLayout, Article article,
			HttpServletRequest request)
		throws Exception {

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			article.getGroupId(), privateLayout, LayoutConstants.TYPE_PORTLET);

		Layout selLayout = LayoutLocalServiceUtil.getLayout(plid);

		if ((selLayout.getGroupId() == article.getGroupId()) &&
			(selLayout.isTypePortlet())) {

			layouts = ListUtil.copy(layouts);

			layouts.remove(selLayout);
			layouts.add(0, selLayout);
		}

		for (Layout layout : layouts) {
			LayoutTypePortlet layoutTypePortlet =
				(LayoutTypePortlet)layout.getLayoutType();

			List<Portlet> portlets = layoutTypePortlet.getAllPortlets();

			for (Portlet portlet : portlets) {
				String rootPortletId = PortletConstants.getRootPortletId(
					portlet.getPortletId());

				if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) {
					return getArticleURL(
						layout.getPlid(), portlet.getPortletId(), request);
				}

				if (!rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_ARTICLE)) {
					continue;
				}

				PortletPreferences preferences =
					PortletPreferencesFactoryUtil.getPortletSetup(
						layout, portlet.getPortletId(), StringPool.BLANK);

				long resourcePrimKey = GetterUtil.getLong(
					preferences.getValue("resourcePrimKey", null));

				Article selArticle = null;

				try {
					selArticle = ArticleLocalServiceUtil.getLatestArticle(
						resourcePrimKey, WorkflowConstants.STATUS_ANY);
				}
				catch (NoSuchArticleException nsae) {
					continue;
				}

				long rootResourcePrimKey = selArticle.getRootResourcePrimKey();

				if (article.getRootResourcePrimKey() == rootResourcePrimKey) {
					return getArticleURL(
						layout.getPlid(), portlet.getPortletId(), request);
				}
			}
		}

		return null;
	}

	protected PortletURL getDisplayPortletURL(
			long plid, HttpServletRequest request)
		throws Exception {

		PortletURL portletURL = getArticleURL(plid, PORTLET_ID, request);

		portletURL.setWindowState(LiferayWindowState.MAXIMIZED);

		if (!_PORTLET_ADD_DEFAULT_RESOURCE_CHECK_ENABLED) {
			return portletURL;
		}

		portletURL.setParameter(
			"p_p_auth", AuthTokenUtil.getToken(request, plid, PORTLET_ID));

		return portletURL;
	}

	protected boolean isValidPlid(long plid) throws Exception {
		try {
			LayoutLocalServiceUtil.getLayout(plid);
		}
		catch (NoSuchLayoutException nsle) {
			return false;
		}

		return true;
	}

	private static final boolean _PORTLET_ADD_DEFAULT_RESOURCE_CHECK_ENABLED =
		GetterUtil.getBoolean(
			PropsUtil.get(
				PropsKeys.PORTLET_ADD_DEFAULT_RESOURCE_CHECK_ENABLED));

}