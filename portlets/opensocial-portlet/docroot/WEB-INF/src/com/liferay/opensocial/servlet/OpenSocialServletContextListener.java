/**
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

package com.liferay.opensocial.servlet;

import com.liferay.opensocial.service.GadgetLocalServiceUtil;
import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalInitable;
import com.liferay.portal.kernel.util.PortalInitableUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * <a href="OpenSocialServletContextListener.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Michael Young
 */
public class OpenSocialServletContextListener
	implements PortalInitable, ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
		try {
			GadgetLocalServiceUtil.destroyGadgets();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public void contextInitialized(ServletContextEvent event) {
		PortalInitableUtil.init(this);
	}

	public void portalInit() {
		try {
			GadgetLocalServiceUtil.initGadgets();

			checkExpando();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void checkExpando() throws Exception {
		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			try {
				ExpandoTableLocalServiceUtil.getTable(
					company.getCompanyId(), User.class.getName(),
					ShindigUtil.getTableOpenSocial());
			}
			catch (NoSuchTableException nste) {
				ExpandoTableLocalServiceUtil.addTable(
					company.getCompanyId(), User.class.getName(),
					ShindigUtil.getTableOpenSocial());
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		OpenSocialServletContextListener.class);

}