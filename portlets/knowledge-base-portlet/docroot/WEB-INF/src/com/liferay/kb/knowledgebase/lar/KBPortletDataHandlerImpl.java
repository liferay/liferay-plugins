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

package com.liferay.kb.knowledgebase.lar;

import com.liferay.kb.knowledgebase.KnowledgeBaseKeys;
import com.liferay.kb.knowledgebase.model.KBArticle;
import com.liferay.kb.knowledgebase.model.KBFeedbackEntry;
import com.liferay.kb.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.kb.knowledgebase.service.KBFeedbackEntryLocalServiceUtil;
import com.liferay.kb.knowledgebase.service.persistence.KBArticleUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.lar.PortletDataContext;
import com.liferay.portal.lar.PortletDataException;
import com.liferay.portal.lar.PortletDataHandler;
import com.liferay.portal.lar.PortletDataHandlerBoolean;
import com.liferay.portal.lar.PortletDataHandlerControl;
import com.liferay.portal.lar.PortletDataHandlerKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.MapUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * <a href="KBPortletDataHandlerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 *
 */
public class KBPortletDataHandlerImpl implements PortletDataHandler {

	public PortletPreferences deleteData(
			PortletDataContext context, String portletId,
			PortletPreferences prefs)
		throws PortletDataException {

		try {
			if (!context.addPrimaryKey(
				KBPortletDataHandlerImpl.class, "deleteData")) {

				List<KBArticle> articles =
					KBArticleLocalServiceUtil.getGroupArticles(
						context.getGroupId(), true, false, false);

				for (KBArticle article : articles) {
					KBFeedbackEntryLocalServiceUtil.deleteFeedbackEntries(
						article.getResourcePrimKey());
				}

				KBArticleLocalServiceUtil.deleteGroupArticles(
					context.getGroupId(), false);
			}

			return null;
		}
		catch (Exception e) {
			throw new PortletDataException(e);
		}
	}

	public String exportData(
			PortletDataContext context, String portletId,
			PortletPreferences prefs)
		throws PortletDataException {

		try {
			Document doc = SAXReaderUtil.createDocument();

			Element root = doc.addElement("kb-data");

			root.addAttribute("group-id", String.valueOf(context.getGroupId()));

			List<KBArticle> articles =
				KBArticleLocalServiceUtil.getGroupArticles(
					context.getGroupId(), true, false, false);

			List<KBFeedbackEntry> feedbackEntries =
				new ArrayList<KBFeedbackEntry>();

			if (context.getBooleanParameter(_NAMESPACE, "feedbacks")) {
				for (KBArticle article : articles) {
					feedbackEntries.addAll(
						KBFeedbackEntryLocalServiceUtil.
							getArticleFeedbackEntries(
								article.getResourcePrimKey()));
				}
			}

			for (KBArticle article : articles) {
				exportArticle(context, root, article);
			}

			for (KBFeedbackEntry entry : feedbackEntries) {
				exportEntry(context, root, entry);
			}

			return doc.formattedString();
		}
		catch (Exception e) {
			throw new PortletDataException(e);
		}
	}

	public PortletDataHandlerControl[] getExportControls() {
		return new PortletDataHandlerControl[] {
			_articles, _categories, _feedbacks, _tags
		};
	}

	public PortletDataHandlerControl[] getImportControls() {
		return new PortletDataHandlerControl[] {
			_articles, _categories, _feedbacks, _tags
		};
	}

	public PortletPreferences importData(
			PortletDataContext context, String portletId,
			PortletPreferences prefs, String data)
		throws PortletDataException {

		try {
			Document doc = SAXReaderUtil.read(data);

			Element root = doc.getRootElement();

			List<Element> articleEls = root.elements("articles");

			Map<Long, Long> articlePKs =
				(Map<Long, Long>)context.getNewPrimaryKeysMap(KBArticle.class);

			for (Element articleEl : articleEls) {
				String path = articleEl.attributeValue("path");

				if (context.isPathNotProcessed(path)) {
					KBArticle article = (KBArticle)context.getZipEntryAsObject(
						path);

					importArticle(context, articlePKs, article, prefs);
				}
			}

			if (context.getBooleanParameter(_NAMESPACE, "feedbacks")) {
				List<Element> entriesEls = root.elements("entries");

				for (Element entryEl : entriesEls) {
					String path = entryEl.attributeValue("path");

					if (context.isPathNotProcessed(path)) {
						KBFeedbackEntry entry =
							(KBFeedbackEntry)context.getZipEntryAsObject(path);

						importEntry(context, articlePKs, entry);
					}
				}
			}

			return null;
		}
		catch (Exception e) {
			throw new PortletDataException(e);
		}
	}

	public boolean isPublishToLiveByDefault() {
		return false;
	}

	protected void exportArticle(
			PortletDataContext context, Element root, KBArticle article)
		throws PortalException, SystemException {

		if (!context.isWithinDateRange(article.getModifiedDate())) {
			return;
		}

		String path = getArticlePath(context, article);

		Element entryEl = root.addElement("articles");

		entryEl.addAttribute("path", path);

		if (context.isPathNotProcessed(path)) {
			article.setUserUuid(article.getUserUuid());

			if (context.getBooleanParameter(_NAMESPACE, "categories")) {
				context.addTagsCategories(
					KBArticle.class, article.getResourcePrimKey());
			}
			if (context.getBooleanParameter(_NAMESPACE, "tags")) {
				context.addTagsEntries(
					KBArticle.class, article.getResourcePrimKey());
			}

			context.addZipEntry(path, article);
		}
	}

