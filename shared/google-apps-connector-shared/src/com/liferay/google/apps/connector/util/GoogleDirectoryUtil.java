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

package com.liferay.google.apps.connector.util;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.admin.directory.Directory;
import com.google.api.services.admin.directory.DirectoryRequest;
import com.google.api.services.admin.directory.model.Alias;
import com.google.api.services.admin.directory.model.Group;
import com.google.api.services.admin.directory.model.Member;
import com.google.api.services.admin.directory.model.Members;
import com.google.api.services.admin.directory.model.User;
import com.google.api.services.admin.directory.model.UserName;

import com.liferay.google.apps.connector.auth.GoogleCredentialUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.StringPool;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Matthew Kong
 * @author Amos Fong
 */
public class GoogleDirectoryUtil {

	public static void addGroup(String name, String groupEmailAddress)
		throws PortalException {

		try {
			Directory directory = _getDirectory();

			Directory.Groups groups = directory.groups();

			Group group = new Group();

			group.setEmail(groupEmailAddress);
			group.setName(name);

			Directory.Groups.Insert insert = groups.insert(group);

			_executeAction(insert);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public static void addGroupMember(
			String groupEmailAddress, String emailAddress)
		throws PortalException {

		try {
			Directory directory = _getDirectory();

			Directory.Members members = directory.members();

			Member member = new Member();

			member.setEmail(emailAddress);

			Directory.Members.Insert insert = members.insert(
				groupEmailAddress, member);

			_executeAction(insert);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public static void addUser(
			String primaryEmailAddress, String password, String firstName,
			String middleName, String lastName)
		throws PortalException {

		try {
			Directory directory = _getDirectory();

			Directory.Users users = directory.users();

			User user = new User();

			UserName userName = new UserName();

			userName.setFamilyName(lastName);
			userName.setGivenName(firstName);

			user.setName(userName);

			user.setPassword(password);

			user.setPrimaryEmail(primaryEmailAddress);

			Directory.Users.Insert insert = users.insert(user);

			_executeAction(insert);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public static void addUserAlias(
			String primaryEmailAddress, String aliasEmailAddress)
		throws PortalException {

		try {
			Directory directory = _getDirectory();

			Directory.Users users = directory.users();

			Directory.Users.Aliases aliases = users.aliases();

			Alias alias = new Alias();

			alias.setAlias(aliasEmailAddress);

			Directory.Users.Aliases.Insert insert = aliases.insert(
				primaryEmailAddress, alias);

			_executeAction(insert);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public static void deleteGroup(String groupEmailAddress)
		throws PortalException {

		try {
			Directory directory = _getDirectory();

			Directory.Groups groups = directory.groups();

			Directory.Groups.Delete delete = groups.delete(groupEmailAddress);

			_executeAction(delete);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public static void deleteGroupMember(
			String groupEmailAddress, String emailAddress)
		throws PortalException {

		try {
			Directory directory = _getDirectory();

			Group group = getGroup(groupEmailAddress);

			if (group == null) {
				return;
			}

			Directory.Members members = directory.members();

			Directory.Members.Delete delete = members.delete(
				groupEmailAddress, emailAddress);

			_executeAction(delete);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public static void deleteUser(String primaryEmailAddress)
		throws PortalException {

		try {
			Directory directory = _getDirectory();

			Directory.Users users = directory.users();

			Directory.Users.Delete delete = users.delete(primaryEmailAddress);

			_executeAction(delete);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public static void deleteUserAlias(
			String primaryEmailAddress, String aliasEmailAddress)
		throws PortalException {

		try {
			Directory directory = _getDirectory();

			Directory.Users users = directory.users();

			Directory.Users.Aliases aliases = users.aliases();

			Directory.Users.Aliases.Delete delete = aliases.delete(
				primaryEmailAddress, aliasEmailAddress);

			_executeAction(delete);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public static Group getGroup(String groupEmailAddress) {
		try {
			Directory directory = _getDirectory();

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
			Directory directory = _getDirectory();

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
			Directory directory = _getDirectory();

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
			Directory directory = _getDirectory();

			Directory.Members members = directory.members();

			Directory.Members.Update update = members.update(
				groupEmailAddress, userEmailAddress, member);

			_executeAction(update);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public static void updateUserPassword(
			String primaryEmailAddress, String password)
		throws PortalException {

		try {
			Directory directory = _getDirectory();

			Directory.Users users = directory.users();

			User user = new User();

			user.setPassword(password);

			Directory.Users.Update update = users.update(
				primaryEmailAddress, user);

			_executeAction(update);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	private static void _executeAction(DirectoryRequest<?> directoryRequest)
		throws Exception {

		directoryRequest.setFields(StringPool.BLANK);

		for (int i = 1; i <= PortletPropsValues.GOOGLE_API_RETRY_ATTEMPTS;
				i++) {

			try {
				directoryRequest.execute();

				return;
			}
			catch (GoogleJsonResponseException gjre) {
				if (gjre.getStatusCode() == HttpServletResponse.SC_CONFLICT) {
					return;
				}

				if (i == PortletPropsValues.GOOGLE_API_RETRY_ATTEMPTS) {
					throw new PortalException(gjre);
				}
				else {
					Thread.sleep(PortletPropsValues.GOOGLE_API_RETRY_INTERVAL);
				}
			}
		}
	}

	private static Directory _getDirectory() throws Exception {
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

	private static Directory _directory;

}