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
User user2 = (User)request.getAttribute("view_user.jsp-user");

Contact contact2 = user2.getContact();
%>

<c:if test="<%= showComments && Validator.isNotNull(user2.getComments()) %>">
	<div class="section lfr-user-comments">
		<h3><liferay-ui:message key="Introduction" />:</h3>

		<ul class="property-list">
			<li>
				<span class="property"><%= user2.getComments() %></span>
			</li>
		</ul>
	</div>
</c:if>

<%
List<Phone> phones = PhoneServiceUtil.getPhones(Contact.class.getName(), contact2.getContactId());
%>

<c:if test="<%= showPhones && !phones.isEmpty() %>">
	<div class="section lfr-user-phones">
		<h3><liferay-ui:message key="phones" />:</h3>

		<ul class="property-list">

			<%
			for (Phone phone: phones) {
			%>

				<li class="<%= phone.isPrimary() ? "primary" : "" %>">
					<span class="property-type"><%= LanguageUtil.get(pageContext, phone.getType().getName()) %></span>
					<span class="property"><%= phone.getNumber() %> <%= phone.getExtension() %></span>
				</li>

			<%
			}
			%>

		</ul>
	</div>
</c:if>

<%
List<EmailAddress> emailAddresses = EmailAddressServiceUtil.getEmailAddresses(Contact.class.getName(), contact2.getContactId());
%>

<c:if test="<%= showAdditionalEmailAddresses && !emailAddresses.isEmpty() %>">
	<div class="section lfr-user-email-addresses">
		<h3><liferay-ui:message key="additional-email-addresses" />:</h3>

		<ul class="property-list">

			<%
			for (int i = 0; i < emailAddresses.size(); i++) {
				EmailAddress emailAddress = emailAddresses.get(i);
			%>

				<li class="<%= emailAddress.isPrimary() ? "primary" : "" %>">
					<span class="property-type"><%= LanguageUtil.get(pageContext, emailAddress.getType().getName()) %></span>
					<span class="property"><a href="mailto:<%= emailAddress.getAddress() %>"><%= emailAddress.getAddress() %></a></span>
				</li>

			<%
			}
			%>

		</ul>
	</div>
</c:if>

<%
String aim = contact2.getAimSn();
String icq = contact2.getIcqSn();
String jabber = contact2.getJabberSn();
String msn = contact2.getMsnSn();
String skype = contact2.getSkypeSn();
String ym = contact2.getYmSn();
%>

<c:if test="<%= showInstantMessenger && (Validator.isNotNull(aim) || Validator.isNotNull(icq) || Validator.isNotNull(jabber) || Validator.isNotNull(msn) || Validator.isNotNull(skype) || Validator.isNotNull(ym)) %>">
	<div class="section lfr-user-instant-messenger">
		<h3><liferay-ui:message key="instant-messenger" />:</h3>

		<ul class="property-list">
			<c:if test="<%= Validator.isNotNull(aim) %>">
				<li>
					<span class="property-type"><liferay-ui:message key="aim" /></span>

					<span class="property"><%= HtmlUtil.escape(aim) %></span>
				</li>
			</c:if>

			<c:if test="<%= Validator.isNotNull(icq) %>">
				<li>
					<span class="property-type"><liferay-ui:message key="icq" /></span>

					<span class="property"><%= HtmlUtil.escape(icq) %></span>

					<img alt="" class="instant-messenger-logo" src="http://web.icq.com/whitepages/online?icq=<%= HtmlUtil.escapeAttribute(icq) %>&img=5" />
				</li>
			</c:if>

			<c:if test="<%= Validator.isNotNull(jabber) %>">
				<li>
					<span class="property-type"><liferay-ui:message key="jabber" /></span>

					<span class="property"><%= HtmlUtil.escape(jabber) %></span>
				</li>
			</c:if>

			<c:if test="<%= Validator.isNotNull(msn) %>">
				<li>
					<span class="property-type"><liferay-ui:message key="msn" /></span>

					<span class="property"><%= HtmlUtil.escape(msn) %></span>
				</li>
			</c:if>

			<c:if test="<%= Validator.isNotNull(skype) %>">
				<li>
					<span class="property-type"><liferay-ui:message key="skype" /></span>

					<span class="property"><%= HtmlUtil.escape(skype) %></span>
				</li>
			</c:if>

			<c:if test="<%= Validator.isNotNull(ym) %>">
				<li>
					<span class="property-type"><liferay-ui:message key="ym" /></span>

					<span class="property"><%= HtmlUtil.escape(ym) %></span>

					<img alt="" class="instant-messenger-logo" src="http://opi.yahoo.com/online?u=<%= HtmlUtil.escapeAttribute(ym) %>&m=g&t=0" />
				</li>
			</c:if>
		</ul>
	</div>
