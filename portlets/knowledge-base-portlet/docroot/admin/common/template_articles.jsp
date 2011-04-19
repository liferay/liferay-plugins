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
KBTemplate kbTemplate = (KBTemplate)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_TEMPLATE);

long kbTemplateId = BeanParamUtil.getLong(kbTemplate, request, "kbTemplateId");
String title = BeanParamUtil.getString(kbTemplate, request, "title");

String orderByCol = ParamUtil.getString(request, "orderByCol", "modified-date");
String orderByType = ParamUtil.getString(request, "orderByType", "desc");
%>

<liferay-ui:header
	title='<%= LanguageUtil.format(pageContext, "articles-with-template-x", title, false) %>'
/>

<liferay-portlet:actionURL name="updateKBArticlesKBTemplates" var="updateKBArticlesKBTemplatesURL">
	<portlet:param name="jspPage" value='<%= jspPath + "template_articles.jsp" %>' />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="kbTemplateId" value="<%= String.valueOf(kbTemplateId) %>" />
</liferay-portlet:actionURL>

<aui:form action="<%= updateKBArticlesKBTemplatesURL %>" method="post" name="fm">
	<liferay-portlet:renderURLParams varImpl="searchURL" />
	<aui:input name="kbArticleIds" type="hidden" />
	<aui:input name="kbTemplateId" type="hidden" value="<%= kbTemplateId %>" />

	<aui:fieldset>
		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="jspPage" value='<%= jspPath + "template_articles.jsp" %>' />
			<portlet:param name="kbTemplateId" value="<%= String.valueOf(kbTemplateId) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container
			emptyResultsMessage='<%= LanguageUtil.format(pageContext, "there-are-no-articles-with-template-x", title, false) %>'
			rowChecker="<%= (KBTemplatePermission.contains(permissionChecker, kbTemplate, ActionKeys.DELETE) || KBTemplatePermission.contains(permissionChecker, kbTemplate, ActionKeys.UPDATE)) ? new RowChecker(renderResponse) : null %>"
			orderByCol="<%= orderByCol %>"
			orderByComparator="<%= KnowledgeBaseUtil.getKBArticleOrderByComparator(orderByCol, orderByType) %>"
			orderByType="<%= orderByType %>"
		>
			<liferay-ui:search-container-results
				results="<%= KBArticleLocalServiceUtil.getKBTemplateKBArticles(kbTemplateId, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
				total="<%= KBArticleLocalServiceUtil.getKBTemplateKBArticlesCount(kbTemplateId) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.knowledgebase.model.KBArticle"
				keyProperty="kbArticleId"
				modelVar="kbArticle"
			>
				<liferay-portlet:renderURL var="rowURL">
					<portlet:param name="jspPage" value='<%= jspPath + "print_article.jsp" %>' />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
					<portlet:param name="status" value="<%= String.valueOf(kbArticle.getStatus()) %>" />
				</liferay-portlet:renderURL>

				<%
				rowURL = "javascript:var printKBArticleWindow = window.open('" + rowURL + "', 'printKBArticle', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); printKBArticleWindow.focus();";
				%>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					orderable="<%= true %>"
					property="title"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="author"
					orderable="<%= true %>"
					orderableProperty="user-name"
					property="userName"
				/>

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="create-date"
					orderable="<%= true %>"
					value='<%= dateFormatDate.format(kbArticle.getCreateDate()) + "<br />" + dateFormatTime.format(kbArticle.getCreateDate()) %>'
				/>

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="modified-date"
					orderable="<%= true %>"
					value='<%= dateFormatDate.format(kbArticle.getModifiedDate()) + "<br />" + dateFormatTime.format(kbArticle.getModifiedDate()) %>'
				/>

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					orderable="<%= true %>"
					property="version"
				/>

				<liferay-ui:search-container-column-text
					align="right"
				>

					<%
					String taglibURL = "javascript:" + renderResponse.getNamespace() + "updateKBArticleKBTemplate('" + kbArticle.getKbArticleId() + "');";
					%>

					<liferay-ui:icon
						image="unlink"
						label="<%= true %>"
						message="remove-template"
						url="<%= taglibURL %>"
					/>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<c:if test="<%= (total > 0) && (KBTemplatePermission.contains(permissionChecker, kbTemplate, ActionKeys.DELETE) || KBTemplatePermission.contains(permissionChecker, kbTemplate, ActionKeys.UPDATE)) %>">
				<aui:button-row>
					<aui:button onClick='<%= renderResponse.getNamespace() + "updateKBArticlesKBTemplates();" %>' value="remove-template" />
				</aui:button-row>
			</c:if>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>

<aui:script>
	function <portlet:namespace />updateKBArticleKBTemplate(kbArticleId) {
		document.<portlet:namespace />fm.<portlet:namespace />kbArticleIds.value = kbArticleId;
		submitForm(document.<portlet:namespace />fm);
	}

	Liferay.provide(
		window,
		'<portlet:namespace />updateKBArticlesKBTemplates',
		function() {
			document.<portlet:namespace />fm.<portlet:namespace />kbArticleIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
			submitForm(document.<portlet:namespace />fm);
		},
		['liferay-util-list-fields']
	);
</aui:script>