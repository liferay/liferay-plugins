<%
/**
 * Copyright (c) 2008-2009 Liferay, Inc. All rights reserved.
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
%>

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

		if (users.size() > 0) {
			curUser = users.get(0);
		}
	}
}

String currentURL = PortalUtil.getCurrentURL(request);

boolean isEditable = (curUser.getUserId() == user.getUserId());
%>

<c:choose>
	<c:when test="<%= curUser != null %>">

		<%
		Contact curContact = curUser.getContact();
		%>

		<h1>
			<%= curUser.getFullName() %> : <liferay-ui:message key="profile" />
		</h1>

		<table width="100%">
		<tr>
			<td valign="top">
				<div>
					<c:choose>
						<c:when test="<%= isEditable %>">
							<liferay-portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>" portletName="<%= PortletKeys.MY_ACCOUNT %>" var="editUserPortraitURL">
								<liferay-portlet:param name="struts_action" value="/my_account/edit_user_portrait" />
								<liferay-portlet:param name="redirect" value="<%= currentURL %>" />
								<liferay-portlet:param name="p_u_i_d" value="<%= String.valueOf(curUser.getUserId()) %>" />
								<liferay-portlet:param name="portrait_id" value="<%= String.valueOf(curUser.getPortraitId()) %>" />
							</liferay-portlet:renderURL>

							<a class="change-avatar" href="javascript: _<%= PortletKeys.MY_ACCOUNT %>_openEditUserPortraitWindow('<%= editUserPortraitURL %>');"><img alt="<liferay-ui:message key="avatar" />" class="avatar" id="<portlet:namespace />avatar" src='<%= themeDisplay.getPathImage() %>/user_<%= user.isFemale() ? "female" : "male" %>_portrait?img_id=<%= user.getPortraitId() %>&t=<%= ImageServletTokenUtil.getToken(user.getPortraitId()) %>' /></a>

							<br />
							<a class="edit-avatar" href="javascript: _<%= PortletKeys.MY_ACCOUNT %>_openEditUserPortraitWindow('<%= editUserPortraitURL %>');"><img src="<%= themeDisplay.getPathThemeImage() %>/common/edit.png" /></a>

						</c:when>
						<c:otherwise>
							<img alt="<%= curUser.getFullName() %>" src="<%= themeDisplay.getPathImage() %>/user_<%= (curUser.isFemale() ? "female" : "male") %>_portrait?img_id=<%= curUser.getPortraitId() %>&t=<%= ImageServletTokenUtil.getToken(curUser.getPortraitId()) %>" />
						</c:otherwise>
					</c:choose>
				</div>
			</td>
			<td valign="top" width="90%">
				<div class="profile-area">
					<div class="user-name">
						<span class="<%= isEditable ? "editable" : "" %>" id="firstName"><%= curUser.getFirstName() %></span> <span class="<%= isEditable ? "editable" : "" %>" id="lastName"><%= curUser.getLastName() %></span>
					</div>

					<div class="user-title">
						<span class="<%= isEditable ? "editable" : "" %>" id="title"><%= curContact.getJobTitle() %></span>
					</div>

					<div class="user-email">
						<span class="<%= isEditable ? "editable" : "" %>" id="email"><%= curUser.getEmailAddress() %></span>
					</div>
					<c:choose>
						<c:when test="<%= isEditable %>">
							<div class="user-tags">
								<form action="<portlet:actionURL />" method="post" name="<portlet:namespace />tagsForm">
								<input name="<portlet:namespace />id" type="hidden" value="updateTags" />
								<liferay-ui:tags-selector
									className="<%= User.class.getName() %>"
									classPK="<%= curUser.getUserId() %>"
									hiddenInput="tagsEntries"
								/>
								<br />
								<input type="submit" value="<liferay-ui:message key="save" />" />
								</form>
							</div>
						</c:when>
						<c:otherwise>
							<div class="user-tags">
								<liferay-ui:tags-summary
									className="<%= User.class.getName() %>"
									classPK="<%= curUser.getUserId() %>"
								/>
							</div>
						</c:otherwise>
					</c:choose>
				</div>

				<div class="profile-area user-contact">
					<h3>
						<liferay-ui:message key="contact-information" />
					</h3>

					<table>

					<c:if test="<%= isEditable %>">
						<tr class="add-address">
							<td class="lfr-label">
								<liferay-ui:message key="add-address" />
							</td>
							<td>
								<input class="add-address-button" type="button" value="<liferay-ui:message key="add-address" />" />
							</td>
						</tr>
						<tr class="address-form">
							<td class="lfr-label">
								<liferay-ui:message key="add-address" />
							</td>
							<td>
								<form action="<liferay-portlet:actionURL />" method="post" name="<portlet:namespace />fm1">
								<input id="<portlet:namespace />addressId" name="<portlet:namespace />addressId" type="hidden" value="" />
								<input id="<portlet:namespace />addressCmd" name="<portlet:namespace />id" type="hidden" value="addAddress" />
								<table>
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="type" />
									</td>
									<td>
										<select id="<portlet:namespace />addressTypeId" name="<portlet:namespace />addressTypeId">

											<%
											List<ListType> addressTypes = ListTypeServiceUtil.getListTypes(Contact.class.getName() + ".address");

											for (ListType suffix : addressTypes) {
											%>

												<option value="<%= suffix.getListTypeId() %>"><liferay-ui:message key="<%= suffix.getName() %>" /></option>

											<%
											}
											%>

										</select>
									</td>
								</tr>
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="street1" />
									</td>
									<td>
										<liferay-ui:input-field model="<%= Address.class %>" field="street1" />
									</td>
								</tr>
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="street2" />
									</td>
									<td>
										<liferay-ui:input-field model="<%= Address.class %>" field="street2" />
									</td>
								</tr>
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="city" />
									</td>
									<td>
										<liferay-ui:input-field model="<%= Address.class %>" field="city" />
									</td>
								</tr>
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="zip" />
									</td>
									<td>
										<liferay-ui:input-field model="<%= Address.class %>" field="zip" />
									</td>
								</tr>
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="country" />
									</td>
									<td>
										<select id="<portlet:namespace />addressCountryId" name="<portlet:namespace />addressCountryId"></select>
									</td>
								</tr>
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="region" />
									</td>
									<td>
										<select id="<portlet:namespace />addressRegionId" name="<portlet:namespace />addressRegionId"></select>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<input name="submit" type="submit" value="<liferay-ui:message key="submit" />" />
										<input class="cancel-address-button" name="cancel" type="button" value="<liferay-ui:message key="cancel" />" />
									</td>
								</tr>
								</table>
								</form>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								&nbsp;
							</td>
						</tr>
					</c:if>

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
									String region = StringPool.BLANK;

									if (address.getRegionId() != 0) {
										region = RegionServiceUtil.getRegion(address.getRegionId()).getRegionCode();
									}
								%>

									<div class="user-address">
										<div class="line-1">
											<span class="street1"><%= address.getStreet1() %></span>
										</div>

										<div class="line-2">
											<span class="street2"><%= address.getStreet2() %></span>
										</div>

										<div class="line-4">
											<span class="city"><%= address.getCity() %></span>, <span class="region"><%= region %></span> <span class="zip"><%= address.getZip() %></span>
										</div>
										<div class="user-address-controls" id="<portlet:namespace />address<%=address.getAddressId() %>">
											<a class="edit-address" href="javascript: ;"><img src="<%= themeDisplay.getPathThemeImage() %>/common/edit.png" /></a>
											<a class="delete-address" href="javascript: ;"><img src="<%= themeDisplay.getPathThemeImage() %>/common/delete.png" /></a>
										</div>
									</div>

								<%
								}
								%>

							</td>
						</tr>
					</c:if>

					<c:if test="<%= isEditable %>">
						<tr class="add-phone-number">
							<td class="lfr-label">
								<liferay-ui:message key="add-phone-number" />
							</td>
							<td>
								<input class="add-phone-number-button" type="button" value="<liferay-ui:message key="add-phone-number" />" />
							</td>
						</tr>
						<tr class="phone-number-form">
							<td class="lfr-label">
								<liferay-ui:message key="add-phone-number" />
							</td>
							<td>
								<form action="<liferay-portlet:actionURL />" method="post" name="<portlet:namespace />fm2">
								<input id="<portlet:namespace />phoneCmd" name="<portlet:namespace />id" type="hidden" value="addPhoneNumber" />
								<input id="<portlet:namespace />phoneId" name="<portlet:namespace />phoneId" type="hidden" />
								<table>
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="type" />
									</td>
									<td>
										<select id="<portlet:namespace />phoneType" name="<portlet:namespace />type">

												<%
												List<ListType> phoneTypes = ListTypeServiceUtil.getListTypes(Contact.class.getName() + ".phone");

												for (ListType suffix : phoneTypes) {
												%>

													<option value="<%= suffix.getListTypeId() %>"><liferay-ui:message key="<%= suffix.getName() %>" /></option>

												<%
												}
												%>

										</select>
									</td>
								</tr>
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="number" />
									</td>
									<td>
										<liferay-ui:input-field model="<%= Phone.class %>" field="number" />
									</td>
								</tr>
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="extension" />
									</td>
									<td>
										<liferay-ui:input-field model="<%= Phone.class %>" field="extension" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<input name="Submit" type="Submit" value="<liferay-ui:message key="submit" />" />
										<input class="cancel-phone-number-button" name="cancel" type="button" value="<liferay-ui:message key="cancel" />" />
									</td>
								</tr>
								</table>
								</form>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								&nbsp;
							</td>
						</tr>
					</c:if>

					<%
					List<Phone> phones = PhoneLocalServiceUtil.getPhones(themeDisplay.getCompanyId(), Contact.class.getName(), curContact.getContactId());
					%>

					<c:if test="<%= !phones.isEmpty() %>">

						<%
						for (Phone phone : phones) {
							String extension = phone.getExtension();
						%>

						<tr>
							<td class="lfr-label">
								<%= phone.getType().getName() %> <liferay-ui:message key="number" />
							</td>
							<td>
								<div class="phone-number">
									<%= phone.getNumber() %>

									<span class="lfr-label">
										<%= Validator.isNotNull(phone.getExtension()) ? "x" : "" %>
									</span>

									<%= extension %>

									<div class="phone-number-controls" id="<portlet:namespace />phonenumber<%= phone.getPhoneId() %>">
										<a class="edit-phone-number" href="javascript: ;"><img src="<%= themeDisplay.getPathThemeImage() %>/common/edit.png" /></a>
										<a class="delete-phone-number" href="javascript: ;"><img src="<%= themeDisplay.getPathThemeImage() %>/common/delete.png" /></a>
									</div>
								</div>
							</td>
						</tr>
						<%
						}
						%>

						<tr>
							<td colspan="2">
								&nbsp;
							</td>
						</tr>
					</c:if>

					<c:if test="<%= isEditable %>">
						<tr class="add-website">
							<td class="lfr-label">
								<liferay-ui:message key="add-website" />
							</td>
							<td>
								<input class="add-website-button" type="button" value="<liferay-ui:message key="add-website" />" />
							</td>
						</tr>
						<tr class="website-form">
							<td class="lfr-label">
								<liferay-ui:message key="add-website" />
							</td>
							<td>
								<form action="<liferay-portlet:actionURL />" method="post" name="<portlet:namespace />fm3">
								<input id="<portlet:namespace />websiteCmd" name="<portlet:namespace />id" type="hidden" value="addWebsite" />
								<input id="<portlet:namespace />websiteId" name="<portlet:namespace />websiteId" type="hidden" />
								<table>
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="type" />
									</td>
									<td>
										<select id="<portlet:namespace />websiteType" name="<portlet:namespace />type">

											<%
											List<ListType> websiteTypes = ListTypeServiceUtil.getListTypes(Contact.class.getName() + ".website");

											for (ListType suffix : websiteTypes) {
											%>

												<option value="<%= suffix.getListTypeId() %>"><liferay-ui:message key="<%= suffix.getName() %>" /></option>

											<%
											}
											%>

										</select>
									</td>
								</tr>
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="url" />
									</td>
									<td>
										<liferay-ui:input-field model="<%= Website.class %>" field="url" defaultValue="http://" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<input name="Submit" type="Submit" value="<liferay-ui:message key="submit" />" />
										<input class="cancel-website-button" name="cancel" type="button" value="<liferay-ui:message key="cancel" />" />
									</td>
								</tr>
								</table>
								</form>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								&nbsp;
							</td>
						</tr>
					</c:if>

					<%
					List<Website> websites = WebsiteLocalServiceUtil.getWebsites(themeDisplay.getCompanyId(), Contact.class.getName(), curContact.getContactId());
					%>

					<c:if test="<%= websites.size() > 0 %>">
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

									<div class="website">
										<a href="<%= website.getUrl() %>"><%= website.getUrl() %></a>
										<div class="website-controls" id="<portlet:namespace />website<%= website.getWebsiteId() %>">
											<a class="edit-website" href="javascript: ;"><img src="<%= themeDisplay.getPathThemeImage() %>/common/edit.png" /></a>
											<a class="delete-website" href="javascript: ;"><img src="<%= themeDisplay.getPathThemeImage() %>/common/delete.png" /></a>
										</div>
									</div>

								<%
								}
								%>

							</td>
						</tr>
						<tr>
							<td colspan="2">
								&nbsp;
							</td>
						</tr>
					</c:if>

					<%
					boolean showBreak = false;
					%>

					<c:choose>
						<c:when test="<%= Validator.isNotNull(curContact.getAimSn()) %>">
							<tr>
								<td class="lfr-label">
									<liferay-ui:message key="aim" />
								</td>
								<td>
									<span class="<%= isEditable ? "editable" : "" %>" id="aim"><%= curContact.getAimSn() %></span>
									<a class="delete" href="javascript: ;"><img src="<%= themeDisplay.getPathThemeImage() %>/common/delete.png" /></a>
								</td>
							</tr>

							<%
							showBreak = true;
							%>
						</c:when>
						<c:otherwise>
							<c:if test="<%= isEditable %>">
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="aim" />
									</td>
									<td>
										<span class="editable" id="aim"><liferay-ui:message key="click-to-edit-me" /></span>
									</td>
								</tr>
							</c:if>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="<%= Validator.isNotNull(curContact.getIcqSn()) %>">
							<tr>
								<td class="lfr-label">
									<liferay-ui:message key="icq" />
								</td>
								<td>
									<span class="<%= isEditable ? "editable" : "" %>" id="icq"><%= curContact.getIcqSn() %></span>
									<a class="delete" href="javascript: ;"><img src="<%= themeDisplay.getPathThemeImage() %>/common/delete.png" /></a>
								</td>
							</tr>

							<%
							showBreak = true;
							%>
						</c:when>
						<c:otherwise>
							<c:if test="<%= isEditable %>">
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="icq" />
									</td>
									<td>
										<span class="editable" id="icq"><liferay-ui:message key="click-to-edit-me" /></span>
									</td>
								</tr>
							</c:if>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="<%= Validator.isNotNull(curContact.getJabberSn()) %>">
							<tr>
								<td class="lfr-label">
									<liferay-ui:message key="jabber" />
								</td>
								<td>
									<span class="<%= isEditable ? "editable" : "" %>" id="jabber"><%= curContact.getJabberSn() %></span>
									<a class="delete" href="javascript: ;"><img src="<%= themeDisplay.getPathThemeImage() %>/common/delete.png" /></a>
								</td>
							</tr>

							<%
							showBreak = true;
							%>
						</c:when>
						<c:otherwise>
							<c:if test="<%= isEditable %>">
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="jabber" />
									</td>
									<td>
										<span class="editable" id="jabber"><liferay-ui:message key="click-to-edit-me" /></span>
									</td>
								</tr>
							</c:if>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="<%= Validator.isNotNull(curContact.getMsnSn()) %>">
							<tr>
								<td class="lfr-label">
									<liferay-ui:message key="msn" />
								</td>
								<td>
									<span class="<%= isEditable ? "editable" : "" %>" id="msn"><%= curContact.getMsnSn() %></span>
									<a class="delete" href="javascript: ;"><img src="<%= themeDisplay.getPathThemeImage() %>/common/delete.png" /></a>
								</td>
							</tr>

							<%
							showBreak = true;
							%>
						</c:when>
						<c:otherwise>
							<c:if test="<%= isEditable %>">
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="msn" />
									</td>
									<td>
										<span class="editable" id="msn"><liferay-ui:message key="click-to-edit-me" /></span>
									</td>
								</tr>
							</c:if>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="<%= Validator.isNotNull(curContact.getSkypeSn()) %>">
							<tr>
								<td class="lfr-label">
									<liferay-ui:message key="skype" />
								</td>
								<td>
									<span class="<%= isEditable ? "editable" : "" %>" id="skype"><%= curContact.getSkypeSn() %></span>
									<a class="delete" href="javascript: ;"><img src="<%= themeDisplay.getPathThemeImage() %>/common/delete.png" /></a>
								</td>
							</tr>

							<%
							showBreak = true;
							%>
						</c:when>
						<c:otherwise>
							<c:if test="<%= isEditable %>">
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="skype" />
									</td>
									<td>
										<span class="editable" id="skype"><liferay-ui:message key="click-to-edit-me" /></span>
									</td>
								</tr>
							</c:if>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="<%= Validator.isNotNull(curContact.getYmSn()) %>">
							<tr>
								<td class="lfr-label">
									<liferay-ui:message key="ym" />
								</td>
								<td>
									<span class="<%= isEditable ? "editable" : "" %>" id="ym"><%= curContact.getYmSn() %></span>
									<a class="delete" href="javascript: ;"><img src="<%= themeDisplay.getPathThemeImage() %>/common/delete.png" /></a>
								</td>
							</tr>

							<%
							showBreak = true;
							%>
						</c:when>
						<c:otherwise>
							<c:if test="<%= isEditable %>">
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="ym" />
									</td>
									<td>
										<span class="editable" id="ym"><liferay-ui:message key="click-to-edit-me" /></span>
									</td>
								</tr>
							</c:if>
						</c:otherwise>
					</c:choose>

					<c:if test="<%= showBreak %>">
						<tr>
							<td colspan="2">
								&nbsp;
							</td>
						</tr>

						<%
						showBreak = false;
						%>
					</c:if>

					<c:choose>
						<c:when test="<%= Validator.isNotNull(curContact.getFacebookSn()) %>">
							<tr>
								<td class="lfr-label">
									<liferay-ui:message key="facebook" />
								</td>
								<td>
									<span class="<%= isEditable ? "editable" : "" %>" id="facebook"><%= curContact.getFacebookSn() %></span>
									<a class="delete" href="javascript: ;"><img src="<%= themeDisplay.getPathThemeImage() %>/common/delete.png" /></a>
								</td>
							</tr>

							<%
							showBreak = true;
							%>
						</c:when>
						<c:otherwise>
							<c:if test="<%= isEditable %>">
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="facebook" />
									</td>
									<td>
										<span class="editable" id="facebook"><liferay-ui:message key="click-to-edit-me" /></span>
									</td>
								</tr>
							</c:if>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="<%= Validator.isNotNull(curContact.getMySpaceSn()) %>">
							<tr>
								<td class="lfr-label">
									<liferay-ui:message key="myspace" />
								</td>
								<td>
									<span class="<%= isEditable ? "editable" : "" %>" id="myspace"><%= curContact.getMySpaceSn() %></span>
									<a class="delete" href="javascript: ;"><img src="<%= themeDisplay.getPathThemeImage() %>/common/delete.png" /></a>
								</td>
							</tr>

							<%
							showBreak = true;
							%>
						</c:when>
						<c:otherwise>
							<c:if test="<%= isEditable %>">
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="myspace" />
									</td>
									<td>
										<span class="editable" id="myspace"><liferay-ui:message key="click-to-edit-me" /></span>
									</td>
								</tr>
							</c:if>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="<%= Validator.isNotNull(curContact.getTwitterSn()) %>">
							<tr>
								<td class="lfr-label">
									<liferay-ui:message key="twitter" />
								</td>
								<td>
									<span class="<%= isEditable ? "editable" : "" %>" id="twitter"><%= curContact.getTwitterSn() %></span>
									<a class="delete" href="javascript: ;"><img src="<%= themeDisplay.getPathThemeImage() %>/common/delete.png" /></a>
								</td>
							</tr>

							<%
							showBreak = true;
							%>
						</c:when>
						<c:otherwise>
							<c:if test="<%= isEditable %>">
								<tr>
									<td class="lfr-label">
										<liferay-ui:message key="twitter" />
									</td>
									<td>
										<span class="editable" id="twitter"><liferay-ui:message key="click-to-edit-me" /></span>
									</td>
								</tr>
							</c:if>
						</c:otherwise>
					</c:choose>

					<c:if test="<%= showBreak %>">
						<tr>
							<td colspan="2">
								&nbsp;
							</td>
						</tr>

						<%
						showBreak = false;
						%>

					</c:if>

					</table>
				</div>

				<div class="profile-area user-sites">
					<h3>
						<liferay-ui:message key="sites" />
					</h3>

					<%
					List<Group> groups = curUser.getGroups();

					for (Group curGroup : groups) {
					%>

						<div class="user-site">
							<div class="site-name">
								<%= curGroup.getName() %>
							</div>

							<div class="site-description">
								<%= curGroup.getDescription() %>
							</div>
						</div>

					<%
					}
					%>

				</div>
			</td>
		</tr>
		</table>
	</c:when>
	<c:otherwise>
		<h1>
			<liferay-ui:message key="profile" />
		</h1>

		<div class="portlet-msg-error">
			<liferay-ui:message key="this-site-has-no-members" />
		</div>
	</c:otherwise>
</c:choose>

<script type="text/javascript">
	jQuery(
		function() {
				Liferay.SO.Profiles.init({
					namespace: '<portlet:namespace />',
					postURL: '<portlet:actionURL />'
				}
			);
		}
	);

	function _<%= PortletKeys.MY_ACCOUNT %>_openEditUserPortraitWindow(editUserPortraitURL) {
		var editUserPortraitWindow = window.open(editUserPortraitURL, '<liferay-ui:message key="change" />', 'directories=no,height=400,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=500');

		editUserPortraitWindow.focus();
	}
</script>