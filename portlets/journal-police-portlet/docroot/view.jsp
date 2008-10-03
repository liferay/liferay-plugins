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

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portlet.journal.model.JournalArticle" %>
<%@ page import="com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page import="java.util.regex.Pattern" %>

<%@ page import="javax.portlet.WindowState" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
WindowState windowState = renderRequest.getWindowState();
%>

<c:choose>
	<c:when test="<%= windowState.equals(WindowState.NORMAL) %>">
		Check for articles that have been mangled by FCKEditor.

		<br /><br />

		<input onclick="location.href = '<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" />';" type="button" value="<liferay-ui:message key="submit" />" />
	</c:when>
	<c:otherwise>

		<%
		Pattern pattern = Pattern.compile("(\\d+[.]\\d+)");

		List<JournalArticle> allArticles = JournalArticleLocalServiceUtil.getArticles(themeDisplay.getScopeGroupId());

		List<JournalArticle> badArticles = new ArrayList<JournalArticle>(allArticles.size());

		for (JournalArticle article : allArticles) {
			String content = GetterUtil.getString(article.getContent());

			Matcher matcher = pattern.matcher(content);

			if (matcher.find()) {
				badArticles.add(article);
			}
		}
		%>

		There are <%= badArticles.size() %> bad articles.

		<c:if test="<%= !badArticles.isEmpty() %>">
			<br /><br />

			<%
			for (JournalArticle article : badArticles) {
			%>

				<%= article.getId() %><br />

			<%
			}
			%>

		</c:if>
	</c:otherwise>
</c:choose>