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

package com.liferay.sampletest.portlet;

import com.liferay.portal.kernel.portlet.BaseFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import javax.portlet.PortletMode;
import javax.portlet.WindowState;

/**
 * <a href="TestFriendlyURLMapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class TestFriendlyURLMapper extends BaseFriendlyURLMapper {

	public String buildPath(LiferayPortletURL portletURL) {
		String resourceID = portletURL.getResourceID();

		if (Validator.isNotNull(resourceID)) {
			portletURL.addParameterIncludedInPath("p_p_id");
			portletURL.addParameterIncludedInPath("p_p_lifecycle");
			portletURL.addParameterIncludedInPath("p_p_resource_id");
			portletURL.addParameterIncludedInPath("p_p_cacheability");

			StringBuilder sb = new StringBuilder();

			sb.append(StringPool.SLASH);
			sb.append(_MAPPING);
			sb.append(StringPool.SLASH);
			sb.append(resourceID);

			return sb.toString();
		}

		WindowState windowState = portletURL.getWindowState();

		String jspPage = GetterUtil.getString(
			portletURL.getParameter("jspPage"));

		if ((windowState == null) || (windowState.equals(WindowState.NORMAL)) ||
			(Validator.isNotNull(jspPage))) {

			portletURL.addParameterIncludedInPath("p_p_id");

			portletURL.addParameterIncludedInPath("jspPage");

			StringBuilder sb = new StringBuilder();

			sb.append(StringPool.SLASH);
			sb.append(_MAPPING);
			sb.append(jspPage);

			return sb.toString();
		}

		return null;
	}

	public String getMapping() {
		return _MAPPING;
	}

	public String getPortletId() {
		return _PORTLET_ID;
	}

	public void populateParams(String friendlyURLPath, Map params) {
		addParam(params, "p_p_id", _PORTLET_ID);
		addParam(params, "p_p_lifecycle", "0");
		addParam(params, "p_p_mode", PortletMode.VIEW);

		int x = friendlyURLPath.indexOf("/", 1);

		if ((x + 1) == friendlyURLPath.length()) {
			return;
		}

		String resourceID = friendlyURLPath.substring(x);

		if (resourceID.equals("/logo.png")) {
			addParam(params, "p_p_lifecycle", "2");
			addParam(params, "p_p_resource_id", resourceID.substring(1));

			return;
		}

		String jspPage = resourceID;

		addParam(params, "jspPage", jspPage);
	}

	private static final String _MAPPING = "sample_test";

	private static final String _PORTLET_ID = "1_WAR_sampletestportlet";

}