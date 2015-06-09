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

import com.liferay.knowledgebase.model.KBComment;
import com.liferay.knowledgebase.service.KBCommentLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.exportimport.lar.BaseStagedModelDataHandler;
import com.liferay.portlet.exportimport.lar.ExportImportPathUtil;
import com.liferay.portlet.exportimport.lar.PortletDataContext;
import com.liferay.portlet.exportimport.lar.StagedModelModifiedDateComparator;

import java.util.List;
import java.util.Map;

/**
 * @author Daniel Kocsis
 */
public class KBCommentStagedModelDataHandler
	extends BaseStagedModelDataHandler<KBComment> {

	public static final String[] CLASS_NAMES = {KBComment.class.getName()};

	@Override
	public void deleteStagedModel(KBComment kbComment) throws PortalException {
		KBCommentLocalServiceUtil.deleteKBComment(kbComment);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		KBComment kbComment = fetchStagedModelByUuidAndGroupId(uuid, groupId);

		if (kbComment != null) {
			deleteStagedModel(kbComment);
		}
	}

	@Override
	public KBComment fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return KBCommentLocalServiceUtil.fetchKBCommentByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public List<KBComment> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return KBCommentLocalServiceUtil.getKBCommentsByUuidAndCompanyId(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new StagedModelModifiedDateComparator<KBComment>());
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(KBComment kbComment) {
		return kbComment.getUuid();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, KBComment kbComment)
		throws Exception {

		Element kbCommentElement = portletDataContext.getExportDataElement(
			kbComment);

		portletDataContext.addClassedModel(
			kbCommentElement, ExportImportPathUtil.getModelPath(kbComment),
			kbComment);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, KBComment kbComment)
		throws Exception {

		long userId = portletDataContext.getUserId(kbComment.getUserUuid());

		Map<Long, Long> relatedClassPKs =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				kbComment.getClassName());

		long newClassPK = MapUtil.getLong(
			relatedClassPKs, kbComment.getClassPK(), kbComment.getClassPK());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			kbComment);

		KBComment importedKBComment = null;

		if (portletDataContext.isDataStrategyMirror()) {
			KBComment existingKBComment = fetchStagedModelByUuidAndGroupId(
				kbComment.getUuid(), portletDataContext.getScopeGroupId());

			if (existingKBComment == null) {
				serviceContext.setUuid(kbComment.getUuid());

				importedKBComment = KBCommentLocalServiceUtil.addKBComment(
					userId, kbComment.getClassNameId(), newClassPK,
					kbComment.getContent(), kbComment.getUserRating(),
					serviceContext);
			}
			else {
				importedKBComment = KBCommentLocalServiceUtil.updateKBComment(
					existingKBComment.getKbCommentId(),
					kbComment.getClassNameId(), newClassPK,
					kbComment.getContent(), kbComment.getUserRating(),
					kbComment.getStatus(), serviceContext);
			}
		}
		else {
			importedKBComment = KBCommentLocalServiceUtil.addKBComment(
				userId, kbComment.getClassNameId(), newClassPK,
				kbComment.getContent(), kbComment.getUserRating(),
				serviceContext);
		}

		portletDataContext.importClassedModel(kbComment, importedKBComment);
	}

}