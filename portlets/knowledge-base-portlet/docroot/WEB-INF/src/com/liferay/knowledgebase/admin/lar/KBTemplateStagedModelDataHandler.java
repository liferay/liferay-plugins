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

package com.liferay.knowledgebase.admin.lar;

import com.liferay.knowledgebase.model.KBTemplate;
import com.liferay.knowledgebase.service.KBTemplateLocalServiceUtil;
import com.liferay.knowledgebase.service.persistence.KBTemplateUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.service.ServiceContext;

/**
 * @author Daniel Kocsis
 */
public class KBTemplateStagedModelDataHandler
	extends BaseStagedModelDataHandler<KBTemplate> {

	public static final String[] CLASS_NAMES = {KBTemplate.class.getName()};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException, SystemException {

		KBTemplate kbTemplate =
			KBTemplateLocalServiceUtil.fetchKBTemplateByUuidAndGroupId(
				uuid, groupId);

		if (kbTemplate != null) {
			KBTemplateLocalServiceUtil.deleteKBTemplate(kbTemplate);
		}
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(KBTemplate kbTemplate) {
		return kbTemplate.getTitle();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, KBTemplate kbTemplate)
		throws Exception {

		Element kbTemplateElement = portletDataContext.getExportDataElement(
			kbTemplate);

		portletDataContext.addClassedModel(
			kbTemplateElement, ExportImportPathUtil.getModelPath(kbTemplate),
			kbTemplate);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, KBTemplate kbTemplate)
		throws Exception {

		long userId = portletDataContext.getUserId(kbTemplate.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			kbTemplate);

		KBTemplate importedKBTemplate = null;

		if (portletDataContext.isDataStrategyMirror()) {
			KBTemplate existingKBTemplate = KBTemplateUtil.fetchByUUID_G(
				kbTemplate.getUuid(), portletDataContext.getScopeGroupId());

			if (existingKBTemplate == null) {
				serviceContext.setUuid(kbTemplate.getUuid());

				importedKBTemplate = KBTemplateLocalServiceUtil.addKBTemplate(
					userId, kbTemplate.getTitle(), kbTemplate.getContent(),
					serviceContext);
			}
			else {
				importedKBTemplate =
					KBTemplateLocalServiceUtil.updateKBTemplate(
						existingKBTemplate.getKbTemplateId(),
						kbTemplate.getTitle(), kbTemplate.getContent(),
						serviceContext);
			}
		}
		else {
			importedKBTemplate = KBTemplateLocalServiceUtil.addKBTemplate(
				userId, kbTemplate.getTitle(), kbTemplate.getContent(),
				serviceContext);
		}

		portletDataContext.importClassedModel(kbTemplate, importedKBTemplate);
	}

}