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
<%@ include file="/init.jsp" %>

<%
WikiNode node = (WikiNode)request.getAttribute(KnowledgeBaseKeys.WIKI_NODE);
String type = (String)request.getAttribute("page_iterator.type");

String tag = ParamUtil.getString(request, "tag");

PortletURL portletURL = renderResponse.createRenderURL();

if (type.equals("all_pages")) {
	portletURL.setParameter(Constants.CMD, "view_all_pages");
}
else if (type.equals("tagged_pages")) {
	portletURL.setParameter(Constants.CMD, "view_tagged_pages");
	portletURL.setParameter("tag", tag);
}

List headerNames = new ArrayList();

headerNames.add("id");
headerNames.add("title");
headerNames.add("author");
headerNames.add("views");
headerNames.add("date");
headerNames.add("");

String emptyResultsMessage = null;

if (type.equals("all_pages")) {
	emptyResultsMessage = "there-are-no-knowledge-base-articles";
}
else if (type.equals("tagged_pages")) {
	emptyResultsMessage = "there-are-no-pages-with-this-tag";
}

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, headerNames, emptyResultsMessage);

int total = 0;
List<WikiPage> results = null;

if (type.equals("all_pages")) {
	total = WikiPageLocalServiceUtil.getPagesCount(node.getNodeId(), true);
	results = WikiPageLocalServiceUtil.getPages(node.getNodeId(), true, searchContainer.getStart(), searchContainer.getEnd());
}
else if (type.equals("tagged_pages")) {
	long classNameId = PortalUtil.getClassNameId(WikiPage.class.getName());
	long[] entryIds = TagsEntryLocalServiceUtil.getEntryIds(company.getCompanyId(), new String[] {tag});
	long[] notEntryIds = new long[0];
	Date now = new Date();

	total = TagsAssetLocalServiceUtil.getAssetsCount(portletGroupId.longValue(), new long[] {classNameId}, entryIds, notEntryIds, false, false, now, now);
	List<TagsAsset> assets = TagsAssetLocalServiceUtil.getAssets(portletGroupId.longValue(), new long[] {classNameId}, entryIds, notEntryIds, false, null, null, null, null, false, now, now, searchContainer.getStart(), searchContainer.getEnd());

	results = new ArrayList();

	for (TagsAsset asset : assets) {
		WikiPageResource pageResource = WikiPageResourceLocalServiceUtil.getPageResource(asset.getClassPK());

		WikiPage assetPage = WikiPageLocalServiceUtil.getPage(pageResource.getNodeId(), pageResource.getTitle());

		results.add(assetPage);
	}
}
else {
	results = Collections.EMPTY_LIST;
}

searchContainer.setTotal(total);
searchContainer.setResults(results);

List resultRows = searchContainer.getResultRows();

for (int i = 0; i < results.size(); i++) {
	WikiPage curWikiPage = (WikiPage)results.get(i);

	ResultRow row = new ResultRow(curWikiPage, String.valueOf(curWikiPage.getVersion()), i);

	PortletURL rowURL = renderResponse.createRenderURL();

	rowURL.setParameter(Constants.CMD, "view_page");
	rowURL.setParameter("title", curWikiPage.getTitle());

	// Id

	row.addText(String.valueOf(curWikiPage.getResourcePrimKey()), rowURL);

	// Title

	row.addText(curWikiPage.getTitle(), rowURL);

	// Author

	row.addText(PortalUtil.getUserName(curWikiPage.getUserId(), curWikiPage.getUserName()), rowURL);

	// Views

	TagsAsset asset = TagsAssetLocalServiceUtil.getAsset(WikiPage.class.getName(), curWikiPage.getResourcePrimKey());

	row.addText(String.valueOf(asset.getViewCount()), rowURL);

	// Date

	row.addText(dateFormatDateTime.format(curWikiPage.getCreateDate()), rowURL);

	// Action

	// row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/views/page_action.jsp");

	// Add result row

	resultRows.add(row);
}
%>

<div>
	<input type="button" value="<liferay-ui:message key="add-article" />" onClick="location.href = '<portlet:renderURL><portlet:param name="<%= Constants.CMD %>" value="edit_page" /><portlet:param name="nodeId" value="<%= String.valueOf(node.getNodeId()) %>" /><portlet:param name="redirect" value="<%= currentURL %>"></portlet:param></portlet:renderURL>'" />
</div>

<br />

<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" paginate="true" />

<liferay-ui:icon-list>
	<liferay-ui:icon image="rss" message="Atom 1.0" url='<%= themeDisplay.getPathMain() + "/wiki/rss?p_l_id=" + plid + "&nodeId=" + node.getNodeId() + rssURLAtomParams %>' target="_blank" label="<%= true %>" />

	<liferay-ui:icon image="rss" message="RSS 1.0" url='<%= themeDisplay.getPathMain() + "/wiki/rss?p_l_id=" + plid + "&nodeId=" + node.getNodeId() + rssURLRSS10Params %>' target="_blank" label="<%= true %>" />

	<liferay-ui:icon image="rss" message="RSS 2.0" url='<%= themeDisplay.getPathMain() + "/wiki/rss?p_l_id=" + plid + "&nodeId=" + node.getNodeId() + rssURLRSS20Params %>' target="_blank" label="<%= true %>" />

	<c:choose>
		<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), WikiNode.class.getName(), node.getNodeId()) %>">
			<liferay-portlet:actionURL portletName="<%= PortletKeys.WIKI %>" windowState="<%= WindowState.MAXIMIZED.toString() %>" var="unsubscribeURL">
				<portlet:param name="struts_action" value="/wiki/edit_node" />
				<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UNSUBSCRIBE %>" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="nodeId" value="<%= String.valueOf(node.getNodeId()) %>" />
			</liferay-portlet:actionURL>

			<liferay-ui:icon image="unsubscribe" url="<%= unsubscribeURL %>" />
		</c:when>
		<c:otherwise>
			<liferay-portlet:actionURL portletName="<%= PortletKeys.WIKI %>" windowState="<%= WindowState.MAXIMIZED.toString() %>" var="subscribeURL">
				<portlet:param name="struts_action" value="/wiki/edit_node" />
				<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.SUBSCRIBE %>" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="nodeId" value="<%= String.valueOf(node.getNodeId()) %>" />
			</liferay-portlet:actionURL>

			<liferay-ui:icon image="subscribe" url="<%= subscribeURL %>" />
		</c:otherwise>
	</c:choose>
</liferay-ui:icon-list>
