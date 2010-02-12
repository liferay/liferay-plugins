/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.rubyconsole.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.scripting.ScriptingException;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.util.bridges.ruby.RubyPortlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.portlet.MimeResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * <a href="RubyConsolePortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Raymond Augé
 *
 */
public class RubyConsolePortlet extends RubyPortlet {

	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

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