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

package com.liferay.knowledgebase.model.impl;

import com.liferay.knowledgebase.model.KBTemplate;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing KBTemplate in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KBTemplate
 * @generated
 */
public class KBTemplateCacheModel implements CacheModel<KBTemplate>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", kbTemplateId=");
		sb.append(kbTemplateId);
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

	public KBTemplate toEntityModel() {
		KBTemplateImpl kbTemplateImpl = new KBTemplateImpl();

		if (uuid == null) {
			kbTemplateImpl.setUuid(StringPool.BLANK);
		}
		else {
			kbTemplateImpl.setUuid(uuid);
		}

		kbTemplateImpl.setKbTemplateId(kbTemplateId);
		kbTemplateImpl.setGroupId(groupId);
		kbTemplateImpl.setCompanyId(companyId);
		kbTemplateImpl.setUserId(userId);

		if (userName == null) {
			kbTemplateImpl.setUserName(StringPool.BLANK);
		}
		else {
			kbTemplateImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kbTemplateImpl.setCreateDate(null);
		}
		else {
			kbTemplateImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kbTemplateImpl.setModifiedDate(null);
		}
		else {
			kbTemplateImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
			kbTemplateImpl.setTitle(StringPool.BLANK);
		}
		else {
			kbTemplateImpl.setTitle(title);
		}

		if (content == null) {
			kbTemplateImpl.setContent(StringPool.BLANK);
		}
		else {
			kbTemplateImpl.setContent(content);
		}

		kbTemplateImpl.resetOriginalValues();

		return kbTemplateImpl;
	}

	public String uuid;
	public long kbTemplateId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public String content;
}