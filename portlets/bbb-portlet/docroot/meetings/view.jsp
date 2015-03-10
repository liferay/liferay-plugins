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
long bbbParticipantId = ParamUtil.getLong(request, "bbbParticipantId");
String hash = ParamUtil.getString(request, "hash");

if (bbbParticipantId <= 0) {
	if (themeDisplay.isSignedIn() && MeetingsPermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), ActionKeys.ADD_MEETING)) {
%>

	<%@ include file="/meetings/meetings.jspf" %>

<%
	}
	else {
		renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.TRUE);
		renderRequest.setAttribute(WebKeys.PORTLET_DECORATE, Boolean.FALSE);
	}
}
else {
	BBBParticipant bbbParticipant = BBBParticipantLocalServiceUtil.fetchBBBParticipant(bbbParticipantId);

	BBBMeeting bbbMeeting = null;

	if (bbbParticipant != null) {
		bbbMeeting = BBBMeetingLocalServiceUtil.fetchBBBMeeting(bbbParticipant.getBbbMeetingId());
	}
%>

	<div id="<portlet:namespace />messageContainer">
		<c:choose>
			<c:when test="<%= (bbbParticipant == null) || (bbbMeeting == null) %>">
				<div class="alert alert-danger">
					<liferay-ui:message key="the-meeting-you-have-requested-no-longer-exists" />
				</div>
			</c:when>
			<c:when test="<%= (bbbMeeting != null) && (bbbMeeting.getStatus() == BBBMeetingConstants.STATUS_COMPLETED) && !BBBAPIUtil.isMeetingRunning(bbbMeeting.getBbbMeetingId()) %>">
				<div class="alert alert-danger">
					<liferay-ui:message key="the-meeting-you-have-requested-has-already-completed" />
				</div>
			</c:when>
			<c:otherwise>
				<h3>
					<liferay-ui:message key="meeting-info" />
				</h3>

				<dt>
					<liferay-ui:message key="name" />
				</dt>
				<dd>
					<%= HtmlUtil.escape(bbbMeeting.getName()) %>
				</dd>

				<c:if test="<%= Validator.isNotNull(bbbMeeting.getDescription()) %>">
					<dt>
						<liferay-ui:message key="description" />
					</dt>
					<dd>
						<%= HtmlUtil.escape(bbbMeeting.getDescription()) %>
					</dd>
				</c:if>

				<aui:form name="fm" onSubmit="event.preventDefault();">
					<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
					<aui:input name="bbbParticipantId" type="hidden" value="<%= bbbParticipantId %>" />
					<aui:input name="hash" type="hidden" value="<%= hash %>" />

					<aui:model-context bean="<%= bbbParticipant %>" model="<%= BBBParticipant.class %>" />

					<div class="lfr-form-row lfr-form-row-inline">
						<div class="row-fields">
							<aui:input autoFocus="<%= true %>" label="enter-your-full-name" name="name" />

							<c:if test="<%= (bbbMeeting.getStatus() == BBBMeetingConstants.STATUS_SCHEDULED) && (bbbParticipant.getType() == BBBParticipantConstants.TYPE_MODERATOR) %>">
								<aui:input name="recordMeeting" type="checkbox" />
							</c:if>

							<aui:button-row>
								<aui:button type="submit" value="join-meeting" />
							</aui:button-row>
						</div>
					</div>
				</aui:form>
			</c:otherwise>
		</c:choose>
	</div>

	<aui:script use="aui-io-request,aui-loading-mask-deprecated">
		var form = A.one('#<portlet:namespace />fm');

		var messageContainer = A.one('#<portlet:namespace />messageContainer');

		if (form) {
			form.on(
				'submit',
				function(event) {
					var name = A.one('#<portlet:namespace />name').val();

					if (name == '') {
						A.one('#<portlet:namespace />name').focus();

						return false;
					}

					var loadingMask = new A.LoadingMask(
						{
							'strings.loading': '<%= UnicodeLanguageUtil.get(request, "the-meeting-has-not-yet-started.-you-will-be-automatically-connected-once-the-host-arrives.-please-wait") %>',
							target: A.one('.bbb-portlet')
						}
					);

					loadingMask.show();

					var io = A.io.request(
						'<liferay-portlet:actionURL name="joinBBBMeeting" />',
						{
							dataType: 'JSON',
							form: {
								id: form
							},
							on: {
								complete: function(event, id, obj) {
									var responseText = obj.responseText;

									var responseData = A.JSON.parse(responseText);

									if (responseData.success) {
										loadingMask.hide();

										window.location.href = responseData.joinURL;
									}
									else if (responseData.retry) {
										setTimeout(
											function() {
												io.start();
											},
											5000
										);
									}
									else {
										loadingMask.hide();

										messageContainer.html('<span class="alert alert-danger"><liferay-ui:message key="the-meeting-you-have-requested-no-longer-exists" /></span>');
									}
								}
							}
						}
					);
				}
			);
		}
	</aui:script>

<%
}
%>