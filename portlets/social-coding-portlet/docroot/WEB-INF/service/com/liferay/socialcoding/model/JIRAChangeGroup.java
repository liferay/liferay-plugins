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

package com.liferay.socialcoding.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Accessor;
import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the JIRAChangeGroup service. Represents a row in the &quot;changegroup&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAChangeGroupModel
 * @see com.liferay.socialcoding.model.impl.JIRAChangeGroupImpl
 * @see com.liferay.socialcoding.model.impl.JIRAChangeGroupModelImpl
 * @generated
 */
@ProviderType
public interface JIRAChangeGroup extends JIRAChangeGroupModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.socialcoding.model.impl.JIRAChangeGroupImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<JIRAChangeGroup, Long> JIRA_CHANGE_GROUP_ID_ACCESSOR =
		new Accessor<JIRAChangeGroup, Long>() {
			@Override
			public Long get(JIRAChangeGroup jiraChangeGroup) {
				return jiraChangeGroup.getJiraChangeGroupId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<JIRAChangeGroup> getTypeClass() {
				return JIRAChangeGroup.class;
			}
		};
}