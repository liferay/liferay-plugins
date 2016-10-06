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

package com.liferay.sampleservicebuilder.hook.events;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.sampleservicebuilder.service.FooLocalServiceUtil;

/**
 * @author Inácio Nery
 */
public class StartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			doRun(GetterUtil.getLong(ids[0]));
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun(long companyId) throws Exception {
		setupFoo(companyId);
	}

	protected void setupFoo(long companyId) throws Exception {
		try {
			FooLocalServiceUtil.deleteFoos();

			Company company = CompanyLocalServiceUtil.getCompany(companyId);

			User defaultUser = company.getDefaultUser();

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setUserId(defaultUser.getUserId());
			serviceContext.setScopeGroupId(company.getGroupId());
			serviceContext.setCreateDate(DateUtil.newDate());
			serviceContext.setModifiedDate(DateUtil.newDate());

			for (int i = 0; i < 100; i++) {
				FooLocalServiceUtil.addFoo(
					StringUtil.randomString(), true, i, DateUtil.newDate(),
					StringUtil.randomString(), serviceContext);
			}
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn(pe);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(StartupAction.class);

}