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

package com.liferay.opensocial.admin.lar;

import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.service.GadgetLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;

/**
 * @author Michael C. Han
 */
public class GadgetStagedModelDataHandler
	extends BaseStagedModelDataHandler<Gadget> {

	public static final String[] CLASS_NAMES = {Gadget.class.getName()};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		Gadget gadget = GadgetLocalServiceUtil.fetchGadgetByUuidAndCompanyId(
			uuid, group.getCompanyId());

		if (gadget != null) {
			GadgetLocalServiceUtil.deleteGadget(gadget);
		}
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(Gadget gadget) {
		return gadget.getName();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, Gadget gadget)
		throws Exception {

		Element gadgetElement = portletDataContext.getExportDataElement(gadget);

		portletDataContext.addClassedModel(
			gadgetElement, ExportImportPathUtil.getModelPath(gadget), gadget);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, Gadget gadget)
		throws Exception {

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			gadget);

		Gadget importedGadget = null;

		if (portletDataContext.isDataStrategyMirror()) {
			Gadget existingGadget =
				GadgetLocalServiceUtil.fetchGadgetByUuidAndCompanyId(
					gadget.getUuid(), portletDataContext.getCompanyId());

			if (existingGadget == null) {
				serviceContext.setUuid(gadget.getUuid());

				importedGadget = GadgetLocalServiceUtil.addGadget(
					portletDataContext.getCompanyId(), gadget.getUrl(),
					gadget.getPortletCategoryNames(), serviceContext);
			}
			else {
				existingGadget.setName(gadget.getName());
				existingGadget.setUrl(gadget.getUrl());
				existingGadget.setPortletCategoryNames(
					gadget.getPortletCategoryNames());

				importedGadget = GadgetLocalServiceUtil.updateGadget(
					existingGadget);
			}
		}
		else {
			importedGadget = GadgetLocalServiceUtil.addGadget(
				portletDataContext.getCompanyId(), gadget.getUrl(),
				gadget.getPortletCategoryNames(), serviceContext);
		}

		portletDataContext.importClassedModel(gadget, importedGadget);
	}

}