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
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * @author Adolfo Pérez
 */
public class KBArticleKBArticleSelector implements KBArticleSelector {

	@Override
	public KBArticleSelection findByResourcePrimKey(
			long groupId, String preferredKBFolderUrlTitle,
			long ancestorResourcePrimKey, long resourcePrimKey)
		throws PortalException {

		KBArticle ancestorKBArticle =
			KBArticleLocalServiceUtil.fetchLatestKBArticle(
				ancestorResourcePrimKey, WorkflowConstants.STATUS_APPROVED);

		if (ancestorKBArticle == null) {
			return new KBArticleSelection(null, false);
		}

		KBArticle kbArticle = KBArticleLocalServiceUtil.fetchLatestKBArticle(
			resourcePrimKey, WorkflowConstants.STATUS_APPROVED);

		return getClosestMatchingDescendantKBArticle(
			groupId, ancestorKBArticle, kbArticle);
	}

	@Override
	public KBArticleSelection findByUrlTitle(
			long groupId, String preferredKBFolderUrlTitle,
			long ancestorResourcePrimKey, String kbFolderUrlTitle,
			String urlTitle)
		throws PortalException {

		KBArticle ancestorKBArticle =
			KBArticleLocalServiceUtil.fetchLatestKBArticle(
				ancestorResourcePrimKey, WorkflowConstants.STATUS_APPROVED);

		if (ancestorKBArticle == null) {
			return null;
		}

		KBArticle kbArticle =
			KBArticleLocalServiceUtil.fetchLatestKBArticleByUrlTitle(
				groupId, ancestorKBArticle.getKbFolderId(), urlTitle,
				WorkflowConstants.STATUS_APPROVED);

		return getClosestMatchingDescendantKBArticle(
			groupId, ancestorKBArticle, kbArticle);
	}

	protected KBArticleSelection findClosestMatchingKBArticle(
			long groupId, KBArticle ancestorKBArticle, KBArticle kbArticle)
		throws PortalException {

		KBArticle candidateKBArticle = kbArticle;

		while (candidateKBArticle != null) {
			KBArticle matchingKBArticle =
				KBArticleLocalServiceUtil.fetchKBArticleByUrlTitle(
					groupId, ancestorKBArticle.getKbFolderId(),
					candidateKBArticle.getUrlTitle());

			if (matchingKBArticle != null) {
				return new KBArticleSelection(matchingKBArticle, false);
			}

			candidateKBArticle = candidateKBArticle.getParentKBArticle();
		}

		return new KBArticleSelection(ancestorKBArticle, false);
	}

	protected KBArticleSelection getClosestMatchingDescendantKBArticle(
			long groupId, KBArticle ancestorKBArticle, KBArticle kbArticle)
		throws PortalException {

		if (kbArticle == null) {
			return new KBArticleSelection(ancestorKBArticle, false);
		}

		if (isDescendant(kbArticle, ancestorKBArticle)) {
			return new KBArticleSelection(kbArticle, true);
		}

		return findClosestMatchingKBArticle(
			groupId, ancestorKBArticle, kbArticle);
	}

	protected boolean isDescendant(
			KBArticle kbArticle, KBArticle ancestorKBArticle)
		throws PortalException {

		if (kbArticle.getKbArticleId() == ancestorKBArticle.getKbArticleId()) {
			return true;
		}

		KBArticle parentKBArticle = kbArticle.getParentKBArticle();

		while ((parentKBArticle != null) &&
			   !parentKBArticle.equals(ancestorKBArticle)) {

			parentKBArticle = parentKBArticle.getParentKBArticle();
		}

		if (parentKBArticle != null) {
			return true;
		}

		return false;
	}

}