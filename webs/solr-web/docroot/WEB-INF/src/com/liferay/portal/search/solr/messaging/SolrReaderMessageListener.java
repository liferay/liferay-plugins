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

package com.liferay.portal.search.solr.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.messaging.SearchRequest;
import com.liferay.portal.search.solr.SolrSearchEngineUtil;

/**
 * <a href="SolrReaderMessageListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 *
 */
public class SolrReaderMessageListener implements MessageListener {

	public void receive(Message message) {
		try {
			doReceive(message);
		}
		catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	public void doReceive(Message message) throws Exception {
		Object payload = message.getPayload();

		if (!SolrSearchEngineUtil.isRegistered() ||
			!(payload instanceof SearchRequest)) {

			return;
		}

		SearchRequest searchRequest = (SearchRequest)payload;

		String command = searchRequest.getCommand();

		if (command.equals(SearchRequest.COMMAND_INDEX_ONLY)) {
			doCommandIndexOnly(message);
		}
		else if (command.equals(SearchRequest.COMMAND_SEARCH)) {
			doCommandSearch(message, searchRequest);
		}
	}

	protected void doCommandIndexOnly(Message message)
		throws Exception {

		Boolean indexReadOnly = Boolean.valueOf(
			SolrSearchEngineUtil.isIndexReadOnly());

		message.setPayload(indexReadOnly);

		MessageBusUtil.sendMessage(message.getResponseDestination(), message);
	}

	protected void doCommandSearch(Message message, SearchRequest searchRequest)
		throws Exception {

		Hits hits = SolrSearchEngineUtil.search(
			searchRequest.getCompanyId(), searchRequest.getQuery(),
			searchRequest.getSorts(), searchRequest.getStart(),
			searchRequest.getEnd());

		message.setPayload(hits);

		MessageBusUtil.sendMessage(message.getResponseDestination(), message);
	}

	private static Log _log =
		LogFactoryUtil.getLog(SolrReaderMessageListener.class);

}