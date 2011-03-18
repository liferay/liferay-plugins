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

Article article = (Article)request.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);

int sourceVersion = ParamUtil.getInteger(request, "sourceVersion", article.getVersion() - 1);
int targetVersion = ParamUtil.getInteger(request, "targetVersion", article.getVersion());

String orderByCol = ParamUtil.getString(request, "orderByCol", "version");
String orderByType = ParamUtil.getString(request, "orderByType", "desc");
%>

<liferay-portlet:renderURL varImpl="compareVersionsURL">
	<portlet:param name="jspPage" value='<%= jspPath + "history.jsp" %>' />
	<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
	<portlet:param name="status" value="<%= String.valueOf(status) %>" />
</liferay-portlet:renderURL>

<aui:form action="<%= compareVersionsURL %>" method="get" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "compare();" %>'>
	<liferay-portlet:renderURLParams varImpl="compareVersionsURL" />
	<aui:input name="sourceVersion" type="hidden" value="<%= sourceVersion %>" />
	<aui:input name="targetVersion" type="hidden" value="<%= targetVersion %>" />

	<aui:fieldset>
		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="jspPage" value='<%= jspPath + "history.jsp" %>' />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
			<portlet:param name="status" value="<%= String.valueOf(status) %>" />
		</liferay-portlet:renderURL>

		<%
		RowChecker rowChecker = new RowChecker(renderResponse);

		rowChecker.setAllRowIds(null);
		%>

		<liferay-ui:search-container
			emptyResultsMessage="no-articles-were-found"
			iteratorURL="<%= iteratorURL %>"
			orderByCol="<%= orderByCol %>"
			orderByComparator="<%= KnowledgeBaseUtil.getArticleOrderByComparator(orderByCol, orderByType) %>"
			orderByType="<%= orderByType %>"
			rowChecker="<%= rowChecker %>"
		>
			<liferay-ui:search-container-results>

				<%
				int selStatus = ArticlePermission.contains(permissionChecker, article, ActionKeys.UPDATE) ? WorkflowConstants.STATUS_ANY : status;

				pageContext.setAttribute("results", ArticleServiceUtil.getArticles(scopeGroupId, article.getResourcePrimKey(), selStatus, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()));
				pageContext.setAttribute("total", ArticleServiceUtil.getArticlesCount(scopeGroupId, article.getResourcePrimKey(), selStatus));
				%>

			</liferay-ui:search-container-results>

			<%
			boolean update = ArticlePermission.contains(permissionChecker, article, ActionKeys.UPDATE);
			%>

			<liferay-ui:search-container-row
				className="com.liferay.knowledgebase.model.Article"
				keyProperty="version"
				modelVar="curArticle"
			>
				<liferay-portlet:renderURL var="rowURL">
					<portlet:param name="jspPage" value='<%= jspPath + "history.jsp" %>' />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(curArticle.getResourcePrimKey()) %>" />
					<portlet:param name="status" value="<%= String.valueOf(status) %>" />
					<portlet:param name="sourceVersion" value="<%= String.valueOf(curArticle.getVersion()) %>" />
					<portlet:param name="targetVersion" value="<%= String.valueOf(curArticle.getVersion()) %>" />
				</liferay-portlet:renderURL>

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="version"
					orderable="<%= true %>"
				>
					<%= curArticle.getVersion() %>

					<c:choose>
						<c:when test="<%= (curArticle.getVersion() == sourceVersion) && (curArticle.getVersion() == targetVersion) %>">
							(<liferay-ui:message key="selected" />)
						</c:when>
						<c:when test="<%= curArticle.getVersion() == sourceVersion %>">
							(<liferay-ui:message key="source" />)
						</c:when>
						<c:when test="<%= curArticle.getVersion() == targetVersion %>">
							(<liferay-ui:message key="target" />)
						</c:when>
					</c:choose>
				</liferay-ui:search-container-column-text>

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
					name="date"
					orderable="<%= true %>"
					orderableProperty="modified-date"
					value='<%= dateFormatDate.format(curArticle.getModifiedDate()) + "<br />" + dateFormatTime.format(curArticle.getModifiedDate()) %>'
				/>

				<c:if test="<%= (status == WorkflowConstants.STATUS_ANY) || update %>">
					<liferay-ui:search-container-column-text
						cssClass="kb-column-no-wrap"
						href="<%= rowURL %>"
						name="status"
						orderable="<%= true %>"
						value='<%= curArticle.getStatus() + " (" + LanguageUtil.get(pageContext, WorkflowConstants.toLabel(curArticle.getStatus())) + ")" %>'
					/>
				</c:if>

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="views"
					orderable="<%= true %>"
					orderableProperty="view-count"
					property="viewCount"
				/>

				<c:if test="<%= update %>">
					<liferay-ui:search-container-column-text
						align="right"
					>
						<liferay-portlet:actionURL name="updateArticle" var="revertURL">
							<portlet:param name="jspPage" value='<%= jspPath + "history.jsp" %>' />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
							<portlet:param name="status" value="<%= String.valueOf(status) %>" />
							<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UPDATE %>" />
							<portlet:param name="title" value="<%= curArticle.getTitle() %>" />
							<portlet:param name="content" value="<%= curArticle.getContent() %>" />
							<portlet:param name="description" value="<%= curArticle.getDescription() %>" />
						</liferay-portlet:actionURL>

						<liferay-ui:icon
							image="undo"
							label="<%= true %>"
							message="revert"
							url="<%= revertURL %>"
						/>
					</liferay-ui:search-container-column-text>
				</c:if>
			</liferay-ui:search-container-row>

			<div class="float-container kb-entity-header">
				<div class="kb-title">
					<%= AdminUtil.getArticleDiff(article.getResourcePrimKey(), sourceVersion, targetVersion, "title") %>
				</div>

				<div class="kb-tools">
					<liferay-portlet:renderURL var="viewArticleURL">
						<portlet:param name="jspPage" value='<%= jspPath + "view_article.jsp" %>' />
						<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
						<portlet:param name="status" value="<%= String.valueOf(status) %>" />
					</liferay-portlet:renderURL>

					<liferay-ui:icon
						image="../common/page"
						label="<%= true %>"
						message="latest-version"
						method="get"
						url="<%= viewArticleURL %>"
					/>
				</div>
			</div>

			<div class="kb-entity-body">
				<%= AdminUtil.getArticleDiff(article.getResourcePrimKey(), sourceVersion, targetVersion, "content") %>
			</div>

			<aui:button-row>
				<aui:button type="submit" value="compare-versions" />
			</aui:button-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />compare',
		function() {
			var A = AUI();

			var rowIds = A.all('input[name=<portlet:namespace />rowIds]:checked');
			var sourceVersion = A.one('input[name="<portlet:namespace />sourceVersion"]');
			var targetVersion = A.one('input[name="<portlet:namespace />targetVersion"]');

			var rowIdsSize = rowIds.size();

			if (rowIdsSize == 1) {
				if (sourceVersion) {
					sourceVersion.val(rowIds.item(0).val());
				}
			}
			else if (rowIdsSize == 2) {
				if (sourceVersion) {
					sourceVersion.val(rowIds.item(1).val());
				}

				if (targetVersion) {
					targetVersion.val(rowIds.item(0).val());
				}
			}

			submitForm(document.<portlet:namespace />fm);
		},
		['aui-base', 'selector-css3']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />initRowsChecked',
		function() {
			var A = AUI();

			var rowIds = A.all('input[name=<portlet:namespace />rowIds]');

			rowIds.each(
				function(item, index, collection) {
					if (index >= 2) {
						item.set('checked', false);
					}
				}
			);
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateRowsChecked',
		function(element) {
			var A = AUI();

			var rowsChecked = A.all('input[name=<portlet:namespace />rowIds]:checked');

			if (rowsChecked.size() > 2) {
				var index = 2;

				if (rowsChecked.item(2).compareTo(element)) {
					index = 1;
				}

				rowsChecked.item(index).set('checked', false);
			}
		},
		['aui-base', 'selector-css3']
	);
</aui:script>

<aui:script use="aui-base">
	<portlet:namespace />initRowsChecked();

	A.all('input[name=<portlet:namespace />rowIds]').on(
		'click',
		function(event) {
			<portlet:namespace />updateRowsChecked(event.currentTarget);
		}
	);
</aui:script>