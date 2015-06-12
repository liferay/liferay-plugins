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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.journal.model.JournalArticle" %>
<%@ page import="com.liferay.journal.service.JournalArticleLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>

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
		Pattern pattern = Pattern.compile("<p[^>]{0,}>\\s{0,}</p>|<p[^>]{0,}>\\s{0,}&nbsp;\\s{0,}</p>");

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

				<%= article.getId() %> - <%= article.getTitle() %><br />

			<%
			}
			%>

		</c:if>
	</c:otherwise>
</c:choose>