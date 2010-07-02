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
List<UserPref> userPrefs = (List<UserPref>)renderRequest.getAttribute(WebKeys.USER_PREFS);

String namespace = ShindigUtil.getPortletResourceNamespace(renderRequest, themeDisplay);

JSONObject jsonObject = ExpandoValueServiceUtil.getJSONData(themeDisplay.getCompanyId(), User.class.getName(), ShindigUtil.getTableOpenSocial(), ShindigUtil.getColumnUserPrefs(namespace), themeDisplay.getUserId());
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:fieldset>

		<%
		for (UserPref userPref : userPrefs) {
			String value = userPref.getDefaultValue();

			if (jsonObject != null) {
				value = GetterUtil.getString(jsonObject.getString(userPref.getName()), value);
			}
		%>

			<aui:input cssClass="lfr-input-text-container" label="<%= userPref.getDisplayName() %>" name="<%= userPref.getName() %>" type="text" value="<%= value %>" />

		<%
		}
		%>

	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>