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

package com.liferay.knowledgebase.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.knowledgebase.service.KBArticleServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.security.auth.HttpPrincipal;
import com.liferay.portal.service.http.TunnelUtil;

/**
 * Provides the HTTP utility for the
 * {@link KBArticleServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBArticleServiceSoap
 * @see HttpPrincipal
 * @see KBArticleServiceUtil
 * @generated
 */
@ProviderType
public class KBArticleServiceHttp {
	public static com.liferay.knowledgebase.model.KBArticle addKBArticle(
		HttpPrincipal httpPrincipal, java.lang.String portletId,
		long parentResourceClassNameId, long parentResourcePrimKey,
		java.lang.String title, java.lang.String urlTitle,
		java.lang.String content, java.lang.String description,
		java.lang.String sourceURL, java.lang.String[] sections,
		java.lang.String[] selectedFileNames,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"addKBArticle", _addKBArticleParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					portletId, parentResourceClassNameId,
					parentResourcePrimKey, title, urlTitle, content,
					description, sourceURL, sections, selectedFileNames,
					serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.knowledgebase.model.KBArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int addKBArticlesMarkdown(HttpPrincipal httpPrincipal,
		long groupId, long parentKBFolderId, java.lang.String fileName,
		boolean prioritizeByNumericalPrefix, java.io.InputStream inputStream,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"addKBArticlesMarkdown",
					_addKBArticlesMarkdownParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentKBFolderId, fileName, prioritizeByNumericalPrefix,
					inputStream, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void addTempAttachment(HttpPrincipal httpPrincipal,
		long groupId, long resourcePrimKey, java.lang.String fileName,
		java.lang.String tempFolderName, java.io.InputStream inputStream,
		java.lang.String mimeType)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"addTempAttachment", _addTempAttachmentParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					resourcePrimKey, fileName, tempFolderName, inputStream,
					mimeType);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.knowledgebase.model.KBArticle deleteKBArticle(
		HttpPrincipal httpPrincipal, long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"deleteKBArticle", _deleteKBArticleParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					resourcePrimKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.knowledgebase.model.KBArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteKBArticles(HttpPrincipal httpPrincipal,
		long groupId, long[] resourcePrimKeys)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"deleteKBArticles", _deleteKBArticlesParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					resourcePrimKeys);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteTempAttachment(HttpPrincipal httpPrincipal,
		long groupId, long resourcePrimKey, java.lang.String fileName,
		java.lang.String tempFolderName)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"deleteTempAttachment", _deleteTempAttachmentParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					resourcePrimKey, fileName, tempFolderName);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.knowledgebase.model.KBArticle fetchLatestKBArticle(
		HttpPrincipal httpPrincipal, long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"fetchLatestKBArticle", _fetchLatestKBArticleParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					resourcePrimKey, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.knowledgebase.model.KBArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getGroupKBArticles(
		HttpPrincipal httpPrincipal, long groupId, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBArticle> orderByComparator) {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"getGroupKBArticles", _getGroupKBArticlesParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					status, start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getGroupKBArticlesCount(HttpPrincipal httpPrincipal,
		long groupId, int status) {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"getGroupKBArticlesCount",
					_getGroupKBArticlesCountParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.lang.String getGroupKBArticlesRSS(
		HttpPrincipal httpPrincipal, int status, int rssDelta,
		java.lang.String rssDisplayStyle, java.lang.String rssFormat,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"getGroupKBArticlesRSS",
					_getGroupKBArticlesRSSParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey, status,
					rssDelta, rssDisplayStyle, rssFormat, themeDisplay);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.lang.String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.knowledgebase.model.KBArticle getKBArticle(
		HttpPrincipal httpPrincipal, long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"getKBArticle", _getKBArticleParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					resourcePrimKey, version);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.knowledgebase.model.KBArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticleAndAllDescendantKBArticles(
		HttpPrincipal httpPrincipal, long groupId, long resourcePrimKey,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBArticle> orderByComparator) {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"getKBArticleAndAllDescendantKBArticles",
					_getKBArticleAndAllDescendantKBArticlesParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					resourcePrimKey, status, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticleAndAllDescendants(
		HttpPrincipal httpPrincipal, long groupId, long resourcePrimKey,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBArticle> orderByComparator) {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"getKBArticleAndAllDescendants",
					_getKBArticleAndAllDescendantsParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					resourcePrimKey, status, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.lang.String getKBArticleRSS(
		HttpPrincipal httpPrincipal, long resourcePrimKey, int status,
		int rssDelta, java.lang.String rssDisplayStyle,
		java.lang.String rssFormat,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"getKBArticleRSS", _getKBArticleRSSParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					resourcePrimKey, status, rssDelta, rssDisplayStyle,
					rssFormat, themeDisplay);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.lang.String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticles(
		HttpPrincipal httpPrincipal, long groupId, long parentResourcePrimKey,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBArticle> orderByComparator) {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"getKBArticles", _getKBArticlesParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentResourcePrimKey, status, start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticles(
		HttpPrincipal httpPrincipal, long groupId, long[] resourcePrimKeys,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBArticle> orderByComparator) {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"getKBArticles", _getKBArticlesParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					resourcePrimKeys, status, start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticles(
		HttpPrincipal httpPrincipal, long groupId, long[] resourcePrimKeys,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBArticle> orderByComparator) {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"getKBArticles", _getKBArticlesParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					resourcePrimKeys, status, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getKBArticlesCount(HttpPrincipal httpPrincipal,
		long groupId, long parentResourcePrimKey, int status) {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"getKBArticlesCount", _getKBArticlesCountParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentResourcePrimKey, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getKBArticlesCount(HttpPrincipal httpPrincipal,
		long groupId, long[] resourcePrimKeys, int status) {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"getKBArticlesCount", _getKBArticlesCountParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					resourcePrimKeys, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.knowledgebase.model.KBArticleSearchDisplay getKBArticleSearchDisplay(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String title,
		java.lang.String content, int status, java.util.Date startDate,
		java.util.Date endDate, boolean andOperator, int[] curStartValues,
		int cur, int delta,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBArticle> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"getKBArticleSearchDisplay",
					_getKBArticleSearchDisplayParameterTypes19);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					title, content, status, startDate, endDate, andOperator,
					curStartValues, cur, delta, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.knowledgebase.model.KBArticleSearchDisplay)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticleVersions(
		HttpPrincipal httpPrincipal, long groupId, long resourcePrimKey,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBArticle> orderByComparator) {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"getKBArticleVersions",
					_getKBArticleVersionsParameterTypes20);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					resourcePrimKey, status, start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getKBArticleVersionsCount(HttpPrincipal httpPrincipal,
		long groupId, long resourcePrimKey, int status) {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"getKBArticleVersionsCount",
					_getKBArticleVersionsCountParameterTypes21);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					resourcePrimKey, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.knowledgebase.model.KBArticle getLatestKBArticle(
		HttpPrincipal httpPrincipal, long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"getLatestKBArticle", _getLatestKBArticleParameterTypes22);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					resourcePrimKey, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.knowledgebase.model.KBArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getSectionsKBArticles(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String[] sections,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBArticle> orderByComparator) {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"getSectionsKBArticles",
					_getSectionsKBArticlesParameterTypes23);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					sections, status, start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getSectionsKBArticlesCount(HttpPrincipal httpPrincipal,
		long groupId, java.lang.String[] sections, int status) {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"getSectionsKBArticlesCount",
					_getSectionsKBArticlesCountParameterTypes24);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					sections, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getSiblingKBArticles(
		HttpPrincipal httpPrincipal, long groupId, long parentResourcePrimKey,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBArticle> orderByComparator) {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"getSiblingKBArticles",
					_getSiblingKBArticlesParameterTypes25);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentResourcePrimKey, status, start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getSiblingKBArticlesCount(HttpPrincipal httpPrincipal,
		long groupId, long parentResourcePrimKey, int status) {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"getSiblingKBArticlesCount",
					_getSiblingKBArticlesCountParameterTypes26);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentResourcePrimKey, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.lang.String[] getTempAttachmentNames(
		HttpPrincipal httpPrincipal, long groupId,
		java.lang.String tempFolderName)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"getTempAttachmentNames",
					_getTempAttachmentNamesParameterTypes27);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					tempFolderName);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.lang.String[])returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void moveKBArticle(HttpPrincipal httpPrincipal,
		long resourcePrimKey, long parentResourceClassNameId,
		long parentResourcePrimKey, double priority)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"moveKBArticle", _moveKBArticleParameterTypes28);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					resourcePrimKey, parentResourceClassNameId,
					parentResourcePrimKey, priority);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.knowledgebase.model.KBArticle revertKBArticle(
		HttpPrincipal httpPrincipal, long resourcePrimKey, int version,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"revertKBArticle", _revertKBArticleParameterTypes29);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					resourcePrimKey, version, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.knowledgebase.model.KBArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void subscribeGroupKBArticles(HttpPrincipal httpPrincipal,
		long groupId, java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"subscribeGroupKBArticles",
					_subscribeGroupKBArticlesParameterTypes30);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					portletId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void subscribeKBArticle(HttpPrincipal httpPrincipal,
		long groupId, long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"subscribeKBArticle", _subscribeKBArticleParameterTypes31);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					resourcePrimKey);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void unsubscribeGroupKBArticles(HttpPrincipal httpPrincipal,
		long groupId, java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"unsubscribeGroupKBArticles",
					_unsubscribeGroupKBArticlesParameterTypes32);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					portletId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void unsubscribeKBArticle(HttpPrincipal httpPrincipal,
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"unsubscribeKBArticle",
					_unsubscribeKBArticleParameterTypes33);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					resourcePrimKey);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.knowledgebase.model.KBArticle updateKBArticle(
		HttpPrincipal httpPrincipal, long resourcePrimKey,
		java.lang.String title, java.lang.String content,
		java.lang.String description, java.lang.String sourceURL,
		java.lang.String[] sections, java.lang.String[] selectedFileNames,
		long[] removeFileEntryIds,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"updateKBArticle", _updateKBArticleParameterTypes34);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					resourcePrimKey, title, content, description, sourceURL,
					sections, selectedFileNames, removeFileEntryIds,
					serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.knowledgebase.model.KBArticle)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void updateKBArticlesPriorities(HttpPrincipal httpPrincipal,
		long groupId,
		java.util.Map<java.lang.Long, java.lang.Double> resourcePrimKeyToPriorityMap)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBArticleServiceUtil.class,
					"updateKBArticlesPriorities",
					_updateKBArticlesPrioritiesParameterTypes35);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					resourcePrimKeyToPriorityMap);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(KBArticleServiceHttp.class);
	private static final Class<?>[] _addKBArticleParameterTypes0 = new Class[] {
			java.lang.String.class, long.class, long.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String[].class,
			java.lang.String[].class,
			com.liferay.portal.service.ServiceContext.class
		};
	private static final Class<?>[] _addKBArticlesMarkdownParameterTypes1 = new Class[] {
			long.class, long.class, java.lang.String.class, boolean.class,
			java.io.InputStream.class,
			com.liferay.portal.service.ServiceContext.class
		};
	private static final Class<?>[] _addTempAttachmentParameterTypes2 = new Class[] {
			long.class, long.class, java.lang.String.class,
			java.lang.String.class, java.io.InputStream.class,
			java.lang.String.class
		};
	private static final Class<?>[] _deleteKBArticleParameterTypes3 = new Class[] {
			long.class
		};
	private static final Class<?>[] _deleteKBArticlesParameterTypes4 = new Class[] {
			long.class, long[].class
		};
	private static final Class<?>[] _deleteTempAttachmentParameterTypes5 = new Class[] {
			long.class, long.class, java.lang.String.class,
			java.lang.String.class
		};
	private static final Class<?>[] _fetchLatestKBArticleParameterTypes6 = new Class[] {
			long.class, int.class
		};
	private static final Class<?>[] _getGroupKBArticlesParameterTypes7 = new Class[] {
			long.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getGroupKBArticlesCountParameterTypes8 = new Class[] {
			long.class, int.class
		};
	private static final Class<?>[] _getGroupKBArticlesRSSParameterTypes9 = new Class[] {
			int.class, int.class, java.lang.String.class, java.lang.String.class,
			com.liferay.portal.theme.ThemeDisplay.class
		};
	private static final Class<?>[] _getKBArticleParameterTypes10 = new Class[] {
			long.class, int.class
		};
	private static final Class<?>[] _getKBArticleAndAllDescendantKBArticlesParameterTypes11 =
		new Class[] {
			long.class, long.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getKBArticleAndAllDescendantsParameterTypes12 =
		new Class[] {
			long.class, long.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getKBArticleRSSParameterTypes13 = new Class[] {
			long.class, int.class, int.class, java.lang.String.class,
			java.lang.String.class, com.liferay.portal.theme.ThemeDisplay.class
		};
	private static final Class<?>[] _getKBArticlesParameterTypes14 = new Class[] {
			long.class, long.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getKBArticlesParameterTypes15 = new Class[] {
			long.class, long[].class, int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getKBArticlesParameterTypes16 = new Class[] {
			long.class, long[].class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getKBArticlesCountParameterTypes17 = new Class[] {
			long.class, long.class, int.class
		};
	private static final Class<?>[] _getKBArticlesCountParameterTypes18 = new Class[] {
			long.class, long[].class, int.class
		};
	private static final Class<?>[] _getKBArticleSearchDisplayParameterTypes19 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			int.class, java.util.Date.class, java.util.Date.class, boolean.class,
			int[].class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getKBArticleVersionsParameterTypes20 = new Class[] {
			long.class, long.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getKBArticleVersionsCountParameterTypes21 = new Class[] {
			long.class, long.class, int.class
		};
	private static final Class<?>[] _getLatestKBArticleParameterTypes22 = new Class[] {
			long.class, int.class
		};
	private static final Class<?>[] _getSectionsKBArticlesParameterTypes23 = new Class[] {
			long.class, java.lang.String[].class, int.class, int.class,
			int.class, com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getSectionsKBArticlesCountParameterTypes24 = new Class[] {
			long.class, java.lang.String[].class, int.class
		};
	private static final Class<?>[] _getSiblingKBArticlesParameterTypes25 = new Class[] {
			long.class, long.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getSiblingKBArticlesCountParameterTypes26 = new Class[] {
			long.class, long.class, int.class
		};
	private static final Class<?>[] _getTempAttachmentNamesParameterTypes27 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _moveKBArticleParameterTypes28 = new Class[] {
			long.class, long.class, long.class, double.class
		};
	private static final Class<?>[] _revertKBArticleParameterTypes29 = new Class[] {
			long.class, int.class,
			com.liferay.portal.service.ServiceContext.class
		};
	private static final Class<?>[] _subscribeGroupKBArticlesParameterTypes30 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _subscribeKBArticleParameterTypes31 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _unsubscribeGroupKBArticlesParameterTypes32 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _unsubscribeKBArticleParameterTypes33 = new Class[] {
			long.class
		};
	private static final Class<?>[] _updateKBArticleParameterTypes34 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String[].class, java.lang.String[].class, long[].class,
			com.liferay.portal.service.ServiceContext.class
		};
	private static final Class<?>[] _updateKBArticlesPrioritiesParameterTypes35 = new Class[] {
			long.class, java.util.Map.class
		};
}