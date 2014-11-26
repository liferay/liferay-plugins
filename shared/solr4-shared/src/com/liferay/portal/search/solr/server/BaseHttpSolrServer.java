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

		if (_stopped.get()) {
			return null;
		}

		return _server.request(solrRequest);
	}

	public NamedList<Object> request(
			SolrRequest solrRequest, ResponseParser responseParser)
		throws IOException, SolrServerException {

		if (_stopped.get()) {
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

	public void setFollowRedirects(boolean followRedirects) {
		_followRedirects = followRedirects;

		if (_server != null) {
			_server.setFollowRedirects(followRedirects);
		}
	}

	public void setMaxRetries(int maxRetries) {
		_maxRetries = maxRetries;

		if (_server != null) {
			_server.setMaxRetries(maxRetries);
		}
	}

	public void setParser(ResponseParser responseParser) {
		_responseParser = responseParser;

		if (_server != null) {
			_server.setParser(responseParser);
		}
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

	@Override
	public void shutdown() {
		_stopped.set(true);

		_server.shutdown();

		if (_log.isInfoEnabled()) {
			_log.info(toString() + " has been shut down.");
		}
	}

	protected HttpSolrServer getServer() {
		return _server;
	}

	protected void initServer(HttpClient httpClient) {
		HttpSolrServer server = new HttpSolrServer(_url, httpClient);

		if (_allowCompression != null) {
			server.setAllowCompression(_allowCompression);
		}

		if (Validator.isNotNull(_baseURL)) {
			server.setBaseURL(_baseURL);
		}

		if (_connectionTimeout != null) {
			server.setConnectionTimeout(_connectionTimeout);
		}

		if (_followRedirects != null) {
			server.setFollowRedirects(_followRedirects);
		}

		if (_maxRetries != null) {
			server.setMaxRetries(_maxRetries);
		}

		if (_responseParser != null) {
			server.setParser(_responseParser);
		}

		if (_soTimeout != null) {
			server.setSoTimeout(_soTimeout);
		}

		_server = server;
	}

	private static Log _log = LogFactoryUtil.getLog(BaseHttpSolrServer.class);

	private Boolean _allowCompression;
	private String _baseURL;
	private Integer _connectionTimeout;
	private Boolean _followRedirects;
	private Integer _maxRetries;
	private ResponseParser _responseParser;
	private HttpSolrServer _server;
	private Integer _soTimeout;
	private AtomicBoolean _stopped = new AtomicBoolean(false);
	private String _url;

}