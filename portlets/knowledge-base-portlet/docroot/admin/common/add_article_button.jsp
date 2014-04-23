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
List<KBTemplate> kbTemplates = KBTemplateServiceUtil.getGroupKBTemplates(scopeGroupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, OrderByComparatorFactoryUtil.create("KBTemplate", "title", false));
%>

<liferay-portlet:renderURL var="addBasicKBArticleURL">
	<portlet:param name="mvcPath" value='<%= templatePath + "edit_article.jsp" %>' />
	<portlet:param name="redirect" value="<%= redirect %>" />
</liferay-portlet:renderURL>

<c:choose>
	<c:when test="<%= kbTemplates.isEmpty() %>">
		<aui:button href="<%= addBasicKBArticleURL %>" value="add-article" />
	</c:when>
	<c:otherwise>
		<liferay-ui:icon-menu direction="down" extended="<%= false %>" icon="<%= StringPool.BLANK %>" message="add-article" showWhenSingleIcon="<%= true %>" triggerCssClass="btn kb-add-article-button">
			<liferay-ui:icon
				message="basic-article"
				url="<%= addBasicKBArticleURL %>"
			/>

			<%
			for (KBTemplate kbTemplate : kbTemplates) {
			%>

				<liferay-portlet:renderURL var="addKBArticleURL">
					<portlet:param name="mvcPath" value='<%= templatePath + "edit_article.jsp" %>' />
					<portlet:param name="redirect" value="<%= redirect %>" />
					<portlet:param name="kbTemplateId" value="<%= String.valueOf(kbTemplate.getKbTemplateId()) %>" />
				</liferay-portlet:renderURL>

				<liferay-ui:icon
					message="<%= HtmlUtil.escape(kbTemplate.getTitle()) %>"
					url="<%= addKBArticleURL %>"
				/>

			<%
			}
			%>

		</liferay-ui:icon-menu>
	</c:otherwise>
</c:choose>