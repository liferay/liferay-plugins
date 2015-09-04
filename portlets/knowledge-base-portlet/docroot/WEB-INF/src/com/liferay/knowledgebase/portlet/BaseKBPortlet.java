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

package com.liferay.knowledgebase.portlet;

import com.liferay.knowledgebase.KBArticleContentException;
import com.liferay.knowledgebase.KBArticlePriorityException;
import com.liferay.knowledgebase.KBArticleTitleException;
import com.liferay.knowledgebase.KBCommentContentException;
import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.NoSuchCommentException;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBArticleConstants;
import com.liferay.knowledgebase.model.KBComment;
import com.liferay.knowledgebase.model.KBCommentConstants;
import com.liferay.knowledgebase.model.KBFolderConstants;
import com.liferay.knowledgebase.service.KBArticleServiceUtil;
import com.liferay.knowledgebase.service.KBCommentLocalServiceUtil;
import com.liferay.knowledgebase.service.KBCommentServiceUtil;
import com.liferay.knowledgebase.service.KBFolderServiceUtil;
import com.liferay.knowledgebase.util.KnowledgeBaseConstants;
import com.liferay.knowledgebase.util.WebKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadException;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.AssetCategoryException;
import com.liferay.portlet.asset.AssetTagException;
import com.liferay.portlet.documentlibrary.DuplicateFileException;
import com.liferay.portlet.documentlibrary.FileNameException;
import com.liferay.portlet.documentlibrary.FileSizeException;
import com.liferay.portlet.documentlibrary.NoSuchFileException;

import java.io.IOException;
import java.io.InputStream;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Adolfo Pérez
 */
public abstract class BaseKBPortlet extends MVCPortlet {

	public void addTempAttachment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");
		String sourceFileName = uploadPortletRequest.getFileName("file");

		InputStream inputStream = null;

