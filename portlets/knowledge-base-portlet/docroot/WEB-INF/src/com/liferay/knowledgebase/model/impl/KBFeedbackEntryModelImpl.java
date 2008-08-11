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

import com.liferay.knowledgebase.model.KBFeedbackEntry;
import com.liferay.knowledgebase.model.KBFeedbackEntrySoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="KBFeedbackEntryModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBFeedbackEntryModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "KB_KBFeedbackEntry";
	public static final Object[][] TABLE_COLUMNS = {
			{ "kbFeedbackEntryId", new Integer(Types.BIGINT) },
			

			{ "articleId", new Integer(Types.BIGINT) },
			

			{ "userId", new Integer(Types.BIGINT) },
			

			{ "userName", new Integer(Types.VARCHAR) },
			

			{ "createDate", new Integer(Types.TIMESTAMP) },
			

			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			

			{ "comments", new Integer(Types.VARCHAR) },
			

			{ "score", new Integer(Types.INTEGER) },
			

			{ "vote", new Integer(Types.INTEGER) }
		};
	public static final String TABLE_SQL_CREATE = "create table KB_KBFeedbackEntry (kbFeedbackEntryId LONG not null primary key,articleId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,comments STRING null,score INTEGER,vote INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table KB_KBFeedbackEntry";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.knowledgebase.model.KBFeedbackEntry"),
			true);

	public static KBFeedbackEntry toModel(KBFeedbackEntrySoap soapModel) {
		KBFeedbackEntry model = new KBFeedbackEntryImpl();

		model.setKbFeedbackEntryId(soapModel.getKbFeedbackEntryId());
		model.setArticleId(soapModel.getArticleId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setComments(soapModel.getComments());
		model.setScore(soapModel.getScore());
		model.setVote(soapModel.getVote());

		return model;
	}

	public static List<KBFeedbackEntry> toModels(
		KBFeedbackEntrySoap[] soapModels) {
		List<KBFeedbackEntry> models = new ArrayList<KBFeedbackEntry>(soapModels.length);

		for (KBFeedbackEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.knowledgebase.model.KBFeedbackEntry"));

	public KBFeedbackEntryModelImpl() {
	}

	public long getPrimaryKey() {
		return _kbFeedbackEntryId;
	}

	public void setPrimaryKey(long pk) {
		setKbFeedbackEntryId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_kbFeedbackEntryId);
	}

	public long getKbFeedbackEntryId() {
		return _kbFeedbackEntryId;
	}

	public void setKbFeedbackEntryId(long kbFeedbackEntryId) {
		if (kbFeedbackEntryId != _kbFeedbackEntryId) {
			_kbFeedbackEntryId = kbFeedbackEntryId;
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

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		if (userId != _userId) {
			_userId = userId;
		}
	}

	public String getUserName() {
		return GetterUtil.getString(_userName);
	}

	public void setUserName(String userName) {
		if (((userName == null) && (_userName != null)) ||
				((userName != null) && (_userName == null)) ||
				((userName != null) && (_userName != null) &&
				!userName.equals(_userName))) {
			_userName = userName;
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		if (((createDate == null) && (_createDate != null)) ||
				((createDate != null) && (_createDate == null)) ||
				((createDate != null) && (_createDate != null) &&
				!createDate.equals(_createDate))) {
			_createDate = createDate;
		}
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (((modifiedDate == null) && (_modifiedDate != null)) ||
				((modifiedDate != null) && (_modifiedDate == null)) ||
				((modifiedDate != null) && (_modifiedDate != null) &&
				!modifiedDate.equals(_modifiedDate))) {
			_modifiedDate = modifiedDate;
		}
	}

	public String getComments() {
		return GetterUtil.getString(_comments);
	}

	public void setComments(String comments) {
		if (((comments == null) && (_comments != null)) ||
				((comments != null) && (_comments == null)) ||
				((comments != null) && (_comments != null) &&
				!comments.equals(_comments))) {
			_comments = comments;
		}
	}

	public int getScore() {
		return _score;
	}

	public void setScore(int score) {
		if (score != _score) {
			_score = score;
		}
	}

	public int getVote() {
		return _vote;
	}

	public void setVote(int vote) {
		if (vote != _vote) {
			_vote = vote;
		}
	}

	public KBFeedbackEntry toEscapedModel() {
		if (isEscapedModel()) {
			return (KBFeedbackEntry)this;
		}
		else {
			KBFeedbackEntry model = new KBFeedbackEntryImpl();

			model.setEscapedModel(true);

			model.setKbFeedbackEntryId(getKbFeedbackEntryId());
			model.setArticleId(getArticleId());
			model.setUserId(getUserId());
			model.setUserName(HtmlUtil.escape(getUserName()));
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setComments(HtmlUtil.escape(getComments()));
			model.setScore(getScore());
			model.setVote(getVote());

			model = (KBFeedbackEntry)Proxy.newProxyInstance(KBFeedbackEntry.class.getClassLoader(),
					new Class[] { KBFeedbackEntry.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		KBFeedbackEntryImpl clone = new KBFeedbackEntryImpl();

		clone.setKbFeedbackEntryId(getKbFeedbackEntryId());
		clone.setArticleId(getArticleId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setComments(getComments());
		clone.setScore(getScore());
		clone.setVote(getVote());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		KBFeedbackEntryImpl kbFeedbackEntry = (KBFeedbackEntryImpl)obj;

		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				kbFeedbackEntry.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		KBFeedbackEntryImpl kbFeedbackEntry = null;

		try {
			kbFeedbackEntry = (KBFeedbackEntryImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = kbFeedbackEntry.getPrimaryKey();

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

	private long _kbFeedbackEntryId;
	private long _articleId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _comments;
	private int _score;
	private int _vote;
}