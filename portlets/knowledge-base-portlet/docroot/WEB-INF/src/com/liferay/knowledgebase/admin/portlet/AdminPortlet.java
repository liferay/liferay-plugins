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

package com.liferay.knowledgebase.admin.portlet;

import com.liferay.knowledgebase.KBArticleImportException;
import com.liferay.knowledgebase.KBTemplateContentException;
import com.liferay.knowledgebase.KBTemplateTitleException;
import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.NoSuchCommentException;
import com.liferay.knowledgebase.NoSuchTemplateException;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBArticleConstants;
import com.liferay.knowledgebase.model.KBFolder;
import com.liferay.knowledgebase.model.KBFolderConstants;
import com.liferay.knowledgebase.model.KBTemplate;
import com.liferay.knowledgebase.portlet.BaseKBPortlet;
import com.liferay.knowledgebase.service.KBArticleServiceUtil;
import com.liferay.knowledgebase.service.KBFolderServiceUtil;
import com.liferay.knowledgebase.service.KBTemplateServiceUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.knowledgebase.util.WebKeys;
import com.liferay.portal.NoSuchSubscriptionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;

import java.io.IOException;
import java.io.InputStream;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowStateException;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 * @author Eric Min
 */
public class AdminPortlet extends BaseKBPortlet {

	public void deleteKBArticles(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] resourcePrimKeys = StringUtil.split(
			ParamUtil.getString(actionRequest, "resourcePrimKeys"), 0L);

