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

import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.DocumentSummary;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.PortletURL;

import org.apache.lucene.search.BooleanQuery;

/**
 * <a href="Indexer.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 */
public class Indexer {
	public static final String PORTLET_ID = "1";

	public static void addArticle(
		long companyId, long groupId, String title,
		String content, String description, String[] tagsEntries)
		throws SearchException {

		try {
			deleteArticle(companyId, groupId, title);
		}
		catch (SearchException se) {
		}

		Document doc = getArticleDocument(
			companyId, groupId, title, content, description, tagsEntries);

		SearchEngineUtil.addDocument(companyId, doc);
	}

	public static void deleteArticle(long companyId, long groupId, String title)
		throws SearchException {

		SearchEngineUtil.deleteDocument(
			companyId, getArticleUID(groupId, title));
	}

	public static void deleteArticles(long companyId, long groupId)
		throws SearchException {

		BooleanQuery booleanQuery = new BooleanQuery();

		LuceneUtil.addRequiredTerm(booleanQuery, Field.PORTLET_ID, PORTLET_ID);

		LuceneUtil.addRequiredTerm(booleanQuery, "groupId", groupId);

		Hits hits = SearchEngineUtil.search(
			companyId, booleanQuery.toString(), SearchEngineUtil.ALL_POS,
			SearchEngineUtil.ALL_POS);

		for (int i = 0; i < hits.getLength(); i++) {
			Document doc = hits.doc(i);

			SearchEngineUtil.deleteDocument(companyId, doc.get(Field.UID));
		}
	}

	public static Document getArticleDocument(
		long companyId, long groupId, String title,
		String content, String description, String[] tagsEntries) {

		content = HtmlUtil.extractText(content);

		Document doc = new DocumentImpl();

		doc.addUID(PORTLET_ID, groupId, title);

		doc.addKeyword(Field.COMPANY_ID, companyId);
		doc.addKeyword(Field.PORTLET_ID, PORTLET_ID);
		doc.addKeyword(Field.GROUP_ID, groupId);

		doc.addText(Field.TITLE, title);
		doc.addText(Field.CONTENT, content);
		doc.addText(Field.DESCRIPTION, description);

		doc.addModifiedDate();

		doc.addKeyword(Field.ENTRY_CLASS_PK, groupId);

		doc.addKeyword(Field.TAGS_ENTRIES, tagsEntries);

		return doc;
	}

	public static String getArticleUID(long groupId, String title) {
		Document doc = new DocumentImpl();

		doc.addUID(PORTLET_ID, groupId, title);

		return doc.get(Field.UID);
	}

	public static void updateArticle(
		long companyId, long groupId, String title, String content,
		String description, String[] tagsEntries)
		throws SearchException {

		Document doc = getArticleDocument(
			companyId, groupId, title, content, description, tagsEntries);

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

		String groupId = doc.get(Field.ENTRY_CLASS_PK);

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