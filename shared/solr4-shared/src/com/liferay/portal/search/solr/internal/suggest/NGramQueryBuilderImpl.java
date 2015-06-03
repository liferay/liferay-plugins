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

package com.liferay.portal.search.solr.internal.suggest;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.suggest.NGramHolder;
import com.liferay.portal.kernel.search.suggest.NGramHolderBuilder;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.search.solr.suggest.NGramQueryBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.solr.client.solrj.SolrQuery;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = NGramQueryBuilder.class)
public class NGramQueryBuilderImpl implements NGramQueryBuilder {

	@Override
	public SolrQuery getNGramQuery(String input) throws SearchException {
		SolrQuery solrQuery = new SolrQuery();

		if (_nGramHolderBuilder == null) {
			return solrQuery;
		}

		StringBundler sb = new StringBundler(10);

		NGramHolder nGramHolder = _nGramHolderBuilder.buildNGramHolder(input);

		Map<String, List<String>> nGrams = nGramHolder.getNGrams();

		addNGramsListQuery(sb, nGrams);

		if (!nGrams.isEmpty()) {
			addOrQuerySeparator(sb);
		}

		Map<String, String> nGramEnds = nGramHolder.getNGramEnds();

		addNGramsQuery(sb, nGramEnds);

		if (!nGramEnds.isEmpty()) {
			addOrQuerySeparator(sb);
		}

		Map<String, String> nGramStarts = nGramHolder.getNGramStarts();

		addNGramsQuery(sb, nGramStarts);

		if (!nGramStarts.isEmpty()) {
			addOrQuerySeparator(sb);
		}

		sb.append(Field.SPELL_CHECK_WORD);
		sb.append(StringPool.COLON);
		sb.append(input);

		solrQuery.setQuery(sb.toString());

		return solrQuery;
	}

	protected void addNGramsListQuery(
		StringBundler sb, Map<String, List<String>> nGrams) {

		Set<Map.Entry<String, List<String>>> set = nGrams.entrySet();

		Iterator<Map.Entry<String, List<String>>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, List<String>> entry = iterator.next();

			String fieldName = entry.getKey();
			List<String> fieldValues = entry.getValue();

			Iterator<String> fieldValuesIterator = fieldValues.iterator();

			while (fieldValuesIterator.hasNext()) {
				addQuery(sb, fieldName, fieldValuesIterator.next());

				if (fieldValuesIterator.hasNext() || iterator.hasNext()) {
					addOrQuerySeparator(sb);
				}
			}
		}
	}

	protected void addNGramsQuery(
		StringBundler sb, Map<String, String> nGrams) {

		Set<Map.Entry<String, String>> set = nGrams.entrySet();

		Iterator<Map.Entry<String, String>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();

			String fieldName = entry.getKey();
			String fieldValue = entry.getValue();

			addQuery(sb, fieldName, fieldValue);

			if (iterator.hasNext()) {
				addOrQuerySeparator(sb);
			}
		}
	}

	protected void addOrQuerySeparator(StringBundler sb) {
		sb.append(_OR_QUERY_SEPARATOR);
	}

	protected void addQuery(
		StringBundler sb, String fieldName, String fieldValue) {

		sb.append(fieldName);
		sb.append(StringPool.COLON);
		sb.append(fieldValue);
	}

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void setNGramHolderBuilder(
		NGramHolderBuilder nGramHolderBuilder) {

		_nGramHolderBuilder = nGramHolderBuilder;
	}

	protected void unsetNGramHolderBuilder(
		NGramHolderBuilder nGramHolderBuilder) {

		_nGramHolderBuilder = null;
	}

	private static final String _OR_QUERY_SEPARATOR = " OR ";

	private NGramHolderBuilder _nGramHolderBuilder;

}