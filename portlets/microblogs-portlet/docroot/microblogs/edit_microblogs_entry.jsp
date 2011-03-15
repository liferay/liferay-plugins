<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
long microblogsEntryId = ParamUtil.getLong(request, "microblogsEntryId", 0);

WindowState windowState = renderRequest.getWindowState();

MicroblogsEntry microblogsEntry = null;

if (microblogsEntryId > 0) {
	try {
		microblogsEntry = MicroblogsEntryLocalServiceUtil.getMicroblogsEntry(microblogsEntryId);
	}
	catch (NoSuchModelException nsme) {
	}
}

boolean edit = ParamUtil.getBoolean(request, "edit", false);
boolean reply = ParamUtil.getBoolean(request, "reply", false);
boolean repost = ParamUtil.getBoolean(request, "repost", false);

long receiverUserId = 0;

String receiverUserFullName = StringPool.BLANK;

String receiverUserDisplayURL = StringPool.BLANK;
String receiverUserEmail = StringPool.BLANK;
String receiverUserPortaitURL = StringPool.BLANK;

String modifiedDate = StringPool.BLANK;

if ((microblogsEntry != null) && (reply || repost)) {
	receiverUserId = microblogsEntry.getUserId();

	receiverUserFullName = PortalUtil.getUserName(microblogsEntry.getUserId(), microblogsEntry.getUserName());

	modifiedDate = dateFormatDateTime.format(microblogsEntry.getModifiedDate());

	try {
		User receiverUser = UserLocalServiceUtil.getUserById(microblogsEntry.getUserId());

		receiverUserDisplayURL = receiverUser.getDisplayURL(themeDisplay);
		receiverUserEmail = receiverUser.getEmailAddress();
		receiverUserPortaitURL = receiverUser.getPortraitURL(themeDisplay);
	}
	catch (NoSuchUserException nsue) {
	}
}

String header = "whats-happening";
String formName = "dialogFm";

boolean view = false;

if (edit) {
	header = "what-do-you-want-to-say-instead";
}
else if (repost) {
	header = "repost-this-entry-to-your-followers";
}
else if (reply) {
	header = "what-do-you-want-to-say-to-x";
}
else {
	formName = "fm";

	view = true;
}

header = LanguageUtil.format(pageContext, header, receiverUserFullName);
%>

<liferay-ui:header title="<%= header %>" />

<c:if test="<%= reply || repost %>">
	<c:choose>
		<c:when test="<%= microblogsEntry == null %>">
			<div class="portlet-msg-error">
				<liferay-ui:message key="entry-could-not-be-found" />
			</div>
		</c:when>
		<c:otherwise>
			<div class="microblogs-entry">
				<span class="thumbnail">
					<a href="<%= receiverUserDisplayURL %>"><img alt="<%= receiverUserFullName %>" src="<%= receiverUserPortaitURL %>" /></a>
				</span>

				<div class="entry-bubble">
					<div class="user-name">
						<span><%= receiverUserFullName %></span>
						<span class="small"><%= receiverUserEmail %></span>
					</div>

					<div class="content">
						<span><%= HtmlUtil.escape(microblogsEntry.getContent()) %><span>
					</div>

					<div class="footer">
						<span class="modified-date"><%= modifiedDate %></span>
					</div>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</c:if>

<portlet:actionURL name="updateMicroblogsEntry" var="updateMicroblogsEntryURL" />

