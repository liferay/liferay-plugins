/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
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

import org.apache.bsf.BSFException;

/**
 * <a href="RubyConsolePortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Raymond Augé
 *
 */
public class RubyConsolePortlet extends RubyPortlet {

	public void serveResource(ResourceRequest req, ResourceResponse res)
		throws PortletException, IOException {

		String cmd = ParamUtil.getString(req, Constants.CMD);

		if (cmd.equals("exec")) {
			String consoleInput = ParamUtil.getString(req, "consoleInput");

			includeConsoleInput(consoleInput, req, res);
		}
		else {
			super.serveResource(req, res);
		}
	}

	protected void includeConsoleInput(
			String consoleInput, PortletRequest req, PortletResponse res)
		throws IOException, PortletException {

		try {
			declareBeans(consoleInput, req, res);
		}
		catch (BSFException bsfe) {
			if (res instanceof MimeResponse) {
				MimeResponse mimeRes = (MimeResponse)res;

				mimeRes.setContentType(ContentTypes.TEXT_HTML_UTF8);

				OutputStream out = mimeRes.getPortletOutputStream();

				Throwable te = bsfe.getTargetException();

				te.printStackTrace(new PrintStream(out, true, StringPool.UTF8));

				out.close();
			}
			else {
				logBSFException(bsfe, "Console input");
			}
		}
	}

}