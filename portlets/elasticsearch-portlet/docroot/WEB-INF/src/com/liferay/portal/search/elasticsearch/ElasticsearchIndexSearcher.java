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

package com.liferay.portal.search.elasticsearch;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
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
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.elasticsearch.connection.ElasticsearchConnectionManager;
import com.liferay.portal.search.elasticsearch.facet.ElasticsearchFacetFieldCollector;
import com.liferay.portal.search.elasticsearch.facet.FacetProcessorUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.time.StopWatch;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.facet.Facets;
import org.elasticsearch.search.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;

/**
 * @author Michael C. Han
 * @author Milen Dyankov
 */
public class ElasticsearchIndexSearcher extends BaseIndexSearcher {

	@Override
	public Hits search(SearchContext searchContext, Query query)
		throws SearchException {

		ElasticsearchConnectionManager elasticsearchConnectionManager =
			ElasticsearchConnectionManager.getInstance();

		Client client = elasticsearchConnectionManager.getClient();

		QueryConfig queryConfig = query.getQueryConfig();

		StopWatch stopWatch = null;

		if (_log.isInfoEnabled()) {
			stopWatch = new StopWatch();

			stopWatch.start();
		}

		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(
			String.valueOf(searchContext.getCompanyId()));

		addFacets(searchRequestBuilder, searchContext);
		addHighlights(searchRequestBuilder, queryConfig);
		addPagination(
			searchRequestBuilder, searchContext.getStart(),
			searchContext.getEnd());
		addSelectedFields(searchRequestBuilder, queryConfig);
		addSort(searchRequestBuilder, searchContext.getSorts());

		QueryBuilder queryBuilder = QueryBuilders.queryString(query.toString());

		searchRequestBuilder.setQuery(queryBuilder);

		long startTime = System.currentTimeMillis();

		SearchRequest searchRequest = searchRequestBuilder.request();

		ActionFuture<SearchResponse> future = client.search(searchRequest);

		SearchResponse searchResponse = future.actionGet();

		updateFacetCollectors(searchContext, searchResponse);

		Hits hits = processSearchHits(searchResponse.getHits(), queryConfig);

		hits.setQuery(query);

		TimeValue timeValue = searchResponse.getTook();
		hits.setSearchTime((float)timeValue.getSecondsFrac());

		hits.setStart(startTime);

		if (_log.isInfoEnabled()) {
			stopWatch.stop();

			_log.info(
				"Search: " + queryBuilder.toString() + " consumed: " +
					stopWatch.getTime() + "ms. Search engine took: " +
					hits.getSearchTime() + "s");
		}

		return hits;
	}

	protected void addFacets(
		SearchRequestBuilder searchRequestBuilder,
		SearchContext searchContext) {

		Map<String, Facet> facets = searchContext.getFacets();

		for (Facet facet : facets.values()) {
			if (facet.isStatic()) {
				continue;
			}

			FacetProcessorUtil.processFacet(searchRequestBuilder, facet);
		}
	}

	protected void addHighlights(
		SearchRequestBuilder searchRequestBuilder, QueryConfig queryConfig) {

		if (!queryConfig.isHighlightEnabled()) {
			return;
		}

		String localizedContentName = DocumentImpl.getLocalizedName(
			queryConfig.getLocale(), Field.CONTENT);

		String localizedTitleName = DocumentImpl.getLocalizedName(
			queryConfig.getLocale(), Field.TITLE);

		searchRequestBuilder.addHighlightedField(
			localizedContentName, queryConfig.getHighlightFragmentSize(),
			queryConfig.getHighlightSnippetSize());

		searchRequestBuilder.addHighlightedField(
			localizedTitleName, queryConfig.getHighlightFragmentSize(),
			queryConfig.getHighlightSnippetSize());
	}

