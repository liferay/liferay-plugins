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

package com.liferay.portal.search.solr.suggest;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.CollatorUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.QuerySuggester;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.TokenizerUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.lucene.search.spell.StringDistance;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

/**
* @author Daniela Zapata Riesco
* @author David Gonzalez
* @author Michael C. Han
*/
public class SolrQuerySuggester implements QuerySuggester {

	public static float INFINITE_WEIGHT = 100f;

	public static int QUERY_RESULTS = 50;

	public void setNGramQueryBuilder(NGramQueryBuilder nGramQueryBuilder) {
		_nGramQueryBuilder = nGramQueryBuilder;
	}

	public void setSolrServer(SolrServer solrServer) {
		_solrServer = solrServer;
	}

	public void setStringDistance(StringDistance stringDistance) {
		_stringDistance = stringDistance;
	}

	public void setThreshold(float threshold) {
		_threshold = threshold;
	}

	@Override
	public String spellCheckKeywords(SearchContext searchContext)
		throws SearchException {

		Map<String, List<String>> mapSuggestions = spellCheckKeywords(
			searchContext, 1);

		List<String> tokens = TokenizerUtil.tokenize(
			Field.SPELL_CHECK_WORD, searchContext.getKeywords(),
			searchContext.getLanguageId());

		return CollatorUtil.collate(mapSuggestions, tokens);
	}

	@Override
	public Map<String, List<String>> spellCheckKeywords(
			SearchContext searchContext, int maxSuggestions)
		throws SearchException {

		Map<String, List<String>> suggestions =
			new HashMap<String, List<String>>();

		List<String> originals = TokenizerUtil.tokenize(
			Field.SPELL_CHECK_WORD, searchContext.getKeywords(),
			searchContext.getLanguageId());

		for (String original : originals) {
			List<String> similarTokens = suggestTokenSimilars(
				searchContext, maxSuggestions, original);

			suggestions.put(original, similarTokens);
		}

		return suggestions;
	}

