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
public interface GGroupManager {

	public void addGGroup(
			String groupEmailAddress, String groupName, String description,
			String emailPermission)
		throws GoogleAppsException;

	public void addGGroupMember(
			String groupEmailAddress, String memberEmailAddress)
		throws GoogleAppsException;

	public void addGGroupOwner(
			String groupEmailAddress, String ownerEmailAddress)
		throws GoogleAppsException;

	public void deleteGGroup(String emailAddress) throws GoogleAppsException;

	public void deleteGGroupMember(
			String groupEmailAddress, String memberEmailAddress)
		throws GoogleAppsException;

	public void deleteGGroupOwner(
			String groupEmailAddress, String ownerEmailAddress)
		throws GoogleAppsException;

	public GGroup getGGroup(String emailAddress) throws GoogleAppsException;

	public GGroupMember getGGroupMember(
			String groupEmailAddress, String memberEmailAddress)
		throws GoogleAppsException;

	public List<GGroupMember> getGGroupMembers(String emailAddress)
		throws GoogleAppsException;

	public GGroupOwner getGGroupOwner(
			String groupEmailAddress, String ownerEmailAddress)
		throws GoogleAppsException;

	public List<GGroupOwner> getGGroupOwners(String emailAddress)
		throws GoogleAppsException;

	public List<GGroup> getGGroups() throws GoogleAppsException;

	public List<GGroup> getGGroups(long userId, boolean directOnly)
		throws GoogleAppsException;

	public void updateDescription(String emailAddress, String description)
		throws GoogleAppsException;

}