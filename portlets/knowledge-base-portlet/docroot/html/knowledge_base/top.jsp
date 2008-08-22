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
boolean print = ParamUtil.getBoolean(request, Constants.PRINT);

String view = ParamUtil.getString(request, "view", "view_all_articles");
%>

<c:if test="<%= !print %>">
	<%
	String keywords = ParamUtil.getString(request, "keywords");

	// Portlet URLs

	PortletURL articlesURL = renderResponse.createRenderURL();

	PortletURL templatesURL = renderResponse.createRenderURL();

	templatesURL.setParameter("view", "view_templates");

	PortletURL subscriptionsURL = renderResponse.createRenderURL();

	subscriptionsURL.setParameter("view", "view_subscriptions");
	%>

	<div class="top-links">
		<table class="lfr-table">
		<tr>
			<td align="right" valign="top">
				<liferay-portlet:renderURL varImpl="searchURL"><portlet:param name="view" value="search" /></liferay-portlet:renderURL>

				<form action="<%= searchURL %>" method="get" name="<portlet:namespace />fmSearch">
				<liferay-portlet:renderURLParams varImpl="searchURL" />
				<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(currentURL) %>" />

				<a
					href="<%= articlesURL.toString() %>"
					<c:if test='<%= view.equals("view_all_articles") && themeDisplay.isSignedIn() %>'>
						class="selected-top-link"
					</c:if>
				>
					<liferay-ui:message key="all-articles" />
				</a>

				<c:if test="<%= KBPermission.contains(permissionChecker, plid, KnowledgeBaseKeys.MANAGE_TEMPLATES) %>">
					&nbsp;|&nbsp;

					<a
						href="<%= templatesURL.toString() %>"
						<c:if test='<%= view.equals("view_templates") %>'>
							class="selected-top-link"
						</c:if>
					>
						<liferay-ui:message key="templates" />
					</a>
				</c:if>

				<c:if test='<%= themeDisplay.isSignedIn() %>'>
					&nbsp;|&nbsp;

					<a
						href="<%= subscriptionsURL.toString() %>"
						<c:if test='<%= view.equals("view_subscriptions") %>'>
							class="selected-top-link"
						</c:if>
					>
						<liferay-ui:message key="my-subscriptions" />
					</a>
				</c:if>

				<span class="search-button-holder">
					<input name="<portlet:namespace />keywords" size="30" type="text" value="<%= HtmlUtil.escape(keywords) %>" />

					<input type="submit" value="<liferay-ui:message key="search" />" />
				</span>

				</form>
			</td>
		</tr>
		</table>
	</div>
</c:if>