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

KBComment kbComment = (KBComment)request.getAttribute("article_comment.jsp-kb_comment");

KBFeedbackListDisplayContext kbFeedbackListDisplayContext = (KBFeedbackListDisplayContext)request.getAttribute(WebKeys.KB_FEEDBACK_LIST_DISPLAY_CONTEXT);
%>

<div class="kb-article-comment">
	<table class="lfr-table" width="100%">
	<tr>
		<td align="center" valign="top">
			<liferay-ui:user-display
				displayStyle="2"
				userId="<%= kbComment.getUserId() %>"
				userName="<%= kbComment.getUserName() %>"
			/>
		</td>
		<td valign="top" width="90%">
			<portlet:renderURL var="viewKBArticleURL">
				<portlet:param name="mvcPath" value='<%= templatePath + "view_article.jsp" %>' />
				<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
			</portlet:renderURL>

			<c:if test="<%= kbFeedbackListDisplayContext.isShowKBArticleTitle() %>">
				<h4><a href="<%= viewKBArticleURL %>"><%= HtmlUtil.escape(kbArticle.getTitle()) %></a></h4>
			</c:if>

			<div>
				<%= HtmlUtil.escape(kbComment.getContent()) %>
			</div>

			<div class="kb-article-comment-helpful">
				<c:choose>
					<c:when test="<%= kbComment.getHelpful() %>">
						<span class="icon icon-thumbs-up"></span>

						<liferay-ui:message key="the-user-liked-the-article" />
					</c:when>
					<c:otherwise>
						<span class="icon icon-thumbs-down"></span>

						<liferay-ui:message key="the-user-did-not-like-the-article" />
					</c:otherwise>
				</c:choose>
			</div>

			<div class="kb-article-comment-date">

				<%
				DateSearchEntry dateSearchEntry = new DateSearchEntry();

				dateSearchEntry.setDate(kbComment.getModifiedDate());

				int feedbackStatus = kbComment.getStatus();
				%>

				<span class="icon icon-calendar"></span> <%= dateSearchEntry.getName(pageContext) %>

				<aui:model-context bean="<%= kbComment %>" model="<%= KBComment.class %>" />

				<aui:workflow-status status="<%= feedbackStatus %>" statusMessage="<%= KnowledgeBaseUtil.getStatusLabel(feedbackStatus) %>" />
			</div>

			<%
			int previousStatus = KnowledgeBaseUtil.getPreviousStatus(feedbackStatus);
			int nextStatus = KnowledgeBaseUtil.getNextStatus(feedbackStatus);
			%>

			<div class="kb-feedback-actions">
				<c:if test="<%= previousStatus != KBCommentConstants.STATUS_NONE %>">
					<liferay-portlet:actionURL name="updateKBCommentStatus" varImpl="previousStatusURL">
						<portlet:param name="kbCommentId" value="<%= String.valueOf(kbComment.getKbCommentId()) %>" />
						<portlet:param name="status" value="<%= String.valueOf(previousStatus) %>" />
					</liferay-portlet:actionURL>

					<aui:button href="<%= kbFeedbackListDisplayContext.getViewFeedbackURL(previousStatusURL, kbFeedbackListDisplayContext.getSelectedNavItem()) %>" value="<%= KnowledgeBaseUtil.getStatusTransitionLabel(previousStatus) %>" />
				</c:if>

				<c:if test="<%= nextStatus != KBCommentConstants.STATUS_NONE %>">
					<liferay-portlet:actionURL name="updateKBCommentStatus" varImpl="nextStatusURL">
						<portlet:param name="kbCommentId" value="<%= String.valueOf(kbComment.getKbCommentId()) %>" />
						<portlet:param name="status" value="<%= String.valueOf(nextStatus) %>" />
					</liferay-portlet:actionURL>

					<aui:button href="<%= kbFeedbackListDisplayContext.getViewFeedbackURL(nextStatusURL, kbFeedbackListDisplayContext.getSelectedNavItem()) %>" value="<%= KnowledgeBaseUtil.getStatusTransitionLabel(nextStatus) %>" />
				</c:if>

				<c:if test="<%= (feedbackStatus == KBCommentConstants.STATUS_COMPLETED) && KBCommentPermission.contains(permissionChecker, kbComment, ActionKeys.DELETE) %>">
					<liferay-portlet:actionURL name="deleteKBComment" varImpl="deleteURL">
						<portlet:param name="kbCommentId" value="<%= String.valueOf(kbComment.getKbCommentId()) %>" />
					</liferay-portlet:actionURL>

					<aui:button cssClass="kb-feedback-delete" href="<%= kbFeedbackListDisplayContext.getViewFeedbackURL(deleteURL, kbFeedbackListDisplayContext.getSelectedNavItem()) %>" value="delete" />
				</c:if>
			</div>
		</td>
	</tr>
	</table>
</div>