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

<%@ include file="/aggregator/init.jsp" %>

<liferay-ui:tabs
	names="articles"
/>

<aui:form name="fm">
	<aui:fieldset>
		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="jspPage" value="/aggregator/select_articles.jsp" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-articles"
			headerNames="title"
			iteratorURL="<%= iteratorURL %>"
		>
			<liferay-ui:search-container-results>

				<%
				Map<String, Object> params = new HashMap<String, Object>();

				params.put("parentGroupId", themeDisplay.getParentGroupId());

				pageContext.setAttribute("results", ArticleLocalServiceUtil.getArticles(params, false, searchContainer.getStart(), searchContainer.getEnd(), new ArticleTitleComparator(true)));
				pageContext.setAttribute("total", ArticleLocalServiceUtil.getArticlesCount(params, false));
				%>

			</liferay-ui:search-container-results>

			<liferay-ui:search-container-row
				className="com.liferay.knowledgebase.model.Article"
				keyProperty="resourcePrimKey"
				modelVar="article"
			>
				<liferay-ui:search-container-column-text
					property="title"
				/>

				<liferay-ui:search-container-column-text
					align="right"
				>
					<div id="<portlet:namespace />article<%= article.getResourcePrimKey() %>Icons">
						<span id="<portlet:namespace />addArticle<%= article.getResourcePrimKey() %>">

							<%
							String taglibURL = "javascript:" + renderResponse.getNamespace() + "addArticle(" + article.getResourcePrimKey() + ", '" + UnicodeFormatter.toString(article.getTitle()) + "');";
							%>

							<liferay-ui:icon
								image="../common/add"
								label="<%= true %>"
								message="add"
								method="get"
								url="<%= taglibURL %>"
							/>
						</span>

						<span id="<portlet:namespace />removeArticle<%= article.getResourcePrimKey() %>" style="display: none;">

							<%
							taglibURL = "javascript:" + renderResponse.getNamespace() + "removeArticle(" + article.getResourcePrimKey() + ", '" + UnicodeFormatter.toString(article.getTitle()) + "');";
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
					<aui:select cssClass="kb-select-box" inlineField="<%= true %>" label="" name="resourcePrimKeysBox" size="10" />
				</td>
				<td class="lfr-top">

					<%
					String taglibOnClick = renderResponse.getNamespace() + "reorderArticles(0);";
					%>

					<aui:a href="javascript:;" onClick="<%= taglibOnClick %>"><img border="0" height="16" hspace="0" src="<%= themeDisplay.getPathThemeImages() %>/arrows/02_up.png" vspace="2" width="16" /></aui:a><br />

					<%
					taglibOnClick = renderResponse.getNamespace() + "reorderArticles(1);";
					%>

					<aui:a href="javascript:;" onClick="<%= taglibOnClick %>"><img border="0" height="16" hspace="0" src="<%= themeDisplay.getPathThemeImages() %>/arrows/02_down.png" vspace="2" width="16" /></aui:a><br />

					<%
					taglibOnClick = renderResponse.getNamespace() + "removeSelectedArticle();";
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
	function <portlet:namespace />addArticle(resourcePrimKey, title) {
		var box = document.<portlet:namespace />fm.<portlet:namespace />resourcePrimKeysBox;

		for (var i = box.options.length; i > 0; i--) {
			box.options[i] = new Option(box.options[i - 1].text, box.options[i - 1].value);
		}

		box.options[0] = new Option(title, resourcePrimKey, false, true);

		document.getElementById("<portlet:namespace />addArticle" + resourcePrimKey).style.display = "none";
		document.getElementById("<portlet:namespace />removeArticle" + resourcePrimKey).style.display = "";

		<portlet:namespace />selectArticles(box);
	}

	function <portlet:namespace />removeArticle(resourcePrimKey, title) {
		var box = document.<portlet:namespace />fm.<portlet:namespace />resourcePrimKeysBox;

		for (var i = 0; i < box.options.length; i++) {
			if (box.options[i].value == resourcePrimKey) {
				box.remove(i);
				box.selectedIndex = 0;

				break;
			}
		}

		document.getElementById("<portlet:namespace />addArticle" + resourcePrimKey).style.display = "";
		document.getElementById("<portlet:namespace />removeArticle" + resourcePrimKey).style.display = "none";

		<portlet:namespace />selectArticles(box);
	}

	function <portlet:namespace />removeSelectedArticle() {
		var box = document.<portlet:namespace />fm.<portlet:namespace />resourcePrimKeysBox;

		if (box.selectedIndex != -1) {
			var resourcePrimKey = box.options[box.selectedIndex].value;

			box.remove(box.selectedIndex);
			box.selectedIndex = 0;

			var articleIcons = document.getElementById("<portlet:namespace />article" + resourcePrimKey + "Icons");

			if (articleIcons) {
				document.getElementById("<portlet:namespace />addArticle" + resourcePrimKey).style.display = "";
				document.getElementById("<portlet:namespace />removeArticle" + resourcePrimKey).style.display = "none";
			}

			<portlet:namespace />selectArticles(box);
		}
	}

	function <portlet:namespace />reorderArticles(down) {
		Liferay.Util.reorder(document.<portlet:namespace />fm.<portlet:namespace />resourcePrimKeysBox, down);

		<portlet:namespace />selectArticles(document.<portlet:namespace />fm.<portlet:namespace />resourcePrimKeysBox);
	}

	function <portlet:namespace />selectArticles(box) {
		var resourcePrimKeys = [];
		var titles = [];

		for (var i = 0; i < box.options.length; i++) {
			resourcePrimKeys.push(box.options[i].value);
			titles.push(box.options[i].text);
		}

		opener.<%= PortalUtil.getPortletNamespace(PortletKeys.PORTLET_CONFIGURATION) %>selectArticles(resourcePrimKeys, titles);
	}

	function <portlet:namespace />updateArticles() {
		var resourcePrimKeysValue = opener.document.<%= PortalUtil.getPortletNamespace(PortletKeys.PORTLET_CONFIGURATION) %>fm.<%= PortalUtil.getPortletNamespace(PortletKeys.PORTLET_CONFIGURATION) %>resourcePrimKeys.value;

		if (resourcePrimKeysValue != "") {
			var resourcePrimKeys = resourcePrimKeysValue.split(",");

			for (var i = 0; i < resourcePrimKeys.length; i++) {
				var titleElement = opener.document.getElementById("<%= PortalUtil.getPortletNamespace(PortletKeys.PORTLET_CONFIGURATION) %>article" + resourcePrimKeys[i]);

				if (titleElement) {
					var box = document.<portlet:namespace />fm.<portlet:namespace />resourcePrimKeysBox;

					box.options[i] = new Option(titleElement.innerHTML, resourcePrimKeys[i], false, i == 0);
				}

				var articleIcons = document.getElementById("<portlet:namespace />article" + resourcePrimKeys[i] + "Icons");

				if (articleIcons) {
					document.getElementById("<portlet:namespace />addArticle" + resourcePrimKeys[i]).style.display = "none";
					document.getElementById("<portlet:namespace />removeArticle" + resourcePrimKeys[i]).style.display = "";
				}
			}
		}
	}

	<portlet:namespace />updateArticles();
</aui:script>