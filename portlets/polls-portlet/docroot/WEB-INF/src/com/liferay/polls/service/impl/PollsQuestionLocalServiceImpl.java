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

import com.liferay.polls.PollsQuestionChoiceException;
import com.liferay.polls.PollsQuestionDescriptionException;
import com.liferay.polls.PollsQuestionExpirationDateException;
import com.liferay.polls.PollsQuestionTitleException;
import com.liferay.polls.model.PollsChoice;
import com.liferay.polls.model.PollsQuestion;
import com.liferay.polls.service.base.PollsQuestionLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Julio Camarero
 */
public class PollsQuestionLocalServiceImpl
	extends PollsQuestionLocalServiceBaseImpl {

	public PollsQuestion addPollsQuestion(
			long userId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, List<PollsChoice> pollsChoices,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Polls question

		User user = userPersistence.findByPrimaryKey(userId);
		long groupId = serviceContext.getScopeGroupId();

		Date expirationDate = null;

		if (!neverExpire) {
			expirationDate = PortalUtil.getDate(
				expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, user.getTimeZone(),
				PollsQuestionExpirationDateException.class);
		}

		Date now = new Date();

		validate(titleMap, descriptionMap, pollsChoices);

		long pollsQuestionId = counterLocalService.increment();

		PollsQuestion pollsQuestion = pollsQuestionPersistence.create(
			pollsQuestionId);

		pollsQuestion.setUuid(serviceContext.getUuid());
		pollsQuestion.setGroupId(groupId);
		pollsQuestion.setCompanyId(user.getCompanyId());
		pollsQuestion.setUserId(user.getUserId());
		pollsQuestion.setUserName(user.getFullName());
		pollsQuestion.setCreateDate(serviceContext.getCreateDate(now));
		pollsQuestion.setModifiedDate(serviceContext.getModifiedDate(now));
		pollsQuestion.setTitleMap(titleMap);
		pollsQuestion.setDescriptionMap(descriptionMap);
		pollsQuestion.setExpirationDate(expirationDate);

		pollsQuestionPersistence.update(pollsQuestion);

		// Resources

		resourceLocalService.addModelResources(pollsQuestion, serviceContext);

		// Polls choices

		if (pollsChoices != null) {
			for (PollsChoice pollsChoice : pollsChoices) {
				pollsChoiceLocalService.addPollsChoice(
					pollsQuestionId, pollsChoice.getName(),
					pollsChoice.getDescription(), new ServiceContext());
			}
		}

		return pollsQuestion;
	}

	@Override
	public PollsQuestion deletePollsQuestion(long pollsQuestionId)
		throws PortalException, SystemException {

		PollsQuestion pollsQuestion = pollsQuestionPersistence.findByPrimaryKey(
			pollsQuestionId);

		return deletePollsQuestion(pollsQuestion);
	}

	@Override
	public PollsQuestion deletePollsQuestion(PollsQuestion pollsQuestion)
		throws PortalException, SystemException {

		// Polls question

		pollsQuestionPersistence.remove(pollsQuestion);

		// Resources

		resourceLocalService.deleteResource(
			pollsQuestion, ResourceConstants.SCOPE_INDIVIDUAL);

		// Polls choices

		pollsChoicePersistence.removeByPollsQuestionId(
			pollsQuestion.getPollsQuestionId());

		// Polls votes

		pollsVotePersistence.removeByPollsQuestionId(
			pollsQuestion.getPollsQuestionId());

		return pollsQuestion;
	}

	public void deletePollsQuestions(long groupId)
		throws PortalException, SystemException {

		for (PollsQuestion pollsQuestion :
				pollsQuestionPersistence.findByGroupId(groupId)) {

			deletePollsQuestion(pollsQuestion);
		}
	}

	public PollsQuestion getPollsQuestion(long pollsQuestionId)
		throws PortalException, SystemException {

		return pollsQuestionPersistence.findByPrimaryKey(pollsQuestionId);
	}

	public List<PollsQuestion> getPollsQuestions(long groupId)
		throws SystemException {

		return pollsQuestionPersistence.findByGroupId(groupId);
	}

	public List<PollsQuestion> getPollsQuestions(
			long groupId, int start, int end)
		throws SystemException {

		return pollsQuestionPersistence.findByGroupId(groupId, start, end);
	}

	public int getPollsQuestionsCount(long groupId) throws SystemException {
		return pollsQuestionPersistence.countByGroupId(groupId);
	}

	public PollsQuestion updatePollsQuestion(
			long userId, long pollsQuestionId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, List<PollsChoice> pollsChoices,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Polls question

		User user = userPersistence.findByPrimaryKey(userId);

		Date expirationDate = null;

		if (!neverExpire) {
			expirationDate = PortalUtil.getDate(
				expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, user.getTimeZone(),
				PollsQuestionExpirationDateException.class);
		}

		validate(titleMap, descriptionMap, pollsChoices);

		PollsQuestion pollsQuestion = pollsQuestionPersistence.findByPrimaryKey(
			pollsQuestionId);

		pollsQuestion.setModifiedDate(serviceContext.getModifiedDate(null));
		pollsQuestion.setTitleMap(titleMap);
		pollsQuestion.setDescriptionMap(descriptionMap);
		pollsQuestion.setExpirationDate(expirationDate);

		pollsQuestionPersistence.update(pollsQuestion);

		// Polls choices

		if (pollsChoices != null) {
			int oldChoicesCount = pollsChoicePersistence.countByPollsQuestionId(
				pollsQuestionId);

			if (oldChoicesCount > pollsChoices.size()) {
				throw new PollsQuestionChoiceException();
			}

			for (PollsChoice pollsChoice : pollsChoices) {
				pollsChoice = pollsChoicePersistence.fetchByPQI_N(
					pollsQuestionId, pollsChoice.getName());

				if (pollsChoice == null) {
					pollsChoiceLocalService.addPollsChoice(
						pollsQuestionId, pollsChoice.getName(),
						pollsChoice.getDescription(), new ServiceContext());
				}
				else {
					pollsChoiceLocalService.updatePollsChoice(
						pollsChoice.getPollsChoiceId(), pollsQuestionId,
						pollsChoice.getName(), pollsChoice.getDescription());
				}
			}
		}

		return pollsQuestion;
	}

	protected void validate(
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			List<PollsChoice> pollsChoices)
		throws PortalException {

		Locale locale = LocaleUtil.getDefault();

		String title = titleMap.get(locale);

		if (Validator.isNull(title)) {
			throw new PollsQuestionTitleException();
		}

		String description = descriptionMap.get(locale);

		if (Validator.isNull(description)) {
			throw new PollsQuestionDescriptionException();
		}

		if ((pollsChoices != null) && (pollsChoices.size() < 2)) {
			throw new PollsQuestionChoiceException();
		}

		if (pollsChoices != null) {
			for (PollsChoice pollsChoice : pollsChoices) {
				if (Validator.isNull(pollsChoice.getDescription(locale))) {
					throw new PollsQuestionChoiceException();
				}
			}
		}
	}

}