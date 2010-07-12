<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PropsUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.service.UserLocalServiceUtil" %>
<%@ page import="com.liferay.testhook.hook.model.impl.TestUserImpl" %>
<%@ page import="com.liferay.testhook.util.TestHookUtil" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<h3>portal-properties</h3>

<p>
	<%= _testProperty("terms.of.use.required", false) %><br />
</p>

<p>
	application.startup.events=<%= _assertTrue(TestHookUtil.getStartupActionFile().exists()) %>
</p>

<p>
	<%= _testProperty("field.enable.com.liferay.portal.model.Contact.male", false) %><br />
	<%= _testProperty("field.enable.com.liferay.portal.model.Contact.birthday", false) %><br />
	<%= _testProperty("field.enable.com.liferay.portal.model.Organization.status", true) %>
</p>

<h3>language-properties</h3>

<p>
	javax.portlet.title.33=<%= _assertEquals("Blogger", LanguageUtil.get(pageContext, "javax.portlet.title.33")) %>
</p>

<h3>custom-jsp-dir</h3>

<liferay-util:buffer var="blogsViewJsp">
	<liferay-util:include page="/html/portlet/blogs/view.jsp" />
</liferay-util:buffer>

<p>
	/META-INF/custom_jsps=<%= _assertTrue(blogsViewJsp.contains("Custom Blogs Header")) %>
</p>

<h3>service</h3>

<p>
	com.liferay.portal.service.UserLocalService=<%= _assertEquals(TestUserImpl.class.getName(),
		UserLocalServiceUtil.getUserByEmailAddress(themeDisplay.getCompanyId(),"test@liferay.com").getClass().getName()) %>
</p>

<%!
private static String _assertEquals(Object expected, Object actual) {
	return _assertTrue(Validator.equals(expected, actual));
}

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