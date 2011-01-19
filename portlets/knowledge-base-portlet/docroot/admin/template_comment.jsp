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
Template template = (Template)request.getAttribute(WebKeys.KNOWLEDGE_BASE_TEMPLATE);

Comment comment = (Comment)request.getAttribute("template_comments.jsp-comment");
%>

<div class="kb-template-comment">
	<table class="lfr-table" width="100%">
	<tr>
		<td align="center" valign="top">
			<liferay-ui:user-display
				displayStyle="<%= 2 %>"
				userId="<%= comment.getUserId() %>"
				userName="<%= comment.getUserName() %>"
			/>
		</td>
		<td valign="top" width="99%">
			<div>
				<strong class="kb-question"><liferay-ui:message key="was-this-information-helpful" /></strong>

				<c:choose>
					<c:when test="<%= comment.getHelpful() %>">
						<strong class="kb-yes"><liferay-ui:message key="yes" /></strong>
					</c:when>
					<c:otherwise>
						<strong class="kb-no"><liferay-ui:message key="no" /></strong>
					</c:otherwise>
				</c:choose>
			</div>

			<br />

			<div>
				<%= comment.getContent() %>
			</div>

			<br />

			<div>
				<%= LanguageUtil.format(pageContext, "posted-on-x", dateFormatDateTime.format(comment.getModifiedDate())) %>
			</div>

			<c:if test="<%= TemplatePermission.contains(permissionChecker, template, ActionKeys.UPDATE) || (user.getUserId() == comment.getUserId()) %>">
				<br />

				<%
				String deleteURL = "javascript:" + renderResponse.getNamespace() + "deleteComment(" + comment.getCommentId() + ");";
				%>

				<liferay-ui:icon-delete
					label="<%= true %>"
					url="<%= deleteURL %>"
				/>
			</c:if>
		</td>
	</tr>
	</table>

	<div class="separator"><!-- --></div>
</div>