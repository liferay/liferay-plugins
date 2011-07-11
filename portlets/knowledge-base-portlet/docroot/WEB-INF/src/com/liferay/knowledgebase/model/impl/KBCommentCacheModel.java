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

package com.liferay.knowledgebase.model.impl;

import com.liferay.knowledgebase.model.KBComment;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing KBComment in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KBComment
 * @generated
 */
public class KBCommentCacheModel implements CacheModel<KBComment> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", kbCommentId=");
		sb.append(kbCommentId);
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
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", content=");
		sb.append(content);
		sb.append(", helpful=");
		sb.append(helpful);
		sb.append("}");

		return sb.toString();
	}

	public KBComment toEntityModel() {
		KBCommentImpl kbCommentImpl = new KBCommentImpl();

		if (uuid == null) {
			kbCommentImpl.setUuid(StringPool.BLANK);
		}
		else {
			kbCommentImpl.setUuid(uuid);
		}

		kbCommentImpl.setKbCommentId(kbCommentId);
		kbCommentImpl.setGroupId(groupId);
		kbCommentImpl.setCompanyId(companyId);
		kbCommentImpl.setUserId(userId);

		if (userName == null) {
			kbCommentImpl.setUserName(StringPool.BLANK);
		}
		else {
			kbCommentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kbCommentImpl.setCreateDate(null);
		}
		else {
			kbCommentImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kbCommentImpl.setModifiedDate(null);
		}
		else {
			kbCommentImpl.setModifiedDate(new Date(modifiedDate));
		}

		kbCommentImpl.setClassNameId(classNameId);
		kbCommentImpl.setClassPK(classPK);

		if (content == null) {
			kbCommentImpl.setContent(StringPool.BLANK);
		}
		else {
			kbCommentImpl.setContent(content);
		}

		kbCommentImpl.setHelpful(helpful);

		kbCommentImpl.resetOriginalValues();

		return kbCommentImpl;
	}

	public String uuid;
	public long kbCommentId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public String content;
	public boolean helpful;
}