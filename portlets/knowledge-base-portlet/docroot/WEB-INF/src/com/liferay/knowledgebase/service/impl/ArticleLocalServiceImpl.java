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
			long companyId, int start, int end, OrderByComparator obc)
		throws SystemException {

		Map<String, Long> params = new HashMap<String, Long>();

		params.put("companyId", new Long(companyId));

		return _findByMaxVersion(params, start, end, obc);
	}

	public int getCompanyArticlesCount(long companyId) throws SystemException {
		Map<String, Long> params = new HashMap<String, Long>();

		params.put("companyId", new Long(companyId));

		return _countByMaxVersion(params);
	}

	public List<Article> getGroupArticles(
			long groupId, int start, int end, OrderByComparator obc)
		throws SystemException {

		Map<String, Long> params = new HashMap<String, Long>();

		params.put("groupId", new Long(groupId));

		return _findByMaxVersion(params, start, end, obc);
	}

	public List<Article> getGroupArticles(
			long groupId, long parentResourcePrimKey, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		Map<String, Long> params = new HashMap<String, Long>();

		params.put("groupId", new Long(groupId));
		params.put("parentResourcePrimKey", new Long(parentResourcePrimKey));

		return _findByMaxVersion(params, start, end, obc);
	}

	public int getGroupArticlesCount(long groupId) throws SystemException {
		Map<String, Long> params = new HashMap<String, Long>();

		params.put("groupId", new Long(groupId));

		return _countByMaxVersion(params);
	}

	public int getGroupArticlesCount(long groupId, long parentResourcePrimKey)
		throws SystemException {

		Map<String, Long> params = new HashMap<String, Long>();

		params.put("groupId", new Long(groupId));
		params.put("parentResourcePrimKey", new Long(parentResourcePrimKey));

		return _countByMaxVersion(params);
	}

	private int _countByMaxVersion(Map<String, Long> params)
		throws SystemException {

		return dynamicQueryCount(_getMaxVersionQuery(params));
	}

	private List<Article> _findByMaxVersion(
			Map<String, Long> params, int start, int end, OrderByComparator obc)
		throws SystemException {

		List<Object> objects = dynamicQuery(
			_getMaxVersionQuery(params), start, end, obc);

		List<Article> articles = new ArrayList<Article>();

		for (Object object : objects) {
			articles.add((Article)object);
		}

		return articles;
	}

	private DynamicQuery _getMaxVersionQuery(Map<String, Long> params)
		throws SystemException {

		DynamicQuery subSelect = DynamicQueryFactoryUtil.forClass(
			Article.class, "article2", PortletClassLoaderUtil.getClassLoader());

		subSelect.setProjection(PropertyFactoryUtil.forName("version").max());

		Property property1 = PropertyFactoryUtil.forName(
			"article1.resourcePrimKey");
		Property property2 = PropertyFactoryUtil.forName(
			"article2.resourcePrimKey");

		subSelect.add(property1.eqProperty(property2));

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Article.class, "article1", PortletClassLoaderUtil.getClassLoader());

		for (Map.Entry<String, Long> entry : params.entrySet()) {
			Property property = PropertyFactoryUtil.forName(entry.getKey());

			dynamicQuery.add(property.eq(entry.getValue()));
		}

		dynamicQuery.add(PropertyFactoryUtil.forName("version").in(subSelect));

		return dynamicQuery;
	}

}