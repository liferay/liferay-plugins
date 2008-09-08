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
 * <a href="KBArticleServiceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBArticleServiceClp implements KBArticleService {
	public KBArticleServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
		_classLoader = classLoaderProxy.getClassLoader();
	}

	public com.liferay.kb.knowledgebase.model.KBArticle addArticle(long plid,
		java.lang.String title, java.lang.String content,
		java.lang.String description, boolean minorEdit, boolean template,
		boolean draft, java.lang.String[] tagsEntries,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		Object paramObj0 = new LongWrapper(plid);

		Object paramObj1 = translateInput(title);

		if (title == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = translateInput(content);

		if (content == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = translateInput(description);

		if (description == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = new BooleanWrapper(minorEdit);

		Object paramObj5 = new BooleanWrapper(template);

		Object paramObj6 = new BooleanWrapper(draft);

		Object paramObj7 = translateInput(tagsEntries);

		if (tagsEntries == null) {
			paramObj7 = new NullWrapper("[Ljava.lang.String;");
		}

		Object paramObj8 = translateInput(prefs);

		if (prefs == null) {
			paramObj8 = new NullWrapper("javax.portlet.PortletPreferences");
		}

		Object paramObj9 = translateInput(themeDisplay);

		if (themeDisplay == null) {
			paramObj9 = new NullWrapper("com.liferay.portal.theme.ThemeDisplay");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addArticle",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8, paramObj9
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)t;
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
			com.liferay.portal.SystemException, java.rmi.RemoteException {
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

			if (t instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)t;
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
			com.liferay.portal.SystemException, java.rmi.RemoteException {
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

			if (t instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)t;
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
			com.liferay.portal.SystemException, java.rmi.RemoteException {
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

			if (t instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)t;
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
			com.liferay.portal.SystemException, java.rmi.RemoteException {
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

			if (t instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)t;
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
			com.liferay.portal.SystemException, java.rmi.RemoteException {
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

			if (t instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)t;
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
		long resourcePrimKey, double version, long plid)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
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

			if (t instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)t;
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
			com.liferay.portal.SystemException, java.rmi.RemoteException {
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

			if (t instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)t;
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
			com.liferay.portal.SystemException, java.rmi.RemoteException {
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

			if (t instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)t;
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
		long groupId, java.lang.String title, double version, long plid)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = translateInput(title);

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

			if (t instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)t;
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

	public java.lang.String getArticlesRSS(long resourcePrimKey, int max,
		java.lang.String type, double version, java.lang.String displayStyle,
		int abstractLength, java.lang.String feedURL)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		Object paramObj1 = new IntegerWrapper(max);

		Object paramObj2 = translateInput(type);

		if (type == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = new DoubleWrapper(version);

		Object paramObj4 = translateInput(displayStyle);

		if (displayStyle == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = new IntegerWrapper(abstractLength);

		Object paramObj6 = translateInput(feedURL);

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

			if (t instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getCompanyArticles(
		long companyId, int max)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
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

			if (t instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)t;
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

	public java.lang.String getCompanyArticlesRSS(long companyId, int max,
		java.lang.String type, double version, java.lang.String displayStyle,
		int abstractLength, java.lang.String description,
		java.lang.String feedURL,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = new IntegerWrapper(max);

		Object paramObj2 = translateInput(type);

		if (type == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = new DoubleWrapper(version);

		Object paramObj4 = translateInput(displayStyle);

		if (displayStyle == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = new IntegerWrapper(abstractLength);

		Object paramObj6 = translateInput(description);

		if (description == null) {
			paramObj6 = new NullWrapper("java.lang.String");
		}

		Object paramObj7 = translateInput(feedURL);

		if (feedURL == null) {
			paramObj7 = new NullWrapper("java.lang.String");
		}

		Object paramObj8 = translateInput(themeDisplay);

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

			if (t instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> getGroupArticles(
		long userId, long groupId, boolean template, int max)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		Object paramObj0 = new LongWrapper(userId);

		Object paramObj1 = new LongWrapper(groupId);

		Object paramObj2 = new BooleanWrapper(template);

		Object paramObj3 = new IntegerWrapper(max);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getGroupArticles",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)t;
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

	public java.lang.String getGroupArticlesRSS(long groupId, int max,
		java.lang.String type, double version, java.lang.String displayStyle,
		int abstractLength, java.lang.String description,
		java.lang.String feedURL,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = new IntegerWrapper(max);

		Object paramObj2 = translateInput(type);

		if (type == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = new DoubleWrapper(version);

		Object paramObj4 = translateInput(displayStyle);

		if (displayStyle == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = new IntegerWrapper(abstractLength);

		Object paramObj6 = translateInput(description);

		if (description == null) {
			paramObj6 = new NullWrapper("java.lang.String");
		}

		Object paramObj7 = translateInput(feedURL);

		if (feedURL == null) {
			paramObj7 = new NullWrapper("java.lang.String");
		}

		Object paramObj8 = translateInput(themeDisplay);

		if (themeDisplay == null) {
			paramObj8 = new NullWrapper("com.liferay.portal.theme.ThemeDisplay");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getGroupArticlesRSS",
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

			if (t instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)translateOutput(returnObj);
	}

	public com.liferay.kb.knowledgebase.model.KBArticle revertArticle(
		long resourcePrimKey, double version,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		Object paramObj1 = new DoubleWrapper(version);

		Object paramObj2 = translateInput(prefs);

		if (prefs == null) {
			paramObj2 = new NullWrapper("javax.portlet.PortletPreferences");
		}

		Object paramObj3 = translateInput(themeDisplay);

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

			if (t instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)t;
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

	public void subscribe(long plid)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
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

			if (t instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)t;
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
			com.liferay.portal.SystemException, java.rmi.RemoteException {
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

			if (t instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)t;
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
			com.liferay.portal.SystemException, java.rmi.RemoteException {
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

			if (t instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)t;
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
			com.liferay.portal.SystemException, java.rmi.RemoteException {
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

			if (t instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)t;
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
		boolean draft, java.lang.String[] tagsEntries,
		javax.portlet.PortletPreferences prefs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException, java.rmi.RemoteException {
		Object paramObj0 = new LongWrapper(plid);

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

			if (t instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException)t;
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

	private static Log _log = LogFactoryUtil.getLog(KBArticleServiceClp.class);
	private ClassLoaderProxy _classLoaderProxy;
	private ClassLoader _classLoader;
}