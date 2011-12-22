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
String orderByCol = ParamUtil.getString(request, "orderByCol", "modified-date");
String orderByType = ParamUtil.getString(request, "orderByType", "desc");
%>

<liferay-util:include page="/display/top_links.jsp" servletContext="<%= application %>" />

<aui:form method="post" name="fm">

	<aui:fieldset>
		<liferay-ui:search-container
			emptyResultsMessage="no-subscriptions-were-found"
			orderByCol="<%= orderByCol %>"
			orderByComparator="<%= KnowledgeBaseUtil.getKBArticleOrderByComparator(orderByCol, orderByType) %>"
			orderByType="<%= orderByType %>"
		>
			<liferay-ui:search-container-results>

				<%
				List<Subscription> subscriptions = SubscriptionLocalServiceUtil.getUserSubscriptions(user.getUserId(), KBArticle.class.getName());

				List<KBArticle> kbArticles = KBArticleServiceUtil.getKBArticles(scopeGroupId, StringUtil.split(ListUtil.toString(subscriptions, "classPK"), 0L), WorkflowConstants.STATUS_APPROVED, searchContainer.getOrderByComparator());

				pageContext.setAttribute("results", kbArticles);
				pageContext.setAttribute("total", kbArticles.size());
				%>

			</liferay-ui:search-container-results>

			<liferay-ui:search-container-row
				className="com.liferay.knowledgebase.model.KBArticle"
				keyProperty="resourcePrimKey"
				modelVar="kbArticle"
			>
				<liferay-portlet:renderURL varImpl="rowURL">
					<portlet:param name="jspPage" value="/display/view_article.jsp" />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
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
						orderableProperty="user-name"
						property="userName"
					/>
				</c:if>

				<c:if test="<%= showKBArticleCreateDateColumn %>">
					<liferay-ui:search-container-column-text
						cssClass="kb-column-no-wrap"
						href="<%= rowURL %>"
						name="create-date"
						orderable="<%= true %>"
						value='<%= dateFormatDate.format(kbArticle.getCreateDate()) + "<br />" + dateFormatTime.format(kbArticle.getCreateDate()) %>'
					/>
				</c:if>

				<c:if test="<%= showKBArticleModifiedDateColumn %>">
					<liferay-ui:search-container-column-text
						cssClass="kb-column-no-wrap"
						href="<%= rowURL %>"
						name="modified-date"
						orderable="<%= true %>"
						value='<%= dateFormatDate.format(kbArticle.getModifiedDate()) + "<br />" + dateFormatTime.format(kbArticle.getModifiedDate()) %>'
					/>
				</c:if>

				<c:if test="<%= showKBArticleStatusColumn && DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR) %>">
					<liferay-ui:search-container-column-text
						cssClass="kb-column-no-wrap"
						href="<%= rowURL %>"
						name="status"
						orderable="<%= true %>"
						value='<%= kbArticle.getStatus() + " (" + LanguageUtil.get(pageContext, WorkflowConstants.toLabel(kbArticle.getStatus())) + ")" %>'
					/>
				</c:if>

				<c:if test="<%= showKBArticleViewsColumn %>">
					<liferay-ui:search-container-column-text
						cssClass="kb-column-no-wrap"
						href="<%= rowURL %>"
						name="views"
						orderable="<%= true %>"
						orderableProperty="view-count"
						property="viewCount"
					/>
				</c:if>

				<c:if test="<%= KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.SUBSCRIBE) %>">
					<liferay-ui:search-container-column-text
						align="right"
					>
						<liferay-portlet:actionURL name="unsubscribeKBArticle" var="unsubscribeKBArticleURL">
							<portlet:param name="redirect" value="<%= redirect %>" />
							<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
						</liferay-portlet:actionURL>

						<liferay-ui:icon
							image="unsubscribe"
							label="<%= true %>"
							url="<%= unsubscribeKBArticleURL %>"
						/>
					</liferay-ui:search-container-column-text>
				</c:if>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>