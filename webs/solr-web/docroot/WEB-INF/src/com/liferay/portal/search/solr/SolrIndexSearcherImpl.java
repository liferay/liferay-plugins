/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.search.solr;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.HitsImpl;
import com.liferay.portal.kernel.search.IndexSearcher;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.QueryTranslatorUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.RangeFacet;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.solr.facet.SolrFacetFieldCollector;
import com.liferay.portal.search.solr.facet.SolrFacetQueryCollector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.FacetParams;

/**
 * @author Bruno Farache
 * @author Zsolt Berentey
 * @author Raymond Augé
 */
public class SolrIndexSearcherImpl implements IndexSearcher {

	public Hits search(SearchContext searchContext, Query query)
		throws SearchException {

		try {
			return doSearch(searchContext, query);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new SearchException(e.getMessage());
		}
	}

	public Hits search(
			String searchEngineId, long companyId, Query query, Sort[] sorts,
			int start, int end)
		throws SearchException {

		try {
			SolrQuery solrQuery = translateQuery(
				companyId, query, sorts, start, end);

			QueryResponse queryResponse = _solrServer.query(
				solrQuery, METHOD.POST);

			boolean allResults = false;

			if (solrQuery.getRows() == 0) {
				allResults = true;
			}

			return subset(
				solrQuery, query, query.getQueryConfig(), queryResponse,
				allResults);
		}
		catch (Exception e) {
			_log.error(e, e);

			if (!_swallowException) {
				throw new SearchException(e.getMessage());
			}

			return new HitsImpl();
		}
	}

	public void setSolrServer(SolrServer solrServer) {
		_solrServer = solrServer;
	}

	public void setSwallowException(boolean swallowException) {
		_swallowException = swallowException;
	}

	protected Hits doSearch(SearchContext searchContext, Query query)
		throws Exception {

		SolrQuery solrQuery = translateQuery(
			searchContext.getCompanyId(), query, searchContext.getSorts(),
			searchContext.getStart(), searchContext.getEnd());

		Map<String, Facet> facets = searchContext.getFacets();

		for (Facet facet : facets.values()) {
			if (facet.isStatic()) {
				continue;
			}

			FacetConfiguration facetConfiguration =
				facet.getFacetConfiguration();

			if (facet instanceof RangeFacet) {
				solrQuery.addFacetField(facetConfiguration.getFieldName());

				JSONObject dataJSONObject = facetConfiguration.getData();

				JSONArray rangesJSONArray = dataJSONObject.getJSONArray(
					"ranges");

				if (rangesJSONArray == null) {
					continue;
				}

				for (int i = 0; i < rangesJSONArray.length(); i++) {
					JSONObject rangeJSONObject = rangesJSONArray.getJSONObject(
						i);

					String range = rangeJSONObject.getString("range");

					String facetQuery =
						facetConfiguration.getFieldName() +
							StringPool.COLON + range;

					solrQuery.addFacetQuery(facetQuery);
				}
			}
			else {
				solrQuery.addFacetField(facetConfiguration.getFieldName());
			}

			String facetSort = FacetParams.FACET_SORT_COUNT;

			String order = facetConfiguration.getOrder();

			if (order.equals("OrderValueAsc")) {
				facetSort = FacetParams.FACET_SORT_INDEX;
			}

			solrQuery.add(
				"f." + facetConfiguration.getFieldName() + ".facet.sort",
				facetSort);
		}

		solrQuery.setFacetLimit(-1);

		QueryResponse queryResponse = _solrServer.query(solrQuery, METHOD.POST);

		boolean allResults = false;

		if (solrQuery.getRows() == 0) {
			allResults = true;
		}

		List<FacetField> facetFields = queryResponse.getFacetFields();

		if (facetFields != null) {
			for (FacetField facetField : facetFields) {
				Facet facet = facets.get(facetField.getName());

				FacetCollector facetCollector = null;

				if (facet instanceof RangeFacet) {
					facetCollector = new SolrFacetQueryCollector(
						facetField.getName(), queryResponse.getFacetQuery());
				}
				else {
					facetCollector = new SolrFacetFieldCollector(
						facetField.getName(), facetField);
				}

				facet.setFacetCollector(facetCollector);
			}
		}

		return subset(
			solrQuery, query, query.getQueryConfig(), queryResponse,
			allResults);
	}

	protected String getSnippet(
		SolrDocument solrDocument, QueryConfig queryConfig,
		Set<String> queryTerms,
		Map<String, Map<String, List<String>>> highlights, String field) {

		if (highlights == null) {
			return StringPool.BLANK;
		}

		String key = (String)solrDocument.getFieldValue(Field.UID);

		Map<String, List<String>> uidHighlights = highlights.get(key);

		boolean localizedSearch = true;

		String defaultLanguageId = LocaleUtil.toLanguageId(
			LocaleUtil.getDefault());
		String queryLanguageId = LocaleUtil.toLanguageId(
			queryConfig.getLocale());

		if (defaultLanguageId.equals(queryLanguageId)) {
			localizedSearch = false;
		}

		if (localizedSearch) {
			String localizedName = DocumentImpl.getLocalizedName(
				queryConfig.getLocale(), field);

			if (solrDocument.containsKey(localizedName)) {
				field = localizedName;
			}
		}

		List<String> snippets = uidHighlights.get(field);

		String snippet = StringUtil.merge(snippets, "...");

		if (Validator.isNotNull(snippet)) {
			snippet = snippet + "...";
		}
		else {
			snippet = StringPool.BLANK;
		}

		Pattern pattern = Pattern.compile("<em>(.*?)</em>");

		Matcher matcher = pattern.matcher(snippet);

		while (matcher.find()) {
			queryTerms.add(matcher.group(1));
		}

		snippet = StringUtil.replace(snippet, "<em>", "");
		snippet = StringUtil.replace(snippet, "</em>", "");

		return snippet;
	}

