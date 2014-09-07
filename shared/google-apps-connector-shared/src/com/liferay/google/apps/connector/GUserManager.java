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

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public interface GUserManager {

	public void addGUser(
			long userId, String password, String firstName, String lastName)
		throws GoogleAppsException;

	public void deleteGUser(long userId) throws GoogleAppsException;

	public GUser getGUser(long userId) throws GoogleAppsException;

	public GUser getGUser(String emailAddress) throws GoogleAppsException;

	public List<GUser> getGUsers() throws GoogleAppsException;

	public void updateActive(long userId, boolean active)
		throws GoogleAppsException;

	public void updatePassword(long userId, String password)
		throws GoogleAppsException;

}