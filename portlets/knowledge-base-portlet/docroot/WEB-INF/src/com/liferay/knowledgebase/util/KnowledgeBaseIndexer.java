/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.util;

import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.knowledgebase.util.comparator.ArticleCreateDateComparator;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.BaseIndexer;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeIndexerUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletURL;

/**
 * <a href="KnowledgeBaseIndexer.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class KnowledgeBaseIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = {Article.class.getName()};

	public static final String PORTLET_ID = "1_WAR_knowledgebaseportlet";

	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	public Summary getSummary(
		Document document, String snippet, PortletURL portletURL) {

		String title = document.get(Field.TITLE);

		String content = snippet;

		if (Validator.isNull(snippet)) {
			content = StringUtil.shorten(document.get(Field.CONTENT), 200);
		}

		String resourcePrimKey = document.get(Field.ENTRY_CLASS_PK);

		portletURL.setParameter("jspPage", "/admin/view_article.jsp");
		portletURL.setParameter("resourcePrimKey", resourcePrimKey);

		return new Summary(title, content, portletURL);
	}

	public Hits search(SearchContext searchContext) throws SearchException {
		Hits hits = super.search(searchContext);

		// See KnowledgeBaseIndexer#postProcessSearchQuery.

		hits.setQueryTerms(ArrayUtil.append(
			hits.getQueryTerms(), _splitKeywords(searchContext.getKeywords())));

		return hits;
	}

	protected void doDelete(Object obj) throws Exception {
		Article article = (Article)obj;

		Document document = new DocumentImpl();

		document.addUID(PORTLET_ID, article.getResourcePrimKey());

		SearchEngineUtil.deleteDocument(
			article.getCompanyId(), document.get(Field.UID));
	}

	protected Document doGetDocument(Object obj) throws Exception {
		Article article = (Article)obj;

		long companyId = article.getCompanyId();
		long groupId = getParentGroupId(article.getGroupId());
		long scopeGroupId = article.getGroupId();
		long userId = article.getUserId();
		String userName = PortalUtil.getUserName(userId, article.getUserName());
		long resourcePrimKey = article.getResourcePrimKey();
		String title = article.getTitle();
		String content = HtmlUtil.extractText(article.getContent());
		String description = article.getDescription();
		Date modifiedDate = article.getModifiedDate();

		ExpandoBridge expandoBridge = article.getExpandoBridge();

		Document document = new DocumentImpl();

		document.addUID(PORTLET_ID, resourcePrimKey);

		document.addModifiedDate(modifiedDate);

		document.addKeyword(Field.COMPANY_ID, companyId);
		document.addKeyword(Field.PORTLET_ID, PORTLET_ID);
		document.addKeyword(Field.GROUP_ID, groupId);
		document.addKeyword(Field.SCOPE_GROUP_ID, scopeGroupId);
		document.addKeyword(Field.USER_ID, userId);
		document.addText(Field.USER_NAME, userName);

		document.addText(Field.TITLE, title);
		document.addText(Field.CONTENT, content);
		document.addText(Field.DESCRIPTION, description);

		document.addKeyword(Field.ENTRY_CLASS_NAME, Article.class.getName());
		document.addKeyword(Field.ENTRY_CLASS_PK, resourcePrimKey);

		ExpandoBridgeIndexerUtil.addAttributes(document, expandoBridge);

		return document;
	}

	protected void doReindex(Object obj) throws Exception {
		Article article = (Article)obj;

		SearchEngineUtil.updateDocument(
			article.getCompanyId(), getDocument(article));
	}

	protected void doReindex(String className, long classPK) throws Exception {
		Article article = ArticleLocalServiceUtil.getArticle(classPK);

		doReindex(article);
	}

	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		int count = ArticleLocalServiceUtil.getCompanyArticlesCount(companyId);
		int pages = count / Indexer.DEFAULT_INTERVAL;

		for (int i = 0; i <= pages; i++) {
			int start = (i * Indexer.DEFAULT_INTERVAL);
			int end = start + Indexer.DEFAULT_INTERVAL;

			List<Article> articles = ArticleLocalServiceUtil.getCompanyArticles(
				companyId, start, end, new ArticleCreateDateComparator());

			Collection<Document> documents = new ArrayList<Document>();

			for (Article article : articles) {
				documents.add(getDocument(article));
			}

			SearchEngineUtil.updateDocuments(companyId, documents);
		}
	}

	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
	}

	protected void postProcessSearchQuery(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		// Add additional query terms to return partial matches.

		for (String value : _splitKeywords(searchContext.getKeywords())) {
			searchQuery.addTerm(Field.TITLE, value, true);
			searchQuery.addTerm(Field.CONTENT, value, true);
		}
	}

	private String[] _splitKeywords(String keywords) {
		String s = keywords.trim();

		if (Validator.isNull(s)) {
			return new String[0];
		}

		List<String> list = new ArrayList<String>();

		StringBuilder sb = new StringBuilder();

		for (char c : s.toCharArray()) {
			if (Character.isWhitespace(c)) {
				if (sb.length() > 0) {
					list.add(sb.toString());

					sb = new StringBuilder();
				}
			}
			else if (Character.isLetterOrDigit(c)) {
				sb.append(c);
			}
			else {
				return new String[0];
			}
		}

		if (sb.length() > 0) {
			list.add(sb.toString());
		}

		return list.toArray(new String[list.size()]);
	}

}