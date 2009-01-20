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
import com.liferay.kb.knowledgebase.NoSuchFeedbackStatsException;
import com.liferay.kb.knowledgebase.model.KBFeedbackStats;
import com.liferay.kb.knowledgebase.service.base.KBFeedbackStatsLocalServiceBaseImpl;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * <a href="KBFeedbackStatsLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 *
 */
public class KBFeedbackStatsLocalServiceImpl
	extends KBFeedbackStatsLocalServiceBaseImpl {

	public void deleteFeedbackStats(long articleResourcePrimKey)
		throws SystemException {

		// KBFeedback stats

		try {
			kbFeedbackStatsPersistence.removeByArticleResourcePrimKey(articleResourcePrimKey);
		}
		catch (NoSuchFeedbackStatsException nsfse) {
			if (_log.isWarnEnabled()) {
				_log.warn(nsfse);
			}
		}

		// KBFeedback entry

		kbFeedbackEntryPersistence.removeByArticleResourcePrimKey(articleResourcePrimKey);
	}

	public KBFeedbackStats getFeedbackStats(long feedbackStatsId)
		throws PortalException, SystemException {

		return kbFeedbackStatsPersistence.findByPrimaryKey(feedbackStatsId);
	}

	public KBFeedbackStats getArticleFeedbackStats(long articleResourcePrimKey)
		throws SystemException {

		KBFeedbackStats feedbackStats = null;

		try {
			feedbackStats = kbFeedbackStatsPersistence.findByArticleResourcePrimKey(articleResourcePrimKey);
		}
		catch (Exception e) {
			long feedbackStatsId = CounterLocalServiceUtil.increment();

			feedbackStats = kbFeedbackStatsPersistence.create(feedbackStatsId);

			feedbackStats.setArticleResourcePrimKey(articleResourcePrimKey);
			feedbackStats.setAverageScore(0.0);
			feedbackStats.setTotalScoreEntries(0);
			feedbackStats.setTotalVotes(0);
			feedbackStats.setYesVotes(0);

			kbFeedbackStatsPersistence.update(feedbackStats, false);
		}

		return feedbackStats;
	}

	private static Log _log =
		LogFactoryUtil.getLog(KBFeedbackStatsLocalServiceImpl.class);

}