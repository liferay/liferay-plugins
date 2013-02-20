/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.polls.model.PollsChoice;
import com.liferay.polls.model.PollsQuestion;
import com.liferay.polls.service.base.PollsQuestionServiceBaseImpl;
import com.liferay.polls.service.permission.PollsPermission;
import com.liferay.polls.service.permission.PollsQuestionPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Julio Camarero
 */
public class PollsQuestionServiceImpl extends PollsQuestionServiceBaseImpl {

	public PollsQuestion addPollsQuestion(
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			List<PollsChoice> pollsChoices, ServiceContext serviceContext)
		throws PortalException, SystemException {

		PollsPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_QUESTION);

		return pollsQuestionLocalService.addPollsQuestion(
			getUserId(), titleMap, descriptionMap, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, pollsChoices, serviceContext);
	}

	public PollsQuestion deletePollsQuestion(long pollsQuestionId)
		throws PortalException, SystemException {

		PollsQuestionPermission.check(
			getPermissionChecker(), pollsQuestionId, ActionKeys.DELETE);

		return pollsQuestionLocalService.deletePollsQuestion(pollsQuestionId);
	}

	public PollsQuestion getPollsQuestion(long pollsQuestionId)
		throws PortalException, SystemException {

		PollsQuestionPermission.check(
			getPermissionChecker(), pollsQuestionId, ActionKeys.VIEW);

		return pollsQuestionLocalService.getPollsQuestion(pollsQuestionId);
	}

	public PollsQuestion updatePollsQuestion(
			long pollsQuestionId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, List<PollsChoice> pollsChoices,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		PollsQuestionPermission.check(
			getPermissionChecker(), pollsQuestionId, ActionKeys.UPDATE);

		return pollsQuestionLocalService.updatePollsQuestion(
			getUserId(), pollsQuestionId, titleMap, descriptionMap,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire, pollsChoices,
			serviceContext);
	}

}