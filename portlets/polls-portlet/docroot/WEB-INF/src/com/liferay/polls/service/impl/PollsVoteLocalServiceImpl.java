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

	public PollsVote addVote(
			long userId, long questionId, long choiceId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Choice

		Date now = new Date();

		PollsChoice choice = pollsChoicePersistence.findByPrimaryKey(choiceId);

		if (choice.getPollsQuestionId() != questionId) {
			throw new NoSuchQuestionException();
		}

		// Question

		PollsQuestion question = pollsQuestionPersistence.findByPrimaryKey(
				questionId);

		if (question.isExpired(serviceContext, now)) {
			throw new PollsQuestionExpiredException();
		}

		question.setLastVoteDate(serviceContext.getCreateDate(now));

		pollsQuestionPersistence.update(question);

		// Vote

		PollsVote vote = pollsVotePersistence.fetchByU_PQI(userId, questionId);

		if (vote != null) {
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

			long voteId = counterLocalService.increment();

			vote = pollsVotePersistence.create(voteId);

			vote.setCompanyId(serviceContext.getCompanyId());
			vote.setUserId(userId);
			vote.setUserName(userName);
			vote.setCreateDate(serviceContext.getCreateDate(now));
			vote.setModifiedDate(serviceContext.getModifiedDate(now));
			vote.setPollsQuestionId(questionId);
			vote.setPollsChoiceId(choiceId);
			vote.setVoteDate(serviceContext.getCreateDate(now));

			pollsVotePersistence.update(vote);
		}

		return vote;
	}

	public List<PollsVote> getChoiceVotes(long choiceId, int start, int end)
		throws SystemException {

		return pollsVotePersistence.findByPollsChoiceId(choiceId, start, end);
	}

	public int getChoiceVotesCount(long choiceId) throws SystemException {
		return pollsVotePersistence.countByPollsChoiceId(choiceId);
	}

	public List<PollsVote> getQuestionVotes(long questionId, int start, int end)
		throws SystemException {

		return pollsVotePersistence.findByPollsQuestionId(
			questionId, start, end);
	}

	public int getQuestionVotesCount(long questionId) throws SystemException {
		return pollsVotePersistence.countByPollsQuestionId(questionId);
	}

	public PollsVote getVote(long questionId, long userId)
		throws PortalException, SystemException {

		return pollsVotePersistence.findByU_PQI(userId, questionId);
	}

}