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
String backURL = ParamUtil.getString(request, "backURL");

long userId = ParamUtil.getLong(request, "userId");

User user2 = null;

if (userId > 0) {
	user2 = UserLocalServiceUtil.getUser(userId);
}
else {
	user2 = (User)request.getAttribute(WebKeys.CONTACTS_USER);
}

user2 = user2.toEscapedModel();

request.setAttribute("view_user.jsp-user", user2);
%>

<c:if test="<%= user2 != null %>">
	<div class="contacts-profile <%= (user.getUserId() == user2.getUserId()) ? "my-profile" : StringPool.BLANK %>">
		<c:if test="<%= (displayStyle == ContactsConstants.DISPLAY_STYLE_BASIC) || (displayStyle == ContactsConstants.DISPLAY_STYLE_FULL) %>">
			<aui:layout cssClass="social-relations">

				<%
				boolean connection = SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION);
				boolean follower = SocialRelationLocalServiceUtil.hasRelation(user2.getUserId(), themeDisplay.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER);
				boolean following = SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER);
				%>

				<c:if test="<%= connection || follower || following %>">
					<div class="lfr-asset-metadata">
						<c:if test="<%= connection %>">
							<span class="lfr-asset-icon lfr-asset-connection<%= (following || follower) ? StringPool.BLANK : " last" %>">
								<liferay-ui:message key="connection" />
							</span>
						</c:if>

						<c:if test="<%= following %>">
							<span class="lfr-asset-icon lfr-asset-following<%= follower ? StringPool.BLANK : " last" %>">
								<liferay-ui:message key="following" />
							</span>
						</c:if>

						<c:if test="<%= follower %>">
							<span class="lfr-asset-icon lfr-asset-follower last">
								<liferay-ui:message key="follower" />
							</span>
						</c:if>
					</div>
				</c:if>

				<aui:layout cssClass="contacts-action">
					<c:choose>
						<c:when test="<%= portletName.equals(PortletKeys.CONTACTS_CENTER) || portletName.equals(PortletKeys.MEMBERS) %>">

							<%
							boolean blocked = false;

							if (SocialRelationLocalServiceUtil.hasRelation(user2.getUserId(), themeDisplay.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY)) {
								blocked = true;
							}
							else if (SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY)) {
								blocked = true;
							}
							%>

							<c:choose>
								<c:when test="<%= !blocked && SocialRequestLocalServiceUtil.hasRequest(themeDisplay.getUserId(), User.class.getName(), themeDisplay.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION, user2.getUserId(), SocialRequestConstants.STATUS_PENDING) %>">
									<liferay-ui:icon
										cssClass="disabled"
										image="../social/coworker"
										label="<%= true %>"
										message="connection-requested"
									/>
								</c:when>
								<c:when test="<%= !blocked && SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION) %>">
									<liferay-ui:icon
										cssClass="connected"
										image="../social/coworker"
										label="<%= true %>"
										message="connected"
									/>
								</c:when>
							</c:choose>

							<c:if test="<%= !blocked && SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER) %>">
								<liferay-ui:icon
									cssClass="following"
									image="../social/following"
									label="<%= true %>"
									message="following"
								/>
							</c:if>

							<c:if test="<%= SocialRelationLocalServiceUtil.hasRelation(themeDisplay.getUserId(), user2.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY) %>">
								<liferay-ui:icon
									cssClass="block"
									image="../social/block"
									label="<%= true %>"
									message="block"
								/>
							</c:if>
						</c:when>
						<c:otherwise>
							<liferay-util:include page="/contacts_center/user_toolbar.jsp" servletContext="<%= application %>" />
						</c:otherwise>
					</c:choose>
				</aui:layout>
			</aui:layout>

			<div class="lfr-detail-info">
				<c:if test="<%= showIcon %>">
					<div class="lfr-contact-thumb">
						<a href="<%= user2.getDisplayURL(themeDisplay) %>"><img alt="<%= HtmlUtil.escape(user2.getFullName()) %>" src="<%= user2.getPortraitURL(themeDisplay) %>" /></a>
					</div>
				</c:if>

				<div class="<%= showIcon ? StringPool.BLANK : "no-icon" %> lfr-contact-info">
					<div class="lfr-contact-name">
						<a href="<%= user2.getDisplayURL(themeDisplay) %>"><%= HtmlUtil.escape(user2.getFullName()) %></a>
					</div>

					<div class="lfr-contact-job-title">
						<%= HtmlUtil.escape(user2.getJobTitle()) %>
					</div>

					<div class="lfr-contact-extra">
						<%= HtmlUtil.escape(user2.getEmailAddress()) %>
					</div>
				</div>

				<div class="clear"><!-- --></div>
			</div>
		</c:if>

		<c:if test="<%= ((displayStyle == ContactsConstants.DISPLAY_STYLE_DETAIL) || (displayStyle == ContactsConstants.DISPLAY_STYLE_FULL) || ((themeDisplay.getUserId() == user2.getUserId()) && showCompleteYourProfile)) && UserPermissionUtil.contains(permissionChecker, user2.getUserId(), ActionKeys.VIEW) %>">
			<div class="user-information" id="<portlet:namespace />userInformation">
				<aui:layout>
					<c:if test="<%= showUsersInformation %>">
						<aui:column columnWidth="<%= showSites ? 80 : 100 %>" cssClass="user-information-column-1">
							<div class="user-information-title">
								<liferay-ui:message key="about" />
							</div>

							<div class="lfr-user-info-container">
								<liferay-util:include page="/contacts_center/view_user_information.jsp" servletContext="<%= application %>" />
							</div>

							<%
							Map<String, String> extensions = ContactsExtensionsUtil.getExtensions();

							Set<String> servletContextNames = extensions.keySet();

							request.setAttribute("view_user.jsp-showCompleteYourProfile", String.valueOf(showCompleteYourProfile));

							for (String servletContextName : servletContextNames) {
								String extensionPath = extensions.get(servletContextName);

								ServletContext extensionServletContext = ServletContextPool.get(servletContextName);

								String title = extensionPath.substring(extensionPath.lastIndexOf(StringPool.SLASH) + 1, extensionPath.lastIndexOf(StringPool.PERIOD));

								title = title.replace(CharPool.UNDERLINE, CharPool.DASH);

								String cssClass = "lfr-" + title + "-container";
							%>

								<div class="user-information-title">
									<liferay-ui:message key="<%= title %>" />
								</div>

								<liferay-util:include page="<%= extensionPath %>" servletContext="<%= extensionServletContext %>" />

							<%
							}
							%>

						</aui:column>
					</c:if>

					<c:if test="<%= showSites || showTags %>">
						<aui:column columnWidth="<%= showUsersInformation ? 20 : 100 %>" cssClass="user-information-column-2">
							<c:if test="<%= showSites %>">

								<%
								LinkedHashMap groupParams = new LinkedHashMap();

								groupParams.put("site", Boolean.TRUE);

								Group group = themeDisplay.getScopeGroup();

								if (group.isUser()) {
									groupParams.put("usersGroups", new Long(group.getClassPK()));
								}
								else {
									groupParams.put("usersGroups", new Long(themeDisplay.getUserId()));
								}

								groupParams.put("active", Boolean.TRUE);

								if (group.isUser() && (themeDisplay.getUserId() != group.getClassPK())) {
									List<Integer> types = new ArrayList<Integer>();

									types.add(GroupConstants.TYPE_SITE_OPEN);
									types.add(GroupConstants.TYPE_SITE_RESTRICTED);

									groupParams.put("types", types);
								}

								List<Group> results = GroupLocalServiceUtil.search(company.getCompanyId(), null, null, groupParams, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
								%>

								<div class="user-sites-title">
									<liferay-ui:message key="sites" />
								</div>

								<ul class="user-sites">
									<c:choose>
										<c:when test="<%= !results.isEmpty() %>">

											<%
											for (Group curGroup : results) {
											%>

												<liferay-portlet:actionURL portletName="<%= PortletKeys.SITE_REDIRECTOR %>" var="siteURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
													<portlet:param name="struts_action" value="/my_sites/view" />
													<portlet:param name="groupId" value="<%= String.valueOf(curGroup.getGroupId()) %>" />
													<portlet:param name="privateLayout" value="<%= Boolean.FALSE.toString() %>" />
												</liferay-portlet:actionURL>

												<li class="user-information-sites"><a href="<%= siteURL %>"><%= HtmlUtil.escape(curGroup.getDescriptiveName(locale)) %></a></li>

											<%
											}
											%>

										</c:when>
										<c:otherwise>
											<div class="empty">
												<liferay-ui:message arguments="<%= PortalUtil.getUserName(user2.getUserId(), group.getDescriptiveName(locale)) %>" key="x-does-not-belong-to-any-sites" />
											</div>
										</c:otherwise>
									</c:choose>
								</ul>
							</c:if>

							<c:if test="<%= showTags %>">
								<div class="user-tags-title">
									<liferay-ui:message key="tags" />
								</div>

								<%
								List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(User.class.getName(), user2.getUserId());
								%>

								<c:choose>
									<c:when test="<%= !assetTags.isEmpty() %>">
										<div class="field-group" data-sectionId="categorization" data-title="<%= LanguageUtil.get(pageContext, "tags") %>">
											<ul class="user-tags">

												<%
												StringBuilder sb = new StringBuilder();

												for (AssetTag assetTag : assetTags) {
													PortletURL searchURL = ((LiferayPortletResponse)renderResponse).createRenderURL("3");

													searchURL.setWindowState(WindowState.MAXIMIZED);

													searchURL.setParameter("groupId", "0");
													searchURL.setParameter("keywords", assetTag.getName());
													searchURL.setParameter("struts_action", "/search/search");

													sb.append("<li><a href=\"");
													sb.append(searchURL);
													sb.append("\">");
													sb.append(assetTag.getName());
													sb.append("</a></li>");
												}
												%>

												<%= sb.toString() %>
											</ul>
										</div>
									</c:when>
									<c:otherwise>

										<%
										Group group = themeDisplay.getScopeGroup();
										%>

										<liferay-ui:message arguments="<%= PortalUtil.getUserName(user2.getUserId(), group.getDescriptiveName(locale)) %>" key="x-does-not-have-any-tags" />
									</c:otherwise>
								</c:choose>
							</c:if>
						</aui:column>
					</c:if>
				</aui:layout>
			</div>

			<c:if test="<%= showRecentActivity && UserPermissionUtil.contains(permissionChecker, user2.getUserId(), ActionKeys.VIEW) %>">
				<div class="user-information-title">
					<liferay-ui:message key="recent-activity" />
				</div>

				<liferay-ui:social-activities
					activities="<%= SocialActivityLocalServiceUtil.getUserActivities(user2.getUserId(), 0, 10) %>"
					feedEnabled="<%= false %>"
				/>
			</c:if>
		</c:if>
	</div>
</c:if>

<c:if test="<%= (themeDisplay.getUserId() == user2.getUserId()) && (showCompleteYourProfile || showUsersInformation || showTags) && ((displayStyle == ContactsConstants.DISPLAY_STYLE_DETAIL) || (displayStyle == ContactsConstants.DISPLAY_STYLE_FULL)) && UserPermissionUtil.contains(permissionChecker, user2.getUserId(), ActionKeys.VIEW) %>">
	<aui:script use="aui-base">
			var userInformation = A.one('#<portlet:namespace />userInformation');

			userInformation.delegate(
				'click',
				function(event) {
					<portlet:namespace />openDialog(event);
				},
				'.field-group, .action-field'
			);

			var <portlet:namespace />openDialog = function(event) {
				var node = event.currentTarget;

				var uri = '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/contacts_center/edit_user_dialogs.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:renderURL>';

				if (node.getAttribute('data-sectionId')) {
					uri = Liferay.Util.addParams('curSectionId=' + node.getAttribute('data-sectionId'), uri) || uri;
				}

				if (node.getAttribute('data-extension')) {
					uri = Liferay.Util.addParams('extension=' + node.getAttribute('data-extension'), uri) || uri;
				}

				var dialog = new A.Dialog(
					{
						centered: true,
						constrain2view: true,
						cssClass: 'profile-dialog',
						destroyOnClose: true,
						modal: true,
						resizable: false,
						title: node.getAttribute('data-title'),
						width: 500
					}
				).plug(
					A.Plugin.IO,
					{
						uri: uri
					}
				).render();
			};
	</aui:script>
</c:if>