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

/**
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.sun.com/cddl/cddl.html and
 * legal/CDDLv1.0.txt. See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at legal/CDDLv1.0.txt.
 *
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2009 Sun Microsystems Inc. All rights reserved.
 */

package com.liferay.wsrp.producer.usermanager;

import com.liferay.portal.NoSuchCompanyException;
import com.liferay.portal.NoSuchOrganizationException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ImageServletTokenUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.OrganizationConstants;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.User;
import com.liferay.portal.portletcontainer.PortletWindowContextFactoryUtil;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.Normalizer;

import com.sun.portal.container.PortletWindowContext;
import com.sun.portal.wsrp.common.WSRPSpecKeys;
import com.sun.portal.wsrp.common.stubs.v2.Contact;
import com.sun.portal.wsrp.common.stubs.v2.Online;
import com.sun.portal.wsrp.common.stubs.v2.UserContext;
import com.sun.portal.wsrp.common.stubs.v2.UserProfile;
import com.sun.portal.wsrp.producer.Producer;
import com.sun.portal.wsrp.producer.ProducerException;
import com.sun.portal.wsrp.producer.filter.ProducerThreadLocalizer;
import com.sun.portal.wsrp.producer.usermanager.UserManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * <a href="UserManagerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Manish Gupta
 * @author Brian Wing Shun Chan
 * @author Rajesh Thiagarajan
 *
 */
public class UserManagerImpl implements UserManager {

	public UserManagerImpl(Producer producer) {
		_producer = producer;
	}

	public void createConsumerUserStore(String consumerId)
		throws ProducerException {

		try {
			User user = _getAuthlessUser();

			_createOrganization(consumerId, user);
		}
		catch (Exception ex) {
			_log.error(ex, ex);
			throw new ProducerException(ex);
		}
	}

	public void createDefaultUserStore() throws ProducerException {
		try {
			User user = _getAuthlessUser();

			_createOrganization(_producer.getProducerKey(), user);
		}
		catch (Exception ex) {
			_log.error(ex, ex);
			throw new ProducerException(ex);
		}
	}

	public void deleteConsumerUserStore(String consumerId)
		throws ProducerException {

		try {

			Company company = getWSRPCompany();

			Organization organization = _getOrganization(
				company.getCompanyId(),	consumerId);

			if (organization != null) {
				long organizationId = organization.getOrganizationId();
				
				List<User> users = UserLocalServiceUtil.getOrganizationUsers(
					organizationId);

				for (User user : users) {
					if (user.getPrimaryKey() !=
							_getAuthlessUser().getPrimaryKey()) {

						UserLocalServiceUtil.deleteUser(user);
					}
				}

				UserLocalServiceUtil.clearOrganizationUsers(organizationId);

				OrganizationLocalServiceUtil.deleteOrganization(organizationId);
			}
		}
		catch (Exception ex) {
			_log.error(ex, ex);
			throw new ProducerException(ex);
		}
	}

	public void deleteDefaultUserStore() throws ProducerException {
		try {
			Company company = getWSRPCompany();

			Organization organization = _getOrganization(
				company.getCompanyId(),	_producer.getProducerKey());

			if (organization != null) {
				List<Organization> organizations =
					new ArrayList<Organization>();

				organizations.add(organization);

				List<Organization> subOrganizations =
					OrganizationLocalServiceUtil.getSuborganizations(
						organizations);

				for (Organization org : subOrganizations) {
					deleteConsumerUserStore(org.getName());
				}

				List<User> users = UserLocalServiceUtil.getOrganizationUsers(
					organization.getOrganizationId());

				for (User user : users) {
					if (user.getPrimaryKey() !=
							_getAuthlessUser().getPrimaryKey()) {

						UserLocalServiceUtil.deleteUser(user);
					}
				}

				OrganizationLocalServiceUtil.deleteOrganization(
					organization.getOrganizationId());
			}
		}
		catch (Exception ex) {
			_log.error(ex, ex);
			throw new ProducerException(ex);
		}
	}

