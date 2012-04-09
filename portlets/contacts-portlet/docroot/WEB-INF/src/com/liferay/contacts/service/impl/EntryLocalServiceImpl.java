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

package com.liferay.contacts.service.impl;

import com.liferay.contacts.DuplicateEntryEmailAddressException;
import com.liferay.contacts.EntryEmailAddressException;
import com.liferay.contacts.RequiredEntryEmailAddressException;
import com.liferay.contacts.model.Entry;
import com.liferay.contacts.service.base.EntryLocalServiceBaseImpl;
import com.liferay.portal.ContactFullNameException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class EntryLocalServiceImpl extends EntryLocalServiceBaseImpl {

	public Entry addEntry(
			long userId, String fullName, String emailAddress, String comments)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(0, userId, fullName, emailAddress);

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

		entryPersistence.update(entry, true);

		return entry;
	}

	public List<Entry> getEntries(long userId, int start, int end)
		throws SystemException {

		return entryPersistence.findByUserId(userId);
	}

	public int getEntriesCount(long userId) throws SystemException {
		return entryPersistence.countByUserId(userId);
	}

	public List<Entry> search(long userId, String keywords, int start, int end)
		throws SystemException {

		return entryFinder.findByKeywords(userId, keywords, start, end);
	}

	public int searchCount(long userId, String keywords)
		throws SystemException {

		return entryFinder.countByKeywords(userId, keywords);
	}

	public List<BaseModel<?>> searchUsersAndContacts(
			long companyId, long userId, String keywords, int start, int end)
		throws SystemException {

		return entryFinder.findByKeywords(
			companyId, userId, keywords, start, end);
	}

	public int searchUsersAndContactsCount(
			long companyId, long userId, String keywords)
		throws SystemException {

		return entryFinder.countByKeywords(companyId, userId, keywords);
	}

	public Entry updateEntry(
			long entryId, String fullName, String emailAddress, String comments)
		throws PortalException, SystemException {

		Entry entry = entryPersistence.findByPrimaryKey(entryId);

		validate(entryId, entry.getUserId(), fullName, emailAddress);

		entry.setModifiedDate(new Date());
		entry.setFullName(fullName);
		entry.setEmailAddress(emailAddress);
		entry.setComments(comments);

		entryPersistence.update(entry, true);

		return entry;
	}

	protected void validate(
			long entryId, long userId, String fullName, String emailAddress)
		throws PortalException, SystemException {

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

			if (!emailAddress.equalsIgnoreCase(entry.getEmailAddress())) {
				validateEmailAddress(userId, emailAddress);
			}
		}
		else {
			validateEmailAddress(userId, emailAddress);
		}
	}

	protected void validateEmailAddress(long userId, String emailAddress)
		throws PortalException, SystemException {

		Entry entry = entryPersistence.fetchByU_EA(userId, emailAddress);

		if (entry != null) {
			throw new DuplicateEntryEmailAddressException();
		}
	}

}