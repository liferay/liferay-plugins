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

<%
String redirect = ParamUtil.getString(request, "redirect");

long entryId = ParamUtil.getLong(request, "entryId");

Entry entry = null;

if (entryId > 0) {
	entry = EntryLocalServiceUtil.getEntry(entryId);
}
%>

<div id="<portlet:namespace />errorMessage"></div>

<liferay-portlet:actionURL name="updateEntry" var="updateEntryURL" />

<aui:form action="<%= updateEntryURL %>" method="post" name="addEntry">
	<aui:input name="redirect" type="hidden"  value="<%= redirect %>" />
	<aui:input name="entryId" type="hidden"  value="<%= entryId %>" />

	<aui:model-context bean="<%= entry %>" model="<%= Entry.class %>" />

	<aui:input label="name" name="fullName" />

	<aui:input name="emailAddress" />

	<aui:input name="comments" />

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-io-request">
	Liferay.Util.focusFormField(document.<portlet:namespace />addEntry.<portlet:namespace />fullName);

	var form = A.one('#<portlet:namespace />addEntry');

	var failureCallback = funciton () {
		var errorMessage = A.one('#<portlet:namespace/>errorMessage');

		if (errorMessage) {
			errorMessage.html('<span class="portlet-msg-error"><liferay-ui:message key="an-error-occurred-while-retrieving-the-users-information" /></span>');
		}
	}

	form.on(
		'submit',
		function(event) {
			event.halt();

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
									message.html('<span class="portlet-msg-error">' + responseData.message + '</span>');
								}
							}
							else {
								A.io.request(
									'<liferay-portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/contacts_center/view_resources.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="portalUser" value="0" %>" /></liferay-portlet:renderURL>',
									{
										after: {
											failure: failureCallback,
											success: function(event, id, obj) {
												Liferay.ContactsCenter.renderContent(this.get('responseData'));

												var searchInput = A.one('.contacts-portlet #<portlet:namespace />name');
												var contactFilterSelect = A.one('.contacts-portlet .contact-group-filter select[name=<portlet:namespace />socialRelationType]');

												Liferay.ContactsCenter.updateContacts(searchInput.get('value'), contactFilterSelect.get('value'));

												A.one('#<portlet:namespace />fm').getData('dialogInstance').destroy();
											}
										},
										data: {
											entryId: responseData.entryId
										}
									}
								);
							}
						}
					},
					dataType: 'json',
					form: {
						id: form
					}
				}
			);
		}
	);
</aui:script>