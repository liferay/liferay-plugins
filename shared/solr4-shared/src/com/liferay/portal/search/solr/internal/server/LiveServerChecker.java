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

import com.liferay.portal.kernel.util.MapUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.SolrPingResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bruno Farache
 * @author Zsigmond Rab
 */
public class LiveServerChecker implements Runnable {

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

	@Activate
	protected void activate(Map<String, Object> properties) {
		_scheduledExecutorService =
			Executors.newSingleThreadScheduledExecutor();

		long delay = MapUtil.getLong(properties, "delay", 10);

		_scheduledExecutorService.scheduleWithFixedDelay(
			this, 0, delay, TimeUnit.SECONDS);
	}

	@Deactivate
	protected void deactivate() {
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

	@Reference(unbind = "-")
	protected void setSolrServerFactory(SolrServerFactory solrServerFactory) {
		_solrServerFactory = solrServerFactory;
	}

	private ScheduledExecutorService _scheduledExecutorService;
	private SolrServerFactory _solrServerFactory;

}