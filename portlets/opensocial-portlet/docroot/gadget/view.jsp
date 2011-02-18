<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
Gadget gadget = (Gadget)renderRequest.getAttribute(WebKeys.GADGET);
String view = (String)renderRequest.getAttribute(WebKeys.VIEW);

String ownerId = ShindigUtil.getOwnerId(layout);
String appId = gadget.getUrl();
String gadgetUrl = gadget.getUrl();
long moduleId = ShindigUtil.getModuleId(renderResponse.getNamespace());
boolean requiresPubsub = ShindigUtil.isRequiresPubsub(gadgetUrl);

String secureToken = ShindigUtil.createSecurityToken(ownerId, themeDisplay.getUserId(), appId, PortalUtil.getPortalURL(themeDisplay), gadgetUrl, moduleId, currentURL);
%>

<div class="gadgets-gadget-chrome" id="<portlet:namespace />gadget"></div>

<aui:script use="liferay-open-social-gadget">
	new Liferay.OpenSocial.Gadget(
		{
			appId: '<%= gadgetUrl %>',
			debug: '<%= PortletPropsValues.SHINDIG_JS_DEBUG %>',
			moduleId: '<%= moduleId %>',
			nocache: '<%= PortletPropsValues.SHINDIG_NO_CACHE %>',
			portletId: '<%= portletDisplay.getId() %>',
			requiresPubsub: '<%= requiresPubsub %>',
			secureToken: '<%= secureToken %>',
			serverBase: '<%= renderRequest.getContextPath() %>/gadgets/',
			specUrl: '<%= gadgetUrl %>',
			store: new Liferay.OpenSocial.Store.Expando(
				{
					userPrefsKey: '<%= ShindigUtil.getColumnUserPrefs(renderResponse.getNamespace()) %>'
				}
			),
			view: '<%= view %>',
			viewParams: '<%= ParamUtil.getString(renderRequest, "viewParams") %>'
		}
	).render('#<portlet:namespace />gadget');
</aui:script>