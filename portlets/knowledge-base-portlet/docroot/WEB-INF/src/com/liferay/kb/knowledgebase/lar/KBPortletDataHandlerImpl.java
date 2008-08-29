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
import com.liferay.kb.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.kb.knowledgebase.service.persistence.KBArticleFinderUtil;
import com.liferay.kb.knowledgebase.service.persistence.KBArticleUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataException;
import com.liferay.portal.kernel.lar.PortletDataHandler;
import com.liferay.portal.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.portal.kernel.lar.PortletDataHandlerControl;
import com.liferay.portal.kernel.lar.PortletDataHandlerKeys;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.List;

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
			long defaultUserId = UserLocalServiceUtil.getDefaultUserId(
				context.getCompanyId());

			Document doc = SAXReaderUtil.createDocument();

			Element root = doc.addElement("kb-data");

			root.addAttribute("group-id", String.valueOf(context.getGroupId()));

			List<KBArticle> articles = 
				KBArticleFinderUtil.findByU_G_H_T_D(
					defaultUserId, context.getGroupId(), true, false, false);

			for (KBArticle article : articles) {
				exportArticle(context, root, article);
			}

			return doc.formattedString();
		}
		catch (Exception e) {
			throw new PortletDataException(e);
		}
	}

	public PortletDataHandlerControl[] getExportControls() {
		return new PortletDataHandlerControl[] {
			_articles, _comments, _ratings, _tags
		};
	}

	public PortletDataHandlerControl[] getImportControls() {
		return new PortletDataHandlerControl[] {
			_articles, _comments, _ratings, _tags
		};
	}

	public PortletPreferences importData(
			PortletDataContext context, String portletId,
			PortletPreferences prefs, String data)
		throws PortletDataException {

		try {
			Document doc = SAXReaderUtil.read(data);

			Element root = doc.getRootElement();

			List<Element> entryEls = root.elements("article");

			for (Element entryEl : entryEls) {
				String path = entryEl.attributeValue("path");

				if (context.isPathNotProcessed(path)) {
					KBArticle article = (KBArticle)context.getZipEntryAsObject(
						path);

					importArticle(context, article, prefs);
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

		Element entryEl = root.addElement("article");

		entryEl.addAttribute("path", path);

		if (context.isPathNotProcessed(path)) {
			if (context.getBooleanParameter(_NAMESPACE, "comments")) {
				context.addComments(KBArticle.class, article.getArticleId());
			}

			if (context.getBooleanParameter(_NAMESPACE, "ratings")) {
				context.addRatingsEntries(
					KBArticle.class, article.getArticleId());
			}

			if (context.getBooleanParameter(_NAMESPACE, "tags")) {
				context.addTagsEntries(KBArticle.class, article.getArticleId());
			}

			article.setUserUuid(article.getUserUuid());

			context.addZipEntry(path, article);
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

	protected void importArticle(
			PortletDataContext context, KBArticle article,
			PortletPreferences prefs)
		throws Exception {

		long userId = context.getUserId(article.getUserUuid());
		long groupId = context.getGroupId();

		String[] tagsEntries = null;

		if (context.getBooleanParameter(_NAMESPACE, "tags")) {
			tagsEntries = context.getTagsEntries(
				KBArticle.class, article.getArticleId());
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
					article.getVersion(), article.getContent(),
					article.getDescription(), article.isMinorEdit(), 
					article.isHead(), article.isTemplate(), article.isDraft(),
					tagsEntries, null, themeDisplay);
			}
			else {
				existingArticle = KBArticleLocalServiceUtil.updateArticle(
					userId, existingArticle.getResourcePrimKey(), 
					article.getVersion(), article.getTitle(),
					article.getContent(), article.getDescription(), 
					article.isMinorEdit(), article.isTemplate(),
					article.isDraft(), tagsEntries, null, themeDisplay);
			}
		}
		else {
			existingArticle = KBArticleLocalServiceUtil.addArticle(
				userId, groupId, article.getTitle(), article.getContent(),
				article.getDescription(), article.isMinorEdit(),
				article.isTemplate(), article.isDraft(), tagsEntries, null,
				themeDisplay);
		}

		if (context.getBooleanParameter(_NAMESPACE, "comments")) {
			context.importComments(
				KBArticle.class, article.getArticleId(),
				existingArticle.getArticleId(), context.getGroupId());
		}

		if (context.getBooleanParameter(_NAMESPACE, "ratings")) {
			context.importRatingsEntries(
				KBArticle.class, existingArticle.getArticleId(),
				existingArticle.getArticleId());
		}
	}

	private static final String _NAMESPACE = "kb";

	private static final PortletDataHandlerBoolean _articles =
		new PortletDataHandlerBoolean(_NAMESPACE, "articles", true, true);

	private static final PortletDataHandlerBoolean _comments =
		new PortletDataHandlerBoolean(_NAMESPACE, "comments");

	private static final PortletDataHandlerBoolean _ratings =
		new PortletDataHandlerBoolean(_NAMESPACE, "ratings");

	private static final PortletDataHandlerBoolean _tags =
		new PortletDataHandlerBoolean(_NAMESPACE, "tags");

}