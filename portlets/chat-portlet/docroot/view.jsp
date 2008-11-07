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

<c:if test="<%= themeDisplay.isSignedIn() %>">

	<%
	Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletDisplay.getId());

	boolean online = true;
	String activePanelId = StringPool.BLANK;
	String statusMessage = StringPool.BLANK;
	boolean playSound = true;

	try {
		Status status = StatusLocalServiceUtil.getUserStatus(themeDisplay.getUserId());

		online = status.getOnline();
		activePanelId = status.getActivePanelId();
		statusMessage = status.getMessage();
		playSound = status.getPlaySound();
	}
	catch (NoSuchStatusException nsse) {
	}
	%>

	<%@ include file="/buddies.jsp" %>

	<%
	int buddiesCount = buddies.size();

	if (buddiesCount > 0) {
		buddiesCount -= 1;
	}
	%>

	<link href="<%= request.getContextPath() %>/css.jsp?themeId=<%= themeDisplay.getTheme().getThemeId() %>&amp;colorSchemeId=<%= themeDisplay.getColorScheme().getColorSchemeId() %>&amp;t=<%= portlet.getTimestamp() %>" rel="stylesheet" type="text/css" />

	<script src="<%= request.getContextPath() %>/javascript.js?t=<%= portlet.getTimestamp() %>" type="text/javascript"></script>

	<div id="btm-dock">
		<div id="btm-dock-sound"></div>

		<div id="btm-dock-left">
			<div id="chatMyStatus"><%= Validator.isNotNull(statusMessage) ? "You are <strong>" + statusMessage + "</strong>" : "..." %></div>
		</div>

		<div id="btm-dock-right">
			<ul id="chat-tabs">
				<li class="buddylist <%= activePanelId.equals("buddylist") ? "selected" : "" %>">
					<div class="link" userId="buddylist"><span class="trigger-name">Online Friends (<%= buddiesCount %>)</span></div>

					<div class="popup">
						<div class="popup-window">
							<div class="popup-button minimize"></div>

							<div class="popup-title">Online Friends (<%= buddiesCount %>)</div>

							<div class="popup-content">
								<ul class="lfr-component online-users">

									<%
									for (Object[] buddy : buddies) {
										long userId = (Long)buddy[0];
										String firstName = (String)buddy[1];
										String middleName = (String)buddy[2];
										String lastName = (String)buddy[3];
										long portraitId = (Long)buddy[4];
										boolean awake = (Boolean)buddy[5];

										String fullName = ContactConstants.getFullName(firstName, middleName, lastName);

										if (userId == themeDisplay.getUserId()) {
											continue;
										}

									%>

										<li class="<%= awake ? "active" : "idle" %>" userId="<%= userId %>">
											<img src="<%= themeDisplay.getPathImage() %>/user_portrait?img_id=<%= portraitId %>">

											<div class="name">
												<%= fullName %>
											</div>
										</li>

									<%
									}
									%>

								</ul>
							</div>
						</div>
					</div>
				</li>
				<li class="settings <%= activePanelId.equals("settings") ? "selected" : "" %>">
					<div class="link" userId="settings">
						<span class="trigger-name">Settings</span>
					</div>

					<div class="popup">
						<div class="popup-window">
							<div class="popup-button minimize"></div>

							<div class="popup-title">Settings</div>

							<ul class="lfr-component settings">
								<li>
									<label for="statusMessage"><%= user.getFullName() %> is...</label>

									<input id="statusMessage" type="text" value="<%= statusMessage %>" />
								</li>
								<li>
									<label for="onlineStatus"><input <%= online ? "checked=\"checked\"" : "" %> id="onlineStatus" type="checkbox" /> Show me as online</label>
								</li>
								<li>
									<label for="playSound"><input <%= playSound ? "checked=\"checked\"" : "" %> id="playSound" type="checkbox" /> Play a sound when I recieve a new message in a hidden window.</label>
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
</c:if>