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

<%@ include file="/html/portlet/message_boards/init.jsp" %>

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/portlet/message_boards/view_message_content.portal.jsp" />
</liferay-util:buffer>

<c:if test="<%= MBPermission.contains(permissionChecker, scopeGroupId, ActionKeys.BAN_USER) %>">

	<%
	int messagePos = 0;

	while ((messagePos = html.indexOf("<div class=\"message-container", messagePos)) > -1) {
		int x = html.indexOf("<ul class=\"edit-controls", messagePos);
		int y = html.indexOf("</ul>", x);

		String messageIdParameter = renderResponse.getNamespace() + "messageId=";

		int messageIdPos = html.indexOf(messageIdParameter, x);

		if ((x > 0) && (y > 0) && (messageIdPos > 0)) {
			String messageId = html.substring(messageIdPos + messageIdParameter.length(), html.indexOf("\"", messageIdPos));

			MBMessage message = null;

			try {
				message = MBMessageLocalServiceUtil.getMessage(GetterUtil.getLong(messageId));
			}
			catch (Exception e) {
				messagePos++;

				continue;
			}

			boolean spam = false;

			if (message.getStatus() == WorkflowConstants.STATUS_DENIED) {
				spam = true;
			}
	%>

			<liferay-util:buffer var="customHTML">
				<c:choose>
					<c:when test="<%= spam %>">
						<li>
							<portlet:actionURL var="notSpamURL">
								<portlet:param name="struts_action" value="/message_boards/akismet_edit_message" />
								<portlet:param name="<%= Constants.CMD %>" value="updateStatus" />
								<portlet:param name="redirect" value="<%= currentURL %>" />
								<portlet:param name="messageId" value="<%= messageId %>" />
								<portlet:param name="spam" value="<%= String.valueOf(Boolean.FALSE) %>" />
							</portlet:actionURL>

							<liferay-ui:icon
								iconCssClass="icon-envelope-alt"
								label="<%= true %>"
								message="not-spam"
								url="<%= notSpamURL %>"
							/>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<portlet:actionURL var="markAsSpamURL">
								<portlet:param name="struts_action" value="/message_boards/akismet_edit_message" />
								<portlet:param name="<%= Constants.CMD %>" value="updateStatus" />
								<portlet:param name="redirect" value="<%= currentURL %>" />
								<portlet:param name="messageId" value="<%= messageId %>" />
								<portlet:param name="spam" value="<%= String.valueOf(Boolean.TRUE) %>" />
							</portlet:actionURL>

							<liferay-ui:icon
								iconCssClass="icon-remove"
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

			if (spam) {
				sb.append(html.substring(0, messagePos + 4));
				sb.append(" style=\"background-color: #FDD; border: 1px solid red;\"");
				sb.append(html.substring(messagePos + 4, y));
			}
			else {
				sb.append(html.substring(0, y));
			}

			sb.append(customHTML);
			sb.append(html.substring(y));

			html = sb.toString();

			messagePos = y + customHTML.length();
		}
		else {
			messagePos++;
		}
	}
	%>

</c:if>

<%= html %>