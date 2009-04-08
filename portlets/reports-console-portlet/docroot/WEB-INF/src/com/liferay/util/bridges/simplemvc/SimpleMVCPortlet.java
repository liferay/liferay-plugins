/*
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

package com.liferay.util.bridges.simplemvc;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.bridges.jsp.JSPPortlet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="SimplMVCPortlet.java.html"><b><i>View Source</i></b></a>
 * <p/>
 * Simple MVC type portlet that will use reflection to find the Action that
 * handles the specific ActionRequest.
 * <p/>
 * The naming convention for Action classes are is NameOfAction + Action.  So
 * for an action called sendMail, the expected Action class is SendMailAction.
 *
 * @author Michael C. Han
 */
public class SimpleMVCPortlet extends JSPPortlet {

	@Override
	public void init(PortletConfig portletConfig) throws PortletException {
		super.init(portletConfig);
		_packagePrefix =
			portletConfig.getInitParameter(_ACTION_PACKAGE_NAME);
		if (Validator.isNotNull(_packagePrefix)) {
			_checkActionClass = true;
			if (!_packagePrefix.endsWith(StringPool.PERIOD)) {
				_packagePrefix = _packagePrefix + StringPool.PERIOD;
			}
		}
	}

	protected boolean callActionMethod(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

 		String actionName = ParamUtil.getString(
			actionRequest, ActionRequest.ACTION_NAME);

		if (Validator.isNull(actionName)) {
			return false;
		}

		if (_checkActionClass) {
			Action action = getAction(actionName);
			if (action != _EMPTY_ACTION) {
				return action.processAction(actionRequest, actionResponse);
			}
		}
		return super.callActionMethod(actionRequest, actionResponse);
	}

	private Action getAction(String actionName)
		throws PortletException {
		String className = null;
		try {
			//TBD Need to strip out so that we use the last path...
			//e.g. /reporting/addAction should be addAction
			Action action = _actionCache.get(actionName);
			if (action == null) {
				className =
					_packagePrefix +
					Character.toUpperCase(actionName.charAt(0)) +
					actionName.substring(1, actionName.length()) +
					_ACTION_POSTFIX;
				action =
					(Action) Class.forName(className).newInstance();
				_actionCache.put(actionName, action);
			}

			return action;
		}
		catch (Exception e) {

			if (_log.isWarnEnabled()) {
				_log.warn("Unable to find an action class: " + className);
			}
			_actionCache.put(actionName, _EMPTY_ACTION);
			return _EMPTY_ACTION;
		}

	}

	private static final Action _EMPTY_ACTION = new Action() {
		public boolean processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortletException {
			return false;
		}
	};
	private static final Log _log = LogFactory.getLog(SimpleMVCPortlet.class);
	private static final String _ACTION_POSTFIX = "Action";
	private static final String _ACTION_PACKAGE_NAME = "action.package.prefix";

	private Map<String, Action> _actionCache =
		new ConcurrentHashMap<String, Action>();
	private String _packagePrefix;
	private boolean _checkActionClass;
}
