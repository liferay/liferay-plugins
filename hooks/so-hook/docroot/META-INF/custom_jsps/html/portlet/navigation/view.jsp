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
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/html/portlet/navigation/init.jsp" %>

<%
Group group = themeDisplay.getScopeGroup();

bulletStyle = PrefsParamUtil.getString(preferences, renderRequest, "bulletStyle", StringPool.BLANK);
displayStyle = PrefsParamUtil.getString(preferences, renderRequest, "displayStyle", "from-level-0");
%>

<liferay-ui:navigation
	bulletStyle="<%= bulletStyle %>"
	displayStyle="<%= displayStyle %>"
	headerType="<%= headerType %>"
	includedLayouts="<%= includedLayouts %>"
	nestedChildren="<%= nestedChildren %>"
	rootLayoutLevel="<%= rootLayoutLevel %>"
	rootLayoutType="<%= rootLayoutType %>"
/>

<c:if test='<%= group.isUser() && displayStyle.equals("from-level-0") %>'>
	<div class="control-container">
	</div>

	<aui:script use="aui-base,aui-dialog,aui-dialog-iframe,aui-io,aui-toolbar">
		var controlContainer = A.one('.portlet-navigation .control-container');

		var navMenu = A.one('.portlet-navigation .nav-menu');

		var addPage = function() {
			var icons = [
				{
					handler: function() {
						cancelPage(addBlock, comboBox);
					},
					icon: 'circle-close',
					id: 'cancel'
				},
				{
					handler: function() {
						savePage(comboField);
					},
					icon: 'circle-check',
					id: 'save'
				}
			];

			var onKeypress = function(event) {
				if (event.isKey('ESC')) {
					cancelPage(addBlock, comboBox);
				}

				if (event.isKey('ENTER')) {
					savePage(comboField);
				}
			};

			var addBlock = A.Node.create('<li class="add-page"></li>');
			var navBlock = A.one('.portlet-navigation .nav-menu');

			addBlock.delegate('keypress', A.bind(onKeypress, this), 'input');

			navBlock.append(addBlock);

			var comboBox = new A.Combobox(
				{
					icons: icons
				}
			).render(addBlock);

			var comboField = comboBox._field;

			Liferay.Util.focusFormField(comboField.get('node'));
		};

		var addPageButton = new A.Toolbar(
			{
				children: [
					{
						icon: 'plusthick',
						label: '<liferay-ui:message key="add-page" />',
						on: {
							click: function() {
								addPage();
							}
						}
					},
					{
						label: '<liferay-ui:message key="manage" />',
						on: {
							click: function() {
								displayPopup('<%= themeDisplay.getURLSiteMapSettings().toString() %>', '<liferay-ui:message key="site-pages" />');
							}
						}
					}
				]
			}
		).render(controlContainer);

		var cancelPage = function(listItem, comboBox) {
			listItem.remove(true);

			comboBox.destroy();
		};

		var displayPopup = function(url, title) {
			var dialog = new A.Dialog(
				{
					align: {
						node: null,
						points: ['tc', 'tc']
					},
					constrain2view: true,
					cssClass: 'portlet-navigation',
					modal: true,
					resizable: false,
					title: title,
					width: 950
				}
			).plug(
				A.Plugin.DialogIframe,
				{
					uri: url
				}
			).render();
		};

		var savePage = function(comboField) {
			var pageTitle = comboField.get('value');

			if (pageTitle) {
				A.io.request(
					'<liferay-portlet:actionURL portletName="<%= PortletKeys.SITES_ADMIN %>"><portlet:param name="struts_action" value="/sites_admin/edit_layouts" /><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" /></liferay-portlet:actionURL>',
					{
						data: {
							addUserDefaults: true,
							groupId: themeDisplay.getParentGroupId(),
							pageTitle: pageTitle,
							parentLayoutId: themeDisplay.getParentLayoutId()
						},
						on: {
							success: updatePages
						}

					}
				);
			}
		};

		var updatePages = function() {
			var pages = A.one('.portlet-navigation .portlet-body');

			if (!pages.io) {
				pages.plug(
					A.Plugin.IO,
					{
						autoLoad: false
					}
				);
			}

			pages.io.set('uri', '<liferay-portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="struts_action" value="/navigation/view" /></liferay-portlet:renderURL>');

			pages.io.start();
		};
	</aui:script>
</c:if>