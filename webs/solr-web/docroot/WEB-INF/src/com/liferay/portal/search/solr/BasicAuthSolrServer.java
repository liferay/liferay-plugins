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

package com.liferay.portal.search.solr;

import java.io.IOException;

import java.net.MalformedURLException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.solr.client.solrj.ResponseParser;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.util.NamedList;

/**
 * <a href="BasicAuthSolrServer.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 *
 */
public class BasicAuthSolrServer extends SolrServer {

	public BasicAuthSolrServer(String url) throws MalformedURLException {
		this(null, null, url);
	}

	public BasicAuthSolrServer(String username, String password, String url)
		throws MalformedURLException {

		this(null, username, password, url);
	}

	public BasicAuthSolrServer(
			AuthScope authScope, String username, String password, String url)
		throws MalformedURLException {

		_username = username;
		_password = password;

		HttpClient httpClient = new HttpClient();

		if (_username != null && _password != null) {
			if (authScope == null) {
				authScope = AuthScope.ANY;
			}

			HttpState httpState = httpClient.getState();

			httpState.setCredentials(
				authScope,
				new UsernamePasswordCredentials(_username, _password));
		}

		_server = new CommonsHttpSolrServer(url, httpClient);
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

	public NamedList<Object> request(SolrRequest request)
		throws IOException, SolrServerException {

		return _server.request(request);
	}

	public NamedList<Object> request(SolrRequest request, ResponseParser parser)
		throws IOException, SolrServerException {

		return _server.request(request, parser);
	}

	public void setAllowCompression(boolean compression) {
		_server.setAllowCompression(compression);
	}

	public void setBaseURL(String baseURL) {
		_server.setBaseURL(baseURL);
	}

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

	public void setParser(ResponseParser parser) {
		_server.setParser(parser);
	}

	public void setSoTimeout(int soTimeout) {
		_server.setSoTimeout(soTimeout);
	}

	private String _password;
	private CommonsHttpSolrServer _server;
	private String _username;

}