<%
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
%>

<%@ include file="/html/portlet/init.jsp" %>

<%
PortletPreferences prefs = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	prefs = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource, true, true);
}

String confType = prefs.getValue("conf-type", StringPool.BLANK);
String gadgetCode = prefs.getValue("gadget-code", StringPool.BLANK);

String gadgetId = prefs.getValue("gadget-id", StringPool.BLANK);
String title = prefs.getValue("title", StringPool.BLANK);
String borderId = prefs.getValue("border-id", StringPool.BLANK);
String width = prefs.getValue("width", StringPool.BLANK);
String height = prefs.getValue("height", StringPool.BLANK);
%>

<%@ include file="/html/portlet/google_gadget/register_gadgets.jsp" %>

<%
int gadgetIdPos = 0;

for (int i = 0; i < gadgets.length; i++) {
	if (gadgets[i][0].equals(gadgetId)) {
		gadgetIdPos = i;
	}
}
String gadgetURL = gadgets[gadgetIdPos][4];
String gadgetParams = gadgets[gadgetIdPos][5];

int borderIdPos = 0;

for (int i = 0; i < borders.length; i++) {
	if (borders[i][0].equals(borderId)) {
		borderIdPos = i;
	}
}

String border = borders[borderIdPos][2];

boolean customConf = confType.equals("custom");
%>