/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.hook.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.CompanyLocalServiceWrapper;
import com.liferay.so.util.InstanceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class SOCompanyLocalServiceImpl extends CompanyLocalServiceWrapper {

	public SOCompanyLocalServiceImpl(CompanyLocalService companyLocalService) {
		super(companyLocalService);
	}

	@Override
	public Company checkCompany(String webId) throws PortalException {
		Company company = super.checkCompany(webId);

		InstanceUtil.initRuntime(company.getCompanyId());

		if (InstanceUtil.isInitialized(company.getCompanyId())) {
			return company;
		}

		InstanceUtil.initInstance(company.getCompanyId());

		return company;
	}

}