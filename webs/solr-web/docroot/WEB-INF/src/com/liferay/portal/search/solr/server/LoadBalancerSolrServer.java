/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

import java.io.IOException;

import java.util.List;

import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrException;
import org.apache.solr.common.util.NamedList;

/**
 * <a href="LoadBalancerSolrServer.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 *
 */
public class LoadBalancerSolrServer extends SolrServer {

	public LoadBalancerSolrServer(SolrServerFactory solrServerFactory) {
		_solrServerFactory = solrServerFactory;
	}

	public NamedList<Object> request(SolrRequest solrRequest)
		throws SolrServerException {

		List<SolrServerWrapper> solrServerWrappers =
			_solrServerFactory.getLiveServers();

		for (SolrServerWrapper solrServerWrapper : solrServerWrappers) {
			SolrServer solrServer = _solrServerFactory.getLiveServer(
				solrServerWrapper);

			if (solrServer == null) {
				continue;
			}

			try {
				return solrServer.request(solrRequest);
			}
			catch (SolrException se) {
				throw se;
			}
			catch (SolrServerException sse) {
				if (sse.getRootCause() instanceof IOException) {
					_solrServerFactory.killServer(solrServerWrapper);
				}
				else {
					throw sse;
				}
			}
			catch (Exception e) {
				throw new SolrServerException(e);
			}
		}

		throw new SolrServerException("No server available");
	}

	private SolrServerFactory _solrServerFactory;

}