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

package com.liferay.portal.search.solr;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexSearcher;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.HitsImpl;
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
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.solr.facet.SolrFacetFieldCollector;
import com.liferay.portal.search.solr.facet.SolrFacetQueryCollector;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.StopWatch;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
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
public class SolrIndexSearcher extends BaseIndexSearcher {

	@Override
	public Hits search(SearchContext searchContext, Query query)
		throws SearchException {

		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		try {
			QueryResponse queryResponse = search(
				searchContext, query, searchContext.getStart(),
				searchContext.getEnd());

			Hits hits = processQueryResponse(
				queryResponse, searchContext, query);

			hits.setStart(stopWatch.getStartTime());

			return hits;
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}

			if (!_swallowException) {
				throw new SearchException(e.getMessage());
			}

			return new HitsImpl();
		}
		finally {
			if (_log.isInfoEnabled()) {
				stopWatch.stop();

				_log.info(
					"Searching " + query.toString() + " took " +
						stopWatch.getTime() + " ms");
			}
		}
	}

	public void setSolrServer(SolrServer solrServer) {
		_solrServer = solrServer;
	}

	public void setSwallowException(boolean swallowException) {
		_swallowException = swallowException;
	}

	protected void addFacets(SolrQuery solrQuery, SearchContext searchContext) {
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

				Serializable modified = searchContext.getAttribute("modified");

				if (Validator.isNotNull(modified)) {
					String facetQuery =
						facetConfiguration.getFieldName() + StringPool.COLON +
							GetterUtil.getString(modified);

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
	}

	protected void addHighlightedField(
		SolrQuery solrQuery, QueryConfig queryConfig,
		String fieldName) {

		solrQuery.addHighlightField(fieldName);

		String localizedFieldName = DocumentImpl.getLocalizedName(
			queryConfig.getLocale(), fieldName);

		solrQuery.addHighlightField(localizedFieldName);
	}

	protected void addHighlights(SolrQuery solrQuery, QueryConfig queryConfig) {
		if (!queryConfig.isHighlightEnabled()) {
			return;
		}

		solrQuery.setHighlight(true);
		solrQuery.setHighlightFragsize(queryConfig.getHighlightFragmentSize());
		solrQuery.setHighlightRequireFieldMatch(true);
		solrQuery.setHighlightSnippets(queryConfig.getHighlightSnippetSize());

		addHighlightedField(solrQuery, queryConfig, Field.CONTENT);
		addHighlightedField(solrQuery, queryConfig, Field.DESCRIPTION);
		addHighlightedField(solrQuery, queryConfig, Field.TITLE);
	}

	protected void addPagination(SolrQuery solrQuery, int start, int end) {
		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS)) {
			solrQuery.setRows(0);
		}
		else {
			solrQuery.setRows(end - start);
			solrQuery.setStart(start);
		}
	}

	protected float addScore(
		SolrDocument solrDocument, List<Float> scores, float maxScore,
		boolean scoreEnabled) {

		if (scoreEnabled) {
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

		return maxScore;
	}

	protected void addSelectedFields(
		SolrQuery solrQuery, QueryConfig queryConfig) {

		if (queryConfig.isAllFieldsSelected()) {
			return;
		}

		String[] selectedFieldNames = queryConfig.getSelectedFieldNames();

		solrQuery.setFields(selectedFieldNames);
	}

	protected void addSort(SolrQuery solrQuery, Sort[] sorts) {
		if (ArrayUtil.isEmpty(sorts)) {
			return;
		}

		Set<String> sortFieldNames = new HashSet<String>();

		for (Sort sort : sorts) {
			if (sort == null) {
				continue;
			}

			String sortFieldName = DocumentImpl.getSortFieldName(sort, "score");

			if (sortFieldNames.contains(sortFieldName)) {
				continue;
			}

			sortFieldNames.add(sortFieldName);

			ORDER order = ORDER.asc;

			if (sort.isReverse() || sortFieldName.equals("score")) {
				order = ORDER.desc;
			}

			solrQuery.addSort(new SortClause(sortFieldName, order));
		}
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

		Matcher matcher = _pattern.matcher(snippet);

		while (matcher.find()) {
			queryTerms.add(matcher.group(1));
		}

		snippet = StringUtil.replace(snippet, "<em>", "");
		snippet = StringUtil.replace(snippet, "</em>", "");

		return snippet;
	}

	protected Float[] normalizeScores(
		List<Float> scores, QueryConfig queryConfig, float maxScore,
		int numDocuments) {

		Float[] scoresArray = scores.toArray(new Float[numDocuments]);

		if (queryConfig.isScoreEnabled() && (numDocuments > 0) &&
			(maxScore > 0)) {

			for (int i = 0; i < scoresArray.length; i++) {
				scoresArray[i] = scoresArray[i] / maxScore;
			}
		}

		return scoresArray;
	}

	protected Hits processQueryResponse(
			QueryResponse queryResponse, SearchContext searchContext,
			Query query)
		throws Exception {

		boolean allResults = false;

		if ((searchContext.getStart() == QueryUtil.ALL_POS) &&
			(searchContext.getEnd() == QueryUtil.ALL_POS)) {

			allResults = true;
		}

		return subset(
			searchContext, query, query.getQueryConfig(), queryResponse,
			allResults);
	}

	protected Document processSolrDocument(SolrDocument solrDocument) {
		Document document = new DocumentImpl();

		Collection<String> names = solrDocument.getFieldNames();

		for (String name : names) {
			Collection<Object> fieldValues = solrDocument.getFieldValues(name);

			Field field = new Field(
				name,
				ArrayUtil.toStringArray(
					fieldValues.toArray(new Object[fieldValues.size()])));

			document.add(field);
		}

		return document;
	}

	protected QueryResponse search(
			SearchContext searchContext, Query query, int start, int end)
		throws Exception {

		SolrQuery solrQuery = new SolrQuery();

		QueryConfig queryConfig = query.getQueryConfig();

		addFacets(solrQuery, searchContext);
		addHighlights(solrQuery, queryConfig);
		addPagination(solrQuery, start, end);
		addSelectedFields(solrQuery, queryConfig);
		addSort(solrQuery, searchContext.getSorts());

		solrQuery.setIncludeScore(queryConfig.isScoreEnabled());

		translateQuery(solrQuery, searchContext, query);

		return _solrServer.query(solrQuery, METHOD.POST);
	}

	protected Hits subset(
			SearchContext searchContext, Query query, QueryConfig queryConfig,
			QueryResponse queryResponse, boolean allResults)
		throws Exception {

		long startTime = System.currentTimeMillis();

		Hits hits = new HitsImpl();

		SolrDocumentList solrDocumentList = queryResponse.getResults();

		long total = solrDocumentList.getNumFound();

		if (allResults && (total > 0)) {
			queryResponse = search(searchContext, query, 0, (int)total);

			solrDocumentList = queryResponse.getResults();

			total = solrDocumentList.getNumFound();
		}

		updateFacetCollectors(queryResponse, searchContext);

		List<Document> documents = new ArrayList<Document>();
		List<Float> scores = new ArrayList<Float>();
		List<String> snippets = new ArrayList<String>();

		float maxScore = -1;
		Set<String> queryTerms = new HashSet<String>();
		int subsetTotal = 0;

		for (SolrDocument solrDocument : solrDocumentList) {
			Document document = processSolrDocument(solrDocument);

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

			maxScore = addScore(
				solrDocument, scores, maxScore, queryConfig.isScoreEnabled());

			subsetTotal++;
		}

		hits.setDocs(documents.toArray(new Document[subsetTotal]));
		hits.setLength((int)total);
		hits.setQuery(query);
		hits.setQueryTerms(queryTerms.toArray(new String[queryTerms.size()]));

		Float[] scoresArray = normalizeScores(
			scores, query.getQueryConfig(), maxScore, documents.size());

		hits.setScores(scoresArray);

		hits.setSearchTime(queryResponse.getQTime());
		hits.setSnippets(snippets.toArray(new String[subsetTotal]));
		hits.setStart(startTime);

		return hits;
	}

	protected void translateQuery(
			SolrQuery solrQuery, SearchContext searchContext, Query query)
		throws Exception {

		QueryTranslatorUtil.translateForSolr(query);

		String queryString = query.toString();

		StringBundler sb = new StringBundler(6);

		sb.append(queryString);
		sb.append(StringPool.SPACE);
		sb.append(StringPool.PLUS);
		sb.append(Field.COMPANY_ID);
		sb.append(StringPool.COLON);
		sb.append(searchContext.getCompanyId());

		solrQuery.setQuery(sb.toString());
	}

	protected void updateFacetCollectors(
		QueryResponse queryResponse, SearchContext searchContext) {

		Map<String, Facet> facetsMap = searchContext.getFacets();

		List<FacetField> facetFields = queryResponse.getFacetFields();

		if (ListUtil.isEmpty(facetFields)) {
			return;
		}

		for (FacetField facetField : facetFields) {
			Facet facet = facetsMap.get(facetField.getName());

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

	private static Log _log = LogFactoryUtil.getLog(SolrIndexSearcher.class);

	private Pattern _pattern = Pattern.compile("<em>(.*?)</em>");
	private SolrServer _solrServer;
	private boolean _swallowException;

}