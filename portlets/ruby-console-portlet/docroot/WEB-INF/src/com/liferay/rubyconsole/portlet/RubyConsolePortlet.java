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

package com.liferay.rubyconsole.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.scripting.ScriptingException;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.util.bridges.ruby.RubyPortlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.portlet.MimeResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Raymond Augé
 */
public class RubyConsolePortlet extends RubyPortlet {

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		try {
			AuthTokenUtil.checkCSRFToken(
				PortalUtil.getOriginalServletRequest(
					PortalUtil.getHttpServletRequest(resourceRequest)),
				RubyConsolePortlet.class.getName());
		}
		catch (PrincipalException pe) {
			return;
		}

		String cmd = ParamUtil.getString(resourceRequest, Constants.CMD);

		if (cmd.equals("exec")) {
			String consoleInput = ParamUtil.getString(
				resourceRequest, "consoleInput");

			includeConsoleInput(
				consoleInput, resourceRequest, resourceResponse);
		}
		else {
			super.serveResource(resourceRequest, resourceResponse);
		}
	}

	protected void includeConsoleInput(
			String consoleInput, PortletRequest portletRequest,
			PortletResponse portletResponse)
		throws IOException {

		try {
			declareBeans(consoleInput, portletRequest, portletResponse);
		}
		catch (ScriptingException se) {
			if (portletResponse instanceof MimeResponse) {
				MimeResponse mimeResponse = (MimeResponse)portletResponse;

				mimeResponse.setContentType(ContentTypes.TEXT_HTML_UTF8);

				OutputStream out = mimeResponse.getPortletOutputStream();

				Throwable t = se.getCause();

				out.write("\n@ERROR@\n".getBytes());

				t.printStackTrace(new PrintStream(out, true, StringPool.UTF8));

				out.close();
			}
			else {
				_log.error("The configured script has errors", se);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(RubyConsolePortlet.class);

}