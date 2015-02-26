/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.display.portlet;

import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.NoSuchCommentException;
import com.liferay.knowledgebase.display.selector.KBArticleSelector;
import com.liferay.knowledgebase.display.selector.KBArticleSelectorFactoryUtil;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBArticleConstants;
import com.liferay.knowledgebase.model.KBFolder;
import com.liferay.knowledgebase.model.KBFolderConstants;
import com.liferay.knowledgebase.portlet.BaseKBPortlet;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.KBFolderServiceUtil;
import com.liferay.knowledgebase.service.permission.KBArticlePermission;
import com.liferay.knowledgebase.util.ActionKeys;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.knowledgebase.util.WebKeys;
import com.liferay.knowledgebase.util.comparator.KBArticlePriorityComparator;
import com.liferay.portal.NoSuchSubscriptionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortalPreferences;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.PortletURLFactoryUtil;

import java.io.IOException;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 * @author Sergio González
 */
public class DisplayPortlet extends BaseKBPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			KBArticle kbArticle = getKBArticle(renderRequest);

			int status = getStatus(renderRequest, kbArticle);

			renderRequest.setAttribute(WebKeys.KNOWLEDGE_BASE_STATUS, status);

			if ((kbArticle != null) && (kbArticle.getStatus() != status)) {
				kbArticle = KBArticleLocalServiceUtil.fetchLatestKBArticle(
					kbArticle.getResourcePrimKey(), status);
			}

			renderRequest.setAttribute(
				WebKeys.KNOWLEDGE_BASE_KB_ARTICLE, kbArticle);
		}
		catch (Exception e) {
			if (e instanceof NoSuchArticleException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass());

				SessionMessages.add(
					renderRequest,
					PortalUtil.getPortletId(renderRequest) +
						SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			}
			else {
				throw new PortletException(e);
			}
		}

		super.render(renderRequest, renderResponse);
	}

	public void updateRootKBFolderId(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortalException, SystemException {

		long kbFolderId = ParamUtil.getLong(actionRequest, "rootKBFolderId");

		if (kbFolderId == KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return;
		}

		KBFolder kbFolder = KBFolderServiceUtil.getKBFolder(kbFolderId);

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(
				PortalUtil.getLiferayPortletRequest(actionRequest));

		PortletPreferences portletPreferences = actionRequest.getPreferences();

		String contentRootPrefix = GetterUtil.getString(
			portletPreferences.getValue("contentRootPrefix", null));

		String previousPreferredKBFolderURLTitle =
			KnowledgeBaseUtil.getPreferredKBFolderURLTitle(
				portalPreferences, contentRootPrefix);

		KnowledgeBaseUtil.setPreferredKBFolderURLTitle(
			portalPreferences, contentRootPrefix, kbFolder.getUrlTitle());

		String urlTitle = ParamUtil.getString(actionRequest, "urlTitle");

		if (Validator.isNull(urlTitle)) {
			return;
		}

		KBArticle kbArticle =
			KBArticleLocalServiceUtil.fetchKBArticleByUrlTitle(
				kbFolder.getGroupId(), kbFolder.getUrlTitle(), urlTitle);

		if (kbArticle == null) {
			if (Validator.isNull(previousPreferredKBFolderURLTitle)) {
				return;
			}

			kbArticle = findClosestMatchingKBArticle(
				kbFolder.getGroupId(), previousPreferredKBFolderURLTitle,
				kbFolder.getKbFolderId(), urlTitle);

			if (kbArticle == null) {
				return;
			}
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!KBArticlePermission.contains(
				themeDisplay.getPermissionChecker(), kbArticle,
				ActionKeys.VIEW)) {

			return;
		}

		PortletURL redirectURL = PortletURLFactoryUtil.create(
			actionRequest, PortletKeys.KNOWLEDGE_BASE_DISPLAY,
			themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

		redirectURL.setParameter("kbFolderUrlTitle", kbFolder.getUrlTitle());
		redirectURL.setParameter("urlTitle", kbArticle.getUrlTitle());

		actionResponse.sendRedirect(redirectURL.toString());
	}

	@Override
	protected void addSuccessMessage(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		String actionName = ParamUtil.getString(
			actionRequest, ActionRequest.ACTION_NAME);

		if (actionName.equals("deleteKBArticle") ||
			actionName.equals("updateKBComment") ||
			actionName.equals("updateRootKBFolderId")) {

			return;
		}

		super.addSuccessMessage(actionRequest, actionResponse);
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (SessionErrors.contains(
				renderRequest, NoSuchArticleException.class.getName()) ||
			SessionErrors.contains(
				renderRequest, NoSuchCommentException.class.getName()) ||
			SessionErrors.contains(
				renderRequest, NoSuchSubscriptionException.class.getName()) ||
			SessionErrors.contains(
				renderRequest, PrincipalException.class.getName())) {

			include(templatePath + "error.jsp", renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	protected KBArticle findClosestMatchingKBArticle(
			long groupId, String oldKBFolderURLTitle, long newKBFolderId,
			String urlTitle)
		throws PortalException, SystemException {

		KBArticle oldKBArticle =
			KBArticleLocalServiceUtil.fetchKBArticleByUrlTitle(
				groupId, oldKBFolderURLTitle, urlTitle);

		KBArticle kbArticle = null;

		while ((kbArticle == null) && (oldKBArticle != null)) {
			kbArticle = KBArticleLocalServiceUtil.fetchKBArticleByUrlTitle(
				groupId, newKBFolderId, oldKBArticle.getUrlTitle());

			if (kbArticle == null) {
				oldKBArticle = oldKBArticle.getParentKBArticle();
			}
		}

		if (kbArticle == null) {
			List<KBArticle> kbArticles =
				KBArticleLocalServiceUtil.getKBArticles(
					groupId, newKBFolderId, WorkflowConstants.STATUS_APPROVED,
					0, 1, new KBArticlePriorityComparator(true));

			if (!kbArticles.isEmpty()) {
				kbArticle = kbArticles.get(0);
			}
		}

		return kbArticle;
	}

	protected KBArticle getKBArticle(RenderRequest renderRequest)
		throws PortalException, SystemException {

		String mvcPath = ParamUtil.getString(renderRequest, "mvcPath");

		if (mvcPath.endsWith("/edit_article.jsp")) {
			long resourcePrimKey = ParamUtil.getLong(
				renderRequest, "resourcePrimKey");

			return KBArticleLocalServiceUtil.getLatestKBArticle(
				resourcePrimKey, WorkflowConstants.STATUS_ANY);
		}

		PortletPreferences portletPreferences = renderRequest.getPreferences();

		long kbFolderClassNameId = ClassNameLocalServiceUtil.getClassNameId(
			KBFolderConstants.getClassName());

		long parentResourcePrimKey = GetterUtil.getLong(
			portletPreferences.getValue("resourcePrimKey", null));
		long parentResourceClassNameId = GetterUtil.getLong(
			portletPreferences.getValue("resourceClassNameId", null),
			kbFolderClassNameId);

		KBArticleSelector kbArticleSelector =
			KBArticleSelectorFactoryUtil.getKBArticleSelector(
				parentResourceClassNameId);

		String urlTitle = ParamUtil.getString(renderRequest, "urlTitle");

		String preferredKBFolderURLTitle = getPreferredKBFolderUrlTitle(
			renderRequest, portletPreferences);

		if (Validator.isNotNull(urlTitle)) {
			String kbFolderUrlTitle = ParamUtil.getString(
				renderRequest, "kbFolderUrlTitle");

			return kbArticleSelector.findByUrlTitle(
				PortalUtil.getScopeGroupId(renderRequest),
				preferredKBFolderURLTitle, parentResourcePrimKey,
				kbFolderUrlTitle, urlTitle);
		}

		long resourcePrimKey = ParamUtil.getLong(
			renderRequest, "resourcePrimKey",
			KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY);

		return kbArticleSelector.findByResourcePrimKey(
			PortalUtil.getScopeGroupId(renderRequest),
			preferredKBFolderURLTitle, parentResourcePrimKey, resourcePrimKey);
	}

	protected String getPreferredKBFolderUrlTitle(
			RenderRequest renderRequest, PortletPreferences portletPreferences)
		throws PortalException, SystemException {

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(renderRequest);

		String contentRootPrefix = GetterUtil.getString(
			portletPreferences.getValue("contentRootPrefix", null));

		return KnowledgeBaseUtil.getPreferredKBFolderURLTitle(
			portalPreferences, contentRootPrefix);
	}

	protected int getStatus(RenderRequest renderRequest, KBArticle kbArticle)
		throws Exception {

		if (kbArticle == null) {
			return WorkflowConstants.STATUS_APPROVED;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (KBArticlePermission.contains(
				themeDisplay.getPermissionChecker(), kbArticle,
				ActionKeys.UPDATE)) {

			return ParamUtil.getInteger(
				renderRequest, "status", WorkflowConstants.STATUS_ANY);
		}

		return WorkflowConstants.STATUS_APPROVED;
	}

}