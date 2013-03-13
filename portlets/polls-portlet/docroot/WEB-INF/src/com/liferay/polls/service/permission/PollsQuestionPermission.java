/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.polls.service.permission;

import com.liferay.polls.model.PollsQuestion;
import com.liferay.polls.service.PollsQuestionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Brian Wing Shun Chan
 */
public class PollsQuestionPermission {

	public static void check(
			PermissionChecker permissionChecker, long pollsQuestionId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, pollsQuestionId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, PollsQuestion pollsQuestion,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, pollsQuestion, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long pollsQuestionId,
			String actionId)
		throws PortalException, SystemException {

		PollsQuestion pollsQuestion =
			PollsQuestionLocalServiceUtil.getPollsQuestion(pollsQuestionId);

		return contains(permissionChecker, pollsQuestion, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, PollsQuestion pollsQuestion,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				pollsQuestion.getCompanyId(), PollsQuestion.class.getName(),
				pollsQuestion.getPollsQuestionId(), pollsQuestion.getUserId(),
				actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			pollsQuestion.getGroupId(), PollsQuestion.class.getName(),
			pollsQuestion.getPollsQuestionId(), actionId);
	}

}