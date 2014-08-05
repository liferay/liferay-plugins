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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.pool.PoolStats;
import org.apache.solr.client.solrj.ResponseParser;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.util.NamedList;

/**
 * @author Bruno Farache
 */
public class BasicAuthSolrServer extends SolrServer {

	public void afterPropertiesSet() {
		DefaultHttpClient defaultHttpClient = new DefaultHttpClient(
			_poolingClientConnectionManager);

		if ((_username != null) && (_password != null)) {
			if (_authScope == null) {
				_authScope = AuthScope.ANY;
			}

			CredentialsProvider credentialsProvider =
				defaultHttpClient.getCredentialsProvider();

			credentialsProvider.setCredentials(
				_authScope,
				new UsernamePasswordCredentials(_username, _password));

			for (HttpRequestInterceptor httpRequestInterceptor :
					_httpRequestInterceptors) {

				defaultHttpClient.addRequestInterceptor(
					httpRequestInterceptor, 0);
			}
		}

		_server = new HttpSolrServer(_url, defaultHttpClient);

		if (_allowCompression != null) {
			_server.setAllowCompression(_allowCompression);
		}

		if (Validator.isNotNull(_baseURL)) {
			_server.setBaseURL(_baseURL);
		}

		if (_connectionTimeout != null) {
			_server.setConnectionTimeout(_connectionTimeout);
		}

		if (_defaultMaxConnectionsPerRoute != null) {
			_poolingClientConnectionManager.setDefaultMaxPerRoute(
				_defaultMaxConnectionsPerRoute);
			//_server.setDefaultMaxConnectionsPerHost(
			//	_defaultMaxConnectionsPerRoute);
		}

		if (_followRedirects != null) {
			_server.setFollowRedirects(_followRedirects);
		}

		if (_maxTotalConnections != null) {
			_poolingClientConnectionManager.setMaxTotal(_maxTotalConnections);
			//_server.setMaxTotalConnections(_maxTotalConnections);
		}

		if (_maxRetries != null) {
			_server.setMaxRetries(_maxRetries);
		}

		if (_responseParser != null) {
			_server.setParser(_responseParser);
		}

		if (_soTimeout != null) {
			_server.setSoTimeout(_soTimeout);
		}
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

	public void setAllowCompression(boolean allowCompression) {
		_allowCompression = allowCompression;

		if (_server != null) {
			_server.setAllowCompression(_allowCompression);
		}
	}

	public void setAuthScope(AuthScope authScope) {
		_authScope = authScope;
	}

	public void setBaseURL(String baseURL) {
		_baseURL = baseURL;

		if (_server != null) {
			_server.setBaseURL(baseURL);
		}
	}

	public void setConnectionTimeout(int connectionTimeout) {
		_connectionTimeout = connectionTimeout;

		if (_server != null) {
			_server.setConnectionTimeout(connectionTimeout);
		}
	}

	public void setDefaultMaxConnectionsPerRoute(
		int defaultMaxConnectionsPerRoute) {

		_defaultMaxConnectionsPerRoute = defaultMaxConnectionsPerRoute;

		if (_server != null) {
			_server.setDefaultMaxConnectionsPerHost(
				defaultMaxConnectionsPerRoute);
		}
	}

	public void setFollowRedirects(boolean followRedirects) {
		_followRedirects = followRedirects;

		if (_server != null) {
			_server.setFollowRedirects(followRedirects);
		}
	}

	public void setHttpRequestInterceptors(
		List<HttpRequestInterceptor> httpRequestInterceptors) {

		_httpRequestInterceptors = httpRequestInterceptors;
	}

	public void setMaxRetries(int maxRetries) {
		_maxRetries = maxRetries;

		if (_server != null) {
			_server.setMaxRetries(maxRetries);
		}
	}

	public void setMaxTotalConnections(int maxTotalConnections) {
		_maxTotalConnections = maxTotalConnections;

		if (_server != null) {
			_server.setMaxTotalConnections(maxTotalConnections);
		}
	}

	public void setParser(ResponseParser responseParser) {
		_responseParser = responseParser;

		if (_server != null) {
			_server.setParser(responseParser);
		}
	}

	public void setPassword(String password) {
		_password = password;
	}

	public void setSoTimeout(int soTimeout) {
		_soTimeout = soTimeout;

		if (_server != null) {
			_server.setSoTimeout(soTimeout);
		}
	}

	public void setUrl(String url) {
		_url = url;
	}

	public void setUsername(String username) {
		_username = username;
	}

	@Override
	public void shutdown() {
		_stopped = true;

		_server.shutdown();

		int retry = 0;

		while (retry < 10) {
			PoolStats poolStats =
				_poolingClientConnectionManager.getTotalStats();

			int availableConnections = poolStats.getAvailable();

			if (availableConnections <= 0) {
				break;
			}

			if (_log.isDebugEnabled()) {
				_log.debug(
					toString() + " waiting on " + availableConnections +
						" connections");
			}

			_poolingClientConnectionManager.closeIdleConnections(
				200, TimeUnit.MILLISECONDS);

			try {
				Thread.sleep(500);
			}
			catch (InterruptedException ie) {
			}

			retry++;
		}

		_poolingClientConnectionManager.shutdown();

		if (_log.isDebugEnabled()) {
			_log.debug(toString() + " is shutdown");
		}
	}

	private static Log _log = LogFactoryUtil.getLog(BasicAuthSolrServer.class);

	private Boolean _allowCompression;
	private AuthScope _authScope;
	private String _baseURL;
	private Integer _connectionTimeout;
	private Integer _defaultMaxConnectionsPerRoute;
	private Boolean _followRedirects;
	private List<HttpRequestInterceptor> _httpRequestInterceptors;
	private Integer _maxRetries;
	private Integer _maxTotalConnections;
	private String _password;
	private PoolingClientConnectionManager _poolingClientConnectionManager =
		new PoolingClientConnectionManager();
	private ResponseParser _responseParser;
	private HttpSolrServer _server;
	private Integer _soTimeout;
	private boolean _stopped;
	private String _url;
	private String _username;

}