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

package com.liferay.knowledgebase.service.impl;

import com.liferay.knowledgebase.DuplicateKBArticleUrlTitleException;
import com.liferay.knowledgebase.InvalidKBArticleUrlTitleException;
import com.liferay.knowledgebase.KBArticleContentException;
import com.liferay.knowledgebase.KBArticleParentException;
import com.liferay.knowledgebase.KBArticlePriorityException;
import com.liferay.knowledgebase.KBArticleSourceURLException;
import com.liferay.knowledgebase.KBArticleTitleException;
import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.admin.importer.KBArticleImporter;
import com.liferay.knowledgebase.admin.social.AdminActivityKeys;
import com.liferay.knowledgebase.admin.util.AdminSubscriptionSender;
import com.liferay.knowledgebase.admin.util.AdminUtil;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBArticleConstants;
import com.liferay.knowledgebase.model.KBFolder;
import com.liferay.knowledgebase.model.KBFolderConstants;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.base.KBArticleLocalServiceBaseImpl;
import com.liferay.knowledgebase.util.KnowledgeBaseConstants;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.knowledgebase.util.PortletKeys;
import com.liferay.knowledgebase.util.PortletPropsValues;
import com.liferay.knowledgebase.util.comparator.KBArticlePriorityComparator;
import com.liferay.knowledgebase.util.comparator.KBArticleVersionComparator;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.dao.orm.Conjunction;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.systemevent.SystemEventHierarchyEntryThreadLocal;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.model.SystemEventConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.SubscriptionSender;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLink;
import com.liferay.portlet.asset.model.AssetLinkConstants;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 * @author Edward Han
 */
public class KBArticleLocalServiceImpl extends KBArticleLocalServiceBaseImpl {

