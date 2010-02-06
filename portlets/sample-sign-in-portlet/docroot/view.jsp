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

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.CookieNotSupportedException" %>
<%@ page import="com.liferay.portal.NoSuchUserException" %>
<%@ page import="com.liferay.portal.PasswordExpiredException" %>
<%@ page import="com.liferay.portal.UserEmailAddressException" %>
<%@ page import="com.liferay.portal.UserLockoutException" %>
<%@ page import="com.liferay.portal.UserPasswordException" %>
<%@ page import="com.liferay.portal.UserScreenNameException" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.MethodInvoker" %>
<%@ page import="com.liferay.portal.kernel.util.MethodWrapper" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PortalClassInvoker" %>
<%@ page import="com.liferay.portal.kernel.util.PropsUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.security.auth.AuthException" %>

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

		<%= LanguageUtil.format(pageContext, "you-are-signed-in-as-x", signedInAs) %>
	</c:when>
	<c:otherwise>

		<%
		String login = GetterUtil.getString((String)PortalClassInvoker.invoke("com.liferay.portal.action.LoginAction", "getLogin", request, "login", company, false));
		boolean rememberMe = ParamUtil.getBoolean(request, "rememberMe");
		%>

		<form action="<portlet:actionURL><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UPDATE %>" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm">
		<input name="save_last_path" type="hidden" value="0" />
		<input name="<portlet:namespace />rememberMe" type="hidden" value="<%= rememberMe %>" />

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
				<liferay-ui:message key="login" />
			</td>
			<td>
				<input name="<portlet:namespace />login" style="width: 120px;" type="text" value="<%= HtmlUtil.escape(login) %>" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="password" />
			</td>
			<td>
				<input id="<portlet:namespace />password" name="<portlet:namespace />password" style="width: 120px;" type="password" value="" />

				<span id="<portlet:namespace />passwordCapsLockSpan" style="display: none;"><liferay-ui:message key="caps-lock-is-on" /></span>
			</td>
		</tr>

		<c:if test='<%= company.isAutoLogin() && !GetterUtil.getBoolean(PropsUtil.get("session.disabled")) %>'>
			<tr>
				<td>
					<span style="font-size: xx-small;">
					<liferay-ui:message key="remember-me" />
					</span>
				</td>
				<td>
					<input <%= rememberMe ? "checked" : "" %> type="checkbox"
						onClick="
							if (this.checked) {
								document.<portlet:namespace />fm.<portlet:namespace />rememberMe.value = 'on';
							}
							else {
								document.<portlet:namespace />fm.<portlet:namespace />rememberMe.value = 'off';
							}"
					>
				</td>
			</tr>
		</c:if>

		</table>

		<br />

		<input type="submit" value="<liferay-ui:message key="sign-in" />" />

		<c:if test="<%= company.isStrangers() %>">
			<input type="button" value="<liferay-ui:message key="create-account" />" onClick="self.location = '<%= themeDisplay.getURLCreateAccount() %>';" />
		</c:if>

		<c:if test="<%= company.isSendPassword() %>">
			<br /><br />

			<a href="<%= themeDisplay.getPathMain() %>/portal/login?tabs1=forgot-password" style="font-size: xx-small;">
			<liferay-ui:message key="forgot-password" />?
			</a>
		</c:if>

		</form>

		<script type="text/javascript">
			AUI().ready(
				function() {
					jQuery('#<portlet:namespace />password').keypress(
						function(event) {
							Liferay.Util.showCapsLock(event, '<portlet:namespace />passwordCapsLockSpan');
						}
					);
				}
			);

			<c:if test="<%= renderRequest.getWindowState().equals(WindowState.MAXIMIZED) %>">
				Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />login);
			</c:if>
		</script>
	</c:otherwise>
</c:choose>