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
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;

import java.io.IOException;
import java.io.OutputStream;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.MimeResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.PortletRequestDispatcher;

import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jruby.RubyException;
import org.jruby.exceptions.RaiseException;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

/**
 * <a href="ApplicationBuilderPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class ApplicationBuilderPortlet extends GenericPortlet {

	public void init() {
		bsfManager = new BSFManager();
	}

	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		String language = renderRequest.getPreferences().getValue(
			"language", ConfigurationActionImpl.DEFAULT_LANGUAGE);
		String script = renderRequest.getPreferences().getValue(
			"script", ConfigurationActionImpl.DEFAULT_SCRIPT);

		renderResponse.setContentType(ContentTypes.TEXT_HTML);

		if (Validator.isNotNull(script)) {
			include(script, language, renderRequest, renderResponse);
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

				String language = ParamUtil.getString(req, "language");
				String script = ParamUtil.getString(req, "script");

				includeScript(script, language, req, res);
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
			String script, String language, PortletRequest portletRequest,
			PortletResponse portletResponse)
		throws Exception {

		PortletConfig portletConfig = getPortletConfig();
		PortletContext portletContext = getPortletContext();

		if (language.equals("javascript")) {
			try {
				Context context = Context.enter();
				Scriptable scope = context.initStandardObjects();

				BeanDeclarator.declareRhinoBeans(
					scope, portletConfig, portletContext, portletRequest,
					portletResponse);

				context.evaluateString(
					scope, script, "javascript script", 1, null);
			}
			finally {
				Context.exit();
			}
		}
		else {
			BeanDeclarator.declareBSFBeans(
				bsfManager, portletConfig, portletContext, portletRequest,
				portletResponse);

			ScriptingLanguageInfo selectedLanguageInfo =
				(ScriptingLanguageInfo)ConfigurationActionImpl.
					getSupportedLanguagesInfo().get(language);

			BSFManager.registerScriptingEngine(
				language, selectedLanguageInfo.getScriptingEngineClassName(),
				new String[] {
					selectedLanguageInfo.getScriptingEngineExtension()});

			bsfManager.exec(language, language + " script", 0, 0, script);
		}
	}

	protected void include(
			String script, String language, PortletRequest portletRequest,
			PortletResponse portletResponse)
		throws IOException, PortletException {

		try {
			declareBeans(script, language, portletRequest, portletResponse);
		}
		catch (Exception ex) {
			SessionErrors.add(portletRequest, "script-execution-error");
			_log.error("\n@ERROR@\n" + ex.getMessage() + "\n" + script + "\n");

			PortletRequestDispatcher prd =
				getPortletContext().
					getRequestDispatcher("/application_builder/error.jsp");

			prd.include(portletRequest, portletResponse);
		}
	}

	protected void includeScript(
			String consoleInput, String language, PortletRequest req,
			PortletResponse res)
		throws IOException {

		try {
			declareBeans(consoleInput, language, req, res);
		}
		catch (Exception ex) {
			if (res instanceof MimeResponse) {
				MimeResponse mimeRes = (MimeResponse)res;

				mimeRes.setContentType(ContentTypes.TEXT_HTML_UTF8);

				OutputStream out = mimeRes.getPortletOutputStream();

				out.write(("\n@ERROR@\n" + ex.getMessage() + "\n").getBytes());

				out.close();
			}
			else {
				_log.error("\n@ERROR@\n" + ex.getMessage() + "\n");
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

	private static Log _log =
			LogFactory.getLog(ApplicationBuilderPortlet.class);

	protected BSFManager bsfManager;

}