<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

long entryId = ParamUtil.getLong(request, "entryId");

Entry entry = null;

if (entryId > 0) {
	entry = EntryLocalServiceUtil.getEntry(entryId);
}
%>

<div id="<portlet:namespace />errorMessage"></div>

<liferay-portlet:actionURL name="updateEntry" var="updateEntryURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>" />

<aui:form action="<%= updateEntryURL %>" method="post" name="addEntry" onSubmit="event.preventDefault();">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="entryId" type="hidden" value="<%= entryId %>" />

	<aui:model-context bean="<%= entry %>" model="<%= Entry.class %>" />

	<aui:input label="name" name="fullName" />

	<aui:input name="emailAddress" type="email" />

	<aui:input name="comments" />

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-io-request-deprecated,datatype-number">
	Liferay.Util.focusFormField(document.<portlet:namespace />addEntry.<portlet:namespace />fullName);

	var form = A.one('#<portlet:namespace />addEntry');

	var failureCallback = function() {
		var errorMessage = A.one('#<portlet:namespace />errorMessage');

		if (errorMessage) {
			errorMessage.addClass('alert alert-danger');

			errorMessage.html('<liferay-ui:message key="an-error-occurred-while-retrieving-the-users-information" unicode="<%= true %>" />');
		}
	};

	form.on(
		'submit',
		function(event) {
			var end = <%= ContactsConstants.MAX_RESULT_COUNT %>;

			var lastNameAnchor = '';

			var node = A.one('.more-results a');

			if (node) {
				end = A.DataType.Number.parse(node.getAttribute('data-end'));

				lastNameAnchor = node.getAttribute('data-lastNameAnchor');
			}

			var contactFilterSelect = A.one('#<portlet:namespace />filterBy');

			var searchInput = A.one('.contacts-portlet #<portlet:namespace />name');

			A.io.request(
				form.attr('action'),
				{
					after: {
						failure: failureCallback,
						success: function(event, id, obj) {
							var responseData = this.get('responseData');

							if (!responseData.success) {
								var message = A.one('#<portlet:namespace />errorMessage');

								if (message) {
									message.addClass('alert alert-danger');

									message.html(responseData.message);
								}
							}
							else {
								Liferay.component('contactsCenter').renderEntry(responseData);

								Liferay.component('contactsCenter').closePopup();
							}
						}
					},
					data: {
						<portlet:namespace />end: end,
						<portlet:namespace />filterBy: contactFilterSelect.get('value') || '<%= ContactsConstants.FILTER_BY_DEFAULT %>',
						<portlet:namespace />keywords: searchInput.get('value'),
						<portlet:namespace />start: 0
					},
					dataType: 'JSON',
					form: {
						id: form
					}
				}
			);
		}
	);
</aui:script>