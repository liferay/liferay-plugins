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

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.NGramHolder;
import com.liferay.portal.kernel.search.NGramHolderBuilderUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;

/**
 * @author Michael C. Han
 */
public class NGramQueryBuilderImpl implements NGramQueryBuilder {

	public SolrQuery getNGramQuery(String token) throws SearchException {

		NGramHolder nGramHolder = NGramHolderBuilderUtil.buildNGramHolder(
			token);

		Map<String, List<String>> nGrams = nGramHolder.getNGrams();
		Map<String, String> nGramEnds = nGramHolder.getNGramEnds();
		Map<String, String> nGramStarts = nGramHolder.getNGramStarts();

		StringBundler stringBundler = new StringBundler(10);

		addNGramsListQuery(stringBundler, nGrams);

		if (!nGrams.isEmpty()) {
			addOrQuerySeparator(stringBundler);
		}

		addNGramsQuery(stringBundler, nGramEnds);

		if (!nGramEnds.isEmpty()) {
			addOrQuerySeparator(stringBundler);
		}

		addNGramsQuery(stringBundler, nGramStarts);

		if (!nGramStarts.isEmpty()) {
			addOrQuerySeparator(stringBundler);
		}

		stringBundler.append(Field.SPELL_CHECK_WORD);
		stringBundler.append(StringPool.COLON);
		stringBundler.append(token);

		SolrQuery solrQuery = new SolrQuery();

		solrQuery.setQuery(stringBundler.toString());

		return solrQuery;
	}

	protected void addNGramsListQuery(
		StringBundler stringBundler, Map<String, List<String>> nGrams) {

		Iterator<Map.Entry<String, List<String>>> nGramEntriesIterator =
			nGrams.entrySet().iterator();

		while (nGramEntriesIterator.hasNext()) {
			Map.Entry<String, List<String>> nGramEntries =
				nGramEntriesIterator.next();

			String fieldName = nGramEntries.getKey();

			Iterator<String> nGramValuesIterator =
				nGramEntries.getValue().iterator();

			while (nGramValuesIterator.hasNext()) {
				addQuery(stringBundler, fieldName, nGramValuesIterator.next());

				if (nGramValuesIterator.hasNext() ||
					nGramEntriesIterator.hasNext()) {
						addOrQuerySeparator(stringBundler);
				}
			}
		}
	}

	protected void addNGramsQuery(
		StringBundler stringBundler, Map<String, String> nGrams) {

		Iterator<Map.Entry<String, String>> nGramEntriesIterator =
			nGrams.entrySet().iterator();

		while (nGramEntriesIterator.hasNext()) {
			Map.Entry<String, String> nGramEntry = nGramEntriesIterator.next();

			String fieldName = nGramEntry.getKey();

			String fieldValue = nGramEntry.getValue();

			addQuery(stringBundler, fieldName, fieldValue);

			if (nGramEntriesIterator.hasNext()) {
				addOrQuerySeparator(stringBundler);
			}
		}
	}

	protected void addOrQuerySeparator(StringBundler stringBundler) {
		stringBundler.append(StringPool.SPACE);
		stringBundler.append("OR");
		stringBundler.append(StringPool.SPACE);
	}

	protected void addQuery(
		StringBundler stringBundler, String fieldName, String fieldValue) {

		stringBundler.append(fieldName);
		stringBundler.append(StringPool.COLON);
		stringBundler.append(fieldValue);
	}

}