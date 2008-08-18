/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.knowledgebase.portlet;

import com.liferay.documentlibrary.service.DLLocalServiceUtil;
import com.liferay.knowledgebase.ArticleTitleException;
import com.liferay.knowledgebase.ArticleVersionException;
import com.liferay.knowledgebase.KnowledgeBaseKeys;
import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBFeedbackStats;
import com.liferay.knowledgebase.service.KBArticleServiceUtil;
import com.liferay.knowledgebase.service.KBFeedbackEntryLocalServiceUtil;
import com.liferay.knowledgebase.service.KBFeedbackStatsLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.DocumentConversionUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.tags.EntryNameException;
import com.liferay.portlet.tags.model.TagsEntry;
import com.liferay.portlet.tags.model.TagsEntryConstants;
import com.liferay.portlet.tags.model.TagsVocabulary;
import com.liferay.portlet.tags.service.TagsEntryServiceUtil;
import com.liferay.portlet.tags.service.TagsVocabularyServiceUtil;
import com.liferay.util.RSSUtil;
import com.liferay.util.bridges.jsp.JSPPortlet;
import com.liferay.util.servlet.PortletResponseUtil;
import com.liferay.util.servlet.ServletResponseUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="KnowledgeBasePortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 * @author Bruno Farache
 * @author Alvaro del Castillo
 * @author Peter Shin
 *
 */
public class KnowledgeBasePortlet extends JSPPortlet {

	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		try {
			String actionName = ParamUtil.getString(
				actionRequest, "actionName");

			KBArticle article = null;

			if (actionName.equals(Constants.DELETE)) {
				deleteArticle(actionRequest);
			}
			else if (actionName.equals(Constants.UPDATE)) {
				article = updateArticle(actionRequest);
			}
			else if (actionName.equals(Constants.SUBSCRIBE)) {
				subscribe(actionRequest);
			}
			else if (actionName.equals(Constants.UNSUBSCRIBE)) {
				unsubscribe(actionRequest);
			}
			else if (actionName.equals(Constants.REVERT)) {
				revertPage(actionRequest);
			}
			else if (actionName.equals("subscribeArticle")) {
				subscribeArticle(actionRequest);
			}
			else if (actionName.equals("unsubscribeArticle")) {
				unsubscribeArticle(actionRequest);
			}
			else if (actionName.equals("addFile")) {
				addAttachment(actionRequest);
			}
			else if (actionName.equals("deleteAttachment")) {
				deleteAttachment(actionRequest);
			}

			boolean preview = ParamUtil.getBoolean(actionRequest, "preview");

			if (preview) {
				actionResponse.setRenderParameters(
					actionRequest.getParameterMap());
				actionResponse.setRenderParameter("view", "edit_article");
			}
			else if (Validator.isNotNull(actionName)) {
				String redirect = ParamUtil.getString(
					actionRequest, "redirect");

				if (article != null) {
					String saveAndContinueRedirect = ParamUtil.getString(
						actionRequest, "saveAndContinueRedirect");

					if (Validator.isNotNull(saveAndContinueRedirect)) {
						redirect = saveAndContinueRedirect;
					}

					if (Validator.isNotNull(redirect)) {
						redirect = HttpUtil.addParameter(
							redirect, "resourcePrimKey",
							article.getResourcePrimKey());

						redirect = HttpUtil.addParameter(
							redirect, "title", article.getTitle());
					}
				}

				if (SessionErrors.isEmpty(actionRequest)) {
					SessionMessages.add(actionRequest, "request_processed");
				}

				sendRedirect(actionRequest, actionResponse, redirect);

				return;
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchArticleException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass().getName());

				actionResponse.setRenderParameters(
					actionRequest.getParameterMap());
				actionResponse.setRenderParameter("view", "error");
			}
			else if (e instanceof ArticleTitleException ||
				e instanceof ArticleVersionException ||
				e instanceof EntryNameException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass().getName());

