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
long mbThreadId = ParamUtil.getLong(request, "mbThreadId");

String subject = StringPool.BLANK;
String to = StringPool.BLANK;

if (mbThreadId != 0) {
	List<MBMessage> mbMessages = MBMessageLocalServiceUtil.getThreadMessages(mbThreadId, WorkflowConstants.STATUS_ANY);

	MBMessage firstMessage = mbMessages.get(0);

	subject = firstMessage.getSubject();

	List<UserThread> userThreads = UserThreadLocalServiceUtil.getMBThreadUserThreads(mbThreadId);

	to = ListUtil.toString(userThreads, "userId", ", ");
}
%>

<portlet:renderURL var="backURL" windowState="<%= WindowState.NORMAL.toString() %>" />

<aui:layout cssClass="message-body-container">
	<form enctype="multipart/form-data" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />sendPrivateMessage(); return false;">
		<aui:input name="redirect" type="hidden" value="<%= backURL %>" />
		<aui:input name="userId" type="hidden" value="<%= user.getUserId() %>" />
		<aui:input name="mbThreadId" type="hidden" value="<%= mbThreadId %>" />

		<div id="<portlet:namespace />autoCompleteContainer">
			<aui:input cssClass="message-to" name="to" value="<%= to %>" />
		</div>

		<aui:input cssClass="message-subject" name="subject" value="<%= subject %>" />

		<label class="aui-field-label">
			<liferay-ui:message key="message" />
		</label>

		<textarea class="message-body" id="<portlet:namespace />body" name="<portlet:namespace />body"></textarea>

		<label class="aui-field-label">
			<liferay-ui:message key="attachments" />
		</label>

		<aui:input label="" name="msgFile1" type="file" />

		<aui:input label="" name="msgFile2" type="file" />

		<aui:input label="" name="msgFile3" type="file" />

		<aui:button-row>
			<input type="submit" value="<liferay-ui:message key="send" />" />
		</aui:button-row>
	</form>
</aui:layout>

<aui:script>
	function <portlet:namespace />sendPrivateMessage() {
		var A = AUI();

		if (A.one('#<portlet:namespace />to').val() == '') {
			A.one('#<portlet:namespace />to').focus();

			return false;
		}

		if (A.one('#<portlet:namespace />subject').val() == '') {
			A.one('#<portlet:namespace />subject').focus();

			return false;
		}

		if (A.one('#<portlet:namespace />body').val() == '') {
			A.one('#<portlet:namespace />body').focus();

			return false;
		}

		submitForm(document.<portlet:namespace />fm, '<liferay-portlet:actionURL name="sendMessage"><portlet:param name="redirect" value="<%= PortalUtil.getLayoutURL(themeDisplay) %>" /></liferay-portlet:actionURL>');
	}
</aui:script>

<aui:script use="aui-autocomplete">
	var autocomplete = new A.AutoComplete(
		{
			dataSource: <%= PrivateMessagingUtil.getJSONRecipients(user.getUserId()) %>,
			delimChar: ',',
			typeAhead: true,
			contentBox: '#<portlet:namespace/>autoCompleteContainer',
			input: '#<portlet:namespace/>to'
		}
	).render();

	autocomplete.overlay.set('zIndex', 1002);
</aui:script>