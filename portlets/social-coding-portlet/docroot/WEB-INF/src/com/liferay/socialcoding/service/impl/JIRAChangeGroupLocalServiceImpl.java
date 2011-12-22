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

package com.liferay.socialcoding.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.socialcoding.model.JIRAChangeGroup;
import com.liferay.socialcoding.service.base.JIRAChangeGroupLocalServiceBaseImpl;

/**
 * @author Brian Wing Shun Chan
 */
public class JIRAChangeGroupLocalServiceImpl
	extends JIRAChangeGroupLocalServiceBaseImpl {

	@Override
	public JIRAChangeGroup getJIRAChangeGroup(long jiraChangeGroupId)
		throws PortalException, SystemException {

		return jiraChangeGroupPersistence.findByPrimaryKey(jiraChangeGroupId);
	}

}