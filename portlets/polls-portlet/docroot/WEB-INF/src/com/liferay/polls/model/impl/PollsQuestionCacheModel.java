/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.polls.model.PollsQuestion;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PollsQuestion in entity cache.
 *
 * @author Juan Fern√°ndez
 * @see PollsQuestion
 * @generated
 */
public class PollsQuestionCacheModel implements CacheModel<PollsQuestion>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", pollsQuestionId=");
		sb.append(pollsQuestionId);
		sb.append(", groupId=");
		sb.append(groupId);
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
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", expirationDate=");
		sb.append(expirationDate);
		sb.append(", lastVoteDate=");
		sb.append(lastVoteDate);
		sb.append("}");

		return sb.toString();
	}

	public PollsQuestion toEntityModel() {
		PollsQuestionImpl pollsQuestionImpl = new PollsQuestionImpl();

		if (uuid == null) {
			pollsQuestionImpl.setUuid(StringPool.BLANK);
		}
		else {
			pollsQuestionImpl.setUuid(uuid);
		}

		pollsQuestionImpl.setPollsQuestionId(pollsQuestionId);
		pollsQuestionImpl.setGroupId(groupId);
		pollsQuestionImpl.setCompanyId(companyId);
		pollsQuestionImpl.setUserId(userId);

		if (userName == null) {
			pollsQuestionImpl.setUserName(StringPool.BLANK);
		}
		else {
			pollsQuestionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			pollsQuestionImpl.setCreateDate(null);
		}
		else {
			pollsQuestionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			pollsQuestionImpl.setModifiedDate(null);
		}
		else {
			pollsQuestionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
			pollsQuestionImpl.setTitle(StringPool.BLANK);
		}
		else {
			pollsQuestionImpl.setTitle(title);
		}

		if (description == null) {
			pollsQuestionImpl.setDescription(StringPool.BLANK);
		}
		else {
			pollsQuestionImpl.setDescription(description);
		}

		if (expirationDate == Long.MIN_VALUE) {
			pollsQuestionImpl.setExpirationDate(null);
		}
		else {
			pollsQuestionImpl.setExpirationDate(new Date(expirationDate));
		}

		if (lastVoteDate == Long.MIN_VALUE) {
			pollsQuestionImpl.setLastVoteDate(null);
		}
		else {
			pollsQuestionImpl.setLastVoteDate(new Date(lastVoteDate));
		}

		pollsQuestionImpl.resetOriginalValues();

		return pollsQuestionImpl;
	}

	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		pollsQuestionId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		title = objectInput.readUTF();
		description = objectInput.readUTF();
		expirationDate = objectInput.readLong();
		lastVoteDate = objectInput.readLong();
	}

	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(pollsQuestionId);
		objectOutput.writeLong(groupId);
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

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(expirationDate);
		objectOutput.writeLong(lastVoteDate);
	}

	public String uuid;
	public long pollsQuestionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public String description;
	public long expirationDate;
	public long lastVoteDate;
}