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

import com.liferay.knowledgebase.DuplicateKBFolderNameException;
import com.liferay.knowledgebase.InvalidKBFolderNameException;
import com.liferay.knowledgebase.NoSuchFolderException;
import com.liferay.knowledgebase.model.KBFolder;
import com.liferay.knowledgebase.model.KBFolderConstants;
import com.liferay.knowledgebase.service.base.KBFolderLocalServiceBaseImpl;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class KBFolderLocalServiceImpl extends KBFolderLocalServiceBaseImpl {

	@Override
	public KBFolder addKBFolder(
			long userId, long groupId, long parentResourceClassNameId,
			long parentResourcePrimKey, String name, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// KB folder

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validateName(groupId, parentResourcePrimKey, name);
		validateParent(parentResourceClassNameId, parentResourcePrimKey);

		long kbFolderId = counterLocalService.increment();

		KBFolder kbFolder = kbFolderPersistence.create(kbFolderId);

		kbFolder.setUuid(serviceContext.getUuid());
		kbFolder.setGroupId(groupId);
		kbFolder.setCompanyId(user.getCompanyId());
		kbFolder.setUserId(userId);
		kbFolder.setUserName(user.getFullName());
		kbFolder.setCreateDate(now);
		kbFolder.setModifiedDate(now);
		kbFolder.setParentKBFolderId(parentResourcePrimKey);
		kbFolder.setName(name);
		kbFolder.setUrlTitle(
			getUniqueUrlTitle(
				groupId, parentResourcePrimKey, kbFolderId, name));
		kbFolder.setDescription(description);

		kbFolderPersistence.update(kbFolder);

		// Resources

		if (serviceContext.isAddGroupPermissions() ||
			serviceContext.isAddGuestPermissions()) {

			addKBFolderResources(
				kbFolder, serviceContext.isAddGroupPermissions(),
				serviceContext.isAddGuestPermissions());
		}
		else {
			addKBFolderResources(
				kbFolder, serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

		return kbFolder;
	}

	@Override
	public KBFolder deleteKBFolder(long kbFolderId)
		throws PortalException, SystemException {

		KBFolder kbFolder = kbFolderPersistence.findByPrimaryKey(kbFolderId);

		kbArticleLocalService.deleteKBArticles(
			kbFolder.getGroupId(), kbFolder.getKbFolderId());

		List<KBFolder> childKBFolders = kbFolderPersistence.findByG_P(
			kbFolder.getGroupId(), kbFolder.getKbFolderId());

		for (KBFolder childKBFolder : childKBFolders) {
			deleteKBFolder(childKBFolder.getKbFolderId());
		}

		return kbFolderPersistence.remove(kbFolder);
	}

	@Override
	public KBFolder fetchFirstChildKBFolder(long groupId, long kbFolderId)
		throws PortalException, SystemException {

		return kbFolderPersistence.fetchByG_P_First(groupId, kbFolderId, null);
	}

	@Override
	public KBFolder fetchKBFolder(long kbFolderId) throws SystemException {
		return kbFolderPersistence.fetchByPrimaryKey(kbFolderId);
	}

	@Override
	public KBFolder fetchKBFolderByUrlTitle(
			long groupId, long parentKbFolderId, String urlTitle)
		throws PortalException, SystemException {

		return kbFolderPersistence.fetchByG_P_UT(
			groupId, parentKbFolderId, urlTitle);
	}

	@Override
	public KBFolder getKBFolderByUrlTitle(
			long groupId, long parentKbFolderId, String urlTitle)
		throws PortalException, SystemException {

		return kbFolderPersistence.findByG_P_UT(
			groupId, parentKbFolderId, urlTitle);
	}

	@Override
	public List<KBFolder> getKBFolders(
			long groupId, long parentKBFolderId, int start, int end)
		throws PortalException, SystemException {

		return kbFolderPersistence.findByG_P(
			groupId, parentKBFolderId, start, end);
	}

	@Override
	public int getKBFoldersCount(long groupId, long parentKBFolderId)
		throws PortalException, SystemException {

		return kbFolderPersistence.countByG_P(groupId, parentKBFolderId);
	}

	@Override
	public void moveKBFolder(long kbFolderId, long parentKBFolderId)
		throws PortalException, SystemException {

		KBFolder kbFolder = kbFolderPersistence.findByPrimaryKey(kbFolderId);

		if (parentKBFolderId != KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			KBFolder parentKBFolder = kbFolderPersistence.findByPrimaryKey(
				parentKBFolderId);

			validateParent(kbFolder, parentKBFolder);

			parentKBFolderId = parentKBFolder.getKbFolderId();
		}

		kbFolder.setParentKBFolderId(parentKBFolderId);

		kbFolderPersistence.update(kbFolder);
	}

	@Override
	public KBFolder updateKBFolder(
			long parentResourceClassNameId, long parentResourcePrimKey,
			long kbFolderId, String name, String description)
		throws PortalException, SystemException {

		validateParent(parentResourceClassNameId, parentResourcePrimKey);

		KBFolder kbFolder = kbFolderPersistence.findByPrimaryKey(kbFolderId);

		kbFolder.setModifiedDate(new Date());
		kbFolder.setParentKBFolderId(parentResourcePrimKey);
		kbFolder.setName(name);
		kbFolder.setDescription(description);

		return kbFolderPersistence.update(kbFolder);
	}

	protected void addKBFolderResources(
			KBFolder kbFolder, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			kbFolder.getCompanyId(), kbFolder.getGroupId(),
			kbFolder.getUserId(), KBFolder.class.getName(),
			kbFolder.getKbFolderId(), false, addGroupPermissions,
			addGuestPermissions);
	}

	protected void addKBFolderResources(
			KBFolder kbFolder, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			kbFolder.getCompanyId(), kbFolder.getGroupId(),
			kbFolder.getUserId(), KBFolder.class.getName(),
			kbFolder.getKbFolderId(), groupPermissions, guestPermissions);
	}

	protected void getSubfolderIds(
			KBFolder parentKBFolder, Collection<Long> kbFolderIds)
		throws SystemException {

		Collection<KBFolder> kbFolders = kbFolderPersistence.findByG_P(
			parentKBFolder.getGroupId(), parentKBFolder.getKbFolderId());

		for (KBFolder kbFolder : kbFolders) {
			getSubfolderIds(kbFolder, kbFolderIds);
		}

		kbFolderIds.add(parentKBFolder.getKbFolderId());
	}

	protected String getUniqueUrlTitle(
			long groupId, long parentKbFolderId, long kbFolderId, String name)
		throws SystemException {

		String urlTitle = KnowledgeBaseUtil.getUrlTitle(kbFolderId, name);

		String uniqueUrlTitle = urlTitle;

		KBFolder kbFolder = null;

		for (int i = 1; kbFolder != null; i++) {
			uniqueUrlTitle = urlTitle + StringPool.DASH + i;

			kbFolder = kbFolderPersistence.fetchByG_P_UT(
				groupId, parentKbFolderId, uniqueUrlTitle);
		}

		return uniqueUrlTitle;
	}

	protected void validateName(
			long groupId, long parentKBFolderId, String name)
		throws PortalException, SystemException {

		if (Validator.isNull(name)) {
			throw new InvalidKBFolderNameException("KB folder name is null");
		}

		KBFolder kbFolder = kbFolderPersistence.fetchByG_P_N(
			groupId, parentKBFolderId, name);

		if (kbFolder != null) {
			throw new DuplicateKBFolderNameException(
				String.format("A KB folder with name %s already exists", name));
		}
	}

	protected void validateParent(KBFolder kbFolder, KBFolder parentKBFolder)
		throws PortalException, SystemException {

		if (kbFolder.getGroupId() != parentKBFolder.getGroupId()) {
			throw new NoSuchFolderException(
				String.format(
					"No KB folder with KB folder ID %s found in group %s",
					parentKBFolder.getKbFolderId(), kbFolder.getGroupId()));
		}

		Set<Long> subfolderIds = new HashSet<Long>();

		getSubfolderIds(kbFolder, subfolderIds);

		if (subfolderIds.contains(parentKBFolder.getKbFolderId())) {
			throw new InvalidKBFolderNameException(
				String.format(
					"Cannot move KBFolder %s inside its descendant KBFolder %s",
					kbFolder.getKbFolderId(), parentKBFolder.getKbFolderId()));
		}
	}

	protected void validateParent(
			long parentResourceClassNameId, long parentResourcePrimKey)
		throws PortalException, SystemException {

		long kbFolderClassNameId = classNameLocalService.getClassNameId(
			KBFolderConstants.getClassName());

		KBFolder parentKBFolder = null;

		if (parentResourceClassNameId == kbFolderClassNameId) {
			if (parentResourcePrimKey ==
					KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

				return;
			}

			parentKBFolder = kbFolderPersistence.fetchByPrimaryKey(
				parentResourcePrimKey);
		}

		if (parentKBFolder == null) {
			throw new NoSuchFolderException(
				String.format(
					"No KB folder found with KB folder ID %",
					parentResourcePrimKey));
		}
	}

}