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

package com.liferay.microblogs.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Accessor;
import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the MicroblogsEntry service. Represents a row in the &quot;MicroblogsEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see MicroblogsEntryModel
 * @see com.liferay.microblogs.model.impl.MicroblogsEntryImpl
 * @see com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl
 * @generated
 */
@ProviderType
public interface MicroblogsEntry extends MicroblogsEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.microblogs.model.impl.MicroblogsEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MicroblogsEntry, Long> MICROBLOGS_ENTRY_ID_ACCESSOR =
		new Accessor<MicroblogsEntry, Long>() {
			@Override
			public Long get(MicroblogsEntry microblogsEntry) {
				return microblogsEntry.getMicroblogsEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<MicroblogsEntry> getTypeClass() {
				return MicroblogsEntry.class;
			}
		};

	public long fetchParentMicroblogsEntryUserId();

	public long getParentMicroblogsEntryUserId()
		throws com.liferay.portal.kernel.exception.PortalException;
}