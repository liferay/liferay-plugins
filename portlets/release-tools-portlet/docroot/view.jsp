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

<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.util.HttpUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ReleaseInfo" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>

<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.TreeSet" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page import="java.util.regex.Pattern" %>

<portlet:defineObjects />

<%
String url = ParamUtil.getString(request, "url");
//url = "http://sourceforge.net/project/showfiles.php?group_id=49260&package_id=243192&release_id=571184";

String projectName = StringPool.BLANK;

String content = null;

if (Validator.isNotNull(url)) {
	content = HttpUtil.URLtoString(url);

	Pattern pattern = Pattern.compile("\"project\": \"(\\S+)\"");

	Matcher matcher = pattern.matcher(content);

	if (matcher.find()) {
		projectName = matcher.group(1);
	}
}
%>

Please enter a SourceForge URL that points to a specific release.

<br /><br />

An example URL is: <em>http://sourceforge.net/project/showfiles.php?group_id=49260&package_id=243192&release_id=571184</em>

<br /><br />

<form action="<portlet:renderURL />" method="post" name="<portlet:namespace />fm">

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="url" />:
	</td>
	<td>
		<input name="<portlet:namespace />url" size="120" type="text" value="<%= url %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="project" />:
	</td>
	<td>
		<%= projectName %>
	</td>
</tr>
</table>

<br />

<input type="submit" value="<liferay-ui:message key="refresh" />" />

</form>

<c:if test="<%= Validator.isNotNull(url) %>">
	<br />

	<div class="portlet-msg-info">
		Below are a list of plugins that are on SourceForge's download page.
	</div>

	<%
	Set sfPlugins = new TreeSet();

	Pattern pattern = Pattern.compile("<tr title=\"(\\S+)\" class=\"file \">");

	Matcher matcher = pattern.matcher(content);

	while (matcher.find()) {
		String fileName = matcher.group(1);

		sfPlugins.add(fileName);
	%>

		<%= fileName %><br />

	<%
	}
	%>

	<br />

	<div class="portlet-msg-info">
		Below are a list of plugins that are in SVN but not on SourceForge's download page.
	</div>

	<%
	String svnTrunk = "http://" + projectName + ".svn.sourceforge.net/svnroot/" + projectName + "/plugins/trunk";

	Set svnPlugins = new TreeSet();

	svnPlugins.addAll(_getSvnPlugins(svnTrunk + "/layouttpl"));
	svnPlugins.addAll(_getSvnPlugins(svnTrunk + "/portlets"));
	svnPlugins.addAll(_getSvnPlugins(svnTrunk + "/themes"));

	Iterator itr = svnPlugins.iterator();

	while (itr.hasNext()) {
		String svnPlugin = (String)itr.next();

		if (!sfPlugins.contains(svnPlugin)) {
	%>

			<%= svnPlugin %><br />

	<%
		}
	}
	%>

	<br />

	<div class="portlet-msg-info">
		Below are a list of plugins that are SourceForge's download page but not in SVN.
	</div>

	<%
	itr = sfPlugins.iterator();

	while (itr.hasNext()) {
		String sfPlugin = (String)itr.next();

		if (!svnPlugins.contains(sfPlugin)) {
	%>

			<%= sfPlugin %><br />

	<%
		}
	}
	%>

</c:if>

<%!
private static Set _getSvnPlugins(String url) throws Exception {
	Set svnPlugins = new TreeSet();

	String content = HttpUtil.URLtoString(url);

	int x = content.indexOf("</li>");
	int y = content.lastIndexOf("</li>");

	content = content.substring(x, y);

	x = 0;
	y = 0;

	while (true) {
		x = content.indexOf("/\">", x);
		y = content.indexOf("/</a>", x);

		if ((x == -1) || (y == -1)) {
			break;
		}

		x = x + 3;

		String fileName = content.substring(x, y) + "-" + ReleaseInfo.getVersion() + ".1.war";

		svnPlugins.add(fileName);
	}

	return svnPlugins;
}
%>