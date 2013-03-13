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
import com.liferay.polls.model.PollsChoice;
import com.liferay.polls.service.base.PollsChoiceLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * @author Juan Fernández
 */
public class PollsChoiceLocalServiceImpl
	extends PollsChoiceLocalServiceBaseImpl {

	public PollsChoice addPollsChoice(
			long pollsQuestionId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		validate(name, description);

		pollsQuestionPersistence.findByPrimaryKey(pollsQuestionId);

		long pollsChoiceId = counterLocalService.increment();

		PollsChoice pollsChoice = pollsChoicePersistence.create(pollsChoiceId);

		pollsChoice.setUuid(serviceContext.getUuid());
		pollsChoice.setPollsQuestionId(pollsQuestionId);
		pollsChoice.setName(name);
		pollsChoice.setDescription(description);

		pollsChoicePersistence.update(pollsChoice);

		return pollsChoice;
	}

	public PollsChoice getPollsChoice(long pollsChoiceId)
		throws PortalException, SystemException {

		return pollsChoicePersistence.findByPrimaryKey(pollsChoiceId);
	}

	public List<PollsChoice> getPollsChoices(long pollsQuestionId)
		throws SystemException {

		return pollsChoicePersistence.findByPollsQuestionId(pollsQuestionId);
	}

	public int getPollsChoicesCount(long pollsQuestionId)
		throws SystemException {

		return pollsChoicePersistence.countByPollsQuestionId(pollsQuestionId);
	}

	public PollsChoice updatePollsChoice(
			long pollsChoiceId, long pollsQuestionId, String name,
			String description)
		throws PortalException, SystemException {

		validate(name, description);

		pollsQuestionPersistence.findByPrimaryKey(pollsQuestionId);

		PollsChoice pollsChoice = pollsChoicePersistence.findByPrimaryKey(
			pollsChoiceId);

		pollsChoice.setPollsQuestionId(pollsQuestionId);
		pollsChoice.setName(name);
		pollsChoice.setDescription(description);

		pollsChoicePersistence.update(pollsChoice);

		return pollsChoice;
	}

	protected void validate(String name, String description)
		throws PortalException {

		if (Validator.isNull(name) || Validator.isNull(description)) {
			throw new PollsQuestionChoiceException();
		}
	}

}