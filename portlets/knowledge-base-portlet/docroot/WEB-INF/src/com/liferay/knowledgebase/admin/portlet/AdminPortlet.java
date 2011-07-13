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

package com.liferay.knowledgebase.admin.portlet;

import com.liferay.knowledgebase.KBArticleContentException;
import com.liferay.knowledgebase.KBArticlePriorityException;
import com.liferay.knowledgebase.KBArticleTitleException;
import com.liferay.knowledgebase.KBCommentContentException;
import com.liferay.knowledgebase.KBTemplateContentException;
import com.liferay.knowledgebase.KBTemplateTitleException;
import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.NoSuchCommentException;
import com.liferay.knowledgebase.NoSuchTemplateException;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBComment;
import com.liferay.knowledgebase.model.KBTemplate;
import com.liferay.knowledgebase.service.KBArticleServiceUtil;
import com.liferay.knowledgebase.service.KBCommentLocalServiceUtil;
import com.liferay.knowledgebase.service.KBTemplateServiceUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.knowledgebase.util.WebKeys;
import com.liferay.portal.NoSuchSubscriptionException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.DuplicateFileException;
import com.liferay.portlet.documentlibrary.FileNameException;
import com.liferay.portlet.documentlibrary.FileSizeException;
import com.liferay.portlet.documentlibrary.NoSuchFileException;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.liferay.util.servlet.PortletResponseUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 * @author Eric Min
 */
public class AdminPortlet extends MVCPortlet {

	public void addAttachment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(
			actionRequest);

		String portletId = PortalUtil.getPortletId(actionRequest);

		long resourcePrimKey = ParamUtil.getLong(
			uploadRequest, "resourcePrimKey");

