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

package com.liferay.jsonwebserviceclient;

import java.security.KeyStore;

import java.util.Map;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public interface JSONWebServiceClient {

	public String doDelete(String url, Map<String, String> parameters)
		throws JSONWebServiceTransportException;

	public String doDelete(
			String url, Map<String, String> parameters,
			Map<String, String> headers)
		throws JSONWebServiceTransportException;

	public String doGet(String url, Map<String, String> parameters)
		throws JSONWebServiceTransportException;

	public String doGet(
			String url, Map<String, String> parameters,
			Map<String, String> headers)
		throws JSONWebServiceTransportException;

	public String doPost(String url, Map<String, String> parameters)
		throws JSONWebServiceTransportException;

	public String doPost(
			String url, Map<String, String> parameters,
			Map<String, String> headers)
		throws JSONWebServiceTransportException;

	public String doPostAsJSON(String url, String json)
		throws JSONWebServiceTransportException;

	public String doPostAsJSON(
			String url, String json, Map<String, String> headers)
		throws JSONWebServiceTransportException;

	public String doPut(String url, Map<String, String> parameters)
		throws JSONWebServiceTransportException;

	public String doPut(
		String url, Map<String, String> parameters,
		Map<String, String> headers)
	throws JSONWebServiceTransportException;

	public String getHostName();

	public int getHostPort();

	public String getProtocol();

	public void resetHttpClient();

	public void setHostName(String hostName);

	public void setHostPort(int hostPort);

	public void setKeyStore(KeyStore keyStore);

	public void setLogin(String login);

	public void setPassword(String password);

	public void setProtocol(String protocol);

}