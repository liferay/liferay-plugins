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

<%@ include file="/sites/init.jsp" %>

<%
String keywords = ParamUtil.getString(request, "keywords");
boolean userSites = ParamUtil.getBoolean(request, "userSites");

String searchKeywords = DAOParamUtil.getLike(request, "keywords");

LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

if (userSites) {
	params.put("usersGroups", themeDisplay.getUserId());
}
else {
	List<Integer> types = new ArrayList<Integer>();

	types.add(GroupConstants.TYPE_SITE_OPEN);
	types.add(GroupConstants.TYPE_SITE_RESTRICTED);

	params.put("types", types);
}

List<Group> groups = GroupLocalServiceUtil.search(themeDisplay.getCompanyId(), searchKeywords, null, params, 0, 20, new GroupNameComparator(true));

int totalGroups = GroupLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), searchKeywords, null, params);
%>

<div class="directory">
	<liferay-ui:header title="directory" />

	<div class="search">
		<div class="buttons-left">
			<input id="<portlet:namespace />dialogKeywords" size="30" type="text" value="<%= HtmlUtil.escape(keywords) %>" />

			<span>
				<input <%= userSites ? "checked" : StringPool.BLANK %> id="<portlet:namespace />userSites" name="<portlet:namespace />userSites" type="checkbox" />

				<label for="<portlet:namespace />userSites"><liferay-ui:message key="my-sites" /></label>
			</span>
		</div>

		<div class="buttons-right">
			<aui:button cssClass="previous" disabled="<%= true %>" value="previous" />

			<aui:button cssClass="next" disabled="<%= totalGroups < 20 %>" value="next" />
		</div>

		<div style="clear: both;"><!-- --></div>
	</div>

	<ul class="directory-list">

		<%
		boolean alternate = false;

		for (Group group : groups) {
			String classNames = StringPool.BLANK;

			if (GetterUtil.getBoolean(group.getExpandoBridge().getAttribute("socialOfficeEnabled"))) {
				classNames += "social-office-enabled ";
			}

			boolean member = GroupLocalServiceUtil.hasUserGroup(themeDisplay.getUserId(), group.getGroupId());

			if (member) {
				classNames += "member ";
			}

			if (alternate) {
				classNames += "alt";
			}
		%>

			<li class="<%= classNames %>">
				<c:if test="<%= !GroupLocalServiceUtil.hasUserGroup(themeDisplay.getUserId(), group.getGroupId()) && GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.ASSIGN_MEMBERS) %>">
					<liferay-portlet:actionURL windowState="<%= WindowState.NORMAL.toString() %>" portletName="<%= PortletKeys.SITES_ADMIN %>" var="joinURL">
						<portlet:param name="struts_action" value="/sites_admin/edit_site_assignments" />
						<portlet:param name="<%= Constants.CMD %>" value="group_users" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
						<portlet:param name="addUserIds" value="<%= String.valueOf(user.getUserId()) %>" />
					</liferay-portlet:actionURL>

					<span class="join">
						<a href="<%= joinURL %>"><liferay-ui:message key="join" /></a>
					</span>
				</c:if>

				<span class="name">
					<c:choose>
						<c:when test="<%= group.hasPrivateLayouts() || group.hasPublicLayouts() %>">
							<liferay-portlet:actionURL windowState="<%= LiferayWindowState.NORMAL.toString() %>" portletName="<%= PortletKeys.MY_PLACES %>" var="siteURL">
								<portlet:param name="struts_action" value="/my_places/view" />
								<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
								<portlet:param name="privateLayout" value="<%= String.valueOf(!group.hasPublicLayouts()) %>" />
							</liferay-portlet:actionURL>

							<a href="<%= siteURL %>"><%= group.getDescriptiveName() %></a>
						</c:when>
						<c:otherwise>
							<%= group.getDescriptiveName() %>
						</c:otherwise>
					</c:choose>
				</span>

				<span class="description">
					<%= group.getDescription() %>
				</span>
			</li>

		<%
			alternate = !alternate;
		}
		%>

	</ul>

	<aui:button-row>
		<div class="directory-navigation buttons-left">
			<span class="page-indicator">
				<%= LanguageUtil.format(pageContext, "page-x-of-x", new String[] {"<span class=\"current\">1</span>", "<span class=\"total\">" + String.valueOf((int)Math.ceil(totalGroups / 20.0)) + "</span>"}) %>
			</span>
		</div>

		<div class="buttons-right">
			<aui:button onClick="Liferay.SO.Sites.closePopup()" value="close" />
		</div>
	</aui:button-row>
</div>

