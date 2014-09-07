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

package com.liferay.google.apps.connector;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class GUserManagerImpl extends GBaseManagerImpl implements GUserManager {

	public GUserManagerImpl(GoogleAppsConnection googleApps) {
		super(googleApps);

		GAuthenticator gAuthenticator = googleApps.getGAuthenticator();

		StringBundler sb = new StringBundler(4);

		sb.append(APPS_URL);
		sb.append(StringPool.SLASH);
		sb.append(gAuthenticator.getDomain());
		sb.append("/user/2.0");

		userURL = sb.toString();
	}

	@Override
	public void addGUser(
			long userId, String password, String firstName, String lastName)
		throws GoogleAppsException {

		Document document = SAXReaderUtil.createDocument();

		Element atomEntryElement = addAtomEntry(document);

		addAtomCategory(atomEntryElement, "user");

		Element appsLoginElement = atomEntryElement.addElement("apps:login");

		appsLoginElement.addAttribute("password", password);
		appsLoginElement.addAttribute("userName", String.valueOf(userId));

		Element appsNameElement = atomEntryElement.addElement("apps:name");

		appsNameElement.addAttribute("familyName", lastName);
		appsNameElement.addAttribute("givenName", firstName);

		submitAdd(userURL, document);
	}

	@Override
	public void deleteGUser(long userId) throws GoogleAppsException {
		submitDelete(getUserURL(userId));
	}

	@Override
	public GUser getGUser(long userId) throws GoogleAppsException {
		Document document = getDocument(getUserURL(userId));

		if (hasError(document)) {
			if (_log.isInfoEnabled()) {
				_log.info(getErrorMessage(document));
			}

			return null;
		}

		Element atomEntryElement = document.getRootElement();

		return getGUser(atomEntryElement);
	}

	@Override
	public GUser getGUser(String emailAddress) throws GoogleAppsException {
		int pos = emailAddress.indexOf(CharPool.AT);

		if (pos == -1) {
			return null;
		}

		String nickname = emailAddress.substring(0, pos);

		if (Validator.isNumber(nickname)) {
			long userId = GetterUtil.getLong(nickname);

			return getGUser(userId);
		}

		try {
			User user = UserLocalServiceUtil.getUserByEmailAddress(
				getCompanyId(), emailAddress);

			return getGUser(user.getUserId());
		}
		catch (Exception e) {
		}

		GNicknameManager gNicknameManager = googleApps.getGNicknameManager();

		GNickname gNickname = gNicknameManager.getGNickname(nickname);

		if (gNickname != null) {
			return getGUser(gNickname.getUserId());
		}

		return null;
	}

	@Override
	public List<GUser> getGUsers() throws GoogleAppsException {
		List<GUser> gUsers = new ArrayList<GUser>();

		getGUsers(gUsers, userURL);

		return gUsers;
	}

	@Override
	public void updateActive(long userId, boolean active)
		throws GoogleAppsException {

		Document document = getDocument(getUserURL(userId));

		if (hasError(document)) {
			if (_log.isInfoEnabled()) {
				_log.info(getErrorMessage(document));
			}

			return;
		}

		Element atomEntryElement = document.getRootElement();

		Element appsLoginElement = atomEntryElement.element(
			getAppsQName("login"));

		appsLoginElement.addAttribute("suspended", String.valueOf(!active));

		submitUpdate(getUserURL(userId), document);
	}

	@Override
	public void updatePassword(long userId, String password)
		throws GoogleAppsException {

		Document document = getDocument(getUserURL(userId));

		if (hasError(document)) {
			if (_log.isInfoEnabled()) {
				_log.info(getErrorMessage(document));
			}

			return;
		}

		Element atomEntryElement = document.getRootElement();

		Element appsLoginElement = atomEntryElement.element(
			getAppsQName("login"));

		appsLoginElement.addAttribute("password", password);

		submitUpdate(getUserURL(userId), document);
	}

	protected GUser getGUser(Element atomEntryElement) {
		GUser gUser = new GUser();

		Element appsLoginElement = atomEntryElement.element(
			getAppsQName("login"));
		Element appsNameElement = atomEntryElement.element(
			getAppsQName("name"));

		boolean active = !GetterUtil.getBoolean(
			appsLoginElement.attributeValue("suspended"));

		gUser.setActive(active);

		boolean administrator = GetterUtil.getBoolean(
			appsLoginElement.attributeValue("admin"));

		gUser.setAdministrator(administrator);

		boolean agreedToTermsOfUse = GetterUtil.getBoolean(
			appsLoginElement.attributeValue("agreedToTerms"));

		gUser.setAgreedToTermsOfUse(agreedToTermsOfUse);

		String firstName = appsNameElement.attributeValue("givenName");

		gUser.setFirstName(firstName);

		String lastName = appsNameElement.attributeValue("familyName");

		gUser.setLastName(lastName);

		long userId = GetterUtil.getLong(
			appsLoginElement.attributeValue("userName"));

		gUser.setUserId(userId);

		return gUser;
	}

	protected void getGUsers(final List<GUser> gUsers, String url)
		throws GoogleAppsException {

		Document document = getDocument(url);

		Element atomFeedElement = document.getRootElement();

		List<Element> atomEntryElements = atomFeedElement.elements(
			getAtomQName("entry"));

		for (Element atomEntryElement : atomEntryElements) {
			GUser gUser = getGUser(atomEntryElement);

			gUsers.add(gUser);
		}

		new GetNextItems(url, atomFeedElement) {

			@Override
			public void getNextItems(String nextURL)
				throws GoogleAppsException {

				getGUsers(gUsers, nextURL);
			}

		};
	}

	protected String getUserURL(long userId) {
		return userURL.concat(StringPool.SLASH).concat(String.valueOf(userId));
	}

	protected String userURL;

	private static Log _log = LogFactoryUtil.getLog(GUserManagerImpl.class);

}