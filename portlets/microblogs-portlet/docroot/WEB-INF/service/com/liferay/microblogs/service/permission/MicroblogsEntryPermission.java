/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.microblogs.service.permission;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.service.MicroblogsEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.social.kernel.service.SocialRelationLocalServiceUtil;

/**
 * @author Jonathan Lee
 */
public class MicroblogsEntryPermission {

	public static void check(
			PermissionChecker permissionChecker, long microblogsEntryId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, microblogsEntryId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker,
			MicroblogsEntry microblogsEntry, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, microblogsEntry, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long microblogsEntryId,
			String actionId)
		throws PortalException {

		MicroblogsEntry microblogsEntry =
			MicroblogsEntryLocalServiceUtil.getMicroblogsEntry(
				microblogsEntryId);

		return contains(permissionChecker, microblogsEntry, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, MicroblogsEntry microblogsEntry,
		String actionId) {

		if (actionId.equals(ActionKeys.DELETE) ||
			actionId.equals(ActionKeys.UPDATE)) {

			if (permissionChecker.hasOwnerPermission(
					microblogsEntry.getCompanyId(),
					MicroblogsEntry.class.getName(),
					microblogsEntry.getMicroblogsEntryId(),
					microblogsEntry.getUserId(), actionId)) {

				return true;
			}

			return false;
		}

		if (permissionChecker.hasOwnerPermission(
				microblogsEntry.getCompanyId(), MicroblogsEntry.class.getName(),
				microblogsEntry.getMicroblogsEntryId(),
				microblogsEntry.getUserId(), actionId)) {

			return true;
		}

		if (microblogsEntry.getSocialRelationType() == 0) {
			return true;
		}

		if ((microblogsEntry.getUserId() != permissionChecker.getUserId()) &&
			SocialRelationLocalServiceUtil.hasRelation(
				permissionChecker.getUserId(), microblogsEntry.getUserId(),
				microblogsEntry.getSocialRelationType())) {

			return true;
		}

		return false;
	}

}