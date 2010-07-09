/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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
 * @author Bruno Farache
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