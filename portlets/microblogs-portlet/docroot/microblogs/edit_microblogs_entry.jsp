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
long microblogsEntryId = ParamUtil.getLong(request, "microblogsEntryId");

MicroblogsEntry microblogsEntry = null;

if (microblogsEntryId > 0) {
	try {
		microblogsEntry = MicroblogsEntryLocalServiceUtil.getMicroblogsEntry(microblogsEntryId);
	}
	catch (NoSuchEntryException nsee) {
	}
}

String modifiedDate = StringPool.BLANK;

long receiverUserId = 0;

String receiverUserDisplayURL = StringPool.BLANK;
String receiverUserFullName = StringPool.BLANK;
String receiverUserPortaitURL = StringPool.BLANK;
String receiverUserScreenName = StringPool.BLANK;

boolean edit = ParamUtil.getBoolean(request, "edit");
boolean reply = ParamUtil.getBoolean(request, "reply");
boolean repost = ParamUtil.getBoolean(request, "repost");

if ((microblogsEntry != null) && (reply || repost)) {
	modifiedDate = dateFormatDateTime.format(microblogsEntry.getModifiedDate());

	receiverUserId = microblogsEntry.getUserId();

	receiverUserFullName = PortalUtil.getUserName(microblogsEntry.getUserId(), microblogsEntry.getUserName());

	try {
		User receiverUser = UserLocalServiceUtil.getUserById(microblogsEntry.getUserId());

		receiverUserDisplayURL = receiverUser.getDisplayURL(themeDisplay);
		receiverUserPortaitURL = receiverUser.getPortraitURL(themeDisplay);
		receiverUserScreenName = receiverUser.getScreenName();
	}
	catch (NoSuchUserException nsue) {
	}
}

String header = "whats-happening";

String cssClassName = "highlighter-content";
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
	cssClassName = "highlighter-content textbox";
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
						<span><%= receiverUserFullName %></span> <span class="small">(<%= receiverUserScreenName %>)</span>
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
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="microblogsEntryId" type="hidden" value="<%= (repost || reply) ? 0 : microblogsEntryId %>" />
	<aui:input name="receiverUserId" type="hidden" value="<%= receiverUserId %>" />
	<aui:input name="receiverMicroblogsEntryId" type="hidden" value="<%= microblogsEntryId %>" />

	<c:choose>
		<c:when test="<%= repost %>">
			<aui:input name="type" type="hidden" value="<%= MicroblogsEntryConstants.TYPE_REPOST %>" />

			<aui:input name="content" type="hidden" value="<%= microblogsEntry.getContent() %>" />
		</c:when>
		<c:when test="<%= reply %>">
			<aui:input name="type" type="hidden" value="<%= MicroblogsEntryConstants.TYPE_REPLY %>" />

			<aui:input name="receiverMicroblogsEntryId" type="hidden" value="<%= microblogsEntryId %>" />
		</c:when>
	</c:choose>

	<aui:model-context bean="<%= microblogsEntry %>" model="<%= MicroblogsEntry.class %>" />

	<c:if test="<%= !repost %>">
		<div class="autocomplete" id="<portlet:namespace />autocomplete">
			<div id="<portlet:namespace />autocompleteContent"></div>

			<div class="<%= cssClassName %>" id="<portlet:namespace />highlighterContent"></div>
		</div>

		<aui:input label="" name="content" type="hidden" />
	</c:if>

	<aui:button-row cssClass='<%= view ? "aui-helper-hidden" : StringPool.BLANK %>'>
		<c:if test="<%= !repost && !reply %>">

			<%
			int socialRelationType = 0;

			if(microblogsEntry != null) {
				socialRelationType = microblogsEntry.getSocialRelationType();
			}
			%>

			<aui:select label="viewable-by" inlineLabel="true" name="socialRelationType">
				<aui:option label="everyone" selected="<%= socialRelationType == MicroblogsEntryConstants.TYPE_EVERYONE %>" value="<%= MicroblogsEntryConstants.TYPE_EVERYONE %>" />
				<aui:option label="friends" selected="<%= socialRelationType == SocialRelationConstants.TYPE_BI_FRIEND %>" value="<%= SocialRelationConstants.TYPE_BI_FRIEND %>" />
				<aui:option label="coworkers" selected="<%= socialRelationType == SocialRelationConstants.TYPE_BI_COWORKER %>" value="<%=SocialRelationConstants.TYPE_BI_COWORKER %>" />
				<aui:option label="followers" selected="<%= socialRelationType == SocialRelationConstants.TYPE_UNI_FOLLOWER %>" value="<%= SocialRelationConstants.TYPE_UNI_FOLLOWER %>" />
			</aui:select>
		</c:if>

		<span class="button-holder-right">
			<c:if test="<%= !repost %>">
				<span class="microblogs-countdown">150</span>
			</c:if>

			<aui:button inputCssClass="microblogs-button-input" name="submit" type="submit" value="post" />

			<c:if test="<%= !view %>">
				<aui:button onClick="Liferay.Microblogs.closePopup();" type="cancel" />
			</c:if>
		</span>
	</aui:button-row>
