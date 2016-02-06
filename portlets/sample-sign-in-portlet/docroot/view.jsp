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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.exception.CookieNotSupportedException" %>
<%@ page import="com.liferay.portal.kernel.exception.NoSuchUserException" %>
<%@ page import="com.liferay.portal.kernel.exception.PasswordExpiredException" %>
<%@ page import="com.liferay.portal.kernel.exception.UserEmailAddressException" %>
<%@ page import="com.liferay.portal.kernel.exception.UserLockoutException" %>
<%@ page import="com.liferay.portal.kernel.exception.UserPasswordException" %>
<%@ page import="com.liferay.portal.kernel.exception.UserScreenNameException" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.model.Company" %>
<%@ page import="com.liferay.portal.kernel.security.auth.AuthException" %>
<%@ page import="com.liferay.portal.kernel.util.ClassResolverUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.MethodKey" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PortalClassInvoker" %>
<%@ page import="com.liferay.portal.kernel.util.PropsUtil" %>

<%@ page import="javax.portlet.WindowState" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<c:choose>
	<c:when test="<%= themeDisplay.isSignedIn() %>">

		<%
		String signedInAs = user.getFullName();

		if (themeDisplay.isShowMyAccountIcon()) {
			signedInAs = "<a href=\"" + themeDisplay.getURLMyAccount().toString() + "\">" + signedInAs + "</a>";
		}
		%>

		<%= LanguageUtil.format(request, "you-are-signed-in-as-x", signedInAs, false) %>
	</c:when>
	<c:otherwise>

		<%
		MethodKey methodKey = new MethodKey(ClassResolverUtil.resolveByPortalClassLoader("com.liferay.portlet.login.util.LoginUtil"), "getLogin", HttpServletRequest.class, String.class, Company.class);

		String login = GetterUtil.getString((String)PortalClassInvoker.invoke(false, methodKey, request, "login", company));

		boolean rememberMe = ParamUtil.getBoolean(request, "rememberMe");
		%>

		<portlet:actionURL var="loginURL" />

		<aui:form action="<%= loginURL %>" method="post" name="fm">
			<aui:input name="saveLastPath" type="hidden" value="<%= false %>" />
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
			<aui:input name="rememberMe" type="hidden" value="<%= rememberMe %>" />

			<liferay-ui:error exception="<%= AuthException.class %>" message="authentication-failed" />
			<liferay-ui:error exception="<%= CookieNotSupportedException.class %>" message="authentication-failed-please-enable-browser-cookies" />
			<liferay-ui:error exception="<%= NoSuchUserException.class %>" message="please-enter-a-valid-login" />
			<liferay-ui:error exception="<%= PasswordExpiredException.class %>" message="your-password-has-expired" />
			<liferay-ui:error exception="<%= UserEmailAddressException.class %>" message="please-enter-a-valid-login" />
			<liferay-ui:error exception="<%= UserLockoutException.class %>" message="this-account-has-been-locked" />
			<liferay-ui:error exception="<%= UserPasswordException.class %>" message="please-enter-a-valid-password" />
			<liferay-ui:error exception="<%= UserScreenNameException.class %>" message="please-enter-a-valid-screen-name" />

			<table class="lfr-table">
			<tr>
				<td>
					<aui:input name="login" style="width: 120px;" type="text" value="<%= HtmlUtil.escape(login) %>" />
				</td>
			</tr>
			<tr>
				<td>
					<aui:input name="password" style="width: 120px;" type="password" value="" />

					<span id="<portlet:namespace />passwordCapsLockSpan" style="display: none;"><liferay-ui:message key="caps-lock-is-on" /></span>
				</td>
			</tr>

			<c:if test='<%= company.isAutoLogin() && !GetterUtil.getBoolean(PropsUtil.get("session.disabled")) %>'>
				<tr>
					<td>
						<aui:input checked="<%= rememberMe %>" name="rememberMe" type="checkbox" />
					</td>
				</tr>
			</c:if>

			</table>

			<br />

			<input type="submit" value="<liferay-ui:message key="sign-in" />" />
		</aui:form>

		<c:if test="<%= renderRequest.getWindowState().equals(WindowState.MAXIMIZED) %>">
			<aui:script>
				Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />login);
			</aui:script>
		</c:if>

		<aui:script use="aui-base">
			A.one('#<portlet:namespace />password').on(
				'keypress',
				function(event) {
					Liferay.Util.showCapsLock(event, '<portlet:namespace />passwordCapsLockSpan');
				}
			);
		</aui:script>
	</c:otherwise>
</c:choose>