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

				List<LayoutTemplate> servletContextLayoutTemplates = (List<LayoutTemplate>)servletContext.getAttribute(WebKeys.PLUGIN_LAYOUT_TEMPLATES);

				if (servletContextLayoutTemplates != null) {
					layoutTemplates.addAll(servletContextLayoutTemplates);

					Iterator<LayoutTemplate> itr = layoutTemplates.iterator();

					while (itr.hasNext()) {
						LayoutTemplate layoutTemplate = itr.next();

						if (layoutTemplate.isStandard()) {
							itr.remove();
						}
					}
				}

				List<Portlet> servletContextPortlets = (List<Portlet>)servletContext.getAttribute(WebKeys.PLUGIN_PORTLETS);

				if (servletContextPortlets != null) {
					portlets.addAll(servletContextPortlets);

					Iterator<Portlet> itr = portlets.iterator();

					while (itr.hasNext()) {
						Portlet portlet = itr.next();

						String curPortletId = portlet.getPortletId();

						if (curPortletId.equals(PortletKeys.PORTAL)) {
							itr.remove();
						}
						else if (portlet.isSystem()) {
							itr.remove();
						}
					}
				}

				List<Theme> servletContextThemes = (List<Theme>)servletContext.getAttribute(WebKeys.PLUGIN_THEMES);

				if (servletContextThemes != null) {
					themes.addAll(servletContextThemes);
				}
			}

			List plugins = new ArrayList(layoutTemplates.size() + portlets.size() + themes.size());

			plugins.addAll(layoutTemplates);
			plugins.addAll(portlets);
			plugins.addAll(themes);

			plugins = ListUtil.sort(plugins, new PluginComparator(application, locale));
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

					<div class="plugins well">
						<c:choose>
							<c:when test="<%= !plugins.isEmpty() %>">
								<ul class="summary">
									<li class="switch">
										<i class="icon-chevron-right"></i>
									</li>

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

								<div class="plugin-list">
									<%@ include file="/app_manager/plugins.jspf" %>
								</div>
							</c:when>
							<c:otherwise>
								<liferay-ui:message key="there-are-no-configurable-plugins-for-this-app" />
							</c:otherwise>
						</c:choose>
					</div>
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
			var targetNode = event.currentTarget;

			var pluginsContainer = targetNode.ancestor('.plugins');

			var pluginSwitch = pluginsContainer.one('.switch i');
			var pluginList = pluginsContainer.one('.plugin-list');

			if (pluginsContainer.hasClass('active')) {
				pluginsContainer.removeClass('active');

				pluginSwitch.setAttribute('class', 'icon-chevron-right');
			}
			else {
				pluginsContainer.addClass('active');

				pluginSwitch.setAttribute('class', 'icon-chevron-down');
			}
		},
		'ul.summary'
	);

	A.one('.marketplace-portlet .apps').delegate(
		'click',
		function(event) {
			event.preventDefault();

			submitForm(document.hrefFm, event.currentTarget.getAttribute('href'));
		},
		'.reindex'
	);
</aui:script>