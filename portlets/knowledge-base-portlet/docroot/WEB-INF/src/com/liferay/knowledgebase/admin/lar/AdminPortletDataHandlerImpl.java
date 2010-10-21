/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringPool;
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
			PortletDataContext context, String portletId,
			PortletPreferences preferences)
		throws Exception {

		if (!context.addPrimaryKey(
				AdminPortletDataHandlerImpl.class, "deleteData")) {

			ArticleLocalServiceUtil.deleteGroupArticles(
				context.getScopeGroupId());

			CommentUtil.removeByGroupId(context.getScopeGroupId());

			TemplateLocalServiceUtil.deleteGroupTemplates(
				context.getScopeGroupId());
		}

		return null;
	}

	protected String doExportData(
			PortletDataContext context, String portletId,
			PortletPreferences preferences)
		throws Exception {

		context.addPermissions(
			"com.liferay.knowledgebase.admin", context.getScopeGroupId());

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("knowledge-base-admin-data");

		rootElement.addAttribute(
			"group-id", String.valueOf(context.getScopeGroupId()));

		exportArticles(context, rootElement);

		if (context.getBooleanParameter(_NAMESPACE_ARTICLE, "comments")) {
			exportComments(
				context, Article.class, "article-comment", rootElement);
		}

		if (context.getBooleanParameter(_NAMESPACE, "templates")) {
			exportTemplates(context, rootElement);
		}

		if (context.getBooleanParameter(_NAMESPACE_TEMPLATE, "comments")) {
			exportComments(
				context, Template.class, "template-comment", rootElement);
		}

		return document.formattedString();
	}

	protected PortletPreferences doImportData(
			PortletDataContext context, String portletId,
			PortletPreferences preferences, String data)
		throws Exception {

		context.importPermissions(
			"com.liferay.knowledgebase.admin", context.getSourceGroupId(),
			context.getScopeGroupId());

		Document document = SAXReaderUtil.read(data);

		Element rootElement = document.getRootElement();

		importArticles(context, rootElement);

		if (context.getBooleanParameter(_NAMESPACE, "templates")) {
			importTemplates(context, rootElement);
		}

		return null;
	}

	protected void exportArticle(
			PortletDataContext context, Element rootElement, String path,
			Article article)
		throws PortalException, SystemException {

		Element articleElement = rootElement.addElement("article");

		articleElement.addAttribute("path", path);

		article.setUserUuid(article.getUserUuid());

		context.addZipEntry(path, article);

		context.addPermissions(Article.class, article.getResourcePrimKey());

		exportArticleVersions(
			context, articleElement, article.getResourcePrimKey());

		if (context.getBooleanParameter(_NAMESPACE_ARTICLE, "attachments")) {
			exportArticleAttachments(context, rootElement, article);
		}

		if (context.getBooleanParameter(_NAMESPACE_ARTICLE, "categories")) {
			context.addAssetCategories(
				Article.class, article.getResourcePrimKey());
		}

		if (context.getBooleanParameter(_NAMESPACE_ARTICLE, "tags")) {
			context.addAssetTags(Article.class, article.getResourcePrimKey());
		}

		if (context.getBooleanParameter(_NAMESPACE_ARTICLE, "ratings")) {
			context.addRatingsEntries(
				Article.class, article.getResourcePrimKey());
		}
	}

	protected void exportArticleAttachments(
			PortletDataContext context, Element rootElement, Article article)
		throws PortalException, SystemException {

		Element articleAttachmentsElement = rootElement.addElement(
			"article-attachments");

		articleAttachmentsElement.addAttribute(
			"resource-prim-key", String.valueOf(article.getResourcePrimKey()));

		String rootPath =
			context.getPortletPath(PortletKeys.KNOWLEDGE_BASE_ADMIN) +
				"/articles/attachments/" + article.getResourcePrimKey();

		for (String fileName : article.getAttachmentsFileNames()) {
			String shortFileName = FileUtil.getShortFileName(fileName);

			String path = rootPath + StringPool.SLASH + shortFileName;
			InputStream inputStream = DLLocalServiceUtil.getFileAsStream(
				article.getCompanyId(), CompanyConstants.SYSTEM, fileName);

			Element fileElement = articleAttachmentsElement.addElement("file");

			fileElement.addAttribute("path", path);
			fileElement.addAttribute("short-file-name", shortFileName);

			context.addZipEntry(path, inputStream);
		}
	}

	protected void exportArticleVersions(
			PortletDataContext context, Element articleElement,
			long resourcePrimKey)
		throws SystemException {

		Element versionsElement = articleElement.addElement("versions");

		String rootPath =
			context.getPortletPath(PortletKeys.KNOWLEDGE_BASE_ADMIN) +
				"/articles/versions/" + resourcePrimKey;

		List<Article> articles = ArticleUtil.findByResourcePrimKey(
			resourcePrimKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new ArticleModifiedDateComparator(true));

		for (Article article : articles) {
			String path =
				rootPath + StringPool.SLASH + article.getArticleId() + ".xml";

			Element curArticleElement = versionsElement.addElement("article");

			curArticleElement.addAttribute("path", path);

			context.addZipEntry(path, article);
		}
	}

	protected void exportArticles(
			PortletDataContext context, Element rootElement)
		throws PortalException, SystemException {

		for (Article article : filterArticles(context)) {
			String path =
				context.getPortletPath(PortletKeys.KNOWLEDGE_BASE_ADMIN) +
					"/articles/" + article.getResourcePrimKey() + ".xml";

			if (!context.isPathNotProcessed(path)) {
				continue;
			}

			exportArticle(context, rootElement, path, article);
		}
	}

	protected void exportComment(
			PortletDataContext context, Element rootElement, String name,
			String path, Comment comment)
		throws PortalException, SystemException {

		Element commentElement = rootElement.addElement(name);

		commentElement.addAttribute("path", path);

		comment.setUserUuid(comment.getUserUuid());

		context.addZipEntry(path, comment);
	}

	protected void exportComments(
			PortletDataContext context, Class<?> classObj, String name,
			Element rootElement)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(classObj);

		List<Comment> comments = CommentUtil.findByG_C(
			context.getScopeGroupId(), classNameId);

		for (Comment comment : comments) {
			if (!context.isWithinDateRange(comment.getModifiedDate())) {
				return;
			}

			String path =
				context.getPortletPath(PortletKeys.KNOWLEDGE_BASE_ADMIN) +
					"/comments/" + comment.getCommentId() + ".xml";

			if (!context.isPathNotProcessed(path)) {
				continue;
			}

			exportComment(context, rootElement, name, path, comment);
		}
	}

	protected void exportTemplate(
			PortletDataContext context, Element rootElement, String path,
			Template template)
		throws PortalException, SystemException {

		Element templateElement = rootElement.addElement("template");

		templateElement.addAttribute("path", path);

		template.setUserUuid(template.getUserUuid());

		context.addZipEntry(path, template);

		context.addPermissions(Template.class, template.getTemplateId());
	}

	protected void exportTemplates(
			PortletDataContext context, Element rootElement)
		throws PortalException, SystemException {

		List<Template> templates = TemplateUtil.findByGroupId(
			context.getScopeGroupId());

		for (Template template : templates) {
			if (!context.isWithinDateRange(template.getModifiedDate())) {
				continue;
			}

			String path =
				context.getPortletPath(PortletKeys.KNOWLEDGE_BASE_ADMIN) +
					"/templates/" + template.getTemplateId() + ".xml";

			if (!context.isPathNotProcessed(path)) {
				continue;
			}

			exportTemplate(context, rootElement, path, template);
		}
	}

	protected List<Article> filterArticles(PortletDataContext context)
		throws SystemException {

		// Sort articles to simplify import code. Order articles by depth and
		// sort siblings by priority. See AdminPortletDataHandler#importArticle
		// and ArticleLocalServiceImpl#updateDisplayOrder.

		List<Article> articles = new ArrayList<Article>();

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("groupId", context.getScopeGroupId());
		params.put(
			"parentResourcePrimKey",
			ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY);
		params.put("status", WorkflowConstants.STATUS_APPROVED);

		List<Article> rootArticles = ArticleLocalServiceUtil.getArticles(
			params, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new ArticlePriorityComparator(true));

		for (Article rootArticle : rootArticles) {
			if (context.isWithinDateRange(rootArticle.getModifiedDate())) {
				articles.add(rootArticle);
			}
		}

		int index = -1;

		while ((index = index + 1) < articles.size()) {
			Article article = articles.get(index);

			Map<String, Object> curParams = new HashMap<String, Object>();

			curParams.put("groupId", article.getGroupId());
			curParams.put(
				"parentResourcePrimKey", article.getResourcePrimKey());
			curParams.put("status", WorkflowConstants.STATUS_APPROVED);

			List<Article> childArticles = ArticleLocalServiceUtil.getArticles(
				curParams, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new ArticlePriorityComparator(true));

			for (Article childArticle : childArticles) {
				if (context.isWithinDateRange(childArticle.getModifiedDate())) {
					articles.add(childArticle);
				}
			}
		}

		return articles;
	}

	protected void importArticle(
			PortletDataContext context, Map<Long, Long> resourcePrimKeys,
			Map<String, String> dirNames, Element articleElement,
			Article article)
		throws Exception {

		long userId = context.getUserId(article.getUserUuid());
		long parentResourcePrimKey = MapUtil.getLong(
			resourcePrimKeys, article.getParentResourcePrimKey());
		int priority = article.getPriority();
		String dirName = MapUtil.getString(
			dirNames, String.valueOf(article.getResourcePrimKey()));

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("groupId", context.getScopeGroupId());
		params.put("parentResourcePrimKey", parentResourcePrimKey);
		params.put("status", WorkflowConstants.STATUS_APPROVED);

		int maxPriority = ArticleLocalServiceUtil.getArticlesCount(
			params, false);

		if (priority > maxPriority) {
			priority = maxPriority;
		}

		long[] assetCategoryIds = null;

		if (context.getBooleanParameter(_NAMESPACE_ARTICLE, "categories")) {
			assetCategoryIds = context.getAssetCategoryIds(
				Article.class, article.getResourcePrimKey());
		}

		String[] assetTagNames = null;

		if (context.getBooleanParameter(_NAMESPACE_ARTICLE, "tags")) {
			assetTagNames = context.getAssetTagNames(
				Article.class, article.getResourcePrimKey());
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setAssetCategoryIds(assetCategoryIds);
		serviceContext.setAssetTagNames(assetTagNames);
		serviceContext.setCreateDate(article.getCreateDate());
		serviceContext.setModifiedDate(article.getModifiedDate());
		serviceContext.setScopeGroupId(context.getScopeGroupId());

		Article importedArticle = null;

		if (context.isDataStrategyMirror()) {
			Article existingArticle = ArticleUtil.fetchByUUID_G(
				article.getUuid(), context.getScopeGroupId());

			if (existingArticle == null) {
				importedArticle = importArticleVersions(
					context, article.getUuid(), parentResourcePrimKey, priority,
					dirName, assetCategoryIds, assetTagNames, articleElement);
			}
			else {
				importedArticle = ArticleLocalServiceUtil.updateArticle(
					userId, existingArticle.getResourcePrimKey(),
					parentResourcePrimKey, article.getTitle(),
					article.getContent(), article.getDescription(), priority,
					dirName, serviceContext);
			}
		}
		else {
			importedArticle = importArticleVersions(
				context, null, parentResourcePrimKey, priority, dirName,
				assetCategoryIds, assetTagNames, articleElement);
		}

		resourcePrimKeys.put(
			article.getResourcePrimKey(), importedArticle.getResourcePrimKey());

		context.importPermissions(
			Article.class, article.getResourcePrimKey(),
			importedArticle.getResourcePrimKey());

		if (context.getBooleanParameter(_NAMESPACE_ARTICLE, "ratings")) {
			context.importRatingsEntries(
				Article.class, article.getResourcePrimKey(),
				importedArticle.getResourcePrimKey());
		}
	}

	protected void importArticleAttachments(
			PortletDataContext context, long importId,
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
				context.getCompanyId(), CompanyConstants.SYSTEM, dirName);

			List<Element> fileElements = articleAttachmentsElement.elements(
				"file");

			for (Element fileElement : fileElements) {
				String shortFileName = fileElement.attributeValue(
					"short-file-name");

				String fileName = dirName + StringPool.SLASH + shortFileName;
				InputStream inputStream = context.getZipEntryAsInputStream(
					fileElement.attributeValue("path"));

				ServiceContext serviceContext = new ServiceContext();

				DLLocalServiceUtil.addFile(
					context.getCompanyId(), CompanyConstants.SYSTEM_STRING,
					GroupConstants.DEFAULT_PARENT_GROUP_ID,
					CompanyConstants.SYSTEM, fileName, true, 0,
					StringPool.BLANK, serviceContext.getCreateDate(null),
					serviceContext, inputStream);
			}

			dirNames.put(resourcePrimKey, dirName);
		}
	}

	protected Article importArticleVersions(
			PortletDataContext context, String uuid, long parentResourcePrimKey,
			int priority, String dirName, long[] assetCategoryIds,
			String[] assetTagNames, Element articleElement)
		throws Exception {

		Element versionsElement = articleElement.element("versions");

		List<Element> articleElements = versionsElement.elements("article");

		Article importedArticle = null;

		for (Element curArticleElement : articleElements) {
			Article curArticle = (Article)context.getZipEntryAsObject(
				curArticleElement.attributeValue("path"));

			long userId = context.getUserId(curArticle.getUserUuid());

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
			serviceContext.setScopeGroupId(context.getScopeGroupId());

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
			PortletDataContext context, Element rootElement)
		throws Exception {

		long importId = CounterLocalServiceUtil.increment();

		Map<Long, Long> resourcePrimKeys = new HashMap<Long, Long>();
		Map<String, String> dirNames = new HashMap<String, String>();

		try {
			if (context.getBooleanParameter(
					_NAMESPACE_ARTICLE, "attachments")) {

				DLLocalServiceUtil.addDirectory(
					context.getCompanyId(), CompanyConstants.SYSTEM,
					"knowledgebase/temp/import/" + importId);

				importArticleAttachments(
					context, importId, dirNames, rootElement);
			}

			for (Element articleElement : rootElement.elements("article")) {
				String path = articleElement.attributeValue("path");

				if (!context.isPathNotProcessed(path)) {
					continue;
				}

				Article article = (Article)context.getZipEntryAsObject(path);

				importArticle(
					context, resourcePrimKeys, dirNames, articleElement,
					article);
			}

			importComments(
				 context, "article-comment", resourcePrimKeys, rootElement);
		}
		finally {
			if (context.getBooleanParameter(
					_NAMESPACE_ARTICLE, "attachments")) {

				DLLocalServiceUtil.deleteDirectory(
					context.getCompanyId(), CompanyConstants.SYSTEM_STRING,
					CompanyConstants.SYSTEM,
					"knowledgebase/temp/import/" + importId);
			}
		}
	}

	protected void importComment(
			PortletDataContext context, Map<Long, Long> classPKs,
			Comment comment)
		throws Exception {

		long userId = context.getUserId(comment.getUserUuid());
		long classPK = MapUtil.getLong(classPKs, comment.getClassPK());

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCreateDate(comment.getCreateDate());
		serviceContext.setModifiedDate(comment.getModifiedDate());
		serviceContext.setScopeGroupId(context.getScopeGroupId());

		if (context.isDataStrategyMirror()) {
			Comment existingComment = CommentUtil.fetchByUUID_G(
				comment.getUuid(), context.getScopeGroupId());

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
			PortletDataContext context, String name, Map<Long, Long> classPKs,
			Element rootElement)
		throws Exception {

		List<Element> commentElements = rootElement.elements(name);

		for (Element commentElement : commentElements) {
			String path = commentElement.attributeValue("path");

			if (!context.isPathNotProcessed(path)) {
				continue;
			}

			Comment comment = (Comment)context.getZipEntryAsObject(path);

			importComment(context, classPKs, comment);
		}
	}

	protected void importTemplate(
			PortletDataContext context, Map<Long, Long> templateIds,
			Template template)
		throws Exception {

		long userId = context.getUserId(template.getUserUuid());

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setCreateDate(template.getCreateDate());
		serviceContext.setModifiedDate(template.getModifiedDate());
		serviceContext.setScopeGroupId(context.getScopeGroupId());

		Template importedTemplate = null;

		if (context.isDataStrategyMirror()) {
			Template existingTemplate = TemplateUtil.fetchByUUID_G(
				template.getUuid(), context.getScopeGroupId());

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

		context.importPermissions(
			Template.class, template.getTemplateId(),
			importedTemplate.getTemplateId());
	}

	protected void importTemplates(
			PortletDataContext context, Element rootElement)
		throws Exception {

		Map<Long, Long> templateIds = new HashMap<Long, Long>();

		for (Element templateElement : rootElement.elements("template")) {
			String path = templateElement.attributeValue("path");

			if (!context.isPathNotProcessed(path)) {
				continue;
			}

			Template template = (Template)context.getZipEntryAsObject(path);

			importTemplate(context, templateIds, template);
		}

		importComments(context, "template-comment", templateIds, rootElement);
	}

	private static final String _NAMESPACE = "knowledge_base";

	private static final String _NAMESPACE_ARTICLE = "knowledge_base_article";

	private static final String _NAMESPACE_TEMPLATE = "knowledge_base_template";

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