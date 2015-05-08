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

import aQute.bnd.annotation.ProviderType;

import com.liferay.knowledgebase.model.KBArticle;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the k b article service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.knowledgebase.service.persistence.impl.KBArticlePersistenceImpl
 * @see KBArticleUtil
 * @generated
 */
@ProviderType
public interface KBArticlePersistence extends BasePersistence<KBArticle> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KBArticleUtil} to access the k b article persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the k b articles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the k b articles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the k b articles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where uuid = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByUuid_PrevAndNext(long kbArticleId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Removes all the k b articles where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of k b articles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching k b articles
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the k b article where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchArticleException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the k b article where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the k b article where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the k b article where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the k b article that was removed
	*/
	public KBArticle removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the number of k b articles where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching k b articles
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the k b articles where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the k b articles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByUuid_C_PrevAndNext(long kbArticleId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Removes all the k b articles where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of k b articles where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching k b articles
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the k b articles where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByResourcePrimKey(long resourcePrimKey);

	/**
	* Returns a range of all the k b articles where resourcePrimKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByResourcePrimKey(
		long resourcePrimKey, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByResourcePrimKey(
		long resourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByResourcePrimKey_First(long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByResourcePrimKey_First(long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByResourcePrimKey_Last(long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByResourcePrimKey_Last(long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByResourcePrimKey_PrevAndNext(long kbArticleId,
		long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	*/
	public void removeByResourcePrimKey(long resourcePrimKey);

	/**
	* Returns the number of k b articles where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @return the number of matching k b articles
	*/
	public int countByResourcePrimKey(long resourcePrimKey);

	/**
	* Returns all the k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByR_G(long resourcePrimKey,
		long groupId);

	/**
	* Returns a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_G(long resourcePrimKey,
		long groupId, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_G(long resourcePrimKey,
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByR_G_First(long resourcePrimKey, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByR_G_First(long resourcePrimKey, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByR_G_Last(long resourcePrimKey, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByR_G_Last(long resourcePrimKey, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByR_G_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByR_G(long resourcePrimKey,
		long groupId);

	/**
	* Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByR_G(long resourcePrimKey,
		long groupId, int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByR_G(long resourcePrimKey,
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] filterFindByR_G_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; and groupId = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	*/
	public void removeByR_G(long resourcePrimKey, long groupId);

	/**
	* Returns the number of k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @return the number of matching k b articles
	*/
	public int countByR_G(long resourcePrimKey, long groupId);

	/**
	* Returns the number of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByR_G(long resourcePrimKey, long groupId);

	/**
	* Returns the k b article where resourcePrimKey = &#63; and version = &#63; or throws a {@link NoSuchArticleException} if it could not be found.
	*
	* @param resourcePrimKey the resource prim key
	* @param version the version
	* @return the matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByR_V(long resourcePrimKey, int version)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the k b article where resourcePrimKey = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param resourcePrimKey the resource prim key
	* @param version the version
	* @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByR_V(long resourcePrimKey, int version);

	/**
	* Returns the k b article where resourcePrimKey = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param resourcePrimKey the resource prim key
	* @param version the version
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByR_V(long resourcePrimKey, int version,
		boolean retrieveFromCache);

	/**
	* Removes the k b article where resourcePrimKey = &#63; and version = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	* @param version the version
	* @return the k b article that was removed
	*/
	public KBArticle removeByR_V(long resourcePrimKey, int version)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the number of k b articles where resourcePrimKey = &#63; and version = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param version the version
	* @return the number of matching k b articles
	*/
	public int countByR_V(long resourcePrimKey, int version);

	/**
	* Returns all the k b articles where resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param latest the latest
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByR_L(long resourcePrimKey,
		boolean latest);

	/**
	* Returns a range of all the k b articles where resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_L(long resourcePrimKey,
		boolean latest, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_L(long resourcePrimKey,
		boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByR_L_First(long resourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByR_L_First(long resourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByR_L_Last(long resourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByR_L_Last(long resourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByR_L_PrevAndNext(long kbArticleId,
		long resourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles where resourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param latest the latest
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByR_L(long[] resourcePrimKeies,
		boolean latest);

	/**
	* Returns a range of all the k b articles where resourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_L(long[] resourcePrimKeies,
		boolean latest, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_L(long[] resourcePrimKeies,
		boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; and latest = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	* @param latest the latest
	*/
	public void removeByR_L(long resourcePrimKey, boolean latest);

	/**
	* Returns the number of k b articles where resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param latest the latest
	* @return the number of matching k b articles
	*/
	public int countByR_L(long resourcePrimKey, boolean latest);

	/**
	* Returns the number of k b articles where resourcePrimKey = any &#63; and latest = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param latest the latest
	* @return the number of matching k b articles
	*/
	public int countByR_L(long[] resourcePrimKeies, boolean latest);

	/**
	* Returns all the k b articles where resourcePrimKey = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param main the main
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByR_M(long resourcePrimKey,
		boolean main);

	/**
	* Returns a range of all the k b articles where resourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_M(long resourcePrimKey,
		boolean main, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_M(long resourcePrimKey,
		boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByR_M_First(long resourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByR_M_First(long resourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByR_M_Last(long resourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByR_M_Last(long resourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByR_M_PrevAndNext(long kbArticleId,
		long resourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles where resourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param main the main
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByR_M(long[] resourcePrimKeies,
		boolean main);

	/**
	* Returns a range of all the k b articles where resourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_M(long[] resourcePrimKeies,
		boolean main, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_M(long[] resourcePrimKeies,
		boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; and main = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	* @param main the main
	*/
	public void removeByR_M(long resourcePrimKey, boolean main);

	/**
	* Returns the number of k b articles where resourcePrimKey = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param main the main
	* @return the number of matching k b articles
	*/
	public int countByR_M(long resourcePrimKey, boolean main);

	/**
	* Returns the number of k b articles where resourcePrimKey = any &#63; and main = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param main the main
	* @return the number of matching k b articles
	*/
	public int countByR_M(long[] resourcePrimKeies, boolean main);

	/**
	* Returns all the k b articles where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByR_S(long resourcePrimKey, int status);

	/**
	* Returns a range of all the k b articles where resourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_S(long resourcePrimKey,
		int status, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_S(long resourcePrimKey,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByR_S_First(long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByR_S_First(long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByR_S_Last(long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByR_S_Last(long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByR_S_PrevAndNext(long kbArticleId,
		long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles where resourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param status the status
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByR_S(long[] resourcePrimKeies,
		int status);

	/**
	* Returns a range of all the k b articles where resourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_S(long[] resourcePrimKeies,
		int status, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_S(long[] resourcePrimKeies,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; and status = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	*/
	public void removeByR_S(long resourcePrimKey, int status);

	/**
	* Returns the number of k b articles where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param status the status
	* @return the number of matching k b articles
	*/
	public int countByR_S(long resourcePrimKey, int status);

	/**
	* Returns the number of k b articles where resourcePrimKey = any &#63; and status = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param status the status
	* @return the number of matching k b articles
	*/
	public int countByR_S(long[] resourcePrimKeies, int status);

	/**
	* Returns all the k b articles where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param latest the latest
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByG_L(long groupId, boolean latest);

	/**
	* Returns a range of all the k b articles where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_L(long groupId, boolean latest,
		int start, int end);

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_L(long groupId, boolean latest,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_L_First(long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_L_First(long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_L_Last(long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_L_Last(long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and latest = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByG_L_PrevAndNext(long kbArticleId, long groupId,
		boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param latest the latest
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_L(long groupId,
		boolean latest);

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_L(long groupId,
		boolean latest, int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_L(long groupId,
		boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and latest = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] filterFindByG_L_PrevAndNext(long kbArticleId,
		long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Removes all the k b articles where groupId = &#63; and latest = &#63; from the database.
	*
	* @param groupId the group ID
	* @param latest the latest
	*/
	public void removeByG_L(long groupId, boolean latest);

	/**
	* Returns the number of k b articles where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param latest the latest
	* @return the number of matching k b articles
	*/
	public int countByG_L(long groupId, boolean latest);

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param latest the latest
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByG_L(long groupId, boolean latest);

	/**
	* Returns all the k b articles where groupId = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param main the main
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByG_M(long groupId, boolean main);

	/**
	* Returns a range of all the k b articles where groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_M(long groupId, boolean main,
		int start, int end);

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_M(long groupId, boolean main,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_M_First(long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_M_First(long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_M_Last(long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_M_Last(long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and main = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByG_M_PrevAndNext(long kbArticleId, long groupId,
		boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param main the main
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_M(long groupId, boolean main);

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_M(long groupId,
		boolean main, int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_M(long groupId,
		boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and main = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] filterFindByG_M_PrevAndNext(long kbArticleId,
		long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Removes all the k b articles where groupId = &#63; and main = &#63; from the database.
	*
	* @param groupId the group ID
	* @param main the main
	*/
	public void removeByG_M(long groupId, boolean main);

	/**
	* Returns the number of k b articles where groupId = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param main the main
	* @return the number of matching k b articles
	*/
	public int countByG_M(long groupId, boolean main);

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param main the main
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByG_M(long groupId, boolean main);

	/**
	* Returns all the k b articles where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByG_S(long groupId, int status);

	/**
	* Returns a range of all the k b articles where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_S(long groupId, int status,
		int start, int end);

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_S(long groupId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_S_First(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_S_First(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_S_Last(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_S_Last(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByG_S_PrevAndNext(long kbArticleId, long groupId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_S(long groupId, int status);

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_S(long groupId, int status,
		int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_S(long groupId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] filterFindByG_S_PrevAndNext(long kbArticleId,
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Removes all the k b articles where groupId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param status the status
	*/
	public void removeByG_S(long groupId, int status);

	/**
	* Returns the number of k b articles where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching k b articles
	*/
	public int countByG_S(long groupId, int status);

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByG_S(long groupId, int status);

	/**
	* Returns all the k b articles where companyId = &#63; and latest = &#63;.
	*
	* @param companyId the company ID
	* @param latest the latest
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByC_L(long companyId, boolean latest);

	/**
	* Returns a range of all the k b articles where companyId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByC_L(long companyId, boolean latest,
		int start, int end);

	/**
	* Returns an ordered range of all the k b articles where companyId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByC_L(long companyId, boolean latest,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where companyId = &#63; and latest = &#63;.
	*
	* @param companyId the company ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByC_L_First(long companyId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where companyId = &#63; and latest = &#63;.
	*
	* @param companyId the company ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByC_L_First(long companyId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where companyId = &#63; and latest = &#63;.
	*
	* @param companyId the company ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByC_L_Last(long companyId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where companyId = &#63; and latest = &#63;.
	*
	* @param companyId the company ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByC_L_Last(long companyId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where companyId = &#63; and latest = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param companyId the company ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByC_L_PrevAndNext(long kbArticleId, long companyId,
		boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Removes all the k b articles where companyId = &#63; and latest = &#63; from the database.
	*
	* @param companyId the company ID
	* @param latest the latest
	*/
	public void removeByC_L(long companyId, boolean latest);

	/**
	* Returns the number of k b articles where companyId = &#63; and latest = &#63;.
	*
	* @param companyId the company ID
	* @param latest the latest
	* @return the number of matching k b articles
	*/
	public int countByC_L(long companyId, boolean latest);

	/**
	* Returns all the k b articles where companyId = &#63; and main = &#63;.
	*
	* @param companyId the company ID
	* @param main the main
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByC_M(long companyId, boolean main);

	/**
	* Returns a range of all the k b articles where companyId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByC_M(long companyId, boolean main,
		int start, int end);

	/**
	* Returns an ordered range of all the k b articles where companyId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByC_M(long companyId, boolean main,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where companyId = &#63; and main = &#63;.
	*
	* @param companyId the company ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByC_M_First(long companyId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where companyId = &#63; and main = &#63;.
	*
	* @param companyId the company ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByC_M_First(long companyId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where companyId = &#63; and main = &#63;.
	*
	* @param companyId the company ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByC_M_Last(long companyId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where companyId = &#63; and main = &#63;.
	*
	* @param companyId the company ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByC_M_Last(long companyId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where companyId = &#63; and main = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param companyId the company ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByC_M_PrevAndNext(long kbArticleId, long companyId,
		boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Removes all the k b articles where companyId = &#63; and main = &#63; from the database.
	*
	* @param companyId the company ID
	* @param main the main
	*/
	public void removeByC_M(long companyId, boolean main);

	/**
	* Returns the number of k b articles where companyId = &#63; and main = &#63;.
	*
	* @param companyId the company ID
	* @param main the main
	* @return the number of matching k b articles
	*/
	public int countByC_M(long companyId, boolean main);

	/**
	* Returns all the k b articles where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByC_S(long companyId, int status);

	/**
	* Returns a range of all the k b articles where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByC_S(long companyId, int status,
		int start, int end);

	/**
	* Returns an ordered range of all the k b articles where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByC_S(long companyId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByC_S_First(long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByC_S_First(long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByC_S_Last(long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByC_S_Last(long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where companyId = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param companyId the company ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByC_S_PrevAndNext(long kbArticleId, long companyId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Removes all the k b articles where companyId = &#63; and status = &#63; from the database.
	*
	* @param companyId the company ID
	* @param status the status
	*/
	public void removeByC_S(long companyId, int status);

	/**
	* Returns the number of k b articles where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID
	* @param status the status
	* @return the number of matching k b articles
	*/
	public int countByC_S(long companyId, int status);

	/**
	* Returns all the k b articles where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByP_L(long parentResourcePrimKey,
		boolean latest);

	/**
	* Returns a range of all the k b articles where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByP_L(long parentResourcePrimKey,
		boolean latest, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByP_L(long parentResourcePrimKey,
		boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByP_L_First(long parentResourcePrimKey,
		boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByP_L_First(long parentResourcePrimKey,
		boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByP_L_Last(long parentResourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByP_L_Last(long parentResourcePrimKey,
		boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByP_L_PrevAndNext(long kbArticleId,
		long parentResourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles where parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByP_L(long[] parentResourcePrimKeies,
		boolean latest);

	/**
	* Returns a range of all the k b articles where parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByP_L(long[] parentResourcePrimKeies,
		boolean latest, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByP_L(long[] parentResourcePrimKeies,
		boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Removes all the k b articles where parentResourcePrimKey = &#63; and latest = &#63; from the database.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	*/
	public void removeByP_L(long parentResourcePrimKey, boolean latest);

	/**
	* Returns the number of k b articles where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @return the number of matching k b articles
	*/
	public int countByP_L(long parentResourcePrimKey, boolean latest);

	/**
	* Returns the number of k b articles where parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @return the number of matching k b articles
	*/
	public int countByP_L(long[] parentResourcePrimKeies, boolean latest);

	/**
	* Returns all the k b articles where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByP_M(long parentResourcePrimKey,
		boolean main);

	/**
	* Returns a range of all the k b articles where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByP_M(long parentResourcePrimKey,
		boolean main, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByP_M(long parentResourcePrimKey,
		boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByP_M_First(long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByP_M_First(long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByP_M_Last(long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByP_M_Last(long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByP_M_PrevAndNext(long kbArticleId,
		long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles where parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByP_M(long[] parentResourcePrimKeies,
		boolean main);

	/**
	* Returns a range of all the k b articles where parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByP_M(long[] parentResourcePrimKeies,
		boolean main, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByP_M(long[] parentResourcePrimKeies,
		boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Removes all the k b articles where parentResourcePrimKey = &#63; and main = &#63; from the database.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	*/
	public void removeByP_M(long parentResourcePrimKey, boolean main);

	/**
	* Returns the number of k b articles where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @return the number of matching k b articles
	*/
	public int countByP_M(long parentResourcePrimKey, boolean main);

	/**
	* Returns the number of k b articles where parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @return the number of matching k b articles
	*/
	public int countByP_M(long[] parentResourcePrimKeies, boolean main);

	/**
	* Returns all the k b articles where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByP_S(long parentResourcePrimKey,
		int status);

	/**
	* Returns a range of all the k b articles where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByP_S(long parentResourcePrimKey,
		int status, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByP_S(long parentResourcePrimKey,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByP_S_First(long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByP_S_First(long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByP_S_Last(long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByP_S_Last(long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByP_S_PrevAndNext(long kbArticleId,
		long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles where parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByP_S(long[] parentResourcePrimKeies,
		int status);

	/**
	* Returns a range of all the k b articles where parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByP_S(long[] parentResourcePrimKeies,
		int status, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByP_S(long[] parentResourcePrimKeies,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Removes all the k b articles where parentResourcePrimKey = &#63; and status = &#63; from the database.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	*/
	public void removeByP_S(long parentResourcePrimKey, int status);

	/**
	* Returns the number of k b articles where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @return the number of matching k b articles
	*/
	public int countByP_S(long parentResourcePrimKey, int status);

	/**
	* Returns the number of k b articles where parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @return the number of matching k b articles
	*/
	public int countByP_S(long[] parentResourcePrimKeies, int status);

	/**
	* Returns all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByR_G_L(long resourcePrimKey,
		long groupId, boolean latest);

	/**
	* Returns a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_G_L(long resourcePrimKey,
		long groupId, boolean latest, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_G_L(long resourcePrimKey,
		long groupId, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByR_G_L_First(long resourcePrimKey, long groupId,
		boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByR_G_L_First(long resourcePrimKey, long groupId,
		boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByR_G_L_Last(long resourcePrimKey, long groupId,
		boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByR_G_L_Last(long resourcePrimKey, long groupId,
		boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByR_G_L_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByR_G_L(long resourcePrimKey,
		long groupId, boolean latest);

	/**
	* Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByR_G_L(long resourcePrimKey,
		long groupId, boolean latest, int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByR_G_L(long resourcePrimKey,
		long groupId, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] filterFindByR_G_L_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param latest the latest
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByR_G_L(
		long[] resourcePrimKeies, long groupId, boolean latest);

	/**
	* Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByR_G_L(
		long[] resourcePrimKeies, long groupId, boolean latest, int start,
		int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByR_G_L(
		long[] resourcePrimKeies, long groupId, boolean latest, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param latest the latest
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByR_G_L(long[] resourcePrimKeies,
		long groupId, boolean latest);

	/**
	* Returns a range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_G_L(long[] resourcePrimKeies,
		long groupId, boolean latest, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_G_L(long[] resourcePrimKeies,
		long groupId, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	*/
	public void removeByR_G_L(long resourcePrimKey, long groupId, boolean latest);

	/**
	* Returns the number of k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @return the number of matching k b articles
	*/
	public int countByR_G_L(long resourcePrimKey, long groupId, boolean latest);

	/**
	* Returns the number of k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param latest the latest
	* @return the number of matching k b articles
	*/
	public int countByR_G_L(long[] resourcePrimKeies, long groupId,
		boolean latest);

	/**
	* Returns the number of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param latest the latest
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByR_G_L(long resourcePrimKey, long groupId,
		boolean latest);

	/**
	* Returns the number of k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param latest the latest
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByR_G_L(long[] resourcePrimKeies, long groupId,
		boolean latest);

	/**
	* Returns all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByR_G_M(long resourcePrimKey,
		long groupId, boolean main);

	/**
	* Returns a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_G_M(long resourcePrimKey,
		long groupId, boolean main, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_G_M(long resourcePrimKey,
		long groupId, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByR_G_M_First(long resourcePrimKey, long groupId,
		boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByR_G_M_First(long resourcePrimKey, long groupId,
		boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByR_G_M_Last(long resourcePrimKey, long groupId,
		boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByR_G_M_Last(long resourcePrimKey, long groupId,
		boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByR_G_M_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByR_G_M(long resourcePrimKey,
		long groupId, boolean main);

	/**
	* Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByR_G_M(long resourcePrimKey,
		long groupId, boolean main, int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByR_G_M(long resourcePrimKey,
		long groupId, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] filterFindByR_G_M_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param main the main
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByR_G_M(
		long[] resourcePrimKeies, long groupId, boolean main);

	/**
	* Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByR_G_M(
		long[] resourcePrimKeies, long groupId, boolean main, int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByR_G_M(
		long[] resourcePrimKeies, long groupId, boolean main, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param main the main
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByR_G_M(long[] resourcePrimKeies,
		long groupId, boolean main);

	/**
	* Returns a range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_G_M(long[] resourcePrimKeies,
		long groupId, boolean main, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_G_M(long[] resourcePrimKeies,
		long groupId, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	*/
	public void removeByR_G_M(long resourcePrimKey, long groupId, boolean main);

	/**
	* Returns the number of k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @return the number of matching k b articles
	*/
	public int countByR_G_M(long resourcePrimKey, long groupId, boolean main);

	/**
	* Returns the number of k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param main the main
	* @return the number of matching k b articles
	*/
	public int countByR_G_M(long[] resourcePrimKeies, long groupId, boolean main);

	/**
	* Returns the number of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param main the main
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByR_G_M(long resourcePrimKey, long groupId,
		boolean main);

	/**
	* Returns the number of k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param main the main
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByR_G_M(long[] resourcePrimKeies, long groupId,
		boolean main);

	/**
	* Returns all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByR_G_S(long resourcePrimKey,
		long groupId, int status);

	/**
	* Returns a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_G_S(long resourcePrimKey,
		long groupId, int status, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_G_S(long resourcePrimKey,
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByR_G_S_First(long resourcePrimKey, long groupId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByR_G_S_First(long resourcePrimKey, long groupId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByR_G_S_Last(long resourcePrimKey, long groupId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByR_G_S_Last(long resourcePrimKey, long groupId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByR_G_S_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByR_G_S(long resourcePrimKey,
		long groupId, int status);

	/**
	* Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByR_G_S(long resourcePrimKey,
		long groupId, int status, int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByR_G_S(long resourcePrimKey,
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] filterFindByR_G_S_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param status the status
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByR_G_S(
		long[] resourcePrimKeies, long groupId, int status);

	/**
	* Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByR_G_S(
		long[] resourcePrimKeies, long groupId, int status, int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByR_G_S(
		long[] resourcePrimKeies, long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param status the status
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByR_G_S(long[] resourcePrimKeies,
		long groupId, int status);

	/**
	* Returns a range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_G_S(long[] resourcePrimKeies,
		long groupId, int status, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByR_G_S(long[] resourcePrimKeies,
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	*/
	public void removeByR_G_S(long resourcePrimKey, long groupId, int status);

	/**
	* Returns the number of k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching k b articles
	*/
	public int countByR_G_S(long resourcePrimKey, long groupId, int status);

	/**
	* Returns the number of k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching k b articles
	*/
	public int countByR_G_S(long[] resourcePrimKeies, long groupId, int status);

	/**
	* Returns the number of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByR_G_S(long resourcePrimKey, long groupId, int status);

	/**
	* Returns the number of k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByR_G_S(long[] resourcePrimKeies, long groupId,
		int status);

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_L(long groupId,
		long parentResourcePrimKey, boolean latest);

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_L(long groupId,
		long parentResourcePrimKey, boolean latest, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_L(long groupId,
		long parentResourcePrimKey, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_P_L_First(long groupId,
		long parentResourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_P_L_First(long groupId,
		long parentResourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_P_L_Last(long groupId, long parentResourcePrimKey,
		boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_P_L_Last(long groupId,
		long parentResourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByG_P_L_PrevAndNext(long kbArticleId, long groupId,
		long parentResourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_L(long groupId,
		long parentResourcePrimKey, boolean latest);

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_L(long groupId,
		long parentResourcePrimKey, boolean latest, int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_L(long groupId,
		long parentResourcePrimKey, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] filterFindByG_P_L_PrevAndNext(long kbArticleId,
		long groupId, long parentResourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest);

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest, int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest);

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	*/
	public void removeByG_P_L(long groupId, long parentResourcePrimKey,
		boolean latest);

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @return the number of matching k b articles
	*/
	public int countByG_P_L(long groupId, long parentResourcePrimKey,
		boolean latest);

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @return the number of matching k b articles
	*/
	public int countByG_P_L(long groupId, long[] parentResourcePrimKeies,
		boolean latest);

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param latest the latest
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByG_P_L(long groupId, long parentResourcePrimKey,
		boolean latest);

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param latest the latest
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByG_P_L(long groupId, long[] parentResourcePrimKeies,
		boolean latest);

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_M(long groupId,
		long parentResourcePrimKey, boolean main);

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_M(long groupId,
		long parentResourcePrimKey, boolean main, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_M(long groupId,
		long parentResourcePrimKey, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_P_M_First(long groupId,
		long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_P_M_First(long groupId,
		long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_P_M_Last(long groupId, long parentResourcePrimKey,
		boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_P_M_Last(long groupId,
		long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByG_P_M_PrevAndNext(long kbArticleId, long groupId,
		long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_M(long groupId,
		long parentResourcePrimKey, boolean main);

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_M(long groupId,
		long parentResourcePrimKey, boolean main, int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_M(long groupId,
		long parentResourcePrimKey, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] filterFindByG_P_M_PrevAndNext(long kbArticleId,
		long groupId, long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main);

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main, int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main);

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	*/
	public void removeByG_P_M(long groupId, long parentResourcePrimKey,
		boolean main);

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @return the number of matching k b articles
	*/
	public int countByG_P_M(long groupId, long parentResourcePrimKey,
		boolean main);

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @return the number of matching k b articles
	*/
	public int countByG_P_M(long groupId, long[] parentResourcePrimKeies,
		boolean main);

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param main the main
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByG_P_M(long groupId, long parentResourcePrimKey,
		boolean main);

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param main the main
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByG_P_M(long groupId, long[] parentResourcePrimKeies,
		boolean main);

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_S(long groupId,
		long parentResourcePrimKey, int status);

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_S(long groupId,
		long parentResourcePrimKey, int status, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_S(long groupId,
		long parentResourcePrimKey, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_P_S_First(long groupId,
		long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_P_S_First(long groupId,
		long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_P_S_Last(long groupId, long parentResourcePrimKey,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_P_S_Last(long groupId,
		long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByG_P_S_PrevAndNext(long kbArticleId, long groupId,
		long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_S(long groupId,
		long parentResourcePrimKey, int status);

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_S(long groupId,
		long parentResourcePrimKey, int status, int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_S(long groupId,
		long parentResourcePrimKey, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] filterFindByG_P_S_PrevAndNext(long kbArticleId,
		long groupId, long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status);

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status, int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status);

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	*/
	public void removeByG_P_S(long groupId, long parentResourcePrimKey,
		int status);

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @return the number of matching k b articles
	*/
	public int countByG_P_S(long groupId, long parentResourcePrimKey, int status);

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @return the number of matching k b articles
	*/
	public int countByG_P_S(long groupId, long[] parentResourcePrimKeies,
		int status);

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param status the status
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByG_P_S(long groupId, long parentResourcePrimKey,
		int status);

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKeies the parent resource prim keies
	* @param status the status
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByG_P_S(long groupId, long[] parentResourcePrimKeies,
		int status);

	/**
	* Returns all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByG_KBFI_UT(long groupId,
		long kbFolderId, java.lang.String urlTitle);

	/**
	* Returns a range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_KBFI_UT(long groupId,
		long kbFolderId, java.lang.String urlTitle, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_KBFI_UT(long groupId,
		long kbFolderId, java.lang.String urlTitle, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_KBFI_UT_First(long groupId, long kbFolderId,
		java.lang.String urlTitle,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_KBFI_UT_First(long groupId, long kbFolderId,
		java.lang.String urlTitle,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_KBFI_UT_Last(long groupId, long kbFolderId,
		java.lang.String urlTitle,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_KBFI_UT_Last(long groupId, long kbFolderId,
		java.lang.String urlTitle,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByG_KBFI_UT_PrevAndNext(long kbArticleId,
		long groupId, long kbFolderId, java.lang.String urlTitle,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_KBFI_UT(long groupId,
		long kbFolderId, java.lang.String urlTitle);

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_KBFI_UT(long groupId,
		long kbFolderId, java.lang.String urlTitle, int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_KBFI_UT(long groupId,
		long kbFolderId, java.lang.String urlTitle, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] filterFindByG_KBFI_UT_PrevAndNext(long kbArticleId,
		long groupId, long kbFolderId, java.lang.String urlTitle,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Removes all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; from the database.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	*/
	public void removeByG_KBFI_UT(long groupId, long kbFolderId,
		java.lang.String urlTitle);

	/**
	* Returns the number of k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @return the number of matching k b articles
	*/
	public int countByG_KBFI_UT(long groupId, long kbFolderId,
		java.lang.String urlTitle);

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByG_KBFI_UT(long groupId, long kbFolderId,
		java.lang.String urlTitle);

	/**
	* Returns all the k b articles where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByG_KBFI_S(long groupId,
		long kbFolderId, int status);

	/**
	* Returns a range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_KBFI_S(long groupId,
		long kbFolderId, int status, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_KBFI_S(long groupId,
		long kbFolderId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_KBFI_S_First(long groupId, long kbFolderId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_KBFI_S_First(long groupId, long kbFolderId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_KBFI_S_Last(long groupId, long kbFolderId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_KBFI_S_Last(long groupId, long kbFolderId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByG_KBFI_S_PrevAndNext(long kbArticleId,
		long groupId, long kbFolderId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_KBFI_S(long groupId,
		long kbFolderId, int status);

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_KBFI_S(long groupId,
		long kbFolderId, int status, int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_KBFI_S(long groupId,
		long kbFolderId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] filterFindByG_KBFI_S_PrevAndNext(long kbArticleId,
		long groupId, long kbFolderId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Removes all the k b articles where groupId = &#63; and kbFolderId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	*/
	public void removeByG_KBFI_S(long groupId, long kbFolderId, int status);

	/**
	* Returns the number of k b articles where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @return the number of matching k b articles
	*/
	public int countByG_KBFI_S(long groupId, long kbFolderId, int status);

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param status the status
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByG_KBFI_S(long groupId, long kbFolderId, int status);

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_S_L(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean latest);

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_S_L(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean latest,
		int start, int end);

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	*/
	public java.util.List<KBArticle> findByG_P_S_L(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean latest,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_P_S_L_First(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_P_S_L_First(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_P_S_L_Last(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_P_S_L_Last(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

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
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByG_P_S_L_PrevAndNext(long kbArticleId,
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_S_L(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean latest);

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_S_L(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean latest,
		int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	*/
	public java.util.List<KBArticle> filterFindByG_P_S_L(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean latest,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

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
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] filterFindByG_P_S_L_PrevAndNext(long kbArticleId,
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param latest the latest
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_S_L(long groupId,
		long parentResourcePrimKey, java.lang.String[] sectionses,
		boolean latest);

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_S_L(long groupId,
		long parentResourcePrimKey, java.lang.String[] sectionses,
		boolean latest, int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	*/
	public java.util.List<KBArticle> filterFindByG_P_S_L(long groupId,
		long parentResourcePrimKey, java.lang.String[] sectionses,
		boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param latest the latest
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_S_L(long groupId,
		long parentResourcePrimKey, java.lang.String[] sectionses,
		boolean latest);

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param latest the latest
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_S_L(long groupId,
		long parentResourcePrimKey, java.lang.String[] sectionses,
		boolean latest, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	*/
	public java.util.List<KBArticle> findByG_P_S_L(long groupId,
		long parentResourcePrimKey, java.lang.String[] sectionses,
		boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	*/
	public void removeByG_P_S_L(long groupId, long parentResourcePrimKey,
		java.lang.String sections, boolean latest);

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @return the number of matching k b articles
	*/
	public int countByG_P_S_L(long groupId, long parentResourcePrimKey,
		java.lang.String sections, boolean latest);

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param latest the latest
	* @return the number of matching k b articles
	*/
	public int countByG_P_S_L(long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, boolean latest);

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param latest the latest
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByG_P_S_L(long groupId, long parentResourcePrimKey,
		java.lang.String sections, boolean latest);

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and latest = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param latest the latest
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByG_P_S_L(long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, boolean latest);

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_S_M(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean main);

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_S_M(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean main,
		int start, int end);

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	*/
	public java.util.List<KBArticle> findByG_P_S_M(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean main,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_P_S_M_First(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_P_S_M_First(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_P_S_M_Last(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_P_S_M_Last(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

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
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByG_P_S_M_PrevAndNext(long kbArticleId,
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_S_M(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean main);

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_S_M(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean main,
		int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	*/
	public java.util.List<KBArticle> filterFindByG_P_S_M(long groupId,
		long parentResourcePrimKey, java.lang.String sections, boolean main,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

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
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] filterFindByG_P_S_M_PrevAndNext(long kbArticleId,
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		boolean main,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param main the main
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_S_M(long groupId,
		long parentResourcePrimKey, java.lang.String[] sectionses, boolean main);

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_S_M(long groupId,
		long parentResourcePrimKey, java.lang.String[] sectionses,
		boolean main, int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	*/
	public java.util.List<KBArticle> filterFindByG_P_S_M(long groupId,
		long parentResourcePrimKey, java.lang.String[] sectionses,
		boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param main the main
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_S_M(long groupId,
		long parentResourcePrimKey, java.lang.String[] sectionses, boolean main);

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param main the main
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_S_M(long groupId,
		long parentResourcePrimKey, java.lang.String[] sectionses,
		boolean main, int start, int end);

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	*/
	public java.util.List<KBArticle> findByG_P_S_M(long groupId,
		long parentResourcePrimKey, java.lang.String[] sectionses,
		boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	*/
	public void removeByG_P_S_M(long groupId, long parentResourcePrimKey,
		java.lang.String sections, boolean main);

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @return the number of matching k b articles
	*/
	public int countByG_P_S_M(long groupId, long parentResourcePrimKey,
		java.lang.String sections, boolean main);

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param main the main
	* @return the number of matching k b articles
	*/
	public int countByG_P_S_M(long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, boolean main);

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param main the main
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByG_P_S_M(long groupId, long parentResourcePrimKey,
		java.lang.String sections, boolean main);

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and main = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param main the main
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByG_P_S_M(long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, boolean main);

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_S_S(long groupId,
		long parentResourcePrimKey, java.lang.String sections, int status);

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_S_S(long groupId,
		long parentResourcePrimKey, java.lang.String sections, int status,
		int start, int end);

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	*/
	public java.util.List<KBArticle> findByG_P_S_S(long groupId,
		long parentResourcePrimKey, java.lang.String sections, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_P_S_S_First(long groupId,
		long parentResourcePrimKey, java.lang.String sections, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_P_S_S_First(long groupId,
		long parentResourcePrimKey, java.lang.String sections, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_P_S_S_Last(long groupId,
		long parentResourcePrimKey, java.lang.String sections, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_P_S_S_Last(long groupId,
		long parentResourcePrimKey, java.lang.String sections, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

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
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByG_P_S_S_PrevAndNext(long kbArticleId,
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_S_S(long groupId,
		long parentResourcePrimKey, java.lang.String sections, int status);

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_S_S(long groupId,
		long parentResourcePrimKey, java.lang.String sections, int status,
		int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	*/
	public java.util.List<KBArticle> filterFindByG_P_S_S(long groupId,
		long parentResourcePrimKey, java.lang.String sections, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

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
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] filterFindByG_P_S_S_PrevAndNext(long kbArticleId,
		long groupId, long parentResourcePrimKey, java.lang.String sections,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param status the status
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_S_S(long groupId,
		long parentResourcePrimKey, java.lang.String[] sectionses, int status);

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_P_S_S(long groupId,
		long parentResourcePrimKey, java.lang.String[] sectionses, int status,
		int start, int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	*/
	public java.util.List<KBArticle> filterFindByG_P_S_S(long groupId,
		long parentResourcePrimKey, java.lang.String[] sectionses, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param status the status
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_S_S(long groupId,
		long parentResourcePrimKey, java.lang.String[] sectionses, int status);

	/**
	* Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_P_S_S(long groupId,
		long parentResourcePrimKey, java.lang.String[] sectionses, int status,
		int start, int end);

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	*/
	public java.util.List<KBArticle> findByG_P_S_S(long groupId,
		long parentResourcePrimKey, java.lang.String[] sectionses, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	*/
	public void removeByG_P_S_S(long groupId, long parentResourcePrimKey,
		java.lang.String sections, int status);

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @return the number of matching k b articles
	*/
	public int countByG_P_S_S(long groupId, long parentResourcePrimKey,
		java.lang.String sections, int status);

	/**
	* Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param status the status
	* @return the number of matching k b articles
	*/
	public int countByG_P_S_S(long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, int status);

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sections the sections
	* @param status the status
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByG_P_S_S(long groupId, long parentResourcePrimKey,
		java.lang.String sections, int status);

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and sections LIKE any &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param parentResourcePrimKey the parent resource prim key
	* @param sectionses the sectionses
	* @param status the status
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByG_P_S_S(long groupId, long parentResourcePrimKey,
		java.lang.String[] sectionses, int status);

	/**
	* Returns all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByG_KBFI_UT_ST(long groupId,
		long kbFolderId, java.lang.String urlTitle, int status);

	/**
	* Returns a range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_KBFI_UT_ST(long groupId,
		long kbFolderId, java.lang.String urlTitle, int status, int start,
		int end);

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	*/
	public java.util.List<KBArticle> findByG_KBFI_UT_ST(long groupId,
		long kbFolderId, java.lang.String urlTitle, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_KBFI_UT_ST_First(long groupId, long kbFolderId,
		java.lang.String urlTitle, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the first k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_KBFI_UT_ST_First(long groupId, long kbFolderId,
		java.lang.String urlTitle, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws NoSuchArticleException if a matching k b article could not be found
	*/
	public KBArticle findByG_KBFI_UT_ST_Last(long groupId, long kbFolderId,
		java.lang.String urlTitle, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the last k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	*/
	public KBArticle fetchByG_KBFI_UT_ST_Last(long groupId, long kbFolderId,
		java.lang.String urlTitle, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

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
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] findByG_KBFI_UT_ST_PrevAndNext(long kbArticleId,
		long groupId, long kbFolderId, java.lang.String urlTitle, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_KBFI_UT_ST(long groupId,
		long kbFolderId, java.lang.String urlTitle, int status);

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_KBFI_UT_ST(long groupId,
		long kbFolderId, java.lang.String urlTitle, int status, int start,
		int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	*/
	public java.util.List<KBArticle> filterFindByG_KBFI_UT_ST(long groupId,
		long kbFolderId, java.lang.String urlTitle, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

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
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle[] filterFindByG_KBFI_UT_ST_PrevAndNext(long kbArticleId,
		long groupId, long kbFolderId, java.lang.String urlTitle, int status,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param statuses the statuses
	* @return the matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_KBFI_UT_ST(long groupId,
		long kbFolderId, java.lang.String urlTitle, int[] statuses);

	/**
	* Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param statuses the statuses
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	*/
	public java.util.List<KBArticle> filterFindByG_KBFI_UT_ST(long groupId,
		long kbFolderId, java.lang.String urlTitle, int[] statuses, int start,
		int end);

	/**
	* Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	*/
	public java.util.List<KBArticle> filterFindByG_KBFI_UT_ST(long groupId,
		long kbFolderId, java.lang.String urlTitle, int[] statuses, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Returns all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param statuses the statuses
	* @return the matching k b articles
	*/
	public java.util.List<KBArticle> findByG_KBFI_UT_ST(long groupId,
		long kbFolderId, java.lang.String urlTitle, int[] statuses);

	/**
	* Returns a range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param statuses the statuses
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of matching k b articles
	*/
	public java.util.List<KBArticle> findByG_KBFI_UT_ST(long groupId,
		long kbFolderId, java.lang.String urlTitle, int[] statuses, int start,
		int end);

	/**
	* Returns an ordered range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	*/
	public java.util.List<KBArticle> findByG_KBFI_UT_ST(long groupId,
		long kbFolderId, java.lang.String urlTitle, int[] statuses, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Removes all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	*/
	public void removeByG_KBFI_UT_ST(long groupId, long kbFolderId,
		java.lang.String urlTitle, int status);

	/**
	* Returns the number of k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @return the number of matching k b articles
	*/
	public int countByG_KBFI_UT_ST(long groupId, long kbFolderId,
		java.lang.String urlTitle, int status);

	/**
	* Returns the number of k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param statuses the statuses
	* @return the number of matching k b articles
	*/
	public int countByG_KBFI_UT_ST(long groupId, long kbFolderId,
		java.lang.String urlTitle, int[] statuses);

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param status the status
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByG_KBFI_UT_ST(long groupId, long kbFolderId,
		java.lang.String urlTitle, int status);

	/**
	* Returns the number of k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	*
	* @param groupId the group ID
	* @param kbFolderId the kb folder ID
	* @param urlTitle the url title
	* @param statuses the statuses
	* @return the number of matching k b articles that the user has permission to view
	*/
	public int filterCountByG_KBFI_UT_ST(long groupId, long kbFolderId,
		java.lang.String urlTitle, int[] statuses);

	/**
	* Caches the k b article in the entity cache if it is enabled.
	*
	* @param kbArticle the k b article
	*/
	public void cacheResult(KBArticle kbArticle);

	/**
	* Caches the k b articles in the entity cache if it is enabled.
	*
	* @param kbArticles the k b articles
	*/
	public void cacheResult(java.util.List<KBArticle> kbArticles);

	/**
	* Creates a new k b article with the primary key. Does not add the k b article to the database.
	*
	* @param kbArticleId the primary key for the new k b article
	* @return the new k b article
	*/
	public KBArticle create(long kbArticleId);

	/**
	* Removes the k b article with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbArticleId the primary key of the k b article
	* @return the k b article that was removed
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle remove(long kbArticleId)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	public KBArticle updateImpl(KBArticle kbArticle);

	/**
	* Returns the k b article with the primary key or throws a {@link NoSuchArticleException} if it could not be found.
	*
	* @param kbArticleId the primary key of the k b article
	* @return the k b article
	* @throws NoSuchArticleException if a k b article with the primary key could not be found
	*/
	public KBArticle findByPrimaryKey(long kbArticleId)
		throws com.liferay.knowledgebase.NoSuchArticleException;

	/**
	* Returns the k b article with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kbArticleId the primary key of the k b article
	* @return the k b article, or <code>null</code> if a k b article with the primary key could not be found
	*/
	public KBArticle fetchByPrimaryKey(long kbArticleId);

	@Override
	public java.util.Map<java.io.Serializable, KBArticle> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the k b articles.
	*
	* @return the k b articles
	*/
	public java.util.List<KBArticle> findAll();

	/**
	* Returns a range of all the k b articles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of k b articles
	*/
	public java.util.List<KBArticle> findAll(int start, int end);

	/**
	* Returns an ordered range of all the k b articles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of k b articles
	*/
	public java.util.List<KBArticle> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KBArticle> orderByComparator);

	/**
	* Removes all the k b articles from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of k b articles.
	*
	* @return the number of k b articles
	*/
	public int countAll();
}