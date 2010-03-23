/*
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.manager;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Company;

/**
 * <a href="KaleoCompanyModelListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class KaleoCompanyModelListener extends BaseModelListener<Company> {

	public void onAfterCreate(Company companyModel)
		throws ModelListenerException {

		try {
			PortalKaleoManager portalKaleoManager =
				PortalKaleoManagerUtil.getPortalKaleoManager();

			portalKaleoManager.deployKaleoDefaults();
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to deploy default Kaleo definitions.  " +
					"Please attempt to manually deploy", e);
			}
		}

	}

	public void onAfterRemove(Company companyModel)
		throws ModelListenerException {

		try {
			PortalKaleoManager portalKaleoManager =
				PortalKaleoManagerUtil.getPortalKaleoManager();

			portalKaleoManager.deleteKaleoData(companyModel);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to delete Kaleo data.  " +
					"Please attempt to manually remove", e);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		KaleoCompanyModelListener.class);

}