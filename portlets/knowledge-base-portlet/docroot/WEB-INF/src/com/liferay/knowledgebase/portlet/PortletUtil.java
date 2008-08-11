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
import com.liferay.knowledgebase.NoSuchFeedbackEntryException;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBFeedbackEntry;
import com.liferay.knowledgebase.model.KBFeedbackStats;
import com.liferay.knowledgebase.service.KBArticleServiceUtil;
import com.liferay.knowledgebase.service.KBFeedbackEntryLocalServiceUtil;
import com.liferay.knowledgebase.service.KBFeedbackStatsLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import javax.portlet.RenderRequest;

/**
 * <a href="PortletUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 *
 */
public class PortletUtil {

	public static void getKBArticle(RenderRequest renderRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourcePrimKey = ParamUtil.getLong(
			renderRequest, "resourcePrimKey");
		double version = ParamUtil.getDouble(renderRequest, "version");
		String title = ParamUtil.getString(renderRequest, "title");

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

	public static void getKBFeedback(RenderRequest renderRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		KBArticle article = (KBArticle) renderRequest.getAttribute(
			KnowledgeBaseKeys.ARTICLE);

		long userId = themeDisplay.getUserId();

		KBFeedbackEntry kbFeedbackEntry = null;
		KBFeedbackStats kbFeedbackStats = null;

		if (article != null) {
			try {
				kbFeedbackEntry =
					KBFeedbackEntryLocalServiceUtil.getKBFeedbackEntry(
						article.getArticleId(), userId);
			}
			catch (NoSuchFeedbackEntryException nsfee) {
				kbFeedbackEntry = null;
			}

			kbFeedbackStats =
				KBFeedbackStatsLocalServiceUtil.getArticleKBFeedbackStats(
					article.getArticleId());
		}

		renderRequest.setAttribute(
			KnowledgeBaseKeys.KNOWLEDGE_BASE_FEEDBACK_ENTRY, kbFeedbackEntry);
		renderRequest.setAttribute(
			KnowledgeBaseKeys.KNOWLEDGE_BASE_FEEDBACK_STATS, kbFeedbackStats);
	}

	public static void getKBBeans(RenderRequest renderRequest)
		throws Exception {

		// Store KB Article in the Request object

		getKBArticle(renderRequest);

		// Store KB Feedback in the Request object

		getKBFeedback(renderRequest);
	}

}