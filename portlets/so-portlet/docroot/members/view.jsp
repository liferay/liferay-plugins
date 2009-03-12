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

<h1><liferay-ui:message key="members" /></h1>

<liferay-ui:search-container
	delta="<%= 1000 %>"
>
	<liferay-ui:search-container-results
		results="<%= UserLocalServiceUtil.getGroupUsers(layout.getGroupId()) %>"
		total="<%= UserLocalServiceUtil.getGroupUsersCount(layout.getGroupId()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.User"
		keyProperty="userId"
		modelVar="curUser"
	>
		<liferay-ui:search-container-column-text
			buffer="buffer"
		>

			<%
			buffer.append("<a class=\"user\" href=\"javascript: ;\" data-userId=\"");
			buffer.append(curUser.getUserId());
			buffer.append("\"><img alt=\"");
			buffer.append(curUser.getFullName());
			buffer.append("\" src=\"");
			buffer.append(themeDisplay.getPathImage());
			buffer.append("/user_");
			buffer.append((curUser.isFemale() ? "female" : "male"));
			buffer.append("_portrait?img_id=");
			buffer.append(curUser.getPortraitId());
			buffer.append("&t=");
			buffer.append(ImageServletTokenUtil.getToken(curUser.getPortraitId()));
			buffer.append("\" width=\"35\" /></a>");
			%>

		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text
			buffer="buffer"
		>

			<%
			buffer.append("<a class=\"user\" href=\"javascript: ;\" data-userId=\"");
			buffer.append(curUser.getUserId());
			buffer.append("\">");
			buffer.append(curUser.getFullName());
			buffer.append("</a><br />");

			if(!curUser.getContact().getJobTitle().equals(StringPool.BLANK)) {
				buffer.append(curUser.getContact().getJobTitle());
				buffer.append("<br />");
			}
			%>

		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<%
List users = UserLocalServiceUtil.getGroupUsers(layout.getGroupId());

int invitedMembersCount = ParamUtil.getInteger(renderRequest, "invitedMembersCount");
%>

<c:if test="<%= users.contains(user) %>">
	<div class="invite-members">
		<a href="javascript: ;"><liferay-ui:message key="invite-members" /></a>
	</div>
</c:if>

<c:if test="<%= invitedMembersCount > 0 %>">
	<div class="portlet-msg-success">
		<c:choose>
			<c:when test="<%= invitedMembersCount > 1 %>">
				<%= LanguageUtil.format(pageContext, "you-have-invited-x-friends", invitedMembersCount) %>
			</c:when>
			<c:otherwise>
				<%= LanguageUtil.format(pageContext, "you-have-invited-x-friend", invitedMembersCount) %>
			</c:otherwise>
		</c:choose>
	</div>
</c:if>

<script type="text/javascript">
	jQuery(
		function() {
			Liferay.SO.Profiles.init();
		}
	);
</script>