<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
long assetCategoryId = ParamUtil.getLong(request, "categoryId");
String assetTagName = ParamUtil.getString(request, "tag");

String orderByCol = ParamUtil.getString(request, "orderByCol", "modifiedDate");
String orderByType = ParamUtil.getString(request, "orderByType", "desc");
%>

<liferay-util:include page="/display/top_links.jsp" servletContext="<%= application %>" />

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="jspPage" value="/display/view.jsp" />
	<portlet:param name="categoryId" value="<%= String.valueOf(assetCategoryId) %>" />
	<portlet:param name="tag" value="<%= assetTagName %>" />
</liferay-portlet:renderURL>

<liferay-ui:search-container
	iteratorURL="<%= iteratorURL %>"
	orderByCol="<%= orderByCol %>"
	orderByType="<%= orderByType %>"
>
	<liferay-ui:search-container-results>

		<%
		AssetEntryQuery assetEntryQuery = new AssetEntryQuery(KBArticle.class.getName(), searchContainer);

		pageContext.setAttribute("results", AssetEntryServiceUtil.getEntries(assetEntryQuery));
		pageContext.setAttribute("total", AssetEntryServiceUtil.getEntriesCount(assetEntryQuery));
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.portlet.asset.model.AssetEntry"
		keyProperty="entryId"
		modelVar="assetEntry"
	>
		<liferay-portlet:renderURL varImpl="rowURL">
			<portlet:param name="jspPage" value="/display/view_article.jsp" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(assetEntry.getClassPK()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			orderable="<%= true %>"
			property="title"
		/>

		<c:if test="<%= showKBArticleAuthorColumn %>">
			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="author"
				orderable="<%= true %>"
				orderableProperty="userName"
				property="userName"
			/>
		</c:if>

		<c:if test="<%= showKBArticleCreateDateColumn %>">
			<liferay-ui:search-container-column-text
				cssClass="kb-column-no-wrap"
				href="<%= rowURL %>"
				name="create-date"
				orderable="<%= true %>"
				orderableProperty="createDate"
				value='<%= dateFormatDate.format(assetEntry.getCreateDate()) + "<br />" + dateFormatTime.format(assetEntry.getCreateDate()) %>'
			/>
		</c:if>

		<c:if test="<%= showKBArticleModifiedDateColumn %>">
			<liferay-ui:search-container-column-text
				cssClass="kb-column-no-wrap"
				href="<%= rowURL %>"
				name="modified-date"
				orderable="<%= true %>"
				orderableProperty="modifiedDate"
				value='<%= dateFormatDate.format(assetEntry.getModifiedDate()) + "<br />" + dateFormatTime.format(assetEntry.getModifiedDate()) %>'
			/>
		</c:if>

		<c:if test="<%= showKBArticleStatusColumn && DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR) %>">
			<liferay-ui:search-container-column-text
				cssClass="kb-column-no-wrap"
				href="<%= rowURL %>"
				name="status"
				orderable="<%= true %>"
				value='<%= WorkflowConstants.STATUS_APPROVED + " (" + LanguageUtil.get(pageContext, WorkflowConstants.LABEL_APPROVED) + ")" %>'
			/>
		</c:if>

		<c:if test="<%= showKBArticleViewsColumn %>">
			<liferay-ui:search-container-column-text
				cssClass="kb-column-no-wrap"
				href="<%= rowURL %>"
				name="views"
				orderable="<%= true %>"
				orderableProperty="viewCount"
				property="viewCount"
			/>
		</c:if>
	</liferay-ui:search-container-row>

	<c:if test="<%= (assetCategoryId > 0) || Validator.isNotNull(assetTagName) %>">
		<div class="portlet-msg-info">
			<c:choose>
				<c:when test="<%= assetCategoryId > 0 %>">

					<%
					AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(assetCategoryId);

					assetCategory = assetCategory.toEscapedModel();

					AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.getAssetVocabulary(assetCategory.getVocabularyId());

					assetVocabulary = assetVocabulary.toEscapedModel();
					%>

					<c:choose>
						<c:when test="<%= Validator.isNotNull(assetTagName) %>">
							<c:choose>
								<c:when test="<%= total > 0 %>">
									<%= LanguageUtil.format(pageContext, "articles-with-x-x-and-tag-x", new String[] {assetVocabulary.getTitle(locale), assetCategory.getTitle(locale), assetTagName}, false) %>
								</c:when>
								<c:otherwise>
									<%= LanguageUtil.format(pageContext, "there-are-no-articles-with-x-x-and-tag-x", new String[] {assetVocabulary.getTitle(locale), assetCategory.getTitle(locale), assetTagName}, false) %>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="<%= total > 0 %>">
									<%= LanguageUtil.format(pageContext, "articles-with-x-x", new String[] {assetVocabulary.getTitle(locale), assetCategory.getTitle(locale)}, false) %>
								</c:when>
								<c:otherwise>
									<%= LanguageUtil.format(pageContext, "there-are-no-articles-with-x-x", new String[] {assetVocabulary.getTitle(locale), assetCategory.getTitle(locale)}, false) %>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="<%= total > 0 %>">
							<%= LanguageUtil.format(pageContext, "articles-with-tag-x", assetTagName, false) %>
						</c:when>
						<c:otherwise>
							<%= LanguageUtil.format(pageContext, "there-are-no-articles-with-tag-x", assetTagName, false) %>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>