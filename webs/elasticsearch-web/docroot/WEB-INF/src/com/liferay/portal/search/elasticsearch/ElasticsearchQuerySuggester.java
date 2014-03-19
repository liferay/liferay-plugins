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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseQuerySuggester;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.search.elasticsearch.connection.ElasticsearchConnectionManager;
import com.liferay.portal.search.elasticsearch.util.DocumentTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.StopWatch;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.phrase.PhraseSuggestionBuilder;
import org.elasticsearch.search.suggest.term.TermSuggestionBuilder;

/**
 * @author Michael C. Han
 */
public class ElasticsearchQuerySuggester extends BaseQuerySuggester {

	@Override
	public Map<String, List<String>> spellCheckKeywords(
		SearchContext searchContext, int max) {

		StopWatch stopWatch = null;

		if (_log.isInfoEnabled()) {
			stopWatch = new StopWatch();

			stopWatch.start();
		}

		SuggestBuilder.SuggestionBuilder<TermSuggestionBuilder>
			suggestionBuilder = SuggestBuilder.termSuggestion(
				_REQUEST_TYPE_SPELL_CHECK);

		Suggest.Suggestion<?> suggestion = getSuggestion(
			searchContext, suggestionBuilder, DocumentTypes.SPELL_CHECK,
			Field.SPELL_CHECK_WORD, _REQUEST_TYPE_SPELL_CHECK, max);

		Map<String, List<String>> suggestionsMap =
			new HashMap<String, List<String>>();

		for (Object entry : suggestion.getEntries()) {
			Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>
				suggestionEntry =
					(Suggest.Suggestion.Entry
						<? extends Suggest.Suggestion.Entry.Option>)entry;

			String text = String.valueOf(suggestionEntry.getText());

			List<String> suggestionsList = suggestionsMap.get(text);

			if (suggestionsList == null) {
				suggestionsList = new ArrayList<String>();

				suggestionsMap.put(text, suggestionsList);
			}

			for (Suggest.Suggestion.Entry.Option option :
					suggestionEntry.getOptions()) {

				String optionText = String.valueOf(option.getText());

				suggestionsList.add(optionText);
			}
		}

		if (_log.isInfoEnabled()) {
			stopWatch.stop();

			_log.info(
				"Spell checked keywords in " + stopWatch.getTime() + "ms");
		}

		return suggestionsMap;
	}

	@Override
	public String[] suggestKeywordQueries(
		SearchContext searchContext, int max) {

		StopWatch stopWatch = null;

		if (_log.isInfoEnabled()) {
			stopWatch = new StopWatch();

			stopWatch.start();
		}

		SuggestBuilder.SuggestionBuilder<PhraseSuggestionBuilder>
			suggestionBuilder = SuggestBuilder.phraseSuggestion(
				_REQUEST_TYPE_KEYWORD_QUERY);

		Suggest.Suggestion<?> suggestion = getSuggestion(
			searchContext, suggestionBuilder, DocumentTypes.KEYWORD_QUERY,
			Field.KEYWORD_SEARCH, _REQUEST_TYPE_KEYWORD_QUERY, max);

		List<?> entries = suggestion.getEntries();

		Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>
			suggestionEntry =
				(Suggest.Suggestion.Entry
					<? extends Suggest.Suggestion.Entry.Option>)entries.get(0);

		List<String> keywordQueries = new ArrayList<String>();

		for (Suggest.Suggestion.Entry.Option option :
				suggestionEntry.getOptions()) {

			String optionText = String.valueOf(option.getText());

			keywordQueries.add(optionText);
		}

		if (_log.isInfoEnabled()) {
			stopWatch.stop();

			_log.info(
				"Suggested keyword queries in " + stopWatch.getTime() + "ms");
		}

		return keywordQueries.toArray(new String[keywordQueries.size()]);
	}

	protected Client getClient() {
		ElasticsearchConnectionManager elasticsearchConnectionManager =
			ElasticsearchConnectionManager.getInstance();

		return elasticsearchConnectionManager.getClient();
	}

	protected Suggest.Suggestion<?> getSuggestion(
		SearchContext searchContext,
		SuggestBuilder.SuggestionBuilder<?> suggestionBuilder,
		String documentType, String fieldName, String requestType, int max) {

		Client client = getClient();

		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(
			String.valueOf(searchContext.getCompanyId()));

		String localizedFieldName = DocumentImpl.getLocalizedName(
			searchContext.getLocale(), fieldName);

		suggestionBuilder.field(localizedFieldName);
		suggestionBuilder.size(max);
		suggestionBuilder.text(searchContext.getKeywords());

		searchRequestBuilder.addSuggestion(suggestionBuilder);

		searchRequestBuilder.setQuery(QueryBuilders.matchAllQuery());
		searchRequestBuilder.setTypes(documentType);

		SearchRequest searchRequest = searchRequestBuilder.request();

		ActionFuture<SearchResponse> future = client.search(searchRequest);

		SearchResponse searchResponse = future.actionGet();

		Suggest suggest = searchResponse.getSuggest();

		return suggest.getSuggestion(requestType);
	}

	private static final String _REQUEST_TYPE_KEYWORD_QUERY =
		"keywordQueryRequest";

	private static final String _REQUEST_TYPE_SPELL_CHECK = "spellCheckRequest";

	private static Log _log = LogFactoryUtil.getLog(
		ElasticsearchQuerySuggester.class);

}