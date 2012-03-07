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

package com.liferay.knowledgebase.hook.action;

import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.admin.util.AdminUtil;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.permission.KBArticlePermission;
import com.liferay.knowledgebase.util.ActionKeys;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.ArrayUtil;
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
public class FindKBArticleAction extends BaseStrutsAction {

	@Override
	public String execute(
			StrutsAction originalStrutsAction, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		long plid = ParamUtil.getLong(request, "plid");
		long resourcePrimKey = ParamUtil.getLong(request, "resourcePrimKey");
		int status = ParamUtil.getInteger(
			request, "status", WorkflowConstants.STATUS_APPROVED);
		boolean maximized = ParamUtil.getBoolean(request, "maximized");

		if (!isValidPlid(plid)) {
			plid = themeDisplay.getPlid();
		}

		PortletURL portletURL = null;

		KBArticle kbArticle = getKBArticle(resourcePrimKey, status);

		if (kbArticle == null) {
			portletURL = getDynamicPortletURL(plid, status, request);
		}

		if (status != WorkflowConstants.STATUS_APPROVED) {
			portletURL = getDynamicPortletURL(plid, status, request);
		}

		if (portletURL == null) {
			portletURL = getKBArticleURL(plid, false, kbArticle, request);
		}

		if (portletURL == null) {
			portletURL = getKBArticleURL(plid, true, kbArticle, request);
		}

		if (portletURL == null) {
			portletURL = getDynamicPortletURL(plid, status, request);
		}

		if (maximized) {
			portletURL.setWindowState(LiferayWindowState.MAXIMIZED);
			portletURL.setPortletMode(PortletMode.VIEW);
		}

		response.sendRedirect(portletURL.toString());

		return null;
	}

	protected PortletURL getDynamicPortletURL(
			long plid, int status, HttpServletRequest request)
		throws Exception {

		String portletId = PortletKeys.KNOWLEDGE_BASE_ARTICLE_DEFAULT_INSTANCE;

		PortletURL portletURL = getKBArticleURL(plid, portletId, request);

		if (status != WorkflowConstants.STATUS_APPROVED) {
			portletURL.setParameter("status", String.valueOf(status));
		}

		if (_PORTLET_ADD_DEFAULT_RESOURCE_CHECK_ENABLED) {
			String token = AuthTokenUtil.getToken(request, plid, portletId);

			portletURL.setParameter("p_p_auth", token);
		}

		portletURL.setPortletMode(PortletMode.VIEW);
		portletURL.setWindowState(LiferayWindowState.MAXIMIZED);

		return portletURL;
	}

	protected KBArticle getKBArticle(long resourcePrimKey, int status)
		throws Exception {

		KBArticle kbArticle = null;

		try {
			kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(
				resourcePrimKey, status);
		}
		catch (NoSuchArticleException nsae) {
			return null;
		}

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (!KBArticlePermission.contains(
				permissionChecker, kbArticle, ActionKeys.VIEW)) {

			return null;
		}

		return kbArticle;
	}

	protected PortletURL getKBArticleURL(
			long plid, boolean privateLayout, KBArticle kbArticle,
			HttpServletRequest request)
		throws Exception {

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			kbArticle.getGroupId(), privateLayout,
			LayoutConstants.TYPE_PORTLET);

		Layout selLayout = LayoutLocalServiceUtil.getLayout(plid);

		if ((selLayout.getGroupId() == kbArticle.getGroupId()) &&
			selLayout.isTypePortlet()) {

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
					return getKBArticleURL(
						layout.getPlid(), portlet.getPortletId(), request);
				}

				if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_SECTION)) {
					PortletPreferences preferences =
						PortletPreferencesFactoryUtil.getPortletSetup(
							layout, portlet.getPortletId(), StringPool.BLANK);

					String[] kbArticlesSections = preferences.getValues(
						"kbArticlesSections", new String[0]);

					KBArticle rootKBArticle = null;

					try {
						rootKBArticle =
							KBArticleLocalServiceUtil.getLatestKBArticle(
								kbArticle.getRootResourcePrimKey(),
								WorkflowConstants.STATUS_APPROVED);
					}
					catch (NoSuchArticleException nsae) {
						continue;
					}

					String[] sections = AdminUtil.unescapeSections(
						rootKBArticle.getSections());

					for (String section : sections) {
						if (!ArrayUtil.contains(kbArticlesSections, section)) {
							continue;
						}

						return getKBArticleURL(
							layout.getPlid(), portlet.getPortletId(), request);
					}
				}

				if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_ARTICLE)) {
					PortletPreferences preferences =
						PortletPreferencesFactoryUtil.getPortletSetup(
							layout, portlet.getPortletId(), StringPool.BLANK);

					long resourcePrimKey = GetterUtil.getLong(
						preferences.getValue("resourcePrimKey", null));

					KBArticle selKBArticle = null;

					try {
						selKBArticle =
							KBArticleLocalServiceUtil.getLatestKBArticle(
								resourcePrimKey,
								WorkflowConstants.STATUS_APPROVED);
					}
					catch (NoSuchArticleException nsae) {
						continue;
					}

					long rootResourcePrimKey =
						kbArticle.getRootResourcePrimKey();
					long selRootResourcePrimKey =
						selKBArticle.getRootResourcePrimKey();

					if (rootResourcePrimKey == selRootResourcePrimKey) {
						return getKBArticleURL(
							layout.getPlid(), portlet.getPortletId(), request);
					}
				}
			}
		}

		return null;
	}

	protected PortletURL getKBArticleURL(
			long plid, String portletId, HttpServletRequest request)
		throws Exception {

		long resourcePrimKey = ParamUtil.getLong(request, "resourcePrimKey");

		String mvcPath = null;

		String rootPortletId = PortletConstants.getRootPortletId(portletId);

		if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_ARTICLE)) {
			mvcPath = "/article/view_article.jsp";
		}
		else if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) {
			mvcPath = "/display/view_article.jsp";
		}
		else if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_SECTION)) {
			mvcPath = "/section/view_article.jsp";
		}

		PortletURL portletURL = PortletURLFactoryUtil.create(
			request, portletId, plid, PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcPath", mvcPath);
		portletURL.setParameter(
			"resourcePrimKey", String.valueOf(resourcePrimKey));

		portletURL.setPortletMode(PortletMode.VIEW);

		portletURL.setWindowState(LiferayWindowState.NORMAL);

		if (rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_SECTION)) {
			portletURL.setWindowState(LiferayWindowState.MAXIMIZED);
		}

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