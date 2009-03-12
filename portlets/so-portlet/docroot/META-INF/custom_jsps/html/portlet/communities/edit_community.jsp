<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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

<input type="submit" value="<liferay-ui:message key="save" />" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="Liferay.Popup.close(this);" />

</form>

<c:if test="<%= windowState.equals(LiferayWindowState.EXCLUSIVE) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm1.<portlet:namespace />name);
	</script>
</c:if>