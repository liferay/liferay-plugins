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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Jesse Rao
 */
public class PrioritizationStrategy {

	public static PrioritizationStrategy create(
		long groupId, long parentKBFolderId, boolean prioritizeUpdatedArticles,
		boolean prioritizeByNumericalPrefix) throws SystemException {

		List<KBArticle> existingParentArticles =
			KBArticleServiceUtil.getKBArticles(
				groupId, parentKBFolderId, WorkflowConstants.STATUS_ANY,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		List<String> existingParentUrlTitles = new ArrayList<String>();

		Map<String, List<KBArticle>> existingChildArticlesMap =
				new HashMap<String, List<KBArticle>>();

		Map<String, List<String>> existingChildUrlTitlesMap =
			new HashMap<String, List<String>>();

		for (KBArticle existingParentArticle : existingParentArticles) {
			existingParentUrlTitles.add(existingParentArticle.getUrlTitle());

			long resourcePrimKey = existingParentArticle.getResourcePrimKey();

			List<KBArticle> existingChildArticles =
				KBArticleServiceUtil.getKBArticles(
					groupId, resourcePrimKey, WorkflowConstants.STATUS_ANY,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			List<String> existingChildUrlTitles = new ArrayList<String>();

			for (KBArticle existingChildArticle : existingChildArticles) {
				existingChildUrlTitles.add(existingChildArticle.getUrlTitle());
			}

			existingChildArticlesMap.put(
				existingParentArticle.getUrlTitle(), existingChildArticles);

			existingChildUrlTitlesMap.put(
				existingParentArticle.getUrlTitle(), existingChildUrlTitles);
		}

		return new PrioritizationStrategy(
			groupId, parentKBFolderId, prioritizeUpdatedArticles,
			prioritizeByNumericalPrefix, existingParentArticles,
			existingParentUrlTitles, existingChildArticlesMap,
			existingChildUrlTitlesMap);
	}

	public void addKBArticle(KBArticle kbArticle, String fileName)
		throws PortalException, SystemException {

		KBArticle parentKBArticle = kbArticle.getParentKBArticle();

		String parentKBArticleUrlTitle = StringPool.BLANK;

		if (parentKBArticle != null) {
			parentKBArticleUrlTitle = parentKBArticle.getUrlTitle();
		}

		List<KBArticle> kbArticles = _importedArticlesMap.get(
			parentKBArticleUrlTitle);

		if (kbArticles == null) {
			kbArticles = new ArrayList<KBArticle>();
		}

		kbArticles.add(kbArticle);

		_importedArticlesMap.put(parentKBArticleUrlTitle, kbArticles);

		List<String> importedUrlTitles = _importedUrlTitlesMap.get(
			parentKBArticleUrlTitle);

		if (importedUrlTitles == null) {
			importedUrlTitles = new ArrayList<String>();
		}

		importedUrlTitles.add(kbArticle.getUrlTitle());

		_importedUrlTitlesMap.put(parentKBArticleUrlTitle, importedUrlTitles);

		if (_prioritizeByNumericalPrefix) {
			double sectionFileEntryNamePrefix = _getNumericalPrefix(fileName);

			if (sectionFileEntryNamePrefix > 0) {
				_importedUrlTitlesPrioritiesMap.put(
					kbArticle.getUrlTitle(), sectionFileEntryNamePrefix);
			}
		}
	}

	public void prioritizeArticles() throws PortalException, SystemException {
		if (_prioritizeUpdatedArticles) {
			_initNonImportedArticles();
		}
		else {
			_initNewArticles();
		}

		if (_prioritizeByNumericalPrefix) {
			Set<String> importedKBArticleUrlTitles =
				_importedUrlTitlesPrioritiesMap.keySet();

			for (String importedKBArticleUrlTitle :
					importedKBArticleUrlTitles) {

				KBArticle kbArticle =
					KBArticleLocalServiceUtil.getKBArticleByUrlTitle(
						_groupId, _parentKBFolderId, importedKBArticleUrlTitle);

				double priority = _importedUrlTitlesPrioritiesMap.get(
					importedKBArticleUrlTitle);

				KBArticleLocalServiceUtil.updatePriority(
					kbArticle.getResourcePrimKey(), priority);

				/*
				 * Remove articles with numerical prefixes, and their URL
				 * titles, from lists of imported and new articles. Only
				 * articles without numerical prefixes need to be
				 * alphanumerically prioritized.
				 */

				if (_importedArticlesMap != null) {
					Set<String> keySet = _importedArticlesMap.keySet();

					for (String parentUrlTitle : keySet) {
						List<KBArticle> kbArticles = _importedArticlesMap.get(
							parentUrlTitle);

						if (kbArticles.contains(kbArticle)) {
							kbArticles.remove(kbArticle);
						}

						_importedArticlesMap.put(parentUrlTitle, kbArticles);
					}

					keySet = _importedUrlTitlesMap.keySet();

					for (String parentUrlTitle : keySet) {
						List<String> urlTitles = _importedUrlTitlesMap.get(
							parentUrlTitle);

						String urlTitle = kbArticle.getUrlTitle();

						if (urlTitles.contains(urlTitle)) {
							urlTitles.remove(urlTitle);
						}

						_importedUrlTitlesMap.put(parentUrlTitle, urlTitles);
					}
				}

				if (_newArticlesMap != null) {
					Set<String> keySet = _newArticlesMap.keySet();

					for (String parentUrlTitle : keySet) {
						List<KBArticle> kbArticles = _newArticlesMap.get(
							parentUrlTitle);

						if (kbArticles.contains(kbArticle)) {
							kbArticles.remove(kbArticle);
						}

						_newArticlesMap.put(parentUrlTitle, kbArticles);
					}

					keySet = _newUrlTitlesMap.keySet();

					for (String parentUrlTitle : keySet) {
						List<String> urlTitles = _newUrlTitlesMap.get(
							parentUrlTitle);

						String urlTitle = kbArticle.getUrlTitle();

						if (urlTitles.contains(urlTitle)) {
							urlTitles.remove(urlTitle);
						}

						_newUrlTitlesMap.put(parentUrlTitle, urlTitles);
					}
				}
			}
		}

		if (_prioritizeUpdatedArticles) {

			// prioritize all imported articles

			Map<String, Double> maxKBArticlePriorityMap =
				new HashMap<String, Double>();

			Set<String> keySet = _nonImportedArticlesMap.keySet();

			for (String parentUrlTitle : keySet) {
				double maxKBArticlePriority = 0.0;

				List<KBArticle> kbArticles = _nonImportedArticlesMap.get(
					parentUrlTitle);

				for (KBArticle kbArticle : kbArticles) {
					double kbArticlePriority = kbArticle.getPriority();

					if (kbArticlePriority > maxKBArticlePriority) {
						maxKBArticlePriority = kbArticlePriority;
					}
				}

				maxKBArticlePriorityMap.put(
					parentUrlTitle, maxKBArticlePriority);
			}

			// prioritize imported articles by URL title

			keySet = _importedArticlesMap.keySet();

			for (String parentUrlTitle : keySet) {
				List<String> urlTitles = _importedUrlTitlesMap.get(
					parentUrlTitle);

				ListUtil.sort(urlTitles);

				_importedUrlTitlesMap.put(parentUrlTitle, urlTitles);

				int size = urlTitles.size();

				for (int i = 0; i < size; i++) {
					String urlTitle = urlTitles.get(i);

					KBArticle kbArticle =
						KBArticleLocalServiceUtil.getKBArticleByUrlTitle(
							_groupId, _parentKBFolderId, urlTitle);

					double maxPriority = 0.0;

					if (maxKBArticlePriorityMap.containsKey(parentUrlTitle)) {
						maxPriority = maxKBArticlePriorityMap.get(
							parentUrlTitle);
					}

					maxPriority++;

					maxKBArticlePriorityMap.put(parentUrlTitle, maxPriority);

					KBArticleLocalServiceUtil.updatePriority(
						kbArticle.getResourcePrimKey(), maxPriority);
				}
			}
		}
		else {

			// prioritize only new articles

			Map<String, Double> maxKBArticlePriorityMap =
				new HashMap<String, Double>();

			Set<String> keySet = _existingArticlesMap.keySet();

			for (String parentUrlTitle : keySet) {
				double maxKBArticlePriority = 0.0;

				List<KBArticle> kbArticles = _existingArticlesMap.get(
					parentUrlTitle);

				for (KBArticle kbArticle : kbArticles) {
					double kbArticlePriority = kbArticle.getPriority();

					if (kbArticlePriority > maxKBArticlePriority) {
						maxKBArticlePriority = kbArticlePriority;
					}
				}

				maxKBArticlePriorityMap.put(
					parentUrlTitle, maxKBArticlePriority);
			}

			// prioritize new articles by URL title

			keySet = _newArticlesMap.keySet();

			for (String parentUrlTitle : keySet) {
				List<String> urlTitles = _newUrlTitlesMap.get(parentUrlTitle);

				ListUtil.sort(urlTitles);

				_newUrlTitlesMap.put(parentUrlTitle, urlTitles);

				int size = urlTitles.size();

				for (int i = 0; i < size; i++) {
					String urlTitle = urlTitles.get(i);

					KBArticle kbArticle =
						KBArticleLocalServiceUtil.getKBArticleByUrlTitle(
							_groupId, _parentKBFolderId, urlTitle);

					double maxPriority = 0.0;

					if (maxKBArticlePriorityMap.containsKey(parentUrlTitle)) {
						maxPriority = maxKBArticlePriorityMap.get(
							parentUrlTitle);
					}

					maxPriority++;

					maxKBArticlePriorityMap.put(parentUrlTitle, maxPriority);

					KBArticleLocalServiceUtil.updatePriority(
						kbArticle.getResourcePrimKey(), maxPriority);
				}
			}
		}
	}

	protected PrioritizationStrategy(
		long groupId, long parentKBFolderId, boolean prioritizeUpdatedArticles,
		boolean prioritizeByNumericalPrefix,
		List<KBArticle> existingParentArticles,
		List<String> existingParentUrlTitles,
		Map<String, List<KBArticle>> existingChildArticlesMap,
		Map<String, List<String>> existingChildUrlTitlesMap) {

		_groupId = groupId;
		_parentKBFolderId = parentKBFolderId;

		_prioritizeUpdatedArticles = prioritizeUpdatedArticles;
		_prioritizeByNumericalPrefix = prioritizeByNumericalPrefix;

		_existingArticlesMap = new HashMap<String, List<KBArticle>>(
			existingChildArticlesMap);
		_existingArticlesMap.put(StringPool.BLANK, existingParentArticles);

		_existingUrlTitlesMap = new HashMap<String, List<String>>(
			existingChildUrlTitlesMap);
		_existingUrlTitlesMap.put(StringPool.BLANK, existingParentUrlTitles);

		_importedArticlesMap = new HashMap<String, List<KBArticle>>();
		_importedUrlTitlesMap = new HashMap<String, List<String>>();

		_importedUrlTitlesPrioritiesMap = new HashMap<String, Double>();
	}

	private double _getNumericalPrefix(String path) {
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

	private void _initNewArticles() {
		_newArticlesMap = new HashMap<String, List<KBArticle>>();

		Set<String> keySet = _importedArticlesMap.keySet();

		for (String parentUrlTitle : keySet) {
			List<KBArticle> importedArticles = _importedArticlesMap.get(
				parentUrlTitle);

			List<String> existingUrlTitles = _existingUrlTitlesMap.get(
				parentUrlTitle);

			if (existingUrlTitles == null) {
				existingUrlTitles = new ArrayList<String>();
			}

			List<KBArticle> newArticles = new ArrayList<KBArticle>();

			for (KBArticle kbArticle : importedArticles) {
				String urlTitle = kbArticle.getUrlTitle();

				if (!existingUrlTitles.contains(urlTitle)) {
					newArticles.add(kbArticle);
				}
			}

			_newArticlesMap.put(parentUrlTitle, newArticles);
		}

		_newUrlTitlesMap = new HashMap<String, List<String>>();

		keySet = _newArticlesMap.keySet();

		for (String parentUrlTitle : keySet) {
			List<KBArticle> kbArticles = _newArticlesMap.get(parentUrlTitle);

			List<String> kbUrlTitles = new ArrayList<String>();

			for (KBArticle kbArticle : kbArticles) {
				kbUrlTitles.add(kbArticle.getUrlTitle());
			}

			_newUrlTitlesMap.put(parentUrlTitle, kbUrlTitles);
		}
	}

	private void _initNonImportedArticles() {
		_nonImportedArticlesMap = new HashMap<String, List<KBArticle>>();

		Set<String> keySet = _existingArticlesMap.keySet();

		for (String parentUrlTitle : keySet) {
			List<KBArticle> existingArticles = _existingArticlesMap.get(
				parentUrlTitle);

			List<String> importedUrlTitles = _importedUrlTitlesMap.get(
				parentUrlTitle);

			if (importedUrlTitles == null) {
				importedUrlTitles = new ArrayList<String>();
			}

			List<KBArticle> nonImportedArticles = new ArrayList<KBArticle>();

			for (KBArticle kbArticle : existingArticles) {
				String urlTitle = kbArticle.getUrlTitle();

				if (!importedUrlTitles.contains(urlTitle)) {
					nonImportedArticles.add(kbArticle);
				}
			}

			_nonImportedArticlesMap.put(parentUrlTitle, nonImportedArticles);
		}

		_nonImportedUrlTitlesMap = new HashMap<String, List<String>>();

		keySet = _nonImportedArticlesMap.keySet();

		for (String parentUrlTitle : keySet) {
			List<KBArticle> kbArticles = _nonImportedArticlesMap.get(
				parentUrlTitle);

			List<String> kbUrlTitles = new ArrayList<String>();

			for (KBArticle kbArticle : kbArticles) {
				kbUrlTitles.add(kbArticle.getUrlTitle());
			}

			_nonImportedUrlTitlesMap.put(parentUrlTitle, kbUrlTitles);
		}
	}

	private Map<String, List<KBArticle>> _existingArticlesMap;
	private Map<String, List<String>> _existingUrlTitlesMap;
	private final long _groupId;
	private Map<String, List<KBArticle>> _importedArticlesMap;
	private Map<String, List<String>> _importedUrlTitlesMap;
	private Map<String, Double> _importedUrlTitlesPrioritiesMap;
	private Map<String, List<KBArticle>> _newArticlesMap;
	private Map<String, List<String>> _newUrlTitlesMap;
	private Map<String, List<KBArticle>> _nonImportedArticlesMap;
	private Map<String, List<String>> _nonImportedUrlTitlesMap;
	private final long _parentKBFolderId;
	private boolean _prioritizeByNumericalPrefix;
	private boolean _prioritizeUpdatedArticles;

}