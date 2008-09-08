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

package com.liferay.kb.knowledgebase.model;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

/**
 * <a href="KBFeedbackStatsClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBFeedbackStatsClp extends BaseModelImpl implements KBFeedbackStats {
	public KBFeedbackStatsClp() {
	}

	public long getPrimaryKey() {
		return _feedbackStatsId;
	}

	public void setPrimaryKey(long pk) {
		setFeedbackStatsId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_feedbackStatsId);
	}

	public long getFeedbackStatsId() {
		return _feedbackStatsId;
	}

	public void setFeedbackStatsId(long feedbackStatsId) {
		_feedbackStatsId = feedbackStatsId;
	}

	public long getArticleResourcePrimKey() {
		return _articleResourcePrimKey;
	}

	public void setArticleResourcePrimKey(long articleResourcePrimKey) {
		_articleResourcePrimKey = articleResourcePrimKey;
	}

	public double getAverageScore() {
		return _averageScore;
	}

	public void setAverageScore(double averageScore) {
		_averageScore = averageScore;
	}

	public int getTotalScoreEntries() {
		return _totalScoreEntries;
	}

	public void setTotalScoreEntries(int totalScoreEntries) {
		_totalScoreEntries = totalScoreEntries;
	}

	public int getTotalVotes() {
		return _totalVotes;
	}

	public void setTotalVotes(int totalVotes) {
		_totalVotes = totalVotes;
	}

	public int getYesVotes() {
		return _yesVotes;
	}

	public void setYesVotes(int yesVotes) {
		_yesVotes = yesVotes;
	}

	public KBFeedbackStats toEscapedModel() {
		if (isEscapedModel()) {
			return (KBFeedbackStats)this;
		}
		else {
			KBFeedbackStats model = new KBFeedbackStatsClp();

			model.setEscapedModel(true);

			model.setFeedbackStatsId(getFeedbackStatsId());
			model.setArticleResourcePrimKey(getArticleResourcePrimKey());
			model.setAverageScore(getAverageScore());
			model.setTotalScoreEntries(getTotalScoreEntries());
			model.setTotalVotes(getTotalVotes());
			model.setYesVotes(getYesVotes());

			model = (KBFeedbackStats)Proxy.newProxyInstance(KBFeedbackStats.class.getClassLoader(),
					new Class[] { KBFeedbackStats.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		KBFeedbackStatsClp clone = new KBFeedbackStatsClp();

		clone.setFeedbackStatsId(getFeedbackStatsId());
		clone.setArticleResourcePrimKey(getArticleResourcePrimKey());
		clone.setAverageScore(getAverageScore());
		clone.setTotalScoreEntries(getTotalScoreEntries());
		clone.setTotalVotes(getTotalVotes());
		clone.setYesVotes(getYesVotes());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		KBFeedbackStatsClp kbFeedbackStats = (KBFeedbackStatsClp)obj;

		long pk = kbFeedbackStats.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		KBFeedbackStatsClp kbFeedbackStats = null;

		try {
			kbFeedbackStats = (KBFeedbackStatsClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = kbFeedbackStats.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	private long _feedbackStatsId;
	private long _articleResourcePrimKey;
	private double _averageScore;
	private int _totalScoreEntries;
	private int _totalVotes;
	private int _yesVotes;
}