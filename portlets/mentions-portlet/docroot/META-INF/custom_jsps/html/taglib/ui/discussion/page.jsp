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

<%@ include file="/html/taglib/ui/discussion/page.portal.jsp" %>

<c:if test="<%= _isMentionsEnabled(themeDisplay.getSiteGroupId()) %>">
	<aui:script use="liferay-autocomplete-input">
		new Liferay.AutoCompleteInput(
			{
				'acConfig.resultTextLocator': 'screenName',
				'acConfig.resultFilters': function(query, results) {
					return results;
				},
				inputNode: '#<portlet:namespace /><%= randomNamespace + "postReplyBody" + "0" %>',
				source: '<%= themeDisplay.getPathMain() %>/portal/auto_complete_user?query={query}',
				tplResults: '<div class="taglib-user-display display-style-3"><span><span class="user-profile-image" style="background-image: url(\'{portrait}\'); background-size: 32px 32px; height: 32px; width: 32px;"></span><span class="user-name">{fullName}</span><span class="user-details">@{screenName}</span></span></div>'
			}
		);
	</aui:script>
</c:if>

<%!
private boolean _isMentionsEnabled(long siteGroupId) throws PortalException, SystemException {
	Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

	return GetterUtil.getBoolean(group.getLiveParentTypeSettingsProperty("mentionsEnabled"), true);
}
%>