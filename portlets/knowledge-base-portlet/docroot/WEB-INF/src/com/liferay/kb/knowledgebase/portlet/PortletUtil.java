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

package com.liferay.kb.knowledgebase.portlet;

import com.liferay.kb.knowledgebase.KnowledgeBaseKeys;
import com.liferay.kb.knowledgebase.NoSuchFeedbackEntryException;
import com.liferay.kb.knowledgebase.model.KBArticle;
import com.liferay.kb.knowledgebase.model.KBFeedbackEntry;
import com.liferay.kb.knowledgebase.model.KBFeedbackStats;
import com.liferay.kb.knowledgebase.service.KBArticleServiceUtil;
import com.liferay.kb.knowledgebase.service.KBFeedbackEntryLocalServiceUtil;
import com.liferay.kb.knowledgebase.service.KBFeedbackStatsLocalServiceUtil;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import javax.portlet.RenderRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * <a href="PortletUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 *
 */
public class PortletUtil {

	public static void getKBArticle(RenderRequest renderRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			renderRequest);

		getKBArticle(request);
	}

	public static void getKBArticle(HttpServletRequest request)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourcePrimKey = ParamUtil.getLong(
			request, "resourcePrimKey");
		double version = ParamUtil.getDouble(request, "version");
		String title = ParamUtil.getString(request, "title");

		KBArticle article = null;

		if (resourcePrimKey > 0) {
			article = KBArticleServiceUtil.getArticle(
				resourcePrimKey, version);
		}
		else if (Validator.isNotNull(title)) {
			article = KBArticleServiceUtil.getArticle(
				themeDisplay.getPortletGroupId(), title, version);
		}

		request.setAttribute(KnowledgeBaseKeys.ARTICLE, article);
	}

	public static void getKBFeedback(RenderRequest renderRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			renderRequest);

		getKBFeedback(request);
	}

	public static void getKBFeedback(HttpServletRequest request)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		KBArticle article = (KBArticle) request.getAttribute(
			KnowledgeBaseKeys.ARTICLE);

		long userId = themeDisplay.getUserId();

		KBFeedbackEntry feedbackEntry = null;
		KBFeedbackStats feedbackStats = null;

		if (article != null) {
			try {
				feedbackEntry =
					KBFeedbackEntryLocalServiceUtil.getFeedbackEntry(
						article.getResourcePrimKey(), userId);
			}
			catch (NoSuchFeedbackEntryException nsfee) {
				feedbackEntry = null;
			}

			feedbackStats =
				KBFeedbackStatsLocalServiceUtil.getArticleFeedbackStats(
					article.getResourcePrimKey());
		}

		request.setAttribute(
			KnowledgeBaseKeys.KNOWLEDGE_BASE_FEEDBACK_ENTRY, feedbackEntry);
		request.setAttribute(
			KnowledgeBaseKeys.KNOWLEDGE_BASE_FEEDBACK_STATS, feedbackStats);
	}

	public static void getKBBeans(RenderRequest renderRequest)
		throws Exception {

		getKBArticle(renderRequest);

		getKBFeedback(renderRequest);
	}

}