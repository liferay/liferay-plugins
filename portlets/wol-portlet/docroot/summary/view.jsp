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

<c:choose>
	<c:when test="<%= windowState.equals(WindowState.NORMAL) || !UserPermissionUtil.contains(permissionChecker, user2.getUserId(), ActionKeys.UPDATE) %>">
		<div class="summary-container">
			<h2>
				<%= user2.getFullName() %>
			</h2>

			<img src="<%= themeDisplay.getPathImage() %>/user_portrait?img_id=<%= user2.getPortraitId() %>&t=<%= ImageServletTokenUtil.getToken(user2.getPortraitId()) %>" />

			<c:choose>
				<c:when test="<%= SocialRequestLocalServiceUtil.hasRequest(user.getUserId(), User.class.getName(), user.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND, user2.getUserId(), SocialRequestConstants.STATUS_PENDING) %>">
					<div class="portlet-msg-info">
						<liferay-ui:message key="friend-requested" />
					</div>
				</c:when>
				<c:when test="<%= SocialRelationLocalServiceUtil.isRelatable(user.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND) %>">

					<%
					PortletURL addAsFriendURL = renderResponse.createActionURL();

					addAsFriendURL.setParameter(Constants.CMD, "add_friend");
					addAsFriendURL.setParameter("redirect", PortalUtil.getCurrentURL(request));
					%>

					<p>
						<liferay-ui:icon
							image="join"
							message="add-as-friend"
							url="<%= addAsFriendURL.toString() %>"
							label="<%= true %>"
						/>
					</p>
				</c:when>
			</c:choose>

			<p>
				<span class="user-job-title"><liferay-ui:message key="job-title" /></span>

				<%= user2.getContact().getJobTitle() %>
			</p>

			<%
			String aboutMe = HtmlUtil.escape(ExpandoValueLocalServiceUtil.getData(User.class.getName(), "WOL", "aboutMe", user2.getUserId(), StringPool.BLANK));
			%>

			<c:if test="<%= Validator.isNotNull(aboutMe) %>">
				<p>
					<span class="user-about"><liferay-ui:message key="about-me" /></span>

					<%= aboutMe %>
				</p>
			</c:if>

			<span class="user-about"><liferay-ui:message key="activity-details" /></span>

			<table class="lfr-table">
			<tr>
				<td>

					<%
					int mbMessagesCount = MBStatsUserLocalServiceUtil.getStatsUser(14, user2.getUserId()).getMessageCount();
					%>

					<liferay-ui:icon
						image="view"
						message='<%= LanguageUtil.format(pageContext, "x-forum-posts", new Object[] {"<b>" + mbMessagesCount + "</b>"}) %>'
						url='<%= themeDisplay.getPathContext() + "/web/guest/community/forums/-/message_boards/recent_posts?_19_groupThreadsUserId=" + user2.getUserId() %>'
						label="<%= true %>"
					/>
				</td>
				<td>
					<liferay-ui:icon
						image="rss"
						url='<%= themeDisplay.getPathMain() + "/message_boards/rss?p_l_id=1990&groupId=14&userId=" + user2.getUserId() %>'
					/>
				</td>
			</tr>
			<tr>
				<td>

					<%
					int blogsEntriesCount = BlogsStatsUserLocalServiceUtil.getStatsUser(group.getGroupId(), user2.getUserId()).getEntryCount();
					%>

					<liferay-ui:icon
						image="view"
						message='<%= LanguageUtil.format(pageContext, "x-blog-entries", new Object[] {"<b>" + blogsEntriesCount + "</b>"}) %>'
						url='<%= themeDisplay.getPathContext() + "/web/" + user2.getScreenName() + "/blog" %>'
						label="<%= true %>"
					/>
				</td>
				<td>
					<liferay-ui:icon
						image="rss"
						url='<%= themeDisplay.getPathContext() + "/web/" + user2.getScreenName() + "/blog/-/blogs/rss" %>'
					/>
				</td>
			</tr>
			</table>
		</div>

		<c:if test="<%= UserPermissionUtil.contains(permissionChecker, user2.getUserId(), ActionKeys.UPDATE) %>">
			<br />

			<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="editURL" />

			<liferay-ui:icon image="edit" url="<%= editURL %>" label="<%= true %>" />
		</c:if>
	</c:when>
	<c:otherwise>

		<%
		try {

			// Initializaing an Expando table only needs to happen once. In
			// general, this should be done as a startup event so it only
			// happens once. This code is left here even though that is bad
			// practice so it can be easily referenced as an example.

			ExpandoTable table = ExpandoTableLocalServiceUtil.addTable(User.class.getName(), "WOL");

			ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), "jiraUserId", ExpandoColumnConstants.STRING);
			ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), "sfUserId", ExpandoColumnConstants.STRING);
			ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), "aboutMe", ExpandoColumnConstants.STRING);
		}
		catch (Exception e) {
		}

		String jiraUserId = HtmlUtil.escape(ExpandoValueLocalServiceUtil.getData(User.class.getName(), "WOL", "jiraUserId", user2.getUserId(), StringPool.BLANK));
		String sfUserId = HtmlUtil.escape(ExpandoValueLocalServiceUtil.getData(User.class.getName(), "WOL", "sfUserId", user2.getUserId(), StringPool.BLANK));
		String aboutMe = HtmlUtil.escape(ExpandoValueLocalServiceUtil.getData(User.class.getName(), "WOL", "aboutMe", user2.getUserId(), StringPool.BLANK));
		%>

		<form action="<portlet:actionURL />" method="post" name="<portlet:namespace />fm">
		<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
		<input name="<portlet:namespace />redirect" type="hidden" value="<%= PortalUtil.getCurrentURL(request) %>" />

		<div class="portlet-msg-info">
			This form will allow you to change settings used to track your participation in the Liferay Community. Use <a href="<%= themeDisplay.getURLMyAccount() %>">My Account</a> to change regular account settings like profile picture or password.
		</div>

		<table class="lfr-table">
		<tr>
			<td>
				<liferay-ui:message key="jira-login" />
			</td>
			<td>
				<input class="lfr-input-text" name="<portlet:namespace />jiraUserId" style="width: 150px;" type="text" value="<%= jiraUserId %>" />

				<liferay-ui:icon-help message="Set your login for http://support.liferay.com to track your JIRA activity." />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="sourceforge-login" />
			</td>
			<td>
				<input class="lfr-input-text" name="<portlet:namespace />sfUserId" style="width: 150px;" type="text" value="<%= sfUserId %>" />

				<liferay-ui:icon-help message="Set your SourceForge login if you are a committer. It will enable the SVN portlet to track your commit activity." />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="job-title" />
			</td>
			<td>
				<liferay-ui:input-field model="<%= Contact.class %>" bean="<%= user2.getContact() %>" field="jobTitle" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="about-me" />
			</td>
			<td>
				<liferay-ui:input-textarea param="aboutMe" defaultValue="<%= aboutMe %>" />
			</td>
		</tr>
		</table>

		<br />

		<input type="submit" value="<liferay-ui:message key="save" />" />

		<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<portlet:renderURL windowState="<%= WindowState.NORMAL.toString() %>" />';" />

		</form>
	</c:otherwise>
</c:choose>