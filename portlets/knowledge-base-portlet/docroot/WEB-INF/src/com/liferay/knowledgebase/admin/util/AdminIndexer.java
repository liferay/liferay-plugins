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

import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.knowledgebase.util.comparator.KBArticleModifiedDateComparator;
import com.liferay.portal.kernel.search.BaseIndexer;
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
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletURL;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AdminIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = {KBArticle.class.getName()};

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

	protected void doDelete(Object obj) throws Exception {
		KBArticle kbArticle = (KBArticle)obj;

		Document document = new DocumentImpl();

		document.addUID(PORTLET_ID, kbArticle.getResourcePrimKey());

		SearchEngineUtil.deleteDocument(
			kbArticle.getCompanyId(), document.get(Field.UID));
	}

	protected Document doGetDocument(Object obj) throws Exception {
		KBArticle kbArticle = (KBArticle)obj;

		long companyId = kbArticle.getCompanyId();
		long groupId = getParentGroupId(kbArticle.getGroupId());
		long scopeGroupId = kbArticle.getGroupId();
		long userId = kbArticle.getUserId();
		String userName = PortalUtil.getUserName(
			userId, kbArticle.getUserName());
		long resourcePrimKey = kbArticle.getResourcePrimKey();
		long rootResourcePrimKey = kbArticle.getRootResourcePrimKey();
		String title = kbArticle.getTitle();
		String content = HtmlUtil.extractText(kbArticle.getContent());
		String description = kbArticle.getDescription();
		Date createDate = kbArticle.getCreateDate();
		Date modifiedDate = kbArticle.getModifiedDate();
		long kbTemplateId = kbArticle.getKbTemplateId();

		long[] assetCategoryIds = AssetCategoryLocalServiceUtil.getCategoryIds(
			KBArticle.class.getName(), resourcePrimKey);
		String[] assetCategoryNames =
			AssetCategoryLocalServiceUtil.getCategoryNames(
				KBArticle.class.getName(), resourcePrimKey);
		String[] assetTagNames = AssetTagLocalServiceUtil.getTagNames(
			KBArticle.class.getName(), resourcePrimKey);

		Document document = new DocumentImpl();

		document.addUID(PORTLET_ID, resourcePrimKey);

		document.addDate("createDate", createDate);
		document.addDate(Field.MODIFIED, modifiedDate);

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

		document.addKeyword(Field.ENTRY_CLASS_NAME, KBArticle.class.getName());
		document.addKeyword(Field.ENTRY_CLASS_PK, resourcePrimKey);
		document.addKeyword(Field.ROOT_ENTRY_CLASS_PK, rootResourcePrimKey);

		document.addKeyword(Field.USER_NAME + "Keyword", userName, true);
		document.addKeyword(Field.TITLE + "Keyword", title, true);
		document.addKeyword("kbTemplateId", kbTemplateId);

		return document;
	}

	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletURL portletURL) {

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
		KBArticle kbArticle = (KBArticle)obj;

		SearchEngineUtil.updateDocument(
			kbArticle.getCompanyId(), getDocument(kbArticle));
	}

	protected void doReindex(String className, long classPK) throws Exception {
		KBArticle kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(
			classPK, WorkflowConstants.STATUS_APPROVED);

		reindexKBArticles(kbArticle);
	}

	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexKBArticles(companyId);
	}

	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
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

	protected void reindexKBArticles(KBArticle kbArticle) throws Exception {

		// See KBArticlePermission#contains

		List<KBArticle> kbArticles =
			KBArticleLocalServiceUtil.getKBArticleAndAllDescendants(
				kbArticle.getResourcePrimKey(),
				WorkflowConstants.STATUS_APPROVED, null);

 		Collection<Document> documents = new ArrayList<Document>();

		for (KBArticle curKBArticle : kbArticles) {
			documents.add(getDocument(curKBArticle));
		}

		SearchEngineUtil.updateDocuments(kbArticle.getCompanyId(), documents);
	}

	protected void reindexKBArticles(long companyId) throws Exception {
		int count = KBArticleLocalServiceUtil.getCompanyKBArticlesCount(
			companyId, WorkflowConstants.STATUS_APPROVED);

		int pages = count / Indexer.DEFAULT_INTERVAL;

		for (int i = 0; i <= pages; i++) {
			int start = (i * Indexer.DEFAULT_INTERVAL);
			int end = start + Indexer.DEFAULT_INTERVAL;

			reindexKBArticles(companyId, start, end);
		}
	}

	protected void reindexKBArticles(long companyId, int start, int end)
		throws Exception {

		List<KBArticle> kbArticles =
			KBArticleLocalServiceUtil.getCompanyKBArticles(
				companyId, WorkflowConstants.STATUS_APPROVED, start, end,
				new KBArticleModifiedDateComparator());

		Collection<Document> documents = new ArrayList<Document>();

		for (KBArticle kbArticle : kbArticles) {
			Document document = getDocument(kbArticle);

			documents.add(document);
		}

		SearchEngineUtil.updateDocuments(companyId, documents);
	}

}