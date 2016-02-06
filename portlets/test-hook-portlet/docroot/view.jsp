<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
--%>

<%@ include file="/init.jsp" %>

<h3>portal-properties</h3>

<p>
	<%= _testProperty("terms.of.use.required", false) %><br />
</p>

<p>
	application.startup.events=<%= _assertTrue(TestHookUtil.getStartupActionFile().exists()) %>
</p>

<p>
	<%= _testProperty("field.enable.com.liferay.portal.kernel.model.Contact.male", false) %><br />
	<%= _testProperty("field.enable.com.liferay.portal.kernel.model.Contact.birthday", false) %><br />
	<%= _testProperty("field.enable.com.liferay.portal.kernel.model.Organization.status", true) %>
</p>

<h3>language-properties</h3>

<p>
	javax.portlet.title.33=<%= _assertEquals("Blogger", LanguageUtil.get(request, "javax.portlet.title.33")) %>
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
	com.liferay.portal.kernel.service.UserLocalService=<%= _assertEquals(TestHookUserImpl.class.getName(), UserLocalServiceUtil.getUserByEmailAddress(themeDisplay.getCompanyId(), "test@liferay.com").getClass().getName()) %>
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