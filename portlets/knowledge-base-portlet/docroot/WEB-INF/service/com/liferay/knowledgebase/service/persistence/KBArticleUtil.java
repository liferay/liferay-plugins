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

package com.liferay.knowledgebase.service.persistence;

import com.liferay.knowledgebase.model.KBArticle;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the k b article service. This utility wraps {@link KBArticlePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBArticlePersistence
 * @see KBArticlePersistenceImpl
 * @generated
 */
public class KBArticleUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(KBArticle kbArticle) {
		getPersistence().clearCache(kbArticle);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<KBArticle> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KBArticle> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KBArticle> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static KBArticle update(KBArticle kbArticle)
		throws SystemException {
		return getPersistence().update(kbArticle);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static KBArticle update(KBArticle kbArticle,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(kbArticle, serviceContext);
	}

	/**
	* Returns all the k b articles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the k b articles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where uuid = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByUuid_PrevAndNext(
		long kbArticleId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(kbArticleId, uuid, orderByComparator);
	}

	/**
	* Removes all the k b articles where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of k b articles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the k b article where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.knowledgebase.NoSuchArticleException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the k b article where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the k b article where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the k b article where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the k b article that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of k b articles where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the k b articles where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the k b articles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByUuid_C_PrevAndNext(
		long kbArticleId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(kbArticleId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the k b articles where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of k b articles where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the k b articles where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByResourcePrimKey(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByResourcePrimKey(resourcePrimKey);
	}

	/**
	* Returns a range of all the k b articles where resourcePrimKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByResourcePrimKey(
		long resourcePrimKey, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourcePrimKey(resourcePrimKey, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByResourcePrimKey(
		long resourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourcePrimKey(resourcePrimKey, start, end,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByResourcePrimKey_First(
		long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourcePrimKey_First(resourcePrimKey,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByResourcePrimKey_First(
		long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByResourcePrimKey_First(resourcePrimKey,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByResourcePrimKey_Last(
		long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourcePrimKey_Last(resourcePrimKey,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByResourcePrimKey_Last(
		long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByResourcePrimKey_Last(resourcePrimKey,
			orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByResourcePrimKey_PrevAndNext(
		long kbArticleId, long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourcePrimKey_PrevAndNext(kbArticleId,
			resourcePrimKey, orderByComparator);
	}

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByResourcePrimKey(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByResourcePrimKey(resourcePrimKey);
	}

	/**
	* Returns the number of k b articles where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByResourcePrimKey(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByResourcePrimKey(resourcePrimKey);
	}

	/**
	* Returns all the k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G(
		long resourcePrimKey, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_G(resourcePrimKey, groupId);
	}

	/**
	* Returns a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G(
		long resourcePrimKey, long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_G(resourcePrimKey, groupId, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G(
		long resourcePrimKey, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G(resourcePrimKey, groupId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_G_First(
		long resourcePrimKey, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_First(resourcePrimKey, groupId, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByR_G_First(
		long resourcePrimKey, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_G_First(resourcePrimKey, groupId, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_G_Last(
		long resourcePrimKey, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_Last(resourcePrimKey, groupId, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByR_G_Last(
		long resourcePrimKey, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_G_Last(resourcePrimKey, groupId, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByR_G_PrevAndNext(
		long kbArticleId, long resourcePrimKey, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_PrevAndNext(kbArticleId, resourcePrimKey,
			groupId, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G(
		long resourcePrimKey, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByR_G(resourcePrimKey, groupId);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G(
		long resourcePrimKey, long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G(resourcePrimKey, groupId, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G(
		long resourcePrimKey, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G(resourcePrimKey, groupId, start, end,
			orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByR_G_PrevAndNext(
		long kbArticleId, long resourcePrimKey, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_PrevAndNext(kbArticleId, resourcePrimKey,
			groupId, orderByComparator);
	}

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; and groupId = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_G(long resourcePrimKey, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_G(resourcePrimKey, groupId);
	}

	/**
	* Returns the number of k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_G(long resourcePrimKey, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_G(resourcePrimKey, groupId);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByR_G(long resourcePrimKey, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByR_G(resourcePrimKey, groupId);
	}

	/**
	* Returns the k b article where resourcePrimKey = &#63; and version = &#63; or throws a {@link com.liferay.knowledgebase.NoSuchArticleException} if it could not be found.
	*
	* @param resourcePrimKey the resource prim key
	* @param version the version
	* @return the matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_V(
		long resourcePrimKey, int version)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_V(resourcePrimKey, version);
	}

	/**
	* Returns the k b article where resourcePrimKey = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param resourcePrimKey the resource prim key
	* @param version the version
	* @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByR_V(
		long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByR_V(resourcePrimKey, version);
	}

	/**
	* Returns the k b article where resourcePrimKey = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param resourcePrimKey the resource prim key
	* @param version the version
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByR_V(
		long resourcePrimKey, int version, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_V(resourcePrimKey, version, retrieveFromCache);
	}

	/**
	* Removes the k b article where resourcePrimKey = &#63; and version = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	* @param version the version
	* @return the k b article that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle removeByR_V(
		long resourcePrimKey, int version)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByR_V(resourcePrimKey, version);
	}

	/**
	* Returns the number of k b articles where resourcePrimKey = &#63; and version = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param version the version
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_V(long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_V(resourcePrimKey, version);
	}

	/**
	* Returns all the k b articles where resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param latest the latest
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_L(
		long resourcePrimKey, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_L(resourcePrimKey, latest);
	}

	/**
	* Returns a range of all the k b articles where resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_L(
		long resourcePrimKey, boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_L(resourcePrimKey, latest, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_L(
		long resourcePrimKey, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L(resourcePrimKey, latest, start, end,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_L_First(
		long resourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L_First(resourcePrimKey, latest, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByR_L_First(
		long resourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_L_First(resourcePrimKey, latest, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_L_Last(
		long resourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L_Last(resourcePrimKey, latest, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByR_L_Last(
		long resourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_L_Last(resourcePrimKey, latest, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByR_L_PrevAndNext(
		long kbArticleId, long resourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L_PrevAndNext(kbArticleId, resourcePrimKey, latest,
			orderByComparator);
	}

	/**
	* Returns all the k b articles where resourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param latest the latest
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_L(
		long[] resourcePrimKeies, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_L(resourcePrimKeies, latest);
	}

	/**
	* Returns a range of all the k b articles where resourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_L(
		long[] resourcePrimKeies, boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_L(resourcePrimKeies, latest, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_L(
		long[] resourcePrimKeies, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L(resourcePrimKeies, latest, start, end,
			orderByComparator);
	}

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; and latest = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	* @param latest the latest
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_L(long resourcePrimKey, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_L(resourcePrimKey, latest);
	}

	/**
	* Returns the number of k b articles where resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param latest the latest
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_L(long resourcePrimKey, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_L(resourcePrimKey, latest);
	}

	/**
	* Returns the number of k b articles where resourcePrimKey = any &#63; and latest = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param latest the latest
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_L(long[] resourcePrimKeies, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_L(resourcePrimKeies, latest);
	}

	/**
	* Returns all the k b articles where resourcePrimKey = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param main the main
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_M(
		long resourcePrimKey, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_M(resourcePrimKey, main);
	}

	/**
	* Returns a range of all the k b articles where resourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_M(
		long resourcePrimKey, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_M(resourcePrimKey, main, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_M(
		long resourcePrimKey, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_M(resourcePrimKey, main, start, end,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_M_First(
		long resourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_M_First(resourcePrimKey, main, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByR_M_First(
		long resourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_M_First(resourcePrimKey, main, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_M_Last(
		long resourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_M_Last(resourcePrimKey, main, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByR_M_Last(
		long resourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_M_Last(resourcePrimKey, main, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByR_M_PrevAndNext(
		long kbArticleId, long resourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_M_PrevAndNext(kbArticleId, resourcePrimKey, main,
			orderByComparator);
	}

	/**
	* Returns all the k b articles where resourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param main the main
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_M(
		long[] resourcePrimKeies, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_M(resourcePrimKeies, main);
	}

	/**
	* Returns a range of all the k b articles where resourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_M(
		long[] resourcePrimKeies, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_M(resourcePrimKeies, main, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_M(
		long[] resourcePrimKeies, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_M(resourcePrimKeies, main, start, end,
			orderByComparator);
	}

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; and main = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	* @param main the main
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_M(long resourcePrimKey, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_M(resourcePrimKey, main);
	}

	/**
	* Returns the number of k b articles where resourcePrimKey = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param main the main
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_M(long resourcePrimKey, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_M(resourcePrimKey, main);
	}

	/**
	* Returns the number of k b articles where resourcePrimKey = any &#63; and main = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param main the main
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_M(long[] resourcePrimKeies, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_M(resourcePrimKeies, main);
	}

	/**
	* Returns all the k b articles where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_S(
		long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_S(resourcePrimKey, status);
	}

	/**
	* Returns a range of all the k b articles where resourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_S(
		long resourcePrimKey, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_S(resourcePrimKey, status, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_S(
		long resourcePrimKey, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_S(resourcePrimKey, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_S_First(
		long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_S_First(resourcePrimKey, status, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByR_S_First(
		long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_S_First(resourcePrimKey, status, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_S_Last(
		long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_S_Last(resourcePrimKey, status, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByR_S_Last(
		long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_S_Last(resourcePrimKey, status, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByR_S_PrevAndNext(
		long kbArticleId, long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_S_PrevAndNext(kbArticleId, resourcePrimKey, status,
			orderByComparator);
	}

	/**
	* Returns all the k b articles where resourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param status the status
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_S(
		long[] resourcePrimKeies, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_S(resourcePrimKeies, status);
	}

	/**
	* Returns a range of all the k b articles where resourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_S(
		long[] resourcePrimKeies, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_S(resourcePrimKeies, status, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_S(
		long[] resourcePrimKeies, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_S(resourcePrimKeies, status, start, end,
			orderByComparator);
	}

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; and status = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_S(long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_S(resourcePrimKey, status);
	}

	/**
	* Returns the number of k b articles where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_S(long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_S(resourcePrimKey, status);
	}

	/**
	* Returns the number of k b articles where resourcePrimKey = any &#63; and status = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param status the status
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_S(long[] resourcePrimKeies, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_S(resourcePrimKeies, status);
	}

	/**
	* Returns all the k b articles where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param latest the latest
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_L(
		long groupId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_L(groupId, latest);
	}

	/**
	* Returns a range of all the k b articles where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_L(
		long groupId, boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_L(groupId, latest, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_L(
		long groupId, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_L(groupId, latest, start, end, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_L_First(
		long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_L_First(groupId, latest, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_L_First(
		long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_L_First(groupId, latest, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_L_Last(
		long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_L_Last(groupId, latest, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_L_Last(
		long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_L_Last(groupId, latest, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and latest = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByG_L_PrevAndNext(
		long kbArticleId, long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_L_PrevAndNext(kbArticleId, groupId, latest,
			orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param latest the latest
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_L(
		long groupId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_L(groupId, latest);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_L(
		long groupId, boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_L(groupId, latest, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_L(
		long groupId, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_L(groupId, latest, start, end,
			orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and latest = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByG_L_PrevAndNext(
		long kbArticleId, long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_L_PrevAndNext(kbArticleId, groupId, latest,
			orderByComparator);
	}

	/**
	* Removes all the k b articles where groupId = &#63; and latest = &#63; from the database.
	*
	* @param groupId the group ID
	* @param latest the latest
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_L(long groupId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_L(groupId, latest);
	}

	/**
	* Returns the number of k b articles where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param latest the latest
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_L(long groupId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_L(groupId, latest);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param latest the latest
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_L(long groupId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_L(groupId, latest);
	}

	/**
	* Returns all the k b articles where groupId = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param main the main
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_M(
		long groupId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_M(groupId, main);
	}

	/**
	* Returns a range of all the k b articles where groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_M(
		long groupId, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_M(groupId, main, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_M(
		long groupId, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_M(groupId, main, start, end, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_M_First(
		long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_M_First(groupId, main, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_M_First(
		long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_M_First(groupId, main, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_M_Last(
		long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_M_Last(groupId, main, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_M_Last(
		long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByG_M_Last(groupId, main, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and main = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByG_M_PrevAndNext(
		long kbArticleId, long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_M_PrevAndNext(kbArticleId, groupId, main,
			orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param main the main
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_M(
		long groupId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_M(groupId, main);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_M(
		long groupId, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_M(groupId, main, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_M(
		long groupId, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_M(groupId, main, start, end, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and main = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByG_M_PrevAndNext(
		long kbArticleId, long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_M_PrevAndNext(kbArticleId, groupId, main,
			orderByComparator);
	}

	/**
	* Removes all the k b articles where groupId = &#63; and main = &#63; from the database.
	*
	* @param groupId the group ID
	* @param main the main
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_M(long groupId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_M(groupId, main);
	}

	/**
	* Returns the number of k b articles where groupId = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param main the main
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_M(long groupId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_M(groupId, main);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param main the main
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_M(long groupId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_M(groupId, main);
	}

	/**
	* Returns all the k b articles where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_S(
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_S(groupId, status);
	}

	/**
	* Returns a range of all the k b articles where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_S(
		long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_S(groupId, status, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_S(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_S(groupId, status, start, end, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_S_First(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_S_First(groupId, status, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_S_First(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_S_First(groupId, status, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_S_Last(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_S_Last(groupId, status, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_S_Last(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_S_Last(groupId, status, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByG_S_PrevAndNext(
		long kbArticleId, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_S_PrevAndNext(kbArticleId, groupId, status,
			orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_S(
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_S(groupId, status);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_S(
		long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_S(groupId, status, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_S(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_S(groupId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByG_S_PrevAndNext(
		long kbArticleId, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_S_PrevAndNext(kbArticleId, groupId, status,
			orderByComparator);
	}

	/**
	* Removes all the k b articles where groupId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_S(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_S(groupId, status);
	}

	/**
	* Returns the number of k b articles where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_S(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_S(groupId, status);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_S(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_S(groupId, status);
	}

	/**
	* Returns all the k b articles where companyId = &#63; and latest = &#63;.
	*
	* @param companyId the company ID
	* @param latest the latest
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByC_L(
		long companyId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_L(companyId, latest);
	}

	/**
	* Returns a range of all the k b articles where companyId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByC_L(
		long companyId, boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_L(companyId, latest, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where companyId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByC_L(
		long companyId, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_L(companyId, latest, start, end, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where companyId = &#63; and latest = &#63;.
	*
	* @param companyId the company ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByC_L_First(
		long companyId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_L_First(companyId, latest, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where companyId = &#63; and latest = &#63;.
	*
	* @param companyId the company ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByC_L_First(
		long companyId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_L_First(companyId, latest, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where companyId = &#63; and latest = &#63;.
	*
	* @param companyId the company ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByC_L_Last(
		long companyId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_L_Last(companyId, latest, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where companyId = &#63; and latest = &#63;.
	*
	* @param companyId the company ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByC_L_Last(
		long companyId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_L_Last(companyId, latest, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where companyId = &#63; and latest = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param companyId the company ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByC_L_PrevAndNext(
		long kbArticleId, long companyId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_L_PrevAndNext(kbArticleId, companyId, latest,
			orderByComparator);
	}

	/**
	* Removes all the k b articles where companyId = &#63; and latest = &#63; from the database.
	*
	* @param companyId the company ID
	* @param latest the latest
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_L(long companyId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_L(companyId, latest);
	}

	/**
	* Returns the number of k b articles where companyId = &#63; and latest = &#63;.
	*
	* @param companyId the company ID
	* @param latest the latest
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_L(long companyId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_L(companyId, latest);
	}

	/**
	* Returns all the k b articles where companyId = &#63; and main = &#63;.
	*
	* @param companyId the company ID
	* @param main the main
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByC_M(
		long companyId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_M(companyId, main);
	}

	/**
	* Returns a range of all the k b articles where companyId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByC_M(
		long companyId, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_M(companyId, main, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where companyId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByC_M(
		long companyId, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_M(companyId, main, start, end, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where companyId = &#63; and main = &#63;.
	*
	* @param companyId the company ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByC_M_First(
		long companyId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_M_First(companyId, main, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where companyId = &#63; and main = &#63;.
	*
	* @param companyId the company ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByC_M_First(
		long companyId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_M_First(companyId, main, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where companyId = &#63; and main = &#63;.
	*
	* @param companyId the company ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByC_M_Last(
		long companyId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_M_Last(companyId, main, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where companyId = &#63; and main = &#63;.
	*
	* @param companyId the company ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByC_M_Last(
		long companyId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_M_Last(companyId, main, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where companyId = &#63; and main = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param companyId the company ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByC_M_PrevAndNext(
		long kbArticleId, long companyId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_M_PrevAndNext(kbArticleId, companyId, main,
			orderByComparator);
	}

	/**
	* Removes all the k b articles where companyId = &#63; and main = &#63; from the database.
	*
	* @param companyId the company ID
	* @param main the main
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_M(long companyId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_M(companyId, main);
	}

	/**
	* Returns the number of k b articles where companyId = &#63; and main = &#63;.
	*
	* @param companyId the company ID
	* @param main the main
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_M(long companyId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_M(companyId, main);
	}

	/**
	* Returns all the k b articles where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByC_S(
		long companyId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_S(companyId, status);
	}

	/**
	* Returns a range of all the k b articles where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByC_S(
		long companyId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_S(companyId, status, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByC_S(
		long companyId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_S(companyId, status, start, end, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByC_S_First(
		long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_S_First(companyId, status, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByC_S_First(
		long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_S_First(companyId, status, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByC_S_Last(
		long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_S_Last(companyId, status, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByC_S_Last(
		long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_S_Last(companyId, status, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByC_S_PrevAndNext(
		long kbArticleId, long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_S_PrevAndNext(kbArticleId, companyId, status,
			orderByComparator);
	}

	/**
	* Removes all the k b articles where companyId = &#63; and status = &#63; from the database.
	*
	* @param companyId the company ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_S(long companyId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_S(companyId, status);
	}

	/**
	* Returns the number of k b articles where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_S(long companyId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_S(companyId, status);
	}

	/**
	* Returns all the k b articles where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_L(
		long parentResourcePrimKey, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_L(parentResourcePrimKey, latest);
	}

	/**
	* Returns a range of all the k b articles where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_L(
		long parentResourcePrimKey, boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L(parentResourcePrimKey, latest, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_L(
		long parentResourcePrimKey, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L(parentResourcePrimKey, latest, start, end,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByP_L_First(
		long parentResourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L_First(parentResourcePrimKey, latest,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByP_L_First(
		long parentResourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByP_L_First(parentResourcePrimKey, latest,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByP_L_Last(
		long parentResourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L_Last(parentResourcePrimKey, latest,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByP_L_Last(
		long parentResourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByP_L_Last(parentResourcePrimKey, latest,
			orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByP_L_PrevAndNext(
		long kbArticleId, long parentResourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L_PrevAndNext(kbArticleId, parentResourcePrimKey,
			latest, orderByComparator);
	}

	/**
	* Returns all the k b articles where parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_L(
		long[] parentResourcePrimKeies, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_L(parentResourcePrimKeies, latest);
	}

	/**
	* Returns a range of all the k b articles where parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_L(
		long[] parentResourcePrimKeies, boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L(parentResourcePrimKeies, latest, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_L(
		long[] parentResourcePrimKeies, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L(parentResourcePrimKeies, latest, start, end,
			orderByComparator);
	}

	/**
	* Removes all the k b articles where parentResourcePrimKey = &#63; and latest = &#63; from the database.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByP_L(long parentResourcePrimKey, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByP_L(parentResourcePrimKey, latest);
	}

	/**
	* Returns the number of k b articles where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_L(long parentResourcePrimKey, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_L(parentResourcePrimKey, latest);
	}

	/**
	* Returns the number of k b articles where parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_L(long[] parentResourcePrimKeies, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_L(parentResourcePrimKeies, latest);
	}

	/**
	* Returns all the k b articles where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_M(
		long parentResourcePrimKey, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_M(parentResourcePrimKey, main);
	}

	/**
	* Returns a range of all the k b articles where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_M(
		long parentResourcePrimKey, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_M(parentResourcePrimKey, main, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_M(
		long parentResourcePrimKey, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_M(parentResourcePrimKey, main, start, end,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByP_M_First(
		long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_M_First(parentResourcePrimKey, main,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByP_M_First(
		long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByP_M_First(parentResourcePrimKey, main,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByP_M_Last(
		long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_M_Last(parentResourcePrimKey, main,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByP_M_Last(
		long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByP_M_Last(parentResourcePrimKey, main,
			orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByP_M_PrevAndNext(
		long kbArticleId, long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_M_PrevAndNext(kbArticleId, parentResourcePrimKey,
			main, orderByComparator);
	}

	/**
	* Returns all the k b articles where parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_M(
		long[] parentResourcePrimKeies, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_M(parentResourcePrimKeies, main);
	}

	/**
	* Returns a range of all the k b articles where parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_M(
		long[] parentResourcePrimKeies, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_M(parentResourcePrimKeies, main, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_M(
		long[] parentResourcePrimKeies, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_M(parentResourcePrimKeies, main, start, end,
			orderByComparator);
	}

	/**
	* Removes all the k b articles where parentResourcePrimKey = &#63; and main = &#63; from the database.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByP_M(long parentResourcePrimKey, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByP_M(parentResourcePrimKey, main);
	}

	/**
	* Returns the number of k b articles where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_M(long parentResourcePrimKey, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_M(parentResourcePrimKey, main);
	}

	/**
	* Returns the number of k b articles where parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_M(long[] parentResourcePrimKeies, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_M(parentResourcePrimKeies, main);
	}

	/**
	* Returns all the k b articles where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_S(
		long parentResourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_S(parentResourcePrimKey, status);
	}

	/**
	* Returns a range of all the k b articles where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_S(
		long parentResourcePrimKey, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_S(parentResourcePrimKey, status, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_S(
		long parentResourcePrimKey, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_S(parentResourcePrimKey, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByP_S_First(
		long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_S_First(parentResourcePrimKey, status,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByP_S_First(
		long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByP_S_First(parentResourcePrimKey, status,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByP_S_Last(
		long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_S_Last(parentResourcePrimKey, status,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByP_S_Last(
		long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByP_S_Last(parentResourcePrimKey, status,
			orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByP_S_PrevAndNext(
		long kbArticleId, long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_S_PrevAndNext(kbArticleId, parentResourcePrimKey,
			status, orderByComparator);
	}

	/**
	* Returns all the k b articles where parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_S(
		long[] parentResourcePrimKeies, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_S(parentResourcePrimKeies, status);
	}

	/**
	* Returns a range of all the k b articles where parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_S(
		long[] parentResourcePrimKeies, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_S(parentResourcePrimKeies, status, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_S(
		long[] parentResourcePrimKeies, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_S(parentResourcePrimKeies, status, start, end,
			orderByComparator);
	}

	/**
	* Removes all the k b articles where parentResourcePrimKey = &#63; and status = &#63; from the database.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByP_S(long parentResourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByP_S(parentResourcePrimKey, status);
	}

	/**
	* Returns the number of k b articles where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_S(long parentResourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_S(parentResourcePrimKey, status);
	}

	/**
	* Returns the number of k b articles where parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_S(long[] parentResourcePrimKeies, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_S(parentResourcePrimKeies, status);
	}

	/**
	* Returns all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_L(
		long resourcePrimKey, long groupId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_G_L(resourcePrimKey, groupId, latest);
	}

	/**
	* Returns a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_L(
		long resourcePrimKey, long groupId, boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_L(resourcePrimKey, groupId, latest, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_L(
		long resourcePrimKey, long groupId, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_L(resourcePrimKey, groupId, latest, start, end,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_G_L_First(
		long resourcePrimKey, long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_L_First(resourcePrimKey, groupId, latest,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByR_G_L_First(
		long resourcePrimKey, long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_G_L_First(resourcePrimKey, groupId, latest,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_G_L_Last(
		long resourcePrimKey, long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_L_Last(resourcePrimKey, groupId, latest,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByR_G_L_Last(
		long resourcePrimKey, long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_G_L_Last(resourcePrimKey, groupId, latest,
			orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByR_G_L_PrevAndNext(
		long kbArticleId, long resourcePrimKey, long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_L_PrevAndNext(kbArticleId, resourcePrimKey,
			groupId, latest, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_L(
		long resourcePrimKey, long groupId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_L(resourcePrimKey, groupId, latest);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_L(
		long resourcePrimKey, long groupId, boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_L(resourcePrimKey, groupId, latest, start,
			end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_L(
		long resourcePrimKey, long groupId, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_L(resourcePrimKey, groupId, latest, start,
			end, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByR_G_L_PrevAndNext(
		long kbArticleId, long resourcePrimKey, long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_L_PrevAndNext(kbArticleId, resourcePrimKey,
			groupId, latest, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param latest the latest
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_L(
		long[] resourcePrimKeies, long groupId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_L(resourcePrimKeies, groupId, latest);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_L(
		long[] resourcePrimKeies, long groupId, boolean latest, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_L(resourcePrimKeies, groupId, latest,
			start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_L(
		long[] resourcePrimKeies, long groupId, boolean latest, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_L(resourcePrimKeies, groupId, latest,
			start, end, orderByComparator);
	}

	/**
	* Returns all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param latest the latest
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_L(
		long[] resourcePrimKeies, long groupId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_G_L(resourcePrimKeies, groupId, latest);
	}

	/**
	* Returns a range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_L(
		long[] resourcePrimKeies, long groupId, boolean latest, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_L(resourcePrimKeies, groupId, latest, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_L(
		long[] resourcePrimKeies, long groupId, boolean latest, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_L(resourcePrimKeies, groupId, latest, start, end,
			orderByComparator);
	}

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_G_L(long resourcePrimKey, long groupId,
		boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_G_L(resourcePrimKey, groupId, latest);
	}

	/**
	* Returns the number of k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_G_L(long resourcePrimKey, long groupId,
		boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_G_L(resourcePrimKey, groupId, latest);
	}

	/**
	* Returns the number of k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param latest the latest
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_G_L(long[] resourcePrimKeies, long groupId,
		boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_G_L(resourcePrimKeies, groupId, latest);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByR_G_L(long resourcePrimKey, long groupId,
		boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByR_G_L(resourcePrimKey, groupId, latest);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param latest the latest
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByR_G_L(long[] resourcePrimKeies,
		long groupId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByR_G_L(resourcePrimKeies, groupId, latest);
	}

	/**
	* Returns all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_M(
		long resourcePrimKey, long groupId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_G_M(resourcePrimKey, groupId, main);
	}

	/**
	* Returns a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_M(
		long resourcePrimKey, long groupId, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_M(resourcePrimKey, groupId, main, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_M(
		long resourcePrimKey, long groupId, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_M(resourcePrimKey, groupId, main, start, end,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_G_M_First(
		long resourcePrimKey, long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_M_First(resourcePrimKey, groupId, main,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByR_G_M_First(
		long resourcePrimKey, long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_G_M_First(resourcePrimKey, groupId, main,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_G_M_Last(
		long resourcePrimKey, long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_M_Last(resourcePrimKey, groupId, main,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByR_G_M_Last(
		long resourcePrimKey, long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_G_M_Last(resourcePrimKey, groupId, main,
			orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByR_G_M_PrevAndNext(
		long kbArticleId, long resourcePrimKey, long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_M_PrevAndNext(kbArticleId, resourcePrimKey,
			groupId, main, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_M(
		long resourcePrimKey, long groupId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByR_G_M(resourcePrimKey, groupId, main);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_M(
		long resourcePrimKey, long groupId, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_M(resourcePrimKey, groupId, main, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_M(
		long resourcePrimKey, long groupId, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_M(resourcePrimKey, groupId, main, start,
			end, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByR_G_M_PrevAndNext(
		long kbArticleId, long resourcePrimKey, long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_M_PrevAndNext(kbArticleId, resourcePrimKey,
			groupId, main, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param main the main
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_M(
		long[] resourcePrimKeies, long groupId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_M(resourcePrimKeies, groupId, main);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_M(
		long[] resourcePrimKeies, long groupId, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_M(resourcePrimKeies, groupId, main, start,
			end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_M(
		long[] resourcePrimKeies, long groupId, boolean main, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_M(resourcePrimKeies, groupId, main, start,
			end, orderByComparator);
	}

	/**
	* Returns all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param main the main
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_M(
		long[] resourcePrimKeies, long groupId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_G_M(resourcePrimKeies, groupId, main);
	}

	/**
	* Returns a range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_M(
		long[] resourcePrimKeies, long groupId, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_M(resourcePrimKeies, groupId, main, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_M(
		long[] resourcePrimKeies, long groupId, boolean main, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_M(resourcePrimKeies, groupId, main, start, end,
			orderByComparator);
	}

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_G_M(long resourcePrimKey, long groupId,
		boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_G_M(resourcePrimKey, groupId, main);
	}

	/**
	* Returns the number of k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_G_M(long resourcePrimKey, long groupId,
		boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_G_M(resourcePrimKey, groupId, main);
	}

	/**
	* Returns the number of k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param main the main
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_G_M(long[] resourcePrimKeies, long groupId,
		boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_G_M(resourcePrimKeies, groupId, main);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByR_G_M(long resourcePrimKey, long groupId,
		boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByR_G_M(resourcePrimKey, groupId, main);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param main the main
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByR_G_M(long[] resourcePrimKeies,
		long groupId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByR_G_M(resourcePrimKeies, groupId, main);
	}

	/**
	* Returns all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_S(
		long resourcePrimKey, long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_G_S(resourcePrimKey, groupId, status);
	}

	/**
	* Returns a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_S(
		long resourcePrimKey, long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_S(resourcePrimKey, groupId, status, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_S(
		long resourcePrimKey, long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_S(resourcePrimKey, groupId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_G_S_First(
		long resourcePrimKey, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_S_First(resourcePrimKey, groupId, status,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByR_G_S_First(
		long resourcePrimKey, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_G_S_First(resourcePrimKey, groupId, status,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_G_S_Last(
		long resourcePrimKey, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_S_Last(resourcePrimKey, groupId, status,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByR_G_S_Last(
		long resourcePrimKey, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_G_S_Last(resourcePrimKey, groupId, status,
			orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByR_G_S_PrevAndNext(
		long kbArticleId, long resourcePrimKey, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_S_PrevAndNext(kbArticleId, resourcePrimKey,
			groupId, status, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_S(
		long resourcePrimKey, long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_S(resourcePrimKey, groupId, status);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_S(
		long resourcePrimKey, long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_S(resourcePrimKey, groupId, status, start,
			end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_S(
		long resourcePrimKey, long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_S(resourcePrimKey, groupId, status, start,
			end, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByR_G_S_PrevAndNext(
		long kbArticleId, long resourcePrimKey, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_S_PrevAndNext(kbArticleId, resourcePrimKey,
			groupId, status, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param status the status
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_S(
		long[] resourcePrimKeies, long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_S(resourcePrimKeies, groupId, status);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_S(
		long[] resourcePrimKeies, long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_S(resourcePrimKeies, groupId, status,
			start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_S(
		long[] resourcePrimKeies, long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_S(resourcePrimKeies, groupId, status,
			start, end, orderByComparator);
	}

	/**
	* Returns all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param status the status
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_S(
		long[] resourcePrimKeies, long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_G_S(resourcePrimKeies, groupId, status);
	}

	/**
	* Returns a range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_S(
		long[] resourcePrimKeies, long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_S(resourcePrimKeies, groupId, status, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_S(
		long[] resourcePrimKeies, long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_S(resourcePrimKeies, groupId, status, start, end,
			orderByComparator);
	}

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_G_S(long resourcePrimKey, long groupId,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_G_S(resourcePrimKey, groupId, status);
	}

	/**
	* Returns the number of k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_G_S(long resourcePrimKey, long groupId,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_G_S(resourcePrimKey, groupId, status);
	}

	/**
	* Returns the number of k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_G_S(long[] resourcePrimKeies, long groupId,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_G_S(resourcePrimKeies, groupId, status);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByR_G_S(long resourcePrimKey, long groupId,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByR_G_S(resourcePrimKey, groupId, status);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByR_G_S(long[] resourcePrimKeies,
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByR_G_S(resourcePrimKeies, groupId, status);
	}

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_L(
		long groupId, long parentResourcePrimKey, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L(groupId, parentResourcePrimKey, latest);
	}

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_L(
		long groupId, long parentResourcePrimKey, boolean latest, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L(groupId, parentResourcePrimKey, latest, start,
			end);
	}

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_L(
		long groupId, long parentResourcePrimKey, boolean latest, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L(groupId, parentResourcePrimKey, latest, start,
			end, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_P_L_First(
		long groupId, long parentResourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L_First(groupId, parentResourcePrimKey, latest,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_P_L_First(
		long groupId, long parentResourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_L_First(groupId, parentResourcePrimKey, latest,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_P_L_Last(
		long groupId, long parentResourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L_Last(groupId, parentResourcePrimKey, latest,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_P_L_Last(
		long groupId, long parentResourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_L_Last(groupId, parentResourcePrimKey, latest,
			orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByG_P_L_PrevAndNext(
		long kbArticleId, long groupId, long parentResourcePrimKey,
		boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L_PrevAndNext(kbArticleId, groupId,
			parentResourcePrimKey, latest, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_L(
		long groupId, long parentResourcePrimKey, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L(groupId, parentResourcePrimKey, latest);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_L(
		long groupId, long parentResourcePrimKey, boolean latest, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L(groupId, parentResourcePrimKey, latest,
			start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_L(
		long groupId, long parentResourcePrimKey, boolean latest, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L(groupId, parentResourcePrimKey, latest,
			start, end, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByG_P_L_PrevAndNext(
		long kbArticleId, long groupId, long parentResourcePrimKey,
		boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L_PrevAndNext(kbArticleId, groupId,
			parentResourcePrimKey, latest, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_L(
		long groupId, long[] parentResourcePrimKeies, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L(groupId, parentResourcePrimKeies, latest);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_L(
		long groupId, long[] parentResourcePrimKeies, boolean latest,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L(groupId, parentResourcePrimKeies, latest,
			start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_L(
		long groupId, long[] parentResourcePrimKeies, boolean latest,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L(groupId, parentResourcePrimKeies, latest,
			start, end, orderByComparator);
	}

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_L(
		long groupId, long[] parentResourcePrimKeies, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L(groupId, parentResourcePrimKeies, latest);
	}

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_L(
		long groupId, long[] parentResourcePrimKeies, boolean latest,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L(groupId, parentResourcePrimKeies, latest,
			start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_L(
		long groupId, long[] parentResourcePrimKeies, boolean latest,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L(groupId, parentResourcePrimKeies, latest,
			start, end, orderByComparator);
	}

	/**
	* Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_P_L(long groupId, long parentResourcePrimKey,
		boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_P_L(groupId, parentResourcePrimKey, latest);
	}

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_L(long groupId, long parentResourcePrimKey,
		boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_L(groupId, parentResourcePrimKey, latest);
	}

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_L(groupId, parentResourcePrimKeies, latest);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_L(long groupId,
		long parentResourcePrimKey, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_L(groupId, parentResourcePrimKey, latest);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_L(groupId, parentResourcePrimKeies, latest);
	}

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_M(
		long groupId, long parentResourcePrimKey, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_P_M(groupId, parentResourcePrimKey, main);
	}

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_M(
		long groupId, long parentResourcePrimKey, boolean main, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_M(groupId, parentResourcePrimKey, main, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_M(
		long groupId, long parentResourcePrimKey, boolean main, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_M(groupId, parentResourcePrimKey, main, start,
			end, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_P_M_First(
		long groupId, long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_M_First(groupId, parentResourcePrimKey, main,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_P_M_First(
		long groupId, long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_M_First(groupId, parentResourcePrimKey, main,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_P_M_Last(
		long groupId, long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_M_Last(groupId, parentResourcePrimKey, main,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_P_M_Last(
		long groupId, long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_M_Last(groupId, parentResourcePrimKey, main,
			orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByG_P_M_PrevAndNext(
		long kbArticleId, long groupId, long parentResourcePrimKey,
		boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_M_PrevAndNext(kbArticleId, groupId,
			parentResourcePrimKey, main, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_M(
		long groupId, long parentResourcePrimKey, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_M(groupId, parentResourcePrimKey, main);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_M(
		long groupId, long parentResourcePrimKey, boolean main, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_M(groupId, parentResourcePrimKey, main,
			start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_M(
		long groupId, long parentResourcePrimKey, boolean main, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_M(groupId, parentResourcePrimKey, main,
			start, end, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByG_P_M_PrevAndNext(
		long kbArticleId, long groupId, long parentResourcePrimKey,
		boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_M_PrevAndNext(kbArticleId, groupId,
			parentResourcePrimKey, main, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_M(
		long groupId, long[] parentResourcePrimKeies, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_M(groupId, parentResourcePrimKeies, main);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_M(
		long groupId, long[] parentResourcePrimKeies, boolean main, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_M(groupId, parentResourcePrimKeies, main,
			start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_M(
		long groupId, long[] parentResourcePrimKeies, boolean main, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_M(groupId, parentResourcePrimKeies, main,
			start, end, orderByComparator);
	}

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_M(
		long groupId, long[] parentResourcePrimKeies, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_M(groupId, parentResourcePrimKeies, main);
	}

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_M(
		long groupId, long[] parentResourcePrimKeies, boolean main, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_M(groupId, parentResourcePrimKeies, main, start,
			end);
	}

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_M(
		long groupId, long[] parentResourcePrimKeies, boolean main, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_M(groupId, parentResourcePrimKeies, main, start,
			end, orderByComparator);
	}

	/**
	* Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_P_M(long groupId, long parentResourcePrimKey,
		boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_P_M(groupId, parentResourcePrimKey, main);
	}

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_M(long groupId, long parentResourcePrimKey,
		boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_M(groupId, parentResourcePrimKey, main);
	}

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_M(groupId, parentResourcePrimKeies, main);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_M(long groupId,
		long parentResourcePrimKey, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_M(groupId, parentResourcePrimKey, main);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_M(groupId, parentResourcePrimKeies, main);
	}

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S(
		long groupId, long parentResourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S(groupId, parentResourcePrimKey, status);
	}

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S(
		long groupId, long parentResourcePrimKey, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S(groupId, parentResourcePrimKey, status, start,
			end);
	}

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S(
		long groupId, long parentResourcePrimKey, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S(groupId, parentResourcePrimKey, status, start,
			end, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_P_S_First(
		long groupId, long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_First(groupId, parentResourcePrimKey, status,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_P_S_First(
		long groupId, long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_S_First(groupId, parentResourcePrimKey, status,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_P_S_Last(
		long groupId, long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_Last(groupId, parentResourcePrimKey, status,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_P_S_Last(
		long groupId, long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_S_Last(groupId, parentResourcePrimKey, status,
			orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByG_P_S_PrevAndNext(
		long kbArticleId, long groupId, long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_PrevAndNext(kbArticleId, groupId,
			parentResourcePrimKey, status, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S(
		long groupId, long parentResourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S(groupId, parentResourcePrimKey, status);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S(
		long groupId, long parentResourcePrimKey, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S(groupId, parentResourcePrimKey, status,
			start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S(
		long groupId, long parentResourcePrimKey, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S(groupId, parentResourcePrimKey, status,
			start, end, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByG_P_S_PrevAndNext(
		long kbArticleId, long groupId, long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_PrevAndNext(kbArticleId, groupId,
			parentResourcePrimKey, status, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S(
		long groupId, long[] parentResourcePrimKeies, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S(groupId, parentResourcePrimKeies, status);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S(
		long groupId, long[] parentResourcePrimKeies, int status, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S(groupId, parentResourcePrimKeies, status,
			start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S(
		long groupId, long[] parentResourcePrimKeies, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S(groupId, parentResourcePrimKeies, status,
			start, end, orderByComparator);
	}

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S(
		long groupId, long[] parentResourcePrimKeies, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S(groupId, parentResourcePrimKeies, status);
	}

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S(
		long groupId, long[] parentResourcePrimKeies, int status, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S(groupId, parentResourcePrimKeies, status,
			start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S(
		long groupId, long[] parentResourcePrimKeies, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S(groupId, parentResourcePrimKeies, status,
			start, end, orderByComparator);
	}

	/**
	* Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_P_S(long groupId, long parentResourcePrimKey,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_P_S(groupId, parentResourcePrimKey, status);
	}

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_S(long groupId, long parentResourcePrimKey,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_S(groupId, parentResourcePrimKey, status);
	}

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_S(groupId, parentResourcePrimKeies, status);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_S(long groupId,
		long parentResourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_S(groupId, parentResourcePrimKey, status);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_S(groupId, parentResourcePrimKeies, status);
	}

	/**
	* Returns all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_KBFI_UT(
		long groupId, long kbFolderId, java.lang.String urlTitle)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_KBFI_UT(groupId, kbFolderId, urlTitle);
	}

	/**
	* Returns a range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_KBFI_UT(
		long groupId, long kbFolderId, java.lang.String urlTitle, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_KBFI_UT(groupId, kbFolderId, urlTitle, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_KBFI_UT(
		long groupId, long kbFolderId, java.lang.String urlTitle, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_KBFI_UT(groupId, kbFolderId, urlTitle, start, end,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_KBFI_UT_First(
		long groupId, long kbFolderId, java.lang.String urlTitle,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_KBFI_UT_First(groupId, kbFolderId, urlTitle,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_KBFI_UT_First(
		long groupId, long kbFolderId, java.lang.String urlTitle,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_KBFI_UT_First(groupId, kbFolderId, urlTitle,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_KBFI_UT_Last(
		long groupId, long kbFolderId, java.lang.String urlTitle,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_KBFI_UT_Last(groupId, kbFolderId, urlTitle,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_KBFI_UT_Last(
		long groupId, long kbFolderId, java.lang.String urlTitle,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_KBFI_UT_Last(groupId, kbFolderId, urlTitle,
			orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByG_KBFI_UT_PrevAndNext(
		long kbArticleId, long groupId, long kbFolderId,
		java.lang.String urlTitle,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_KBFI_UT_PrevAndNext(kbArticleId, groupId,
			kbFolderId, urlTitle, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_KBFI_UT(
		long groupId, long kbFolderId, java.lang.String urlTitle)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_KBFI_UT(groupId, kbFolderId, urlTitle);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_KBFI_UT(
		long groupId, long kbFolderId, java.lang.String urlTitle, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_KBFI_UT(groupId, kbFolderId, urlTitle, start,
			end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_KBFI_UT(
		long groupId, long kbFolderId, java.lang.String urlTitle, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_KBFI_UT(groupId, kbFolderId, urlTitle, start,
			end, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByG_KBFI_UT_PrevAndNext(
		long kbArticleId, long groupId, long kbFolderId,
		java.lang.String urlTitle,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_KBFI_UT_PrevAndNext(kbArticleId, groupId,
			kbFolderId, urlTitle, orderByComparator);
	}

	/**
	* Removes all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; from the database.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_KBFI_UT(long groupId, long kbFolderId,
		java.lang.String urlTitle)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_KBFI_UT(groupId, kbFolderId, urlTitle);
	}

	/**
	* Returns the number of k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_KBFI_UT(long groupId, long kbFolderId,
		java.lang.String urlTitle)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_KBFI_UT(groupId, kbFolderId, urlTitle);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_KBFI_UT(long groupId, long kbFolderId,
		java.lang.String urlTitle)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_KBFI_UT(groupId, kbFolderId, urlTitle);
	}

	/**
	* Returns all the k b articles where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_KBFI_S(
		long groupId, long kbFolderId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_KBFI_S(groupId, kbFolderId, status);
	}

	/**
	* Returns a range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_KBFI_S(
		long groupId, long kbFolderId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_KBFI_S(groupId, kbFolderId, status, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_KBFI_S(
		long groupId, long kbFolderId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_KBFI_S(groupId, kbFolderId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_KBFI_S_First(
		long groupId, long kbFolderId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_KBFI_S_First(groupId, kbFolderId, status,
			orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_KBFI_S_First(
		long groupId, long kbFolderId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_KBFI_S_First(groupId, kbFolderId, status,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_KBFI_S_Last(
		long groupId, long kbFolderId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_KBFI_S_Last(groupId, kbFolderId, status,
			orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_KBFI_S_Last(
		long groupId, long kbFolderId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_KBFI_S_Last(groupId, kbFolderId, status,
			orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByG_KBFI_S_PrevAndNext(
		long kbArticleId, long groupId, long kbFolderId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_KBFI_S_PrevAndNext(kbArticleId, groupId,
			kbFolderId, status, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_KBFI_S(
		long groupId, long kbFolderId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_KBFI_S(groupId, kbFolderId, status);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_KBFI_S(
		long groupId, long kbFolderId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_KBFI_S(groupId, kbFolderId, status, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_KBFI_S(
		long groupId, long kbFolderId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_KBFI_S(groupId, kbFolderId, status, start,
			end, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByG_KBFI_S_PrevAndNext(
		long kbArticleId, long groupId, long kbFolderId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_KBFI_S_PrevAndNext(kbArticleId, groupId,
			kbFolderId, status, orderByComparator);
	}

	/**
	* Removes all the k b articles where groupId = &#63; and kbFolderId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_KBFI_S(long groupId, long kbFolderId,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_KBFI_S(groupId, kbFolderId, status);
	}

	/**
	* Returns the number of k b articles where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_KBFI_S(long groupId, long kbFolderId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_KBFI_S(groupId, kbFolderId, status);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_KBFI_S(long groupId, long kbFolderId,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_KBFI_S(groupId, kbFolderId, status);
	}

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S_L(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_L(groupId, parentResourcePrimKey, sections,
			latest);
	}

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S_L(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_L(groupId, parentResourcePrimKey, sections,
			latest, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S_L(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_L(groupId, parentResourcePrimKey, sections,
			latest, start, end, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_P_S_L_First(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_L_First(groupId, parentResourcePrimKey,
			sections, latest, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_P_S_L_First(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_S_L_First(groupId, parentResourcePrimKey,
			sections, latest, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_P_S_L_Last(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_L_Last(groupId, parentResourcePrimKey,
			sections, latest, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_P_S_L_Last(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_S_L_Last(groupId, parentResourcePrimKey,
			sections, latest, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByG_P_S_L_PrevAndNext(
		long kbArticleId, long groupId, long parentResourcePrimKey,
		java.lang.String sections, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_L_PrevAndNext(kbArticleId, groupId,
			parentResourcePrimKey, sections, latest, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S_L(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_L(groupId, parentResourcePrimKey,
			sections, latest);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S_L(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_L(groupId, parentResourcePrimKey,
			sections, latest, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S_L(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_L(groupId, parentResourcePrimKey,
			sections, latest, start, end, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByG_P_S_L_PrevAndNext(
		long kbArticleId, long groupId, long parentResourcePrimKey,
		java.lang.String sections, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_L_PrevAndNext(kbArticleId, groupId,
			parentResourcePrimKey, sections, latest, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param latest the latest
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S_L(
		long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_L(groupId, parentResourcePrimKey,
			sectionses, latest);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S_L(
		long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_L(groupId, parentResourcePrimKey,
			sectionses, latest, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S_L(
		long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_L(groupId, parentResourcePrimKey,
			sectionses, latest, start, end, orderByComparator);
	}

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param latest the latest
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S_L(
		long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_L(groupId, parentResourcePrimKey, sectionses,
			latest);
	}

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S_L(
		long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_L(groupId, parentResourcePrimKey, sectionses,
			latest, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S_L(
		long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_L(groupId, parentResourcePrimKey, sectionses,
			latest, start, end, orderByComparator);
	}

	/**
	* Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_P_S_L(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByG_P_S_L(groupId, parentResourcePrimKey, sections, latest);
	}

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_S_L(long groupId, long parentResourcePrimKey,
		java.lang.String sections, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_S_L(groupId, parentResourcePrimKey, sections,
			latest);
	}

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param latest the latest
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_S_L(long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_S_L(groupId, parentResourcePrimKey, sectionses,
			latest);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_S_L(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_S_L(groupId, parentResourcePrimKey,
			sections, latest);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param latest the latest
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_S_L(long groupId,
		long parentResourcePrimKey, java.lang.String[] sectionses,
		boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_S_L(groupId, parentResourcePrimKey,
			sectionses, latest);
	}

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S_M(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_M(groupId, parentResourcePrimKey, sections, main);
	}

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S_M(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_M(groupId, parentResourcePrimKey, sections,
			main, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S_M(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_M(groupId, parentResourcePrimKey, sections,
			main, start, end, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_P_S_M_First(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_M_First(groupId, parentResourcePrimKey,
			sections, main, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_P_S_M_First(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_S_M_First(groupId, parentResourcePrimKey,
			sections, main, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_P_S_M_Last(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_M_Last(groupId, parentResourcePrimKey,
			sections, main, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_P_S_M_Last(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_S_M_Last(groupId, parentResourcePrimKey,
			sections, main, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByG_P_S_M_PrevAndNext(
		long kbArticleId, long groupId, long parentResourcePrimKey,
		java.lang.String sections, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_M_PrevAndNext(kbArticleId, groupId,
			parentResourcePrimKey, sections, main, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S_M(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_M(groupId, parentResourcePrimKey,
			sections, main);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S_M(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_M(groupId, parentResourcePrimKey,
			sections, main, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S_M(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_M(groupId, parentResourcePrimKey,
			sections, main, start, end, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByG_P_S_M_PrevAndNext(
		long kbArticleId, long groupId, long parentResourcePrimKey,
		java.lang.String sections, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_M_PrevAndNext(kbArticleId, groupId,
			parentResourcePrimKey, sections, main, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param main the main
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S_M(
		long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_M(groupId, parentResourcePrimKey,
			sectionses, main);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S_M(
		long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_M(groupId, parentResourcePrimKey,
			sectionses, main, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S_M(
		long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_M(groupId, parentResourcePrimKey,
			sectionses, main, start, end, orderByComparator);
	}

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param main the main
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S_M(
		long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_M(groupId, parentResourcePrimKey, sectionses,
			main);
	}

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S_M(
		long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_M(groupId, parentResourcePrimKey, sectionses,
			main, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S_M(
		long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_M(groupId, parentResourcePrimKey, sectionses,
			main, start, end, orderByComparator);
	}

	/**
	* Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_P_S_M(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByG_P_S_M(groupId, parentResourcePrimKey, sections, main);
	}

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_S_M(long groupId, long parentResourcePrimKey,
		java.lang.String sections, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_S_M(groupId, parentResourcePrimKey, sections,
			main);
	}

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param main the main
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_S_M(long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_S_M(groupId, parentResourcePrimKey, sectionses,
			main);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_S_M(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_S_M(groupId, parentResourcePrimKey,
			sections, main);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param main the main
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_S_M(long groupId,
		long parentResourcePrimKey, java.lang.String[] sectionses, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_S_M(groupId, parentResourcePrimKey,
			sectionses, main);
	}

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S_S(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_S(groupId, parentResourcePrimKey, sections,
			status);
	}

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S_S(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_S(groupId, parentResourcePrimKey, sections,
			status, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S_S(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_S(groupId, parentResourcePrimKey, sections,
			status, start, end, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_P_S_S_First(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_S_First(groupId, parentResourcePrimKey,
			sections, status, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_P_S_S_First(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_S_S_First(groupId, parentResourcePrimKey,
			sections, status, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_P_S_S_Last(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_S_Last(groupId, parentResourcePrimKey,
			sections, status, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_P_S_S_Last(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_P_S_S_Last(groupId, parentResourcePrimKey,
			sections, status, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByG_P_S_S_PrevAndNext(
		long kbArticleId, long groupId, long parentResourcePrimKey,
		java.lang.String sections, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_S_PrevAndNext(kbArticleId, groupId,
			parentResourcePrimKey, sections, status, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S_S(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_S(groupId, parentResourcePrimKey,
			sections, status);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S_S(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_S(groupId, parentResourcePrimKey,
			sections, status, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S_S(
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_S(groupId, parentResourcePrimKey,
			sections, status, start, end, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByG_P_S_S_PrevAndNext(
		long kbArticleId, long groupId, long parentResourcePrimKey,
		java.lang.String sections, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_S_PrevAndNext(kbArticleId, groupId,
			parentResourcePrimKey, sections, status, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param status the status
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S_S(
		long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_S(groupId, parentResourcePrimKey,
			sectionses, status);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S_S(
		long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_S(groupId, parentResourcePrimKey,
			sectionses, status, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S_S(
		long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_S(groupId, parentResourcePrimKey,
			sectionses, status, start, end, orderByComparator);
	}

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param status the status
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S_S(
		long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_S(groupId, parentResourcePrimKey, sectionses,
			status);
	}

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S_S(
		long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_S(groupId, parentResourcePrimKey, sectionses,
			status, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S_S(
		long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_S(groupId, parentResourcePrimKey, sectionses,
			status, start, end, orderByComparator);
	}

	/**
	* Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_P_S_S(long groupId,
		long parentResourcePrimKey, java.lang.String sections, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByG_P_S_S(groupId, parentResourcePrimKey, sections, status);
	}

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_S_S(long groupId, long parentResourcePrimKey,
		java.lang.String sections, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_S_S(groupId, parentResourcePrimKey, sections,
			status);
	}

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param status the status
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_S_S(long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_S_S(groupId, parentResourcePrimKey, sectionses,
			status);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_S_S(long groupId,
		long parentResourcePrimKey, java.lang.String sections, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_S_S(groupId, parentResourcePrimKey,
			sections, status);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param status the status
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_S_S(long groupId,
		long parentResourcePrimKey, java.lang.String[] sectionses, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_S_S(groupId, parentResourcePrimKey,
			sectionses, status);
	}

	/**
	* Returns all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_KBFI_UT_ST(
		long groupId, long kbFolderId, java.lang.String urlTitle, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle, status);
	}

	/**
	* Returns a range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_KBFI_UT_ST(
		long groupId, long kbFolderId, java.lang.String urlTitle, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle, status,
			start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_KBFI_UT_ST(
		long groupId, long kbFolderId, java.lang.String urlTitle, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle, status,
			start, end, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_KBFI_UT_ST_First(
		long groupId, long kbFolderId, java.lang.String urlTitle, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_KBFI_UT_ST_First(groupId, kbFolderId, urlTitle,
			status, orderByComparator);
	}

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_KBFI_UT_ST_First(
		long groupId, long kbFolderId, java.lang.String urlTitle, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_KBFI_UT_ST_First(groupId, kbFolderId, urlTitle,
			status, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_KBFI_UT_ST_Last(
		long groupId, long kbFolderId, java.lang.String urlTitle, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_KBFI_UT_ST_Last(groupId, kbFolderId, urlTitle,
			status, orderByComparator);
	}

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByG_KBFI_UT_ST_Last(
		long groupId, long kbFolderId, java.lang.String urlTitle, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_KBFI_UT_ST_Last(groupId, kbFolderId, urlTitle,
			status, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByG_KBFI_UT_ST_PrevAndNext(
		long kbArticleId, long groupId, long kbFolderId,
		java.lang.String urlTitle, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_KBFI_UT_ST_PrevAndNext(kbArticleId, groupId,
			kbFolderId, urlTitle, status, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_KBFI_UT_ST(
		long groupId, long kbFolderId, java.lang.String urlTitle, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle,
			status);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_KBFI_UT_ST(
		long groupId, long kbFolderId, java.lang.String urlTitle, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle,
			status, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_KBFI_UT_ST(
		long groupId, long kbFolderId, java.lang.String urlTitle, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle,
			status, start, end, orderByComparator);
	}

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByG_KBFI_UT_ST_PrevAndNext(
		long kbArticleId, long groupId, long kbFolderId,
		java.lang.String urlTitle, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_KBFI_UT_ST_PrevAndNext(kbArticleId, groupId,
			kbFolderId, urlTitle, status, orderByComparator);
	}

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param statuses the statuses
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_KBFI_UT_ST(
		long groupId, long kbFolderId, java.lang.String urlTitle, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle,
			statuses);
	}

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param statuses the statuses
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_KBFI_UT_ST(
		long groupId, long kbFolderId, java.lang.String urlTitle,
		int[] statuses, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle,
			statuses, start, end);
	}

	/**
	* Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param statuses the statuses
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_KBFI_UT_ST(
		long groupId, long kbFolderId, java.lang.String urlTitle,
		int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle,
			statuses, start, end, orderByComparator);
	}

	/**
	* Returns all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param statuses the statuses
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_KBFI_UT_ST(
		long groupId, long kbFolderId, java.lang.String urlTitle, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle, statuses);
	}

	/**
	* Returns a range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param statuses the statuses
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_KBFI_UT_ST(
		long groupId, long kbFolderId, java.lang.String urlTitle,
		int[] statuses, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle, statuses,
			start, end);
	}

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param statuses the statuses
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_KBFI_UT_ST(
		long groupId, long kbFolderId, java.lang.String urlTitle,
		int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle, statuses,
			start, end, orderByComparator);
	}

	/**
	* Removes all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_KBFI_UT_ST(long groupId, long kbFolderId,
		java.lang.String urlTitle, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle, status);
	}

	/**
	* Returns the number of k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_KBFI_UT_ST(long groupId, long kbFolderId,
		java.lang.String urlTitle, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle, status);
	}

	/**
	* Returns the number of k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param statuses the statuses
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_KBFI_UT_ST(long groupId, long kbFolderId,
		java.lang.String urlTitle, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle, statuses);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_KBFI_UT_ST(long groupId, long kbFolderId,
		java.lang.String urlTitle, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle,
			status);
	}

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param statuses the statuses
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_KBFI_UT_ST(long groupId, long kbFolderId,
		java.lang.String urlTitle, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle,
			statuses);
	}

	/**
	* Caches the k b article in the entity cache if it is enabled.
	*
	* @param kbArticle the k b article
	*/
	public static void cacheResult(
		com.liferay.knowledgebase.model.KBArticle kbArticle) {
		getPersistence().cacheResult(kbArticle);
	}

	/**
	* Caches the k b articles in the entity cache if it is enabled.
	*
	* @param kbArticles the k b articles
	*/
	public static void cacheResult(
		java.util.List<com.liferay.knowledgebase.model.KBArticle> kbArticles) {
		getPersistence().cacheResult(kbArticles);
	}

	/**
	* Creates a new k b article with the primary key. Does not add the k b article to the database.
	*
	* @param kbArticleId the primary key for the new k b article
	* @return the new k b article
	*/
	public static com.liferay.knowledgebase.model.KBArticle create(
		long kbArticleId) {
		return getPersistence().create(kbArticleId);
	}

	/**
	* Removes the k b article with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbArticleId the primary key of the k b article
	* @return the k b article that was removed
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle remove(
		long kbArticleId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(kbArticleId);
	}

	public static com.liferay.knowledgebase.model.KBArticle updateImpl(
		com.liferay.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(kbArticle);
	}

	/**
	* Returns the k b article with the primary key or throws a {@link com.liferay.knowledgebase.NoSuchArticleException} if it could not be found.
	*
	* @param kbArticleId the primary key of the k b article
	* @return the k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByPrimaryKey(
		long kbArticleId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(kbArticleId);
	}

	/**
	* Returns the k b article with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kbArticleId the primary key of the k b article
	* @return the k b article, or <code>null</code> if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByPrimaryKey(
		long kbArticleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(kbArticleId);
	}

	/**
	* Returns all the k b articles.
	*
	* @return the k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the k b articles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the k b articles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the k b articles from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of k b articles.
	*
	* @return the number of k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static KBArticlePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KBArticlePersistence)PortletBeanLocatorUtil.locate(com.liferay.knowledgebase.service.ClpSerializer.getServletContextName(),
					KBArticlePersistence.class.getName());

			ReferenceRegistry.registerReference(KBArticleUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(KBArticlePersistence persistence) {
	}

	private static KBArticlePersistence _persistence;
}