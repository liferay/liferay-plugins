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

<%
String category = ParamUtil.getString(request, "category");

portletURL.setParameter("category", category);
%>

<div class="row">
	<div class="span3">
		<div class="category-nav well">
			<%@ include file="/app_manager/categories.jspf" %>
		</div>
	</div>

	<div class="apps span9">
		<h3><liferay-ui:message key="all-apps" /></h3>

		<%
		List<App> apps = null;

		if (Validator.isNotNull(category)) {
			apps = AppLocalServiceUtil.getApps(category);
		}
		else {
			apps = AppLocalServiceUtil.getInstalledApps();
		}

		for (App app : apps) {
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

					<c:if test="<%= !layoutTemplates.isEmpty() || !portlets.isEmpty() || !themes.isEmpty() %>">
						<div class="plugins well">
							<div class="tabbable">
								<ul class="nav nav-pills">
									<li class="active">
										<a data-toggle="tab1" href="javascript:;"><liferay-ui:message key="summary" /></a>
									</li>

									<c:if test="<%= !layoutTemplates.isEmpty() %>">
										<li>
											<a data-toggle="tab2" href="javascript:;"><liferay-ui:message key="layout-templates" /></a>
										</li>
									</c:if>

									<c:if test="<%= !portlets.isEmpty() %>">
										<li>
											<a data-toggle="tab3" href="javascript:;"><liferay-ui:message key="portlets" /></a>
										</li>
									</c:if>

									<c:if test="<%= !themes.isEmpty() %>">
										<li>
											<a data-toggle="tab4" href="javascript:;"><liferay-ui:message key="themes" /></a>
										</li>
									</c:if>
								</ul>

								<div class="tab-content">
									<div class="tab-pane tab1 active">
										<ul class="summary">
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

									<div class="tab-pane tab2">
										<%@ include file="/app_manager/layout_templates.jspf" %>
									</div>

									<div class="tab-pane tab3">
										<%@ include file="/app_manager/portlets.jspf" %>
									</div>

									<div class="tab-pane tab4">
										<%@ include file="/app_manager/themes.jspf" %>
									</div>
								</div>
							</div>
						</div>
					</c:if>
				</div>

				<c:if test='<%= !ArrayUtil.contains(contextNames, PortalUtil.getPathContext()) && !ArrayUtil.contains(contextNames, "marketplace-portlet") %>'>
					<div class="actions">
						<liferay-ui:icon-menu>
							<liferay-portlet:actionURL name="updatePluginSettings" var="activateURL">
								<portlet:param name="contextNames" value="<%= StringUtil.merge(app.getContextNames()) %>" />
								<portlet:param name="active" value="<%= String.valueOf(true) %>" />
							</liferay-portlet:actionURL>

							<liferay-ui:icon
								image="activate"
								url="<%= activateURL %>"
							/>

							<liferay-portlet:actionURL name="updatePluginSettings" var="deactivateURL">
								<portlet:param name="contextNames" value="<%= StringUtil.merge(app.getContextNames()) %>" />
								<portlet:param name="active" value="<%= String.valueOf(false) %>" />
							</liferay-portlet:actionURL>

							<liferay-ui:icon
								image="deactivate"
								url="<%= deactivateURL %>"
							/>

							<liferay-portlet:actionURL name="uninstallApp" var="uninstallURL">
								<portlet:param name="remoteAppId" value="<%= String.valueOf(app.getRemoteAppId()) %>" />
								<portlet:param name="contextNames" value="<%= StringUtil.merge(app.getContextNames()) %>" />
								<portlet:param name="activate" value="<%= String.valueOf(false) %>" />
							</liferay-portlet:actionURL>

							<liferay-ui:icon
								image="uninstall"
								url="<%= uninstallURL %>"
							/>
						</liferay-ui:icon-menu>
					</div>
				</c:if>
			</div>

		<%
		}
		%>

	</div>
</div>

<aui:script use="aui-base">
	A.one('.marketplace-portlet .apps').delegate(
		'click',
		function(event) {
			var tab = event.currentTarget;

			tab.ancestor('ul').all('li').removeClass('active');
			tab.ancestor('li').addClass('active');

			var tabbable = tab.ancestor('.tabbable');

			tabbable.one('.tab-content').all('.tab-pane').removeClass('active');
			tabbable.one('.tab-content .' + tab.getAttribute('data-toggle')).addClass('active');
		},
		'.nav-pills a'
	)
</aui:script>