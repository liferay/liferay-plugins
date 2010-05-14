<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>

<%@ page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %>

<%@ page import="javax.portlet.PortletPreferences" %>

<portlet:defineObjects />

<%
PortletPreferences preferences = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

String newsType = preferences.getValue("newsType", "domain");

boolean domain = newsType.equals("domain");
boolean friends = newsType.equals("friends");
boolean front = newsType.equals("front");
boolean search = newsType.equals("search");
boolean top = newsType.equals("top");
boolean user = newsType.equals("user");

boolean fallback = GetterUtil.getBoolean(preferences.getValue("fallback", "true"));
boolean newWindow = GetterUtil.getBoolean(preferences.getValue("newWindow", "false"));
boolean showDesc = GetterUtil.getBoolean(preferences.getValue("showDesc", "false"));
boolean showDiggs = GetterUtil.getBoolean(preferences.getValue("showDiggs", "true"));
boolean showFooter = GetterUtil.getBoolean(preferences.getValue("showFooter", "false"));
boolean showThumbs = GetterUtil.getBoolean(preferences.getValue("showThumbs", "false"));

int storyCount = GetterUtil.getInteger(preferences.getValue("storyCount", StringPool.BLANK), 5);

String apiSearch = preferences.getValue("apiSearch", StringPool.BLANK);
String friendsUsername = preferences.getValue("friendsUsername", StringPool.BLANK);
String height = preferences.getValue("height", StringPool.BLANK);
String minDate = preferences.getValue("minDate", StringPool.BLANK);
String newsFriends = preferences.getValue("newsFriends", "dugg");
String newsFront = preferences.getValue("newsFront", StringPool.BLANK);
String newsTop = preferences.getValue("newsTop", StringPool.BLANK);
String newsUser = preferences.getValue("newsUser", "dugg");
String searchSort = preferences.getValue("searchSort", StringPool.BLANK);
String sourcePopOrUp = preferences.getValue("sourcePopOrUp", "popular");
String searchTopics = preferences.getValue("searchTopics", StringPool.BLANK);
String url = preferences.getValue("url", StringPool.BLANK);
String urlSort = preferences.getValue("urlSort", "promote_date-desc");
String userUsername = preferences.getValue("userUsername", StringPool.BLANK);
String width = preferences.getValue("width", StringPool.BLANK);

String widgetDim = "height:" + height + "px;width:" + width + "px;";

%>