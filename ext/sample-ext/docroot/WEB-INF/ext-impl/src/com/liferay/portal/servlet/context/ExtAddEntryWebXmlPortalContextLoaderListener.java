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

package com.liferay.portal.servlet.context;

import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.SystemProperties;

import java.io.BufferedWriter;
import java.io.IOException;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Lawrence Lee
 */
public class ExtAddEntryWebXmlPortalContextLoaderListener
	implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("EXT_ADD_ENTRY_WEB_XML_INSTALLED");

		Date date = new Date();

		String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

		String logPath = null;

		if (ServerDetector.isJBoss()) {
			logPath = SystemProperties.get("jboss.home.dir") +
				"/../logs/liferay." + formattedDate + ".log";
		}
		else if (ServerDetector.isTomcat()) {
			logPath = SystemProperties.get("catalina.base") +
				"/../logs/liferay." + formattedDate + ".log";
		}
		else if (ServerDetector.isWebLogic()) {
			logPath = SystemProperties.get("env.DOMAIN_HOME") +
				"/../logs/liferay." + formattedDate + ".log";
		}
		else {
			logPath = SystemProperties.get("user.dir") +
				"/liferay/logs/liferay." + formattedDate + ".log";
		}

		try (BufferedWriter bufferedWriter = Files.newBufferedWriter(
				Paths.get(logPath), Charset.defaultCharset(),
				StandardOpenOption.APPEND, StandardOpenOption.CREATE,
				StandardOpenOption.WRITE)) {

			bufferedWriter.write("EXT_ADD_ENTRY_WEB_XML_INSTALLED");

			bufferedWriter.newLine();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}