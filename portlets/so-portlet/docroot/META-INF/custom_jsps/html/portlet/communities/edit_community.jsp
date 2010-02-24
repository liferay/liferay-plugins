<%
/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/communities/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

Group group = (Group)request.getAttribute(WebKeys.GROUP);

long groupId = BeanParamUtil.getLong(group, request, "groupId");

int type = BeanParamUtil.getInteger(group, request, "type");
String friendlyURL = BeanParamUtil.getString(group, request, "friendlyURL");
%>

<script type="text/javascript">
	function <portlet:namespace />saveGroup() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= group == null ? Constants.ADD : Constants.UPDATE %>";
		submitForm(document.<portlet:namespace />fm);
	}

	<c:if test="<%= windowState.equals(LiferayWindowState.EXCLUSIVE) %>">
		jQuery(
			function() {
				jQuery(document.<portlet:namespace />fm).ajaxForm(
					{
						type: "POST",
						success: function() {
							Liferay.SO.Sites.closePopup();

							var siteList = jQuery('.so-portlet-sites .results-grid');

							siteList.html('<div class="loading-animation" />');
							siteList.load(themeDisplay.getLayoutURL() + ' .so-portlet-sites .taglib-search-iterator', {t:(+new Date())});

							if (jQuery('.contacts-portlet').length > 0) {
								Liferay.Contacts.loadEntries(false);
							}
						}
					}
				);
			}
		);
	</c:if>
</script>

<form action="<portlet:actionURL windowState="<%= windowState.toString() %>"><portlet:param name="struts_action" value="/communities/edit_community" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= group == null ? Constants.ADD : Constants.UPDATE %>" />
<input name="<portlet:namespace />redirect" type="hidden" value="<%= windowState.equals(LiferayWindowState.EXCLUSIVE) ? StringPool.BLANK : HtmlUtil.escape(redirect) %>" />
<input name="<portlet:namespace />groupId" type="hidden" value="<%= groupId %>" />
<input name="<portlet:namespace />friendlyURL" type="hidden" value="<%= HtmlUtil.escapeAttribute(friendlyURL) %>" />
<input name="<portlet:namespace />active" type="hidden" value="1" />

<c:if test="<%= !portletName.equals(PortletKeys.ADMIN_SERVER) && !portletName.equals(PortletKeys.COMMUNITIES) %>">
	<liferay-util:include page="/html/portlet/communities/toolbar.jsp">
		<liferay-util:param name="toolbarItem" value='<%= (group == null) ? "add" : "view-all" %>' />
	</liferay-util:include>
</c:if>

<liferay-ui:error exception="<%= DuplicateGroupException.class %>" message="please-enter-a-unique-name" />
<liferay-ui:error exception="<%= GroupNameException.class %>" message="please-enter-a-valid-name" />
<liferay-ui:error exception="<%= RequiredGroupException.class %>" message="old-group-name-is-a-required-system-group" />
<liferay-ui:success	key="request_processed" message="your-request-processed-successfully" />

<table class="lfr-table">
<tr>
	<td class="lfr-label">
		<liferay-ui:message key="name" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= Group.class %>" bean="<%= group %>" field="name" />
	</td>
</tr>
<tr>
	<td class="lfr-label">
		<liferay-ui:message key="description" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= Group.class %>" bean="<%= group %>" field="description" />
	</td>
</tr>
<tr>
	<td class="lfr-label">
		<liferay-ui:message key="type" />
	</td>
	<td>
		<select name="<portlet:namespace />type">
			<option <%= (type == GroupConstants.TYPE_COMMUNITY_OPEN) ? "selected" : "" %> value="<%= GroupConstants.TYPE_COMMUNITY_OPEN %>"><liferay-ui:message key="open" /></option>
			<option <%= (type == GroupConstants.TYPE_COMMUNITY_PRIVATE) ? "selected" : "" %> value="<%= GroupConstants.TYPE_COMMUNITY_PRIVATE %>"><liferay-ui:message key="private" /></option>
		</select>
	</td>
</tr>
</table>

<br />

<c:choose>
	<c:when test="<%= windowState.equals(LiferayWindowState.EXCLUSIVE) %>">
		<input type="submit" value="<liferay-ui:message key="save" />" />

		<input type="button" value="<liferay-ui:message key="cancel" />" onClick="Liferay.SO.Sites.closePopup();" />
	</c:when>
	<c:otherwise>
		<input type="button" value="<liferay-ui:message key="save" />" onClick="<portlet:namespace />saveGroup();" />

		<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>';" />
	</c:otherwise>
</c:choose>

</form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) || windowState.equals(LiferayWindowState.EXCLUSIVE) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
	</script>
</c:if>