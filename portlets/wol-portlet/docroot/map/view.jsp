<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="/init.jsp" %>

<%
boolean communityProfileMap = false;
boolean friendsProfileMap = false;
boolean organizationProfileMap = false;
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
	communityProfileMap = true;
}

if (communityProfileMap || organizationProfileMap) {
	renderResponse.setTitle(LanguageUtil.format(pageContext, "where-are-the-x-members", group.getDescriptiveName()));
}
else if (friendsProfileMap) {
	renderResponse.setTitle(LanguageUtil.format(pageContext, "where-are-x's-friends", user2.getFirstName()));
}
else {
	renderResponse.setTitle(LanguageUtil.format(pageContext, "where-is-x", user2.getFirstName()));
}
%>

<c:if test="<%= !MessageBusUtil.hasMessageListener(DestinationNames.IP_GEOCODER) %>">
	<div class="portlet-msg-error">
		Markers will not be shown because ip-geocoder-portlet is not available.
	</div>
</c:if>

<script src="http://www.google.com/jsapi?key=<%= PortletProps.get("map.google.maps.api.key") %>" type="text/javascript"></script>

<script type="text/javascript">
	google.load("maps", "2.x", {"language" : "ja_JP"});

	function <portlet:namespace />initMap() {
		if (GBrowserIsCompatible()) {
			var maximized = <%= windowState.equals(WindowState.MAXIMIZED) ? true : false %>;

			var map = new GMap2(document.getElementById("<portlet:namespace />map"));

			map.setCenter(new GLatLng(0.0, 0.0), <%= windowState.equals(WindowState.MAXIMIZED) ? 2 : 0 %>);
			map.setMapType(G_NORMAL_MAP);

			<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
				map.addControl(new GLargeMapControl());
			</c:if>

			<%
			List<User> users = null;

			if (communityProfileMap) {
				LinkedHashMap<String, Object> userParams = new LinkedHashMap<String, Object>();

				userParams.put("usersGroups", new Long(group.getGroupId()));

				users = UserLocalServiceUtil.search(company.getCompanyId(), null, Boolean.TRUE, userParams, 0, 50, new UserLoginDateComparator());
			}
			else if (friendsProfileMap) {
				users = UserLocalServiceUtil.getSocialUsers(user2.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND, 0, 50, new UserLoginDateComparator());
			}
			else if (organizationProfileMap) {
				LinkedHashMap<String, Object> userParams = new LinkedHashMap<String, Object>();

				userParams.put("usersOrgs", new Long(organization.getOrganizationId()));

				users = UserLocalServiceUtil.search(company.getCompanyId(), null, Boolean.TRUE, userParams, 0, 50, new UserLoginDateComparator());
			}
			else if (userProfileMap) {
				users = new ArrayList();

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
					map.setCenter(new GLatLng(<%= latitude %>, <%= longitude %>), <%= windowState.equals(WindowState.MAXIMIZED) ? 5 : 0 %>);
				</c:if>

				var marker<%= i %> = new GMarker(
					new GLatLng(<%= latitude %>, <%= longitude %>),
					{title: '<%= mapUser.getFullName() %>'}
				);

				GEvent.addListener(
					marker<%= i %>,
					"click",
					function() {
						<c:choose>
							<c:when test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
								var html = '<center><img src="<%= themeDisplay.getPathImage() %>/user_portrait?img_id=<%= mapUser.getPortraitId() %>&t=<%= ImageServletTokenUtil.getToken(mapUser.getPortraitId()) %>" width="65" /><br /><%= mapUser.getFullName() %></center>';

								marker<%= i %>.openInfoWindowHtml(html);
							</c:when>
							<c:otherwise>
								location.href = '<%= themeDisplay.getPathContext() %>/web/<%= mapUser.getScreenName() %>/profile/-/map';
							</c:otherwise>
						</c:choose>
					}
				);

				map.addOverlay(marker<%= i %>);

			<%
			}
			%>
		}
	}

	google.setOnLoadCallback(<portlet:namespace />initMap);
</script>

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