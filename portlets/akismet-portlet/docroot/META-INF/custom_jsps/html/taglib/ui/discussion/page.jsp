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

<%@ include file="/html/taglib/init.jsp" %>

<%@ page import="com.liferay.message.boards.kernel.model.MBMessage" %>
<%@ page import="com.liferay.message.boards.kernel.model.MBMessageDisplay" %>
<%@ page import="com.liferay.message.boards.kernel.model.MBTreeWalker" %>
<%@ page import="com.liferay.message.boards.kernel.service.MBMessageLocalServiceUtil" %>

<%
String className = (String)request.getAttribute("liferay-ui:discussion:className");
long classPK = GetterUtil.getLong((String)request.getAttribute("liferay-ui:discussion:classPK"));
boolean hideControls = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:discussion:hideControls"));
long userId = GetterUtil.getLong((String)request.getAttribute("liferay-ui:discussion:userId"));
%>

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/taglib/ui/discussion/page.portal.jsp" />
</liferay-util:buffer>

<%
if (!hideControls && (permissionChecker.isCompanyAdmin() || permissionChecker.isGroupAdmin(scopeGroupId))) {
	MBMessageDisplay messageDisplay = MBMessageLocalServiceUtil.getDiscussionMessageDisplay(userId, scopeGroupId, className, classPK, WorkflowConstants.STATUS_ANY, PropsValues.DISCUSSION_THREAD_VIEW);

	MBTreeWalker treeWalker = messageDisplay.getTreeWalker();

	if (treeWalker != null) {
		List<MBMessage> messages = treeWalker.getMessages();

		for (MBMessage message : messages) {
			if (message.isRoot()) {
				continue;
			}

			int x = html.indexOf("messageScroll" + message.getMessageId());

			if (x < 0) {
				continue;
			}

			int y = html.indexOf("<ul class=\"lfr-discussion-actions\">", x);

			if (y < 0) {
				continue;
			}

			int z = html.indexOf("</ul>", y);
%>

			<liferay-util:buffer var="customHTML">
				<c:choose>
					<c:when test="<%= message.getStatus() == WorkflowConstants.STATUS_DENIED %>">

						<%
						String notSpamURL = themeDisplay.getPortalURL() + themeDisplay.getPathMain() + "/portal/akismet_edit_discussion";

						notSpamURL = HttpUtil.addParameter(notSpamURL, "redirect", currentURL);
						notSpamURL = HttpUtil.addParameter(notSpamURL, "messageId", message.getMessageId());
						notSpamURL = HttpUtil.addParameter(notSpamURL, "spam", Boolean.FALSE);
						%>

						<li>
							<liferay-ui:icon
								image="../mail/compose"
								label="<%= true %>"
								message="not-spam"
								url="<%= notSpamURL %>"
							/>
						</li>
					</c:when>
					<c:otherwise>
						<li>

							<%
							String markAsSpamURL = themeDisplay.getPortalURL() + themeDisplay.getPathMain() + "/portal/akismet_edit_discussion";

							markAsSpamURL = HttpUtil.addParameter(markAsSpamURL, "redirect", currentURL);
							markAsSpamURL = HttpUtil.addParameter(markAsSpamURL, "messageId", message.getMessageId());
							markAsSpamURL = HttpUtil.addParameter(markAsSpamURL, "spam", Boolean.TRUE);
							%>

							<liferay-ui:icon
								image="../mail/delete"
								label="<%= true %>"
								message="mark-as-spam"
								url="<%= markAsSpamURL %>"
							/>
						</li>
					</c:otherwise>
				</c:choose>
			</liferay-util:buffer>

<%
			StringBundler sb = new StringBundler(5);

			if (message.getStatus() == WorkflowConstants.STATUS_DENIED) {
				int divContainerPos = html.lastIndexOf("<div class=\"lfr-discussion", x);

				sb.append(html.substring(0, divContainerPos + 4));
				sb.append(" style=\"background-color: #FDD; border: 1px solid red;\"");
				sb.append(html.substring(divContainerPos + 4, z));
			}
			else {
				sb.append(html.substring(0, z));
			}

			sb.append(customHTML);
			sb.append(html.substring(z));

			html = sb.toString();
		}
	}
}
%>

<%= html %>