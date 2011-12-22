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

package com.liferay.opensocial.servlet;

import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.service.GadgetLocalServiceUtil;
import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Michael Young
 */
public class OpenSocialServletContextListener
	extends BasePortalLifecycle implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		portalDestroy();
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		registerPortalLifecycle();
	}

	protected void checkExpando() throws Exception {
		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			try {
				ExpandoTableLocalServiceUtil.getTable(
					company.getCompanyId(), Layout.class.getName(),
					ShindigUtil.getTableOpenSocial());
			}
			catch (NoSuchTableException nste) {
				ExpandoTableLocalServiceUtil.addTable(
					company.getCompanyId(), Layout.class.getName(),
					ShindigUtil.getTableOpenSocial());
			}
		}
	}

	@Override
	protected void doPortalDestroy() throws Exception {
		GadgetLocalServiceUtil.destroyGadgets();
	}

	@Override
	protected void doPortalInit() throws Exception {
		verifyGadgets();

		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			PortletLocalServiceUtil.addPortletCategory(
				company.getCompanyId(), _GADGETS_CATEGORY);
		}

		GadgetLocalServiceUtil.initGadgets();

		checkExpando();
	}

	protected void verifyGadgets() throws Exception {
		List<Gadget> gadgets = GadgetLocalServiceUtil.getGadgets(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Gadget gadget : gadgets) {
			if (Validator.isNull(gadget.getUuid()) ||
				Validator.isNull(gadget.getPortletCategoryNames())) {

				gadget.setPortletCategoryNames(_GADGETS_CATEGORY);

				GadgetLocalServiceUtil.updateGadget(gadget);
			}
		}
	}

	private static final String _GADGETS_CATEGORY = "category.gadgets";

}