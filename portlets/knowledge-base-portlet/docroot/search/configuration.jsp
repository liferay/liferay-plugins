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

<%@ include file="/search/init.jsp" %>

<%
String tabs2 = ParamUtil.getString(request, "tabs2", "display-settings");

List<Group> scopeGroups = new ArrayList<Group>();

for (long curScopeGroupId : scopeGroupIds) {
	try {
		scopeGroups.add(GroupLocalServiceUtil.getGroup(curScopeGroupId));
	}
	catch (NoSuchGroupException nsge) {
		continue;
	}
}

List<Article> articles = KnowledgeBaseUtil.getArticles(resourcePrimKeys, QueryUtil.ALL_POS, QueryUtil.ALL_POS, false);
%>

<liferay-portlet:renderURL portletConfiguration="true" var="portletURL">
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
</liferay-portlet:renderURL>

<liferay-ui:tabs
	names="display-settings,selection-method,rss"
	param="tabs2"
	url="<%= portletURL %>"
/>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />
	<aui:input name="scopeGroupIds" type="hidden" value='<%= ListUtil.toString(scopeGroups, "groupId") %>' />
	<aui:input name="resourcePrimKeys" type="hidden" value='<%= ListUtil.toString(articles, "resourcePrimKey") %>' />

	<aui:fieldset>
		<c:choose>
			<c:when test='<%= tabs2.equals("display-settings") %>'>
				<aui:select label="maximum-items-to-display" name="articlesDelta">
					<aui:option label="1" selected="<%= articlesDelta == 1 %>" />
					<aui:option label="2" selected="<%= articlesDelta == 2 %>" />
					<aui:option label="3" selected="<%= articlesDelta == 3 %>" />
					<aui:option label="4" selected="<%= articlesDelta == 4 %>" />
					<aui:option label="5" selected="<%= articlesDelta == 5 %>" />
					<aui:option label="10" selected="<%= articlesDelta == 10 %>" />
					<aui:option label="15" selected="<%= articlesDelta == 15 %>" />
					<aui:option label="20" selected="<%= articlesDelta == 20 %>" />
					<aui:option label="25" selected="<%= articlesDelta == 25 %>" />
					<aui:option label="30" selected="<%= articlesDelta == 30 %>" />
					<aui:option label="40" selected="<%= articlesDelta == 40 %>" />
					<aui:option label="50" selected="<%= articlesDelta == 50 %>" />
					<aui:option label="60" selected="<%= articlesDelta == 60 %>" />
					<aui:option label="70" selected="<%= articlesDelta == 70 %>" />
					<aui:option label="80" selected="<%= articlesDelta == 80 %>" />
					<aui:option label="90" selected="<%= articlesDelta == 90 %>" />
					<aui:option label="100" selected="<%= articlesDelta == 100 %>" />
				</aui:select>

				<aui:select name="childArticlesDisplayStyle">
					<aui:option label="<%= RSSUtil.DISPLAY_STYLE_ABSTRACT %>" selected="<%= childArticlesDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_ABSTRACT) %>" />
					<aui:option label="<%= RSSUtil.DISPLAY_STYLE_FULL_CONTENT %>" selected="<%= childArticlesDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_FULL_CONTENT) %>" />
					<aui:option label="<%= RSSUtil.DISPLAY_STYLE_TITLE %>" selected="<%= childArticlesDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_TITLE) %>" />
				</aui:select>

				<aui:input inlineLabel="left" label="enable-comments" name="enableArticleComments" type="checkbox" value="<%= enableArticleComments %>" />

				<aui:input inlineLabel="left" label="enable-comment-ratings" name="enableArticleCommentRatings" type="checkbox" value="<%= enableArticleCommentRatings %>" />
			</c:when>
			<c:when test='<%= tabs2.equals("selection-method") %>'>

				<%
				String taglibOnChange = renderResponse.getNamespace() + "updateSelectionMethod(this.value);";
				%>

				<aui:select name="selectionMethod" onChange="<%= taglibOnChange %>">
					<aui:option label="articles" selected='<%= selectionMethod.equals("articles") %>' />
					<aui:option label="scopes" selected='<%= selectionMethod.equals("scope-groups") %>' value="scope-groups" />
					<aui:option label='<%= "this-" + (themeDisplay.getScopeGroup().isOrganization() ? "organization" : "community") %>' selected='<%= selectionMethod.equals("parent-group") %>' value="parent-group" />
				</aui:select>

				<div class="kb-field-wrapper" id="<portlet:namespace />articlesSelectionOptions">
					<aui:field-wrapper label="articles">
						<div class="kb-selected-entries" id="<portlet:namespace />articles">

							<%
							for (Article article : articles) {
							%>

								<span id="<portlet:namespace />article<%= article.getResourcePrimKey() %>"><%= article.getTitle() %></span>

							<%
							}
							%>

						</div>

						<liferay-portlet:renderURL portletName="<%= portletResource %>" var="selectArticlesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
							<portlet:param name="jspPage" value="/search/select_articles.jsp" />
						</liferay-portlet:renderURL>

						<%
						String taglibOnClick = "var selectArticlesWindow = window.open('" + selectArticlesURL + "', 'selectArticles', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); selectArticlesWindow.focus();";
						%>

						<div class="kb-edit-link">
							<aui:a href="javascript:;" onClick="<%= taglibOnClick %>"><liferay-ui:message key="select-articles" /> &raquo;</aui:a>
						</div>
					</aui:field-wrapper>
				</div>

				<div class="kb-field-wrapper" id="<portlet:namespace />scopeGroupsSelectionOptions">
					<aui:field-wrapper label="scopes">
						<div class="kb-selected-entries" id="<portlet:namespace />scopeGroups">

							<%
							for (Group scopeGroup : scopeGroups) {
							%>

								<span id="<portlet:namespace />scopeGroup<%= scopeGroup.getGroupId() %>"><%= scopeGroup.getDescriptiveName() %></span>

							<%
							}
							%>

						</div>

						<liferay-portlet:renderURL portletName="<%= portletResource %>" var="selectScopeGroupsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
							<portlet:param name="jspPage" value="/search/select_scope_groups.jsp" />
						</liferay-portlet:renderURL>

						<%
						String taglibOnClick = "var selectScopeGroupsWindow = window.open('" + selectScopeGroupsURL + "', 'selectScopeGroups', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); selectScopeGroupsWindow.focus();";
						%>

						<div class="kb-edit-link">
							<aui:a href="javascript:;" onClick="<%= taglibOnClick %>"><liferay-ui:message key="select-scopes" /> &raquo;</aui:a>
						</div>
					</aui:field-wrapper>
				</div>
			</c:when>
			<c:when test='<%= tabs2.equals("rss") %>'>
				<aui:select label="maximum-items-to-display" name="rssDelta">
					<aui:option label="1" selected="<%= rssDelta == 1 %>" />
					<aui:option label="2" selected="<%= rssDelta == 2 %>" />
					<aui:option label="3" selected="<%= rssDelta == 3 %>" />
					<aui:option label="4" selected="<%= rssDelta == 4 %>" />
					<aui:option label="5" selected="<%= rssDelta == 5 %>" />
					<aui:option label="10" selected="<%= rssDelta == 10 %>" />
					<aui:option label="15" selected="<%= rssDelta == 15 %>" />
					<aui:option label="20" selected="<%= rssDelta == 20 %>" />
					<aui:option label="25" selected="<%= rssDelta == 25 %>" />
					<aui:option label="30" selected="<%= rssDelta == 30 %>" />
					<aui:option label="40" selected="<%= rssDelta == 40 %>" />
					<aui:option label="50" selected="<%= rssDelta == 50 %>" />
					<aui:option label="60" selected="<%= rssDelta == 60 %>" />
					<aui:option label="70" selected="<%= rssDelta == 70 %>" />
					<aui:option label="80" selected="<%= rssDelta == 80 %>" />
					<aui:option label="90" selected="<%= rssDelta == 90 %>" />
					<aui:option label="100" selected="<%= rssDelta == 100 %>" />
				</aui:select>

				<aui:select label="display-style" name="rssDisplayStyle">
					<aui:option label="<%= RSSUtil.DISPLAY_STYLE_FULL_CONTENT %>" selected="<%= rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_FULL_CONTENT) %>" />
					<aui:option label="<%= RSSUtil.DISPLAY_STYLE_ABSTRACT %>" selected="<%= rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_ABSTRACT) %>" />
					<aui:option label="<%= RSSUtil.DISPLAY_STYLE_TITLE %>" selected="<%= rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_TITLE) %>" />
				</aui:select>

				<aui:select label="format" name="rssFormat">
					<aui:option label="RSS 1.0" selected='<%= rssFormat.equals("rss10") %>' value="rss10" />
					<aui:option label="RSS 2.0" selected='<%= rssFormat.equals("rss20") %>' value="rss20" />
					<aui:option label="Atom 1.0" selected='<%= rssFormat.equals("atom10") %>' value="atom10" />
				</aui:select>
			</c:when>
		</c:choose>

		<aui:button-row>
			<aui:button type="submit" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<c:if test='<%= tabs2.equals("selection-method") %>'>
	<aui:script>
		function <portlet:namespace />selectArticles(resourcePrimKeys, titles) {
			document.<portlet:namespace />fm.<portlet:namespace />resourcePrimKeys.value = resourcePrimKeys.join();
			document.getElementById("<portlet:namespace />articles").innerHTML = "";

			var articlesElement = document.getElementById("<portlet:namespace />articles");

			for (var i = 0; i < titles.length; i++) {
				var articleElement = document.createElement("span");

				articleElement.id = "<portlet:namespace />article" + resourcePrimKeys[i];
				articleElement.innerHTML = titles[i];

				articlesElement.appendChild(articleElement);
			}
		}

		function <portlet:namespace />selectScopeGroups(scopeGroupIds, names) {
			document.<portlet:namespace />fm.<portlet:namespace />scopeGroupIds.value = scopeGroupIds.join();
			document.getElementById("<portlet:namespace />scopeGroups").innerHTML = "";

			var scopeGroupsElement = document.getElementById("<portlet:namespace />scopeGroups");

			for (var i = 0; i < names.length; i++) {
				var scopeGroupElement = document.createElement("span");

				scopeGroupElement.id = "<portlet:namespace />scopeGroup" + scopeGroupIds[i];
				scopeGroupElement.innerHTML = names[i];

				scopeGroupsElement.appendChild(scopeGroupElement);
			}
		}

		function <portlet:namespace />updateSelectionMethod(value) {
			if (value == "articles") {
				document.getElementById("<portlet:namespace />articlesSelectionOptions").style.display = "";
				document.getElementById("<portlet:namespace />scopeGroupsSelectionOptions").style.display = "none";
			}
			else if (value == "parent-group") {
				document.getElementById("<portlet:namespace />articlesSelectionOptions").style.display = "none";
				document.getElementById("<portlet:namespace />scopeGroupsSelectionOptions").style.display = "none";
			}
			else if (value == "scope-groups") {
				document.getElementById("<portlet:namespace />articlesSelectionOptions").style.display = "none";
				document.getElementById("<portlet:namespace />scopeGroupsSelectionOptions").style.display = "";
			}
		}

		<portlet:namespace />updateSelectionMethod("<%= selectionMethod %>");
	</aui:script>
</c:if>