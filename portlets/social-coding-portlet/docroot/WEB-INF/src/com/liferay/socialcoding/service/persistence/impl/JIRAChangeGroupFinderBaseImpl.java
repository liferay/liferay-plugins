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

package com.liferay.socialcoding.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.socialcoding.model.JIRAChangeGroup;
import com.liferay.socialcoding.service.persistence.JIRAChangeGroupPersistence;

import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class JIRAChangeGroupFinderBaseImpl extends BasePersistenceImpl<JIRAChangeGroup> {
	@Override
	public Set<String> getBadColumnNames() {
		return getJIRAChangeGroupPersistence().getBadColumnNames();
	}

	/**
	 * Returns the j i r a change group persistence.
	 *
	 * @return the j i r a change group persistence
	 */
	public JIRAChangeGroupPersistence getJIRAChangeGroupPersistence() {
		return jiraChangeGroupPersistence;
	}

	/**
	 * Sets the j i r a change group persistence.
	 *
	 * @param jiraChangeGroupPersistence the j i r a change group persistence
	 */
	public void setJIRAChangeGroupPersistence(
		JIRAChangeGroupPersistence jiraChangeGroupPersistence) {
		this.jiraChangeGroupPersistence = jiraChangeGroupPersistence;
	}

	@BeanReference(type = JIRAChangeGroupPersistence.class)
	protected JIRAChangeGroupPersistence jiraChangeGroupPersistence;
}