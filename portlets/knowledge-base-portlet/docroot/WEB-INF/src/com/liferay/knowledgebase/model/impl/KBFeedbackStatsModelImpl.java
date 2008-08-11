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

package com.liferay.knowledgebase.model.impl;

import com.liferay.knowledgebase.model.KBFeedbackStats;
import com.liferay.knowledgebase.model.KBFeedbackStatsSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="KBFeedbackStatsModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBFeedbackStatsModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "KB_KBFeedbackStats";
	public static final Object[][] TABLE_COLUMNS = {
			{ "kbFeedbackStatsId", new Integer(Types.BIGINT) },
			

			{ "articleId", new Integer(Types.BIGINT) },
			

			{ "averageScore", new Integer(Types.DOUBLE) },
			

			{ "totalScoreEntries", new Integer(Types.INTEGER) },
			

			{ "totalVotes", new Integer(Types.INTEGER) },
			

			{ "yesVotes", new Integer(Types.INTEGER) }
		};
	public static final String TABLE_SQL_CREATE = "create table KB_KBFeedbackStats (kbFeedbackStatsId LONG not null primary key,articleId LONG,averageScore DOUBLE,totalScoreEntries INTEGER,totalVotes INTEGER,yesVotes INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table KB_KBFeedbackStats";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.knowledgebase.model.KBFeedbackStats"),
			true);

	public static KBFeedbackStats toModel(KBFeedbackStatsSoap soapModel) {
		KBFeedbackStats model = new KBFeedbackStatsImpl();

		model.setKbFeedbackStatsId(soapModel.getKbFeedbackStatsId());
		model.setArticleId(soapModel.getArticleId());
		model.setAverageScore(soapModel.getAverageScore());
		model.setTotalScoreEntries(soapModel.getTotalScoreEntries());
		model.setTotalVotes(soapModel.getTotalVotes());
		model.setYesVotes(soapModel.getYesVotes());

		return model;
	}

	public static List<KBFeedbackStats> toModels(
		KBFeedbackStatsSoap[] soapModels) {
		List<KBFeedbackStats> models = new ArrayList<KBFeedbackStats>(soapModels.length);

		for (KBFeedbackStatsSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.knowledgebase.model.KBFeedbackStats"));

	public KBFeedbackStatsModelImpl() {
	}

	public long getPrimaryKey() {
		return _kbFeedbackStatsId;
	}

	public void setPrimaryKey(long pk) {
		setKbFeedbackStatsId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_kbFeedbackStatsId);
	}

	public long getKbFeedbackStatsId() {
		return _kbFeedbackStatsId;
	}

	public void setKbFeedbackStatsId(long kbFeedbackStatsId) {
		if (kbFeedbackStatsId != _kbFeedbackStatsId) {
			_kbFeedbackStatsId = kbFeedbackStatsId;
		}
	}

	public long getArticleId() {
		return _articleId;
	}

	public void setArticleId(long articleId) {
		if (articleId != _articleId) {
			_articleId = articleId;
		}
	}

	public double getAverageScore() {
		return _averageScore;
	}

	public void setAverageScore(double averageScore) {
		if (averageScore != _averageScore) {
			_averageScore = averageScore;
		}
	}

	public int getTotalScoreEntries() {
		return _totalScoreEntries;
	}

	public void setTotalScoreEntries(int totalScoreEntries) {
		if (totalScoreEntries != _totalScoreEntries) {
			_totalScoreEntries = totalScoreEntries;
		}
	}

	public int getTotalVotes() {
		return _totalVotes;
	}

	public void setTotalVotes(int totalVotes) {
		if (totalVotes != _totalVotes) {
			_totalVotes = totalVotes;
		}
	}

	public int getYesVotes() {
		return _yesVotes;
	}

	public void setYesVotes(int yesVotes) {
		if (yesVotes != _yesVotes) {
			_yesVotes = yesVotes;
		}
	}

	public KBFeedbackStats toEscapedModel() {
		if (isEscapedModel()) {
			return (KBFeedbackStats)this;
		}
		else {
			KBFeedbackStats model = new KBFeedbackStatsImpl();

			model.setEscapedModel(true);

			model.setKbFeedbackStatsId(getKbFeedbackStatsId());
			model.setArticleId(getArticleId());
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
		KBFeedbackStatsImpl clone = new KBFeedbackStatsImpl();

		clone.setKbFeedbackStatsId(getKbFeedbackStatsId());
		clone.setArticleId(getArticleId());
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

		KBFeedbackStatsImpl kbFeedbackStats = (KBFeedbackStatsImpl)obj;

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

		KBFeedbackStatsImpl kbFeedbackStats = null;

		try {
			kbFeedbackStats = (KBFeedbackStatsImpl)obj;
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

	private long _kbFeedbackStatsId;
	private long _articleId;
	private double _averageScore;
	private int _totalScoreEntries;
	private int _totalVotes;
	private int _yesVotes;
}