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

package com.liferay.opensocial.admin.lar;

import com.liferay.opensocial.NoSuchGadgetException;
import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.service.GadgetLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.lar.BasePortletDataHandler;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * @author Michael C. Han
 */
public class AdminPortletDataHandler extends BasePortletDataHandler {

	public static final String NAMESPACE = "opensocial";

	public AdminPortletDataHandler() {
		setPublishToLiveByDefault(true);
	}

	@Override
	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		List<Gadget> gadgets = GadgetLocalServiceUtil.getGadgets(
			portletDataContext.getCompanyId(), QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);

		for (Gadget gadget : gadgets) {
			GadgetLocalServiceUtil.deleteGadget(gadget);
		}

		return portletPreferences;
	}

	@Override
	protected String doExportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		Element rootElement = addExportDataRootElement(portletDataContext);

		Element gadgetsElement = rootElement.addElement("gadgets");

		List<Gadget> gadgets = GadgetLocalServiceUtil.getGadgets(
			portletDataContext.getCompanyId(), QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);

		for (Gadget gadget : gadgets) {
			exportGadget(portletDataContext, gadgetsElement, gadget);
		}

		return getExportDataRootElementString(rootElement);
	}

	@Override
	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		Element rootElement = portletDataContext.getImportDataRootElement();

		Element gadgetsElement = rootElement.element("gadgets");

		for (Element gadgetElement : gadgetsElement.elements("gadget")) {
			String gadgetPath = gadgetElement.attributeValue("path");

			if (!portletDataContext.isPathNotProcessed(gadgetPath)) {
				continue;
			}

			Gadget gadget = (Gadget)portletDataContext.getZipEntryAsObject(
				gadgetPath);

			importGadget(portletDataContext, gadgetElement, gadget);
		}

		return null;
	}

	protected void exportGadget(
			PortletDataContext portletDataContext, Element gadgetsElement,
			Gadget gadget)
		throws Exception {

		String path = getGadgetPath(portletDataContext, gadget);

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		Element gadgetElement = gadgetsElement.addElement("gadget");

		portletDataContext.addClassedModel(
			gadgetElement, path, gadget, NAMESPACE);
	}

	protected String getGadgetPath(
		PortletDataContext portletDataContext, Gadget gadget) {

		StringBundler sb = new StringBundler(4);

		sb.append(portletDataContext.getPortletPath(_PORTLET_ID));
		sb.append("/gadgets/");
		sb.append(gadget.getUuid());
		sb.append(".xml");

		return sb.toString();
	}

	protected void importGadget(
			PortletDataContext portletDataContext, Element gadgetElement,
			Gadget gadget)
		throws Exception {

		Gadget importedGadget = null;

		try {
			importedGadget = GadgetLocalServiceUtil.getGadget(
				gadget.getUuid(), portletDataContext.getCompanyId());

			importedGadget.setName(gadget.getName());
			importedGadget.setUrl(gadget.getUrl());
			importedGadget.setPortletCategoryNames(
				gadget.getPortletCategoryNames());

			GadgetLocalServiceUtil.updateGadget(importedGadget);
		}
		catch (NoSuchGadgetException nsge) {
			ServiceContext serviceContext =
				portletDataContext.createServiceContext(
					gadgetElement, gadget, NAMESPACE);

			serviceContext.setUuid(gadget.getUuid());

			GadgetLocalServiceUtil.addGadget(
				portletDataContext.getCompanyId(), gadget.getUrl(),
				gadget.getPortletCategoryNames(), serviceContext);
		}
	}

	private static final String _PORTLET_ID = "1_WAR_opensocialportlet";

}