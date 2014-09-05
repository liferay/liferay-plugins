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

package com.liferay.googleapps;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class GGroupManagerImpl
	extends GBaseManagerImpl implements GGroupManager {

	public GGroupManagerImpl(GoogleApps googleApps) {
		super(googleApps);

		GAuthenticator gAuthenticator = googleApps.getGAuthenticator();

		groupURL = APPS_URL.concat("/group/2.0/").concat(
			gAuthenticator.getDomain());
	}

	@Override
	public void addGGroup(
			String groupEmailAddress, String groupName, String description,
			String emailPermission)
		throws GoogleAppsException {

		Document document = SAXReaderUtil.createDocument();

		Element atomEntryElement = addAtomEntry(document);

		addAppsProperty(atomEntryElement, "groupId", groupEmailAddress);
		addAppsProperty(atomEntryElement, "groupName", groupName);
		addAppsProperty(atomEntryElement, "description", description);
		addAppsProperty(atomEntryElement, "emailPermission", emailPermission);

		submitAdd(groupURL, document);
	}

	@Override
	public void addGGroupMember(
			String groupEmailAddress, String memberEmailAddress)
		throws GoogleAppsException {

		Document document = SAXReaderUtil.createDocument();

		Element atomEntryElement = addAtomEntry(document);

		addAppsProperty(atomEntryElement, "memberId", memberEmailAddress);

		StringBundler sb = new StringBundler(4);

		sb.append(groupURL);
		sb.append(StringPool.SLASH);
		sb.append(groupEmailAddress);
		sb.append("/member");

		submitAdd(sb.toString(), document);
	}

	@Override
	public void addGGroupOwner(
			String groupEmailAddress, String ownerEmailAddress)
		throws GoogleAppsException {

		Document document = SAXReaderUtil.createDocument();

		Element atomEntryElement = addAtomEntry(document);

		addAppsProperty(atomEntryElement, "email", ownerEmailAddress);

		StringBundler sb = new StringBundler(4);

		sb.append(groupURL);
		sb.append(StringPool.SLASH);
		sb.append(groupEmailAddress);
		sb.append("/owner");

		submitAdd(sb.toString(), document);
	}

	@Override
	public void deleteGGroup(String emailAddress) throws GoogleAppsException {
		submitDelete(getGroupURL(emailAddress));
	}

	@Override
	public void deleteGGroupMember(
			String groupEmailAddress, String memberEmailAddress)
		throws GoogleAppsException {

		StringBundler sb = new StringBundler(5);

		sb.append(groupURL);
		sb.append(StringPool.SLASH);
		sb.append(groupEmailAddress);
		sb.append("/member/");
		sb.append(memberEmailAddress);

		submitDelete(sb.toString());
	}

	@Override
	public void deleteGGroupOwner(
			String groupEmailAddress, String ownerEmailAddress)
		throws GoogleAppsException {

		StringBundler sb = new StringBundler(5);

		sb.append(groupURL);
		sb.append(StringPool.SLASH);
		sb.append(groupEmailAddress);
		sb.append("/owner/");
		sb.append(ownerEmailAddress);

		submitDelete(sb.toString());
	}

	@Override
	public GGroup getGGroup(String emailAddress) throws GoogleAppsException {
		Document document = getDocument(getGroupURL(emailAddress));

		if (hasError(document)) {
			if (_log.isInfoEnabled()) {
				_log.info(getErrorMessage(document));
			}

			return null;
		}

		Element atomEntryElement = document.getRootElement();

		return getGGroup(atomEntryElement);
	}

	@Override
	public GGroupMember getGGroupMember(
			String groupEmailAddress, String memberEmailAddress)
		throws GoogleAppsException {

		StringBundler sb = new StringBundler(5);

		sb.append(groupURL);
		sb.append(StringPool.SLASH);
		sb.append(groupEmailAddress);
		sb.append("/member/");
		sb.append(memberEmailAddress);

		Document document = getDocument(sb.toString());

		if (hasError(document)) {
			if (_log.isInfoEnabled()) {
				_log.info(getErrorMessage(document));
			}

			return null;
		}

		Element atomEntryElement = document.getRootElement();

		return getGGroupMember(atomEntryElement);
	}

	@Override
	public List<GGroupMember> getGGroupMembers(String emailAddress)
		throws GoogleAppsException {

		List<GGroupMember> gGroupMembers = new ArrayList<GGroupMember>();

		StringBundler sb = new StringBundler(4);

		sb.append(groupURL);
		sb.append(StringPool.SLASH);
		sb.append(emailAddress);
		sb.append("/member");

		getGGroupMembers(gGroupMembers, sb.toString());

		return gGroupMembers;
	}

	@Override
	public GGroupOwner getGGroupOwner(
			String groupEmailAddress, String ownerEmailAddress)
		throws GoogleAppsException {

		StringBundler sb = new StringBundler(5);

		sb.append(groupURL);
		sb.append(StringPool.SLASH);
		sb.append(groupEmailAddress);
		sb.append("/owner/");
		sb.append(ownerEmailAddress);

		Document document = getDocument(sb.toString());

		if (hasError(document)) {
			if (_log.isInfoEnabled()) {
				_log.info(getErrorMessage(document));
			}

			return null;
		}

		Element atomEntryElement = document.getRootElement();

		return getGGroupOwner(atomEntryElement);
	}

	@Override
	public List<GGroupOwner> getGGroupOwners(String emailAddress)
		throws GoogleAppsException {

		List<GGroupOwner> gGroupOwners = new ArrayList<GGroupOwner>();

		StringBundler sb = new StringBundler(4);

		sb.append(groupURL);
		sb.append(StringPool.SLASH);
		sb.append(emailAddress);
		sb.append("/owner");

		getGGroupOwners(gGroupOwners, sb.toString());

		return gGroupOwners;
	}

	@Override
	public List<GGroup> getGGroups() throws GoogleAppsException {
		List<GGroup> gGroups = new ArrayList<GGroup>();

		getGGroups(gGroups, groupURL);

		return gGroups;
	}

	@Override
	public List<GGroup> getGGroups(long userId, boolean directOnly)
		throws GoogleAppsException {

		List<GGroup> gGroups = new ArrayList<GGroup>();

		StringBundler sb = new StringBundler(4);

		sb.append(groupURL);
		sb.append("?member=");
		sb.append(userId);
		sb.append("&directOnly=");
		sb.append(directOnly);

		getGGroups(gGroups, sb.toString());

		return gGroups;
	}

	@Override
	public void updateDescription(String emailAddress, String description)
		throws GoogleAppsException {

		Document document = getDocument(getGroupURL(emailAddress));

		if (hasError(document)) {
			if (_log.isInfoEnabled()) {
				_log.info(getErrorMessage(document));
			}

			return;
		}

		Element atomEntryElement = document.getRootElement();

		List<Element> appsPropertyElements = atomEntryElement.elements(
			getAppsQName("property"));

		for (Element appsPropertyElement : appsPropertyElements) {
			String name = appsPropertyElement.attributeValue("name");

			if (name.equals("description")) {
				Attribute valueAttribute = appsPropertyElement.attribute(
					"value");

				valueAttribute.setValue(description);
			}
		}

		submitUpdate(getGroupURL(emailAddress), document);
	}

	protected GGroup getGGroup(Element atomEntryElement) {
		GGroup gGroup = new GGroup();

		List<Element> appsPropertyElements = atomEntryElement.elements(
			getAppsQName("property"));

		for (Element appsPropertyElement : appsPropertyElements) {
			String name = appsPropertyElement.attributeValue("name");
			String value = appsPropertyElement.attributeValue("value");

			if (name.equals("description")) {
				gGroup.setDescription(value);
			}
			else if (name.equals("emailPermission")) {
				gGroup.setEmailPermission(value);
			}
			else if (name.equals("groupId")) {
				gGroup.setEmailAddress(value);
			}
			else if (name.equals("groupName")) {
				gGroup.setName(value);
			}
			else if (name.equals("permissionPreset")) {
				gGroup.setPermissionPreset(value);
			}
		}

		return gGroup;
	}

	protected GGroupMember getGGroupMember(Element atomEntryElement)
		throws GoogleAppsException {

		GGroupMember gGroupMember = new GGroupMember();

		List<Element> appsPropertyElements = atomEntryElement.elements(
			getAppsQName("property"));

		for (Element appsPropertyElement : appsPropertyElements) {
			String name = appsPropertyElement.attributeValue("name");
			String value = appsPropertyElement.attributeValue("value");

			if (name.equals("directMember")) {
				gGroupMember.setDirect(GetterUtil.getBoolean(value));
			}
			else if (name.equals("memberId")) {
				gGroupMember.setEmailAddress(value);
			}
			else if (name.equals("memberType")) {
				gGroupMember.setType(value);
			}
		}

		String type = gGroupMember.getType();

		if (type.equals("Group")) {
			GGroup gGroup = getGGroup(gGroupMember.getEmailAddress());

			gGroupMember.setGGroup(gGroup);
		}
		else if (type.equals("User")) {
			GUserManager gUserManager = googleApps.getGUserManager();

			GUser gUser = gUserManager.getGUser(gGroupMember.getEmailAddress());

			gGroupMember.setGUser(gUser);
		}

		return gGroupMember;
	}

	protected void getGGroupMembers(
			final List<GGroupMember> gGroupMembers, String url)
		throws GoogleAppsException {

		Document document = getDocument(url);

		Element atomFeedElement = document.getRootElement();

		List<Element> atomEntryElements = atomFeedElement.elements(
			getAtomQName("entry"));

		for (Element atomEntryElement : atomEntryElements) {
			GGroupMember gGroupMember = getGGroupMember(atomEntryElement);

			gGroupMembers.add(gGroupMember);
		}

		new GetNextItems(url, atomFeedElement) {

			@Override
			public void getNextItems(String nextURL)
				throws GoogleAppsException {

				getGGroupMembers(gGroupMembers, nextURL);
			}

		};
	}

	protected GGroupOwner getGGroupOwner(Element atomEntryElement)
		throws GoogleAppsException {

		GGroupOwner gGroupOwner = new GGroupOwner();

		List<Element> appsPropertyElements = atomEntryElement.elements(
			getAppsQName("property"));

		for (Element appsPropertyElement : appsPropertyElements) {
			String name = appsPropertyElement.attributeValue("name");
			String value = appsPropertyElement.attributeValue("value");

			if (name.equals("email")) {
				gGroupOwner.setEmailAddress(value);
			}
			else if (name.equals("type")) {
				gGroupOwner.setType(value);
			}
		}

		String type = gGroupOwner.getType();

		if (type.equals("Group")) {
			GGroup gGroup = getGGroup(gGroupOwner.getEmailAddress());

			gGroupOwner.setGGroup(gGroup);
		}
		else if (type.equals("User")) {
			GUserManager gUserManager = googleApps.getGUserManager();

			GUser gUser = gUserManager.getGUser(gGroupOwner.getEmailAddress());

			gGroupOwner.setGUser(gUser);
		}

		return gGroupOwner;
	}

	protected void getGGroupOwners(
			final List<GGroupOwner> gGroupOwners, String url)
		throws GoogleAppsException {

		Document document = getDocument(url);

		Element atomFeedElement = document.getRootElement();

		List<Element> atomEntryElements = atomFeedElement.elements(
			getAtomQName("entry"));

		for (Element atomEntryElement : atomEntryElements) {
			GGroupOwner gGroupOwner = getGGroupOwner(atomEntryElement);

			gGroupOwners.add(gGroupOwner);
		}

		new GetNextItems(url, atomFeedElement) {

			@Override
			public void getNextItems(String nextURL)
				throws GoogleAppsException {

				getGGroupOwners(gGroupOwners, nextURL);
			}

		};
	}

	protected void getGGroups(final List<GGroup> gGroups, String url)
		throws GoogleAppsException {

		Document document = getDocument(url);

		Element atomFeedElement = document.getRootElement();

		List<Element> atomEntryElements = atomFeedElement.elements(
			getAtomQName("entry"));

		for (Element atomEntryElement : atomEntryElements) {
			GGroup gGroup = getGGroup(atomEntryElement);

			gGroups.add(gGroup);
		}

		new GetNextItems(url, atomFeedElement) {

			@Override
			public void getNextItems(String nextURL)
				throws GoogleAppsException {

				getGGroups(gGroups, nextURL);
			}

		};
	}

	protected String getGroupURL(String emailAddress) {
		return groupURL.concat(StringPool.SLASH).concat(emailAddress);
	}

	protected String groupURL;

	private static Log _log = LogFactoryUtil.getLog(GGroupManagerImpl.class);

}