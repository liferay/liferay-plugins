<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/activities/init.jsp" %>

<%
Group group = themeDisplay.getScopeGroup();

List<SocialActivitySet> results = null;
int total = 0;

int start = ParamUtil.getInteger(request, "start");
int end = start + _DELTA;

int[] startAndEnd = null;
%>

<c:choose>
	<c:when test="<%= group.isUser() %>">

		<%
		if (!layout.isPublicLayout()) {
			if (tabs1.equals("connections")) {
				total = SocialActivitySetLocalServiceUtil.getRelationActivitySetsCount(group.getClassPK(), SocialRelationConstants.TYPE_BI_CONNECTION);

				startAndEnd = SearchPaginationUtil.calculateStartAndEnd(start, end, total);

				results = SocialActivitySetLocalServiceUtil.getRelationActivitySets(group.getClassPK(), SocialRelationConstants.TYPE_BI_CONNECTION, startAndEnd[0], startAndEnd[1]);
			}
			else if (tabs1.equals("following")) {
				total = SocialActivitySetLocalServiceUtil.getRelationActivitySetsCount(group.getClassPK(), SocialRelationConstants.TYPE_UNI_FOLLOWER);

				startAndEnd = SearchPaginationUtil.calculateStartAndEnd(start, end, total);

				results = SocialActivitySetLocalServiceUtil.getRelationActivitySets(group.getClassPK(), SocialRelationConstants.TYPE_UNI_FOLLOWER, startAndEnd[0], startAndEnd[1]);
			}
			else if (tabs1.equals("me")) {
				total = SocialActivitySetLocalServiceUtil.getUserActivitySetsCount(group.getClassPK());

				startAndEnd = SearchPaginationUtil.calculateStartAndEnd(start, end, total);

				results = SocialActivitySetLocalServiceUtil.getUserActivitySets(group.getClassPK(), startAndEnd[0], startAndEnd[1]);
			}
			else if (tabs1.equals("my-sites")) {
				total = SocialActivitySetLocalServiceUtil.getUserGroupsActivitySetsCount(group.getClassPK());

				startAndEnd = SearchPaginationUtil.calculateStartAndEnd(start, end, total);

				results = SocialActivitySetLocalServiceUtil.getUserGroupsActivitySets(group.getClassPK(), startAndEnd[0], startAndEnd[1]);
			}
			else {
				total = SocialActivitySetLocalServiceUtil.getUserViewableActivitySetsCount(group.getClassPK());

				startAndEnd = SearchPaginationUtil.calculateStartAndEnd(start, end, total);

				results = SocialActivitySetLocalServiceUtil.getUserViewableActivitySets(group.getClassPK(), startAndEnd[0], startAndEnd[1]);
			}
		}
		else {
			total = SocialActivitySetLocalServiceUtil.getUserActivitySetsCount(group.getClassPK());

			startAndEnd = SearchPaginationUtil.calculateStartAndEnd(start, end, total);

			results = SocialActivitySetLocalServiceUtil.getUserActivitySets(group.getClassPK(), startAndEnd[0], startAndEnd[1]);
		}
		%>

	</c:when>
	<c:otherwise>

		<%
		total = SocialActivitySetLocalServiceUtil.getGroupActivitySetsCount(group.getGroupId());

		startAndEnd = SearchPaginationUtil.calculateStartAndEnd(start, end, total);

		results = SocialActivitySetLocalServiceUtil.getGroupActivitySets(group.getGroupId(), startAndEnd[0], startAndEnd[1]);
		%>

	</c:otherwise>
</c:choose>

<%@ include file="/activities/view_activity_sets_feed.jspf" %>

<c:if test="<%= (results.isEmpty()) %>">
	<div class="no-activities">
		<c:choose>
			<c:when test="<%= total == 0 %>">
				<liferay-ui:message key="there-are-no-activities" />
			</c:when>
			<c:otherwise>
				<liferay-ui:message key="there-are-no-more-activities" />
			</c:otherwise>
		</c:choose>
	</div>
</c:if>