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

package com.liferay.google.mail.groups.util;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.admin.directory.Directory;
import com.google.api.services.admin.directory.model.Group;
import com.google.api.services.admin.directory.model.Member;
import com.google.api.services.admin.directory.model.Members;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Matthew Kong
 */
public class GoogleDirectoryUtil {

	public static void addGroup(String name, String groupEmailAddress)
		throws PortalException {

		try {
			Directory directory = getDirectory();

			Directory.Groups groups = directory.groups();

			Group group = new Group();

			group.setEmail(groupEmailAddress);
			group.setName(name);

			Directory.Groups.Insert insert = groups.insert(group);

			insert.setFields(StringPool.BLANK);

			for (int i = 1; i <= PortletPropsValues.GOOGLE_API_RETRY_ATTEMPTS;
					i++) {

				try {
					insert.execute();

					return;
				}
				catch (GoogleJsonResponseException gjre) {
					if (i == PortletPropsValues.GOOGLE_API_RETRY_ATTEMPTS) {
						throw new PortalException(gjre);
					}
					else {
						Thread.sleep(
							PortletPropsValues.GOOGLE_API_RETRY_INTERVAL);
					}
				}
			}
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public static void addGroupMember(
			String groupEmailAddress, String emailAddress)
		throws PortalException {

		try {
			Directory directory = getDirectory();

			Directory.Members members = directory.members();

			Member member = new Member();

			member.setEmail(emailAddress);

			Directory.Members.Insert insert = members.insert(
				groupEmailAddress, member);

			insert.setFields(StringPool.BLANK);

			for (int i = 1; i <= PortletPropsValues.GOOGLE_API_RETRY_ATTEMPTS;
					i++) {

				try {
					insert.execute();

					return;
				}
				catch (GoogleJsonResponseException gjre) {
					if (gjre.getStatusCode() == _ERROR_CONFLICT) {
						return;
					}

					if (i == PortletPropsValues.GOOGLE_API_RETRY_ATTEMPTS) {
						throw new PortalException(gjre);
					}
					else {
						Thread.sleep(
							PortletPropsValues.GOOGLE_API_RETRY_INTERVAL);
					}
				}
			}
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public static void deleteGroup(String groupEmailAddress)
		throws PortalException {

		try {
			Directory directory = getDirectory();

			Directory.Groups groups = directory.groups();

			Directory.Groups.Delete delete = groups.delete(groupEmailAddress);

			delete.setFields(StringPool.BLANK);

			for (int i = 1; i <= PortletPropsValues.GOOGLE_API_RETRY_ATTEMPTS;
					i++) {

				try {
					delete.execute();

					return;
				}
				catch (GoogleJsonResponseException gjre) {
					if (i == PortletPropsValues.GOOGLE_API_RETRY_ATTEMPTS) {
						throw new PortalException(gjre);
					}
					else {
						Thread.sleep(
							PortletPropsValues.GOOGLE_API_RETRY_INTERVAL);
					}
				}
			}
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public static void deleteGroupMember(
			String groupEmailAddress, String emailAddress)
		throws PortalException {

		try {
			Directory directory = getDirectory();

			Group group = getGroup(groupEmailAddress);

			if (group == null) {
				return;
			}

			Directory.Members members = directory.members();

			Directory.Members.Delete delete = members.delete(
				groupEmailAddress, emailAddress);

			delete.setFields(StringPool.BLANK);

			for (int i = 1; i <= PortletPropsValues.GOOGLE_API_RETRY_ATTEMPTS;
					i++) {

				try {
					delete.execute();

					return;
				}
				catch (GoogleJsonResponseException gjre) {
					if (i == PortletPropsValues.GOOGLE_API_RETRY_ATTEMPTS) {
						throw new PortalException(gjre);
					}
					else {
						Thread.sleep(
							PortletPropsValues.GOOGLE_API_RETRY_INTERVAL);
					}
				}
			}
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public static Directory getDirectory() throws Exception {
		if (_directory != null) {
			return _directory;
		}

		GoogleCredential googleCredential =
			GoogleCredentialUtil.getGoogleCredential();

		Directory.Builder builder = new Directory.Builder(
			googleCredential.getTransport(), googleCredential.getJsonFactory(),
			googleCredential);

		_directory = builder.build();

		return _directory;
	}

	public static Group getGroup(String groupEmailAddress) {
		try {
			Directory directory = getDirectory();

			Directory.Groups groups = directory.groups();

			Directory.Groups.Get get = groups.get(groupEmailAddress);

			return get.execute();
		}
		catch (Exception e) {
			return null;
		}
	}

	public static Member getGroupMember(
		String groupEmailAddress, String userEmailAddress) {

		try {
			Directory directory = getDirectory();

			Directory.Members members = directory.members();

			Directory.Members.Get get = members.get(
				groupEmailAddress, userEmailAddress);

			return get.execute();
		}
		catch (Exception e) {
			return null;
		}
	}

	public static Members getGroupMembers(String groupEmailAddress) {
		try {
			Directory directory = getDirectory();

			Directory.Members members = directory.members();

			Directory.Members.List list = members.list(groupEmailAddress);

			return list.execute();
		}
		catch (Exception e) {
			return null;
		}
	}

	public static void updateGroupMember(
			String groupEmailAddress, String userEmailAddress, Member member)
		throws PortalException {

		try {
			Directory directory = getDirectory();

			Directory.Members members = directory.members();

			Directory.Members.Update update = members.update(
				groupEmailAddress, userEmailAddress, member);

			update.setFields(StringPool.BLANK);

			for (int i = 1; i <= PortletPropsValues.GOOGLE_API_RETRY_ATTEMPTS;
					i++) {

				try {
					update.execute();

					return;
				}
				catch (GoogleJsonResponseException gjre) {
					if (i == PortletPropsValues.GOOGLE_API_RETRY_ATTEMPTS) {
						throw new PortalException(gjre);
					}
					else {
						Thread.sleep(
							PortletPropsValues.GOOGLE_API_RETRY_INTERVAL);
					}
				}
			}
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	private static final int _ERROR_CONFLICT = 409;

	private static Directory _directory;

}