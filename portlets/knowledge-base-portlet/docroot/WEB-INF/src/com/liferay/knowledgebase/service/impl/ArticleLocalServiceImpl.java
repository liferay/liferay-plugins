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

package com.liferay.knowledgebase.service.impl;

import com.liferay.documentlibrary.DuplicateDirectoryException;
import com.liferay.documentlibrary.DuplicateFileException;
import com.liferay.documentlibrary.NoSuchDirectoryException;
import com.liferay.knowledgebase.ArticleContentException;
import com.liferay.knowledgebase.ArticlePriorityException;
import com.liferay.knowledgebase.ArticleTitleException;
import com.liferay.knowledgebase.admin.social.AdminActivityKeys;
import com.liferay.knowledgebase.admin.util.AdminSubscriptionSender;
import com.liferay.knowledgebase.admin.util.AdminUtil;
import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.model.ArticleConstants;
import com.liferay.knowledgebase.service.base.ArticleLocalServiceBaseImpl;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.knowledgebase.util.comparator.ArticlePriorityComparator;
import com.liferay.knowledgebase.util.comparator.ArticleVersionComparator;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.dao.orm.Conjunction;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.SubscriptionSender;
import com.liferay.portlet.asset.model.AssetEntry;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class ArticleLocalServiceImpl extends ArticleLocalServiceBaseImpl {

	public Article addArticle(
			long userId, long parentResourcePrimKey, String title,
			String content, String description, String dirName,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Article

		User user = userPersistence.findByPrimaryKey(userId);
		long groupId = serviceContext.getScopeGroupId();
		double priority = getPriority(groupId, parentResourcePrimKey);
		Date now = new Date();

		validate(title, content);

		long articleId = counterLocalService.increment();

		long resourcePrimKey = counterLocalService.increment();

		long rootResourcePrimKey = getRootResourcePrimKey(
			resourcePrimKey, parentResourcePrimKey);

		Article article = articlePersistence.create(articleId);

		article.setUuid(serviceContext.getUuid());
		article.setResourcePrimKey(resourcePrimKey);
		article.setGroupId(groupId);
		article.setCompanyId(user.getCompanyId());
		article.setUserId(user.getUserId());
		article.setUserName(user.getFullName());
		article.setCreateDate(serviceContext.getCreateDate(now));
		article.setModifiedDate(serviceContext.getModifiedDate(now));
		article.setRootResourcePrimKey(rootResourcePrimKey);
		article.setParentResourcePrimKey(parentResourcePrimKey);
		article.setVersion(ArticleConstants.DEFAULT_VERSION);
		article.setTitle(title);
		article.setContent(content);
		article.setDescription(description);
		article.setPriority(priority);
		article.setLatest(ArticleConstants.LATEST_VERSION);
		article.setStatus(WorkflowConstants.STATUS_DRAFT);

		articlePersistence.update(article, false);

		// Resources

		if (serviceContext.getAddCommunityPermissions() ||
			serviceContext.getAddGuestPermissions()) {

			addArticleResources(
				article, serviceContext.getAddCommunityPermissions(),
				serviceContext.getAddGuestPermissions());
		}
		else {
			addArticleResources(
				article, serviceContext.getCommunityPermissions(),
				serviceContext.getGuestPermissions());
		}

		// Asset

		updateAsset(
			userId, article, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		// Attachments

		addAttachments(article, dirName);

		// Workflow

		WorkflowHandlerRegistryUtil.startWorkflowInstance(
			user.getCompanyId(), groupId, userId, Article.class.getName(),
			resourcePrimKey, article, serviceContext);

		return article;
	}

	public void addArticleResources(
			Article article, boolean addCommunityPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			article.getCompanyId(), article.getGroupId(), article.getUserId(),
			Article.class.getName(), article.getResourcePrimKey(), false,
			addCommunityPermissions, addGuestPermissions);
	}

	public void addArticleResources(
			Article article, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			article.getCompanyId(), article.getGroupId(), article.getUserId(),
			Article.class.getName(), article.getResourcePrimKey(),
			communityPermissions, guestPermissions);
	}

	public void addAttachment(
			long companyId, String dirName, String shortFileName,
			InputStream inputStream)
		throws PortalException, SystemException {

		ServiceContext serviceContext = new ServiceContext();

		dlLocalService.addFile(
			companyId, CompanyConstants.SYSTEM_STRING,
			GroupConstants.DEFAULT_PARENT_GROUP_ID, CompanyConstants.SYSTEM,
			dirName + StringPool.SLASH + shortFileName, true, 0,
			StringPool.BLANK, serviceContext.getCreateDate(null),
			serviceContext, inputStream);
	}

	public void checkAttachments() throws PortalException, SystemException {
		for (long companyId : PortalUtil.getCompanyIds()) {
			checkAttachments(companyId);
		}
	}

	public void deleteArticle(Article article)
		throws PortalException, SystemException {

		// Child articles

		List<Article> siblingArticles = getSiblingArticles(
			article.getGroupId(), article.getResourcePrimKey(),
			WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new ArticlePriorityComparator());

		for (Article siblingArticle : siblingArticles) {
			deleteArticle(siblingArticle);
		}

		// Resources

		resourceLocalService.deleteResource(
			article.getCompanyId(), Article.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, article.getResourcePrimKey());

		// Articles

		articlePersistence.removeByResourcePrimKey(
			article.getResourcePrimKey());

		// Comment

		commentLocalService.deleteComments(
			Article.class.getName(), article.getResourcePrimKey());

		// Asset

		deleteAssets(article);

		// Ratings

		ratingsStatsLocalService.deleteStats(
			Article.class.getName(), article.getResourcePrimKey());

		// Social

		socialActivityLocalService.deleteActivities(
			Article.class.getName(), article.getResourcePrimKey());

		// Indexer

		Indexer indexer = IndexerRegistryUtil.getIndexer(Article.class);

		indexer.delete(article);

		// Attachments

		deleteAttachments(article);

		// Subscriptions

		deleteSubscriptions(article);

		// Workflow

		workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
			article.getCompanyId(), article.getGroupId(),
			Article.class.getName(), article.getResourcePrimKey());
	}

	public void deleteArticle(long resourcePrimKey)
		throws PortalException, SystemException {

		Article article = getLatestArticle(
			resourcePrimKey, WorkflowConstants.STATUS_ANY);

		deleteArticle(article);
	}

	public void deleteArticles(long[] resourcePrimKeys)
		throws PortalException, SystemException {

		List<Article> articles = getArticles(
			resourcePrimKeys, WorkflowConstants.STATUS_ANY, null);

		for (Article article : articles) {
			deleteArticle(article);
		}
	}

	public void deleteAttachment(long companyId, String fileName)
		throws PortalException, SystemException {

		dlLocalService.deleteFile(
			companyId, CompanyConstants.SYSTEM_STRING, CompanyConstants.SYSTEM,
			fileName);
	}

	public void deleteGroupArticles(long groupId)
		throws PortalException, SystemException {

		// Articles

		List<Article> siblingArticles = getSiblingArticles(
			groupId, ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY,
			WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new ArticlePriorityComparator());

		for (Article siblingArticle : siblingArticles) {
			deleteArticle(siblingArticle);
		}

		// Subscriptions

		Group group = groupLocalService.getGroup(groupId);

		List<Subscription> subscriptions =
			subscriptionLocalService.getSubscriptions(
				group.getCompanyId(), Article.class.getName(), groupId);

		for (Subscription subscription : subscriptions) {
			unsubscribeGroupArticles(subscription.getUserId(), groupId);
		}
	}

	public Article getArticle(long resourcePrimKey, int version)
		throws PortalException, SystemException {

		return articlePersistence.findByR_V(resourcePrimKey, version);
	}

	public List<Article> getArticles(
			long resourcePrimKey, int status, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return articlePersistence.findByResourcePrimKey(
				resourcePrimKey, start, end, orderByComparator);
		}

		return articlePersistence.findByR_S(
			resourcePrimKey, status, start, end, orderByComparator);
	}

	public List<Article> getArticles(
			long resourcePrimKey, int status,
			OrderByComparator orderByComparator)
		throws SystemException {

		List<Article> articles = getArticles(
			new long[] {resourcePrimKey}, status, null);

		articles = ListUtil.copy(articles);

		Long[][] params = new Long[][] {new Long[] {resourcePrimKey}};

		while ((params = KnowledgeBaseUtil.getParams(params[0])) != null) {
			List<Article> curArticles = null;

			if (status == WorkflowConstants.STATUS_ANY) {
				curArticles = articlePersistence.findByP_L(
					ArrayUtil.toArray(params[1]),
					new int[] {ArticleConstants.LATEST_VERSION});
			}
			else {
				curArticles = articlePersistence.findByP_L_S(
					ArrayUtil.toArray(params[1]), ArticleConstants.LATEST_ANY,
					status);
			}

			articles.addAll(curArticles);

			long[] resourcePrimKeys = StringUtil.split(
				ListUtil.toString(curArticles, "resourcePrimKey"), 0L);

			params[0] = ArrayUtil.append(
				params[0], ArrayUtil.toArray(resourcePrimKeys));
		}

		if (orderByComparator != null) {
			articles = ListUtil.sort(articles, orderByComparator);
		}

		return new UnmodifiableList<Article>(articles);
	}

	public List<Article> getArticles(
			long[] resourcePrimKeys, int status,
			OrderByComparator orderByComparator)
		throws SystemException {

		List<Article> articles = new ArrayList<Article>();

		Long[][] params = new Long[][] {ArrayUtil.toArray(resourcePrimKeys)};

		while ((params = KnowledgeBaseUtil.getParams(params[0])) != null) {
			List<Article> curArticles = null;

			if (status == WorkflowConstants.STATUS_ANY) {
				curArticles = articlePersistence.findByR_L(
					ArrayUtil.toArray(params[1]),
					new int[] {ArticleConstants.LATEST_VERSION});
			}
			else {
				curArticles = articlePersistence.findByR_L_S(
					ArrayUtil.toArray(params[1]),
					ArticleConstants.LATEST_ANY, status);
			}

			articles.addAll(curArticles);
		}

		if (orderByComparator != null) {
			articles = ListUtil.sort(articles, orderByComparator);
		}
		else {
			articles = KnowledgeBaseUtil.sort(resourcePrimKeys, articles);
		}

		return new UnmodifiableList<Article>(articles);
	}

	public int getArticlesCount(long resourcePrimKey, int status)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return articlePersistence.countByResourcePrimKey(resourcePrimKey);
		}

		return articlePersistence.countByR_S(resourcePrimKey, status);
	}

	public List<Article> getCompanyArticles(
			long companyId, int status, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return articlePersistence.findByC_L(
				companyId, ArticleConstants.LATEST_VERSION, start, end,
				orderByComparator);
		}

		return articlePersistence.findByC_L_S(
			companyId, ArticleConstants.LATEST_ANY, status, start, end,
			orderByComparator);
	}

	public int getCompanyArticlesCount(long companyId, int status)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return articlePersistence.countByC_L(
				companyId, ArticleConstants.LATEST_VERSION);
		}

		return articlePersistence.countByC_L_S(
			companyId, ArticleConstants.LATEST_ANY, status);
	}

	public List<Article> getGroupArticles(
			long groupId, int status, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return articlePersistence.findByG_L(
				groupId, ArticleConstants.LATEST_VERSION, start, end,
				orderByComparator);
		}

		return articlePersistence.findByG_L_S(
			groupId, ArticleConstants.LATEST_ANY, status, start, end,
			orderByComparator);
	}

	public int getGroupArticlesCount(long groupId, int status)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return articlePersistence.countByG_L(
				groupId, ArticleConstants.LATEST_VERSION);
		}

		return articlePersistence.countByG_L_S(
			groupId, ArticleConstants.LATEST_ANY, status);
	}

	public Article getLatestArticle(long resourcePrimKey, int status)
		throws PortalException, SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return articlePersistence.findByResourcePrimKey_First(
				resourcePrimKey, new ArticleVersionComparator());
		}

		return articlePersistence.findByR_S_First(
			resourcePrimKey, status, new ArticleVersionComparator());
	}

	public List<Article> getSiblingArticles(
			long groupId, long parentResourcePrimKey, int status, int start,
			int end, OrderByComparator orderByComparator)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return articlePersistence.findByG_P_L(
				groupId, parentResourcePrimKey, ArticleConstants.LATEST_VERSION,
				start, end, orderByComparator);
		}

		return articlePersistence.findByG_P_L_S(
			groupId, new long[] {parentResourcePrimKey},
			ArticleConstants.LATEST_ANY, status, start, end, orderByComparator);
	}

	public int getSiblingArticlesCount(
			long groupId, long parentResourcePrimKey, int status)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return articlePersistence.countByG_P_L(
				groupId, parentResourcePrimKey,
				ArticleConstants.LATEST_VERSION);
		}

		return articlePersistence.countByG_P_L_S(
			groupId, new long[] {parentResourcePrimKey},
			ArticleConstants.LATEST_ANY, status);
	}

	public Article moveArticle(
			long userId, long resourcePrimKey, long parentResourcePrimKey,
			double priority)
		throws PortalException, SystemException {

		// Article

		validate(priority);

		updatePermissionFields(resourcePrimKey, parentResourcePrimKey);

		Article article = getLatestArticle(
			resourcePrimKey, WorkflowConstants.STATUS_ANY);

		article.setParentResourcePrimKey(parentResourcePrimKey);
		article.setPriority(priority);

		articlePersistence.update(article, false);

		// Social

		socialActivityLocalService.addActivity(
			userId, article.getGroupId(), Article.class.getName(),
			resourcePrimKey, AdminActivityKeys.MOVE_ARTICLE, StringPool.BLANK,
			0);

		return article;
	}

	public List<Article> search(
			long groupId, String title, String content, int status,
			Date startDate, Date endDate, boolean andOperator, int start,
			int end, OrderByComparator orderByComparator)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			groupId, title, content, status, startDate, endDate, andOperator);

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public void subscribeArticle(
			long userId, long groupId, long resourcePrimKey)
		throws PortalException, SystemException {

		subscriptionLocalService.addSubscription(
			userId, groupId, Article.class.getName(), resourcePrimKey);
	}

	public void subscribeGroupArticles(long userId, long groupId)
		throws PortalException, SystemException {

		subscriptionLocalService.addSubscription(
			userId, groupId, Article.class.getName(), groupId);
	}

	public void unsubscribeArticle(long userId, long resourcePrimKey)
		throws PortalException, SystemException {

		subscriptionLocalService.deleteSubscription(
			userId, Article.class.getName(), resourcePrimKey);
	}

	public void unsubscribeGroupArticles(long userId, long groupId)
		throws PortalException, SystemException {

		subscriptionLocalService.deleteSubscription(
			userId, Article.class.getName(), groupId);
	}

	public Article updateArticle(
			long userId, long resourcePrimKey, String title, String content,
			String description, String dirName, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Article

		User user = userPersistence.findByPrimaryKey(userId);
		int version = ArticleConstants.DEFAULT_VERSION;
		int status = WorkflowConstants.STATUS_DRAFT;

		validate(title, content);

		Article oldArticle = getLatestArticle(
			resourcePrimKey, WorkflowConstants.STATUS_ANY);

		long oldResourcePrimKey = oldArticle.getResourcePrimKey();
		long oldGroupId = oldArticle.getGroupId();
		Date oldCreateDate = oldArticle.getCreateDate();
		long oldRootResourcePrimKey = oldArticle.getRootResourcePrimKey();
		long oldParentResourcePrimKey = oldArticle.getParentResourcePrimKey();
		int oldVersion = oldArticle.getVersion();
		double oldPriority = oldArticle.getPriority();
		int oldStatus = oldArticle.getStatus();

		Article article = null;

		if (oldStatus == WorkflowConstants.STATUS_APPROVED) {
			long articleId = counterLocalService.increment();

			article = articlePersistence.create(articleId);
			version = oldVersion + 1;
		}
		else {
			article = oldArticle;
			version = oldVersion;
		}

		if (oldStatus == WorkflowConstants.STATUS_PENDING) {
			status = WorkflowConstants.STATUS_PENDING;
		}

		article.setResourcePrimKey(oldResourcePrimKey);
		article.setGroupId(oldGroupId);
		article.setCompanyId(user.getCompanyId());
		article.setUserId(user.getUserId());
		article.setUserName(user.getFullName());
		article.setCreateDate(oldCreateDate);
		article.setModifiedDate(serviceContext.getModifiedDate(null));
		article.setRootResourcePrimKey(oldRootResourcePrimKey);
		article.setParentResourcePrimKey(oldParentResourcePrimKey);
		article.setVersion(version);
		article.setTitle(title);
		article.setContent(content);
		article.setDescription(description);
		article.setPriority(oldPriority);
		article.setLatest(ArticleConstants.LATEST_VERSION);
		article.setStatus(status);

		articlePersistence.update(article, false);

		if (oldStatus == WorkflowConstants.STATUS_APPROVED) {
			oldArticle.setLatest(ArticleConstants.LATEST_APPROVED);

			articlePersistence.update(oldArticle, false);
		}

		// Resources

		if ((serviceContext.getCommunityPermissions() != null) ||
			(serviceContext.getGuestPermissions() != null)) {

			updateArticleResources(
				article, serviceContext.getCommunityPermissions(),
				serviceContext.getGuestPermissions());
		}

		// Asset

		updateAsset(
			userId, article, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		// Attachments

		updateAttachments(article, oldStatus, dirName);

		// Workflow

		WorkflowHandlerRegistryUtil.startWorkflowInstance(
			user.getCompanyId(), article.getGroupId(), userId,
			Article.class.getName(), resourcePrimKey, article, serviceContext);

		return article;
	}

	public void updateArticleResources(
			Article article, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.updateResources(
			article.getCompanyId(), article.getGroupId(),
			Article.class.getName(), article.getResourcePrimKey(),
			communityPermissions, guestPermissions);
	}

	public void updateAsset(
			long userId, Article article, long[] assetCategoryIds,
			String[] assetTagNames)
		throws PortalException, SystemException {

		assetEntryLocalService.updateEntry(
			userId, article.getGroupId(), Article.class.getName(),
			article.getClassPK(), article.getUuid(), assetCategoryIds,
			assetTagNames, article.isApproved(), null, null, null, null,
			ContentTypes.TEXT_HTML, article.getTitle(),
			article.getDescription(), null, null, 0, 0, null, false);
	}

	public String updateAttachments(
			long companyId, long resourcePrimKey, String dirName)
		throws PortalException, SystemException {

		if (Validator.isNotNull(dirName)) {
			return dirName;
		}

		dirName =
			"knowledgebase/temp/attachments/" + counterLocalService.increment();

		dlLocalService.addDirectory(
			companyId, CompanyConstants.SYSTEM, dirName);

		if (resourcePrimKey <= 0) {
			return dirName;
		}

		Article article = getLatestArticle(
			resourcePrimKey, WorkflowConstants.STATUS_ANY);

		for (String fileName : article.getAttachmentsFileNames()) {
			String shortFileName = FileUtil.getShortFileName(fileName);

			InputStream inputStream = dlLocalService.getFileAsStream(
				article.getCompanyId(), CompanyConstants.SYSTEM, fileName);

			addAttachment(companyId, dirName, shortFileName, inputStream);
		}

		return dirName;
	}

	public void updatePriorities(Map<Long, Double> resourcePrimKeyToPriorityMap)
		throws PortalException, SystemException {

		for (double priority : resourcePrimKeyToPriorityMap.values()) {
			validate(priority);
		}

		long[] resourcePrimKeys = StringUtil.split(
			StringUtil.merge(resourcePrimKeyToPriorityMap.keySet()), 0L);

		List<Article> articles = getArticles(
			resourcePrimKeys, WorkflowConstants.STATUS_ANY, null);

		for (Article article : articles) {
			double priority = resourcePrimKeyToPriorityMap.get(
				article.getResourcePrimKey());

			article.setPriority(priority);

			articlePersistence.update(article, false);
		}
	}

	public Article updateStatus(
			long userId, long resourcePrimKey, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		// Article

		Article article = getLatestArticle(
			resourcePrimKey, WorkflowConstants.STATUS_ANY);

		article.setModifiedDate(serviceContext.getModifiedDate(now));
		article.setStatus(status);
		article.setStatusByUserId(user.getUserId());
		article.setStatusByUserName(user.getFullName());
		article.setStatusDate(serviceContext.getModifiedDate(now));

		articlePersistence.update(article, false);

		if (status != WorkflowConstants.STATUS_APPROVED) {
			return article;
		}

		if (!article.isFirstVersion()) {
			Article oldArticle = articlePersistence.findByR_V(
				resourcePrimKey, article.getVersion() - 1);

			oldArticle.setLatest(ArticleConstants.LATEST_ARCHIVED);

			articlePersistence.update(oldArticle, false);
		}

		// Asset

		if (!article.isFirstVersion()) {
			AssetEntry assetEntry = assetEntryLocalService.getEntry(
				Article.class.getName(), article.getPrimaryKey());

			updateAsset(
				userId, article, assetEntry.getCategoryIds(),
				assetEntry.getTagNames());

			assetEntryLocalService.deleteEntry(
				Article.class.getName(), article.getPrimaryKey());
		}

		// Social

		if (!article.isFirstVersion()) {
			socialActivityLocalService.addActivity(
				userId, article.getGroupId(), Article.class.getName(),
				resourcePrimKey, AdminActivityKeys.UPDATE_ARTICLE,
				StringPool.BLANK, 0);
		}
		else {
			socialActivityLocalService.addActivity(
				userId, article.getGroupId(), Article.class.getName(),
				resourcePrimKey, AdminActivityKeys.ADD_ARTICLE,
				StringPool.BLANK, 0);
		}

		// Indexer

		Indexer indexer = IndexerRegistryUtil.getIndexer(Article.class);

		indexer.reindex(article);

		// Attachments

		if (!article.isFirstVersion()) {
			deleteAttachments(article, article.getResourcePrimKey());

			addAttachments(
				article,
				ArticleConstants.DIR_NAME_PREFIX + article.getPrimaryKey());

			deleteAttachments(article, article.getPrimaryKey());
		}

		// Subscriptions

		notifySubscribers(article, serviceContext);

		return article;
	}

	protected void addAttachments(Article article, String dirName)
		throws PortalException, SystemException {

		try {
			dlLocalService.addDirectory(
				article.getCompanyId(), CompanyConstants.SYSTEM,
				article.getAttachmentsDirName());
		}
		catch (DuplicateDirectoryException dde) {
			_log.error("Directory already exists for " + dde.getMessage());
		}

		if (Validator.isNull(dirName)) {
			return;
		}

		String[] fileNames = dlLocalService.getFileNames(
			article.getCompanyId(), CompanyConstants.SYSTEM, dirName);

		for (String fileName : fileNames) {
			InputStream inputStream = dlLocalService.getFileAsStream(
				article.getCompanyId(), CompanyConstants.SYSTEM, fileName);

			try {
				addAttachment(
					article.getCompanyId(), article.getAttachmentsDirName(),
					FileUtil.getShortFileName(fileName), inputStream);
			}
			catch (DuplicateFileException dfe) {
				_log.error("File already exists for " + dfe.getMessage());
			}
		}
	}

	protected DynamicQuery buildDynamicQuery(
		long groupId, String title, String content, int status,
		Date startDate, Date endDate, boolean andOperator) {

		Junction junction = null;

		if (andOperator) {
			junction = RestrictionsFactoryUtil.conjunction();
		}
		else {
			junction = RestrictionsFactoryUtil.disjunction();
		}

		if (Validator.isNotNull(title)) {
			Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

			for (String s : KnowledgeBaseUtil.splitKeywords(title)) {
				String value = StringPool.PERCENT + s + StringPool.PERCENT;

				disjunction.add(RestrictionsFactoryUtil.ilike("title", value));
			}

			junction.add(disjunction);
		}

		if (Validator.isNotNull(content)) {
			Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

			for (String s : KnowledgeBaseUtil.splitKeywords(content)) {
				String value = StringPool.PERCENT + s + StringPool.PERCENT;

				disjunction.add(
					RestrictionsFactoryUtil.ilike("content", value));
			}

			junction.add(disjunction);
		}

		if (status != WorkflowConstants.STATUS_ANY) {
			Property property = PropertyFactoryUtil.forName("status");

			junction.add(property.eq(status));
		}

		if ((endDate != null) && (startDate != null)) {
			Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

			String[] propertyNames = {"createDate", "modifiedDate"};

			for (String propertyName : propertyNames) {
				Property property = PropertyFactoryUtil.forName(propertyName);

				Conjunction conjunction = RestrictionsFactoryUtil.conjunction();

				conjunction.add(property.gt(startDate));
				conjunction.add(property.lt(endDate));

				disjunction.add(conjunction);
			}

			junction.add(disjunction);
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Article.class, getClass().getClassLoader());

		Property latestProperty = PropertyFactoryUtil.forName("latest");

		dynamicQuery.add(latestProperty.eq(ArticleConstants.LATEST_VERSION));

		if (groupId > 0) {
			Property property = PropertyFactoryUtil.forName("groupId");

			dynamicQuery.add(property.eq(groupId));
		}

		return dynamicQuery.add(junction);
	}

	protected void checkAttachments(long companyId)
		throws PortalException, SystemException {

		String dirName =
			"knowledgebase/temp/attachments/" + counterLocalService.increment();

		dlLocalService.addDirectory(
			companyId, CompanyConstants.SYSTEM, dirName);

		String[] fileNames = dlLocalService.getFileNames(
			companyId, CompanyConstants.SYSTEM,
			"knowledgebase/temp/attachments");

		Arrays.sort(fileNames);

		for (int i = 0; i < fileNames.length - 50; i++) {
			dlLocalService.deleteDirectory(
				companyId, CompanyConstants.SYSTEM_STRING,
				CompanyConstants.SYSTEM, fileNames[i]);
		}

		dlLocalService.deleteDirectory(
			companyId, CompanyConstants.SYSTEM_STRING, CompanyConstants.SYSTEM,
			dirName);
	}

	protected void deleteAssets(Article article)
		throws PortalException, SystemException {

		assetEntryLocalService.deleteEntry(
			Article.class.getName(), article.getResourcePrimKey());

		if (!article.isFirstVersion() && !article.isApproved()) {
			assetEntryLocalService.deleteEntry(
				Article.class.getName(), article.getPrimaryKey());
		}
	}

	protected void deleteAttachments(Article article)
		throws PortalException, SystemException {

		deleteAttachments(article, article.getResourcePrimKey());

		if (!article.isFirstVersion() && !article.isApproved()) {
			deleteAttachments(article, article.getPrimaryKey());
		}
	}

	protected void deleteAttachments(Article article, long folderId)
		throws PortalException, SystemException {

		try {
			dlLocalService.deleteDirectory(
				article.getCompanyId(), CompanyConstants.SYSTEM_STRING,
				CompanyConstants.SYSTEM,
				ArticleConstants.DIR_NAME_PREFIX + folderId);
		}
		catch (NoSuchDirectoryException nsde) {
			_log.error("No directory found for " + nsde.getMessage());
		}
	}

	protected void deleteSubscriptions(Article article)
		throws PortalException, SystemException {

		List<Subscription> subscriptions =
			subscriptionLocalService.getSubscriptions(
				article.getCompanyId(), Article.class.getName(),
				article.getResourcePrimKey());

		for (Subscription subscription : subscriptions) {
			unsubscribeArticle(
				subscription.getUserId(), subscription.getClassPK());
		}
	}

	protected Map<String, String> getEmailArticleDiffs(Article article) {
		Map<String, String> emailArticleDiffs = new HashMap<String, String>();

		for (String param : new String[] {"content", "title"}) {
			String value = BeanPropertiesUtil.getString(article, param);

			try {
				value = AdminUtil.getArticleDiff(
					article.getResourcePrimKey(), article.getVersion() - 1,
					article.getVersion(), param);
			}
			catch (Exception e) {
				_log.error(e, e);
			}

			emailArticleDiffs.put(param, value);
		}

		return emailArticleDiffs;
	}

	protected double getPriority(long groupId, long parentResourcePrimKey)
		throws SystemException {

		List<Article> siblingArticles = getSiblingArticles(
			groupId, parentResourcePrimKey, WorkflowConstants.STATUS_ANY, 0, 1,
			new ArticlePriorityComparator());

		if (siblingArticles.isEmpty()) {
			return ArticleConstants.DEFAULT_PRIORITY;
		}

		Article article = siblingArticles.get(0);

		return Math.floor(article.getPriority()) + 1;
	}

	protected long getRootResourcePrimKey(
			long resourcePrimKey, long parentResourcePrimKey)
		throws PortalException, SystemException {

		if (parentResourcePrimKey ==
				ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) {

			return resourcePrimKey;
		}

		Article article = getLatestArticle(
			parentResourcePrimKey, WorkflowConstants.STATUS_ANY);

		return article.getRootResourcePrimKey();
	}

	protected void notifySubscribers(
			Article article, ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (Validator.isNull(serviceContext.getLayoutFullURL())) {
			return;
		}

		PortletPreferences preferences =
			ServiceContextUtil.getPortletPreferences(serviceContext);

		if (preferences == null) {
			long ownerId = article.getGroupId();
			int ownerType = PortletKeys.PREFS_OWNER_TYPE_GROUP;
			long plid = PortletKeys.PREFS_PLID_SHARED;
			String portletId = PortletKeys.KNOWLEDGE_BASE_ADMIN;
			String defaultPreferences = null;

			preferences = portletPreferencesLocalService.getPreferences(
				article.getCompanyId(), ownerId, ownerType, plid, portletId,
				defaultPreferences);
		}

		if (serviceContext.isCommandAdd() &&
			!AdminUtil.getEmailArticleAddedEnabled(preferences)) {

			return;
		}

		if (serviceContext.isCommandUpdate() &&
			!AdminUtil.getEmailArticleUpdatedEnabled(preferences)) {

			return;
		}

		String fromName = AdminUtil.getEmailFromName(preferences);
		String fromAddress = AdminUtil.getEmailFromAddress(preferences);

		String articleContent = StringUtil.replace(
			article.getContent(),
			new String[] {
				"href=\"/",
				"src=\"/"
			},
			new String[] {
				"href=\"" + serviceContext.getPortalURL() + "/",
				"src=\"" + serviceContext.getPortalURL() + "/"
			});

		Map<String, String> articleDiffs = getEmailArticleDiffs(article);

		for (String key : articleDiffs.keySet()) {
			String value = StringUtil.replace(
				articleDiffs.get(key),
				new String[] {
					"href=\"/",
					"src=\"/"
				},
				new String[] {
					"href=\"" + serviceContext.getPortalURL() + "/",
					"src=\"" + serviceContext.getPortalURL() + "/"
				});

			articleDiffs.put(key, value);
		}

		String subject = null;
		String body = null;

		if (serviceContext.isCommandAdd()) {
			subject = AdminUtil.getEmailArticleAddedSubject(preferences);
			body = AdminUtil.getEmailArticleUpdatedBody(preferences);
		}
		else {
			subject = AdminUtil.getEmailArticleUpdatedSubject(preferences);
			body = AdminUtil.getEmailArticleUpdatedBody(preferences);
		}

		SubscriptionSender subscriptionSender = new AdminSubscriptionSender(
			article, serviceContext);

		subscriptionSender.setBody(body);
		subscriptionSender.setCompanyId(article.getCompanyId());
		subscriptionSender.setContextAttributes(
			"[$ARTICLE_CONTENT$]", articleContent, "[$ARTICLE_CONTENT_DIFF$]",
			articleDiffs.get("content"), "[$ARTICLE_TITLE$]",
			article.getTitle(), "[$ARTICLE_TITLE_DIFF$]",
			articleDiffs.get("title"));
		subscriptionSender.setContextUserPrefix("ARTICLE");
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setGroupId(article.getGroupId());
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setMailId("kb_article", article.getArticleId());
		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setSubject(subject);
		subscriptionSender.setUserId(article.getUserId());

		subscriptionSender.addPersistedSubscribers(
			Article.class.getName(), article.getGroupId());
		subscriptionSender.addPersistedSubscribers(
			Article.class.getName(), article.getResourcePrimKey());

		while (!article.isRoot()) {
			article = getLatestArticle(
				article.getParentResourcePrimKey(),
				WorkflowConstants.STATUS_APPROVED);

			subscriptionSender.addPersistedSubscribers(
				Article.class.getName(), article.getResourcePrimKey());
		}

		subscriptionSender.flushNotificationsAsync();
	}

	protected void updateAttachments(
			Article article, int oldStatus, String dirName)
		throws PortalException, SystemException {

		if (oldStatus == WorkflowConstants.STATUS_APPROVED) {
			if (Validator.isNull(dirName)) {
				dirName = ArticleConstants.DIR_NAME_PREFIX +
					article.getResourcePrimKey();
			}

			addAttachments(article, dirName);
		}
		else {
			if (Validator.isNull(dirName)) {
				return;
			}

			deleteAttachments(article, article.getClassPK());

			addAttachments(article, dirName);
		}
	}

	protected void updatePermissionFields(
			long resourcePrimKey, long parentResourcePrimKey)
		throws PortalException, SystemException {

		// See ArticlePermission#contains

		Article article = getLatestArticle(
			resourcePrimKey, WorkflowConstants.STATUS_ANY);

		if (article.getParentResourcePrimKey() == parentResourcePrimKey) {
			return;
		}

		long rootResourcePrimKey = getRootResourcePrimKey(
			resourcePrimKey, parentResourcePrimKey);

		if (article.getRootResourcePrimKey() == rootResourcePrimKey) {
			return;
		}

		// Sync database

		List<Article> articles1 = getArticles(
			resourcePrimKey, WorkflowConstants.STATUS_ANY, null);

		for (Article article1 : articles1) {
			List<Article> articles2 = getArticles(
				article1.getResourcePrimKey(), WorkflowConstants.STATUS_ANY,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			for (Article article2 : articles2) {
				article2.setRootResourcePrimKey(rootResourcePrimKey);

				articlePersistence.update(article2, false);
			}
		}

		// Sync indexed permission fields

		SearchEngineUtil.updatePermissionFields(
			Article.class.getName(), String.valueOf(resourcePrimKey));
	}

	protected void validate(double priority) throws PortalException {
		if (priority <= 0) {
			throw new ArticlePriorityException();
		}
	}

	protected void validate(String title, String content)
		throws PortalException {

		if (Validator.isNull(title)) {
			throw new ArticleTitleException();
		}

		if (Validator.isNull(content)) {
			throw new ArticleContentException();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ArticleLocalServiceImpl.class);

}