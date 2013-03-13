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

package com.liferay.polls.model.impl;

import com.liferay.polls.model.PollsVote;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PollsVote in entity cache.
 *
 * @author Juan Fern√°ndez
 * @see PollsVote
 * @generated
 */
public class PollsVoteCacheModel implements CacheModel<PollsVote>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{pollsVoteId=");
		sb.append(pollsVoteId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", pollsQuestionId=");
		sb.append(pollsQuestionId);
		sb.append(", pollsChoiceId=");
		sb.append(pollsChoiceId);
		sb.append(", voteDate=");
		sb.append(voteDate);
		sb.append("}");

		return sb.toString();
	}

	public PollsVote toEntityModel() {
		PollsVoteImpl pollsVoteImpl = new PollsVoteImpl();

		pollsVoteImpl.setPollsVoteId(pollsVoteId);
		pollsVoteImpl.setCompanyId(companyId);
		pollsVoteImpl.setUserId(userId);

		if (userName == null) {
			pollsVoteImpl.setUserName(StringPool.BLANK);
		}
		else {
			pollsVoteImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			pollsVoteImpl.setCreateDate(null);
		}
		else {
			pollsVoteImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			pollsVoteImpl.setModifiedDate(null);
		}
		else {
			pollsVoteImpl.setModifiedDate(new Date(modifiedDate));
		}

		pollsVoteImpl.setPollsQuestionId(pollsQuestionId);
		pollsVoteImpl.setPollsChoiceId(pollsChoiceId);

		if (voteDate == Long.MIN_VALUE) {
			pollsVoteImpl.setVoteDate(null);
		}
		else {
			pollsVoteImpl.setVoteDate(new Date(voteDate));
		}

		pollsVoteImpl.resetOriginalValues();

		return pollsVoteImpl;
	}

	public void readExternal(ObjectInput objectInput) throws IOException {
		pollsVoteId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		pollsQuestionId = objectInput.readLong();
		pollsChoiceId = objectInput.readLong();
		voteDate = objectInput.readLong();
	}

	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(pollsVoteId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(pollsQuestionId);
		objectOutput.writeLong(pollsChoiceId);
		objectOutput.writeLong(voteDate);
	}

	public long pollsVoteId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long pollsQuestionId;
	public long pollsChoiceId;
	public long voteDate;
}