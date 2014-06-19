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
String tabs1 = ParamUtil.getString(request, "tabs1", "attending");

String redirect = ParamUtil.getString(request, "redirect");

long meetupsEntryId = ParamUtil.getLong(request, "meetupsEntryId");

MeetupsEntry meetupsEntry = null;

try {
	meetupsEntry = MeetupsEntryLocalServiceUtil.getMeetupsEntry(meetupsEntryId);
}
catch (NoSuchMeetupsEntryException nsmee) {
%>

	<span class="alert alert-error">
		<liferay-ui:message key="the-meetup-could-not-be-found" />
	</span>

<%
	return;
}

MeetupsRegistration meetupsRegistration = null;

try {
	meetupsRegistration = MeetupsRegistrationLocalServiceUtil.getMeetupsRegistration(themeDisplay.getUserId(), meetupsEntryId);
}
catch (NoSuchMeetupsRegistrationException nsmre) {
}

int status = MeetupsConstants.STATUS_YES;

if (meetupsRegistration != null) {
	status = meetupsRegistration.getStatus();
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/meetups/view_entry.jsp");
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("meetupsEntryId", String.valueOf(meetupsEntryId));

String thumbnailURL = null;

if (meetupsEntry.getThumbnailId() == 0) {
	thumbnailURL = PortalUtil.getPathContext(request) + "/meetups/images/calendar.png";
}
else {
	thumbnailURL = themeDisplay.getPathImage() + "/meetups?img_id=" + meetupsEntry.getThumbnailId() + "&t=" + WebServerServletTokenUtil.getToken(meetupsEntry.getThumbnailId());
}
%>

<div class="meetup-description">
	<img alt="" src="<%= thumbnailURL %>" style="float: left; margin-right: 10px;" />

	<h4>
		<%= meetupsEntry.getTitle() %>
	</h4>

	<p>
		<%= meetupsEntry.getDescription() %>
	</p>
</div>

<%
int yesTotal = MeetupsRegistrationLocalServiceUtil.getMeetupsRegistrationsCount(meetupsEntryId, MeetupsConstants.STATUS_YES);
%>

<c:if test="<%= yesTotal > 1 %>">
	<div>
		<%= LanguageUtil.format(request, "x-people-are-planning-to-attend-this-meetup", String.valueOf(yesTotal), false) %>
	</div>

	<br />
</c:if>

<c:choose>
	<c:when test="<%= themeDisplay.isSignedIn() %>">
		<portlet:actionURL name="updateMeetupsRegistration" var="updateMeetupsRegistrationURL" />

		<aui:form action="<%= updateMeetupsRegistrationURL %>" cssClass="meetup-form" method="post" name="fm" onSubmit='<%= renderResponse.getNamespace() + "updateMeetupsRegistration(); return false;" %>'>
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
			<aui:input name="meetupsEntryId" type="hidden" value="<%= meetupsEntryId %>" />

			<aui:field-wrapper label="will-you-attend">
				<aui:input checked="<%= status == MeetupsConstants.STATUS_YES %>" inlineField="<%= true %>" label="yes" name="status" type="radio" value="<%= MeetupsConstants.STATUS_YES %>" />

				<aui:input checked="<%= status == MeetupsConstants.STATUS_NO %>" inlineField="<%= true %>" label="no" name="status" type="radio" value="<%= MeetupsConstants.STATUS_NO %>" />

				<aui:input checked="<%= status == MeetupsConstants.STATUS_MAYBE %>" inlineField="<%= true %>" label="maybe" name="status" type="radio" value="<%= MeetupsConstants.STATUS_MAYBE %>" />
			</aui:field-wrapper>

			<aui:model-context bean="<%= meetupsRegistration %>" model="<%= MeetupsRegistration.class %>" />

			<aui:input name="comments" />

			<aui:button-row>
				<aui:button type="submit" value="register" />
			</aui:button-row>
		</aui:form>

		<br />

		<liferay-ui:tabs
			names="attending,not-attending,maybe-attending"
			portletURL="<%= portletURL %>"
		/>

		<%
		int tabs1Status = MeetupsConstants.STATUS_YES;

		if (tabs1.equals("not-attending")) {
			tabs1Status = MeetupsConstants.STATUS_NO;
		}
		else if (tabs1.equals("maybe-attending")) {
			tabs1Status = MeetupsConstants.STATUS_MAYBE;
		}

		SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 10, portletURL, null, null);

		int total = MeetupsRegistrationLocalServiceUtil.getMeetupsRegistrationsCount(meetupsEntryId, tabs1Status);

		searchContainer.setTotal(total);

		List<MeetupsRegistration> results = MeetupsRegistrationLocalServiceUtil.getMeetupsRegistrations(meetupsEntryId, tabs1Status, searchContainer.getStart(), searchContainer.getEnd());

		searchContainer.setResults(results);

		for (MeetupsRegistration curMeetupsRegistration : results) {
		%>

			<div class="response">
				<liferay-ui:user-display
					displayStyle="2"
					userId="<%= curMeetupsRegistration.getUserId() %>"
					userName="<%= curMeetupsRegistration.getUserName() %>"
				/>

				<c:if test="<%= Validator.isNotNull(curMeetupsRegistration.getComments()) %>">
					<div class="comments">
						<%= curMeetupsRegistration.getComments() %>

						<span class="indicator"></span>
					</div>
				</c:if>
			</div>

		<%
		}
		%>

		<div class="taglib-search-iterator-page-iterator-bottom">
			<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
		</div>

		<aui:script use="aui-base">
			var meetups = A.one('.social-networking-portlet-meetups');

			if (meetups) {
				meetups.delegate(
					'mouseenter',
					function(event) {
						event.currentTarget.addClass('hovering');
					},
					'.response'
				);

				meetups.delegate(
					'mouseleave',
					function(event) {
						event.currentTarget.removeClass('hovering');
					},
					'.response'
				);
			}
		</aui:script>
	</c:when>
	<c:otherwise>
		<div>
			<liferay-ui:message arguments="<%= new Object[] {themeDisplay.getURLSignIn(), PortalUtil.getCreateAccountURL(request, themeDisplay)} %>" key="you-have-to-be-signed-in-to-register-for-this-meetup" translateArguments="<%= false %>" />
		</div>
	</c:otherwise>
</c:choose>