		try {
			inputStream = uploadPortletRequest.getFileAsStream("file");

			String mimeType = uploadPortletRequest.getContentType("file");

			KBArticleServiceUtil.addTempAttachment(
				themeDisplay.getScopeGroupId(), resourcePrimKey, sourceFileName,
				KnowledgeBaseConstants.TEMP_FOLDER_NAME, inputStream, mimeType);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	public void deleteKBArticle(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		KBArticleServiceUtil.deleteKBArticle(resourcePrimKey);
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

		KBCommentServiceUtil.deleteKBComment(kbCommentId);

		SessionMessages.add(actionRequest, "suggestionDeleted");
	}

	public void deleteTempAttachment(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");
		String fileName = ParamUtil.getString(actionRequest, "fileName");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			KBArticleServiceUtil.deleteTempAttachment(
				themeDisplay.getScopeGroupId(), resourcePrimKey, fileName,
				KnowledgeBaseConstants.TEMP_FOLDER_NAME);

			jsonObject.put("deleted", Boolean.TRUE);
		}
		catch (Exception e) {
			String errorMessage = themeDisplay.translate(
				"an-unexpected-error-occurred-while-deleting-the-file");

			jsonObject.put("deleted", Boolean.FALSE);
			jsonObject.put("errorMessage", errorMessage);
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void moveKBObject(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long resourceClassNameId = ParamUtil.getLong(
			actionRequest, "resourceClassNameId");
		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");
		long parentResourceClassNameId = ParamUtil.getLong(
			actionRequest, "parentResourceClassNameId",
			PortalUtil.getClassNameId(KBFolderConstants.getClassName()));
		long parentResourcePrimKey = ParamUtil.getLong(
			actionRequest, "parentResourcePrimKey",
			KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);
		double priority = ParamUtil.getDouble(actionRequest, "priority");

		long kbArticleClassNameId = PortalUtil.getClassNameId(
			KBArticleConstants.getClassName());

		if (resourceClassNameId == kbArticleClassNameId) {
			KBArticleServiceUtil.moveKBArticle(
				resourcePrimKey, parentResourceClassNameId,
				parentResourcePrimKey, priority);
		}
		else {
			KBFolderServiceUtil.moveKBFolder(
				resourcePrimKey, parentResourcePrimKey);
		}
	}

	public void serveKBArticleRSS(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		PortletPreferences portletPreferences =
			resourceRequest.getPreferences();

		boolean enableRss = GetterUtil.getBoolean(
			portletPreferences.getValue("enableRss", null), true);

		if (!PortalUtil.isRSSFeedsEnabled() || !enableRss) {
			PortalUtil.sendRSSFeedsDisabledError(
				resourceRequest, resourceResponse);

			return;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourcePrimKey = ParamUtil.getLong(
			resourceRequest, "resourcePrimKey");

		int rssDelta = ParamUtil.getInteger(resourceRequest, "rssDelta");
		String rssDisplayStyle = ParamUtil.getString(
			resourceRequest, "rssDisplayStyle");
		String rssFormat = ParamUtil.getString(resourceRequest, "rssFormat");

		String rss = KBArticleServiceUtil.getKBArticleRSS(
			resourcePrimKey, WorkflowConstants.STATUS_APPROVED, rssDelta,
			rssDisplayStyle, rssFormat, themeDisplay);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, null,
			rss.getBytes(StringPool.UTF8), ContentTypes.TEXT_XML_UTF8);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("kbArticleRSS")) {
				serveKBArticleRSS(resourceRequest, resourceResponse);
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

	public void unsubscribeKBArticle(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		KBArticleServiceUtil.unsubscribeKBArticle(resourcePrimKey);
	}

	public void updateKBArticle(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String portletId = PortalUtil.getPortletId(actionRequest);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");
		long parentResourceClassNameId = ParamUtil.getLong(
			actionRequest, "parentResourceClassNameId",
			PortalUtil.getClassNameId(KBFolderConstants.getClassName()));
		long parentResourcePrimKey = ParamUtil.getLong(
			actionRequest, "parentResourcePrimKey",
			KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		String title = ParamUtil.getString(actionRequest, "title");
		String urlTitle = ParamUtil.getString(actionRequest, "urlTitle");
		String content = ParamUtil.getString(actionRequest, "content");
		String description = ParamUtil.getString(actionRequest, "description");
		String sourceURL = ParamUtil.getString(actionRequest, "sourceURL");
		String[] sections = actionRequest.getParameterValues("sections");
		String[] selectedFileNames = ParamUtil.getParameterValues(
			actionRequest, "selectedFileName");
		long[] removeFileEntryIds = ParamUtil.getLongValues(
			actionRequest, "removeFileEntryIds");
		int workflowAction = ParamUtil.getInteger(
			actionRequest, "workflowAction");

		KBArticle kbArticle = null;

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			KBArticle.class.getName(), actionRequest);

		if (cmd.equals(Constants.ADD)) {
			kbArticle = KBArticleServiceUtil.addKBArticle(
				portletId, parentResourceClassNameId, parentResourcePrimKey,
				title, urlTitle, content, description, sourceURL, sections,
				selectedFileNames, serviceContext);
		}
		else if (cmd.equals(Constants.REVERT)) {
			int version = ParamUtil.getInteger(
				actionRequest, "version", KBArticleConstants.DEFAULT_VERSION);

			kbArticle = KBArticleServiceUtil.revertKBArticle(
				resourcePrimKey, version, serviceContext);
		}
		else if (cmd.equals(Constants.UPDATE)) {
			kbArticle = KBArticleServiceUtil.updateKBArticle(
				resourcePrimKey, title, content, description, sourceURL,
				sections, selectedFileNames, removeFileEntryIds,
				serviceContext);
		}

		if (!cmd.equals(Constants.ADD) && !cmd.equals(Constants.UPDATE)) {
			return;
		}

		if (workflowAction == WorkflowConstants.ACTION_SAVE_DRAFT) {
			String editURL = buildEditURL(
				actionRequest, actionResponse, kbArticle);

			actionRequest.setAttribute(WebKeys.REDIRECT, editURL);
		}
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
		int status = ParamUtil.getInteger(
			actionRequest, "status", KBCommentConstants.STATUS_ANY);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			KBComment.class.getName(), actionRequest);

		if (cmd.equals(Constants.ADD)) {
			KBCommentLocalServiceUtil.addKBComment(
				themeDisplay.getUserId(), classNameId, classPK, content,
				serviceContext);
		}
		else if (cmd.equals(Constants.UPDATE)) {
			if (status == KBCommentConstants.STATUS_ANY) {
				KBComment kbComment = KBCommentServiceUtil.getKBComment(
					kbCommentId);

				status = kbComment.getStatus();
			}

			KBCommentServiceUtil.updateKBComment(
				kbCommentId, classNameId, classPK, content, status,
				serviceContext);
		}

		SessionMessages.add(actionRequest, "suggestionSaved");
	}

	public void updateKBCommentStatus(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

		long kbCommentId = ParamUtil.getLong(actionRequest, "kbCommentId");

		int status = ParamUtil.getInteger(actionRequest, "kbCommentStatus");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			KBComment.class.getName(), actionRequest);

		KBCommentServiceUtil.updateStatus(kbCommentId, status, serviceContext);

		SessionMessages.add(actionRequest, "suggestionStatusUpdated");
	}

	protected String buildEditURL(
			ActionRequest actionRequest, ActionResponse actionResponse,
			KBArticle kbArticle)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String editURL = PortalUtil.getLayoutFullURL(themeDisplay);

		editURL = HttpUtil.setParameter(
			editURL, "p_p_id", portletDisplay.getId());
		editURL = HttpUtil.setParameter(
			editURL, actionResponse.getNamespace() + "mvcPath",
			templatePath + "edit_article.jsp");
		editURL = HttpUtil.setParameter(
			editURL, actionResponse.getNamespace() + "redirect",
			getRedirect(actionRequest, actionResponse));
		editURL = HttpUtil.setParameter(
			editURL, actionResponse.getNamespace() + "resourcePrimKey",
			kbArticle.getResourcePrimKey());
		editURL = HttpUtil.setParameter(
			editURL, actionResponse.getNamespace() + "status",
			WorkflowConstants.STATUS_ANY);

		return editURL;
	}

	protected void checkExceededSizeLimit(HttpServletRequest request)
		throws PortalException {

		UploadException uploadException = (UploadException)request.getAttribute(
			WebKeys.UPLOAD_EXCEPTION);

		if (uploadException != null) {
			if (uploadException.isExceededSizeLimit()) {
				throw new FileSizeException(uploadException.getCause());
			}

			throw new PortalException(uploadException.getCause());
		}
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof AssetCategoryException ||
			cause instanceof AssetTagException ||
			cause instanceof DuplicateFileException ||
			cause instanceof FileNameException ||
			cause instanceof FileSizeException ||
			cause instanceof KBArticleContentException ||
			cause instanceof KBArticlePriorityException ||
			cause instanceof KBArticleTitleException ||
			cause instanceof KBCommentContentException ||
			cause instanceof NoSuchArticleException ||
			cause instanceof NoSuchCommentException ||
			cause instanceof NoSuchFileException ||
			cause instanceof PrincipalException ||
			super.isSessionErrorException(cause)) {

			return true;
		}

		return false;
	}

}