	@Override
	public KBArticle addKBArticle(
			long userId, long parentResourceClassNameId,
			long parentResourcePrimKey, String title, String urlTitle,
			String content, String description, String sourceURL,
			String[] sections, String[] selectedFileNames,
			ServiceContext serviceContext)
		throws PortalException {

		// KB article

		User user = userPersistence.findByPrimaryKey(userId);
		long groupId = serviceContext.getScopeGroupId();
		double priority = getPriority(groupId, parentResourcePrimKey);
		Date now = new Date();

		validate(title, content, sourceURL);
		validateParent(parentResourceClassNameId, parentResourcePrimKey);

		long kbFolderId = KnowledgeBaseUtil.getKBFolderId(
			parentResourceClassNameId, parentResourcePrimKey);

		validateUrlTitle(groupId, kbFolderId, urlTitle);

		long kbArticleId = counterLocalService.increment();

		long resourcePrimKey = counterLocalService.increment();

		long rootResourcePrimKey = getRootResourcePrimKey(
			resourcePrimKey, parentResourceClassNameId, parentResourcePrimKey);

		KBArticle kbArticle = kbArticlePersistence.create(kbArticleId);

		kbArticle.setUuid(serviceContext.getUuid());
		kbArticle.setResourcePrimKey(resourcePrimKey);
		kbArticle.setGroupId(groupId);
		kbArticle.setCompanyId(user.getCompanyId());
		kbArticle.setUserId(user.getUserId());
		kbArticle.setUserName(user.getFullName());
		kbArticle.setCreateDate(serviceContext.getCreateDate(now));
		kbArticle.setModifiedDate(serviceContext.getModifiedDate(now));
		kbArticle.setRootResourcePrimKey(rootResourcePrimKey);
		kbArticle.setParentResourceClassNameId(parentResourceClassNameId);
		kbArticle.setParentResourcePrimKey(parentResourcePrimKey);
		kbArticle.setKbFolderId(kbFolderId);
		kbArticle.setVersion(KBArticleConstants.DEFAULT_VERSION);
		kbArticle.setTitle(title);
		kbArticle.setUrlTitle(
			getUniqueUrlTitle(
				groupId, kbFolderId, kbArticleId, title, urlTitle));
		kbArticle.setContent(content);
		kbArticle.setDescription(description);
		kbArticle.setPriority(priority);
		kbArticle.setSections(
			StringUtil.merge(AdminUtil.escapeSections(sections)));
		kbArticle.setViewCount(0);
		kbArticle.setLatest(true);
		kbArticle.setMain(false);
		kbArticle.setStatus(WorkflowConstants.STATUS_DRAFT);
		kbArticle.setSourceURL(sourceURL);

		kbArticlePersistence.update(kbArticle);

		// Resources

		if (serviceContext.isAddGroupPermissions() ||
			serviceContext.isAddGuestPermissions()) {

			addKBArticleResources(
				kbArticle, serviceContext.isAddGroupPermissions(),
				serviceContext.isAddGuestPermissions());
		}
		else {
			addKBArticleResources(
				kbArticle, serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

		// Asset

		updateKBArticleAsset(
			userId, kbArticle, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds());

		// Attachments

		addKBArticleAttachments(userId, kbArticle, selectedFileNames);

		// Workflow

		WorkflowHandlerRegistryUtil.startWorkflowInstance(
			user.getCompanyId(), groupId, userId, KBArticle.class.getName(),
			resourcePrimKey, kbArticle, serviceContext);

		return kbArticle;
	}

	@Override
	public void addKBArticleResources(
			KBArticle kbArticle, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException {

		resourceLocalService.addResources(
			kbArticle.getCompanyId(), kbArticle.getGroupId(),
			kbArticle.getUserId(), KBArticle.class.getName(),
			kbArticle.getResourcePrimKey(), false, addGroupPermissions,
			addGuestPermissions);
	}

	@Override
	public void addKBArticleResources(
			KBArticle kbArticle, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException {

		resourceLocalService.addModelResources(
			kbArticle.getCompanyId(), kbArticle.getGroupId(),
			kbArticle.getUserId(), KBArticle.class.getName(),
			kbArticle.getResourcePrimKey(), groupPermissions, guestPermissions);
	}

	@Override
	public void addKBArticleResources(
			long kbArticleId, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException {

		KBArticle kbArticle = kbArticlePersistence.findByPrimaryKey(
			kbArticleId);

		addKBArticleResources(
			kbArticle, addGroupPermissions, addGuestPermissions);
	}

	@Override
	public void addKBArticleResources(
			long kbArticleId, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException {

		KBArticle kbArticle = kbArticlePersistence.findByPrimaryKey(
			kbArticleId);

		addKBArticleResources(kbArticle, groupPermissions, guestPermissions);
	}

	@Override
	public int addKBArticlesMarkdown(
			long userId, long groupId, long parentKbFolderId, String fileName,
			boolean prioritizeByNumericalPrefix, InputStream inputStream,
			ServiceContext serviceContext)
		throws PortalException {

		KBArticleImporter kbArticleImporter = new KBArticleImporter();

		return kbArticleImporter.processZipFile(
			userId, groupId, parentKbFolderId, prioritizeByNumericalPrefix,
			inputStream, serviceContext);
	}

	@Override
	public void addTempAttachment(
			long groupId, long userId, String fileName, String tempFolderName,
			InputStream inputStream, String mimeType)
		throws PortalException {

		TempFileEntryUtil.addTempFileEntry(
			groupId, userId, fileName, tempFolderName, inputStream, mimeType);
	}

	@Override
	public void deleteGroupKBArticles(long groupId) throws PortalException {

		// KB articles

		deleteKBArticles(groupId, KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		// Subscriptions

		Group group = groupLocalService.getGroup(groupId);

		List<Subscription> subscriptions =
			subscriptionLocalService.getSubscriptions(
				group.getCompanyId(), KBArticle.class.getName(), groupId);

		for (Subscription subscription : subscriptions) {
			unsubscribeGroupKBArticles(subscription.getUserId(), groupId);
		}
	}

	@Override
	@SystemEvent(
		action = SystemEventConstants.ACTION_SKIP,
		type = SystemEventConstants.TYPE_DELETE
	)
	public KBArticle deleteKBArticle(KBArticle kbArticle)
		throws PortalException {

		// Child KB articles

		deleteKBArticles(
			kbArticle.getGroupId(), kbArticle.getResourcePrimKey());

		// Resources

		resourceLocalService.deleteResource(
			kbArticle.getCompanyId(), KBArticle.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, kbArticle.getResourcePrimKey());

		// KB articles

		kbArticlePersistence.removeByResourcePrimKey(
			kbArticle.getResourcePrimKey());

		// KB comments

		kbCommentLocalService.deleteKBComments(
			KBArticle.class.getName(), kbArticle.getResourcePrimKey());

		// Asset

		deleteAssets(kbArticle);

		// Ratings

		ratingsStatsLocalService.deleteStats(
			KBArticle.class.getName(), kbArticle.getResourcePrimKey());

		// Social

		socialActivityLocalService.deleteActivities(
			KBArticle.class.getName(), kbArticle.getResourcePrimKey());

		// Indexer

		Indexer<KBArticle> indexer = IndexerRegistryUtil.getIndexer(
			KBArticle.class);

		indexer.delete(kbArticle);

		// Attachments

		PortletFileRepositoryUtil.deletePortletFolder(
			kbArticle.getAttachmentsFolderId());

		// Subscriptions

		deleteSubscriptions(kbArticle);

		// Workflow

		workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
			kbArticle.getCompanyId(), kbArticle.getGroupId(),
			KBArticle.class.getName(), kbArticle.getResourcePrimKey());

		return kbArticle;
	}

	@Override
	public KBArticle deleteKBArticle(long resourcePrimKey)
		throws PortalException {

		KBArticle kbArticle = getLatestKBArticle(
			resourcePrimKey, WorkflowConstants.STATUS_ANY);

		return kbArticleLocalService.deleteKBArticle(kbArticle);
	}

	@Override
	public void deleteKBArticles(long groupId, long parentResourcePrimKey)
		throws PortalException {

		List<KBArticle> childKBArticles = getKBArticles(
			groupId, parentResourcePrimKey, WorkflowConstants.STATUS_ANY,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (KBArticle childKBArticle : childKBArticles) {
			kbArticleLocalService.deleteKBArticle(childKBArticle);
		}
	}

	@Override
	public void deleteKBArticles(long[] resourcePrimKeys)
		throws PortalException {

		List<KBArticle> kbArticles = getKBArticles(
			resourcePrimKeys, WorkflowConstants.STATUS_ANY, null);

		for (KBArticle kbArticle : kbArticles) {
			kbArticleLocalService.deleteKBArticle(kbArticle);
		}
	}

	@Override
	public void deleteTempAttachment(
			long groupId, long userId, String fileName, String tempFolderName)
		throws PortalException {

		TempFileEntryUtil.deleteTempFileEntry(
			groupId, userId, fileName, tempFolderName);
	}

	@Override
	public KBArticle fetchFirstChildKBArticle(
		long groupId, long parentResourcePrimKey) {

		return kbArticlePersistence.fetchByG_P_L_First(
			groupId, parentResourcePrimKey, true,
			new KBArticlePriorityComparator(true));
	}

	@Override
	public KBArticle fetchKBArticleByUrlTitle(
		long groupId, long kbFolderId, String urlTitle) {

		urlTitle = StringUtil.replaceFirst(
			urlTitle, StringPool.SLASH, StringPool.BLANK);

		KBArticle kbArticle = fetchLatestKBArticleByUrlTitle(
			groupId, kbFolderId, urlTitle, WorkflowConstants.STATUS_APPROVED);

		if (kbArticle == null) {
			kbArticle = fetchLatestKBArticleByUrlTitle(
				groupId, kbFolderId, urlTitle,
				WorkflowConstants.STATUS_PENDING);
		}

		return kbArticle;
	}

	@Override
	public KBArticle fetchKBArticleByUrlTitle(
			long groupId, String kbFolderUrlTitle, String urlTitle)
		throws PortalException {

		urlTitle = StringUtil.replaceFirst(
			urlTitle, StringPool.SLASH, StringPool.BLANK);

		List<KBArticle> kbArticles = kbArticleFinder.findByUrlTitle(
			groupId, kbFolderUrlTitle, urlTitle, _STATUSES, 0, 1);

		if (kbArticles.isEmpty()) {
			return null;
		}

		return kbArticles.get(0);
	}

	@Override
	public KBArticle fetchLatestKBArticle(long resourcePrimKey, int status) {
		if (status == WorkflowConstants.STATUS_ANY) {
			return kbArticlePersistence.fetchByResourcePrimKey_First(
				resourcePrimKey, new KBArticleVersionComparator());
		}

		return kbArticlePersistence.fetchByR_S_First(
			resourcePrimKey, status, new KBArticleVersionComparator());
	}

	@Override
	public KBArticle fetchLatestKBArticleByUrlTitle(
		long groupId, long kbFolderId, String urlTitle, int status) {

		urlTitle = StringUtil.replaceFirst(
			urlTitle, StringPool.SLASH, StringPool.BLANK);

		List<KBArticle> kbArticles = null;

		OrderByComparator<KBArticle> orderByComparator =
			new KBArticleVersionComparator();

		if (status == WorkflowConstants.STATUS_ANY) {
			kbArticles = kbArticlePersistence.findByG_KBFI_UT(
				groupId, kbFolderId, urlTitle, 0, 1, orderByComparator);
		}
		else {
			kbArticles = kbArticlePersistence.findByG_KBFI_UT_ST(
				groupId, kbFolderId, urlTitle, status, 0, 1, orderByComparator);
		}

		if (kbArticles.isEmpty()) {
			return null;
		}

		return kbArticles.get(0);
	}

	@Override
	public List<KBArticle> getAllDescendantKBArticles(
		long resourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator) {

		return getAllDescendantKBArticles(
			resourcePrimKey, status, orderByComparator, false);
	}

	@Override
	public List<KBArticle> getCompanyKBArticles(
		long companyId, int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return kbArticlePersistence.findByC_L(
				companyId, true, start, end, orderByComparator);
		}
		else if (status == WorkflowConstants.STATUS_APPROVED) {
			return kbArticlePersistence.findByC_M(
				companyId, true, start, end, orderByComparator);
		}

		return kbArticlePersistence.findByC_S(
			companyId, status, start, end, orderByComparator);
	}

	@Override
	public int getCompanyKBArticlesCount(long companyId, int status) {
		if (status == WorkflowConstants.STATUS_ANY) {
			return kbArticlePersistence.countByC_L(companyId, true);
		}
		else if (status == WorkflowConstants.STATUS_APPROVED) {
			return kbArticlePersistence.countByC_M(companyId, true);
		}

		return kbArticlePersistence.countByC_S(companyId, status);
	}

	@Override
	public List<KBArticle> getGroupKBArticles(
		long groupId, int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return kbArticlePersistence.findByG_L(
				groupId, true, start, end, orderByComparator);
		}
		else if (status == WorkflowConstants.STATUS_APPROVED) {
			return kbArticlePersistence.findByG_M(
				groupId, true, start, end, orderByComparator);
		}

		return kbArticlePersistence.findByG_S(
			groupId, status, start, end, orderByComparator);
	}

	@Override
	public int getGroupKBArticlesCount(long groupId, int status) {
		if (status == WorkflowConstants.STATUS_ANY) {
			return kbArticlePersistence.countByG_L(groupId, true);
		}
		else if (status == WorkflowConstants.STATUS_APPROVED) {
			return kbArticlePersistence.countByG_M(groupId, true);
		}

		return kbArticlePersistence.countByG_S(groupId, status);
	}

	@Override
	public KBArticle getKBArticle(long resourcePrimKey, int version)
		throws PortalException {

		return kbArticlePersistence.findByR_V(resourcePrimKey, version);
	}

	@Override
	public List<KBArticle> getKBArticleAndAllDescendantKBArticles(
		long resourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator) {

		return getAllDescendantKBArticles(
			resourcePrimKey, status, orderByComparator, true);
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             #getKBArticleAndAllDescendantKBArticles(long, int,
	 *             com.liferay.portal.kernel.util.OrderByComparator)}
	 */
	@Deprecated
	@Override
	public List<KBArticle> getKBArticleAndAllDescendants(
		long resourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator) {

		return getKBArticleAndAllDescendantKBArticles(
			resourcePrimKey, status, orderByComparator);
	}

	@Override
	public KBArticle getKBArticleByUrlTitle(
			long groupId, long kbFolderId, String urlTitle)
		throws PortalException {

		urlTitle = StringUtil.replaceFirst(
			urlTitle, StringPool.SLASH, StringPool.BLANK);

		// Get the latest KB article that is approved, if none are approved, get
		// the latest unapproved KB article

		KBArticle kbArticle = fetchKBArticleByUrlTitle(
			groupId, kbFolderId, urlTitle);

		if (kbArticle == null) {
			throw new NoSuchArticleException(
				"No KBArticle exists with the key {groupId=" + groupId +
					", kbFolderId=" + kbFolderId + ", urlTitle=" + urlTitle +
						"}");
		}

		return kbArticle;
	}

	@Override
	public KBArticle getKBArticleByUrlTitle(
			long groupId, String kbFolderUrlTitle, String urlTitle)
		throws PortalException {

		urlTitle = StringUtil.replaceFirst(
			urlTitle, StringPool.SLASH, StringPool.BLANK);

		KBArticle kbArticle = fetchKBArticleByUrlTitle(
			groupId, kbFolderUrlTitle, urlTitle);

		if (kbArticle == null) {
			throw new NoSuchArticleException(
				"No KBArticle with the key {groupId=" + groupId +
					", urlTitle=" + urlTitle + "} found in a folder with URL " +
						"title " + kbFolderUrlTitle);
		}

		return kbArticle;
	}

	@Override
	public List<KBArticle> getKBArticles(
		long groupId, long parentResourcePrimKey, int status, int start,
		int end, OrderByComparator<KBArticle> orderByComparator) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return kbArticlePersistence.findByG_P_L(
				groupId, parentResourcePrimKey, true, start, end,
				orderByComparator);
		}
		else if (status == WorkflowConstants.STATUS_APPROVED) {
			return kbArticlePersistence.findByG_P_M(
				groupId, parentResourcePrimKey, true, start, end,
				orderByComparator);
		}

		return kbArticlePersistence.findByG_P_S(
			groupId, parentResourcePrimKey, status, start, end,
			orderByComparator);
	}

	@Override
	public List<KBArticle> getKBArticles(
		long[] resourcePrimKeys, int status,
		OrderByComparator<KBArticle> orderByComparator) {

		List<KBArticle> kbArticles = new ArrayList<>();

		Long[][] params = new Long[][] {ArrayUtil.toArray(resourcePrimKeys)};

		while ((params = KnowledgeBaseUtil.getParams(params[0])) != null) {
			List<KBArticle> curKBArticles = null;

			if (status == WorkflowConstants.STATUS_ANY) {
				curKBArticles = kbArticlePersistence.findByR_L(
					ArrayUtil.toArray(params[1]), true);
			}
			else if (status == WorkflowConstants.STATUS_APPROVED) {
				curKBArticles = kbArticlePersistence.findByR_M(
					ArrayUtil.toArray(params[1]), true);
			}
			else {
				curKBArticles = kbArticlePersistence.findByR_S(
					ArrayUtil.toArray(params[1]), status);
			}

			kbArticles.addAll(curKBArticles);
		}

		if (orderByComparator != null) {
			kbArticles = ListUtil.sort(kbArticles, orderByComparator);
		}
		else {
			kbArticles = KnowledgeBaseUtil.sort(resourcePrimKeys, kbArticles);
		}

		return Collections.unmodifiableList(kbArticles);
	}

	@Override
	public int getKBArticlesCount(
		long groupId, long parentResourcePrimKey, int status) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return kbArticlePersistence.countByG_P_L(
				groupId, parentResourcePrimKey, true);
		}
		else if (status == WorkflowConstants.STATUS_APPROVED) {
			return kbArticlePersistence.countByG_P_M(
				groupId, parentResourcePrimKey, true);
		}

		return kbArticlePersistence.countByG_P_S(
			groupId, parentResourcePrimKey, status);
	}

