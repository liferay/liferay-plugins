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

package com.liferay.vldap.server.directory;

import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.model.User;

import java.text.Format;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class UserDirectory extends BaseDirectory {

	public UserDirectory(
			User user, Directory parentDirectory)
		throws Exception {

		super("cn=" + user.getScreenName(), parentDirectory);

		_user = user;

		initAttributes();
	}

	protected void initAttributes() {
		addAttribute("cn", _user.getScreenName());
		addAttribute("createTimestamp", _format.format(_user.getCreateDate()));
		addAttribute("displayName", _user.getFullName());
		addAttribute("givenName", _user.getFirstName());
		addAttribute("mail", _user.getEmailAddress());
		addAttribute(
			"modifyTimestamp", _format.format(_user.getModifiedDate()));
		addAttribute("sn", _user.getLastName());
		addAttribute("objectclass", "inetOrgPerson");
		addAttribute("objectclass", "liferayPerson");
		addAttribute("objectclass", "top");
		addAttribute("uid", String.valueOf(_user.getUserId()));
		addAttribute("uuid", _user.getUuid());
	}

	protected List<Directory> initDirectories() throws Exception {
		return _directories;
	}

	private Format _format =
		FastDateFormatFactoryUtil.getSimpleDateFormat("yyyyMMddHHmmss.SZ");
	private User _user;

}