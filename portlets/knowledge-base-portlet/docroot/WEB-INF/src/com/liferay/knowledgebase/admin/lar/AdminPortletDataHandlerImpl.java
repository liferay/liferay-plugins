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
import com.liferay.documentlibrary.service.DLServiceUtil;
import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.persistence.ArticleUtil;
import com.liferay.knowledgebase.util.comparator.ArticlePriorityComparator;
import com.liferay.knowledgebase.util.comparator.ArticleVersionComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.lar.BasePortletDataHandler;
import com.liferay.portal.lar.PortletDataContext;
import com.liferay.portal.lar.PortletDataException;
import com.liferay.portal.lar.PortletDataHandlerBoolean;
import com.liferay.portal.lar.PortletDataHandlerControl;
import com.liferay.portal.lar.PortletDataHandlerKeys;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortletKeys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * <a href="AdminPortletDataHandlerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AdminPortletDataHandlerImpl extends BasePortletDataHandler {

	public PortletPreferences deleteData(
			PortletDataContext context, String portletId,
			PortletPreferences preferences)
		throws PortletDataException {

		try {
			if (!context.addPrimaryKey(
					AdminPortletDataHandlerImpl.class, "deleteData")) {

				ArticleLocalServiceUtil.deleteGroupArticles(
					context.getGroupId());
			}

			return null;
		}
		catch (Exception e) {
			throw new PortletDataException(e);
		}
	}

	public String exportData(
			PortletDataContext context, String portletId,
			PortletPreferences preferences)
		throws PortletDataException {

		try {
			context.addPermissions(
				"com.liferay.knowledgebase.admin", context.getGroupId());

			Document doc = SAXReaderUtil.createDocument();

			Element root = doc.addElement("knowledge-base-admin-data");

			root.addAttribute("group-id", String.valueOf(context.getGroupId()));

			exportArticles(context, root);

			return doc.formattedString();
		}
		catch (Exception e) {
			throw new PortletDataException(e);
		}
	}

	public PortletDataHandlerControl[] getExportControls() {
		return new PortletDataHandlerControl[] {_articles};
	}

	public PortletDataHandlerControl[] getImportControls() {
		return new PortletDataHandlerControl[] {_articles};
	}

	public PortletPreferences importData(
			PortletDataContext context, String portletId,
			PortletPreferences preferences, String data)
		throws PortletDataException {

		try {
			context.importPermissions(
				"com.liferay.knowledgebase.admin", context.getSourceGroupId(),
				context.getGroupId());

			Document doc = SAXReaderUtil.read(data);

			Element root = doc.getRootElement();

			importArticles(context, root);

			return null;
		}
		catch (Exception e) {
			throw new PortletDataException(e);
		}
	}

	protected void exportArticle(
			PortletDataContext context, Element root, String path,
			Article article)
		throws PortalException, SystemException {

		Element articleEl = root.addElement("article");

		articleEl.addAttribute("path", path);

		article.setUserUuid(article.getUserUuid());

		context.addZipEntry(path, article);

		context.addPermissions(Article.class, article.getResourcePrimKey());

		exportArticleVersions(context, articleEl, article.getResourcePrimKey());

		if (context.getBooleanParameter(_ARTICLE, "attachments")) {
			exportArticleAttachments(context, root, article);
		}

		if (context.getBooleanParameter(_ARTICLE, "comments")) {
			context.addComments(Article.class, article.getResourcePrimKey());
		}
	}

	protected void exportArticleAttachments(
			PortletDataContext context, Element root, Article article)
		throws PortalException, SystemException {

		Element articleAttachmentsEl = root.addElement("article-attachments");

		articleAttachmentsEl.addAttribute(
			"resource-prim-key", String.valueOf(article.getResourcePrimKey()));

		String rootPath =
			context.getPortletPath(PortletKeys.KNOWLEDGE_BASE_ADMIN) +
				"/articles/attachments/" + article.getResourcePrimKey();

		for (String fileName : article.getAttachmentsFileNames()) {
			String shortFileName = FileUtil.getShortFileName(fileName);
			String path = rootPath + StringPool.SLASH + shortFileName;
			byte[] bytes = DLServiceUtil.getFile(
				article.getCompanyId(), CompanyConstants.SYSTEM, fileName);

			Element fileEl = articleAttachmentsEl.addElement("file");

			fileEl.addAttribute("path", path);
			fileEl.addAttribute("short-file-name", shortFileName);

			context.addZipEntry(path, bytes);
		}
	}

	protected void exportArticleVersions(
			PortletDataContext context, Element articleEl, long resourcePrimKey)
		throws PortalException, SystemException {

		Element versionsEl = articleEl.addElement("versions");

		String rootPath =
			context.getPortletPath(PortletKeys.KNOWLEDGE_BASE_ADMIN) +
				"/articles/versions/" + resourcePrimKey;

		List<Article> articles = ArticleUtil.findByResourcePrimKey(
			resourcePrimKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new ArticleVersionComparator(true));

		for (Article article : articles) {
			String path =
				rootPath + StringPool.SLASH + article.getArticleId() + ".xml";

			Element curArticleEl = versionsEl.addElement("article");

			curArticleEl.addAttribute("path", path);

			context.addZipEntry(path, article);
		}
	}

	protected void exportArticles(PortletDataContext context, Element root)
		throws PortalException, SystemException {

		for (Article article : filterArticles(context)) {
			String path =
				context.getPortletPath(PortletKeys.KNOWLEDGE_BASE_ADMIN) +
					"/articles/" + article.getResourcePrimKey() + ".xml";

			if (!context.isPathNotProcessed(path)) {
				continue;
			}

			exportArticle(context, root, path, article);
		}
	}

	protected List<Article> filterArticles(PortletDataContext context)
		throws PortalException, SystemException {

		// Sort articles to simplify import code. Order articles by depth and
		// sort siblings by priority. See AdminPortletDataHandler#importArticle
		// and ArticleLocalServiceImpl#updateDisplayOrder.

		List<Article> articles = new ArrayList<Article>();

		List<Article> groupArticles = ArticleLocalServiceUtil.getGroupArticles(
			context.getGroupId(), 0, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new ArticlePriorityComparator(true));

		for (Article article : groupArticles) {
			if (context.isWithinDateRange(article.getModifiedDate())) {
				articles.add(article);
			}
		}

		int index = -1;

		while ((index = index + 1) < articles.size()) {
			Article article = articles.get(index);

			List<Article> curGroupArticles =
				ArticleLocalServiceUtil.getGroupArticles(
					article.getGroupId(), article.getResourcePrimKey(),
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					new ArticlePriorityComparator(true));

			for (Article curArticle : curGroupArticles) {
				if (context.isWithinDateRange(curArticle.getModifiedDate())) {
					articles.add(curArticle);
				}
			}
		}

		return articles;
	}

	protected void importArticle(
			PortletDataContext context, Map<Long, Long> resourcePrimKeys,
			Map<String, String> dirNames, Element articleEl, Article article)
		throws Exception {

		long userId = context.getUserId(article.getUserUuid());
		long parentResourcePrimKey = MapUtil.getLong(
			resourcePrimKeys, article.getParentResourcePrimKey());
		int priority = article.getPriority();
		String dirName = MapUtil.getString(
			dirNames, String.valueOf(article.getResourcePrimKey()));

		int maxPriority = (int)ArticleLocalServiceUtil.getGroupArticlesCount(
			context.getGroupId(), parentResourcePrimKey);

		if (priority > maxPriority) {
			priority = maxPriority;
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddCommunityPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setCreateDate(article.getCreateDate());
		serviceContext.setModifiedDate(article.getModifiedDate());
		serviceContext.setScopeGroupId(context.getGroupId());

		Article importedArticle = null;

		if (context.getDataStrategy().equals(
				PortletDataHandlerKeys.DATA_STRATEGY_MIRROR)) {

			Article existingArticle = ArticleUtil.fetchByUUID_G(
				article.getUuid(), context.getGroupId());

			if (existingArticle == null) {
				importedArticle = importArticleVersions(
					context, article.getUuid(), parentResourcePrimKey, priority,
					dirName, articleEl);
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
				articleEl);
		}

		resourcePrimKeys.put(
			article.getResourcePrimKey(), importedArticle.getResourcePrimKey());

		context.importPermissions(
			Article.class, article.getResourcePrimKey(),
			importedArticle.getResourcePrimKey());

		if (context.getBooleanParameter(_ARTICLE, "comments")) {
			context.importComments(
				Article.class, article.getResourcePrimKey(),
				importedArticle.getResourcePrimKey(), context.getGroupId());
		}
	}

	protected void importArticleAttachments(
			PortletDataContext context, long importId,
			Map<String, String> dirNames, Element root)
		throws Exception {

		List<Element> articlesAttachmentsEl = root.elements(
			"article-attachments");

		for (Element articleAttachmentsEl : articlesAttachmentsEl) {
			String resourcePrimKey = articleAttachmentsEl.attributeValue(
				"resource-prim-key");
			String importPath = "knowledgebase/temp/import/" + importId;
			String dirName = importPath + StringPool.SLASH + resourcePrimKey;

			DLServiceUtil.addDirectory(
				context.getCompanyId(), CompanyConstants.SYSTEM, dirName);

			for (Element fileEl : articleAttachmentsEl.elements("file")) {
				String shortFileName = fileEl.attributeValue("short-file-name");
				String fileName = dirName + StringPool.SLASH + shortFileName;
				byte[] bytes = context.getZipEntryAsByteArray(
					fileEl.attributeValue("path"));

				ServiceContext serviceContext = new ServiceContext();

				DLServiceUtil.addFile(
					context.getCompanyId(), CompanyConstants.SYSTEM_STRING,
					GroupConstants.DEFAULT_PARENT_GROUP_ID,
					CompanyConstants.SYSTEM, fileName, 0, StringPool.BLANK,
					serviceContext.getCreateDate(null), serviceContext, bytes);
			}

			dirNames.put(resourcePrimKey, dirName);
		}
	}

	protected Article importArticleVersions(
			PortletDataContext context, String uuid, long parentResourcePrimKey,
			int priority, String dirName, Element articleEl)
		throws Exception {

		Element versionsEl = articleEl.element("versions");

		List<Element> articlesEl = versionsEl.elements("article");

		Article importedArticle = null;

		for (Element curArticleEl : articlesEl) {
			Article curArticle = (Article)context.getZipEntryAsObject(
				curArticleEl.attributeValue("path"));

			long userId = context.getUserId(curArticle.getUserUuid());

			String curDirName = StringPool.BLANK;

			if (articlesEl.indexOf(curArticleEl) == (articlesEl.size() - 1)) {
				curDirName = dirName;
			}

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAddCommunityPermissions(true);
			serviceContext.setAddGuestPermissions(true);
			serviceContext.setCreateDate(curArticle.getCreateDate());
			serviceContext.setModifiedDate(curArticle.getModifiedDate());
			serviceContext.setScopeGroupId(context.getGroupId());

			if (importedArticle == null) {
				importedArticle = ArticleLocalServiceUtil.addArticle(
					uuid, userId, parentResourcePrimKey, curArticle.getTitle(),
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

	protected void importArticles(PortletDataContext context, Element root)
		throws Exception {

		long importId = CounterLocalServiceUtil.increment();

		Map<Long, Long> resourcePrimKeys = new HashMap<Long, Long>();
		Map<String, String> dirNames = new HashMap<String, String>();

		try {
			if (context.getBooleanParameter(_ARTICLE, "attachments")) {
				DLServiceUtil.addDirectory(
					context.getCompanyId(), CompanyConstants.SYSTEM,
					"knowledgebase/temp/import/" + importId);

				importArticleAttachments(context, importId, dirNames, root);
			}

			for (Element articleEl : root.elements("article")) {
				String path = articleEl.attributeValue("path");

				if (!context.isPathNotProcessed(path)) {
					continue;
				}

				importArticle(
					context, resourcePrimKeys, dirNames, articleEl,
					(Article)context.getZipEntryAsObject(path));
			}
		}
		finally {
			if (context.getBooleanParameter(_ARTICLE, "attachments")) {
				DLServiceUtil.deleteDirectory(
					context.getCompanyId(), CompanyConstants.SYSTEM_STRING,
					CompanyConstants.SYSTEM,
					"knowledgebase/temp/import/" + importId);
			}
		}
	}

	private static final String _ARTICLE = "knowledge_base_article";

	private static final PortletDataHandlerControl[] _article_options =
		new PortletDataHandlerControl[] {
			new PortletDataHandlerBoolean(_ARTICLE, "attachments"),
			new PortletDataHandlerBoolean(_ARTICLE, "comments")
		};

	private static final PortletDataHandlerBoolean _articles =
		new PortletDataHandlerBoolean(
			_ARTICLE, "articles", true, true, _article_options);

}