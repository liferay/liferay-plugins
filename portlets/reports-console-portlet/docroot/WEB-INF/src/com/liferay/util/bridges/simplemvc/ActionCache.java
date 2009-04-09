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

import com.liferay.portal.kernel.util.StringPool;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.PortletException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="ActionCache.java.html"><b><i>View Source</i></b></a>
 *
 * Cache for Portlet Actions to avoid reflection costs per call.
 *
 * @author Michael C. Han
 */
public class ActionCache {
	public static final String ACTION_PACKAGE_NAME = "action.package.prefix";
	public static final Action EMPTY_ACTION = new Action() {
		public boolean processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortletException {
			return false;
		}
	};

	public ActionCache(String packagePrefix) {
		if (!packagePrefix.endsWith(StringPool.PERIOD)) {
			packagePrefix = packagePrefix + StringPool.PERIOD;
		}
		_packagePrefix = packagePrefix;
	}

	public List<Action> getActionChain(String actionChain)
	throws PortletException {
		List<Action> actions = _actionChainCache.get(actionChain);
		if (actions == null) {
			actions = new ArrayList<Action>(5);
			int index = actionChain.indexOf(',');
			final int length = actionChain.length();
			int currentIndex = 0;
			do {
				final String parsedName =
					actionChain.substring(currentIndex, index);
				final Action action = getAction(parsedName);
				if (action != EMPTY_ACTION) {
					actions.add(action);
				}
				else {
					if (_log.isWarnEnabled()) {
						_log.warn("Unable to find action: " + actionChain);
					}
				}
				currentIndex = index + 1;
				index = actionChain.indexOf(',', currentIndex);
				index = (index == -1) ? length : index;
			} while ((currentIndex < length));
			_actionChainCache.put(actionChain, actions);
		}

		return actions;
	}

	public Action getAction(String actionName)
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
			_actionCache.put(actionName, EMPTY_ACTION);
			return EMPTY_ACTION;
		}

	}

	private static final Log _log = LogFactory.getLog(ActionCache.class);
	private static final String _ACTION_POSTFIX = "Action";

	private Map<String, Action> _actionCache =
		new ConcurrentHashMap<String, Action>();
	private Map<String, List<Action>> _actionChainCache =
		new ConcurrentHashMap<String, List<Action>>();
	private String _packagePrefix;
}
