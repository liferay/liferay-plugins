<%
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
%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PropsUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.sampletesthook.util.SampleTestHookUtil" %>

<portlet:defineObjects />

<p>
	<%= _testProperty("terms.of.use.required", false) %><br />
</p>

<p>
	application.startup.events=<%= _assertTrue(SampleTestHookUtil.getStartupActionFile().exists()) %>
</p>

<p>
	<%= _testProperty("field.enable.com.liferay.portal.model.Contact.male", false) %><br />
	<%= _testProperty("field.enable.com.liferay.portal.model.Contact.birthday", false) %><br />
	<%= _testProperty("field.enable.com.liferay.portal.model.Organization.status", true) %>
</p>

<%!
private static String _assertFalse(boolean value) {
	return _assertTrue(!value);
}

private static String _assertFalse(String value) {
	return _assertFalse(GetterUtil.getBoolean(value));
}

private static String _assertTrue(boolean value) {
	if (value) {
		return "PASSED";
	}
	else {
		return "FAILED";
	}
}

private static String _assertTrue(String value) {
	return _assertTrue(GetterUtil.getBoolean(value));
}

private static String _testProperty(String key, boolean expected) throws Exception {
	StringBuilder sb = new StringBuilder();

	sb.append(key);
	sb.append(StringPool.EQUAL);

	String actual = PropsUtil.get(key);

	if (expected) {
		sb.append(_assertTrue(actual));
	}
	else {
		sb.append(_assertFalse(actual));
	}

	return sb.toString();
}
%>