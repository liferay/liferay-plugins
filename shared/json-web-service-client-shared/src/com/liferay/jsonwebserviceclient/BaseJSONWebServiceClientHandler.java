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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Igor Beslic
 */
public abstract class BaseJSONWebServiceClientHandler {

	public abstract JSONWebServiceClient getJSONWebServiceClient();

	protected BaseJSONWebServiceClientHandler() {
		objectMapper.configure(
			DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	protected String doGet(
		String url, Map<String, String> parameters,
		Map<String, String> headers) {

		JSONWebServiceClient jsonWebServiceClient = getJSONWebServiceClient();

		return jsonWebServiceClient.doGet(url, parameters, headers);
	}

	protected String doGet(String url, String... parametersArray) {
		JSONWebServiceClient jsonWebServiceClient = getJSONWebServiceClient();

		Map<String, String> parameters = new HashMap<>();

		for (int i = 0; i < parametersArray.length; i += 2) {
			parameters.put(parametersArray[i], parametersArray[i + 1]);
		}

		return jsonWebServiceClient.doGet(url, parameters);
	}

	protected <T> List<T> doGetToList(
			Class<T> clazz, String url, Map<String, String> parameters,
			Map<String, String> headers)
		throws JSONWebServiceInvocationException {

		String json = doGet(url, parameters, headers);

		if ((json == null) || json.equals("") || json.equals("{}") ||
			json.equals("[]")) {

			return Collections.emptyList();
		}

		if (json.contains("exception\":\"")) {
			throw new JSONWebServiceInvocationException(
				getExceptionMessage(json));
		}

		try {
			TypeFactory typeFactory = objectMapper.getTypeFactory();

			JavaType javaType = typeFactory.constructCollectionType(
				List.class, clazz);

			return objectMapper.readValue(json, javaType);
		}
		catch (IOException ie) {
			throw new JSONWebServiceInvocationException(ie);
		}
	}

	protected <T> List<T> doGetToList(
			Class<T> clazz, String url, String... parametersArray)
		throws JSONWebServiceInvocationException {

		Map<String, String> parameters = new HashMap<String, String>();

		for (int i = 0; i < parametersArray.length; i += 2) {
			parameters.put(parametersArray[i], parametersArray[i + 1]);
		}

		return doGetToList(
			clazz, url, parameters, Collections.<String, String>emptyMap());
	}

	protected <T> T doGetToObject(
			Class<T> clazz, String url, String... parametersArray)
		throws JSONWebServiceInvocationException {

		String json = doGet(url, parametersArray);

		if ((json == null) || json.equals("") || json.equals("{}")) {
			return null;
		}

		if (json.contains("exception\":\"")) {
			throw new JSONWebServiceInvocationException(
				getExceptionMessage(json));
		}

		try {
			return objectMapper.readValue(json, clazz);
		}
		catch (IOException ie) {
			throw new JSONWebServiceInvocationException(ie);
		}
	}

	protected String doPost(
		String url, Map<String, String> parameters,
		Map<String, String> headers) {

		JSONWebServiceClient jsonWebServiceClient = getJSONWebServiceClient();

		return jsonWebServiceClient.doPost(url, parameters, headers);
	}

	protected String doPost(String url, String... parametersArray) {
		JSONWebServiceClient jsonWebServiceClient = getJSONWebServiceClient();

		Map<String, String> parameters = new HashMap<>();

		for (int i = 0; i < parametersArray.length; i += 2) {
			parameters.put(parametersArray[i], parametersArray[i + 1]);
		}

		return jsonWebServiceClient.doPost(url, parameters);
	}

	protected String doPostAsJSON(String url, Object object)
		throws JSONWebServiceInvocationException {

		try {
			JSONWebServiceClient jsonWebServiceClient =
				getJSONWebServiceClient();

			String json = objectMapper.writeValueAsString(object);

			return jsonWebServiceClient.doPostAsJSON(url, json);
		}
		catch (IOException ie) {
			throw new JSONWebServiceInvocationException(ie);
		}
	}

	protected String getExceptionMessage(String json) {
		int exceptionMessageStart = json.indexOf("exception\":\"") + 12;

		int exceptionMessageEnd = json.indexOf("\"", exceptionMessageStart);

		return json.substring(exceptionMessageStart, exceptionMessageEnd);
	}

	protected ObjectMapper objectMapper = new ObjectMapper();

}