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

KBNavigationDisplayContext kbNavigationDisplayContext = (KBNavigationDisplayContext)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_NAVIGATION_DISPLAY_CONTEXT);

String currentKBFolderURLTitle = kbNavigationDisplayContext.getCurrentKBFolderURLTitle();

List<KBFolder> kbFolders = KnowledgeBaseUtil.getAlternateRootKBFolders(scopeGroupId, resourcePrimKey);
%>

<c:if test="<%= kbFolders.size() > 1 %>">
	<liferay-portlet:actionURL name="updateRootKBFolderId" var="updateRootKBFolderIdURL">
		<c:if test="<%= kbArticle != null %>">
			<portlet:param name="urlTitle" value="<%= kbArticle.getUrlTitle() %>" />
		</c:if>
	</liferay-portlet:actionURL>

	<div class="kb-field-wrapper kbarticle-root-selector">
		<aui:form action="<%= updateRootKBFolderIdURL %>" name="updateRootKBFolderIdFm">
			<aui:select label="" name="rootKBFolderId" title="root-folder">

				<%
				for (KBFolder kbFolder : kbFolders) {
				%>

					<aui:option
						selected="<%= currentKBFolderURLTitle.equals(kbFolder.getUrlTitle()) %>"
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