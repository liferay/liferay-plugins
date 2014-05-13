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
List<KBArticle> kbArticles = KBArticleLocalServiceUtil.getKBArticles(themeDisplay.getScopeGroupId(), KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY, WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

long resourcePrimKey = ParamUtil.getLong(request, "resourcePrimKey");
%>

<div id="<portlet:namespace />kbArticlesTreeBoundingBox"></div>

<aui:script use="aui-tree-view">
	var children = [

		<%
		for (int i = 0; i < kbArticles.size(); i++) {
			boolean expandedKBarticle = false;

			KBArticle kbArticle = kbArticles.get(i);

			if (kbArticle.getResourcePrimKey() == resourcePrimKey) {
				expandedKBarticle = true;
			}

			List<KBArticle> childKBArticles = KBArticleLocalServiceUtil.getKBArticles(themeDisplay.getScopeGroupId(), kbArticle.getResourcePrimKey(), WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
		%>

			{

				<c:if test="<%= !childKBArticles.isEmpty() %>">
					children: [

						<%
						for (int j = 0; j < childKBArticles.size(); j++) {
							boolean expandedChildKBArticle = false;

							KBArticle childKBArticle = childKBArticles.get(j);

							List<KBArticle> allDescendantKBArticles = KBArticleLocalServiceUtil.getAllDescendantKBArticles(childKBArticle.getResourcePrimKey(), WorkflowConstants.STATUS_APPROVED, null);

							PortletURL viewChildURL = renderResponse.createRenderURL();

							viewChildURL.setParameter("resourcePrimKey", String.valueOf(childKBArticle.getResourcePrimKey()));

							if (childKBArticle.getResourcePrimKey() == resourcePrimKey) {
								expandedChildKBArticle = true;
								expandedKBarticle = true;
							}
						%>

							{
								children: [

									<%
									for (int k = 0; k < allDescendantKBArticles.size(); k++) {
										KBArticle curKBArticle = allDescendantKBArticles.get(k);

										PortletURL viewCurKBArticleURL = renderResponse.createRenderURL();

										viewCurKBArticleURL.setParameter("resourcePrimKey", String.valueOf(curKBArticle.getResourcePrimKey()));

										if (curKBArticle.getResourcePrimKey() == resourcePrimKey) {
											expandedChildKBArticle = true;
											expandedKBarticle = true;
										}
									%>

										{
											expanded: false,
											id:'<portlet:namespace/><%= curKBArticle.getResourcePrimKey() %>',
											label: '<a href="<%= viewCurKBArticleURL %>"><%= curKBArticle.getTitle() %></a>'

											<c:choose>
												<c:when test="<%= k == (allDescendantKBArticles.size() - 1) %>">
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

								expanded: <%= expandedChildKBArticle %>,
								id:'<portlet:namespace/><%= childKBArticle.getResourcePrimKey() %>',
								label: '<a href="<%= viewChildURL %>"><%= childKBArticle.getTitle() %></a>'

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

				viewURL.setParameter("resourcePrimKey", String.valueOf(kbArticle.getResourcePrimKey()));
				%>

				expanded: <%= expandedKBarticle %>,
				id:'<portlet:namespace/><%= kbArticle.getResourcePrimKey() %>',
				label: '<a href="<%= viewURL %>"><%= kbArticle.getTitle() %></a>',
				on: {
					select: function(event) {
						var node = event.currentTarget;

						var tree = node.get('ownerTree');

						tree.collapseAll();

						node.expand();
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