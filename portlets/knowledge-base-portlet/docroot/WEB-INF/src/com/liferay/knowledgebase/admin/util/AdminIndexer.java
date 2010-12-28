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

package com.liferay.knowledgebase.admin.util;

import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.knowledgebase.util.comparator.ArticleModifiedDateComparator;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.TermQueryFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetCategoryServiceUtil;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletURL;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AdminIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = {Article.class.getName()};

	public static final String PORTLET_ID = PortletKeys.KNOWLEDGE_BASE_ADMIN;

	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	public Summary getSummary(
		Document document, String snippet, PortletURL portletURL) {

		String title = document.get(Field.TITLE);

		String content = snippet;

		if (Validator.isNull(snippet)) {
			content = document.get(Field.DESCRIPTION);

			if (Validator.isNull(content)) {
				content = StringUtil.shorten(document.get(Field.CONTENT), 200);
			}
		}

		String resourcePrimKey = document.get(Field.ENTRY_CLASS_PK);

		portletURL.setParameter("jspPage", "/admin/view_article.jsp");
		portletURL.setParameter("resourcePrimKey", resourcePrimKey);

		return new Summary(title, content, portletURL);
	}

	public Hits search(SearchContext searchContext) throws SearchException {
		Hits hits = super.search(searchContext);

		String[] queryTerms = hits.getQueryTerms();

		queryTerms = ArrayUtil.append(
			queryTerms, _splitKeywords(searchContext.getKeywords()));

		hits.setQueryTerms(queryTerms);

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

		long[] assetCategoryIds = AssetCategoryLocalServiceUtil.getCategoryIds(
			Article.class.getName(), resourcePrimKey);
		String[] assetCategoryNames =
			AssetCategoryLocalServiceUtil.getCategoryNames(
				Article.class.getName(), resourcePrimKey);
		String[] assetTagNames = AssetTagLocalServiceUtil.getTagNames(
			Article.class.getName(), resourcePrimKey);

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
		document.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);
		document.addKeyword(Field.ASSET_CATEGORY_NAMES, assetCategoryNames);
		document.addKeyword(Field.ASSET_TAG_NAMES, assetTagNames);

		document.addKeyword(Field.ENTRY_CLASS_NAME, Article.class.getName());
		document.addKeyword(Field.ENTRY_CLASS_PK, resourcePrimKey);

		return document;
	}

	protected void doReindex(Object obj) throws Exception {
		Article article = (Article)obj;

		SearchEngineUtil.updateDocument(
			article.getCompanyId(), getDocument(article));
	}

	protected void doReindex(String className, long classPK) throws Exception {
		Article article = ArticleLocalServiceUtil.getLatestArticle(
			classPK, WorkflowConstants.STATUS_APPROVED);

		doReindex(article);
	}

	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexArticles(companyId);
	}

	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
	}

	protected void postProcessContextQuery(
			BooleanQuery contextQuery, SearchContext searchContext)
		throws Exception {

		_addPortletPreferences(contextQuery, searchContext);
	}

	protected void postProcessSearchQuery(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		for (String value : _splitKeywords(searchContext.getKeywords())) {
			searchQuery.addTerm(Field.TITLE, value, true);
			searchQuery.addTerm(Field.CONTENT, value, true);
		}
	}

	protected void reindexArticles(long companyId) throws Exception {
		int count = ArticleLocalServiceUtil.getCompanyArticlesCount(
			companyId, WorkflowConstants.STATUS_APPROVED, false);

		int pages = count / Indexer.DEFAULT_INTERVAL;

		for (int i = 0; i <= pages; i++) {
			int start = (i * Indexer.DEFAULT_INTERVAL);
			int end = start + Indexer.DEFAULT_INTERVAL;

			reindexArticles(companyId, start, end);
		}
	}

	protected void reindexArticles(long companyId, int start, int end)
		throws Exception {

		List<Article> articles = ArticleLocalServiceUtil.getCompanyArticles(
			companyId, WorkflowConstants.STATUS_APPROVED, false, start, end,
			new ArticleModifiedDateComparator());

		if (articles.isEmpty()) {
			return;
		}

		Collection<Document> documents = new ArrayList<Document>();

		for (Article article : articles) {
			Document document = getDocument(article);

			documents.add(document);
		}

		SearchEngineUtil.updateDocuments(companyId, documents);
	}

	private void _addPortletPreferences(
			BooleanQuery contextQuery, SearchContext searchContext)
		throws Exception {

		String selectionMethod = (String)searchContext.getAttribute(
			"selectionMethod");
		long[] resourcePrimKeys = (long[])searchContext.getAttribute(
			"resourcePrimKeys");

		if (Validator.isNull(selectionMethod)) {
			return;
		}

		if (selectionMethod.equals("articles")) {
			BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create();

			for (long resourcePrimKey : resourcePrimKeys) {
				booleanQuery.addExactTerm(
					Field.ENTRY_CLASS_PK, resourcePrimKey);
			}

			if (resourcePrimKeys.length == 0) {
				booleanQuery.addExactTerm(Field.ENTRY_CLASS_PK, new Long(0));
			}

			contextQuery.add(booleanQuery, BooleanClauseOccur.MUST);

			return;
		}

		Boolean assetEntryQueryContains = (Boolean)searchContext.getAttribute(
			"assetEntryQueryContains");
		Boolean assetEntryQueryAndOperator =
			(Boolean)searchContext.getAttribute("assetEntryQueryAndOperator");
		String assetEntryQueryName = (String)searchContext.getAttribute(
			"assetEntryQueryName");
		long[] assetCategoryIds = (long[])searchContext.getAttribute(
			"assetCategoryIds");
		String[] assetTagNames = (String[])searchContext.getAttribute(
			"assetTagNames");

		BooleanQuery booleanQuery = null;

		if ((assetEntryQueryName.equals("asset-categories")) &&
			(assetCategoryIds.length > 0)) {

			booleanQuery = BooleanQueryFactoryUtil.create();

			for (long assetCategoryId : assetCategoryIds) {
				if (searchContext.getUserId() > 0) {
					try {
						AssetCategoryServiceUtil.getCategory(assetCategoryId);
					}
					catch (Exception e) {
						continue;
					}
				}

				if (assetEntryQueryAndOperator) {
					TermQuery termQuery = TermQueryFactoryUtil.create(
						Field.ASSET_CATEGORY_IDS, assetCategoryId);

					booleanQuery.add(termQuery, BooleanClauseOccur.MUST);
				}
				else {
					booleanQuery.addExactTerm(
						Field.ASSET_CATEGORY_IDS, assetCategoryId);
				}
			}
		}

		if ((assetEntryQueryName.equals("asset-tags")) &&
			(assetTagNames.length > 0)) {

			booleanQuery = BooleanQueryFactoryUtil.create();

			for (String assetTagName : assetTagNames) {
				if (assetEntryQueryAndOperator) {
					TermQuery termQuery = TermQueryFactoryUtil.create(
						Field.ASSET_TAG_NAMES, assetTagName);

					booleanQuery.add(termQuery, BooleanClauseOccur.MUST);
				}
				else {
					booleanQuery.addExactTerm(
						Field.ASSET_TAG_NAMES, assetTagName);
				}
			}
		}

		if (booleanQuery == null) {
			return;
		}

		if (assetEntryQueryContains) {
			contextQuery.add(booleanQuery, BooleanClauseOccur.MUST);
		}
		else {
			contextQuery.add(booleanQuery, BooleanClauseOccur.MUST_NOT);
		}
	}

	private String[] _splitKeywords(String keywords) {
		keywords = keywords.trim();

		if (Validator.isNull(keywords)) {
			return new String[0];
		}

		List<String> keywordsList = new ArrayList<String>();

		StringBuilder sb = new StringBuilder();

		for (char c : keywords.toCharArray()) {
			if (Character.isWhitespace(c)) {
				if (sb.length() > 0) {
					keywordsList.add(sb.toString());

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
			keywordsList.add(sb.toString());
		}

		return keywordsList.toArray(new String[keywordsList.size()]);
	}

}