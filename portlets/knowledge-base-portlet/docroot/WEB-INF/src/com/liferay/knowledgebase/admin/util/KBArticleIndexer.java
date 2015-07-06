/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
import com.liferay.knowledgebase.service.permission.KBArticlePermission;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
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
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class KBArticleIndexer extends BaseIndexer<KBArticle> {

	public static final String CLASS_NAME = KBArticle.class.getName();

	public KBArticleIndexer() {
		setDefaultSelectedFieldNames(
			Field.COMPANY_ID, Field.CONTENT, Field.CREATE_DATE,
			Field.DESCRIPTION, Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
			Field.MODIFIED_DATE, Field.TITLE, Field.UID, Field.USER_NAME);
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, String entryClassName,
			long entryClassPK, String actionId)
		throws Exception {

		return KBArticlePermission.contains(
			permissionChecker, entryClassPK, ActionKeys.VIEW);
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		addSearchTerm(searchQuery, searchContext, Field.CONTENT, true);
		addSearchTerm(searchQuery, searchContext, Field.DESCRIPTION, true);
		addSearchTerm(searchQuery, searchContext, Field.TITLE, true);
		addSearchTerm(searchQuery, searchContext, Field.USER_NAME, true);
	}

	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		Hits hits = super.search(searchContext);

		String[] queryTerms = hits.getQueryTerms();

		String keywords = searchContext.getKeywords();

		queryTerms = ArrayUtil.append(
			queryTerms, KnowledgeBaseUtil.splitKeywords(keywords));

		hits.setQueryTerms(queryTerms);

		return hits;
	}

	@Override
	protected void doDelete(KBArticle kbArticle) throws Exception {
		deleteDocument(
			kbArticle.getCompanyId(), kbArticle.getResourcePrimKey());
	}

	@Override
	protected Document doGetDocument(KBArticle kbArticle) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, kbArticle);

		document.addText(
			Field.CONTENT, HtmlUtil.extractText(kbArticle.getContent()));
		document.addText(Field.DESCRIPTION, kbArticle.getDescription());
		document.addText(Field.TITLE, kbArticle.getTitle());

		document.addKeyword("titleKeyword", kbArticle.getTitle(), true);

		return document;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletRequest portletRequest, PortletResponse portletResponse) {

		String title = document.get(Field.TITLE);

		String content = snippet;

		if (Validator.isNull(snippet)) {
			content = document.get(Field.DESCRIPTION);

			if (Validator.isNull(content)) {
				content = StringUtil.shorten(document.get(Field.CONTENT), 200);
			}
		}

		String resourcePrimKey = document.get(Field.ENTRY_CLASS_PK);

		return new Summary(title, content);
	}

	@Override
	protected void doReindex(KBArticle kbArticle) throws Exception {
		SearchEngineUtil.updateDocument(
			getSearchEngineId(), kbArticle.getCompanyId(),
			getDocument(kbArticle), isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		KBArticle kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(
			classPK, WorkflowConstants.STATUS_ANY);

		reindexKBArticles(kbArticle);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexKBArticles(companyId);
	}

	protected void reindexKBArticles(KBArticle kbArticle) throws Exception {

		// See KBArticlePermission#contains

		List<KBArticle> kbArticles =
			KBArticleLocalServiceUtil.getKBArticleAndAllDescendantKBArticles(
				kbArticle.getResourcePrimKey(),
				WorkflowConstants.STATUS_APPROVED, null);

		Collection<Document> documents = new ArrayList<>();

		for (KBArticle curKBArticle : kbArticles) {
			documents.add(getDocument(curKBArticle));
		}

		SearchEngineUtil.updateDocuments(
			getSearchEngineId(), kbArticle.getCompanyId(), documents,
			isCommitImmediately());
	}

	protected void reindexKBArticles(long companyId) throws Exception {
		final ActionableDynamicQuery actionableDynamicQuery =
			KBArticleLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					Property property = PropertyFactoryUtil.forName("status");

					dynamicQuery.add(
						property.eq(WorkflowConstants.STATUS_APPROVED));
				}

			});
		actionableDynamicQuery.setCompanyId(companyId);
		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod() {

				@Override
				public void performAction(Object object) {
					KBArticle kbArticle = (KBArticle)object;

					try {
						Document document = getDocument(kbArticle);

						actionableDynamicQuery.addDocument(document);
					}
					catch (PortalException pe) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								"Unable to index knowledge base article " +
									kbArticle.getKbArticleId(),
								pe);
						}
					}
				}

			});
		actionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		actionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		KBArticleIndexer.class);

}