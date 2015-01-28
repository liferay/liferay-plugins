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

package com.liferay.knowledgebase.display.selector;

import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBFolder;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.KBFolderLocalServiceUtil;
import com.liferay.knowledgebase.util.comparator.KBArticlePriorityComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;

/**
 * @author Adolfo Pérez
 */
public class KBFolderKBArticleSelector implements KBArticleSelector {

	@Override
	public KBArticle findByResourcePrimKey(
			long groupId, long ancestorResourcePrimKey, long resourcePrimKey)
		throws PortalException, SystemException {

		KBFolder ancestorKBFolder = KBFolderLocalServiceUtil.fetchKBFolder(
			ancestorResourcePrimKey);

		if (ancestorKBFolder == null) {
			return null;
		}

		KBArticle kbArticle = KBArticleLocalServiceUtil.fetchLatestKBArticle(
			resourcePrimKey, WorkflowConstants.STATUS_APPROVED);

		if ((kbArticle == null) || !isDescendant(kbArticle, ancestorKBFolder)) {
			return findClosestMatchingKBArticle(groupId, ancestorKBFolder);
		}

		return kbArticle;
	}

	@Override
	public KBArticle findByUrlTitle(
			long groupId, long ancestorResourcePrimKey, String kbFolderUrlTitle,
			String urlTitle)
		throws PortalException, SystemException {

		KBFolder ancestorKBFolder = KBFolderLocalServiceUtil.fetchKBFolder(
			ancestorResourcePrimKey);

		if (ancestorKBFolder == null) {
			return null;
		}

		KBArticle kbArticle =
			KBArticleLocalServiceUtil.fetchKBArticleByUrlTitle(
				groupId, kbFolderUrlTitle, urlTitle);

		if ((kbArticle == null) || !isDescendant(kbArticle, ancestorKBFolder)) {
			return findClosestMatchingKBArticle(
				groupId, ancestorKBFolder, kbFolderUrlTitle, urlTitle);
		}

		return kbArticle;
	}

	protected KBArticle findClosestMatchingKBArticle(
			long groupId, KBFolder ancestorKBFolder)
		throws PortalException, SystemException {

		List<KBFolder> kbFolders = KBFolderLocalServiceUtil.getKBFolders(
			groupId, ancestorKBFolder.getKbFolderId(), 0, 1);

		KBFolder kbFolder = null;

		if (!kbFolders.isEmpty()) {
			kbFolder = kbFolders.get(0);
		}
		else {
			kbFolder = ancestorKBFolder;
		}

		List<KBArticle> kbArticles =
			KBArticleLocalServiceUtil.getKBArticles(
				groupId, kbFolder.getKbFolderId(),
				WorkflowConstants.STATUS_APPROVED, 0, 1,
				new KBArticlePriorityComparator());

		if (kbArticles.isEmpty()) {
			return null;
		}

		return kbArticles.get(0);
	}

	protected KBArticle findClosestMatchingKBArticle(
			long groupId, KBFolder ancestorKBFolder, String kbFolderUrlTitle,
			String urlTitle)
		throws PortalException, SystemException {

		KBFolder kbFolder = getCandidateKBFolder(
			groupId, ancestorKBFolder, kbFolderUrlTitle);

		KBArticle kbArticle =
			KBArticleLocalServiceUtil.fetchKBArticleByUrlTitle(
				groupId, kbFolder.getKbFolderId(), urlTitle);

		if (kbArticle != null) {
			return kbArticle;
		}

		List<KBArticle> kbArticles =
			KBArticleLocalServiceUtil.getKBArticles(
				groupId, kbFolder.getKbFolderId(),
				WorkflowConstants.STATUS_APPROVED, 0, 1,
				new KBArticlePriorityComparator());

		if (kbArticles.isEmpty()) {
			return null;
		}

		return kbArticles.get(0);
	}

	protected KBFolder getCandidateKBFolder(
			long groupId, KBFolder ancestorKBFolder, String kbFolderUrlTitle)
		throws PortalException, SystemException {

		KBFolder kbFolder =
			KBFolderLocalServiceUtil.fetchKBFolderByUrlTitle(
				groupId, ancestorKBFolder.getKbFolderId(), kbFolderUrlTitle);

		if (kbFolder != null) {
			return kbFolder;
		}

		List<KBFolder> kbFolders = KBFolderLocalServiceUtil.getKBFolders(
			groupId, ancestorKBFolder.getKbFolderId(), 0, 1);

		if (!kbFolders.isEmpty()) {
			return kbFolders.get(0);
		}

		return ancestorKBFolder;
	}

	protected boolean isDescendant(
			KBArticle kbArticle, KBFolder ancestorKBFolder)
		throws PortalException, SystemException {

		KBFolder parentKBFolder = KBFolderLocalServiceUtil.fetchKBFolder(
			kbArticle.getKbFolderId());

		while ((parentKBFolder != null) &&
			   !parentKBFolder.equals(ancestorKBFolder)) {

			parentKBFolder = KBFolderLocalServiceUtil.fetchKBFolder(
				parentKBFolder.getParentKBFolderId());
		}

		return parentKBFolder != null;
	}

}