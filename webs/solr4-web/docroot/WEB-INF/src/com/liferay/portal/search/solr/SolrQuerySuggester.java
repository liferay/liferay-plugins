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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseQuerySuggester;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.StringDistanceCalculatorUtil;
import com.liferay.portal.kernel.search.SuggestionConstants;
import com.liferay.portal.kernel.search.TokenizerUtil;
import com.liferay.portal.kernel.search.WeightedWord;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
public class SolrQuerySuggester extends BaseQuerySuggester {

	public void setDistanceThreshold(float distanceThreshold) {
		_distanceThreshold = distanceThreshold;
	}

	public void setNGramQueryBuilder(NGramQueryBuilder nGramQueryBuilder) {
		_nGramQueryBuilder = nGramQueryBuilder;
	}

	public void setSolrServer(SolrServer solrServer) {
		_solrServer = solrServer;
	}

	@Override
	public Map<String, List<String>> spellCheckKeywords(
			SearchContext searchContext, int maxSuggestions)
		throws SearchException {

		Map<String, List<String>> suggestions =
			new HashMap<String, List<String>>();

		String localizedFieldName = DocumentImpl.getLocalizedName(
			searchContext.getLanguageId(), Field.SPELL_CHECK_WORD);

		List<String> keywordTokens = TokenizerUtil.tokenize(
			localizedFieldName, searchContext.getKeywords(),
			searchContext.getLanguageId());

		for (String keywordToken : keywordTokens) {
			List<String> keywordSuggestions = suggestKeywords(
				searchContext, maxSuggestions, keywordToken);

			suggestions.put(keywordToken, keywordSuggestions);
		}

		return suggestions;
	}

	@Override
	public String[] suggestKeywordQueries(SearchContext searchContext, int max)
		throws SearchException {

		StringBundler sb = new StringBundler(8);

		sb.append("start");
		sb.append(searchContext.getKeywords().length());
		sb.append(StringPool.COLON);
		sb.append(StringPool.QUOTE);
		sb.append(searchContext.getKeywords());
		sb.append(StringPool.QUOTE);

		SolrQuery solrQuery = new SolrQuery();

		solrQuery.setFilterQueries(
			getFilterQueries(
				searchContext, SuggestionConstants.QUERY_SUGGESTION_TYPE));

		solrQuery.setQuery(sb.toString());
		solrQuery.setRows(max);

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

		List<String> filterQueries = new ArrayList<String>(4);

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

		return filterQueries.toArray(new String[filterQueries.size()]);
	}

	protected String getFilterQuery(String field, long value) {
		return getFilterQuery(field, String.valueOf(value));
	}

	protected String getFilterQuery(String field, long[] values) {
		StringBundler sb = new StringBundler(6 * values.length - 2);

		for (int i = 0; i < values.length; i ++) {
			sb.append(field);
			sb.append(StringPool.COLON);
			sb.append(values[i]);
			sb.append(StringPool.SPACE);

			if (i < (values.length - 1)) {
				sb.append("OR");
				sb.append(StringPool.SPACE);
			}
		}

		return sb.toString();
	}

	protected String getFilterQuery(String field, String value) {
		return field.concat(StringPool.COLON).concat(value);
	}

	protected long[] getGroupIdsForSuggestions(SearchContext searchContext) {

		// when suggestion queries, always use the current-site scope AND global
		// scope as groupIds

		long[] groupIds = searchContext.getGroupIds();

		long globalGroupId = _GLOBAL_GROUP_ID;

		// add global scope, if we're not having it included already

		if (!ArrayUtil.contains(groupIds, globalGroupId)) {
			groupIds = ArrayUtil.append(groupIds, globalGroupId);
		}

		return groupIds;
	}

	protected List<String> suggestKeywords(
			SearchContext searchContext, int maxSuggestions, String token)
		throws SearchException {

		Set<WeightedWord> suggestionSet = suggestKeywords(searchContext, token);

		maxSuggestions = Math.min(maxSuggestions, suggestionSet.size());

		Iterator<WeightedWord> suggestionsIterator = suggestionSet.iterator();

		List<String> suggestionList = new ArrayList<String>(maxSuggestions);

		int counter = 0;

		while (suggestionsIterator.hasNext()) {
			WeightedWord weightedWord = suggestionsIterator.next();

			suggestionList.add(weightedWord.getWord());

			counter++;

			if (counter >= maxSuggestions) {
				break;
			}
		}

		return suggestionList;
	}

	protected Set<WeightedWord> suggestKeywords(
			SearchContext searchContext, String token)
		throws SearchException {

		try {
			SolrQuery solrQuery = _nGramQueryBuilder.getNGramQuery(token);

			solrQuery.addFilterQuery(
				getFilterQueries(
					searchContext, SuggestionConstants.SPELL_CHECKER_TYPE));

			solrQuery.setRows(_MAX_QUERY_RESULTS);

			QueryResponse queryResponse = _solrServer.query(
				solrQuery, SolrRequest.METHOD.POST);

			SolrDocumentList solrDocumentList = queryResponse.getResults();

			int numResults = solrDocumentList.size();

			Set<WeightedWord> sortedWords = new TreeSet<WeightedWord>();

			Map<String, WeightedWord> weightedWordMap =
				new HashMap<String, WeightedWord>();

			for (int i = 0; i < numResults; i++) {
				SolrDocument solrDocument = solrDocumentList.get(i);

				String suggestion = ((List<String>)solrDocument.get(
					Field.SPELL_CHECK_WORD)).get(0);

				String strWeight = ((List<String>)solrDocument.get(
					Field.PRIORITY)).get(0);

				float weight = Float.parseFloat(strWeight);

				if (suggestion.equals(token)) {
					weight = _INFINITE_WEIGHT;
				}
				else {
					String lowerCaseToken = token.toLowerCase();
					String lowerCaseSuggestion = suggestion.toLowerCase();

					float distance = StringDistanceCalculatorUtil.getDistance(
						lowerCaseToken, lowerCaseSuggestion);

					if (distance >= _distanceThreshold) {
						weight = weight + distance;
					}
				}

				WeightedWord weightedWord = weightedWordMap.get(suggestion);

				if (weightedWord == null) {
					weightedWord = new WeightedWord(suggestion, weight);

					weightedWordMap.put(suggestion, weightedWord);

					sortedWords.add(weightedWord);
				}
				else if (weight > weightedWord.getWeight()) {
					weightedWord.setWeight(weight);
				}
			}

			return sortedWords;
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to execute Solr query", e);
			}

			throw new SearchException(e.getMessage(), e);
		}
	}

	private static final long _GLOBAL_GROUP_ID = 0l;

	private static Log _log = LogFactoryUtil.getLog(SolrQuerySuggester.class);

	private static float _INFINITE_WEIGHT = 100f;

	private static int _MAX_QUERY_RESULTS = 50;

	private float _distanceThreshold;
	private NGramQueryBuilder _nGramQueryBuilder;
	private SolrServer _solrServer;

}