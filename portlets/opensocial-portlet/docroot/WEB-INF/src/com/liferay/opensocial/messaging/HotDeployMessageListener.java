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

package com.liferay.opensocial.messaging;

import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.service.GadgetLocalServiceUtil;
import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

import java.util.List;

/**
 * @author Michael Young
 */
public class HotDeployMessageListener extends BaseMessageListener {

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

	@Override
	protected void doReceive(Message message) throws Exception {
		String servletContextName = message.getString("servletContextName");

		if (!servletContextName.equals("opensocial")) {
			return;
		}

		String command = message.getString("command");

		if (command.equals("deploy")) {
			verifyGadgets();

			List<Company> companies = CompanyLocalServiceUtil.getCompanies();

			for (Company company : companies) {
				PortletLocalServiceUtil.addPortletCategory(
					company.getCompanyId(), _GADGETS_CATEGORY);
			}

			GadgetLocalServiceUtil.initGadgets();

			checkExpando();
		}
		else if (command.equals("undeploy")) {
			GadgetLocalServiceUtil.destroyGadgets();
		}
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