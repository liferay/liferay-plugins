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

package com.liferay.socialnetworking.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.socialnetworking.model.MeetupsEntry;

import java.util.Date;

/**
 * The cache model class for representing MeetupsEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsEntry
 * @generated
 */
public class MeetupsEntryCacheModel implements CacheModel<MeetupsEntry> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{meetupsEntryId=");
		sb.append(meetupsEntryId);
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
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", totalAttendees=");
		sb.append(totalAttendees);
		sb.append(", maxAttendees=");
		sb.append(maxAttendees);
		sb.append(", price=");
		sb.append(price);
		sb.append(", thumbnailId=");
		sb.append(thumbnailId);
		sb.append("}");

		return sb.toString();
	}

	public MeetupsEntry toEntityModel() {
		MeetupsEntryImpl meetupsEntryImpl = new MeetupsEntryImpl();

		meetupsEntryImpl.setMeetupsEntryId(meetupsEntryId);
		meetupsEntryImpl.setCompanyId(companyId);
		meetupsEntryImpl.setUserId(userId);

		if (userName == null) {
			meetupsEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			meetupsEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			meetupsEntryImpl.setCreateDate(null);
		}
		else {
			meetupsEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			meetupsEntryImpl.setModifiedDate(null);
		}
		else {
			meetupsEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
			meetupsEntryImpl.setTitle(StringPool.BLANK);
		}
		else {
			meetupsEntryImpl.setTitle(title);
		}

		if (description == null) {
			meetupsEntryImpl.setDescription(StringPool.BLANK);
		}
		else {
			meetupsEntryImpl.setDescription(description);
		}

		if (startDate == Long.MIN_VALUE) {
			meetupsEntryImpl.setStartDate(null);
		}
		else {
			meetupsEntryImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			meetupsEntryImpl.setEndDate(null);
		}
		else {
			meetupsEntryImpl.setEndDate(new Date(endDate));
		}

		meetupsEntryImpl.setTotalAttendees(totalAttendees);
		meetupsEntryImpl.setMaxAttendees(maxAttendees);
		meetupsEntryImpl.setPrice(price);
		meetupsEntryImpl.setThumbnailId(thumbnailId);

		meetupsEntryImpl.resetOriginalValues();

		return meetupsEntryImpl;
	}

	public long meetupsEntryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public String description;
	public long startDate;
	public long endDate;
	public int totalAttendees;
	public int maxAttendees;
	public double price;
	public long thumbnailId;
}