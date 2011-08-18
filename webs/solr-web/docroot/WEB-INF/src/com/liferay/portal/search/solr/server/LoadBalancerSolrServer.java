/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

	@Override
	public NamedList<Object> request(SolrRequest solrRequest)
		throws SolrServerException {

		SolrServerWrapper solrServerWapper = _solrServerFactory.getLiveServer();

		try {
			return solrServerWapper.request(solrRequest);
		}
		catch (SolrException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SolrServerException(e);
		}
	}

	private SolrServerFactory _solrServerFactory;

}