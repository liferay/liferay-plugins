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
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBArticleConstants;
import com.liferay.knowledgebase.model.KBFolder;
import com.liferay.knowledgebase.model.KBFolderConstants;
import com.liferay.knowledgebase.portlet.BaseKBPortlet;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.KBArticleServiceUtil;
import com.liferay.knowledgebase.service.KBFolderLocalServiceUtil;
import com.liferay.knowledgebase.service.KBFolderServiceUtil;
import com.liferay.knowledgebase.service.permission.KBArticlePermission;
import com.liferay.knowledgebase.service.permission.KBFolderPermission;
import com.liferay.knowledgebase.util.ActionKeys;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.knowledgebase.util.WebKeys;
import com.liferay.knowledgebase.util.comparator.KBArticlePriorityComparator;
import com.liferay.portal.NoSuchSubscriptionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortalPreferences;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import java.io.IOException;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
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
			ThemeDisplay themeDisplay =
				(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			int status = getStatus(renderRequest);

			renderRequest.setAttribute(WebKeys.KNOWLEDGE_BASE_STATUS, status);

			KBArticle kbArticle = null;

			Tuple resourceTuple = getResourceTuple(renderRequest);

			long resourcePrimKey = (Long)resourceTuple.getObject(1);

			if (resourcePrimKey != KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
				long resourceClassNameId = (Long)resourceTuple.getObject(0);

				long kbFolderClassNameId = PortalUtil.getClassNameId(
					KBFolderConstants.getClassName());

				if (resourceClassNameId == kbFolderClassNameId) {
					PortalPreferences portalPreferences =
						PortletPreferencesFactoryUtil.getPortalPreferences(
							renderRequest);

					String preferredKBFolderUrlTitle =
						portalPreferences.getValue(
							PortletKeys.KNOWLEDGE_BASE_DISPLAY,
							"preferredKBFolderUrlTitle");

					kbArticle = getKBFolderKBArticle(
						themeDisplay.getScopeGroupId(), resourcePrimKey,
						preferredKBFolderUrlTitle);
				}
				else {
					kbArticle = KBArticleServiceUtil.fetchLatestKBArticle(
						resourcePrimKey, status);
				}
			}
			else {
				long parentResourcePrimKey = ParamUtil.getLong(
					renderRequest, "parentResourcePrimKey",
					KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);

				if (parentResourcePrimKey ==
						KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

					List<KBArticle> kbArticles =
						KBArticleLocalServiceUtil.getGroupKBArticles(
							themeDisplay.getScopeGroupId(), status, 0, 1,
							new KBArticlePriorityComparator(true));

					if (!kbArticles.isEmpty()) {
						kbArticle = kbArticles.get(0);
					}
				}
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
		throws PortalException {

		long kbFolderId = ParamUtil.getLong(actionRequest, "rootKBFolderId");

		if (kbFolderId == KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return;
		}

		KBFolder kbFolder = KBFolderServiceUtil.getKBFolder(kbFolderId);

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(
				PortalUtil.getLiferayPortletRequest(actionRequest));

		portalPreferences.setValue(
			PortletKeys.KNOWLEDGE_BASE_DISPLAY, "preferredKBFolderUrlTitle",
			kbFolder.getUrlTitle());
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

	protected KBArticle getKBFolderKBArticle(
			long groupId, long kbFolderId, String kbFolderUrlTitle)
		throws PortalException {

		if (Validator.isNotNull(kbFolderUrlTitle)) {
			KBFolder kbFolder = KBFolderServiceUtil.fetchKBFolderByUrlTitle(
				groupId, kbFolderId, kbFolderUrlTitle);

			if (kbFolder != null) {
				kbFolderId = kbFolder.getKbFolderId();
			}
		}

		List<KBArticle> kbArticles = KBArticleServiceUtil.getKBArticles(
			groupId, kbFolderId, WorkflowConstants.STATUS_APPROVED, 0, 1,
			new KBArticlePriorityComparator(true));

		if (!kbArticles.isEmpty()) {
			return kbArticles.get(0);
		}

		List<KBFolder> kbFolders = KnowledgeBaseUtil.getAlternateRootKBFolders(
			groupId, kbFolderId);

		for (KBFolder kbFolder : kbFolders) {
			KBArticle kbArticle = getKBFolderKBArticle(
				groupId, kbFolder.getKbFolderId(), kbFolder.getUrlTitle());

			if (kbArticle != null) {
				return kbArticle;
			}
		}

		return null;
	}

	protected Tuple getResourceTuple(RenderRequest renderRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String urlTitle = ParamUtil.getString(renderRequest, "urlTitle");

		if (Validator.isNotNull(urlTitle)) {
			String kbFolderUrlTitle = ParamUtil.getString(
				renderRequest, "kbFolderUrlTitle");

			KBArticle kbArticle = null;

			if (Validator.isNotNull(kbFolderUrlTitle)) {
				kbArticle = KBArticleLocalServiceUtil.fetchKBArticleByUrlTitle(
					themeDisplay.getScopeGroupId(), kbFolderUrlTitle, urlTitle);
			}
			else {
				kbArticle = KBArticleLocalServiceUtil.fetchKBArticleByUrlTitle(
					themeDisplay.getScopeGroupId(),
					KBFolderConstants.DEFAULT_PARENT_FOLDER_ID, urlTitle);
			}

			if ((kbArticle != null) &&
				KBArticlePermission.contains(
					themeDisplay.getPermissionChecker(), kbArticle,
					ActionKeys.VIEW)) {

				return new Tuple(
					kbArticle.getClassNameId(), kbArticle.getResourcePrimKey());
			}
		}

		PortletPreferences portletPreferences = renderRequest.getPreferences();

		long defaultResourcePrimKey = GetterUtil.getLong(
			portletPreferences.getValue("resourcePrimKey", null));

		String mvcPath = ParamUtil.getString(renderRequest, "mvcPath");

		if (((defaultResourcePrimKey == 0) && mvcPath.equals(viewTemplate)) ||
			mvcPath.equals("/display/select_configuration_article.jsp")) {

			return new Tuple(
				PortalUtil.getClassNameId(KBFolderConstants.getClassName()),
				KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);
		}

		long defaultResourceClassNameId = GetterUtil.getLong(
			portletPreferences.getValue("resourceClassNameId", null));

		long resourceClassNameId = ParamUtil.getLong(
			renderRequest, "resourceClassNameId", defaultResourceClassNameId);

		long resourcePrimKey = ParamUtil.getLong(
			renderRequest, "resourcePrimKey", defaultResourcePrimKey);

		if ((resourcePrimKey == 0) ||
			(resourcePrimKey != defaultResourcePrimKey)) {

			return new Tuple(resourceClassNameId, resourcePrimKey);
		}

		long kbFolderClassNameId = PortalUtil.getClassNameId(
			KBFolderConstants.getClassName());

		if (resourceClassNameId == kbFolderClassNameId) {
			KBFolder kbFolder = KBFolderLocalServiceUtil.fetchKBFolder(
				resourcePrimKey);

			if ((kbFolder != null) &&
				!KBFolderPermission.contains(
					themeDisplay.getPermissionChecker(),
					themeDisplay.getScopeGroupId(), defaultResourcePrimKey,
					ActionKeys.VIEW)) {

				return new Tuple(
					PortalUtil.getClassNameId(KBFolderConstants.getClassName()),
					KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);
			}
		}
		else {
			KBArticle kbArticle =
				KBArticleLocalServiceUtil.fetchLatestKBArticle(
					defaultResourcePrimKey, WorkflowConstants.STATUS_ANY);

			if ((kbArticle != null) &&
				!KBArticlePermission.contains(
					themeDisplay.getPermissionChecker(), defaultResourcePrimKey,
					ActionKeys.VIEW)) {

				return new Tuple(
					PortalUtil.getClassNameId(KBFolderConstants.getClassName()),
					KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);
			}
		}

		return new Tuple(defaultResourceClassNameId, defaultResourcePrimKey);
	}

	protected int getStatus(RenderRequest renderRequest) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return WorkflowConstants.STATUS_APPROVED;
		}

		String statusString = renderRequest.getParameter("status");

		int status = GetterUtil.getInteger(statusString);

		if ((statusString != null) &&
			(status == WorkflowConstants.STATUS_APPROVED)) {

			return WorkflowConstants.STATUS_APPROVED;
		}

		Tuple resourceTuple = getResourceTuple(renderRequest);

		long resourcePrimKey = (Long)resourceTuple.getObject(1);

		if (resourcePrimKey == KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return WorkflowConstants.STATUS_APPROVED;
		}

		long resourceClassNameId = (Long)resourceTuple.getObject(0);

		long kbArticleClassNameId = PortalUtil.getClassNameId(
			KBArticleConstants.getClassName());

		if (resourceClassNameId == kbArticleClassNameId) {
			KBArticle kbArticle = KBArticleServiceUtil.fetchLatestKBArticle(
				resourcePrimKey, WorkflowConstants.STATUS_ANY);

			if (kbArticle == null) {
				return WorkflowConstants.STATUS_APPROVED;
			}

			if (KBArticlePermission.contains(
					themeDisplay.getPermissionChecker(), resourcePrimKey,
					ActionKeys.UPDATE)) {

				return ParamUtil.getInteger(
					renderRequest, "status", WorkflowConstants.STATUS_ANY);
			}
		}

		return WorkflowConstants.STATUS_APPROVED;
	}

}