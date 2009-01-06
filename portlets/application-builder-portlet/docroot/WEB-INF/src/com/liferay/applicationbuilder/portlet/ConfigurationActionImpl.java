/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * <a href="ConfigurationActionImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 * @autror Alberto Montero
 *
 */
public class ConfigurationActionImpl implements ConfigurationAction {

	public static final String DEFAULT_LANGUAGE = "ruby";
	public static final String DEFAULT_SCRIPT =
		"out = $portletResponse.getPortletOutputStream\n" +
			"out.println '<h2>Built by Application Builder in Ruby</h2>'\n" +
			"out.println '<p>The current time is: ' + `date` + '</p>'\n" +
			"out.println 'The following technologies were used::'\n" +
			"out.println '<ul>'\n" +
			"['Liferay', 'Portlets', 'Jruby', 'Apache BSF'].each() {|it|\n" +
			"\tout.println '<li>' + it + '</li>'\n" +
			"}\n" +
			"out.println '</ul>'";

	protected static Map<String, ScriptingLanguageInfo> _supportedLanguagesInfo;

	static {
		_supportedLanguagesInfo = new HashMap<String, ScriptingLanguageInfo>();

		_supportedLanguagesInfo.put(
			"ruby",
			new ScriptingLanguageInfo(
				"org.jruby.javasupport.bsf.JRubyEngine", "rb", "ruby"));
		_supportedLanguagesInfo.put(
			"groovy",
			new ScriptingLanguageInfo(
				"org.codehaus.groovy.bsf.GroovyEngine", "groovy", "groovy"));
		_supportedLanguagesInfo.put(
			"python",
			new ScriptingLanguageInfo(
				"org.apache.bsf.engines.jython.JythonEngine", "py", "python"));
		_supportedLanguagesInfo.put(
			"javascript",
			new ScriptingLanguageInfo(
				"", "js",
				"javascript"));
	}

	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		PortletPreferences prefs =
			PortletPreferencesFactoryUtil.getPortletSetup(
				actionRequest, portletResource);

		String language = ParamUtil.getString(actionRequest, "language");
		String script = ParamUtil.getString(actionRequest, "script");

		prefs.setValue("language", language);
		prefs.setValue("script", script);

		prefs.store();

		SessionMessages.add(
			actionRequest, portletConfig.getPortletName() + ".doConfigure");
	}

	public String render(
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		return "/application_builder/configuration.jsp";
	}

	public static Map getSupportedLanguagesInfo() {
		return _supportedLanguagesInfo;
	}

}