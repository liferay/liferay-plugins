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
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;

import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Michael C. Han
 */
public class DDLFormPortletDataHandler extends BasePortletDataHandler {

	public DDLFormPortletDataHandler() {
		setDataLevel(DataLevel.PORTLET_INSTANCE);
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
	protected String doExportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		portletDataContext.addPermissions(
			"com.liferay.portlet.dynamicdatalist",
			portletDataContext.getScopeGroupId());

		long recordSetId = GetterUtil.getLong(
			portletPreferences.getValue("recordSetId", null));

		if (recordSetId == 0) {
			return StringPool.BLANK;
		}

		DDLRecordSet recordSet = DDLRecordSetLocalServiceUtil.getRecordSet(
			recordSetId);

		StagedModelDataHandlerUtil.exportStagedModel(
			portletDataContext, recordSet);

		Element rootElement = addExportDataRootElement(portletDataContext);

		return getExportDataRootElementString(rootElement);
	}

	@Override
	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		portletDataContext.importPermissions(
			"com.liferay.portlet.dynamicdatalist",
			portletDataContext.getSourceGroupId(),
			portletDataContext.getScopeGroupId());

		Element recordSetsElement =
			portletDataContext.getImportDataGroupElement(DDLRecordSet.class);

		List<Element> recordSetElements = recordSetsElement.elements();

		Element recordSetElement = recordSetElements.get(0);

		StagedModelDataHandlerUtil.importStagedModel(
			portletDataContext, recordSetElement);

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

}