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

long microblogsEntryId = ParamUtil.getLong(request, "microblogsEntryId");

if (microblogsEntryId <= 0) {
	microblogsEntryId = GetterUtil.getLong(request.getAttribute("view_comments.jsp-receiverMicroblogsEntryId"));
}

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
boolean repost = ParamUtil.getBoolean(request, "repost");

if ((microblogsEntry != null) && !edit) {
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

String formName = "fm" + microblogsEntryId;
String formCssClass = "microblogs-entry-form";

boolean comment = GetterUtil.getBoolean((String)request.getAttribute("view_comments.jsp-comment"), false);

if (comment) {
	formCssClass += " reply";
}
%>

<c:if test="<%= repost %>">
	<div class="repost-header">
		<span><liferay-ui:message key="do-you-want-to-repost-this-entry" /></span>
	</div>

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

<portlet:renderURL var="commentsURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="mvcPath" value="/microblogs/view_comments.jsp" />
	<portlet:param name="receiverMicroblogsEntryId" value="<%= String.valueOf(microblogsEntryId) %>" />
</portlet:renderURL>

<aui:form action="<%= updateMicroblogsEntryURL %>" cssClass="<%= formCssClass %>" name="<%= formName %>">
	<aui:input name="redirect" type="hidden" value="<%= comment ? commentsURL : redirect %>" />
	<aui:input name="microblogsEntryId" type="hidden" value="<%= edit ? microblogsEntryId : 0 %>" />
	<aui:input name="receiverUserId" type="hidden" value="<%= receiverUserId %>" />
	<aui:input name="receiverMicroblogsEntryId" type="hidden" value="<%= microblogsEntryId %>" />

	<c:choose>
		<c:when test="<%= repost %>">
			<aui:input name="type" type="hidden" value="<%= MicroblogsEntryConstants.TYPE_REPOST %>" />

			<aui:input name="content" type="hidden" value="<%= microblogsEntry.getContent() %>" />
		</c:when>
		<c:when test="<%= comment %>">
			<aui:input name="type" type="hidden" value="<%= MicroblogsEntryConstants.TYPE_REPLY %>" />
		</c:when>
	</c:choose>

	<aui:model-context bean="<%= microblogsEntry %>" model="<%= MicroblogsEntry.class %>" />

	<c:if test="<%= !repost %>">
		<c:if test="<%= comment %>">
			<span class="thumbnail">
				<a href="<%= user.getDisplayURL(themeDisplay) %>"><img alt="<%= user.getFullName() %>" src="<%= user.getPortraitURL(themeDisplay) %>" /></a>
			</span>
		</c:if>

		<div class="autocomplete textarea" id="<portlet:namespace />autocomplete">
			<div id="<portlet:namespace />autocompleteContent">
				<span class="placeholder-text">
					<c:choose>
						<c:when test="<%= comment %>">
							<liferay-ui:message key="leave-a-comment" />
						</c:when>
						<c:otherwise>
							<liferay-ui:message key="update-your-status" />
						</c:otherwise>
					</c:choose>
				</span>
			</div>

			<div class="highlighter-content <%= comment || edit || repost ? StringPool.BLANK : "textbox" %>" id="<portlet:namespace />highlighterContent"></div>
		</div>

		<aui:input label="" name="content" type="hidden" />
	</c:if>

	<aui:button-row cssClass='<%= !repost ? "aui-helper-hidden" : StringPool.BLANK %>'>
		<c:if test="<%= !comment && !repost %>">

			<%
			int socialRelationType = 0;

			if(microblogsEntry != null) {
				socialRelationType = microblogsEntry.getSocialRelationType();
			}
			%>

			<aui:select inlineLabel="true" label="viewable-by" name="socialRelationType">
				<aui:option label="everyone" selected="<%= socialRelationType == MicroblogsEntryConstants.TYPE_EVERYONE %>" value="<%= MicroblogsEntryConstants.TYPE_EVERYONE %>" />
				<aui:option label="connections" selected="<%= socialRelationType == SocialRelationConstants.TYPE_BI_CONNECTION %>" value="<%= SocialRelationConstants.TYPE_BI_CONNECTION %>" />
				<aui:option label="followers" selected="<%= socialRelationType == SocialRelationConstants.TYPE_UNI_FOLLOWER %>" value="<%= SocialRelationConstants.TYPE_UNI_FOLLOWER %>" />
			</aui:select>
		</c:if>

		<span class="button-holder-right">
			<c:if test="<%= !repost %>">
				<span class="microblogs-countdown">150</span>
			</c:if>

			<aui:button inputCssClass="microblogs-button-input" name="submit" type="submit" value="post" />

			<c:if test="<%= repost %>">
				<aui:button onClick="Liferay.Microblogs.closePopup();" type="cancel" />
			</c:if>
		</span>
	</aui:button-row>
</aui:form>

<aui:script use="aui-base,aui-event-input,aui-template,aui-form-textarea,autocomplete,autocomplete-filters">
	var MAP_MATCHED_USERS = {
		screenName: function(str, match) {
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
			'<img src="{portraitURL}" alt="{fullName}" />' +
		'</div>' +
		'<div>' +
			'<span class="user-name">{fullName}</span><br />' +
			'<span class="small">{emailAddress}</span><br />' +
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
			var autocomplete = form.one('#<portlet:namespace/>autocomplete');
			var autocompleteContent = autocomplete.one('#<portlet:namespace />autocompleteContent');
			var highlighterContent = form.one('#<portlet:namespace/>highlighterContent');

			var inputValue = '<%= ((microblogsEntry != null) && (edit)) ? StringUtil.replace(microblogsEntry.getContent(), "\'", "\\'") : StringPool.BLANK %>';

			if ((autocomplete.height() < 45) || (highlighterContent.height() < 45)) {
				autocomplete.height(45);

				highlighterContent.height(45);
			}

			var textarea = new A.Textarea(
				{
					autoSize: true,
					id: '<portlet:namespace />contentInput',
					value: inputValue
				}
			).render(autocompleteContent);

			var contentTextarea = form.one('#<portlet:namespace />contentInput textarea');

			contentTextarea.on(
				'focus',
				function(contentTextarea) {
					var buttonContainer = form.one('.aui-button-holder');

					buttonContainer.show();

					autocompleteContent.one('.placeholder-text').remove();
				}
			);

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

			var highlighterContent = form.one('#<portlet:namespace/>highlighterContent');

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

			var autocomplete = form.one('#<portlet:namespace/>autocomplete');
			var highlighterContent = form.one('#<portlet:namespace/>highlighterContent');

			var contentInputHeight = contentInput.height();

			autocomplete.height(contentInputHeight);

			highlighterContent.height(contentInputHeight);
		};

		var updateContentTextbox = function(event) {
			event.preventDefault();

			var rawResult = event.result.raw;

			var fullName = rawResult.fullName;
			var screenName = rawResult.screenName;

			var inputNode = event.currentTarget._inputNode;

			var inputNodeValue = inputNode.val();

			var inputValueUpdated = inputNodeValue.replace(REGEX_USER_NAME, fullName);

			inputNode.val(inputValueUpdated);

			MAP_USERS[fullName] = screenName;

			autocompleteDiv.hide()
		};

		var createAutocomplete = function(contentTextarea) {
			return autocompleteDiv = new A.AutoComplete(
				{
					inputNode: contentTextarea,
					maxResults: 5,
					on: {
						clear: function() {
							var highlighterContent = form.one('#<portlet:namespace/>highlighterContent');

							highlighterContent.html('');
						},
						query: updateHighlightDivContent,
						select: updateContentTextbox
					},
					resultFilters: 'phraseMatch',
					resultFormatter: resultFormatter,
					resultTextLocator: 'fullName',
					source: <%= MicroblogsUtil.getJSONRecipients(user.getUserId(), themeDisplay) %>
				}
			).render();
		}

		<c:choose>
			<c:when test="<%= !edit %>">
				var autocomplete = form.one('#<portlet:namespace/>autocomplete');

				autocomplete.on(
					'click',
					function(event) {
						var contentInput = form.one('#<portlet:namespace/>contentInput');
						var highlighterContent = form.one('#<portlet:namespace/>highlighterContent');

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

	form.on(
		'submit',
		function(event) {
			event.halt(true);

			<c:if test="<%= !repost %>">
				var content = form.one('#<portlet:namespace />content');
				var contentInput = form.one('#<portlet:namespace />contentInput textarea');

				var contentInputValue = contentInput.val();

				var updatedText = replaceName(contentInputValue, 'screenName');

				content.val(updatedText);
			</c:if>

			var url = form.one('input[name="<portlet:namespace />redirect"]');

			var updateContainer = A.one('.microblogs-portlet .portlet-body');

			<c:if test="<%= comment %>">
				updateContainer = A.one('.microblogs-portlet #commentsContainer<%= microblogsEntryId %>');
			</c:if>

			Liferay.Microblogs.updateMicroblogs(form, url.get("value"), updateContainer);

			<c:if test="<%= repost %>">
				Liferay.Microblogs.closePopup();
			</c:if>
		}
	);
</aui:script>