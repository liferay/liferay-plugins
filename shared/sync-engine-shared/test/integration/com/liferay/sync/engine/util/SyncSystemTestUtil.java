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

package com.liferay.sync.engine.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.sync.engine.session.Session;
import com.liferay.sync.engine.session.SessionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.Socket;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import org.junit.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class SyncSystemTestUtil {

	public static void addUser(String name, long syncAccountId)
		throws Exception {

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("companyId", getCompanyId(syncAccountId));
		parameters.put("autoPassword", false);
		parameters.put("password1", "test");
		parameters.put("password2", "test");
		parameters.put("autoScreenName", false);
		parameters.put("screenName", name);
		parameters.put("emailAddress", name.concat("@liferay.com"));
		parameters.put("facebookId", 0);
		parameters.put("openId", "");
		parameters.put("locale", Locale.getDefault());
		parameters.put("firstName", name);
		parameters.put("middleName", "");
		parameters.put("lastName", "");
		parameters.put("prefixId", 0);
		parameters.put("suffixId", 0);
		parameters.put("male", true);
		parameters.put("birthdayMonth", 1);
		parameters.put("birthdayDay", 1);
		parameters.put("birthdayYear", 1901);
		parameters.put("jobTitle", "");
		parameters.put("groupIds", getGuestGroupId(syncAccountId));
		parameters.put("-organizationIds", null);
		parameters.put("-roleIds", null);
		parameters.put("-userGroupIds", null);
		parameters.put("sendEmail", false);

		executePost(_ADD_USER_URL_PATH, parameters, syncAccountId);
	}

	public static void deleteUser(long userId, long syncAccountId)
		throws Exception {

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("userId", userId);

		executePost(_DELETE_USER_URL_PATH, parameters, syncAccountId);
	}

	public static long getGuestGroupId(long syncAccountId) throws Exception {
		if (_guestGroupId > 0) {
			return _guestGroupId;
		}

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("companyId", getCompanyId(syncAccountId));
		parameters.put("name", "Guest");

		Session session = SessionManager.getSession(syncAccountId);

		HttpResponse httpResponse = session.executePost(
			_GET_GROUP_URL_PATH, parameters);

		HttpEntity httpEntity = httpResponse.getEntity();

		String response = EntityUtils.toString(httpEntity);

		ObjectMapper mapper = new ObjectMapper();

		JsonNode rootJsonNode = mapper.readTree(response);

		JsonNode groupIdJsonNode = rootJsonNode.get("groupId");

		_guestGroupId = groupIdJsonNode.asLong();

		return _guestGroupId;
	}

	public static boolean startLiferay() throws Exception {
		if (isPortInUse(8080)) {
			Assert.fail(
				"Liferay is currently running. Stop the process before " +
					"continuing on with any tests.");
		}

		Process process = executeCommand("run");

		InputStreamReader inputStreamReader = new InputStreamReader(
			process.getInputStream());

		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String line = null;

		_logger.info("Starting Liferay Portal");

		while ((line = bufferedReader.readLine()) != null) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(line);
			}

			if (line.contains("sync-web is available for use")) {
				break;
			}
		}

		return true;
	}

	public static void stopLiferay() throws Exception {
		if (!isPortInUse(8005)) {
			return;
		}

		executeCommand("stop");
	}

	protected static Process executeCommand(String action) throws Exception {
		String command = null;

		String tomcatDir = System.getProperty("app.server.tomcat.dir");

		if (OSDetector.isWindows()) {
			command = tomcatDir.concat("/bin/catalina.bat ").concat(action);
		}
		else {
			command = tomcatDir.concat("/bin/catalina.sh ").concat(action);
		}

		Runtime runtime = Runtime.getRuntime();

		return runtime.exec(command);
	}

	protected static HttpResponse executePost(
			String urlPath, Map<String, Object> parameters, long syncAccountId)
		throws Exception {

		Session session = SessionManager.getSession(syncAccountId);

		return session.executePost(urlPath, parameters);
	}

	protected static long getCompanyId(long syncAccountId) throws Exception {
		if (_companyId > 0) {
			return _companyId;
		}

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("virtualHost", "localhost");

		Session session = SessionManager.getSession(syncAccountId);

		HttpResponse httpResponse = session.executePost(
			_GET_COMPANY_URL_PATH, parameters);

		HttpEntity httpEntity = httpResponse.getEntity();

		String response = EntityUtils.toString(httpEntity);

		ObjectMapper mapper = new ObjectMapper();

		JsonNode rootJsonNode = mapper.readTree(response);

		JsonNode companyIdJsonNode = rootJsonNode.get("companyId");

		_companyId = companyIdJsonNode.asLong();

		return _companyId;
	}

	protected static boolean isPortInUse(int port) {
		try {
			Socket socket = new Socket("localhost", port);

			socket.close();

			return true;
		}
		catch (IOException ioe) {
			return false;
		}
	}

	private static final String _ADD_USER_URL_PATH = "/user/add-user";

	private static final String _DELETE_USER_URL_PATH = "/user/delete-user";

	private static final String _GET_COMPANY_URL_PATH =
		"/company/get-company-by-virtual-host";

	private static final String _GET_GROUP_URL_PATH = "/group/get-group";

	private static Logger _logger = LoggerFactory.getLogger(
		SyncSystemTestUtil.class);

	private static long _companyId;
	private static long _guestGroupId;

}