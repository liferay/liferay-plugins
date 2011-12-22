/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * @author Brian Wing Shun Chan
 */
public class KBArticleLocalServiceClp implements KBArticleLocalService {
	public KBArticleLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;

		_addKBArticleMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
				"addKBArticle", com.liferay.knowledgebase.model.KBArticle.class);

		_createKBArticleMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
				"createKBArticle", long.class);

		_deleteKBArticleMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteKBArticle", long.class);

		_deleteKBArticleMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteKBArticle",
				com.liferay.knowledgebase.model.KBArticle.class);

		_dynamicQueryMethodKey4 = new MethodKey(_classLoaderProxy.getClassName(),
				"dynamicQuery",
				com.liferay.portal.kernel.dao.orm.DynamicQuery.class);

		_dynamicQueryMethodKey5 = new MethodKey(_classLoaderProxy.getClassName(),
				"dynamicQuery",
				com.liferay.portal.kernel.dao.orm.DynamicQuery.class,
				int.class, int.class);

		_dynamicQueryMethodKey6 = new MethodKey(_classLoaderProxy.getClassName(),
				"dynamicQuery",
				com.liferay.portal.kernel.dao.orm.DynamicQuery.class,
				int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_dynamicQueryCountMethodKey7 = new MethodKey(_classLoaderProxy.getClassName(),
				"dynamicQueryCount",
				com.liferay.portal.kernel.dao.orm.DynamicQuery.class);

		_fetchKBArticleMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
				"fetchKBArticle", long.class);

		_getKBArticleMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKBArticle", long.class);

		_getPersistedModelMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
				"getPersistedModel", java.io.Serializable.class);

		_getKBArticleByUuidAndGroupIdMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKBArticleByUuidAndGroupId", java.lang.String.class,
				long.class);

		_getKBArticlesMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKBArticles", int.class, int.class);

		_getKBArticlesCountMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKBArticlesCount");

		_updateKBArticleMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateKBArticle",
				com.liferay.knowledgebase.model.KBArticle.class);

		_updateKBArticleMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateKBArticle",
				com.liferay.knowledgebase.model.KBArticle.class, boolean.class);

		_getBeanIdentifierMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
				"getBeanIdentifier");

		_setBeanIdentifierMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
				"setBeanIdentifier", java.lang.String.class);

		_addAttachmentMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
				"addAttachment", java.lang.String.class,
				java.lang.String.class, java.io.InputStream.class,
				com.liferay.portal.service.ServiceContext.class);

		_addKBArticleMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
				"addKBArticle", long.class, long.class, java.lang.String.class,
				java.lang.String.class, java.lang.String.class,
				java.lang.String[].class, java.lang.String.class,
				com.liferay.portal.service.ServiceContext.class);

		_checkAttachmentsMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
				"checkAttachments");

		_deleteAttachmentMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteAttachment", long.class, java.lang.String.class);

		_deleteGroupKBArticlesMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteGroupKBArticles", long.class);

		_deleteKBArticlesMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteKBArticles", long[].class);

		_getCompanyKBArticlesMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCompanyKBArticles", long.class, int.class, int.class,
				int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getCompanyKBArticlesCountMethodKey25 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCompanyKBArticlesCount", long.class, int.class);

		_getGroupKBArticlesMethodKey26 = new MethodKey(_classLoaderProxy.getClassName(),
				"getGroupKBArticles", long.class, int.class, int.class,
				int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getGroupKBArticlesCountMethodKey27 = new MethodKey(_classLoaderProxy.getClassName(),
				"getGroupKBArticlesCount", long.class, int.class);

		_getKBArticleMethodKey28 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKBArticle", long.class, int.class);

		_getKBArticleAndAllDescendantsMethodKey29 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKBArticleAndAllDescendants", long.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getKBArticlesMethodKey30 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKBArticles", long[].class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getKBArticleVersionsMethodKey31 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKBArticleVersions", long.class, int.class, int.class,
				int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getKBArticleVersionsCountMethodKey32 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKBArticleVersionsCount", long.class, int.class);

		_getLatestKBArticleMethodKey33 = new MethodKey(_classLoaderProxy.getClassName(),
				"getLatestKBArticle", long.class, int.class);

		_getSectionsKBArticlesMethodKey34 = new MethodKey(_classLoaderProxy.getClassName(),
				"getSectionsKBArticles", long.class, java.lang.String[].class,
				int.class, int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getSectionsKBArticlesCountMethodKey35 = new MethodKey(_classLoaderProxy.getClassName(),
				"getSectionsKBArticlesCount", long.class,
				java.lang.String[].class, int.class);

		_getSiblingKBArticlesMethodKey36 = new MethodKey(_classLoaderProxy.getClassName(),
				"getSiblingKBArticles", long.class, long.class, int.class,
				int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getSiblingKBArticlesCountMethodKey37 = new MethodKey(_classLoaderProxy.getClassName(),
				"getSiblingKBArticlesCount", long.class, long.class, int.class);

		_moveKBArticleMethodKey38 = new MethodKey(_classLoaderProxy.getClassName(),
				"moveKBArticle", long.class, long.class, long.class,
				double.class);

		_searchMethodKey39 = new MethodKey(_classLoaderProxy.getClassName(),
				"search", long.class, java.lang.String.class,
				java.lang.String.class, int.class, java.util.Date.class,
				java.util.Date.class, boolean.class, int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_subscribeGroupKBArticlesMethodKey40 = new MethodKey(_classLoaderProxy.getClassName(),
				"subscribeGroupKBArticles", long.class, long.class);

		_subscribeKBArticleMethodKey41 = new MethodKey(_classLoaderProxy.getClassName(),
				"subscribeKBArticle", long.class, long.class, long.class);

		_unsubscribeGroupKBArticlesMethodKey42 = new MethodKey(_classLoaderProxy.getClassName(),
				"unsubscribeGroupKBArticles", long.class, long.class);

		_unsubscribeKBArticleMethodKey43 = new MethodKey(_classLoaderProxy.getClassName(),
				"unsubscribeKBArticle", long.class, long.class);

		_updateAttachmentsMethodKey44 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateAttachments", long.class, java.lang.String.class,
				com.liferay.portal.service.ServiceContext.class);

		_updateKBArticleMethodKey45 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateKBArticle", long.class, long.class,
				java.lang.String.class, java.lang.String.class,
				java.lang.String.class, java.lang.String[].class,
				java.lang.String.class,
				com.liferay.portal.service.ServiceContext.class);

		_updateKBArticleAssetMethodKey46 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateKBArticleAsset", long.class,
				com.liferay.knowledgebase.model.KBArticle.class, long[].class,
				java.lang.String[].class);

		_updateKBArticleResourcesMethodKey47 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateKBArticleResources",
				com.liferay.knowledgebase.model.KBArticle.class,
				java.lang.String[].class, java.lang.String[].class);

		_updateKBArticlesPrioritiesMethodKey48 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateKBArticlesPriorities", java.util.Map.class);

		_updateStatusMethodKey49 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateStatus", long.class, long.class, int.class,
				com.liferay.portal.service.ServiceContext.class);

		_updateViewCountMethodKey50 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateViewCount", long.class, long.class, int.class);
	}

	public com.liferay.knowledgebase.model.KBArticle addKBArticle(
		com.liferay.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addKBArticleMethodKey0,
				ClpSerializer.translateInput(kbArticle));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.knowledgebase.model.KBArticle createKBArticle(
		long kbArticleId) {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_createKBArticleMethodKey1,
				kbArticleId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
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

		return (com.liferay.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteKBArticle(long kbArticleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteKBArticleMethodKey2,
				kbArticleId);

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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
		com.liferay.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteKBArticleMethodKey3,
				ClpSerializer.translateInput(kbArticle));

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey4,
				ClpSerializer.translateInput(dynamicQuery));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey5,
				ClpSerializer.translateInput(dynamicQuery), start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey6,
				ClpSerializer.translateInput(dynamicQuery), start, end,
				ClpSerializer.translateInput(orderByComparator));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryCountMethodKey7,
				ClpSerializer.translateInput(dynamicQuery));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Long)returnObj).longValue();
	}

	public com.liferay.knowledgebase.model.KBArticle fetchKBArticle(
		long kbArticleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_fetchKBArticleMethodKey8,
				kbArticleId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.knowledgebase.model.KBArticle getKBArticle(
		long kbArticleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKBArticleMethodKey9,
				kbArticleId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getPersistedModelMethodKey10,
				ClpSerializer.translateInput(primaryKeyObj));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.portal.model.PersistedModel)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.knowledgebase.model.KBArticle getKBArticleByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKBArticleByUuidAndGroupIdMethodKey11,
				ClpSerializer.translateInput(uuid), groupId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticles(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKBArticlesMethodKey12,
				start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)ClpSerializer.translateOutput(returnObj);
	}

	public int getKBArticlesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKBArticlesCountMethodKey13);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public com.liferay.knowledgebase.model.KBArticle updateKBArticle(
		com.liferay.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateKBArticleMethodKey14,
				ClpSerializer.translateInput(kbArticle));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.knowledgebase.model.KBArticle updateKBArticle(
		com.liferay.knowledgebase.model.KBArticle kbArticle, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateKBArticleMethodKey15,
				ClpSerializer.translateInput(kbArticle), merge);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String getBeanIdentifier() {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getBeanIdentifierMethodKey16);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
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

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		MethodHandler methodHandler = new MethodHandler(_setBeanIdentifierMethodKey17,
				ClpSerializer.translateInput(beanIdentifier));

		try {
			_classLoaderProxy.invoke(methodHandler);
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
	}

	public void addAttachment(java.lang.String dirName,
		java.lang.String shortFileName, java.io.InputStream inputStream,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_addAttachmentMethodKey18,
				ClpSerializer.translateInput(dirName),
				ClpSerializer.translateInput(shortFileName),
				ClpSerializer.translateInput(inputStream),
				ClpSerializer.translateInput(serviceContext));

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public com.liferay.knowledgebase.model.KBArticle addKBArticle(long userId,
		long parentResourcePrimKey, java.lang.String title,
		java.lang.String content, java.lang.String description,
		java.lang.String[] sections, java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addKBArticleMethodKey19,
				userId, parentResourcePrimKey,
				ClpSerializer.translateInput(title),
				ClpSerializer.translateInput(content),
				ClpSerializer.translateInput(description),
				ClpSerializer.translateInput(sections),
				ClpSerializer.translateInput(dirName),
				ClpSerializer.translateInput(serviceContext));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public void checkAttachments()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_checkAttachmentsMethodKey20);

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public void deleteAttachment(long companyId, java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteAttachmentMethodKey21,
				companyId, ClpSerializer.translateInput(fileName));

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public void deleteGroupKBArticles(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteGroupKBArticlesMethodKey22,
				groupId);

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public void deleteKBArticles(long[] resourcePrimKeys)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteKBArticlesMethodKey23,
				ClpSerializer.translateInput(resourcePrimKeys));

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getCompanyKBArticles(
		long companyId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCompanyKBArticlesMethodKey24,
				companyId, status, start, end,
				ClpSerializer.translateInput(orderByComparator));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)ClpSerializer.translateOutput(returnObj);
	}

	public int getCompanyKBArticlesCount(long companyId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCompanyKBArticlesCountMethodKey25,
				companyId, status);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getGroupKBArticles(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getGroupKBArticlesMethodKey26,
				groupId, status, start, end,
				ClpSerializer.translateInput(orderByComparator));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)ClpSerializer.translateOutput(returnObj);
	}

	public int getGroupKBArticlesCount(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getGroupKBArticlesCountMethodKey27,
				groupId, status);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public com.liferay.knowledgebase.model.KBArticle getKBArticle(
		long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKBArticleMethodKey28,
				resourcePrimKey, version);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticleAndAllDescendants(
		long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKBArticleAndAllDescendantsMethodKey29,
				resourcePrimKey, status,
				ClpSerializer.translateInput(orderByComparator));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticles(
		long[] resourcePrimKeys, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKBArticlesMethodKey30,
				ClpSerializer.translateInput(resourcePrimKeys), status,
				ClpSerializer.translateInput(orderByComparator));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticleVersions(
		long resourcePrimKey, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKBArticleVersionsMethodKey31,
				resourcePrimKey, status, start, end,
				ClpSerializer.translateInput(orderByComparator));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)ClpSerializer.translateOutput(returnObj);
	}

	public int getKBArticleVersionsCount(long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKBArticleVersionsCountMethodKey32,
				resourcePrimKey, status);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public com.liferay.knowledgebase.model.KBArticle getLatestKBArticle(
		long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getLatestKBArticleMethodKey33,
				resourcePrimKey, status);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getSectionsKBArticles(
		long groupId, java.lang.String[] sections, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getSectionsKBArticlesMethodKey34,
				groupId, ClpSerializer.translateInput(sections), status, start,
				end, ClpSerializer.translateInput(orderByComparator));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)ClpSerializer.translateOutput(returnObj);
	}

	public int getSectionsKBArticlesCount(long groupId,
		java.lang.String[] sections, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getSectionsKBArticlesCountMethodKey35,
				groupId, ClpSerializer.translateInput(sections), status);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getSiblingKBArticles(
		long groupId, long parentResourcePrimKey, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getSiblingKBArticlesMethodKey36,
				groupId, parentResourcePrimKey, status, start, end,
				ClpSerializer.translateInput(orderByComparator));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)ClpSerializer.translateOutput(returnObj);
	}

	public int getSiblingKBArticlesCount(long groupId,
		long parentResourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getSiblingKBArticlesCountMethodKey37,
				groupId, parentResourcePrimKey, status);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public void moveKBArticle(long userId, long resourcePrimKey,
		long parentResourcePrimKey, double priority)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_moveKBArticleMethodKey38,
				userId, resourcePrimKey, parentResourcePrimKey, priority);

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> search(
		long groupId, java.lang.String title, java.lang.String content,
		int status, java.util.Date startDate, java.util.Date endDate,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_searchMethodKey39,
				groupId, ClpSerializer.translateInput(title),
				ClpSerializer.translateInput(content), status,
				ClpSerializer.translateInput(startDate),
				ClpSerializer.translateInput(endDate), andOperator, start, end,
				ClpSerializer.translateInput(orderByComparator));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)ClpSerializer.translateOutput(returnObj);
	}

	public void subscribeGroupKBArticles(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_subscribeGroupKBArticlesMethodKey40,
				userId, groupId);

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public void subscribeKBArticle(long userId, long groupId,
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_subscribeKBArticleMethodKey41,
				userId, groupId, resourcePrimKey);

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public void unsubscribeGroupKBArticles(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_unsubscribeGroupKBArticlesMethodKey42,
				userId, groupId);

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public void unsubscribeKBArticle(long userId, long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_unsubscribeKBArticleMethodKey43,
				userId, resourcePrimKey);

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public java.lang.String updateAttachments(long resourcePrimKey,
		java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateAttachmentsMethodKey44,
				resourcePrimKey, ClpSerializer.translateInput(dirName),
				ClpSerializer.translateInput(serviceContext));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public com.liferay.knowledgebase.model.KBArticle updateKBArticle(
		long userId, long resourcePrimKey, java.lang.String title,
		java.lang.String content, java.lang.String description,
		java.lang.String[] sections, java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateKBArticleMethodKey45,
				userId, resourcePrimKey, ClpSerializer.translateInput(title),
				ClpSerializer.translateInput(content),
				ClpSerializer.translateInput(description),
				ClpSerializer.translateInput(sections),
				ClpSerializer.translateInput(dirName),
				ClpSerializer.translateInput(serviceContext));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public void updateKBArticleAsset(long userId,
		com.liferay.knowledgebase.model.KBArticle kbArticle,
		long[] assetCategoryIds, java.lang.String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_updateKBArticleAssetMethodKey46,
				userId, ClpSerializer.translateInput(kbArticle),
				ClpSerializer.translateInput(assetCategoryIds),
				ClpSerializer.translateInput(assetTagNames));

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public void updateKBArticleResources(
		com.liferay.knowledgebase.model.KBArticle kbArticle,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_updateKBArticleResourcesMethodKey47,
				ClpSerializer.translateInput(kbArticle),
				ClpSerializer.translateInput(groupPermissions),
				ClpSerializer.translateInput(guestPermissions));

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public void updateKBArticlesPriorities(
		java.util.Map<java.lang.Long, java.lang.Double> resourcePrimKeyToPriorityMap)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_updateKBArticlesPrioritiesMethodKey48,
				ClpSerializer.translateInput(resourcePrimKeyToPriorityMap));

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public com.liferay.knowledgebase.model.KBArticle updateStatus(long userId,
		long resourcePrimKey, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateStatusMethodKey49,
				userId, resourcePrimKey, status,
				ClpSerializer.translateInput(serviceContext));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public void updateViewCount(long userId, long resourcePrimKey, int viewCount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_updateViewCountMethodKey50,
				userId, resourcePrimKey, viewCount);

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private ClassLoaderProxy _classLoaderProxy;
	private MethodKey _addKBArticleMethodKey0;
	private MethodKey _createKBArticleMethodKey1;
	private MethodKey _deleteKBArticleMethodKey2;
	private MethodKey _deleteKBArticleMethodKey3;
	private MethodKey _dynamicQueryMethodKey4;
	private MethodKey _dynamicQueryMethodKey5;
	private MethodKey _dynamicQueryMethodKey6;
	private MethodKey _dynamicQueryCountMethodKey7;
	private MethodKey _fetchKBArticleMethodKey8;
	private MethodKey _getKBArticleMethodKey9;
	private MethodKey _getPersistedModelMethodKey10;
	private MethodKey _getKBArticleByUuidAndGroupIdMethodKey11;
	private MethodKey _getKBArticlesMethodKey12;
	private MethodKey _getKBArticlesCountMethodKey13;
	private MethodKey _updateKBArticleMethodKey14;
	private MethodKey _updateKBArticleMethodKey15;
	private MethodKey _getBeanIdentifierMethodKey16;
	private MethodKey _setBeanIdentifierMethodKey17;
	private MethodKey _addAttachmentMethodKey18;
	private MethodKey _addKBArticleMethodKey19;
	private MethodKey _checkAttachmentsMethodKey20;
	private MethodKey _deleteAttachmentMethodKey21;
	private MethodKey _deleteGroupKBArticlesMethodKey22;
	private MethodKey _deleteKBArticlesMethodKey23;
	private MethodKey _getCompanyKBArticlesMethodKey24;
	private MethodKey _getCompanyKBArticlesCountMethodKey25;
	private MethodKey _getGroupKBArticlesMethodKey26;
	private MethodKey _getGroupKBArticlesCountMethodKey27;
	private MethodKey _getKBArticleMethodKey28;
	private MethodKey _getKBArticleAndAllDescendantsMethodKey29;
	private MethodKey _getKBArticlesMethodKey30;
	private MethodKey _getKBArticleVersionsMethodKey31;
	private MethodKey _getKBArticleVersionsCountMethodKey32;
	private MethodKey _getLatestKBArticleMethodKey33;
	private MethodKey _getSectionsKBArticlesMethodKey34;
	private MethodKey _getSectionsKBArticlesCountMethodKey35;
	private MethodKey _getSiblingKBArticlesMethodKey36;
	private MethodKey _getSiblingKBArticlesCountMethodKey37;
	private MethodKey _moveKBArticleMethodKey38;
	private MethodKey _searchMethodKey39;
	private MethodKey _subscribeGroupKBArticlesMethodKey40;
	private MethodKey _subscribeKBArticleMethodKey41;
	private MethodKey _unsubscribeGroupKBArticlesMethodKey42;
	private MethodKey _unsubscribeKBArticleMethodKey43;
	private MethodKey _updateAttachmentsMethodKey44;
	private MethodKey _updateKBArticleMethodKey45;
	private MethodKey _updateKBArticleAssetMethodKey46;
	private MethodKey _updateKBArticleResourcesMethodKey47;
	private MethodKey _updateKBArticlesPrioritiesMethodKey48;
	private MethodKey _updateStatusMethodKey49;
	private MethodKey _updateViewCountMethodKey50;
}