	protected void addPagination(
		SearchRequestBuilder searchRequestBuilder, int start, int end) {

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS)) {
			searchRequestBuilder.setSize(0);
		}
		else {
			searchRequestBuilder.setSize(end - start);
			searchRequestBuilder.setFrom(start);
		}
	}

	protected void addSelectedFields(
		SearchRequestBuilder searchRequestBuilder, QueryConfig queryConfig) {

		String[] selectedFieldNames = queryConfig.getSelectedFieldNames();

		if ((selectedFieldNames == null) || (selectedFieldNames.length == 0)) {
			searchRequestBuilder.addFields("*");
		}
		else {
			searchRequestBuilder.addFields(selectedFieldNames);
		}
	}

	protected void addSort(
		SearchRequestBuilder searchRequestBuilder, Sort[] sorts) {

		if ((sorts == null) || (sorts.length == 0)) {
			return;
		}

		for (Sort sort : sorts) {
			if (sort == null) {
				continue;
			}

			String sortFieldName = sort.getFieldName();

			if (DocumentImpl.isSortableTextField(sortFieldName)) {
				sortFieldName = DocumentImpl.getSortableFieldName(
					sortFieldName);
			}

			SortOrder order = SortOrder.ASC;

			if (Validator.isNull(sortFieldName) ||
				!sortFieldName.endsWith("sortable")) {

				order = SortOrder.DESC;

				sortFieldName = "_score";
			}

			if (sort.isReverse()) {
				order = SortOrder.DESC;
			}

			searchRequestBuilder.addSort(sortFieldName, order);
		}
	}

	protected String getSnippet(SearchHit hit, QueryConfig queryConfig) {
		String localizedContentName = DocumentImpl.getLocalizedName(
			queryConfig.getLocale(), Field.CONTENT);

		String localizedTitleName = DocumentImpl.getLocalizedName(
			queryConfig.getLocale(), Field.TITLE);

		String snippet = null;

		Map<String, HighlightField> highlightFields = hit.getHighlightFields();

		if ((highlightFields == null) || highlightFields.isEmpty()) {
			return StringPool.BLANK;
		}

		if (localizedContentName != null) {
			HighlightField highlightField = highlightFields.get(
				localizedContentName);

			if (highlightField != null) {
				Text[] texts = highlightField.fragments();

				if ((texts != null) && (texts.length > 0)) {
					snippet = texts[0].string();
				}
			}
		}

		if ((snippet == null) && (localizedTitleName != null)) {
			HighlightField highlightField = highlightFields.get(
				localizedContentName);

			if (highlightField != null) {
				Text[] texts = highlightField.fragments();

				if ((texts != null) && (texts.length > 0)) {
					snippet = texts[0].string();
				}
			}
		}

		if (snippet == null) {
			snippet = StringPool.BLANK;
		}

		return snippet;
	}

	protected Document processSearchHit(SearchHit hit) {
		Document document = new DocumentImpl();

		Map<String, SearchHitField> hitFields = hit.getFields();

		for (Map.Entry<String, SearchHitField> entry : hitFields.entrySet()) {
			Collection<Object> fieldValues = entry.getValue().getValues();

			Field field = new Field(
				entry.getKey(),
				ArrayUtil.toStringArray(
					fieldValues.toArray(new Object[fieldValues.size()])));

			document.add(field);
		}

		return document;
	}

	protected Hits processSearchHits(
		SearchHits searchHits, QueryConfig queryConfig) {

		Hits hits = new HitsImpl();
		List<Document> documents = new ArrayList<Document>();
		Set<String> queryTerms = new HashSet<String>();
		List<Float> scores = new ArrayList<Float>();
		List<String> snippets = new ArrayList<String>();

		if (searchHits.totalHits() > 0) {
			SearchHit[] returnedHits = searchHits.getHits();

			for (SearchHit hit : returnedHits) {
				Document document = processSearchHit(hit);
				documents.add(document);

				String snippet = getSnippet(hit, queryConfig);

				queryTerms.add(snippet);
				snippets.add(snippet);

				scores.add(hit.getScore());
			}
		}

		hits.setDocs(documents.toArray(new Document[documents.size()]));
		hits.setLength((int)searchHits.getTotalHits());
		hits.setQueryTerms(queryTerms.toArray(new String[queryTerms.size()]));
		hits.setScores(scores.toArray(new Float[scores.size()]));
		hits.setSnippets(snippets.toArray(new String[snippets.size()]));

		return hits;
	}

	protected void updateFacetCollectors(
		SearchContext searchContext, SearchResponse searchResponse) {

		for (Facet searchRequestFacet : searchContext.getFacets().values()) {
			if (searchRequestFacet.isStatic()) {
				continue;
			}

			Facets searchResponseFacets = searchResponse.getFacets();

			org.elasticsearch.search.facet.Facet searchResponseFacet =
				searchResponseFacets.facet(searchRequestFacet.getFieldName());

			FacetCollector facetCollector =
				new ElasticsearchFacetFieldCollector(searchResponseFacet);

			searchRequestFacet.setFacetCollector(facetCollector);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ElasticsearchIndexSearcher.class);

}