<aui:form action="<%= updateMicroblogsEntryURL %>" name="<%= formName %>">
	<aui:input type="hidden" name="redirect" value="<%= currentURL %>" />
	<aui:input type="hidden" name="microblogsEntryId" value="<%= repost || reply ? 0 : microblogsEntryId %>" />
	<aui:input type="hidden" name="receiverUserId" value="<%= receiverUserId %>" />
	<aui:input type="hidden" name="receiverEntryId" value="<%= microblogsEntryId %>" />

	<c:choose>
		<c:when test="<%= repost %>">
			<aui:input type="hidden" name="type" value="<%= MicroblogsEntryConstants.REPOST %>" />

			<aui:input type="hidden" name="content" value="<%= microblogsEntry.getContent() %>" />
		</c:when>
		<c:when test="<%= reply %>">
			<aui:input type="hidden" name="type" value="<%= MicroblogsEntryConstants.REPLY %>" />

			<aui:input type="hidden" name="receiverEntryId" value="<%= microblogsEntryId %>" />
		</c:when>
	</c:choose>

	<aui:model-context bean="<%= microblogsEntry %>" model="<%= MicroblogsEntry.class %>" />

	<c:if test="<%= !repost %>">
		<aui:input label="" type="textarea" name="content" />
	</c:if>

	<aui:button-row cssClass='<%= view ? "aui-helper-hidden" : StringPool.BLANK %>'>

		<%
		int socialRelationType = 0;

		if(microblogsEntry != null) {
			socialRelationType = microblogsEntry.getSocialRelationType();
		}
		%>

		<aui:select label="viewable-by" inlineLabel="true" name="socialRelationType">
			<aui:option label="everyone" selected="<%= socialRelationType == MicroblogsEntryConstants.EVERYONE %>" value="<%= MicroblogsEntryConstants.EVERYONE %>" />
			<aui:option label="friends" selected="<%= socialRelationType == SocialRelationConstants.TYPE_BI_FRIEND %>" value="<%= SocialRelationConstants.TYPE_BI_FRIEND %>" />
			<aui:option label="coworkers" selected="<%= socialRelationType == SocialRelationConstants.TYPE_BI_COWORKER %>" value="<%=SocialRelationConstants.TYPE_BI_COWORKER %>" />
			<aui:option label="followers" selected="<%= socialRelationType == SocialRelationConstants.TYPE_UNI_FOLLOWER %>" value="<%= SocialRelationConstants.TYPE_UNI_FOLLOWER %>" />
		</aui:select>

		<span class="button-holder-right">
			<c:if test="<%= !repost %>">
				<span class="microblogs-countdown">150</span>
			</c:if>

			<aui:button name="submit" type="submit" value="post" />

			<c:if test="<%= !view %>">
				<aui:button onClick="Liferay.Microblogs.closePopup()" type="cancel" />
			</c:if>
		</span>
	</aui:button-row>
</aui:form>

<aui:script use="aui-base,aui-io-plugin">
	var form = A.one(document.<portlet:namespace /><%= formName %>);

	<c:if test="<%= !repost %>">
		var buttonContainer = form.one('.aui-button-holder');
		var contentInput = form.one('textarea[name=<portlet:namespace />content]');
		var countdown = form.one('.microblogs-countdown');
		var submitButton = form.one('.aui-button-input-submit');
	</c:if>

	<c:if test="<%= view %>">
		contentInput.on(
			'focus',
			function(event) {
				contentInput.setStyle("height", "60px");

				buttonContainer.show();
			}
		);
	</c:if>

	form.on(
		'submit',
		function(event) {
			event.halt(true);

			A.io.request(
				form.getAttribute('action'),
				{
					form: {
						id: form.getDOM()
					},
					method: 'POST',
					on: {
						success: function() {
							<c:if test="<%= view %>">
								contentInput.setStyle('height', '18px');
								contentInput.set('value', '');

								countContent();

								buttonContainer.hide();
							</c:if>

							Liferay.Microblogs.updateMicroblogs();
						}
					}
				}
			);

			<c:if test="<%= !view %>">
				Liferay.Microblogs.closePopup();
			</c:if>
		}
	);

	<c:if test="<%= reply %>">
		contentInput.set('value', '');

		contentInput.focus();
	</c:if>

	<c:if test="<%= !repost %>">
		var countContent = function() {
			var remainCount = 150 - contentInput.get('value').length;

			countdown.set('innerHTML', remainCount);

			if((remainCount < 0) || (remainCount == 150)) {
				submitButton.set('disabled', true);
				submitButton.setStyle('color', '#C8C9CA');

				if (remainCount < 0) {
					countdown.setStyle('color', '#F00');
				}
			}
			else {
				submitButton.set('disabled', false);
				submitButton.setStyle('color', '#34404F');

				countdown.setStyle('color', '#C8C9CA');
			}
		};

		contentInput.on(
			'keyup',
			countContent
		);

		countContent();
	</c:if>
</aui:script>