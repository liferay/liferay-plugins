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
String redirect = ParamUtil.getString(request, "redirect");

long shortLinkEntryId = ParamUtil.getLong(request, "shortLinkEntryId");

ShortLinkEntry shortLinkEntry = null;

if (shortLinkEntryId > 0) {
	shortLinkEntry = ShortLinkEntryLocalServiceUtil.getShortLinkEntry(shortLinkEntryId);
}
%>

<portlet:actionURL name="updateShortLinkEntry" var="updateShortLinkEntryURL">
	<portlet:param name="mvcPath" value="/short_link_admin/edit_short_link_entry.jsp" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</portlet:actionURL>

<div class="link-shortener-edit-form">
	<aui:form action="<%= updateShortLinkEntryURL %>" method="post" name="fm">
		<aui:input name="shortLinkEntryId" type="hidden" value="<%= shortLinkEntryId %>" />

		<liferay-ui:error exception="<%= DuplicateShortLinkEntryException.class %>" message="the-short-link-is-already-used" />
		<liferay-ui:error exception="<%= ShortLinkEntryOriginalURLException.class %>" message="the-original-url-is-required-and-cannot-be-longer-than-4000-characters" />
		<liferay-ui:error exception="<%= ShortLinkEntryShortURLException.class %>" message="the-short-url-is-required-and-must-be-between-4-and-100-characters-long" />

		<aui:model-context bean="<%= shortLinkEntry %>" model="<%= ShortLinkEntry.class %>" />

		<aui:fieldset>
			<aui:input label="short-url" name="shortURL" prefix="<%= PortletPropsValues.SHORT_URL_HOSTNAME + StringPool.FORWARD_SLASH %>" />

			<aui:input label="original-url" name="originalURL" />

			<c:if test="<%= shortLinkEntry != null %>">
				<aui:input checked="<%= shortLinkEntry.getActive() %>" label="active" name="active" type="checkbox" />
			</c:if>
		</aui:fieldset>

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button onClick="<%= redirect %>" type="cancel" value="back" />
		</aui:button-row>
	</aui:form>
</div>