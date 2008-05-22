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

package com.liferay.plugin.portlet;

import com.liferay.portal.kernel.util.ByteArrayMaker;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringMaker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.MimeResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jruby.RubyException;
import org.jruby.exceptions.RaiseException;

/**
 * <a href="RubyCodeConsolePortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 * @author Raymond Augé
 *
 */
public class RubyCodeConsolePortlet extends GenericPortlet {

	public void init() throws PortletException {
		viewFile = getInitParameter("view-file");
		globalFiles = StringUtil.split(getInitParameter("global-files"));

		BSFManager.registerScriptingEngine(
			"ruby", "org.jruby.javasupport.bsf.JRubyEngine",
			new String[] {"rb"});

		_manager = new BSFManager();
	}

	public void doView(RenderRequest req, RenderResponse res)
		throws IOException, PortletException {

		include(viewFile, req, res);
	}

	public void serveResource(ResourceRequest req, ResourceResponse res)
		throws PortletException, IOException {

		String cmd = ParamUtil.getString(req, Constants.CMD);

		if (cmd.equals("exec")) {
			String code = ParamUtil.getString(req, "code");

			includeCode(code, req, res);
		}
	}

	protected void declareBeans(
			InputStream is, PortletRequest req, PortletResponse res)
		throws BSFException, IOException {

		declareBeans(new String(getBytes(is, -1)), req, res);
	}

	protected void declareBeans(
			String code, PortletRequest req, PortletResponse res)
		throws BSFException, IOException {

		StringMaker sm = new StringMaker();

		sm.append(getGlobalScript());
		sm.append(code);

		String script = sm.toString();

		PortletConfig portletConfig = getPortletConfig();
		PortletContext portletContext = getPortletContext();
		PortletPreferences preferences = req.getPreferences();

		_manager.declareBean(
			"portletConfig", portletConfig, PortletConfig.class);
		_manager.declareBean(
			"portletContext", portletContext, PortletContext.class);
		_manager.declareBean(
			"preferences", preferences, PortletPreferences.class);

		if (req instanceof ActionRequest) {
			_manager.declareBean("actionRequest", req, ActionRequest.class);
		}
		else if (req instanceof RenderRequest) {
			_manager.declareBean("renderRequest", req, RenderRequest.class);
		}
		else if (req instanceof ResourceRequest) {
			_manager.declareBean("resourceRequest", req, ResourceRequest.class);
		}

		if (res instanceof ActionResponse) {
			_manager.declareBean("actionResponse", res, ActionResponse.class);
		}
		else if (res instanceof RenderResponse) {
			_manager.declareBean("renderResponse", res, RenderResponse.class);
		}
		else if (res instanceof ResourceResponse) {
			_manager.declareBean("resourceResponse", res, ResourceResponse.class);
		}

		_manager.exec("ruby", "(java)", 1, 1, script);
	}

	protected byte[] getBytes(InputStream in, int bufferSize)
		throws IOException {

		ByteArrayMaker out = null;

		if (bufferSize <= 0) {
			out = new ByteArrayMaker();
		}
		else {
			out = new ByteArrayMaker(bufferSize);
		}

		boolean createBuffered = false;

		try {
			if (!(in instanceof BufferedInputStream)) {
				in = new BufferedInputStream(in);

				createBuffered = true;
			}

			int c = in.read();

			while (c != -1) {
				out.write(c);

				c = in.read();
			}
		}
		finally {
			if (createBuffered) {
				in.close();
			}
		}

		out.close();

		return out.toByteArray();
	}

	protected String getGlobalScript() throws IOException {
		StringMaker sm = new StringMaker();

		for (int i = 0; i < globalFiles.length; i++) {
			InputStream is = getPortletContext().getResourceAsStream(
				globalFiles[i]);

			if (is == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Global file " + globalFiles[i] + " does not exist");
				}
			}

			try {
				if (is != null) {
					sm.append(new String(getBytes(is, -1)));
					sm.append(StringPool.NEW_LINE);
				}
			}
			finally {
				is.close();
			}
		}

		return sm.toString();
	}

	protected void include(String path, PortletRequest req, PortletResponse res)
		throws IOException, PortletException {

		InputStream is = getPortletContext().getResourceAsStream(path);

		if (is == null) {
			_log.error(path + " is not a valid ruby file");

			return;
		}

		try {
			declareBeans(is, req, res);
		}
		catch (BSFException bsfe) {
			String message =
				"The script at " + path + " or one of the global files has "
					+ "errors: ";

			Throwable te = bsfe.getTargetException();

			if (te instanceof RaiseException) {
				RubyException re = ((RaiseException)te).getException();

				message +=
					re.message + " (" + re.getMetaClass().toString() + ")";

				_log.error(message);

				if (_log.isDebugEnabled()) {
					_log.debug("Ruby exception:", te);
				}
			}
			else {
				_log.error(message, te);
			}
		}
		finally {
			is.close();
		}
	}

	protected void includeCode(String code, PortletRequest req, PortletResponse res)
		throws IOException, PortletException {

		try {
			declareBeans(code, req, res);
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
				String message =
					"The script included or one of the global files has "
						+ "errors: ";

				Throwable te = bsfe.getTargetException();

				if (te instanceof RaiseException) {
					RubyException re = ((RaiseException)te).getException();

					message +=
						re.message + " (" + re.getMetaClass().toString() + ")";

					_log.error(message);

					if (_log.isDebugEnabled()) {
						_log.debug("Ruby exception:", te);
					}
				}
				else {
					_log.error(message, te);
				}
			}
		}
	}

	protected String viewFile;
	protected String[] globalFiles;

	private static Log _log = LogFactory.getLog(RubyCodeConsolePortlet.class);

	private BSFManager _manager;

}