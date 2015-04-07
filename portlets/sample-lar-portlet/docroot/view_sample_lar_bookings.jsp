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

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="mvcPath" value="/view.jsp" />
</liferay-portlet:renderURL>

<liferay-ui:search-container
	emptyResultsMessage="there-are-no-results"
	iteratorURL="<%= iteratorURL %>"
	total="<%= SampleLARBookingLocalServiceUtil.getSampleLARBookingsCount(scopeGroupId) %>"
>
	<liferay-ui:search-container-results
		results="<%= SampleLARBookingLocalServiceUtil.getSampleLARBookings(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.samplelar.model.SampleLARBooking"
		keyProperty="sampleLARBookingId"
		modelVar="sampleLARBooking"
	>
		<liferay-ui:search-container-column-text
			name="booking-id"
			value="<%= String.valueOf(sampleLARBooking.getSampleLARBookingId()) %>"
		/>

		<liferay-ui:search-container-column-text
			name="booking-number"
			value="<%= HtmlUtil.escape(sampleLARBooking.getBookingNumber()) %>"
		/>

		<liferay-ui:search-container-column-text>
			<portlet:actionURL name="deleteSampleLARBooking" var="deleteSampleLARBookingURL">
				<portlet:param name="mvcPath" value="/view.jsp" />
				<portlet:param name="sampleLARBookingId" value="<%= String.valueOf(sampleLARBooking.getSampleLARBookingId()) %>" />
			</portlet:actionURL>

			<liferay-ui:icon-delete
				trash="<%= false %>"
				url="<%= deleteSampleLARBookingURL %>"
			/>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>