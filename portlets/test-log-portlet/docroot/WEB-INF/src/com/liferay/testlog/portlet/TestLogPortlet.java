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

package com.liferay.testlog.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.Format;

import java.util.Date;

import javax.portlet.GenericPortlet;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Shinn Lok
 */
public class TestLogPortlet extends GenericPortlet {

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException {

		PrintWriter printWriter = renderResponse.getWriter();

		if (_log.isInfoEnabled()) {
			printWriter.write("Log#isEnabled=PASSED\n");
		}
		else {
			printWriter.write("Log#isEnabled=FAILED\n");
		}

		File file = new File(
			PropsUtil.get(PropsKeys.LIFERAY_HOME) + "/logs/test-log-portlet." +
				_format.format(new Date()) + ".log");

		if (file.exists()) {
			printWriter.write(file + "=PASSED\n");
		}
		else {
			printWriter.write(file + "=FAILED\n");
		}

		printWriter.close();
	}

	private static Log _log = LogFactoryUtil.getLog(TestLogPortlet.class);

	private static Format _format =
		FastDateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");

}