<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
					<img alt="<%= HtmlUtil.escape(entry.getFullName()) %>" src='<%= themeDisplay.getPathImage() + "/user_male_portrait?img_id=0&t=" %>' />
				</div>
			</c:if>

			<div class="<%= showIcon ? StringPool.BLANK : "no-icon" %> lfr-contact-info">
				<div class="lfr-contact-name">
					<%= HtmlUtil.escape(entry.getFullName()) %>
				</div>

				<div class="lfr-contact-extra">
					<%= HtmlUtil.escape(entry.getEmailAddress()) %>
				</div>
			</div>
		</div>
		<div class="lfr-detail-info">
			<div class="comments">
				<%= HtmlUtil.escape(entry.getComments()) %>
			</div>
		</div>
	</div>
</c:if>