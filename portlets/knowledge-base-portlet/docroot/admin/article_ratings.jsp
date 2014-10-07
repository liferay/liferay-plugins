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

boolean hasUpdatePermission = KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.UPDATE);
%>

<c:if test="<%= enableKBArticleRatings %>">

	<%
	int kbCommentsCount = 0;
	int pendingKBCommentsCount = 0;

	if (hasUpdatePermission) {
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
		<div class="kb-article-feedback-actions" id="<portlet:namespace />additionalFeedbackActionsContainer">
			<a data-show-node-id="<portlet:namespace />feedbackContainer" href="javascript:void(0)">
				<liferay-ui:message key="do-you-have-any-suggestions" />
			</a>

			<c:choose>
				<c:when test="<%= kbCommentsCount == 1 %>">
					|

					<a data-show-node-id="<portlet:namespace />previousCommentsContainer" href="javascript:void(0)">
						<c:choose>
							<c:when test="<%= hasUpdatePermission %>">
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
							<c:when test="<%= hasUpdatePermission %>">
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

		<a name="kbFeedback"></a>

		<div class="hide kb-article-feedback" id="<portlet:namespace />feedbackContainer">
			<liferay-portlet:renderURL var="viewKBArticle">
				<portlet:param name="expanded" value="true" />

				<c:choose>
					<c:when test="<%= Validator.isNotNull(kbArticle.getUrlTitle()) %>">
						<portlet:param name="urlTitle" value="<%= kbArticle.getUrlTitle() %>" />

						<c:if test="<%= kbArticle.getKbFolderId() != KBFolderConstants.DEFAULT_PARENT_FOLDER_ID %>">

							<%
							KBFolder kbFolder = KBFolderServiceUtil.getKBFolder(kbArticle.getKbFolderId());
							%>

							<portlet:param name="kbFolderUrlTitle" value="<%= kbFolder.getUrlTitle() %>" />
						</c:if>
					</c:when>
					<c:otherwise>
						<portlet:param name="resourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
						<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
					</c:otherwise>
				</c:choose>
			</liferay-portlet:renderURL>

			<liferay-portlet:actionURL name="updateKBComment" var="updateKBCommentURL">
				<portlet:param name="redirect" value="<%= viewKBArticle %>" />
			</liferay-portlet:actionURL>

			<aui:form action='<%= updateKBCommentURL + "#kbFeedback" %>' method="post" name="feedbackFm">
				<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
				<aui:input name="classNameId" type="hidden" value="<%= PortalUtil.getClassNameId(KBArticle.class) %>" />
				<aui:input name="classPK" type="hidden" value="<%= kbArticle.getResourcePrimKey() %>" />

				<liferay-ui:error exception="<%= KBCommentContentException.class %>" message="please-enter-valid-content" />

				<aui:model-context model="<%= KBComment.class %>" />

				<aui:fieldset>
					<span class="kb-helpful-text">
						<liferay-ui:message key="what-did-you-like-the-most-what-would-you-improve" />
					</span>

					<aui:input name="helpful" type="hidden" value="0" />

					<aui:input label="" name="content" />

					<aui:button-row cssClass="kb-submit-buttons">
						<aui:button type="submit" value="submit" />

						<aui:button name="cancelFeedback" value="cancel" />
					</aui:button-row>
				</aui:fieldset>
			</aui:form>
		</div>

		<liferay-ui:success
			key="feedbackDeleted"
			message="feedback-deleted-successfully"
		/>

		<liferay-ui:success
			key="feedbackStatusUpdated"
			message="feedback-status-updated-successfully"
		/>

		<liferay-ui:success
			key="feedbackSaved"
			message="feedback-saved-successfully"
		/>

		<%
		boolean expanded = ParamUtil.getBoolean(request, "expanded");
		%>

		<c:choose>
			<c:when test="<%= hasUpdatePermission %>">

				<%
				String navItem = ParamUtil.getString(request, "navItem", "viewNewFeedback");

				KBFeedbackListDisplayContext kbFeedbackListDisplayContext = new KBFeedbackListDisplayContext(kbArticle, navItem);

				request.setAttribute(WebKeys.KNOWLEDGE_BASE_KB_FEEDBACK_LIST_DISPLAY_CONTEXT, kbFeedbackListDisplayContext);
				%>

				<div class='kb-article-previous-comments <%= expanded ? StringPool.BLANK : "hide" %>' id="<portlet:namespace />previousCommentsContainer">
					<liferay-util:include page="/admin/common/view_feedback_by_status.jsp" servletContext="<%= application %>" />
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
						>

							<%
							List<KBComment> kbComments = null;

							if (hasUpdatePermission) {
								kbComments = KBCommentLocalServiceUtil.getKBComments(KBArticle.class.getName(), kbArticle.getClassPK(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());
							}
							else {
								kbComments = KBCommentLocalServiceUtil.getKBComments(themeDisplay.getUserId(), KBArticle.class.getName(), kbArticle.getClassPK(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());
							}
							%>

							<liferay-ui:search-container-results
								results="<%= kbComments %>"
								total="<%= kbCommentsCount %>"
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
			var feedbackFm = A.one('#<portlet:namespace />feedbackFm');

			feedbackFm.on(
				'submit',
				function(event) {
					var ratingThumb = A.one('.kb-article-container input[name="<portlet:namespace />ratingThumb"]');

					if (!ratingThumb) {
						return;
					}

					var helpful = this.one('#<portlet:namespace />helpful');

					helpful.val(ratingThumb.val() === 'up');
				}
			);

			A.one('#<portlet:namespace />additionalFeedbackActionsContainer').delegate(
				'click',
				function(event) {
					var showNode = A.one('#' + event.currentTarget.getData('show-node-id'));

					showNode.toggleView();

					var content = showNode.one('#content');

					if (content) {
						content.focus();
					}
				},
				'a'
			);

			A.one('#<portlet:namespace />cancelFeedback').on(
				'click',
				function(event) {
					this.each(
						function(node) {
							var container = node.ancestor('#<portlet:namespace />feedbackContainer');

							container.hide();

							var content = container.one('#content');

							if (content) {
								content.val('');
							}
						}
					);
				}
			);
		</aui:script>
	</c:if>
</c:if>