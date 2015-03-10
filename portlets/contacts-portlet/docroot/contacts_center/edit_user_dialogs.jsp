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
String curSectionId = ParamUtil.getString(request, "curSectionId");

boolean extension = ParamUtil.getBoolean(request, "extension");

User selUser = themeDisplay.getUser();

Contact selContact = null;

if (selUser != null) {
	selContact = selUser.getContact();
}

String namespace = renderResponse.getNamespace();

if (extension) {
	namespace = PortalUtil.getPortletNamespace(PortletKeys.MY_ACCOUNT);
}
%>

<div id="<%= namespace %>updateUserDialog">
	<aui:form action="" method="post" name="dialogForm" portletNamespace="<%= namespace %>">
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

		<div class="form-section selected" id="<%= namespace + curSectionId %>">
			<div id="<%= namespace %>errorMessage"></div>

			<c:choose>
				<c:when test='<%= curSectionId.equals("categorization") %>'>
					<liferay-util:include page='<%= "/contacts_center/user/" + _getSectionJsp(curSectionId) + ".jsp" %>' servletContext="<%= application %>" />
				</c:when>
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

<aui:script position="inline" use="aui-base,aui-io-request-deprecated">
	var form = A.one('#<%= namespace %>dialogForm');

	form.on(
		'submit',
		function(event) {
			event.halt(true);

			Liferay.fire(
				'saveAutoFields',
				{
					form: form
				}
			);

			var uri;

			<c:choose>
				<c:when test="<%= extension %>">

					<%
					Group controlPanelGroup = GroupLocalServiceUtil.getGroup(themeDisplay.getCompanyId(), GroupConstants.CONTROL_PANEL);

					long controlPanelPlid = LayoutLocalServiceUtil.getDefaultPlid(controlPanelGroup.getGroupId(), true);
					%>

					uri = '<liferay-portlet:actionURL name="updateFieldGroup" plid="<%= controlPanelPlid %>" portletName="<%= PortletKeys.MY_ACCOUNT %>" windowState="<%= LiferayWindowState.NORMAL.toString() %>"><portlet:param name="struts_action" value="/my_account/edit_user" /></liferay-portlet:actionURL>';
				</c:when>
				<c:otherwise>
					uri = '<liferay-portlet:actionURL name="updateFieldGroup" windowState="<%= LiferayWindowState.NORMAL.toString() %>" />';
				</c:otherwise>
			</c:choose>

			A.io.request(
				uri,
				{
					after: {
						success: function(event, id, obj) {
							var responseData = this.get('responseData');

							if (!responseData.success) {
								var message = A.one('#<%= namespace %>errorMessage');

								if (message) {
									message.html('<span class="alert alert-danger">' + responseData.message + '</span>');
								}
							}
							else {
								Liferay.Util.getWindow('<portlet:namespace />Dialog').hide();

								var redirect = responseData.redirect;

								if (redirect) {
									var topWindow = Liferay.Util.getTop();

									topWindow.location.href = redirect;
								}
							}
						}
					},
					dataType: 'JSON',
					form: {
						id: form
					}
				}
			);
		}
	);
</aui:script>

<aui:script use="liferay-auto-fields">
	Liferay.fire('formNavigator:reveal<%= namespace %><%= curSectionId %>');
</aui:script>

<%!
private String _getSectionJsp(String curSectionId) {
	String sectionJsp = TextFormatter.format(curSectionId, TextFormatter.K);

	return TextFormatter.format(sectionJsp, TextFormatter.N);
}
%>