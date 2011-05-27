<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
String topLink = ParamUtil.getString(request, "topLink", "contacts-home");

int socialRelationType = ParamUtil.getInteger(request, "socialRelationType");

boolean viewUser = GetterUtil.getBoolean((String)request.getAttribute("view_user.jsp-viewUser"));

PortletURL portletURL = renderResponse.createRenderURL();
%>

<div class="top-links-container">
	<div class="top-links">
		<div class="top-links-navigation">

			<%
			portletURL.setParameter("topLink", "contacts-home");
			%>

			<liferay-ui:icon
				cssClass="top-link"
				image="../aui/home"
				label="<%= true %>"
				message="contacts-home"
				url='<%= (topLink.equals("contacts-home") && (socialRelationType <= 0) && !viewUser) ? StringPool.BLANK : portletURL.toString() %>'
			/>

			<%
			portletURL.setParameter("topLink", "requests");
			%>

			<liferay-ui:icon
				cssClass="top-link"
				image="../aui/person"
				label="<%= true %>"
				message="requests"
				url='<%= topLink.equals("requests") ? StringPool.BLANK : portletURL.toString() %>'
			/>

			<%
			portletURL.setParameter("topLink", "find-people");
			%>

			<liferay-ui:icon
				cssClass="top-link last"
				image="../aui/search"
				label="<%= true %>"
				message="find-people"
				url='<%= topLink.equals("find-people") ? StringPool.BLANK : portletURL.toString() %>'
			/>
		</div>

		<liferay-portlet:renderURL varImpl="searchURL">
			<portlet:param name="jspPage" value="/contacts_center/view.jsp" />
			<portlet:param name="topLink" value="find-people" />
		</liferay-portlet:renderURL>

		<div class="contact-search">
			<aui:form action="<%= searchURL %>" method="get" name="searchFm">
				<liferay-portlet:renderURLParams varImpl="searchURL" />

				<span class="aui-search-bar">
					<aui:input inlineField="<%= true %>" id="keywords1" label="" name="keywords" size="30" title="search-users" type="text" />

					<aui:button type="submit" value="search" />
				</span>
			</aui:form>
		</div>
	</div>
</div>