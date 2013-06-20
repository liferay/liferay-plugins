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

<%@ include file="/init.jsp" %>

<liferay-ui:header
	title="app-manager"
/>

<div class="row">
	<div class="span3">
		<div class="well">
			<ul class="nav nav-list">
				<li class="active">
					<a href="#"><liferay-ui:message key="all-apps" /></a>
				</li>
			</ul>
		</div>
	</div>

	<div class="span9">
		<h3><liferay-ui:message key="all-apps" /></h3>

		<%
		List<App> apps = AppLocalServiceUtil.getInstalledApps();

		for (App app : apps) {
		%>

			<div class="app">
				<div class="icon">
					<c:if test="<%= Validator.isNotNull(app.getIconURL()) %>">
						<img src="<%= app.getIconURL() %>" />
					</c:if>
				</div>

				<div class="info">
					<div class="title">
						<%= app.getTitle() %>
					</div>

					<div class="description">
						<%= app.getDescription() %>
					</div>

					<div class="meta">

						<%
						String[] contextNames = app.getContextNames();

						List<LayoutTemplate> layoutTemplates = new ArrayList<LayoutTemplate>();
						List<Portlet> portlets = new ArrayList<Portlet>();
						List<Theme> themes = new ArrayList<Theme>();

						for (String contextName : contextNames) {
							ServletContext servletContext = ServletContextPool.get(contextName);

							List<LayoutTemplate> curLayoutTemplates = (List<LayoutTemplate>)servletContext.getAttribute("PLUGIN_LAYOUT_TEMPLATES");

							if (curLayoutTemplates != null) {
								layoutTemplates.addAll(curLayoutTemplates);
							}

							List<Portlet> curPortlets = (List<Portlet>)servletContext.getAttribute("PLUGIN_PORTLETS");

							if (curPortlets != null) {
								portlets.addAll(curPortlets);
							}

							List<Theme> curThemes = (List<Theme>)servletContext.getAttribute("PLUGIN_THEMES");

							if (curThemes != null) {
								themes.addAll(curThemes);
							}
						}
						%>

						<ul>
							<li>
								<liferay-ui:message key="this-app-contains" />
							</li>

							<c:if test="<%= !layoutTemplates.isEmpty() %>">
								<li>
									<%= layoutTemplates.size() %> <liferay-ui:message key='<%= layoutTemplates.size() == 1 ? "layout-template" : "layout-templates" %>' />
								</li>
							</c:if>

							<c:if test="<%= !portlets.isEmpty() %>">
								<li>
									<%= portlets.size() %> <liferay-ui:message key='<%= portlets.size() == 1 ? "portlet" : "portlets" %>' />
								</li>
							</c:if>

							<c:if test="<%= !themes.isEmpty() %>">
								<li>
									<%= themes.size() %> <liferay-ui:message key='<%= themes.size() == 1 ? "theme" : "themes" %>' />
								</li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>

		<%
		}
		%>

	</div>
</div>