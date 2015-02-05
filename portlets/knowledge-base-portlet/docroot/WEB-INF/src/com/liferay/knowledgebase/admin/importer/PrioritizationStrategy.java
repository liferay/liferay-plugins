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
import com.liferay.knowledgebase.model.KBArticleConstants;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.KBArticleServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jesse Rao
 */
public class PrioritizationStrategy {

	public static PrioritizationStrategy create(
		long groupId, long parentKBFolderId,
		boolean prioritizeUpdatedKBArticles,
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
			groupId, parentKBFolderId, prioritizeUpdatedKBArticles,
			prioritizeByNumericalPrefix, existingKBArticlesMap);
	}

	public void addKBArticle(KBArticle kbArticle, String fileName)
		throws PortalException {

		updateKBArticle(kbArticle, fileName);

		if (!_prioritizeUpdatedKBArticles) {
			String parentKBArticleUrlTitle = getParentKBArticleUrlTitle(
				kbArticle);

			List<KBArticle> newKBArticles = getList(
				_newKBArticlesMap, parentKBArticleUrlTitle);

			newKBArticles.add(kbArticle);
		}
	}

	public void prioritizeKBArticles() throws PortalException {
		if (_prioritizeUpdatedKBArticles) {
			initializeNonImportedKBArticles();
		}

		if (_prioritizeByNumericalPrefix) {
			for (Map.Entry<String, Double> entry :
					_importedKBArticleUrlTitlesPrioritiesMap.entrySet()) {

				KBArticle kbArticle =
					KBArticleLocalServiceUtil.getKBArticleByUrlTitle(
						_groupId, _parentKBFolderId, entry.getKey());

				KBArticleLocalServiceUtil.updatePriority(
					kbArticle.getResourcePrimKey(), entry.getValue());

				remove(_importedKBArticlesMap, kbArticle);
				remove(_importedKBArticleUrlTitlesMap, kbArticle.getUrlTitle());
				remove(_newKBArticlesMap, kbArticle);
			}
		}

		if (_prioritizeUpdatedKBArticles) {
			Map<String, Double> maxKBArticlePriorityMap =
				computeMaxKBArticlePriorityMap(_nonimportedKBArticlesMap);

			prioritizeKBArticles(
				_importedKBArticlesMap, maxKBArticlePriorityMap);
		}
		else {
			Map<String, Double> maxKBArticlePriorityMap =
				computeMaxKBArticlePriorityMap(_existingKBArticlesMap);

			prioritizeKBArticles(_newKBArticlesMap, maxKBArticlePriorityMap);
		}
	}

	public void updateKBArticle(KBArticle kbArticle, String fileName)
		throws PortalException {

		String parentKBArticleUrlTitle = getParentKBArticleUrlTitle(kbArticle);

		List<KBArticle> kbArticles = getList(
			_importedKBArticlesMap, parentKBArticleUrlTitle);

		kbArticles.add(kbArticle);

		List<String> importedUrlTitles = getList(
			_importedKBArticleUrlTitlesMap, parentKBArticleUrlTitle);

		importedUrlTitles.add(kbArticle.getUrlTitle());

		if (_prioritizeByNumericalPrefix) {
			double sectionFileEntryNamePrefix = getNumericalPrefix(fileName);

			if (sectionFileEntryNamePrefix > 0) {
				_importedKBArticleUrlTitlesPrioritiesMap.put(
					kbArticle.getUrlTitle(), sectionFileEntryNamePrefix);
			}
		}
	}

	protected PrioritizationStrategy(
		long groupId, long parentKBFolderId,
		boolean prioritizeUpdatedKBArticles,
		boolean prioritizeByNumericalPrefix,
		Map<String, List<KBArticle>> existingKBArticlesMap) {

		_groupId = groupId;
		_parentKBFolderId = parentKBFolderId;
		_prioritizeUpdatedKBArticles = prioritizeUpdatedKBArticles;
		_prioritizeByNumericalPrefix = prioritizeByNumericalPrefix;
		_existingKBArticlesMap = existingKBArticlesMap;
	}

	protected Map<String, Double> computeMaxKBArticlePriorityMap(
		Map<String, List<KBArticle>> kbArticlesMap) {

		Map<String, Double> maxKBArticlePriorityMap = new HashMap<>();

		for (Map.Entry<String, List<KBArticle>> entry :
				kbArticlesMap.entrySet()) {

			double maxKBArticlePriority = 0.0;

			List<KBArticle> kbArticles = entry.getValue();

			if (kbArticles == null) {
				continue;
			}

			for (KBArticle kbArticle : kbArticles) {
				double kbArticlePriority = kbArticle.getPriority();

				if (kbArticlePriority > maxKBArticlePriority) {
					maxKBArticlePriority = kbArticlePriority;
				}
			}

			maxKBArticlePriorityMap.put(entry.getKey(), maxKBArticlePriority);
		}

		return maxKBArticlePriorityMap;
	}

	protected <S, T> List<T> getList(Map<S, List<T>> map, S key) {
		List<T> list = map.get(key);

		if (list == null) {
			list = new ArrayList<>();
			map.put(key, list);
		}

		return list;
	}

	protected double getNumericalPrefix(String path) {
		int i = path.lastIndexOf(CharPool.SLASH);

		if (i == -1) {
			return KBArticleConstants.DEFAULT_PRIORITY;
		}

		String name = path.substring(i + 1);

		String numericalPrefix = StringUtil.extractLeadingDigits(name);

		if (Validator.isNull(numericalPrefix)) {
			return KBArticleConstants.DEFAULT_PRIORITY;
		}

		return Double.parseDouble(numericalPrefix);
	}

	protected String getParentKBArticleUrlTitle(KBArticle kbArticle)
		throws PortalException {

		KBArticle parentKBArticle = kbArticle.getParentKBArticle();

		if (parentKBArticle == null) {
			return StringPool.BLANK;
		}

		return parentKBArticle.getUrlTitle();
	}

	protected void initializeNonImportedKBArticles() {
		_nonimportedKBArticlesMap = new HashMap<>();

		for (Map.Entry<String, List<KBArticle>> entry :
				_existingKBArticlesMap.entrySet()) {

			List<String> importedKBArticleUrlTitles = getList(
				_importedKBArticleUrlTitlesMap, entry.getKey());

			List<KBArticle> nonimportedKBArticles = new ArrayList<>();

			for (KBArticle kbArticle : entry.getValue()) {
				String urlTitle = kbArticle.getUrlTitle();

				if (!importedKBArticleUrlTitles.contains(urlTitle)) {
					nonimportedKBArticles.add(kbArticle);
				}
			}

			_nonimportedKBArticlesMap.put(
				entry.getKey(), nonimportedKBArticles);
		}
	}

	protected void prioritizeKBArticles(
		Map<String, List<KBArticle>> kbArticlesMap,
		Map<String, Double> maxKBArticlePriorityMap) {

		for (Map.Entry<String, List<KBArticle>> entry :
				kbArticlesMap.entrySet()) {

			List<KBArticle> kbArticles = entry.getValue();

			if (kbArticles == null) {
				continue;
			}

			ListUtil.sort(kbArticles, new Comparator<KBArticle>() {

				@Override
				public int compare(KBArticle kbArticle1, KBArticle kbArticle2) {
					String urlTitle1 = kbArticle1.getUrlTitle();
					String urlTitle2 = kbArticle2.getUrlTitle();

					return urlTitle1.compareTo(urlTitle2);
				}

			});

			String parentKBArticleUrlTitle = entry.getKey();

			int size = kbArticles.size();

			for (int i = 0; i < size; i++) {
				KBArticle kbArticle = kbArticles.get(i);

				double maxPriority = 0.0;

				if (maxKBArticlePriorityMap.containsKey(
						parentKBArticleUrlTitle)) {

					maxPriority = maxKBArticlePriorityMap.get(
						parentKBArticleUrlTitle);
				}

				maxPriority++;

				maxKBArticlePriorityMap.put(
					parentKBArticleUrlTitle, maxPriority);

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

	private final Map<String, List<KBArticle>> _existingKBArticlesMap;
	private final long _groupId;
	private final Map<String, List<KBArticle>> _importedKBArticlesMap =
		new HashMap<>();
	private final Map<String, List<String>> _importedKBArticleUrlTitlesMap =
		new HashMap<>();
	private final Map<String, Double> _importedKBArticleUrlTitlesPrioritiesMap =
		new HashMap<>();
	private final Map<String, List<KBArticle>> _newKBArticlesMap =
		new HashMap<>();
	private Map<String, List<KBArticle>> _nonimportedKBArticlesMap;
	private final long _parentKBFolderId;
	private final boolean _prioritizeByNumericalPrefix;
	private final boolean _prioritizeUpdatedKBArticles;

}