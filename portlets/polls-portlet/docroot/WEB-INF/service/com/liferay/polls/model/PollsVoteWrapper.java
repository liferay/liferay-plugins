/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.polls.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PollsVote}.
 * </p>
 *
 * @author Juan Fern√°ndez
 * @see PollsVote
 * @generated
 */
public class PollsVoteWrapper implements PollsVote, ModelWrapper<PollsVote> {
	public PollsVoteWrapper(PollsVote pollsVote) {
		_pollsVote = pollsVote;
	}

	@Override
	public Class<?> getModelClass() {
		return PollsVote.class;
	}

	@Override
	public String getModelClassName() {
		return PollsVote.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("pollsVoteId", getPollsVoteId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("pollsQuestionId", getPollsQuestionId());
		attributes.put("pollsChoiceId", getPollsChoiceId());
		attributes.put("voteDate", getVoteDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long pollsVoteId = (Long)attributes.get("pollsVoteId");

		if (pollsVoteId != null) {
			setPollsVoteId(pollsVoteId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long pollsQuestionId = (Long)attributes.get("pollsQuestionId");

		if (pollsQuestionId != null) {
			setPollsQuestionId(pollsQuestionId);
		}

		Long pollsChoiceId = (Long)attributes.get("pollsChoiceId");

		if (pollsChoiceId != null) {
			setPollsChoiceId(pollsChoiceId);
		}

		Date voteDate = (Date)attributes.get("voteDate");

		if (voteDate != null) {
			setVoteDate(voteDate);
		}
	}

	/**
	* Returns the primary key of this polls vote.
	*
	* @return the primary key of this polls vote
	*/
	@Override
	public long getPrimaryKey() {
		return _pollsVote.getPrimaryKey();
	}

	/**
	* Sets the primary key of this polls vote.
	*
	* @param primaryKey the primary key of this polls vote
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_pollsVote.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the polls vote ID of this polls vote.
	*
	* @return the polls vote ID of this polls vote
	*/
	@Override
	public long getPollsVoteId() {
		return _pollsVote.getPollsVoteId();
	}

	/**
	* Sets the polls vote ID of this polls vote.
	*
	* @param pollsVoteId the polls vote ID of this polls vote
	*/
	@Override
	public void setPollsVoteId(long pollsVoteId) {
		_pollsVote.setPollsVoteId(pollsVoteId);
	}

	/**
	* Returns the company ID of this polls vote.
	*
	* @return the company ID of this polls vote
	*/
	@Override
	public long getCompanyId() {
		return _pollsVote.getCompanyId();
	}

	/**
	* Sets the company ID of this polls vote.
	*
	* @param companyId the company ID of this polls vote
	*/
	@Override
	public void setCompanyId(long companyId) {
		_pollsVote.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this polls vote.
	*
	* @return the user ID of this polls vote
	*/
	@Override
	public long getUserId() {
		return _pollsVote.getUserId();
	}

	/**
	* Sets the user ID of this polls vote.
	*
	* @param userId the user ID of this polls vote
	*/
	@Override
	public void setUserId(long userId) {
		_pollsVote.setUserId(userId);
	}

	/**
	* Returns the user uuid of this polls vote.
	*
	* @return the user uuid of this polls vote
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVote.getUserUuid();
	}

	/**
	* Sets the user uuid of this polls vote.
	*
	* @param userUuid the user uuid of this polls vote
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_pollsVote.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this polls vote.
	*
	* @return the user name of this polls vote
	*/
	@Override
	public java.lang.String getUserName() {
		return _pollsVote.getUserName();
	}

	/**
	* Sets the user name of this polls vote.
	*
	* @param userName the user name of this polls vote
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_pollsVote.setUserName(userName);
	}

	/**
	* Returns the create date of this polls vote.
	*
	* @return the create date of this polls vote
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _pollsVote.getCreateDate();
	}

	/**
	* Sets the create date of this polls vote.
	*
	* @param createDate the create date of this polls vote
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_pollsVote.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this polls vote.
	*
	* @return the modified date of this polls vote
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _pollsVote.getModifiedDate();
	}

	/**
	* Sets the modified date of this polls vote.
	*
	* @param modifiedDate the modified date of this polls vote
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_pollsVote.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the polls question ID of this polls vote.
	*
	* @return the polls question ID of this polls vote
	*/
	@Override
	public long getPollsQuestionId() {
		return _pollsVote.getPollsQuestionId();
	}

	/**
	* Sets the polls question ID of this polls vote.
	*
	* @param pollsQuestionId the polls question ID of this polls vote
	*/
	@Override
	public void setPollsQuestionId(long pollsQuestionId) {
		_pollsVote.setPollsQuestionId(pollsQuestionId);
	}

	/**
	* Returns the polls choice ID of this polls vote.
	*
	* @return the polls choice ID of this polls vote
	*/
	@Override
	public long getPollsChoiceId() {
		return _pollsVote.getPollsChoiceId();
	}

	/**
	* Sets the polls choice ID of this polls vote.
	*
	* @param pollsChoiceId the polls choice ID of this polls vote
	*/
	@Override
	public void setPollsChoiceId(long pollsChoiceId) {
		_pollsVote.setPollsChoiceId(pollsChoiceId);
	}

	/**
	* Returns the vote date of this polls vote.
	*
	* @return the vote date of this polls vote
	*/
	@Override
	public java.util.Date getVoteDate() {
		return _pollsVote.getVoteDate();
	}

	/**
	* Sets the vote date of this polls vote.
	*
	* @param voteDate the vote date of this polls vote
	*/
	@Override
	public void setVoteDate(java.util.Date voteDate) {
		_pollsVote.setVoteDate(voteDate);
	}

	@Override
	public boolean isNew() {
		return _pollsVote.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_pollsVote.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _pollsVote.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_pollsVote.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _pollsVote.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _pollsVote.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_pollsVote.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _pollsVote.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_pollsVote.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_pollsVote.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_pollsVote.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new PollsVoteWrapper((PollsVote)_pollsVote.clone());
	}

	@Override
	public int compareTo(com.liferay.polls.model.PollsVote pollsVote) {
		return _pollsVote.compareTo(pollsVote);
	}

	@Override
	public int hashCode() {
		return _pollsVote.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.polls.model.PollsVote> toCacheModel() {
		return _pollsVote.toCacheModel();
	}

	@Override
	public com.liferay.polls.model.PollsVote toEscapedModel() {
		return new PollsVoteWrapper(_pollsVote.toEscapedModel());
	}

	@Override
	public com.liferay.polls.model.PollsVote toUnescapedModel() {
		return new PollsVoteWrapper(_pollsVote.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _pollsVote.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _pollsVote.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_pollsVote.persist();
	}

	@Override
	public com.liferay.polls.model.PollsChoice getPollsChoice()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsVote.getPollsChoice();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public PollsVote getWrappedPollsVote() {
		return _pollsVote;
	}

	@Override
	public PollsVote getWrappedModel() {
		return _pollsVote;
	}

	@Override
	public void resetOriginalValues() {
		_pollsVote.resetOriginalValues();
	}

	private PollsVote _pollsVote;
}