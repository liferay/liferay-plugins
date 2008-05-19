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

<c:choose>
	<c:when test="<%= windowState.equals(WindowState.NORMAL) || !UserPermissionUtil.contains(permissionChecker, user2.getUserId(), ActionKeys.UPDATE) %>">
		<div class="summary-container">
			<h2>
				<%= organization.getName() %>
			</h2>

			<c:if test="<%= themeDisplay.isSignedIn() %>">
				<c:choose>
					<c:when test="<%= SocialRequestLocalServiceUtil.hasRequest(user.getUserId(), Organization.class.getName(), organization.getOrganizationId(), MembersRequestKeys.ADD_MEMBER, SocialRequestConstants.STATUS_PENDING) %>">
						<div class="portlet-msg-info join-organization pending">
							<liferay-ui:message key="membership-requested" />
						</div>
					</c:when>
					<c:when test="<%= UserLocalServiceUtil.hasOrganizationUser(organization.getOrganizationId(), user.getUserId()) %>">

						<%
						PortletURL leaveOrganizationURL = renderResponse.createActionURL();

						leaveOrganizationURL.setParameter(Constants.CMD, "leave_organization");
						leaveOrganizationURL.setParameter("redirect", PortalUtil.getCurrentURL(request));
						%>

						<p class="leave-organization">
							<liferay-ui:icon
								image="leave"
								message="leave-organization"
								url="<%= leaveOrganizationURL.toString() %>"
								label="<%= true %>"
							/>
						</p>
					</c:when>
					<c:otherwise>

						<%
						PortletURL joinOrganizationURL = renderResponse.createActionURL();

						joinOrganizationURL.setParameter(Constants.CMD, "join_organization");
						joinOrganizationURL.setParameter("redirect", PortalUtil.getCurrentURL(request));
						%>

						<p class="join-organization">
							<liferay-ui:icon
								image="join"
								message="join-organization"
								url="<%= joinOrganizationURL.toString() %>"
								label="<%= true %>"
							/>
						</p>
					</c:otherwise>
				</c:choose>
			</c:if>
		</div>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>