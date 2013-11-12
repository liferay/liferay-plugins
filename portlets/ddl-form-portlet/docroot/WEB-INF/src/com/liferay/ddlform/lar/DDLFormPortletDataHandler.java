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

package com.liferay.ddlform.lar;

import com.liferay.portal.kernel.lar.BasePortletDataHandler;
import com.liferay.portal.kernel.lar.DataLevel;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataHandlerControl;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;

import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Michael C. Han
 */
public class DDLFormPortletDataHandler extends BasePortletDataHandler {

	public DDLFormPortletDataHandler() {
		setDataLevel(DataLevel.PORTLET_INSTANCE);
		setExportControls(new PortletDataHandlerControl[0]);
	}

	@Override
	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		if (portletPreferences == null) {
			return portletPreferences;
		}

		portletPreferences.setValue("formDDMTemplateId", StringPool.BLANK);
		portletPreferences.setValue(
			"multipleSubmissions", Boolean.FALSE.toString());
		portletPreferences.setValue("recordSetId", StringPool.BLANK);

		return portletPreferences;
	}

	@Override
	protected PortletPreferences doProcessExportPortletPreferences(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		portletDataContext.addPortletPermissions(RESOURCE_NAME);

		long recordSetId = GetterUtil.getLong(
			portletPreferences.getValue("recordSetId", null));

		if (recordSetId == 0) {
			return portletPreferences;
		}

		DDLRecordSet recordSet = DDLRecordSetLocalServiceUtil.getRecordSet(
			recordSetId);

		StagedModelDataHandlerUtil.exportReferenceStagedModel(
			portletDataContext, portletId, recordSet);

		return portletPreferences;
	}

	@Override
	protected PortletPreferences doProcessImportPortletPreferences(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		portletDataContext.importPortletPermissions(RESOURCE_NAME);

		StagedModelDataHandlerUtil.importReferenceStagedModels(
			portletDataContext, DDLRecordSet.class);

		Map<Long, Long> templateIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				DDMTemplate.class);

		long importedFormDDMTemplateId = GetterUtil.getLong(
			portletPreferences.getValue("formDDMTemplateId", null));

		long formDDMTemplateId = MapUtil.getLong(
			templateIds, importedFormDDMTemplateId, importedFormDDMTemplateId);

		portletPreferences.setValue(
			"formDDMTemplateId", String.valueOf(formDDMTemplateId));

		long importedRecordSetId = GetterUtil.getLong(
			portletPreferences.getValue("recordSetId", null));

		Map<Long, Long> recordSetIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				DDLRecordSet.class);

		long recordSetId = MapUtil.getLong(
			recordSetIds, importedRecordSetId, importedRecordSetId);

		portletPreferences.setValue("recordSetId", String.valueOf(recordSetId));

		return portletPreferences;
	}

	protected static final String RESOURCE_NAME =
		"com.liferay.portlet.dynamicdatalist";

}