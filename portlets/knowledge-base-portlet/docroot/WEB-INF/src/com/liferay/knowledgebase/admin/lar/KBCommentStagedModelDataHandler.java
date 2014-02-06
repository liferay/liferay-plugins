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

package com.liferay.knowledgebase.admin.lar;

import com.liferay.knowledgebase.model.KBComment;
import com.liferay.knowledgebase.service.KBCommentLocalServiceUtil;
import com.liferay.knowledgebase.service.persistence.KBCommentUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.service.ServiceContext;

import java.util.Map;

/**
 * @author Daniel Kocsis
 */
public class KBCommentStagedModelDataHandler
	extends BaseStagedModelDataHandler<KBComment> {

	public static final String[] CLASS_NAMES = {KBComment.class.getName()};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException, SystemException {

		KBComment comment =
			KBCommentLocalServiceUtil.fetchKBCommentByUuidAndGroupId(
				uuid, groupId);

		if (comment != null) {
			KBCommentLocalServiceUtil.deleteKBComment(comment);
		}
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(KBComment comment) {
		return comment.getUuid();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, KBComment comment)
		throws Exception {

		Element commentElement = portletDataContext.getExportDataElement(
			comment);

		portletDataContext.addClassedModel(
			commentElement, ExportImportPathUtil.getModelPath(comment),
			comment);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, KBComment comment)
		throws Exception {

		long userId = portletDataContext.getUserId(comment.getUserUuid());

		Map<Long, Long> relatedEntityIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				comment.getClassName());

		long newClassPK = MapUtil.getLong(
			relatedEntityIds, comment.getClassPK(), comment.getClassPK());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			comment);

		KBComment importedComment = null;

		if (portletDataContext.isDataStrategyMirror()) {
			KBComment existingKBComment = KBCommentUtil.fetchByUUID_G(
				comment.getUuid(), portletDataContext.getScopeGroupId());

			if (existingKBComment == null) {
				serviceContext.setUuid(comment.getUuid());

				importedComment = KBCommentLocalServiceUtil.addKBComment(
					userId, comment.getClassNameId(), newClassPK,
					comment.getContent(), comment.getHelpful(), serviceContext);
			}
			else {
				importedComment = KBCommentLocalServiceUtil.updateKBComment(
					existingKBComment.getKbCommentId(),
					comment.getClassNameId(), newClassPK, comment.getContent(),
					comment.getHelpful(), serviceContext);
			}
		}
		else {
			importedComment = KBCommentLocalServiceUtil.addKBComment(
				userId, comment.getClassNameId(), newClassPK,
				comment.getContent(), comment.getHelpful(), serviceContext);
		}

		portletDataContext.importClassedModel(comment, importedComment);
	}

}