	public PortletWindowContext getPortletWindowContext(
			HttpServletRequest request, String registrationHandle,
			String userKey)
		throws ProducerException {

		try {
			UserContext userContext = new UserContext();
			userContext.setUserContextKey(userKey);

			User phantomUser = _getPhantomUser(userContext, registrationHandle);

			request.setAttribute(_IS_WSRP_REQUEST, "true");

			String portletId = (String) request.getAttribute(_PORTAL_ID);

			Portlet portlet = PortletLocalServiceUtil.getPortletById(
				PortalUtil.getCompanyId(request), portletId);

			PortletWindowContext portletWindowContext =
				PortletWindowContextFactoryUtil.create(
					request, portlet, PortletRequest.ACTION_PHASE);

			initializeLiferayRequest(request, phantomUser);

			return portletWindowContext;
		}
		catch (Exception e) {
			_log.error(e, e);
			throw new ProducerException(e);
		}
	}

	public String getUserKey(UserContext userContext) {

		String userKey = null;

		if (userContext == null || userContext.getUserContextKey() == null ||
				userContext.getUserContextKey().equals(
					WSRPSpecKeys.WSRP_GUEST_KEY)) {

			User user = null;

			try {
				user = _getAuthlessUser();
				userKey = user.getScreenName();
			}
			catch (ProducerException pe) {
				_log.error(pe, pe);

				if (userContext != null) {
					userKey = userContext.getUserContextKey();
				}
			}
		}

		return userKey;
	}

	public static Company getWSRPCompany() throws ProducerException {

		if (_wsrpCompany == null ) {
			try {
				_wsrpCompany = CompanyLocalServiceUtil.getCompanyByVirtualHost(
					_WSRP_VIRTUAL_HOST);
			}
			catch (NoSuchCompanyException nsce) {
				_wsrpCompany = _createWSRPCompany();
			}
			catch (Exception e) {
				_log.error(e, e);
				throw new ProducerException(e);
			}
		}
		return _wsrpCompany;
	}

	public boolean supportsRoleCreation() {
		return false;
	}

	public boolean supportsUserCreation() {
		return true;
	}

	protected void initializeLiferayRequest(
			HttpServletRequest request, User user)
		throws Exception {

		// Company

		Company company = PortalUtil.getCompany(request);

		CompanyThreadLocal.setCompanyId(company.getCompanyId());

		// Paths

		String imagePath = PortalUtil.getPathImage();

		// Company logo

		String companyLogo =
			imagePath + "/company_logo?img_id=" + company.getLogoId() + "&t=" +
			ImageServletTokenUtil.getToken(company.getLogoId());

		PrincipalThreadLocal.setName(String.valueOf(user.getUserId()));

		// Permission checker

		PermissionChecker permissionChecker =
			PermissionCheckerFactoryUtil.create(user, true);

		PermissionThreadLocal.setPermissionChecker(permissionChecker);

		// Locale

		Locale locale = request.getLocale();

		Group userGroup = user.getGroup();

		if (userGroup == null) {
			userGroup = GroupLocalServiceUtil.getGroup(
				company.getCompanyId(), "Guest");
		}

		// Layouts

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			userGroup.getGroupId(), true,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

		Layout layout = null;

		if (layouts.size() > 0) {
			layout = layouts.get(0);
		}

		if (layout == null){
			String friendlyURL = _getFriendlyURL(
				PropsUtil.get(_DEFAULT_USER_PRIVATE_LAYOUT_FRIENDLY_URL));

			layout = LayoutLocalServiceUtil.addLayout(
				user.getUserId(), userGroup.getGroupId(), true,
				LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
				PropsUtil.get(_DEFAULT_USER_PRIVATE_LAYOUT_NAME),
				StringPool.BLANK, StringPool.BLANK,
				LayoutConstants.TYPE_PORTLET, false, friendlyURL);
		}

		// Manually create a ThemeDisplay object. Do not use the factory unless
		// you manually recycle the object or else there will be a memory leak.

		//ThemeDisplay themeDisplay = ThemeDisplayFactory.create();
		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setCompany(company);
		themeDisplay.setCompanyLogo(companyLogo);
		themeDisplay.setUser(user);
		themeDisplay.setLayout(layout);
		themeDisplay.setScopeGroupId(layout.getGroupId());
		themeDisplay.setPermissionChecker(permissionChecker);
		themeDisplay.setLocale(locale);
		themeDisplay.setLanguageId(LocaleUtil.toLanguageId(locale));
		themeDisplay.setSecure(request.isSecure());
		themeDisplay.setPathImage(imagePath);

		request.setAttribute(WebKeys.CTX, ProducerThreadLocalizer.getContext());
		request.setAttribute(WebKeys.LAYOUT, layout);
		request.setAttribute(WebKeys.THEME_DISPLAY, themeDisplay);
	}

