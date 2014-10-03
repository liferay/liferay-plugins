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

import com.liferay.knowledgebase.service.KBArticleServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.liferay.knowledgebase.service.KBArticleServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.knowledgebase.model.KBArticleSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.knowledgebase.model.KBArticle}, that is translated to a
 * {@link com.liferay.knowledgebase.model.KBArticleSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBArticleServiceHttp
 * @see com.liferay.knowledgebase.model.KBArticleSoap
 * @see com.liferay.knowledgebase.service.KBArticleServiceUtil
 * @generated
 */
public class KBArticleServiceSoap {
	public static com.liferay.knowledgebase.model.KBArticleSoap addKBArticle(
		java.lang.String portletId, long parentResourceClassNameId,
		long parentResourcePrimKey, java.lang.String title,
		java.lang.String urlTitle, java.lang.String content,
		java.lang.String description, java.lang.String sourceURL,
		java.lang.String[] sections, java.lang.String[] selectedFileNames,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBArticle returnValue = KBArticleServiceUtil.addKBArticle(portletId,
					parentResourceClassNameId, parentResourcePrimKey, title,
					urlTitle, content, description, sourceURL, sections,
					selectedFileNames, serviceContext);

			return com.liferay.knowledgebase.model.KBArticleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBArticleSoap deleteKBArticle(
		long resourcePrimKey) throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBArticle returnValue = KBArticleServiceUtil.deleteKBArticle(resourcePrimKey);

			return com.liferay.knowledgebase.model.KBArticleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteKBArticles(long groupId, long[] resourcePrimKeys)
		throws RemoteException {
		try {
			KBArticleServiceUtil.deleteKBArticles(groupId, resourcePrimKeys);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteTempAttachment(long groupId, long resourcePrimKey,
		java.lang.String fileName, java.lang.String tempFolderName)
		throws RemoteException {
		try {
			KBArticleServiceUtil.deleteTempAttachment(groupId, resourcePrimKey,
				fileName, tempFolderName);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBArticleSoap fetchLatestKBArticle(
		long resourcePrimKey, int status) throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBArticle returnValue = KBArticleServiceUtil.fetchLatestKBArticle(resourcePrimKey,
					status);

			return com.liferay.knowledgebase.model.KBArticleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBArticleSoap[] getGroupKBArticles(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.knowledgebase.model.KBArticle> returnValue =
				KBArticleServiceUtil.getGroupKBArticles(groupId, status, start,
					end, orderByComparator);

			return com.liferay.knowledgebase.model.KBArticleSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getGroupKBArticlesCount(long groupId, int status)
		throws RemoteException {
		try {
			int returnValue = KBArticleServiceUtil.getGroupKBArticlesCount(groupId,
					status);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBArticleSoap getKBArticle(
		long resourcePrimKey, int version) throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBArticle returnValue = KBArticleServiceUtil.getKBArticle(resourcePrimKey,
					version);

			return com.liferay.knowledgebase.model.KBArticleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBArticleSoap[] getKBArticleAndAllDescendantKBArticles(
		long groupId, long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.knowledgebase.model.KBArticle> returnValue =
				KBArticleServiceUtil.getKBArticleAndAllDescendantKBArticles(groupId,
					resourcePrimKey, status, orderByComparator);

			return com.liferay.knowledgebase.model.KBArticleSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* @deprecated As of 7.0.0, replaced by
	{@link #getKBArticleAndAllDescendantKBArticles(long, long,
	int, com.liferay.portal.kernel.util.OrderByComparator)}
	*/
	public static com.liferay.knowledgebase.model.KBArticleSoap[] getKBArticleAndAllDescendants(
		long groupId, long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.knowledgebase.model.KBArticle> returnValue =
				KBArticleServiceUtil.getKBArticleAndAllDescendants(groupId,
					resourcePrimKey, status, orderByComparator);

			return com.liferay.knowledgebase.model.KBArticleSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBArticleSoap[] getKBArticles(
		long groupId, long parentResourcePrimKey, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.knowledgebase.model.KBArticle> returnValue =
				KBArticleServiceUtil.getKBArticles(groupId,
					parentResourcePrimKey, status, start, end, orderByComparator);

			return com.liferay.knowledgebase.model.KBArticleSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBArticleSoap[] getKBArticles(
		long groupId, long[] resourcePrimKeys, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.knowledgebase.model.KBArticle> returnValue =
				KBArticleServiceUtil.getKBArticles(groupId, resourcePrimKeys,
					status, start, end, orderByComparator);

			return com.liferay.knowledgebase.model.KBArticleSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBArticleSoap[] getKBArticles(
		long groupId, long[] resourcePrimKeys, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.knowledgebase.model.KBArticle> returnValue =
				KBArticleServiceUtil.getKBArticles(groupId, resourcePrimKeys,
					status, orderByComparator);

			return com.liferay.knowledgebase.model.KBArticleSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getKBArticlesCount(long groupId,
		long parentResourcePrimKey, int status) throws RemoteException {
		try {
			int returnValue = KBArticleServiceUtil.getKBArticlesCount(groupId,
					parentResourcePrimKey, status);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getKBArticlesCount(long groupId, long[] resourcePrimKeys,
		int status) throws RemoteException {
		try {
			int returnValue = KBArticleServiceUtil.getKBArticlesCount(groupId,
					resourcePrimKeys, status);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBArticleSearchDisplay getKBArticleSearchDisplay(
		long groupId, java.lang.String title, java.lang.String content,
		int status, java.util.Date startDate, java.util.Date endDate,
		boolean andOperator, int[] curStartValues, int cur, int delta,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBArticleSearchDisplay returnValue = KBArticleServiceUtil.getKBArticleSearchDisplay(groupId,
					title, content, status, startDate, endDate, andOperator,
					curStartValues, cur, delta, orderByComparator);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBArticleSoap[] getKBArticleVersions(
		long groupId, long resourcePrimKey, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.knowledgebase.model.KBArticle> returnValue =
				KBArticleServiceUtil.getKBArticleVersions(groupId,
					resourcePrimKey, status, start, end, orderByComparator);

			return com.liferay.knowledgebase.model.KBArticleSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getKBArticleVersionsCount(long groupId,
		long resourcePrimKey, int status) throws RemoteException {
		try {
			int returnValue = KBArticleServiceUtil.getKBArticleVersionsCount(groupId,
					resourcePrimKey, status);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBArticleSoap getLatestKBArticle(
		long resourcePrimKey, int status) throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBArticle returnValue = KBArticleServiceUtil.getLatestKBArticle(resourcePrimKey,
					status);

			return com.liferay.knowledgebase.model.KBArticleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBArticleSoap[] getSectionsKBArticles(
		long groupId, java.lang.String[] sections, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.knowledgebase.model.KBArticle> returnValue =
				KBArticleServiceUtil.getSectionsKBArticles(groupId, sections,
					status, start, end, orderByComparator);

			return com.liferay.knowledgebase.model.KBArticleSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getSectionsKBArticlesCount(long groupId,
		java.lang.String[] sections, int status) throws RemoteException {
		try {
			int returnValue = KBArticleServiceUtil.getSectionsKBArticlesCount(groupId,
					sections, status);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getKBArticles(long, long,
	int, int, int,
	com.liferay.portal.kernel.util.OrderByComparator)}
	*/
	public static com.liferay.knowledgebase.model.KBArticleSoap[] getSiblingKBArticles(
		long groupId, long parentResourcePrimKey, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.knowledgebase.model.KBArticle> returnValue =
				KBArticleServiceUtil.getSiblingKBArticles(groupId,
					parentResourcePrimKey, status, start, end, orderByComparator);

			return com.liferay.knowledgebase.model.KBArticleSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getKBArticlesCount(long,
	long, int)}
	*/
	public static int getSiblingKBArticlesCount(long groupId,
		long parentResourcePrimKey, int status) throws RemoteException {
		try {
			int returnValue = KBArticleServiceUtil.getSiblingKBArticlesCount(groupId,
					parentResourcePrimKey, status);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String[] getTempAttachmentNames(long groupId,
		java.lang.String tempFolderName) throws RemoteException {
		try {
			java.lang.String[] returnValue = KBArticleServiceUtil.getTempAttachmentNames(groupId,
					tempFolderName);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void moveKBArticle(long resourcePrimKey,
		long parentResourceClassNameId, long parentResourcePrimKey,
		double priority) throws RemoteException {
		try {
			KBArticleServiceUtil.moveKBArticle(resourcePrimKey,
				parentResourceClassNameId, parentResourcePrimKey, priority);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void subscribeGroupKBArticles(long groupId,
		java.lang.String portletId) throws RemoteException {
		try {
			KBArticleServiceUtil.subscribeGroupKBArticles(groupId, portletId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void subscribeKBArticle(long groupId, long resourcePrimKey)
		throws RemoteException {
		try {
			KBArticleServiceUtil.subscribeKBArticle(groupId, resourcePrimKey);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void unsubscribeGroupKBArticles(long groupId,
		java.lang.String portletId) throws RemoteException {
		try {
			KBArticleServiceUtil.unsubscribeGroupKBArticles(groupId, portletId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void unsubscribeKBArticle(long resourcePrimKey)
		throws RemoteException {
		try {
			KBArticleServiceUtil.unsubscribeKBArticle(resourcePrimKey);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBArticleSoap updateKBArticle(
		long resourcePrimKey, java.lang.String title, java.lang.String content,
		java.lang.String description, java.lang.String sourceURL,
		java.lang.String[] sections, java.lang.String[] selectedFileNames,
		long[] removeFileEntryIds,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBArticle returnValue = KBArticleServiceUtil.updateKBArticle(resourcePrimKey,
					title, content, description, sourceURL, sections,
					selectedFileNames, removeFileEntryIds, serviceContext);

			return com.liferay.knowledgebase.model.KBArticleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(KBArticleServiceSoap.class);
}