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

<%@ include file="/init.jsp" %>

<%
long entryId = ParamUtil.getLong(request, "entryId");
%>

<c:if test="<%= entryId > 0 %>">

	<%
	Entry entry = EntryLocalServiceUtil.getEntry(entryId);

	entry = entry.toEscapedModel();
	%>

	<div class="contacts-profile external-contact">
		<div class="lfr-detail-info">
			<c:if test="<%= showIcon %>">
				<div class="lfr-contact-thumb">
					<img alt="<%= entry.getFullName() %>" src='<%= themeDisplay.getPathImage() + "/user_male_portrait?img_id=0&t=" %>' />
				</div>
			</c:if>

			<div class="<%= showIcon ? StringPool.BLANK : "no-icon" %> lfr-contact-info">
				<div class="lfr-contact-name">
					<%= entry.getFullName() %>
				</div>

				<div class="lfr-contact-extra">
					<a href="mailto:<%= entry.getEmailAddress() %>"><%= entry.getEmailAddress() %></a>
				</div>
			</div>
		</div>
		<div class="lfr-detail-info">
			<div class="comments">
				<%= entry.getComments() %>
			</div>
		</div>
	</div>
</c:if>