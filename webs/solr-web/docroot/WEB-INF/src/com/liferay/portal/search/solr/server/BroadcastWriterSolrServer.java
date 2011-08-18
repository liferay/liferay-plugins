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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.common.util.NamedList;

/**
 * @author Bruno Farache
 */
public class BroadcastWriterSolrServer extends SolrServer {

	public BroadcastWriterSolrServer(SolrServerFactory solrServerFactory) {
		_solrServerFactory = solrServerFactory;
	}

	@Override
	public NamedList<Object> request(SolrRequest solrRequest)
		throws SolrServerException {

		NamedList<Object> response = null;

		if (!(solrRequest instanceof UpdateRequest)) {
			throw new IllegalStateException(
				"This SolrServer should be used only to update requests");
		}

		List<SolrServerWrapper> solrServerWrappers =
			_solrServerFactory.getLiveServers();

		for (SolrServerWrapper solrServerWrapper : solrServerWrappers) {
			try {
				response = solrServerWrapper.request(solrRequest);
			}
			catch (Exception e) {
				_log.error(
					"Could not send request to server " +
						solrServerWrapper.getId(),
					e);
			}
		}

		if (response == null) {
			throw new SolrServerException("No server available");
		}

		return response;
	}

	private static Log _log = LogFactoryUtil.getLog(
		BroadcastWriterSolrServer.class);

	private SolrServerFactory _solrServerFactory;

}