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

			<h4><a href="<%= viewKBArticleURL %>"><%= HtmlUtil.escape(kbArticle.getTitle()) %></a></h4>

			<div>
				<span class="kb-question"><liferay-ui:message key="was-this-information-helpful" /></span>

				<c:choose>
					<c:when test="<%= kbComment.getHelpful() %>">
						<strong class="kb-yes"><liferay-ui:message key="yes" /></strong>
					</c:when>
					<c:otherwise>
						<strong class="kb-no"><liferay-ui:message key="no" /></strong>
					</c:otherwise>
				</c:choose>
			</div>

			<br />

			<div>
				<%= HtmlUtil.escape(kbComment.getContent()) %>
			</div>

			<div class="kb-article-comment-date">

				<%
				DateSearchEntry dateSearchEntry = new DateSearchEntry();

				dateSearchEntry.setDate(kbComment.getModifiedDate());
				%>

				<span class="icon icon-calendar"></span> <%= dateSearchEntry.getName(pageContext) %>
			</div>

			<%
			int feedbackStatus = kbComment.getStatus();

			int previousStatus = KnowledgeBaseUtil.getPreviousStatus(feedbackStatus);
			int nextStatus = KnowledgeBaseUtil.getNextStatus(feedbackStatus);
			%>

			<div class="kb-feedback-actions">
				<c:if test="<%= previousStatus != KBCommentConstants.STATUS_NONE %>">
					<liferay-portlet:actionURL name="updateKBCommentStatus" var="previousStatusURL">
						<portlet:param name="kbCommentId" value="<%= String.valueOf(kbComment.getKbCommentId()) %>" />
						<portlet:param name="redirect" value="<%= redirect %>" />
						<portlet:param name="status" value="<%= String.valueOf(previousStatus) %>" />
					</liferay-portlet:actionURL>

					<aui:button href="<%= previousStatusURL %>" value="<%= KnowledgeBaseUtil.getStatusTransitionLabel(previousStatus) %>" />
				</c:if>

				<c:if test="<%= nextStatus != KBCommentConstants.STATUS_NONE %>">
					<liferay-portlet:actionURL name="updateKBCommentStatus" var="nextStatusURL">
						<portlet:param name="kbCommentId" value="<%= String.valueOf(kbComment.getKbCommentId()) %>" />
						<portlet:param name="redirect" value="<%= redirect %>" />
						<portlet:param name="status" value="<%= String.valueOf(nextStatus) %>" />
					</liferay-portlet:actionURL>

					<aui:button href="<%= nextStatusURL %>" value="<%= KnowledgeBaseUtil.getStatusTransitionLabel(nextStatus) %>" />
				</c:if>

				<c:if test="<%= feedbackStatus == KBCommentConstants.STATUS_RESOLVED && KBCommentPermission.contains(permissionChecker, kbComment, ActionKeys.DELETE) %>">
					<liferay-portlet:actionURL name="deleteKBComment" var="deleteURL">
						<portlet:param name="kbCommentId" value="<%= String.valueOf(kbComment.getKbCommentId()) %>" />
						<portlet:param name="redirect" value="<%= redirect %>" />
					</liferay-portlet:actionURL>

					<aui:button cssClass="kb-feedback-delete" href="<%= deleteURL %>" value="delete" />
				</c:if>
			</div>
		</td>
	</tr>
	</table>

	<div class="separator"><!-- --></div>
</div>