/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.knowledgebase.NoSuchFeedbackEntryException;
import com.liferay.knowledgebase.model.KBFeedbackEntry;
import com.liferay.knowledgebase.model.KBFeedbackStats;
import com.liferay.knowledgebase.service.base.KBFeedbackEntryLocalServiceBaseImpl;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.Date;
import java.util.List;

/**
 * <a href="KBFeedbackEntryLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 *
 */
public class KBFeedbackEntryLocalServiceImpl
	extends KBFeedbackEntryLocalServiceBaseImpl {

	public KBFeedbackEntry addKBFeedbackEntry(
			long articleId, long userId, int score, int vote,
			String comments)
		throws PortalException, SystemException {

		Date now = new Date();
		User user = UserLocalServiceUtil.getUserById(userId);

		// KBFeedback entry

		long kbFeedbackEntryId = CounterLocalServiceUtil.increment();

		KBFeedbackEntry kbFeedbackEntry = kbFeedbackEntryPersistence.create(
			kbFeedbackEntryId);

		kbFeedbackEntry.setArticleId(articleId);
		kbFeedbackEntry.setCreateDate(now);
		kbFeedbackEntry.setComments(comments);
		kbFeedbackEntry.setModifiedDate(now);
		kbFeedbackEntry.setScore(score);
		kbFeedbackEntry.setUserId(userId);
		kbFeedbackEntry.setUserName(user.getFullName());
		kbFeedbackEntry.setVote(vote);

		kbFeedbackEntryPersistence.update(kbFeedbackEntry, false);

		// KBFeedback stats

		KBFeedbackStats kbFeedbackStats =
			kbFeedbackStatsLocalService.getArticleKBFeedbackStats(
				articleId);

		int totalScoreEntries = kbFeedbackStats.getTotalScoreEntries();
		int totalVotes = kbFeedbackStats.getTotalVotes();
		int yesVotes = kbFeedbackStats.getYesVotes();
		double averageScore = kbFeedbackStats.getAverageScore();
		double totalScore = averageScore * totalScoreEntries;

		if (score != 0) {
			totalScoreEntries = totalScoreEntries + 1;
		}

		averageScore = (totalScore + score) / totalScoreEntries;

		if (vote == 1) {
			yesVotes++;
		}

		if (vote != 0) {
			totalVotes++;
		}

		kbFeedbackStats.setAverageScore(averageScore);
		kbFeedbackStats.setTotalScoreEntries(totalScoreEntries);
		kbFeedbackStats.setTotalVotes(totalVotes);
		kbFeedbackStats.setYesVotes(yesVotes);

		kbFeedbackStatsPersistence.update(kbFeedbackStats, false);

		return kbFeedbackEntry;
	}

	public void deleteKBFeedbackEntries(long articleId)
		throws PortalException, SystemException {

		List<KBFeedbackEntry> kbFeedbackEntries =
			kbFeedbackEntryPersistence.findByArticleId(articleId);

		for (KBFeedbackEntry kbFeedbackEntry : kbFeedbackEntries) {
			deleteKBFeedbackEntry(kbFeedbackEntry);
		}
	}

	public void deleteKBFeedbackEntry(long kbFeedbackEntryId)
		throws PortalException, SystemException {

		KBFeedbackEntry kbFeedbackEntry =
			kbFeedbackEntryPersistence.findByPrimaryKey(kbFeedbackEntryId);

		deleteKBFeedbackEntry(kbFeedbackEntry);
	}

	public void deleteKBFeedbackEntry(KBFeedbackEntry kbFeedbackEntry)
		throws SystemException {

		// KBFeedback Entry

		int vote = kbFeedbackEntry.getVote();
		int oldScore = kbFeedbackEntry.getScore();
		long articleId = kbFeedbackEntry.getArticleId();

		kbFeedbackEntryPersistence.remove(kbFeedbackEntry);

		// KBFeedback Stats

		KBFeedbackStats kbFeedbackStats =
			kbFeedbackStatsLocalService.getArticleKBFeedbackStats(articleId);

		int totalScoreEntries = kbFeedbackStats.getTotalScoreEntries();
		int totalVotes = kbFeedbackStats.getTotalVotes() - 1;
		int yesVotes = kbFeedbackStats.getYesVotes();
		double averageScore = 0;
		double totalScore = kbFeedbackStats.getAverageScore() * totalScoreEntries;

		if (vote == 1) {
			yesVotes--;
		}

		if (oldScore != 0) {
			totalScoreEntries--;
		}

		if (totalScoreEntries > 0) {
			averageScore = (totalScore - oldScore) / totalScoreEntries;
		}

		kbFeedbackStats.setAverageScore(averageScore);
		kbFeedbackStats.setTotalScoreEntries(totalScoreEntries);
		kbFeedbackStats.setTotalVotes(totalVotes);
		kbFeedbackStats.setYesVotes(yesVotes);

		kbFeedbackStatsPersistence.update(kbFeedbackStats, false);
	}

	public List<KBFeedbackEntry> getArticleKBFeedbackEntries(
			long articleId, int start, int end)
		throws SystemException {

		return kbFeedbackEntryPersistence.findByArticleId(
			articleId, start, end);
	}

	public int getArticleKBFeedbackEntriesCount(long articleId)
		throws SystemException {

		return kbFeedbackEntryPersistence.countByArticleId(articleId);
	}

	public List<KBFeedbackEntry> getKBFeedbackEntriesByArticleScore(
			long articleId, int score, int start, int end)
		throws SystemException {

		return kbFeedbackEntryPersistence.findByA_S(
			articleId, score, start, end);
	}

	public int getKBFeedbackEntriesByArticleScoreCount(long articleId, int score)
		throws SystemException {

		return kbFeedbackEntryPersistence.countByA_S(articleId, score);
	}

	public List<KBFeedbackEntry> getKBFeedbackEntriesByArticleVote(
			long articleId, int vote, int start, int end)
		throws SystemException {

		return kbFeedbackEntryPersistence.findByA_V(
			articleId, vote, start, end);
	}

	public int getKBFeedbackEntriesByArticleVoteCount(long articleId, int vote)
		throws SystemException {

		return kbFeedbackEntryPersistence.countByA_V(articleId, vote);
	}

	public KBFeedbackEntry getKBFeedbackEntry(long kbFeedbackEntryId)
		throws PortalException, SystemException {

		return kbFeedbackEntryPersistence.findByPrimaryKey(
			kbFeedbackEntryId);
	}

	public KBFeedbackEntry getKBFeedbackEntry(long articleId, long userId)
		throws PortalException, SystemException {

		return kbFeedbackEntryPersistence.findByA_U(articleId, userId);
	}

	public List<KBFeedbackEntry> getUserKBFeedbackEntries(
			long userId, int start, int end)
		throws SystemException {

		return kbFeedbackEntryPersistence.findByUserId(userId, start, end);
	}

	public int getUserKBFeedbackEntriesCount(long userId)
		throws SystemException {

		return kbFeedbackEntryPersistence.countByUserId(userId);
	}

	public KBFeedbackEntry updateComments(
			long articleId, long userId, String comments)
		throws PortalException, SystemException {

		KBFeedbackEntry kbFeedbackEntry =
			kbFeedbackEntryPersistence.findByA_U(articleId, userId);

		kbFeedbackEntry.setModifiedDate(new Date());
		kbFeedbackEntry.setComments(comments);

		kbFeedbackEntryPersistence.update(kbFeedbackEntry, false);

		return kbFeedbackEntry;
	}

	public KBFeedbackEntry updateKBFeedback(
			long articleId, long userId, int score, int vote,
			String comments)
		throws PortalException, SystemException {

		KBFeedbackEntry kbFeedbackEntry = null;

		try {

			// KBFeedback entry

			int oldVote = 0;
			double oldScore = 0;
			Date now = new Date();

			kbFeedbackEntry = kbFeedbackEntryPersistence.findByA_U(
				articleId, userId);

			oldScore = kbFeedbackEntry.getScore();
			oldVote = kbFeedbackEntry.getVote();

			kbFeedbackEntry.setComments(comments);
			kbFeedbackEntry.setModifiedDate(now);
			kbFeedbackEntry.setScore(score);
			kbFeedbackEntry.setVote(vote);

			kbFeedbackEntryPersistence.update(kbFeedbackEntry, false);

			// KBFeedback stats

			KBFeedbackStats kbFeedbackStats =
				kbFeedbackStatsLocalService.getArticleKBFeedbackStats(
					articleId);

			int totalScoreEntries = kbFeedbackStats.getTotalScoreEntries();
			int totalVotes = kbFeedbackStats.getTotalVotes();
			int yesVotes = kbFeedbackStats.getYesVotes();
			double averageScore = kbFeedbackStats.getAverageScore();
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

			kbFeedbackStats.setAverageScore(averageScore);
			kbFeedbackStats.setTotalScoreEntries(totalScoreEntries);
			kbFeedbackStats.setTotalVotes(totalVotes);
			kbFeedbackStats.setYesVotes(yesVotes);

			kbFeedbackStatsPersistence.update(kbFeedbackStats, false);
		}
		catch (NoSuchFeedbackEntryException nsfee) {
			kbFeedbackEntry = addKBFeedbackEntry(
				articleId, userId, score, vote, comments);
		}

		return kbFeedbackEntry;
	}

	public KBFeedbackEntry updateScore(long articleId, long userId, int score)
		throws PortalException, SystemException {

		KBFeedbackEntry kbFeedbackEntry = null;

		try {

			// KBFeedback Entry

			Date now = new Date();

			kbFeedbackEntry =
				kbFeedbackEntryPersistence.findByA_U(articleId, userId);

			double oldScore = kbFeedbackEntry.getScore();

			kbFeedbackEntry.setModifiedDate(now);
			kbFeedbackEntry.setScore(score);

			kbFeedbackEntryPersistence.update(kbFeedbackEntry, false);

			// KBFeedback Stats

			KBFeedbackStats kbFeedbackStats =
				kbFeedbackStatsLocalService.getArticleKBFeedbackStats(articleId);

			int totalScoreEntries = kbFeedbackStats.getTotalScoreEntries();
			double averageScore = kbFeedbackStats.getAverageScore();
			double scoreDifference = score - oldScore;
			double totalScore = averageScore * totalScoreEntries;

			if ((oldScore == 0) && (score != 0)) {
				totalScoreEntries = totalScoreEntries + 1;
			}

			if (oldScore != score) {
				averageScore = (totalScore + scoreDifference) / totalScoreEntries;
			}

			kbFeedbackStats.setAverageScore(averageScore);
			kbFeedbackStats.setTotalScoreEntries(totalScoreEntries);

			kbFeedbackStatsPersistence.update(kbFeedbackStats, false);
		}
		catch (NoSuchFeedbackEntryException nsfee) {
			kbFeedbackEntry = addKBFeedbackEntry(
				articleId, userId, score, 0, StringPool.BLANK);
		}

		return kbFeedbackEntry;
	}

	public KBFeedbackEntry updateVote(
			long articleId, long userId, int vote)
		throws PortalException, SystemException {

		KBFeedbackEntry kbFeedbackEntry = null;

		try {

			// KBFeedback entry

			int oldVote = 0;
			Date now = new Date();

			kbFeedbackEntry = kbFeedbackEntryPersistence.findByA_U(
				articleId, userId);

			oldVote = kbFeedbackEntry.getVote();

			kbFeedbackEntry.setModifiedDate(now);
			kbFeedbackEntry.setVote(vote);

			kbFeedbackEntryPersistence.update(kbFeedbackEntry, false);

			// KBFeedback stats

			KBFeedbackStats kbFeedbackStats =
				kbFeedbackStatsLocalService.getArticleKBFeedbackStats(
					articleId);

			int totalVotes = kbFeedbackStats.getTotalVotes();
			int yesVotes = kbFeedbackStats.getYesVotes();

			if ((oldVote != 1) && (vote == 1)) {
				yesVotes++;
			}
			else if ((oldVote == 1) && (vote != 1)) {
				yesVotes--;
			}

			if ((oldVote == 0) && (vote != 0)) {
				totalVotes++;
			}

			kbFeedbackStats.setTotalVotes(totalVotes);
			kbFeedbackStats.setYesVotes(yesVotes);

			kbFeedbackStatsPersistence.update(kbFeedbackStats, false);
		}
		catch (NoSuchFeedbackEntryException nsfee) {
			kbFeedbackEntry = addKBFeedbackEntry(
				articleId, userId, 0, vote, StringPool.BLANK);
		}

		return kbFeedbackEntry;
	}

}