	private Organization _createOrganization(String orgName, User user)
		throws PortalException, SystemException {

		long companyId = user.getCompanyId();

		Organization organization = _getOrganization(companyId, orgName);

		if (organization == null) {
			String comments = _WSRP_ORG_DESC + orgName;

			long parentOrgId =
				OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID;

			if (!orgName.equals(_producer.getProducerKey())) {

				Organization parent =
					OrganizationLocalServiceUtil.getOrganization(
						companyId, _producer.getProducerKey());

				parentOrgId = parent.getOrganizationId();
			}

			int statusId = 0;

			try {
				statusId = GetterUtil.getInteger(
					PropsUtil.get(_DEFAULT_ORGANIZATION_STATUS));
			} catch (Exception ex) {
				_log.info(ex, ex);
			}

			int countryId = 0;

			try {
				countryId = GetterUtil.getInteger(
					PropsUtil.get(_DEFAULT_COUNTRY_ID));

			} catch (Exception ex) {
				_log.info(ex, ex);
			}

			int regionId = 0;

			try {
				regionId = GetterUtil.getInteger(
					PropsUtil.get(_DEFAULT_REGION_ID));

			} catch (Exception ex) {
				_log.info(ex, ex);
			}

			organization = OrganizationLocalServiceUtil.addOrganization(
				user.getUserId(), parentOrgId, orgName,
				OrganizationConstants.TYPE_REGULAR_ORGANIZATION, true,
				regionId, countryId, statusId, comments, null);
		}
		return organization;
	}

	private User _getAuthlessUser() throws ProducerException {

		if (_authlessUser == null) {
			try {
				_authlessUser = UserLocalServiceUtil.getDefaultUser(
					getWSRPCompany().getCompanyId());
			}
			catch (Exception ex) {
				throw new ProducerException (ex);
			}
		}

		return _authlessUser;
	}

	private String _getFriendlyURL(String friendlyURL) {

		friendlyURL = GetterUtil.getString(friendlyURL);

		return Normalizer.normalizeToAscii(friendlyURL.trim().toLowerCase());
	}

	private Organization _getOrganization(long companyId, String orgName)
		throws PortalException, SystemException {

		Organization organization = null;

		try {
			organization = OrganizationLocalServiceUtil.getOrganization(
				companyId, orgName);
		}
		catch (NoSuchOrganizationException ex) {
			_log.info(ex, ex);
		}

		return organization;
	}

