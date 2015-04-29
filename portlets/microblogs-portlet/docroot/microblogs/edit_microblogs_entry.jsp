<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

long microblogsEntryId = ParamUtil.getLong(request, "microblogsEntryId");

if (microblogsEntryId <= 0) {
	microblogsEntryId = GetterUtil.getLong(request.getAttribute("view_comments.jsp-parentMicroblogsEntryId"));
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

String receiverUserDisplayURL = StringPool.BLANK;
String receiverUserFullName = StringPool.BLANK;
String receiverUserPortaitURL = StringPool.BLANK;
String receiverUserScreenName = StringPool.BLANK;

boolean edit = ParamUtil.getBoolean(request, "edit");
boolean repost = ParamUtil.getBoolean(request, "repost");

if ((microblogsEntry != null) && !edit) {
	modifiedDate = dateFormatDateTime.format(microblogsEntry.getModifiedDate());

	receiverUserFullName = HtmlUtil.escape(PortalUtil.getUserName(microblogsEntry));

	try {
		User receiverUser = UserLocalServiceUtil.getUserById(microblogsEntry.getUserId());

		receiverUserDisplayURL = receiverUser.getDisplayURL(themeDisplay);
		receiverUserPortaitURL = receiverUser.getPortraitURL(themeDisplay);
		receiverUserScreenName = receiverUser.getScreenName();
	}
	catch (NoSuchUserException nsue) {
	}
}

String formId = String.valueOf(microblogsEntryId);

if (edit) {
	formId = "Edit" + formId;
}

String formName = "fm" + formId;

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
			<div class="alert alert-danger">
				<liferay-ui:message key="entry-could-not-be-found" />
			</div>
		</c:when>
		<c:otherwise>
			<div class="microblogs-entry">
				<span class="thumbnail">
					<a href="<%= receiverUserDisplayURL %>"><img alt="<%= HtmlUtil.escapeAttribute(receiverUserFullName) %>" src="<%= receiverUserPortaitURL %>" /></a>
				</span>

				<div class="entry-bubble">
					<div class="user-name">
						<span><%= receiverUserFullName %></span> <span class="small">(<%= receiverUserScreenName %>)</span>
					</div>

					<div class="content">
						<span><%= HtmlUtil.escape(microblogsEntry.getContent()) %></span>
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

<aui:form action="<%= updateMicroblogsEntryURL %>" cssClass="<%= formCssClass %>" name="<%= formName %>">
	<portlet:renderURL var="commentsURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
		<portlet:param name="mvcPath" value="/microblogs/view_comments.jsp" />
		<portlet:param name="parentMicroblogsEntryId" value="<%= String.valueOf(microblogsEntryId) %>" />
	</portlet:renderURL>

	<aui:input name="redirect" type="hidden" value="<%= comment ? commentsURL : redirect %>" />
	<aui:input name="microblogsEntryId" type="hidden" value="<%= edit ? microblogsEntryId : 0 %>" />
	<aui:input name="parentMicroblogsEntryId" type="hidden" value="<%= microblogsEntryId %>" />

	<aui:model-context bean="<%= microblogsEntry %>" model="<%= MicroblogsEntry.class %>" />

	<c:choose>
		<c:when test="<%= repost %>">
			<aui:input name="type" type="hidden" value="<%= MicroblogsEntryConstants.TYPE_REPOST %>" />

			<aui:input name="content" type="hidden" />
		</c:when>
		<c:when test="<%= comment %>">
			<aui:input name="type" type="hidden" value="<%= MicroblogsEntryConstants.TYPE_REPLY %>" />
		</c:when>
	</c:choose>

	<c:if test="<%= !repost %>">
		<c:if test="<%= comment %>">
			<span class="thumbnail">
				<a href="<%= user.getDisplayURL(themeDisplay) %>"><img alt="<%= HtmlUtil.escapeAttribute(user.getFullName()) %>" src="<%= user.getPortraitURL(themeDisplay) %>" /></a>
			</span>
		</c:if>

		<div class="autocomplete inactive" id="<portlet:namespace />autocomplete<%= formId %>">
			<div id="<portlet:namespace />autocompleteContent<%= formId %>">
				<span class="placeholder-text" id="<portlet:namespace />placeholderText<%= formId %>">
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

			<div class="highlighter-content <%= comment || edit || repost ? StringPool.BLANK : "textbox" %>" id="<portlet:namespace />highlighterContent<%= formId %>"></div>
		</div>

		<aui:input label="" name="content" type="hidden" />
	</c:if>

	<span class="microblogs-countdown-holder">
		<c:if test="<%= !repost %>">
			<span class="microblogs-countdown"><%= 150 - (((microblogsEntry != null) && edit) ? microblogsEntry.getContent() : StringPool.BLANK).length() %></span>
		</c:if>
	</span>

	<%
	String rowCssClass = "button-holder";

	if (!repost) {
		rowCssClass += " hide";
	}
	%>

	<div class="<%= rowCssClass %>">
		<aui:button cssClass="microblogs-post pull-left" disabled="<%= !repost %>" type="submit" value="post" />

		<c:if test="<%= repost %>">
			<aui:button onClick="Liferay.Microblogs.closePopup();" type="cancel" />
		</c:if>

		<c:if test="<%= !comment && !repost %>">

			<%
			int socialRelationType = 0;

			if (microblogsEntry != null) {
				socialRelationType = microblogsEntry.getSocialRelationType();
			}
			%>

			<aui:select inlineLabel="true" label="viewable-by" name="socialRelationType" value="<%= socialRelationType %>">
				<aui:option label="everyone" value="<%= MicroblogsEntryConstants.TYPE_EVERYONE %>" />
				<aui:option label="connections" value="<%= SocialRelationConstants.TYPE_BI_CONNECTION %>" />
				<aui:option label="followers" value="<%= SocialRelationConstants.TYPE_UNI_FOLLOWER %>" />
			</aui:select>
		</c:if>
	</div>
</aui:form>

<aui:script use="aui-base,aui-event-input,aui-template-deprecated,aui-form-textarea-deprecated,autocomplete,autocomplete-filters">
	var MAP_MATCHED_USERS = {
		screenName: function(str, match) {
			return '[@' + MAP_USERS[str] + ']';
		},
		userName: function(str, match) {
			return '<span>' + str + '</span>';
		}
	};

	var MAP_USERS = {};

	var REGEX_USER_NAME = /@(.*[^\s]+)$/;

	var TPL_SEARCH_RESULTS = '<div class="microblogs-autocomplete">' +
			'<div class="thumbnail">' +
				'<img alt="{fullName}" src="{portraitURL}" />' +
			'</div>' +
			'<div>' +
				'<span class="user-name">{fullName}</span>' +
				'<br />' +
				'<span class="small">{emailAddress}</span>' +
				'<br />' +
				'<span class="job-title">{jobTitle}</span>' +
			'</div>' +
		'</div>';

	var autocompleteDiv;

	var form = A.one('#<portlet:namespace /><%= formName %>');

	<c:if test="<%= !repost %>">
		var countContent = function(event) {
			var contentInput = event.currentTarget;

			var countdown = form.one('.microblogs-countdown');
			var submitButton = form.one('.microblogs-post');

			var remaining = (150 - contentInput.val().length);

			var disabled = ((remaining == 150) || (contentInput.get('value') == '') || (remaining < 0));

			countdown.html(remaining);

			submitButton.attr('disabled', disabled);

			submitButton.toggleClass('disabled', disabled);
			submitButton.toggleClass('btn-warning', disabled);

			countdown.toggleClass('microblogs-countdown-warned', disabled);
		};

		var createTextarea = function(divId) {
			var autocomplete = A.one('#<portlet:namespace />autocomplete<%= formId %>');
			var autocompleteContent = A.one('#<portlet:namespace />autocompleteContent<%= formId %>');
			var highlighterContent = A.one('#<portlet:namespace />highlighterContent<%= formId %>');

			var inputValue = '<%= ((microblogsEntry != null) && (edit)) ? StringUtil.replace(HtmlUtil.escapeJS(microblogsEntry.getContent()), "\'", "\\'") : StringPool.BLANK %>';

			if ((autocomplete.height() < 45) || (highlighterContent.height() < 45)) {
				autocomplete.height(45);

				highlighterContent.height(45);
			}

			var textarea = new A.Textarea(
				{
					autoSize: true,
					id: '<portlet:namespace />contentInput<%= formId %>'
				}
			).render(autocompleteContent);

			var contentTextarea = autocompleteContent.one('textarea');

			contentTextarea.on(
				'focus',
				function(contentTextarea) {
					autocomplete.removeClass('inactive');

					var buttonContainer = form.one('.button-holder');

					buttonContainer.show();

					var placeholderText = A.one('#<portlet:namespace />placeholderText<%= formId %>');

					if (placeholderText) {
						placeholderText.remove();
					}
				}
			);

			var contextCountEvent = 'input';

			if (A.UA.ie >= 9) {
				contextCountEvent = ['input', 'keydown'];
			}

			contentTextarea.on(
				contextCountEvent,
				function(contentTextarea) {
					updateHighlightDivSize(contentTextarea);

					countContent(contentTextarea);
				}
			);

			createAutocomplete(contentTextarea);

			contentTextarea.focus();

			contentTextarea.val(inputValue);

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

			var highlighterContent = A.one('#<portlet:namespace />highlighterContent<%= formId %>');

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

			highlighterContent.html('<div>' + updatedText + '</div>');
		};

		var updateHighlightDivSize = function(event) {
			var contentInput = event.currentTarget;

			var autocomplete = A.one('#<portlet:namespace />autocomplete<%= formId %>');
			var highlighterContent = A.one('#<portlet:namespace />highlighterContent<%= formId %>');

			var contentInputHeight = contentInput.height();

			if (contentInputHeight > 45) {
				autocomplete.height(contentInputHeight);

				highlighterContent.height(contentInputHeight);
			}
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

			autocompleteDiv.hide();
		};

		var createAutocomplete = function(contentTextarea) {
			autocompleteDiv = new A.AutoComplete(
				{
					inputNode: contentTextarea,
					maxResults: 5,
					on: {
						clear: function() {
							var highlighterContent = A.one('#<portlet:namespace />highlighterContent<%= formId %>');

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

			return autocompleteDiv;
		};

		<c:choose>
			<c:when test="<%= !edit %>">
				var autocomplete = A.one('#<portlet:namespace />autocomplete<%= formId %>');

				autocomplete.on(
					'click',
					function(event) {
						var contentInput = A.one('#<portlet:namespace />autocompleteContent<%= formId %> textarea');
						var highlighterContent = A.one('#<portlet:namespace />highlighterContent<%= formId %>');

						if (!contentInput) {
							highlighterContent.removeClass('textbox');

							createTextarea('#<portlet:namespace />autocompleteContent');
						}
						else {
							contentInput.focus();
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
				var content = form.one('input[name="<portlet:namespace />content"]');
				var contentInput = A.one('#<portlet:namespace />autocompleteContent<%= formId %> textarea');

				var contentInputValue = contentInput.val();

				var updatedText = replaceName(contentInputValue, 'screenName');

				content.val(updatedText);
			</c:if>

			var url = form.one('input[name="<portlet:namespace />redirect"]');

			var updateContainer = A.one('#p_p_id<portlet:namespace /> .portlet-body');

			<c:if test="<%= comment %>">
				updateContainer = A.one('#<portlet:namespace />commentsContainer<%= microblogsEntryId %>');
			</c:if>

			Liferay.Microblogs.updateMicroblogs(form, url.get('value'), updateContainer);

			<c:if test="<%= repost %>">
				Liferay.Microblogs.closePopup();
			</c:if>
		}
	);
</aui:script>