/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.portal.search.solr.internal.server;

import java.io.IOException;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.SolrPingResponse;
import org.apache.solr.common.util.NamedList;

/**
 * @author Bruno Farache
 */
public class SolrServerWrapper {

	public SolrServerWrapper(String id, SolrServer solrServer) {
		_id = id;
		_solrServer = solrServer;
	}

	public String getId() {
		return _id;
	}

	public int getInvocationCount() {
		return _invocationCount.get();
	}

	public SolrServer getServer() {
		return _solrServer;
	}

	public int incrementInvocationCount() {
		return _invocationCount.getAndIncrement();
	}

	public SolrPingResponse ping() throws IOException, SolrServerException {
		try {
			return _solrServer.ping();
		}
		catch (SolrServerException sse) {
			if (sse.getRootCause() instanceof IOException) {
				_solrServerFactory.killServer(this);
			}

			throw sse;
		}
	}

	public NamedList<Object> request(SolrRequest solrRequest)
		throws IOException, SolrServerException {

		try {
			incrementInvocationCount();

			return _solrServer.request(solrRequest);
		}
		catch (SolrServerException sse) {
			if (sse.getRootCause() instanceof IOException) {
				_solrServerFactory.killServer(this);
			}

			throw sse;
		}
	}

	public void resetInvocationCount() {
		_invocationCount.set(0);
	}

	public void setSolrServerFactory(SolrServerFactory solrServerFactory) {
		_solrServerFactory = solrServerFactory;
	}

	private String _id;
	private AtomicInteger _invocationCount = new AtomicInteger(0);
	private SolrServer _solrServer;
	private SolrServerFactory _solrServerFactory;

}