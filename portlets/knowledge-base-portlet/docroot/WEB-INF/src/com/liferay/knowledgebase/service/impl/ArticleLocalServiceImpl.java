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

import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.service.base.ArticleLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="ArticleLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class ArticleLocalServiceImpl extends ArticleLocalServiceBaseImpl {

	public List<Article> getCompanyArticles(
			long companyId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		Map<String, Long> params = new HashMap<String, Long>();

		params.put("companyId", new Long(companyId));

		List<Object> results = dynamicQuery(
			getDynamicQuery(params), start, end, orderByComparator);

		return toArticles(results);
	}

	public int getCompanyArticlesCount(long companyId) throws SystemException {
		Map<String, Long> params = new HashMap<String, Long>();

		params.put("companyId", new Long(companyId));

		return dynamicQueryCount(getDynamicQuery(params));
	}

	public List<Article> getGroupArticles(
			long groupId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		Map<String, Long> params = new HashMap<String, Long>();

		params.put("groupId", new Long(groupId));

		List<Object> results = dynamicQuery(
			getDynamicQuery(params), start, end, orderByComparator);

		return toArticles(results);
	}

	public List<Article> getGroupArticles(
			long groupId, long parentResourcePrimKey, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		Map<String, Long> params = new HashMap<String, Long>();

		params.put("groupId", new Long(groupId));
		params.put("parentResourcePrimKey", new Long(parentResourcePrimKey));

		List<Object> results = dynamicQuery(
			getDynamicQuery(params), start, end, orderByComparator);

		return toArticles(results);
	}

	public int getGroupArticlesCount(long groupId) throws SystemException {
		Map<String, Long> params = new HashMap<String, Long>();

		params.put("groupId", new Long(groupId));

		return dynamicQueryCount(getDynamicQuery(params));
	}

	public int getGroupArticlesCount(long groupId, long parentResourcePrimKey)
		throws SystemException {

		Map<String, Long> params = new HashMap<String, Long>();

		params.put("groupId", new Long(groupId));
		params.put("parentResourcePrimKey", new Long(parentResourcePrimKey));

		return dynamicQueryCount(getDynamicQuery(params));
	}

	protected DynamicQuery getDynamicQuery(Map<String, Long> params) {
		DynamicQuery subselectDynamicQuery = DynamicQueryFactoryUtil.forClass(
			Article.class, "article2", PortletClassLoaderUtil.getClassLoader());

		subselectDynamicQuery.setProjection(
			PropertyFactoryUtil.forName("version").max());

		Property resourcePrimKeyProperty1 = PropertyFactoryUtil.forName(
			"article1.resourcePrimKey");
		Property resourcePrimKeyProperty2 = PropertyFactoryUtil.forName(
			"article2.resourcePrimKey");

		subselectDynamicQuery.add(
			resourcePrimKeyProperty1.eqProperty(resourcePrimKeyProperty2));

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Article.class, "article1", PortletClassLoaderUtil.getClassLoader());

		for (Map.Entry<String, Long> entry : params.entrySet()) {
			String name = entry.getKey();

			Property property = PropertyFactoryUtil.forName(name);

			dynamicQuery.add(property.eq(entry.getValue()));
		}

		dynamicQuery.add(
			PropertyFactoryUtil.forName("version").in(subselectDynamicQuery));

		return dynamicQuery;
	}

	protected List<Article> toArticles(List<Object> results) {
		List<Article> articles = new ArrayList<Article>(results.size());

		for (Object result : results) {
			articles.add((Article)result);
		}

		return articles;
	}

}