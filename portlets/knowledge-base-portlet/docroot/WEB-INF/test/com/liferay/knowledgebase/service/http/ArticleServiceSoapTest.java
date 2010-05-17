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

package com.liferay.knowledgebase.service.http;

import com.liferay.client.soap.knowledgebase.model.ArticleSoap;
import com.liferay.client.soap.knowledgebase.service.http.ArticleServiceSoap;
import com.liferay.client.soap.knowledgebase.service.http.ArticleServiceSoapServiceLocator;
import com.liferay.client.soap.portal.service.ServiceContext;
import com.liferay.knowledgebase.model.ArticleConstants;
import com.liferay.portal.kernel.test.TestCase;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.util.log4j.Log4JUtil;

import java.net.URL;

/**
 * <a href="ArticleServiceSoapTest.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class ArticleServiceSoapTest extends TestCase {

	public static final String PASSWORD = "test";

	public static final String URL = "http://localhost:8080";

	public static final long USER_ID = 2;

	public ArticleServiceSoapTest() {
		ClassLoader classLoader = getClass().getClassLoader();

		Log4JUtil.configureLog4J(
			classLoader.getResource("META-INF/portal-log4j.xml"));
	}

	public void testAddArticle() throws Exception {
		addArticle("AddArticleServiceSoapTest");
	}

	public void testDeleteArticle() throws Exception {
		ArticleSoap article = addArticle("DeleteArticleServiceSoapTest");

		getArticleServiceSoap().deleteArticle(article.getResourcePrimKey());
	}

	public void testGetArticle() throws Exception {
		ArticleSoap article = addArticle("GetArticleServiceSoapTest");

		getArticleServiceSoap().getLatestArticle(article.getResourcePrimKey());
	}

	protected ArticleSoap addArticle(String text) throws Exception {
		long parentResourcePrimKey =
			ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY;
		int priority = 0;
		String dirName = StringPool.BLANK;

		ServiceContext serviceContext = new ServiceContext();

		return getArticleServiceSoap().addArticle(
			parentResourcePrimKey, text, text, text, priority, dirName,
			serviceContext);
	}

	protected ArticleServiceSoap getArticleServiceSoap() throws Exception {
		int pos = URL.indexOf("://");

		String protocol = URL.substring(0, pos + 3);
		String host = URL.substring(pos + 3, URL.length());
		String path =
			"/knowledge-base-portlet/secure/axis/Plugin_KB_ArticleService";

		URL url = new URL(
			protocol + USER_ID + StringPool.COLON + PASSWORD + StringPool.AT +
				host + path);

		ArticleServiceSoapServiceLocator locator =
			new ArticleServiceSoapServiceLocator();

		return locator.getPlugin_KB_ArticleService(url);
	}

}