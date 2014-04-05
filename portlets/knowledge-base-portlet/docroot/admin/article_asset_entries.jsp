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

<%@ include file="/admin/init.jsp" %>

<%
KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);
%>

<c:if test="<%= showKBArticleAssetEntries %>">

	<%
	long[] groupIds = KBArticleAssetEntriesUtil.getGroupIds(company.getGroup(), kbArticle);
	long[] assetTagIds = KBArticleAssetEntriesUtil.getAssetTagIds(groupIds, kbArticle);
	%>

	<c:if test="<%= assetTagIds.length > 0 %>">

		<%
		long[] classNameIds = new long[] {ClassNameLocalServiceUtil.getClassNameId(BlogsEntry.class), ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class), ClassNameLocalServiceUtil.getClassNameId(KBArticle.class), ClassNameLocalServiceUtil.getClassNameId(MBMessage.class), ClassNameLocalServiceUtil.getClassNameId(WikiPage.class)};

		List<AssetEntry> mostPopularAssetEntries = KBArticleAssetEntriesUtil.getAssetEntries(groupIds, classNameIds, assetTagIds, kbArticle.getResourcePrimKey(), 0, 10, "viewCount");
		List<AssetEntry> mostRecentAssetEntries = KBArticleAssetEntriesUtil.getAssetEntries(groupIds, classNameIds, assetTagIds, kbArticle.getResourcePrimKey(), 0, 10, "modifiedDate");
		%>

		<c:if test="<%= !mostPopularAssetEntries.isEmpty() || !mostRecentAssetEntries.isEmpty() %>">
			<div class="kb-article-asset-entries">
				<table class="lfr-table" width="100%">
				<tr>
					<td class="kb-most-recent-column">
						<div class="kb-header">
							<liferay-ui:message key="most-recent" />
						</div>

						<c:if test="<%= mostRecentAssetEntries.isEmpty() %>">
							<liferay-ui:message key="there-are-no-entries" />
						</c:if>

						<%
						for (AssetEntry assetEntry : mostRecentAssetEntries) {
							AssetRendererFactory assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(assetEntry.getClassName());

							AssetRenderer assetRenderer = assetRendererFactory.getAssetRenderer(assetEntry.getClassPK());
						%>

							<div class="kb-title">
								<liferay-ui:icon
									label="<%= true %>"
									message="<%= assetRenderer.getTitle(locale) %>"
									method="get"
									src="<%= assetRenderer.getIconPath(renderRequest) %>"
									url="<%= KBArticleAssetEntriesUtil.getURL(request, themeDisplay, assetRendererFactory, assetRenderer) %>"
								/>

								<span class="kb-info"><%= dateFormatDate.format(assetEntry.getModifiedDate()) %></span>
							</div>

						<%
						}
						%>

					</td>
					<td class="kb-most-popular-column">
						<div class="kb-header">
							<liferay-ui:message key="most-popular" />
						</div>

						<c:if test="<%= mostPopularAssetEntries.isEmpty() %>">
							<liferay-ui:message key="there-are-no-entries" />
						</c:if>

						<%
						for (AssetEntry assetEntry : mostPopularAssetEntries) {
							AssetRendererFactory assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(assetEntry.getClassName());

							AssetRenderer assetRenderer = assetRendererFactory.getAssetRenderer(assetEntry.getClassPK());
						%>

							<div class="kb-title">
								<liferay-ui:icon
									label="<%= true %>"
									message="<%= assetRenderer.getTitle(locale) %>"
									method="get"
									src="<%= assetRenderer.getIconPath(renderRequest) %>"
									url="<%= KBArticleAssetEntriesUtil.getURL(request, themeDisplay, assetRendererFactory, assetRenderer) %>"
								/>

								<span class="kb-info">
									<c:choose>
										<c:when test="<%= assetEntry.getViewCount() == 1 %>">
											<%= assetEntry.getViewCount() %> <liferay-ui:message key="view" />
										</c:when>
										<c:otherwise>
											<%= assetEntry.getViewCount() %> <liferay-ui:message key="views" />
										</c:otherwise>
									</c:choose>
								</span>
							</div>

						<%
						}
						%>

					</td>
				</tr>
				</table>
			</div>
		</c:if>
	</c:if>
</c:if>