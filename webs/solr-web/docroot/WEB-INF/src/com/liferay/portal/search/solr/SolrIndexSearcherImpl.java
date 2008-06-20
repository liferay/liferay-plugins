/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.HitsImpl;
import com.liferay.portal.kernel.search.IndexSearcher;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Time;

import java.io.IOException;
import java.io.StringReader;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * <a href="SolrIndexSearcherImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 *
 */
public class SolrIndexSearcherImpl implements IndexSearcher {

	public String getServerURL() {
		return _serverURL;
	}

	public void setServerURL(String serverURL) {
		_serverURL = serverURL;
	}

	public Hits search(long companyId, String query, int start, int end)
		throws SearchException {

		Sort sort = null;

		return search(companyId, query, sort, start, end);
	}

	public Hits search(
			long companyId, String query, Sort sort, int start, int end)
		throws SearchException {

		try {
			String url = HttpUtil.addParameter(_serverURL, "q", query);

			url = HttpUtil.addParameter(url, "fl", "score");

			return subset(HttpUtil.URLtoString(url), start, end);
		}
		catch (IOException ioe) {
			_log.error("Error while sending request to Solr", ioe);

			throw new SearchException(ioe);
		}
	}

	protected Hits subset(String xml, int start, int end)
		throws SearchException {

		long startTime = System.currentTimeMillis();

		Hits subset = new HitsImpl();

		try {
			SAXReader reader = new SAXReader();

			org.dom4j.Document xmlDoc = reader.read(new StringReader(xml));

			Element root = xmlDoc.getRootElement();
			Element resultEl = root.element("result");

			int length = Integer.parseInt(resultEl.attributeValue("numFound"));

			float maxScore = Float.parseFloat(
				resultEl.attributeValue("maxScore"));

			if ((start > - 1) && (start <= end)) {
				if (end > length) {
					end = length;
				}

				int subsetTotal = end - start;

				Document[] subsetDocs = new DocumentImpl[subsetTotal];
				float[] subsetScores = new float[subsetTotal];

				List<Element> docsEl = resultEl.elements();

				int j = 0;

				for (int i = start; i < end; i++, j++) {
					Element docEl = docsEl.get(i);

					DocumentImpl doc = new DocumentImpl();

					List<Element> fields = docEl.elements();

					for (int k = 0; k < fields.size(); k++) {
						Element arrEl = fields.get(k);

						doc.add(
							new Field(
								arrEl.attributeValue("name"), arrEl.getText(),
								false));
					}

					subsetDocs[j] = doc;
					subsetScores[j] = GetterUtil.getFloat(doc.get("score")) /
						maxScore;
				}

				subset.setLength(length);
				subset.setDocs(subsetDocs);
				subset.setScores(subsetScores);
				subset.setStart(startTime);

				float searchTime =
					(float)(System.currentTimeMillis() - startTime) /
						Time.SECOND;

				subset.setSearchTime(searchTime);
			}
		}
		catch (Exception e) {
			throw new SearchException(e);
		}

		return subset;
	}

	private static Log _log = LogFactory.getLog(SolrIndexSearcherImpl.class);

	private String _serverURL;

}