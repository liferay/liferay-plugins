/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.google.login.hook.action;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;

import com.liferay.google.login.util.WebKeys;
import com.liferay.portal.kernel.deploy.DeployManagerUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Sergio González
 * @author Federico Budassi
 */
public class GoogleLoginAction extends BaseStrutsAction {

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String cmd = ParamUtil.getString(request, Constants.CMD);

		if (cmd.equals("login")) {
			GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow =
				getGoogleAuthorizationCodeFlow(themeDisplay.getCompanyId());

			GoogleAuthorizationCodeRequestUrl
				googleAuthorizationCodeRequestUrl =
					googleAuthorizationCodeFlow.newAuthorizationUrl();

			googleAuthorizationCodeRequestUrl.setRedirectUri(
				getRedirectURI(request));

			response.sendRedirect(googleAuthorizationCodeRequestUrl.build());
		}
		else if (cmd.equals("token")) {
			HttpSession session = request.getSession();

			String code = ParamUtil.getString(request, "code");

			if (Validator.isNotNull(code)) {
				Credential credential = getCredential(
					themeDisplay.getCompanyId(), code, getRedirectURI(request));

				User user = setCredential(
					session, themeDisplay.getCompanyId(), credential);

				if ((user != null) &&
					(user.getStatus() == WorkflowConstants.STATUS_INCOMPLETE)) {

					sendUpdateAccountRedirect(request, response, user);

					return null;
				}

				sendLoginRedirect(request, response);

				return null;
			}

			String error = ParamUtil.getString(request, "error");

			if (error.equals("access_denied")) {
				sendLoginRedirect(request, response);

				return null;
			}
		}