	protected void exportEntry(
			PortletDataContext context, Element root, KBFeedbackEntry entry)
		throws PortalException, SystemException {

		if (!context.isWithinDateRange(entry.getModifiedDate())) {
			return;
		}

		String path = getEntryPath(context, entry);

		Element entryEl = root.addElement("entries");

		entryEl.addAttribute("path", path);

		if (context.isPathNotProcessed(path)) {
			entry.setUserUuid(entry.getUserUuid());

			context.addZipEntry(path, entry);
		}
	}

	protected String getArticlePath(
		PortletDataContext context, KBArticle article) {

		StringBuilder sb = new StringBuilder();

		sb.append(context.getPortletPath(KnowledgeBaseKeys.PORTLET_ID));
		sb.append("/articles/");
		sb.append(article.getArticleId());
		sb.append(".xml");

		return sb.toString();
	}

	protected String getEntryPath(
		PortletDataContext context, KBFeedbackEntry entry) {

		StringBuilder sb = new StringBuilder();

		sb.append(context.getPortletPath(KnowledgeBaseKeys.PORTLET_ID));
		sb.append("/entries/");
		sb.append(entry.getPrimaryKey());
		sb.append(".xml");

		return sb.toString();
	}

	protected void importArticle(
			PortletDataContext context, Map<Long, Long> articlePKs,
			KBArticle article, PortletPreferences prefs)
		throws Exception {

		long userId = context.getUserId(article.getUserUuid());
		long groupId = context.getGroupId();

		String[] tagsCategories = null;
		String[] tagsEntries = null;

		if (context.getBooleanParameter(_NAMESPACE, "categories")) {
			tagsCategories = context.getTagsCategories(
				KBArticle.class, article.getResourcePrimKey());
		}

		if (context.getBooleanParameter(_NAMESPACE, "tags")) {
			tagsEntries = context.getTagsEntries(
				KBArticle.class, article.getResourcePrimKey());
		}

		ThemeDisplay themeDisplay = null;

		KBArticle existingArticle = null;

		if (context.getDataStrategy().equals(
				PortletDataHandlerKeys.DATA_STRATEGY_MIRROR)) {

			existingArticle = KBArticleUtil.fetchByUUID_G(
				article.getUuid(), context.getGroupId());

			if (existingArticle == null) {
				existingArticle = KBArticleLocalServiceUtil.addArticle(
					article.getUuid(), userId, groupId, article.getTitle(),
					article.getHtmlTitle(), article.getVersion(),
					article.getContent(), article.getDescription(),
					article.isMinorEdit(), article.isHead(),
					article.isTemplate(), article.isDraft(),
					article.getParentResourcePrimKey(), tagsEntries,
					tagsCategories, prefs, themeDisplay);
			}
			else {
				existingArticle = KBArticleLocalServiceUtil.updateArticle(
					userId, existingArticle.getResourcePrimKey(),
					article.getVersion(), article.getTitle(),
					article.getHtmlTitle(), article.getContent(),
					article.getDescription(), article.isMinorEdit(),
					article.isTemplate(), article.isDraft(),
					article.getParentResourcePrimKey(), tagsEntries,
					tagsCategories, prefs, themeDisplay);
			}
		}
		else {
			existingArticle = KBArticleLocalServiceUtil.addArticle(
				userId, groupId, article.getTitle(), article.getContent(),
				article.getDescription(), article.isMinorEdit(),
				article.isTemplate(), article.isDraft(),
				article.getParentResourcePrimKey(), tagsEntries,
				tagsCategories, prefs, themeDisplay);
		}

		articlePKs.put(
			article.getResourcePrimKey(), existingArticle.getResourcePrimKey());
	}

	protected void importEntry(
			PortletDataContext context, Map<Long, Long> articlePKs,
			KBFeedbackEntry entry)
		throws Exception {

		long userId = context.getUserId(entry.getUserUuid());

		long articleResourcePrimKey = MapUtil.getLong(
			articlePKs, entry.getArticleResourcePrimKey(),
			entry.getArticleResourcePrimKey());

		KBFeedbackEntryLocalServiceUtil.addFeedbackEntry(
			articleResourcePrimKey, userId, entry.getScore(), entry.getVote(),
			entry.getComments());
	}

	private static final String _NAMESPACE = "kb";

	private static final PortletDataHandlerBoolean _articles =
		new PortletDataHandlerBoolean(_NAMESPACE, "articles");

	private static final PortletDataHandlerBoolean _categories =
		new PortletDataHandlerBoolean(_NAMESPACE, "categories");

	private static final PortletDataHandlerBoolean _feedbacks =
		new PortletDataHandlerBoolean(_NAMESPACE, "feedbacks", false);

	private static final PortletDataHandlerBoolean _tags =
		new PortletDataHandlerBoolean(_NAMESPACE, "tags");

}