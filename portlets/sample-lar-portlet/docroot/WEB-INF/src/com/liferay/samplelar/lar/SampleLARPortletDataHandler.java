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

package com.liferay.samplelar.lar;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portlet.exportimport.lar.BasePortletDataHandler;
import com.liferay.portlet.exportimport.lar.PortletDataContext;
import com.liferay.portlet.exportimport.lar.PortletDataHandlerBoolean;
import com.liferay.portlet.exportimport.lar.StagedModelDataHandlerUtil;
import com.liferay.portlet.exportimport.lar.StagedModelType;
import com.liferay.portlet.exportimport.xstream.XStreamAliasRegistryUtil;
import com.liferay.samplelar.model.SampleLARBooking;
import com.liferay.samplelar.model.impl.SampleLARBookingImpl;
import com.liferay.samplelar.service.SampleLARBookingLocalServiceUtil;

import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * @author Mate Thurzo
 */
public class SampleLARPortletDataHandler extends BasePortletDataHandler {

	public static final String NAMESPACE = "samplelar";

	public SampleLARPortletDataHandler() {
		setDeletionSystemEventStagedModelTypes(
			new StagedModelType(SampleLARBooking.class));
		setExportControls(
			new PortletDataHandlerBoolean(
				NAMESPACE, "bookings", true, false, null,
				SampleLARBooking.class.getName()));
		setImportControls(getExportControls());

		XStreamAliasRegistryUtil.register(
			SampleLARBookingImpl.class, "SampleLARBooking");
	}

	@Override
	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		if (portletDataContext.addPrimaryKey(
				SampleLARPortletDataHandler.class, "deleteData")) {

			return portletPreferences;
		}

		SampleLARBookingLocalServiceUtil.deleteSampleLARBookings(
			portletDataContext.getScopeGroupId());

		return portletPreferences;
	}

	@Override
	protected String doExportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		Element rootElement = addExportDataRootElement(portletDataContext);

		if (!portletDataContext.getBooleanParameter(NAMESPACE, "bookings")) {
			return getExportDataRootElementString(rootElement);
		}

		rootElement.addAttribute(
			"group-id", String.valueOf(portletDataContext.getScopeGroupId()));

		ActionableDynamicQuery sampleLARBookingActionableDynamicQuery =
			SampleLARBookingLocalServiceUtil.getExportActionableDynamicQuery(
				portletDataContext);

		sampleLARBookingActionableDynamicQuery.performActions();

		return getExportDataRootElementString(rootElement);
	}

	@Override
	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		if (!portletDataContext.getBooleanParameter(NAMESPACE, "bookings")) {
			return null;
		}

		Element sampleLARBookingsElement =
			portletDataContext.getImportDataGroupElement(
				SampleLARBooking.class);

		List<Element> sampleLARBookingElements =
			sampleLARBookingsElement.elements();

		for (Element sampleLARBookingElement : sampleLARBookingElements) {
			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, sampleLARBookingElement);
		}

		return null;
	}

	@Override
	protected void doPrepareManifestSummary(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws Exception {

		ActionableDynamicQuery sampleLARBookingActionableDynamicQuery =
			SampleLARBookingLocalServiceUtil.getExportActionableDynamicQuery(
				portletDataContext);

		sampleLARBookingActionableDynamicQuery.performCount();
	}

}