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

<%@ include file="/admin/init.jsp" %>

<%
Article article = (Article)request.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);

Comment comment = null;

try {
	comment = CommentLocalServiceUtil.getComment(user.getUserId(), Article.class.getName(), article.getResourcePrimKey());
}
catch (NoSuchCommentException nsce) {
}

long commentId = BeanParamUtil.getLong(comment, request, "commentId");

boolean helpful = BeanParamUtil.getBoolean(comment, request, "helpful", true);
%>

<c:if test="<%= (enableArticleComments || showArticleComments) && layoutTypePortlet.hasPortletId(portletDisplay.getId()) %>">
	<div class="kb-article-comments">
		<c:if test="<%= enableArticleComments && themeDisplay.isSignedIn() && (article.isApproved() || (article.getVersion() > ArticleConstants.DEFAULT_VERSION)) %>">
			<liferay-ui:panel-container extended="<%= false %>" id='<%= renderResponse.getNamespace() + "Article" + article.getResourcePrimKey() + "CommentsPanelContainer" %>' persistState="<%= true %>">
				<liferay-ui:panel collapsible="<%= true %>" defaultState="closed" extended="<%= true %>" id='<%= renderResponse.getNamespace() + "Article" + article.getResourcePrimKey() + "CommentsPanel" %>' persistState="<%= true %>" title="comments">
					<c:if test="<%= comment != null %>">

						<%
						request.setAttribute("article_comment.jsp-comment", comment);
						%>

						<liferay-util:include page="/admin/article_comment.jsp" servletContext="<%= application %>" />
					</c:if>

					<aui:form method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "updateComment();" %>'>
						<aui:input name="<%= Constants.CMD %>" type="hidden" />
						<aui:input name="commentId" type="hidden" value="<%= commentId %>" />
						<aui:input name="classNameId" type="hidden" value="<%= PortalUtil.getClassNameId(Article.class) %>" />
						<aui:input name="classPK" type="hidden" value="<%= article.getResourcePrimKey() %>" />

						<liferay-ui:error exception="<%= CommentContentException.class %>" message="please-enter-valid-content" />

						<aui:model-context bean="<%= comment %>" model="<%= Comment.class %>" />

						<aui:fieldset>
							<aui:input label="" name="content" />

							<div class="kb-helpful-inputs">
								<span class="kb-helpful-text"><liferay-ui:message key="was-this-information-helpful" /></span>

								<aui:input checked="<%= helpful %>" inlineField="<%= true %>" label="yes" name="helpful" type="radio" value="1" />

								<aui:input checked="<%= !helpful %>" inlineField="<%= true %>" label="no" name="helpful" type="radio" value="0" />
							</div>

							<aui:button-row cssClass="kb-submit-buttons">
								<aui:button type="submit" value="post" />
							</aui:button-row>
						</aui:fieldset>
					</aui:form>
				</liferay-ui:panel>
			</liferay-ui:panel-container>
		</c:if>

		<c:if test="<%= showArticleComments %>">
			<liferay-portlet:renderURL varImpl="iteratorURL">
				<portlet:param name="jspPage" value='<%= jspPath + "view_article.jsp" %>' />
				<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container
				iteratorURL="<%= iteratorURL %>"
			>
				<liferay-ui:search-container-results
					results="<%= CommentLocalServiceUtil.getComments(Article.class.getName(), article.getResourcePrimKey(), searchContainer.getStart(), searchContainer.getEnd(), null) %>"
					total="<%= CommentLocalServiceUtil.getCommentsCount(Article.class.getName(), article.getResourcePrimKey()) %>"
				/>

				<c:if test="<%= total > 0 %>">
					<div class="separator"><!-- --></div>
				</c:if>

				<%
				for (Comment curComment : (List<Comment>)results) {
				%>

					<%
					request.setAttribute("article_comment.jsp-comment", curComment);
					%>

					<liferay-util:include page="/admin/article_comment.jsp" servletContext="<%= application %>" />

				<%
				}
				%>

				<c:if test="<%= total > searchContainer.getDelta() %>">
					<div class="taglib-search-iterator-page-iterator-bottom">
						<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
					</div>
				</c:if>
			</liferay-ui:search-container>
		</c:if>
	</div>

	<portlet:renderURL var="viewArticleURL">
		<portlet:param name="jspPage" value='<%= jspPath + "view_article.jsp" %>' />
		<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
	</portlet:renderURL>

	<aui:script>
		function <portlet:namespace />deleteComment(commentId) {
			document.<portlet:namespace />fm.<portlet:namespace />commentId.value = commentId;
			submitForm(document.<portlet:namespace />fm, "<portlet:actionURL name="deleteComment"><portlet:param name="jspPage" value='<%= jspPath + "view_article.jsp" %>' /><portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" /><portlet:param name="redirect" value="<%= viewArticleURL %>" /></portlet:actionURL>");
		}

		function <portlet:namespace />updateComment() {
			document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= (comment == null) ? Constants.ADD : Constants.UPDATE %>";
			submitForm(document.<portlet:namespace />fm, "<portlet:actionURL name="updateComment"><portlet:param name="jspPage" value='<%= jspPath + "view_article.jsp" %>' /><portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" /><portlet:param name="redirect" value="<%= viewArticleURL %>" /></portlet:actionURL>");
		}
	</aui:script>
</c:if>