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

import com.liferay.socialcoding.model.JIRAAction;
import com.liferay.socialcoding.service.persistence.JIRAActionPersistence;

import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class JIRAActionFinderBaseImpl extends BasePersistenceImpl<JIRAAction> {
	@Override
	public Set<String> getBadColumnNames() {
		return getJIRAActionPersistence().getBadColumnNames();
	}

	/**
	 * Returns the j i r a action persistence.
	 *
	 * @return the j i r a action persistence
	 */
	public JIRAActionPersistence getJIRAActionPersistence() {
		return jiraActionPersistence;
	}

	/**
	 * Sets the j i r a action persistence.
	 *
	 * @param jiraActionPersistence the j i r a action persistence
	 */
	public void setJIRAActionPersistence(
		JIRAActionPersistence jiraActionPersistence) {
		this.jiraActionPersistence = jiraActionPersistence;
	}

	@BeanReference(type = JIRAActionPersistence.class)
	protected JIRAActionPersistence jiraActionPersistence;
}