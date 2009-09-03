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

<%@ include file="/html/portlet/communities/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
String callingPageURL = ParamUtil.getString(request, "callingPageURL");

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

	jQuery(
		function() {
			var form = jQuery(document.<portlet:namespace />fm1);
			form.ajaxForm(
				{
					target: ".ui-dialog-content",
					type: "POST",
					success: function(){

						var dialog = jQuery('.site-dialog').find('.ui-dialog-content');

						var cmd = jQuery(document.<portlet:namespace />fm1.<portlet:namespace /><%= Constants.CMD %>);
						cmd.val("<%= group == null ? Constants.ADD : Constants.UPDATE %>");

						if(dialog.find('.portlet-msg-success').length) {
							if(<%= group == null ? false : true %>){
								dialog.dialog('close');
							}
						}

						var pageURLVal = jQuery('#<portlet:namespace />pageURL').val();
						var popupURLVal = jQuery('#<portlet:namespace />popupURL').val();

						jQuery('[name=<portlet:namespace />pageURL]').val(pageURLVal);
						jQuery('[name=<portlet:namespace />redirect]').val(popupURLVal);

						var siteList = jQuery('.portlet-communities .site-list');

						siteList.html('<div class="loading-animation" />');
						siteList.load(pageURLVal + " .site-list", {t:(+new Date())});

						var contactsPortlet = jQuery('.contacts-portlet');

						if (contactsPortlet.length > 0) {
							Liferay.Contacts.loadEntries(false);
						}
					}
				}
			)
		}
	)
</script>

<form action="<portlet:actionURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="struts_action" value="/communities/edit_community" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm1">
<c:choose>
	<c:when test="<%= groupId == 0 %>">
		<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
	</c:when>
	<c:otherwise>
		<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	</c:otherwise>
</c:choose>
<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
<input name="<portlet:namespace />callingPageURL" type="hidden" value="<%= HtmlUtil.escape(callingPageURL) %>" />
<input name="<portlet:namespace />groupId" type="hidden" value="<%= groupId %>" />
<input name="<portlet:namespace />friendlyURL" type="hidden" value="<%= friendlyURL %>" />
<input name="<portlet:namespace />active" type="hidden" value="1" />

<liferay-ui:error exception="<%= DuplicateGroupException.class %>" message="please-enter-a-unique-name" />
<liferay-ui:error exception="<%= GroupNameException.class %>" message="please-enter-a-valid-name.-the-name-can-not-contain-a-comma,-an-asterisk,-be-blank-or-be-a-number" />
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

<input type="submit" value="<liferay-ui:message key="save" />" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="Liferay.Popup.close(this);" />

</form>

<c:if test="<%= windowState.equals(LiferayWindowState.EXCLUSIVE) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm1.<portlet:namespace />name);
	</script>
</c:if>