<aui:script use="datatype-number,liferay-so-site-list">
	var directoryContainer = A.one('.so-portlet-sites-dialog');

	var navigationContainer = directoryContainer.all('.directory-navigation');

	var currentPageNode = directoryContainer.one('.page-indicator .current');
	var totalPageNode = directoryContainer.one('.page-indicator .total');

	var keywordsInput = directoryContainer.one('#<portlet:namespace />dialogKeywords');
	var nextButton = directoryContainer.one('.search .next');
	var previousButton = directoryContainer.one('.search .previous');
	var userGroupsCheckbox = directoryContainer.one('#<portlet:namespace />userSites');

	var directoryList = new Liferay.SO.SiteList(
		{
			requestTemplate: function(query) {
				return {
					directory: true,
					end: 20,
					keywords: query,
					start: 0,
					userGroups: userGroupsCheckbox.get('checked')
				}
			},

			inputNode: '.so-portlet-sites-dialog #<portlet:namespace />dialogKeywords',
			listNode: '.so-portlet-sites-dialog .directory-list',
			minQueryLength: 0,
			source: Liferay.SO.Sites.createDataSource('<portlet:resourceURL id="getSites" />')
		}
	);

	var updateDirectoryList = function(event) {
		var data = A.JSON.parse(event.data.responseText);

		var results = data.sites;
		var count = data.count;

		var options = data.options;

		var buffer = [];

		if (results.length == 0) {
			buffer.push(
				'<li class="empty">' + Liferay.Language.get('there-are-no-results') + '</li>'
			);
		}
		else {
			var siteTemplate =
				'<li class="{classNames}">' +
					'{joinHtml}' +
					'<span class="name">{siteName}</span>' +
					'<span class="description">{siteDescription}</span>'
				'</li>';

			buffer.push(
				A.Array.map(
					results,
					function(result, index) {
						var classNames = [];
						var joinHtml = '';

						if (result.socialOfficeEnabled) {
							classNames.push('social-office-enabled');
						}

						if (!result.joinUrl) {
							classNames.push('member');
						}

						if ((index % 2) == 1) {
							classNames.push('alt');
						}

						var name = result.name;

						if (result.url) {
							name = '<a href="' + result.url + '">' + name + '</a>';
						}

						return A.Lang.sub(
							siteTemplate,
							{
								classNames: classNames.join(' '),
								joinHtml: (result.joinUrl ? '<span class="join"><a href="' + result.joinUrl + '">' + Liferay.Language.get('join') + '</a></span>' : ''),
								siteDescription: result.description,
								siteName: name
							}
						);
					}
				).join('')
			);
		}

		this._listNode.html(buffer.join(''));

		var currentPage = Math.floor(options.start/20) + 1;
		var totalPage = Math.ceil(count/20);

		currentPageNode.html(currentPage);
		totalPageNode.html(totalPage);

		if (currentPage <= 1) {
			Liferay.SO.Sites.disableButton(previousButton);
		}
		else {
			Liferay.SO.Sites.enableButton(previousButton);
		}

		if (currentPage >= totalPage) {
			Liferay.SO.Sites.disableButton(nextButton);
		}
		else {
			Liferay.SO.Sites.enableButton(nextButton);
		}
	};

	directoryList.on('results', updateDirectoryList);

	var getRequestTemplate = function(targetPage) {
		var start = (targetPage - 1) * 20;
		var end = start + 19;

		return function(query) {
			return {
				directory: true,
				end: end,
				keywords: query,
				start: start,
				userGroups: userGroupsCheckbox.get('checked')
			}
		};
	};

	nextButton.on(
		'click',
		function(event) {
			var currentPage = A.DataType.Number.parse(currentPageNode.html());
			var totalPage = A.DataType.Number.parse(totalPageNode.html());

			var targetPage = currentPage + 1;

			if (targetPage > totalPage) {
				return;
			}

			directoryList.sendRequest(keywordsInput.get('value'), getRequestTemplate(targetPage));
		}
	);

	previousButton.on(
		'click',
		function(event) {
			var currentPage = A.DataType.Number.parse(currentPageNode.html());

			var targetPage = currentPage - 1;

			if (targetPage <= 0) {
				return;
			}

			directoryList.sendRequest(keywordsInput.get('value'), getRequestTemplate(targetPage));
		}
	);

	userGroupsCheckbox.on(
		'change',
		function() {
			directoryList.sendRequest();
		}
	);

	directoryContainer.one('.directory-list').delegate(
		'click',
		function(event) {
			event.preventDefault();

			A.io.request(
				event.currentTarget.get('href'),
				{
					after: {
						success: function(event, id, obj) {
							Liferay.SO.Sites.updateSites();

							var targetPage = A.DataType.Number.parse(currentPageNode.html());

							directoryList.sendRequest(keywordsInput.get('value'), getRequestTemplate(targetPage));
						}
					}
				}
			);
		},
		'.join a'
	);
</aui:script>