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

import com.liferay.knowledgebase.KnowledgeBaseKeys;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.service.KBArticleServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.jsp.JSPPortlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * <a href="KnowledgeBasePortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KnowledgeBasePortlet extends JSPPortlet {

	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		try {
			String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

			KBArticle article = null;

			if (cmd.equals(Constants.DELETE)) {
				deleteArticle(actionRequest);
			}
			else if (cmd.equals(Constants.UPDATE)) {
				article = updateArticle(actionRequest);
			}
			else if (cmd.equals(Constants.SUBSCRIBE)) {
				subscribe(actionRequest);
			}
			else if (cmd.equals(Constants.UNSUBSCRIBE)) {
				unsubscribe(actionRequest);
			}
			else if (cmd.equals(
				Constants.SUBSCRIBE + KnowledgeBaseKeys.ARTICLE)) {

				subscribeArticle(actionRequest);
			}
			else if (cmd.equals(
				Constants.UNSUBSCRIBE + KnowledgeBaseKeys.ARTICLE)) {

				unsubscribeArticle(actionRequest);
			}

			boolean preview = ParamUtil.getBoolean(actionRequest, "preview");

			if (preview) {
				actionResponse.setRenderParameters(
					actionRequest.getParameterMap());
			}
			else if (Validator.isNotNull(cmd)) {
				String redirect = ParamUtil.getString(
					actionRequest, "redirect");

				if (article != null) {
					String saveAndContinueRedirect = ParamUtil.getString(
						actionRequest, "saveAndContinueRedirect");

					if (Validator.isNotNull(saveAndContinueRedirect)) {
						redirect = saveAndContinueRedirect;
					}
					else if (redirect.endsWith("title=")) {
						redirect += article.getTitle();
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
			throw new PortletException(e);
		}
	}

	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException, IOException {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String title = ParamUtil.getString(renderRequest, "title");

		try {
			KBArticle article = null;

			if (Validator.isNotNull(title)) {
				article = KBArticleServiceUtil.getArticle(
					themeDisplay.getPortletGroupId(), title);
			}

			renderRequest.setAttribute(KnowledgeBaseKeys.ARTICLE, article);

		}
		catch (Exception e) {
			throw new PortletException(e);
		}

		super.render(renderRequest, renderResponse);
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

	protected void deleteArticle(ActionRequest actionRequest) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String title = ParamUtil.getString(actionRequest, "title");

		KBArticleServiceUtil.deleteArticle(
			themeDisplay.getPortletGroupId(), title);
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

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long groupId = themeDisplay.getPortletGroupId();
		String title = ParamUtil.getString(actionRequest, "title");

		KBArticleServiceUtil.subscribeArticle(groupId, title);
	}

	protected void unsubscribeArticle(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long groupId = themeDisplay.getPortletGroupId();
		String title = ParamUtil.getString(actionRequest, "title");

		KBArticleServiceUtil.unsubscribeArticle(groupId, title);
	}

	protected KBArticle updateArticle(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletPreferences prefs = actionRequest.getPreferences();

		String title = ParamUtil.getString(actionRequest, "title");
		double version = ParamUtil.getDouble(actionRequest, "version");

		String content = ParamUtil.getString(actionRequest, "content");
		String description = ParamUtil.getString(actionRequest, "description");
		String summary = ParamUtil.getString(actionRequest, "summary");
		boolean minorEdit = ParamUtil.getBoolean(actionRequest, "minorEdit");
		String parentTitle = ParamUtil.getString(actionRequest, "parentTitle");

		String[] tagsEntries = StringUtil.split(
			ParamUtil.getString(actionRequest, "tagsEntries"));

		return KBArticleServiceUtil.updateArticle(
			themeDisplay.getPortletGroupId(), title, version, content,
			description,  summary, minorEdit, parentTitle, tagsEntries, prefs,
			themeDisplay);
	}

}