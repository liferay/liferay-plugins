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
KBTemplate kbTemplate = (KBTemplate)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_TEMPLATE);

KBComment kbComment = null;

try {
	kbComment = KBCommentLocalServiceUtil.getKBComment(user.getUserId(), KBTemplate.class.getName(), kbTemplate.getKbTemplateId());
}
catch (NoSuchCommentException nsce) {
}

long kbCommentId = BeanParamUtil.getLong(kbComment, request, "kbCommentId");

boolean helpful = BeanParamUtil.getBoolean(kbComment, request, "helpful", true);
%>

<c:if test="<%= (enableKBTemplateKBComments && themeDisplay.isSignedIn()) || showKBTemplateKBComments %>">
	<div class="kb-template-comments">
		<aui:form method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "updateKBComment();" %>'>
			<aui:input name="<%= Constants.CMD %>" type="hidden" />
			<aui:input name="kbCommentId" type="hidden" value="<%= kbCommentId %>" />
			<aui:input name="classNameId" type="hidden" value="<%= PortalUtil.getClassNameId(KBTemplate.class) %>" />
			<aui:input name="classPK" type="hidden" value="<%= kbTemplate.getKbTemplateId() %>" />

			<liferay-ui:error exception="<%= KBCommentContentException.class %>" message="please-enter-valid-content" />

			<aui:model-context bean="<%= kbComment %>" model="<%= KBComment.class %>" />

			<aui:fieldset>
				<c:if test="<%= enableKBTemplateKBComments && themeDisplay.isSignedIn() %>">
					<liferay-ui:panel-container extended="<%= false %>" id='<%= renderResponse.getNamespace() + "Template" + kbTemplate.getKbTemplateId() + "CommentsPanelContainer" %>' persistState="<%= true %>">
						<liferay-ui:panel collapsible="<%= true %>" defaultState="closed" extended="<%= true %>" id='<%= renderResponse.getNamespace() + "Template" + kbTemplate.getKbTemplateId() + "CommentsPanel" %>' persistState="<%= true %>" title="comments">
							<c:if test="<%= kbComment != null %>">

								<%
								request.setAttribute("template_comment.jsp-kb_comment", kbComment);
								%>

								<liferay-util:include page="/admin/template_comment.jsp" servletContext="<%= application %>" />
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

				<c:if test="<%= showKBTemplateKBComments %>">
					<liferay-portlet:renderURL varImpl="iteratorURL">
						<portlet:param name="mvcPath" value='<%= templatePath + "view_template.jsp" %>' />
						<portlet:param name="kbTemplateId" value="<%= String.valueOf(kbTemplate.getKbTemplateId()) %>" />
					</liferay-portlet:renderURL>

					<liferay-ui:search-container
						iteratorURL="<%= iteratorURL %>"
						total="<%= KBCommentLocalServiceUtil.getKBCommentsCount(KBTemplate.class.getName(), kbTemplate.getKbTemplateId()) %>"
					>
						<liferay-ui:search-container-results
							results="<%= KBCommentLocalServiceUtil.getKBComments(KBTemplate.class.getName(), kbTemplate.getKbTemplateId(), searchContainer.getStart(), searchContainer.getEnd(), null) %>"
						/>

						<c:if test="<%= total > 0 %>">
							<div class="separator"><!-- --></div>

							<div class="kb-all-comments">
								<%= LanguageUtil.format(pageContext, "all-comments-x", total, false) %>
							</div>
						</c:if>

						<%
						for (KBComment curKBComment : (List<KBComment>)searchContainer.getResults()) {
						%>

							<%
							request.setAttribute("template_comment.jsp-kb_comment", curKBComment);
							%>

							<liferay-util:include page="/admin/template_comment.jsp" servletContext="<%= application %>" />

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
			submitForm(document.<portlet:namespace />fm, '<liferay-portlet:actionURL name="deleteKBComment"><portlet:param name="mvcPath" value='<%= templatePath + "view_template.jsp" %>' /><portlet:param name="redirect" value="<%= redirect %>" /><portlet:param name="kbTemplateId" value="<%= String.valueOf(kbTemplate.getKbTemplateId()) %>" /></liferay-portlet:actionURL>');
		}

		function <portlet:namespace />updateKBComment() {
			document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = '<%= (kbComment == null) ? Constants.ADD : Constants.UPDATE %>';
			submitForm(document.<portlet:namespace />fm, '<liferay-portlet:actionURL name="updateKBComment"><portlet:param name="mvcPath" value='<%= templatePath + "view_template.jsp" %>' /><portlet:param name="redirect" value="<%= redirect %>" /><portlet:param name="kbTemplateId" value="<%= String.valueOf(kbTemplate.getKbTemplateId()) %>" /></liferay-portlet:actionURL>');
		}
	</aui:script>
</c:if>