</c:if>

<%
List<Address> addresses = AddressServiceUtil.getAddresses(Contact.class.getName(), contact2.getContactId());
%>

<c:if test="<%= showAddresses && !addresses.isEmpty() %>">
	<div class="section lfr-user-addresses">
		<h3><liferay-ui:message key="addresses" />:</h3>

		<ul class="property-list">

			<%
			for (Address address: addresses) {
				String street1 = address.getStreet1();
				String street2 = address.getStreet2();
				String street3 = address.getStreet3();

				String zipCode = address.getZip();
				String city = address.getCity();

				String mailingName = LanguageUtil.get(pageContext, address.getType().getName());
			%>

				<li class="<%= address.isPrimary() ? "primary" : "" %>">
					<span class="property-type"><%= mailingName %></span>

					<c:if test="<%= Validator.isNotNull(street1) %>">
						<%= HtmlUtil.escape(street1) %>,
					</c:if>

					<c:if test="<%= Validator.isNotNull(street2) %>">
						<%= HtmlUtil.escape(street2) %>,
					</c:if>

					<c:if test="<%= Validator.isNotNull(street3) %>">
						<%= HtmlUtil.escape(street3) %>,
					</c:if>

					<c:if test="<%= Validator.isNotNull(city) %>">
						<%= HtmlUtil.escape(city) %>,
					</c:if>

					<c:if test="<%= Validator.isNotNull(zipCode) %>">
						<%= zipCode %>
					</c:if>

					<c:if test="<%= address.isMailing() %>">(<liferay-ui:message key="mailing" />)</c:if>
				</li>

			<%
			}
			%>

		</ul>
	</div>
</c:if>

<%
List<Website> websites = WebsiteServiceUtil.getWebsites(Contact.class.getName(), contact2.getContactId());
%>

<c:if test="<%= showWebsites && !websites.isEmpty() %>">
	<div class="section lfr-user-websites">
		<h3><liferay-ui:message key="websites" />:</h3>

		<ul class="property-list">

			<%
			for (Website website: websites) {
				website = website.toEscapedModel();
			%>


				<li class="<%= website.isPrimary() ? "primary" : "" %>">
					<span class="property-type"><%= LanguageUtil.get(pageContext, website.getType().getName()) %></span>

					<span class="property"><a href="<%= website.getUrl() %>"><%= website.getUrl() %></a></span>
				</li>

			<%
			}
			%>

		</ul>
	</div>
</c:if>

<%
String facebook = contact2.getFacebookSn();
String mySpace = contact2.getMySpaceSn();
String twitter = contact2.getTwitterSn();
%>

<c:if test="<%= showSocialNetwork && (Validator.isNotNull(facebook) || Validator.isNotNull(mySpace) || Validator.isNotNull(twitter)) %>">
	<div class="section lfr-user-social-network">
		<h3><liferay-ui:message key="social-network" />:</h3>

		<ul class="property-list">
			<c:if test="<%= Validator.isNotNull(facebook) %>">
				<li>
					<span class="property-type"><liferay-ui:message key="facebook" /></span>

					<span class="property"><%= HtmlUtil.escape(facebook) %></span>
				</li>
			</c:if>

			<c:if test="<%= Validator.isNotNull(mySpace) %>">
				<li>
					<span class="property-type"><liferay-ui:message key="myspace" /></span>

					<span class="property"><%= HtmlUtil.escape(mySpace) %></span>
				</li>
			</c:if>

			<c:if test="<%= Validator.isNotNull(twitter) %>">
				<li>
					<span class="property-type"><liferay-ui:message key="twitter" /></span>

					<span class="property"><%= HtmlUtil.escape(twitter) %></span>
				</li>
			</c:if>
		</dl>
	</div>
</c:if>

<c:if test="<%= showSMS && Validator.isNotNull(contact2.getSmsSn()) %>">
	<div class="section lfr-user-sms">
		<h3><liferay-ui:message key="sms" />:</h3>

		<ul class="property-list">
			<li class="property">
				<%= HtmlUtil.escape(contact2.getSmsSn()) %>
			</li>
		</ul>
	</div>
</c:if>