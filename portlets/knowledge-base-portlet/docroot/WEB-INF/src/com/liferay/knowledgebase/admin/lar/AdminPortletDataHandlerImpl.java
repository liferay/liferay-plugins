/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.knowledgebase.admin.util.AdminUtil;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBArticleConstants;
import com.liferay.knowledgebase.model.KBComment;
import com.liferay.knowledgebase.model.KBTemplate;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.KBCommentLocalServiceUtil;
import com.liferay.knowledgebase.service.KBTemplateLocalServiceUtil;
import com.liferay.knowledgebase.service.persistence.KBArticleUtil;
import com.liferay.knowledgebase.service.persistence.KBCommentUtil;
import com.liferay.knowledgebase.service.persistence.KBTemplateUtil;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.knowledgebase.util.comparator.KBArticleModifiedDateComparator;
import com.liferay.knowledgebase.util.comparator.KBArticlePriorityComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.lar.BasePortletDataHandler;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.portal.kernel.lar.PortletDataHandlerControl;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AdminPortletDataHandlerImpl extends BasePortletDataHandler {

	@Override
	public PortletDataHandlerControl[] getExportControls() {
		return new PortletDataHandlerControl[] {
			_kbArticlesKBTemplatesAndKBComments, _categories, _ratings, _tags
		};
	}

	@Override
	public PortletDataHandlerControl[] getImportControls() {
		return new PortletDataHandlerControl[] {
			_kbArticlesKBTemplatesAndKBComments, _categories, _ratings, _tags
		};
	}

	@Override
	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		if (!portletDataContext.addPrimaryKey(
				AdminPortletDataHandlerImpl.class, "deleteData")) {

			KBArticleLocalServiceUtil.deleteGroupKBArticles(
				portletDataContext.getScopeGroupId());

			KBTemplateLocalServiceUtil.deleteGroupKBTemplates(
				portletDataContext.getScopeGroupId());
		}

		return null;
	}

	@Override
	protected String doExportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		portletDataContext.addPermissions(
			"com.liferay.knowledgebase.admin",
			portletDataContext.getScopeGroupId());

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("knowledge-base-admin-data");

		rootElement.addAttribute(
			"group-id", String.valueOf(portletDataContext.getScopeGroupId()));

		exportKBArticles(portletDataContext, rootElement);
		exportKBTemplates(portletDataContext, rootElement);
		exportKBComments(portletDataContext, rootElement);

		return document.formattedString();
	}

	@Override
	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		portletDataContext.importPermissions(
			"com.liferay.knowledgebase.admin",
			portletDataContext.getSourceGroupId(),
			portletDataContext.getScopeGroupId());

		Document document = SAXReaderUtil.read(data);

		Element rootElement = document.getRootElement();

		importKBArticles(portletDataContext, rootElement);
		importKBTemplates(portletDataContext, rootElement);
		importKBComments(portletDataContext, rootElement);

		return null;
	}

	protected void exportKBArticle(
			PortletDataContext portletDataContext, Element rootElement,
			String path, KBArticle kbArticle)
		throws Exception {

		Element kbArticleElement = rootElement.addElement("kb-article");

		exportKBArticleVersions(
			portletDataContext, kbArticleElement,
			kbArticle.getResourcePrimKey());

		exportKBArticleAttachments(portletDataContext, rootElement, kbArticle);

		portletDataContext.addClassedModel(
			kbArticleElement, path, kbArticle, _NAMESPACE);
	}

	protected void exportKBArticleAttachments(
			PortletDataContext portletDataContext, Element rootElement,
			KBArticle kbArticle)
		throws Exception {

		Element kbArticleAttachmentsElement = rootElement.addElement(
			"kb-article-attachments");

		kbArticleAttachmentsElement.addAttribute(
			"resource-prim-key",
			String.valueOf(kbArticle.getResourcePrimKey()));

		String rootPath =
			getPortletPath(portletDataContext) + "/kbarticles/attachments/" +
				kbArticle.getResourcePrimKey();

		for (String fileName : kbArticle.getAttachmentsFileNames()) {
			String shortFileName = FileUtil.getShortFileName(fileName);

			String path = rootPath + StringPool.SLASH + shortFileName;
			byte[] bytes = DLStoreUtil.getFile(
				kbArticle.getCompanyId(), CompanyConstants.SYSTEM, fileName);

			Element fileElement = kbArticleAttachmentsElement.addElement(
				"file");

			fileElement.addAttribute("path", path);
			fileElement.addAttribute("short-file-name", shortFileName);

			portletDataContext.addZipEntry(path, bytes);
		}
	}

	protected void exportKBArticleVersions(
			PortletDataContext portletDataContext, Element kbArticleElement,
			long resourcePrimKey)
		throws Exception {

		Element versionsElement = kbArticleElement.addElement("versions");

		String rootPath =
			getPortletPath(portletDataContext) + "/kbarticles/versions/" +
				resourcePrimKey;

		List<KBArticle> kbArticles = KBArticleUtil.findByR_S(
			resourcePrimKey, WorkflowConstants.STATUS_APPROVED,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new KBArticleModifiedDateComparator(true));

		for (KBArticle kbArticle : kbArticles) {
			String path =
				rootPath + StringPool.SLASH + kbArticle.getKbArticleId() +
					".xml";

			Element curKBArticleElement = versionsElement.addElement(
				"kb-article");

			curKBArticleElement.addAttribute("path", path);

			portletDataContext.addZipEntry(path, kbArticle);
		}
	}

	protected void exportKBArticles(
			PortletDataContext portletDataContext, Element rootElement)
		throws Exception {

		for (KBArticle kbArticle : getKBArticles(portletDataContext)) {
			if (!portletDataContext.isWithinDateRange(
					kbArticle.getModifiedDate())) {

				continue;
			}

			String path =
				getPortletPath(portletDataContext) + "/kbarticles/" +
					kbArticle.getResourcePrimKey() + ".xml";

			if (!portletDataContext.isPathNotProcessed(path)) {
				continue;
			}

			exportKBArticle(portletDataContext, rootElement, path, kbArticle);
		}
	}

	protected void exportKBComment(
			PortletDataContext portletDataContext, Element rootElement,
			String path, KBComment kbComment)
		throws Exception {

		Element kbCommentElement = rootElement.addElement("kb-comment");

		portletDataContext.addClassedModel(
			kbCommentElement, path, kbComment, _NAMESPACE);
	}

	protected void exportKBComments(
			PortletDataContext portletDataContext, Element rootElement)
		throws Exception {

		List<KBComment> kbComments = KBCommentUtil.findByGroupId(
			portletDataContext.getScopeGroupId());

		for (KBComment kbComment : kbComments) {
			if (!portletDataContext.isWithinDateRange(
					kbComment.getModifiedDate())) {

				continue;
			}

			String path =
				getPortletPath(portletDataContext) + "/kbcomments/" +
					kbComment.getKbCommentId() + ".xml";

			if (!portletDataContext.isPathNotProcessed(path)) {
				continue;
			}

			exportKBComment(portletDataContext, rootElement, path, kbComment);
		}
	}

	protected void exportKBTemplate(
			PortletDataContext portletDataContext, Element rootElement,
			String path, KBTemplate kbTemplate)
		throws Exception {

		Element kbTemplateElement = rootElement.addElement("kb-template");

		portletDataContext.addClassedModel(
			kbTemplateElement, path, kbTemplate, _NAMESPACE);
	}

	protected void exportKBTemplates(
			PortletDataContext portletDataContext, Element rootElement)
		throws Exception {

		List<KBTemplate> kbTemplates = KBTemplateUtil.findByGroupId(
			portletDataContext.getScopeGroupId());

		for (KBTemplate kbTemplate : kbTemplates) {
			if (!portletDataContext.isWithinDateRange(
					kbTemplate.getModifiedDate())) {

				continue;
			}

			String path =
				getPortletPath(portletDataContext) + "/kbtemplates/" +
					kbTemplate.getKbTemplateId() + ".xml";

			if (!portletDataContext.isPathNotProcessed(path)) {
				continue;
			}

			exportKBTemplate(portletDataContext, rootElement, path, kbTemplate);
		}
	}

	protected List<KBArticle> getKBArticles(
			PortletDataContext portletDataContext)
		throws Exception {

		// Order kbArticles by depth and sort siblings by priority to retain the
		// priority value on import. See KBArticleLocalServiceImpl#getPriority.

		List<KBArticle> kbArticles = new ArrayList<KBArticle>();

		List<KBArticle> siblingKBArticles = new ArrayList<KBArticle>();

		Long[][] params = new Long[][] {
			new Long[] {KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY}
		};

		while ((params = KnowledgeBaseUtil.getParams(params[0])) != null) {
			List<KBArticle> curKBArticles = KBArticleUtil.findByG_P_M(
				portletDataContext.getScopeGroupId(),
				ArrayUtil.toArray(params[1]), true, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, new KBArticlePriorityComparator(true));

			siblingKBArticles.addAll(curKBArticles);

			if (params[0].length > 0) {
				continue;
			}

			long[] siblingKBArticlesResourcePrimKeys = StringUtil.split(
				ListUtil.toString(siblingKBArticles, "resourcePrimKey"), 0L);

			params[0] = ArrayUtil.toArray(siblingKBArticlesResourcePrimKeys);

			kbArticles.addAll(siblingKBArticles);
			siblingKBArticles.clear();
		}

		return kbArticles;
	}

	protected String getPortletPath(PortletDataContext portletDataContext) {
		return portletDataContext.getPortletPath(
			PortletKeys.KNOWLEDGE_BASE_ADMIN);
	}

	protected void importKBArticle(
			PortletDataContext portletDataContext, Map<String, String> dirNames,
			Element kbArticleElement, KBArticle kbArticle)
		throws Exception {

		Map<Long, Long> kbArticlePKs =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				KBArticle.class);

		long userId = portletDataContext.getUserId(kbArticle.getUserUuid());
		long parentResourcePrimKey = MapUtil.getLong(
			kbArticlePKs, kbArticle.getParentResourcePrimKey());
		String[] sections = AdminUtil.unescapeSections(kbArticle.getSections());
		String dirName = MapUtil.getString(
			dirNames, String.valueOf(kbArticle.getResourcePrimKey()));

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			kbArticleElement, kbArticle, _NAMESPACE);

		KBArticle importedKBArticle = null;

		if (portletDataContext.isDataStrategyMirror()) {
			KBArticle existingKBArticle = KBArticleUtil.fetchByUUID_G(
				kbArticle.getUuid(), portletDataContext.getScopeGroupId());

			if (existingKBArticle == null) {
				importedKBArticle = importKBArticleVersions(
					portletDataContext, kbArticle.getUuid(),
					parentResourcePrimKey, dirName, kbArticleElement);
			}
			else {
				KBArticleLocalServiceUtil.updateKBArticle(
					userId, existingKBArticle.getResourcePrimKey(),
					kbArticle.getTitle(), kbArticle.getContent(),
					kbArticle.getDescription(), sections, dirName,
					serviceContext);

				KBArticleLocalServiceUtil.moveKBArticle(
					userId, existingKBArticle.getResourcePrimKey(),
					parentResourcePrimKey, kbArticle.getPriority());

				importedKBArticle =
					KBArticleLocalServiceUtil.getLatestKBArticle(
						existingKBArticle.getResourcePrimKey(),
						WorkflowConstants.STATUS_APPROVED);
			}
		}
		else {
			importedKBArticle = importKBArticleVersions(
				portletDataContext, null, parentResourcePrimKey, dirName,
				kbArticleElement);
		}

		portletDataContext.importClassedModel(
			kbArticle, importedKBArticle, _NAMESPACE);
	}

	protected void importKBArticleAttachments(
			PortletDataContext portletDataContext, long importId,
			Map<String, String> dirNames, Element rootElement)
		throws Exception {

		List<Element> kbArticleAttachmentsElements = rootElement.elements(
			"kb-article-attachments");

		for (Element kbArticleAttachmentsElement :
				kbArticleAttachmentsElements) {

			String resourcePrimKey = kbArticleAttachmentsElement.attributeValue(
				"resource-prim-key");

			String dirName =
				"knowledgebase/temp/import/" + importId + StringPool.SLASH +
					resourcePrimKey;

			DLStoreUtil.addDirectory(
				portletDataContext.getCompanyId(), CompanyConstants.SYSTEM,
				dirName);

			List<Element> fileElements = kbArticleAttachmentsElement.elements(
				"file");

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(portletDataContext.getCompanyId());
			serviceContext.setScopeGroupId(
				portletDataContext.getScopeGroupId());

			for (Element fileElement : fileElements) {
				String shortFileName = fileElement.attributeValue(
					"short-file-name");

				String fileName = dirName + StringPool.SLASH + shortFileName;
				byte[] bytes = portletDataContext.getZipEntryAsByteArray(
					fileElement.attributeValue("path"));

				DLStoreUtil.addFile(
					portletDataContext.getCompanyId(),
					CompanyConstants.SYSTEM_STRING,
					GroupConstants.DEFAULT_PARENT_GROUP_ID,
					CompanyConstants.SYSTEM, fileName, serviceContext, bytes);
			}

			dirNames.put(resourcePrimKey, dirName);
		}
	}

	protected KBArticle importKBArticleVersions(
			PortletDataContext portletDataContext, String uuid,
			long parentResourcePrimKey, String dirName,
			Element kbArticleElement)
		throws Exception {

		Element versionsElement = kbArticleElement.element("versions");

		List<Element> kbArticleElements = versionsElement.elements(
			"kb-article");

		KBArticle importedKBArticle = null;

		for (Element curKBArticleElement : kbArticleElements) {
			KBArticle curKBArticle =
				(KBArticle)portletDataContext.getZipEntryAsObject(
					curKBArticleElement.attributeValue("path"));

			long curUserId = portletDataContext.getUserId(
				curKBArticle.getUserUuid());
			String[] curSections = AdminUtil.unescapeSections(
				curKBArticle.getSections());
			String curDirName = StringPool.BLANK;

			if (curKBArticle.isMain()) {
				curDirName = dirName;
			}

			ServiceContext serviceContext =
				portletDataContext.createServiceContext(
					curKBArticleElement, curKBArticle, _NAMESPACE);

			if (importedKBArticle == null) {
				serviceContext.setUuid(uuid);

				importedKBArticle = KBArticleLocalServiceUtil.addKBArticle(
					curUserId, parentResourcePrimKey, curKBArticle.getTitle(),
					curKBArticle.getContent(), curKBArticle.getDescription(),
					curSections, curDirName, serviceContext);
			}
			else {
				importedKBArticle = KBArticleLocalServiceUtil.updateKBArticle(
					curUserId, importedKBArticle.getResourcePrimKey(),
					curKBArticle.getTitle(), curKBArticle.getContent(),
					curKBArticle.getDescription(), curSections, curDirName,
					serviceContext);
			}
		}

		return importedKBArticle;
	}

	protected void importKBArticles(
			PortletDataContext portletDataContext, Element rootElement)
		throws Exception {

		long importId = CounterLocalServiceUtil.increment();

		Map<String, String> dirNames = new HashMap<String, String>();

		try {
			DLStoreUtil.addDirectory(
				portletDataContext.getCompanyId(), CompanyConstants.SYSTEM,
				"knowledgebase/temp/import/" + importId);

			importKBArticleAttachments(
				portletDataContext, importId, dirNames, rootElement);

			List<Element> kbArticleElements = rootElement.elements(
				"kb-article");

			for (Element kbArticleElement : kbArticleElements) {
				String path = kbArticleElement.attributeValue("path");

				if (!portletDataContext.isPathNotProcessed(path)) {
					continue;
				}

				KBArticle kbArticle =
					(KBArticle)portletDataContext.getZipEntryAsObject(path);

				importKBArticle(
					portletDataContext, dirNames, kbArticleElement, kbArticle);
			}
		}
		finally {
			DLStoreUtil.deleteDirectory(
				portletDataContext.getCompanyId(),
				CompanyConstants.SYSTEM_STRING, CompanyConstants.SYSTEM,
				"knowledgebase/temp/import/" + importId);
		}
	}

	protected void importKBComment(
			PortletDataContext portletDataContext, Element kbCommentElement,
			KBComment kbComment)
		throws Exception {

		Map<Long, Long> classPKs = new HashMap<Long, Long>();

		for (Class<?> obj : new Class[] {KBArticle.class, KBTemplate.class}) {
			classPKs.putAll(
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(obj));
		}

		long userId = portletDataContext.getUserId(kbComment.getUserUuid());
		long classPK = MapUtil.getLong(classPKs, kbComment.getClassPK());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			kbCommentElement, kbComment, _NAMESPACE);

		if (portletDataContext.isDataStrategyMirror()) {
			KBComment existingKBComment = KBCommentUtil.fetchByUUID_G(
				kbComment.getUuid(), portletDataContext.getScopeGroupId());

			if (existingKBComment == null) {
				serviceContext.setUuid(kbComment.getUuid());

				KBCommentLocalServiceUtil.addKBComment(
					userId, kbComment.getClassNameId(), classPK,
					kbComment.getContent(), kbComment.getHelpful(),
					serviceContext);
			}
			else {
				KBCommentLocalServiceUtil.updateKBComment(
					existingKBComment.getKbCommentId(),
					kbComment.getClassNameId(), classPK, kbComment.getContent(),
					kbComment.getHelpful(), serviceContext);
			}
		}
		else {
			KBCommentLocalServiceUtil.addKBComment(
				userId, kbComment.getClassNameId(), classPK,
				kbComment.getContent(), kbComment.getHelpful(), serviceContext);
		}
	}

	protected void importKBComments(
			PortletDataContext portletDataContext, Element rootElement)
		throws Exception {

		List<Element> kbCommentElements = rootElement.elements("kb-comment");

		for (Element kbCommentElement : kbCommentElements) {
			String path = kbCommentElement.attributeValue("path");

			if (!portletDataContext.isPathNotProcessed(path)) {
				continue;
			}

			KBComment kbComment =
				(KBComment)portletDataContext.getZipEntryAsObject(path);

			importKBComment(portletDataContext, kbCommentElement, kbComment);
		}
	}

	protected void importKBTemplate(
			PortletDataContext portletDataContext, Element kbTemplateElement,
			KBTemplate kbTemplate)
		throws Exception {

		long userId = portletDataContext.getUserId(kbTemplate.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			kbTemplateElement, kbTemplate, _NAMESPACE);

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

		portletDataContext.importClassedModel(
			kbTemplate, importedKBTemplate, _NAMESPACE);
	}

	protected void importKBTemplates(
			PortletDataContext portletDataContext, Element rootElement)
		throws Exception {

		for (Element kbTemplateElement : rootElement.elements("kb-template")) {
			String path = kbTemplateElement.attributeValue("path");

			if (!portletDataContext.isPathNotProcessed(path)) {
				continue;
			}

			KBTemplate kbTemplate =
				(KBTemplate)portletDataContext.getZipEntryAsObject(path);

			importKBTemplate(portletDataContext, kbTemplateElement, kbTemplate);
		}
	}

	private static final String _NAMESPACE = "knowledge_base";

	private static PortletDataHandlerBoolean _categories =
		new PortletDataHandlerBoolean(_NAMESPACE, "categories");

	private static PortletDataHandlerBoolean
		_kbArticlesKBTemplatesAndKBComments = new PortletDataHandlerBoolean(
			_NAMESPACE, "kb-articles-kb-templates-and-kb-comments", true, true);

	private static PortletDataHandlerBoolean _ratings =
		new PortletDataHandlerBoolean(_NAMESPACE, "ratings");

	private static PortletDataHandlerBoolean _tags =
		new PortletDataHandlerBoolean(_NAMESPACE, "tags");

}