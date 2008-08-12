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

package com.liferay.knowledgebase.util;

import com.liferay.knowledgebase.KnowledgeBaseKeys;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.DocumentSummary;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.PortletURL;

/**
 * <a href="Indexer.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 */
public class Indexer {
	public static final String PORTLET_ID = KnowledgeBaseKeys.PORTLET_ID;

	public static void addArticle(
		long companyId, long groupId, long userId, long resourcePrimKey,
		String title, String content, String description, String[] tagsEntries)
		throws SearchException {

		try {
			deleteArticle(companyId, resourcePrimKey);
		}
		catch (SearchException se) {
		}

		Document doc = getArticleDocument(
			companyId, groupId, userId, resourcePrimKey, title, content,
			description, tagsEntries);

		SearchEngineUtil.addDocument(companyId, doc);
	}

	public static void deleteArticle(long companyId, long resourcePrimKey)
		throws SearchException {

		SearchEngineUtil.deleteDocument(
			companyId, getArticleUID(resourcePrimKey));
	}

	public static void deleteArticles(long companyId, long groupId)
		throws SearchException {

		BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create();

		booleanQuery.addRequiredTerm(Field.PORTLET_ID, PORTLET_ID);

		booleanQuery.addRequiredTerm(Field.GROUP_ID, groupId);

		Hits hits = SearchEngineUtil.search(
			companyId, booleanQuery, SearchEngineUtil.ALL_POS,
			SearchEngineUtil.ALL_POS);

		for (int i = 0; i < hits.getLength(); i++) {
			Document doc = hits.doc(i);

			SearchEngineUtil.deleteDocument(companyId, doc.get(Field.UID));
		}
	}

	public static Document getArticleDocument(
		long companyId, long groupId, long userId, long resourcePrimKey,
		String title, String content, String description,
		String[] tagsEntries) {

		content = HtmlUtil.extractText(content);

		Document doc = new DocumentImpl();

		doc.addUID(PORTLET_ID, resourcePrimKey);

		doc.addKeyword(Field.COMPANY_ID, companyId);
		doc.addKeyword(Field.PORTLET_ID, PORTLET_ID);
		doc.addKeyword(Field.GROUP_ID, groupId);
		doc.addKeyword(Field.USER_ID, userId);

		doc.addText(Field.TITLE, title);
		doc.addText(Field.CONTENT, content);
		doc.addText(Field.DESCRIPTION, description);

		doc.addModifiedDate();

		doc.addKeyword(Field.ENTRY_CLASS_NAME, KBArticle.class.getName());
		doc.addKeyword(Field.ENTRY_CLASS_PK, resourcePrimKey);

		doc.addKeyword(Field.TAGS_ENTRIES, tagsEntries);

		return doc;
	}

	public static String getArticleUID(long resourcePrimKey) {
		Document doc = new DocumentImpl();

		doc.addUID(PORTLET_ID, resourcePrimKey);

		return doc.get(Field.UID);
	}

	public static void updateArticle(
		long companyId, long groupId, long userId, long resourcePrimKey,
		String title, String content, String description, String[] tagsEntries)
		throws SearchException {

		Document doc = getArticleDocument(
			companyId, groupId, userId, resourcePrimKey, title, content,
			description, tagsEntries);

		SearchEngineUtil.updateDocument(companyId, doc.get(Field.UID), doc);
	}

	public DocumentSummary getDocumentSummary(
		com.liferay.portal.kernel.search.Document doc, PortletURL portletURL) {

		// Title

		String title = doc.get(Field.TITLE);

		// Content

		String content = doc.get(Field.CONTENT);

		content = StringUtil.shorten(content, 200);

		// Portlet URL

		String groupId = doc.get(Field.GROUP_ID);

		portletURL.setParameter("view", "view_article");
		portletURL.setParameter("groupId", groupId);
		portletURL.setParameter("title", title);

		return new DocumentSummary(title, content, portletURL);
	}

	public void reIndex(String[] ids) throws SearchException {
		try {
			KBArticleLocalServiceUtil.reIndex(ids);
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

}