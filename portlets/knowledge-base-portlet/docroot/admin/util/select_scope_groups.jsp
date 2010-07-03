<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/init.jsp" %>

<liferay-ui:header
	title="scopes"
/>

<aui:form name="fm">
	<aui:fieldset>
		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="jspPage" value='<%= jspPath + "select_scope_groups.jsp" %>' />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-scopes"
			headerNames="name"
			iteratorURL="<%= iteratorURL %>"
		>
			<liferay-ui:search-container-results>

				<%
				List<Group> scopeGroups = new ArrayList<Group>();

				scopeGroups.add(themeDisplay.getScopeGroup());

				for (Layout curLayout : LayoutLocalServiceUtil.getLayouts(layout.getGroupId(), layout.isPrivateLayout())) {
					if (curLayout.hasScopeGroup()) {
						scopeGroups.add(curLayout.getScopeGroup());
					}
				}

				pageContext.setAttribute("results", ListUtil.subList(scopeGroups, searchContainer.getStart(), searchContainer.getEnd()));
				pageContext.setAttribute("total", scopeGroups.size());
				%>

			</liferay-ui:search-container-results>

			<liferay-ui:search-container-row
				className="com.liferay.portal.model.Group"
				keyProperty="groupId"
				modelVar="scopeGroup"
			>
				<liferay-ui:search-container-column-text
					name="name"
					property="descriptiveName"
				/>

				<liferay-ui:search-container-column-text
					align="right"
				>
					<div id="<portlet:namespace />scopeGroup<%= scopeGroup.getGroupId() %>Icons">
						<span id="<portlet:namespace />addScopeGroup<%= scopeGroup.getGroupId() %>">

							<%
							String taglibURL = "javascript:" + renderResponse.getNamespace() + "addScopeGroup(" + scopeGroup.getGroupId() + ", '" + UnicodeFormatter.toString(scopeGroup.getDescriptiveName()) + "');";
							%>

							<liferay-ui:icon
								image="../common/add"
								label="<%= true %>"
								message="add"
								method="get"
								url="<%= taglibURL %>"
							/>
						</span>

						<span id="<portlet:namespace />removeScopeGroup<%= scopeGroup.getGroupId() %>" style="display: none;">

							<%
							taglibURL = "javascript:" + renderResponse.getNamespace() + "removeScopeGroup(" + scopeGroup.getGroupId() + ", '" + UnicodeFormatter.toString(scopeGroup.getDescriptiveName()) + "');";
							%>

							<liferay-ui:icon
								image="../arrows/02_x"
								label="<%= true %>"
								message="remove"
								method="get"
								url="<%= taglibURL %>"
							/>
						</span>
					</div>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<table class="lfr-table">
			<tr>
				<td>
					<aui:select cssClass="kb-select-box" inlineField="<%= true %>" label="" name="scopeGroupIdsBox" size="10" />
				</td>
				<td class="lfr-top">

					<%
					String taglibOnClick = renderResponse.getNamespace() + "reorderScopeGroups(0);";
					%>

					<aui:a href="javascript:;" onClick="<%= taglibOnClick %>"><img border="0" height="16" hspace="0" src="<%= themeDisplay.getPathThemeImages() %>/arrows/02_up.png" vspace="2" width="16" /></aui:a><br />

					<%
					taglibOnClick = renderResponse.getNamespace() + "reorderScopeGroups(1);";
					%>

					<aui:a href="javascript:;" onClick="<%= taglibOnClick %>"><img border="0" height="16" hspace="0" src="<%= themeDisplay.getPathThemeImages() %>/arrows/02_down.png" vspace="2" width="16" /></aui:a><br />

					<%
					taglibOnClick = renderResponse.getNamespace() + "removeSelectedScopeGroup();";
					%>

					<aui:a href="javascript:;" onClick="<%= taglibOnClick %>"><img border="0" height="16" hspace="0" src="<%= themeDisplay.getPathThemeImages() %>/arrows/02_x.png" vspace="2" width="16" /></aui:a>
				</td>
			</tr>
			</table>

			<div class="separator"><!-- --></div>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>

