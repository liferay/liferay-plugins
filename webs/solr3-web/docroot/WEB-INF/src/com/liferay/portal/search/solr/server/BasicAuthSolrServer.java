/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import java.io.IOException;

import java.net.MalformedURLException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.solr.client.solrj.ResponseParser;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.util.NamedList;

/**
 * @author Bruno Farache
 */
public class BasicAuthSolrServer extends StoppableSolrServer {

	public BasicAuthSolrServer(
			AuthScope authScope, String username, String password, String url)
		throws MalformedURLException {

		_username = username;
		_password = password;

		_multiThreadedHttpConnectionManager =
			new MultiThreadedHttpConnectionManager();

		HttpClient httpClient = new HttpClient(
			_multiThreadedHttpConnectionManager);

		if ((_username != null) && (_password != null)) {
			if (authScope == null) {
				authScope = AuthScope.ANY;
			}

			HttpState httpState = httpClient.getState();

			httpState.setCredentials(
				authScope,
				new UsernamePasswordCredentials(_username, _password));

			HttpClientParams httpClientParams = httpClient.getParams();

			httpClientParams.setAuthenticationPreemptive(true);
		}

		_server = new CommonsHttpSolrServer(url, httpClient);
	}

	public BasicAuthSolrServer(String url) throws MalformedURLException {
		this(null, null, url);
	}

	public BasicAuthSolrServer(String username, String password, String url)
		throws MalformedURLException {

		this(null, username, password, url);
	}

	public String getBaseURL() {
		return _server.getBaseURL();
	}

	public HttpClient getHttpClient() {
		return _server.getHttpClient();
	}

	public ModifiableSolrParams getInvariantParams() {
		return _server.getInvariantParams();
	}

	public ResponseParser getParser() {
		return _server.getParser();
	}

	@Override
	public NamedList<Object> request(SolrRequest solrRequest)
		throws IOException, SolrServerException {

		if (_stopped) {
			return null;
		}

		return _server.request(solrRequest);
	}

	public NamedList<Object> request(
			SolrRequest solrRequest, ResponseParser responseParser)
		throws IOException, SolrServerException {

		if (_stopped) {
			return null;
		}

		return _server.request(solrRequest, responseParser);
	}

	public void setAllowCompression(boolean compression) {
		_server.setAllowCompression(compression);
	}

	public void setBaseURL(String baseURL) {
		_server.setBaseURL(baseURL);
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setConnectionManagerTimeout(int connectionManagerTimeout) {
		_server.setConnectionManagerTimeout(connectionManagerTimeout);
	}

	public void setConnectionTimeout(int connectionTimeout) {
		_server.setConnectionTimeout(connectionTimeout);
	}

	public void setDefaultMaxConnectionsPerHost(int maxConnectionsPerHost) {
		_server.setDefaultMaxConnectionsPerHost(maxConnectionsPerHost);
	}

	public void setFollowRedirects(boolean followRedirects) {
		_server.setFollowRedirects(followRedirects);
	}

	public void setMaxRetries(int maxRetries) {
		_server.setMaxRetries(maxRetries);
	}

	public void setMaxTotalConnections(int maxTotalConnections) {
		_server.setMaxTotalConnections(maxTotalConnections);
	}

	public void setParser(ResponseParser responseParser) {
		_server.setParser(responseParser);
	}

	public void setSoTimeout(int soTimeout) {
		_server.setSoTimeout(soTimeout);
	}

	@Override
	public void stop() {
		_stopped = true;

		while (true) {
			int connectionsInPool =
				_multiThreadedHttpConnectionManager.getConnectionsInPool();

			if (connectionsInPool <= 0) {
				break;
			}

			if (_log.isDebugEnabled()) {
				_log.debug(
					toString() + " waiting on " + connectionsInPool +
						" connections");
			}

			_multiThreadedHttpConnectionManager.closeIdleConnections(200);

			try {
				Thread.sleep(200);
			}
			catch (InterruptedException ie) {
			}
		}

		_multiThreadedHttpConnectionManager.shutdown();

		if (_log.isDebugEnabled()) {
			_log.debug(toString() + " is shutdown");
		}
	}

	private static Log _log = LogFactoryUtil.getLog(BasicAuthSolrServer.class);

	private MultiThreadedHttpConnectionManager
		_multiThreadedHttpConnectionManager;
	private String _password;
	private CommonsHttpSolrServer _server;
	private boolean _stopped;
	private String _username;

}