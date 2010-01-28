/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
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
 * <a href="SolrIndexSearcherImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 * @author Zsolt Berentey
 *
 */
public class SolrIndexSearcherImpl implements IndexSearcher {

	public Hits search(
			long companyId, Query query, Sort[] sorts, int start, int end)
		throws SearchException {

		try {
			SolrQuery solrQuery = _translateQuery(query, sorts, start, end);

			QueryResponse response = _solrServer.query(solrQuery);

			boolean allResults = false;

			if (solrQuery.getRows() == 0) {
				allResults = true;
			}

			Hits subset = subset(solrQuery, response, allResults);

			return subset;
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
		SolrDocument doc, Set<String> queryTerms,
		Map<String, Map<String, List<String>>> highlights) {

		if (Validator.isNull(highlights)) {
			return StringPool.BLANK;
		}

		String key = (String) doc.getFieldValue(Field.UID);

		List<String> snippets = highlights.get(key).get(Field.CONTENT);

		String snippet = StringUtil.merge(snippets, "...");

		if (Validator.isNotNull(snippet)) {
			snippet = snippet + "...";
		}
		else {
			snippet = StringPool.BLANK;
		}

		Matcher m = Pattern.compile("<em>(.*?)</em>").matcher(snippet);

		while (m.find()) {
			queryTerms.add(m.group(1));
		}

		snippet = StringUtil.replace(snippet, "<em>", "");
		snippet = StringUtil.replace(snippet, "</em>", "");

		return snippet;
	}

	protected Hits subset(
			SolrQuery solrQuery, QueryResponse response, boolean allResults)
		throws Exception {

		long startTime = System.currentTimeMillis();

		Hits subset = new HitsImpl();

		SolrDocumentList results = response.getResults();

		long length = results.getNumFound();

		if (allResults && (length > 0)) {
			solrQuery.setRows((int)length);

			results = _solrServer.query(solrQuery).getResults();

			return subset(solrQuery, response, false);
		}

		float maxScore = results.getMaxScore();

		int subsetTotal = results.size();

		Document[] subsetDocs = new DocumentImpl[subsetTotal];
		String[] subsetSnippets = new String[subsetTotal];
		float[] subsetScores = new float[subsetTotal];

		int j = 0;

		Map<String, Map<String, List<String>>> highlights =
			response.getHighlighting();

		Set<String> queryTerms = new HashSet<String>();

		for (SolrDocument solrDocument : results) {
			Document doc = new DocumentImpl();

			Collection<String> names = solrDocument.getFieldNames();

			for (String name : names) {
				Field field = new Field(
					name, solrDocument.getFieldValue(name).toString(), false);

				doc.add(field);
			}

			float score = Float.valueOf(
				solrDocument.getFieldValue("score").toString());

			subsetDocs[j] = doc;

			subsetSnippets[j] = getSnippet(
				solrDocument, queryTerms, highlights);

			subsetScores[j] = score / maxScore;

			j++;
		}

		subset.setLength((int)length);
		subset.setDocs(subsetDocs);
		subset.setScores(subsetScores);
		subset.setSnippets(subsetSnippets);
		subset.setStart(startTime);
		subset.setQueryTerms(queryTerms.toArray(new String[queryTerms.size()]));

		float searchTime =
			(float)(System.currentTimeMillis() - startTime) / Time.SECOND;

		subset.setSearchTime(searchTime);

		return subset;
	}

	private SolrQuery _translateQuery(
			Query query, Sort[] sorts, int start, int end)
		throws SearchException {

		try {
			SolrQuery solrQuery = new SolrQuery();

			String queryString = query.toString();

			queryString = queryString.replaceAll(":\\*", ":");

			solrQuery.setQuery(queryString);

			solrQuery.setIncludeScore(true);

			if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS)) {
				solrQuery.setRows(0);
			}
			else {
				solrQuery.setStart(start);
				solrQuery.setRows(end - start);
			}

			if ((sorts != null) && (sorts.length > 0)) {
				for (int i = 0; i < sorts.length; i++) {
					Sort sortField = sorts[i];

					if (sortField == null) {
						continue;
					}

					String sortFieldName = sortField.getFieldName();

					if (sortFieldName == null) {
						sortFieldName = "score";
					}

					ORDER order = ORDER.asc;

					if (sortField.isReverse()) {
						order = ORDER.desc;
					}

					solrQuery.addSortField(sortFieldName, order);
				}
			}

			solrQuery.setHighlightFragsize(80);
			solrQuery.setHighlightSnippets(3);
			solrQuery.setHighlight(true);

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