<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/init.jsp" %>

<%
long messageId = ParamUtil.getLong(request, "messageId");
String messageType = ParamUtil.getString(request, "messageType");

MailManager mailManager = MailManager.getInstance(request);

Message message = null;

String subject = "";
String to = "";
String cc = "";
String body = "";

if (messageId != 0) {
	message = MessageLocalServiceUtil.getMessage(messageId);

	if (messageType.equals("reply")) {
		subject = "re: " + message.getSubject();
		to = message.getSender();
		body = "[quote body from original message]"; //"<ul>" + message.getBody() + "</ul>";
	}
	else if (messageType.equals("reply-all")) {
		subject = "re: " + message.getSubject();
		to = message.getSender() + ", " + message.getTo();
		cc = message.getCc();
		body = "[quote body from original message]"; //"<ul>" + message.getBody() + "</ul>";
	}
	else if (messageType.equals("forward")) {
		subject = "fwd: " + message.getSubject();
	}
}
%>

<form action="<portlet:actionURL name="saveOrSendMessage" />" enctype="multipart/form-data" id="<portlet:namespace />fmCompose" method="post" name="<portlet:namespace />fmCompose">
	<aui:input type="hidden" name="draftMessageId" value="" />
	<aui:input type="hidden" name="sendMessage" value="true" />
	<aui:fieldset>
		<aui:select name="accountId" label="from">

		<%
		List<Account> accounts = mailManager.getAccounts();

		for (Account account : accounts) {
		%>

		<aui:option value="<%= account.getAccountId() %>"><%= account.getAddress() %></aui:option>

		<%
		}
		%>

		</aui:select>

		<aui:input name="to" value="<%= to %>" />

		<aui:input name="cc" value="<%= cc %>" />

		<aui:input name="bcc" />

		<aui:input name="subject" value="<%= subject %>" />

		<aui:field-wrapper label="body">
			<liferay-ui:input-editor editorImpl="<%= EDITOR_WYSIWYG_IMPL_KEY %>" toolbarSet="email" width="100%" />

			<aui:input name="body" type="hidden" />
		</aui:field-wrapper>
	</aui:fieldset>

	<aui:layout>
		<aui:column>
			<aui:button cssClass="send-message" type="submit" value="send" />
		</aui:column>
		<aui:column>
			<aui:button cssClass="save-message" type="submit" value="save" />
		</aui:column>
		<aui:column>
			<aui:button cssClass="discard-message" type="button" value="discard" />
		</aui:column>
	</aui:layout>
</form>

<aui:script>
	function <portlet:namespace />initEditor() {
		return '';
	}
</aui:script>

<%!
public static final String EDITOR_WYSIWYG_IMPL_KEY = "editor.wysiwyg.default";
%>