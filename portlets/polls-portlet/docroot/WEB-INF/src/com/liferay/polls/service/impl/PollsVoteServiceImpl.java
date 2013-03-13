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

package com.liferay.polls.service.impl;

import com.liferay.polls.model.PollsVote;
import com.liferay.polls.service.base.PollsVoteServiceBaseImpl;
import com.liferay.polls.service.permission.PollsQuestionPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;

/**
 * @author Brian Wing Shun Chan
 */
public class PollsVoteServiceImpl extends PollsVoteServiceBaseImpl {

	public PollsVote addPollsVote(
			long pollsQuestionId, long pollsChoiceId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		long userId = 0;

		try {
			userId = getUserId();
		}
		catch (PrincipalException pe) {
			userId = counterLocalService.increment();
		}

		PollsQuestionPermission.check(
			getPermissionChecker(), pollsQuestionId, ActionKeys.ADD_VOTE);

		return pollsVoteLocalService.addPollsVote(
			userId, pollsQuestionId, pollsChoiceId, serviceContext);
	}

}