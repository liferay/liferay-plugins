<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
long moduleId = ShindigUtil.getModuleId(renderResponse.getNamespace());

long gadgetId = gadget.getGadgetId();

String gadgetKey = StringPool.BLANK;

if (gadgetId > 0) {
	gadgetKey = GadgetConstants.toPublishedGadgetKey(gadgetId);
}
else {
	gadgetKey = GadgetConstants.toAdhocGadgetKey(moduleId);
}

GadgetSpec gadgetSpec = ShindigUtil.getGadgetSpec(gadget.getUrl());

ModulePrefs modulePrefs = gadgetSpec.getModulePrefs();

Map<String, Feature> features = modulePrefs.getFeatures();

boolean requiresPubsub = features.containsKey("pubsub-2");

String secureToken = ShindigUtil.createSecurityToken(ownerId, themeDisplay.getUserId(), gadgetKey, PortalUtil.getPortalURL(themeDisplay), gadget.getUrl(), moduleId, currentURL);

String userPrefsKey = ShindigUtil.getColumnUserPrefs(renderResponse.getNamespace(), themeDisplay);

JSONObject userPrefsJSONObject = ExpandoValueServiceUtil.getJSONData(themeDisplay.getCompanyId(), Layout.class.getName(), ShindigUtil.getTableOpenSocial(), userPrefsKey, themeDisplay.getPlid());
%>

<div class="gadgets-gadget-chrome" id="<portlet:namespace />gadget"></div>

<aui:script use="liferay-open-social-gadget">
	new Liferay.OpenSocial.Gadget(
		{
			appId: '<%= gadget.getUrl() %>',
			checksum: '<%= gadgetSpec.getChecksum() %>',
			debug: <%= PortletPropsValues.SHINDIG_JS_DEBUG %>,
			height: <%= modulePrefs.getHeight() %>,
			moduleId: '<%= moduleId %>',
			nocache: <%= PortletPropsValues.SHINDIG_NO_CACHE %>,
			portletId: '<%= portletDisplay.getId() %>',
			pubsubURILoadTimeout: <%= PortletPropsValues.PUBSUB_URI_LOAD_TIMEOUT %>,
			requiresPubsub: <%= requiresPubsub %>,
			scrolling: <%= modulePrefs.getScrolling() %>,
			secureToken: '<%= secureToken %>',
			serverBase: '<%= renderRequest.getContextPath() %>/gadgets/',
			specUrl: '<%= gadget.getUrl() %>',
			store: new Liferay.OpenSocial.Store.Expando(
				{
					userPrefsKey: '<%= userPrefsKey %>'
				}
			),
			userPrefs: A.JSON.parse('<%= String.valueOf(userPrefsJSONObject) %>'),
			view: '<%= view %>',
			viewParams: '<%= ParamUtil.getString(renderRequest, "viewParams") %>'
		}
	).render('#<portlet:namespace />gadget');
</aui:script>