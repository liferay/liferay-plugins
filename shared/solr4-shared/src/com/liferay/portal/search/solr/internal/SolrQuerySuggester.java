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

package com.liferay.portal.search.solr.internal;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.analysis.TokenizerUtil;
import com.liferay.portal.kernel.search.suggest.BaseQuerySuggester;
import com.liferay.portal.kernel.search.suggest.QuerySuggester;
import com.liferay.portal.kernel.search.suggest.SuggestionConstants;
import com.liferay.portal.kernel.search.suggest.WeightedWord;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.solr.suggest.NGramQueryBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.lucene.search.spell.LevensteinDistance;
import org.apache.lucene.search.spell.StringDistance;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Daniela Zapata Riesco
 * @author David Gonzalez
 * @author Michael C. Han
 */
@Component(
	immediate = true,
	property = {"distance.threshold=0.6f", "search.engine.impl=Solr"},
	service = QuerySuggester.class
)
public class SolrQuerySuggester extends BaseQuerySuggester {

	@Override
	public Map<String, List<String>> spellCheckKeywords(
			SearchContext searchContext, int max)
		throws SearchException {

		Map<String, List<String>> suggestions = new HashMap<>();

		String localizedFieldName = DocumentImpl.getLocalizedName(
			searchContext.getLanguageId(), Field.SPELL_CHECK_WORD);

		List<String> keywords = TokenizerUtil.tokenize(
			localizedFieldName, searchContext.getKeywords(),
			searchContext.getLanguageId());

		for (String keyword : keywords) {
			List<String> keywordSuggestions = suggestKeywords(
				searchContext, max, keyword);

			suggestions.put(keyword, keywordSuggestions);
		}

		return suggestions;
	}

