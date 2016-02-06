/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.contacts.service.impl;

import com.liferay.contacts.exception.DuplicateEntryEmailAddressException;
import com.liferay.contacts.exception.EntryEmailAddressException;
import com.liferay.contacts.exception.RequiredEntryEmailAddressException;
import com.liferay.contacts.model.Entry;
import com.liferay.contacts.service.base.EntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.ContactFullNameException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class EntryLocalServiceImpl extends EntryLocalServiceBaseImpl {

	public Entry addEntry(
			long userId, String fullName, String emailAddress, String comments)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(user.getCompanyId(), 0, userId, fullName, emailAddress);

		long contactId = counterLocalService.increment();

		Entry entry = entryPersistence.create(contactId);

		entry.setCompanyId(user.getCompanyId());
		entry.setUserId(user.getUserId());
		entry.setUserName(user.getFullName());
		entry.setCreateDate(now);
		entry.setModifiedDate(now);
		entry.setFullName(fullName);
		entry.setEmailAddress(emailAddress);
		entry.setComments(comments);

		entryPersistence.update(entry);

		return entry;
	}

	public List<Entry> getEntries(long userId, int start, int end) {
		return entryPersistence.findByUserId(userId);
	}

	public int getEntriesCount(long userId) {
		return entryPersistence.countByUserId(userId);
	}

	public List<Entry> search(
		long userId, String keywords, int start, int end) {

		return entryFinder.findByKeywords(userId, keywords, start, end);
	}

	public int searchCount(long userId, String keywords) {
		return entryFinder.countByKeywords(userId, keywords);
	}

	public List<BaseModel<?>> searchUsersAndContacts(
		long companyId, long userId, String keywords, int start, int end) {

		return entryFinder.findByKeywords(
			companyId, userId, keywords, start, end);
	}

	public int searchUsersAndContactsCount(
		long companyId, long userId, String keywords) {

		return entryFinder.countByKeywords(companyId, userId, keywords);
	}

	public Entry updateEntry(
			long entryId, String fullName, String emailAddress, String comments)
		throws PortalException {

		Entry entry = entryPersistence.findByPrimaryKey(entryId);

		validate(
			entry.getCompanyId(), entryId, entry.getUserId(), fullName,
			emailAddress);

		entry.setModifiedDate(new Date());
		entry.setFullName(fullName);
		entry.setEmailAddress(emailAddress);
		entry.setComments(comments);

		entryPersistence.update(entry);

		return entry;
	}

	protected void validate(
			long companyId, long entryId, long userId, String fullName,
			String emailAddress)
		throws PortalException {

		if (Validator.isNull(fullName)) {
			throw new ContactFullNameException();
		}

		if (Validator.isNull(emailAddress)) {
			throw new RequiredEntryEmailAddressException();
		}

		if (!Validator.isEmailAddress(emailAddress)) {
			throw new EntryEmailAddressException();
		}

		if (entryId > 0) {
			Entry entry = entryPersistence.findByPrimaryKey(entryId);

			if (!StringUtil.equalsIgnoreCase(
					emailAddress, entry.getEmailAddress())) {

				validateEmailAddress(companyId, userId, emailAddress);
			}
		}
		else {
			validateEmailAddress(companyId, userId, emailAddress);
		}
	}

	protected void validateEmailAddress(
			long companyId, long userId, String emailAddress)
		throws PortalException {

		Entry entry = entryPersistence.fetchByU_EA(userId, emailAddress);

		if (entry != null) {
			throw new DuplicateEntryEmailAddressException();
		}

		User user = userPersistence.fetchByC_EA(companyId, emailAddress);

		if (user != null) {
			throw new DuplicateEntryEmailAddressException();
		}
	}

}