<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

<c:choose>
	<c:when test="<%= Validator.isNotNull(license) %>">

		<%
		String args = ParamUtil.getString(request, "args");
		int start = (ParamUtil.getInteger(request, "start", 1) - 1) * 10;

		GoogleSearch googleSearch = new GoogleSearch();

		googleSearch.setKey(license);
		googleSearch.setQueryString(args);
		googleSearch.setStartResult(start);
		googleSearch.setSafeSearch(safeSearch);

		GoogleSearchResult searchResult = googleSearch.doSearch();

		NumberFormat doubleFormat = NumberFormat.getInstance();

		doubleFormat.setMaximumFractionDigits(2);

		NumberFormat integerFormat = NumberFormat.getInstance(locale);

		integerFormat.setMaximumFractionDigits(0);
		%>

		<form method="post" name="<portlet:namespace />fm"
			onSubmit="
				if (document.<portlet:namespace />fm.<portlet:namespace />directive.selectedIndex == 0) {
					submitForm(this, '<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="jspPage" value="/search.jsp" /></portlet:renderURL>');
				}
				else {
					submitForm(this, '<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="jspPage" value="/spell.jsp" /></portlet:renderURL>');
				}

				return false;"
		>

		<input name="<portlet:namespace />args" size="30" type="text" value="<%= searchResult.getSearchQuery() %>" />

		<select name="<portlet:namespace />directive">
			<option selected value="search"><liferay-ui:message key="search" /></option>
			<option value="spell"><liferay-ui:message key="spell" /></option>
		</select>

		<input align="absmiddle" border="0" src="<%= themeDisplay.getPathThemeImages() %>/common/search.png" title="<liferay-ui:message key="search" />" type="image" />

		</form>

		<c:if test="<%= renderRequest.getWindowState().equals(WindowState.MAXIMIZED) %>">
			<script type="text/javascript">
				Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />args);
			</script>
		</c:if>

		<br />

		<div class="portlet-msg-info">
			<c:choose>
				<c:when test='<%= searchResult.getSearchQuery().startsWith("related:") %>'>
					<liferay-ui:message key="searched-for-pages-similar-to" /> <b><%= searchResult.getSearchQuery().substring(8, searchResult.getSearchQuery().length()) %></b>.
				</c:when>
				<c:otherwise>
					<liferay-ui:message key="searched-the-web-for" /> <b><%= searchResult.getSearchQuery() %></b>.
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="<%= searchResult.getEstimateIsExact() %>">
					<%= LanguageUtil.format(pageContext, "results-of", new Object[] {"<b>" + searchResult.getStartIndex() + "</b> - <b>" + searchResult.getEndIndex() + "</b>", "<b>" + integerFormat.format(searchResult.getEstimatedTotalResultsCount()) + "</b>"}, false) %>
				</c:when>
				<c:otherwise>
					<%= LanguageUtil.format(pageContext, "results-of-about", new Object[] {"<b>" + searchResult.getStartIndex() + "</b> - <b>" + searchResult.getEndIndex() + "</b>", "<b>" + integerFormat.format(searchResult.getEstimatedTotalResultsCount()) + "</b>"}, false) %>
				</c:otherwise>
			</c:choose>

			<%= LanguageUtil.format(pageContext, "search-took-x-seconds", new LanguageWrapper("<b>", doubleFormat.format(searchResult.getSearchTime()), "</b>"), false) %>
		</div>

		<%
		GoogleSearchResultElement[] resultElements = searchResult.getResultElements();

		for (int i = 0; i < resultElements.length; i++) {
			GoogleSearchResultElement resultElement = resultElements[i];
		%>

			<div>
				<a href="<%= resultElement.getURL() %>" target="_blank"><%= resultElement.getTitle() %></a><br />

				<%= resultElement.getSnippet() %><br />

				<c:if test="<%= Validator.isNotNull(resultElement.getSummary()) %>">
					<liferay-ui:message key="description" />: <%= resultElement.getSummary() %><br />
				</c:if>

				<%
				String categoryName = resultElement.getDirectoryCategory().getFullViewableName();
				%>

				<c:if test="<%= Validator.isNotNull(categoryName) %>">
					<liferay-ui:message key="category" />: <a href="http://directory.google.com/<%= categoryName %>" target="_blank"><%= StringUtil.replace(categoryName.substring(4, categoryName.length()), "/", " &gt; ") %></a><br />
				</c:if>

				<%= resultElement.getURL() %> - <%= resultElement.getCachedSize() %> - <a href="<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="jspPage" value="/cached.jsp" /><portlet:param name="args" value="<%= StringUtil.replace(resultElement.getURL(), "http://", StringPool.BLANK) %>" /></portlet:renderURL>" target="_blank"><liferay-ui:message key="cached" /></a>

				<c:if test="<%= resultElement.getRelatedInformationPresent() %>">

					<%
					PortletURL portletURL = renderResponse.createRenderURL();

					portletURL.setParameter("jspPage", "/search.jsp");
					portletURL.setParameter("args", StringUtil.replace(resultElement.getURL(), "http://", "related:"));
					%>

					- <a href="<%= portletURL.toString() %>"><liferay-ui:message key="similar-pages" /></a>
				</c:if>
			</div>

			<c:if test="<%= i + 1 < resultElements.length %>">
				<br />
			</c:if>

		<%
		}
		%>

		<br />

		<%
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("jspPage", "/search.jsp");
		portletURL.setParameter("args", HttpUtil.encodeURL(searchResult.getSearchQuery()));

		// Temporary fix to limit total results until page-iterator is fixed

		int totalResultsCount = searchResult.getEstimatedTotalResultsCount();

		if (totalResultsCount > 1000) {
			totalResultsCount = 1000;
		}
		%>

		<liferay-ui:page-iterator curParam='<%= renderResponse.getNamespace() + "start" %>' curValue="<%= ParamUtil.getInteger(request, \"start\", 1) %>" delta="<%= 10 %>" maxPages="<%= 25 %>" total="<%= totalResultsCount %>" url="<%= HttpUtil.decodeURL(portletURL.toString()) %>" />
	</c:when>
	<c:otherwise>
		<liferay-ui:message key="please-contact-the-administrator-to-setup-this-portlet" />
	</c:otherwise>
</c:choose>