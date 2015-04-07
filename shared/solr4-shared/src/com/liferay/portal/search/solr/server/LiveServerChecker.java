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

package com.liferay.portal.search.solr.server;

import com.liferay.portal.search.solr.servlet.SolrServletContextListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.SolrPingResponse;

/**
 * @author Bruno Farache
 * @author Zsigmond Rab
 */
public class LiveServerChecker implements Runnable {

	public void afterPropertiesSet() {
		_scheduledExecutorService =
			Executors.newSingleThreadScheduledExecutor();

		_scheduledExecutorService.scheduleWithFixedDelay(
			this, 0, _delay, TimeUnit.SECONDS);

		SolrServletContextListener.registerLiveServerChecker(this);
	}

	@Override
	public void run() {
		Collection<SolrServerWrapper> solrServerWrappers =
			_solrServerFactory.getDeadServers();

		solrServerWrappers.addAll(_solrServerFactory.getLiveServers());

		for (SolrServerWrapper solrServerWrapper : solrServerWrappers) {
			SolrServer solrServer = solrServerWrapper.getServer();

			if (solrServer == null) {
				continue;
			}

			try {
				SolrPingResponse solrPingResponse = solrServerWrapper.ping();

				if (solrPingResponse.getStatus() == 0) {
					_solrServerFactory.startServer(solrServerWrapper);
				}
				else {
					_solrServerFactory.killServer(solrServerWrapper);
				}
			}
			catch (Exception e) {
				_solrServerFactory.killServer(solrServerWrapper);
			}
		}
	}

	public void setDelay(long delay) {
		_delay = delay;
	}

	public void setSolrServerFactory(SolrServerFactory solrServerFactory) {
		_solrServerFactory = solrServerFactory;
	}

	public void shutdown() {
		List<SolrServerWrapper> allSolrServerWrappers = new ArrayList<>();

		List<SolrServerWrapper> deadSolrServerWrappers =
			_solrServerFactory.getDeadServers();

		allSolrServerWrappers.addAll(deadSolrServerWrappers);

		deadSolrServerWrappers.clear();

		List<SolrServerWrapper> liveSolrServerWrappers =
			_solrServerFactory.getLiveServers();

		allSolrServerWrappers.addAll(liveSolrServerWrappers);

		liveSolrServerWrappers.clear();

		for (SolrServerWrapper solrServerWrapper : allSolrServerWrappers) {
			SolrServer solrServer = solrServerWrapper.getServer();

			if (solrServer == null) {
				continue;
			}

			solrServer.shutdown();

			_solrServerFactory.killServer(solrServerWrapper);
		}

		_scheduledExecutorService.shutdownNow();
	}

	private long _delay;
	private ScheduledExecutorService _scheduledExecutorService;
	private SolrServerFactory _solrServerFactory;

}