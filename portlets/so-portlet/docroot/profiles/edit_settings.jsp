<%
/**
 * Copyright (c) 2008-2009 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/init.jsp" %>

<%
String languageId = BeanParamUtil.getString(user, request, "languageId", user.getLanguageId());
String timeZoneId = BeanParamUtil.getString(user, request, "timeZoneId", user.getTimeZoneId());
%>

<script type="text/javascript">
	jQuery(
		function() {
			var form = jQuery(document.<portlet:namespace />fm);

			form.ajaxForm(
				{
					type: "POST",
					beforeSubmit: function() {
						document.getElementById('<portlet:namespace />submit').disabled = true;
					},
					success: function() {
						Liferay.SO.Profiles.displayUserProfile(<%= user.getUserId() %>);
					}
				}
			);
		}
	);
</script>

<h1><%= user.getFullName() %> : <liferay-ui:message key="edit-settings" /></h1>

<form action="<portlet:actionURL name="updateUserSettings"></portlet:actionURL>" name="<portlet:namespace />fm">
<input name="<portlet:namespace />userId" type="hidden" value="<%= user.getUserId() %>" />

<table width="100%">
<tr>
	<td>
		<img alt="<%= user.getFullName() %>" src="<%= themeDisplay.getPathImage() %>/user_<%= (user.isFemale() ? "female" : "male") %>_portrait?img_id=<%= user.getPortraitId() %>&t=<%= ImageServletTokenUtil.getToken(user.getPortraitId()) %>" />

		<div class="profile-controls">
			<a href="javascript: ;" onClick="Liferay.SO.Profiles.displayUserProfile(<%= user.getUserId() %>);"><liferay-ui:message key="cancel-edit" /></a>
		</div>
	</td>
	<td class="profile-area edit-profile">
		<table width="100%">
		<tr>
			<td colspan="2">
				<h3 class="first"><liferay-ui:message key="password" /></h3>
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="password" />
			</td>
			<td>
				<input name="<portlet:namespace />password1" size="30" type="password" value="" />
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="enter-again" />
			</td>
			<td>
				<input name="<portlet:namespace />password2" size="30" type="password" value="" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<h3><liferay-ui:message key="display-settings" /></h3>
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="language" />
			</td>
			<td>
				<select name="<portlet:namespace />languageId">

					<%
					Locale selLocale = LocaleUtil.fromLanguageId(languageId);

					Locale[] locales = LanguageUtil.getAvailableLocales();

					for (Locale curLocale : locales) {
					%>

						<option <%= (selLocale.getLanguage().equals(curLocale.getLanguage()) && selLocale.getCountry().equals(curLocale.getCountry())) ? "selected" : "" %> value="<%= curLocale.getLanguage() + "_" + curLocale.getCountry() %>"><%= curLocale.getDisplayName(curLocale) %></option>

					<%
					}
					%>

				</select>
			</td>
		</tr>
		<tr>
			<td class="lfr-label">
				<liferay-ui:message key="time-zone" />
			</td>
			<td>
				<liferay-ui:input-time-zone name="timeZoneId" value="<%= timeZoneId %>" />
			</td>
		</tr>
		</table>

		<br />

		<div>
			<input id="<portlet:namespace />submit" type="submit" value="<liferay-ui:message key="save" />" />

			<input type="button" value="<liferay-ui:message key="cancel" />" onClick="Liferay.SO.Profiles.displayUserProfile(<%= user.getUserId() %>);" />
		</div>
	</td>
</tr>
</table>

</form>