				actionResponse.setRenderParameters(
					actionRequest.getParameterMap());
				actionResponse.setRenderParameter(
					"view", "edit_article");
			}
			else {
				throw new PortletException(e);
			}
		}
	}

	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException, IOException {

		// Store beans in the Request object

		try {
			PortletUtil.getKBBeans(renderRequest);
		}
		catch (Exception e) {
			throw new PortletException(e);
		}

		// Allow only article authors or OmniAdmins to view unpublished articles

		checkViewable(renderRequest);

		super.render(renderRequest, renderResponse);
	}

	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		try {
			String actionName = ParamUtil.getString(
				resourceRequest, "actionName");

			if (actionName.equals("get_article_attachment")) {
				String title = ParamUtil.getString(resourceRequest, "title");
				String fileName = ParamUtil.getString(
					resourceRequest, "fileName");

				getFile(title, fileName, resourceRequest, resourceResponse);
			}
			else if (actionName.equals("convert")) {
				convertArticle(resourceRequest, resourceResponse);
			}
			else if (actionName.equals("feedback_comments")) {
				saveFeedbackComments(resourceRequest, resourceResponse);
			}
			else if (actionName.equals("feedback_score")) {
				saveFeedbackScore(resourceRequest, resourceResponse);
			}
			else if (actionName.equals("feedback_vote")) {
				saveFeedbackVote(resourceRequest, resourceResponse);
			}
			else if (actionName.equals("get_template")) {
				getTemplate(resourceRequest, resourceResponse);
			}
			else if (actionName.equals("rss")) {
				getRSS(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
		}
	}

	protected void addAttachment(ActionRequest actionRequest)
		throws Exception {

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(
			actionRequest);

		long resourcePrimKey = ParamUtil.getLong(
			uploadRequest, "resourcePrimKey");

		int numOfFiles = ParamUtil.getInteger(uploadRequest, "numOfFiles");

		List<ObjectValuePair<String, byte[]>> files =
			new ArrayList<ObjectValuePair<String, byte[]>>();

		if (numOfFiles == 0) {
			File file = uploadRequest.getFile("file");
			String fileName = uploadRequest.getFileName("file");

			if (file != null) {
				byte[] bytes = FileUtil.getBytes(file);

				if ((bytes != null) && (bytes.length > 0)) {
					ObjectValuePair<String, byte[]> ovp =
						new ObjectValuePair<String, byte[]>(fileName, bytes);

					files.add(ovp);
				}
			}
		}
		else {
			for (int i = 1; i <= numOfFiles; i++) {
				File file = uploadRequest.getFile("file" + i);

				String fileName = uploadRequest.getFileName("file" + i);

				if (file != null) {
					byte[] bytes = FileUtil.getBytes(file);

					if ((bytes != null) && (bytes.length > 0)) {
						ObjectValuePair<String, byte[]> ovp =
							new ObjectValuePair<String, byte[]>(
								fileName, bytes);

						files.add(ovp);
					}
				}
			}
		}

		KBArticleServiceUtil.addArticleAttachments(resourcePrimKey, files);
	}

	protected void checkViewable(RenderRequest renderRequest) {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			renderRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		KBArticle article = (KBArticle) renderRequest.getAttribute(
			KnowledgeBaseKeys.ARTICLE);

		boolean draft = BeanParamUtil.getBoolean(
			article, request, "draft", false);
		long articleAuthorId = BeanParamUtil.getLong(
			article, request, "userId", 0);
		long userId = themeDisplay.getUserId();

		if (permissionChecker.isOmniadmin()) {
			renderRequest.setAttribute("isViewableArticle", "true");
		}
		else if (draft && (articleAuthorId != userId)) {
			renderRequest.setAttribute("isViewableArticle", "false");
		}
		else {
			renderRequest.setAttribute("isViewableArticle", "true");
		}
	}

	protected void convertArticle(
		ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		InputStream is = null;

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)resourceRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			PortletPreferences prefs = resourceRequest.getPreferences();

			long groupId = themeDisplay.getPortletGroupId();
			String title = ParamUtil.getString(resourceRequest, "title");
			double version = ParamUtil.getDouble(resourceRequest, "version");

			String targetExtension = ParamUtil.getString(
				resourceRequest, "targetExtension");

			String[] extensions = prefs.getValues(
				"extensions", new String[] {});

			boolean convert = false;

			for (String extension : extensions) {
				if (extension.equals(targetExtension)) {
					convert = true;

					break;
				}
			}

			if (!convert) {
				return;
			}

			KBArticle article =	KBArticleServiceUtil.getArticle(
				groupId, title, version);

			String content = article.getContent();

			StringBuilder sb = new StringBuilder();

			sb.append("<h1>");
			sb.append(title);
			sb.append("</h1>");
			sb.append(content);

			is = new ByteArrayInputStream(
				sb.toString().getBytes(StringPool.UTF8));

			String sourceExtension = "html";

			sb = new StringBuilder();

			sb.append(title);
			sb.append(StringPool.PERIOD);
			sb.append(sourceExtension);

			String fileName = sb.toString();

			String id = article.getUuid();

			InputStream convertedIS = DocumentConversionUtil.convert(
				id, is, sourceExtension, targetExtension);

			if (convertedIS != null) {
				sb = new StringBuilder();

				sb.append(title);
				sb.append(StringPool.PERIOD);
				sb.append(targetExtension);

				fileName = sb.toString();

				is = convertedIS;
			}

			String contentType = MimeTypesUtil.getContentType(fileName);

			PortletResponseUtil.sendFile(
				resourceResponse, fileName, is, contentType);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
		finally {
			ServletResponseUtil.cleanUp(is);
		}
	}

	protected void deleteArticle(ActionRequest actionRequest) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		KBArticleServiceUtil.deleteArticle(
			themeDisplay.getPlid(), resourcePrimKey);
	}

	protected void deleteAttachment(ActionRequest actionRequest)
		throws Exception {

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");
		String attachment = ParamUtil.getString(actionRequest, "fileName");

		KBArticleServiceUtil.deleteArticleAttachment(
			resourcePrimKey, attachment);
	}

	protected void getFile(
			String title, String fileName, ResourceRequest request,
			ResourceResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		int pos = fileName.indexOf(StringPool.SLASH);

		if (pos != -1) {
			title = fileName.substring(0, pos);
			fileName = fileName.substring(pos + 1);
		}

		InputStream is = null;

		try {
			KBArticle page = KBArticleServiceUtil.getArticle(
				themeDisplay.getPortletGroupId(), title);

			is = DLLocalServiceUtil.getFileAsStream(
				page.getCompanyId(), CompanyConstants.SYSTEM,
				page.getAttachmentsDir() + "/" + fileName);

			String contentType = MimeTypesUtil.getContentType(fileName);

			PortletResponseUtil.sendFile(response, fileName, is, contentType);
		}
		finally {
			ServletResponseUtil.cleanUp(is);
		}
	}

	protected void getRSS(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long groupId = ParamUtil.getLong(resourceRequest, "groupId");
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
		int abstractLength = ParamUtil.getInteger(
			resourceRequest, "abstractLength", SearchContainer.DEFAULT_DELTA);

		String feedURL = PortalUtil.getPortalURL(themeDisplay) +
			PortalUtil.getLayoutURL(themeDisplay);

		String rss = StringPool.BLANK;

		if (resourcePrimKey > 0) {
			rss = KBArticleServiceUtil.getArticlesRSS(
				resourcePrimKey, max, type, version, displayStyle,
				abstractLength, feedURL);
		}
		else if (groupId > 0) {
			rss = KBArticleServiceUtil.getGroupArticlesRSS(
				groupId, max, type, version, displayStyle,  abstractLength,
				feedURL);
		}

		HttpServletResponse response = PortalUtil.getHttpServletResponse(
			resourceResponse);

		ServletResponseUtil.sendFile(
			response, null, rss.getBytes(StringPool.UTF8),
			ContentTypes.TEXT_XML_UTF8);
	}

	protected void getTemplate(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long templateResourcePrimKey = ParamUtil.getLong(
			resourceRequest, "templateResourcePrimKey");

		KBArticle template =
			KBArticleServiceUtil.getArticle(templateResourcePrimKey);

		Map<String, String> parameterMap = new HashMap<String, String>();

		parameterMap.put("content", template.getContent());

		sendJSON(resourceResponse, parameterMap);
	}

	protected void revertPage(ActionRequest actionRequest) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletPreferences prefs = actionRequest.getPreferences();

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");
		double version = ParamUtil.getDouble(actionRequest, "version");

		KBArticleServiceUtil.revertArticle(
			resourcePrimKey, version, prefs, themeDisplay);
	}

	protected void saveFeedbackComments(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long articleResourcePrimKey = ParamUtil.getLong(
			resourceRequest, "articleResourcePrimKey");
		long userId = ParamUtil.getLong(resourceRequest, "userId");
		String comments = ParamUtil.getString(resourceRequest, "comments");

		KBFeedbackEntryLocalServiceUtil.updateComments(
			articleResourcePrimKey, userId, comments);

		Map<String, String> parameterMap = new HashMap<String, String>();

		sendJSON(resourceResponse, parameterMap);
	}

	protected void saveFeedbackScore(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		int score = ParamUtil.getInteger(resourceRequest, "score");
		long articleResourcePrimKey = ParamUtil.getLong(
			resourceRequest, "articleResourcePrimKey");
		long userId = ParamUtil.getLong(resourceRequest, "userId");

		KBFeedbackEntryLocalServiceUtil.updateScore(
			articleResourcePrimKey, userId, score);

		KBFeedbackStats feedbackStats =
			KBFeedbackStatsLocalServiceUtil.getArticleFeedbackStats(
				articleResourcePrimKey);

		Map<String, String> parameterMap = new HashMap<String, String>();

		parameterMap.put(
			"averageScore", String.valueOf(feedbackStats.getAverageScore()));
		parameterMap.put(
			"totalScoreEntries",
			String.valueOf(feedbackStats.getTotalScoreEntries()));

		sendJSON(resourceResponse, parameterMap);
	}

	protected void saveFeedbackVote(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		int vote = ParamUtil.getInteger(resourceRequest, "vote");
		long articleResourcePrimKey = ParamUtil.getLong(
			resourceRequest, "articleResourcePrimKey");
		long userId = ParamUtil.getLong(resourceRequest, "userId");

		KBFeedbackEntryLocalServiceUtil.updateVote(
			articleResourcePrimKey, userId, vote);

		KBFeedbackStats feedbackStats =
			KBFeedbackStatsLocalServiceUtil.getArticleFeedbackStats(
				articleResourcePrimKey);

		Map<String, String> parameterMap = new HashMap<String, String>();

		parameterMap.put(
			"totalVotes", String.valueOf(feedbackStats.getTotalVotes()));
		parameterMap.put(
			"yesVotes", String.valueOf(feedbackStats.getYesVotes()));

		sendJSON(resourceResponse, parameterMap);
	}

	protected void sendJSON(
			ResourceResponse resourceResponse, Map<String, String> parameterMap)
		throws IOException {

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			jsonObj.put(key, value);
		}

		String contentType = ContentTypes.APPLICATION_TEXT;

		resourceResponse.setContentType(contentType);
		resourceResponse.getWriter().write(jsonObj.toString());
	}

	protected void sendRedirect(
			ActionRequest actionRequest, ActionResponse actionResponse,
			String redirect)
		throws IOException {

		if (SessionErrors.isEmpty(actionRequest)) {
			SessionMessages.add(actionRequest, "request_processed");
		}

		if (redirect == null) {
			redirect = ParamUtil.getString(actionRequest, "redirect");
		}

		if (Validator.isNotNull(redirect)) {
			actionResponse.sendRedirect(redirect);
		}
	}

	protected void subscribe(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long groupId = themeDisplay.getPortletGroupId();

		KBArticleServiceUtil.subscribe(groupId);
	}

	protected void subscribeArticle(ActionRequest actionRequest)
		throws Exception {

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		KBArticleServiceUtil.subscribeArticle(resourcePrimKey);
	}

	protected void unsubscribe(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long groupId = themeDisplay.getPortletGroupId();

		KBArticleServiceUtil.unsubscribe(groupId);
	}

	protected void unsubscribeArticle(ActionRequest actionRequest)
		throws Exception {

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		KBArticleServiceUtil.unsubscribeArticle(resourcePrimKey);
	}

	protected KBArticle updateArticle(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletPreferences prefs = actionRequest.getPreferences();

		boolean addTemplate = ParamUtil.getBoolean(
			actionRequest, "addTemplate", false);
		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");
		long templateResourcePrimKey = ParamUtil.getLong(
			actionRequest, "templateResourcePrimKey");

		String title = ParamUtil.getString(actionRequest, "title");
		double version = ParamUtil.getDouble(actionRequest, "version");
		String content = ParamUtil.getString(actionRequest, "content");
		String description = ParamUtil.getString(actionRequest, "description");
		boolean minorEdit = ParamUtil.getBoolean(actionRequest, "minorEdit");
		boolean template = ParamUtil.getBoolean(actionRequest, "template");
		boolean draft = ParamUtil.getBoolean(actionRequest, "draft");
		long parentResourcePrimKey = ParamUtil.getLong(
			actionRequest, "parentResourcePrimKey");

		List<TagsVocabulary> vocabularies =
			TagsVocabularyServiceUtil.getCompanyVocabularies(
				themeDisplay.getCompanyId(), false);

		String tagsEntriesString = ParamUtil.getString(
			actionRequest, "tagsEntries");

		for (TagsVocabulary vocabulary : vocabularies) {
			String vocabularyParamName =
				TagsEntryConstants.VOCABULARY +
				Long.toString(vocabulary.getVocabularyId());

			long entryId = ParamUtil.getLong(
				actionRequest, vocabularyParamName);

			if (Validator.isNotNull(entryId)) {
				TagsEntry entry = TagsEntryServiceUtil.getEntry(entryId);

				tagsEntriesString += "," + entry.getName();
			}
		}

		String[] tagsEntries = StringUtil.split(tagsEntriesString);

		if (addTemplate) {
			KBArticle selectedTemplate = KBArticleServiceUtil.getArticle(
				templateResourcePrimKey);

			content = selectedTemplate.getContent() + content;
		}

		if (resourcePrimKey <= 0) {
			return KBArticleServiceUtil.addArticle(
				themeDisplay.getPlid(), title, content, description,
				minorEdit, template, draft, parentResourcePrimKey, tagsEntries,
				prefs, themeDisplay);
		}
		else {
			KBArticle article = KBArticleServiceUtil.getArticle(
				resourcePrimKey);

			if (article.isTemplate() && (template == false)) {
				return KBArticleServiceUtil.addArticle(
					themeDisplay.getPortletGroupId(), title, content,
					description, minorEdit, template, draft,
					parentResourcePrimKey, tagsEntries, prefs, themeDisplay);
			}
			else {
				return KBArticleServiceUtil.updateArticle(
					themeDisplay.getPlid(), resourcePrimKey, version, title,
					content, description, minorEdit, template, draft,
					parentResourcePrimKey, tagsEntries, prefs, themeDisplay);
			}
		}
	}

	private static Log _log = LogFactory.getLog(KnowledgeBasePortlet.class);

}