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
import com.liferay.portal.kernel.search.HitsImpl;
import com.liferay.portal.kernel.search.SearchEngine;
import com.liferay.portal.kernel.search.Sort;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="SolrSearchEngineUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 *
 */
public class SolrSearchEngineUtil {

	public SolrSearchEngineUtil(
			SearchEngine engine, MessageListener readerMessageListener,
			MessageListener writerMessageListener,
			boolean removeExistingDestination)
		throws Exception {

		try {
			_engine = engine;

			if (removeExistingDestination) {
				MessageBusUtil.removeDestination(
					DestinationNames.SEARCH_READER);

				MessageBusUtil.removeDestination(
					DestinationNames.SEARCH_WRITER);
			}

			Destination searchReadDestination = new ParallelDestination(
				DestinationNames.SEARCH_READER);

			MessageBusUtil.addDestination(searchReadDestination);

			searchReadDestination.register(readerMessageListener);

			Destination searchWriteDestination = new SerialDestination(
				DestinationNames.SEARCH_WRITER);

			MessageBusUtil.addDestination(searchWriteDestination);

			searchWriteDestination.register(writerMessageListener);
		}
		catch (Exception e) {
			_log.error("Unable to initialize search engine.", e);

			throw e;
		}
	}

	public static void addDocument(long companyId, Document doc) {
		try {
			_engine.getWriter().addDocument(companyId, doc);
		}
		catch (Exception e) {
			_log.error("Unable to add document:" + doc, e);
		}
	}

	public static void deleteDocument(long companyId, String uid) {
		try {
			_engine.getWriter().deleteDocument(companyId, uid);
		}
		catch (Exception e) {
			_log.error("Unable to delete document: companyId=" + companyId +
				", uid=" + uid, e);
		}
	}

	public static void deletePortletDocuments(
			long companyId, String portletId) {

		try {
			_engine.getWriter().deletePortletDocuments(companyId, portletId);
		}
		catch (Exception e) {
			_log.error("Unable to delete portlet documents: companyId=" +
				companyId + ", portletId=" + portletId, e);
		}
	}

	public static boolean isIndexReadOnly() {
		return _engine.isIndexReadOnly();
	}

	public static Hits search(
			long companyId, String query, int start, int end) {

		try {
			return _engine.getSearcher().search(companyId, query, start, end);
		}
		catch (Exception e) {
			_log.error("Unable to search: companyId=" + companyId + ", query=" +
				query, e);

			return new HitsImpl();
		}
	}

	public static Hits search(
			long companyId, String query, Sort sort, int start, int end)  {

		try {
			return _engine.getSearcher().search(
				companyId, query, sort, start, end);
		}
		catch (Exception e) {
			_log.error("Unable to search: companyId=" + companyId + ", query=" +
				query + ", sort=" + sort, e);

			return new HitsImpl();
		}
	}

	public static void updateDocument(
			long companyId, String uid, Document doc) {

		try {
			_engine.getWriter().updateDocument(companyId, uid, doc);
		}
		catch (Exception e) {
			_log.error("Unable to update document:" + doc, e);
		}
	}

	private static Log _log = LogFactory.getLog(SolrSearchEngineUtil.class);

	private static SearchEngine _engine;

}