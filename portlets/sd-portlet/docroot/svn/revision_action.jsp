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

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Object[] objArray = (Object[])row.getObject();

SVNRevision svnRevision = (SVNRevision)objArray[0];
String rowHREF = (String)objArray[1];
JIRAIssue jiraIssue = (JIRAIssue)objArray[2];
%>

<div class="revision-actions">
	<liferay-ui:icon-list>

		<%
		String taglibSVNURL = "javascript: location.href = '" + rowHREF + "';";
		%>

		<liferay-ui:icon
			message="SVN"
			src='<%= request.getContextPath() + "/svn/icon.png" %>'
			url="<%= taglibSVNURL %>"
			target="_blank"
			label="<%= true %>"
		/>

		<c:if test="<%= jiraIssue != null %>">

			<%
			String taglibJIRAURL = "javascript: location.href = 'http://support.liferay.com/browse/" + jiraIssue.getKey() + "';";
			%>

			<liferay-ui:icon
				message="JIRA"
				src='<%= request.getContextPath() + "/jira/icon.png" %>'
				url="<%= taglibJIRAURL %>"
				target="_blank"
				label="<%= true %>"
			/>
		</c:if>
	</liferay-ui:icon-list>
</div>