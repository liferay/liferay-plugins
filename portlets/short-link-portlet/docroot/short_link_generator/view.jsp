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
String shortURL = ParamUtil.getString(renderRequest, "shortURL");
%>

<c:choose>
	<c:when test="<%= Validator.isNotNull(shortURL) %>">
		<p class="short-link">
			<a href="//<%= HtmlUtil.escape(shortURL) %>"><%= HtmlUtil.escape(shortURL) %></a>
		</p>

		<aui:button-row>
			<aui:button onClick="<%= portletURL.toString() %>" type="cancel" value="back" />
		</aui:button-row>
	</c:when>
	<c:otherwise>
		<portlet:actionURL name="addShortLinkEntry" var="addShortLinkEntryURL" />

		<aui:form action="<%= addShortLinkEntryURL %>" method="post" name="fm">
			<liferay-ui:error exception="<%= ShortLinkEntryOriginalURLException.class %>" message="please-enter-a-valid-url" />

			<aui:input label="long-link" name="originalURL">
				<aui:validator name="maxLength">
					<%= ShortLinkEntryConstants.ORIGINAL_URL_MAXIMUM_SIZE %>
				</aui:validator>
				<aui:validator name="required" />
				<aui:validator name="url" />
			</aui:input>

			<aui:button-row>
				<aui:button type="submit" />
			</aui:button-row>
		</aui:form>
	</c:otherwise>
</c:choose>