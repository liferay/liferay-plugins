<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

<%@ include file="/init.jsp" %>

<%
User user2 = (User)request.getAttribute("view_user.jsp-user");

Contact contact2 = user2.getContact();

boolean incompleteProfile = false;

List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(User.class.getName(), user2.getUserId());

if (assetTags.isEmpty()) {
	incompleteProfile = true;
}

if (Validator.isNull(user2.getComments())) {
	incompleteProfile = true;
}
%>

<c:if test="<%= showComments && Validator.isNotNull(user2.getComments()) %>">
	<div class="field-group lfr-user-comments section" data-sectionId="comments" data-title="<%= LanguageUtil.get(request, "introduction") %>">
		<i class="icon-edit"></i>

		<h3><liferay-ui:message key="introduction" />:</h3>

		<ul class="property-list">
			<li>
				<span class="property"><%= user2.getComments() %></span>
			</li>
		</ul>
	</div>
</c:if>

<%
List<Phone> phones = PhoneServiceUtil.getPhones(Contact.class.getName(), contact2.getContactId());

if (phones.isEmpty()) {
	incompleteProfile = true;
}
%>

<c:if test="<%= showPhones && !phones.isEmpty() %>">
	<div class="field-group lfr-user-phones section" data-sectionId="phoneNumbers" data-title="<%= LanguageUtil.get(request, "phone-numbers") %>">
		<i class="icon-edit"></i>

		<h3><liferay-ui:message key="phones" />:</h3>

		<ul class="property-list">

			<%
			for (Phone phone: phones) {
			%>

				<li class="<%= phone.isPrimary() ? "primary" : "" %>">
					<span class="property-type"><%= LanguageUtil.get(request, phone.getType().getName()) %></span>
					<span class="property"><%= HtmlUtil.escape(phone.getNumber()) %> <%= phone.getExtension() %></span>
				</li>

			<%
			}
			%>

		</ul>
	</div>
</c:if>

<%
List<EmailAddress> emailAddresses = EmailAddressServiceUtil.getEmailAddresses(Contact.class.getName(), contact2.getContactId());

if (emailAddresses.isEmpty()) {
	incompleteProfile = true;
}
%>

