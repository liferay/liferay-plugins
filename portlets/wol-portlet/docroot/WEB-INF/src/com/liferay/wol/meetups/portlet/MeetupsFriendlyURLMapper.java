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

package com.liferay.wol.meetups.portlet;

import com.liferay.portal.kernel.portlet.BaseFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Map;

import javax.portlet.PortletMode;
import javax.portlet.WindowState;

/**
 * <a href="MeetupsFriendlyURLMapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MeetupsFriendlyURLMapper extends BaseFriendlyURLMapper {

	public String buildPath(LiferayPortletURL portletURL) {
		String jspPage = GetterUtil.getString(
			portletURL.getParameter("jspPage"));
		long meetupsEntryId = GetterUtil.getLong(
			portletURL.getParameter("meetupsEntryId"));

		if ((jspPage.equals("/meetups/view_entry.jsp")) &&
			(meetupsEntryId > 0)) {

			portletURL.addParameterIncludedInPath("p_p_id");

			portletURL.addParameterIncludedInPath("jspPage");
			portletURL.addParameterIncludedInPath("meetupsEntryId");

			StringBuilder sb = new StringBuilder();

			sb.append(StringPool.SLASH);
			sb.append(_MAPPING);
			sb.append("/entry/");
			sb.append(meetupsEntryId);

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

	public void populateParams(
		String friendlyURLPath, Map<String, String[]> params) {

		int x = friendlyURLPath.indexOf("/", 1);

		int y = friendlyURLPath.indexOf("/", x + 1);

		if (y == -1) {
			return;
		}

		String type = friendlyURLPath.substring(x + 1, y);

		if (!type.equals("entry")) {
			return;
		}

		x = friendlyURLPath.indexOf("/", x + 1);

		if (x == -1) {
			return;
		}

		long meetupsEntryId = GetterUtil.getLong(
			friendlyURLPath.substring(x + 1));

		if (meetupsEntryId <= 0) {
			return;
		}

		addParam(params, "p_p_id", _PORTLET_ID);
		addParam(params, "p_p_lifecycle", "0");
		addParam(params, "p_p_state", WindowState.MAXIMIZED);
		addParam(params, "p_p_mode", PortletMode.VIEW);

		addParam(params, "jspPage", "/meetups/view_entry.jsp");
		addParam(params, "meetupsEntryId", String.valueOf(meetupsEntryId));
	}

	private static final String _MAPPING = "meetups";

	private static final String _PORTLET_ID = "9_WAR_wolportlet";

}