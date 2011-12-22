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

package com.liferay.mongodb.hook.events;

import com.liferay.mongodb.util.MongoDBUtil;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Raymond Augé
 * @author Brian Wing Shun Chan
 */
public class StartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			doRun(ids);
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun(String[] ids) throws Exception {
		for (String id : ids) {
			long companyId = GetterUtil.getLong(id);

			if (!MongoDBUtil.authenticate(companyId)) {
				throw new SystemException("Authentication failed");
			}
		}
	}

}