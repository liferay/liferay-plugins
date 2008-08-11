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

import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.ParallelDestination;
import com.liferay.portal.kernel.messaging.SerialDestination;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchEngine;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;

/**
 * <a href="SolrSearchEngineUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 *
 */
public class SolrSearchEngineUtil {

	public static void addDocument(long companyId, Document doc)
		throws SearchException {

		_searchEngine.getWriter().addDocument(companyId, doc);
	}

	public static void deleteDocument(long companyId, String uid)
		throws SearchException {

		_searchEngine.getWriter().deleteDocument(companyId, uid);
	}

	public static void deletePortletDocuments(long companyId, String portletId)
		throws SearchException {

		_searchEngine.getWriter().deletePortletDocuments(companyId, portletId);
	}

	public static boolean isIndexReadOnly() {
		return _searchEngine.isIndexReadOnly();
	}

	public static Hits search(long companyId, Query query, int start, int end)
		throws SearchException {

		return _searchEngine.getSearcher().search(companyId, query, start, end);
	}

	public static Hits search(
			long companyId, Query query, Sort sort, int start, int end)
		throws SearchException {

		return _searchEngine.getSearcher().search(
			companyId, query, sort, start, end);
	}

	public static void updateDocument(
			long companyId, String uid, Document doc)
		throws SearchException {

		_searchEngine.getWriter().updateDocument(companyId, uid, doc);
	}

	public SolrSearchEngineUtil(
			SearchEngine searchEngine,
			MessageListener searchReaderMessageListener,
			MessageListener searchWriterMessageListener)
		throws Exception {

		_searchEngine = searchEngine;

		MessageBusUtil.removeDestination(DestinationNames.SEARCH_READER);

		Destination searchReaderDestination = new ParallelDestination(
			DestinationNames.SEARCH_READER);

		MessageBusUtil.addDestination(searchReaderDestination);

		searchReaderDestination.register(searchReaderMessageListener);

		MessageBusUtil.removeDestination(DestinationNames.SEARCH_WRITER);

		Destination searchWriterDestination = new SerialDestination(
			DestinationNames.SEARCH_WRITER);

		MessageBusUtil.addDestination(searchWriterDestination);

		searchWriterDestination.register(searchWriterMessageListener);
	}

	private static SearchEngine _searchEngine;

}