		KBArticleServiceUtil.deleteKBArticles(
			themeDisplay.getScopeGroupId(), resourcePrimKeys);
	}

	public void deleteKBFolder(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

		long kbFolderId = ParamUtil.getLong(actionRequest, "kbFolderId");

		KBFolderServiceUtil.deleteKBFolder(kbFolderId);
	}

	public void deleteKBTemplate(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long kbTemplateId = ParamUtil.getLong(actionRequest, "kbTemplateId");

		KBTemplateServiceUtil.deleteKBTemplate(kbTemplateId);
	}

	public void deleteKBTemplates(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] kbTemplateIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "kbTemplateIds"), 0L);

		KBTemplateServiceUtil.deleteKBTemplates(
			themeDisplay.getScopeGroupId(), kbTemplateIds);
	}

	public void importFile(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		InputStream inputStream = null;

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			UploadPortletRequest uploadPortletRequest =
				PortalUtil.getUploadPortletRequest(actionRequest);

			checkExceededSizeLimit(actionRequest);

			long parentKBFolderId = ParamUtil.getLong(
				uploadPortletRequest, "parentKBFolderId",
				KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);

			String fileName = uploadPortletRequest.getFileName("file");

			if (Validator.isNull(fileName)) {
				throw new KBArticleImportException("File name is null");
			}

			boolean prioritizeByNumericalPrefix = ParamUtil.getBoolean(
				uploadPortletRequest, "prioritizeByNumericalPrefix");

			inputStream = uploadPortletRequest.getFileAsStream("file");

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				AdminPortlet.class.getName(), actionRequest);

			serviceContext.setGuestPermissions(new String[] {ActionKeys.VIEW});

			int importedKBArticlesCount =
				KBArticleServiceUtil.addKBArticlesMarkdown(
					themeDisplay.getScopeGroupId(), parentKBFolderId, fileName,
					prioritizeByNumericalPrefix, inputStream, serviceContext);

			SessionMessages.add(
				actionRequest, "importedKBArticlesCount",
				importedKBArticlesCount);
		}
		catch (KBArticleImportException kbaie) {
			SessionErrors.add(actionRequest, kbaie.getClass(), kbaie);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			int status = WorkflowConstants.STATUS_ANY;

			renderRequest.setAttribute(WebKeys.KNOWLEDGE_BASE_STATUS, status);

			KBArticle kbArticle = null;

			long kbArticleClassNameId = PortalUtil.getClassNameId(
				KBArticleConstants.getClassName());

			long resourceClassNameId = ParamUtil.getLong(
				renderRequest, "resourceClassNameId", kbArticleClassNameId);
			long resourcePrimKey = ParamUtil.getLong(
				renderRequest, "resourcePrimKey");

			if ((resourcePrimKey > 0) &&
				(resourceClassNameId == kbArticleClassNameId)) {

				kbArticle = KBArticleServiceUtil.getLatestKBArticle(
					resourcePrimKey, status);
			}

			renderRequest.setAttribute(
				WebKeys.KNOWLEDGE_BASE_KB_ARTICLE, kbArticle);

			KBTemplate kbTemplate = null;

			long kbTemplateId = ParamUtil.getLong(
				renderRequest, "kbTemplateId");

			if (kbTemplateId > 0) {
				kbTemplate = KBTemplateServiceUtil.getKBTemplate(kbTemplateId);
			}

			renderRequest.setAttribute(
				WebKeys.KNOWLEDGE_BASE_KB_TEMPLATE, kbTemplate);
		}
		catch (Exception e) {
			if (e instanceof NoSuchArticleException ||
				e instanceof NoSuchTemplateException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass());
			}
			else {
				throw new PortletException(e);
			}
		}

		super.render(renderRequest, renderResponse);
	}

	public void subscribeGroupKBArticles(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String portletId = PortalUtil.getPortletId(actionRequest);

		KBArticleServiceUtil.subscribeGroupKBArticles(
			themeDisplay.getScopeGroupId(), portletId);
	}

	public void unsubscribeGroupKBArticles(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String portletId = PortalUtil.getPortletId(actionRequest);

		KBArticleServiceUtil.unsubscribeGroupKBArticles(
			themeDisplay.getScopeGroupId(), portletId);
	}

	public void updateKBArticlesPriorities(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Enumeration<String> enu = actionRequest.getParameterNames();

		Map<Long, Double> resourcePrimKeyToPriorityMap = new HashMap<>();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			if (!name.startsWith("priority")) {
				continue;
			}

			double priority = ParamUtil.getDouble(actionRequest, name);

			long resourcePrimKey = GetterUtil.getLong(name.substring(8));

			resourcePrimKeyToPriorityMap.put(resourcePrimKey, priority);
		}

		KBArticleServiceUtil.updateKBArticlesPriorities(
			themeDisplay.getScopeGroupId(), resourcePrimKeyToPriorityMap);
	}

	public void updateKBFolder(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long kbFolderId = ParamUtil.getLong(actionRequest, "kbFolderId");

		long parentResourceClassNameId = ParamUtil.getLong(
			actionRequest, "parentResourceClassNameId");
		long parentResourcePrimKey = ParamUtil.getLong(
			actionRequest, "parentResourcePrimKey");
		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			KBFolder.class.getName(), actionRequest);

		if (cmd.equals(Constants.ADD)) {
			KBFolderServiceUtil.addKBFolder(
				themeDisplay.getScopeGroupId(), parentResourceClassNameId,
				parentResourcePrimKey, name, description, serviceContext);
		}
		else if (cmd.equals(Constants.UPDATE)) {
			KBFolderServiceUtil.updateKBFolder(
				parentResourceClassNameId, parentResourcePrimKey, kbFolderId,
				name, description);
		}
	}

	public void updateKBTemplate(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String portletId = PortalUtil.getPortletId(actionRequest);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long kbTemplateId = ParamUtil.getLong(actionRequest, "kbTemplateId");

		String title = ParamUtil.getString(actionRequest, "title");
		String content = ParamUtil.getString(actionRequest, "content");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			KBTemplate.class.getName(), actionRequest);

		if (cmd.equals(Constants.ADD)) {
			KBTemplateServiceUtil.addKBTemplate(
				portletId, title, content, serviceContext);
		}
		else if (cmd.equals(Constants.UPDATE)) {
			KBTemplateServiceUtil.updateKBTemplate(
				kbTemplateId, title, content, serviceContext);
		}
	}

	@Override
	protected String buildEditURL(
			ActionRequest actionRequest, ActionResponse actionResponse,
			KBArticle kbArticle)
		throws PortalException {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			PortletURL portletURL = PortletURLFactoryUtil.create(
				actionRequest, PortletKeys.KNOWLEDGE_BASE_ADMIN,
				themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

			portletURL.setParameter(
				"mvcPath", templatePath + "edit_article.jsp");
			portletURL.setParameter(
				"redirect", getRedirect(actionRequest, actionResponse));
			portletURL.setParameter(
				"resourcePrimKey",
				String.valueOf(kbArticle.getResourcePrimKey()));
			portletURL.setWindowState(actionRequest.getWindowState());

			return portletURL.toString();
		}
		catch (WindowStateException wse) {
			throw new PortalException(wse);
		}
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
				renderRequest, NoSuchTemplateException.class.getName()) ||
			SessionErrors.contains(
				renderRequest, PrincipalException.getNestedClasses())) {

			include(templatePath + "error.jsp", renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof KBArticleImportException ||
			cause instanceof KBTemplateContentException ||
			cause instanceof KBTemplateTitleException ||
			cause instanceof NoSuchTemplateException ||
			super.isSessionErrorException(cause)) {

			return true;
		}

		return false;
	}

}