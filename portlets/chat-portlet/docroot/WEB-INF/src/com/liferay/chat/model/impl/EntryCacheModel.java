/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.chat.model.impl;

import com.liferay.chat.model.Entry;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing Entry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Entry
 * @generated
 */
public class EntryCacheModel implements CacheModel<Entry>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{entryId=");
		sb.append(entryId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", fromUserId=");
		sb.append(fromUserId);
		sb.append(", toUserId=");
		sb.append(toUserId);
		sb.append(", content=");
		sb.append(content);
		sb.append("}");

		return sb.toString();
	}

	public Entry toEntityModel() {
		EntryImpl entryImpl = new EntryImpl();

		entryImpl.setEntryId(entryId);
		entryImpl.setCreateDate(createDate);
		entryImpl.setFromUserId(fromUserId);
		entryImpl.setToUserId(toUserId);

		if (content == null) {
			entryImpl.setContent(StringPool.BLANK);
		}
		else {
			entryImpl.setContent(content);
		}

		entryImpl.resetOriginalValues();

		return entryImpl;
	}

	public long entryId;
	public long createDate;
	public long fromUserId;
	public long toUserId;
	public String content;
}