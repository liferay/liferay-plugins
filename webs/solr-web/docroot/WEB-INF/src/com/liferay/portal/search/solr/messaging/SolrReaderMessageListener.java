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

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.messaging.SearchRequest;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.solr.SolrSearchEngineUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="SolrReaderMessageListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 *
 */
public class SolrReaderMessageListener implements MessageListener {

	public void receive(Object message) {
		try {
			doReceive((Message)message);
		}
		catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	public void receive(String message) {
		throw new UnsupportedOperationException();
	}

	public void doReceive(Message message) throws Exception {
		String responseDestination = message.getResponseDestination();
		String responseId = message.getResponseId();

		SearchRequest searchRequest = (SearchRequest)message.getRequestValue();

		String command = searchRequest.getCommand();

		if (command.equals(SearchRequest.COMMAND_INDEX_ONLY) &&
			Validator.isNotNull(responseDestination) &&
			Validator.isNotNull(responseId)) {

			doCommandIndexOnly(message);
		}
		else if (command.equals(SearchRequest.COMMAND_SEARCH) &&
				 Validator.isNotNull(responseDestination) &&
				 Validator.isNotNull(responseId)) {

			doCommandSearch(message, searchRequest);
		}
	}

	protected void doCommandIndexOnly(Message message)
		throws Exception {

		boolean indexOnly = SolrSearchEngineUtil.isIndexReadOnly();

		message.setResponseValue(Boolean.valueOf(indexOnly));

		MessageBusUtil.sendMessage(message.getResponseDestination(), message);
	}

	protected void doCommandSearch(Message message, SearchRequest searchRequest)
		throws Exception {

		Hits hits = SolrSearchEngineUtil.search(
			searchRequest.getCompanyId(), searchRequest.getQuery(),
			searchRequest.getSort(), searchRequest.getStart(),
			searchRequest.getEnd());

		message.setResponseValue(hits);

		MessageBusUtil.sendMessage(message.getResponseDestination(), message);
	}

	private static Log _log =
		LogFactory.getLog(SolrReaderMessageListener.class);

}