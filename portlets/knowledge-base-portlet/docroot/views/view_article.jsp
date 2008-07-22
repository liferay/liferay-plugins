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
KBArticle article = (KBArticle)request.getAttribute(KnowledgeBaseKeys.ARTICLE);

String title = article.getTitle();

boolean print = ParamUtil.getBoolean(request, Constants.PRINT);

TagsAssetLocalServiceUtil.incrementViewCounter(KBArticle.class.getName(), article.getResourcePrimKey());

PortletURL viewAllURL = renderResponse.createRenderURL();

PortletURL viewArticleURL = renderResponse.createRenderURL();

viewArticleURL.setParameter(Constants.CMD, "view_article");
viewArticleURL.setParameter("title", title);

PortletURL addArticleURL = renderResponse.createRenderURL();

addArticleURL.setParameter(Constants.CMD, "edit_article");
addArticleURL.setParameter("redirect", currentURL);
addArticleURL.setParameter("editTitle", "1");

if (article != null) {
	addArticleURL.setParameter("parentTitle", article.getTitle());
}

List childArticles = article.getChildArticles();

PortletURL editArticleURL = renderResponse.createRenderURL();

editArticleURL.setParameter(Constants.CMD, "edit_article");
editArticleURL.setParameter("redirect", currentURL);
editArticleURL.setParameter("title", title);

PortletURL printArticleURL = renderResponse.createRenderURL();

printArticleURL.setParameter(Constants.CMD, "view_article");
printArticleURL.setParameter("title", title);
printArticleURL.setWindowState(LiferayWindowState.POP_UP);

printArticleURL.setParameter("print", "true");

PortletURL taggedArticlesURL = renderResponse.createRenderURL();

taggedArticlesURL.setParameter(Constants.CMD, "view_tagged_articles");
%>

<c:choose>
	<c:when test="<%= print %>">
		<script type="text/javascript">
			print();
		</script>

		<div class="popup-print">
			<liferay-ui:icon image="print" message="print" url="javascript: print();" />
		</div>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			function <portlet:namespace />printArticle() {
				window.open('<%= printArticleURL %>', '', "directories=0,height=480,left=80,location=1,menubar=1,resizable=1,scrollbars=yes,status=0,toolbar=0,top=180,width=640");
			}
		</script>
	</c:otherwise>
</c:choose>

<c:if test="<%= Validator.isNotNull(article.getParentTitle()) %>">
	<div class="breadcrumbs">

		<%
		PortletURL viewParentArticleURL = renderResponse.createRenderURL();

		viewParentArticleURL.setParameter(Constants.CMD, "view_article");

		List parentArticles = article.getParentArticles();

		for (int i = 0; i < parentArticles.size(); i++) {
			KBArticle curParentArticle = (KBArticle)parentArticles.get(i);

			viewParentArticleURL.setParameter("title", curParentArticle.getTitle());
		%>

			<a href="<%= viewParentArticleURL %>"><%= curParentArticle.getTitle() %></a>

			<c:if test="<%= (i + 1) < parentArticles.size() %>">
				&raquo;
			</c:if>

		<%
		}
		%>

	</div>
</c:if>

<h1 class="article-title">
	<%= title %>
</h1>

