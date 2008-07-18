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

package com.liferay.applicationbuilder.portlet;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;

import java.io.IOException;
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
 * <a href="ApplicationBuilderPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class ApplicationBuilderPortlet extends GenericPortlet {

	public void init() {
		BSFManager.registerScriptingEngine(
			_SCRIPTING_ENGINE_LANGUAGE, _SCRIPTING_ENGINE_CLASS_NAME,
			new String[] {_SCRIPTING_ENGINE_EXTENSION});

		bsfManager = new BSFManager();
	}

	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException {

		String script = renderRequest.getPreferences().getValue(
			"script", ConfigurationActionImpl.DEFAULT_SCRIPT);

		renderResponse.setContentType(ContentTypes.TEXT_HTML);

		if (Validator.isNotNull(script)) {
			include(script, renderRequest, renderResponse);
		}
	}

	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException {
	}

	public void serveResource(ResourceRequest req, ResourceResponse res)
		throws IOException, PortletException {

		String cmd = ParamUtil.getString(req, Constants.CMD);

		if (cmd.equals("exec")) {
			ThemeDisplay themeDisplay = (ThemeDisplay)req.getAttribute(
				WebKeys.THEME_DISPLAY);

			try {
				PortletPermissionUtil.check(
					themeDisplay.getPermissionChecker(), getPortletName(),
					ActionKeys.CONFIGURATION);

				res.setContentType(ContentTypes.TEXT_HTML);

				String script = ParamUtil.getString(req, "script");

				includeScript(script, req, res);
			}
			catch(SystemException se){
				_log.warn("Error checking permissions", se);
			}
			catch(PortalException pe){
				_log.warn("Error checking permissions", pe);
			}
		}
		else {
			super.serveResource(req, res);
		}
	}

	protected void declareBeans(
			String script, PortletRequest portletRequest,
			PortletResponse portletResponse)
		throws BSFException, IOException {

		PortletConfig portletConfig = getPortletConfig();
		PortletContext portletContext = getPortletContext();
		PortletPreferences preferences = portletRequest.getPreferences();
		Map<String, String> userInfo =
			(Map<String, String>)portletRequest.getAttribute(
				PortletRequest.USER_INFO);

		bsfManager.declareBean(
			"portletConfig", portletConfig, PortletConfig.class);
		bsfManager.declareBean(
			"portletContext", portletContext, PortletContext.class);
		bsfManager.declareBean(
			"preferences", preferences, PortletPreferences.class);
		bsfManager.declareBean("userInfo", userInfo, Map.class);

		bsfManager.declareBean(
			"portletRequest", portletRequest, PortletRequest.class);

		if (portletRequest instanceof ActionRequest) {
			bsfManager.declareBean(
				"actionRequest", portletRequest, ActionRequest.class);
		}
		else if (portletRequest instanceof RenderRequest) {
			bsfManager.declareBean(
				"renderRequest", portletRequest, RenderRequest.class);
		}
		else if (portletRequest instanceof ResourceRequest) {
			bsfManager.declareBean(
				"resourceRequest", portletRequest, ResourceRequest.class);
		}

		bsfManager.declareBean(
			"portletResponse", portletResponse, PortletResponse.class);

		if (portletResponse instanceof ActionResponse) {
			bsfManager.declareBean(
				"actionResponse", portletResponse, ActionResponse.class);
		}
		else if (portletResponse instanceof RenderResponse) {
			bsfManager.declareBean(
				"renderResponse", portletResponse, RenderResponse.class);
		}
		else if (portletResponse instanceof ResourceResponse) {
			bsfManager.declareBean(
				"resourceResponse", portletResponse, ResourceResponse.class);
		}

		bsfManager.exec(_SCRIPTING_ENGINE_LANGUAGE, "(Ruby)", 0, 0, script);
	}

	protected void include(
			String script, PortletRequest portletRequest,
			PortletResponse portletResponse)
		throws IOException {

		try {
			declareBeans(script, portletRequest, portletResponse);
		}
		catch (BSFException bsfe) {
			logBSFException(bsfe);
		}
	}

	protected void includeScript(
			String consoleInput, PortletRequest req, PortletResponse res)
		throws IOException {

		try {
			declareBeans(consoleInput, req, res);
		}
		catch (BSFException bsfe) {
			if (res instanceof MimeResponse) {
				MimeResponse mimeRes = (MimeResponse)res;

				mimeRes.setContentType(ContentTypes.TEXT_HTML_UTF8);

				OutputStream out = mimeRes.getPortletOutputStream();

				Throwable te = bsfe.getTargetException();

				out.write("\n@ERROR@\n".getBytes());

				te.printStackTrace(new PrintStream(out, true, StringPool.UTF8));

				out.close();
			}
			else {
				logBSFException(bsfe);
			}
		}
	}

	protected void logBSFException(BSFException bsfe) {
		String message =
			"The script has errors.";

		Throwable t = bsfe.getTargetException();

		if (t instanceof RaiseException) {
			RubyException re = ((RaiseException)t).getException();

			message +=
				re.message + " (" + re.getMetaClass().toString() + ")";

			System.err.println(message);

			System.err.println(t);
		}
		else {
			System.err.println(message + "\n" + t);
		}
	}

	private static final String _SCRIPTING_ENGINE_CLASS_NAME =
		"org.jruby.javasupport.bsf.JRubyEngine";

	private static final String _SCRIPTING_ENGINE_EXTENSION = "rb";

	private static final String _SCRIPTING_ENGINE_LANGUAGE = "ruby";

	private static Log _log = LogFactory.getLog(ApplicationBuilderPortlet.class);

	protected String editFile;
	protected String helpFile;
	protected String viewFile;
	protected String actionFile;
	protected String resourceFile;
	protected String[] globalFiles;
	protected BSFManager bsfManager;

}