/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.servermanager.executor;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.servermanager.util.JSONKeys;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.text.Format;

import java.util.Date;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class OutputLogExecutor extends BaseExecutor {

	@Override
	public void executeRead(
			HttpServletRequest request, JSONObject responseJSONObject,
			Queue<String> arguments)
		throws Exception {

		File logFile = getLogFile();

		if ((logFile == null) || !logFile.exists()) {
			return;
		}

		InputStream inputStream = new FileInputStream(logFile);

		int offset = GetterUtil.getInteger(arguments.poll());

		inputStream.skip(offset);

		OutputStream outputStream = new ByteArrayOutputStream();

		StreamUtil.transfer(inputStream, outputStream);

		responseJSONObject.put(JSONKeys.OUTPUT, outputStream.toString());
	}

	protected String getLiferayDateString() {
		return getTomcatDateString();
	}

	protected File getLogFile() {
		File logFile = null;

		if (ServerDetector.isJBoss()) {
			File logDirectory = new File(
				System.getProperty("jboss.server.log.dir"));

			logFile = new File(logDirectory, "server.log");
		}
		else {
			logFile = new File(
				PropsUtil.get(PropsKeys.LIFERAY_HOME) + "/logs/liferay." +
					getLiferayDateString() + ".log");
		}

		return logFile;
	}

	protected String getTomcatDateString() {
		Date date = new Date();

		return _simpleDateFormat.format(date);
	}

	private static Format _simpleDateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");

}