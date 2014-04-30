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

<%@ include file="/navigation/init.jsp" %>

<%
List<KBArticle> kbArticles = KBArticleLocalServiceUtil.getSiblingKBArticles(themeDisplay.getScopeGroupId(), KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY, WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
%>

<div id="<portlet:namespace />kbArticlesTreeBoundingBox"></div>

<aui:script use="aui-tree-view">
	var children = [

		<%
		for (int i = 0; i < kbArticles.size(); i++) {
			KBArticle kbArticle = kbArticles.get(i);

			List<KBArticle> childKBArticles = KBNavigationUtil.getChildKBArticles(kbArticle, new ArrayList<KBArticle>());
		%>

			{

				<c:if test="<%= !childKBArticles.isEmpty() %>">
					children: [

						<%
						for (int j = 0; j < childKBArticles.size(); j++) {
							KBArticle childKBArticle = childKBArticles.get(j);

							PortletURL viewChildURL = renderResponse.createRenderURL();

							viewChildURL.setWindowState(LiferayWindowState.EXCLUSIVE);

							viewChildURL.setParameter("mvcPath", "/article/view_article.jsp");
							viewChildURL.setParameter("resourcePrimKey", String.valueOf(childKBArticle.getResourcePrimKey()));
						%>

							{
								expanded: false,
								id:'<portlet:namespace/><%= childKBArticle.getResourcePrimKey() %>',
								label: '<%= childKBArticle.getTitle() %>',
								on: {
									select: A.bind('fire', Liferay, 'knowledgeBaseNavigation', {url: '<%= viewChildURL %>'})
								}

							<c:choose>
								<c:when test="<%= j == (childKBArticles.size() - 1) %>">
									}
								</c:when>
								<c:otherwise>
									},
								</c:otherwise>
							</c:choose>

						<%
						}
						%>

						],
				</c:if>

				<%
				PortletURL viewURL = renderResponse.createRenderURL();

				viewURL.setWindowState(LiferayWindowState.EXCLUSIVE);

				viewURL.setParameter("mvcPath", "/article/view_article.jsp");
				viewURL.setParameter("resourcePrimKey", String.valueOf(kbArticle.getResourcePrimKey()));
				%>

				expanded: false,
				id:'<portlet:namespace/><%= kbArticle.getResourcePrimKey() %>',
				label: '<%= kbArticle.getTitle() %>',
				on: {
					select: function(event) {
						var node = event.currentTarget;

						var tree = node.get('ownerTree');

						tree.collapseAll();

						node.expand();

						Liferay.fire('knowledgeBaseNavigation', {url: '<%= viewURL %>'});
					}
				}

			<c:choose>
				<c:when test="<%= i == (kbArticles.size() - 1) %>">
					}
				</c:when>
				<c:otherwise>
					},
				</c:otherwise>
			</c:choose>

		<%
		}
		%>

	];

	new A.TreeView(
		{
			boundingBox: '#<portlet:namespace />kbArticlesTreeBoundingBox',
			children: children,
			type: 'normal'
		}
	).render();
</aui:script>