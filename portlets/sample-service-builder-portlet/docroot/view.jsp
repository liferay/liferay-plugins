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
PortletURL currentURLObj = PortletURLUtil.getCurrent(PortalUtil.getLiferayPortletRequest(renderRequest), PortalUtil.getLiferayPortletResponse(renderResponse));

String[] field1 = ParamUtil.getStringValues(renderRequest, "field1");

SearchContainer<Foo> fooSearchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, currentURLObj, null, "no-entries-were-found");

fooSearchContainer.setForcePost(true);

if (field1.length == 0) {
	fooSearchContainer.setTotal(FooLocalServiceUtil.getFoosCount());

	fooSearchContainer.setResults(FooLocalServiceUtil.getFoos(fooSearchContainer.getStart(), fooSearchContainer.getEnd(), new FooField4Comparator()));
}
else {
	fooSearchContainer.setTotal(FooLocalServiceUtil.getField1FoosCount(field1));

	fooSearchContainer.setResults(FooLocalServiceUtil.getField1Foos(field1, fooSearchContainer.getStart(), fooSearchContainer.getEnd(), new FooField4Comparator()));
}
%>

<strong><liferay-ui:message key="welcome-to-the-sample-service-builder-portlet" /></strong>

<aui:button-row>
	<portlet:renderURL var="editFooURL">
		<portlet:param name="mvcPath" value="/edit_foo.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
	</portlet:renderURL>

	<aui:button href="<%= editFooURL %>" value="add-foo" />
</aui:button-row>

<liferay-ui:search-container
	searchContainer="<%= fooSearchContainer %>"
>
	<portlet:renderURL var="refreshURL" windowState="normal">
		<portlet:param name="mvcPath" value="/view.jsp" />
	</portlet:renderURL>

	<aui:form action="<%= refreshURL %>" method="post" name="fm">
		<section>
			<article>
				<aui:select label="Select" multiple="true" name="field1" />

				<aui:script use="liferay-dynamic-select">
					var data = function(callback) {
						Liferay.Service(
							'/sample-service-builder-portlet.foo/get-foos',
							callback
						);
					};

					new Liferay.DynamicSelect(
						[
							{
								select: '<portlet:namespace />field1',
								selectData: data,
								selectDesc: 'field1',
								selectId: 'field1',
								selectSort: '',
								selectVal: ['<%= StringUtil.merge(field1, "','") %>']
							}
						]
					);
				</aui:script>

				<aui:button type="submit" value="search" />
			</article>
		</section>
	</aui:form>

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

		<liferay-ui:search-container-column-date
			name="field4"
			valign="top"
			value="<%= foo.getField4() %>"
		/>

		<liferay-ui:search-container-column-text
			property="field5"
			valign="top"
		/>

		<liferay-ui:search-container-column-jsp
			cssClass="entry-action"
			path="/foo_action.jsp"
			valign="top"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>