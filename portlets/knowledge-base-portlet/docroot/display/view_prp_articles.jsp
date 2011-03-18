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

<%@ include file="/display/init.jsp" %>

<%
long categoryId = ParamUtil.getLong(request, "categoryId");
String tag = ParamUtil.getString(request, "tag");
%>

<liferay-util:include page="/display/top_links.jsp" servletContext="<%= application %>" />

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="jspPage" value="/display/view.jsp" />
	<portlet:param name="categoryId" value="<%= String.valueOf(categoryId) %>" />
	<portlet:param name="tag" value="<%= tag %>" />
</liferay-portlet:renderURL>

<liferay-ui:search-container
	iteratorURL="<%= iteratorURL %>"
>
	<liferay-ui:search-container-results>

		<%
		AssetEntryQuery assetEntryQuery = new AssetEntryQuery(Article.class.getName(), searchContainer);

		List<AssetEntry> assetEntries = AssetEntryServiceUtil.getEntries(assetEntryQuery);

		pageContext.setAttribute("results", ArticleLocalServiceUtil.getArticles(StringUtil.split(ListUtil.toString(assetEntries, "classPK"), 0L), WorkflowConstants.STATUS_APPROVED, null));
		pageContext.setAttribute("total", AssetEntryServiceUtil.getEntriesCount(assetEntryQuery));
		%>

	</liferay-ui:search-container-results>

	<%
	boolean administrator = DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR);
	%>

	<liferay-ui:search-container-row
		className="com.liferay.knowledgebase.model.Article"
		keyProperty="resourcePrimKey"
		modelVar="article"
	>
		<liferay-portlet:renderURL varImpl="rowURL">
			<portlet:param name="jspPage" value="/display/view_article.jsp" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
		</liferay-portlet:renderURL>

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
			value='<%= dateFormatDate.format(article.getCreateDate()) + "<br />" + dateFormatTime.format(article.getCreateDate()) %>'
		/>

		<liferay-ui:search-container-column-text
			cssClass="kb-column-no-wrap"
			href="<%= rowURL %>"
			name="modified-date"
			orderable="<%= true %>"
			value='<%= dateFormatDate.format(article.getModifiedDate()) + "<br />" + dateFormatTime.format(article.getModifiedDate()) %>'
		/>

		<c:if test="<%= administrator %>">
			<liferay-ui:search-container-column-text
				cssClass="kb-column-no-wrap"
				href="<%= rowURL %>"
				name="status"
				orderable="<%= true %>"
				value='<%= article.getStatus() + " (" + LanguageUtil.get(pageContext, WorkflowConstants.toLabel(article.getStatus())) + ")" %>'
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

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/display/article_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<c:if test="<%= (categoryId > 0) || Validator.isNotNull(tag) %>">
		<div class="portlet-msg-info">
			<c:choose>
				<c:when test="<%= categoryId > 0 %>">

					<%
					AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(categoryId);

					AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.getAssetVocabulary(assetCategory.getVocabularyId());
					%>

					<c:choose>
						<c:when test="<%= Validator.isNotNull(tag) %>">
							<c:choose>
								<c:when test="<%= total > 0 %>">
									<%= LanguageUtil.format(pageContext, "articles-with-x-x-and-tag-x", new String[] {assetVocabulary.getName(), assetCategory.getName(), tag}, false) %>
								</c:when>
								<c:otherwise>
									<%= LanguageUtil.format(pageContext, "there-are-no-articles-with-x-x-and-tag-x", new String[] {assetVocabulary.getName(), assetCategory.getName(), tag}, false) %>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="<%= total > 0 %>">
									<%= LanguageUtil.format(pageContext, "articles-with-x-x", new String[] {assetVocabulary.getName(), assetCategory.getName()}, false) %>
								</c:when>
								<c:otherwise>
									<%= LanguageUtil.format(pageContext, "there-are-no-articles-with-x-x", new String[] {assetVocabulary.getName(), assetCategory.getName()}, false) %>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="<%= total > 0 %>">
							<%= LanguageUtil.format(pageContext, "articles-with-tag-x", tag, false) %>
						</c:when>
						<c:otherwise>
							<%= LanguageUtil.format(pageContext, "there-are-no-articles-with-tag-x", tag, false) %>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>