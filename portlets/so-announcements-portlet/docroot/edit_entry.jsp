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
String redirect = ParamUtil.getString(request, "redirect");

String redirectMvcPath = HttpUtil.getParameter(HttpUtil.decodeURL(redirect), renderResponse.getNamespace() + "mvcPath", false);

long entryId = ParamUtil.getLong(request, "entryId");

AnnouncementsEntry entry = AnnouncementsEntryLocalServiceUtil.fetchAnnouncementsEntry(entryId);

String content = BeanParamUtil.getString(entry, request, "content");

boolean displayImmediately = ParamUtil.getBoolean(request, "displayImmediately");

if (entry == null) {
	displayImmediately = true;
}
%>

<div id="<portlet:namespace />errorMessage"></div>

<aui:form method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveEntry();" %>'>
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="entryId" type="hidden" value="<%= entryId %>" />
	<aui:input name="alert" type="hidden" value="<%= portletName.equals(PortletKeys.ALERTS) %>" />

	<aui:model-context bean="<%= entry %>" model="<%= AnnouncementsEntry.class %>" />

	<aui:fieldset>
		<c:if test='<%= redirectMvcPath.equals("/manage_entries.jsp") %>'>
			<span class="back-link"><a href="<%= HtmlUtil.escape(redirect) %>">&laquo; Back</a></span>
		</c:if>

		<c:choose>
			<c:when test="<%= entry != null %>">

				<%@ include file="/entry_scope.jspf" %>

				<aui:input name="scope" type="hidden" value="<%= scopeName %>" />
			</c:when>
			<c:otherwise>

				<%
				String distributionScope = ParamUtil.getString(request, "distributionScope");

				long classNameId = 0;
				long classPK = 0;

				String[] distributionScopeArray = StringUtil.split(distributionScope);

				if (distributionScopeArray.length == 2) {
					classNameId = GetterUtil.getLong(distributionScopeArray[0]);
					classPK = GetterUtil.getLong(distributionScopeArray[1]);
				}
				else if (!group.isUser()) {
					classNameId = PortalUtil.getClassNameId(Group.class);
					classPK = themeDisplay.getScopeGroupId();
				}

				boolean submitOnChange = false;
				%>

				<div class="distribution-scope-container">
					<%@ include file="/entry_select_scope.jspf" %>
				</div>
			</c:otherwise>
		</c:choose>

		<aui:select cssClass="type" name="type">

			<%
			for (String curType : AnnouncementsEntryConstants.TYPES) {
			%>

				<aui:option label="<%= curType %>" selected="<%= (entry != null) && curType.equals(entry.getType()) %>" />

			<%
			}
			%>

		</aui:select>

		<aui:select cssClass="priority" name="priority">
			<aui:option label="normal" selected="<%= (entry != null) && (entry.getPriority() == 0) %>" value="0" />
			<aui:option label="important" selected="<%= (entry != null) && (entry.getPriority() == 1) %>" value="1" />
		</aui:select>

		<div class="title-container">
			<aui:input cssClass="title" name="title">
				<aui:validator name="required" />
			</aui:input>

			<aui:input cssClass="url" name="url" />
		</div>

		<aui:field-wrapper label="content">
			<liferay-ui:input-editor contents="<%= content %>" height="150" toolbarSet="Basic" width="100%" />

			<aui:input name="content" type="hidden" />
		</aui:field-wrapper>

		<div class="date-container">
			<aui:input cssClass="display-date" dateTogglerCheckboxLabel="display-immediately" disabled="<%= displayImmediately %>" name="displayDate" />

			<aui:input cssClass="expiration-date" name="expirationDate" />
		</div>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button onClick='<%= renderResponse.getNamespace() + "previewEntry();" %>' value="preview" />

		<aui:button onClick='<%= renderResponse.getNamespace() + "closeEntry();" %>' value="cancel" />
	</aui:button-row>
</aui:form>

