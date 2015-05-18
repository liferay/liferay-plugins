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

<%@ include file="/admin/init.jsp" %>

<%
KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

boolean showAdminSuggestionView = SuggestionPermission.contains(permissionChecker, scopeGroupId, kbArticle, ActionKeys.VIEW_SUGGESTIONS);

KBArticleURLHelper kbArticleURLHelper = new KBArticleURLHelper(renderRequest, renderResponse, templatePath);
%>

<c:if test="<%= enableKBArticleRatings %>">

	<%
	int kbCommentsCount = 0;
	int pendingKBCommentsCount = 0;

	if (showAdminSuggestionView) {
		kbCommentsCount = KBCommentLocalServiceUtil.getKBCommentsCount(KBArticle.class.getName(), kbArticle.getClassPK());

		pendingKBCommentsCount = KBCommentLocalServiceUtil.getKBCommentsCount(KBArticle.class.getName(), kbArticle.getClassPK(), new int[]{KBCommentConstants.STATUS_IN_PROGRESS, KBCommentConstants.STATUS_NEW});
	}
	else {
		kbCommentsCount = KBCommentLocalServiceUtil.getKBCommentsCount(themeDisplay.getUserId(), KBArticle.class.getName(), kbArticle.getClassPK());
	}
	%>

	<liferay-ui:ratings
		className="<%= KBArticle.class.getName() %>"
		classPK="<%= kbArticle.getResourcePrimKey() %>"
		numberOfStars="<%= PortletPropsValues.KNOWLEDGE_BASE_RATINGS_NUMBER_OF_STARS %>"
		type="<%= kbArticleRatingsType %>"
	/>

	<c:if test='<%= kbArticleRatingsType.equals("thumbs") && themeDisplay.isSignedIn() %>'>
		<div class="kb-article-suggestion-actions" id="<portlet:namespace />additionalSuggestionActionsContainer">
			<a data-show-node-id="<portlet:namespace />suggestionContainer" href="javascript:void(0)">
				<liferay-ui:message key="do-you-have-any-suggestions" />
			</a>

			<c:choose>
				<c:when test="<%= kbCommentsCount == 1 %>">
					|

					<a data-show-node-id="<portlet:namespace />previousCommentsContainer" href="javascript:void(0)">
						<c:choose>
							<c:when test="<%= showAdminSuggestionView %>">
								<liferay-ui:message key="there-is-one-suggestion" />

								<c:if test="<%= pendingKBCommentsCount > 0 %>">
									(<liferay-ui:message arguments="<%= pendingKBCommentsCount %>" key="x-pending" />)
								</c:if>
							</c:when>
							<c:otherwise>
								<liferay-ui:message key="you-sent-one-suggestion-for-this-article" />
							</c:otherwise>
						</c:choose>
					</a>
				</c:when>
				<c:when test="<%= kbCommentsCount > 1 %>">
					|

					<a data-show-node-id="<portlet:namespace />previousCommentsContainer" href="javascript:void(0)">
						<c:choose>
							<c:when test="<%= showAdminSuggestionView %>">
								<liferay-ui:message arguments="<%= kbCommentsCount %>" key="there-are-x-suggestions" />

								<c:if test="<%= pendingKBCommentsCount > 0 %>">
									(<liferay-ui:message arguments="<%= pendingKBCommentsCount %>" key="x-pending" />)
								</c:if>
							</c:when>
							<c:otherwise>
								<liferay-ui:message arguments="<%= kbCommentsCount %>" key="you-sent-x-suggestions-for-this-article" />
							</c:otherwise>
						</c:choose>
					</a>
				</c:when>
			</c:choose>
		</div>

		<a name="kbSuggestions"></a>

		<div class="hide kb-article-suggestion" id="<portlet:namespace />suggestionContainer">

			<%
			PortletURL viewKBArticleURL = kbArticleURLHelper.createViewWithCommentsURL(kbArticle);
			%>

			<liferay-portlet:actionURL name="updateKBComment" var="updateKBCommentURL">
				<portlet:param name="redirect" value="<%= viewKBArticleURL.toString() %>" />
			</liferay-portlet:actionURL>

			<aui:form action='<%= updateKBCommentURL + "#kbSuggestions" %>' method="post" name="suggestionFm">
				<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
				<aui:input name="classNameId" type="hidden" value="<%= PortalUtil.getClassNameId(KBArticle.class) %>" />
				<aui:input name="classPK" type="hidden" value="<%= kbArticle.getResourcePrimKey() %>" />

				<liferay-ui:error exception="<%= KBCommentContentException.class %>" message="please-enter-valid-content" />

				<aui:model-context model="<%= KBComment.class %>" />

				<aui:fieldset>
					<span class="kb-helpful-text">
						<liferay-ui:message key="what-did-you-like-the-most-what-would-you-improve" />
					</span>

					<aui:input label="" name="content" />

					<aui:button-row cssClass="kb-submit-buttons">
						<aui:button type="submit" value="submit" />

						<aui:button name="cancelSuggestion" value="cancel" />
					</aui:button-row>
				</aui:fieldset>
			</aui:form>
		</div>

		<liferay-ui:success
			key="suggestionDeleted"
			message="suggestion-deleted-successfully"
		/>

		<liferay-ui:success
			key="suggestionStatusUpdated"
			message="suggestion-status-updated-successfully"
		/>

		<liferay-ui:success
			key="suggestionSaved"
			message="suggestion-saved-successfully"
		/>

		<%
		boolean expanded = ParamUtil.getBoolean(request, "expanded");
		%>

		<c:choose>
			<c:when test="<%= showAdminSuggestionView %>">

				<%
				String navItem = ParamUtil.getString(request, "navItem", "viewNewSuggestions");

				KBSuggestionListDisplayContext kbSuggestionListDisplayContext = new KBSuggestionListDisplayContext(request, templatePath, kbArticle, navItem);

				request.setAttribute(WebKeys.KNOWLEDGE_BASE_KB_SUGGESTION_LIST_DISPLAY_CONTEXT, kbSuggestionListDisplayContext);
				%>

				<div class='kb-article-previous-comments <%= expanded ? StringPool.BLANK : "hide" %>' id="<portlet:namespace />previousCommentsContainer">
					<liferay-util:include page="/admin/common/view_suggestions_by_status.jsp" servletContext="<%= application %>" />
				</div>
			</c:when>
			<c:otherwise>
				<div class='kb-article-previous-comments <%= expanded ? "" : "hide" %>' id="<portlet:namespace />previousCommentsContainer">
					<c:if test="<%= kbCommentsCount > 0 %>">
						<liferay-portlet:renderURL varImpl="iteratorURL">
							<portlet:param name="expanded" value="<%= Boolean.TRUE.toString() %>" />
						</liferay-portlet:renderURL>

						<liferay-ui:search-container
							emptyResultsMessage="no-comments-found"
							iteratorURL="<%= iteratorURL %>"
							orderByComparator='<%= KnowledgeBaseUtil.getKBCommentOrderByComparator("modified-date", "desc") %>'
							total="<%= kbCommentsCount %>"
						>

							<liferay-ui:search-container-results
								results="<%= KBCommentLocalServiceUtil.getKBComments(themeDisplay.getUserId(), KBArticle.class.getName(), kbArticle.getClassPK(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
							/>

							<liferay-ui:search-container-row
								className="com.liferay.knowledgebase.model.KBComment"
								escapedModel="true"
								modelVar="kbComment"
							>
								<div class="kb-article-comment">
									<p class="kb-article-comment-body">
										<%= kbComment.getContent() %>
									</p>

									<div class="kb-article-comment-post-date">
										<i class="icon-calendar"></i>

										<%
										DateSearchEntry dateSearchEntry = new DateSearchEntry();

										dateSearchEntry.setDate(kbComment.getModifiedDate());
										%>

										<%= dateSearchEntry.getName(request) %>

										<aui:model-context bean="<%= kbComment %>" model="<%= KBComment.class %>" />

										<aui:workflow-status status="<%= kbComment.getStatus() %>" statusMessage="<%= KnowledgeBaseUtil.getStatusLabel(kbComment.getStatus()) %>" />
									</div>
								</div>
							</liferay-ui:search-container-row>

							<liferay-ui:search-iterator />
						</liferay-ui:search-container>
					</c:if>
				</div>
			</c:otherwise>
		</c:choose>

		<aui:script use="aui-base">
			A.one('#<portlet:namespace />additionalSuggestionActionsContainer').delegate(
				'click',
				function(event) {
					var showNode = A.one('#' + event.currentTarget.getData('show-node-id'));

					showNode.toggleView();

					var content = showNode.one('#<portlet:namespace />content');

					if (content) {
						content.focus();
					}
				},
				'a'
			);

			A.one('#<portlet:namespace />cancelSuggestion').on(
				'click',
				function(event) {
					var container = this.ancestor('#<portlet:namespace />suggestionContainer');

					container.hide();

					var content = container.one('#<portlet:namespace />content');

					if (content) {
						content.val('');
					}
				}
			);
		</aui:script>
	</c:if>
</c:if>