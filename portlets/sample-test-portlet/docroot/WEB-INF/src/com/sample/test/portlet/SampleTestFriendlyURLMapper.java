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

package com.sample.test.portlet;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.StringMaker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.portlet.BaseFriendlyURLMapper;

import java.util.Map;

import javax.portlet.PortletMode;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import javax.portlet.WindowState;

/**
 * <a href="SampleTestFriendlyURLMapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SampleTestFriendlyURLMapper extends BaseFriendlyURLMapper {

	public String getMapping() {
		return _MAPPING;
	}

	public String getPortletId() {
		return _PORTLET_ID;
	}

	public String buildPath(LiferayPortletURL portletURL) {
		WindowState windowState = portletURL.getWindowState();

		String jspPage = GetterUtil.getString(
			portletURL.getParameter("jspPage"));

		if ((Validator.isNotNull(jspPage)) ||
			(windowState == null) || (windowState.equals(WindowState.NORMAL))) {

			portletURL.addParameterIncludedInPath("p_p_id");
			portletURL.addParameterIncludedInPath("jspPage");

			StringMaker sm = new StringMaker();

			sm.append(StringPool.SLASH);
			sm.append(_MAPPING);
			sm.append(jspPage);

			return sm.toString();
		}

		return null;
	}

	public void populateParams(String friendlyURLPath, Map params) {
		params.put("p_p_id", _PORTLET_ID);
		params.put("p_p_action", "0");
		params.put("p_p_mode", PortletMode.VIEW.toString());

		int x = friendlyURLPath.indexOf("/", 1);

		if ((x + 1) == friendlyURLPath.length()) {
			return;
		}

		String jspPage = friendlyURLPath.substring(x);

		addParam(params, "jspPage", jspPage);
	}

	private static final String _MAPPING = "sample_test";

	private static final String _PORTLET_ID =
		"sample_test_portlet_WAR_sampletestportlet";

}