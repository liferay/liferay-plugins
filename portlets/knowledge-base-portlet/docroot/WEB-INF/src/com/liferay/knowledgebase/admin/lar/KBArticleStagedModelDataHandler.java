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

import com.liferay.knowledgebase.admin.util.AdminUtil;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBArticleConstants;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.persistence.KBArticleUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.DuplicateFileException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;

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
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException, SystemException {

		KBArticle article =
			KBArticleLocalServiceUtil.fetchKBArticleByUuidAndGroupId(
				uuid, groupId);

		if (article != null) {
			KBArticleLocalServiceUtil.deleteKBArticle(article);
		}
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(KBArticle article) {
		return article.getTitle();
	}

	@Override
	protected boolean countStagedModel(
		PortletDataContext portletDataContext, KBArticle article) {

		return !portletDataContext.isModelCounted(
			KBArticle.class.getName(), article.getResourcePrimKey());
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, KBArticle article)
		throws Exception {

		if (article.getParentResourcePrimKey() !=
				KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) {

			KBArticle parentArticle =
				KBArticleLocalServiceUtil.getLatestKBArticle(
					article.getParentResourcePrimKey(),
					WorkflowConstants.STATUS_APPROVED);

			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, article, parentArticle,
				PortletDataContext.REFERENCE_TYPE_PARENT);
		}

		Element articleElement = portletDataContext.getExportDataElement(
			article);

		exportKBArticleAttachments(portletDataContext, articleElement, article);

		portletDataContext.addClassedModel(
			articleElement, ExportImportPathUtil.getModelPath(article),
			article);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, KBArticle article)
		throws Exception {

		long userId = portletDataContext.getUserId(article.getUserUuid());

		if (article.getParentResourcePrimKey() !=
				KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) {

			StagedModelDataHandlerUtil.importReferenceStagedModels(
				portletDataContext, article, KBArticle.class);
		}

		Map<Long, Long> articleResourcePrimaryKeys =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				KBArticle.class);

		long parentResourcePrimKey = MapUtil.getLong(
			articleResourcePrimaryKeys, article.getParentResourcePrimKey());

		long resourcePrimaryKey = MapUtil.getLong(
			articleResourcePrimaryKeys, article.getResourcePrimKey(), 0);

		String[] sections = AdminUtil.unescapeSections(article.getSections());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			article);

		KBArticle importedArticle = null;

		if (portletDataContext.isDataStrategyMirror()) {
			KBArticle existingKBArticle = KBArticleUtil.fetchByR_V(
				resourcePrimaryKey, article.getVersion());

			if (existingKBArticle == null) {
				existingKBArticle = KBArticleUtil.fetchByUUID_G(
					article.getUuid(), portletDataContext.getScopeGroupId());
			}

			if (existingKBArticle == null) {
				serviceContext.setUuid(article.getUuid());

				existingKBArticle =
					KBArticleLocalServiceUtil.fetchLatestKBArticle(
						resourcePrimaryKey, WorkflowConstants.STATUS_ANY);

				if (existingKBArticle == null) {
					importedArticle = KBArticleLocalServiceUtil.addKBArticle(
						userId, parentResourcePrimKey, article.getTitle(),
						article.getContent(), article.getDescription(),
						sections, StringPool.BLANK, serviceContext);

					KBArticleLocalServiceUtil.updatePriority(
						importedArticle.getResourcePrimKey(),
						article.getPriority());
				}
				else {
					KBArticleLocalServiceUtil.updateKBArticle(
						userId, existingKBArticle.getResourcePrimKey(),
						article.getTitle(), article.getContent(),
						article.getDescription(), sections, StringPool.BLANK,
						serviceContext);

					KBArticleLocalServiceUtil.moveKBArticle(
						userId, existingKBArticle.getResourcePrimKey(),
						parentResourcePrimKey, article.getPriority());

					importedArticle =
						KBArticleLocalServiceUtil.getLatestKBArticle(
							existingKBArticle.getResourcePrimKey(),
							WorkflowConstants.STATUS_APPROVED);
				}
			}
			else {
				importedArticle = existingKBArticle;
			}
		}
		else {
			importedArticle = KBArticleLocalServiceUtil.addKBArticle(
				userId, parentResourcePrimKey, article.getTitle(),
				article.getContent(), article.getDescription(), sections,
				StringPool.BLANK, serviceContext);

			KBArticleLocalServiceUtil.updatePriority(
				importedArticle.getResourcePrimKey(), article.getPriority());
		}

		importKBArticleAttachments(
			portletDataContext, article, importedArticle);

		portletDataContext.importClassedModel(article, importedArticle);

		if (!article.isMain()) {
			articleResourcePrimaryKeys.put(
				article.getResourcePrimKey(),
				importedArticle.getResourcePrimKey());
		}
	}

	protected void exportKBArticleAttachments(
			PortletDataContext portletDataContext, Element articleElement,
			KBArticle article)
		throws Exception {

		String rootPath = ExportImportPathUtil.getModelPath(
			article, String.valueOf(article.getResourcePrimKey()));

		List<FileEntry> attachmentsFileEntries =
			article.getAttachmentsFileEntries();

		for (FileEntry fileEntry : attachmentsFileEntries) {
			String path = rootPath + StringPool.SLASH + fileEntry.getTitle();

			Element fileElement = portletDataContext.getExportDataElement(
				fileEntry);

			fileElement.addAttribute("path", path);
			fileElement.addAttribute("file-name", fileEntry.getTitle());

			portletDataContext.addZipEntry(path, fileEntry.getContentStream());

			portletDataContext.addReferenceElement(
				article, articleElement, fileEntry,
				PortletDataContext.REFERENCE_TYPE_WEAK, false);
		}
	}

	protected void importKBArticleAttachments(
			PortletDataContext portletDataContext, KBArticle article,
			KBArticle importedArticle)
		throws Exception {

		List<Element> fileElements =
			portletDataContext.getReferenceDataElements(
				article, DLFileEntry.class);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(portletDataContext.getCompanyId());
		serviceContext.setScopeGroupId(portletDataContext.getScopeGroupId());

		InputStream inputStream = null;

		for (Element fileElement : fileElements) {
			try {
				inputStream = portletDataContext.getZipEntryAsInputStream(
					fileElement.attributeValue("path"));

				String fileName = fileElement.attributeValue("file-name");

				String mimeType = MimeTypesUtil.getContentType(
					inputStream, fileName);

				PortletFileRepositoryUtil.addPortletFileEntry(
					portletDataContext.getScopeGroupId(),
					portletDataContext.getUserId(importedArticle.getUserUuid()),
					KBArticle.class.getName(), importedArticle.getClassPK(),
					PortletKeys.KNOWLEDGE_BASE_ADMIN,
					importedArticle.getAttachmentsFolderId(), inputStream,
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