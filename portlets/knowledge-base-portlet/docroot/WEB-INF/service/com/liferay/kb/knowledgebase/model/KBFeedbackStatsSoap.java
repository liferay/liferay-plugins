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

package com.liferay.kb.knowledgebase.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="KBFeedbackStatsSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBFeedbackStatsSoap implements Serializable {
	public static KBFeedbackStatsSoap toSoapModel(KBFeedbackStats model) {
		KBFeedbackStatsSoap soapModel = new KBFeedbackStatsSoap();

		soapModel.setFeedbackStatsId(model.getFeedbackStatsId());
		soapModel.setArticleResourcePrimKey(model.getArticleResourcePrimKey());
		soapModel.setAverageScore(model.getAverageScore());
		soapModel.setTotalScoreEntries(model.getTotalScoreEntries());
		soapModel.setTotalVotes(model.getTotalVotes());
		soapModel.setYesVotes(model.getYesVotes());

		return soapModel;
	}

	public static KBFeedbackStatsSoap[] toSoapModels(
		List<KBFeedbackStats> models) {
		List<KBFeedbackStatsSoap> soapModels = new ArrayList<KBFeedbackStatsSoap>(models.size());

		for (KBFeedbackStats model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KBFeedbackStatsSoap[soapModels.size()]);
	}

	public KBFeedbackStatsSoap() {
	}

	public long getPrimaryKey() {
		return _feedbackStatsId;
	}

	public void setPrimaryKey(long pk) {
		setFeedbackStatsId(pk);
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

	private long _feedbackStatsId;
	private long _articleResourcePrimKey;
	private double _averageScore;
	private int _totalScoreEntries;
	private int _totalVotes;
	private int _yesVotes;
}