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
import com.liferay.socialcoding.model.JIRAAction;
import com.liferay.socialcoding.service.base.JIRAActionLocalServiceBaseImpl;

/**
 * @author Brian Wing Shun Chan
 */
public class JIRAActionLocalServiceImpl extends JIRAActionLocalServiceBaseImpl {

	@Override
	public JIRAAction getJIRAAction(long jiraActionId)
		throws PortalException, SystemException {

		return jiraActionPersistence.findByPrimaryKey(jiraActionId);
	}

}