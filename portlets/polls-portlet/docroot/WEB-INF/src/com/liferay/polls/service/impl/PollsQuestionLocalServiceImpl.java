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

	public PollsQuestion addQuestion(
			long userId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, List<PollsChoice> choices,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Question

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

		validate(titleMap, descriptionMap, choices);

		long questionId = counterLocalService.increment();

		PollsQuestion question = pollsQuestionPersistence.create(questionId);

		question.setUuid(serviceContext.getUuid());
		question.setGroupId(groupId);
		question.setCompanyId(user.getCompanyId());
		question.setUserId(user.getUserId());
		question.setUserName(user.getFullName());
		question.setCreateDate(serviceContext.getCreateDate(now));
		question.setModifiedDate(serviceContext.getModifiedDate(now));
		question.setTitleMap(titleMap);
		question.setDescriptionMap(descriptionMap);
		question.setExpirationDate(expirationDate);

		pollsQuestionPersistence.update(question);

		// Resources

		if (serviceContext.isAddGroupPermissions() ||
			serviceContext.isAddGuestPermissions()) {

			addQuestionResources(
				question, serviceContext.isAddGroupPermissions(),
				serviceContext.isAddGuestPermissions());
		}
		else {
			addQuestionResources(
				question, serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

		// Choices

		if (choices != null) {
			for (PollsChoice choice : choices) {
				pollsChoiceLocalService.addChoice(
					questionId, choice.getName(), choice.getDescription(),
					new ServiceContext());
			}
		}

		return question;
	}

	public void addQuestionResources(
			long questionId, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		PollsQuestion question = pollsQuestionPersistence.findByPrimaryKey(
			questionId);

		addQuestionResources(
			question, addGroupPermissions, addGuestPermissions);
	}

	public void addQuestionResources(
			long questionId, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		PollsQuestion question = pollsQuestionPersistence.findByPrimaryKey(
			questionId);

		addQuestionResources(question, groupPermissions, guestPermissions);
	}

	public void addQuestionResources(
			PollsQuestion question, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			question.getCompanyId(), question.getGroupId(),
			question.getUserId(), PollsQuestion.class.getName(),
			question.getPollsQuestionId(), false, addGroupPermissions,
			addGuestPermissions);
	}

	public void addQuestionResources(
			PollsQuestion question, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			question.getCompanyId(), question.getGroupId(),
			question.getUserId(), PollsQuestion.class.getName(),
			question.getPollsQuestionId(), groupPermissions, guestPermissions);
	}

	public void deleteQuestion(long questionId)
		throws PortalException, SystemException {

		PollsQuestion question = pollsQuestionPersistence.findByPrimaryKey(
			questionId);

		deleteQuestion(question);
	}

	public void deleteQuestion(PollsQuestion question)
		throws PortalException, SystemException {

		// Question

		pollsQuestionPersistence.remove(question);

		// Resources

		resourceLocalService.deleteResource(
			question.getCompanyId(), PollsQuestion.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, question.getPollsQuestionId());

		// Choices

		pollsChoicePersistence.removeByPollsQuestionId(
			question.getPollsQuestionId());

		// Votes

		pollsVotePersistence.removeByPollsQuestionId(
			question.getPollsQuestionId());
	}

	public void deleteQuestions(long groupId)
		throws PortalException, SystemException {

		for (PollsQuestion question :
			pollsQuestionPersistence.findByGroupId(groupId)) {

			deleteQuestion(question);
		}
	}

	public PollsQuestion getQuestion(long questionId)
		throws PortalException, SystemException {

		return pollsQuestionPersistence.findByPrimaryKey(questionId);
	}

	public List<PollsQuestion> getQuestions(long groupId)
		throws SystemException {

		return pollsQuestionPersistence.findByGroupId(groupId);
	}

	public List<PollsQuestion> getQuestions(long groupId, int start, int end)
		throws SystemException {

		return pollsQuestionPersistence.findByGroupId(groupId, start, end);
	}

	public int getQuestionsCount(long groupId) throws SystemException {
		return pollsQuestionPersistence.countByGroupId(groupId);
	}

	public PollsQuestion updateQuestion(
			long userId, long questionId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, List<PollsChoice> choices,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Question

		User user = userPersistence.findByPrimaryKey(userId);

		Date expirationDate = null;

		if (!neverExpire) {
			expirationDate = PortalUtil.getDate(
				expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, user.getTimeZone(),
				PollsQuestionExpirationDateException.class);
		}

		validate(titleMap, descriptionMap, choices);

		PollsQuestion question = pollsQuestionPersistence.findByPrimaryKey(
			questionId);

		question.setModifiedDate(serviceContext.getModifiedDate(null));
		question.setTitleMap(titleMap);
		question.setDescriptionMap(descriptionMap);
		question.setExpirationDate(expirationDate);

		pollsQuestionPersistence.update(question);

		// Choices

		if (choices != null) {
			int oldChoicesCount = pollsChoicePersistence.countByPollsQuestionId(
				questionId);

			if (oldChoicesCount > choices.size()) {
				throw new PollsQuestionChoiceException();
			}

			for (PollsChoice choice : choices) {
				String choiceName = choice.getName();
				String choiceDescription = choice.getDescription();

				choice = pollsChoicePersistence.fetchByPQI_N(
					questionId, choiceName);

				if (choice == null) {
					pollsChoiceLocalService.addChoice(
						questionId, choiceName, choiceDescription,
						new ServiceContext());
				}
				else {
					pollsChoiceLocalService.updateChoice(
						choice.getPollsChoiceId(), questionId, choiceName,
						choiceDescription);
				}
			}
		}

		return question;
	}

	protected void validate(
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			List<PollsChoice> choices)
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

		if ((choices != null) && (choices.size() < 2)) {
			throw new PollsQuestionChoiceException();
		}

		if (choices != null) {
			for (PollsChoice choice : choices) {
				String choiceDescription = choice.getDescription(locale);

				if (Validator.isNull(choiceDescription)) {
					throw new PollsQuestionChoiceException();
				}
			}
		}
	}

}