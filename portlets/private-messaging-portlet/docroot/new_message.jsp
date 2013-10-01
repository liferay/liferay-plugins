<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

long[] userIds = StringUtil.split(ParamUtil.getString(request, "userIds"), 0L);

StringBundler sb = new StringBundler(userIds.length * 6);

for (long userId : userIds) {
	try {
		User user2 = UserLocalServiceUtil.getUser(userId);

		sb.append(user2.getFullName());
		sb.append(CharPool.SPACE);
		sb.append(CharPool.LESS_THAN);
		sb.append(user2.getScreenName());
		sb.append(CharPool.GREATER_THAN);
		sb.append(StringPool.COMMA_AND_SPACE);
	}
	catch (Exception e) {
	}
}

to = sb.toString() + to;
%>

<div id="<portlet:namespace />messageContainer"></div>

<liferay-portlet:actionURL name="sendMessage" var="sendMessageURL">
	<portlet:param name="redirect" value="<%= redirect %>" />
</liferay-portlet:actionURL>

<aui:layout cssClass="message-body-container">
	<aui:form action="<%= sendMessageURL %>" enctype="multipart/form-data" method="post" name="fm" onSubmit="event.preventDefault();">
		<aui:input name="userId" type="hidden" value="<%= user.getUserId() %>" />
		<aui:input name="mbThreadId" type="hidden" value="<%= mbThreadId %>" />

		<div id="<portlet:namespace />autoCompleteContainer">
			<aui:input cssClass="message-to" name="to" value="<%= to %>" />
		</div>

		<aui:input cssClass="message-subject" name="subject" value="<%= subject %>" />

		<label class="field-label">
			<liferay-ui:message key="message" />
		</label>

		<textarea class="message-body" id="<portlet:namespace />body" name="<portlet:namespace />body"></textarea>

		<label class="field-label">
			<liferay-ui:message key="attachments" />
		</label>

		<%
		long fileMaxSize = PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE);

		if (fileMaxSize == 0) {
			fileMaxSize = PrefsPropsUtil.getLong(PropsKeys.UPLOAD_SERVLET_REQUEST_IMPL_MAX_SIZE);
		}

		fileMaxSize /= 1024;
		%>

		<aui:field-wrapper>
			<c:if test="<%= fileMaxSize != 0 %>">
				<div class="portlet-msg-info">
					<%= LanguageUtil.format(pageContext, "upload-documents-no-larger-than-x-k", String.valueOf(fileMaxSize), false) %>
				</div>
			</c:if>
		</aui:field-wrapper>

		<aui:input label="" name="msgFile1" type="file" />

		<aui:input label="" name="msgFile2" type="file" />

		<aui:input label="" name="msgFile3" type="file" />

		<aui:button-row>
			<input type="submit" value="<liferay-ui:message key="send" />" />
		</aui:button-row>
	</aui:form>
</aui:layout>

<aui:script>
	function <portlet:namespace />showMessage(message) {
		var A = AUI();

		var messageContainer = A.one('#<portlet:namespace />messageContainer');

		if (messageContainer) {
			messageContainer.html(message);
		}
	}
</aui:script>

<aui:script use="aui-io-request-deprecated,aui-loading-mask-deprecated,autocomplete,json-parse,io-upload-iframe">
	var form = A.one('#<portlet:namespace />fm');

	form.on(
		'submit',
		function(event) {
			var recipients = A.one('#<portlet:namespace />to').val();

			if (recipients == '') {
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

			var loadingMask = new A.LoadingMask(
				{
					'strings.loading': '<%= UnicodeLanguageUtil.get(pageContext, "sending-message") %>',
					target: A.one('.private-messaging-portlet .message-body-container')
				}
			);

			loadingMask.show();

			A.io.request(
				'<liferay-portlet:resourceURL id="checkData"><liferay-portlet:param name="redirect" value="<%= PortalUtil.getLayoutURL(themeDisplay) %>" /></liferay-portlet:resourceURL>',
				{
					dataType: 'json',
					form: {
						id: form.getDOM(),
						upload: true
					},
					on: {
						complete: function(event, id, obj) {
							var responseText = obj.responseText;

							var responseData = A.JSON.parse(responseText);

							if (responseData.success) {
								submitForm(document.<portlet:namespace />fm);
							}
							else {
								<portlet:namespace />showMessage('<span class="portlet-msg-error">' + responseData.message + '</span>');

								loadingMask.hide();
							}
						},
						failure: function(event, id, obj) {
							<portlet:namespace />showMessage('<span class="portlet-msg-error"><%= UnicodeLanguageUtil.get(pageContext, "your-request-failed-to-complete") %></span>');

							loadingMask.hide();
						}
					}
				}
			);
		}
	);

	var to = A.one('#<portlet:namespace/>to');

	to.plug(
		A.Plugin.AutoComplete,
		{
			queryDelimiter: ',',
			requestTemplate: '&keywords={query}',
			resultListLocator: 'results.users',
			resultTextLocator: 'name',
			source: '<liferay-portlet:resourceURL id="getUsers" />'
		}
	);

	to.on(
		'focus',
		function() {
			to.ac.sendRequest('');
		}
	);
</aui:script>