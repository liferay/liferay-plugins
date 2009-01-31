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
							<c:choose>
								<c:when test="<%= mailInSecure %>">
									<input class="in-secure" type="checkbox" checked="checked" />
								</c:when>
								<c:otherwise>
									<input class="in-secure" type="checkbox" />
								</c:otherwise>
							</c:choose>
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
							<c:choose>
								<c:when test="<%= mailOutSecure %>">
									<input class="out-secure" type="checkbox" checked="checked" />
								</c:when>
								<c:otherwise>
									<input class="out-secure" type="checkbox" />
								</c:otherwise>
							</c:choose>
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
					<c:choose>
						<c:when test="<%= mailInSecure  %>">
							<input class="in-secure" type="checkbox" checked="checked" />
						</c:when>
						<c:otherwise>
							<input class="in-secure" type="checkbox" />
						</c:otherwise>
					</c:choose>
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
					<c:choose>
						<c:when test="<%= mailOutSecure %>">
							<input class="out-secure" type="checkbox" checked="checked" />
						</c:when>
						<c:otherwise>
							<input class="out-secure" type="checkbox" />
						</c:otherwise>
					</c:choose>
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