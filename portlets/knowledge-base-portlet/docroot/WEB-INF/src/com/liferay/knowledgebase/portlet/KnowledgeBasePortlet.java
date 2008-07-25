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
import com.liferay.knowledgebase.service.KBArticleServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Constants;
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
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.tags.EntryNameException;
import com.liferay.util.bridges.jsp.JSPPortlet;
import com.liferay.util.servlet.PortletResponseUtil;
import com.liferay.util.servlet.ServletResponseUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="KnowledgeBasePortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 * @author Bruno Farache
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
			else if (actionName.equals("addAttachment")) {
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
//					else if (redirect.endsWith("title=")) {
//						redirect += article.getTitle();
//					}

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

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourcePrimKey = ParamUtil.getLong(
			renderRequest, "resourcePrimKey");

		String title = ParamUtil.getString(renderRequest, "title");
		double version = ParamUtil.getDouble(renderRequest, "version");

		try {
			KBArticle article = null;

			if (resourcePrimKey > 0) {
				article = KBArticleServiceUtil.getArticle(
					resourcePrimKey, version);
			}
			else if (Validator.isNotNull(title)) {
				article = KBArticleServiceUtil.getArticle(
					themeDisplay.getPortletGroupId(), title, version);
			}

			renderRequest.setAttribute(KnowledgeBaseKeys.ARTICLE, article);

		}
		catch (Exception e) {
			throw new PortletException(e);
		}

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
		}
		catch (Exception e) {
		}
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

	protected void deleteAttachment(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");
		String attachment = ParamUtil.getString(actionRequest, "fileName");

		KBArticleServiceUtil.deleteArticleAttachment(
			resourcePrimKey, attachment);
	}

	protected void deleteArticle(ActionRequest actionRequest) throws Exception {

		long parentResourcePrimKey = ParamUtil.getLong(
			actionRequest, "parentResourcePrimKey");

		KBArticleServiceUtil.deleteArticle(parentResourcePrimKey);
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

	protected void subscribe(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long groupId = themeDisplay.getPortletGroupId();

		KBArticleServiceUtil.subscribe(groupId);
	}

	protected void unsubscribe(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long groupId = themeDisplay.getPortletGroupId();

		KBArticleServiceUtil.unsubscribe(groupId);
	}

	protected void subscribeArticle(ActionRequest actionRequest)
		throws Exception {

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");

		KBArticleServiceUtil.subscribeArticle(resourcePrimKey);
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

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");
		String title = ParamUtil.getString(actionRequest, "title");
		double version = ParamUtil.getDouble(actionRequest, "version");

		String content = ParamUtil.getString(actionRequest, "content");
		String description = ParamUtil.getString(actionRequest, "description");
		boolean minorEdit = ParamUtil.getBoolean(actionRequest, "minorEdit");
		boolean template = ParamUtil.getBoolean(actionRequest, "template");
		long parentResourcePrimKey = ParamUtil.getLong(
			actionRequest, "parentResourcePrimKey");

		String[] tagsEntries = StringUtil.split(
			ParamUtil.getString(actionRequest, "tagsEntries"));

		if (resourcePrimKey <= 0) {
			return KBArticleServiceUtil.addArticle(
				themeDisplay.getPortletGroupId(), title, content, description,
				minorEdit, template, parentResourcePrimKey, tagsEntries, prefs,
				themeDisplay);
		}
		else {
			KBArticle article = KBArticleServiceUtil.getArticle(
				resourcePrimKey);

			if (article.isTemplate() && (template == false)) {
				return KBArticleServiceUtil.addArticle(
					themeDisplay.getPortletGroupId(), title, content,
					description, minorEdit, template, parentResourcePrimKey,
					tagsEntries, prefs, themeDisplay);
			}
			else {
				return KBArticleServiceUtil.updateArticle(
					resourcePrimKey, version, title, content, description,
					minorEdit, template, parentResourcePrimKey, tagsEntries,
					prefs, themeDisplay);
			}
		}

	}

	private static Log _log = LogFactory.getLog(KnowledgeBasePortlet.class);

}