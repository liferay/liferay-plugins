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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
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

		for (KBArticle existingParentArticle : existingParentArticles) {
			existingParentUrlTitles.add(existingParentArticle.getUrlTitle());
		}

		Map<String, List<KBArticle>> existingChildArticlesMap =
				new HashMap<String, List<KBArticle>>();

		Map<String, List<String>> existingChildUrlTitlesMap =
			new HashMap<String, List<String>>();

		for (KBArticle existingParentArticle : existingParentArticles) {
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

		List<KBArticle> importedParentArticles = new ArrayList<KBArticle>();

		List<String> importedParentUrlTitles = new ArrayList<String>();

		Map<String, List<KBArticle>> importedChildArticlesMap =
			new HashMap<String, List<KBArticle>>();

		Map<String, List<String>> importedChildUrlTitlesMap =
			new HashMap<String, List<String>>();

		Map<String, Double> importedUrlTitlesPrioritiesMap =
			new HashMap<String, Double>();

		PrioritizationStrategy prioritizationStrategy =
			new PrioritizationStrategy(
				groupId, parentKBFolderId, prioritizeUpdatedArticles,
				prioritizeByNumericalPrefix, existingParentArticles,
				existingParentUrlTitles, existingChildArticlesMap,
				existingChildUrlTitlesMap, importedParentArticles,
				importedParentUrlTitles, importedChildArticlesMap,
				importedChildUrlTitlesMap, importedUrlTitlesPrioritiesMap);

		return prioritizationStrategy;
	}

	public void addImportedChildArticle(KBArticle childArticle)
		throws PortalException, SystemException {

		KBArticle parentArticle = childArticle.getParentKBArticle();

		String parentUrlTitle = StringPool.BLANK;

		if (parentArticle != null) {
			parentUrlTitle = parentArticle.getUrlTitle();
		}
		else {
			return;
		}

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

	public void addImportedParentArticle(KBArticle ParentArticle) {
		_importedParentArticles.add(ParentArticle);

		String importedParentUrlTitle = ParentArticle.getUrlTitle();

		_importedParentUrlTitles.add(importedParentUrlTitle);
	}

	public void addImportedUrlTitlesPriorities(
		Map<String, Double> importedUrlTitlesPrioritiesMap) {

		_importedUrlTitlesPrioritiesMap = importedUrlTitlesPrioritiesMap;
	}

	public void prioritizeArticles(
			long groupId, long parentKBFolderId,
			boolean prioritizeUpdatedArticles,
			boolean prioritizeByNumericalPrefix)
		throws PortalException, SystemException {

		if (prioritizeUpdatedArticles) {
			_initNonImportedArticles();
		}
		else {
			_initNewArticles();
		}

		if (prioritizeByNumericalPrefix) {
			Set<String> keySet = _importedUrlTitlesPrioritiesMap.keySet();

			KBArticle article = null;

			long resourcePrimKey = 0;

			Double priority = null;

			for (String key : keySet) {
				article =
					KBArticleLocalServiceUtil.getKBArticleByUrlTitle(
						groupId, parentKBFolderId, key);

				resourcePrimKey = article.getResourcePrimKey();

				priority = _importedUrlTitlesPrioritiesMap.get(key);

				KBArticleLocalServiceUtil.updatePriority(
					resourcePrimKey, priority);

				/*
				 * Remove articles with numerical prefixes, and their URL
				 * titles, from lists of imported and new articles. Only
				 * articles without numerical prefixes need to be
				 * alphanumerically prioritized.
				 */

				if (_importedParentArticles != null) {
					if (_importedParentArticles.contains(article)) {
						_importedParentArticles.remove(article);
					}
				}

				if (_importedParentUrlTitles != null) {
					if (_importedParentUrlTitles.contains(key)) {
						_importedParentUrlTitles.remove(key);
					}
				}

				if (_newParentArticles != null) {
					if (_newParentArticles.contains(article)) {
						_newParentArticles.remove(article);
					}
				}

				if (_newParentUrlTitles != null) {
					if (_newParentUrlTitles.contains(key)) {
						_newParentUrlTitles.remove(key);
					}
				}

				Set<String> childArticlesKeySet =
					_importedChildArticlesMap.keySet();

				for (String childArticlesKey : childArticlesKeySet) {
					if (_importedChildArticlesMap != null) {
						List<KBArticle> importedChildArticles =
							_importedChildArticlesMap.get(childArticlesKey);

						if (importedChildArticles.contains(article)) {
							importedChildArticles.remove(article);
						}

						_importedChildArticlesMap.put(
							childArticlesKey, importedChildArticles);
					}

					if (_importedChildUrlTitlesMap != null) {
						List<String> importedChildArticlesUrlTitles =
							_importedChildUrlTitlesMap.get(childArticlesKey);

						if (importedChildArticlesUrlTitles.contains(key)) {
							importedChildArticlesUrlTitles.remove(key);
						}

						_importedChildUrlTitlesMap.put(
							childArticlesKey, importedChildArticlesUrlTitles);
					}

					if (_newChildArticlesMap != null) {
						List<KBArticle> newChildArticles =
							_newChildArticlesMap.get(childArticlesKey);

						if (newChildArticles == null) {
							continue;
						}

						if (newChildArticles.contains(article)) {
							newChildArticles.remove(article);
						}

						_newChildArticlesMap.put(
							childArticlesKey, newChildArticles);
					}

					if (_newChildUrlTitlesMap != null) {
						List<String> newChildArticlesUrlTitles =
							_newChildUrlTitlesMap.get(childArticlesKey);

						if (newChildArticlesUrlTitles == null) {
							continue;
						}

						if (newChildArticlesUrlTitles.contains(key)) {
							newChildArticlesUrlTitles.remove(key);
						}

						_newChildUrlTitlesMap.put(
							childArticlesKey, newChildArticlesUrlTitles);
					}
				}
			}
		}

		KBArticleComparator comparator = new KBArticleComparator();

		if (prioritizeUpdatedArticles) {

			// prioritize all imported articles

			double maxParentPriority = 0.0;

			Map<String, Double> maxChildPriorityMap =
				new HashMap<String, Double>();

			for (KBArticle parentArticle : _nonImportedParentArticles) {
				double parentPriority = parentArticle.getPriority();

				if (parentPriority > maxParentPriority) {
					maxParentPriority = parentPriority;
				}

				String parentUrlTitle = parentArticle.getUrlTitle();

				List<KBArticle> childArticles =
					_nonImportedChildArticlesMap.get(parentUrlTitle);

				if (childArticles == null) {
					continue;
				}

				double maxChildPriority = 0.0;

				for (KBArticle childArticle : childArticles) {
					double childPriority = childArticle.getPriority();

					if (childPriority > maxChildPriority) {
						maxChildPriority = childPriority;
					}
				}

				maxChildPriorityMap.put(parentUrlTitle, maxChildPriority);
			}

			// prioritize imported parent articles by URL title

			ListUtil.sort(_importedParentUrlTitles);

			int parentSize = _importedParentUrlTitles.size();

			for (int i = 0; i < parentSize; i++) {
				KBArticle parentArticle =
					KBArticleLocalServiceUtil.getKBArticleByUrlTitle(
						groupId, parentKBFolderId,
						_importedParentUrlTitles.get(i));

				long parentResourcePrimKey = parentArticle.getResourcePrimKey();

				KBArticleLocalServiceUtil.updatePriority(
					parentResourcePrimKey, maxParentPriority + 1 + i);
			}

			// prioritize imported child articles by URL title

			Set<String> childKeySet = _importedChildArticlesMap.keySet();

			for (String childKey : childKeySet) {
				List<KBArticle> childArticles = _importedChildArticlesMap.get(
					childKey);

				if (childArticles != null) {
					ListUtil.sort(childArticles, comparator);

					int childSize = childArticles.size();

					for (int i = 0; i < childSize; i++) {
						KBArticle childArticle = childArticles.get(i);

						long childArticleResourcePrimKey =
							childArticle.getResourcePrimKey();

						Double maxChildArticlePriority =
							maxChildPriorityMap.get(childKey);

						if (maxChildArticlePriority == null) {
							maxChildArticlePriority = 0.0;
						}

						KBArticleLocalServiceUtil.updatePriority(
							childArticleResourcePrimKey,
							maxChildArticlePriority + 1 + i);
					}
				}
			}
		}
		else {

			// prioritize only new articles

			double maxParentPriority = 0.0;

			Map<String, Double> maxChildPriorityMap =
				new HashMap<String, Double>();

			for (KBArticle parentArticle : _existingParentArticles) {
				double parentPriority = parentArticle.getPriority();

				if (parentPriority > maxParentPriority) {
					maxParentPriority = parentPriority;
				}

				String parentUrlTitle = parentArticle.getUrlTitle();

				List<KBArticle> childArticles = _existingChildArticlesMap.get(
					parentUrlTitle);

				double maxChildPriority = 0.0;

				for (KBArticle childArticle : childArticles) {
					double childPriority = childArticle.getPriority();

					if (childPriority > maxChildPriority) {
						maxChildPriority = childPriority;
					}
				}

				maxChildPriorityMap.put(parentUrlTitle, maxChildPriority);
			}

			// prioritize new parent articles by URL title

			ListUtil.sort(_newParentUrlTitles);

			int parentSize = _newParentUrlTitles.size();

			for (int i = 0; i < parentSize; i++) {
				KBArticle parentArticle =
					KBArticleLocalServiceUtil.getKBArticleByUrlTitle(
						groupId, parentKBFolderId, _newParentUrlTitles.get(i));

				long parentResourcePrimKey = parentArticle.getResourcePrimKey();

				KBArticleLocalServiceUtil.updatePriority(
					parentResourcePrimKey, maxParentPriority + 1 + i);
			}

			// prioritize new child articles by URL title

			Set<String> childKeySet = _newChildArticlesMap.keySet();

			for (String childKey : childKeySet) {
				List<KBArticle> childArticles = _newChildArticlesMap.get(
					childKey);

				if (childArticles != null) {
					ListUtil.sort(childArticles, comparator);

					int childSize = childArticles.size();

					for (int i = 0; i < childSize; i++) {
						KBArticle childArticle = childArticles.get(i);

						long childArticleResourcePrimKey =
							childArticle.getResourcePrimKey();

						Double maxChildArticlePriority =
							maxChildPriorityMap.get(childKey);

						if (maxChildArticlePriority == null) {
							maxChildArticlePriority = 0.0;
						}

						KBArticleLocalServiceUtil.updatePriority(
							childArticleResourcePrimKey,
							maxChildArticlePriority + 1 + i);
					}
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
		Map<String, List<String>> existingChildUrlTitlesMap,
		List<KBArticle> importedParentArticles,
		List<String> importedParentUrlTitles,
		Map<String, List<KBArticle>> importedChildArticlesMap,
		Map<String, List<String>> importedChildUrlTitlesMap,
		Map<String, Double> importedUrlTitlesPrioritiesMap) {

		_existingParentArticles = existingParentArticles;
		_existingParentUrlTitles = existingParentUrlTitles;
		_existingChildArticlesMap = existingChildArticlesMap;
		_existingChildUrlTitlesMap = existingChildUrlTitlesMap;
		_importedParentArticles = importedParentArticles;
		_importedParentUrlTitles = importedParentUrlTitles;
		_importedChildArticlesMap = importedChildArticlesMap;
		_importedChildUrlTitlesMap = importedChildUrlTitlesMap;
		_importedUrlTitlesPrioritiesMap = importedUrlTitlesPrioritiesMap;
	}

	private void _initNewArticles() {
		_newParentArticles = new ArrayList<KBArticle>();

		for (KBArticle article : _importedParentArticles) {
			String urlTitle = article.getUrlTitle();

			if (!_existingParentUrlTitles.contains(urlTitle)) {
				_newParentArticles.add(article);
			}
		}

		_newParentUrlTitles = new ArrayList<String>();

		for (KBArticle article : _newParentArticles) {
			String urlTitle = article.getUrlTitle();

			_newParentUrlTitles.add(urlTitle);
		}

		_newChildArticlesMap = new HashMap<String, List<KBArticle>>();

		Set<String> keySet = _importedChildArticlesMap.keySet();

		for (String key : keySet) {
			List<KBArticle> newChildArticles = new ArrayList<KBArticle>();

			List<KBArticle> importedChildArticles =
				_importedChildArticlesMap.get(key);

			if (_existingChildArticlesMap.containsKey(key)) {
				List<String> existingChildUrlTitles =
					_existingChildUrlTitlesMap.get(key);

				for (KBArticle article : importedChildArticles) {
					String urlTitle = article.getUrlTitle();

					if (!existingChildUrlTitles.contains(urlTitle)) {
						newChildArticles.add(article);
					}
				}

				_newChildArticlesMap.put(key, newChildArticles);
			}
			else {
				for (KBArticle article : importedChildArticles) {
					newChildArticles.add(article);
				}

				_newChildArticlesMap.put(key, newChildArticles);
			}
		}

		_newChildUrlTitlesMap = new HashMap<String, List<String>>();

		Set<String> newKeySet = _newChildArticlesMap.keySet();

		for (String key : newKeySet) {
			List<KBArticle> newChildArticles = new ArrayList<KBArticle>();

			List<String> urlTitles = new ArrayList<String>();

			for (KBArticle article : newChildArticles) {
				String urlTitle = article.getUrlTitle();

				urlTitles.add(urlTitle);
			}

			_newChildUrlTitlesMap.put(key, urlTitles);
		}
	}

	private void _initNonImportedArticles() {
		_nonImportedParentArticles = new ArrayList<KBArticle>();

		for (KBArticle article : _existingParentArticles) {
			String urlTitle = article.getUrlTitle();

			if (!_importedParentUrlTitles.contains(urlTitle)) {
				_nonImportedParentArticles.add(article);
			}
		}

		_nonImportedParentUrlTitles = new ArrayList<String>();

		for (KBArticle article : _nonImportedParentArticles) {
			String urlTitle = article.getUrlTitle();

			_nonImportedParentUrlTitles.add(urlTitle);
		}

		_nonImportedChildArticlesMap = new HashMap<String, List<KBArticle>>();

		Set<String> keySet = _existingChildArticlesMap.keySet();

		for (String key : keySet) {
			List<KBArticle> nonImportedChildArticles =
				new ArrayList<KBArticle>();

			List<KBArticle> existingChildArticles =
				_existingChildArticlesMap.get(key);

			if (_importedChildArticlesMap.containsKey(key)) {
				List<String> importedChildUrlTitles =
					_importedChildUrlTitlesMap.get(key);

				for (KBArticle article : existingChildArticles) {
					String urlTitle = article.getUrlTitle();

					if (!importedChildUrlTitles.contains(urlTitle)) {
						nonImportedChildArticles.add(article);
					}
				}

				_nonImportedChildArticlesMap.put(key, nonImportedChildArticles);
			}
			else {
				for (KBArticle article : existingChildArticles) {
					nonImportedChildArticles.add(article);
				}

				_nonImportedChildArticlesMap.put(key, nonImportedChildArticles);
			}
		}

		_nonImportedChildUrlTitlesMap = new HashMap<String, List<String>>();

		Set<String> nonImportedKeySet = _nonImportedChildArticlesMap.keySet();

		for (String key : nonImportedKeySet) {
			List<KBArticle> nonImportedChildArticles =
				new ArrayList<KBArticle>();

			List<String> urlTitles = new ArrayList<String>();

			for (KBArticle article : nonImportedChildArticles) {
				String urlTitle = article.getUrlTitle();

				urlTitles.add(urlTitle);
			}

			_nonImportedChildUrlTitlesMap.put(key, urlTitles);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PrioritizationStrategy.class);

	private Map<String, List<KBArticle>> _existingChildArticlesMap;
	private Map<String, List<String>> _existingChildUrlTitlesMap;
	private List<KBArticle> _existingParentArticles;
	private List<String> _existingParentUrlTitles;
	private Map<String, List<KBArticle>> _importedChildArticlesMap;
	private Map<String, List<String>> _importedChildUrlTitlesMap;
	private List<KBArticle> _importedParentArticles;
	private List<String> _importedParentUrlTitles;
	private Map<String, Double> _importedUrlTitlesPrioritiesMap;
	private Map<String, List<KBArticle>> _newChildArticlesMap;
	private Map<String, List<String>> _newChildUrlTitlesMap;
	private List<KBArticle> _newParentArticles;
	private List<String> _newParentUrlTitles;
	private Map<String, List<KBArticle>> _nonImportedChildArticlesMap;
	private Map<String, List<String>> _nonImportedChildUrlTitlesMap;
	private List<KBArticle> _nonImportedParentArticles;
	private List<String> _nonImportedParentUrlTitles;

}