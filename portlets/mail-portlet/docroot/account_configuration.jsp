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

<%@ include file="/init.jsp" %>

<%
String description = ParamUtil.getString(request, "description");
String emailAddress = ParamUtil.getString(request, "emailAddress");
boolean emailAddressSameAsUsername = ParamUtil.getBoolean(request, "emailAddressSameAsUsername");
String mailInHostName = ParamUtil.getString(request, "mailInHostName");
String mailInPort = ParamUtil.getString(request, "mailInPort");
boolean mailInSecure = ParamUtil.getBoolean(request, "mailInSecure");
String mailOutHostName = ParamUtil.getString(request, "mailOutHostName");
String mailOutPort = ParamUtil.getString(request, "mailOutPort");
boolean mailOutSecure = ParamUtil.getBoolean(request, "mailOutSecure");
boolean newAccount = ParamUtil.getBoolean(request, "newAccount");
String password = ParamUtil.getString(request, "password");
boolean preconfigured = ParamUtil.getBoolean(request, "preconfigured");
String title = ParamUtil.getString(request, "title");
String username = ParamUtil.getString(request, "username");
%>

<div class="account">
	<c:choose>
		<c:when test="<%= newAccount %>">
			<div class="title">
				<%= title %>
			</div>

			<table class="details">

			<c:if test="<%= Validator.isNotNull(description) %>">
				<tr>
					<td colspan="2">
						<span class="description">
							<%= description %>
						</span>
					</td>
				</tr>
			</c:if>

			<c:choose>
				<c:when test="<%= emailAddressSameAsUsername %>">
					<input class="email-address" type="hidden" value="<%= HtmlUtil.escape(emailAddress) %>" />

					<input class="email-address-same-as-username" type="hidden" value="<%= emailAddressSameAsUsername %>" />
				</c:when>
				<c:otherwise>
					<tr>
						<td>
							<liferay-ui:message key="email-address" />
						</td>
						<td>
							<input class="email-address" type="text" value="<%= HtmlUtil.escape(emailAddress) %>" />

							<input class="email-address-same-as-username" type="hidden" value="<%= emailAddressSameAsUsername %>" />
						</td>
					</tr>
				</c:otherwise>
			</c:choose>

			<tr>
				<td>
					<liferay-ui:message key="user-name" />
				</td>
				<td>
					<input class="username" type="text" value="<%= username %>" />
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="password" />
				</td>
				<td>
					<input class="password" type="password" value="<%= password %>" />
				</td>
			</tr>

			<c:choose>
				<c:when test="<%= preconfigured %>">
					<input class="in-hostname" type="hidden" value="<%= HtmlUtil.escape(mailInHostName) %>" />

					<input class="in-port" type="hidden" value="<%= HtmlUtil.escape(mailInPort) %>" />

					<input class="in-secure" type="hidden" value="<%= mailInSecure %>" />

					<input class="out-hostname" type="hidden" value="<%= HtmlUtil.escape(mailOutHostName) %>" />

					<input class="out-port" type="hidden" value="<%= HtmlUtil.escape(mailOutPort) %>" />

					<input class="out-secure" type="hidden" value="<%= mailOutSecure %>" />
				</c:when>
				<c:otherwise>
					<tr>
						<td>
							<liferay-ui:message key="incoming-imap-server" />
						</td>
						<td>
							<input class="in-hostname" type="text" value="<%= mailInHostName %>" />
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="incoming-port" />
						</td>
						<td>
							<input class="in-port" type="text" value="<%= mailInPort %>" />
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="use-secure-incoming-connection" />
						</td>
						<td>
							<input <%= mailInSecure ? "checked" : "" %> class="in-secure" type="checkbox" />
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="outgoing-smtp-server" />
						</td>
						<td>
							<input class="out-hostname" type="text" value="<%= mailOutHostName %>" />
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="outgoing-port" />
						</td>
						<td>
							<input class="out-port" type="text" value="<%= mailOutPort %>" />
						</td>
					</tr>
					<tr>
						<td>
							<liferay-ui:message key="use-secure-outgoing-connection" />
						</td>
						<td>
							<input <%= mailOutSecure ? "checked" : "" %> class="out-secure" type="checkbox" />
						</td>
					</tr>
				</c:otherwise>
			</c:choose>

			<tr>
				<td colspan="2">
					<input class="save-account" type="button" value="<liferay-ui:message key="save" />" />

					<input class="cancel-account" type="button" value="<liferay-ui:message key="cancel" />" />
				</td>
			</tr>
			</table>
		</c:when>
		<c:otherwise>
			<div class="title">
				<%= HtmlUtil.escape(emailAddress) %>
			</div>

			<input class="email-address" type="hidden" value="<%= HtmlUtil.escape(emailAddress) %>" />

			<table class="details">
			<tr>
				<td>
					<liferay-ui:message key="user-name" />
				</td>
				<td>
					<input class="username" type="text" value="<%= username %>" />
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="password" />
				</td>
				<td>
					<input class="password" type="password" value="<%= password %>" />
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="incoming-imap-server" />
				</td>
				<td>
					<input class="in-hostname" type="text" value="<%= mailInHostName %>" />
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="incoming-port" />
				</td>
				<td>
					<input class="in-port" type="text" value="<%= mailInPort %>" />
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="use-secure-incoming-connection" />
				</td>
				<td>
					<input <%= mailInSecure ? "checked" : "" %> class="in-secure" type="checkbox" />
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="outgoing-smtp-server" />
				</td>
				<td>
					<input class="out-hostname" type="text" value="<%= mailOutHostName %>" />
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="outgoing-port" />
				</td>
				<td>
					<input class="out-port" type="text" value="<%= mailOutPort %>" />
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="use-secure-outgoing-connection" />
				</td>
				<td>
					<input <%= mailOutSecure ? "checked" : "" %> class="out-secure" type="checkbox" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input class="save-account" type="button" value="<liferay-ui:message key="save" /> " />

					<input class="delete-account" type="button" value="<liferay-ui:message key="delete" /> " />

					<input class="cancel-account" type="button" value="<liferay-ui:message key="cancel" />" />
				</td>
			</tr>
			</table>
		</c:otherwise>
	</c:choose>
</div>