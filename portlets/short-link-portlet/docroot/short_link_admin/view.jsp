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

<div class="link-shortener-content">
	<aui:button-row>
		<portlet:renderURL var="addShortLinkEntryURL">
			<portlet:param name="mvcPath" value="/short_link_admin/edit_short_link_entry.jsp" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		</portlet:renderURL>

		<aui:button name="addShortLinkButton" onClick="<%= addShortLinkEntryURL %>" value="add-short-link" />
	</aui:button-row>

	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-short-links-defined"
	>
		<liferay-ui:search-container-results
			results="<%= ShortLinkEntryLocalServiceUtil.getShortLinkEntries(false, searchContainer.getStart(), searchContainer.getEnd()) %>"
			total="<%= ShortLinkEntryLocalServiceUtil.getShortLinkEntriesCount() %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.shortlink.model.ShortLinkEntry"
			escapedModel="<%= true %>"
			keyProperty="shortLinkEntryId"
			modelVar="shortLinkEntry"
		>
			<portlet:renderURL var="editURL">
				<portlet:param name="mvcPath" value="/short_link_admin/edit_short_link_entry.jsp" />
				<portlet:param name="shortLinkEntryId" value="<%= String.valueOf(shortLinkEntry.getShortLinkEntryId()) %>" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= editURL %>"
				name="id"
				property="shortLinkEntryId"
			/>

			<%
			Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);
			%>

			<liferay-ui:search-container-column-text
				name="create-date"
				value="<%= dateFormatDateTime.format(shortLinkEntry.getCreateDate()) %>"
			/>

			<liferay-ui:search-container-column-text
				name="modified-date"
				value="<%= dateFormatDateTime.format(shortLinkEntry.getModifiedDate()) %>"
			/>

			<liferay-ui:search-container-column-text
				href='<%= "//" + PortletPropsValues.SHORT_URL_HOSTNAME + "/" + shortLinkEntry.getShortURL() %>'
				name="short-url"
				property="shortURL"
				target="_blank"
			/>

			<liferay-ui:search-container-column-text
				href="<%= shortLinkEntry.getOriginalURL() %>"
				name="original-url"
				property="originalURL"
				target="_blank"
			/>

			<liferay-ui:search-container-column-text
				name="active"
			>
				<c:choose>
					<c:when test="<%= shortLinkEntry.isActive() %>">
						<liferay-ui:icon image="activate" />
					</c:when>
					<c:otherwise>
						<liferay-ui:icon image="deactivate" />
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="actions"
			>
				<liferay-ui:icon-menu>
					<liferay-ui:icon image="edit" url="<%= editURL %>" />

					<portlet:actionURL name="deleteShortLinkEntry" var="deleteShortLinkEntryURL">
						<portlet:param name="shortLinkEntryId" value="<%= String.valueOf(shortLinkEntry.getShortLinkEntryId()) %>" />
						<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
					</portlet:actionURL>

					<liferay-ui:icon-delete url="<%= deleteShortLinkEntryURL %>" />
				</liferay-ui:icon-menu>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</div>