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

package com.liferay.sevencogs.hook.upgrade;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.sevencogs.hook.upgrade.v1_0_0.UpgradeCompany;

import java.util.List;

/**
 * @author Ryan Park
 */
public class UpgradeProcess_1_0_0 extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return 100;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!isFirstRun()) {
			return;
		}

		upgrade(UpgradeCompany.class);
	}

	protected boolean isFirstRun() throws Exception {
		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		if (companies.isEmpty() || companies.size() > 1) {
			return false;
		}

		Company company = companies.get(0);

		long companyId = company.getCompanyId();

		try {

			// If Bruno exists, do not run again

			UserLocalServiceUtil.getUserByScreenName(companyId, "bruno");

			return false;
		}
		catch (NoSuchUserException nsue) {
		}

		return true;
	}

}