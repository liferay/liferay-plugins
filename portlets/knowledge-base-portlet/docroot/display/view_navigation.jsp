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
	ancestorResourcePrimaryKeys.add(KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);
}

long kbFolderClassNameId = PortalUtil.getClassNameId(KBFolderConstants.getClassName());

long rootResourcePrimKey = KBFolderConstants.DEFAULT_PARENT_FOLDER_ID;

if (kbArticle != null) {
	rootResourcePrimKey = KnowledgeBaseUtil.getKBFolderId(kbArticle.getParentResourceClassNameId(), kbArticle.getParentResourcePrimKey());
}

if (rootResourcePrimKey == KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
	rootResourcePrimKey = KnowledgeBaseUtil.getRootResourcePrimKey(renderRequest, scopeGroupId, resourceClassNameId, resourcePrimKey);
}

String preferredKBFolderUrlTitle = portalPreferences.getValue(PortletKeys.KNOWLEDGE_BASE_DISPLAY, "preferredKBFolderUrlTitle");

String currentKBFolderUrlTitle = preferredKBFolderUrlTitle;

if (rootResourcePrimKey != KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
	KBFolder kbFolder = KBFolderServiceUtil.getKBFolder(rootResourcePrimKey);

	PortalUtil.setPageTitle(contentRootPrefix + " " + kbFolder.getName(), request);

	currentKBFolderUrlTitle = kbFolder.getUrlTitle();
}
%>

<div class="kbarticle-navigation">
	<c:if test="<%= resourceClassNameId == kbFolderClassNameId %>">

		<%
		List<KBFolder> kbFolders = KnowledgeBaseUtil.getAlternateRootKBFolders(scopeGroupId, resourcePrimKey);
		%>

		<c:if test="<%= kbFolders.size() > 1 %>">
			<liferay-portlet:actionURL name="updateRootKBFolderId" var="updateRootKBFolderIdURL">
				<c:if test="<%= kbArticle != null %>">
					<portlet:param name="urlTitle" value="<%= kbArticle.getUrlTitle() %>" />
				</c:if>
			</liferay-portlet:actionURL>

			<div class="kbarticle-root-selector kb-field-wrapper">
				<aui:form action="<%= updateRootKBFolderIdURL %>" name="updateRootKBFolderIdFm">
					<aui:select label="" name="rootKBFolderId">

						<%
						for (KBFolder kbFolder : kbFolders) {
						%>

							<aui:option
								selected="<%= currentKBFolderUrlTitle.equals(kbFolder.getUrlTitle()) %>"
								value="<%= kbFolder.getKbFolderId() %>"
							>
								<%= contentRootPrefix + " " + kbFolder.getName() %>
							</aui:option>

						<%
						}
						%>

					</aui:select>
				</aui:form>
			</div>

			<aui:script use="aui-base">
				var updateRootKBFolderIdFm = A.one('#<portlet:namespace />updateRootKBFolderIdFm');
				var rootKBFolderIdSelect = A.one('#<portlet:namespace />rootKBFolderId');

				rootKBFolderIdSelect.on(
					'change',
					function() {
						updateRootKBFolderIdFm.submit();
					}
				);
			</aui:script>
		</c:if>
	</c:if>

	<%
	List<KBArticle> kbArticles = KBArticleLocalServiceUtil.getKBArticles(themeDisplay.getScopeGroupId(), rootResourcePrimKey, WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new KBArticlePriorityComparator(true));

	for (KBArticle curKBArticle : kbArticles) {
		PortletURL viewURL = renderResponse.createRenderURL();

		String urlTitle = curKBArticle.getUrlTitle();

		if (Validator.isNotNull(urlTitle)) {
			viewURL.setParameter("urlTitle", urlTitle);

			if (curKBArticle.getKbFolderId() != KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
				KBFolder kbFolder = KBFolderServiceUtil.getKBFolder(curKBArticle.getKbFolderId());

				viewURL.setParameter("kbFolderUrlTitle", kbFolder.getUrlTitle());
			}
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

							if (childKBArticle.getKbFolderId() != KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
								KBFolder kbFolder = KBFolderServiceUtil.getKBFolder(childKBArticle.getKbFolderId());

								viewChildURL.setParameter("kbFolderUrlTitle", kbFolder.getUrlTitle());
							}
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

											if (descendantKBArticle.getKbFolderId() != KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
												KBFolder kbFolder = KBFolderServiceUtil.getKBFolder(descendantKBArticle.getKbFolderId());

												viewCurKBArticleURL.setParameter("kbFolderUrlTitle", kbFolder.getUrlTitle());
											}
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