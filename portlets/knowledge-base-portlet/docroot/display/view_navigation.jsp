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

<%@ include file="/display/init.jsp" %>

<%
List<Long> ancestorResourcePrimaryKeys = new ArrayList<Long>();

if (resourcePrimKey != KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) {
	KBArticle kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(resourcePrimKey, WorkflowConstants.STATUS_APPROVED);

	ancestorResourcePrimaryKeys = kbArticle.getAncestorResourcePrimaryKeys();

	Collections.reverse(ancestorResourcePrimaryKeys);
}
else {
	ancestorResourcePrimaryKeys.add(KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY);
}
%>

<div class="kbarticle-navigation">

	<%
	List<KBArticle> kbArticles = KBArticleLocalServiceUtil.getKBArticles(themeDisplay.getScopeGroupId(), KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY, WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new KBArticlePriorityComparator());

	for (KBArticle kbArticle : kbArticles) {
		PortletURL viewURL = renderResponse.createRenderURL();

		viewURL.setParameter("resourcePrimKey", String.valueOf(kbArticle.getResourcePrimKey()));
	%>

		<ul>
			<li>

				<%
				boolean kbArticleExpanded = false;

				if ((ancestorResourcePrimaryKeys.size() > 0) && (kbArticle.getResourcePrimKey() == ancestorResourcePrimaryKeys.get(0))) {
					kbArticleExpanded = true;
				}

				String kbArticleClass = StringPool.BLANK;

				if (kbArticle.getResourcePrimKey() == resourcePrimKey) {
					kbArticleClass = "kbarticle-selected";
				}
				else if (kbArticleExpanded) {
					kbArticleClass = "kbarticle-expanded";
				}
				%>

				<a class="<%= kbArticleClass %>" href="<%= viewURL %>"><%= kbArticle.getTitle() %></a>

				<c:if test="<%= kbArticleExpanded %>">

					<%
					List<KBArticle> childKBArticles = KBArticleLocalServiceUtil.getKBArticles(themeDisplay.getScopeGroupId(), kbArticle.getResourcePrimKey(), WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new KBArticlePriorityComparator());

					for (KBArticle childKBArticle : childKBArticles) {
						PortletURL viewChildURL = renderResponse.createRenderURL();

						viewChildURL.setParameter("resourcePrimKey", String.valueOf(childKBArticle.getResourcePrimKey()));
					%>

						<ul>
							<li>

								<%
								boolean childKBArticleExpanded = false;

								if ((ancestorResourcePrimaryKeys.size() > 1) && (childKBArticle.getResourcePrimKey() == ancestorResourcePrimaryKeys.get(1))) {
									childKBArticleExpanded = true;
								}

								String childKBArticleClass = StringPool.BLANK;

								if (childKBArticle.getResourcePrimKey() == resourcePrimKey) {
									childKBArticleClass = "kbarticle-selected";
								}
								else if (childKBArticleExpanded) {
									childKBArticleClass = "kbarticle-expanded";
								}
								%>

								<a class="<%= childKBArticleClass %>" href="<%= viewChildURL %>"><%= childKBArticle.getTitle() %></a>

								<c:if test="<%= childKBArticleExpanded %>">

									<%
									List<KBArticle> allDescendantKBArticles = KBArticleLocalServiceUtil.getAllDescendantKBArticles(childKBArticle.getResourcePrimKey(), WorkflowConstants.STATUS_APPROVED, new KBArticlePriorityComparator());

									for (KBArticle curKBArticle : allDescendantKBArticles) {
										PortletURL viewCurKBArticleURL = renderResponse.createRenderURL();

										viewCurKBArticleURL.setParameter("resourcePrimKey", String.valueOf(curKBArticle.getResourcePrimKey()));
									%>

										<ul>
											<li>
												<a class="<%= curKBArticle.getResourcePrimKey() == resourcePrimKey ? "kbarticle-selected" : StringPool.BLANK %>" href="<%= viewCurKBArticleURL %>"><%= curKBArticle.getTitle() %></a>
											</li>
										</ul>

									<%
									}
									%>

								</c:if>
							</li>
						</ul>

					<%
					}
					%>

				</c:if>
			</li>
		</ul>

	<%
	}
	%>

</div>