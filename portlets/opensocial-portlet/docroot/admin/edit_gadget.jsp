<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

long gadgetId = ParamUtil.getLong(request, "gadgetId");

Gadget gadget = null;

try {
	gadget = GadgetLocalServiceUtil.getGadget(gadgetId);
}
catch (NoSuchGadgetException nsge) {
}

String portletCategoryNames = StringPool.BLANK;

if (gadget != null) {
	portletCategoryNames = gadget.getPortletCategoryNames();
}

String editorGadgetURL = ParamUtil.getString(request, "editorGadgetURL");

boolean editorGadget = false;

String publishGadgetRedirect = StringPool.BLANK;

if (Validator.isNotNull(editorGadgetURL)) {
	editorGadget = true;

	PortletURL publishGadgetRedirectURL = renderResponse.createRenderURL();

	publishGadgetRedirectURL.setParameter("mvcPath", "/editor/publish_gadget_redirect.jsp");

	publishGadgetRedirectURL.setWindowState(LiferayWindowState.POP_UP);

	publishGadgetRedirect = publishGadgetRedirectURL.toString();
}
%>

<liferay-ui:header
	backURL="<%= editorGadget ? StringPool.BLANK : redirect %>"
	title='<%= (gadget != null) ? gadget.getName() : "new-gadget" %>'
/>

<portlet:actionURL name="updateGadget" var="updateGadgetURL" />

<aui:form action="<%= updateGadgetURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveGadget();" %>'>
	<aui:input name="mvcPath" type="hidden" value="/admin/edit_gadget.jsp" />
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (gadget == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="gadgetId" type="hidden" value="<%= gadgetId %>" />
	<aui:input name="editorGadgetURL" type="hidden" value="<%= editorGadgetURL %>" />
	<aui:input name="portletCategoryNames" type="hidden" value="<%= portletCategoryNames %>" />
	<aui:input name="publishGadgetRedirect" type="hidden" value="<%= publishGadgetRedirect %>" />

	<liferay-ui:error exception="<%= DuplicateGadgetURLException.class %>" message="url-already-points-to-an-existing-gadget" />
	<liferay-ui:error exception="<%= GadgetPortletCategoryNamesException.class %>" message="select-at-least-one-category" />
	<liferay-ui:error exception="<%= GadgetURLException.class %>" message="url-does-not-point-to-a-valid-gadget" />

	<aui:model-context bean="<%= gadget %>" model="<%= Gadget.class %>" />

	<aui:fieldset>
		<c:choose>
			<c:when test="<%= editorGadget %>">
				<aui:input name="url" type="resource" value="<%= editorGadgetURL %>" />
			</c:when>
			<c:when test="<%= gadget != null %>">
				<aui:input name="url" type="resource" value="<%= gadget.getUrl() %>" />
			</c:when>
			<c:otherwise>
				<aui:input name="url" />
			</c:otherwise>
		</c:choose>

		<h4><liferay-ui:message key="category" /></h4>

		<div class="category-treeview" id="<portlet:namespace />categoryTreeView"></div>

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script>
	function <portlet:namespace />saveGadget() {
		submitForm(document.<portlet:namespace />fm);
	}

	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
</aui:script>

<aui:script use="aui-tree-view">
	var selectedPortletCategoryNamesNode = A.one('#<portlet:namespace />portletCategoryNames');

	var portletCategoryNames = selectedPortletCategoryNamesNode.val();

	var selectedPortletCategoryNames = [];

	if (portletCategoryNames) {
		selectedPortletCategoryNames = portletCategoryNames.split(',');
	}

	var CategoryTreeNode = A.Component.create(
		{
			ATTRS: {
				category: {
					value: ''
				}
			},

			EXTENDS: A.TreeNodeCheck,

			NAME: 'CategoryTreeNode'
		}
	);

	var onCheckedChange = function(event) {
		var category = event.target.get('category');

		if (event.newVal) {
			if (selectedPortletCategoryNames.indexOf(category) == -1) {
				selectedPortletCategoryNames.push(category);

				selectedPortletCategoryNamesNode.val(selectedPortletCategoryNames.join());
			}
		}
		else {
			A.Array.removeItem(selectedPortletCategoryNames, category);

			selectedPortletCategoryNamesNode.val(selectedPortletCategoryNames.join());
		}
	};

	var treeView = new A.TreeView(
		{
			boundingBox: '#<portlet:namespace />categoryTreeView',
			on: {
				'*:checkedChange': onCheckedChange
			},
			type: 'normal'
		}
	).render();

	<%
	PortletLister portletLister = PortletListerFactoryUtil.getPortletLister();

	portletLister.setIteratePortlets(false);
	portletLister.setThemeDisplay(themeDisplay);
	portletLister.setUser(user);

	TreeView treeView = portletLister.getTreeView();

	for (TreeNodeView treeNodeView : treeView.getList()) {
	%>

		var category = '<%= treeNodeView.getObjId() %>';

		var checked = <%= gadget == null %> && category == 'root//category.gadgets' || selectedPortletCategoryNames.indexOf(category) > -1;

		var categoryTreeNode = new CategoryTreeNode(
			{
				alwaysShowHitArea: false,
				category: category,
				checked: checked,
				id: '<%= treeNodeView.getId() %>',
				label: '<%= UnicodeFormatter.toString(LanguageUtil.get(request, treeNodeView.getName())) %>',
				leaf: false
			}
		);

		var parentNode = treeView.getNodeById('<%= treeNodeView.getParentId() %>') || treeView;

		parentNode.appendChild(categoryTreeNode);

	<%
	}
	%>

	treeView.expandAll();
</aui:script>

<%
if (gadget == null) {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "publish-gadget"), currentURL);
}
else {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "edit"), currentURL);
}
%>