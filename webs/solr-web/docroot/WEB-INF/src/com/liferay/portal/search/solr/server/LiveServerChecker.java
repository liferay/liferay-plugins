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

import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.SolrPingResponse;

/**
 * @author Bruno Farache
 */
public class LiveServerChecker {

	public LiveServerChecker(
		final SolrServerFactory solrServerFactory, Long delay) {

		ScheduledExecutorService scheduledExecutorService =
			Executors.newSingleThreadScheduledExecutor();

		Runnable runnable = new Runnable() {

			public void run() {
				Collection<SolrServerWrapper> solrServerWrappers =
					solrServerFactory.getDeadServers();

				for (SolrServerWrapper solrServerWrapper : solrServerWrappers) {
					SolrServer solrServer = solrServerFactory.getDeadServer(
						solrServerWrapper);

					if (solrServer == null) {
						continue;
					}

					try {
						SolrPingResponse solrPingResponse = solrServer.ping();

						if (solrPingResponse.getStatus() == 0) {
							solrServerFactory.startServer(solrServerWrapper);
						}
					}
					catch (Exception e) {
					}
				}
			}

		};

		scheduledExecutorService.scheduleWithFixedDelay(
			runnable, 0, delay, TimeUnit.SECONDS);
	}

}