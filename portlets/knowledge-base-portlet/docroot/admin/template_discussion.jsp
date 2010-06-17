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
Template template = (Template)request.getAttribute(WebKeys.KNOWLEDGE_BASE_TEMPLATE);
%>

<c:if test="<%= enableTemplateComments && (TemplatePermission.contains(permissionChecker, template, ActionKeys.ADD_DISCUSSION) || (MBMessageLocalServiceUtil.getDiscussionMessagesCount(Template.class.getName(), template.getTemplateId(), WorkflowConstants.STATUS_APPROVED) > 0)) %>">
	<div class="kb-template-discussion">
		<liferay-ui:panel-container extended="<%= false %>" id='<%= renderResponse.getNamespace() + "Template" + template.getTemplateId() + "CommentsPanelContainer" %>' persistState="<%= true %>">
			<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id='<%= renderResponse.getNamespace() + "Template" + template.getTemplateId() + "CommentsPanel" %>' persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "comments") %>'>
				<portlet:actionURL name="invokeTaglibDiscussion" var="discussionURL" />

				<liferay-ui:discussion
					className="<%= Template.class.getName() %>"
					classPK="<%= template.getTemplateId() %>"
					formAction="<%= discussionURL %>"
					formName="discussionFm"
					ratingsEnabled="<%= enableTemplateCommentRatings %>"
					redirect="<%= currentURL %>"
					subject="<%= template.getTitle() %>"
					userId="<%= template.getUserId() %>"
				/>
			</liferay-ui:panel>
		</liferay-ui:panel-container>
	</div>
</c:if>