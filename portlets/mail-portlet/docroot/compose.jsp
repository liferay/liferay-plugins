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
long accountId = ParamUtil.getLong(request, "accountId");
long messageId = ParamUtil.getLong(request, "messageId");
String messageType = ParamUtil.getString(request, "messageType");
long replyMessageId = ParamUtil.getLong(request, "replyMessageId");

MailManager mailManager = MailManager.getInstance(request);

Message message = null;

String subject = "";
String to = "";
String cc = "";
String body = "";

if (messageType.equals("new")) {
	message = mailManager.addDraft(accountId);
}
else if (messageType.equals("edit")) {
	message = MessageLocalServiceUtil.getMessage(messageId);

	messageId = message.getMessageId();
	subject = message.getSubject();
	to = message.getTo();
	cc = message.getCc();
	body = message.getBody();
}
else {
	message = MessageLocalServiceUtil.getMessage(messageId);

	messageId = message.getMessageId();

	Message replyMessage = MessageLocalServiceUtil.getMessage(replyMessageId);

	if (messageType.equals("reply")) {
		subject = "re: " + replyMessage.getSubject();
		to = replyMessage.getSender();
		body = "[quote body from original message]"; //"<ul>" + replyMessage.getBody() + "</ul>";
	}
	else if (messageType.equals("reply-all")) {
		subject = "re: " + message.getSubject();
		to = replyMessage.getSender() + ", " + replyMessage.getTo();
		cc = replyMessage.getCc();
		body = "[quote body from original message]"; //"<ul>" + replyMessage.getBody() + "</ul>";
	}
	else if (messageType.equals("forward")) {
		subject = "fwd: " + replyMessage.getSubject();
	}
}
%>

<form action="<portlet:actionURL name="saveOrSendMessage" />" enctype="multipart/form-data" id="<portlet:namespace />fmCompose" method="post" name="<portlet:namespace />fmCompose">
	<aui:input type="hidden" name="draftMessageId" value="" />
	<aui:input type="hidden" name="sendMessage" value="true" />
	<aui:fieldset>
		<aui:select name="accountId" label="from">

		<%
		List<Account> mailAccounts = mailManager.getAccounts();

		for (Account mailAccount : mailAccounts) {
		%>

		<aui:option value="<%= mailAccount.getAccountId() %>"><%= mailAccount.getAddress() %></aui:option>

		<%
		}
		%>

		</aui:select>

		<aui:input name="to" value="<%= to %>" />

		<aui:input name="cc" value="<%= cc %>" />

		<aui:input name="bcc" />

		<aui:input name="subject" value="<%= subject %>" />

		<%
		String redirect = ParamUtil.getString(request, "redirect");
		%>

		<portlet:actionURL name="addAttachment" var="addAttachmentURL" />

		<aui:form action="<%= addAttachmentURL %>" enctype="multipart/form-data" method="post" name="fm">
			<aui:input name="cmd" type="hidden" value="add" />
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
			<aui:input name="messageId" type="hidden" value="<%= messageId %>" />
			<aui:input name="numOfFiles" type="hidden" value="3" />

			<div class="lfr-dynamic-uploader">
				<div class="lfr-upload-container" id="<portlet:namespace />fileUpload"></div>
			</div>

			<div class="lfr-fallback aui-helper-hidden" id="<portlet:namespace />fallback">
				<aui:fieldset label="upload-files">
					<aui:input label='<%= LanguageUtil.get(pageContext, "file") + " 1" %>' name="file1" type="file" />

					<aui:input label='<%= LanguageUtil.get(pageContext, "file") + " 2" %>' name="file2" type="file" />

					<aui:input label='<%= LanguageUtil.get(pageContext, "file") + " 3" %>' name="file3" type="file" />
				</aui:fieldset>

				<aui:button-row>
					<aui:button type="submit" />

					<%
					String taglibOnClick = "parent.location = '" + HtmlUtil.escape(redirect) + "';";
					%>

					<aui:button onClick="<%= taglibOnClick %>" type="cancel" />
				</aui:button-row>
			</div>
		</aui:form>

		<aui:script use="aui-base">
			var validateFile = function(fileField) {
			};

			var onFileChange = function(event) {
				validateFile(event.currentTarget);
			};

			for (var i = 1; i < 4; i++) {
				var fileField = A.one('#<portlet:namespace />file' + i);

				if (fileField) {
					fileField.on('change', onFileChange);

					validateFile(fileField);
				}
			}
		</aui:script>

		<aui:script use="liferay-upload">
			new Liferay.Upload(
				{
					allowedFileTypes: '*',
					container: '#<portlet:namespace />fileUpload',
					fileDescription: '*',
					fallbackContainer: '#<portlet:namespace />fallback',
					maxFileSize: 3072000,
					namespace: '<portlet:namespace />',
					uploadFile: '<%= addAttachmentURL %>'
				}
			);
		</aui:script>

		<aui:field-wrapper label="body">
			< l i f eray-ui:input-editor editorImpl="<%= EDITOR_WYSIWYG_IMPL_KEY %>" toolbarSet="email" width="100%" />

			<aui:input name="body" type="hidden" />
		</aui:field-wrapper>
	</aui:fieldset>

	<aui:layout>
		<aui:column>
			<aui:button cssClass="send-draft" type="submit" value="send" />
		</aui:column>
		<aui:column>
			<aui:button cssClass="save-draft" type="button" value="save" />
		</aui:column>
		<aui:column>
			<aui:button cssClass="discard-draft" type="button" value="discard" />
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