<c:if test="<%= showAdditionalEmailAddresses && !emailAddresses.isEmpty() %>">
	<div class="field-group lfr-user-email-addresses section" data-sectionId="additionalEmailAddresses" data-title="<%= LanguageUtil.get(request, "additional-email-addresses") %>">
		<i class="icon-edit"></i>

		<h3><liferay-ui:message key="additional-email-addresses" />:</h3>

		<ul class="property-list">

			<%
			for (int i = 0; i < emailAddresses.size(); i++) {
				EmailAddress emailAddress = emailAddresses.get(i);
			%>

				<li class="<%= emailAddress.isPrimary() ? "primary" : "" %>">
					<span class="property-type"><%= LanguageUtil.get(request, emailAddress.getType().getName()) %></span>
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

if (Validator.isNull(aim) && Validator.isNull(icq) && Validator.isNull(jabber) && Validator.isNull(msn) && Validator.isNull(skype) && Validator.isNull(ym)) {
	incompleteProfile = true;
}
%>

<c:if test="<%= showInstantMessenger && (Validator.isNotNull(aim) || Validator.isNotNull(icq) || Validator.isNotNull(jabber) || Validator.isNotNull(msn) || Validator.isNotNull(skype) || Validator.isNotNull(ym)) %>">
	<div class="field-group section" data-sectionId="instantMessenger" data-title="<%= LanguageUtil.get(request, "instant-messenger") %>">
		<i class="icon-edit"></i>

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

					<i class="icon-skype"></i>
				</li>
			</c:if>

			<c:if test="<%= Validator.isNotNull(ym) %>">
				<li>
					<span class="property-type"><liferay-ui:message key="ym" /></span>

					<span class="property"><%= HtmlUtil.escape(ym) %></span>
				</li>
			</c:if>
		</ul>
	</div>
</c:if>

<%
List<Address> addresses = AddressServiceUtil.getAddresses(Contact.class.getName(), contact2.getContactId());

if (addresses.isEmpty()) {
	incompleteProfile = true;
}
%>

<c:if test="<%= showAddresses && !addresses.isEmpty() %>">
	<div class="field-group lfr-user-addresses section" data-sectionId="addresses" data-title="<%= LanguageUtil.get(request, "addresses") %>">
		<i class="icon-edit"></i>

		<h3><liferay-ui:message key="addresses" />:</h3>

		<ul class="property-list">

			<%
			for (Address address: addresses) {
				String street1 = address.getStreet1();
				String street2 = address.getStreet2();
				String street3 = address.getStreet3();

				String zipCode = address.getZip();
				String city = address.getCity();

				Country country = address.getCountry();

				String countryName = StringPool.BLANK;

				if (country != null) {
					countryName = country.getName(locale);
				}

				Region region = address.getRegion();

				String regionName = StringPool.BLANK;

				if (region != null) {
					regionName = region.getName();
				}

				String mailingName = LanguageUtil.get(request, address.getType().getName());
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
						<%= HtmlUtil.escape(zipCode) %>,
					</c:if>

					<c:if test="<%= Validator.isNotNull(regionName) %>">
						<%= regionName %>,
					</c:if>

					<c:if test="<%= Validator.isNotNull(countryName) %>">
						<%= countryName %>
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

if (websites.isEmpty()) {
	incompleteProfile = true;
}
%>

<c:if test="<%= showWebsites && !websites.isEmpty() %>">
	<div class="field-group lfr-user-websites section" data-sectionId="websites" data-title="<%= LanguageUtil.get(request, "websites") %>">
		<i class="icon-edit"></i>

		<h3><liferay-ui:message key="websites" />:</h3>

		<ul class="property-list">

			<%
			for (Website website : websites) {
				website = website.toEscapedModel();
			%>

				<li class="<%= website.isPrimary() ? "primary" : "" %>">
					<span class="property-type"><%= LanguageUtil.get(request, website.getType().getName()) %></span>

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
String twitter = contact2.getTwitterSn();

if (Validator.isNull(facebook) && Validator.isNull(twitter)) {
	incompleteProfile = true;
}
%>

<c:if test="<%= showSocialNetwork && (Validator.isNotNull(facebook) || Validator.isNotNull(twitter)) %>">
	<div class="field-group lfr-user-social-network section" data-sectionId="socialNetwork" data-title="<%= LanguageUtil.get(request, "social-network") %>">
		<i class="icon-edit"></i>

		<h3><liferay-ui:message key="social-network" />:</h3>

		<ul class="property-list">
			<c:if test="<%= Validator.isNotNull(facebook) %>">
				<li>
					<span class="property-type"><liferay-ui:message key="facebook" /></span>

					<span class="property"><%= HtmlUtil.escape(facebook) %></span>
				</li>
			</c:if>

			<c:if test="<%= Validator.isNotNull(twitter) %>">
				<li>
					<span class="property-type"><liferay-ui:message key="twitter" /></span>

					<span class="property"><%= HtmlUtil.escape(twitter) %></span>
				</li>
			</c:if>
		</ul>
	</div>
</c:if>

<%
if (Validator.isNull(contact2.getSmsSn())) {
	incompleteProfile = true;
}
%>

<c:if test="<%= showSMS && Validator.isNotNull(contact2.getSmsSn()) %>">
	<div class="field-group lfr-user-sms section" data-sectionId="sms" data-title="<%= LanguageUtil.get(request, "sms") %>">
		<i class="icon-edit"></i>

		<h3><liferay-ui:message key="sms" />:</h3>

		<ul class="property-list">
			<li class="property">
				<%= HtmlUtil.escape(contact2.getSmsSn()) %>
			</li>
		</ul>
	</div>
</c:if>

<c:if test="<%= incompleteProfile && showCompleteYourProfile && (themeDisplay.getUserId() == user2.getUserId()) %>">
	<%@ include file="/contacts_center/complete_your_profile.jspf" %>
</c:if>