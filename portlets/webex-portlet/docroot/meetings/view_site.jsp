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

<%
String backURL = ParamUtil.getString(request, "redirect");

long webExSiteId = ParamUtil.getLong(request, "webExSiteId");

WebExSite webExSite = WebExSiteServiceUtil.getWebExSite(webExSiteId);

request.setAttribute("view_site.jsp-webExSite", webExSite);
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	title="<%= webExSite.getName() %>"
/>

<aui:row cssClass="site">
	<aui:col cssClass="folder-column folder-column-first" width="<%= 75 %>">
		<dl class="property-list">
			<dt>
				<liferay-ui:message key="name" />:
			</dt>
			<dd>
				<%= HtmlUtil.escape(webExSite.getName()) %>
			</dd>
			<dt>
				<liferay-ui:message key="api-url" />:
			</dt>
			<dd>
				<%= webExSite.getApiURL() %>
			</dd>

			<c:if test="<%= Validator.isNotNull(webExSite.getLogin()) %>">
				<dt>
					<liferay-ui:message key="login" />:
				</dt>
				<dd>
					<%= HtmlUtil.escape(webExSite.getLogin()) %>
				</dd>
			</c:if>

			<c:if test="<%= Validator.isNotNull(webExSite.getPartnerKey()) %>">
				<dt>
					<liferay-ui:message key="partner-key" />:
				</dt>
				<dd>
					<%= HtmlUtil.escape(webExSite.getPartnerKey()) %>
				</dd>
			</c:if>

			<c:if test="<%= Validator.isNotNull(webExSite.getSiteKey()) %>">
				<dt>
					<liferay-ui:message key="site-key" />:
				</dt>
				<dd>
					<%= webExSite.getSiteKey() %>
				</dd>
			</c:if>
		</dl>

		<%@ include file="/meetings/accounts.jspf" %>
	</aui:col>

	<aui:col cssClass="detail-column detail-column-last" width="<%= 25 %>">
		<div class="folder-icon">
			<liferay-ui:icon
				cssClass="folder-avatar"
				image="../file_system/large/default"
				message='<%= LanguageUtil.get(request, "site") %>'
			/>

			<div class="site-name">
				<h4><%= HtmlUtil.escape(webExSite.getName()) %></h4>
			</div>
		</div>

		<%
		request.removeAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
		%>

		<liferay-util:include page="/meetings/site_action.jsp" servletContext="<%= application %>" />
	</aui:col>
</aui:row>