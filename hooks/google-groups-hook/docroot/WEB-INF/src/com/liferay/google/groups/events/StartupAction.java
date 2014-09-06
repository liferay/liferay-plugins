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

package com.liferay.google.groups.events;

import com.liferay.google.groups.util.GoogleGroupsUtil;
import com.liferay.google.groups.util.PortletPropsValues;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;

/**
 * @author Matthew Kong
 */
public class StartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			if (!PortletPropsValues.GOOGLE_GROUPS_SYNC_ON_STARTUP) {
				return;
			}

			for (String id : ids) {
				GoogleGroupsUtil.syncGroups(Long.valueOf(id));
			}
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

}