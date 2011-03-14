/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
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
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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

	public Hits search(SearchContext searchContext) throws SearchException {
		Hits hits = super.search(searchContext);

		String[] queryTerms = hits.getQueryTerms();

		String keywords = searchContext.getKeywords();

		queryTerms = ArrayUtil.append(
			queryTerms, KnowledgeBaseUtil.splitKeywords(keywords));

		hits.setQueryTerms(queryTerms);

		return hits;
	}

	protected void addPortletPreferences(
			BooleanQuery contextQuery, HashMap<String, Object> params)
		throws Exception {

		String selectionMethod = (String)params.get("selectionMethod");
		Long[] resourcePrimKeys = (Long[])params.get("resourcePrimKeys");
		Boolean assetEntryQueryAndOperator = (Boolean)params.get(
			"assetEntryQueryAndOperator");
		String assetEntryQueryName = (String)params.get("assetEntryQueryName");
		Long[] assetCategoryIds = (Long[])params.get("assetCategoryIds");
		String[] assetTagNames = (String[])params.get("assetTagNames");

		if (selectionMethod.equals("articles")) {
			BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create();

			for (long resourcePrimKey : resourcePrimKeys) {
				String value = String.valueOf(resourcePrimKey);

				booleanQuery.addExactTerm(Field.ENTRY_CLASS_PK, value);
			}

			contextQuery.add(booleanQuery, BooleanClauseOccur.MUST);
		}
		else if (selectionMethod.equals("filter")) {
			List<Tuple> tuples = new ArrayList<Tuple>();

			if (assetEntryQueryName.equals("asset-categories")) {
				for (long assetCategoryId : assetCategoryIds) {
					String value = String.valueOf(assetCategoryId);

					tuples.add(new Tuple(Field.ASSET_CATEGORY_IDS, value));
				}
			}
			else if (assetEntryQueryName.equals("asset-tags")) {
				for (String assetTagName : assetTagNames) {
					tuples.add(new Tuple(Field.ASSET_TAG_NAMES, assetTagName));
				}
			}

			BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create();

			for (Tuple tuple : tuples) {
				String field = (String)tuple.getObject(0);
				String value = (String)tuple.getObject(1);

				if (assetEntryQueryAndOperator) {
					TermQuery termQuery = TermQueryFactoryUtil.create(
						field, value);

					booleanQuery.add(termQuery, BooleanClauseOccur.MUST);
				}
				else {
					booleanQuery.addExactTerm(field, value);
				}
			}

			contextQuery.add(booleanQuery, BooleanClauseOccur.MUST);
		}
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
		long rootResourcePrimKey = article.getRootResourcePrimKey();
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
		document.addKeyword(Field.ROOT_ENTRY_CLASS_PK, rootResourcePrimKey);

		return document;
	}

	protected Summary doGetSummary(
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

	protected void doReindex(Object obj) throws Exception {
		Article article = (Article)obj;

		SearchEngineUtil.updateDocument(
			article.getCompanyId(), getDocument(article));
	}

	protected void doReindex(String className, long classPK) throws Exception {
		Article article = ArticleLocalServiceUtil.getLatestArticle(
			classPK, WorkflowConstants.STATUS_APPROVED);

		reindexArticles(article);
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

		HashMap<String, Object> params =
			(HashMap<String, Object>)searchContext.getAttribute("params");

		if (params != null) {
			addPortletPreferences(contextQuery, params);
		}
	}

	protected void postProcessSearchQuery(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		String[] values = KnowledgeBaseUtil.splitKeywords(
			searchContext.getKeywords());

		for (String value : values) {
			searchQuery.addTerm(Field.TITLE, value, true);
			searchQuery.addTerm(Field.CONTENT, value, true);
		}
	}

	protected void reindexArticles(Article article) throws Exception {

		// See ArticlePermission#contains

		List<Article> articles = ArticleLocalServiceUtil.getArticles(
			article.getResourcePrimKey(), WorkflowConstants.STATUS_APPROVED,
			null);

 		Collection<Document> documents = new ArrayList<Document>();

		for (Article curArticle : articles) {
			documents.add(getDocument(curArticle));
		}

		SearchEngineUtil.updateDocuments(article.getCompanyId(), documents);
	}

	protected void reindexArticles(long companyId) throws Exception {
		int count = ArticleLocalServiceUtil.getCompanyArticlesCount(
			companyId, WorkflowConstants.STATUS_APPROVED);

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
			companyId, WorkflowConstants.STATUS_APPROVED, start, end,
			new ArticleModifiedDateComparator());

		Collection<Document> documents = new ArrayList<Document>();

		for (Article article : articles) {
			Document document = getDocument(article);

			documents.add(document);
		}

		SearchEngineUtil.updateDocuments(companyId, documents);
	}

}