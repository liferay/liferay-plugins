<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<strong><liferay-ui:message key="welcome-to-the-sample-service-builder-portlet" /></strong>

<portlet:renderURL var="editFooURL">
	<portlet:param name="jspPage" value="/edit_foo.jsp" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:renderURL>

<aui:button-row>
	<aui:button href="<%= editFooURL %>" value="add-foo" />
</aui:button-row>

<liferay-ui:search-container>
	<liferay-ui:search-container-results
		results="<%= FooLocalServiceUtil.getFoos(searchContainer.getStart(), searchContainer.getEnd(), new FooField4Comparator()) %>"
		total="<%= FooLocalServiceUtil.getFoosCount() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.sampleservicebuilder.model.Foo"
		escapedModel="true"
		modelVar="foo"
	>
		<liferay-ui:search-container-column-text
			name="id"
			property="fooId"
			valign="top"
		/>

		<liferay-ui:search-container-column-text
			name="field1"
			valign="top"
		>
			<strong><%= foo.getField1() %></strong>

			<br />

			<div class="lfr-asset-categories">
				<liferay-ui:asset-categories-summary
					className="<%= Foo.class.getName() %>"
					classPK="<%= foo.getFooId() %>"
				/>
			</div>

			<div class="lfr-asset-tags">
				<liferay-ui:asset-tags-summary
					className="<%= Foo.class.getName() %>"
					classPK="<%= foo.getFooId() %>"
					message="tags"
				/>
			</div>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			property="field2"
			valign="top"
		/>

		<liferay-ui:search-container-column-text
			property="field3"
			valign="top"
		/>

		<liferay-ui:search-container-column-text
			name="field4"
			valign="top"
			value="<%= dateFormatDateTime.format(foo.getField4()) %>"
		/>

		<liferay-ui:search-container-column-text
			property="field5"
			valign="top"
		/>

		<liferay-ui:search-container-column-jsp
			path="/foo_action.jsp"
			valign="top"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>