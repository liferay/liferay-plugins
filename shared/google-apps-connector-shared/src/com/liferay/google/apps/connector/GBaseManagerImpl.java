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

import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Namespace;
import com.liferay.portal.kernel.xml.QName;

/**
 * @author Brian Wing Shun Chan
 */
public class GBaseManagerImpl {

	public static final String APPS_URL = GHelperUtil.APPS_URL;

	public GBaseManagerImpl(GoogleAppsConnection googleApps) {
		this.googleApps = googleApps;
	}

	protected Element addAppsProperty(
		Element parentElement, String name, String value) {

		return GHelperUtil.addAppsProperty(parentElement, name, value);
	}

	protected Element addAtomCategory(Element parentElement, String type) {
		return GHelperUtil.addAtomCategory(parentElement, type);
	}

	protected Element addAtomEntry(Document document) {
		return GHelperUtil.addAtomEntry(document);
	}

	protected Namespace getAppsNamespace() {
		return GHelperUtil.getAppsNamespace();
	}

	protected QName getAppsQName(String localName) {
		return GHelperUtil.getAppsQName(localName);
	}

	protected Namespace getAtomNamespace() {
		return GHelperUtil.getAtomNamespace();
	}

	protected QName getAtomQName(String localName) {
		return GHelperUtil.getAtomQName(localName);
	}

	protected long getCompanyId() {
		GAuthenticator gAuthenticator = googleApps.getGAuthenticator();

		return gAuthenticator.getCompanyId();
	}

	protected Document getDocument(String url) throws GoogleAppsException {
		return GHelperUtil.getDocument(googleApps.getGAuthenticator(), url);
	}

	protected String getErrorMessage(Document document) {
		return GHelperUtil.getErrorMessage(document);
	}

	protected boolean hasError(Document document) {
		return GHelperUtil.hasError(document);
	}

	protected void submitAdd(String url, Document document)
		throws GoogleAppsException {

		GHelperUtil.submitAdd(googleApps.getGAuthenticator(), url, document);
	}

	protected void submitDelete(String url) throws GoogleAppsException {
		GHelperUtil.submitDelete(googleApps.getGAuthenticator(), url);
	}

	protected void submitUpdate(String url, Document document)
		throws GoogleAppsException {

		GHelperUtil.submitUpdate(googleApps.getGAuthenticator(), url, document);
	}

	protected GoogleAppsConnection googleApps;

}