<div class="entries preview unread-entries">
	<div class="clearfix entry hide" id="<portlet:namespace />preview">
		<div class="user-portrait">
			<span class="avatar">

				<%
				User currentUser = UserLocalServiceUtil.getUserById(themeDisplay.getUserId());
				%>

				<a href="<%= currentUser.getDisplayURL(themeDisplay) %>">
					<img alt="<%= HtmlUtil.escapeAttribute(currentUser.getFullName()) %>" src="<%= currentUser.getPortraitURL(themeDisplay) %>" />
				</a>
			</span>
		</div>

		<div class="entry-header">
			<div class="entry-action">
				<%= LanguageUtil.format(request, "x-to-x", new Object[] {"<a href=\"" + currentUser.getDisplayURL(themeDisplay) + "\">" + HtmlUtil.escape(currentUser.getFullName()) + "</a>", "<span class=\"scope\" id=\"" + renderResponse.getNamespace() + "scope\"></span>"}, false) %>
			</div>

			<div class="entry-time">
				<%= LanguageUtil.get(request, "about-a-minute-ago") %>
			</div>
		</div>

		<div class="entry-block">
			<div class="entry-body">
				<div class="title" id="<portlet:namespace />title"></div>

				<div class="entry-content-container" id="<portlet:namespace />entryContentContainer">
					<div class="entry-content" id="<portlet:namespace />entryContent"></div>
				</div>
			</div>

			<div class="entry-footer" id="<portlet:namespace />entryFooter">
				<div class="entry-footer-toolbar">
					<div class="edit-actions">
						<span class="action hide toggle">
							<a class="toggle-entry" data-entryId="preview" href="javascript:;">
								<i class="icon-expand-alt"></i>

								<span><liferay-ui:message key="view-more" /></span>
							</a>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<aui:script>
	function <portlet:namespace />closeEntry() {
		Liferay.Util.getWindow('<portlet:namespace />Dialog').hide();
	}

	function <portlet:namespace />previewEntry() {
		var A = AUI();

		var preview = A.one('#<portlet:namespace />preview');

		if (preview.hasClass('hide')) {
			preview.removeClass('hide');
		}

		var priority = A.one('#<portlet:namespace />priority')._node.selectedIndex;

		if (priority == 1) {
			preview.addClass('important-entry');
		}
		else {
			preview.removeClass('important-entry');
		}

		var scope;

		if (<%= entry != null %>) {
			scope = A.one('#<portlet:namespace />scope').get('value');
		}
		else {
			var optValue = A.one('select[name="<portlet:namespace />distributionScope"]').get('value');

			scope = A.one('option[value=' + optValue + ']').get('text');
		}

		A.one('#<portlet:namespace />scope').html(scope);

		var url = A.one('#<portlet:namespace />url').get('value');

		var title;

		if (url.length != 0) {
			title = '<a href="' + url + '">' + A.one('#<portlet:namespace />title').get('value') + '</a>';
		}
		else {
			title = A.one('#<portlet:namespace />title').get('value');
		}

		A.one('.preview #<portlet:namespace />title').html(title);

		var content = window.<portlet:namespace />editor.getHTML();

		var previewContent = A.one('#<portlet:namespace />entryContent');

		previewContent.html(content);

		var previewFooter = A.one('#<portlet:namespace />entryFooter');

		if (previewContent.height() > 75) {
			var toggle = preview.one('.toggle');

			toggle.removeClass('hide');

			preview.addClass('announcement-collapsed');
		}
		else {
			var contentContainer = preview.one('.entry-content-container');

			contentContainer.setStyle('height', 'auto');
		}
	}

	function <portlet:namespace />saveEntry() {
		var A = AUI();

		var form = document.<portlet:namespace />fm;

		form.<portlet:namespace />content.value = window.<portlet:namespace />editor.getHTML();
		form.target = '';

		var uri = '<liferay-portlet:actionURL name="saveEntry"><portlet:param name="redirect" value="<%= currentURL %>" /></liferay-portlet:actionURL>';

		A.io.request(
			uri,
			{
				after: {
					success: function(event, id, obj) {
						var responseData = this.get('responseData');

						if (!responseData.success) {
							var message = A.one('#<portlet:namespace />errorMessage');

							if (message) {
								message.html('<span class="alert alert-danger">' + responseData.message + '</span>');
							}
						}
						else if (<%= redirectMvcPath.equals("/manage_entries.jsp") %>) {
							window.location.href = responseData.redirect;
						}
						else {
							Liferay.Util.getWindow('<portlet:namespace />Dialog').hide();
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
</aui:script>

<aui:script use="aui-base">
	var announcementEntries = A.one('#main-content');

	announcementEntries.delegate(
		'click',
		function(event) {
			Liferay.Announcements.toggleEntry(event);
		},
		'.toggle-entry'
	);
</aui:script>