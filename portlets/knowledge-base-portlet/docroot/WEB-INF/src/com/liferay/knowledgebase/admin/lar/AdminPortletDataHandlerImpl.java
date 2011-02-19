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
import com.liferay.documentlibrary.service.DLLocalServiceUtil;
import com.liferay.knowledgebase.admin.util.PriorityHelper;
import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.model.ArticleConstants;
import com.liferay.knowledgebase.model.Comment;
import com.liferay.knowledgebase.model.Template;
import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.CommentLocalServiceUtil;
import com.liferay.knowledgebase.service.TemplateLocalServiceUtil;
import com.liferay.knowledgebase.service.persistence.ArticleUtil;
import com.liferay.knowledgebase.service.persistence.CommentUtil;
import com.liferay.knowledgebase.service.persistence.TemplateUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.knowledgebase.util.comparator.ArticleModifiedDateComparator;
import com.liferay.knowledgebase.util.comparator.ArticlePriorityComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BasePortletDataHandler;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.portal.kernel.lar.PortletDataHandlerControl;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AdminPortletDataHandlerImpl extends BasePortletDataHandler {

	public PortletDataHandlerControl[] getExportControls() {
		return new PortletDataHandlerControl[] {_articles, _templates};
	}

	public PortletDataHandlerControl[] getImportControls() {
		return new PortletDataHandlerControl[] {_articles, _templates};
	}

	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		if (!portletDataContext.addPrimaryKey(
				AdminPortletDataHandlerImpl.class, "deleteData")) {

			ArticleLocalServiceUtil.deleteGroupArticles(
				portletDataContext.getScopeGroupId());

			TemplateLocalServiceUtil.deleteGroupTemplates(
				portletDataContext.getScopeGroupId());
		}

		return null;
	}

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

		exportArticles(portletDataContext, rootElement);

		if (portletDataContext.getBooleanParameter(
				_NAMESPACE_ARTICLE, "comments")) {

			exportComments(
				portletDataContext, Article.class, "article-comment",
				rootElement);
		}

		if (portletDataContext.getBooleanParameter(_NAMESPACE, "templates")) {
			exportTemplates(portletDataContext, rootElement);
		}

		if (portletDataContext.getBooleanParameter(
				_NAMESPACE_TEMPLATE, "comments")) {

			exportComments(
				portletDataContext, Template.class, "template-comment",
				rootElement);
		}

		return document.formattedString();
	}

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

		importArticles(portletDataContext, rootElement);

		if (portletDataContext.getBooleanParameter(_NAMESPACE, "templates")) {
			importTemplates(portletDataContext, rootElement);
		}

		return null;
	}

	protected void exportArticle(
			PortletDataContext portletDataContext, Element rootElement,
			String path, Article article)
		throws PortalException, SystemException {

		Element articleElement = rootElement.addElement("article");

		articleElement.addAttribute("path", path);

		article.setUserUuid(article.getUserUuid());

		portletDataContext.addZipEntry(path, article);

		portletDataContext.addPermissions(
			Article.class, article.getResourcePrimKey());

		exportArticleVersions(
			portletDataContext, articleElement, article.getResourcePrimKey());

		if (portletDataContext.getBooleanParameter(
				_NAMESPACE_ARTICLE, "attachments")) {

			exportArticleAttachments(portletDataContext, rootElement, article);
		}

		if (portletDataContext.getBooleanParameter(
				_NAMESPACE_ARTICLE, "categories")) {

			portletDataContext.addAssetCategories(
				Article.class, article.getResourcePrimKey());
		}

		if (portletDataContext.getBooleanParameter(
				_NAMESPACE_ARTICLE, "tags")) {

			portletDataContext.addAssetTags(
				Article.class, article.getResourcePrimKey());
		}

		if (portletDataContext.getBooleanParameter(
				_NAMESPACE_ARTICLE, "ratings")) {

			portletDataContext.addRatingsEntries(
				Article.class, article.getResourcePrimKey());
		}
	}

	protected void exportArticleAttachments(
			PortletDataContext portletDataContext, Element rootElement,
			Article article)
		throws PortalException, SystemException {

		Element articleAttachmentsElement = rootElement.addElement(
			"article-attachments");

		articleAttachmentsElement.addAttribute(
			"resource-prim-key", String.valueOf(article.getResourcePrimKey()));

		String rootPath =
			getPortletPath(portletDataContext) + "/articles/attachments/" +
				article.getResourcePrimKey();

		for (String fileName : article.getAttachmentsFileNames()) {
			String shortFileName = FileUtil.getShortFileName(fileName);

			String path = rootPath + StringPool.SLASH + shortFileName;
			InputStream inputStream = DLLocalServiceUtil.getFileAsStream(
				article.getCompanyId(), CompanyConstants.SYSTEM, fileName);

			Element fileElement = articleAttachmentsElement.addElement("file");

			fileElement.addAttribute("path", path);
			fileElement.addAttribute("short-file-name", shortFileName);

			portletDataContext.addZipEntry(path, inputStream);
		}
	}

	protected void exportArticleVersions(
			PortletDataContext portletDataContext, Element articleElement,
			long resourcePrimKey)
		throws SystemException {

		Element versionsElement = articleElement.addElement("versions");

		String rootPath =
			getPortletPath(portletDataContext) + "/articles/versions/" +
				resourcePrimKey;

		List<Article> articles = ArticleUtil.findByResourcePrimKey(
			resourcePrimKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new ArticleModifiedDateComparator(true));

		for (Article article : articles) {
			String path =
				rootPath + StringPool.SLASH + article.getArticleId() + ".xml";

			Element curArticleElement = versionsElement.addElement("article");

			curArticleElement.addAttribute("path", path);

			portletDataContext.addZipEntry(path, article);
		}
	}

	protected void exportArticles(
			PortletDataContext portletDataContext, Element rootElement)
		throws PortalException, SystemException {

		for (Article article : filterArticles(portletDataContext)) {
			String path =
				getPortletPath(portletDataContext) + "/articles/" +
					article.getResourcePrimKey() + ".xml";

			if (!portletDataContext.isPathNotProcessed(path)) {
				continue;
			}

			exportArticle(portletDataContext, rootElement, path, article);
		}
	}

	protected void exportComment(
			PortletDataContext portletDataContext, Element rootElement,
			String name, String path, Comment comment)
		throws SystemException {

		Element commentElement = rootElement.addElement(name);

		commentElement.addAttribute("path", path);

		comment.setUserUuid(comment.getUserUuid());

		portletDataContext.addZipEntry(path, comment);
	}

	protected void exportComments(
			PortletDataContext portletDataContext, Class<?> classObj,
			String name, Element rootElement)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(classObj);

		List<Comment> comments = CommentUtil.findByG_C(
			portletDataContext.getScopeGroupId(), classNameId);

		for (Comment comment : comments) {
			if (!portletDataContext.isWithinDateRange(
					comment.getModifiedDate())) {

				return;
			}

			String path =
				getPortletPath(portletDataContext) + "/comments/" +
					comment.getCommentId() + ".xml";

			if (!portletDataContext.isPathNotProcessed(path)) {
				continue;
			}

			exportComment(portletDataContext, rootElement, name, path, comment);
		}
	}

	protected void exportTemplate(
			PortletDataContext portletDataContext, Element rootElement,
			String path, Template template)
		throws PortalException, SystemException {

		Element templateElement = rootElement.addElement("template");

		templateElement.addAttribute("path", path);

		template.setUserUuid(template.getUserUuid());

		portletDataContext.addZipEntry(path, template);

		portletDataContext.addPermissions(
			Template.class, template.getTemplateId());
	}

	protected void exportTemplates(
			PortletDataContext portletDataContext, Element rootElement)
		throws PortalException, SystemException {

		List<Template> templates = TemplateUtil.findByGroupId(
			portletDataContext.getScopeGroupId());

		for (Template template : templates) {
			if (!portletDataContext.isWithinDateRange(
					template.getModifiedDate())) {

				continue;
			}

			String path =
				getPortletPath(portletDataContext) + "/templates/" +
					template.getTemplateId() + ".xml";

			if (!portletDataContext.isPathNotProcessed(path)) {
				continue;
			}

			exportTemplate(portletDataContext, rootElement, path, template);
		}
	}

	protected List<Article> filterArticles(
			PortletDataContext portletDataContext)
		throws SystemException {

		// Sort articles to simplify import code. Order articles by depth and
		// sort siblings by priority. See AdminPortletDataHandler#importArticle.

		List<Article> articles = new ArrayList<Article>();

		List<Article> siblingArticles = new ArrayList<Article>();

		long[] parentResourcePrimKeys = {
			ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY
		};

		while (parentResourcePrimKeys.length > 0) {
			long[] curParentResourcePrimKeys = null;

			if (parentResourcePrimKeys.length > _SQL_DATA_MAX_PARAMETERS) {
				curParentResourcePrimKeys = ArrayUtil.subset(
					parentResourcePrimKeys, 0, _SQL_DATA_MAX_PARAMETERS);

				parentResourcePrimKeys = ArrayUtil.subset(
					parentResourcePrimKeys, _SQL_DATA_MAX_PARAMETERS,
					parentResourcePrimKeys.length);
			}
			else {
				curParentResourcePrimKeys = parentResourcePrimKeys.clone();

				parentResourcePrimKeys = new long[0];
			}

			List<Article> curArticles = ArticleUtil.findByG_P_L_S(
				portletDataContext.getScopeGroupId(), curParentResourcePrimKeys,
				ArticleConstants.LATEST_ANY, WorkflowConstants.STATUS_APPROVED);

			for (Article curArticle : curArticles) {
				if (portletDataContext.isWithinDateRange(
						curArticle.getModifiedDate())) {

					siblingArticles.add(curArticle);
				}
			}

			if (parentResourcePrimKeys.length == 0) {
				Collections.sort(
					siblingArticles, new ArticlePriorityComparator(true));

				articles.addAll(siblingArticles);

				parentResourcePrimKeys = StringUtil.split(
					ListUtil.toString(siblingArticles, "resourcePrimKey"), 0L);

				siblingArticles = new ArrayList<Article>();
			}
		}

		return articles;
	}

	protected String getPortletPath(PortletDataContext portletDataContext) {
		return portletDataContext.getPortletPath(
			PortletKeys.KNOWLEDGE_BASE_ADMIN);
	}

	protected void importArticle(
			PortletDataContext portletDataContext, int humanPriority,
			Map<Long, Long> resourcePrimKeys, Map<String, String> dirNames,
			Element articleElement, Article article)
		throws Exception {

		long userId = portletDataContext.getUserId(article.getUserUuid());
		long parentResourcePrimKey = MapUtil.getLong(
			resourcePrimKeys, article.getParentResourcePrimKey());
		String dirName = MapUtil.getString(
			dirNames, String.valueOf(article.getResourcePrimKey()));

		long[] assetCategoryIds = null;

		if (portletDataContext.getBooleanParameter(
				_NAMESPACE_ARTICLE, "categories")) {

			assetCategoryIds = portletDataContext.getAssetCategoryIds(
				Article.class, article.getResourcePrimKey());
		}

		String[] assetTagNames = null;

		if (portletDataContext.getBooleanParameter(
				_NAMESPACE_ARTICLE, "tags")) {

			assetTagNames = portletDataContext.getAssetTagNames(
				Article.class, article.getResourcePrimKey());
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setAssetCategoryIds(assetCategoryIds);
		serviceContext.setAssetTagNames(assetTagNames);
		serviceContext.setCreateDate(article.getCreateDate());
		serviceContext.setModifiedDate(article.getModifiedDate());
		serviceContext.setScopeGroupId(portletDataContext.getScopeGroupId());

		Article importedArticle = null;

		if (portletDataContext.isDataStrategyMirror()) {
			Article existingArticle = ArticleUtil.fetchByUUID_G(
				article.getUuid(), portletDataContext.getScopeGroupId());

			if (existingArticle == null) {
				importedArticle = importArticleVersions(
					portletDataContext, article.getUuid(),
					parentResourcePrimKey, dirName, assetCategoryIds,
					assetTagNames, articleElement);
			}
			else {
				long priority = PriorityHelper.getPriority(
					existingArticle.getGroupId(),
					existingArticle.getResourcePrimKey(), parentResourcePrimKey,
					humanPriority);

				importedArticle = ArticleLocalServiceUtil.updateArticle(
					userId, existingArticle.getResourcePrimKey(),
					parentResourcePrimKey, article.getTitle(),
					article.getContent(), article.getDescription(), priority,
					dirName, serviceContext);
			}
		}
		else {
			importedArticle = importArticleVersions(
				portletDataContext, null, parentResourcePrimKey, dirName,
				assetCategoryIds, assetTagNames, articleElement);
		}

		resourcePrimKeys.put(
			article.getResourcePrimKey(), importedArticle.getResourcePrimKey());

		portletDataContext.importPermissions(
			Article.class, article.getResourcePrimKey(),
			importedArticle.getResourcePrimKey());

		if (portletDataContext.getBooleanParameter(
				_NAMESPACE_ARTICLE, "ratings")) {

			portletDataContext.importRatingsEntries(
				Article.class, article.getResourcePrimKey(),
				importedArticle.getResourcePrimKey());
		}
	}

	protected void importArticleAttachments(
			PortletDataContext portletDataContext, long importId,
			Map<String, String> dirNames, Element rootElement)
		throws Exception {

		List<Element> articleAttachmentsElements = rootElement.elements(
			"article-attachments");

		for (Element articleAttachmentsElement : articleAttachmentsElements) {
			String resourcePrimKey = articleAttachmentsElement.attributeValue(
				"resource-prim-key");

			String dirName =
				"knowledgebase/temp/import/" + importId + StringPool.SLASH +
					resourcePrimKey;

			DLLocalServiceUtil.addDirectory(
				portletDataContext.getCompanyId(), CompanyConstants.SYSTEM,
				dirName);

			List<Element> fileElements = articleAttachmentsElement.elements(
				"file");

			for (Element fileElement : fileElements) {
				String shortFileName = fileElement.attributeValue(
					"short-file-name");

				String fileName = dirName + StringPool.SLASH + shortFileName;
				InputStream inputStream =
					portletDataContext.getZipEntryAsInputStream(
						fileElement.attributeValue("path"));

				ServiceContext serviceContext = new ServiceContext();

				DLLocalServiceUtil.addFile(
					portletDataContext.getCompanyId(),
					CompanyConstants.SYSTEM_STRING,
					GroupConstants.DEFAULT_PARENT_GROUP_ID,
					CompanyConstants.SYSTEM, fileName, true, 0,
					StringPool.BLANK, serviceContext.getCreateDate(null),
					serviceContext, inputStream);
			}

			dirNames.put(resourcePrimKey, dirName);
		}
	}

	protected Article importArticleVersions(
			PortletDataContext portletDataContext, String uuid,
			long parentResourcePrimKey, String dirName, long[] assetCategoryIds,
			String[] assetTagNames, Element articleElement)
		throws Exception {

		Element versionsElement = articleElement.element("versions");

		List<Element> articleElements = versionsElement.elements("article");

		Article importedArticle = null;

		long priority = PriorityHelper.getPriority(
			portletDataContext.getScopeGroupId(), 0, parentResourcePrimKey, 0);

		for (Element curArticleElement : articleElements) {
			Article curArticle =
				(Article)portletDataContext.getZipEntryAsObject(
					curArticleElement.attributeValue("path"));

			long userId = portletDataContext.getUserId(
				curArticle.getUserUuid());

			String curDirName = StringPool.BLANK;
			long[] curAssetCategoryIds = null;
			String[] curAssetTagNames = null;

			int index = articleElements.indexOf(curArticleElement);

			if (index == (articleElements.size() - 1)) {
				curDirName = dirName;
				curAssetCategoryIds = assetCategoryIds;
				curAssetTagNames = assetTagNames;
			}

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAddCommunityPermissions(true);
			serviceContext.setAddGuestPermissions(true);
			serviceContext.setAssetCategoryIds(curAssetCategoryIds);
			serviceContext.setAssetTagNames(curAssetTagNames);
			serviceContext.setCreateDate(curArticle.getCreateDate());
			serviceContext.setModifiedDate(curArticle.getModifiedDate());
			serviceContext.setScopeGroupId(
				portletDataContext.getScopeGroupId());

			if (importedArticle == null) {
				serviceContext.setUuid(uuid);

				importedArticle = ArticleLocalServiceUtil.addArticle(
					userId, parentResourcePrimKey, curArticle.getTitle(),
					curArticle.getContent(), curArticle.getDescription(),
					priority, curDirName, serviceContext);
			}
			else {
				importedArticle = ArticleLocalServiceUtil.updateArticle(
					userId, importedArticle.getResourcePrimKey(),
					parentResourcePrimKey, curArticle.getTitle(),
					curArticle.getContent(), curArticle.getDescription(),
					priority, curDirName, serviceContext);
			}
		}

		return importedArticle;
	}

	protected void importArticles(
			PortletDataContext portletDataContext, Element rootElement)
		throws Exception {

		long importId = CounterLocalServiceUtil.increment();

		Map<Long, Long> resourcePrimKeys = new HashMap<Long, Long>();
		Map<String, String> dirNames = new HashMap<String, String>();

		try {
			if (portletDataContext.getBooleanParameter(
					_NAMESPACE_ARTICLE, "attachments")) {

				DLLocalServiceUtil.addDirectory(
					portletDataContext.getCompanyId(), CompanyConstants.SYSTEM,
					"knowledgebase/temp/import/" + importId);

				importArticleAttachments(
					portletDataContext, importId, dirNames, rootElement);
			}

			List<Element> articleElements = rootElement.elements("article");

			for (int i = 0; i < articleElements.size(); i++) {
				Element articleElement = articleElements.get(i);

				String path = articleElement.attributeValue("path");

				if (!portletDataContext.isPathNotProcessed(path)) {
					continue;
				}

				Article article =
					(Article)portletDataContext.getZipEntryAsObject(path);

				importArticle(
					portletDataContext, i + 1, resourcePrimKeys, dirNames,
					articleElement, article);
			}

			importComments(
				portletDataContext, "article-comment", resourcePrimKeys,
				rootElement);
		}
		finally {
			if (portletDataContext.getBooleanParameter(
					_NAMESPACE_ARTICLE, "attachments")) {

				DLLocalServiceUtil.deleteDirectory(
					portletDataContext.getCompanyId(),
					CompanyConstants.SYSTEM_STRING, CompanyConstants.SYSTEM,
					"knowledgebase/temp/import/" + importId);
			}
		}
	}

	protected void importComment(
			PortletDataContext portletDataContext, Map<Long, Long> classPKs,
			Comment comment)
		throws Exception {

		long userId = portletDataContext.getUserId(comment.getUserUuid());
		long classPK = MapUtil.getLong(classPKs, comment.getClassPK());

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCreateDate(comment.getCreateDate());
		serviceContext.setModifiedDate(comment.getModifiedDate());
		serviceContext.setScopeGroupId(portletDataContext.getScopeGroupId());

		if (portletDataContext.isDataStrategyMirror()) {
			Comment existingComment = CommentUtil.fetchByUUID_G(
				comment.getUuid(), portletDataContext.getScopeGroupId());

			if (existingComment == null) {
				serviceContext.setUuid(comment.getUuid());

				CommentLocalServiceUtil.addComment(
					userId, comment.getClassNameId(), classPK,
					comment.getContent(), comment.getHelpful(), serviceContext);
			}
			else {
				CommentLocalServiceUtil.updateComment(
					existingComment.getCommentId(), comment.getClassNameId(),
					classPK, comment.getContent(), comment.getHelpful(),
					serviceContext);
			}
		}
		else {
			CommentLocalServiceUtil.addComment(
				userId, comment.getClassNameId(), classPK, comment.getContent(),
				comment.getHelpful(), serviceContext);
		}
	}

	protected void importComments(
			PortletDataContext portletDataContext, String name,
			Map<Long, Long> classPKs, Element rootElement)
		throws Exception {

		List<Element> commentElements = rootElement.elements(name);

		for (Element commentElement : commentElements) {
			String path = commentElement.attributeValue("path");

			if (!portletDataContext.isPathNotProcessed(path)) {
				continue;
			}

			Comment comment =
				(Comment)portletDataContext.getZipEntryAsObject(path);

			importComment(portletDataContext, classPKs, comment);
		}
	}

	protected void importTemplate(
			PortletDataContext portletDataContext, Map<Long, Long> templateIds,
			Template template)
		throws Exception {

		long userId = portletDataContext.getUserId(template.getUserUuid());

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setCreateDate(template.getCreateDate());
		serviceContext.setModifiedDate(template.getModifiedDate());
		serviceContext.setScopeGroupId(portletDataContext.getScopeGroupId());

		Template importedTemplate = null;

		if (portletDataContext.isDataStrategyMirror()) {
			Template existingTemplate = TemplateUtil.fetchByUUID_G(
				template.getUuid(), portletDataContext.getScopeGroupId());

			if (existingTemplate == null) {
				serviceContext.setUuid(template.getUuid());

				importedTemplate = TemplateLocalServiceUtil.addTemplate(
					userId, template.getTitle(), template.getContent(),
					template.getDescription(), serviceContext);
			}
			else {
				importedTemplate = TemplateLocalServiceUtil.updateTemplate(
					existingTemplate.getTemplateId(), template.getTitle(),
					template.getContent(), template.getDescription(),
					serviceContext);
			}
		}
		else {
			importedTemplate = TemplateLocalServiceUtil.addTemplate(
				userId, template.getTitle(), template.getContent(),
				template.getDescription(), serviceContext);
		}

		templateIds.put(
			template.getTemplateId(), importedTemplate.getTemplateId());

		portletDataContext.importPermissions(
			Template.class, template.getTemplateId(),
			importedTemplate.getTemplateId());
	}

	protected void importTemplates(
			PortletDataContext portletDataContext, Element rootElement)
		throws Exception {

		Map<Long, Long> templateIds = new HashMap<Long, Long>();

		for (Element templateElement : rootElement.elements("template")) {
			String path = templateElement.attributeValue("path");

			if (!portletDataContext.isPathNotProcessed(path)) {
				continue;
			}

			Template template =
				(Template)portletDataContext.getZipEntryAsObject(path);

			importTemplate(portletDataContext, templateIds, template);
		}

		importComments(
			portletDataContext, "template-comment", templateIds, rootElement);
	}

	private static final String _NAMESPACE = "knowledge_base";

	private static final String _NAMESPACE_ARTICLE = "knowledge_base_article";

	private static final String _NAMESPACE_TEMPLATE = "knowledge_base_template";

	private static final int _SQL_DATA_MAX_PARAMETERS =
		GetterUtil.getInteger(PropsUtil.get(PropsKeys.SQL_DATA_MAX_PARAMETERS));

	private static PortletDataHandlerControl[] _articleOptions =
		new PortletDataHandlerControl[] {
			new PortletDataHandlerBoolean(_NAMESPACE_ARTICLE, "attachments"),
			new PortletDataHandlerBoolean(_NAMESPACE_ARTICLE, "categories"),
			new PortletDataHandlerBoolean(_NAMESPACE_ARTICLE, "tags"),
			new PortletDataHandlerBoolean(_NAMESPACE_ARTICLE, "ratings"),
			new PortletDataHandlerBoolean(_NAMESPACE_ARTICLE, "comments")
		};

	private static PortletDataHandlerBoolean _articles =
		new PortletDataHandlerBoolean(
			_NAMESPACE, "articles", true, true, _articleOptions);

	private static PortletDataHandlerControl[] _templateOptions =
		new PortletDataHandlerControl[] {
			new PortletDataHandlerBoolean(_NAMESPACE_TEMPLATE, "comments")
		};

	private static PortletDataHandlerBoolean _templates =
		new PortletDataHandlerBoolean(
			_NAMESPACE, "templates", true, false, _templateOptions);

}