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

package com.liferay.asset.entry.set.util;

import com.liferay.asset.entry.set.model.AssetEntrySet;
import com.liferay.asset.entry.set.service.AssetEntrySetLocalServiceUtil;
import com.liferay.asset.entry.set.service.persistence.AssetEntrySetActionableDynamicQuery;
import com.liferay.asset.sharing.model.AssetSharingEntry;
import com.liferay.asset.sharing.service.AssetSharingEntryLocalServiceUtil;
import com.liferay.compat.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngine;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletURL;

/**
 * @author Sherry Yang
 */
public class AssetEntrySetIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = {AssetEntrySet.class.getName()};

	public static final String PORTLET_ID = PortletKeys.ASSET_ENTRY_SET;

	public AssetEntrySetIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getPortletId() {
		return PORTLET_ID;
	}

	@Override
	public void postProcessContextQuery(
			BooleanQuery contextQuery, SearchContext searchContext)
		throws Exception {

		String searchEngineId = searchContext.getSearchEngineId();

		SearchEngine searchEngine = SearchEngineUtil.getSearchEngine(
			searchEngineId);

		if (searchEngine.isLuceneBased()) {
			addFilterQuery(contextQuery, searchContext);

			return;
		}

		QueryConfig queryConfig = searchContext.getQueryConfig();

		BooleanQuery filterQuery = BooleanQueryFactoryUtil.create(
			searchContext);

		addFilterQuery(filterQuery, searchContext);

		queryConfig.setAttribute("filterQuery", filterQuery);
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		addSearchTerm(searchQuery, searchContext, "creatorName", true);
		addSearchTerm(searchQuery, searchContext, "message", true);
		addSearchTerm(searchQuery, searchContext, "title", true);
	}

	protected void addExcludeAssetEntrySetIdsQuery(
			BooleanQuery filterQuery, SearchContext searchContext)
		throws Exception {

		BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create(
			searchContext);

		List<Long> excludeAssetEntrySetIds =
			(ArrayList<Long>)searchContext.getAttribute(
				"excludeAssetEntrySetIds");

		if (ListUtil.isEmpty(excludeAssetEntrySetIds)) {
			return;
		}

		for (long excludeAssetEntrySetId : excludeAssetEntrySetIds) {
			booleanQuery.addTerm("assetEntrySetId", excludeAssetEntrySetId);
		}

		filterQuery.add(booleanQuery, BooleanClauseOccur.MUST_NOT);
	}

	protected void addFilterQuery(
			BooleanQuery filterQuery, SearchContext searchContext)
		throws Exception {

		BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create(
			searchContext);

		addIncludeAssetEntrySetIdsQuery(booleanQuery, searchContext);
		addFullyFollowedEntitiesQuery(booleanQuery, searchContext);
		addPartiallyFollowedEntitiesQuery(booleanQuery, searchContext);

		if (booleanQuery.hasClauses()) {
			filterQuery.add(booleanQuery, BooleanClauseOccur.MUST);
		}

		addExcludeAssetEntrySetIdsQuery(filterQuery, searchContext);
		addMembershipQuery(filterQuery, searchContext);
		addParentAssetEntrySetQuery(filterQuery);
		addPrivateAssetEntrySetQuery(filterQuery, searchContext);
		addTypeQuery(filterQuery, searchContext);
		addTimeQuery(filterQuery, searchContext, "createTime_sortable");
		addTimeQuery(filterQuery, searchContext, "modifiedTime_sortable");
		addTimeQuery(filterQuery, searchContext, "stickyTime_sortable");
	}

	protected void addFullyFollowedEntitiesQuery(
			BooleanQuery booleanQuery, SearchContext searchContext)
		throws Exception {

		List<String> fullyFollowedEntities =
			(ArrayList<String>)searchContext.getAttribute(
				"fullyFollowedEntities");

		for (String fullyFollowedEntity : fullyFollowedEntities) {
			booleanQuery.addTerm("sharedTo", fullyFollowedEntity);
		}
	}

	protected void addIncludeAssetEntrySetIdsQuery(
			BooleanQuery booleanQuery, SearchContext searchContext)
		throws Exception {

		List<Long> includeAssetEntrySetIds =
			(ArrayList<Long>)searchContext.getAttribute(
				"includeAssetEntrySetIds");

		for (long includeAssetEntrySetId : includeAssetEntrySetIds) {
			booleanQuery.addTerm("assetEntrySetId", includeAssetEntrySetId);
		}
	}

	protected void addMembershipQuery(
			BooleanQuery filterQuery, SearchContext searchContext)
		throws Exception {

		BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create(
			searchContext);

		String[] membershipSearchTerms =
			AssetEntrySetParticipantInfoUtil.getMembershipSearchTerms(
				searchContext.getUserId());

		for (String membershipSearchTerm : membershipSearchTerms) {
			booleanQuery.addTerm("sharedTo", membershipSearchTerm);
		}

		booleanQuery.addTerm("privateAssetEntrySet", StringPool.FALSE);

		filterQuery.add(booleanQuery, BooleanClauseOccur.MUST);
	}

	protected void addParentAssetEntrySetQuery(BooleanQuery filterQuery)
		throws Exception {

		filterQuery.addRequiredTerm("parentAssetEntrySetId", 0);
	}

	protected void addPartiallyFollowedEntitiesQuery(
			BooleanQuery booleanQuery, SearchContext searchContext)
		throws Exception {

		List<String> partiallyFollowedEntities =
			(ArrayList<String>)searchContext.getAttribute(
				"partiallyFollowedEntities");

		for (String partiallyFollowedEntity : partiallyFollowedEntities) {
			booleanQuery.addTerm("creator_sortable", partiallyFollowedEntity);
		}
	}

	protected void addPrivateAssetEntrySetQuery(
			BooleanQuery filterQuery, SearchContext searchContext)
		throws Exception {

		boolean privateAssetEntrySet = GetterUtil.getBoolean(
			searchContext.getAttribute("privateAssetEntrySet"));

		if (privateAssetEntrySet) {
			filterQuery.addRequiredTerm(
				"privateAssetEntrySet", privateAssetEntrySet);
		}
	}

	protected void addTimeQuery(
			BooleanQuery filterQuery, SearchContext searchContext,
			String timeFieldName)
		throws Exception {

		BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create(
			searchContext);

		long startValue = 0;
		long endValue = 0;

		long time = GetterUtil.getLong(
			searchContext.getAttribute(timeFieldName));

		if (time <= 0) {
			return;
		}

		String timeComparator = GetterUtil.getString(
			searchContext.getAttribute(
				timeFieldName.substring(
					0,
					timeFieldName.indexOf(
						StringPool.UNDERLINE)) + "Comparator"));

		if (timeComparator.equals(StringPool.GREATER_THAN)) {
			startValue = time + 1;
			endValue = Long.MAX_VALUE;
		}
		else if (timeComparator.equals(StringPool.GREATER_THAN_OR_EQUAL)) {
			startValue = time;
			endValue = Long.MAX_VALUE;
		}
		else if (timeComparator.equals(StringPool.LESS_THAN)) {
			startValue = 0;
			endValue = time - 1;
		}
		else if (timeComparator.equals(StringPool.LESS_THAN_OR_EQUAL)) {
			startValue = 0;
			endValue = time;
		}

		booleanQuery.addNumericRangeTerm(timeFieldName, startValue, endValue);

		filterQuery.add(booleanQuery, BooleanClauseOccur.MUST);
	}

	protected void addTypeQuery(
		BooleanQuery filterQuery, SearchContext searchContext) {

		Integer type = (Integer)searchContext.getAttribute("type");

		if (type == null) {
			return;
		}

		filterQuery.addRequiredTerm("type", type.intValue());
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		AssetEntrySet assetEntrySet = (AssetEntrySet)obj;

		deleteDocument(
			assetEntrySet.getCompanyId(), assetEntrySet.getAssetEntrySetId());
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		AssetEntrySet assetEntrySet = (AssetEntrySet)obj;

		Document document = getBaseModelDocument(PORTLET_ID, assetEntrySet);

		document.addKeyword(Field.TYPE, assetEntrySet.getType());

		document.addKeyword(
			"assetEntrySetId", assetEntrySet.getAssetEntrySetId());
		document.addNumber("createTime", assetEntrySet.getCreateTime());
		document.addText(
			"creatorName",
			AssetEntrySetParticipantInfoUtil.getParticipantName(
				assetEntrySet.getCreatorClassNameId(),
				assetEntrySet.getCreatorClassPK()));
		document.addKeyword(
			"creator_sortable",
			AssetEntrySetParticipantInfoUtil.getSearchTerm(
				assetEntrySet.getCreatorClassNameId(),
				assetEntrySet.getCreatorClassPK()));

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			assetEntrySet.getPayload());

		document.addText("message", payloadJSONObject.getString("message"));
		document.addNumber("modifiedTime", assetEntrySet.getModifiedTime());
		document.addKeyword(
			"parentAssetEntrySetId", assetEntrySet.getParentAssetEntrySetId());
		document.addKeyword(
			"privateAssetEntrySet", assetEntrySet.getPrivateAssetEntrySet());
		document.addKeyword(
			"sharedTo", getSharedTo(assetEntrySet.getAssetEntrySetId()));
		document.addNumber("stickyTime", assetEntrySet.getStickyTime());
		document.addText("title", payloadJSONObject.getString("title"));

		return document;
	}

	@Override
	protected Summary doGetSummary(
			Document document, Locale locale, String snippet,
			PortletURL portletURL)
		throws Exception {

		return new Summary(document.get(Field.ENTRY_CLASS_PK), null, null);
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		AssetEntrySet assetEntrySet = (AssetEntrySet)obj;

		Document document = getDocument(assetEntrySet);

		SearchEngineUtil.updateDocument(
			getSearchEngineId(), assetEntrySet.getCompanyId(), document);

		if (assetEntrySet.getParentAssetEntrySetId() > 0) {
			AssetEntrySet parentAssetEntrySet =
				AssetEntrySetLocalServiceUtil.getAssetEntrySet(
					assetEntrySet.getParentAssetEntrySetId());

			doReindex(parentAssetEntrySet);
		}
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		AssetEntrySet assetEntrySet =
			AssetEntrySetLocalServiceUtil.getAssetEntrySet(classPK);

		doReindex(assetEntrySet);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexEntries(companyId);
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
	}

	protected String[] getSharedTo(long assetEntrySetId) throws Exception {
		List<AssetSharingEntry> assetSharingEntries =
			AssetSharingEntryLocalServiceUtil.getAssetSharingEntries(
				AssetEntrySetConstants.ASSET_ENTRY_SET_CLASS_NAME_ID,
				assetEntrySetId);

		String[] sharedTo = new String[assetSharingEntries.size()];

		for (int i = 0; i < assetSharingEntries.size(); i++) {
			AssetSharingEntry assetSharingEntry = assetSharingEntries.get(i);

			sharedTo[i] = AssetEntrySetParticipantInfoUtil.getSearchTerm(
				assetSharingEntry.getSharedToClassNameId(),
				assetSharingEntry.getSharedToClassPK());
		}

		return sharedTo;
	}

	protected void reindexEntries(long companyId)
		throws PortalException, SystemException {

		ActionableDynamicQuery actionableDynamicQuery =
			new AssetEntrySetActionableDynamicQuery() {

				@Override
				protected void performAction(Object object) {
					AssetEntrySet assetEntrySet = (AssetEntrySet)object;

					try {
						Document document = getDocument(assetEntrySet);

						addDocument(document);
					}
					catch (PortalException pe) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								"Unable to index asset entry set " +
									assetEntrySet.getAssetEntrySetId(),
								pe);
						}
					}
				}
		};

		actionableDynamicQuery.setCompanyId(companyId);
		actionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		actionableDynamicQuery.performActions();
	}

	private static Log _log = LogFactoryUtil.getLog(AssetEntrySetIndexer.class);

}