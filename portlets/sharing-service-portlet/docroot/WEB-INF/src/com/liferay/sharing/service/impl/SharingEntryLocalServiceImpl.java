/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.sharing.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.sharing.model.SharingEntry;
import com.liferay.sharing.service.base.SharingEntryLocalServiceBaseImpl;
import com.liferay.sharing.service.persistence.SharingEntryPK;

import java.util.List;
import java.util.Map;

/**
 * @author Sherry Yang
 */
@ProviderType
public class SharingEntryLocalServiceImpl
	extends SharingEntryLocalServiceBaseImpl {

	@Override
	public void addSharingEntries(
		long classNameId, long classPK, Map<Long, long[]> scopes) {

		for (Long sharingClassNameId : scopes.keySet()) {
			long[] sharingClassPKs = scopes.get(sharingClassNameId);

			if ((sharingClassPKs == null) || (sharingClassPKs.length == 0)) {
				continue;
			}

			for (long sharingClassPK : sharingClassPKs) {
				addSharingEntry(
					classNameId, classPK, sharingClassNameId, sharingClassPK);
			}
		}
	}

	@Override
	public void addSharingEntry(
		long classNameId, long classPK, long sharingClassNameId,
		long sharingClassPK) {

		SharingEntryPK pk = new SharingEntryPK(
			classNameId, classPK, sharingClassNameId, sharingClassPK);

		SharingEntry SharingEntry = sharingEntryPersistence.fetchByPrimaryKey(
			pk);

		if (SharingEntry == null) {
			SharingEntry = sharingEntryPersistence.create(pk);

			sharingEntryPersistence.update(SharingEntry);
		}
	}

	@Override
	public int countEntriesByUserId(
		long userId, long[] classNameIds, Map<Long, long[]> scopes) {

		return sharingEntryFinder.countEntriesByUserId(
			userId, classNameIds, scopes);
	}

	@Override
	public int countSharingEntriesByScope(
		long sharingClassNameId, long sharingClassPK) {

		return sharingEntryPersistence.countByS_S(
			sharingClassNameId, sharingClassPK);
	}

	@Override
	public int countSharingEntriesByScope(
		long classNameId, long sharingClassNameId, long sharingClassPK) {

		return sharingEntryPersistence.countByC_S_S(
			classNameId, sharingClassNameId, sharingClassPK);
	}

	@Override
	public void deleteSharingEntries(long classNameId, long classPK) {
		sharingEntryPersistence.removeByC_C(classNameId, classPK);
	}

	@Override
	public List<Object[]> getEntriesByUserId(
		long userId, long[] classNameIds, Map<Long, long[]> scopes,
		int start, int end) {

		return sharingEntryFinder.findEntriesByUserId(
			userId, classNameIds, scopes, start, end);
	}

	@Override
	public List<SharingEntry> getSharingEntries(
		long classNameId, long classPK) {

		return sharingEntryPersistence.findByC_C(classNameId, classPK);
	}

	@Override
	public List<SharingEntry> getSharingEntries(
		long classNameId, long classPK, long sharingClassNameId) {

		return sharingEntryPersistence.findByC_C_S(
			classNameId, classPK, sharingClassNameId);
	}

	@Override
	public List<SharingEntry> getSharingEntriesByScope(
		long sharingClassNameId, long sharingClassPK, int start, int end) {

		return sharingEntryPersistence.findByS_S(
			sharingClassNameId, sharingClassPK, start, end);
	}

	@Override
	public List<SharingEntry> getSharingEntriesByScope(
		long classNameId, long sharingClassNameId, long sharingClassPK,
		int start, int end) {

		return sharingEntryPersistence.findByC_S_S(
			classNameId, sharingClassNameId, sharingClassPK, start, end);
	}

}