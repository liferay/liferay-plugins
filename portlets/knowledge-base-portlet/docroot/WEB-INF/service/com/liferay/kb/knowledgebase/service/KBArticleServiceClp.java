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

import com.liferay.portal.kernel.util.BooleanWrapper;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.DoubleWrapper;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.kernel.util.NullWrapper;

/**
 * <a href="KBArticleServiceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBArticleServiceClp implements KBArticleService {
	public KBArticleServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
	}

	public com.liferay.kb.knowledgebase.model.KBArticle addArticle(long plid,
		java.lang.String title, java.lang.String content,
		java.lang.String description, boolean minorEdit, boolean template,
		boolean draft, long parentResourcePrimKey,
		java.lang.String[] tagsEntries, javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(plid);

		Object paramObj1 = ClpSerializer.translateInput(title);

		if (title == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = ClpSerializer.translateInput(content);

		if (content == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(description);

		if (description == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = new BooleanWrapper(minorEdit);

		Object paramObj5 = new BooleanWrapper(template);

		Object paramObj6 = new BooleanWrapper(draft);

		Object paramObj7 = new LongWrapper(parentResourcePrimKey);

		Object paramObj8 = ClpSerializer.translateInput(tagsEntries);

		if (tagsEntries == null) {
			paramObj8 = new NullWrapper("[Ljava.lang.String;");
		}

		Object paramObj9 = ClpSerializer.translateInput(prefs);

		if (prefs == null) {
			paramObj9 = new NullWrapper("javax.portlet.PortletPreferences");
		}

		Object paramObj10 = ClpSerializer.translateInput(themeDisplay);

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

		return (com.liferay.kb.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public void addArticleAttachments(long resourcePrimKey,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<String, byte[]>> files)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		Object paramObj1 = ClpSerializer.translateInput(files);

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

	public void deleteArticle(long plid, long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(plid);

		Object paramObj1 = new LongWrapper(resourcePrimKey);

		try {
			_classLoaderProxy.invoke("deleteArticle",
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

	public void deleteArticleAttachment(long resourcePrimKey,
		java.lang.String fileName)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		Object paramObj1 = ClpSerializer.translateInput(fileName);

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

		return (com.liferay.kb.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
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

		return (com.liferay.kb.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long resourcePrimKey, double version, long plid)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		Object paramObj1 = new DoubleWrapper(version);

		Object paramObj2 = new LongWrapper(plid);

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

		return (com.liferay.kb.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long groupId, java.lang.String title)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = ClpSerializer.translateInput(title);

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

		return (com.liferay.kb.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long groupId, java.lang.String title, double version)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = ClpSerializer.translateInput(title);

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

		return (com.liferay.kb.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.kb.knowledgebase.model.KBArticle getArticle(
		long groupId, java.lang.String title, double version, long plid)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = ClpSerializer.translateInput(title);

		if (title == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = new DoubleWrapper(version);

		Object paramObj3 = new LongWrapper(plid);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getArticle",
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

		return (com.liferay.kb.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getArticles(
		long resourcePrimKey, int start, int end)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getArticles",
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

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBArticle>)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String getArticlesRSS(long resourcePrimKey, int max,
		java.lang.String type, double version, java.lang.String displayStyle,
		int abstractLength, java.lang.String feedURL)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		Object paramObj1 = new IntegerWrapper(max);

		Object paramObj2 = ClpSerializer.translateInput(type);

		if (type == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = new DoubleWrapper(version);

		Object paramObj4 = ClpSerializer.translateInput(displayStyle);

		if (displayStyle == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = new IntegerWrapper(abstractLength);

		Object paramObj6 = ClpSerializer.translateInput(feedURL);

		if (feedURL == null) {
			paramObj6 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getArticlesRSS",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6
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

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getCompanyArticles(
		long companyId, int max)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = new IntegerWrapper(max);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getCompanyArticles",
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

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBArticle>)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String getCompanyArticlesRSS(long companyId, int max,
		java.lang.String type, double version, java.lang.String displayStyle,
		int abstractLength, java.lang.String description,
		java.lang.String feedURL,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = new IntegerWrapper(max);

		Object paramObj2 = ClpSerializer.translateInput(type);

		if (type == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = new DoubleWrapper(version);

		Object paramObj4 = ClpSerializer.translateInput(displayStyle);

		if (displayStyle == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = new IntegerWrapper(abstractLength);

		Object paramObj6 = ClpSerializer.translateInput(description);

		if (description == null) {
			paramObj6 = new NullWrapper("java.lang.String");
		}

		Object paramObj7 = ClpSerializer.translateInput(feedURL);

		if (feedURL == null) {
			paramObj7 = new NullWrapper("java.lang.String");
		}

		Object paramObj8 = ClpSerializer.translateInput(themeDisplay);

		if (themeDisplay == null) {
			paramObj8 = new NullWrapper("com.liferay.portal.theme.ThemeDisplay");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getCompanyArticlesRSS",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8
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

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticlesIncludingUserDrafts(
		long groupId, boolean template, long userId, int start, int end)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
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

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBArticle>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticlesIncludingUserDrafts(
		long groupId, boolean template, long userId, int max)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = new BooleanWrapper(template);

		Object paramObj2 = new LongWrapper(userId);

		Object paramObj3 = new IntegerWrapper(max);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getGroupArticlesIncludingUserDrafts",
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

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBArticle>)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String getGroupArticlesIncludingUserDraftsRSS(
		long groupId, int max, java.lang.String type, double version,
		java.lang.String displayStyle, int abstractLength,
		java.lang.String description, java.lang.String feedURL,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = new IntegerWrapper(max);

		Object paramObj2 = ClpSerializer.translateInput(type);

		if (type == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = new DoubleWrapper(version);

		Object paramObj4 = ClpSerializer.translateInput(displayStyle);

		if (displayStyle == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = new IntegerWrapper(abstractLength);

		Object paramObj6 = ClpSerializer.translateInput(description);

		if (description == null) {
			paramObj6 = new NullWrapper("java.lang.String");
		}

		Object paramObj7 = ClpSerializer.translateInput(feedURL);

		if (feedURL == null) {
			paramObj7 = new NullWrapper("java.lang.String");
		}

		Object paramObj8 = ClpSerializer.translateInput(themeDisplay);

		if (themeDisplay == null) {
			paramObj8 = new NullWrapper("com.liferay.portal.theme.ThemeDisplay");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getGroupArticlesIncludingUserDraftsRSS",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8
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

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupsArticles(
		long[] groupIds, int max)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(groupIds);

		if (groupIds == null) {
			paramObj0 = new NullWrapper("[J");
		}

		Object paramObj1 = new IntegerWrapper(max);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getGroupsArticles",
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

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBArticle>)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String getGroupsArticlesRSS(long[] groupIds, int max,
		java.lang.String type, double version, java.lang.String displayStyle,
		int abstractLength, java.lang.String description,
		java.lang.String feedURL,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(groupIds);

		if (groupIds == null) {
			paramObj0 = new NullWrapper("[J");
		}

		Object paramObj1 = new IntegerWrapper(max);

		Object paramObj2 = ClpSerializer.translateInput(type);

		if (type == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = new DoubleWrapper(version);

		Object paramObj4 = ClpSerializer.translateInput(displayStyle);

		if (displayStyle == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = new IntegerWrapper(abstractLength);

		Object paramObj6 = ClpSerializer.translateInput(description);

		if (description == null) {
			paramObj6 = new NullWrapper("java.lang.String");
		}

		Object paramObj7 = ClpSerializer.translateInput(feedURL);

		if (feedURL == null) {
			paramObj7 = new NullWrapper("java.lang.String");
		}

		Object paramObj8 = ClpSerializer.translateInput(themeDisplay);

		if (themeDisplay == null) {
			paramObj8 = new NullWrapper("com.liferay.portal.theme.ThemeDisplay");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getGroupsArticlesRSS",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8
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

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getSubscribedArticles(
		long userId, long groupId, int start, int end)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
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

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBArticle>)ClpSerializer.translateOutput(returnObj);
	}

	public void importDocbook(long groupId, java.io.File file,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws java.lang.Exception {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = ClpSerializer.translateInput(file);

		if (file == null) {
			paramObj1 = new NullWrapper("java.io.File");
		}

		Object paramObj2 = ClpSerializer.translateInput(prefs);

		if (prefs == null) {
			paramObj2 = new NullWrapper("javax.portlet.PortletPreferences");
		}

		Object paramObj3 = ClpSerializer.translateInput(themeDisplay);

		if (themeDisplay == null) {
			paramObj3 = new NullWrapper("com.liferay.portal.theme.ThemeDisplay");
		}

		try {
			_classLoaderProxy.invoke("importDocbook",
				new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
		}
		catch (Throwable t) {
			if (t instanceof java.lang.Exception) {
				throw (java.lang.Exception)t;
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
		long resourcePrimKey, double version,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		Object paramObj1 = new DoubleWrapper(version);

		Object paramObj2 = ClpSerializer.translateInput(prefs);

		if (prefs == null) {
			paramObj2 = new NullWrapper("javax.portlet.PortletPreferences");
		}

		Object paramObj3 = ClpSerializer.translateInput(themeDisplay);

		if (themeDisplay == null) {
			paramObj3 = new NullWrapper("com.liferay.portal.theme.ThemeDisplay");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("revertArticle",
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

		return (com.liferay.kb.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public void subscribe(long plid)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(plid);

		try {
			_classLoaderProxy.invoke("subscribe", new Object[] { paramObj0 });
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

	public void subscribeArticle(long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		try {
			_classLoaderProxy.invoke("subscribeArticle",
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

	public void unsubscribe(long plid)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(plid);

		try {
			_classLoaderProxy.invoke("unsubscribe", new Object[] { paramObj0 });
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

	public void unsubscribeArticle(long resourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		try {
			_classLoaderProxy.invoke("unsubscribeArticle",
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

	public com.liferay.kb.knowledgebase.model.KBArticle updateArticle(
		long plid, long resourcePrimKey, double version,
		java.lang.String title, java.lang.String content,
		java.lang.String description, boolean minorEdit, boolean template,
		boolean draft, long parentResourcePrimKey,
		java.lang.String[] tagsEntries, javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(plid);

		Object paramObj1 = new LongWrapper(resourcePrimKey);

		Object paramObj2 = new DoubleWrapper(version);

		Object paramObj3 = ClpSerializer.translateInput(title);

		if (title == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = ClpSerializer.translateInput(content);

		if (content == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = ClpSerializer.translateInput(description);

		if (description == null) {
			paramObj5 = new NullWrapper("java.lang.String");
		}

		Object paramObj6 = new BooleanWrapper(minorEdit);

		Object paramObj7 = new BooleanWrapper(template);

		Object paramObj8 = new BooleanWrapper(draft);

		Object paramObj9 = new LongWrapper(parentResourcePrimKey);

		Object paramObj10 = ClpSerializer.translateInput(tagsEntries);

		if (tagsEntries == null) {
			paramObj10 = new NullWrapper("[Ljava.lang.String;");
		}

		Object paramObj11 = ClpSerializer.translateInput(prefs);

		if (prefs == null) {
			paramObj11 = new NullWrapper("javax.portlet.PortletPreferences");
		}

		Object paramObj12 = ClpSerializer.translateInput(themeDisplay);

		if (themeDisplay == null) {
			paramObj12 = new NullWrapper(
					"com.liferay.portal.theme.ThemeDisplay");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateArticle",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8, paramObj9,
						paramObj10, paramObj11, paramObj12
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

		return (com.liferay.kb.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	private ClassLoaderProxy _classLoaderProxy;
}