	@Override
	public String[] suggestKeywordQueries(SearchContext searchContext, int max)
		throws SearchException {

		SolrQuery solrQuery = new SolrQuery();

		StringBundler sb = new StringBundler(6);
		sb.append("start");
		sb.append(searchContext.getKeywords().length());
		sb.append(StringPool.COLON);
		sb.append(StringPool.QUOTE);
		sb.append(searchContext.getKeywords());
		sb.append(StringPool.QUOTE);

		solrQuery.setQuery(sb.toString());
		solrQuery.setRows(max);
		solrQuery.setFilterQueries(
			getFilterQueries(
				searchContext, QUERY_SUGGESTION_TYPE));

		try {
			QueryResponse queryResponse = _solrServer.query(solrQuery);

			SolrDocumentList solrDocumentList = queryResponse.getResults();

			int numDocuments = solrDocumentList.size();

			String[] results = new String[numDocuments];

			for (int i = 0; i < numDocuments; i++) {
				SolrDocument solrDocument = solrDocumentList.get(i);
				results[i] = (String)solrDocument.getFieldValue(
					Field.KEYWORD_SEARCH);
			}

			return results;
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to execute Solr query", e);
			}

			throw new SearchException(e.getMessage());
		}
	}

	protected String[] getFilterQueries(
		SearchContext searchContext, String type) {

		List<String> filterQueries = new ArrayList<String>();

		String companyIdFilterQuery = getFilterQuery(
			Field.COMPANY_ID, searchContext.getCompanyId());
		filterQueries.add(companyIdFilterQuery);

		long[] groupIds = getGroupIdsForSuggestions(searchContext);

		if (Validator.isNotNull(groupIds)) {
			String groupIdFilterQuery = getFilterQuery(
				Field.GROUP_ID, groupIds);

			filterQueries.add(groupIdFilterQuery);
		}

		String localeFilterQuery = getFilterQuery(
			Field.LANGUAGE_ID, searchContext.getLanguageId());
		filterQueries.add(localeFilterQuery);

		String suggestionFilterQuery = getFilterQuery(Field.TYPE, type);

		filterQueries.add(suggestionFilterQuery);

		String[] arrayFilterQueries = new String[filterQueries.size()];

		return filterQueries.toArray(arrayFilterQueries);
	}

	protected String getFilterQuery(String field, long value) {
		return getFilterQuery(field, String.valueOf(value));
	}

	protected String getFilterQuery(String field, long[] values) {
		StringBundler stringBundler = new StringBundler(6 * values.length - 2);

		for (int i = 0; i < values.length - 1; i ++) {
			stringBundler.append(field);
			stringBundler.append(StringPool.COLON);
			stringBundler.append(values[i]);
			stringBundler.append(StringPool.SPACE);
			stringBundler.append("OR");
			stringBundler.append(StringPool.SPACE);
		}

		stringBundler.append(field);
		stringBundler.append(StringPool.COLON);
		stringBundler.append(values[values.length - 1]);
		stringBundler.append(StringPool.SPACE);

		return stringBundler.toString();
	}

	protected String getFilterQuery(String field, String value) {
		return field.concat(StringPool.COLON).concat(value);
	}

	protected long[] getGroupIdsForSuggestions(SearchContext searchContext) {

		// when suggestion queries, always use the current-site scope AND global
		// scope as groupIds

		long[] groupIds = searchContext.getGroupIds();

		long globalGroupId = SolrSpellCheckIndexWriter.GLOBAL_GROUP_ID;

		// add global scope, if we're not having it included already

		if (!ArrayUtil.contains(groupIds, globalGroupId)) {
			groupIds = ArrayUtil.append(groupIds, globalGroupId);
		}

		return groupIds;
	}

	protected void searchTokenSimilars(
			SearchContext searchContext, String token, SolrQuery solrQuery,
			Map<String, Float> words)
		throws SearchException {

		solrQuery.addFilterQuery(
			getFilterQueries(
				searchContext, SPELL_CHECKER_TYPE));

		try {
			solrQuery.setRows(QUERY_RESULTS);

			QueryResponse queryResponse = _solrServer.query(
				solrQuery, SolrRequest.METHOD.POST);

			SolrDocumentList solrDocumentList = queryResponse.getResults();

			int numResults = solrDocumentList.size();

			for (int i = 0; i < numResults; i++) {
				SolrDocument solrDocument = solrDocumentList.get(i);

				String suggestion = ((List<String>)solrDocument.get(
					Field.SPELL_CHECK_WORD)).get(0);

				String strWeight = ((List<String>)solrDocument.get(
					Field.SPELL_CHECK_WORD)).get(0);

				float weight = Float.parseFloat(strWeight);

				if (suggestion.equals(token)) {
					weight = INFINITE_WEIGHT;
					words.put(token, weight);
					continue;
				}

				String lowerCaseToken = token.toLowerCase();
				String lowerCaseSuggestion = suggestion.toLowerCase();

				float distance = _stringDistance.getDistance(
					lowerCaseToken, lowerCaseSuggestion);

				if (distance >= _threshold) {
					float normalizedWeight = weight + distance;

					if (words.containsKey(suggestion)) {
						float existingWeight = words.get(suggestion);

						if (normalizedWeight < existingWeight) {
							continue;
						}
					}

					words.put(suggestion, normalizedWeight);
				}
			}
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to execute Solr query", e);
			}

			throw new SearchException(e.getMessage());
		}
	}

	protected List<String> suggestTokenSimilars(
			SearchContext searchContext, int maxSuggestions, String token)
		throws SearchException {

		SolrQuery solrQuery = _nGramQueryBuilder.getNGramQuery(token);

		Map<String, Float> words = new HashMap<String, Float>();

		searchTokenSimilars(searchContext, token, solrQuery, words);

		MapValueComparator valueComparator = new MapValueComparator(words);
		TreeMap<String, Float> sortedWords = new TreeMap<String, Float>(
			valueComparator);
		sortedWords.putAll(words);

		List<String> listWords = new ArrayList(sortedWords.keySet());

		return listWords.subList(0, Math.min(maxSuggestions, listWords.size()));
	}

	protected static final String QUERY_SUGGESTION_TYPE = "querySuggestion";

	protected static final String SPELL_CHECKER_TYPE = "spellChecker";

	private static Log _log = LogFactoryUtil.getLog(SolrQuerySuggester.class);

	private NGramQueryBuilder _nGramQueryBuilder;
	private SolrServer _solrServer;
	private StringDistance _stringDistance;
	private float _threshold;

}