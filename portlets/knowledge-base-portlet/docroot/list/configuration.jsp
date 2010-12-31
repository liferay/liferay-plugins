<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

<%@ include file="/list/init.jsp" %>

<%
String tabs2 = ParamUtil.getString(request, "tabs2", "display-settings");
%>

<liferay-portlet:renderURL portletConfiguration="true" var="portletURL">
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
</liferay-portlet:renderURL>

<liferay-ui:tabs
	names="display-settings,rss"
	param="tabs2"
	url="<%= portletURL %>"
/>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />

	<aui:fieldset>
		<c:choose>
			<c:when test='<%= tabs2.equals("display-settings") %>'>
				<aui:input cssClass="lfr-input-text-container" label="title" name="articlesTitle" value="<%= articlesTitle %>" />

				<div class="kb-field-wrapper">
					<aui:field-wrapper label="options">
						<aui:select inlineField="<%= true %>" label="" name="allArticles">
							<aui:option label="all-articles" selected="<%= allArticles %>" value="<%= true %>" />
							<aui:option label="root-articles" selected="<%= !allArticles %>" value="<%= false %>" />
						</aui:select>

						<aui:select inlineField="<%= true %>" inlineLabel="left" label="order-by" name="orderByColumn">
							<aui:option label="create-date" selected='<%= orderByColumn.equals("create-date") %>' />
							<aui:option label="modified-date" selected='<%= orderByColumn.equals("modified-date") %>' />
							<aui:option label="priority" selected='<%= orderByColumn.equals("priority") %>' />
							<aui:option label="title" selected='<%= orderByColumn.equals("title") %>' />
						</aui:select>

						<aui:select inlineField="<%= true %>" label="" name="orderByAscending">
							<aui:option label="ascending" selected="<%= orderByAscending %>" value="<%= true %>" />
							<aui:option label="descending" selected="<%= !orderByAscending %>" value="<%= false %>" />
						</aui:select>
					</aui:field-wrapper>
				</div>

				<aui:select label="maximum-items-to-display" name="articlesDelta">

					<%
					int[] pageDeltaValues = GetterUtil.getIntegerValues(PropsUtil.getArray(PropsKeys.SEARCH_CONTAINER_PAGE_DELTA_VALUES));

					Arrays.sort(pageDeltaValues);

					for (int pageDeltaValue : pageDeltaValues) {
						if (pageDeltaValue > SearchContainer.MAX_DELTA) {
							break;
						}
					%>

						<aui:option label="<%= pageDeltaValue %>" selected="<%= articlesDelta == pageDeltaValue %>" />

					<%
					}
					%>

				</aui:select>

				<aui:select name="articleWindowState">
					<aui:option label="maximized" selected="<%= articleWindowState.equals(WindowState.MAXIMIZED.toString()) %>" value="<%= WindowState.MAXIMIZED.toString() %>" />
					<aui:option label="normal" selected="<%= articleWindowState.equals(WindowState.NORMAL.toString()) %>" value="<%= WindowState.NORMAL.toString() %>" />
				</aui:select>

				<aui:select name="childArticlesDisplayStyle">
					<aui:option label="<%= RSSUtil.DISPLAY_STYLE_ABSTRACT %>" selected="<%= childArticlesDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_ABSTRACT) %>" />
					<aui:option label="<%= RSSUtil.DISPLAY_STYLE_FULL_CONTENT %>" selected="<%= childArticlesDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_FULL_CONTENT) %>" />
					<aui:option label="<%= RSSUtil.DISPLAY_STYLE_TITLE %>" selected="<%= childArticlesDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_TITLE) %>" />
				</aui:select>

				<aui:input inlineLabel="left" label="show-categories" name="enableArticleAssetCategories" type="checkbox" value="<%= enableArticleAssetCategories %>" />

				<aui:input inlineLabel="left" label="show-tags" name="enableArticleAssetTags" type="checkbox" value="<%= enableArticleAssetTags %>" />

				<aui:input inlineLabel="left" label="show-ratings" name="enableArticleRatings" type="checkbox" value="<%= enableArticleRatings %>" />

				<aui:input inlineLabel="left" label="show-comments" name="enableArticleComments" type="checkbox" value="<%= enableArticleComments %>" />
			</c:when>
			<c:when test='<%= tabs2.equals("rss") %>'>
				<aui:select label="maximum-items-to-display" name="rssDelta">
					<aui:option label="1" selected="<%= rssDelta == 1 %>" />
					<aui:option label="2" selected="<%= rssDelta == 2 %>" />
					<aui:option label="3" selected="<%= rssDelta == 3 %>" />
					<aui:option label="4" selected="<%= rssDelta == 4 %>" />
					<aui:option label="5" selected="<%= rssDelta == 5 %>" />
					<aui:option label="10" selected="<%= rssDelta == 10 %>" />
					<aui:option label="15" selected="<%= rssDelta == 15 %>" />
					<aui:option label="20" selected="<%= rssDelta == 20 %>" />
					<aui:option label="25" selected="<%= rssDelta == 25 %>" />
					<aui:option label="30" selected="<%= rssDelta == 30 %>" />
					<aui:option label="40" selected="<%= rssDelta == 40 %>" />
					<aui:option label="50" selected="<%= rssDelta == 50 %>" />
					<aui:option label="60" selected="<%= rssDelta == 60 %>" />
					<aui:option label="70" selected="<%= rssDelta == 70 %>" />
					<aui:option label="80" selected="<%= rssDelta == 80 %>" />
					<aui:option label="90" selected="<%= rssDelta == 90 %>" />
					<aui:option label="100" selected="<%= rssDelta == 100 %>" />
				</aui:select>

				<aui:select label="display-style" name="rssDisplayStyle">
					<aui:option label="<%= RSSUtil.DISPLAY_STYLE_FULL_CONTENT %>" selected="<%= rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_FULL_CONTENT) %>" />
					<aui:option label="<%= RSSUtil.DISPLAY_STYLE_ABSTRACT %>" selected="<%= rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_ABSTRACT) %>" />
					<aui:option label="<%= RSSUtil.DISPLAY_STYLE_TITLE %>" selected="<%= rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_TITLE) %>" />
				</aui:select>

				<aui:select label="format" name="rssFormat">
					<aui:option label="RSS 1.0" selected='<%= rssFormat.equals("rss10") %>' value="rss10" />
					<aui:option label="RSS 2.0" selected='<%= rssFormat.equals("rss20") %>' value="rss20" />
					<aui:option label="Atom 1.0" selected='<%= rssFormat.equals("atom10") %>' value="atom10" />
				</aui:select>
			</c:when>
		</c:choose>

		<aui:button-row cssClass="kb-submit-buttons">
			<aui:button type="submit" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>