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

<%@ include file="/section/init.jsp" %>

<%
String tabs2 = ParamUtil.getString(request, "tabs2", "general");

String tabs2Names = "general,display-settings";

if (PortalUtil.isRSSFeedsEnabled()) {
	tabs2Names += ",rss";
}
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true" var="configurationRenderURL">
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
</liferay-portlet:renderURL>

<liferay-ui:tabs
	names="<%= tabs2Names %>"
	param="tabs2"
	url="<%= configurationRenderURL %>"
/>

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />

	<liferay-ui:error key="kbArticlesSections" message="please-select-at-least-one-section" />

	<aui:fieldset>
		<c:choose>
			<c:when test='<%= tabs2.equals("general") %>'>
				<aui:input label="show-sections-title" name="preferences--showKBArticlesSectionsTitle--" type="checkbox" value="<%= showKBArticlesSectionsTitle %>" />

				<aui:select cssClass="kb-field-wrapper" ignoreRequestValue="<%= true %>" label="sections" multiple="<%= true %>" name="kbArticlesSections">

					<%
					Map<String, String> sectionsMap = new TreeMap<String, String>();

					for (String section : PortletPropsValues.ADMIN_KB_ARTICLE_SECTIONS) {
						sectionsMap.put(LanguageUtil.get(pageContext, section), section);
					}

					for (Map.Entry<String, String> entry : sectionsMap.entrySet()) {
					%>

						<aui:option label="<%= entry.getKey() %>" selected="<%= ArrayUtil.contains(kbArticlesSections, entry.getValue()) %>" value="<%= entry.getValue() %>" />

					<%
					}
					%>

				</aui:select>

				<aui:select label="article-display-style" name="preferences--kbArticleDisplayStyle--">
					<aui:option label="title" selected='<%= kbArticleDisplayStyle.equals("title") %>' />
					<aui:option label="abstract" selected='<%= kbArticleDisplayStyle.equals("abstract") %>' />
				</aui:select>

				<aui:select label="article-window-state" name="preferences--kbArticleWindowState--">
					<aui:option label="maximized" selected="<%= kbArticleWindowState.equals(WindowState.MAXIMIZED.toString()) %>" value="<%= WindowState.MAXIMIZED.toString() %>" />
					<aui:option label="normal" selected="<%= kbArticleWindowState.equals(WindowState.NORMAL.toString()) %>" value="<%= WindowState.NORMAL.toString() %>" />
				</aui:select>

				<div class="kb-block-labels kb-field-wrapper">
					<aui:select inlineField="<%= true %>" label="order-by" name="preferences--kbArticlesOrderByCol--">
						<aui:option label="create-date" selected='<%= kbArticlesOrderByCol.equals("create-date") %>' />
						<aui:option label="modified-date" selected='<%= kbArticlesOrderByCol.equals("modified-date") %>' />
						<aui:option label="priority" selected='<%= kbArticlesOrderByCol.equals("priority") %>' />
						<aui:option label="title" selected='<%= kbArticlesOrderByCol.equals("title") %>' />
						<aui:option label="view-count" selected='<%= kbArticlesOrderByCol.equals("view-count") %>' />
					</aui:select>

					<aui:select inlineField="<%= true %>" label="<%= StringPool.NBSP %>" name="preferences--kbArticlesOrderByType--">
						<aui:option label="ascending" selected='<%= kbArticlesOrderByType.equals("asc") %>' value="asc" />
						<aui:option label="descending" selected='<%= kbArticlesOrderByType.equals("desc") %>' value="desc" />
					</aui:select>
				</div>

				<aui:select cssClass="kb-field-wrapper" label="items-per-page" name="preferences--kbArticlesDelta--">

					<%
					int[] pageDeltaValues = GetterUtil.getIntegerValues(PropsUtil.getArray(PropsKeys.SEARCH_CONTAINER_PAGE_DELTA_VALUES));

					Arrays.sort(pageDeltaValues);

					for (int pageDeltaValue : pageDeltaValues) {
					%>

						<aui:option label="<%= pageDeltaValue %>" selected="<%= kbArticlesDelta == pageDeltaValue %>" />

					<%
					}
					%>

				</aui:select>

				<aui:input label="show-pagination" name="preferences--showKBArticlesPagination--" type="checkbox" value="<%= showKBArticlesPagination %>" />
			</c:when>
			<c:when test='<%= tabs2.equals("display-settings") %>'>
				<aui:input label="enable-description" name="preferences--enableKBArticleDescription--" type="checkbox" value="<%= enableKBArticleDescription %>" />

				<aui:input label="enable-ratings" name="preferences--enableKBArticleRatings--" type="checkbox" value="<%= enableKBArticleRatings %>" />

				<div class="kb-ratings-type" id="<portlet:namespace />ratingsType">
					<aui:input checked='<%= kbArticleRatingsType.equals("stars") %>' label="use-star-ratings" name="preferences--kbArticleRatingsType--" type="radio" value="stars" />
					<aui:input checked='<%= kbArticleRatingsType.equals("thumbs") %>' label="use-thumbs-up-thumbs-down" name="preferences--kbArticleRatingsType--" type="radio" value="thumbs" />
				</div>

				<aui:input label="show-asset-entries" name="preferences--showKBArticleAssetEntries--" type="checkbox" value="<%= showKBArticleAssetEntries %>" />

				<aui:input label="enable-related-assets" name="preferences--enableKBArticleAssetLinks--" type="checkbox" value="<%= enableKBArticleAssetLinks %>" />

				<aui:input label="enable-view-count-increment" name="preferences--enableKBArticleViewCountIncrement--" type="checkbox" value="<%= enableKBArticleViewCountIncrement %>" />

				<aui:input label="enable-subscriptions" name="preferences--enableKBArticleSubscriptions--" type="checkbox" value="<%= enableKBArticleSubscriptions %>" />

				<aui:input label="enable-history" name="preferences--enableKBArticleHistory--" type="checkbox" value="<%= enableKBArticleHistory %>" />

				<aui:input label="enable-print" name="preferences--enableKBArticlePrint--" type="checkbox" value="<%= enableKBArticlePrint %>" />

				<aui:fieldset>
					<aui:input label="enable-social-bookmarks" name="preferences--enableSocialBookmarks--" type="checkbox" value="<%= enableSocialBookmarks %>" />

					<div class="social-boomarks-options" id="<portlet:namespace />socialBookmarksOptions">
						<aui:select label="display-style" name="preferences--socialBookmarksDisplayStyle--">
							<aui:option label="simple" selected='<%= socialBookmarksDisplayStyle.equals("simple") %>' />
							<aui:option label="vertical" selected='<%= socialBookmarksDisplayStyle.equals("vertical") %>' />
							<aui:option label="horizontal" selected='<%= socialBookmarksDisplayStyle.equals("horizontal") %>' />
						</aui:select>

						<aui:select label="display-position" name="preferences--socialBookmarksDisplayPosition--">
							<aui:option label="top" selected='<%= socialBookmarksDisplayPosition.equals("top") %>' />
							<aui:option label="bottom" selected='<%= socialBookmarksDisplayPosition.equals("bottom") %>' />
						</aui:select>

						<aui:field-wrapper label="social-bookmarks">

							<%
							String[] socialBookmarksTypesArray = StringUtil.split(socialBookmarksTypes);

							for (String type : PropsUtil.getArray(PropsKeys.SOCIAL_BOOKMARK_TYPES)) {
							%>

								<aui:input
									checked="<%= ArrayUtil.contains(socialBookmarksTypesArray, type) %>"
									id='<%= "socialBookmarksTypes" + type %>'
									inlineLabel="right"
									label="<%= type %>"
									name="preferences--socialBookmarksTypes--"
									type="checkbox"
									value="<%= type %>"
								/>

							<%
							}
							%>

						</aui:field-wrapper>
					</div>
				</aui:fieldset>
			</c:when>
			<c:when test='<%= tabs2.equals("rss") %>'>
				<liferay-ui:rss-settings
					delta="<%= rssDelta %>"
					displayStyle="<%= rssDisplayStyle %>"
					enabled="<%= enableRSS %>"
					feedType="<%= rssFeedType %>"
				/>
			</c:when>
		</c:choose>

		<aui:button-row cssClass="kb-submit-buttons">
			<aui:button type="submit" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<c:if test='<%= tabs2.equals("display-settings") %>'>
	<aui:script>
		Liferay.Util.toggleBoxes('<portlet:namespace />enableKBArticleRatingsCheckbox', '<portlet:namespace />ratingsType');
		Liferay.Util.toggleBoxes('<portlet:namespace />enableSocialBookmarksCheckbox','<portlet:namespace />socialBookmarksOptions');
	</aui:script>
</c:if>