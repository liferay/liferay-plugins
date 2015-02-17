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

KBSuggestionListDisplayContext kbSuggestionListDisplayContext = (KBSuggestionListDisplayContext)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_SUGGESTION_LIST_DISPLAY_CONTEXT);

KBArticleURLHelper kbArticleURLHelper = new KBArticleURLHelper(renderRequest, renderResponse, templatePath);
%>

<div class="kb-article-comment">
	<table class="lfr-table">
	<tr>
		<td class="kb-article-comment-user">
			<liferay-ui:user-display
				displayStyle="<%= 2 %>"
				userId="<%= kbComment.getUserId() %>"
				userName="<%= kbComment.getUserName() %>"
			/>
		</td>
		<td>

			<%
			PortletURL viewKBArticleURL = kbArticleURLHelper.createViewWithRedirectURL(kbArticle, currentURL);
			%>

			<c:if test="<%= kbSuggestionListDisplayContext.isShowKBArticleTitle() %>">
				<h4><a href="<%= viewKBArticleURL.toString() %>"><%= HtmlUtil.escape(kbArticle.getTitle()) %></a></h4>
			</c:if>

			<div class="kb-article-comment-body">
				<%= HtmlUtil.replaceNewLine(HtmlUtil.escape(kbComment.getContent())) %>
			</div>

			<div class="kb-article-comment-helpful">
				<c:choose>
					<c:when test="<%= kbComment.getUserRating() == KBCommentConstants.USER_RATING_LIKE %>">
						<span class="icon icon-thumbs-up"></span>

						<liferay-ui:message key="the-user-liked-the-article" />
					</c:when>
					<c:when test="<%= kbComment.getUserRating() == KBCommentConstants.USER_RATING_DISLIKE %>">
						<span class="icon icon-thumbs-down"></span>

						<liferay-ui:message key="the-user-did-not-like-the-article" />
					</c:when>
				</c:choose>
			</div>

			<div class="kb-article-comment-date">

				<%
				DateSearchEntry dateSearchEntry = new DateSearchEntry();

				dateSearchEntry.setDate(kbComment.getModifiedDate());

				int suggestionStatus = kbComment.getStatus();
				%>

				<span class="icon icon-calendar"></span> <%= dateSearchEntry.getName(pageContext) %>

				<aui:model-context bean="<%= kbComment %>" model="<%= KBComment.class %>" />

				<aui:workflow-status status="<%= suggestionStatus %>" statusMessage="<%= KnowledgeBaseUtil.getStatusLabel(suggestionStatus) %>" />
			</div>

			<%
			int previousStatus = KnowledgeBaseUtil.getPreviousStatus(suggestionStatus);
			int nextStatus = KnowledgeBaseUtil.getNextStatus(suggestionStatus);
			%>

			<c:if test="<%= KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.UPDATE) %>">
				<div class="kb-suggestion-actions">
					<c:if test="<%= previousStatus != KBCommentConstants.STATUS_NONE %>">
						<liferay-portlet:actionURL name="updateKBCommentStatus" varImpl="previousStatusURL">
							<portlet:param name="kbCommentId" value="<%= String.valueOf(kbComment.getKbCommentId()) %>" />
							<portlet:param name="kbCommentStatus" value="<%= String.valueOf(previousStatus) %>" />
						</liferay-portlet:actionURL>

						<aui:button href="<%= kbSuggestionListDisplayContext.getViewSuggestionURL(previousStatusURL, kbSuggestionListDisplayContext.getSelectedNavItem()) %>" value="<%= KnowledgeBaseUtil.getStatusTransitionLabel(previousStatus) %>" />
					</c:if>

					<c:if test="<%= nextStatus != KBCommentConstants.STATUS_NONE %>">
						<liferay-portlet:actionURL name="updateKBCommentStatus" varImpl="nextStatusURL">
							<portlet:param name="kbCommentId" value="<%= String.valueOf(kbComment.getKbCommentId()) %>" />
							<portlet:param name="kbCommentStatus" value="<%= String.valueOf(nextStatus) %>" />
						</liferay-portlet:actionURL>

						<aui:button href="<%= kbSuggestionListDisplayContext.getViewSuggestionURL(nextStatusURL, kbSuggestionListDisplayContext.getSelectedNavItem()) %>" value="<%= KnowledgeBaseUtil.getStatusTransitionLabel(nextStatus) %>" />
					</c:if>

					<c:if test="<%= (suggestionStatus == KBCommentConstants.STATUS_COMPLETED) && KBCommentPermission.contains(permissionChecker, kbComment, ActionKeys.DELETE) %>">
						<liferay-portlet:actionURL name="deleteKBComment" varImpl="deleteURL">
							<portlet:param name="kbCommentId" value="<%= String.valueOf(kbComment.getKbCommentId()) %>" />
						</liferay-portlet:actionURL>

						<aui:button cssClass="kb-suggestion-delete" data-href="<%= kbSuggestionListDisplayContext.getViewSuggestionURL(deleteURL, kbSuggestionListDisplayContext.getSelectedNavItem()) %>" value="delete" />
					</c:if>
				</div>
			</c:if>
		</td>
	</tr>
	</table>
</div>