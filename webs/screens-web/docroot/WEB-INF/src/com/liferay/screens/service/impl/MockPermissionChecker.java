/**
 * Copyright (c) 2000-2015 Liferay, Inc. All rights reserved.
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

package com.liferay.screens.service.impl;

import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;

import java.util.List;

import javax.portlet.PortletRequest;
public class MockPermissionChecker implements PermissionChecker {

	public MockPermissionChecker(long companyId) {
		_companyId = companyId;
	}

	@Override
	public PermissionChecker clone() {
		return null;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public List<Long> getGuestResourceBlockIds(long companyId, long groupId, String name, String actionId) {
		return null;
	}

	@Override
	public List<Long> getOwnerResourceBlockIds(long companyId, long groupId, String name, String actionId) {
		return null;
	}

	@Override
	public long getOwnerRoleId() {
		return 0;
	}

	@Override
	public List<Long> getResourceBlockIds(long companyId, long groupId, long userId, String name, String actionId) {
		return null;
	}

	@Override
	public long[] getRoleIds(long userId, long groupId) {
		return new long[0];
	}

	@Override
	public User getUser() {
		return null;
	}

	@Override
	public long getUserId() {
		return 0;
	}

	@Override
	public boolean hasOwnerPermission(long companyId, String name, long primKey, long ownerId, String actionId) {
		return false;
	}

	@Override
	public boolean hasOwnerPermission(long companyId, String name, String primKey, long ownerId, String actionId) {
		return false;
	}

	@Override
	public boolean hasPermission(long groupId, String name, long primKey, String actionId) {
		return false;
	}

	@Override
	public boolean hasPermission(long groupId, String name, String primKey, String actionId) {
		return false;
	}

	@Override
	public boolean hasUserPermission(long groupId, String name, String primKey, String actionId, boolean checkAdmin) {
		return false;
	}

	@Override
	public void init(User user) {
	}

	@Override
	public boolean isCheckGuest() {
		return false;
	}

	@Override
	public boolean isCommunityAdmin(long groupId) {
		return false;
	}

	@Override
	public boolean isCommunityOwner(long groupId) {
		return false;
	}

	@Override
	public boolean isCompanyAdmin() {
		return false;
	}

	@Override
	public boolean isCompanyAdmin(long companyId) {
		return false;
	}

	@Override
	public boolean isContentReviewer(long companyId, long groupId) {
		return false;
	}

	@Override
	public boolean isGroupAdmin(long groupId) {
		return false;
	}

	@Override
	public boolean isGroupMember(long groupId) {
		return false;
	}

	@Override
	public boolean isGroupOwner(long groupId) {
		return false;
	}

	@Override
	public boolean isOmniadmin() {
		return false;
	}

	@Override
	public boolean isOrganizationAdmin(long organizationId) {
		return false;
	}

	@Override
	public boolean isOrganizationOwner(long organizationId) {
		return false;
	}

	@Override
	public boolean isSignedIn() {
		return false;
	}

	@Override
	public void resetValues() {
	}

	@Override
	public void setValues(PortletRequest portletRequest) {
	}

	private long _companyId;
}