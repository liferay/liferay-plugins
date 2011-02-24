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

package com.liferay.portal.search.solr;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
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
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

/**
 * @author Bruno Farache
 * @author Zsolt Berentey
 */
public class SolrIndexSearcherImpl implements IndexSearcher {

	public Hits search(
			long companyId, Query query, Sort[] sorts, int start, int end)
		throws SearchException {

		try {
			SolrQuery solrQuery = translateQuery(
				companyId, query, sorts, start, end);

			QueryResponse queryResponse = _solrServer.query(solrQuery);

			boolean allResults = false;

			if (solrQuery.getRows() == 0) {
				allResults = true;
			}

			return subset(
				solrQuery, query.getQueryConfig(), queryResponse, allResults);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new SearchException(e.getMessage());
		}
	}

	public void setSolrServer(SolrServer solrServer) {
		_solrServer = solrServer;
	}

	protected String getSnippet(
		SolrDocument solrDocument, Set<String> queryTerms,
		Map<String, Map<String, List<String>>> highlights) {

		if (Validator.isNull(highlights)) {
			return StringPool.BLANK;
		}

		String key = (String)solrDocument.getFieldValue(Field.UID);

		List<String> snippets = highlights.get(key).get(Field.CONTENT);

		String snippet = StringUtil.merge(snippets, "...");

		if (Validator.isNotNull(snippet)) {
			snippet = snippet + "...";
		}
		else {
			snippet = StringPool.BLANK;
		}

		Matcher matcher = Pattern.compile("<em>(.*?)</em>").matcher(snippet);

		while (matcher.find()) {
			queryTerms.add(matcher.group(1));
		}

		snippet = StringUtil.replace(snippet, "<em>", "");
		snippet = StringUtil.replace(snippet, "</em>", "");

		return snippet;
	}

	protected Hits subset(
			SolrQuery solrQuery, QueryConfig queryConfig,
			QueryResponse queryResponse, boolean allResults)
		throws Exception {

		long startTime = System.currentTimeMillis();

		Hits subset = new HitsImpl();

		SolrDocumentList results = queryResponse.getResults();

		long length = results.getNumFound();

		if (allResults && (length > 0)) {
			solrQuery.setRows((int)length);

			queryResponse = _solrServer.query(solrQuery);

			return subset(solrQuery, queryConfig, queryResponse, false);
		}

		float maxScore = 1f;

		if (queryConfig.isScoreEnabled()) {
			maxScore = results.getMaxScore();
		}

		int subsetTotal = results.size();

		Document[] subsetDocs = new DocumentImpl[subsetTotal];
		String[] subsetSnippets = new String[subsetTotal];
		float[] subsetScores = new float[subsetTotal];

		int j = 0;

		Map<String, Map<String, List<String>>> highlights =
			queryResponse.getHighlighting();

		Set<String> queryTerms = new HashSet<String>();

		boolean highlightEnabled = queryConfig.isHighlightEnabled();

		boolean scoringEnabled = queryConfig.isScoreEnabled();

		for (SolrDocument solrDocument : results) {
			Document document = new DocumentImpl();

			Collection<String> names = solrDocument.getFieldNames();

			for (String name : names) {
				Field field = new Field(
					name, solrDocument.getFieldValue(name).toString());

				document.add(field);
			}

			float score = 1f;

			if (scoringEnabled) {
				score = Float.valueOf(
					solrDocument.getFieldValue("score").toString());
			}


			subsetDocs[j] = document;

			if (highlightEnabled) {
				subsetSnippets[j] = getSnippet(
					solrDocument, queryTerms, highlights);
			}
			else {
				subsetSnippets[j] = StringPool.BLANK;
			}

			subsetScores[j] = score / maxScore;

			j++;
		}

		float searchTime =
			(float)(System.currentTimeMillis() - startTime) / Time.SECOND;

		subset.setDocs(subsetDocs);
		subset.setLength((int)length);
		subset.setQueryTerms(queryTerms.toArray(new String[queryTerms.size()]));
		subset.setScores(subsetScores);
		subset.setSearchTime(searchTime);
		subset.setSnippets(subsetSnippets);
		subset.setStart(startTime);

		return subset;
	}

	protected SolrQuery translateQuery(
			long companyId, Query query, Sort[] sorts, int start, int end)
		throws SearchException {

		try {
			String queryString = query.toString();

			queryString = queryString.replaceAll(":\\*", ":");
			queryString = queryString.replaceAll(":\\w+:", ":");

			StringBundler sb = new StringBundler(queryString);

			sb.append(StringPool.SPACE);
			sb.append(StringPool.PLUS);
			sb.append(Field.COMPANY_ID);
			sb.append(StringPool.COLON);
			sb.append(companyId);

			QueryConfig queryConfig = query.getQueryConfig();

			SolrQuery solrQuery = new SolrQuery();

			solrQuery.setHighlight(queryConfig.isHighlightEnabled());
			solrQuery.setHighlightFragsize(
				queryConfig.getHighlightFragmentSize());
			solrQuery.setHighlightSnippets(
				queryConfig.getHighlightSnippetSize());
			solrQuery.setIncludeScore(queryConfig.isScoreEnabled());

			solrQuery.setQuery(sb.toString());

			if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS)) {
				solrQuery.setRows(0);
			}
			else {
				solrQuery.setRows(end - start);
				solrQuery.setStart(start);
			}

			if ((sorts != null) && (sorts.length > 0)) {
				for (int i = 0; i < sorts.length; i++) {
					Sort sortField = sorts[i];

					if (sortField == null) {
						continue;
					}

					String sortFieldName = sortField.getFieldName();

					ORDER order = ORDER.asc;

					if (sortFieldName == null) {
						sortFieldName = "score";

						order = ORDER.desc;
					}
					else if (sortField.isReverse()) {
						order = ORDER.desc;
					}

					solrQuery.addSortField(sortFieldName, order);
				}
			}

			return solrQuery;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new SearchException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		SolrIndexSearcherImpl.class);

	private SolrServer _solrServer;

}