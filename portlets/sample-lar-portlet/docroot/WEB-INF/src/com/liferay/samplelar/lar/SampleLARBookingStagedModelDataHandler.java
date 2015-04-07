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

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.service.ServiceContext;
import com.liferay.samplelar.model.SampleLARBooking;
import com.liferay.samplelar.service.SampleLARBookingLocalServiceUtil;

import java.util.List;

/**
 * @author Mate Thurzo
 */
public class SampleLARBookingStagedModelDataHandler
	extends BaseStagedModelDataHandler<SampleLARBooking> {

	public static final String[] CLASS_NAMES =
		{SampleLARBooking.class.getName()};

	@Override
	public void deleteStagedModel(
		String uuid, long groupId, String className, String extraData) {

		SampleLARBooking sampleLARBooking = fetchStagedModelByUuidAndGroupId(
			uuid, groupId);

		if (sampleLARBooking != null) {
			SampleLARBookingLocalServiceUtil.deleteSampleLARBooking(
				sampleLARBooking);
		}
	}

	@Override
	public SampleLARBooking fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return SampleLARBookingLocalServiceUtil.
			fetchSampleLARBookingByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public List<SampleLARBooking> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return SampleLARBookingLocalServiceUtil.
			getSampleLARBookingsByUuidAndCompanyId(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new StagedModelModifiedDateComparator<SampleLARBooking>());
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(SampleLARBooking sampleLARBooking) {
		return sampleLARBooking.getBookingNumber();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext,
			SampleLARBooking sampleLARBooking)
		throws Exception {

		Element sampleLARBookingElement =
			portletDataContext.getExportDataElement(sampleLARBooking);

		portletDataContext.addClassedModel(
			sampleLARBookingElement,
			ExportImportPathUtil.getModelPath(sampleLARBooking),
			sampleLARBooking);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext,
			SampleLARBooking sampleLARBooking)
		throws Exception {

		long userId = portletDataContext.getUserId(
			sampleLARBooking.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			sampleLARBooking);

		SampleLARBooking importedSampleLARBooking = null;

		if (portletDataContext.isDataStrategyMirror()) {
			SampleLARBooking existingSampleLARBooking =
				fetchStagedModelByUuidAndGroupId(
					sampleLARBooking.getUuid(),
					portletDataContext.getScopeGroupId());

			if (existingSampleLARBooking == null) {
				serviceContext.setUuid(sampleLARBooking.getUuid());

				importedSampleLARBooking =
					SampleLARBookingLocalServiceUtil.addSampleLARBooking(
						userId, portletDataContext.getScopeGroupId(),
						sampleLARBooking.getBookingNumber(), serviceContext);
			}
			else {
				importedSampleLARBooking =
					SampleLARBookingLocalServiceUtil.updateSampleLARBooking(
						userId,
						existingSampleLARBooking.getSampleLARBookingId(),
						sampleLARBooking.getBookingNumber(), serviceContext);
			}
		}
		else {
			importedSampleLARBooking =
				SampleLARBookingLocalServiceUtil.addSampleLARBooking(
					userId, portletDataContext.getScopeGroupId(),
					sampleLARBooking.getBookingNumber(), serviceContext);
		}

		portletDataContext.importClassedModel(
			sampleLARBooking, importedSampleLARBooking);
	}

}