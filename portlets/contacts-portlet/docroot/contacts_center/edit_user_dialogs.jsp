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
String curSectionId = ParamUtil.getString(request, "curSectionId");

boolean extension = ParamUtil.getBoolean(request, "extension");

User selUser = themeDisplay.getUser();

Contact selContact = null;

if (selUser != null) {
	selContact = selUser.getContact();
}
%>

<liferay-util:buffer var="html">

	<%
	String taglibOnSubmit = "event.preventDefault(); " + renderResponse.getNamespace() + "saveForm();";
	%>

	<div id="<portlet:namespace />updateUserDialog">
		<aui:form action="" method="post" name="dialogForm" onSubmit="<%= taglibOnSubmit %>">
			<aui:input name="redirect" type="hidden"  value="<%= selUser.getDisplayURL(themeDisplay) %>" />
			<aui:input name="fieldGroup" type="hidden"  value="<%= curSectionId %>" />
			<aui:input name="p_u_i_d" type="hidden" value="<%= (selUser != null) ? selUser.getUserId() : 0 %>" />

			<%
			request.setAttribute("user.selContact", selContact);
			request.setAttribute("user.selUser", selUser);

			request.setAttribute("addresses.className", Contact.class.getName());
			request.setAttribute("emailAddresses.className", Contact.class.getName());
			request.setAttribute("phones.className", Contact.class.getName());
			request.setAttribute("websites.className", Contact.class.getName());

			if (selContact != null) {
				request.setAttribute("addresses.classPK", selContact.getContactId());
				request.setAttribute("emailAddresses.classPK", selContact.getContactId());
				request.setAttribute("phones.classPK", selContact.getContactId());
				request.setAttribute("websites.classPK", selContact.getContactId());
			}
			else {
				request.setAttribute("addresses.classPK", 0L);
				request.setAttribute("emailAddresses.classPK", 0L);
				request.setAttribute("phones.classPK", 0L);
				request.setAttribute("websites.classPK", 0L);
			}

			String sectionJsp = "/html/portlet/users_admin/user/" + _getSectionJsp(curSectionId) + ".jsp";
			%>

			<div class="form-section selected" id="<portlet:namespace /><%= curSectionId %>">
				<div id="<portlet:namespace />errorMessage"></div>

				<c:choose>
					<c:when test='<%= curSectionId.equals("details") %>'>
						<liferay-util:include page='<%= "/contacts_center/user/" + _getSectionJsp(curSectionId) + ".jsp" %>' servletContext="<%= application %>" />
					</c:when>
					<c:otherwise>
						<liferay-util:include page="<%= sectionJsp %>" />
					</c:otherwise>
				</c:choose>
			</div>

			<aui:button-row>
				<aui:button type="submit" />
			</aui:button-row>
		</aui:form>
	</div>

	<aui:script>
		var <portlet:namespace />saveForm = function() {
			var A = AUI();

			var form = A.one('#<portlet:namespace />dialogForm');

			Liferay.fire(
				'saveAutoFields',
				{
					form: form
				}
			);

			<c:choose>
				<c:when test="<%= extension %>">

					<%
					Group controlPanelGroup = GroupLocalServiceUtil.getGroup(themeDisplay.getCompanyId(), GroupConstants.CONTROL_PANEL);

					long controlPanelPlid = LayoutLocalServiceUtil.getDefaultPlid(controlPanelGroup.getGroupId(), true);
					%>

					var uri = '<liferay-portlet:actionURL name="updateFieldGroup" plid="<%= controlPanelPlid %>" portletName="<%= PortletKeys.MY_ACCOUNT %>" windowState="<%= LiferayWindowState.NORMAL.toString() %>"><portlet:param name="struts_action" value="/my_account/edit_user" /></liferay-portlet:actionURL>';
				</c:when>
				<c:otherwise>
					var uri = '<liferay-portlet:actionURL name="updateFieldGroup" />';
				</c:otherwise>
			</c:choose>

			A.io.request(
				uri,
				{
					dataType: 'json',
					form: {
						id: form
					},
					after: {
						success: function(event, id, obj) {
							var responseData = this.get('responseData');

							if (!responseData.success) {
								var message = A.one('#<portlet:namespace />errorMessage');

								if (message) {
									message.html('<span class="portlet-msg-error">' + responseData.message + '</span>');
								}
							}
							else {
								var redirect = responseData.redirect;

								if (redirect) {
									location.href = redirect;
								}
							}
						}
					}
				}
			);
		}

		AUI().ready(
			'liferay-auto-fields',
			function() {
				Liferay.fire('formNavigator:reveal<portlet:namespace /><%= curSectionId %>');
			}
		);
	</aui:script>
</liferay-util:buffer>

<c:choose>
	<c:when test="<%= extension %>">
		<%= StringUtil.replace(html, renderResponse.getNamespace(), "_" + PortletKeys.MY_ACCOUNT + "_") %>
	</c:when>
	<c:otherwise>
		<%= html %>
	</c:otherwise>
</c:choose>

<%!
private String _getSectionJsp(String curSectionId) {
	String sectionJsp = TextFormatter.format(curSectionId, TextFormatter.K);

	return TextFormatter.format(sectionJsp, TextFormatter.N);
}
%>