	protected Hits subset(
			SolrQuery solrQuery, Query query, QueryConfig queryConfig,
			QueryResponse queryResponse, boolean allResults)
		throws Exception {

		long startTime = System.currentTimeMillis();

		Hits hits = new HitsImpl();

		SolrDocumentList solrDocumentList = queryResponse.getResults();

		long total = solrDocumentList.getNumFound();

		if (allResults && (total > 0)) {
			solrQuery.setRows((int)total);

			queryResponse = _solrServer.query(solrQuery);

			return subset(solrQuery, query, queryConfig, queryResponse, false);
		}

		List<Document> documents = new ArrayList<Document>();
		List<Float> scores = new ArrayList<Float>();
		List<String> snippets = new ArrayList<String>();

		float maxScore = -1;
		Set<String> queryTerms = new HashSet<String>();
		int subsetTotal = 0;

		for (SolrDocument solrDocument : solrDocumentList) {
			Document document = new DocumentImpl();

			Collection<String> names = solrDocument.getFieldNames();

			for (String name : names) {
				Collection<Object> fieldValues = solrDocument.getFieldValues(
					name);

				Field field = new Field(
					name,
					ArrayUtil.toStringArray(
						fieldValues.toArray(new Object[fieldValues.size()])));

				document.add(field);
			}

			documents.add(document);

			String snippet = StringPool.BLANK;

			if (queryConfig.isHighlightEnabled()) {
				snippet = getSnippet(
					solrDocument, queryConfig, queryTerms,
					queryResponse.getHighlighting(), Field.CONTENT);

				if (Validator.isNull(snippet)) {
					snippet = getSnippet(
						solrDocument, queryConfig, queryTerms,
						queryResponse.getHighlighting(), Field.TITLE);
				}

				if (Validator.isNotNull(snippet)) {
					snippets.add(snippet);
				}
			}

			if (queryConfig.isScoreEnabled()) {
				float score = GetterUtil.getFloat(
					String.valueOf(solrDocument.getFieldValue("score")));

				if (score > maxScore) {
					maxScore = score;
				}

				scores.add(score);
			}
			else {
				scores.add(maxScore);
			}

			subsetTotal++;
		}

		hits.setDocs(documents.toArray(new Document[subsetTotal]));
		hits.setLength((int)total);
		hits.setQuery(query);
		hits.setQueryTerms(queryTerms.toArray(new String[queryTerms.size()]));

		Float[] scoresArray = scores.toArray(new Float[subsetTotal]);

		if (queryConfig.isScoreEnabled() && (subsetTotal > 0) &&
			(maxScore > 0)) {

			for (int i = 0; i < scoresArray.length; i++) {
				scoresArray[i] = scoresArray[i] / maxScore;
			}
		}

		hits.setScores(scoresArray);

		float searchTime =
			(float)(System.currentTimeMillis() - startTime) / Time.SECOND;

		hits.setSearchTime(searchTime);
		hits.setSnippets(snippets.toArray(new String[subsetTotal]));
		hits.setStart(startTime);

		return hits;
	}

	protected SolrQuery translateQuery(
			long companyId, Query query, Sort[] sorts, int start, int end)
		throws Exception {

		QueryConfig queryConfig = query.getQueryConfig();

		SolrQuery solrQuery = new SolrQuery();

		if (queryConfig.isHighlightEnabled()) {
			solrQuery.setHighlight(true);
			solrQuery.setHighlightFragsize(
				queryConfig.getHighlightFragmentSize());
			solrQuery.setHighlightSnippets(
				queryConfig.getHighlightSnippetSize());

			String localizedContentName = DocumentImpl.getLocalizedName(
				queryConfig.getLocale(), Field.CONTENT);

			String localizedTitleName = DocumentImpl.getLocalizedName(
				queryConfig.getLocale(), Field.TITLE);

			solrQuery.setParam(
				"hl.fl", Field.CONTENT, localizedContentName, Field.TITLE,
				localizedTitleName);
		}

		solrQuery.setIncludeScore(queryConfig.isScoreEnabled());

		QueryTranslatorUtil.translateForSolr(query);

		String queryString = query.toString();

		StringBundler sb = new StringBundler(6);

		sb.append(queryString);
		sb.append(StringPool.SPACE);
		sb.append(StringPool.PLUS);
		sb.append(Field.COMPANY_ID);
		sb.append(StringPool.COLON);
		sb.append(companyId);

		solrQuery.setQuery(sb.toString());

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS)) {
			solrQuery.setRows(0);
		}
		else {
			solrQuery.setRows(end - start);
			solrQuery.setStart(start);
		}

		if (sorts != null) {
			for (Sort sort : sorts) {
				if (sort == null) {
					continue;
				}

				String sortFieldName = sort.getFieldName();

				if (DocumentImpl.isSortableTextField(sortFieldName)) {
					sortFieldName = DocumentImpl.getSortableFieldName(
						sortFieldName);
				}

				ORDER order = ORDER.asc;

				if (Validator.isNull(sortFieldName) ||
					!sortFieldName.endsWith("sortable")) {

					sortFieldName = "score";

					order = ORDER.desc;
				}

				if (sort.isReverse()) {
					order = ORDER.desc;
				}

				solrQuery.addSortField(sortFieldName, order);
			}
		}

		return solrQuery;
	}

	private static Log _log = LogFactoryUtil.getLog(
		SolrIndexSearcherImpl.class);

	private SolrServer _solrServer;
	private boolean _swallowException;

}