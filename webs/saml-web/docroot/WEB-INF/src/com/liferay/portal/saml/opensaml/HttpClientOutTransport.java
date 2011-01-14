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

package com.liferay.portal.saml.opensaml;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpParams;
import org.opensaml.ws.transport.http.HTTPOutTransport;
import org.opensaml.xml.security.credential.Credential;

/**
 * @author Mika Koivisto
 */
public class HttpClientOutTransport implements HTTPOutTransport {

	public HttpClientOutTransport(PostMethod method) {

		_postMethod = method;
	}

	public void setVersion(HTTP_VERSION http_version) {

		HttpParams params = _postMethod.getParams();

		switch (http_version) {
		case HTTP1_0:
			params.setParameter("http.protocol.version", HttpVersion.HTTP_1_0);
			break;
		case HTTP1_1:
			params.setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
			break;
		}
	}

	public void setHeader(String s, String s1) {

		_postMethod.setRequestHeader(s, s1);
	}

	public void addParameter(String s, String s1) {

	}

	public void setStatusCode(int i) {

	}

	public void sendRedirect(String s) {

	}

	public void setAttribute(String s, Object o) {

	}

	public void setCharacterEncoding(String s) {

		_postMethod.getParams().setParameter("http.protocol.content-charset", s);
	}

	public OutputStream getOutgoingStream() {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		RequestEntity requestEntity =
			new OutputStreamRequestEntity(outputStream);

		_postMethod.setRequestEntity(requestEntity);

		return outputStream;
	}

	public Object getAttribute(String s) {

		return null;
	}

	public String getCharacterEncoding() {

		return _postMethod.getParameter("http.protocol.content-charset").getValue();
	}

	public Credential getLocalCredential() {

		return null;
	}

	public Credential getPeerCredential() {

		return null;
	}

	public boolean isAuthenticated() {

		return false;
	}

	public void setAuthenticated(boolean b) {

	}

	public boolean isConfidential() {

		return false;
	}

	public void setConfidential(boolean b) {

	}

	public boolean isIntegrityProtected() {

		return false;
	}

	public void setIntegrityProtected(boolean b) {

	}

	public String getHeaderValue(String s) {

		return _postMethod.getRequestHeader(s).getValue();
	}

	public String getHTTPMethod() {

		return _postMethod.getParameter("http.protocol.version").getValue();
	}

	public int getStatusCode() {

		return -1;
	}

	public String getParameterValue(String s) {

		return null;
	}

	public List<String> getParameterValues(String s) {

		return null;
	}

	public HTTP_VERSION getVersion() {

		HttpVersion httpVersion =
			(HttpVersion) _postMethod.getParams().getParameter(
				"http.protocol.version");

		if (httpVersion == HttpVersion.HTTP_1_1) {
			return HTTP_VERSION.HTTP1_1;
		}

		return HTTP_VERSION.HTTP1_0;
	}

	private final PostMethod _postMethod;
}
