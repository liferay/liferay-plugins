<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/init.jsp" %>

<h1><%= HtmlUtil.escape(user.getFullName()) %> : <liferay-ui:message key="edit-profile" /></h1>

<form action="<portlet:actionURL name="updateUserProfile"></portlet:actionURL>" name="<portlet:namespace />fm">
<input name="<portlet:namespace />redirect" type="hidden" value="<%= PortalUtil.getLayoutURL(layout, themeDisplay) %>/-/profiles/user_profile/<%= user.getUserId() %>" />
<input name="<portlet:namespace />redirectOnError" type="hidden" value="<%= PortalUtil.getLayoutURL(layout, themeDisplay) %>/-/profiles/edit_profile" />
<input name="<portlet:namespace />userId" type="hidden" value="<%= user.getUserId() %>" />

<liferay-ui:error />

<table width="100%">
<tr>
	<td>
		<liferay-portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>" portletName="<%= PortletKeys.MY_ACCOUNT %>" var="editUserPortraitURL">
			<liferay-portlet:param name="struts_action" value="/my_account/edit_user_portrait" />
			<liferay-portlet:param name="redirect" value="<%= currentURL %>" />
			<liferay-portlet:param name="p_u_i_d" value="<%= String.valueOf(user.getUserId()) %>" />
			<liferay-portlet:param name="portrait_id" value="<%= String.valueOf(user.getPortraitId()) %>" />
		</liferay-portlet:renderURL>

		<a class="change-avatar" href="javascript:<portlet:namespace />editUserPortrait('<%= editUserPortraitURL %>');"><img alt="<liferay-ui:message key="avatar" />" class="avatar" id="<portlet:namespace />avatar" src="<%= user.getPortraitURL(themeDisplay) %>" /></a>

		<div class="profile-controls">
			<a href="javascript:;" onClick="<portlet:namespace />editUserPortrait('<%= editUserPortraitURL %>');"><liferay-ui:message key="change-picture" /></a>

			<portlet:actionURL name="deleteUserPortrait" var="deleteUserPortraitURL">
				<portlet:param name="userId" value="<%= String.valueOf(user.getUserId()) %>" />
			</portlet:actionURL>

			<a href="javascript:;" onClick="<portlet:namespace />deleteUserPortrait('<%= deleteUserPortraitURL %>');"><liferay-ui:message key="delete-picture" /></a>

			<a class="so-display-profile" href="javascript:;"><liferay-ui:message key="cancel-edit" /></a>
		</div>
	</td>
	<td class="profile-area edit-profile">
		<table width="100%">
		<tr>
			<td colspan="2">
				<h3 class="first"><liferay-ui:message key="general" /></h3>
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="first-name" />
			</td>
			<td>
				<liferay-ui:error exception="<%= ContactFirstNameException.class %>" message="please-enter-a-valid-first-name" />

				<liferay-ui:input-field model="<%= Contact.class %>" bean="<%= contact %>" field="firstName" />
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="middle-name" />
			</td>
			<td>
				<liferay-ui:input-field model="<%= Contact.class %>" bean="<%= contact %>" field="middleName" />
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="last-name" />
			</td>
			<td>
				<liferay-ui:error exception="<%= ContactLastNameException.class %>" message="please-enter-a-valid-last-name" />

				<liferay-ui:input-field model="<%= Contact.class %>" bean="<%= contact %>" field="lastName" />
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="job-title" />
			</td>
			<td>
				<liferay-ui:input-field model="<%= Contact.class %>" bean="<%= contact %>" field="jobTitle" />
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="primary-email" />
			</td>
			<td>
				<liferay-ui:error exception="<%= DuplicateUserEmailAddressException.class %>" message="the-email-address-you-requested-is-already-taken" />
				<liferay-ui:error exception="<%= ReservedUserEmailAddressException.class %>" message="the-email-address-you-requested-is-reserved" />
				<liferay-ui:error exception="<%= UserEmailAddressException.class %>" message="please-enter-a-valid-email-address" />

				<liferay-ui:input-field model="<%= User.class %>" bean="<%= user %>" field="emailAddress" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<h3><liferay-ui:message key="contact-information" /></h3>
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="addresses" />
			</td>
			<td>

				<%
				request.setAttribute("addresses.className", Contact.class.getName());
				request.setAttribute("addresses.classPK", user.getContactId());
				%>

				<div id="addresses">
					<liferay-util:buffer var="addressesHTML">
						<liferay-util:include page="/html/portlet/enterprise_admin/common/addresses.jsp" />
					</liferay-util:buffer>

					<%= _formatHTML(addressesHTML) %>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="phone-numbers" />
			</td>
			<td>

				<%
				request.setAttribute("phones.className", Contact.class.getName());
				request.setAttribute("phones.classPK", user.getContactId());
				%>

				<div id="phoneNumbers">
					<liferay-util:buffer var="phoneNumbersHTML">
						<liferay-util:include page="/html/portlet/enterprise_admin/common/phone_numbers.jsp" />
					</liferay-util:buffer>

					<%= _formatHTML(phoneNumbersHTML) %>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="websites" />
			</td>
			<td>

				<%
				request.setAttribute("websites.className", Contact.class.getName());
				request.setAttribute("websites.classPK", user.getContactId());
				%>

				<div id="websites">
					<liferay-util:buffer var="websitesHTML">
						<liferay-util:include page="/html/portlet/enterprise_admin/common/websites.jsp" />
					</liferay-util:buffer>

					<%= _formatHTML(websitesHTML) %>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="additional-email-addresses" />
			</td>
			<td>

				<%
				request.setAttribute("emailAddresses.className", Contact.class.getName());
				request.setAttribute("emailAddresses.classPK", user.getContactId());
				%>

				<div id="additionalEmailAddresses">
					<liferay-util:buffer var="additionalEmailAddressesHTML">
						<liferay-util:include page="/html/portlet/enterprise_admin/common/additional_email_addresses.jsp" />
					</liferay-util:buffer>

					<%= _formatHTML(additionalEmailAddressesHTML) %>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="aim" />
			</td>
			<td>
				<liferay-ui:input-field model="<%= Contact.class %>" bean="<%= contact %>" field="aimSn" />
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="icq" />
			</td>
			<td>
				<liferay-ui:input-field model="<%= Contact.class %>" bean="<%= contact %>" field="icqSn" />
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="jabber" />
			</td>
			<td>
				<liferay-ui:input-field model="<%= Contact.class %>" bean="<%= contact %>" field="jabberSn" />
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="msn" />
			</td>
			<td>
				<liferay-ui:input-field model="<%= Contact.class %>" bean="<%= contact %>" field="msnSn" />
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="skype" />
			</td>
			<td>
				<liferay-ui:input-field model="<%= Contact.class %>" bean="<%= contact %>" field="skypeSn" />
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="ym" />
			</td>
			<td>
				<liferay-ui:input-field model="<%= Contact.class %>" bean="<%= contact %>" field="ymSn" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="facebook" />
			</td>
			<td>
				<liferay-ui:input-field model="<%= Contact.class %>" bean="<%= contact %>" field="facebookSn" />
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="myspace" />
			</td>
			<td>
				<liferay-ui:input-field model="<%= Contact.class %>" bean="<%= contact %>" field="mySpaceSn" />
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="twitter" />
			</td>
			<td>
				<liferay-ui:input-field model="<%= Contact.class %>" bean="<%= contact %>" field="twitterSn" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<h3><liferay-ui:message key="tags" /></h3>
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="tags" />
			</td>
			<td>
				<liferay-ui:asset-tags-selector
					className="<%= User.class.getName() %>"
					classPK="<%= user.getUserId() %>"
				/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<h3><liferay-ui:message key="notes" /></h3>
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="notes" />
			</td>
			<td>
				<liferay-ui:input-field model="<%= User.class %>" bean="<%= user %>" field="comments" />
			</td>
		</tr>
		</table>

		<br />

		<div>
			<input id="<portlet:namespace />submit" type="submit" value="<liferay-ui:message key="save" />" />

			<input class="so-display-profile" type="button" value="<liferay-ui:message key="cancel" />" />
		</div>
	</td>
