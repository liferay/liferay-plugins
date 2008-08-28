<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="/knowledge_base/init.jsp" %>

<%
KBArticle article = (KBArticle) request.getAttribute(KnowledgeBaseKeys.ARTICLE);
String type = (String) request.getAttribute("article_iterator.type");

long entryId = ParamUtil.getLong(request, "entryId");

PortletURL portletURL = renderResponse.createRenderURL();

if (type.equals("all_articles")) {
	portletURL.setParameter("view", "view_all_articles");
}
else if (type.equals("article_history")) {
	portletURL.setParameter("view", "view_article_history");
	portletURL.setParameter("tabs", "history");
}
else if (type.equals("subscriptions")) {
	portletURL.setParameter("view", "view_subscriptions");
}
else if (type.equals("templates")) {
	portletURL.setParameter("view", "view_templates");
}
else if (type.equals("tagged_articles")) {
	portletURL.setParameter("view", "view_tagged_articles");
	portletURL.setParameter("entryId", String.valueOf(entryId));
}

List headerNames = new ArrayList();

headerNames.add("id");
headerNames.add("title");

if (type.equals("article_history")) {
	headerNames.add("version");
}

headerNames.add("author");

if (type.endsWith("_articles") || type.equals("subscriptions")) {
	headerNames.add("views");
}

headerNames.add("date");

if (type.equals("article_history") || themeDisplay.isSignedIn()) {
	headerNames.add("");
}

String emptyResultsMessage = null;

if (type.equals("all_articles")) {
	emptyResultsMessage = "there-are-no-knowledge-base-articles";
}
else if (type.equals("subscriptions")) {
	emptyResultsMessage = "you-are-not-subscribed-to-any-knowledge-base-articles";
}
else if (type.equals("templates")) {
	emptyResultsMessage = "there-are-no-knowledge-base-templates";
}
else if (type.equals("tagged_articles")) {
	emptyResultsMessage = "there-are-no-knowledge-base-articles-with-this-tag";
}

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, headerNames, emptyResultsMessage);

if (type.equals("article_history")) {
	searchContainer.setRowChecker(new RowChecker(renderResponse, RowChecker.ALIGN, RowChecker.VALIGN, RowChecker.FORM_NAME, null, RowChecker.ROW_IDS));
}

int total = 0;
List<KBArticle> results = null;

if (type.equals("all_articles")) {
	total = KBArticleLocalServiceUtil.getGroupArticlesCount(themeDisplay.getUserId(), portletGroupId, true, false, false);
	results = KBArticleLocalServiceUtil.getGroupArticles(themeDisplay.getUserId(), portletGroupId, true, false, false, searchContainer.getStart(), searchContainer.getEnd());
}
else if (type.equals("article_history")) {
	total = KBArticleLocalServiceUtil.getArticlesCount(article.getResourcePrimKey());
	results = KBArticleLocalServiceUtil.getArticles(article.getResourcePrimKey(), searchContainer.getStart(), searchContainer.getEnd(), new ArticleVersionComparator());
}
else if (type.equals("subscriptions")) {
	total = KBArticleLocalServiceUtil.getSubscribedArticlesCount(themeDisplay.getUserId(), portletGroupId);
	results = KBArticleLocalServiceUtil.getSubscribedArticles(themeDisplay.getUserId(), portletGroupId, searchContainer.getStart(), searchContainer.getEnd());
}
else if (type.equals("templates")) {
	total = KBArticleLocalServiceUtil.getGroupArticlesCount(themeDisplay.getUserId(), portletGroupId, true, true, false);
	results = KBArticleLocalServiceUtil.getGroupArticles(themeDisplay.getUserId(), portletGroupId, true, true, false, searchContainer.getStart(), searchContainer.getEnd());
}
else if (type.equals("tagged_articles")) {
	long classNameId = PortalUtil.getClassNameId(KBArticle.class.getName());
	long[] entryIds = new long[] {entryId};
	long[] notEntryIds = new long[0];
	Date now = new Date();

	total = TagsAssetLocalServiceUtil.getAssetsCount(portletGroupId.longValue(), new long[] {classNameId}, entryIds, notEntryIds, false, false, now, now);
	List<TagsAsset> assets = TagsAssetLocalServiceUtil.getAssets(portletGroupId.longValue(), new long[] {classNameId}, entryIds, notEntryIds, false, null, null, null, null, false, now, now, searchContainer.getStart(), searchContainer.getEnd());

	results = new ArrayList();

	for (TagsAsset asset : assets) {
		KBArticle assetArticle = KBArticleLocalServiceUtil.getArticle(asset.getClassPK());

		if (!assetArticle.getDraft()) {
			results.add(assetArticle);
		}
	}
}
else {
	results = Collections.EMPTY_LIST;
}

searchContainer.setTotal(total);
searchContainer.setResults(results);

List resultRows = searchContainer.getResultRows();

