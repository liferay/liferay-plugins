<%--
/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/init.jsp" %>

<%
long userId = ParamUtil.getLong(request, "userId");

Group group = GroupLocalServiceUtil.getGroup(scopeGroupId);

User curUser = null;

if (group.isUser()) {
	curUser = UserLocalServiceUtil.getUserById(group.getClassPK());
}
else {
	try {
		if (userId > 0) {
			curUser = UserLocalServiceUtil.getUser(userId);
		}
	}
	catch (Exception e) {
	}

	if (curUser == null) {
		List<User> users = UserLocalServiceUtil.getGroupUsers(layout.getGroupId());

		if (!users.isEmpty()) {
			curUser = users.get(0);
		}
	}
}

if (curUser != null) {
	curUser = curUser.toEscapedModel();
}
%>

<c:choose>
	<c:when test="<%= curUser != null %>">

		<%
		Contact curContact = curUser.getContact();

		curContact = curContact.toEscapedModel();
		%>

		<h1><%= curUser.getFullName() %> : <liferay-ui:message key="profile" /></h1>

		<table width="100%">
		<tr>
			<td valign="top">
				<div>
					<img alt="<%= curUser.getFullName() %>" src="<%= curUser.getPortraitURL(themeDisplay) %>" />

					<c:if test="<%= curUser.getUserId() == user.getUserId() %>">
						<div class="profile-controls">
							<a class="so-edit-profile" href="javascript:;"><liferay-ui:message key="edit-profile" /></a>

							<a class="so-edit-projects" href="javascript:;"><liferay-ui:message key="edit-projects" /></a>

							<a class="so-change-settings" href="javascript:;"><liferay-ui:message key="change-settings" /></a>
						</div>
					</c:if>

					<div class="relations-controls">
						<c:choose>
							<c:when test="<%= SocialRelationLocalServiceUtil.hasRelation(user.getUserId(), curUser.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND) %>">

								<%
								PortletURL removeFriendURL = renderResponse.createActionURL();

								removeFriendURL.setWindowState(WindowState.NORMAL);

								removeFriendURL.setParameter(ActionRequest.ACTION_NAME, "deleteFriend");
								removeFriendURL.setParameter("redirect", PortalUtil.getLayoutURL(layout, themeDisplay));
								removeFriendURL.setParameter("userId", String.valueOf(curUser.getUserId()));

								String removeFriendHREF = "javascript:if (confirm('" + LanguageUtil.format(pageContext, "are-you-sure-you-want-to-remove-x-as-a-friend-x-will-not-be-notified", curUser.getFullName()) + "')) { submitForm(document.hrefFm, '" + removeFriendURL + "'); }";
								%>

								<a href="<%= removeFriendHREF %>"><liferay-ui:message key="remove-friend" /></a>
							</c:when>
							<c:when test="<%= SocialRequestLocalServiceUtil.hasRequest(user.getUserId(), User.class.getName(), user.getUserId(), ProfilesRequestKeys.ADD_FRIEND, curUser.getUserId(), SocialRequestConstants.STATUS_PENDING) %>">
								<div>
									<liferay-ui:message key="friend-requested" />
								</div>
							</c:when>
							<c:when test="<%= SocialRelationLocalServiceUtil.isRelatable(user.getUserId(), curUser.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND) %>">

								<%
								PortletURL addAsFriendURL = renderResponse.createActionURL();

								addAsFriendURL.setWindowState(WindowState.NORMAL);

								addAsFriendURL.setParameter(ActionRequest.ACTION_NAME, "addFriend");
								addAsFriendURL.setParameter("redirect", PortalUtil.getLayoutURL(layout, themeDisplay));
								addAsFriendURL.setParameter("userId", String.valueOf(curUser.getUserId()));
								%>

								<a href="<%= addAsFriendURL.toString() %>"><liferay-ui:message key="add-as-friend" /></a>
							</c:when>
						</c:choose>
					</div>
				</div>
			</td>
			<td valign="top">
				<div class="profile-area">
					<div class="user-name">
						<%= curUser.getFullName() %>
					</div>

					<div class="user-title">
						<%= curContact.getJobTitle() %>
					</div>

					<div class="user-email">
						<%= curUser.getEmailAddress() %>
					</div>
				</div>

				<table class="extend-information">
				<tr>
					<td class="column-1">
						<div class="profile-area user-contact">
							<h3><liferay-ui:message key="contact-information" /></h3>

							<table>

							<%
							List<Address> addresses = AddressLocalServiceUtil.getAddresses(themeDisplay.getCompanyId(), Contact.class.getName(), curContact.getContactId());
							%>

							<c:if test="<%= !addresses.isEmpty() %>">
								<tr>
									<td class="lfr-label">
										<c:choose>
											<c:when test="<%= addresses.size() == 1 %>">
												<liferay-ui:message key="address" />
											</c:when>
											<c:otherwise>
												<liferay-ui:message key="addresses" />
											</c:otherwise>
										</c:choose>
									</td>
									<td>

										<%
										for (Address address : addresses) {
											address = address.toEscapedModel();

											String region = StringPool.BLANK;

											if (address.getRegionId() != 0) {
												region = RegionServiceUtil.getRegion(address.getRegionId()).getRegionCode();
											}
										%>

											<div class="user-address">
												<div class="line-1">
													<%= address.getStreet1() %>
												</div>

												<div class="line-2">
													<%= address.getStreet2() %>
												</div>

												<div class="line-3">
													<%= address.getStreet3() %>
												</div>

												<div class="line-4">
													<%= address.getCity() %>, <%= region %> <%= address.getZip() %>
												</div>
											</div>

										<%
										}
										%>

									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
							</c:if>

							<%
							List<Phone> phones = PhoneLocalServiceUtil.getPhones(themeDisplay.getCompanyId(), Contact.class.getName(), curContact.getContactId());
							%>

							<c:if test="<%= !phones.isEmpty() %>">

								<%
								for (Phone phone : phones) {
									phone = phone.toEscapedModel();

									String extension = phone.getExtension();

									if (!extension.equals(StringPool.BLANK)) {
										extension = "x" + extension;
									}
								%>

									<tr>
										<td class="lfr-label">
											<%= phone.getType().getName() %> <liferay-ui:message key="number" />
										</td>
										<td>
											<%= phone.getNumber() %> <%= extension %>
										</td>
									</tr>

								<%
								}
								%>

								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
							</c:if>

							<%
							List<Website> websites = WebsiteLocalServiceUtil.getWebsites(themeDisplay.getCompanyId(), Contact.class.getName(), curContact.getContactId());
							%>

							<c:if test="<%= !websites.isEmpty() %>">
								<tr>
									<td class="lfr-label">
										<c:choose>
											<c:when test="<%= websites.size() == 1 %>">
												<liferay-ui:message key="website" />
											</c:when>
											<c:otherwise>
												<liferay-ui:message key="websites" />
											</c:otherwise>
										</c:choose>
									</td>
									<td>

										<%
										for (Website website : websites) {
										%>

											<div class="user-website">
												<a href="<%= website.getUrl() %>"><%= website.getUrl() %></a>
											</div>

										<%
										}
										%>

									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
							</c:if>

							<%
							List<EmailAddress> emailAddresses = EmailAddressLocalServiceUtil.getEmailAddresses(themeDisplay.getCompanyId(), Contact.class.getName(), curContact.getContactId());
							%>

							<c:if test="<%= Validator.isNotNull(curUser.getEmailAddress()) || !emailAddresses.isEmpty() %>">
								<tr>
									<td class="lfr-label">
										<c:choose>
											<c:when test="<%= !emailAddresses.isEmpty() %>">
												<liferay-ui:message key="email-addresses" />
											</c:when>
											<c:otherwise>
												<liferay-ui:message key="email-address" />
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<div class="user-email">
											<a href="mailto:<%= curUser.getEmailAddress() %>"><%= curUser.getEmailAddress() %></a>
										</div>

										<%
										for (EmailAddress emailAddress : emailAddresses) {
										%>

											<div class="user-email">
												<a href="mailto:<%= emailAddress.getAddress() %>"><%= emailAddress.getAddress() %></a>
											</div>

										<%
										}
										%>

									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
							</c:if>

							<c:if test="<%= Validator.isNotNull(curContact.getAimSn()) %>">
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="aim" />
									</td>
									<td>
										<%= curContact.getAimSn() %>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
							</c:if>

							<c:if test="<%= Validator.isNotNull(curContact.getIcqSn()) %>">
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="icq" />
									</td>
									<td>
										<%= curContact.getIcqSn() %>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
							</c:if>

							<c:if test="<%= Validator.isNotNull(curContact.getJabberSn()) %>">
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="jabber" />
									</td>
									<td>
										<%= curContact.getJabberSn() %>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
							</c:if>

							<c:if test="<%= Validator.isNotNull(curContact.getMsnSn()) %>">
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="msn" />
									</td>
									<td>
										<%= curContact.getMsnSn() %>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
							</c:if>

							<c:if test="<%= Validator.isNotNull(curContact.getSkypeSn()) %>">
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="skype" />
									</td>
									<td>
										<%= curContact.getSkypeSn() %>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
							</c:if>

							<c:if test="<%= Validator.isNotNull(curContact.getYmSn()) %>">
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="ym" />
									</td>
									<td>
										<%= curContact.getYmSn() %>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
							</c:if>

							<c:if test="<%= Validator.isNotNull(curContact.getFacebookSn()) %>">
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="facebook" />
									</td>
									<td>
										<%= curContact.getFacebookSn() %>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
							</c:if>

							<c:if test="<%= Validator.isNotNull(curContact.getMySpaceSn()) %>">
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="myspace" />
									</td>
									<td>
										<%= curContact.getMySpaceSn() %>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
							</c:if>

							<c:if test="<%= Validator.isNotNull(curContact.getTwitterSn()) %>">
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="twitter" />
									</td>
									<td>
										<%= curContact.getTwitterSn() %>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br />
									</td>
								</tr>
							</c:if>

							</table>
						</div>
					</td>
					<td class="column-2">
						<div class="profile-area user-sites">
							<h3><liferay-ui:message key="sites" /></h3>

							<%
							LiferayPortletURL myPlacesURL = PortletURLFactoryUtil.create(request, PortletKeys.MY_PLACES, layout.getPlid(), PortletRequest.ACTION_PHASE);

							myPlacesURL.setWindowState(LiferayWindowState.NORMAL);

							myPlacesURL.setParameter("struts_action", "/my_places/view");
							myPlacesURL.setParameter("privateLayout", "0");

							List<Group> groups = curUser.getGroups();

							for (Group curGroup : groups) {
								curGroup.toEscapedModel();

								myPlacesURL.setParameter("groupId", String.valueOf(curGroup.getGroupId()));
							%>

								<div class="site-name">
									<c:if test="<%= curGroup.hasPublicLayouts() || GroupLocalServiceUtil.hasUserGroup(themeDisplay.getUserId(), curGroup.getGroupId()) %>">
										<a href="<%= myPlacesURL.toString() %>"><%= curGroup.getName() %></a>
									</c:if>
								</div>

							<%
							}
							%>

						</div>

						<div class="profile-area user-tags">
							<h3><liferay-ui:message key="tags" /></h3>

							<%
							StringBuilder sb = new StringBuilder();

							List<AssetTag> tags = AssetTagLocalServiceUtil.getTags(User.class.getName(), curUser.getUserId());

							Iterator itr = tags.iterator();

							while (itr.hasNext()) {
								AssetTag tag = (AssetTag)itr.next();

								sb.append("<nobr>");
								sb.append(tag.getName());
								sb.append("</nobr>");

								if (itr.hasNext()) {
									sb.append(", ");
								}
							}
							%>

							<%= sb.toString() %>

						</div>
					</td>
				</tr>
				<tr>
					<td class="column-3" colspan="2">
						<div class="profile-area user-notes">
							<h3><liferay-ui:message key="notes" /></h3>

							<%= curUser.getComments() %>
						</div>
					</td>
				</tr>
				<tr>
					<td class="column-4" colspan="2">
						<div class="profile-area user-projects">
							<h3><liferay-ui:message key="projects" /></h3>

							<%
							List<ProjectsEntry> projectsEntries = ProjectsEntryLocalServiceUtil.getUserProjectsEntries(curUser.getUserId());

							for (ProjectsEntry projectsEntry : projectsEntries) {
								projectsEntry = projectsEntry.toEscapedModel();
							%>

								<div class="project">
									<h4 class="project-title">
										<%= projectsEntry.getTitle() %>

										<span>
											<%= dateFormatDate.format(projectsEntry.getStartDate()) %>

											-

											<c:choose>
												<c:when test="<%= projectsEntry.getEndDate() != null %>">
													<%= dateFormatDate.format(projectsEntry.getEndDate()) %>
												</c:when>
												<c:otherwise>
													<liferay-ui:message key="current" />
												</c:otherwise>
											</c:choose>
										</span>
									</h4>

									<div class="project-body">
										<%= projectsEntry.getDescription() %>
									</div>

									<div class="project-footer">
										<span><liferay-ui:message key="other-members" />:</span><%= projectsEntry.getData() %>
									</div>
								</div>

							<%
							}
							%>

						</div>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</c:when>
	<c:otherwise>
		<h1><liferay-ui:message key="profile" /></h1>

		<div class="portlet-msg-error">
			<liferay-ui:message key="this-site-has-no-members" />
		</div>
	</c:otherwise>
</c:choose>