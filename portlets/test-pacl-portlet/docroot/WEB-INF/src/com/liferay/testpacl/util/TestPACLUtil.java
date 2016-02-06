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

package com.liferay.testpacl.util;

import com.liferay.portal.kernel.deploy.DeployManagerUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.service.PortalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.File;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class TestPACLUtil {

	public static final String TEST_FIELD = "TEST_FIELD";

	public static Map<String, Boolean> testCurrentThread(long userId) {
		Map<String, Boolean> results = new HashMap<>();

		try {
			PortalServiceUtil.getBuildNumber();

			results.put("PortalServiceUtil#getBuildNumber", true);
		}
		catch (SecurityException se) {
			results.put("PortalServiceUtil#getBuildNumber", false);
		}
		catch (Exception e) {
			results.put("PortalServiceUtil#getBuildNumber", false);
		}

		try {
			UserLocalServiceUtil.getUser(userId);

			results.put("UserLocalServiceUtil#getUser", false);
		}
		catch (SecurityException se) {
			results.put("UserLocalServiceUtil#getUser", true);
		}
		catch (Exception e) {
			results.put("UserLocalServiceUtil#getUser", false);
		}

		return results;
	}

	public static Map<String, Boolean> testMessageBusThread(long userId)
		throws Exception {

		Message message = new Message();

		message.put("userId", userId);

		return (Map<String, Boolean>)MessageBusUtil.sendSynchronousMessage(
			"liferay/test_pacl", message);
	}

	public static Map<String, Boolean> testNewThread(final long userId)
		throws Exception {

		final Map<String, Boolean> results = new HashMap<>();

		Thread thread = new Thread() {

			@Override
			public void run() {
				results.putAll(testCurrentThread(userId));
			}

		};

		thread.start();

		try {
			thread.join();
		}
		catch (InterruptedException ie) {
			_log.error(ie, ie);
		}

		return results;
	}

	public static void testWriteFile() {
		File file = new File(
			translateFileName("../webapps/chat-portlet/css/main.css"));

		try {
			file.exists();

			throw new RuntimeException("File is not protected");
		}
		catch (SecurityException se) {
		}
	}

	public static String translateFileName(String fileName) {
		if (fileName.startsWith("../webapps")) {
			String installedDir = StringPool.BLANK;

			try {
				installedDir = DeployManagerUtil.getInstalledDir();
			}
			catch (Exception e) {
				_log.error(e, e);
			}

			fileName = StringUtil.replace(fileName, "../webapps", installedDir);

			if (ServerDetector.isGlassfish()) {
				fileName = StringUtil.replace(
					fileName, "autodeploy", "applications");
			}
			else if (ServerDetector.isJBoss()) {
				fileName = StringUtil.replace(
					fileName, "/chat-portlet/", "/chat-portlet.war/");
			}
			else if (ServerDetector.isWebSphere()) {
				String serverRoot = System.getProperty("server.root");
				String cellName = System.getenv("WAS_CELL");

				fileName = StringUtil.replace(
					fileName, installedDir + "/chat-portlet/",
					serverRoot + "/installedApps/" + cellName +
						"/chat-portlet.ear/chat-portlet.war/");
			}
		}

		return fileName;
	}

	private static Log _log = LogFactoryUtil.getLog(TestPACLUtil.class);

}