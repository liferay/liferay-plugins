<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>
<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

KBArticle article = (KBArticle)row.getObject();

boolean updatePermission = false;
boolean managePermissions = false;
boolean deletePermission = false;

if (article.isTemplate()) {
	updatePermission = KBPermission.contains(permissionChecker, plid, KnowledgeBaseKeys.MANAGE_TEMPLATES);
	managePermissions = updatePermission;
	deletePermission = updatePermission;
}
else {
	updatePermission = KBArticlePermission.contains(permissionChecker, article, ActionKeys.UPDATE);
	managePermissions = KBArticlePermission.contains(permissionChecker, article, ActionKeys.PERMISSIONS);
	deletePermission = KBArticlePermission.contains(permissionChecker, article, ActionKeys.DELETE);
}
%>

<liferay-ui:icon-menu>
	<c:if test="<%= article.isTemplate() && KBPermission.contains(permissionChecker, plid, ActionKeys.ADD_ARTICLE) %>">
		<portlet:renderURL var="addTemplateBasedArticleURL">
			<portlet:param name="view" value="edit_article" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
			<portlet:param name="template" value="false" />
		</portlet:renderURL>

		<liferay-ui:icon image="add" message="add-article" url="<%= addTemplateBasedArticleURL.toString() %>" />
	</c:if>

	<c:if test="<%= updatePermission %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="view" value="edit_article" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon image="edit" url="<%= editURL.toString() %>" />
	</c:if>

	<c:if test="<%= managePermissions %>">
		<liferay-security:permissionsURL
			modelResource="<%= KBArticle.class.getName() %>"
			modelResourceDescription="<%= article.getTitle() %>"
			resourcePrimKey="<%= String.valueOf(article.getResourcePrimKey()) %>"
			var="permissionsURL"
			/>

		<liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />
	</c:if>

	<c:if test="<%= KBArticlePermission.contains(permissionChecker, article, ActionKeys.SUBSCRIBE) %>">
		<c:choose>
			<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), KBArticle.class.getName(), article.getResourcePrimKey()) %>">
				<portlet:actionURL var="unsubscribeURL">
					<portlet:param name="actionName" value="unsubscribeArticle" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon image="unsubscribe" url="<%= unsubscribeURL %>" label="<%= true %>" />
			</c:when>
			<c:otherwise>
				<portlet:actionURL var="subscribeURL">
					<portlet:param name="actionName" value="subscribeArticle" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon image="subscribe" url="<%= subscribeURL %>" label="<%= true %>" />
			</c:otherwise>
		</c:choose>
	</c:if>

	<c:if test="<%= deletePermission %>">
		<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="deleteURL">
			<portlet:param name="actionName" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%= deleteURL.toString() %>" />
	</c:if>
</liferay-ui:icon-menu>