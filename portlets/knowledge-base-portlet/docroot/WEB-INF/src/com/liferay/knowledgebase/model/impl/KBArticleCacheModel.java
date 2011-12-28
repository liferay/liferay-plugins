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

import com.liferay.knowledgebase.model.KBArticle;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing KBArticle in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KBArticle
 * @generated
 */
public class KBArticleCacheModel implements CacheModel<KBArticle>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(49);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", kbArticleId=");
		sb.append(kbArticleId);
		sb.append(", resourcePrimKey=");
		sb.append(resourcePrimKey);
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
		sb.append(", rootResourcePrimKey=");
		sb.append(rootResourcePrimKey);
		sb.append(", parentResourcePrimKey=");
		sb.append(parentResourcePrimKey);
		sb.append(", version=");
		sb.append(version);
		sb.append(", title=");
		sb.append(title);
		sb.append(", content=");
		sb.append(content);
		sb.append(", description=");
		sb.append(description);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", sections=");
		sb.append(sections);
		sb.append(", viewCount=");
		sb.append(viewCount);
		sb.append(", latest=");
		sb.append(latest);
		sb.append(", main=");
		sb.append(main);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	public KBArticle toEntityModel() {
		KBArticleImpl kbArticleImpl = new KBArticleImpl();

		if (uuid == null) {
			kbArticleImpl.setUuid(StringPool.BLANK);
		}
		else {
			kbArticleImpl.setUuid(uuid);
		}

		kbArticleImpl.setKbArticleId(kbArticleId);
		kbArticleImpl.setResourcePrimKey(resourcePrimKey);
		kbArticleImpl.setGroupId(groupId);
		kbArticleImpl.setCompanyId(companyId);
		kbArticleImpl.setUserId(userId);

		if (userName == null) {
			kbArticleImpl.setUserName(StringPool.BLANK);
		}
		else {
			kbArticleImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kbArticleImpl.setCreateDate(null);
		}
		else {
			kbArticleImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kbArticleImpl.setModifiedDate(null);
		}
		else {
			kbArticleImpl.setModifiedDate(new Date(modifiedDate));
		}

		kbArticleImpl.setRootResourcePrimKey(rootResourcePrimKey);
		kbArticleImpl.setParentResourcePrimKey(parentResourcePrimKey);
		kbArticleImpl.setVersion(version);

		if (title == null) {
			kbArticleImpl.setTitle(StringPool.BLANK);
		}
		else {
			kbArticleImpl.setTitle(title);
		}

		if (content == null) {
			kbArticleImpl.setContent(StringPool.BLANK);
		}
		else {
			kbArticleImpl.setContent(content);
		}

		if (description == null) {
			kbArticleImpl.setDescription(StringPool.BLANK);
		}
		else {
			kbArticleImpl.setDescription(description);
		}

		kbArticleImpl.setPriority(priority);

		if (sections == null) {
			kbArticleImpl.setSections(StringPool.BLANK);
		}
		else {
			kbArticleImpl.setSections(sections);
		}

		kbArticleImpl.setViewCount(viewCount);
		kbArticleImpl.setLatest(latest);
		kbArticleImpl.setMain(main);
		kbArticleImpl.setStatus(status);
		kbArticleImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			kbArticleImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			kbArticleImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			kbArticleImpl.setStatusDate(null);
		}
		else {
			kbArticleImpl.setStatusDate(new Date(statusDate));
		}

		kbArticleImpl.resetOriginalValues();

		return kbArticleImpl;
	}

	public String uuid;
	public long kbArticleId;
	public long resourcePrimKey;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long rootResourcePrimKey;
	public long parentResourcePrimKey;
	public int version;
	public String title;
	public String content;
	public String description;
	public double priority;
	public String sections;
	public int viewCount;
	public boolean latest;
	public boolean main;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
}