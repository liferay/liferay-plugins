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

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;

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
		String packagePrefix =
			portletConfig.getInitParameter(ActionCache.ACTION_PACKAGE_NAME);
		if (Validator.isNotNull(packagePrefix)) {
			_checkActionClass = true;
			_cache = new ActionCache(packagePrefix);
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
			if (actionName.indexOf(',') == -1) {
				Action action = _cache.getAction(actionName);
				if (action != ActionCache.EMPTY_ACTION) {
					return action.processAction(actionRequest, actionResponse);
				}
			}
			else {
				//find multiple actions and process..
				List<Action> actions = _cache.getActionChain(actionName);
				int size = actions.size();
				if (size != 0) {
					boolean finalResult = false;
					for (int i = 0; i < size; i++) {
						finalResult =
							actions.get(i).processAction(
								actionRequest, actionResponse);
					}
					return finalResult;
				}
			}
		}
		return super.callActionMethod(actionRequest, actionResponse);
	}

	private boolean _checkActionClass;
	private ActionCache _cache;
}
