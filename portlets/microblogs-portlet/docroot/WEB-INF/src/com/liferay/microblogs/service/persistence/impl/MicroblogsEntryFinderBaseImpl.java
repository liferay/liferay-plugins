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

package com.liferay.microblogs.service.persistence.impl;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.service.persistence.MicroblogsEntryPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MicroblogsEntryFinderBaseImpl extends BasePersistenceImpl<MicroblogsEntry> {
	@Override
	public Set<String> getBadColumnNames() {
		return getMicroblogsEntryPersistence().getBadColumnNames();
	}

	/**
	 * Returns the microblogs entry persistence.
	 *
	 * @return the microblogs entry persistence
	 */
	public MicroblogsEntryPersistence getMicroblogsEntryPersistence() {
		return microblogsEntryPersistence;
	}

	/**
	 * Sets the microblogs entry persistence.
	 *
	 * @param microblogsEntryPersistence the microblogs entry persistence
	 */
	public void setMicroblogsEntryPersistence(
		MicroblogsEntryPersistence microblogsEntryPersistence) {
		this.microblogsEntryPersistence = microblogsEntryPersistence;
	}

	@BeanReference(type = MicroblogsEntryPersistence.class)
	protected MicroblogsEntryPersistence microblogsEntryPersistence;
}