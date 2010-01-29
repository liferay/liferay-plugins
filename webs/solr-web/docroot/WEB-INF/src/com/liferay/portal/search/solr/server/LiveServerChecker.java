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

import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.SolrPingResponse;

/**
 * <a href="LiveServerChecker.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 *
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