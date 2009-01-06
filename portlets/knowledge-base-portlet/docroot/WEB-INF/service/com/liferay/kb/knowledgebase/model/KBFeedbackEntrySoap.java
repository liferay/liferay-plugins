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
import java.util.Date;
import java.util.List;

/**
 * <a href="KBFeedbackEntrySoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBFeedbackEntrySoap implements Serializable {
	public static KBFeedbackEntrySoap toSoapModel(KBFeedbackEntry model) {
		KBFeedbackEntrySoap soapModel = new KBFeedbackEntrySoap();

		soapModel.setFeedbackEntryId(model.getFeedbackEntryId());
		soapModel.setArticleResourcePrimKey(model.getArticleResourcePrimKey());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setComments(model.getComments());
		soapModel.setScore(model.getScore());
		soapModel.setVote(model.getVote());

		return soapModel;
	}

	public static KBFeedbackEntrySoap[] toSoapModels(
		List<KBFeedbackEntry> models) {
		List<KBFeedbackEntrySoap> soapModels = new ArrayList<KBFeedbackEntrySoap>(models.size());

		for (KBFeedbackEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KBFeedbackEntrySoap[soapModels.size()]);
	}

	public KBFeedbackEntrySoap() {
	}

	public long getPrimaryKey() {
		return _feedbackEntryId;
	}

	public void setPrimaryKey(long pk) {
		setFeedbackEntryId(pk);
	}

	public long getFeedbackEntryId() {
		return _feedbackEntryId;
	}

	public void setFeedbackEntryId(long feedbackEntryId) {
		_feedbackEntryId = feedbackEntryId;
	}

	public long getArticleResourcePrimKey() {
		return _articleResourcePrimKey;
	}

	public void setArticleResourcePrimKey(long articleResourcePrimKey) {
		_articleResourcePrimKey = articleResourcePrimKey;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	public int getScore() {
		return _score;
	}

	public void setScore(int score) {
		_score = score;
	}

	public int getVote() {
		return _vote;
	}

	public void setVote(int vote) {
		_vote = vote;
	}

	private long _feedbackEntryId;
	private long _articleResourcePrimKey;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _comments;
	private int _score;
	private int _vote;
}