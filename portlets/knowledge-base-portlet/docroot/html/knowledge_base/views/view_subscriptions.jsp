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

<%@ include file="/html/knowledge_base/init.jsp" %>

<%
boolean isSubscribedPortlet = SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), KBArticle.class.getName(), portletGroupId);

int subscribedArticleCount = KBArticleLocalServiceUtil.getSubscribedArticlesCount(themeDisplay.getUserId(), portletGroupId);

// Store attribute

request.setAttribute("article_iterator.type", "subscriptions");
%>

<c:if test="<%= isSubscribedPortlet %>">

	<%
	PortletURL subscriptionsURL = renderResponse.createRenderURL();

	subscriptionsURL.setParameter("view", "view_subscriptions");

	Portlet knowledgeBasePortlet = PortletLocalServiceUtil.getPortletById(themeDisplay.getCompanyId(), KnowledgeBaseKeys.PORTLET_ID);

	List headerNames = new ArrayList();

	headerNames.add(knowledgeBasePortlet.getDisplayName());
	headerNames.add(StringPool.BLANK);

	String emptyResultsMessage = StringPool.BLANK;

	int total = 1;

	List<Portlet> results = new ArrayList<Portlet>();

	results.add(knowledgeBasePortlet);

	SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, subscriptionsURL, headerNames, emptyResultsMessage);

	searchContainer.setTotal(total);
	searchContainer.setResults(results);

	List resultRows = searchContainer.getResultRows();

	ResultRow row = new ResultRow(knowledgeBasePortlet, KnowledgeBaseKeys.PORTLET_ID, 0);

	PortletURL rowURL = renderResponse.createRenderURL();

	rowURL.setParameter("view", "view_all_articles");

	// Knowledge Base

	row.addText(LanguageUtil.get(pageContext, "all-knowledge-base-articles"), rowURL);

	// Action

	row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/knowledge_base/views/portlet_action.jsp", config.getServletContext(), request, response);

	// Add result row

	resultRows.add(row);
	%>

	<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" paginate="true" />
</c:if>

<br />

<c:if test="<%= (subscribedArticleCount > 0) || !isSubscribedPortlet %>">
	<jsp:include page="/html/knowledge_base/views/article_iterator.jsp" />
</c:if>