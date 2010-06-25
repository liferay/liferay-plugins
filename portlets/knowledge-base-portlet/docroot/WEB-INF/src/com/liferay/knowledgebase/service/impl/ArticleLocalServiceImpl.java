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

package com.liferay.knowledgebase.service.impl;

import com.liferay.documentlibrary.DuplicateDirectoryException;
import com.liferay.documentlibrary.DuplicateFileException;
import com.liferay.documentlibrary.NoSuchDirectoryException;
import com.liferay.knowledgebase.ArticleContentException;
import com.liferay.knowledgebase.ArticleTitleException;
import com.liferay.knowledgebase.admin.social.AdminActivityKeys;
import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.model.ArticleConstants;
import com.liferay.knowledgebase.service.base.ArticleLocalServiceBaseImpl;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.knowledgebase.util.comparator.ArticlePriorityComparator;
import com.liferay.knowledgebase.util.comparator.ArticleVersionComparator;
import com.liferay.portal.kernel.dao.orm.Conjunction;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.portlet.PortletProps;

import java.io.IOException;
import java.io.InputStream;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * <a href="ArticleLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class ArticleLocalServiceImpl extends ArticleLocalServiceBaseImpl {

	public Article addArticle(
			String uuid, long userId, long parentResourcePrimKey, String title,
			String content, String description, int priority, String dirName,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Article

		User user = userPersistence.findByPrimaryKey(userId);
		long groupId = serviceContext.getScopeGroupId();
		Date now = new Date();

		validate(title, content);

		long articleId = counterLocalService.increment();

		long resourcePrimKey = counterLocalService.increment();

		Article article = articlePersistence.create(articleId);

		article.setUuid(uuid);
		article.setResourcePrimKey(resourcePrimKey);
		article.setGroupId(groupId);
		article.setCompanyId(user.getCompanyId());
		article.setUserId(user.getUserId());
		article.setUserName(user.getFullName());
		article.setCreateDate(serviceContext.getCreateDate(now));
		article.setModifiedDate(serviceContext.getModifiedDate(now));
		article.setParentResourcePrimKey(parentResourcePrimKey);
		article.setVersion(ArticleConstants.DEFAULT_VERSION);
		article.setTitle(title);
		article.setContent(content);
		article.setDescription(description);
		article.setPriority(priority);

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

		// Articles

		updateDisplayOrder(article, parentResourcePrimKey, priority);

		// Message Boards

		mbMessageLocalService.addDiscussionMessage(
			userId, article.getUserName(), groupId, Article.class.getName(),
			resourcePrimKey, WorkflowConstants.ACTION_PUBLISH);

		// Social

		socialActivityLocalService.addActivity(
			userId, groupId, Article.class.getName(), resourcePrimKey,
			AdminActivityKeys.ADD_ARTICLE, StringPool.BLANK, 0);

		// Indexer

		Indexer indexer = IndexerRegistryUtil.getIndexer(Article.class);

		indexer.reindex(article);

		// Attachments

		addAttachments(article, dirName);

		// Subscriptions

		notifySubscribers(article, false, serviceContext);

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

		// Resources

		resourceLocalService.deleteResource(
			article.getCompanyId(), Article.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, article.getResourcePrimKey());

		// Articles

		articlePersistence.removeByResourcePrimKey(
			article.getResourcePrimKey());

		// Message boards

		mbMessageLocalService.deleteDiscussionMessages(
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
	}

	public void deleteArticle(long resourcePrimKey)
		throws PortalException, SystemException {

		// Article

		Article article = articlePersistence.findByResourcePrimKey_First(
			resourcePrimKey, new ArticleVersionComparator());

		// Child Articles

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("groupId", article.getGroupId());
		params.put("parentResourcePrimKey", article.getResourcePrimKey());

		List<Article> childArticles = getArticles(
			params, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new ArticlePriorityComparator());

		for (Article childArticle : childArticles) {
			deleteArticle(childArticle.getResourcePrimKey());
		}

		deleteArticle(article);
	}

	public void deleteAttachment(long companyId, String fileName)
		throws PortalException, SystemException {

		dlService.deleteFile(
			companyId, CompanyConstants.SYSTEM_STRING, CompanyConstants.SYSTEM,
			fileName);
	}

	public void deleteGroupArticles(long groupId)
		throws PortalException, SystemException {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("groupId", groupId);
		params.put(
			"parentResourcePrimKey",
			ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY);

		List<Article> articles = getArticles(
			params, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new ArticlePriorityComparator());

		for (Article article : articles) {
			deleteArticle(article.getResourcePrimKey());
		}
	}

	public Article getArticle(long resourcePrimKey, int version)
		throws PortalException, SystemException {

		return articlePersistence.findByR_V(resourcePrimKey, version);
	}

	public List<Article> getArticles(
			long resourcePrimKey, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return articlePersistence.findByResourcePrimKey(
			resourcePrimKey, start, end, orderByComparator);
	}

	public List<Article> getArticles(
			Map<String, Object> params, boolean allVersions, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(params, allVersions);

		if (dynamicQuery == null) {
			return Collections.EMPTY_LIST;
		}

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public int getArticlesCount(long resourcePrimKey) throws SystemException {
		return articlePersistence.countByResourcePrimKey(resourcePrimKey);
	}

	public int getArticlesCount(Map<String, Object> params, boolean allVersions)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(params, allVersions);

		if (dynamicQuery == null) {
			return 0;
		}

		return (int)dynamicQueryCount(dynamicQuery);
	}

	public List<Article> getCompanyArticles(
			long companyId, boolean allVersions, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("companyId", companyId);

		return getArticles(params, allVersions, start, end, orderByComparator);
	}

	public int getCompanyArticlesCount(long companyId, boolean allVersions)
		throws SystemException {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("companyId", companyId);

		return getArticlesCount(params, allVersions);
	}

	public List<Article> getGroupArticles(
			long groupId, boolean allVersions, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("groupId", groupId);

		return getArticles(params, allVersions, start, end, orderByComparator);
	}

	public int getGroupArticlesCount(long groupId, boolean allVersions)
		throws SystemException {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("groupId", groupId);

		return getArticlesCount(params, allVersions);
	}

	public Article getLatestArticle(long resourcePrimKey)
		throws PortalException, SystemException {

		return articlePersistence.findByResourcePrimKey_First(
			resourcePrimKey, new ArticleVersionComparator());
	}

	public void subscribe(long groupId, long userId)
		throws PortalException, SystemException {

		subscriptionLocalService.addSubscription(
			userId, Article.class.getName(), groupId);
	}

	public void subscribeArticle(
			long userId, long resourcePrimKey, String portletId)
		throws PortalException, SystemException {

		// Subscription

		Subscription subscription = subscriptionLocalService.addSubscription(
			userId, Article.class.getName(), resourcePrimKey);

		// Expando

		expandoValueLocalService.addValue(
			subscription.getCompanyId(), Subscription.class.getName(), "KB",
			"portletId", subscription.getSubscriptionId(), portletId);
	}

	public void unsubscribe(long groupId, long userId)
		throws PortalException, SystemException {

		subscriptionLocalService.deleteSubscription(
			userId, Article.class.getName(), groupId);
	}

	public void unsubscribeArticle(
			long companyId, long userId, long resourcePrimKey)
		throws PortalException, SystemException {

		Subscription subscription = subscriptionLocalService.getSubscription(
			companyId, userId, Article.class.getName(), resourcePrimKey);

		unsubscribeArticle(subscription);
	}

	public void unsubscribeArticle(Subscription subscription)
		throws PortalException, SystemException {

		// Subscription

		subscriptionLocalService.deleteSubscription(subscription);

		// Expando

		expandoValueLocalService.deleteValue(
			subscription.getCompanyId(), Subscription.class.getName(), "KB",
			"portletId", subscription.getSubscriptionId());
	}

	public Article updateArticle(
			long userId, long resourcePrimKey, long parentResourcePrimKey,
			String title, String content, String description, int priority,
			String dirName, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Article

		User user = userPersistence.findByPrimaryKey(userId);
		Article oldArticle = articlePersistence.findByResourcePrimKey_First(
			resourcePrimKey, new ArticleVersionComparator());

		validate(title, content);

		long articleId = counterLocalService.increment();

		Article article = articlePersistence.create(articleId);

		article.setResourcePrimKey(oldArticle.getResourcePrimKey());
		article.setGroupId(oldArticle.getGroupId());
		article.setCompanyId(oldArticle.getCompanyId());
		article.setUserId(user.getUserId());
		article.setUserName(user.getFullName());
		article.setCreateDate(oldArticle.getCreateDate());
		article.setModifiedDate(serviceContext.getModifiedDate(null));
		article.setParentResourcePrimKey(parentResourcePrimKey);
		article.setVersion(oldArticle.getVersion() + 1);
		article.setTitle(title);
		article.setContent(content);
		article.setDescription(description);
		article.setPriority(priority);

		articlePersistence.update(article, false);

		// Resources

		if ((serviceContext.getCommunityPermissions() != null) ||
			(serviceContext.getGuestPermissions() != null)) {

			updateArticleResources(
				article, serviceContext.getCommunityPermissions(),
				serviceContext.getGuestPermissions());
		}

		// Articles

		updateDisplayOrder(article, parentResourcePrimKey, priority);

		// Social

		socialActivityLocalService.addActivity(
			userId, article.getGroupId(), Article.class.getName(),
			resourcePrimKey, AdminActivityKeys.UPDATE_ARTICLE, StringPool.BLANK,
			0);

		// Indexer

		Indexer indexer = IndexerRegistryUtil.getIndexer(Article.class);

		indexer.reindex(article);

		// Attachments

		updateAttachments(article, dirName);

		// Subscriptions

		notifySubscribers(article, true, serviceContext);

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

	public String updateAttachments(
			long companyId, long resourcePrimKey, String dirName)
		throws PortalException, SystemException {

		if (Validator.isNotNull(dirName)) {
			return dirName;
		}

		dirName =
			"knowledgebase/temp/attachments/" + counterLocalService.increment();

		dlService.addDirectory(companyId, CompanyConstants.SYSTEM, dirName);

		if (resourcePrimKey <= 0) {
			return dirName;
		}

		Article article = getLatestArticle(resourcePrimKey);

		for (String fileName : article.getAttachmentsFileNames()) {
			String shortFileName = FileUtil.getShortFileName(fileName);

			InputStream inputStream = dlLocalService.getFileAsStream(
				article.getCompanyId(), CompanyConstants.SYSTEM, fileName);

			addAttachment(companyId, dirName, shortFileName, inputStream);
		}

		return dirName;
	}

	public Article updateDisplayOrder(
			Article article, long parentResourcePrimKey, int priority)
		throws SystemException {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("groupId", article.getGroupId());
		params.put("parentResourcePrimKey", article.getParentResourcePrimKey());

		List<Article> siblingArticles = getArticles(
			params, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new ArticlePriorityComparator(true));

		siblingArticles = ListUtil.copy(siblingArticles);

		siblingArticles.remove(article);
		siblingArticles.add(priority, article);

		for (int i = 0; i < siblingArticles.size(); i++) {
			Article siblingArticle = siblingArticles.get(i);

			long siblingParentResourcePrimKey =
				siblingArticle.getParentResourcePrimKey();

			if (priority == i) {
				siblingParentResourcePrimKey = parentResourcePrimKey;
			}

			List<Article> articles = articlePersistence.findByResourcePrimKey(
				siblingArticle.getResourcePrimKey());

			for (Article curArticle : articles) {
				curArticle.setParentResourcePrimKey(
					siblingParentResourcePrimKey);
				curArticle.setPriority(i);

				articlePersistence.update(curArticle, false);

				if (article.getArticleId() == curArticle.getArticleId()) {
					article = curArticle;
				}
			}
		}

		return article;
	}

	protected void addAttachments(Article article, String dirName)
		throws PortalException, SystemException {

		String articleDirName = article.getAttachmentsDirName();

		try {
			dlService.addDirectory(
				article.getCompanyId(), CompanyConstants.SYSTEM,
				articleDirName);
		}
		catch (DuplicateDirectoryException dde) {
			_log.error("Directory already exists for " + dde.getMessage());
		}

		if (Validator.isNull(dirName)) {
			return;
		}

		String[] fileNames = dlService.getFileNames(
			article.getCompanyId(), CompanyConstants.SYSTEM, dirName);

		for (String fileName : fileNames) {
			InputStream inputStream = dlLocalService.getFileAsStream(
				article.getCompanyId(), CompanyConstants.SYSTEM, fileName);

			try {
				addAttachment(
					article.getCompanyId(), articleDirName,
					FileUtil.getShortFileName(fileName), inputStream);
			}
			catch (DuplicateFileException dfe) {
				_log.error("File already exists for " + dfe.getMessage());
			}
		}
	}

	protected DynamicQuery buildDynamicQuery(
			Map<String, Object> params, boolean allVersions)
		throws SystemException {

		for (Object value : params.values()) {
			if (value instanceof Object[]) {
				Object[] valueArray = (Object[])value;

				if (valueArray.length == 0) {
					return null;
				}
			}
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Article.class, "article1", PortletClassLoaderUtil.getClassLoader());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (key.equals("parentGroupId")) {
				key = "groupId";
				value = getGroupIds((Long)value);
			}

			if (value instanceof Object[]) {
				Property property = PropertyFactoryUtil.forName(key);

				dynamicQuery.add(property.in((Object[])value));
			}
			else {
				Property property = PropertyFactoryUtil.forName(key);

				dynamicQuery.add(property.eq(value));
			}
		}

		if (allVersions) {
			return dynamicQuery;
		}

		DynamicQuery subselectDynamicQuery = DynamicQueryFactoryUtil.forClass(
			Article.class, "article2", PortletClassLoaderUtil.getClassLoader());

		Property versionProperty = PropertyFactoryUtil.forName("version");

		subselectDynamicQuery.setProjection(versionProperty.max());

		Property resourcePrimKeyProperty1 = PropertyFactoryUtil.forName(
			"article1.resourcePrimKey");
		Property resourcePrimKeyProperty2 = PropertyFactoryUtil.forName(
			"article2.resourcePrimKey");

		subselectDynamicQuery.add(
			resourcePrimKeyProperty1.eqProperty(resourcePrimKeyProperty2));

		dynamicQuery.add(versionProperty.in(subselectDynamicQuery));

		return dynamicQuery;
	}

	protected void checkAttachments(long companyId)
		throws PortalException, SystemException {

		String dirName =
			"knowledgebase/temp/attachments/" + counterLocalService.increment();

		dlService.addDirectory(companyId, CompanyConstants.SYSTEM, dirName);

		String[] fileNames = dlService.getFileNames(
			companyId, CompanyConstants.SYSTEM,
			"knowledgebase/temp/attachments");

		Arrays.sort(fileNames);

		for (int i = 0; i < fileNames.length - 50; i++) {
			dlService.deleteDirectory(
				companyId, CompanyConstants.SYSTEM_STRING,
				CompanyConstants.SYSTEM, fileNames[i]);
		}

		dlService.deleteDirectory(
			companyId, CompanyConstants.SYSTEM_STRING, CompanyConstants.SYSTEM,
			dirName);
	}

	protected void deleteAttachments(Article article)
		throws PortalException, SystemException {

		try {
			dlService.deleteDirectory(
				article.getCompanyId(), CompanyConstants.SYSTEM_STRING,
				CompanyConstants.SYSTEM, article.getAttachmentsDirName());
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
			unsubscribeArticle(subscription);
		}
	}

	protected Long[] getGroupIds(long parentGroupId) throws SystemException {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Group.class, "group", PortalClassLoaderUtil.getClassLoader());

		Property groupIdProperty = PropertyFactoryUtil.forName("groupId");
		Property classNameIdProperty = PropertyFactoryUtil.forName(
			"classNameId");
		Property parentGroupIdProperty = PropertyFactoryUtil.forName(
			"parentGroupId");

		long classNameId = PortalUtil.getClassNameId(Layout.class);

		Conjunction conjunction1 = RestrictionsFactoryUtil.conjunction();

		conjunction1.add(groupIdProperty.eq(parentGroupId));
		conjunction1.add(classNameIdProperty.ne(classNameId));

		Conjunction conjunction2 = RestrictionsFactoryUtil.conjunction();

		conjunction2.add(classNameIdProperty.eq(classNameId));
		conjunction2.add(parentGroupIdProperty.eq(parentGroupId));

		Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

		disjunction.add(conjunction1);
		disjunction.add(conjunction2);

		dynamicQuery.add(disjunction);

		List<Group> groups = groupPersistence.findWithDynamicQuery(
			dynamicQuery);

		return ArrayUtil.toArray(
			StringUtil.split(ListUtil.toString(groups, "groupId"), 0L));
	}

	protected void notifySubscribers(
			Article article, boolean update, ServiceContext serviceContext)
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

		String emailArticleAddedEnabled = preferences.getValue(
			"email-article-added-enabled",
			PortletProps.get("admin.email.article.added.enabled"));

		if (!update && !GetterUtil.getBoolean(emailArticleAddedEnabled)) {
			return;
		}

		String emailArticleUpdatedEnabled = preferences.getValue(
			"email-article-updated-enabled",
			PortletProps.get("admin.email.article.updated.enabled"));

		if (update && !GetterUtil.getBoolean(emailArticleUpdatedEnabled)) {
			return;
		}

		Company company = companyPersistence.findByPrimaryKey(
			article.getCompanyId());

		Group group = groupPersistence.findByPrimaryKey(
			serviceContext.getScopeGroupId());

		User user = userPersistence.fetchByPrimaryKey(article.getUserId());

		String fullName = article.getUserName();
		String emailAddress = StringPool.BLANK;

		if (user != null) {
			fullName = user.getFullName();
			emailAddress = user.getEmailAddress();
		}

		String fromName = preferences.getValue(
			"email-from-name", PortletProps.get("admin.email.from.name"));
		String fromAddress = preferences.getValue(
			"email-from-address", PortletProps.get("admin.email.from.address"));

		fromName = StringUtil.replace(
			fromName,
			new String[] {
				"[$ARTICLE_USER_ADDRESS$]",
				"[$ARTICLE_USER_NAME$]",
				"[$COMPANY_ID$]",
				"[$COMPANY_MX$]",
				"[$COMPANY_NAME$]",
				"[$COMMUNITY_NAME$]"
			},
			new String[] {
				emailAddress,
				fullName,
				String.valueOf(company.getCompanyId()),
				company.getMx(),
				company.getName(),
				group.getName()
			});

		fromAddress = StringUtil.replace(
			fromAddress,
			new String[] {
				"[$ARTICLE_USER_ADDRESS$]",
				"[$ARTICLE_USER_NAME$]",
				"[$COMPANY_ID$]",
				"[$COMPANY_MX$]",
				"[$COMPANY_NAME$]",
				"[$COMMUNITY_NAME$]"
			},
			new String[] {
				emailAddress,
				fullName,
				String.valueOf(company.getCompanyId()),
				company.getMx(),
				company.getName(),
				group.getName()
			});

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

		Map<String, String> articleDiffs = new HashMap<String, String>();

		String[] parameters = new String[] {"content", "title"};

		for (String parameter : parameters) {
			String articleDiff = StringPool.BLANK;

			try {
				articleDiff = KnowledgeBaseUtil.getArticleDiff(
					article.getResourcePrimKey(), article.getVersion(),
					parameter, serviceContext.getPortalURL());
			}
			catch (Exception e) {
				_log.error(
					"Unable to process diff for {resourcePrimKey=" +
						article.getResourcePrimKey() + ", version=" +
							article.getVersion() + "}");
			}

			articleDiffs.put(parameter, articleDiff);
		}

		String subject = null;
		String body = null;

		if (!update) {
			subject = preferences.getValue("email-article-added-subject", null);

			if (subject == null) {
				String name = PortletProps.get(
					"admin.email.article.added.subject");

				try {
					subject = StringUtil.read(
						getClass().getClassLoader(), name);
				}
				catch (IOException ioe) {
					_log.error(ioe, ioe);
				}
			}

			body = preferences.getValue("email-article-added-body", null);

			if (body == null) {
				String name = PortletProps.get(
					"admin.email.article.added.body");

				try {
					body = StringUtil.read(getClass().getClassLoader(), name);
				}
				catch (IOException ioe) {
					_log.error(ioe, ioe);
				}
			}
		}
		else {
			subject = preferences.getValue(
				"email-article-updated-subject", null);

			if (subject == null) {
				String name = PortletProps.get(
					"admin.email.article.updated.subject");

				try {
					subject = StringUtil.read(
						getClass().getClassLoader(), name);
				}
				catch (IOException ioe) {
					_log.error(ioe, ioe);
				}
			}

			body = preferences.getValue("email-article-updated-body", null);

			if (body == null) {
				String name = PortletProps.get(
					"admin.email.article.updated.body");

				try {
					body = StringUtil.read(getClass().getClassLoader(), name);
				}
				catch (IOException ioe) {
					_log.error(ioe, ioe);
				}
			}
		}

		subject = StringUtil.replace(
			subject,
			new String[] {
				"[$ARTICLE_CONTENT$]",
				"[$ARTICLE_CONTENT_DIFF$]",
				"[$ARTICLE_TITLE$]",
				"[$ARTICLE_TITLE_DIFF$]",
				"[$ARTICLE_USER_ADDRESS$]",
				"[$ARTICLE_USER_NAME$]",
				"[$COMPANY_ID$]",
				"[$COMPANY_MX$]",
				"[$COMPANY_NAME$]",
				"[$COMMUNITY_NAME$]",
				"[$FROM_ADDRESS$]",
				"[$FROM_NAME$]",
				"[$PORTAL_URL$]"
			},
			new String[] {
				articleContent,
				articleDiffs.get("content"),
				article.getTitle(),
				articleDiffs.get("title"),
				emailAddress,
				fullName,
				String.valueOf(company.getCompanyId()),
				company.getMx(),
				company.getName(),
				group.getName(),
				fromAddress,
				fromName,
				serviceContext.getPortalURL()
			});

		body = StringUtil.replace(
			body,
			new String[] {
				"[$ARTICLE_CONTENT$]",
				"[$ARTICLE_CONTENT_DIFF$]",
				"[$ARTICLE_TITLE$]",
				"[$ARTICLE_TITLE_DIFF$]",
				"[$ARTICLE_USER_ADDRESS$]",
				"[$ARTICLE_USER_NAME$]",
				"[$COMPANY_ID$]",
				"[$COMPANY_MX$]",
				"[$COMPANY_NAME$]",
				"[$COMMUNITY_NAME$]",
				"[$FROM_ADDRESS$]",
				"[$FROM_NAME$]",
				"[$PORTAL_URL$]"
			},
			new String[] {
				articleContent,
				articleDiffs.get("content"),
				article.getTitle(),
				articleDiffs.get("title"),
				emailAddress,
				fullName,
				String.valueOf(company.getCompanyId()),
				company.getMx(),
				company.getName(),
				group.getName(),
				fromAddress,
				fromName,
				serviceContext.getPortalURL()
			});

		String mailId =
			StringPool.LESS_THAN + "knowledge_base.article." +
				article.getResourcePrimKey() + StringPool.AT +
					company.getMx() + StringPool.GREATER_THAN;

		Message message = new Message();

		message.put("companyId", article.getCompanyId());
		message.put("groupId", article.getGroupId());
		message.put("userId", article.getUserId());
		message.put("resourcePrimKey", article.getResourcePrimKey());
		message.put("portalURL", serviceContext.getPortalURL());
		message.put("fromName", fromName);
		message.put("fromAddress", fromAddress);
		message.put("subject", subject);
		message.put("body", body);
		message.put("replyToAddress", fromAddress);
		message.put("mailId", mailId);
		message.put("htmlFormat", Boolean.TRUE);

		MessageBusUtil.sendMessage("liferay/knowledge_base_admin", message);
	}

	protected void updateAttachments(Article article, String dirName)
		throws PortalException, SystemException {

		if (Validator.isNull(dirName)) {
			return;
		}

		deleteAttachments(article);

		addAttachments(article, dirName);
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