<aui:script>
	function <portlet:namespace />addScopeGroup(scopeGroupId, name) {
		var box = document.<portlet:namespace />fm.<portlet:namespace />scopeGroupIdsBox;

		for (var i = box.options.length; i > 0; i--) {
			box.options[i] = new Option(box.options[i - 1].text, box.options[i - 1].value);
		}

		box.options[0] = new Option(name, scopeGroupId, false, true);

		document.getElementById("<portlet:namespace />addScopeGroup" + scopeGroupId).style.display = "none";
		document.getElementById("<portlet:namespace />removeScopeGroup" + scopeGroupId).style.display = "";

		<portlet:namespace />selectScopeGroups(box);
	}

	function <portlet:namespace />removeScopeGroup(scopeGroupId, name) {
		var box = document.<portlet:namespace />fm.<portlet:namespace />scopeGroupIdsBox;

		for (var i = 0; i < box.options.length; i++) {
			if (box.options[i].value == scopeGroupId) {
				box.remove(i);
				box.selectedIndex = 0;

				break;
			}
		}

		document.getElementById("<portlet:namespace />addScopeGroup" + scopeGroupId).style.display = "";
		document.getElementById("<portlet:namespace />removeScopeGroup" + scopeGroupId).style.display = "none";

		<portlet:namespace />selectScopeGroups(box);
	}

	function <portlet:namespace />removeSelectedScopeGroup() {
		var box = document.<portlet:namespace />fm.<portlet:namespace />scopeGroupIdsBox;

		if (box.selectedIndex != -1) {
			var scopeGroupId = box.options[box.selectedIndex].value;

			box.remove(box.selectedIndex);
			box.selectedIndex = 0;

			var scopeGroupIcons = document.getElementById("<portlet:namespace />scopeGroup" + scopeGroupId + "Icons");

			if (scopeGroupIcons) {
				document.getElementById("<portlet:namespace />addScopeGroup" + scopeGroupId).style.display = "";
				document.getElementById("<portlet:namespace />removeScopeGroup" + scopeGroupId).style.display = "none";
			}

			<portlet:namespace />selectScopeGroups(box);
		}
	}

	function <portlet:namespace />reorderScopeGroups(down) {
		Liferay.Util.reorder(document.<portlet:namespace />fm.<portlet:namespace />scopeGroupIdsBox, down);

		<portlet:namespace />selectScopeGroups(document.<portlet:namespace />fm.<portlet:namespace />scopeGroupIdsBox);
	}

	function <portlet:namespace />selectScopeGroups(box) {
		var scopeGroupIds = [];
		var names = [];

		for (var i = 0; i < box.options.length; i++) {
			scopeGroupIds.push(box.options[i].value);
			names.push(box.options[i].text);
		}

		opener.<%= PortalUtil.getPortletNamespace(PortletKeys.PORTLET_CONFIGURATION) %>selectScopeGroups(scopeGroupIds, names);
	}

	function <portlet:namespace />updateScopeGroups() {
		var scopeGroupIdsValue = opener.document.<%= PortalUtil.getPortletNamespace(PortletKeys.PORTLET_CONFIGURATION) %>fm.<%= PortalUtil.getPortletNamespace(PortletKeys.PORTLET_CONFIGURATION) %>scopeGroupIds.value;

		if (scopeGroupIdsValue != "") {
			var scopeGroupIds = scopeGroupIdsValue.split(",");

			for (var i = 0; i < scopeGroupIds.length; i++) {
				var nameElement = opener.document.getElementById("<%= PortalUtil.getPortletNamespace(PortletKeys.PORTLET_CONFIGURATION) %>scopeGroup" + scopeGroupIds[i]);

				if (nameElement) {
					var box = document.<portlet:namespace />fm.<portlet:namespace />scopeGroupIdsBox;

					box.options[i] = new Option(nameElement.innerHTML, scopeGroupIds[i], false, i == 0);
				}

				var scopeGroupIcons = document.getElementById("<portlet:namespace />scopeGroup" + scopeGroupIds[i] + "Icons");

				if (scopeGroupIcons) {
					document.getElementById("<portlet:namespace />addScopeGroup" + scopeGroupIds[i]).style.display = "none";
					document.getElementById("<portlet:namespace />removeScopeGroup" + scopeGroupIds[i]).style.display = "";
				}
			}
		}
	}

	<portlet:namespace />updateScopeGroups();
</aui:script>