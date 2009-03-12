<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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
%>

<c:choose>
	<c:when test="<%= curUser != null %>">

		<%
		Contact curContact = curUser.getContact();
		%>

		<h1><%= curUser.getFullName() %> : <liferay-ui:message key="profile" /></h1>

		<table width="100%">
		<tr>
			<td valign="top">
				<div>
					<img alt="<%= curUser.getFullName() %>" src="<%= themeDisplay.getPathImage() %>/user_<%= (curUser.isFemale() ? "female" : "male") %>_portrait?img_id=<%= curUser.getPortraitId() %>&t=<%= ImageServletTokenUtil.getToken(curUser.getPortraitId()) %>" />
				</div>
			</td>
			<td valign="top" width="90%">
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

					<div class="user-tags">
						<liferay-ui:tags-summary
							className="<%= User.class.getName() %>"
							classPK="<%= curUser.getUserId() %>"
						/>
					</div>
				</div>

				<div class="profile-area user-contact">
					<h3>
						<liferay-ui:message key="contact-information" />

						<c:if test="<%= curUser.getUserId() == user.getUserId() %>">
							<span><a href="<%= themeDisplay.getURLMyAccount() %>"><liferay-ui:message key="edit" /></a></span>
						</c:if>
					</h3>

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
								&nbsp;
							</td>
						</tr>
					</c:if>

					<%
					List<EmailAddress> emailAddresses = EmailAddressLocalServiceUtil.getEmailAddresses(themeDisplay.getCompanyId(), Contact.class.getName(), curContact.getContactId());
					%>

					<c:if test="<%= Validator.isNotNull(curUser.getEmailAddress()) || (emailAddresses.size() > 0) %>">
						<tr>
							<td class="lfr-label">
								<c:choose>
									<c:when test="<%= emailAddresses.size() > 0 %>">
										<liferay-ui:message key="email-addresses" />
									</c:when>
									<c:otherwise>
										<liferay-ui:message key="email-address" />
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<div class="user-email">
									<%= curUser.getEmailAddress() %>
								</div>

								<%
								for (EmailAddress emailAddress : emailAddresses) {
								%>

									<div class="user-email">
										<%= emailAddress.getAddress() %>
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

					<c:if test="<%= Validator.isNotNull(curContact.getAimSn()) %>">
						<tr>
							<td class="lfr-label">
								<liferay-ui:message key="aim" />
							</td>
							<td>
								<%= curContact.getAimSn() %>
							</td>
						</tr>

						<%
						showBreak = true;
						%>

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

						<%
						showBreak = true;
						%>

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

						<%
						showBreak = true;
						%>

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

						<%
						showBreak = true;
						%>

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

						<%
						showBreak = true;
						%>

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

						<%
						showBreak = true;
						%>

					</c:if>

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

					<c:if test="<%= Validator.isNotNull(curContact.getFacebookSn()) %>">
						<tr>
							<td class="lfr-label">
								<liferay-ui:message key="facebook" />
							</td>
							<td>
								<%= curContact.getFacebookSn() %>
							</td>
						</tr>

						<%
						showBreak = true;
						%>

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

						<%
						showBreak = true;
						%>

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

						<%
						showBreak = true;
						%>

					</c:if>

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
					<h3><liferay-ui:message key="sites" /></h3>

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
		<h1><liferay-ui:message key="profile" /></h1>

		<div class="portlet-msg-error">
			<liferay-ui:message key="this-site-has-no-members" />
		</div>
	</c:otherwise>
</c:choose>