</aui:form>

<aui:script use="aui-base,aui-event-input,aui-template,aui-form-textarea,autocomplete,autocomplete-filters">
	var MAP_MATCHED_USERS = {
		userScreenName: function(str, match) {
			return '[@' + MAP_USERS[str] + ']';
		},
		userName: function(str, match) {
			return '<b>' + str + '</b>'
		}
	};

	var MAP_USERS = {};

	var REGEX_USER_NAME = /@[^\s]+$/;

	var TPL_SEARCH_RESULTS = '<div class="microblogs-autocomplete">' +
		'<div class="thumbnail">' +
			'<img src="{portraitURL}" alt="{userFullName}" />' +
		'</div>' +
		'<div>' +
			'<span class="user-name">{userFullName}</span><br />' +
			'<span class="small">{email}</span><br />' +
			'<span class="job-title">{jobTitle}</span>' +
		'</div>' +
	'</div>';

	var form = A.one('#<portlet:namespace /><%= formName %>');

	<c:if test="<%= !repost %>">
		var countContent = function(event) {
			var contentInput = event.currentTarget;

			var countdown = form.one('.microblogs-countdown');
			var submitButton = form.one('.aui-button-input-submit');

			var remaining = (150 - contentInput.val().length);

			var disabled = ((remaining < 0) || (remaining == 150));

			countdown.html(remaining);

			submitButton.attr('disabled', disabled);
			submitButton.toggleClass('microblogs-button-input-disabled', disabled);

			countdown.toggleClass('microblogs-countdown-warned', (remaining < 0));
		};

		var createTextarea = function(divId) {
			var autocomplete = A.one('#<portlet:namespace/>autocomplete');
			var highlighterContent = A.one('#<portlet:namespace/>highlighterContent');

			var inputValue = '<%= ((microblogsEntry != null) && (edit)) ? microblogsEntry.getContent() : StringPool.BLANK %>';

			if (autocomplete.height() < 45 || highlighterContent.height() < 45) {
				autocomplete.height(45);

				highlighterContent.height(45);
			}

			var textarea = new A.Textarea(
				{
					autoSize: true,
					id: '<portlet:namespace />contentInput',
					value: inputValue
				}
			).render(divId);

			var contentTextarea = A.one('#<portlet:namespace />contentInput textarea');

			<c:if test="<%= view %>">
				contentTextarea.on(
					'focus',
					function(contentTextarea) {
						var buttonContainer = form.one('.aui-button-holder');

						buttonContainer.show();
					}
				);
			</c:if>

			contentTextarea.on(
				'input',
				function(contentTextarea) {
					updateHighlightDivSize(contentTextarea);

					countContent(contentTextarea);
				}
			);

			createAutocomplete(contentTextarea);

			contentTextarea.focus();

			return contentTextarea;
		};

		var replaceName = function(inputText, returnType) {
			var matchedUsers = {};

			var updatedText = inputText;

			var users = A.Object.keys(MAP_USERS);

			var findNames = new RegExp('(' + users.join('|') + ')', 'g');

			if (users.length > 0) {
				updatedText = updatedText.replace(
					findNames,
					function(userName) {
						if (userName !== '') {
							matchedUsers[userName] = MAP_USERS[userName];

							return MAP_MATCHED_USERS[returnType](userName, MAP_USERS[userName]);
						}
					}
				);
			}

			MAP_USERS = matchedUsers;

			return updatedText;
		};

		var resultFormatter = function(query, results) {
			return A.Array.map(
				results,
				function(result) {
					var userData = result.raw;

					return A.Lang.sub(TPL_SEARCH_RESULTS, userData);
				}
			);
		};

		var updateHighlightDivContent = function(event) {
			var inputValue = event.inputValue;

			var highlighterContent = A.one('#<portlet:namespace/>highlighterContent');

			var query = inputValue.match(REGEX_USER_NAME);

			if (query) {
				event.query = query[0].substr(1);
			}
			else {
				event.preventDefault();
			}

			var updatedText = replaceName(inputValue, 'userName');

			updatedText = updatedText.replace(/(\n)/gm, '<br />');
			updatedText = updatedText.replace(/\s{2}/g, '&nbsp; ');

			highlighterContent.html('<span>' + updatedText + '</span>');
		};

		var updateHighlightDivSize = function(event) {
			var contentInput = event.currentTarget;

			var autocomplete = A.one('#<portlet:namespace/>autocomplete');
			var highlighterContent = A.one('#<portlet:namespace/>highlighterContent');

			var contentInputHeight = contentInput.height();

			autocomplete.height(contentInputHeight);

			highlighterContent.height(contentInputHeight);
		};

		var updateContentTextbox = function(event) {
			event.preventDefault();

			var rawResult = event.result.raw;

			var fullName = rawResult.userFullName;
			var userScreenName = rawResult.userScreenName;

			var inputNode = event.currentTarget._inputNode;

			var inputNodeValue = inputNode.val();

			var inputValueUpdated = inputNodeValue.replace(REGEX_USER_NAME, fullName);

			inputNode.val(inputValueUpdated);

			MAP_USERS[fullName] = userScreenName;

			autocompleteDiv.hide()
		};

		var createAutocomplete = function(contentTextarea) {
			return autocompleteDiv = new A.AutoComplete(
				{
					inputNode: contentTextarea,
					maxResults: 5,
					on: {
						clear: function() {
							var highlighterContent = A.one('#<portlet:namespace/>highlighterContent');

							highlighterContent.html('');
						},
						query: updateHighlightDivContent,
						select: updateContentTextbox
					},
					resultFilters: 'phraseMatch',
					resultFormatter: resultFormatter,
					resultTextLocator: 'userFullName',
					source: <%= MicroblogsUtil.getJSONRecipients(user.getUserId(), themeDisplay) %>
				}
			).render();
		}

		form.on(
			'submit',
			function(event) {
				event.halt(true);

				var content = A.one('#<portlet:namespace />content');
				var contentInput = A.one('#<portlet:namespace />contentInput textarea');

				var contentInputValue = contentInput.val();

				var updatedText = replaceName(contentInputValue, 'userScreenName');

				content.val(updatedText);

				Liferay.Microblogs.updateMicroblogs(form);

				<c:if test="<%= !view %>">
					Liferay.Microblogs.closePopup();
				</c:if>
			}
		);

		<c:choose>
			<c:when test="<%= !reply && !edit %>">
				var autocomplete = A.one('#<portlet:namespace/>autocomplete');

				autocomplete.on(
					'click',
					function(event) {
						var contentInput = A.one('#<portlet:namespace/>contentInput');
						var highlighterContent = A.one('#<portlet:namespace/>highlighterContent');

						if (!contentInput) {
							highlighterContent.removeClass('textbox');

							createTextarea('#<portlet:namespace />autocompleteContent');
						}
					}
				);
			</c:when>
			<c:otherwise>
				createTextarea('#<portlet:namespace />autocompleteContent');
			</c:otherwise>
		</c:choose>
	</c:if>
</aui:script>