<div>
	<c:if test="<%= !print %>">
		<div class="side-boxes">
			<div class="side-box">
				<div class="side-box-title"><liferay-ui:message key="details" /></div>
				<div class="side-box-content">
					<ul class="lfr-component">
						<li>
							<b><liferay-ui:message key="id" /></b>: <%= article.getResourcePrimKey() %>
						</li>
						<li>
							<b><liferay-ui:message key="version" /></b>: <%= article.getVersion() %>
						</li>
						<li>
							<b><liferay-ui:message key="updated" /></b>: <%= dateFormatDateTime.format(article.getModifiedDate()) %>
						</li>
						<li>
							<b><liferay-ui:message key="by" /></b>: <%= PortalUtil.getUserName(article.getUserId(), article.getUserName()) %>
						</li>
						<li>
							<b><liferay-ui:message key="views" /></b>:
							<%
							TagsAsset asset = TagsAssetLocalServiceUtil.getAsset(KBArticle.class.getName(), article.getResourcePrimKey());
							%>
							<%= asset.getViewCount() %>
						</li>
						<li>
							<liferay-ui:tags-summary
								className="<%= KBArticle.class.getName() %>"
								classPK="<%= article.getResourcePrimKey() %>"
								portletURL="<%= taggedArticlesURL %>"
							/>
						</li>

				</div>
			</div>
			<div class="side-box">
				<div class="side-box-title"><liferay-ui:message key="attachments" /></div>
				<div class="side-box-content">
					0 <liferay-ui:message key="attachments" />
				</div>
			</div>
			<div class="side-box">
				<div class="side-box-title"><liferay-ui:message key="actions" /></div>
				<div class="side-box-content">
					<liferay-ui:icon-list>
						<liferay-ui:icon image="edit" label="<%= true %>" url="<%= editArticleURL.toString() %>" />
						<liferay-ui:icon image="print" label="<%= true %>" message="print" url='<%= "javascript: " + renderResponse.getNamespace() + "printArticle();" %>' />
						<%--
						<liferay-ui:icon image="rss" message="Atom 1.0" url='<%= themeDisplay.getPathMain() + "/kb/rss?p_l_id=" + plid + "&companyId=" + company.getCompanyId() + "&nodeId=" + article.getNodeId() + "&title=" + article.getTitle() + rssURLAtomParams %>' target="_blank" label="<%= true %>" />
						<liferay-ui:icon image="rss" message="RSS 1.0" url='<%= themeDisplay.getPathMain() + "/kb/rss?p_l_id=" + plid + "&companyId=" + company.getCompanyId() + "&nodeId=" + article.getNodeId() + "&title=" + article.getTitle() + rssURLRSS10Params %>' target="_blank" label="<%= true %>" />
						<liferay-ui:icon image="rss" message="RSS 2.0" url='<%= themeDisplay.getPathMain() + "/kb/rss?p_l_id=" + plid + "&companyId=" + company.getCompanyId() + "&nodeId=" + article.getNodeId() + "&title=" + article.getTitle() + rssURLRSS20Params %>' target="_blank" label="<%= true %>" />
						--%>
						<c:choose>
							<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), KBArticle.class.getName(), article.getResourcePrimKey()) %>">
								<portlet:actionURL var="unsubscribeURL">
									<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UNSUBSCRIBE + KnowledgeBaseKeys.ARTICLE %>" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="title" value="<%= String.valueOf(article.getTitle()) %>" />
								</portlet:actionURL>

								<liferay-ui:icon image="unsubscribe" url="<%= unsubscribeURL %>" label="<%= true %>" />
							</c:when>
							<c:otherwise>
								<portlet:actionURL var="subscribeURL">
									<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.SUBSCRIBE + KnowledgeBaseKeys.ARTICLE %>" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="title" value="<%= String.valueOf(article.getTitle()) %>" />
								</portlet:actionURL>

								<liferay-ui:icon image="subscribe" url="<%= subscribeURL %>" label="<%= true %>" />
							</c:otherwise>
						</c:choose>
						<%
						PortletURL deleteArticleURL = renderResponse.createActionURL();

						deleteArticleURL.setParameter(Constants.CMD, Constants.DELETE);
						deleteArticleURL.setParameter("title", article.getTitle());
						deleteArticleURL.setParameter("redirect", viewAllURL.toString());
						%>

						<liferay-ui:icon-delete url="<%= deleteArticleURL.toString() %>" label="<%= true %>" />
					</liferay-ui:icon-list>
				</div>
			</div>
		</div>
	</c:if>

	<div class="knowledge-base-body">
		<%= article.getContent() %>
	</div>
</div>

<c:if test="<%= (childArticles.size() > 0) %>">
	<div class="separator"><!-- --></div>
</c:if>

<c:if test="<%= childArticles.size() > 0 %>">
	<div class="child-articles">
		<h3><liferay-ui:message key="children" /></h3>

		<ul class="child-articles">

			<%
			PortletURL curArticleURL = renderResponse.createRenderURL();
			curArticleURL.setParameter(Constants.CMD, "view_article");

			for (int i = 0; i < childArticles.size(); i++) {
				KBArticle curArticle = (KBArticle)childArticles.get(i);

				curArticleURL.setParameter("title", curArticle.getTitle());
			%>

				<li>
					<a href="<%= curArticleURL %>"><%= curArticle.getTitle() %></a>
				</li>

			<%
			}
			%>

		</ul>
	</div>
</c:if>

<c:if test="<%= !print %>">
	<div class="article-actions">
		<liferay-ui:icon image="add_article" message="add-child-article" url="<%= addArticleURL.toString() %>" label="<%= true %>" />
	</div>
</c:if>