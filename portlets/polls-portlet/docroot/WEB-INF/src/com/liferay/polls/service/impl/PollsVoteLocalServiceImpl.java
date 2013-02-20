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

import com.liferay.polls.DuplicatePollsVoteException;
import com.liferay.polls.NoSuchQuestionException;
import com.liferay.polls.PollsQuestionExpiredException;
import com.liferay.polls.model.PollsChoice;
import com.liferay.polls.model.PollsQuestion;
import com.liferay.polls.model.PollsVote;
import com.liferay.polls.service.base.PollsVoteLocalServiceBaseImpl;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Mate Thurzo
 */
public class PollsVoteLocalServiceImpl extends PollsVoteLocalServiceBaseImpl {

	public PollsVote addPollsVote(
			long userId, long pollsQuestionId, long pollsChoiceId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Polls choice

		Date now = new Date();

		PollsChoice pollsChoice = pollsChoicePersistence.findByPrimaryKey(
			pollsChoiceId);

		if (pollsChoice.getPollsQuestionId() != pollsQuestionId) {
			throw new NoSuchQuestionException();
		}

		// Polls question

		PollsQuestion pollsQuestion = pollsQuestionPersistence.findByPrimaryKey(
			pollsQuestionId);

		if (pollsQuestion.isExpired(serviceContext, now)) {
			throw new PollsQuestionExpiredException();
		}

		pollsQuestion.setLastVoteDate(serviceContext.getCreateDate(now));

		pollsQuestionPersistence.update(pollsQuestion);

		// Polls vote

		PollsVote pollsVote = pollsVotePersistence.fetchByU_PQI(
			userId, pollsQuestionId);

		if (pollsVote != null) {
			throw new DuplicatePollsVoteException();
		}
		else {
			String userName = null;

			try {
				User user = userPersistence.findByPrimaryKey(userId);

				userName = user.getFullName();
			}
			catch (NoSuchUserException nsue) {
				userName = serviceContext.translate("anonymous");
			}

			long pollsVoteId = counterLocalService.increment();

			pollsVote = pollsVotePersistence.create(pollsVoteId);

			pollsVote.setCompanyId(serviceContext.getCompanyId());
			pollsVote.setUserId(userId);
			pollsVote.setUserName(userName);
			pollsVote.setCreateDate(serviceContext.getCreateDate(now));
			pollsVote.setModifiedDate(serviceContext.getModifiedDate(now));
			pollsVote.setPollsQuestionId(pollsQuestionId);
			pollsVote.setPollsChoiceId(pollsChoiceId);
			pollsVote.setVoteDate(serviceContext.getCreateDate(now));

			pollsVotePersistence.update(pollsVote);
		}

		return pollsVote;
	}

	public List<PollsVote> getPollsChoicePollsVotes(
			long pollsChoiceId, int start, int end)
		throws SystemException {

		return pollsVotePersistence.findByPollsChoiceId(
			pollsChoiceId, start, end);
	}

	public int getPollsChoicePollsVotesCount(long pollsChoiceId)
		throws SystemException {

		return pollsVotePersistence.countByPollsChoiceId(pollsChoiceId);
	}

	public List<PollsVote> getPollsQuestionPollsVotes(
			long pollsQuestionId, int start, int end)
		throws SystemException {

		return pollsVotePersistence.findByPollsQuestionId(
			pollsQuestionId, start, end);
	}

	public int getPollsQuestionPollsVotesCount(long pollsQuestionId)
		throws SystemException {

		return pollsVotePersistence.countByPollsQuestionId(pollsQuestionId);
	}

	public PollsVote getPollsVote(long pollsQuestionId, long userId)
		throws PortalException, SystemException {

		return pollsVotePersistence.findByU_PQI(userId, pollsQuestionId);
	}

}