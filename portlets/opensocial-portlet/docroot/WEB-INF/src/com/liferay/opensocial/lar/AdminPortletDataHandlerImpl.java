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

package com.liferay.opensocial.lar;

import com.liferay.opensocial.NoSuchGadgetException;
import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.service.GadgetLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BasePortletDataHandler;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * @author Michael C. Han
 */
public class AdminPortletDataHandlerImpl extends BasePortletDataHandler {

	public boolean isPublishToLiveByDefault() {
		return _PUBLISH_TO_LIVE_BY_DEFAULT;
	}

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

		return null;
	}

	protected String doExportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("opensocial-data");

		Element gadgetsElement = rootElement.addElement("gadgets");

		List<Gadget> gadgets = GadgetLocalServiceUtil.getGadgets(
			portletDataContext.getCompanyId(), QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);

		for (Gadget gadget : gadgets) {
			exportGadget(portletDataContext, gadgetsElement, gadget);
		}

		return document.formattedString();
	}

	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		Document document = SAXReaderUtil.read(data);

		Element rootElement = document.getRootElement();

		Element gadgetsElement = rootElement.element("gadgets");

		for (Element gadgetElement : gadgetsElement.elements("gadget")) {
			String gadgetPath = gadgetElement.attributeValue("path");

			if (!portletDataContext.isPathNotProcessed(gadgetPath)) {
				continue;
			}

			Gadget gadget = (Gadget)portletDataContext.getZipEntryAsObject(
				gadgetPath);

			importGadget(portletDataContext, gadget);
		}

		return null;
	}

	protected void exportGadget(
			PortletDataContext portletDataContext, Element gadgetsElement,
			Gadget gadget)
		throws SystemException {

		String path = getGadgetPath(portletDataContext, gadget);

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		Element gadgetElement = gadgetsElement.addElement("gadget");

		gadgetElement.addAttribute("path",path);

		portletDataContext.addZipEntry(path, gadget);
	}

	protected String getGadgetPath(
		PortletDataContext portletDataContext, Gadget gadget) {

		StringBundler sb = new StringBundler(4);

		sb.append(portletDataContext.getPortletPath(_PORTLET_KEY));
		sb.append("/gadgets/");
		sb.append(gadget.getUuid());
		sb.append(".xml");

		return sb.toString();
	}

	protected void importGadget(
			PortletDataContext portletDataContext, Gadget gadget)
		throws PortalException, SystemException {

		Gadget importedGadget = null;

		try {
			importedGadget = GadgetLocalServiceUtil.getGadget(gadget.getUuid());

			importedGadget.setName(gadget.getName());
			importedGadget.setUrl(gadget.getUrl());

			GadgetLocalServiceUtil.updateGadget(importedGadget, false);
		}
		catch (NoSuchGadgetException nsge) {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setUuid(gadget.getUuid());

			GadgetLocalServiceUtil.addGadget(
				portletDataContext.getCompanyId(), gadget.getName(),
				gadget.getUrl(), serviceContext);
		}
	}

	private static final String _PORTLET_KEY = "1_WAR_opensocialportlet";

	private static final boolean _PUBLISH_TO_LIVE_BY_DEFAULT = true;

}