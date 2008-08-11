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

package com.liferay.portal.search.solr.messaging;

import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.messaging.SearchRequest;
import com.liferay.portal.search.solr.SolrSearchEngineUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="SolrWriterMessageListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 *
 */
public class SolrWriterMessageListener implements MessageListener {

	public void receive(Object message) {
		try {
			doReceive(message);
		}
		catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	public void receive(String message) {
		throw new UnsupportedOperationException();
	}

	public void doReceive(Object message) throws Exception {
		SearchRequest searchRequest = (SearchRequest)message;

		String command = searchRequest.getCommand();

		long companyId = searchRequest.getCompanyId();
		String id = searchRequest.getId();
		Document doc = searchRequest.getDocument();

		if (command.equals(SearchRequest.COMMAND_ADD)) {
			SolrSearchEngineUtil.addDocument(companyId, doc);
		}
		else if (command.equals(SearchRequest.COMMAND_DELETE)) {
			SolrSearchEngineUtil.deleteDocument(companyId, id);
		}
		else if (command.equals(SearchRequest.COMMAND_DELETE_PORTLET_DOCS)) {
			SolrSearchEngineUtil.deletePortletDocuments(companyId, id);
		}
		else if (command.equals(SearchRequest.COMMAND_UPDATE)) {
			SolrSearchEngineUtil.updateDocument(companyId, id, doc);
		}
	}

	private static Log _log =
		LogFactory.getLog(SolrWriterMessageListener.class);

}