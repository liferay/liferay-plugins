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
KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

List<Long> ancestorResourcePrimaryKeys = new ArrayList<Long>();

if (kbArticle != null) {
	KBArticle latestKBArticle = KBArticleLocalServiceUtil.getLatestKBArticle(kbArticle.getResourcePrimKey(), WorkflowConstants.STATUS_APPROVED);

	ancestorResourcePrimaryKeys = latestKBArticle.getAncestorResourcePrimaryKeys();

	Collections.reverse(ancestorResourcePrimaryKeys);
}
else {
	ancestorResourcePrimaryKeys.add(KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY);
}
%>

<div class="kbarticle-navigation">

	<%
	List<KBArticle> kbArticles = KBArticleLocalServiceUtil.getKBArticles(themeDisplay.getScopeGroupId(), KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY, WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new KBArticlePriorityComparator(true));

	for (KBArticle curKBArticle : kbArticles) {
		PortletURL viewURL = renderResponse.createRenderURL();

		String urlTitle = curKBArticle.getUrlTitle();

		if (Validator.isNotNull(urlTitle)) {
			viewURL.setParameter("urlTitle", urlTitle);
		}
		else {
			viewURL.setParameter("resourcePrimKey", String.valueOf(curKBArticle.getResourcePrimKey()));
		}
	%>

		<ul>
			<li>

				<%
				boolean kbArticleExpanded = false;

				if ((ancestorResourcePrimaryKeys.size() > 0) && (curKBArticle.getResourcePrimKey() == ancestorResourcePrimaryKeys.get(0))) {
					kbArticleExpanded = true;
				}

				String kbArticleClass = StringPool.BLANK;

				if (curKBArticle.getResourcePrimKey() == kbArticle.getResourcePrimKey()) {
					kbArticleClass = "kbarticle-selected";
				}
				else if (kbArticleExpanded) {
					kbArticleClass = "kbarticle-expanded";
				}
				%>

				<a class="<%= kbArticleClass %>" href="<%= viewURL %>"><%= curKBArticle.getTitle() %></a>

				<c:if test="<%= kbArticleExpanded %>">

					<%
					List<KBArticle> childKBArticles = KBArticleLocalServiceUtil.getKBArticles(themeDisplay.getScopeGroupId(), curKBArticle.getResourcePrimKey(), WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new KBArticlePriorityComparator(true));

					for (KBArticle childKBArticle : childKBArticles) {
						PortletURL viewChildURL = renderResponse.createRenderURL();

						urlTitle = childKBArticle.getUrlTitle();

						if (Validator.isNotNull(urlTitle)) {
							viewChildURL.setParameter("urlTitle", urlTitle);
						}
						else {
							viewChildURL.setParameter("resourcePrimKey", String.valueOf(childKBArticle.getResourcePrimKey()));
						}
					%>

						<ul>
							<li>

								<%
								boolean childKBArticleExpanded = false;

								if ((ancestorResourcePrimaryKeys.size() > 1) && (childKBArticle.getResourcePrimKey() == ancestorResourcePrimaryKeys.get(1))) {
									childKBArticleExpanded = true;
								}

								String childKBArticleClass = StringPool.BLANK;

								if (childKBArticle.getResourcePrimKey() == kbArticle.getResourcePrimKey()) {
									childKBArticleClass = "kbarticle-selected";
								}
								else if (childKBArticleExpanded) {
									childKBArticleClass = "kbarticle-expanded";
								}
								%>

								<a class="<%= childKBArticleClass %>" href="<%= viewChildURL %>"><%= childKBArticle.getTitle() %></a>

								<c:if test="<%= childKBArticleExpanded %>">

									<%
									List<KBArticle> allDescendantKBArticles = KBArticleLocalServiceUtil.getAllDescendantKBArticles(childKBArticle.getResourcePrimKey(), WorkflowConstants.STATUS_APPROVED, new KBArticlePriorityComparator(true));

									for (KBArticle descendantKBArticle : allDescendantKBArticles) {
										PortletURL viewCurKBArticleURL = renderResponse.createRenderURL();

										urlTitle = descendantKBArticle.getUrlTitle();

										if (Validator.isNotNull(urlTitle)) {
											viewCurKBArticleURL.setParameter("urlTitle", urlTitle);
										}
										else {
											viewCurKBArticleURL.setParameter("resourcePrimKey", String.valueOf(descendantKBArticle.getResourcePrimKey()));
										}
									%>

										<ul>
											<li>
												<a class="<%= descendantKBArticle.getResourcePrimKey() == kbArticle.getResourcePrimKey() ? "kbarticle-selected" : StringPool.BLANK %>" href="<%= viewCurKBArticleURL %>"><%= descendantKBArticle.getTitle() %></a>
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