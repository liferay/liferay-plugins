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

package com.liferay.knowledgebase.admin.importer;

import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.KBArticleServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jesse Rao
 */
public class PrioritizationStrategy {

	public static PrioritizationStrategy create(
			long groupId, long parentKBFolderId,
			boolean prioritizeByNumericalPrefix) {

		Map<String, List<KBArticle>> existingKBArticlesMap = new HashMap<>();

		List<KBArticle> existingParentKBArticles =
			KBArticleServiceUtil.getKBArticles(
				groupId, parentKBFolderId, WorkflowConstants.STATUS_ANY,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		existingKBArticlesMap.put(StringPool.BLANK, existingParentKBArticles);

		for (KBArticle existingParentKBArticle : existingParentKBArticles) {
			long resourcePrimKey = existingParentKBArticle.getResourcePrimKey();

			List<KBArticle> existingChildKBArticles =
				KBArticleServiceUtil.getKBArticles(
					groupId, resourcePrimKey, WorkflowConstants.STATUS_ANY,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			existingKBArticlesMap.put(
				existingParentKBArticle.getUrlTitle(), existingChildKBArticles);
		}

		return new PrioritizationStrategy(
			groupId, parentKBFolderId, prioritizeByNumericalPrefix,
			existingKBArticlesMap);
	}

	public void addKBArticle(KBArticle kbArticle, String filePath)
		throws PortalException {

		handleNumericalPrefix(kbArticle, filePath);
	}

	public void prioritizeKBArticles() throws PortalException {
		if (_prioritizeByNumericalPrefix) {
			for (Map.Entry<String, Double> entry :
					_importedKBArticleUrlTitlesPrioritiesMap.entrySet()) {

				if (entry.getValue() < 1.0) {
					continue;
				}

				KBArticle kbArticle =
					KBArticleLocalServiceUtil.getKBArticleByUrlTitle(
						_groupId, _parentKBFolderId, entry.getKey());

				KBArticleLocalServiceUtil.updatePriority(
					kbArticle.getResourcePrimKey(), entry.getValue());

				remove(_importedKBArticlesMap, kbArticle);
			}
		}

		prioritizeKBArticles(_importedKBArticlesMap);
	}

	public void updateKBArticle(KBArticle kbArticle, String filePath)
		throws PortalException {

		handleNumericalPrefix(kbArticle, filePath);
	}

	protected PrioritizationStrategy(
		long groupId, long parentKBFolderId,
		boolean prioritizeByNumericalPrefix,
		Map<String, List<KBArticle>> existingKBArticlesMap) {

		_groupId = groupId;
		_parentKBFolderId = parentKBFolderId;
		_prioritizeByNumericalPrefix = prioritizeByNumericalPrefix;
	}

	protected <S, T> List<T> getList(Map<S, List<T>> map, S key) {
		List<T> list = map.get(key);

		if (list == null) {
			list = new ArrayList<>();
			map.put(key, list);
		}

		return list;
	}

	protected double getNumericalPrefix(
		String filePath, boolean isChildArticleFile) {

		double numericalPrefix = -1.0;

		if (isChildArticleFile) {
			String fileName = filePath;

			int i = filePath.lastIndexOf(CharPool.SLASH);

			if (i != -1) {
				fileName = filePath.substring(i + 1);
			}

			String digits = StringUtil.extractLeadingDigits(fileName);

			if (Validator.isNull(digits)) {
				return numericalPrefix;
			}

			return GetterUtil.getDouble(digits);
		}
		else {
			String[] pathEntries = filePath.split(StringPool.SLASH);

			if (pathEntries == null) {
				String digits = StringUtil.extractLeadingDigits(filePath);

				if (Validator.isNull(digits)) {
					return numericalPrefix;
				}

				return GetterUtil.getDouble(digits);
			}

			int length = pathEntries.length;

			for (int i = length - 1; i > -1; i--) {
				String fileName = pathEntries[i];

				String digits = StringUtil.extractLeadingDigits(fileName);

				if (Validator.isNull(digits)) {
					continue;
				}

				numericalPrefix = GetterUtil.getDouble(digits);

				if (numericalPrefix >= 1.0) {
					return numericalPrefix;
				}
			}
		}

		return numericalPrefix;
	}

	protected String getParentKBArticleUrlTitle(KBArticle kbArticle)
		throws PortalException {

		KBArticle parentKBArticle = kbArticle.getParentKBArticle();

		if (parentKBArticle == null) {
			return StringPool.BLANK;
		}

		return parentKBArticle.getUrlTitle();
	}

	protected void handleNumericalPrefix(KBArticle kbArticle, String filePath)
			throws PortalException {

		String parentKBArticleUrlTitle = getParentKBArticleUrlTitle(kbArticle);

		List<KBArticle> kbArticles = getList(
			_importedKBArticlesMap, parentKBArticleUrlTitle);

		kbArticles.add(kbArticle);

		if (_prioritizeByNumericalPrefix) {
			boolean isChildArticle = true;

			if (kbArticle.getParentKBArticle() == null) {
				isChildArticle = false;
			}

			double sectionFileEntryNamePrefix = getNumericalPrefix(
				filePath, isChildArticle);

			if (sectionFileEntryNamePrefix < 0.0) {
			}
			else if (sectionFileEntryNamePrefix < 1.0) {
				kbArticle.setPriority(1.0);

				_importedKBArticleUrlTitlesPrioritiesMap.put(
					kbArticle.getUrlTitle(), sectionFileEntryNamePrefix);
			}
			else {
				_importedKBArticleUrlTitlesPrioritiesMap.put(
					kbArticle.getUrlTitle(), sectionFileEntryNamePrefix);
			}
		}
	}

	protected void prioritizeKBArticles(
			Map<String, List<KBArticle>> kbArticlesMap)
		throws PortalException {

		for (Map.Entry<String, List<KBArticle>> entry :
				kbArticlesMap.entrySet()) {

			List<KBArticle> kbArticles = entry.getValue();

			if (kbArticles == null) {
				continue;
			}

			List<KBArticle> siblingKBArticles = null;

			if (Validator.isNull(entry.getKey())) {

				// Handle lead articles

				siblingKBArticles = KBArticleLocalServiceUtil.getKBArticles(
					_groupId, _parentKBFolderId, WorkflowConstants.STATUS_ANY,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
			}
			else {
				KBArticle parentKBArticle =
					KBArticleLocalServiceUtil.fetchKBArticleByUrlTitle(
						_groupId, _parentKBFolderId, entry.getKey());

				siblingKBArticles = KBArticleServiceUtil.getKBArticles(
					_groupId, parentKBArticle.getResourcePrimKey(),
					WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null);
			}

			List<KBArticle> siblingKBArticlesCopy = ListUtil.copy(
				siblingKBArticles);

			siblingKBArticlesCopy.removeAll(kbArticles);

			double maxPriority = 0.0;

			for (KBArticle sibling : siblingKBArticlesCopy) {
				double priority = sibling.getPriority();

				if (priority > maxPriority) {
					maxPriority = priority;
				}
			}

			int size = kbArticles.size();

			for (int i = 0; i < size; i++) {
				KBArticle kbArticle = kbArticles.get(i);

				if (kbArticle.getPriority() >= 1.0) {
					continue;
				}

				maxPriority++;

				KBArticleLocalServiceUtil.updatePriority(
					kbArticle.getResourcePrimKey(), maxPriority);
			}
		}
	}

	protected <S, T> void remove(Map<S, List<T>> map, T object) {
		for (Map.Entry<S, List<T>> entry : map.entrySet()) {
			List<T> list = entry.getValue();

			if (list == null) {
				continue;
			}

			list.remove(object);
		}
	}

	private final long _groupId;
	private final Map<String, List<KBArticle>> _importedKBArticlesMap =
		new HashMap<>();
	private final Map<String, Double> _importedKBArticleUrlTitlesPrioritiesMap =
		new HashMap<>();
	private final long _parentKBFolderId;
	private final boolean _prioritizeByNumericalPrefix;

}