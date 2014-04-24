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

package com.liferay.rtl.hook.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Eduardo Garcia
 * @see com.liferay.portal.servlet.filters.BasePortalFilter
 */
public abstract class BasePortalFilter extends BaseFilter {

	@Override
	public boolean isFilterEnabled() {
		return _filterEnabled;
	}

	@Override
	public void setFilterEnabled(boolean filterEnabled) {
		_filterEnabled = filterEnabled;
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	private static Log _log = LogFactoryUtil.getLog(BasePortalFilter.class);

	private boolean _filterEnabled = GetterUtil.getBoolean(
		PropsUtil.get(getClass().getName()), true);

}