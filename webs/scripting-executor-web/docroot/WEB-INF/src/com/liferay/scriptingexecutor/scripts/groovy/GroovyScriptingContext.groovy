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

package com.liferay.scriptingexecutor.scripts.groovy;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Michael C. Han
 */
class GroovyScriptingContext {

	static Map<Locale, String> getLocalizationMap(String value) {
		Map<Locale, String> localizationMap = new HashMap<Locale, String>();

		localizationMap.put(LocaleUtil.getDefault(), value);

		return localizationMap;
	}

	GroovyScriptingContext() {
		serviceContext = new ServiceContext();

		long defaultCompanyId = PortalUtil.getDefaultCompanyId();

		serviceContext.setCompanyId(defaultCompanyId);

		defaultUserId = UserLocalServiceUtil.getDefaultUserId(defaultCompanyId);
	}

	GroovyScriptingContext(long companyId) {
		serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);

		defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);
	}

	long getCompanyId() {
		return serviceContext.getCompanyId();
	}

	long defaultUserId;
	ServiceContext serviceContext;

}