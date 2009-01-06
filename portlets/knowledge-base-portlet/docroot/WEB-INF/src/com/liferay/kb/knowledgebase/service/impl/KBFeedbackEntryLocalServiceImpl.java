/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.kb.knowledgebase.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.kb.knowledgebase.NoSuchFeedbackEntryException;
import com.liferay.kb.knowledgebase.NoSuchFeedbackStatsException;
import com.liferay.kb.knowledgebase.model.KBFeedbackEntry;
import com.liferay.kb.knowledgebase.model.KBFeedbackStats;
import com.liferay.kb.knowledgebase.service.base.KBFeedbackEntryLocalServiceBaseImpl;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="KBFeedbackEntryLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 *
 */
public class KBFeedbackEntryLocalServiceImpl
	extends KBFeedbackEntryLocalServiceBaseImpl {

	public KBFeedbackEntry addFeedbackEntry(
			long articleResourcePrimKey, long userId, int score, int vote,
			String comments)
		throws PortalException, SystemException {

		Date now = new Date();
		User user = UserLocalServiceUtil.getUserById(userId);

		// KBFeedback entry

		long feedbackEntryId = CounterLocalServiceUtil.increment();

		KBFeedbackEntry feedbackEntry = kbFeedbackEntryPersistence.create(
			feedbackEntryId);

		feedbackEntry.setArticleResourcePrimKey(articleResourcePrimKey);
		feedbackEntry.setCreateDate(now);
		feedbackEntry.setComments(comments);
		feedbackEntry.setModifiedDate(now);
		feedbackEntry.setScore(score);
		feedbackEntry.setUserId(userId);
		feedbackEntry.setUserName(user.getFullName());
		feedbackEntry.setVote(vote);

		kbFeedbackEntryPersistence.update(feedbackEntry, false);

		// KBFeedback stats

		KBFeedbackStats feedbackStats =
			kbFeedbackStatsLocalService.getArticleFeedbackStats(
				articleResourcePrimKey);

		int totalScoreEntries = feedbackStats.getTotalScoreEntries();
		int totalVotes = feedbackStats.getTotalVotes();
		int yesVotes = feedbackStats.getYesVotes();
		double averageScore = feedbackStats.getAverageScore();
		double totalScore = averageScore * totalScoreEntries;

		if (score != 0) {
			totalScoreEntries = totalScoreEntries + 1;
		}

		if (totalScoreEntries != 0) {
			averageScore = (totalScore + score) / totalScoreEntries;
		}

		if (vote == 1) {
			yesVotes++;
		}

		if (vote != 0) {
			totalVotes++;
		}

		feedbackStats.setAverageScore(averageScore);
		feedbackStats.setTotalScoreEntries(totalScoreEntries);
		feedbackStats.setTotalVotes(totalVotes);
		feedbackStats.setYesVotes(yesVotes);

		kbFeedbackStatsPersistence.update(feedbackStats, false);

		return feedbackEntry;
	}

	public void deleteArticleFeedbackEntries(long articleResourcePrimKey)
		throws PortalException, SystemException {

		// KBFeedback entry

		kbFeedbackEntryPersistence.removeByArticleResourcePrimKey(articleResourcePrimKey);

		// KBFeedback stats

		try {
			kbFeedbackStatsPersistence.removeByArticleResourcePrimKey(articleResourcePrimKey);
		}
		catch (NoSuchFeedbackStatsException nsfse) {
			if (_log.isWarnEnabled()) {
				_log.warn(nsfse);
			}
		}
	}

	public void deleteFeedbackEntries(long articleResourcePrimKey)
		throws PortalException, SystemException {

		List<KBFeedbackEntry> feedbackEntries =
			kbFeedbackEntryPersistence.findByArticleResourcePrimKey(articleResourcePrimKey);

		for (KBFeedbackEntry feedbackEntry : feedbackEntries) {
			deleteFeedbackEntry(feedbackEntry);
		}
	}

	public void deleteFeedbackEntry(long feedbackEntryId)
		throws PortalException, SystemException {

		KBFeedbackEntry feedbackEntry =
			kbFeedbackEntryPersistence.findByPrimaryKey(feedbackEntryId);

		deleteFeedbackEntry(feedbackEntry);
	}

	public void deleteFeedbackEntry(KBFeedbackEntry feedbackEntry)
		throws SystemException {

		// KBFeedback entry

		int vote = feedbackEntry.getVote();
		int oldScore = feedbackEntry.getScore();
		long articleResourcePrimKey = feedbackEntry.getArticleResourcePrimKey();

		kbFeedbackEntryPersistence.remove(feedbackEntry);

		// KBFeedback stats

		KBFeedbackStats feedbackStats =
			kbFeedbackStatsLocalService.getArticleFeedbackStats(articleResourcePrimKey);

		int totalScoreEntries = feedbackStats.getTotalScoreEntries();
		int totalVotes = feedbackStats.getTotalVotes() - 1;
		int yesVotes = feedbackStats.getYesVotes();
		double averageScore = 0;
		double totalScore = feedbackStats.getAverageScore() * totalScoreEntries;

		if (vote == 1) {
			yesVotes--;
		}

		if (oldScore != 0) {
			totalScoreEntries--;
		}

		if (totalScoreEntries > 0) {
			averageScore = (totalScore - oldScore) / totalScoreEntries;
		}

		feedbackStats.setAverageScore(averageScore);
		feedbackStats.setTotalScoreEntries(totalScoreEntries);
		feedbackStats.setTotalVotes(totalVotes);
		feedbackStats.setYesVotes(yesVotes);

		kbFeedbackStatsPersistence.update(feedbackStats, false);
	}

	public List<KBFeedbackEntry> getArticleFeedbackEntries(
			long articleResourcePrimKey)
		throws SystemException {

		return kbFeedbackEntryPersistence.findByArticleResourcePrimKey(
				articleResourcePrimKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<KBFeedbackEntry> getArticleFeedbackEntries(
			long articleResourcePrimKey, int start, int end)
		throws SystemException {

		return kbFeedbackEntryPersistence.findByArticleResourcePrimKey(
				articleResourcePrimKey, start, end);
	}

	public int getArticleFeedbackEntriesCount(long articleResourcePrimKey)
		throws SystemException {

		return kbFeedbackEntryPersistence.countByArticleResourcePrimKey(articleResourcePrimKey);
	}

	public List<KBFeedbackEntry> getFeedbackEntriesByArticleScore(
			long articleResourcePrimKey, int score, int start, int end)
		throws SystemException {

		return kbFeedbackEntryPersistence.findByA_S(
			articleResourcePrimKey, score, start, end);
	}

	public int getFeedbackEntriesByArticleScoreCount(long articleResourcePrimKey, int score)
		throws SystemException {

		return kbFeedbackEntryPersistence.countByA_S(articleResourcePrimKey, score);
	}

	public List<KBFeedbackEntry> getFeedbackEntriesByArticleVote(
			long articleResourcePrimKey, int vote, int start, int end)
		throws SystemException {

		return kbFeedbackEntryPersistence.findByA_V(
			articleResourcePrimKey, vote, start, end);
	}

	public int getFeedbackEntriesByArticleVoteCount(long articleResourcePrimKey, int vote)
		throws SystemException {

		return kbFeedbackEntryPersistence.countByA_V(articleResourcePrimKey, vote);
	}

	public KBFeedbackEntry getFeedbackEntry(long feedbackEntryId)
		throws PortalException, SystemException {

		return kbFeedbackEntryPersistence.findByPrimaryKey(
			feedbackEntryId);
	}

	public KBFeedbackEntry getFeedbackEntry(long articleResourcePrimKey, long userId)
		throws PortalException, SystemException {

		return kbFeedbackEntryPersistence.findByA_U(articleResourcePrimKey, userId);
	}

	public List<KBFeedbackEntry> getUserFeedbackEntries(
			long userId, int start, int end)
		throws SystemException {

		return kbFeedbackEntryPersistence.findByUserId(userId, start, end);
	}

	public int getUserFeedbackEntriesCount(long userId)
		throws SystemException {

		return kbFeedbackEntryPersistence.countByUserId(userId);
	}

	public KBFeedbackEntry updateComments(
			long articleResourcePrimKey, long userId, String comments)
		throws PortalException, SystemException {

		KBFeedbackEntry feedbackEntry =
			kbFeedbackEntryPersistence.findByA_U(articleResourcePrimKey, userId);

		feedbackEntry.setModifiedDate(new Date());
		feedbackEntry.setComments(comments);

		kbFeedbackEntryPersistence.update(feedbackEntry, false);

		return feedbackEntry;
	}

	public KBFeedbackEntry updateFeedback(
			long articleResourcePrimKey, long userId, int score, int vote,
			String comments)
		throws PortalException, SystemException {

		KBFeedbackEntry feedbackEntry = null;

		try {

			// KBFeedback entry

			int oldVote = 0;
			double oldScore = 0;
			Date now = new Date();

			feedbackEntry = kbFeedbackEntryPersistence.findByA_U(
				articleResourcePrimKey, userId);

			oldScore = feedbackEntry.getScore();
			oldVote = feedbackEntry.getVote();

			feedbackEntry.setComments(comments);
			feedbackEntry.setModifiedDate(now);
			feedbackEntry.setScore(score);
			feedbackEntry.setVote(vote);

			kbFeedbackEntryPersistence.update(feedbackEntry, false);

			// KBFeedback stats

			KBFeedbackStats feedbackStats =
				kbFeedbackStatsLocalService.getArticleFeedbackStats(
					articleResourcePrimKey);

			int totalScoreEntries = feedbackStats.getTotalScoreEntries();
			int totalVotes = feedbackStats.getTotalVotes();
			int yesVotes = feedbackStats.getYesVotes();
			double averageScore = feedbackStats.getAverageScore();
			double scoreDifference = score - oldScore;
			double totalScore = averageScore * totalScoreEntries;

			if ((oldScore == 0) && (score != 0)) {
				totalScoreEntries = totalScoreEntries + 1;
			}

			averageScore = (totalScore + scoreDifference) / totalScoreEntries;

			if ((oldVote != 1) && (vote == 1)) {
				yesVotes++;
			}
			else if ((oldVote == 1) && (vote != 1)) {
				yesVotes--;
			}

			if ((oldVote == 0) && (vote != 0)) {
				totalVotes++;
			}

			feedbackStats.setAverageScore(averageScore);
			feedbackStats.setTotalScoreEntries(totalScoreEntries);
			feedbackStats.setTotalVotes(totalVotes);
			feedbackStats.setYesVotes(yesVotes);

			kbFeedbackStatsPersistence.update(feedbackStats, false);
		}
		catch (NoSuchFeedbackEntryException nsfee) {
			feedbackEntry = addFeedbackEntry(
				articleResourcePrimKey, userId, score, vote, comments);
		}

		return feedbackEntry;
	}

	public KBFeedbackEntry updateScore(long articleResourcePrimKey, long userId, int score)
		throws PortalException, SystemException {

		KBFeedbackEntry feedbackEntry = null;

		try {

			// KBFeedback entry

			Date now = new Date();

			feedbackEntry =
				kbFeedbackEntryPersistence.findByA_U(articleResourcePrimKey, userId);

			double oldScore = feedbackEntry.getScore();

			feedbackEntry.setModifiedDate(now);
			feedbackEntry.setScore(score);

			kbFeedbackEntryPersistence.update(feedbackEntry, false);

			// KBFeedback stats

			KBFeedbackStats feedbackStats =
				kbFeedbackStatsLocalService.getArticleFeedbackStats(articleResourcePrimKey);

			int totalScoreEntries = feedbackStats.getTotalScoreEntries();
			double averageScore = feedbackStats.getAverageScore();
			double scoreDifference = score - oldScore;
			double totalScore = averageScore * totalScoreEntries;

			if ((oldScore == 0) && (score != 0)) {
				totalScoreEntries = totalScoreEntries + 1;
			}

			if (oldScore != score) {
				averageScore = (totalScore + scoreDifference) / totalScoreEntries;
			}

			feedbackStats.setAverageScore(averageScore);
			feedbackStats.setTotalScoreEntries(totalScoreEntries);

			kbFeedbackStatsPersistence.update(feedbackStats, false);
		}
		catch (NoSuchFeedbackEntryException nsfee) {
			feedbackEntry = addFeedbackEntry(
				articleResourcePrimKey, userId, score, 0, StringPool.BLANK);
		}

		return feedbackEntry;
	}

	public KBFeedbackEntry updateVote(
			long articleResourcePrimKey, long userId, int vote)
		throws PortalException, SystemException {

		KBFeedbackEntry feedbackEntry = null;

		try {

			// KBFeedback entry

			int oldVote = 0;
			Date now = new Date();

			feedbackEntry = kbFeedbackEntryPersistence.findByA_U(
				articleResourcePrimKey, userId);

			oldVote = feedbackEntry.getVote();

			feedbackEntry.setModifiedDate(now);
			feedbackEntry.setVote(vote);

			kbFeedbackEntryPersistence.update(feedbackEntry, false);

			// KBFeedback stats

			KBFeedbackStats feedbackStats =
				kbFeedbackStatsLocalService.getArticleFeedbackStats(
					articleResourcePrimKey);

			int totalVotes = feedbackStats.getTotalVotes();
			int yesVotes = feedbackStats.getYesVotes();

			if ((oldVote != 1) && (vote == 1)) {
				yesVotes++;
			}
			else if ((oldVote == 1) && (vote != 1)) {
				yesVotes--;
			}

			if ((oldVote == 0) && (vote != 0)) {
				totalVotes++;
			}

			feedbackStats.setTotalVotes(totalVotes);
			feedbackStats.setYesVotes(yesVotes);

			kbFeedbackStatsPersistence.update(feedbackStats, false);
		}
		catch (NoSuchFeedbackEntryException nsfee) {
			feedbackEntry = addFeedbackEntry(
				articleResourcePrimKey, userId, 0, vote, StringPool.BLANK);
		}

		return feedbackEntry;
	}

	private static Log _log =
		LogFactory.getLog(KBFeedbackEntryLocalServiceImpl.class);

}