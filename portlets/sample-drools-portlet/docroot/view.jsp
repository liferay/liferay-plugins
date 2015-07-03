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

<c:choose>
	<c:when test="<%= themeDisplay.isSignedIn() %>">

		<%
		List<Fact<?>> facts = new ArrayList<Fact<?>>();

		facts.add(new Fact<KeyValuePair>("classNameIds", new KeyValuePair("classNameIds", StringUtil.merge(classNameIds))));
		facts.add(new Fact<KeyValuePair>("parentGroupId", new KeyValuePair("parentGroupId", String.valueOf(themeDisplay.getSiteGroupId()))));
		facts.add(new Fact<User>("user", user));
		facts.add(new Fact<KeyValuePair>("userCustomAttributeNames", new KeyValuePair("userCustomAttributeNames", userCustomAttributeNames)));
		facts.add(new Fact<List<AssetEntry>>("results", new ArrayList<AssetEntry>()));

		if (!RulesEngineUtil.containsRuleDomain(domainName)) {
			RulesResourceRetriever rulesResourceRetriever = new RulesResourceRetriever(new StringResourceRetriever(rules), String.valueOf(RulesLanguage.DROOLS_RULE_LANGUAGE));

			RulesEngineUtil.update(domainName, rulesResourceRetriever, PortalClassLoaderUtil.getClassLoader());
		}

		Map<String, ?> results = RulesEngineUtil.execute(domainName, facts, Query.createStandardQuery(), PortalClassLoaderUtil.getClassLoader());

		List<AssetEntry> assetEntries = (List<AssetEntry>)results.get("results");
		%>

		<%= user.getGreeting() %>

		<div class="separator"><!-- --></div>

		<c:choose>
			<c:when test="<%= !assetEntries.isEmpty() %>">

				<%
				for (AssetEntry assetEntry : assetEntries) {
					AssetRendererFactory assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(assetEntry.getClassName());

					if (assetRendererFactory == null) {
						continue;
					}

					AssetRenderer assetRenderer = assetRendererFactory.getAssetRenderer(assetEntry.getClassPK());

					request.setAttribute(WebKeys.ASSET_RENDERER_FACTORY, assetRendererFactory);
					request.setAttribute(WebKeys.ASSET_RENDERER, assetRenderer);
				%>

					<strong><%= assetEntry.getTitle() %></strong>

					<br /><br />

					<liferay-ui:asset-display
						assetRenderer="<%= assetRenderer %>"
						template="<%= AssetRenderer.TEMPLATE_FULL_CONTENT %>"
					/>

					<div class="separator"><!-- --></div>

				<%
				}
				%>

			</c:when>
			<c:otherwise>
				<liferay-ui:message key="there-are-no-results" />
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<div class="portlet-msg-info">
			<aui:a href="<%= themeDisplay.getURLSignIn() %>" label="sign-in-to-your-account" />
		</div>
	</c:otherwise>
</c:choose>