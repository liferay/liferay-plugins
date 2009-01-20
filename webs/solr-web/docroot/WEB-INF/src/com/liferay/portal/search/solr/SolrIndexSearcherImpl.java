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
import com.liferay.portal.kernel.util.Time;

import java.util.Collection;

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
 *
 */
public class SolrIndexSearcherImpl implements IndexSearcher {

	public Hits search(
			long companyId, Query query, Sort[] sorts, int start, int end)
		throws SearchException {

		try {
			SolrQuery solrQuery = new SolrQuery();

			solrQuery.setQuery(query.toString());
			solrQuery.setIncludeScore(true);

			boolean allResults = false;

			if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS)) {
				solrQuery.setRows(0);

				allResults = true;
			}
			else {
				solrQuery.setStart(start);
				solrQuery.setRows(end - start);
			}

			if ((sorts != null) && (sorts.length > 0)) {
				for (int i = 0; i < sorts.length; i++) {
					Sort sortField = sorts[i];

					ORDER order = ORDER.asc;

					if (sortField.isReverse()) {
						order = ORDER.desc;
					}

					solrQuery.addSortField(sortField.getFieldName(), order);
				}
			}

			QueryResponse response = _solrServer.query(solrQuery);

			return subset(solrQuery, response.getResults(), allResults);
		}
		catch (Exception e) {
			_log.error("Error while sending request to Solr", e);

			throw new SearchException(e);
		}
	}

	public void setSolrServer(SolrServer solrServer) {
		_solrServer = solrServer;
	}

	protected Hits subset(
			SolrQuery solrQuery, SolrDocumentList results, boolean allResults)
		throws Exception {

		long startTime = System.currentTimeMillis();

		Hits subset = new HitsImpl();

		long length = results.getNumFound();

		if (allResults && (length > 0)) {
			solrQuery.setRows((int)length);

			results = _solrServer.query(solrQuery).getResults();

			return subset(solrQuery, results, false);
		}

		float maxScore = results.getMaxScore();

		int subsetTotal = results.size();

		Document[] subsetDocs = new DocumentImpl[subsetTotal];
		float[] subsetScores = new float[subsetTotal];

		int j = 0;

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
			subsetScores[j] = score / maxScore;

			j++;
		}

		subset.setLength((int)length);
		subset.setDocs(subsetDocs);
		subset.setScores(subsetScores);
		subset.setStart(startTime);

		float searchTime =
			(float)(System.currentTimeMillis() - startTime) / Time.SECOND;

		subset.setSearchTime(searchTime);

		return subset;
	}

	private static Log _log =
		 LogFactoryUtil.getLog(SolrIndexSearcherImpl.class);

	private SolrServer _solrServer;

}