	private User _getPhantomUser(
			UserContext userContext, String registrationHandle)
		throws ProducerException {

		Company company = null;
		User user = null;

		String screenName = _getPhantomUserScreenName(
			userContext , registrationHandle);

		try {
			company = getWSRPCompany();

			user = UserLocalServiceUtil.getUserByScreenName(
				company.getCompanyId(), screenName);
		}
		catch (Exception e) {

			try {
				if (user == null) {
					UserProfile userProfile = userContext.getProfile();

					if (userProfile == null) {
						userProfile = new UserProfile();
					}

					String email =
						screenName + StringPool.AT + _WSRP_MAIL_SERVER;

					Contact userContact = userProfile.getBusinessInfo();

					if (userContact != null) {
						Online online = userContact.getOnline();

						if (online != null && online.getEmail() != null) {
							email = online.getEmail();
						}
					}

					boolean gender = false;

					if (userProfile.getGender() != null) {
						gender = Boolean.parseBoolean(userProfile.getGender());
					}

					String givenName = StringPool.BLANK;
					String middleName = StringPool.BLANK;
					String familyName = StringPool.BLANK;

					if (userProfile.getName() != null ) {
						givenName = userProfile.getName().getGiven();
						middleName = userProfile.getName().getMiddle();
						familyName = userProfile.getName().getFamily();
					}

					int birthYear = _defaultCalendar.get(Calendar.YEAR);
					int birthMonth = _defaultCalendar.get(Calendar.MONTH);
					int birthDay = _defaultCalendar.get(Calendar.DAY_OF_MONTH);

					if (userProfile.getBdate() != null ) {
						birthYear = userProfile.getBdate().getYear();
						birthMonth = userProfile.getBdate().getMonth();
						birthDay = userProfile.getBdate().getDay();
					}

					long organizations[] = new long[0];

					user = UserServiceUtil.addUser(
						company.getCompanyId(),	false, screenName,
						screenName, false, screenName, email,
						StringPool.BLANK, Locale.getDefault(), givenName,
						middleName, familyName, 0, 0, gender, birthMonth,
						birthDay, birthYear, StringPool.BLANK, null,
						organizations, null, null, false, null);
				}
			}
			catch (Exception ex) {
				_log.error(ex);
				throw new ProducerException(ex);
			}
		}
		return user;
	}

	private String _getPhantomUserScreenName(
			UserContext userContext , String registrationHandle)
		throws ProducerException {

		if (userContext == null || userContext.getUserContextKey() == null) {
			return _getAuthlessUser().getScreenName();
		}

		String userKey = userContext.getUserContextKey();

		if (userKey.equals(WSRPSpecKeys.WSRP_GUEST_KEY) ||
				userKey.equals(_getAuthlessUser().getScreenName())) {

			return _getAuthlessUser().getScreenName();
		}
		else {
			return userContext.getUserContextKey() + StringPool.UNDERLINE +
				registrationHandle + StringPool.UNDERLINE +
				_producer.getProducerKey();
		}
	}

	private static Company _createWSRPCompany() throws ProducerException {
		try{
			_wsrpCompany = CompanyLocalServiceUtil.addCompany(
				_WSRP_WEB_HOST,	_WSRP_VIRTUAL_HOST,	_WSRP_MAIL_SERVER,
				PropsUtil.get(_SHARD_DEFAULT_NAME), true);

			return _wsrpCompany;
		}
		catch(Exception e){
			_log.error(e, e);
			throw new ProducerException(e);
		}
	}

	private static final String _DEFAULT_COUNTRY_ID =
			"sql.data.com.liferay.portal.model.Country.country.id";
	private static final String _DEFAULT_ORGANIZATION_STATUS =
			"sql.data.com.liferay.portal.model.ListType.organization.status";
	private static final String _DEFAULT_REGION_ID =
			"sql.data.com.liferay.portal.model.Region.region.id";
	private static final String _DEFAULT_USER_PRIVATE_LAYOUT_NAME =
			"default.user.private.layout.name";
	private static final String _DEFAULT_USER_PRIVATE_LAYOUT_FRIENDLY_URL =
			"default.user.private.layout.friendly.url";

	private static final String _IS_WSRP_REQUEST = "is.wsrp.request";
	private static final String _PORTAL_ID = "com.sun.portal.portlet.id";
	private static final String _SHARD_DEFAULT_NAME = "shard.default.name";
	private static final String _WSRP_MAIL_SERVER = "wsrp.liferay.com";
	private static final String _WSRP_ORG_DESC = "WSRP Organization for : ";
	private static final String _WSRP_VIRTUAL_HOST = "wsrp.liferay.com";
	private static final String _WSRP_WEB_HOST = "wsrp.liferay.com";

	private static Calendar _defaultCalendar = Calendar.getInstance();
	private static Log _log = LogFactoryUtil.getLog(UserManagerImpl.class);

	private User _authlessUser = null;
	private Producer _producer = null;
	private static Company _wsrpCompany = null;

}