	@Override
	public List<KBArticle> getKBArticleVersions(
		long resourcePrimKey, int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {

		if (status == WorkflowConstants.STATUS_ANY) {
			return kbArticlePersistence.findByResourcePrimKey(
				resourcePrimKey, start, end, orderByComparator);
		}

		return kbArticlePersistence.findByR_S(
			resourcePrimKey, status, start, end, orderByComparator);
	}

	@Override
	public int getKBArticleVersionsCount(long resourcePrimKey, int status) {
		if (status == WorkflowConstants.STATUS_ANY) {
			return kbArticlePersistence.countByResourcePrimKey(resourcePrimKey);
		}

		return kbArticlePersistence.countByR_S(resourcePrimKey, status);
	}

	@Override
	public int getKBFolderKBArticlesCount(
		long groupId, long kbFolderId, int status) {

		return kbArticlePersistence.countByG_KBFI_S(
			groupId, kbFolderId, status);
	}

	@Override
	public KBArticle getLatestKBArticle(long resourcePrimKey, int status)
		throws PortalException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return kbArticlePersistence.findByResourcePrimKey_First(
				resourcePrimKey, new KBArticleVersionComparator());
		}

		return kbArticlePersistence.findByR_S_First(
			resourcePrimKey, status, new KBArticleVersionComparator());
	}

	@Override
	public KBArticle getLatestKBArticleByUrlTitle(
			long groupId, long kbFolderId, String urlTitle, int status)
		throws PortalException {

		urlTitle = StringUtil.replaceFirst(
			urlTitle, StringPool.SLASH, StringPool.BLANK);

		KBArticle latestKBArticle = fetchLatestKBArticleByUrlTitle(
			groupId, kbFolderId, urlTitle, status);

		if (latestKBArticle == null) {
			throw new NoSuchArticleException(
				"No KBArticle exists with the key {groupId=" + groupId +
					", kbFolderId=" + kbFolderId + ", urlTitle=" + urlTitle +
						", status=" + status + "}");
		}

		return latestKBArticle;
	}

	@Override
	public KBArticle[] getPreviousAndNextKBArticles(long kbArticleId)
		throws PortalException {

		KBArticle kbArticle = kbArticlePersistence.findByPrimaryKey(
			kbArticleId);

		KBArticle[] previousAndNextKBArticles = getPreviousAndNextKBArticles(
			kbArticle);

		KBArticle previousKBArticle = getPreviousKBArticle(
			kbArticle, previousAndNextKBArticles[0]);
		KBArticle nextKBArticle = getNextKBArticle(
			kbArticle, previousAndNextKBArticles[2]);

		return new KBArticle[] {previousKBArticle, kbArticle, nextKBArticle};
	}

	@Override
	public List<KBArticle> getSectionsKBArticles(
		long groupId, String[] sections, int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {

		String[] array = AdminUtil.escapeSections(sections);

		for (int i = 0; i < array.length; i++) {
			array[i] = StringUtil.quote(array[i], StringPool.PERCENT);
		}

		if (status == WorkflowConstants.STATUS_ANY) {
			return kbArticlePersistence.findByG_S_L(
				groupId, array, true, start, end, orderByComparator);
		}
		else if (status == WorkflowConstants.STATUS_APPROVED) {
			return kbArticlePersistence.findByG_S_M(
				groupId, array, true, start, end, orderByComparator);
		}

		return kbArticlePersistence.findByG_S_S(
			groupId, array, status, start, end, orderByComparator);
	}

	@Override
	public int getSectionsKBArticlesCount(
		long groupId, String[] sections, int status) {

		String[] array = AdminUtil.escapeSections(sections);

		for (int i = 0; i < array.length; i++) {
			array[i] = StringUtil.quote(array[i], StringPool.PERCENT);
		}

		if (status == WorkflowConstants.STATUS_ANY) {
			return kbArticlePersistence.countByG_S_L(groupId, array, true);
		}
		else if (status == WorkflowConstants.STATUS_APPROVED) {
			return kbArticlePersistence.countByG_S_M(groupId, array, true);
		}

		return kbArticlePersistence.countByG_S_S(groupId, array, status);
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #getKBArticles(long, long,
	 *             int, int, int,
	 *             com.liferay.portal.kernel.util.OrderByComparator)}
	 */
	@Deprecated
	@Override
	public List<KBArticle> getSiblingKBArticles(
		long groupId, long parentResourcePrimKey, int status, int start,
		int end, OrderByComparator<KBArticle> orderByComparator) {

		return getKBArticles(
			groupId, parentResourcePrimKey, status, start, end,
			orderByComparator);
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #getKBArticlesCount(long,
	 *             long, int)}
	 */
	@Deprecated
	@Override
	public int getSiblingKBArticlesCount(
		long groupId, long parentResourcePrimKey, int status) {

		return getKBArticlesCount(groupId, parentResourcePrimKey, status);
	}

	@Override
	public String[] getTempAttachmentNames(
			long groupId, long userId, String tempFolderName)
		throws PortalException {

		return TempFileEntryUtil.getTempFileNames(
			groupId, userId, tempFolderName);
	}

	@Override
	public void moveKBArticle(
			long userId, long resourcePrimKey, long parentResourceClassNameId,
			long parentResourcePrimKey, double priority)
		throws PortalException {

		// KB article

		validate(priority);

		updatePermissionFields(
			resourcePrimKey, parentResourceClassNameId, parentResourcePrimKey);

		long kbFolderClassNameId = classNameLocalService.getClassNameId(
			KBFolderConstants.getClassName());

		long kbFolderId = KBFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		if (parentResourceClassNameId == kbFolderClassNameId) {
			kbFolderId = parentResourcePrimKey;
		}
		else {
			KBArticle latestKBArticle = getLatestKBArticle(
				parentResourcePrimKey, WorkflowConstants.STATUS_ANY);

			kbFolderId = latestKBArticle.getKbFolderId();
		}

		List<KBArticle> kbArticles = getKBArticleVersions(
			resourcePrimKey, WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, new KBArticleVersionComparator());

		for (KBArticle curKBArticle : kbArticles) {
			curKBArticle.setParentResourceClassNameId(
				parentResourceClassNameId);
			curKBArticle.setParentResourcePrimKey(parentResourcePrimKey);

			curKBArticle.setKbFolderId(kbFolderId);
			curKBArticle.setPriority(priority);

			kbArticlePersistence.update(curKBArticle);
		}

		KBArticle kbArticle = getLatestKBArticle(
			resourcePrimKey, WorkflowConstants.STATUS_ANY);

		if (kbArticle.getKbFolderId() != kbFolderId) {
			List<KBArticle> descendantKBArticles = getAllDescendantKBArticles(
				resourcePrimKey, WorkflowConstants.STATUS_ANY, null);

			for (KBArticle curKBArticle : descendantKBArticles) {
				List<KBArticle> kbArticleVersions = getKBArticleVersions(
					curKBArticle.getResourcePrimKey(),
					WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, new KBArticleVersionComparator());

				for (KBArticle kbArticleVersion : kbArticleVersions) {
					kbArticleVersion.setKbFolderId(kbFolderId);

					kbArticlePersistence.update(kbArticleVersion);
				}
			}
		}

		// Social

		KBArticle latestKBArticle = getLatestKBArticle(
			resourcePrimKey, WorkflowConstants.STATUS_ANY);

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		extraDataJSONObject.put("title", latestKBArticle.getTitle());

		if (latestKBArticle.isApproved() || !latestKBArticle.isFirstVersion()) {
			socialActivityLocalService.addActivity(
				userId, latestKBArticle.getGroupId(), KBArticle.class.getName(),
				resourcePrimKey, AdminActivityKeys.MOVE_KB_ARTICLE,
				extraDataJSONObject.toString(), 0);
		}
	}

	@Override
	public List<KBArticle> search(
		long groupId, String title, String content, int status, Date startDate,
		Date endDate, boolean andOperator, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			groupId, title, content, status, startDate, endDate, andOperator);

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	@Override
	public void subscribeGroupKBArticles(long userId, long groupId)
		throws PortalException {

		subscriptionLocalService.addSubscription(
			userId, groupId, KBArticle.class.getName(), groupId);
	}

	@Override
	public void subscribeKBArticle(
			long userId, long groupId, long resourcePrimKey)
		throws PortalException {

		subscriptionLocalService.addSubscription(
			userId, groupId, KBArticle.class.getName(), resourcePrimKey);
	}

	@Override
	public void unsubscribeGroupKBArticles(long userId, long groupId)
		throws PortalException {

		subscriptionLocalService.deleteSubscription(
			userId, KBArticle.class.getName(), groupId);
	}

	@Override
	public void unsubscribeKBArticle(long userId, long resourcePrimKey)
		throws PortalException {

		subscriptionLocalService.deleteSubscription(
			userId, KBArticle.class.getName(), resourcePrimKey);
	}

	@Override
	public KBArticle updateKBArticle(
			long userId, long resourcePrimKey, String title, String content,
			String description, String sourceURL, String[] sections,
			String[] selectedFileNames, long[] removeFileEntryIds,
			ServiceContext serviceContext)
		throws PortalException {

		// KB article

		User user = userPersistence.findByPrimaryKey(userId);

		validate(title, content, sourceURL);

		KBArticle oldKBArticle = getLatestKBArticle(
			resourcePrimKey, WorkflowConstants.STATUS_ANY);

		int oldVersion = oldKBArticle.getVersion();

		KBArticle kbArticle = null;

		if (oldKBArticle.isApproved()) {
			long kbArticleId = counterLocalService.increment();

			kbArticle = kbArticlePersistence.create(kbArticleId);

			kbArticle.setUuid(serviceContext.getUuid());
			kbArticle.setResourcePrimKey(oldKBArticle.getResourcePrimKey());
			kbArticle.setGroupId(oldKBArticle.getGroupId());
			kbArticle.setCompanyId(user.getCompanyId());
			kbArticle.setUserId(user.getUserId());
			kbArticle.setUserName(user.getFullName());
			kbArticle.setCreateDate(oldKBArticle.getCreateDate());
			kbArticle.setRootResourcePrimKey(
				oldKBArticle.getRootResourcePrimKey());
			kbArticle.setParentResourceClassNameId(
				oldKBArticle.getParentResourceClassNameId());
			kbArticle.setParentResourcePrimKey(
				oldKBArticle.getParentResourcePrimKey());
			kbArticle.setKbFolderId(oldKBArticle.getKbFolderId());
			kbArticle.setVersion(oldVersion + 1);
			kbArticle.setUrlTitle(oldKBArticle.getUrlTitle());
			kbArticle.setPriority(oldKBArticle.getPriority());
			kbArticle.setViewCount(oldKBArticle.getViewCount());
		}
		else {
			kbArticle = oldKBArticle;
		}

		if (oldKBArticle.isPending()) {
			kbArticle.setStatus(WorkflowConstants.STATUS_PENDING);
		}
		else {
			kbArticle.setStatus(WorkflowConstants.STATUS_DRAFT);
		}

		kbArticle.setModifiedDate(serviceContext.getModifiedDate(null));
		kbArticle.setTitle(title);
		kbArticle.setContent(content);
		kbArticle.setDescription(description);
		kbArticle.setSourceURL(sourceURL);
		kbArticle.setSections(
			StringUtil.merge(AdminUtil.escapeSections(sections)));
		kbArticle.setLatest(true);
		kbArticle.setMain(false);

		kbArticlePersistence.update(kbArticle);

		if (oldKBArticle.isApproved()) {
			oldKBArticle.setLatest(false);

			kbArticlePersistence.update(oldKBArticle);
		}

		// Resources

		if ((serviceContext.getGroupPermissions() != null) ||
			(serviceContext.getGuestPermissions() != null)) {

			updateKBArticleResources(
				kbArticle, serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

		// Asset

		updateKBArticleAsset(
			userId, kbArticle, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds());

		// Attachments

		addKBArticleAttachments(userId, kbArticle, selectedFileNames);

		removeKBArticleAttachments(removeFileEntryIds);

		// Workflow

		WorkflowHandlerRegistryUtil.startWorkflowInstance(
			user.getCompanyId(), kbArticle.getGroupId(), userId,
			KBArticle.class.getName(), resourcePrimKey, kbArticle,
			serviceContext);

		return kbArticle;
	}

	@Override
	public void updateKBArticleAsset(
			long userId, KBArticle kbArticle, long[] assetCategoryIds,
			String[] assetTagNames, long[] assetLinkEntryIds)
		throws PortalException {

		// TODO

		long classTypeId = 0;

		AssetEntry assetEntry = assetEntryLocalService.updateEntry(
			userId, kbArticle.getGroupId(), kbArticle.getCreateDate(),
			kbArticle.getModifiedDate(), KBArticle.class.getName(),
			kbArticle.getClassPK(), kbArticle.getUuid(), classTypeId,
			assetCategoryIds, assetTagNames, false, null, null, null,
			ContentTypes.TEXT_HTML, kbArticle.getTitle(),
			kbArticle.getDescription(), null, null, null, 0, 0, null, false);

		assetLinkLocalService.updateLinks(
			userId, assetEntry.getEntryId(), assetLinkEntryIds,
			AssetLinkConstants.TYPE_RELATED);
	}

	@Override
	public void updateKBArticleResources(
			KBArticle kbArticle, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException {

		resourceLocalService.updateResources(
			kbArticle.getCompanyId(), kbArticle.getGroupId(),
			KBArticle.class.getName(), kbArticle.getResourcePrimKey(),
			groupPermissions, guestPermissions);
	}

	@Override
	public void updateKBArticlesPriorities(
			Map<Long, Double> resourcePrimKeyToPriorityMap)
		throws PortalException {

		for (double priority : resourcePrimKeyToPriorityMap.values()) {
			validate(priority);
		}

		long[] resourcePrimKeys = StringUtil.split(
			StringUtil.merge(resourcePrimKeyToPriorityMap.keySet()), 0L);

		List<KBArticle> kbArticles = getKBArticles(
			resourcePrimKeys, WorkflowConstants.STATUS_ANY, null);

		for (KBArticle kbArticle : kbArticles) {
			double priority = resourcePrimKeyToPriorityMap.get(
				kbArticle.getResourcePrimKey());

			updatePriority(kbArticle.getResourcePrimKey(), priority);
		}
	}

	@Override
	public void updatePriority(long resourcePrimKey, double priority) {
		List<KBArticle> kbArticleVersions = getKBArticleVersions(
			resourcePrimKey, WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		for (KBArticle kbArticle : kbArticleVersions) {
			kbArticle.setPriority(priority);

			kbArticlePersistence.update(kbArticle);
		}
	}

	@Override
	public KBArticle updateStatus(
			long userId, long resourcePrimKey, int status,
			ServiceContext serviceContext)
		throws PortalException {

		// KB article

		User user = userPersistence.findByPrimaryKey(userId);
		boolean main = false;
		Date now = new Date();

		if (status == WorkflowConstants.STATUS_APPROVED) {
			main = true;
		}

		KBArticle kbArticle = getLatestKBArticle(
			resourcePrimKey, WorkflowConstants.STATUS_ANY);

		kbArticle.setModifiedDate(serviceContext.getModifiedDate(now));
		kbArticle.setMain(main);
		kbArticle.setStatus(status);
		kbArticle.setStatusByUserId(user.getUserId());
		kbArticle.setStatusByUserName(user.getFullName());
		kbArticle.setStatusDate(serviceContext.getModifiedDate(now));

		kbArticlePersistence.update(kbArticle);

		if (status != WorkflowConstants.STATUS_APPROVED) {
			return kbArticle;
		}

		if (!kbArticle.isFirstVersion()) {
			KBArticle oldKBArticle = kbArticlePersistence.findByR_V(
				resourcePrimKey, kbArticle.getVersion() - 1);

			oldKBArticle.setMain(false);

			kbArticlePersistence.update(oldKBArticle);
		}

		// Asset

		AssetEntry assetEntry = assetEntryLocalService.getEntry(
			KBArticle.class.getName(), kbArticle.getKbArticleId());

		List<AssetLink> assetLinks = assetLinkLocalService.getDirectLinks(
			assetEntry.getEntryId(), AssetLinkConstants.TYPE_RELATED);

		long[] assetLinkEntryIds = StringUtil.split(
			ListUtil.toString(assetLinks, AssetLink.ENTRY_ID2_ACCESSOR), 0L);

		updateKBArticleAsset(
			userId, kbArticle, assetEntry.getCategoryIds(),
			assetEntry.getTagNames(), assetLinkEntryIds);

		SystemEventHierarchyEntryThreadLocal.push(KBArticle.class);

		try {
			assetEntryLocalService.deleteEntry(
				KBArticle.class.getName(), kbArticle.getKbArticleId());
		}
		finally {
			SystemEventHierarchyEntryThreadLocal.pop(KBArticle.class);
		}

		assetEntryLocalService.updateVisible(
			KBArticle.class.getName(), kbArticle.getResourcePrimKey(), true);

		// Social

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		extraDataJSONObject.put("title", kbArticle.getTitle());

		if (!kbArticle.isFirstVersion()) {
			socialActivityLocalService.addActivity(
				userId, kbArticle.getGroupId(), KBArticle.class.getName(),
				resourcePrimKey, AdminActivityKeys.UPDATE_KB_ARTICLE,
				extraDataJSONObject.toString(), 0);
		}
		else {
			socialActivityLocalService.addActivity(
				userId, kbArticle.getGroupId(), KBArticle.class.getName(),
				resourcePrimKey, AdminActivityKeys.ADD_KB_ARTICLE,
				extraDataJSONObject.toString(), 0);
		}

		// Indexer

		Indexer<KBArticle> indexer = IndexerRegistryUtil.getIndexer(
			KBArticle.class);

		indexer.reindex(kbArticle);

		// Subscriptions

		notifySubscribers(userId, kbArticle, serviceContext);

		return kbArticle;
	}

	@Override
	public void updateViewCount(
			long userId, long resourcePrimKey, int viewCount)
		throws PortalException {

		KBArticle kbArticle = getLatestKBArticle(
			resourcePrimKey, WorkflowConstants.STATUS_ANY);

		kbArticle.setViewCount(viewCount);

		kbArticlePersistence.update(kbArticle);

		if (kbArticle.isApproved() || kbArticle.isFirstVersion()) {
			return;
		}

		kbArticle = getLatestKBArticle(
			resourcePrimKey, WorkflowConstants.STATUS_APPROVED);

		kbArticle.setViewCount(viewCount);

		kbArticlePersistence.update(kbArticle);
	}

	protected void addAttachment(
			long userId, long resourcePrimKey, String fileName,
			InputStream inputStream, String mimeType)
		throws PortalException {

		KBArticle kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(
			resourcePrimKey, WorkflowConstants.STATUS_ANY);

		PortletFileRepositoryUtil.addPortletFileEntry(
			kbArticle.getGroupId(), userId, KBArticle.class.getName(),
			kbArticle.getClassPK(), PortletKeys.KNOWLEDGE_BASE_ARTICLE,
			kbArticle.getAttachmentsFolderId(), inputStream, fileName, mimeType,
			false);
	}

	protected void addKBArticleAttachment(
			long userId, long groupId, long resourcePrimKey,
			String selectedFileName)
		throws PortalException {

		FileEntry tempFileEntry = TempFileEntryUtil.getTempFileEntry(
			groupId, userId, selectedFileName,
			KnowledgeBaseConstants.TEMP_FOLDER_NAME);

		InputStream inputStream = tempFileEntry.getContentStream();
		String mimeType = tempFileEntry.getMimeType();

		addAttachment(
			userId, resourcePrimKey, selectedFileName, inputStream, mimeType);

		if (tempFileEntry != null) {
			TempFileEntryUtil.deleteTempFileEntry(
				tempFileEntry.getFileEntryId());
		}
	}

	protected void addKBArticleAttachments(
			long userId, KBArticle kbArticle, String[] selectedFileNames)
		throws PortalException {

		if (ArrayUtil.isEmpty(selectedFileNames)) {
			return;
		}

		for (String selectedFileName : selectedFileNames) {
			addKBArticleAttachment(
				userId, kbArticle.getGroupId(), kbArticle.getResourcePrimKey(),
				selectedFileName);
		}
	}

	protected DynamicQuery buildDynamicQuery(
		long groupId, String title, String content, int status, Date startDate,
		Date endDate, boolean andOperator) {

		Junction junction = null;

		if (andOperator) {
			junction = RestrictionsFactoryUtil.conjunction();
		}
		else {
			junction = RestrictionsFactoryUtil.disjunction();
		}

		Map<String, String> terms = new HashMap<>();

		if (Validator.isNotNull(title)) {
			terms.put("title", title);
		}

		if (Validator.isNotNull(content)) {
			terms.put("content", content);
		}

		for (Map.Entry<String, String> entry : terms.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

			for (String keyword : KnowledgeBaseUtil.splitKeywords(value)) {
				Criterion criterion = RestrictionsFactoryUtil.ilike(
					key, StringUtil.quote(keyword, StringPool.PERCENT));

				disjunction.add(criterion);
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
			KBArticle.class, getClassLoader());

		if (status == WorkflowConstants.STATUS_ANY) {
			Property property = PropertyFactoryUtil.forName("latest");

			dynamicQuery.add(property.eq(Boolean.TRUE));
		}
		else if (status == WorkflowConstants.STATUS_APPROVED) {
			Property property = PropertyFactoryUtil.forName("main");

			dynamicQuery.add(property.eq(Boolean.TRUE));
		}

		if (groupId > 0) {
			Property property = PropertyFactoryUtil.forName("groupId");

			dynamicQuery.add(property.eq(groupId));
		}

		return dynamicQuery.add(junction);
	}

	protected void deleteAssets(KBArticle kbArticle) throws PortalException {
		assetEntryLocalService.deleteEntry(
			KBArticle.class.getName(), kbArticle.getClassPK());

		if (!kbArticle.isApproved() && !kbArticle.isFirstVersion()) {
			assetEntryLocalService.deleteEntry(
				KBArticle.class.getName(), kbArticle.getResourcePrimKey());
		}
	}

	protected void deleteSubscriptions(KBArticle kbArticle)
		throws PortalException {

		List<Subscription> subscriptions =
			subscriptionLocalService.getSubscriptions(
				kbArticle.getCompanyId(), KBArticle.class.getName(),
				kbArticle.getResourcePrimKey());

		for (Subscription subscription : subscriptions) {
			unsubscribeKBArticle(
				subscription.getUserId(), subscription.getClassPK());
		}
	}

	protected List<KBArticle> getAllDescendantKBArticles(
		long resourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator,
		boolean includeParentArticle) {

		List<KBArticle> kbArticles = null;

		if (includeParentArticle) {
			kbArticles = getKBArticles(
				new long[] {resourcePrimKey}, status, null);

			kbArticles = ListUtil.copy(kbArticles);
		}
		else {
			kbArticles = new ArrayList<>();
		}

		Long[][] params = new Long[][] {new Long[] {resourcePrimKey}};

		while ((params = KnowledgeBaseUtil.getParams(params[0])) != null) {
			List<KBArticle> curKBArticles = null;

			if (status == WorkflowConstants.STATUS_ANY) {
				curKBArticles = kbArticlePersistence.findByP_L(
					ArrayUtil.toArray(params[1]), true);
			}
			else if (status == WorkflowConstants.STATUS_APPROVED) {
				curKBArticles = kbArticlePersistence.findByP_M(
					ArrayUtil.toArray(params[1]), true);
			}
			else {
				curKBArticles = kbArticlePersistence.findByP_S(
					ArrayUtil.toArray(params[1]), status);
			}

			kbArticles.addAll(curKBArticles);

			long[] resourcePrimKeys = StringUtil.split(
				ListUtil.toString(curKBArticles, "resourcePrimKey"), 0L);

			params[0] = ArrayUtil.append(
				params[0], ArrayUtil.toArray(resourcePrimKeys));
		}

		if (orderByComparator != null) {
			kbArticles = ListUtil.sort(kbArticles, orderByComparator);
		}

		return Collections.unmodifiableList(kbArticles);
	}

	protected Map<String, String> getEmailKBArticleDiffs(KBArticle kbArticle) {
		Map<String, String> emailKBArticleDiffs = new HashMap<>();

		for (String param : new String[] {"content", "title"}) {
			String value = BeanPropertiesUtil.getString(kbArticle, param);

			try {
				value = AdminUtil.getKBArticleDiff(
					kbArticle.getResourcePrimKey(), kbArticle.getVersion() - 1,
					kbArticle.getVersion(), param);
			}
			catch (Exception e) {
				_log.error(e, e);
			}

			emailKBArticleDiffs.put(param, value);
		}

		return emailKBArticleDiffs;
	}

	protected KBArticle getNextAncestorKBArticle(
			long kbArticleId, KBArticle nextKBArticle)
		throws PortalException {

		if (nextKBArticle != null) {
			return nextKBArticle;
		}

		KBArticle kbArticle = kbArticlePersistence.findByPrimaryKey(
			kbArticleId);

		KBArticle parentKBArticle = kbArticle.getParentKBArticle();

		if (parentKBArticle == null) {
			return null;
		}

		KBArticle[] previousAndNextKBArticles = getPreviousAndNextKBArticles(
			parentKBArticle);

		return getNextAncestorKBArticle(
			parentKBArticle.getKbArticleId(), previousAndNextKBArticles[2]);
	}

	protected KBArticle getNextKBArticle(
			KBArticle kbArticle, KBArticle nextKBArticle)
		throws PortalException {

		KBArticle firstChildKBArticle = kbArticlePersistence.fetchByG_P_L_First(
			kbArticle.getGroupId(), kbArticle.getResourcePrimKey(), true,
			new KBArticlePriorityComparator(true));

		if (firstChildKBArticle != null) {
			return firstChildKBArticle;
		}

		return getNextAncestorKBArticle(
			kbArticle.getKbArticleId(), nextKBArticle);
	}

	protected KBArticle[] getPreviousAndNextKBArticles(KBArticle kbArticle) {
		List<KBArticle> kbArticles = kbArticlePersistence.findByG_P_L(
			kbArticle.getGroupId(), kbArticle.getParentResourcePrimKey(), true,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new KBArticlePriorityComparator(true));

		int index = kbArticles.indexOf(kbArticle);

		KBArticle[] previousAndNextKBArticles = {null, kbArticle, null};

		if (index > 0) {
			previousAndNextKBArticles[0] = kbArticles.get(index - 1);
		}

		if (index < (kbArticles.size() - 1)) {
			previousAndNextKBArticles[2] = kbArticles.get(index + 1);
		}

		return previousAndNextKBArticles;
	}

	protected KBArticle getPreviousKBArticle(
			KBArticle kbArticle, KBArticle previousKBArticle)
		throws PortalException {

		if (previousKBArticle == null) {
			return kbArticle.getParentKBArticle();
		}

		KBArticle lastSiblingChildKBArticle =
			kbArticlePersistence.fetchByG_P_L_Last(
				kbArticle.getGroupId(), previousKBArticle.getResourcePrimKey(),
				true, new KBArticlePriorityComparator(true));

		if (lastSiblingChildKBArticle == null) {
			return previousKBArticle;
		}

		return lastSiblingChildKBArticle;
	}

	protected double getPriority(long groupId, long parentResourcePrimKey) {
		if (!PortletPropsValues.ADMIN_KB_ARTICLE_INCREMENT_PRIORITY_ENABLED) {
			return KBArticleConstants.DEFAULT_VERSION;
		}

		List<KBArticle> kbArticles = getKBArticles(
			groupId, parentResourcePrimKey, WorkflowConstants.STATUS_ANY, 0, 1,
			new KBArticlePriorityComparator());

		if (kbArticles.isEmpty()) {
			return KBArticleConstants.DEFAULT_PRIORITY;
		}

		KBArticle kbArticle = kbArticles.get(0);

		return Math.floor(kbArticle.getPriority()) + 1;
	}

	protected long getRootResourcePrimKey(
			long resourcePrimKey, long parentResourceClassNameId,
			long parentResourcePrimKey)
		throws PortalException {

		if (parentResourcePrimKey ==
				KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			return resourcePrimKey;
		}

		long classNameId = classNameLocalService.getClassNameId(
			KBArticleConstants.getClassName());

		if (parentResourceClassNameId == classNameId) {
			KBArticle kbArticle = getLatestKBArticle(
				parentResourcePrimKey, WorkflowConstants.STATUS_ANY);

			return kbArticle.getRootResourcePrimKey();
		}

		return resourcePrimKey;
	}

	protected Date getTicketExpirationDate() {
		return new Date(System.currentTimeMillis() + _TICKET_EXPIRATION);
	}

	protected String getUniqueUrlTitle(
			long groupId, long kbFolderId, long kbArticleId, String title)
		throws PortalException {

		String urlTitle = KnowledgeBaseUtil.getUrlTitle(kbArticleId, title);

		String uniqueUrlTitle = urlTitle;

		if (kbFolderId == KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			int kbArticlesCount = kbArticlePersistence.countByG_KBFI_UT_ST(
				groupId, kbFolderId, uniqueUrlTitle, _STATUSES);

			for (int i = 1; kbArticlesCount > 0; i++) {
				uniqueUrlTitle = urlTitle + StringPool.DASH + i;

				kbArticlesCount = kbArticlePersistence.countByG_KBFI_UT_ST(
					groupId, kbFolderId, uniqueUrlTitle, _STATUSES);
			}

			return uniqueUrlTitle;
		}

		KBFolder kbFolder = kbFolderPersistence.findByPrimaryKey(kbFolderId);

		int kbArticlesCount = kbArticleFinder.countByUrlTitle(
			groupId, kbFolder.getUrlTitle(), uniqueUrlTitle, _STATUSES);

		for (int i = 1; kbArticlesCount > 0; i++) {
			uniqueUrlTitle = urlTitle + StringPool.DASH + i;

			kbArticlesCount = kbArticleFinder.countByUrlTitle(
				groupId, kbFolder.getUrlTitle(), uniqueUrlTitle, _STATUSES);
		}

		return uniqueUrlTitle;
	}

	protected String getUniqueUrlTitle(
			long groupId, long kbFolderId, long kbArticleId, String title,
			String urlTitle)
		throws PortalException {

		if (Validator.isNull(urlTitle)) {
			return getUniqueUrlTitle(groupId, kbFolderId, kbArticleId, title);
		}

		return urlTitle.substring(1);
	}

	protected boolean isValidFileName(String name) {
		if ((name == null) || name.contains(StringPool.BACK_SLASH) ||
			name.contains(StringPool.SLASH)) {

			return false;
		}

		return true;
	}

	protected void notifySubscribers(
			long userId, KBArticle kbArticle, ServiceContext serviceContext)
		throws PortalException {

		if (Validator.isNull(serviceContext.getLayoutFullURL())) {
			return;
		}

		PortletPreferences preferences =
			portletPreferencesLocalService.getPreferences(
				kbArticle.getCompanyId(), kbArticle.getGroupId(),
				PortletKeys.PREFS_OWNER_TYPE_GROUP,
				PortletKeys.PREFS_PLID_SHARED, PortletKeys.KNOWLEDGE_BASE_ADMIN,
				null);

		if (serviceContext.isCommandAdd() &&
			!AdminUtil.getEmailKBArticleAddedEnabled(preferences)) {

			return;
		}

		if (serviceContext.isCommandUpdate() &&
			!AdminUtil.getEmailKBArticleUpdatedEnabled(preferences)) {

			return;
		}

		String fromName = AdminUtil.getEmailFromName(
			preferences, kbArticle.getCompanyId());
		String fromAddress = AdminUtil.getEmailFromAddress(
			preferences, kbArticle.getCompanyId());

		String kbArticleContent = StringUtil.replace(
			kbArticle.getContent(), new String[] {"href=\"/", "src=\"/"},
			new String[] {
				"href=\"" + serviceContext.getPortalURL() + "/",
				"src=\"" + serviceContext.getPortalURL() + "/"
			});

		Map<String, String> kbArticleDiffs = getEmailKBArticleDiffs(kbArticle);

		for (String key : kbArticleDiffs.keySet()) {
			String value = StringUtil.replace(
				kbArticleDiffs.get(key), new String[] {"href=\"/", "src=\"/"},
				new String[] {
					"href=\"" + serviceContext.getPortalURL() + "/",
					"src=\"" + serviceContext.getPortalURL() + "/"
				});

			kbArticleDiffs.put(key, value);
		}

		String subject = null;
		String body = null;

		if (serviceContext.isCommandAdd()) {
			subject = AdminUtil.getEmailKBArticleAddedSubject(preferences);
			body = AdminUtil.getEmailKBArticleAddedBody(preferences);
		}
		else {
			subject = AdminUtil.getEmailKBArticleUpdatedSubject(preferences);
			body = AdminUtil.getEmailKBArticleUpdatedBody(preferences);
		}

		SubscriptionSender subscriptionSender = new AdminSubscriptionSender(
			kbArticle, serviceContext);

		subscriptionSender.setBody(body);
		subscriptionSender.setCompanyId(kbArticle.getCompanyId());
		subscriptionSender.setContextAttribute(
			"[$ARTICLE_CONTENT$]", kbArticleContent, false);
		subscriptionSender.setContextAttribute(
			"[$ARTICLE_CONTENT_DIFF$]", kbArticleDiffs.get("content"), false);
		subscriptionSender.setContextAttribute(
			"[$ARTICLE_TITLE$]", kbArticle.getTitle(), false);
		subscriptionSender.setContextAttribute(
			"[$ARTICLE_TITLE_DIFF$]", kbArticleDiffs.get("title"), false);
		subscriptionSender.setContextCreatorUserPrefix("ARTICLE");
		subscriptionSender.setCreatorUserId(kbArticle.getUserId());
		subscriptionSender.setCurrentUserId(userId);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setMailId("kb_article", kbArticle.getKbArticleId());
		subscriptionSender.setPortletId(serviceContext.getPortletId());
		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setScopeGroupId(kbArticle.getGroupId());
		subscriptionSender.setSubject(subject);

		subscriptionSender.addPersistedSubscribers(
			KBArticle.class.getName(), kbArticle.getGroupId());
		subscriptionSender.addPersistedSubscribers(
			KBArticle.class.getName(), kbArticle.getResourcePrimKey());

		while (!kbArticle.isRoot() &&
			   (kbArticle.getClassNameId() ==
				   kbArticle.getParentResourceClassNameId())) {

			kbArticle = getLatestKBArticle(
				kbArticle.getParentResourcePrimKey(),
				WorkflowConstants.STATUS_APPROVED);

			subscriptionSender.addPersistedSubscribers(
				KBArticle.class.getName(), kbArticle.getResourcePrimKey());
		}

		subscriptionSender.flushNotificationsAsync();
	}

	protected void removeKBArticleAttachments(long[] removeFileEntryIds)
		throws PortalException {

		if (ArrayUtil.isEmpty(removeFileEntryIds)) {
			return;
		}

		for (long removeFileEntryId : removeFileEntryIds) {
			PortletFileRepositoryUtil.deletePortletFileEntry(removeFileEntryId);
		}
	}

	protected void updatePermissionFields(
			long resourcePrimKey, long parentResourceClassNameId,
			long parentResourcePrimKey)
		throws PortalException {

		// See KBArticlePermission#contains

		KBArticle kbArticle = getLatestKBArticle(
			resourcePrimKey, WorkflowConstants.STATUS_ANY);

		if (kbArticle.getParentResourcePrimKey() == parentResourcePrimKey) {
			return;
		}

		long rootResourcePrimKey = getRootResourcePrimKey(
			resourcePrimKey, parentResourceClassNameId, parentResourcePrimKey);

		if (kbArticle.getRootResourcePrimKey() == rootResourcePrimKey) {
			return;
		}

		// Sync database

		List<KBArticle> kbArticles1 = getKBArticleAndAllDescendantKBArticles(
			resourcePrimKey, WorkflowConstants.STATUS_ANY, null);

		for (KBArticle kbArticle1 : kbArticles1) {
			List<KBArticle> kbArticles2 = getKBArticleVersions(
				kbArticle1.getResourcePrimKey(), WorkflowConstants.STATUS_ANY,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			for (KBArticle kbArticle2 : kbArticles2) {
				kbArticle2.setRootResourcePrimKey(rootResourcePrimKey);

				kbArticlePersistence.update(kbArticle2);
			}
		}

		// Sync indexed permission fields

		SearchEngineUtil.updatePermissionFields(
			KBArticle.class.getName(), String.valueOf(resourcePrimKey));
	}

	protected void validate(double priority) throws PortalException {
		if (priority <= 0) {
			throw new KBArticlePriorityException();
		}
	}

	protected void validate(String title, String content, String sourceURL)
		throws PortalException {

		if (Validator.isNull(title)) {
			throw new KBArticleTitleException();
		}

		if (Validator.isNull(content)) {
			throw new KBArticleContentException();
		}

		validateSourceURL(sourceURL);
	}

	protected void validateParent(
			long resourceClassNameId, long resourcePrimKey)
		throws PortalException {

		long kbArticleClassNameId = classNameLocalService.getClassNameId(
			KBArticleConstants.getClassName());
		long kbFolderClassNameId = classNameLocalService.getClassNameId(
			KBFolderConstants.getClassName());

		if ((resourceClassNameId != kbArticleClassNameId) &&
			(resourceClassNameId != kbFolderClassNameId)) {

			throw new KBArticleParentException(
				String.format(
					"Invalid parent with resource class name ID %s and " +
						"resource primary key %s",
					resourceClassNameId, resourcePrimKey));
		}
	}

	protected void validateSourceURL(String sourceURL) throws PortalException {
		if (Validator.isNull(sourceURL)) {
			return;
		}

		if (!Validator.isUrl(sourceURL)) {
			throw new KBArticleSourceURLException(sourceURL);
		}
	}

	protected void validateUrlTitle(
			long groupId, long kbFolderId, String urlTitle)
		throws PortalException {

		if (Validator.isNull(urlTitle)) {
			return;
		}

		if (!KnowledgeBaseUtil.isValidUrlTitle(urlTitle)) {
			throw new InvalidKBArticleUrlTitleException(
				"URL title must start with a '/' and contain only " +
					"alphanumeric characters, dashes, and underscores");
		}

		Collection<KBArticle> kbArticles = kbArticlePersistence.findByG_KBFI_UT(
			groupId, kbFolderId, urlTitle.substring(1));

		if (!kbArticles.isEmpty()) {
			throw new DuplicateKBArticleUrlTitleException(
				"Duplicate URL title " + urlTitle);
		}
	}

	private static final int[] _STATUSES = {
		WorkflowConstants.STATUS_APPROVED, WorkflowConstants.STATUS_PENDING
	};

	private static final long _TICKET_EXPIRATION = Time.HOUR;

	private static Log _log = LogFactoryUtil.getLog(
		KBArticleLocalServiceImpl.class);

}