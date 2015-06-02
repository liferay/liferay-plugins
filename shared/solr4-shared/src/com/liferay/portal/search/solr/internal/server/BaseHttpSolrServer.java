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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.http.client.HttpClient;
import org.apache.solr.client.solrj.ResponseParser;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.util.NamedList;

/**
 * @author László Csontos
 * @author Bruno Farache
 * @author André de Oliveira
 */
public class BaseHttpSolrServer extends SolrServer {

	public String getBaseURL() {
		return _httpSolrServer.getBaseURL();
	}

	public HttpClient getHttpClient() {
		return _httpSolrServer.getHttpClient();
	}

	public ModifiableSolrParams getInvariantParams() {
		return _httpSolrServer.getInvariantParams();
	}

	public ResponseParser getParser() {
		return _httpSolrServer.getParser();
	}

	@Override
	public NamedList<Object> request(SolrRequest solrRequest)
		throws IOException, SolrServerException {

		if (_stopped.get()) {
			return null;
		}

		return _httpSolrServer.request(solrRequest);
	}

	public NamedList<Object> request(
			SolrRequest solrRequest, ResponseParser responseParser)
		throws IOException, SolrServerException {

		if (_stopped.get()) {
			return null;
		}

		return _httpSolrServer.request(solrRequest, responseParser);
	}

	public void setAllowCompression(boolean allowCompression) {
		_allowCompression = allowCompression;

		if (_httpSolrServer != null) {
			_httpSolrServer.setAllowCompression(_allowCompression);
		}
	}

	public void setBaseURL(String baseURL) {
		_baseURL = baseURL;

		if (_httpSolrServer != null) {
			_httpSolrServer.setBaseURL(baseURL);
		}
	}

	public void setConnectionTimeout(int connectionTimeout) {
		_connectionTimeout = connectionTimeout;

		if (_httpSolrServer != null) {
			_httpSolrServer.setConnectionTimeout(connectionTimeout);
		}
	}

	public void setFollowRedirects(boolean followRedirects) {
		_followRedirects = followRedirects;

		if (_httpSolrServer != null) {
			_httpSolrServer.setFollowRedirects(followRedirects);
		}
	}

	public void setMaxRetries(int maxRetries) {
		_maxRetries = maxRetries;

		if (_httpSolrServer != null) {
			_httpSolrServer.setMaxRetries(maxRetries);
		}
	}

	public void setParser(ResponseParser responseParser) {
		_responseParser = responseParser;

		if (_httpSolrServer != null) {
			_httpSolrServer.setParser(responseParser);
		}
	}

	public void setSoTimeout(int soTimeout) {
		_soTimeout = soTimeout;

		if (_httpSolrServer != null) {
			_httpSolrServer.setSoTimeout(soTimeout);
		}
	}

	public void setUrl(String url) {
		_url = url;
	}

	@Override
	public void shutdown() {
		_stopped.set(true);

		_httpSolrServer.shutdown();

		if (_log.isInfoEnabled()) {
			_log.info(toString() + " was shut down");
		}
	}

	protected HttpSolrServer getHttpSolrServer() {
		return _httpSolrServer;
	}

	protected void initHttpSolrServer(HttpClient httpClient) {
		HttpSolrServer httpSolrServer = new HttpSolrServer(_url, httpClient);

		if (_allowCompression != null) {
			httpSolrServer.setAllowCompression(_allowCompression);
		}

		if (Validator.isNotNull(_baseURL)) {
			httpSolrServer.setBaseURL(_baseURL);
		}

		if (_connectionTimeout != null) {
			httpSolrServer.setConnectionTimeout(_connectionTimeout);
		}

		if (_followRedirects != null) {
			httpSolrServer.setFollowRedirects(_followRedirects);
		}

		if (_maxRetries != null) {
			httpSolrServer.setMaxRetries(_maxRetries);
		}

		if (_responseParser != null) {
			httpSolrServer.setParser(_responseParser);
		}

		if (_soTimeout != null) {
			httpSolrServer.setSoTimeout(_soTimeout);
		}

		_httpSolrServer = httpSolrServer;
	}

	private static Log _log = LogFactoryUtil.getLog(BaseHttpSolrServer.class);

	private Boolean _allowCompression;
	private String _baseURL;
	private Integer _connectionTimeout;
	private Boolean _followRedirects;
	private HttpSolrServer _httpSolrServer;
	private Integer _maxRetries;
	private ResponseParser _responseParser;
	private Integer _soTimeout;
	private AtomicBoolean _stopped = new AtomicBoolean(false);
	private String _url;

}