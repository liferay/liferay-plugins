<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/html/portlet/dockbar/init.jsp" %>

<%@ page import="com.liferay.portal.kernel.util.DateUtil" %>
<%@ page import="com.liferay.portal.kernel.util.FastDateFormatConstants" %>
<%@ page import="com.liferay.portal.license.util.LicenseManagerUtil" %>
<%@ page import="com.liferay.portlet.expando.model.ExpandoBridge" %>

<%@ page import="java.util.Date" %>

<%
boolean socialOfficeEnabled = false;

try {
	Group group = layout.getGroup();

	ExpandoBridge expandoBridge = group.getExpandoBridge();

	socialOfficeEnabled = GetterUtil.getBoolean(expandoBridge.getAttribute("socialOfficeEnabled"));
}
catch (Exception e) {
}
%>

<c:if test="<%= socialOfficeEnabled %>">

	<%
	Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(FastDateFormatConstants.LONG, FastDateFormatConstants.SHORT, locale, timeZone);

	Map<String, String> licenseProperties = LicenseManagerUtil.getLicenseProperties("Social Office EE");

	long expirationDateTime = 0;
	int licenseRemainingDays = 0;
	int licenseState = 0;
	long startDateTime = 0;

	if (licenseProperties != null) {
		expirationDateTime = GetterUtil.getLong(licenseProperties.get("expirationDate"));

		if (expirationDateTime > System.currentTimeMillis()) {
			licenseRemainingDays = DateUtil.getDaysBetween(new Date(expirationDateTime), new Date(), themeDisplay.getTimeZone());
		}

		licenseState = GetterUtil.getInteger(licenseProperties.get("licenseState"), LicenseManagerUtil.getLicenseState("Social Office EE"));
		startDateTime = GetterUtil.getLong(licenseProperties.get("startDate"));
	}
	%>

	<c:if test="<%= (licenseState == 3) && (licenseRemainingDays < 10) %>">
		<div class="license-warning-message" id="<portlet:namespace />licenseWarningMessage">
			<span class="portlet-msg-alert">
				<liferay-ui:message arguments="<%= new Object[] {String.valueOf(licenseRemainingDays), dateFormatDateTime.format(new Date(expirationDateTime))} %>" key="your-social-office-license-will-expire-in-x-days-on-x" /> <liferay-ui:message key="please-contact-your-portal-administrator-to-continue-using-social-office" />
			</span>
		</div>

		<aui:script use="anim,aui-base">
			var licenseWarningMessage = A.one('#<portlet:namespace />licenseWarningMessage');

			if (licenseWarningMessage) {
				var warningMessageAnimation = new A.Anim(
					{
						duration: 8,
						easing: 'easeInStrong',
						node: '#<portlet:namespace />licenseWarningMessage',
						to: {
							opacity: 0
						}
					}
				);

				licenseWarningMessage.on(
					'mouseout',
					function(e) {
						warningMessageAnimation.run();
					}
				)

				licenseWarningMessage.on(
					'mouseover',
					function(e) {
						warningMessageAnimation.reset();
						warningMessageAnimation.pause();
						licenseWarningMessage.setStyle('opacity', '100');
					}
				)

				var onEnd = function() {
					var node = this.get('node');
					node.get('parentNode').removeChild(node);
				};

				warningMessageAnimation.on('end', onEnd);

				warningMessageAnimation.run();
			}
		</aui:script>
	</c:if>

	<c:if test="<%= licenseState != 3 %>">
		<div id="<portlet:namespace />licenseExpired" style="display: none;">
			<div id="licenseExpiredContent">
				<div class="header">
					<img alt="<liferay-ui:message key="social-office" /> <liferay-ui:message key="logo" />" src="<%= themeDisplay.getPathThemeImages() %>/custom/so_logo.png" />

					<span class="company-title">
						<%= HtmlUtil.escape(company.getName()) %>
					</span>
				</div>

				<div class="license-expired-message">
					<p><liferay-ui:message key="your-social-office-license-has-expired" /></p>
				</div>

				<table class="license-expired-details">
					<tr>
						<td>
							<strong><liferay-ui:message key="start-date" /></strong>
							<br />
							<%= dateFormatDateTime.format(new Date(startDateTime)) %>
						</td>
						<td>
							<strong><liferay-ui:message key="expiration-date" /></strong>
							<br />
							<%= dateFormatDateTime.format(new Date(expirationDateTime)) %>
						</td>
					</tr>
				</table>

				<c:choose>
					<c:when test="<%= permissionChecker.isCompanyAdmin() %>">
						<div class="license-expired-message">
							<p><liferay-ui:message key="please-renew-your-license-in-order-to-continue-using-social-office" /></p>

							<p><liferay-ui:message key="please-contact-liferay-support-if-you-are-getting-this-message-in-error" /><a href="mailto:support@liferay.com"> support@liferay.com</a></p>
						</div>

						<%
						Group controlPanelgroup = GroupLocalServiceUtil.getGroup(themeDisplay.getCompanyId(), GroupConstants.CONTROL_PANEL);

						PortletURL renewLicenseURL = PortletURLFactoryUtil.create(request, "176", controlPanelgroup.getDefaultPrivatePlid(), PortletRequest.RENDER_PHASE);
						%>

						<aui:button href="<%= renewLicenseURL.toString() %>" value="renew-license" />

						<%
						PortletURL unassignURL = PortletURLFactoryUtil.create(request, "1_WAR_soconfigurationsportlet", controlPanelgroup.getDefaultPrivatePlid(), PortletRequest.RENDER_PHASE);
						%>

						<aui:button href="<%= unassignURL.toString() %>" value="unassign-users" />

						<%
						PortletURL uninstallURL = PortletURLFactoryUtil.create(request, "2_WAR_marketplaceportlet", controlPanelgroup.getDefaultPrivatePlid(), PortletRequest.RENDER_PHASE);
						%>

						<aui:button href="<%= uninstallURL.toString() %>" value="uninstall-social-office" />
					</c:when>
					<c:otherwise>
						<div class="license-expired-message">
							<p><liferay-ui:message key="please-contact-your-portal-administrator-to-continue-using-social-office" /></p>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<aui:script use="aui-dialog">
			new A.Dialog(
				{
					bodyContent: A.one('#<portlet:namespace />licenseExpired').html(),
					centered: true,
					close: false,
					destroyOnClose: true,
					modal: true,
					resizable: false,
					title: '<%= UnicodeLanguageUtil.get(pageContext, "your-social-office-license-has-expired") %>',
					width: 650
				}
			).render();

			var userBar = A.one('.so-portlet-user-bar');

			userBar.setStyle('z-index', '1001');
		</aui:script>
	</c:if>
</c:if>