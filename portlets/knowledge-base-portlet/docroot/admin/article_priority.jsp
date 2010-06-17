<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/admin/init.jsp" %>

<%
Article article = (Article)request.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);

long resourcePrimKey = BeanParamUtil.getLong(article, request, "resourcePrimKey");

long parentResourcePrimKey = BeanParamUtil.getLong(article, request, "parentResourcePrimKey", ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY);
int priority = BeanParamUtil.getInteger(article, request, "priority", ArticleConstants.DEFAULT_PRIORITY);

long oldParentResourcePrimKey = ParamUtil.getLong(request, "oldParentResourcePrimKey", parentResourcePrimKey);
%>

<div class="kb-article-priority">
	<c:if test="<%= parentResourcePrimKey != ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY %>">

		<%
		Article parentArticle = ArticleServiceUtil.getLatestArticle(parentResourcePrimKey);
		%>

		<%= parentArticle.getTitle() %>
	</c:if>

	<%
	Map<String, Object> params = new HashMap<String, Object>();

	params.put("groupId", scopeGroupId);
	params.put("parentResourcePrimKey", parentResourcePrimKey);

	int total = ArticleServiceUtil.getArticlesCount(params, false);

	if ((resourcePrimKey <= 0) || (parentResourcePrimKey != oldParentResourcePrimKey)) {
		total = total + 1;
	}
	%>

	<aui:select inlineField="<%= true %>" label="" name="priority">

		<%
		for (int i = 0; i < total; i++) {
		%>

			<aui:option label="<%= i + 1 %>" selected="<%= priority == i %>" value="<%= i %>" />

		<%
		}
		%>

	</aui:select>
</div>