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

import com.liferay.knowledgebase.model.KBStructure;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing KBStructure in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KBStructure
 * @generated
 */
public class KBStructureCacheModel implements CacheModel<KBStructure> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", kbStructureId=");
		sb.append(kbStructureId);
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
		sb.append(", content=");
		sb.append(content);
		sb.append("}");

		return sb.toString();
	}

	public KBStructure toEntityModel() {
		KBStructureImpl kbStructureImpl = new KBStructureImpl();

		if (uuid == null) {
			kbStructureImpl.setUuid(StringPool.BLANK);
		}
		else {
			kbStructureImpl.setUuid(uuid);
		}

		kbStructureImpl.setKbStructureId(kbStructureId);
		kbStructureImpl.setGroupId(groupId);
		kbStructureImpl.setCompanyId(companyId);
		kbStructureImpl.setUserId(userId);

		if (userName == null) {
			kbStructureImpl.setUserName(StringPool.BLANK);
		}
		else {
			kbStructureImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kbStructureImpl.setCreateDate(null);
		}
		else {
			kbStructureImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kbStructureImpl.setModifiedDate(null);
		}
		else {
			kbStructureImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
			kbStructureImpl.setTitle(StringPool.BLANK);
		}
		else {
			kbStructureImpl.setTitle(title);
		}

		if (content == null) {
			kbStructureImpl.setContent(StringPool.BLANK);
		}
		else {
			kbStructureImpl.setContent(content);
		}

		kbStructureImpl.resetOriginalValues();

		return kbStructureImpl;
	}

	public String uuid;
	public long kbStructureId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public String content;
}