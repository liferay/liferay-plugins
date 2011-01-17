<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

List<Phone> phones = PhoneServiceUtil.getPhones(Contact.class.getName(), contact2.getContactId());
%>

<c:if test="<%= !phones.isEmpty() %>">
	<div class="section lfr-user-phones">
		<h3><liferay-ui:message key="phones" /></h3>

		<dl class="property-list">

			<%
			for (Phone phone: phones) {
			%>

				<dt class="<%= phone.isPrimary() ? "primary" : "" %>">
					<%= LanguageUtil.get(pageContext, phone.getType().getName()) %>
				</dt>
				<dd class="<%= phone.isPrimary() ? "primary" : "" %>">
					<%= phone.getNumber() %> <%= phone.getExtension() %>
				</dd>

			<%
			}
			%>

		</dl>
	</div>
</c:if>

<%
List<EmailAddress> emailAddresses = EmailAddressServiceUtil.getEmailAddresses(Contact.class.getName(), contact2.getContactId());
%>

<c:if test="<%= !emailAddresses.isEmpty() %>">
	<div class="section lfr-user-email-addresses">
		<h3><liferay-ui:message key="additional-email-addresses" /></h3>

		<dl class="property-list">

			<%
			for (int i = 0; i < emailAddresses.size(); i++) {
				EmailAddress emailAddress = emailAddresses.get(i);
			%>

				<dt class="<%= emailAddress.isPrimary() ? "primary" : "" %>">
					<%= LanguageUtil.get(pageContext, emailAddress.getType().getName()) %>
				</dt>
				<dd class="<%= emailAddress.isPrimary() ? "primary" : "" %>">
					<a href="mailto:<%= emailAddress.getAddress() %>"><%= emailAddress.getAddress() %></a>
				</dd>

			<%
			}
			%>

		</dl>
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

<c:if test="<%= Validator.isNotNull(aim) || Validator.isNotNull(icq) || Validator.isNotNull(jabber) || Validator.isNotNull(msn) || Validator.isNotNull(skype) || Validator.isNotNull(ym) %>">
	<div class="section lfr-user-instant-messenger">
		<h3><liferay-ui:message key="instant-messenger" /></h3>

		<dl class="property-list">
			<c:if test="<%= Validator.isNotNull(aim) %>">
				<dt>
					<liferay-ui:message key="aim" />
				</dt>
				<dd>
					<%= HtmlUtil.escape(aim) %>
				</dd>
			</c:if>

			<c:if test="<%= Validator.isNotNull(icq) %>">
				<dt>
					<liferay-ui:message key="icq" />
				</dt>
				<dd>
					<%= HtmlUtil.escape(icq) %>

					<img alt="" class="instant-messenger-logo" src="http://web.icq.com/whitepages/online?icq=<%= HtmlUtil.escapeAttribute(icq) %>&img=5" />
				</dd>
			</c:if>

			<c:if test="<%= Validator.isNotNull(jabber) %>">
				<dt>
					<liferay-ui:message key="jabber" />
				</dt>
				<dd>
					<%= HtmlUtil.escape(jabber) %>
				</dd>
			</c:if>

			<c:if test="<%= Validator.isNotNull(msn) %>">
				<dt>
					<liferay-ui:message key="msn" />
				</dt>
				<dd>
					<%= HtmlUtil.escape(msn) %>
				</dd>
			</c:if>

			<c:if test="<%= Validator.isNotNull(skype) %>">
				<dt>
					<liferay-ui:message key="skype" />
				</dt>
				<dd>
					<%= HtmlUtil.escape(skype) %>
				</dd>
			</c:if>

			<c:if test="<%= Validator.isNotNull(ym) %>">
				<dt>
					<liferay-ui:message key="ym" />
				</dt>
				<dd>
					<%= HtmlUtil.escape(ym) %>
					<img alt="" class="instant-messenger-logo" src="http://opi.yahoo.com/online?u=<%= HtmlUtil.escapeAttribute(ym) %>&m=g&t=0" />
				</dd>
			</c:if>
		</dl>
	</div>
</c:if>

<%
List<Address> addresses = AddressServiceUtil.getAddresses(Contact.class.getName(), contact2.getContactId());
%>

<c:if test="<%= !addresses.isEmpty() %>">
	<div class="section lfr-user-addresses">
		<h3><liferay-ui:message key="addresses" /></h3>

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
				<strong class="mailing-name"><%= mailingName %></strong><br />

				<c:if test="<%= Validator.isNotNull(street1) %>">
					<%= HtmlUtil.escape(street1) %><br />
				</c:if>

				<c:if test="<%= Validator.isNotNull(street2) %>">
					<%= HtmlUtil.escape(street2) %><br />
				</c:if>

				<c:if test="<%= Validator.isNotNull(street3) %>">
					<%= HtmlUtil.escape(street3) %><br />
				</c:if>

				<c:if test="<%= Validator.isNotNull(zipCode) %>">
					<%= zipCode %>,
				</c:if>

				<c:if test="<%= Validator.isNotNull(city) %>">
					<%= HtmlUtil.escape(city) %>
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

<c:if test="<%= !websites.isEmpty() %>">
	<div class="section lfr-user-websites">
		<h3><liferay-ui:message key="websites" /></h3>

		<dl class="property-list">

			<%
			for (Website website: websites) {
				website = website.toEscapedModel();
			%>


				<dt class="<%= website.isPrimary() ? "primary" : "" %>">
					<%= LanguageUtil.get(pageContext, website.getType().getName()) %>
				</dt>
				<dd class="<%= website.isPrimary() ? "primary" : "" %>">
					<a href="<%= website.getUrl() %>"><%= website.getUrl() %></a>
				</dd>

			<%
			}
			%>

		</dl>
	</div>
</c:if>

<%
String facebook = contact2.getFacebookSn();
String mySpace = contact2.getMySpaceSn();
String twitter = contact2.getTwitterSn();
%>

<c:if test="<%= Validator.isNotNull(facebook) || Validator.isNotNull(mySpace) || Validator.isNotNull(twitter) %>">
	<div class="section lfr-user-social-network">
		<h3><liferay-ui:message key="social-network" /></h3>

		<dl class="property-list">
			<c:if test="<%= Validator.isNotNull(facebook) %>">
				<dt>
					<liferay-ui:message key="facebook" />
				</dt>
				<dd>
					<%= HtmlUtil.escape(facebook) %>
				</dd>
			</c:if>

			<c:if test="<%= Validator.isNotNull(mySpace) %>">
				<dt>
					<liferay-ui:message key="myspace" />
				</dt>
				<dd>
					<%= HtmlUtil.escape(mySpace) %>
				</dd>
			</c:if>

			<c:if test="<%= Validator.isNotNull(twitter) %>">
				<dt>
					<liferay-ui:message key="twitter" />
				</dt>
				<dd>
					<%= HtmlUtil.escape(twitter) %>
				</dd>
			</c:if>
		</dl>
	</div>
</c:if>

<c:if test="<%= Validator.isNotNull(contact2.getSmsSn()) %>">
	<div class="section lfr-user-sms">
		<h3><liferay-ui:message key="sms" /></h3>

		<ul class="property-list">
			<li><%= HtmlUtil.escape(contact2.getSmsSn()) %></li>
		</ul>
	</div>
</c:if>

<c:if test="<%= Validator.isNotNull(user2.getComments()) %>">
	<div class="section lfr-user-comments">
		<h3><liferay-ui:message key="comments" /></h3>

		<%= user2.getComments() %>
	</div>
</c:if>

<div style="clear: both;"><!-- --></div>