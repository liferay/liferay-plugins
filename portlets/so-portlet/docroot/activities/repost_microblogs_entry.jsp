<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
long microblogsEntryId = ParamUtil.getLong(request, "microblogsEntryId");

MicroblogsEntry microblogsEntry = MicroblogsEntryLocalServiceUtil.fetchMicroblogsEntry(microblogsEntryId);

Format dateFormat = FastDateFormatFactoryUtil.getDate(DateFormat.FULL, themeDisplay.getLocale(), themeDisplay.getTimeZone());

Date modifiedDate = microblogsEntry.getModifiedDate();
%>

<div class="repost-header">
	<span><liferay-ui:message key="do-you-want-to-repost-this-entry" /></span>
</div>

<c:choose>
	<c:when test="<%= microblogsEntry == null %>">
		<div class="portlet-msg-error">
			<liferay-ui:message key="entry-could-not-be-found" />
		</div>
	</c:when>
	<c:otherwise>
		<div class="so-portlet-activities">
			<div class="activity-item clearfix microblogs-entry">

				<%
				User receiverUser = UserLocalServiceUtil.getUserById(microblogsEntry.getUserId());
				%>

				<div class="user-portrait">
					<span class="avatar">
						<a href="<%= receiverUser.getDisplayURL(themeDisplay) %>"><img alt="<%= HtmlUtil.escapeAttribute(receiverUser.getFullName()) %>" src="<%= receiverUser.getPortraitURL(themeDisplay) %>" /></a>
					</span>
				</div>

				<div class="activity-header">
					<div class="activity-time">
						<%= Time.getRelativeTimeDescription(modifiedDate.getTime(), themeDisplay.getLocale(), themeDisplay.getTimeZone(), dateFormat) %>
					</div>

					<div class="activity-user-name">
						<%= HtmlUtil.escape(receiverUser.getFullName()) %>
					</div>
				</div>

				<div class="activity-action">
					<%= HtmlUtil.escape(microblogsEntry.getContent()) %>
				</div>
			</div>
		</div>

		<aui:button-row>
			<aui:button onClick='<%= renderResponse.getNamespace() + "saveEntry();" %>' value="repost" />

			<aui:button onClick='<%= renderResponse.getNamespace() + "closeEntry();" %>' value="cancel" />
		</aui:button-row>
	</c:otherwise>
</c:choose>

<aui:script>
	function <portlet:namespace />closeEntry() {
		Liferay.Util.getWindow('<portlet:namespace />Dialog').destroy();
	}

	function <portlet:namespace />saveEntry() {
		var A = AUI();

		var uri = '<portlet:actionURL name="repostMicroblogsEntry"><portlet:param name="microblogsEntryId" value="<%= String.valueOf(microblogsEntryId) %>" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>';

		A.io.request(
			uri,
			{
				after: {
					success: function(event, id, obj) {
						<portlet:namespace />closeEntry();

						var topWindow = Liferay.Util.getTop();

						topWindow.document.location.reload();
					}
				}
			}
		);
	}
</aui:script>