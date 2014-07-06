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
OrderByComparator<KBTemplate> obc = OrderByComparatorFactoryUtil.create("KBTemplate", "title", false);

List<KBTemplate> kbTemplates = KBTemplateServiceUtil.getGroupKBTemplates(scopeGroupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

long parentResourcePrimKey = ParamUtil.getLong(request, "parentResourcePrimKey", KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY);
%>

<liferay-portlet:renderURL var="addBasicKBArticleURL">
	<portlet:param name="mvcPath" value='<%= templatePath + "edit_article.jsp" %>' />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(parentResourcePrimKey) %>" />
</liferay-portlet:renderURL>

<c:choose>
	<c:when test="<%= kbTemplates.isEmpty() %>">
		<aui:nav-item href="<%= addBasicKBArticleURL %>" label="add-article" />
	</c:when>
	<c:otherwise>
		<aui:nav-item dropdown="<%= true %>" label="add">
			<aui:nav-item
				href="<%= addBasicKBArticleURL %>"
				label="basic-article"
			/>

			<%
			for (KBTemplate kbTemplate : kbTemplates) {
			%>

				<liferay-portlet:renderURL var="addKBArticleURL">
					<portlet:param name="mvcPath" value='<%= templatePath + "edit_article.jsp" %>' />
					<portlet:param name="redirect" value="<%= redirect %>" />
					<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(parentResourcePrimKey) %>" />
					<portlet:param name="kbTemplateId" value="<%= String.valueOf(kbTemplate.getKbTemplateId()) %>" />
				</liferay-portlet:renderURL>

				<aui:nav-item
					href="<%= addKBArticleURL %>"
					label="<%= HtmlUtil.escape(kbTemplate.getTitle()) %>"
				/>

			<%
			}
			%>

		</aui:nav-item>
	</c:otherwise>
</c:choose>