		String dirName = ParamUtil.getString(uploadRequest, "dirName");
		File file = uploadRequest.getFile("file");
		String fileName = uploadRequest.getFileName("file");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			KBArticle.class.getName(), actionRequest);

		KBArticleServiceUtil.addAttachment(
			portletId, resourcePrimKey, dirName, fileName,
			FileUtil.getBytes(file), serviceContext);
	}

	public void deleteAttachment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String portletId = PortalUtil.getPortletId(actionRequest);

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		String fileName = ParamUtil.getString(actionRequest, "fileName");

		KBArticleServiceUtil.deleteAttachment(
			themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
			portletId, resourcePrimKey, fileName);
	}

	public void deleteKBArticle(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		KBArticleServiceUtil.deleteKBArticle(resourcePrimKey);
	}

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

	public void deleteKBComment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		long kbCommentId = ParamUtil.getLong(actionRequest, "kbCommentId");

		KBCommentLocalServiceUtil.deleteKBComment(kbCommentId);
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

	public void moveKBArticle(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		long parentResourcePrimKey = ParamUtil.getLong(
			actionRequest, "parentResourcePrimKey");
		double priority = ParamUtil.getDouble(actionRequest, "priority");

		KBArticleServiceUtil.moveKBArticle(
			resourcePrimKey, parentResourcePrimKey, priority);
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException, IOException {

		try {
			int status = WorkflowConstants.STATUS_ANY;

			renderRequest.setAttribute(WebKeys.KNOWLEDGE_BASE_STATUS, status);

			KBArticle kbArticle = null;

			long resourcePrimKey = ParamUtil.getLong(
				renderRequest, "resourcePrimKey");

			if (resourcePrimKey > 0) {
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

				SessionErrors.add(renderRequest, e.getClass().getName());
			}
			else {
				throw new PortletException(e);
			}
		}

		super.render(renderRequest, renderResponse);
	}

	public void serveAttachment(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long companyId = ParamUtil.getLong(resourceRequest, "companyId");
		String fileName = ParamUtil.getString(resourceRequest, "fileName");

		String shortFileName = FileUtil.getShortFileName(fileName);
		InputStream is = DLStoreUtil.getFileAsStream(
			companyId, CompanyConstants.SYSTEM, fileName);
		String contentType = MimeTypesUtil.getContentType(fileName);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, shortFileName, is, contentType);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("attachment")) {
				serveAttachment(resourceRequest, resourceResponse);
			}
		}
		catch (IOException ioe) {
			throw ioe;
		}
		catch (PortletException pe) {
			throw pe;
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
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

	public void subscribeKBArticle(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		KBArticleServiceUtil.subscribeKBArticle(
			themeDisplay.getScopeGroupId(), resourcePrimKey);
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

	public void unsubscribeKBArticle(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		KBArticleServiceUtil.unsubscribeKBArticle(resourcePrimKey);
	}

	public void updateAttachments(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String portletId = PortalUtil.getPortletId(actionRequest);

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		String dirName = ParamUtil.getString(actionRequest, "dirName");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			KBArticle.class.getName(), actionRequest);

		dirName = KBArticleServiceUtil.updateAttachments(
			portletId, resourcePrimKey, dirName, serviceContext);

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		redirect = HttpUtil.setParameter(
			redirect, actionResponse.getNamespace() + "dirName", dirName);

		actionRequest.setAttribute(WebKeys.REDIRECT, redirect);
	}

	public void updateKBArticle(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String portletId = PortalUtil.getPortletId(actionRequest);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		long parentResourcePrimKey = ParamUtil.getLong(
			actionRequest, "parentResourcePrimKey");
		String title = ParamUtil.getString(actionRequest, "title");
		String content = ParamUtil.getString(actionRequest, "content");
		String description = ParamUtil.getString(actionRequest, "description");
		String[] sections = actionRequest.getParameterValues("sections");
		String dirName = ParamUtil.getString(actionRequest, "dirName");
		int workflowAction = ParamUtil.getInteger(
			actionRequest, "workflowAction");

		KBArticle kbArticle = null;

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			KBArticle.class.getName(), actionRequest);

		if (cmd.equals(Constants.ADD)) {
			kbArticle = KBArticleServiceUtil.addKBArticle(
				portletId, parentResourcePrimKey, title, content, description,
				sections, dirName, serviceContext);
		}
		else if (cmd.equals(Constants.UPDATE)) {
			kbArticle = KBArticleServiceUtil.updateKBArticle(
				resourcePrimKey, title, content, description, sections, dirName,
				serviceContext);
		}

		if (!cmd.equals(Constants.ADD) && !cmd.equals(Constants.UPDATE)) {
			return;
		}

		if (workflowAction == WorkflowConstants.ACTION_SAVE_DRAFT) {
			String namespace = actionResponse.getNamespace();
			String redirect = getRedirect(actionRequest, actionResponse);

			String editURL = PortalUtil.getLayoutFullURL(themeDisplay);

			editURL = HttpUtil.setParameter(
				editURL, "p_p_id", PortletKeys.KNOWLEDGE_BASE_ADMIN);
			editURL = HttpUtil.setParameter(
				editURL, namespace + "jspPage", jspPath + "edit_article.jsp");
			editURL = HttpUtil.setParameter(
				editURL, namespace + "redirect", redirect);
			editURL = HttpUtil.setParameter(
				editURL, namespace + "resourcePrimKey",
				kbArticle.getResourcePrimKey());

			actionRequest.setAttribute(WebKeys.REDIRECT, editURL);
		}
	}

	public void updateKBArticlesPriorities(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Enumeration<String> enu = actionRequest.getParameterNames();

		Map<Long, Double> resourcePrimKeyToPriorityMap =
			new HashMap<Long, Double>();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			if (!name.startsWith("priority")) {
				continue;
			}

			double priority = ParamUtil.getDouble(actionRequest, name);

			long resourcePrimKey = GetterUtil.getLong(
				name.substring(8, name.length()));

			resourcePrimKeyToPriorityMap.put(resourcePrimKey, priority);
		}

		KBArticleServiceUtil.updateKBArticlesPriorities(
			themeDisplay.getScopeGroupId(), resourcePrimKeyToPriorityMap);
	}

	public void updateKBComment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long kbCommentId = ParamUtil.getLong(actionRequest, "kbCommentId");

		long classNameId = ParamUtil.getLong(actionRequest, "classNameId");
		long classPK = ParamUtil.getLong(actionRequest, "classPK");
		String content = ParamUtil.getString(actionRequest, "content");
		boolean helpful = ParamUtil.getBoolean(actionRequest, "helpful");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			KBComment.class.getName(), actionRequest);

		if (cmd.equals(Constants.ADD)) {
			KBCommentLocalServiceUtil.addKBComment(
				themeDisplay.getUserId(), classNameId, classPK, content,
				helpful, serviceContext);
		}
		else if (cmd.equals(Constants.UPDATE)) {
			KBCommentLocalServiceUtil.updateKBComment(
				kbCommentId, classNameId, classPK, content, helpful,
				serviceContext);
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
	protected void addSuccessMessage(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		String actionName = ParamUtil.getString(
			actionRequest, ActionRequest.ACTION_NAME);

		if (actionName.equals("updateAttachments")) {
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
				renderRequest, NoSuchTemplateException.class.getName()) ||
			SessionErrors.contains(
				renderRequest, PrincipalException.class.getName())) {

			include(jspPath + "error.jsp", renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof DuplicateFileException ||
			cause instanceof FileNameException ||
			cause instanceof FileSizeException ||
			cause instanceof KBArticleContentException ||
			cause instanceof KBArticlePriorityException ||
			cause instanceof KBArticleTitleException ||
			cause instanceof KBCommentContentException ||
			cause instanceof KBTemplateContentException ||
			cause instanceof KBTemplateTitleException ||
			cause instanceof NoSuchArticleException ||
			cause instanceof NoSuchCommentException ||
			cause instanceof NoSuchFileException ||
			cause instanceof NoSuchTemplateException ||
			cause instanceof PrincipalException) {

			return true;
		}

		return false;
	}

}