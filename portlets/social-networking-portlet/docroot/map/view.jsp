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

<%
boolean friendsProfileMap = false;
boolean organizationProfileMap = false;
boolean siteProfileMap = false;
boolean userProfileMap = false;

if ((user2 != null) && layout.getFriendlyURL().equals("/friends")) {
	friendsProfileMap = true;
}
else if (organization != null) {
	organizationProfileMap = true;
}
else if (user2 != null) {
	userProfileMap = true;
}
else {
	siteProfileMap = true;
}

if (organizationProfileMap || siteProfileMap) {
	renderResponse.setTitle(LanguageUtil.format(pageContext, "where-are-the-x-members", group.getDescriptiveName(locale)));
}
else if (friendsProfileMap) {
	renderResponse.setTitle(LanguageUtil.format(pageContext, "where-are-x's-friends", user2.getFirstName()));
}
else {
	renderResponse.setTitle(LanguageUtil.format(pageContext, "where-is-x", user2.getFirstName()));
}
%>

<%
boolean ipGeocoderInstalled = MessageBusUtil.hasMessageListener(DestinationNames.IP_GEOCODER);
boolean ipGeocoderConfigured = ipGeocoderInstalled && (IPGeocoderUtil.getIPInfo(request.getRemoteAddr()) != null);
%>

<c:choose>
	<c:when test="<%= !ipGeocoderInstalled %>">
		<div class="portlet-msg-error">
			<liferay-ui:message key="install-and-configure-the-ip-geocoder-portlet-to-enable-this-portlet" />
		</div>
	</c:when>
	<c:when test="<%= !ipGeocoderConfigured %>">
		<div class="portlet-msg-error">
			<aui:a href="http://www.maxmind.com/app/geolitecity" target="_blank"><liferay-ui:message key="install-and-configure-maxmind-geoip-city-or-geolite-city-to-enable-this-portlet" /></aui:a>
		</div>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
				<div id="<portlet:namespace />map" style="height: 600px; width: 900px;"></div>
			</c:when>
			<c:otherwise>
				<div id="<portlet:namespace />map" style="height: 190px; width: 190px;"></div>

				<div style="padding-top: 5px;">
					<a href="<%= PortalUtil.getLayoutURL(layout, themeDisplay) %>/-/map"><liferay-ui:message key="view-larger-map" /> &raquo;</a>
				</div>
			</c:otherwise>
		</c:choose>

		<script src="http://maps.googleapis.com/maps/api/js?key=<%= PortletProps.get("map.google.maps.api.key") %>&language=<%= themeDisplay.getLanguageId() %>&sensor=false" type="text/javascript"></script>

		<aui:script>
			function <portlet:namespace />initMap() {
				var maximized = <%= windowState.equals(WindowState.MAXIMIZED) ? true : false %>;

				var mapOptions = {
					center: new google.maps.LatLng(0.0, 0.0),
					disableDefaultUI: !maximized,
					mapTypeId: google.maps.MapTypeId.ROADMAP,
					zoom: (maximized ? 2 : 0),
					zoomControl: maximized,
					zoomControlOptions: {
						style: google.maps.ZoomControlStyle.LARGE
					}
				};

				var map = new google.maps.Map(document.getElementById('<portlet:namespace />map'), mapOptions);

				<%
				List<User> users = null;

				if (siteProfileMap) {
					LinkedHashMap<String, Object> userParams = new LinkedHashMap<String, Object>();

					userParams.put("usersGroups", new Long(group.getGroupId()));

					users = UserLocalServiceUtil.search(company.getCompanyId(), null, WorkflowConstants.STATUS_APPROVED, userParams, 0, 50, new UserLoginDateComparator());
				}
				else if (friendsProfileMap) {
					users = UserLocalServiceUtil.getSocialUsers(user2.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND, 0, 50, new UserLoginDateComparator());
				}
				else if (organizationProfileMap) {
					LinkedHashMap<String, Object> userParams = new LinkedHashMap<String, Object>();

					userParams.put("usersOrgs", new Long(organization.getOrganizationId()));

					users = UserLocalServiceUtil.search(company.getCompanyId(), null, WorkflowConstants.STATUS_APPROVED, userParams, 0, 50, new UserLoginDateComparator());
				}
				else if (userProfileMap) {
					users = new ArrayList<User>();

					users.add(user2);
				}

				for (int i = 0; i < users.size(); i++) {
					User mapUser = users.get(i);

					if (Validator.isNull(mapUser.getLastLoginIP())) {
						continue;
					}

					IPInfo ipInfo = IPGeocoderUtil.getIPInfo(mapUser.getLastLoginIP());

					if (ipInfo == null) {
						continue;
					}

					float latitude = ipInfo.getLatitude();
					float longitude = ipInfo.getLongitude();

					if ((latitude == 0) && (longitude == 0)) {
						continue;
					}
				%>

					<c:if test="<%= userProfileMap %>">
						map.setCenter(new google.maps.LatLng(<%= latitude %>, <%= longitude %>));

						map.setZoom(<%= windowState.equals(WindowState.MAXIMIZED) ? 5 : 0 %>);
					</c:if>

					var marker<%= i %> = new google.maps.Marker(
						{
							position: new google.maps.LatLng(<%= latitude %>, <%= longitude %>),
							map: map,
							title: '<%= HtmlUtil.escapeJS(mapUser.getFullName()) %>'
						}
					);

					google.maps.event.addListener(
						marker<%= i %>,
						'click',
						function() {
							<c:choose>
								<c:when test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
									var infoWindow = new google.maps.InfoWindow(
										{
											content: '<center><img alt="<%= HtmlUtil.escapeJS(LanguageUtil.get(pageContext, "user-portrait")) %>" src="<%= mapUser.getPortraitURL(themeDisplay) %>" width="65" /><br /><%= HtmlUtil.escapeJS(mapUser.getFullName()) %></center>'
										}
									);

									infoWindow.open(map, marker<%= i %>);
								</c:when>
								<c:otherwise>
									location.href = '<%= mapUser.getDisplayURL(themeDisplay) %>/-/map';
								</c:otherwise>
							</c:choose>
						}
					);

				<%
				}
				%>

			}

			google.maps.event.addDomListener(window, 'load', <portlet:namespace />initMap);
		</aui:script>
	</c:otherwise>
</c:choose>