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

package com.liferay.opensocial.messaging;

import com.liferay.expando.kernel.exception.NoSuchTableException;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.service.GadgetLocalServiceUtil;
import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.messaging.HotDeployMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

/**
 * @author Michael Young
 */
public class OpenSocialHotDeployMessageListener
	extends HotDeployMessageListener {

	public OpenSocialHotDeployMessageListener(String... servletContextNames) {
		super(servletContextNames);
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
	protected void onDeploy(Message message) throws Exception {
		verifyGadgets();

		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			PortletLocalServiceUtil.addPortletCategory(
				company.getCompanyId(), _GADGETS_CATEGORY);
		}

		GadgetLocalServiceUtil.initGadgets();

		checkExpando();
	}

	@Override
	protected void onUndeploy(Message message) throws Exception {
		GadgetLocalServiceUtil.destroyGadgets();
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