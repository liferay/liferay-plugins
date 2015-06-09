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

import com.liferay.knowledgebase.admin.util.AdminUtil;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBArticleConstants;
import com.liferay.knowledgebase.model.KBFolder;
import com.liferay.knowledgebase.model.KBFolderConstants;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.KBFolderLocalServiceUtil;
import com.liferay.knowledgebase.service.persistence.KBArticleUtil;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.DuplicateFileException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.exportimport.lar.BaseStagedModelDataHandler;
import com.liferay.portlet.exportimport.lar.ExportImportPathUtil;
import com.liferay.portlet.exportimport.lar.PortletDataContext;
import com.liferay.portlet.exportimport.lar.StagedModelDataHandlerUtil;
import com.liferay.portlet.exportimport.lar.StagedModelModifiedDateComparator;

import java.io.InputStream;

import java.util.List;
import java.util.Map;

/**
 * @author Daniel Kocsis
 */
public class KBArticleStagedModelDataHandler
	extends BaseStagedModelDataHandler<KBArticle> {

	public static final String[] CLASS_NAMES = {KBArticle.class.getName()};

	@Override
	public void deleteStagedModel(KBArticle kbArticle) throws PortalException {
		KBArticleLocalServiceUtil.deleteKBArticle(kbArticle);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		KBArticle kbArticle = fetchStagedModelByUuidAndGroupId(uuid, groupId);

		if (kbArticle != null) {
			deleteStagedModel(kbArticle);
		}
	}

	@Override
	public KBArticle fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return KBArticleLocalServiceUtil.fetchKBArticleByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public List<KBArticle> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return KBArticleLocalServiceUtil.getKBArticlesByUuidAndCompanyId(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new StagedModelModifiedDateComparator<KBArticle>());
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(KBArticle kbArticle) {
		return kbArticle.getTitle();
	}

	@Override
	protected boolean countStagedModel(
		PortletDataContext portletDataContext, KBArticle kbArticle) {

		return !portletDataContext.isModelCounted(
			KBArticle.class.getName(), kbArticle.getResourcePrimKey());
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, KBArticle kbArticle)
		throws Exception {

		if (kbArticle.getParentResourcePrimKey() !=
				KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			long kbArticleClassNameId = PortalUtil.getClassNameId(
				KBArticleConstants.getClassName());

			if (kbArticle.getParentResourceClassNameId() ==
					kbArticleClassNameId) {

				KBArticle parentKBArticle =
					KBArticleLocalServiceUtil.getLatestKBArticle(
						kbArticle.getParentResourcePrimKey(),
						WorkflowConstants.STATUS_APPROVED);

				StagedModelDataHandlerUtil.exportReferenceStagedModel(
					portletDataContext, kbArticle, parentKBArticle,
					PortletDataContext.REFERENCE_TYPE_PARENT);
			}
			else {
				KBFolder parentKBFolder = KBFolderLocalServiceUtil.getKBFolder(
					kbArticle.getParentResourcePrimKey());

				StagedModelDataHandlerUtil.exportReferenceStagedModel(
					portletDataContext, kbArticle, parentKBFolder,
					PortletDataContext.REFERENCE_TYPE_PARENT);
			}
		}

		Element kbArticleElement = portletDataContext.getExportDataElement(
			kbArticle);

		exportKBArticleAttachments(
			portletDataContext, kbArticleElement, kbArticle);

		portletDataContext.addClassedModel(
			kbArticleElement, ExportImportPathUtil.getModelPath(kbArticle),
			kbArticle);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, KBArticle kbArticle)
		throws Exception {

		long userId = portletDataContext.getUserId(kbArticle.getUserUuid());

		if (kbArticle.getParentResourcePrimKey() !=
				KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			if (kbArticle.getClassNameId() ==
					kbArticle.getParentResourceClassNameId()) {

				StagedModelDataHandlerUtil.importReferenceStagedModels(
					portletDataContext, kbArticle, KBArticle.class);
			}
			else {
				StagedModelDataHandlerUtil.importReferenceStagedModels(
					portletDataContext, kbArticle, KBFolder.class);
			}
		}

		Map<Long, Long> kbArticleResourcePrimKeys =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				KBArticle.class);

		long parentResourcePrimKey = MapUtil.getLong(
			kbArticleResourcePrimKeys, kbArticle.getParentResourcePrimKey(),
			KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		long resourcePrimaryKey = MapUtil.getLong(
			kbArticleResourcePrimKeys, kbArticle.getResourcePrimKey(), 0);

		if (parentResourcePrimKey ==
				KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			Map<Long, Long> kbFolderResourcePrimKeys =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
					KBFolder.class);

			parentResourcePrimKey = MapUtil.getLong(
				kbFolderResourcePrimKeys, kbArticle.getParentResourcePrimKey(),
				KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);
		}

		String[] sections = AdminUtil.unescapeSections(kbArticle.getSections());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			kbArticle);

		KBArticle importedKBArticle = null;

		if (portletDataContext.isDataStrategyMirror()) {
			KBArticle existingKBArticle = KBArticleUtil.fetchByR_V(
				resourcePrimaryKey, kbArticle.getVersion());

			if (existingKBArticle == null) {
				existingKBArticle = fetchStagedModelByUuidAndGroupId(
					kbArticle.getUuid(), portletDataContext.getScopeGroupId());
			}

			if (existingKBArticle == null) {
				serviceContext.setUuid(kbArticle.getUuid());

				existingKBArticle =
					KBArticleLocalServiceUtil.fetchLatestKBArticle(
						resourcePrimaryKey, WorkflowConstants.STATUS_ANY);

				if (existingKBArticle == null) {
					importedKBArticle = KBArticleLocalServiceUtil.addKBArticle(
						userId, kbArticle.getParentResourceClassNameId(),
						parentResourcePrimKey, kbArticle.getTitle(),
						kbArticle.getUrlTitle(), kbArticle.getContent(),
						kbArticle.getDescription(), kbArticle.getSourceURL(),
						sections, null, serviceContext);

					KBArticleLocalServiceUtil.updatePriority(
						importedKBArticle.getResourcePrimKey(),
						kbArticle.getPriority());
				}
				else {
					KBArticleLocalServiceUtil.updateKBArticle(
						userId, existingKBArticle.getResourcePrimKey(),
						kbArticle.getTitle(), kbArticle.getContent(),
						kbArticle.getDescription(), kbArticle.getSourceURL(),
						sections, null, null, serviceContext);

					KBArticleLocalServiceUtil.moveKBArticle(
						userId, existingKBArticle.getResourcePrimKey(),
						existingKBArticle.getParentResourceClassNameId(),
						parentResourcePrimKey, kbArticle.getPriority());

					importedKBArticle =
						KBArticleLocalServiceUtil.getLatestKBArticle(
							existingKBArticle.getResourcePrimKey(),
							WorkflowConstants.STATUS_APPROVED);
				}
			}
			else {
				importedKBArticle = existingKBArticle;
			}
		}
		else {
			importedKBArticle = KBArticleLocalServiceUtil.addKBArticle(
				userId, kbArticle.getParentResourceClassNameId(),
				parentResourcePrimKey, kbArticle.getTitle(),
				kbArticle.getUrlTitle(), kbArticle.getContent(),
				kbArticle.getDescription(), kbArticle.getSourceURL(), sections,
				null, serviceContext);

			KBArticleLocalServiceUtil.updatePriority(
				importedKBArticle.getResourcePrimKey(),
				kbArticle.getPriority());
		}

		importKBArticleAttachments(
			portletDataContext, kbArticle, importedKBArticle);

		portletDataContext.importClassedModel(kbArticle, importedKBArticle);

		if (!kbArticle.isMain()) {
			kbArticleResourcePrimKeys.put(
				kbArticle.getResourcePrimKey(),
				importedKBArticle.getResourcePrimKey());
		}
	}

	protected void exportKBArticleAttachments(
			PortletDataContext portletDataContext, Element kbArticleElement,
			KBArticle kbArticle)
		throws Exception {

		List<FileEntry> attachmentsFileEntries =
			kbArticle.getAttachmentsFileEntries();

		for (FileEntry fileEntry : attachmentsFileEntries) {
			String path = ExportImportPathUtil.getModelPath(
				kbArticle, fileEntry.getTitle());

			Element fileEntryElement = portletDataContext.getExportDataElement(
				fileEntry);

			fileEntryElement.addAttribute("path", path);
			fileEntryElement.addAttribute("file-name", fileEntry.getTitle());

			portletDataContext.addZipEntry(path, fileEntry.getContentStream());

			portletDataContext.addReferenceElement(
				kbArticle, kbArticleElement, fileEntry,
				PortletDataContext.REFERENCE_TYPE_WEAK, false);
		}
	}

	protected void importKBArticleAttachments(
			PortletDataContext portletDataContext, KBArticle kbArticle,
			KBArticle importedKBArticle)
		throws Exception {

		List<Element> dlFileEntryElements =
			portletDataContext.getReferenceDataElements(
				kbArticle, DLFileEntry.class);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(portletDataContext.getCompanyId());
		serviceContext.setScopeGroupId(portletDataContext.getScopeGroupId());

		InputStream inputStream = null;

		for (Element dlFileEntryElement : dlFileEntryElements) {
			try {
				byte[] bytes = portletDataContext.getZipEntryAsByteArray(
					dlFileEntryElement.attributeValue("path"));

				inputStream = new UnsyncByteArrayInputStream(bytes);

				String fileName = dlFileEntryElement.attributeValue(
					"file-name");

				String mimeType = KnowledgeBaseUtil.getMimeType(
					bytes, fileName);

				PortletFileRepositoryUtil.addPortletFileEntry(
					portletDataContext.getScopeGroupId(),
					portletDataContext.getUserId(
						importedKBArticle.getUserUuid()),
					KBArticle.class.getName(), importedKBArticle.getClassPK(),
					PortletKeys.KNOWLEDGE_BASE_ADMIN,
					importedKBArticle.getAttachmentsFolderId(), inputStream,
					fileName, mimeType, true);
			}
			catch (DuplicateFileException dfe) {
				continue;
			}
			finally {
				StreamUtil.cleanUp(inputStream);
			}
		}
	}

}