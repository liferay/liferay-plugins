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
int status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);

KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

KBComment kbComment = null;

try {
	kbComment = KBCommentLocalServiceUtil.getKBComment(user.getUserId(), KBArticle.class.getName(), kbArticle.getResourcePrimKey());
}
catch (NoSuchCommentException nsce) {
}

long kbCommentId = BeanParamUtil.getLong(kbComment, request, "kbCommentId");

boolean helpful = BeanParamUtil.getBoolean(kbComment, request, "helpful", true);
%>

<c:if test="<%= ((enableKBArticleKBComments && themeDisplay.isSignedIn()) || showKBArticleKBComments) && (kbArticle.isApproved() || !kbArticle.isFirstVersion()) %>">
	<div class="kb-article-comments">
		<aui:form method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "updateKBComment();" %>'>
			<aui:input name="<%= Constants.CMD %>" type="hidden" />
			<aui:input name="kbCommentId" type="hidden" value="<%= kbCommentId %>" />
			<aui:input name="classNameId" type="hidden" value="<%= PortalUtil.getClassNameId(KBArticle.class) %>" />
			<aui:input name="classPK" type="hidden" value="<%= kbArticle.getResourcePrimKey() %>" />

			<liferay-ui:error exception="<%= KBCommentContentException.class %>" message="please-enter-valid-content" />

			<aui:model-context bean="<%= kbComment %>" model="<%= KBComment.class %>" />

			<aui:fieldset>
				<c:if test="<%= enableKBArticleKBComments && themeDisplay.isSignedIn() %>">
					<liferay-ui:panel-container extended="<%= false %>" id='<%= renderResponse.getNamespace() + "Article" + kbArticle.getResourcePrimKey() + "CommentsPanelContainer" %>' persistState="<%= true %>">
						<liferay-ui:panel collapsible="<%= true %>" defaultState="closed" extended="<%= true %>" id='<%= renderResponse.getNamespace() + "Article" + kbArticle.getResourcePrimKey() + "CommentsPanel" %>' persistState="<%= true %>" title="comments">
							<c:if test="<%= kbComment != null %>">

								<%
								request.setAttribute("article_comment.jsp-kb_comment", kbComment);
								%>

								<liferay-util:include page="/admin/article_comment.jsp" servletContext="<%= application %>" />
							</c:if>

							<aui:input label="" name="content" />

							<div class="kb-helpful-inputs">
								<span class="kb-helpful-text"><liferay-ui:message key="was-this-information-helpful" /></span>

								<aui:input checked="<%= helpful %>" inlineField="<%= true %>" label="yes" name="helpful" type="radio" value="1" />

								<aui:input checked="<%= !helpful %>" inlineField="<%= true %>" label="no" name="helpful" type="radio" value="0" />
							</div>

							<aui:button-row cssClass="kb-submit-buttons">
								<aui:button type="submit" value="post" />
							</aui:button-row>
						</liferay-ui:panel>
					</liferay-ui:panel-container>
				</c:if>

				<c:if test="<%= showKBArticleKBComments %>">
					<liferay-portlet:renderURL varImpl="iteratorURL">
						<portlet:param name="jspPage" value='<%= jspPath + "view_article.jsp" %>' />
						<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
						<portlet:param name="status" value="<%= String.valueOf(status) %>" />
					</liferay-portlet:renderURL>

					<liferay-ui:search-container
						iteratorURL="<%= iteratorURL %>"
					>
						<liferay-ui:search-container-results
							results="<%= KBCommentLocalServiceUtil.getKBComments(KBArticle.class.getName(), kbArticle.getResourcePrimKey(), searchContainer.getStart(), searchContainer.getEnd(), null) %>"
							total="<%= KBCommentLocalServiceUtil.getKBCommentsCount(KBArticle.class.getName(), kbArticle.getResourcePrimKey()) %>"
						/>

						<c:if test="<%= total > 0 %>">
							<div class="separator"><!-- --></div>

							<div class="kb-all-comments">
								<%= LanguageUtil.format(pageContext, "all-comments-x", total) %>
							</div>
						</c:if>

						<%
						for (KBComment curKBComment : (List<KBComment>)results) {
						%>

							<%
							request.setAttribute("article_comment.jsp-kb_comment", curKBComment);
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
			</aui:fieldset>
		</aui:form>
	</div>

	<aui:script>
		function <portlet:namespace />deleteKBComment(kbCommentId) {
			document.<portlet:namespace />fm.<portlet:namespace />kbCommentId.value = kbCommentId;
			submitForm(document.<portlet:namespace />fm, "<liferay-portlet:actionURL name="deleteKBComment"><portlet:param name="jspPage" value='<%= jspPath + "view_article.jsp" %>' /><portlet:param name="redirect" value="<%= redirect %>" /><portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" /><portlet:param name="status" value="<%= String.valueOf(status) %>" /></liferay-portlet:actionURL>");
		}

		function <portlet:namespace />updateKBComment() {
			document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= (kbComment == null) ? Constants.ADD : Constants.UPDATE %>";
			submitForm(document.<portlet:namespace />fm, "<liferay-portlet:actionURL name="updateKBComment"><portlet:param name="jspPage" value='<%= jspPath + "view_article.jsp" %>' /><portlet:param name="redirect" value="<%= redirect %>" /><portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" /><portlet:param name="status" value="<%= String.valueOf(status) %>" /></liferay-portlet:actionURL>");
		}
	</aui:script>
</c:if>