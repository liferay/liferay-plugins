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
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.HitsImpl;
import com.liferay.portal.kernel.search.SearchEngine;
import com.liferay.portal.kernel.search.Sort;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jabsorb.JSONSerializer;
import org.jabsorb.serializer.MarshallException;
import org.jabsorb.serializer.UnmarshallException;

/**
 * <a href="SolrSearchEngineUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 *
 */
public class SolrSearchEngineUtil {

	public SolrSearchEngineUtil(
			SearchEngine engine, MessageListener messageListener,
			boolean removeExistingDestination)
		throws Exception {

		try {
			_engine = engine;

			_serializer = new JSONSerializer();

			_serializer.registerDefaultSerializers();

			if (removeExistingDestination) {
				MessageBusUtil.removeDestination(DestinationNames.SEARCH);
			}

			Destination destination = new ParallelDestination(
				DestinationNames.SEARCH);

			MessageBusUtil.addDestination(destination);

			destination.register(messageListener);
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

	public static Object deserialize(String json) {
		try {
			return _serializer.fromJSON(json);
		}
		catch (UnmarshallException ue) {
			 _log.error(ue, ue);

			throw new IllegalStateException("Unable to deserialize oject", ue);
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

	public static String serialize(Object obj) {
		try {
			return _serializer.toJSON(obj);
		}
		catch (MarshallException me) {
			_log.error(me, me);

			throw new IllegalStateException("Unable to serialize oject", me);
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
	private static JSONSerializer _serializer;

}