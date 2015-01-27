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

	public void addImportedChildArticle(KBArticle childArticle, String fileName)
		throws PortalException, SystemException {

		if (_prioritizeByNumericalPrefix) {
			double sectionFileEntryNamePrefix = _getNumericalPrefix(fileName);

			if (sectionFileEntryNamePrefix > 0) {
				_importedUrlTitlesPrioritiesMap.put(
					childArticle.getUrlTitle(), sectionFileEntryNamePrefix);
			}
		}

		KBArticle parentArticle = childArticle.getParentKBArticle();

		if (parentArticle == null) {
			return;
		}

		String parentUrlTitle = parentArticle.getUrlTitle();

		List<KBArticle> childArticles = null;

		if (_importedChildArticlesMap.containsKey(parentUrlTitle)) {
			childArticles = _importedChildArticlesMap.get(parentUrlTitle);
		}
		else {
			childArticles = new ArrayList<KBArticle>();
		}

		childArticles.add(childArticle);

		_importedChildArticlesMap.put(parentUrlTitle, childArticles);

		List<String> childUrlTitles = null;

		if (_importedChildUrlTitlesMap.containsKey(parentUrlTitle)) {
			childUrlTitles = _importedChildUrlTitlesMap.get(parentUrlTitle);
		}
		else {
			childUrlTitles = new ArrayList<String>();
		}

		childUrlTitles.add(childArticle.getUrlTitle());

		_importedChildUrlTitlesMap.put(parentUrlTitle, childUrlTitles);
	}

	public void addImportedParentArticle(
		KBArticle parentArticle, String fileName) {

		_importedParentArticles.add(parentArticle);

		String importedParentUrlTitle = parentArticle.getUrlTitle();

		_importedParentUrlTitles.add(importedParentUrlTitle);

		if (_prioritizeByNumericalPrefix) {
			double folderNamePrefix = _getNumericalPrefix(fileName);

			if (folderNamePrefix > 0) {
				_importedUrlTitlesPrioritiesMap.put(
					parentArticle.getUrlTitle(), folderNamePrefix);
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

				if (_importedParentArticles != null) {
					if (_importedParentArticles.contains(kbArticle)) {
						_importedParentArticles.remove(kbArticle);
					}
				}

				if (_importedParentUrlTitles != null) {
					if (_importedParentUrlTitles.contains(
							importedKBArticleUrlTitle)) {

						_importedParentUrlTitles.remove(
							importedKBArticleUrlTitle);
					}
				}

				if (_newParentArticles != null) {
					if (_newParentArticles.contains(kbArticle)) {
						_newParentArticles.remove(kbArticle);
					}
				}

				if (_newParentUrlTitles != null) {
					if (_newParentUrlTitles.contains(
							importedKBArticleUrlTitle)) {

						_newParentUrlTitles.remove(importedKBArticleUrlTitle);
					}
				}

				Set<String> parentKBArticleUrlTitles =
					_importedChildArticlesMap.keySet();

				for (String parentKBArticleUrlTitle :
						parentKBArticleUrlTitles) {

					if (_importedChildArticlesMap != null) {
						List<KBArticle> importedChildKBArticles =
							_importedChildArticlesMap.get(
								parentKBArticleUrlTitle);

						if (importedChildKBArticles.contains(kbArticle)) {
							importedChildKBArticles.remove(kbArticle);
						}

						_importedChildArticlesMap.put(
							parentKBArticleUrlTitle, importedChildKBArticles);
					}

					if (_importedChildUrlTitlesMap != null) {
						List<String> importedChildKBArticlesUrlTitles =
							_importedChildUrlTitlesMap.get(
								parentKBArticleUrlTitle);

						if (importedChildKBArticlesUrlTitles.contains(
								importedKBArticleUrlTitle)) {

							importedChildKBArticlesUrlTitles.remove(
								importedKBArticleUrlTitle);
						}

						_importedChildUrlTitlesMap.put(
							parentKBArticleUrlTitle,
							importedChildKBArticlesUrlTitles);
					}

					if (_newChildArticlesMap != null) {
						List<KBArticle> newChildKBArticles =
							_newChildArticlesMap.get(parentKBArticleUrlTitle);

						if (newChildKBArticles == null) {
							continue;
						}

						if (newChildKBArticles.contains(kbArticle)) {
							newChildKBArticles.remove(kbArticle);
						}

						_newChildArticlesMap.put(
							parentKBArticleUrlTitle, newChildKBArticles);
					}

					if (_newChildUrlTitlesMap != null) {
						List<String> newChildKBArticleUrlTitles =
							_newChildUrlTitlesMap.get(parentKBArticleUrlTitle);

						if (newChildKBArticleUrlTitles == null) {
							continue;
						}

						if (newChildKBArticleUrlTitles.contains(
								importedKBArticleUrlTitle)) {

							newChildKBArticleUrlTitles.remove(
								importedKBArticleUrlTitle);
						}

						_newChildUrlTitlesMap.put(
							parentKBArticleUrlTitle,
							newChildKBArticleUrlTitles);
					}
				}
			}
		}

		if (_prioritizeUpdatedArticles) {

			// prioritize all imported articles

			double maxParentKBArticlePriority = 0.0;

			Map<String, Double> maxChildKBArticlePriorityMap =
				new HashMap<String, Double>();

			for (KBArticle parentKBArticle : _nonImportedParentArticles) {
				double parentKBArticlePriority = parentKBArticle.getPriority();

				if (parentKBArticlePriority > maxParentKBArticlePriority) {
					maxParentKBArticlePriority = parentKBArticlePriority;
				}

				String parentKBArticleUrlTitle = parentKBArticle.getUrlTitle();

				List<KBArticle> childKBArticles =
					_nonImportedChildArticlesMap.get(parentKBArticleUrlTitle);

				if (childKBArticles == null) {
					continue;
				}

				double maxChildKBArticlePriority = 0.0;

				for (KBArticle childArticle : childKBArticles) {
					double childKBArticlePriority = childArticle.getPriority();

					if (childKBArticlePriority > maxChildKBArticlePriority) {
						maxChildKBArticlePriority = childKBArticlePriority;
					}
				}

				maxChildKBArticlePriorityMap.put(
					parentKBArticleUrlTitle, maxChildKBArticlePriority);
			}

			// prioritize imported parent articles by URL title

			ListUtil.sort(_importedParentUrlTitles);

			for (String importedParentKBArticleUrlTitle :
					_importedParentUrlTitles) {

				KBArticle parentKBArticle =
					KBArticleLocalServiceUtil.getKBArticleByUrlTitle(
						_groupId, _parentKBFolderId,
						importedParentKBArticleUrlTitle);

				maxParentKBArticlePriority++;

				KBArticleLocalServiceUtil.updatePriority(
					parentKBArticle.getResourcePrimKey(),
					maxParentKBArticlePriority);
			}

			// prioritize imported child articles by URL title

			updateChildKBArticlesPriorities(
				_importedChildArticlesMap, maxChildKBArticlePriorityMap);
		}
		else {

			// prioritize only new articles

			double maxParentKBArticlePriority = 0.0;

			Map<String, Double> maxChildKBArticlePriorityMap =
				new HashMap<String, Double>();

			for (KBArticle parentKBArticle : _existingParentArticles) {
				double parentKBArticlePriority = parentKBArticle.getPriority();

				if (parentKBArticlePriority > maxParentKBArticlePriority) {
					maxParentKBArticlePriority = parentKBArticlePriority;
				}

				String parentKBArticleUrlTitle = parentKBArticle.getUrlTitle();

				List<KBArticle> childKBArticles = _existingChildArticlesMap.get(
					parentKBArticleUrlTitle);

				double maxChildKBArticlePriority = 0.0;

				for (KBArticle childKBArticle : childKBArticles) {
					double childKBArticlePriority =
						childKBArticle.getPriority();

					if (childKBArticlePriority > maxChildKBArticlePriority) {
						maxChildKBArticlePriority = childKBArticlePriority;
					}
				}

				maxChildKBArticlePriorityMap.put(
					parentKBArticleUrlTitle, maxChildKBArticlePriority);
			}

			// prioritize new parent articles by URL title

			ListUtil.sort(_newParentUrlTitles);

			for (String parentKBArticleUrlTitle : _newParentUrlTitles) {
				KBArticle parentKBArticle =
					KBArticleLocalServiceUtil.getKBArticleByUrlTitle(
						_groupId, _parentKBFolderId, parentKBArticleUrlTitle);

				maxParentKBArticlePriority++;

				KBArticleLocalServiceUtil.updatePriority(
					parentKBArticle.getResourcePrimKey(),
					maxParentKBArticlePriority);
			}

			// prioritize new child articles by URL title

			updateChildKBArticlesPriorities(
				_newChildArticlesMap, maxChildKBArticlePriorityMap);
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
		
		_existingArticlesMap = new HashMap<String, List<KBArticle>>();
		_existingArticlesMap.put(StringPool.BLANK, existingParentArticles);
		
		Set<String> keySet = existingChildArticlesMap.keySet();
		for (String key : keySet) {
			List<KBArticle> childArticles = existingChildArticlesMap.get(key);
			
			_existingArticlesMap.put(key, childArticles);
		}
		
		_existingUrlTitlesMap = new HashMap<String, List<String>>();
		_existingUrlTitlesMap.put(StringPool.BLANK, existingParentUrlTitles);
		
		keySet = existingChildUrlTitlesMap.keySet();
		for (String key : keySet) {
			List<String> childUrlTitles = existingChildUrlTitlesMap.get(key);
			
			_existingUrlTitlesMap.put(key, childUrlTitles);
		}
		
		_importedArticlesMap = new HashMap<String, List<KBArticle>>();
		_importedUrlTitlesMap = new HashMap<String, List<String>>();
		
		_importedUrlTitlesPrioritiesMap = new HashMap<String, Double>();
	}

	protected void updateChildKBArticlesPriorities(
			Map<String, List<KBArticle>> childKBArticlesMap,
			Map<String, Double> maxChildKBArticlePriorityMap)
		throws SystemException {

		Set<String> parentKBArticleUrlTitles = childKBArticlesMap.keySet();

		for (String parentKBArticleUrlTitle : parentKBArticleUrlTitles) {
			List<KBArticle> childKBArticles = childKBArticlesMap.get(
				parentKBArticleUrlTitle);

			if (childKBArticles == null) {
				return;
			}

			Double maxChildKBArticlePriority = maxChildKBArticlePriorityMap.get(
				parentKBArticleUrlTitle);

			if (maxChildKBArticlePriority == null) {
				maxChildKBArticlePriority = 0.0;
			}

			ListUtil.sort(childKBArticles, new KBArticleComparator());

			for (KBArticle childKBArticle : childKBArticles) {
				maxChildKBArticlePriority++;

				KBArticleLocalServiceUtil.updatePriority(
					childKBArticle.getResourcePrimKey(),
					maxChildKBArticlePriority);
			}
		}
	}

	private double _getNumericalPrefix(String path) {
		int i = path.lastIndexOf(CharPool.SLASH);

		if (i == -1) {
			return KBArticleConstants.DEFAULT_PRIORITY;
		}

		String name = path.substring(i);

		String numericalPrefix = StringUtil.extractLeadingDigits(name);

		if (Validator.isNull(numericalPrefix)) {
			return KBArticleConstants.DEFAULT_PRIORITY;
		}

		return Double.parseDouble(numericalPrefix);
	}

	private void _initNewArticles() {
		_newParentArticles = new ArrayList<KBArticle>();

		for (KBArticle kbArticle : _importedParentArticles) {
			String urlTitle = kbArticle.getUrlTitle();

			if (!_existingParentUrlTitles.contains(urlTitle)) {
				_newParentArticles.add(kbArticle);
			}
		}

		_newParentUrlTitles = new ArrayList<String>();

		for (KBArticle kbArticle : _newParentArticles) {
			_newParentUrlTitles.add(kbArticle.getUrlTitle());
		}

		_newChildArticlesMap = new HashMap<String, List<KBArticle>>();

		Set<String> parentKBArticleUrlTitles =
			_importedChildArticlesMap.keySet();

		for (String parentKBArticleUrlTitle : parentKBArticleUrlTitles) {
			List<KBArticle> newChildKBArticles = new ArrayList<KBArticle>();

			List<KBArticle> importedChildKBArticles =
				_importedChildArticlesMap.get(parentKBArticleUrlTitle);

			if (_existingChildArticlesMap.containsKey(
					parentKBArticleUrlTitle)) {

				List<String> existingChildKBArticleUrlTitles =
					_existingChildUrlTitlesMap.get(parentKBArticleUrlTitle);

				for (KBArticle kbArticle : importedChildKBArticles) {
					String urlTitle = kbArticle.getUrlTitle();

					if (!existingChildKBArticleUrlTitles.contains(urlTitle)) {
						newChildKBArticles.add(kbArticle);
					}
				}

				_newChildArticlesMap.put(
					parentKBArticleUrlTitle, newChildKBArticles);
			}
			else {
				for (KBArticle kbArticle : importedChildKBArticles) {
					newChildKBArticles.add(kbArticle);
				}

				_newChildArticlesMap.put(
					parentKBArticleUrlTitle, newChildKBArticles);
			}
		}

		_newChildUrlTitlesMap = new HashMap<String, List<String>>();

		Set<String> newKeySet = _newChildArticlesMap.keySet();

		for (String key : newKeySet) {
			_newChildUrlTitlesMap.put(key, new ArrayList<String>());
		}
	}

	private void _initNonImportedArticles() {
		_nonImportedParentArticles = new ArrayList<KBArticle>();

		for (KBArticle kbArticle : _existingParentArticles) {
			String urlTitle = kbArticle.getUrlTitle();

			if (!_importedParentUrlTitles.contains(urlTitle)) {
				_nonImportedParentArticles.add(kbArticle);
			}
		}

		_nonImportedChildArticlesMap = new HashMap<String, List<KBArticle>>();

		Set<String> parentKBArticleUrlTitles =
			_existingChildArticlesMap.keySet();

		for (String parentKBArticleUrlTitle : parentKBArticleUrlTitles) {
			List<KBArticle> nonImportedChildKBArticles =
				new ArrayList<KBArticle>();

			List<KBArticle> existingChildKBArticles =
				_existingChildArticlesMap.get(parentKBArticleUrlTitle);

			if (_importedChildArticlesMap.containsKey(
					parentKBArticleUrlTitle)) {

				List<String> importedChildKBArticleUrlTitles =
					_importedChildUrlTitlesMap.get(parentKBArticleUrlTitle);

				for (KBArticle kbArticle : existingChildKBArticles) {
					String urlTitle = kbArticle.getUrlTitle();

					if (!importedChildKBArticleUrlTitles.contains(urlTitle)) {
						nonImportedChildKBArticles.add(kbArticle);
					}
				}

				_nonImportedChildArticlesMap.put(
					parentKBArticleUrlTitle, nonImportedChildKBArticles);
			}
			else {
				for (KBArticle kbArticle : existingChildKBArticles) {
					nonImportedChildKBArticles.add(kbArticle);
				}

				_nonImportedChildArticlesMap.put(
					parentKBArticleUrlTitle, nonImportedChildKBArticles);
			}
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