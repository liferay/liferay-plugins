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

<%
Gadget gadget = (Gadget)renderRequest.getAttribute(WebKeys.GADGET);

long classPK = layout.getGroup().getClassPK();

String ownerId = "G-" + classPK;

if (layout.getGroup().isUser()) {
	ownerId = String.valueOf(classPK);
}

String gadgetUrl = gadget.getUrl();
String namespace = renderResponse.getNamespace();
long moduleId = namespace.hashCode();

String secureToken = ShindigUtil.createSecurityToken(ownerId, themeDisplay.getUserId(), gadgetUrl, PortalUtil.getPortalURL(themeDisplay), gadgetUrl, moduleId, PortalUtil.getCurrentURL(renderRequest));
%>

<div class="gadgets-gadget-chrome" id="<portlet:namespace />gadget"></div>

<aui:script use="liferay-open-social-gadget">
	new Liferay.OpenSocial.Gadget(
		{
			appId: '<%= gadgetUrl %>',
			moduleId: '<%= moduleId %>',
			secureToken: '<%= secureToken %>',
			serverBase: '<%= renderRequest.getContextPath() %>/gadgets/',
			specUrl: '<%= gadgetUrl %>',
			userPrefsKey: '<%= ShindigUtil.USER_PREFS + namespace %>'
		}
	).render('#<portlet:namespace />gadget');
</aui:script>