</tr>
</table>

</form>

<aui:script>
	function <portlet:namespace />deleteUserPortrait(deleteUserPortraitURL) {
		AUI.use(
			'aui-io',
			function(A) {
				A.io.request(
					deleteUserPortraitURL,
					{
						method: 'POST',
						on: {
							success: function(event) {
								A.one('#<portlet:namespace />avatar').attr('src', '<%= UserConstants.getPortraitURL(themeDisplay.getPathImage(), user.isMale(), 0) %>');
							}
						}
					}
				);
			}
		);
	}

	function <%= PortalUtil.getPortletNamespace(PortletKeys.MY_ACCOUNT) %>changePortrait(newPortraitURL) {
		A.one('#<portlet:namespace />avatar').attr('src', newPortraitURL);
	}

	function <portlet:namespace />editUserPortrait(editUserPortraitURL) {
		var editUserPortraitWindow = window.open(editUserPortraitURL, '<liferay-ui:message key="change" />', 'directories=no,height=400,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=500');

		editUserPortraitWindow.focus();
	}
</aui:script>

<%!
private String _formatHTML(String html) {
	int x = html.indexOf("<h3>");
	int y = html.indexOf("</h3>");

	StringBuilder sb = new StringBuilder();

	sb.append(html.substring(0, x));
	sb.append(html.substring(y + 5));

	html = sb.toString();

	html = _removeOptions("<div class=\"ctrl-holder mailing-ctrl\">", html);
	html = _removeOptions("<div class=\"ctrl-holder primary-ctrl\">", html);
	html = _removeOptions("<div class=\"ctrl-holder street3-ctrl\">", html);

	return html;
}

private String _removeOptions(String option, String html) {
	int x = html.indexOf(option);

	if (x >= 0) {
		int y = html.indexOf("</div>", x);

		StringBuilder sb = new StringBuilder();

		sb.append(html.substring(0, x));
		sb.append(html.substring(y + 6));

		return _removeOptions(option, sb.toString());
	}

	return html;
}
%>