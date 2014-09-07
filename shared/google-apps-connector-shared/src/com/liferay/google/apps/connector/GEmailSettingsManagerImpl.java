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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class GEmailSettingsManagerImpl
	extends GBaseManagerImpl implements GEmailSettingsManager {

	public GEmailSettingsManagerImpl(GoogleAppsConnection googleApps) {
		super(googleApps);

		GAuthenticator gAuthenticator = googleApps.getGAuthenticator();

		emailSettingsURL = APPS_URL.concat(
			"/emailsettings/2.0/").concat(gAuthenticator.getDomain());
	}

	@Override
	public void addSendAs(long userId, String fullName, String emailAddress)
		throws GoogleAppsException {

		Document document = SAXReaderUtil.createDocument();

		Element atomEntryElement = addAtomEntry(document);

		addAppsProperty(atomEntryElement, "name", fullName);
		addAppsProperty(atomEntryElement, "address", emailAddress);
		addAppsProperty(
			atomEntryElement, "makeDefault", Boolean.TRUE.toString());

		submitAdd(getEmailSettingsURL(userId).concat("/sendas"), document);
	}

	protected String getEmailSettingsURL(long userId) {
		return emailSettingsURL.concat(StringPool.SLASH).concat(
			String.valueOf(userId));
	}

	protected String emailSettingsURL;

}