		return null;
	}

	protected User addUser(
			HttpSession session, long companyId, Userinfoplus userinfoplus)
		throws Exception {

		long creatorUserId = 0;
		boolean autoPassword = true;
		String password1 = StringPool.BLANK;
		String password2 = StringPool.BLANK;
		boolean autoScreenName = true;
		String screenName = StringPool.BLANK;
		String emailAddress = userinfoplus.getEmail();
		String openId = StringPool.BLANK;
		Locale locale = LocaleUtil.getDefault();
		String firstName = userinfoplus.getGivenName();
		String middleName = StringPool.BLANK;
		String lastName = userinfoplus.getFamilyName();
		long prefixId = 0;
		long suffixId = 0;
		boolean male = Validator.equals(userinfoplus.getGender(), "male");
		int birthdayMonth = Calendar.JANUARY;
		int birthdayDay = 1;
		int birthdayYear = 1970;
		String jobTitle = StringPool.BLANK;
		long[] groupIds = null;
		long[] organizationIds = null;
		long[] roleIds = null;
		long[] userGroupIds = null;
		boolean sendEmail = true;

		ServiceContext serviceContext = new ServiceContext();

		User user = UserLocalServiceUtil.addUser(
			creatorUserId, companyId, autoPassword, password1, password2,
			autoScreenName, screenName, emailAddress, 0, openId, locale,
			firstName, middleName, lastName, prefixId, suffixId, male,
			birthdayMonth, birthdayDay, birthdayYear, jobTitle, groupIds,
			organizationIds, roleIds, userGroupIds, sendEmail, serviceContext);

		user = UserLocalServiceUtil.updateLastLogin(
			user.getUserId(), user.getLoginIP());

		user = UserLocalServiceUtil.updatePasswordReset(
			user.getUserId(), false);

		user = UserLocalServiceUtil.updateEmailAddressVerified(
			user.getUserId(), true);

		session.setAttribute(WebKeys.GOOGLE_USER_EMAIL_ADDRESS, emailAddress);

		return user;
	}

	protected Credential getCredential(
			long companyId, String authorizationCode, String redirectURI)
		throws Exception {

		GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow =
			getGoogleAuthorizationCodeFlow(companyId);

		GoogleAuthorizationCodeTokenRequest
			googleAuthorizationCodeTokenRequest =
				googleAuthorizationCodeFlow.newTokenRequest(authorizationCode);

		googleAuthorizationCodeTokenRequest.setRedirectUri(redirectURI);

		GoogleTokenResponse googleTokenResponse =
			googleAuthorizationCodeTokenRequest.execute();

		return googleAuthorizationCodeFlow.createAndStoreCredential(
			googleTokenResponse, null);
	}

	protected GoogleAuthorizationCodeFlow getGoogleAuthorizationCodeFlow(
			long companyId)
		throws Exception {

		HttpTransport httpTransport = new NetHttpTransport();
		JacksonFactory jsonFactory = new JacksonFactory();
		String googleClientId = PrefsPropsUtil.getString(
			companyId, "google-client-id");
		String googleClientSecret = PrefsPropsUtil.getString(
			companyId, "google-client-secret");

		List<String> scopes = null;

		if (DeployManagerUtil.isDeployed(_GOOGLE_DRIVE_CONTEXT)) {
			scopes = _SCOPES_DRIVE;
		}
		else {
			scopes = _SCOPES_LOGIN;
		}

		GoogleAuthorizationCodeFlow.Builder builder =
			new GoogleAuthorizationCodeFlow.Builder(
				httpTransport, jsonFactory, googleClientId, googleClientSecret,
				scopes);

		String accessType = "online";

		if (DeployManagerUtil.isDeployed(_GOOGLE_DRIVE_CONTEXT)) {
			accessType = "offline";
		}

		builder.setAccessType(accessType);

		return builder.build();
	}

	protected String getRedirectURI(HttpServletRequest request) {
		return PortalUtil.getPortalURL(request) + PortalUtil.getPathMain() +
			_REDIRECT_URI;
	}

	protected Userinfoplus getUserinfoplus(
			com.google.api.services.oauth2.Oauth2.Userinfo oAuth2Userinfo)
		throws Exception {

		com.google.api.services.oauth2.Oauth2.Userinfo.Get oAuth2UserinfoGet =
			oAuth2Userinfo.get();

		return oAuth2UserinfoGet.execute();
	}

	protected Userinfoplus getUserinfoplus(Credential credentials)
		throws Exception {

		Oauth2.Builder builder = new Oauth2.Builder(
			new NetHttpTransport(), new JacksonFactory(), credentials);

		Oauth2 oauth2 = builder.build();

		Userinfoplus userinfoplus = getUserinfoplus(oauth2.userinfo());

		if ((userinfoplus == null) || (userinfoplus.getId() == null)) {
			throw new PrincipalException();
		}

		return userinfoplus;
	}

	protected void sendLoginRedirect(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletURL portletURL = PortletURLFactoryUtil.create(
			request, PortletKeys.FAST_LOGIN, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("struts_action", "/login/login_redirect");
		portletURL.setWindowState(LiferayWindowState.POP_UP);

		response.sendRedirect(portletURL.toString());
	}

	protected void sendUpdateAccountRedirect(
			HttpServletRequest request, HttpServletResponse response, User user)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletURL portletURL = PortletURLFactoryUtil.create(
			request, PortletKeys.LOGIN, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("saveLastPath", Boolean.FALSE.toString());
		portletURL.setParameter("struts_action", "/login/update_account");

		PortletURL redirectURL = PortletURLFactoryUtil.create(
			request, PortletKeys.FAST_LOGIN, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);

		redirectURL.setParameter("struts_action", "/login/login_redirect");
		redirectURL.setParameter("emailAddress", user.getEmailAddress());
		redirectURL.setParameter("anonymousUser", Boolean.FALSE.toString());
		redirectURL.setPortletMode(PortletMode.VIEW);
		redirectURL.setWindowState(LiferayWindowState.POP_UP);

		portletURL.setParameter("redirect", redirectURL.toString());

		portletURL.setParameter("userId", String.valueOf(user.getUserId()));
		portletURL.setParameter("emailAddress", user.getEmailAddress());
		portletURL.setParameter("firstName", user.getFirstName());
		portletURL.setParameter("lastName", user.getLastName());
		portletURL.setPortletMode(PortletMode.VIEW);
		portletURL.setWindowState(LiferayWindowState.POP_UP);

		response.sendRedirect(portletURL.toString());
	}

	protected User setCredential(
			HttpSession session, long companyId, Credential credential)
		throws Exception {

		Userinfoplus userinfoplus = getUserinfoplus(credential);

		if (userinfoplus == null) {
			return null;
		}

		User user = null;

		String emailAddress = userinfoplus.getEmail();

		if ((user == null) && Validator.isNotNull(emailAddress)) {
			user = UserLocalServiceUtil.fetchUserByEmailAddress(
				companyId, emailAddress);

			if ((user != null) &&
				(user.getStatus() != WorkflowConstants.STATUS_INCOMPLETE)) {

				session.setAttribute(
					WebKeys.GOOGLE_USER_EMAIL_ADDRESS, emailAddress);
			}
		}

		if (user != null) {
			if (user.getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
				session.setAttribute(
					WebKeys.GOOGLE_INCOMPLETE_USER_ID, userinfoplus.getId());

				user.setEmailAddress(userinfoplus.getEmail());
				user.setFirstName(userinfoplus.getGivenName());
				user.setLastName(userinfoplus.getFamilyName());

				return user;
			}

			user = updateUser(user, userinfoplus);
		}
		else {
			user = addUser(session, companyId, userinfoplus);
		}

		if (DeployManagerUtil.isDeployed(_GOOGLE_DRIVE_CONTEXT)) {
			updateExpandoValues(
				user, userinfoplus, credential.getAccessToken(),
				credential.getRefreshToken());
		}

		return user;
	}

	protected void updateExpandoValues(
			User user, Userinfoplus userinfoplus, String accessToken,
			String refreshToken)
		throws Exception {

		ExpandoValueLocalServiceUtil.addValue(
			user.getCompanyId(), User.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME, "googleAccessToken",
			user.getUserId(), accessToken);

		ExpandoValueLocalServiceUtil.addValue(
			user.getCompanyId(), User.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME, "googleRefreshToken",
			user.getUserId(), refreshToken);

		ExpandoValueLocalServiceUtil.addValue(
			user.getCompanyId(), User.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME, "googleUserId",
			user.getUserId(), userinfoplus.getId());
	}

	protected User updateUser(User user, Userinfoplus userinfoplus)
		throws Exception {

		String emailAddress = userinfoplus.getEmail();
		String firstName = userinfoplus.getGivenName();
		String lastName = userinfoplus.getFamilyName();
		boolean male = Validator.equals(userinfoplus.getGender(), "male");

		if (emailAddress.equals(user.getEmailAddress()) &&
			firstName.equals(user.getFirstName()) &&
			lastName.equals(user.getLastName()) && (male == user.isMale())) {

			return user;
		}

		Contact contact = user.getContact();

		Calendar birthdayCal = CalendarFactoryUtil.getCalendar();

		birthdayCal.setTime(contact.getBirthday());

		int birthdayMonth = birthdayCal.get(Calendar.MONTH);
		int birthdayDay = birthdayCal.get(Calendar.DAY_OF_MONTH);
		int birthdayYear = birthdayCal.get(Calendar.YEAR);

		long[] groupIds = null;
		long[] organizationIds = null;
		long[] roleIds = null;
		List<UserGroupRole> userGroupRoles = null;
		long[] userGroupIds = null;

		ServiceContext serviceContext = new ServiceContext();

		if (!StringUtil.equalsIgnoreCase(
				emailAddress, user.getEmailAddress())) {

			UserLocalServiceUtil.updateEmailAddress(
				user.getUserId(), StringPool.BLANK, emailAddress, emailAddress);
		}

		UserLocalServiceUtil.updateEmailAddressVerified(user.getUserId(), true);

		return UserLocalServiceUtil.updateUser(
			user.getUserId(), StringPool.BLANK, StringPool.BLANK,
			StringPool.BLANK, false, user.getReminderQueryQuestion(),
			user.getReminderQueryAnswer(), user.getScreenName(), emailAddress,
			0, user.getOpenId(), true, null, user.getLanguageId(),
			user.getTimeZoneId(), user.getGreeting(), user.getComments(),
			firstName, user.getMiddleName(), lastName, contact.getPrefixId(),
			contact.getSuffixId(), male, birthdayMonth, birthdayDay,
			birthdayYear, contact.getSmsSn(), contact.getFacebookSn(),
			contact.getJabberSn(), contact.getSkypeSn(), contact.getTwitterSn(),
			contact.getJobTitle(), groupIds, organizationIds, roleIds,
			userGroupRoles, userGroupIds, serviceContext);
	}

	private static final String _GOOGLE_DRIVE_CONTEXT = "google-drive-hook";

	private static final String _REDIRECT_URI =
		"/portal/google_login?cmd=token";

	private static final List<String> _SCOPES_DRIVE = Arrays.asList(
		"https://www.googleapis.com/auth/drive", "email", "profile");

	private static final List<String> _SCOPES_LOGIN = Arrays.asList(
		"email", "profile");

}