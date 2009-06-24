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

package com.liferay.portal.search.solr.server;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.common.util.NamedList;

/**
 * <a href="BroadcastWriterSolrServer.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 *
 */
public class BroadcastWriterSolrServer extends SolrServer {

	public BroadcastWriterSolrServer(SolrServerWrapper... serverWrappers) {
		_serverWrappers = serverWrappers;
	}

	public NamedList<Object> request(SolrRequest request)
		throws SolrServerException {

		NamedList<Object> response = null;

		if (!(request instanceof UpdateRequest)) {
			throw new IllegalStateException(
				"This SolrServer should be used only to update requests.");
		}

		for (SolrServerWrapper serverWrapper : _serverWrappers) {
			try {
				SolrServer server = serverWrapper.getServer();

				response = server.request(request);
			}
			catch (Exception e) {
				_log.error(
					"Could not send request to server: " +
						serverWrapper.getId());
			}
		}

		if (response == null) {
			throw new SolrServerException("No server available.");
		}

		return response;
	}

	private static Log _log =
		LogFactoryUtil.getLog(BroadcastWriterSolrServer.class);

	private SolrServerWrapper[] _serverWrappers;

}