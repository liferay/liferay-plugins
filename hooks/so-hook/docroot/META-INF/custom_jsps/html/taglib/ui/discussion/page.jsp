<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/taglib/init.jsp" %>

<%
String formAction = (String)request.getAttribute("liferay-ui:discussion:formAction");
%>

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/taglib/ui/discussion/page.jsp" useCustomPage="<%= false %>" />
</liferay-util:buffer>

<c:choose>
	<c:when test="<%= formAction.contains(LiferayWindowState.EXCLUSIVE.toString()) %>">
		<%= html.replace("submitForm(", "discussionSubmit(") %>

		<aui:script>
			function discussionSubmit(form) {
				var A = AUI();

				var form = A.one(form);
				var widget = A.DialogManager.findByChild(form);

				if (widget.io) {
					widget.io.set('form', {id: form.getDOM()});
					widget.io.set('uri', form.getAttribute('action'));

					widget.io.start();

					widget.io.set('form', null);
				}
				else {
					submitForm(form);
				}
			}
		</aui:script>
	</c:when>
	<c:otherwise>
		<%= html %>
	</c:otherwise>
</c:choose>