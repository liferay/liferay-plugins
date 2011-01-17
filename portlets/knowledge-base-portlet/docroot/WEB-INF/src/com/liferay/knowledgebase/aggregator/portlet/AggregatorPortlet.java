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

package com.liferay.knowledgebase.aggregator.portlet;

import com.liferay.documentlibrary.service.DLLocalServiceUtil;
import com.liferay.knowledgebase.CommentContentException;
import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.NoSuchCommentException;
import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.service.ArticleServiceUtil;
import com.liferay.knowledgebase.service.CommentLocalServiceUtil;
import com.liferay.knowledgebase.util.WebKeys;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
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
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.liferay.util.servlet.PortletResponseUtil;

import java.io.IOException;
import java.io.InputStream;

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
 */
public class AggregatorPortlet extends MVCPortlet {

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

	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException, IOException {

		try {
			Article article = null;

			long resourcePrimKey = ParamUtil.getLong(
				renderRequest, "resourcePrimKey");

			if (resourcePrimKey > 0) {
				article = ArticleServiceUtil.getLatestArticle(
					resourcePrimKey, _STATUS);
			}

			renderRequest.setAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE, article);

			renderRequest.setAttribute(WebKeys.KNOWLEDGE_BASE_STATUS, _STATUS);
		}
		catch (Exception e) {
			if (e instanceof NoSuchArticleException ||
				e instanceof NoSuchCommentException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass().getName());
			}
			else {
				throw new PortletException(e);
			}
		}

		super.render(renderRequest, renderResponse);
	}

	public void serveArticleRSS(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourcePrimKey = ParamUtil.getLong(
			resourceRequest, "resourcePrimKey");

		int rssDelta = ParamUtil.getInteger(resourceRequest, "rssDelta");
		String rssDisplayStyle = ParamUtil.getString(
			resourceRequest, "rssDisplayStyle");
		String rssFormat = ParamUtil.getString(resourceRequest, "rssFormat");

		String portletId = PortalUtil.getPortletId(resourceRequest);

		String rss = ArticleServiceUtil.getArticleRSS(
			portletId, resourcePrimKey, _STATUS, rssDelta, rssDisplayStyle,
			rssFormat, themeDisplay);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, null,
			rss.getBytes(StringPool.UTF8), ContentTypes.TEXT_XML_UTF8);
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

	public void serveGroupArticlesRSS(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		int rssDelta = ParamUtil.getInteger(resourceRequest, "rssDelta");
		String rssDisplayStyle = ParamUtil.getString(
			resourceRequest, "rssDisplayStyle");
		String rssFormat = ParamUtil.getString(resourceRequest, "rssFormat");

		String portletId = PortalUtil.getPortletId(resourceRequest);

		String rss = ArticleServiceUtil.getGroupArticlesRSS(
			portletId, _STATUS, rssDelta, rssDisplayStyle, rssFormat,
			themeDisplay);

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
			else if (resourceID.equals("articleRSS")) {
				serveArticleRSS(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("groupArticlesRSS")) {
				serveGroupArticlesRSS(resourceRequest, resourceResponse);
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

	public void subscribeArticle(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		ArticleServiceUtil.subscribeArticle(
			themeDisplay.getScopeGroupId(), resourcePrimKey);
	}

	public void subscribeGroupArticles(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String portletId = PortalUtil.getPortletId(actionRequest);

		ArticleServiceUtil.subscribeGroupArticles(
			themeDisplay.getScopeGroupId(), portletId);
	}

	public void unsubscribeArticle(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		ArticleServiceUtil.unsubscribeArticle(resourcePrimKey);
	}

	public void unsubscribeGroupArticles(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String portletId = PortalUtil.getPortletId(actionRequest);

		ArticleServiceUtil.unsubscribeGroupArticles(
			themeDisplay.getScopeGroupId(), portletId);
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

	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (SessionErrors.contains(
				renderRequest, NoSuchArticleException.class.getName()) ||
			SessionErrors.contains(
				renderRequest, NoSuchCommentException.class.getName()) ||
			SessionErrors.contains(
				renderRequest, PrincipalException.class.getName())) {

			include(jspPath + "error.jsp", renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof CommentContentException ||
			cause instanceof NoSuchArticleException ||
			cause instanceof NoSuchCommentException ||
			cause instanceof PrincipalException) {

			return true;
		}

		return false;
	}

	private static final int _STATUS = WorkflowConstants.STATUS_APPROVED;

}