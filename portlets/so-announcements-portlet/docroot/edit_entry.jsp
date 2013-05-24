<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
String redirect = PortalUtil.escapeRedirect(ParamUtil.getString(request, "redirect"));
String fromManageEntries = ParamUtil.getString(request, "fromManageEntries");

long entryId = ParamUtil.getLong(request, "entryId");

AnnouncementsEntry entry = AnnouncementsEntryLocalServiceUtil.fetchAnnouncementsEntry(entryId);

String content = BeanParamUtil.getString(entry, request, "content");

User currentUser = UserLocalServiceUtil.getUserById(themeDisplay.getUserId());
%>

<div id="<portlet:namespace />errorMessage"></div>

<aui:form method="post" name='<%= renderResponse.getNamespace() + "fm" %>' onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveEntry();" %>' useNamespace="false">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (entry == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="entryId" type="hidden" value="<%= entryId %>" />
	<aui:input name="alert" type="hidden" value="<%= portletName.equals(PortletKeys.ALERTS) %>" />

	<aui:model-context bean="<%= entry %>" model="<%= AnnouncementsEntry.class %>" />

	<aui:fieldset>
		<c:choose>
			<c:when test="<%= entry != null %>">

				<%
				boolean showScopeName = true;
				%>

				<%@ include file="/entry_scope.jspf" %>

				<aui:input name="scope" type="hidden" value="<%= scopeName %>" />
			</c:when>
			<c:otherwise>

				<%
				String distributionScope = ParamUtil.getString(request, "distributionScope");

				long classNameId = -1;
				long classPK = -1;

				String[] distributionScopeArray = StringUtil.split(distributionScope);

				if (distributionScopeArray.length == 2) {
					classNameId = GetterUtil.getLong(distributionScopeArray[0]);
					classPK = GetterUtil.getLong(distributionScopeArray[1]);
				}

				boolean submitOnChange = false;
				%>

				<%@ include file="/entry_select_scope.jspf" %>

			</c:otherwise>
		</c:choose>

		<aui:select cssClass="type" name="type">

			<%
			for (String curType : AnnouncementsEntryConstants.TYPES) {
			%>

			<aui:option label="<%= curType %>" />

			<%
			}
			%>

		</aui:select>

		<aui:select cssClass="priority" name="priority">
			<aui:option label="normal" value="0" />
			<aui:option label="important" value="1" />
		</aui:select>

		<div class="title-container">
			<aui:input cssClass="title" name="title">
				<aui:validator name="required" />
			</aui:input>

			<aui:input cssClass="url" name="url" />
		</div>

		<aui:field-wrapper label="content">
			<liferay-ui:input-editor height="150" toolbarSet="Basic" width="100%" />

			<aui:input name="content" type="hidden" />
		</aui:field-wrapper>

		<div class="date-container">
			<aui:input cssClass="display-date" name="displayDate" />

			<aui:input cssClass="expiration-date" name="expirationDate" />
		</div>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button onClick='<%= renderResponse.getNamespace() + "closeEntry();" %>' value="cancel" />
	</aui:button-row>
</aui:form>

<div class="entry" id="<%= renderResponse.getNamespace() + "preview" %>"></div>

<aui:script>
	function initEditor() {
		var ckEditor = CKEDITOR.instances["editor"];
		ckEditor.resize("100%", "200");

		return '<%= UnicodeFormatter.toString(content) %>';
	}

	function <portlet:namespace />closeEntry() {
		Liferay.Util.getWindow('<portlet:namespace />Dialog').close();
	}

	function <portlet:namespace />saveEntry() {
		var A = AUI();

		var form = document.<portlet:namespace />fm;

		form.target = '';
		form.<%= Constants.CMD %>.value = "<%= (entry == null) ? Constants.ADD : Constants.UPDATE %>";
		form.content.value = window.editor.getHTML();

		var uri = '<liferay-portlet:actionURL name="saveEntry"><portlet:param name="redirect" value="<%= currentURL %>" /></liferay-portlet:actionURL>';

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
							if (responseData.fromManageEntries) {
								window.location.href = '<%= redirect %>';
							}
							else {
								Liferay.Util.getWindow('<portlet:namespace />Dialog').close();

								var topWindow = Liferay.Util.getTop();

								topWindow.document.location.reload();
							}
						}
					}
				}
			}
		);
	}
</aui:script>