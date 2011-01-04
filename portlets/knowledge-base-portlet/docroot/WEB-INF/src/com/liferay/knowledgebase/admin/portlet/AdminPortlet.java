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

import com.liferay.documentlibrary.DuplicateFileException;
import com.liferay.documentlibrary.FileSizeException;
import com.liferay.documentlibrary.NoSuchFileException;
import com.liferay.documentlibrary.service.DLLocalServiceUtil;
import com.liferay.knowledgebase.ArticleContentException;
import com.liferay.knowledgebase.ArticleTitleException;
import com.liferay.knowledgebase.CommentContentException;
import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.NoSuchCommentException;
import com.liferay.knowledgebase.NoSuchTemplateException;
import com.liferay.knowledgebase.TemplateContentException;
import com.liferay.knowledgebase.TemplateTitleException;
import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.model.Template;
import com.liferay.knowledgebase.service.ArticleServiceUtil;
import com.liferay.knowledgebase.service.CommentLocalServiceUtil;
import com.liferay.knowledgebase.service.TemplateServiceUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.knowledgebase.util.WebKeys;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.RSSUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.liferay.util.servlet.PortletResponseUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AdminPortlet extends MVCPortlet {

	public void addAttachment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(
			actionRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourcePrimKey = ParamUtil.getLong(
			uploadRequest, "resourcePrimKey");

		String dirName = ParamUtil.getString(uploadRequest, "dirName");
		File file = uploadRequest.getFile("file");
		String fileName = uploadRequest.getFileName("file");

		ArticleServiceUtil.addAttachment(
			themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
			resourcePrimKey, dirName, fileName, new FileInputStream(file));
	}

	public void deleteArticle(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		ArticleServiceUtil.deleteArticle(resourcePrimKey);
	}

	public void deleteAttachment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		String fileName = ParamUtil.getString(actionRequest, "fileName");

		ArticleServiceUtil.deleteAttachment(
			themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
			resourcePrimKey, fileName);
	}

	public void deleteComment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		long commentId = ParamUtil.getLong(actionRequest, "commentId");

		CommentLocalServiceUtil.deleteComment(commentId);
	}

	public void deleteTemplate(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long templateId = ParamUtil.getLong(actionRequest, "templateId");

		TemplateServiceUtil.deleteTemplate(templateId);
	}

	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException, IOException {

		try {
			Article article = null;

			long resourcePrimKey = ParamUtil.getLong(
				renderRequest, "resourcePrimKey");

			if (resourcePrimKey > 0) {
				article = ArticleServiceUtil.getLatestArticle(
					resourcePrimKey, getStatus(renderRequest));
			}

			renderRequest.setAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE, article);

			Template template = null;

			long templateId = ParamUtil.getLong(renderRequest, "templateId");

			if (templateId > 0) {
				template = TemplateServiceUtil.getTemplate(templateId);
			}

			renderRequest.setAttribute(
				WebKeys.KNOWLEDGE_BASE_TEMPLATE, template);

			renderRequest.setAttribute(
				WebKeys.KNOWLEDGE_BASE_STATUS, getStatus(renderRequest));
		}
		catch (Exception e) {
			if (e instanceof NoSuchArticleException ||
				e instanceof NoSuchCommentException ||
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
		InputStream is = DLLocalServiceUtil.getFileAsStream(
			companyId, CompanyConstants.SYSTEM, fileName);
		String contentType = MimeTypesUtil.getContentType(fileName);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, shortFileName, is, contentType);
	}

	public void serveRSS(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourcePrimKey = ParamUtil.getLong(
			resourceRequest, "resourcePrimKey");

		int max = ParamUtil.getInteger(
			resourceRequest, "max", SearchContainer.DEFAULT_DELTA);
		String type = ParamUtil.getString(
			resourceRequest, "type", RSSUtil.DEFAULT_TYPE);
		double version = ParamUtil.getDouble(
			resourceRequest, "version", RSSUtil.DEFAULT_VERSION);
		String displayStyle = ParamUtil.getString(
			resourceRequest, "displayStyle",
			RSSUtil.DISPLAY_STYLE_FULL_CONTENT);

		String portletId = PortalUtil.getPortletId(resourceRequest);

		String rss = StringPool.BLANK;

		if (resourcePrimKey <= 0) {
			rss = ArticleServiceUtil.getGroupArticlesRSS(
				portletId, getStatus(resourceRequest), max, type, version,
				displayStyle, isServeRSSMaximized(resourceRequest),
				themeDisplay);
		}
		else {
			rss = ArticleServiceUtil.getArticlesRSS(
				portletId, resourcePrimKey, getStatus(resourceRequest), max,
				type, version, displayStyle,
				isServeRSSMaximized(resourceRequest), themeDisplay);
		}

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, null,
			rss.getBytes(StringPool.UTF8), ContentTypes.TEXT_XML_UTF8);
	}

	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("attachment")) {
				serveAttachment(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("rss")) {
				serveRSS(resourceRequest, resourceResponse);
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

	public void subscribe(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		String portletId = PortalUtil.getPortletId(actionRequest);

		if (resourcePrimKey <= 0) {
			ArticleServiceUtil.subscribe(
				themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
				themeDisplay.getPlid(), portletId);
		}
		else {
			ArticleServiceUtil.subscribeArticle(
				themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
				themeDisplay.getPlid(), portletId, resourcePrimKey);
		}
	}

	public void unsubscribe(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		String portletId = PortalUtil.getPortletId(actionRequest);

		if (resourcePrimKey <= 0) {
			ArticleServiceUtil.unsubscribe(
				themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
				themeDisplay.getPlid(), portletId);
		}
		else {
			ArticleServiceUtil.unsubscribeArticle(
				themeDisplay.getCompanyId(), themeDisplay.getPlid(), portletId,
				resourcePrimKey);
		}
	}

	public void updateArticle(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		long parentResourcePrimKey = ParamUtil.getLong(
			actionRequest, "parentResourcePrimKey");
		String title = ParamUtil.getString(actionRequest, "title");
		String content = ParamUtil.getString(actionRequest, "content");
		String description = ParamUtil.getString(actionRequest, "description");
		int priority = ParamUtil.getInteger(actionRequest, "priority");
		String dirName = ParamUtil.getString(actionRequest, "dirName");
		int workflowAction = ParamUtil.getInteger(
			actionRequest, "workflowAction", WorkflowConstants.ACTION_PUBLISH);

		Article article = null;

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Article.class.getName(), actionRequest);

		if (cmd.equals(Constants.ADD)) {
			article = ArticleServiceUtil.addArticle(
				parentResourcePrimKey, title, content, description, priority,
				dirName, serviceContext);
		}
		else if (cmd.equals(Constants.UPDATE)) {
			article = ArticleServiceUtil.updateArticle(
				resourcePrimKey, parentResourcePrimKey, title, content,
				description, priority, dirName, serviceContext);
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
				editURL, namespace + "jspPage", "/admin/edit_article.jsp");
			editURL = HttpUtil.setParameter(
				editURL, namespace + "redirect", redirect);
			editURL = HttpUtil.setParameter(
				editURL, namespace + "resourcePrimKey",
				article.getResourcePrimKey());

			actionRequest.setAttribute(WebKeys.REDIRECT, editURL);
		}
	}

	public void updateAttachments(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		String dirName = ParamUtil.getString(actionRequest, "dirName");

		dirName = ArticleServiceUtil.updateAttachments(
			themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
			resourcePrimKey, dirName);

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		redirect = HttpUtil.setParameter(
			redirect, actionResponse.getNamespace() + "dirName", dirName);

		actionRequest.setAttribute(WebKeys.REDIRECT, redirect);
	}

	public void updateComment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long commentId = ParamUtil.getLong(actionRequest, "commentId");

		long classNameId = ParamUtil.getLong(actionRequest, "classNameId");
		long classPK = ParamUtil.getLong(actionRequest, "classPK");
		String content = ParamUtil.getString(actionRequest, "content");
		boolean helpful = ParamUtil.getBoolean(actionRequest, "helpful");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Article.class.getName(), actionRequest);

		if (cmd.equals(Constants.ADD)) {
			CommentLocalServiceUtil.addComment(
				themeDisplay.getUserId(), classNameId, classPK, content,
				helpful, serviceContext);
		}
		else if (cmd.equals(Constants.UPDATE)) {
			CommentLocalServiceUtil.updateComment(
				commentId, classNameId, classPK, content, helpful,
				serviceContext);
		}
	}

	public void updateTemplate(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long templateId = ParamUtil.getLong(actionRequest, "templateId");

		String title = ParamUtil.getString(actionRequest, "title");
		String content = ParamUtil.getString(actionRequest, "content");
		String description = ParamUtil.getString(actionRequest, "description");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Template.class.getName(), actionRequest);

		if (cmd.equals(Constants.ADD)) {
			TemplateServiceUtil.addTemplate(
				title, content, description, serviceContext);
		}
		else if (cmd.equals(Constants.UPDATE)) {
			TemplateServiceUtil.updateTemplate(
				templateId, title, content, description, serviceContext);
		}
	}

	protected void addSuccessMessage(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		String actionName = ParamUtil.getString(
			actionRequest, ActionRequest.ACTION_NAME);

		if (actionName.equals("updateAttachments")) {
			return;
		}

		super.addSuccessMessage(actionRequest, actionResponse);
	}

	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (SessionErrors.contains(
				renderRequest, NoSuchArticleException.class.getName()) ||
			SessionErrors.contains(
				renderRequest, NoSuchCommentException.class.getName()) ||
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

	protected int getStatus(PortletRequest portletRequest) {
		return WorkflowConstants.STATUS_ANY;
	}

	protected boolean isServeRSSMaximized(ResourceRequest resourceRequest) {
		return _SERVE_RSS_MAXIMIZED;
	}

	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof ArticleContentException ||
			cause instanceof ArticleTitleException ||
			cause instanceof CommentContentException ||
			cause instanceof DuplicateFileException ||
			cause instanceof FileSizeException ||
			cause instanceof NoSuchArticleException ||
			cause instanceof NoSuchCommentException ||
			cause instanceof NoSuchFileException ||
			cause instanceof NoSuchTemplateException ||
			cause instanceof PrincipalException ||
			cause instanceof TemplateContentException ||
			cause instanceof TemplateTitleException) {

			return true;
		}

		return false;
	}

	private static final boolean _SERVE_RSS_MAXIMIZED = false;

}