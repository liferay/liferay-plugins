/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.kb.knowledgebase.service;

import com.liferay.kb.knowledgebase.model.KBArticleClp;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.BooleanWrapper;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.DoubleWrapper;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.kernel.util.NullWrapper;
import com.liferay.portal.model.BaseModel;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="KBArticleLocalServiceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBArticleLocalServiceClp implements KBArticleLocalService {
	public KBArticleLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
		_classLoader = classLoaderProxy.getClassLoader();
	}

	public com.liferay.kb.knowledgebase.model.KBArticle addKBArticle(
		com.liferay.kb.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(kbArticle);

		if (kbArticle == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.kb.knowledgebase.model.KBArticle");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addKBArticle",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBArticle)translateOutput(returnObj);
	}

	public com.liferay.kb.knowledgebase.model.KBArticle createKBArticle(
		long articleId) {
		Object paramObj0 = new LongWrapper(articleId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("createKBArticle",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBArticle)translateOutput(returnObj);
	}

	public void deleteKBArticle(long articleId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(articleId);

		try {
			_classLoaderProxy.invoke("deleteKBArticle",
				new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void deleteKBArticle(
		com.liferay.kb.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(kbArticle);

		if (kbArticle == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.kb.knowledgebase.model.KBArticle");
		}

		try {
			_classLoaderProxy.invoke("deleteKBArticle",
				new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQuery",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<Object>)translateOutput(returnObj);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQuery",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<Object>)translateOutput(returnObj);
	}

	public com.liferay.kb.knowledgebase.model.KBArticle getKBArticle(
		long articleId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(articleId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getKBArticle",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBArticle)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getKBArticles(
		int start, int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = new IntegerWrapper(start);

		Object paramObj1 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getKBArticles",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBArticle>)translateOutput(returnObj);
	}

	public int getKBArticlesCount() throws com.liferay.portal.SystemException {
		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getKBArticlesCount",
					new Object[0]);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.kb.knowledgebase.model.KBArticle updateKBArticle(
		com.liferay.kb.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(kbArticle);

		if (kbArticle == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.kb.knowledgebase.model.KBArticle");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateKBArticle",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBArticle)translateOutput(returnObj);
	}

	public com.liferay.kb.knowledgebase.model.KBArticle addArticle(
		long userId, long groupId, java.lang.String title,
		java.lang.String content, java.lang.String description,
		boolean minorEdit, boolean template, boolean draft,
		java.lang.String[] tagsEntries, javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(userId);

		Object paramObj1 = new LongWrapper(groupId);

		Object paramObj2 = translateInput(title);

		if (title == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = translateInput(content);

		if (content == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = translateInput(description);

		if (description == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = new BooleanWrapper(minorEdit);

		Object paramObj6 = new BooleanWrapper(template);

		Object paramObj7 = new BooleanWrapper(draft);

		Object paramObj8 = translateInput(tagsEntries);

		if (tagsEntries == null) {
			paramObj8 = new NullWrapper("[Ljava.lang.String;");
		}

		Object paramObj9 = translateInput(prefs);

		if (prefs == null) {
			paramObj9 = new NullWrapper("javax.portlet.PortletPreferences");
		}

		Object paramObj10 = translateInput(themeDisplay);

		if (themeDisplay == null) {
			paramObj10 = new NullWrapper(
					"com.liferay.portal.theme.ThemeDisplay");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addArticle",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8, paramObj9,
						paramObj10
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBArticle)translateOutput(returnObj);
	}

	public com.liferay.kb.knowledgebase.model.KBArticle addArticle(
		java.lang.String uuid, long userId, long groupId,
		java.lang.String title, double version, java.lang.String content,
		java.lang.String description, boolean minorEdit, boolean head,
		boolean template, boolean draft, java.lang.String[] tagsEntries,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(uuid);

		if (uuid == null) {
			paramObj0 = new NullWrapper("java.lang.String");
		}

		Object paramObj1 = new LongWrapper(userId);

		Object paramObj2 = new LongWrapper(groupId);

		Object paramObj3 = translateInput(title);

		if (title == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = new DoubleWrapper(version);

		Object paramObj5 = translateInput(content);

		if (content == null) {
			paramObj5 = new NullWrapper("java.lang.String");
		}

		Object paramObj6 = translateInput(description);

		if (description == null) {
			paramObj6 = new NullWrapper("java.lang.String");
		}

		Object paramObj7 = new BooleanWrapper(minorEdit);

		Object paramObj8 = new BooleanWrapper(head);

		Object paramObj9 = new BooleanWrapper(template);

		Object paramObj10 = new BooleanWrapper(draft);

		Object paramObj11 = translateInput(tagsEntries);

		if (tagsEntries == null) {
			paramObj11 = new NullWrapper("[Ljava.lang.String;");
		}

		Object paramObj12 = translateInput(prefs);

		if (prefs == null) {
			paramObj12 = new NullWrapper("javax.portlet.PortletPreferences");
		}

		Object paramObj13 = translateInput(themeDisplay);

		if (themeDisplay == null) {
			paramObj13 = new NullWrapper(
					"com.liferay.portal.theme.ThemeDisplay");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addArticle",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8, paramObj9,
						paramObj10, paramObj11, paramObj12, paramObj13
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBArticle)translateOutput(returnObj);
	}

	public void addArticleAttachments(long resourcePrimKey,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<String, byte[]>> files)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		Object paramObj1 = translateInput(files);

		if (files == null) {
			paramObj1 = new NullWrapper("java.util.List");
		}

		try {
			_classLoaderProxy.invoke("addArticleAttachments",
				new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void addArticleResources(long groupId,
		com.liferay.kb.knowledgebase.model.KBArticle article,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = translateInput(article);

		if (article == null) {
			paramObj1 = new NullWrapper(
					"com.liferay.kb.knowledgebase.model.KBArticle");
		}

		Object paramObj2 = new BooleanWrapper(addCommunityPermissions);

		Object paramObj3 = new BooleanWrapper(addGuestPermissions);

		try {
			_classLoaderProxy.invoke("addArticleResources",
				new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void addArticleResources(long groupId,
		com.liferay.kb.knowledgebase.model.KBArticle article,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = translateInput(article);

		if (article == null) {
			paramObj1 = new NullWrapper(
					"com.liferay.kb.knowledgebase.model.KBArticle");
		}

		Object paramObj2 = translateInput(communityPermissions);

		if (communityPermissions == null) {
			paramObj2 = new NullWrapper("[Ljava.lang.String;");
		}

		Object paramObj3 = translateInput(guestPermissions);

		if (guestPermissions == null) {
			paramObj3 = new NullWrapper("[Ljava.lang.String;");
		}

		try {
			_classLoaderProxy.invoke("addArticleResources",
				new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void deleteArticle(long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		try {
			_classLoaderProxy.invoke("deleteArticle", new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void deleteArticle(
		com.liferay.kb.knowledgebase.model.KBArticle article)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(article);

		if (article == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.kb.knowledgebase.model.KBArticle");
		}

		try {
			_classLoaderProxy.invoke("deleteArticle", new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void deleteArticleAttachment(long resourcePrimKey,
		java.lang.String fileName)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		Object paramObj1 = translateInput(fileName);

		if (fileName == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		try {
			_classLoaderProxy.invoke("deleteArticleAttachment",
				new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void deleteGroupArticles(long groupId, boolean template)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = new BooleanWrapper(template);

		try {
			_classLoaderProxy.invoke("deleteGroupArticles",
				new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getArticle",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBArticle)translateOutput(returnObj);
	}

	public com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long resourcePrimKey, double version)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		Object paramObj1 = new DoubleWrapper(version);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getArticle",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBArticle)translateOutput(returnObj);
	}

	public com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long groupId, java.lang.String title)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = translateInput(title);

		if (title == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getArticle",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBArticle)translateOutput(returnObj);
	}

	public com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long groupId, java.lang.String title, double version)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = translateInput(title);

		if (title == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = new DoubleWrapper(version);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getArticle",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBArticle)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getArticles(
		long resourcePrimKey, int start, int end)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getArticles",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBArticle>)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getArticles(
		long resourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object paramObj3 = translateInput(obc);

		if (obc == null) {
			paramObj3 = new NullWrapper(
					"com.liferay.portal.kernel.util.OrderByComparator");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getArticles",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBArticle>)translateOutput(returnObj);
	}

	public int getArticlesCount(long resourcePrimKey)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getArticlesCount",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getCompanyArticles(
		long companyId, boolean head, boolean template, boolean draft,
		int start, int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = new BooleanWrapper(head);

		Object paramObj2 = new BooleanWrapper(template);

		Object paramObj3 = new BooleanWrapper(draft);

		Object paramObj4 = new IntegerWrapper(start);

		Object paramObj5 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getCompanyArticles",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBArticle>)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticles(
		long groupId, boolean head, boolean template, int start, int end)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = new BooleanWrapper(head);

		Object paramObj2 = new BooleanWrapper(template);

		Object paramObj3 = new IntegerWrapper(start);

		Object paramObj4 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getGroupArticles",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBArticle>)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticles(
		long groupId, boolean head, boolean template, boolean draft)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = new BooleanWrapper(head);

		Object paramObj2 = new BooleanWrapper(template);

		Object paramObj3 = new BooleanWrapper(draft);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getGroupArticles",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBArticle>)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticles(
		long groupId, boolean head, boolean template, boolean draft, int start,
		int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = new BooleanWrapper(head);

		Object paramObj2 = new BooleanWrapper(template);

		Object paramObj3 = new BooleanWrapper(draft);

		Object paramObj4 = new IntegerWrapper(start);

		Object paramObj5 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getGroupArticles",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBArticle>)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticles(
		long groupId, java.lang.String title, boolean head, int start, int end)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = translateInput(title);

		if (title == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = new BooleanWrapper(head);

		Object paramObj3 = new IntegerWrapper(start);

		Object paramObj4 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getGroupArticles",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBArticle>)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticlesIncludingUserDrafts(
		long groupId, boolean template, long userId)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = new BooleanWrapper(template);

		Object paramObj2 = new LongWrapper(userId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getGroupArticlesIncludingUserDrafts",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBArticle>)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticlesIncludingUserDrafts(
		long groupId, boolean template, long userId, int start, int end)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = new BooleanWrapper(template);

		Object paramObj2 = new LongWrapper(userId);

		Object paramObj3 = new IntegerWrapper(start);

		Object paramObj4 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getGroupArticlesIncludingUserDrafts",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBArticle>)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupsArticles(
		long[] groupIds, int start, int end)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(groupIds);

		if (groupIds == null) {
			paramObj0 = new NullWrapper("[J");
		}

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getGroupsArticles",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBArticle>)translateOutput(returnObj);
	}

	public int getGroupArticlesCount(long groupId, boolean head,
		boolean template) throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = new BooleanWrapper(head);

		Object paramObj2 = new BooleanWrapper(template);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getGroupArticlesCount",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public int getGroupArticlesCount(long groupId, java.lang.String title,
		boolean head) throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = translateInput(title);

		if (title == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = new BooleanWrapper(head);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getGroupArticlesCount",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public int getGroupArticlesCount(long groupId, boolean head,
		boolean template, boolean draft)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = new BooleanWrapper(head);

		Object paramObj2 = new BooleanWrapper(template);

		Object paramObj3 = new BooleanWrapper(draft);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getGroupArticlesCount",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public int getGroupArticlesIncludingUserDraftsCount(long groupId,
		boolean template, long userId)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = new BooleanWrapper(template);

		Object paramObj2 = new LongWrapper(userId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getGroupArticlesIncludingUserDraftsCount",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public int getGroupsArticlesCount(long[] groupIds)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(groupIds);

		if (groupIds == null) {
			paramObj0 = new NullWrapper("[J");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getGroupsArticlesCount",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getSubscribedArticles(
		long userId, long groupId) throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(userId);

		Object paramObj1 = new LongWrapper(groupId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getSubscribedArticles",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBArticle>)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getSubscribedArticles(
		long userId, long groupId, int start, int end)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(userId);

		Object paramObj1 = new LongWrapper(groupId);

		Object paramObj2 = new IntegerWrapper(start);

		Object paramObj3 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getSubscribedArticles",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBArticle>)translateOutput(returnObj);
	}

	public int getSubscribedArticlesCount(long userId, long groupId)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(userId);

		Object paramObj1 = new LongWrapper(groupId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getSubscribedArticlesCount",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public void reIndex(java.lang.String[] ids)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(ids);

		if (ids == null) {
			paramObj0 = new NullWrapper("[Ljava.lang.String;");
		}

		try {
			_classLoaderProxy.invoke("reIndex", new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public com.liferay.kb.knowledgebase.model.KBArticle revertArticle(
		long userId, long resourcePrimKey, double version,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(userId);

		Object paramObj1 = new LongWrapper(resourcePrimKey);

		Object paramObj2 = new DoubleWrapper(version);

		Object paramObj3 = translateInput(prefs);

		if (prefs == null) {
			paramObj3 = new NullWrapper("javax.portlet.PortletPreferences");
		}

		Object paramObj4 = translateInput(themeDisplay);

		if (themeDisplay == null) {
			paramObj4 = new NullWrapper("com.liferay.portal.theme.ThemeDisplay");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("revertArticle",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBArticle)translateOutput(returnObj);
	}

	public com.liferay.portal.kernel.search.Hits search(long companyId,
		long groupId, java.lang.String keywords, int start, int end)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = new LongWrapper(groupId);

		Object paramObj2 = translateInput(keywords);

		if (keywords == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = new IntegerWrapper(start);

		Object paramObj4 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("search",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.portal.kernel.search.Hits)translateOutput(returnObj);
	}

	public void subscribe(long userId, long groupId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(userId);

		Object paramObj1 = new LongWrapper(groupId);

		try {
			_classLoaderProxy.invoke("subscribe",
				new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void subscribeArticle(long userId, long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(userId);

		Object paramObj1 = new LongWrapper(resourcePrimKey);

		try {
			_classLoaderProxy.invoke("subscribeArticle",
				new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void unsubscribe(long userId, long groupId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(userId);

		Object paramObj1 = new LongWrapper(groupId);

		try {
			_classLoaderProxy.invoke("unsubscribe",
				new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void unsubscribeArticle(long userId, long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(userId);

		Object paramObj1 = new LongWrapper(resourcePrimKey);

		try {
			_classLoaderProxy.invoke("unsubscribeArticle",
				new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public com.liferay.kb.knowledgebase.model.KBArticle updateArticle(
		long userId, long resourcePrimKey, double version,
		java.lang.String title, java.lang.String content,
		java.lang.String description, boolean minorEdit, boolean template,
		boolean draft, java.lang.String[] tagsEntries,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(userId);

		Object paramObj1 = new LongWrapper(resourcePrimKey);

		Object paramObj2 = new DoubleWrapper(version);

		Object paramObj3 = translateInput(title);

		if (title == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = translateInput(content);

		if (content == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = translateInput(description);

		if (description == null) {
			paramObj5 = new NullWrapper("java.lang.String");
		}

		Object paramObj6 = new BooleanWrapper(minorEdit);

		Object paramObj7 = new BooleanWrapper(template);

		Object paramObj8 = new BooleanWrapper(draft);

		Object paramObj9 = translateInput(tagsEntries);

		if (tagsEntries == null) {
			paramObj9 = new NullWrapper("[Ljava.lang.String;");
		}

		Object paramObj10 = translateInput(prefs);

		if (prefs == null) {
			paramObj10 = new NullWrapper("javax.portlet.PortletPreferences");
		}

		Object paramObj11 = translateInput(themeDisplay);

		if (themeDisplay == null) {
			paramObj11 = new NullWrapper(
					"com.liferay.portal.theme.ThemeDisplay");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateArticle",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8, paramObj9,
						paramObj10, paramObj11
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBArticle)translateOutput(returnObj);
	}

	public void updateTagsAsset(long userId,
		com.liferay.kb.knowledgebase.model.KBArticle article,
		java.lang.String[] tagsEntries)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(userId);

		Object paramObj1 = translateInput(article);

		if (article == null) {
			paramObj1 = new NullWrapper(
					"com.liferay.kb.knowledgebase.model.KBArticle");
		}

		Object paramObj2 = translateInput(tagsEntries);

		if (tagsEntries == null) {
			paramObj2 = new NullWrapper("[Ljava.lang.String;");
		}

		try {
			_classLoaderProxy.invoke("updateTagsAsset",
				new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void validateTitle(java.lang.String title)
		throws com.liferay.portal.PortalException {
		Object paramObj0 = translateInput(title);

		if (title == null) {
			paramObj0 = new NullWrapper("java.lang.String");
		}

		try {
			_classLoaderProxy.invoke("validateTitle", new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	protected Object translateInput(BaseModel oldModel) {
		Class oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(KBArticleClp.class.getName())) {
			KBArticleClp oldCplModel = (KBArticleClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class newModelClass = Class.forName("com.liferay.kb.knowledgebase.model.impl.KBArticleImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setUuid",
							new Class[] { String.class });

					String value0 = oldCplModel.getUuid();

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setArticleId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getArticleId());

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setGroupId",
							new Class[] { Long.TYPE });

					Long value2 = new Long(oldCplModel.getGroupId());

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setResourcePrimKey",
							new Class[] { Long.TYPE });

					Long value3 = new Long(oldCplModel.getResourcePrimKey());

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setCompanyId",
							new Class[] { Long.TYPE });

					Long value4 = new Long(oldCplModel.getCompanyId());

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setUserId",
							new Class[] { Long.TYPE });

					Long value5 = new Long(oldCplModel.getUserId());

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setUserName",
							new Class[] { String.class });

					String value6 = oldCplModel.getUserName();

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setModifiedDate",
							new Class[] { Date.class });

					Date value7 = oldCplModel.getModifiedDate();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setTitle",
							new Class[] { String.class });

					String value8 = oldCplModel.getTitle();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setVersion",
							new Class[] { Double.TYPE });

					Double value9 = new Double(oldCplModel.getVersion());

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setMinorEdit",
							new Class[] { Boolean.TYPE });

					Boolean value10 = new Boolean(oldCplModel.getMinorEdit());

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setContent",
							new Class[] { String.class });

					String value11 = oldCplModel.getContent();

					method11.invoke(newModel, value11);

					Method method12 = newModelClass.getMethod("setDescription",
							new Class[] { String.class });

					String value12 = oldCplModel.getDescription();

					method12.invoke(newModel, value12);

					Method method13 = newModelClass.getMethod("setHead",
							new Class[] { Boolean.TYPE });

					Boolean value13 = new Boolean(oldCplModel.getHead());

					method13.invoke(newModel, value13);

					Method method14 = newModelClass.getMethod("setTemplate",
							new Class[] { Boolean.TYPE });

					Boolean value14 = new Boolean(oldCplModel.getTemplate());

					method14.invoke(newModel, value14);

					Method method15 = newModelClass.getMethod("setDraft",
							new Class[] { Boolean.TYPE });

					Boolean value15 = new Boolean(oldCplModel.getDraft());

					method15.invoke(newModel, value15);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		return oldModel;
	}

	protected Object translateInput(List oldList) {
		List newList = new ArrayList(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateInput(curObj));
		}

		return newList;
	}

	protected Object translateInput(Object obj) {
		if (obj instanceof BaseModel) {
			return translateInput((BaseModel)obj);
		}
		else if (obj instanceof List) {
			return translateInput((List)obj);
		}
		else {
			return obj;
		}
	}

	protected Object translateOutput(BaseModel oldModel) {
		Class oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(
					"com.liferay.kb.knowledgebase.model.impl.KBArticleImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					KBArticleClp newModel = new KBArticleClp();

					Method method0 = oldModelClass.getMethod("getUuid");

					String value0 = (String)method0.invoke(oldModel,
							(Object[])null);

					newModel.setUuid(value0);

					Method method1 = oldModelClass.getMethod("getArticleId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setArticleId(value1.longValue());

					Method method2 = oldModelClass.getMethod("getGroupId");

					Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

					newModel.setGroupId(value2.longValue());

					Method method3 = oldModelClass.getMethod(
							"getResourcePrimKey");

					Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

					newModel.setResourcePrimKey(value3.longValue());

					Method method4 = oldModelClass.getMethod("getCompanyId");

					Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

					newModel.setCompanyId(value4.longValue());

					Method method5 = oldModelClass.getMethod("getUserId");

					Long value5 = (Long)method5.invoke(oldModel, (Object[])null);

					newModel.setUserId(value5.longValue());

					Method method6 = oldModelClass.getMethod("getUserName");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setUserName(value6);

					Method method7 = oldModelClass.getMethod("getModifiedDate");

					Date value7 = (Date)method7.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value7);

					Method method8 = oldModelClass.getMethod("getTitle");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setTitle(value8);

					Method method9 = oldModelClass.getMethod("getVersion");

					Double value9 = (Double)method9.invoke(oldModel,
							(Object[])null);

					newModel.setVersion(value9.doubleValue());

					Method method10 = oldModelClass.getMethod("getMinorEdit");

					Boolean value10 = (Boolean)method10.invoke(oldModel,
							(Object[])null);

					newModel.setMinorEdit(value10.booleanValue());

					Method method11 = oldModelClass.getMethod("getContent");

					String value11 = (String)method11.invoke(oldModel,
							(Object[])null);

					newModel.setContent(value11);

					Method method12 = oldModelClass.getMethod("getDescription");

					String value12 = (String)method12.invoke(oldModel,
							(Object[])null);

					newModel.setDescription(value12);

					Method method13 = oldModelClass.getMethod("getHead");

					Boolean value13 = (Boolean)method13.invoke(oldModel,
							(Object[])null);

					newModel.setHead(value13.booleanValue());

					Method method14 = oldModelClass.getMethod("getTemplate");

					Boolean value14 = (Boolean)method14.invoke(oldModel,
							(Object[])null);

					newModel.setTemplate(value14.booleanValue());

					Method method15 = oldModelClass.getMethod("getDraft");

					Boolean value15 = (Boolean)method15.invoke(oldModel,
							(Object[])null);

					newModel.setDraft(value15.booleanValue());

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		return oldModel;
	}

	protected Object translateOutput(List oldList) {
		List newList = new ArrayList(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateOutput(curObj));
		}

		return newList;
	}

	protected Object translateOutput(Object obj) {
		if (obj instanceof BaseModel) {
			return translateOutput((BaseModel)obj);
		}
		else if (obj instanceof List) {
			return translateOutput((List)obj);
		}
		else {
			return obj;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(KBArticleLocalServiceClp.class);
	private ClassLoaderProxy _classLoaderProxy;
	private ClassLoader _classLoader;
}