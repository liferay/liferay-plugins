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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.httpclient.methods.PostMethod;
import org.opensaml.ws.transport.http.HTTPInTransport;
import org.opensaml.xml.security.credential.Credential;

/**
 * @author Mika Koivisto
 */
public class HttpClientInTransport implements HTTPInTransport {

	public HttpClientInTransport(PostMethod postMethod, String endpointURI) {

		_postMethod = postMethod;
		_endpointURI = endpointURI;
	}

	public String getLocalAddress() {
		return _endpointURI;
	}

	public String getPeerAddress() {
		return null;
	}

	public String getPeerDomainName() {
		return null;
	}

	public InputStream getIncomingStream() {
		try {
			return _postMethod.getResponseBodyAsStream();
		}
		catch (IOException ioe) {
			return null;
		}
	}

	public Object getAttribute(String s) {
		return null;
	}

	public String getCharacterEncoding() {
		return _postMethod.getResponseCharSet();
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
		return null;
	}

	public String getHTTPMethod() {
		return _postMethod.getName();
	}

	public int getStatusCode() {
		return _postMethod.getStatusCode();
	}

	public String getParameterValue(String s) {
		return null;
	}

	public List<String> getParameterValues(String s) {
		return null;
	}

	public HTTP_VERSION getVersion() {
		return null;
	}

	private final String _endpointURI;
	private final PostMethod _postMethod;

}
