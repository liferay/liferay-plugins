/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.compat.hook.events;

import com.liferay.compat.portlet.documentlibrary.util.DLUtil;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Jonathan Lee
 */
public class StartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			for (String id : ids) {
				doRun(GetterUtil.getLong(id));
			}
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun(long companyId) throws Exception {
		ExpandoBridge expandoBridge =
			ExpandoBridgeFactoryUtil.getExpandoBridge(
				companyId, DLFileEntry.class.getName());

		if (!expandoBridge.hasAttribute(DLUtil.MANUAL_CHECK_IN_REQUIRED)) {
			expandoBridge.addAttribute(
				DLUtil.MANUAL_CHECK_IN_REQUIRED, ExpandoColumnConstants.BOOLEAN,
				false);

			UnicodeProperties properties = expandoBridge.getAttributeProperties(
				DLUtil.MANUAL_CHECK_IN_REQUIRED);

			properties.setProperty("hidden", Boolean.toString(true));

			expandoBridge.setAttributeProperties(
				DLUtil.MANUAL_CHECK_IN_REQUIRED, properties, false);
		}
	}

}