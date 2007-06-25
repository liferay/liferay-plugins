/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
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

package com.sample.ruby.portlet;

import com.liferay.portal.kernel.util.StringMaker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.util.FileUtil;
import com.liferay.util.StringUtil;

import java.io.IOException;
import java.io.InputStream;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jruby.RubyException;
import org.jruby.exceptions.RaiseException;

/**
 * <a href="RubyPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class RubyPortlet extends GenericPortlet {

	public void init() throws PortletException {
		editFile = getInitParameter("edit-file");
		helpFile = getInitParameter("help-file");
		viewFile = getInitParameter("view-file");
		actionFile = getInitParameter("action-file");
		globalFiles = StringUtil.split(getInitParameter("global-files"));

		BSFManager.registerScriptingEngine(
			"ruby", "org.jruby.javasupport.bsf.JRubyEngine",
			new String[] {"rb"});

		_manager = new BSFManager();
	}

	public void doDispatch(RenderRequest req, RenderResponse res)
		throws IOException, PortletException {

		String jspPage = req.getParameter("rubyFile");

		if (jspPage != null) {
			include(jspPage, req, res);
		}
		else {
			super.doDispatch(req, res);
		}
	}

	public void doEdit(RenderRequest req, RenderResponse res)
		throws IOException, PortletException {

		if (req.getPreferences() == null) {
			super.doEdit(req, res);
		}
		else {
			include(editFile, req, res);
		}
	}

	public void doHelp(RenderRequest req, RenderResponse res)
		throws IOException, PortletException {

		include(helpFile, req, res);
	}

	public void doView(RenderRequest req, RenderResponse res)
		throws IOException, PortletException {

		include(viewFile, req, res);
	}

	public void processAction(ActionRequest req, ActionResponse res)
		throws IOException, PortletException {

		include(actionFile, req, res);
	}

	protected void declareBeans(
			InputStream is, PortletRequest req, PortletResponse res)
		throws BSFException, IOException {

		StringMaker sm = new StringMaker();

		sm.append(getGlobalScript());
		sm.append(new String(FileUtil.getBytes(is)));

		String script = sm.toString();

		PortletConfig portletConfig = getPortletConfig();
		PortletContext portletContext = getPortletContext();
		PortletPreferences preferences = req.getPreferences();
		Map userInfo = (Map)req.getAttribute(PortletRequest.USER_INFO);

		_manager.declareBean(
			"portletConfig", getPortletConfig(), PortletConfig.class);
		_manager.declareBean(
			"portletContext", getPortletContext(), PortletContext.class);
		_manager.declareBean(
			"preferences", preferences, PortletPreferences.class);
		_manager.declareBean("userInfo", userInfo, Map.class);

		if (req instanceof ActionRequest) {
			_manager.declareBean("actionRequest", req, ActionRequest.class);
		}
		else if (req instanceof RenderRequest) {
			_manager.declareBean("renderRequest", req, RenderRequest.class);
		}

		if (res instanceof ActionResponse) {
			_manager.declareBean("actionResponse", res, ActionResponse.class);
		}
		else if (res instanceof RenderResponse) {
			_manager.declareBean("renderResponse", res, RenderResponse.class);
		}

		_manager.exec("ruby", "(java)", 1, 1, script);
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
					sm.append(new String(FileUtil.getBytes(is)));
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

	protected String editFile;
	protected String helpFile;
	protected String viewFile;
	protected String actionFile;
	protected String[] globalFiles;

	private static Log _log = LogFactory.getLog(RubyPortlet.class);

	private BSFManager _manager;

}