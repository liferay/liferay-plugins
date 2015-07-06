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

package com.liferay.localization.hook.service.impl;

import com.liferay.localization.util.InstanceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalService;
import com.liferay.portal.service.CompanyLocalServiceWrapper;

/**
 * @author Brian Wing Shun Chan
 */
public class LocalizationCompanyLocalServiceImpl
	extends CompanyLocalServiceWrapper {

	public LocalizationCompanyLocalServiceImpl(
		CompanyLocalService companyLocalService) {

		super(companyLocalService);
	}

	@Override
	public Company checkCompany(String webId, String mx)
		throws PortalException {

		Company company = super.checkCompany(webId, mx);

		InstanceUtil.initInstance(company.getCompanyId());

		return company;
	}

}