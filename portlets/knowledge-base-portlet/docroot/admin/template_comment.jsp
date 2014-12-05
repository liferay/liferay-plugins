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

KBComment kbComment = (KBComment)request.getAttribute("template_comment.jsp-kb_comment");
%>

<div class="kb-template-comment">
	<table class="lfr-table" width="100%">
	<tr>
		<td align="center" valign="top">
			<liferay-ui:user-display
				displayStyle="<%= 2 %>"
				userId="<%= kbComment.getUserId() %>"
				userName="<%= kbComment.getUserName() %>"
			/>
		</td>
		<td valign="top" width="90%">
			<div>
				<strong class="kb-question"><liferay-ui:message key="was-this-information-helpful" /></strong>

				<c:choose>
					<c:when test="<%= kbComment.getUserRating() == KBCommentConstants.USER_RATING_LIKE %>">
						<strong class="kb-yes"><liferay-ui:message key="yes" /></strong>
					</c:when>
					<c:when test="<%= kbComment.getUserRating() == KBCommentConstants.USER_RATING_DISLIKE %>">
						<strong class="kb-no"><liferay-ui:message key="no" /></strong>
					</c:when>
				</c:choose>
			</div>

			<br />

			<div>
				<%= kbComment.getContent() %>
			</div>

			<br />

			<div>
				<%= LanguageUtil.format(pageContext, "posted-on-x", dateFormatDateTime.format(kbComment.getModifiedDate()), false) %>
			</div>

			<c:if test="<%= KBCommentPermission.contains(permissionChecker, kbComment, ActionKeys.DELETE) %>">
				<br />

				<%
				String deleteURL = "javascript:" + renderResponse.getNamespace() + "deleteKBComment(" + kbComment.getKbCommentId() + ");";
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