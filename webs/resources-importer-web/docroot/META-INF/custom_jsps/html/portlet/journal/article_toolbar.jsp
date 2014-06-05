<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/journal/init.jsp" %>

<%
String referringPortletResource = ParamUtil.getString(request, "referringPortletResource");

JournalArticle article = (JournalArticle)request.getAttribute(WebKeys.JOURNAL_ARTICLE);

long classNameId = BeanParamUtil.getLong(article, request, "classNameId");
%>

<div class="article-toolbar" id="<portlet:namespace />articleToolbar"></div>

<aui:script use="aui-toolbar,aui-dialog-iframe-deprecated,aui-tooltip,liferay-util-window">
	var permissionPopUp = null;

	var toolbarButtonGroup = [];

	<c:if test="<%= classNameId == JournalArticleConstants.CLASSNAME_ID_DEFAULT %>">
		<liferay-portlet:renderURL plid="<%= JournalUtil.getPreviewPlid(article, themeDisplay) %>" var="previewArticleContentURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="struts_action" value="/journal/preview_article_content" />
			<portlet:param name="groupId" value="<%= String.valueOf(article.getGroupId()) %>" />
			<portlet:param name="articleId" value="<%= article.getArticleId() %>" />
			<portlet:param name="version" value="<%= String.valueOf(article.getVersion()) %>" />
		</liferay-portlet:renderURL>

		var form = A.one(document.<portlet:namespace />fm1);

		var formChanged = false;

		var hasUnsavedChanges = function() {
			var unsavedChanges = formChanged;

			if (!unsavedChanges && typeof CKEDITOR !== 'undefined') {
				A.Object.some(
					CKEDITOR.instances,
					function(item, index, collection) {
						var parentForm = A.one('#' + item.element.getId()).ancestor('form');

						if (parentForm.compareTo(form)) {
							unsavedChanges = item.checkDirty();

							return unsavedChanges;
						}
					}
				);
			}

			return unsavedChanges;
		};

		var previewArticleContentURL = '<%= previewArticleContentURL %>';

		form.delegate(
			'change',
			function(event) {
				formChanged = true;
			},
			':input'
		);

		toolbarButtonGroup.push(
			{
				icon: 'icon-search',
				id: '<portlet:namespace/>basicPreviewButton',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "basic-preview") %>',
				on: {
					click: function(event) {
						event.domEvent.preventDefault();

						if (!hasUnsavedChanges()) {
							Liferay.fire(
								'previewArticle',
								{
									title: '<%= HtmlUtil.escapeJS(article.getTitle(locale)) %>',
									uri: '<%= HtmlUtil.escapeJS(previewArticleContentURL.toString()) %>'
								}
							);
						}
						else if (confirm('<liferay-ui:message key="in-order-to-preview-your-changes,-the-web-content-will-be-saved-as-a-draft" />')) {
							var hasStructure = window.<portlet:namespace />journalPortlet.hasStructure();
							var hasTemplate = window.<portlet:namespace />journalPortlet.hasTemplate();
							var updateStructureDefaultValues = window.<portlet:namespace />journalPortlet.updateStructureDefaultValues();

							if (hasStructure && !hasTemplate && !updateStructureDefaultValues) {
								window.<portlet:namespace />journalPortlet.displayTemplateMessage();
							}
							else {
								form.one('#<portlet:namespace /><%= Constants.CMD %>').val('<%= Constants.PREVIEW %>');

								submitForm(form);
							}
						}
					},
					render: function(event) {
						new A.Tooltip(
							{
								trigger: '#<portlet:namespace/>basicPreviewButton'
							}
						).render();
					}
				},
				title: '<liferay-ui:message key="this-preview-won't-include-the-theme-context" />'
			}
		);
	</c:if>

	<c:if test="<%= JournalArticlePermission.contains(permissionChecker, article, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"
			modelResource="<%= JournalArticle.class.getName() %>"
			modelResourceDescription="<%= article.getTitle(locale) %>"
			resourcePrimKey="<%= String.valueOf(article.getResourcePrimKey()) %>"
			var="permissionsURL"
		/>

		toolbarButtonGroup.push(
			{
				icon: 'icon-lock',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "permissions") %>',
				on: {
					click: function(event) {
						if (!permissionPopUp) {
							permissionPopUp = Liferay.Util.openWindow(
								{
									dialog: {
										cssClass: 'portlet-asset-categories-admin-dialog permissions-change'
									},
									id: '<portlet:namespace />articlePermissions',
									title: '<%= UnicodeLanguageUtil.get(pageContext, "permissions") %>',
									uri: '<%= permissionsURL %>'
								}
							);
						}
						else {
							permissionPopUp.iframe.node.get('contentWindow.location').reload(true);
						}

						event.domEvent.preventDefault();
					}
				}
			}
		);
	</c:if>

	<portlet:renderURL var="viewHistoryURL">
		<portlet:param name="struts_action" value="/journal/view_article_history" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="referringPortletResource" value="<%= referringPortletResource %>" />
		<portlet:param name="articleId" value="<%= article.getArticleId() %>" />
	</portlet:renderURL>

	toolbarButtonGroup.push(
		{
			icon: 'icon-time',
			label: '<%= UnicodeLanguageUtil.get(pageContext, "view-history") %>',
			on: {
				click: function(event) {
					window.location = '<%= viewHistoryURL %>';

					event.domEvent.preventDefault();
				}
			}
		}
	);

	new A.Toolbar(
		{
			boundingBox: '#<portlet:namespace />articleToolbar',
			children: [toolbarButtonGroup]
		}
	).render();
</aui:script>