for (int i = 0; i < results.size(); i++) {
	KBArticle curArticle = results.get(i);

	ResultRow row = new ResultRow(curArticle, String.valueOf(curArticle.getVersion()), i);

	PortletURL rowURL = renderResponse.createRenderURL();

	rowURL.setParameter("view", "view_article");
	rowURL.setParameter("title", curArticle.getTitle());

	if (type.equals("article_history")) {
		rowURL.setParameter("version", String.valueOf(curArticle.getVersion()));
	}

	// Id

	row.addText(String.valueOf(curArticle.getResourcePrimKey()), rowURL);

	// Title

	row.addText(curArticle.getTitle(), rowURL);

	// Revision

	if (type.equals("article_history")) {
		String revision = String.valueOf(curArticle.getVersion());

		if (curArticle.isMinorEdit()) {
			revision += " (" + LanguageUtil.get(pageContext, "minor-edit") + ")";
		}

		row.addText(revision, rowURL);
	}

	// Author

	row.addText(PortalUtil.getUserName(curArticle.getUserId(), curArticle.getUserName()), rowURL);

	// Views

	if (type.endsWith("_articles") || type.equals("subscriptions")) {
		TagsAsset asset = TagsAssetLocalServiceUtil.getAsset(KBArticle.class.getName(), curArticle.getResourcePrimKey());

		row.addText(String.valueOf(asset.getViewCount()), rowURL);
	}

	// Date

	row.addText(dateFormatDateTime.format(curArticle.getModifiedDate()), rowURL);

	// Action

	if (type.equals("article_history")) {
		if (curArticle.isHead()) {
			row.addText(StringPool.BLANK);
		}
		else {
			row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/knowledge_base/views/article_history_action.jsp", config.getServletContext(), request, response);
		}
	}
	else if (themeDisplay.isSignedIn()) {
		row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/knowledge_base/views/article_action.jsp", config.getServletContext(), request, response);
	}

	// Add result row

	resultRows.add(row);
}
%>

<c:if test='<%= type.equals("article_history") && (results.size() > 1) %>'>

	<%
	KBArticle latestArticle = results.get(1);
	%>

	<form action="<portlet:renderURL><portlet:param name="view" value="compare_versions" /></portlet:renderURL>" method="post" name="<portlet:namespace />fmCompare" onSubmit="<portlet:namespace />compare(); return false;">
	<input name="<portlet:namespace />backURL" type="hidden" value="<%= HtmlUtil.escape(currentURL) %>" />
	<input name="<portlet:namespace />resourcePrimKey" type="hidden" value="<%= article.getResourcePrimKey() %>" />
	<input name="<portlet:namespace />sourceVersion" type="hidden" value="<%= latestArticle.getVersion() %>" />
	<input name="<portlet:namespace />targetVersion" type="hidden" value="<%= article.getVersion() %>" />

	<input type="submit" value="<liferay-ui:message key="compare-versions" />" />

	</form>

	<br />
</c:if>

<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" paginate="true" />

<c:if test='<%= type.equals("all_articles") %>'>
	<div class="subscriptions">
		<liferay-ui:icon-list>
			<c:if test='<%= KBPermission.contains(permissionChecker, plid, ActionKeys.SUBSCRIBE) %>'>
				<c:choose>
					<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), KBArticle.class.getName(), portletGroupId) %>">
						<portlet:actionURL var="unsubscribeURL">
							<portlet:param name="actionName" value="<%= Constants.UNSUBSCRIBE %>" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
						</portlet:actionURL>

						<liferay-ui:icon image="unsubscribe" url="<%= unsubscribeURL %>" />
					</c:when>
					<c:otherwise>
						<portlet:actionURL var="subscribeURL">
							<portlet:param name="actionName" value="<%= Constants.SUBSCRIBE %>" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
						</portlet:actionURL>

						<liferay-ui:icon image="subscribe" url="<%= subscribeURL %>" />
					</c:otherwise>
				</c:choose>
			</c:if>

			<%
			String[] displayRSSTypes = prefs.getValues("displayRSSTypes", new String[] {rss20});

			rssAtomURL.setParameter("groupId", String.valueOf(themeDisplay.getPortletGroupId()));
			rssRSS10URL.setParameter("groupId", String.valueOf(themeDisplay.getPortletGroupId()));
			rssRSS20URL.setParameter("groupId", String.valueOf(themeDisplay.getPortletGroupId()));
			%>

			<c:if test="<%= ArrayUtil.contains(displayRSSTypes, atom) %>">
				<liferay-ui:icon image="rss" message="<%= atom %>" method="get" url='<%= rssAtomURL.toString() %>' target="_blank" label="<%= true %>" />
			</c:if>

			<c:if test="<%= ArrayUtil.contains(displayRSSTypes, rss10) %>">
				<liferay-ui:icon image="rss" message="<%= rss10 %>" method="get" url='<%= rssRSS10URL.toString() %>' target="_blank" label="<%= true %>" />
			</c:if>

			<c:if test="<%= ArrayUtil.contains(displayRSSTypes, rss20) %>">
				<liferay-ui:icon image="rss" message="<%= rss20 %>" method="get" url='<%= rssRSS20URL.toString() %>' target="_blank" label="<%= true %>" />
			</c:if>
		</liferay-ui:icon-list>
	</div>
</c:if>