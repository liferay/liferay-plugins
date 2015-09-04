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
KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

int status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);

int sourceVersion = ParamUtil.getInteger(request, "sourceVersion", kbArticle.getVersion() - 1);
int targetVersion = ParamUtil.getInteger(request, "targetVersion", kbArticle.getVersion());

String orderByCol = ParamUtil.getString(request, "orderByCol", "version");
String orderByType = ParamUtil.getString(request, "orderByType", "desc");

KBArticleURLHelper kbArticleURLHelper = new KBArticleURLHelper(renderRequest, renderResponse, templatePath);
%>

<liferay-portlet:renderURL varImpl="compareVersionsURL">
	<portlet:param name="mvcPath" value='<%= templatePath + "history.jsp" %>' />
	<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
	<portlet:param name="status" value="<%= String.valueOf(status) %>" />
</liferay-portlet:renderURL>

<aui:form action="<%= compareVersionsURL %>" method="get" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "compare();" %>'>
	<liferay-portlet:renderURLParams varImpl="compareVersionsURL" />
	<aui:input name="sourceVersion" type="hidden" value="<%= sourceVersion %>" />
	<aui:input name="targetVersion" type="hidden" value="<%= targetVersion %>" />

	<aui:fieldset>

		<%
		RowChecker rowChecker = new RowChecker(renderResponse);

		rowChecker.setAllRowIds(null);

		int selStatus = KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.UPDATE) ? WorkflowConstants.STATUS_ANY : status;
		%>

		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="mvcPath" value='<%= templatePath + "history.jsp" %>' />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
			<portlet:param name="status" value="<%= String.valueOf(status) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container
			emptyResultsMessage="no-articles-were-found"
			iteratorURL="<%= iteratorURL %>"
			orderByCol="<%= orderByCol %>"
			orderByComparator="<%= KnowledgeBaseUtil.getKBArticleOrderByComparator(orderByCol, orderByType) %>"
			orderByType="<%= orderByType %>"
			rowChecker="<%= rowChecker %>"
			total="<%= KBArticleServiceUtil.getKBArticleVersionsCount(scopeGroupId, kbArticle.getResourcePrimKey(), selStatus) %>"
		>
			<liferay-ui:search-container-results
				results="<%= KBArticleServiceUtil.getKBArticleVersions(scopeGroupId, kbArticle.getResourcePrimKey(), selStatus, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.knowledgebase.model.KBArticle"
				escapedModel="<%= true %>"
				keyProperty="version"
				modelVar="curKBArticle"
			>
				<liferay-portlet:renderURL var="rowURL">
					<portlet:param name="mvcPath" value='<%= templatePath + "history.jsp" %>' />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(curKBArticle.getResourcePrimKey()) %>" />
					<portlet:param name="status" value="<%= String.valueOf(status) %>" />
					<portlet:param name="sourceVersion" value="<%= String.valueOf(curKBArticle.getVersion()) %>" />
					<portlet:param name="targetVersion" value="<%= String.valueOf(curKBArticle.getVersion()) %>" />
				</liferay-portlet:renderURL>

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="version"
					orderable="<%= true %>"
				>
					<%= curKBArticle.getVersion() %>

					<c:choose>
						<c:when test="<%= (curKBArticle.getVersion() == sourceVersion) && (curKBArticle.getVersion() == targetVersion) %>">
							(<liferay-ui:message key="selected" />)
						</c:when>
						<c:when test="<%= curKBArticle.getVersion() == sourceVersion %>">
							(<liferay-ui:message key="source" />)
						</c:when>
						<c:when test="<%= curKBArticle.getVersion() == targetVersion %>">
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

				<liferay-ui:search-container-column-date
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="date"
					orderable="<%= true %>"
					orderableProperty="modified-date"
					value="<%= curKBArticle.getModifiedDate() %>"
				/>

				<c:if test="<%= (status == WorkflowConstants.STATUS_ANY) || KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.UPDATE) %>">
					<liferay-ui:search-container-column-text
						cssClass="kb-column-no-wrap"
						href="<%= rowURL %>"
						name="status"
						orderable="<%= true %>"
						value='<%= curKBArticle.getStatus() + " (" + LanguageUtil.get(request, WorkflowConstants.getStatusLabel(curKBArticle.getStatus())) + ")" %>'
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

				<c:if test="<%= KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.UPDATE) %>">
					<liferay-ui:search-container-column-text
						align="right"
					>
						<liferay-portlet:actionURL name="updateKBArticle" varImpl="revertURL">
							<portlet:param name="mvcPath" value='<%= templatePath + "history.jsp" %>' />
							<portlet:param name="redirect" value="<%= redirect %>" />
							<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
							<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.REVERT %>" />
							<portlet:param name="version" value="<%= String.valueOf(curKBArticle.getVersion()) %>" />
							<portlet:param name="workflowAction" value="<%= String.valueOf(WorkflowConstants.ACTION_PUBLISH) %>" />
						</liferay-portlet:actionURL>

						<%
						revertURL.setParameter("section", AdminUtil.unescapeSections(curKBArticle.getSections()));
						%>

						<liferay-ui:icon
							iconCssClass="icon-undo"
							label="<%= true %>"
							message="revert"
							url="<%= revertURL.toString() %>"
						/>
					</liferay-ui:search-container-column-text>
				</c:if>
			</liferay-ui:search-container-row>

			<div class="float-container kb-entity-header">
				<div class="kb-title">
					<liferay-ui:diff-html diffHtmlResults='<%= AdminUtil.getKBArticleDiff(kbArticle.getResourcePrimKey(), sourceVersion, targetVersion, "title") %>' />
				</div>

				<div class="kb-tools">

					<%
					PortletURL viewKBArticleURL = kbArticleURLHelper.createViewURL(kbArticle);
					%>

					<liferay-ui:icon
						iconCssClass="icon-file-alt"
						label="<%= true %>"
						message="latest-version"
						method="get"
						url="<%= viewKBArticleURL.toString() %>"
					/>
				</div>
			</div>

			<div class="kb-entity-body">
				<liferay-ui:diff-html diffHtmlResults='<%= AdminUtil.getKBArticleDiff(kbArticle.getResourcePrimKey(), sourceVersion, targetVersion, "content") %>' />
			</div>

			<aui:button-row cssClass="kb-bulk-action-button-holder">
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

			if (rowIdsSize === 1) {
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
						item.attr('checked', false);
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

				rowsChecked.item(index).attr('checked', false);
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