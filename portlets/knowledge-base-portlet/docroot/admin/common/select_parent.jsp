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
int status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);

long kbFolderClassNameId = PortalUtil.getClassNameId(KBFolderConstants.getClassName());

long resourceClassNameId = ParamUtil.getLong(request, "resourceClassNameId");
long resourcePrimKey = ParamUtil.getLong(request, "resourcePrimKey");
long parentResourceClassNameId = ParamUtil.getLong(request, "parentResourceClassNameId", kbFolderClassNameId);
long parentResourcePrimKey = ParamUtil.getLong(request, "parentResourcePrimKey", KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);
long oldParentResourceClassNameId = ParamUtil.getLong(request, "oldParentResourceClassNameId");
long oldParentResourcePrimKey = ParamUtil.getLong(request, "oldParentResourcePrimKey");

String orderByCol = ParamUtil.getString(request, "orderByCol", "priority");
String orderByType = ParamUtil.getString(request, "orderByType", "desc");
%>

<liferay-ui:header
	title="parent-article"
/>

<aui:form method="post" name="fm">
	<aui:fieldset>
		<c:if test="<%= oldParentResourcePrimKey != KBFolderConstants.DEFAULT_PARENT_FOLDER_ID %>">
			<aui:button-row cssClass="input-append">
				<c:choose>
					<c:when test="<%= oldParentResourceClassNameId == kbFolderClassNameId %>">

						<%
						KBFolder oldParentKBFolder = KBFolderServiceUtil.getKBFolder(oldParentResourcePrimKey);
						%>

						<liferay-ui:input-resource url="<%= oldParentKBFolder.getName() %>" />
					</c:when>
					<c:otherwise>
						<liferay-ui:input-resource url='<%= BeanPropertiesUtil.getString(KBArticleServiceUtil.getLatestKBArticle(oldParentResourcePrimKey, status), "title") %>' />
					</c:otherwise>
				</c:choose>

				<%
				String taglibOnClick = "opener." + renderResponse.getNamespace() + "selectKBObject('(" + LanguageUtil.get(locale, "none") + ")', '1.0', '" + KBFolderConstants.DEFAULT_PARENT_FOLDER_ID + "', '" + kbFolderClassNameId + "'); window.close();";
				%>

				<aui:button onClick="<%= taglibOnClick %>" value="remove" />
			</aui:button-row>

			<div class="separator"><!-- --></div>
		</c:if>

		<div class="kb-select-article-breadcrumbs">
			<liferay-portlet:renderURL var="breadcrumbURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcPath" value='<%= templatePath + "select_parent.jsp" %>' />
				<portlet:param name="resourceClassNameId" value="<%= String.valueOf(resourceClassNameId) %>" />
				<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
				<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(kbFolderClassNameId) %>" />
				<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) %>" />
				<portlet:param name="oldParentResourceClassNameId" value="<%= String.valueOf(oldParentResourceClassNameId) %>" />
				<portlet:param name="oldParentResourcePrimKey" value="<%= String.valueOf(oldParentResourcePrimKey) %>" />
				<portlet:param name="status" value="<%= String.valueOf(status) %>" />
			</liferay-portlet:renderURL>

			<aui:a href="<%= breadcrumbURL %>"><liferay-ui:message key="home" /></aui:a> &raquo;

			<c:if test="<%= parentResourcePrimKey != KBFolderConstants.DEFAULT_PARENT_FOLDER_ID %>">

				<%
				List<Tuple> tuples = new ArrayList<Tuple>();

				long selParentResourcePrimKey = parentResourcePrimKey;
				long selParentResourceClassNameId = parentResourceClassNameId;

				while (selParentResourcePrimKey != KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
					if (selParentResourceClassNameId == kbFolderClassNameId) {
						KBFolder selKBFolder = KBFolderServiceUtil.getKBFolder(selParentResourcePrimKey);

						tuples.add(new Tuple(selKBFolder.getKbFolderId(), StringUtil.shorten(selKBFolder.getName(), 30)));

						selParentResourcePrimKey = selKBFolder.getParentKBFolderId();
						selParentResourceClassNameId = selKBFolder.getClassNameId();
					}
					else {
						KBArticle selKBArticle = KBArticleServiceUtil.getLatestKBArticle(selParentResourcePrimKey, status);

						tuples.add(new Tuple(selKBArticle.getResourcePrimKey(), StringUtil.shorten(selKBArticle.getTitle(), 30)));

						selParentResourcePrimKey = selKBArticle.getParentResourcePrimKey();
						selParentResourceClassNameId = selKBArticle.getParentResourceClassNameId();
					}
				}

				for (Tuple tuple: tuples) {
				%>

					<aui:a href='<%= HttpUtil.setParameter(breadcrumbURL, "parentResourcePrimKey", (Long)tuple.getObject(0)) %>'><%= tuple.getObject(1) %></aui:a> &raquo;

				<%
				}
				%>

			</c:if>
		</div>

		<liferay-portlet:renderURL varImpl="iteratorURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value='<%= templatePath + "select_parent.jsp" %>' />
			<portlet:param name="resourceClassNameId" value="<%= String.valueOf(resourceClassNameId) %>" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
			<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(parentResourceClassNameId) %>" />
			<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(parentResourcePrimKey) %>" />
			<portlet:param name="oldParentResourceClassNameId" value="<%= String.valueOf(oldParentResourceClassNameId) %>" />
			<portlet:param name="oldParentResourcePrimKey" value="<%= String.valueOf(oldParentResourcePrimKey) %>" />
			<portlet:param name="status" value="<%= String.valueOf(status) %>" />
		</liferay-portlet:renderURL>

		<c:if test="<%= parentResourceClassNameId == kbFolderClassNameId %>">
			<liferay-ui:search-container
				curParam="cur1"
				id="kbFoldersAdminSearchContainer"
				iteratorURL="<%= iteratorURL %>"
				total="<%= KBFolderServiceUtil.getKBFoldersCount(scopeGroupId, parentResourcePrimKey) %>"
			>
				<liferay-ui:search-container-results
					results="<%= KBFolderServiceUtil.getKBFolders(scopeGroupId, parentResourcePrimKey, searchContainer.getStart(), searchContainer.getEnd()) %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.knowledgebase.model.KBFolder"
					escapedModel="<%= true %>"
					keyProperty="kbFolderId"
					modelVar="kbFolder"
				>

					<liferay-portlet:renderURL var="rowURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
						<portlet:param name="mvcPath" value='<%= templatePath + "select_parent.jsp" %>' />
						<portlet:param name="resourceClassNameId" value="<%= String.valueOf(resourceClassNameId) %>" />
						<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
						<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(kbFolder.getClassNameId()) %>" />
						<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(kbFolder.getKbFolderId()) %>" />
						<portlet:param name="oldParentResourceClassNameId" value="<%= String.valueOf(oldParentResourceClassNameId) %>" />
						<portlet:param name="oldParentResourcePrimKey" value="<%= String.valueOf(oldParentResourcePrimKey) %>" />
						<portlet:param name="status" value="<%= String.valueOf(status) %>" />
					</liferay-portlet:renderURL>

					<liferay-ui:search-container-column-text
						name="folder"
					>
						<a class="icon-folder-open" href="<%= rowURL %>">
							<%= kbFolder.getName() %>
						</a>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						href="<%= rowURL %>"
						name="author"
						property="userName"
					/>

					<liferay-ui:search-container-column-date
						href="<%= rowURL %>"
						name="create-date"
						property="createDate"
					/>

					<liferay-ui:search-container-column-date
						href="<%= rowURL %>"
						name="modified-date"
						property="modifiedDate"
					/>

					<liferay-ui:search-container-column-text
						align="right"
					>

						<%
						String taglibOnClick = "opener." + renderResponse.getNamespace() + "selectKBObject('" + kbFolder.getName() + "', '1.0', '" + kbFolder.getKbFolderId() + "', '" + kbFolder.getClassNameId() + "'); window.close();";
						%>

						<aui:button disabled="<%= (kbFolder.getKbFolderId() == resourcePrimKey) || (kbFolder.getKbFolderId() == oldParentResourcePrimKey) %>" onClick="<%= taglibOnClick %>" value="choose" />
					</liferay-ui:search-container-column-text>

				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator />
			</liferay-ui:search-container>
		</c:if>

		<liferay-ui:search-container
			curParam="cur2"
			emptyResultsMessage="there-are-no-articles"
			iteratorURL="<%= iteratorURL %>"
			orderByCol="<%= orderByCol %>"
			orderByComparator="<%= KnowledgeBaseUtil.getKBArticleOrderByComparator(orderByCol, orderByType) %>"
			orderByType="<%= orderByType %>"
			total="<%= KBArticleServiceUtil.getKBArticlesCount(scopeGroupId, parentResourcePrimKey, status) %>"
		>
			<liferay-ui:search-container-results
				results="<%= KBArticleServiceUtil.getKBArticles(scopeGroupId, parentResourcePrimKey, status, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.knowledgebase.model.KBArticle"
				escapedModel="<%= true %>"
				keyProperty="resourcePrimKey"
				modelVar="curKBArticle"
			>
				<liferay-portlet:renderURL var="rowURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="mvcPath" value='<%= templatePath + "select_parent.jsp" %>' />
					<portlet:param name="resourceClassNameId" value="<%= String.valueOf(resourceClassNameId) %>" />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
					<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(curKBArticle.getClassNameId()) %>" />
					<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(curKBArticle.getResourcePrimKey()) %>" />
					<portlet:param name="oldParentResourceClassNameId" value="<%= String.valueOf(oldParentResourceClassNameId) %>" />
					<portlet:param name="oldParentResourcePrimKey" value="<%= String.valueOf(oldParentResourcePrimKey) %>" />
					<portlet:param name="status" value="<%= String.valueOf(status) %>" />
				</liferay-portlet:renderURL>

				<%
				if ((curKBArticle.getResourcePrimKey() == resourcePrimKey) || (KBArticleServiceUtil.getKBArticlesCount(scopeGroupId, curKBArticle.getResourcePrimKey(), status) == 0)) {
					rowURL = null;
				}
				%>

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="priority"
					orderable="<%= true %>"
					value="<%= BigDecimal.valueOf(curKBArticle.getPriority()).toPlainString() %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					orderable="<%= true %>"
					property="title"
				/>

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="status"
					orderable="<%= true %>"
					value='<%= curKBArticle.getStatus() + " (" + LanguageUtil.get(request, WorkflowConstants.getStatusLabel(curKBArticle.getStatus())) + ")" %>'
				/>

				<liferay-ui:search-container-column-text
					align="right"
				>

					<%
					String taglibOnClick = "opener." + renderResponse.getNamespace() + "selectKBObject('" + curKBArticle.getTitle() + "', '" + curKBArticle.getPriority() + "', '" + curKBArticle.getResourcePrimKey() + "', '" + curKBArticle.getClassNameId() + "'); window.close();";
					%>

					<aui:button disabled="<%= (resourceClassNameId == kbFolderClassNameId) || (curKBArticle.getResourcePrimKey() == resourcePrimKey) || (curKBArticle.getResourcePrimKey() == oldParentResourcePrimKey) %>" onClick="<%= taglibOnClick %>" value="choose" />
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<c:if test="<%= oldParentResourcePrimKey != KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY %>">
				<aui:button-row cssClass="input-append">
					<liferay-ui:input-resource url='<%= BeanPropertiesUtil.getString(KBArticleServiceUtil.getLatestKBArticle(oldParentResourcePrimKey, status), "title") %>' />

					<liferay-util:buffer var="html">
						<liferay-util:include page="/admin/new_parent.jsp" servletContext="<%= application %>">
							<liferay-util:param name="parentResourceClassNameId" value="<%= String.valueOf(defaultClassNameId) %>" />
							<liferay-util:param name="parentResourcePrimKey" value="<%= String.valueOf(KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) %>" />
						</liferay-util:include>
					</liferay-util:buffer>

					<%
					String taglibOnClick = "opener." + renderResponse.getNamespace() + "selectKBArticle('(" + LanguageUtil.get(locale, "none") + ")', '1.0', '" + KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY + "', '" + defaultClassNameId + "', '" + UnicodeFormatter.toString(html) + "'); window.close();";
					%>

					<aui:button onClick="<%= taglibOnClick %>" value="remove" />
				</aui:button-row>

				<div class="separator"><!-- --></div>
			</c:if>

			<div class="kb-select-article-breadcrumbs">
				<liferay-portlet:renderURL var="breadcrumbURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="mvcPath" value='<%= templatePath + "select_article.jsp" %>' />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
					<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(defaultClassNameId) %>" />
					<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) %>" />
					<portlet:param name="oldParentResourcePrimKey" value="<%= String.valueOf(oldParentResourcePrimKey) %>" />
					<portlet:param name="status" value="<%= String.valueOf(status) %>" />
				</liferay-portlet:renderURL>

				<aui:a href="<%= breadcrumbURL %>"><liferay-ui:message key="home" /></aui:a> &raquo;

				<c:if test="<%= parentResourcePrimKey != KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY %>">

					<%
					List<KBArticle> selKBArticles = new ArrayList<KBArticle>();

					long selParentResourcePrimKey = parentResourcePrimKey;

					while (selParentResourcePrimKey != KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) {
						KBArticle selKBArticle = KBArticleServiceUtil.getLatestKBArticle(selParentResourcePrimKey, status);

						selKBArticles.add(selKBArticle);

						selParentResourcePrimKey = selKBArticle.getParentResourcePrimKey();
					}

					for (int i = selKBArticles.size(); i > 0; i--) {
						KBArticle selKBArticle = selKBArticles.get(i - 1);
					%>

						<aui:a href='<%= HttpUtil.setParameter(breadcrumbURL, "parentResourcePrimKey", selKBArticle.getResourcePrimKey()) %>'><%= (i == 1) ? selKBArticle.getTitle() : StringUtil.shorten(selKBArticle.getTitle(), 30) %></aui:a> &raquo;

					<%
					}
					%>

				</c:if>
			</div>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>