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
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Shinn Lok
 */
public class TestLogPortlet extends GenericPortlet {

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		PrintWriter printWriter = renderResponse.getWriter();

		_log.info("Execute Test");

		if (!_log.isInfoEnabled()) {
			printWriter.write("FAILED");

			printWriter.close();

			return;
		}

		File file = new File(
			PropsUtil.get(PropsKeys.LIFERAY_HOME) + "/logs/test-log-portlet." +
				_simpleDateFormat.format(new Date()) + ".log");

		if (file.exists()) {
			printWriter.write("PASSED");
		}
		else {
			printWriter.write("FAILED");
		}

		printWriter.close();
	}

	private static Log _log = LogFactoryUtil.getLog(TestLogPortlet.class);

	private static Format _simpleDateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");

}