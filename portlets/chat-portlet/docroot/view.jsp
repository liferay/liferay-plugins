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

<c:if test="<%= themeDisplay.isSignedIn() && !(BrowserSnifferUtil.isIe(request) && (BrowserSnifferUtil.getMajorVersion(request) < 7)) && !BrowserSnifferUtil.isMobile(request) %>">

	<%
	Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletDisplay.getId());
	%>

	<liferay-util:html-top>
		<link href="<%= PortalUtil.getStaticResourceURL(request, request.getContextPath() + "/css/main.css", portlet.getTimestamp()) %>" rel="stylesheet" type="text/css" />
	</liferay-util:html-top>

	<liferay-util:html-bottom>
		<script defer="defer" src="<%= PortalUtil.getStaticResourceURL(request, request.getContextPath() + "/js/main.js", portlet.getTimestamp()) %>" type="text/javascript"></script>
	</liferay-util:html-bottom>

	<%
	Status status = StatusLocalServiceUtil.getUserStatus(themeDisplay.getUserId());

	boolean online = status.getOnline();
	String activePanelId = status.getActivePanelId();
	String statusMessage = HtmlUtil.escape(status.getMessage());
	boolean playSound = status.getPlaySound();

	List<Object[]> buddies = ChatUtil.getBuddies(themeDisplay.getCompanyId(), themeDisplay.getUserId());

	int buddiesCount = buddies.size();
	%>

	<div class="portlet-chat" id="chatBar">
		<div class="chat-bar">
			<div class="chat-sound"></div>

			<div class="chat-status">
				<div class="status-message">
					<c:if test="<%= Validator.isNotNull(statusMessage) %>">
						<%= LanguageUtil.format(pageContext, "you-are-x", "<strong>" + statusMessage + "</strong>", false) %>
					</c:if>
				</div>
			</div>

			<div class="chat-tabs-container">
				<ul class="chat-tabs">
					<li class="buddy-list <%= activePanelId.equals("buddylist") ? "selected" : "" %>">
						<div class="panel-trigger" panelId="buddylist">
							<span class="trigger-name"><%= LanguageUtil.format(pageContext, "online-friends-x", "(" + buddiesCount + ")", false) %></span>
						</div>

						<div class="chat-panel">
							<div class="panel-window">
								<div class="panel-button minimize"></div>

								<div class="panel-title">
									<%= LanguageUtil.format(pageContext, "online-friends-x", "(" + buddiesCount + ")", false) %>
								</div>

								<aui:input cssClass="search-buddies" inputCssClass="search-buddies-field" label="" name="searchBuddies" />

								<div class="panel-content">
									<ul class="lfr-component online-users">

										<%
										for (Object[] buddy : buddies) {
											long userId = (Long)buddy[0];
											String firstName = (String)buddy[2];
											String middleName = (String)buddy[3];
											String lastName = (String)buddy[4];
											long portraitId = (Long)buddy[5];
										%>

											<li class="user active" userId="<%= userId %>">
												<img alt="" src="<%= themeDisplay.getPathImage() %>/user_portrait?img_id=<%= portraitId %>">

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
					<li class="chat-settings <%= activePanelId.equals("settings") ? "selected" : "" %>">
						<div class="panel-trigger" panelId="settings">
							<span class="trigger-name"><liferay-ui:message key="settings" /></span>
						</div>

						<div class="chat-panel">
							<div class="panel-window">
								<div class="panel-button minimize"></div>

								<div class="panel-title"><liferay-ui:message key="settings" /></div>

								<ul class="lfr-component settings">
									<li>
										<label for="statusMessage"><%= LanguageUtil.format(pageContext, "x-is", HtmlUtil.escape(user.getFullName()), false) %></label>

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

		<input id="activePanelId" type="hidden" value="<%= activePanelId %>" />
		<input id="chatPortletId" type="hidden" value="<%= portletDisplay.getId() %>" />

		<div class="chat-extensions aui-helper-hidden">

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