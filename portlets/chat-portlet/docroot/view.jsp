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

<c:if test="<%= themeDisplay.isSignedIn() && !(BrowserSnifferUtil.isIe(request) && (BrowserSnifferUtil.getMajorVersion(request) < 7)) && !BrowserSnifferUtil.isMobile(request) %>">

	<%
	Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletDisplay.getId());
	%>

	<liferay-util:html-top>
		<link data-senna-track="permanent" href="<%= PortalUtil.getStaticResourceURL(request, PortalUtil.getPathContext(request) + "/css/main.css", portlet.getTimestamp()) %>" rel="stylesheet" type="text/css" />
	</liferay-util:html-top>

	<liferay-util:html-bottom>
		<script defer="defer" src="<%= PortalUtil.getStaticResourceURL(request, PortalUtil.getPathContext(request) + "/js/main.js", portlet.getTimestamp()) %>" type="text/javascript"></script>
	</liferay-util:html-bottom>

	<%
	Status status = StatusLocalServiceUtil.getUserStatus(themeDisplay.getUserId());

	boolean online = status.getOnline();

	JSONObject activePanelIdsJSONObject = null;

	String openPanelId = StringPool.BLANK;

	if (Validator.isNotNull(status.getActivePanelIds())) {
		activePanelIdsJSONObject = JSONFactoryUtil.createJSONObject(status.getActivePanelIds());

		openPanelId = activePanelIdsJSONObject.getString("open");
	}

	String statusMessage = HtmlUtil.escape(status.getMessage());
	boolean playSound = status.getPlaySound();

	List<Object[]> buddies = BuddyFinderUtil.getBuddies(themeDisplay.getCompanyId(), themeDisplay.getUserId());

	int buddiesCount = buddies.size();
	%>

	<div class="portlet-chat" id="chatBar">
		<div class="chat-bar">
			<div class="chat-sound"></div>

			<div class="chat-status">
				<div class="status-message">
					<c:if test="<%= Validator.isNotNull(statusMessage) %>">
						<%= LanguageUtil.format(request, "you-are-x", "<strong>" + statusMessage + "</strong>", false) %>
					</c:if>
				</div>
			</div>

			<div class="chat-tabs-container">
				<ul class="chat-tabs">
					<li class="buddy-list loading <%= openPanelId.equals("buddylist") ? "selected" : "" %>">
						<div class="chat-panel-trigger" panelId="buddylist" tabindex="0">
							<span class="trigger-name"><%= LanguageUtil.format(request, "online-friends-x", "(" + buddiesCount + ")", false) %></span>
						</div>

						<div class="chat-panel">
							<div class="chat-panel-window">
								<div class="chat-panel-button minimize"></div>

								<div class="chat-panel-title">
									<%= LanguageUtil.format(request, "online-friends-x", "(" + buddiesCount + ")", false) %>
								</div>

								<aui:input cssClass="search-buddies" label="" name="searchBuddies" placeholder="search" />

								<div class="chat-panel-content">
									<ul class="lfr-component online-users">

										<%
										for (Object[] buddy : buddies) {
											String firstName = (String)buddy[1];
											long groupId = (Long)buddy[2];
											String lastName = (String)buddy[3];
											boolean male = (Boolean)buddy[4];
											String middleName = (String)buddy[5];
											long portraitId = (Long)buddy[6];
											String screenName = (String)buddy[7];
											long userId = (Long)buddy[8];
											String userUuid = (String)buddy[9];

											Group group = GroupLocalServiceUtil.fetchGroup(groupId);
										%>

											<li class="active user" data-displayURL="<%= group.getDisplayURL(themeDisplay, false) %>" data-groupId="<%= groupId %>" data-userId="<%= userId %>" tabindex="0">
												<img alt="<%= HtmlUtil.escape(ContactConstants.getFullName(firstName, middleName, lastName)) %>" src="<%= UserConstants.getPortraitURL(themeDisplay.getPathImage(), male, portraitId, userUuid) %>" />

												<div class="name">
													<%= HtmlUtil.escape(ContactConstants.getFullName(firstName, middleName, lastName)) %>
												</div>
											</li>

										<%
										}
										%>

									</ul>
								</div>

								<div style="clear: both;"></div>
							</div>
						</div>
					</li>
					<li class="chat-settings <%= openPanelId.equals("settings") ? "selected" : "" %>">
						<div class="chat-panel-trigger" panelId="settings" tabindex="0">
							<span class="trigger-name"><liferay-ui:message key="settings" /></span>
						</div>

						<div class="chat-panel">
							<div class="chat-panel-window">
								<div class="chat-panel-button minimize"></div>

								<div class="chat-panel-title"><liferay-ui:message key="settings" /></div>

								<ul class="lfr-component settings">
									<li>
										<label for="statusMessage"><%= LanguageUtil.format(request, "x-is", HtmlUtil.escape(user.getFullName()), false) %></label>

										<input id="statusMessage" type="text" value="<%= statusMessage %>" />
									</li>
									<li>
										<label for="onlineStatus"><input <%= online ? "checked=\"checked\"" : "" %> id="onlineStatus" type="checkbox" /> <liferay-ui:message key="show-me-as-online" /></label>
									</li>
									<li>
										<label for="playSound"><input <%= playSound ? "checked=\"checked\"" : "" %> id="playSound" type="checkbox" /> <liferay-ui:message key="play-a-sound-when-i-receive-a-new-message-in-a-hidden-window" /> </label>
									</li>
									<li class="show-notifications-setting">
										<label for="showNotifications"><input disabled="disabled" id="showNotifications" type="checkbox" /> <liferay-ui:message key="enable-desktop-notifications-for-new-messages" /> </label>
									</li>
								</ul>

								<div class="ctrl-holder">
									<input id="saveSettings" type="submit" value="<liferay-ui:message key="save" />">
								</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>

		<input id="activePanelIds" type="hidden" value="<%= HtmlUtil.escapeAttribute(status.getActivePanelIds()) %>" />
		<input id="chatPortletId" type="hidden" value="<%= portletDisplay.getId() %>" />

		<div class="chat-extensions hide">

			<%
			Map<String, String> extensions = ChatExtensionsUtil.getExtensions();

			Set<String> servletContextNames = extensions.keySet();

			for (String servletContextName : servletContextNames) {
				String extensionPath = extensions.get(servletContextName);
				ServletContext extensionServletContext = ServletContextPool.get(servletContextName);
			%>

				<liferay-util:include page="<%= extensionPath %>" servletContext="<%= extensionServletContext %>" />

			<%
			}
			%>

		</div>
	</div>

	<aui:input name="currentChatServerTime" type="hidden" useNamespace="false" value="<%= System.currentTimeMillis() %>" />
</c:if>