	@Override
	public String[] suggestKeywordQueries(SearchContext searchContext, int max)
		throws SearchException {

		SolrQuery solrQuery = new SolrQuery();

		solrQuery.setFilterQueries(
			getFilterQueries(
				searchContext, SuggestionConstants.TYPE_QUERY_SUGGESTION));

		StringBundler sb = new StringBundler(6);

		sb.append("start");

		String keywords = searchContext.getKeywords();

		sb.append(keywords.length());

		sb.append(StringPool.COLON);
		sb.append(StringPool.QUOTE);
		sb.append(keywords);
		sb.append(StringPool.QUOTE);

		solrQuery.setQuery(sb.toString());

		solrQuery.setRows(max);

		try {
			QueryResponse queryResponse = _solrServer.query(solrQuery);

			SolrDocumentList solrDocumentList = queryResponse.getResults();

			String[] querySuggestions = new String[solrDocumentList.size()];

			for (int i = 0; i < solrDocumentList.size(); i++) {
				SolrDocument solrDocument = solrDocumentList.get(i);

				querySuggestions[i] = (String)solrDocument.getFieldValue(
					Field.KEYWORD_SEARCH);
			}

			return querySuggestions;
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to execute Solr query", e);
			}

			throw new SearchException(e.getMessage());
		}
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_distanceThreshold = MapUtil.getDouble(
			properties, "distance.threshold", 0.6d);
	}

	protected String[] getFilterQueries(
		SearchContext searchContext, String type) {

		List<String> filterQueries = new ArrayList<>(4);

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
		if (ArrayUtil.isEmpty(values)) {
			return StringPool.BLANK;
		}

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
		long[] groupIds = searchContext.getGroupIds();

		if (ArrayUtil.isEmpty(groupIds)) {
			return new long[] {_GLOBAL_GROUP_ID};
		}

		if (ArrayUtil.contains(groupIds, _GLOBAL_GROUP_ID)) {
			return groupIds;
		}

		return ArrayUtil.append(groupIds, _GLOBAL_GROUP_ID);
	}

	@Reference(unbind = "-")
	protected void setNGramQueryBuilder(NGramQueryBuilder nGramQueryBuilder) {
		_nGramQueryBuilder = nGramQueryBuilder;
	}

	@Reference(unbind = "-")
	protected void setSolrServer(SolrServer solrServer) {
		_solrServer = solrServer;
	}

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void setStringDistance(StringDistance stringDistance) {
		_stringDistance = stringDistance;
	}

	protected List<String> suggestKeywords(
			SearchContext searchContext, int max, String input)
		throws SearchException {

		TreeSet<WeightedWord> suggestionsSet = suggestKeywords(
			searchContext, input);

		max = Math.min(max, suggestionsSet.size());

		List<String> suggestionsList = new ArrayList<>(max);

		int count = 0;

		Iterator<WeightedWord> iterator = suggestionsSet.descendingIterator();

		while (iterator.hasNext()) {
			WeightedWord weightedWord = iterator.next();

			if (weightedWord.getWeight() != 0) {
				suggestionsList.add(weightedWord.getWord());
			}
			else {
				suggestionsList.add(input);
			}

			count++;

			if (count >= max) {
				break;
			}
		}

		return suggestionsList;
	}

	protected TreeSet<WeightedWord> suggestKeywords(
			SearchContext searchContext, String input)
		throws SearchException {

		try {
			Map<String, WeightedWord> weightedWordsMap = new HashMap<>();
			TreeSet<WeightedWord> weightedWordsSet = new TreeSet<>();

			SolrQuery solrQuery = _nGramQueryBuilder.getNGramQuery(input);

			solrQuery.addFilterQuery(
				getFilterQueries(
					searchContext, SuggestionConstants.TYPE_SPELL_CHECKER));

			solrQuery.setRows(_MAX_QUERY_RESULTS);

			QueryResponse queryResponse = _solrServer.query(
				solrQuery, SolrRequest.METHOD.POST);

			SolrDocumentList solrDocumentList = queryResponse.getResults();

			for (int i = 0; i < solrDocumentList.size(); i++) {
				SolrDocument solrDocument = solrDocumentList.get(i);

				List<String> suggestions = (List<String>)solrDocument.get(
					Field.SPELL_CHECK_WORD);

				String suggestion = suggestions.get(0);

				List<String> weights = (List<String>)solrDocument.get(
					Field.PRIORITY);

				float weight = GetterUtil.getFloat(weights.get(0));

				if (suggestion.equals(input)) {
					weight = _INFINITE_WEIGHT;
				}
				else {
					String inputLowerCase = StringUtil.toLowerCase(input);
					String suggestionLowerCase = StringUtil.toLowerCase(
						suggestion);

					float distance = _stringDistance.getDistance(
						inputLowerCase, suggestionLowerCase);

					if (distance >= _distanceThreshold) {
						weight = weight + distance;
					}
				}

				WeightedWord weightedWord = weightedWordsMap.get(suggestion);

				if (weightedWord == null) {
					weightedWord = new WeightedWord(suggestion, weight);

					weightedWordsMap.put(suggestion, weightedWord);

					weightedWordsSet.add(weightedWord);
				}
				else if (weight > weightedWord.getWeight()) {
					weightedWord.setWeight(weight);
				}
			}

			return weightedWordsSet;
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to execute Solr query", e);
			}

			throw new SearchException(e.getMessage(), e);
		}
	}

	protected void unsetStringDistance(StringDistance stringDistance) {
		_stringDistance = new LevensteinDistance();
	}

	private static final long _GLOBAL_GROUP_ID = 0;

	private static final float _INFINITE_WEIGHT = 100f;

	private static final int _MAX_QUERY_RESULTS = 500;

	private static Log _log = LogFactoryUtil.getLog(SolrQuerySuggester.class);

	private double _distanceThreshold;
	private NGramQueryBuilder _nGramQueryBuilder;
	private SolrServer _solrServer;
	private StringDistance _stringDistance = new LevensteinDistance();

}