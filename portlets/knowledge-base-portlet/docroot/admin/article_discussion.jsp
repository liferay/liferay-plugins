<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/admin/init.jsp" %>

<%
Article article = (Article)request.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);
%>

<c:if test="<%= enableArticleComments && (ArticlePermission.contains(permissionChecker, article, ActionKeys.ADD_DISCUSSION) || (MBMessageLocalServiceUtil.getDiscussionMessagesCount(Article.class.getName(), article.getResourcePrimKey(), WorkflowConstants.STATUS_APPROVED) > 0)) %>">
	<div class="kb-article-discussion">
		<liferay-ui:panel-container extended="<%= false %>" id='<%= renderResponse.getNamespace() + "Article" + article.getResourcePrimKey() + "CommentsPanelContainer" %>' persistState="<%= true %>">
			<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id='<%= renderResponse.getNamespace() + "Article" + article.getResourcePrimKey() + "CommentsPanel" %>' persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "comments") %>'>
				<portlet:actionURL name="invokeTaglibDiscussion" var="discussionURL" />

				<liferay-ui:discussion
					className="<%= Article.class.getName() %>"
					classPK="<%= article.getResourcePrimKey() %>"
					formAction="<%= discussionURL %>"
					formName="discussionFm"
					ratingsEnabled="<%= enableArticleCommentRatings %>"
					redirect="<%= currentURL %>"
					subject="<%= article.getTitle() %>"
					userId="<%= article.getUserId() %>"
				/>
			</liferay-ui:panel>
		</liferay-ui:panel-container>
	</div>
</c:if>