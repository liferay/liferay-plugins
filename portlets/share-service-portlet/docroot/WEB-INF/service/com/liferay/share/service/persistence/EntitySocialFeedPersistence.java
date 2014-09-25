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

package com.liferay.share.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.share.model.EntitySocialFeed;

/**
 * The persistence interface for the entity social feed service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntitySocialFeedPersistenceImpl
 * @see EntitySocialFeedUtil
 * @generated
 */
@ProviderType
public interface EntitySocialFeedPersistence extends BasePersistence<EntitySocialFeed> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EntitySocialFeedUtil} to access the entity social feed persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the entity social feeds where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching entity social feeds
	*/
	public java.util.List<com.liferay.share.model.EntitySocialFeed> findByC_C(
		long classNameId, long classPK);

	/**
	* Returns a range of all the entity social feeds where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.share.model.impl.EntitySocialFeedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of entity social feeds
	* @param end the upper bound of the range of entity social feeds (not inclusive)
	* @return the range of matching entity social feeds
	*/
	public java.util.List<com.liferay.share.model.EntitySocialFeed> findByC_C(
		long classNameId, long classPK, int start, int end);

	/**
	* Returns an ordered range of all the entity social feeds where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.share.model.impl.EntitySocialFeedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of entity social feeds
	* @param end the upper bound of the range of entity social feeds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entity social feeds
	*/
	public java.util.List<com.liferay.share.model.EntitySocialFeed> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator);

	/**
	* Returns the first entity social feed in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entity social feed
	* @throws com.liferay.share.NoSuchEntitySocialFeedException if a matching entity social feed could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException;

	/**
	* Returns the first entity social feed in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entity social feed, or <code>null</code> if a matching entity social feed could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator);

	/**
	* Returns the last entity social feed in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entity social feed
	* @throws com.liferay.share.NoSuchEntitySocialFeedException if a matching entity social feed could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException;

	/**
	* Returns the last entity social feed in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entity social feed, or <code>null</code> if a matching entity social feed could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator);

	/**
	* Returns the entity social feeds before and after the current entity social feed in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param entitySocialFeedPK the primary key of the current entity social feed
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entity social feed
	* @throws com.liferay.share.NoSuchEntitySocialFeedException if a entity social feed with the primary key could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed[] findByC_C_PrevAndNext(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK,
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException;

	/**
	* Removes all the entity social feeds where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	*/
	public void removeByC_C(long classNameId, long classPK);

	/**
	* Returns the number of entity social feeds where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching entity social feeds
	*/
	public int countByC_C(long classNameId, long classPK);

	/**
	* Returns all the entity social feeds where feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @return the matching entity social feeds
	*/
	public java.util.List<com.liferay.share.model.EntitySocialFeed> findByF_F(
		long feedClassNameId, long feedClassPK);

	/**
	* Returns a range of all the entity social feeds where feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.share.model.impl.EntitySocialFeedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @param start the lower bound of the range of entity social feeds
	* @param end the upper bound of the range of entity social feeds (not inclusive)
	* @return the range of matching entity social feeds
	*/
	public java.util.List<com.liferay.share.model.EntitySocialFeed> findByF_F(
		long feedClassNameId, long feedClassPK, int start, int end);

	/**
	* Returns an ordered range of all the entity social feeds where feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.share.model.impl.EntitySocialFeedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @param start the lower bound of the range of entity social feeds
	* @param end the upper bound of the range of entity social feeds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entity social feeds
	*/
	public java.util.List<com.liferay.share.model.EntitySocialFeed> findByF_F(
		long feedClassNameId, long feedClassPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator);

	/**
	* Returns the first entity social feed in the ordered set where feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entity social feed
	* @throws com.liferay.share.NoSuchEntitySocialFeedException if a matching entity social feed could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed findByF_F_First(
		long feedClassNameId, long feedClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException;

	/**
	* Returns the first entity social feed in the ordered set where feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entity social feed, or <code>null</code> if a matching entity social feed could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed fetchByF_F_First(
		long feedClassNameId, long feedClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator);

	/**
	* Returns the last entity social feed in the ordered set where feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entity social feed
	* @throws com.liferay.share.NoSuchEntitySocialFeedException if a matching entity social feed could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed findByF_F_Last(
		long feedClassNameId, long feedClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException;

	/**
	* Returns the last entity social feed in the ordered set where feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entity social feed, or <code>null</code> if a matching entity social feed could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed fetchByF_F_Last(
		long feedClassNameId, long feedClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator);

	/**
	* Returns the entity social feeds before and after the current entity social feed in the ordered set where feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param entitySocialFeedPK the primary key of the current entity social feed
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entity social feed
	* @throws com.liferay.share.NoSuchEntitySocialFeedException if a entity social feed with the primary key could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed[] findByF_F_PrevAndNext(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK,
		long feedClassNameId, long feedClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException;

	/**
	* Removes all the entity social feeds where feedClassNameId = &#63; and feedClassPK = &#63; from the database.
	*
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	*/
	public void removeByF_F(long feedClassNameId, long feedClassPK);

	/**
	* Returns the number of entity social feeds where feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @return the number of matching entity social feeds
	*/
	public int countByF_F(long feedClassNameId, long feedClassPK);

	/**
	* Returns all the entity social feeds where classNameId = &#63; and classPK = &#63; and feedClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param feedClassNameId the feed class name ID
	* @return the matching entity social feeds
	*/
	public java.util.List<com.liferay.share.model.EntitySocialFeed> findByC_C_F(
		long classNameId, long classPK, long feedClassNameId);

	/**
	* Returns a range of all the entity social feeds where classNameId = &#63; and classPK = &#63; and feedClassNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.share.model.impl.EntitySocialFeedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param feedClassNameId the feed class name ID
	* @param start the lower bound of the range of entity social feeds
	* @param end the upper bound of the range of entity social feeds (not inclusive)
	* @return the range of matching entity social feeds
	*/
	public java.util.List<com.liferay.share.model.EntitySocialFeed> findByC_C_F(
		long classNameId, long classPK, long feedClassNameId, int start, int end);

	/**
	* Returns an ordered range of all the entity social feeds where classNameId = &#63; and classPK = &#63; and feedClassNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.share.model.impl.EntitySocialFeedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param feedClassNameId the feed class name ID
	* @param start the lower bound of the range of entity social feeds
	* @param end the upper bound of the range of entity social feeds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entity social feeds
	*/
	public java.util.List<com.liferay.share.model.EntitySocialFeed> findByC_C_F(
		long classNameId, long classPK, long feedClassNameId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator);

	/**
	* Returns the first entity social feed in the ordered set where classNameId = &#63; and classPK = &#63; and feedClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param feedClassNameId the feed class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entity social feed
	* @throws com.liferay.share.NoSuchEntitySocialFeedException if a matching entity social feed could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed findByC_C_F_First(
		long classNameId, long classPK, long feedClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException;

	/**
	* Returns the first entity social feed in the ordered set where classNameId = &#63; and classPK = &#63; and feedClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param feedClassNameId the feed class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entity social feed, or <code>null</code> if a matching entity social feed could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed fetchByC_C_F_First(
		long classNameId, long classPK, long feedClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator);

	/**
	* Returns the last entity social feed in the ordered set where classNameId = &#63; and classPK = &#63; and feedClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param feedClassNameId the feed class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entity social feed
	* @throws com.liferay.share.NoSuchEntitySocialFeedException if a matching entity social feed could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed findByC_C_F_Last(
		long classNameId, long classPK, long feedClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException;

	/**
	* Returns the last entity social feed in the ordered set where classNameId = &#63; and classPK = &#63; and feedClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param feedClassNameId the feed class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entity social feed, or <code>null</code> if a matching entity social feed could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed fetchByC_C_F_Last(
		long classNameId, long classPK, long feedClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator);

	/**
	* Returns the entity social feeds before and after the current entity social feed in the ordered set where classNameId = &#63; and classPK = &#63; and feedClassNameId = &#63;.
	*
	* @param entitySocialFeedPK the primary key of the current entity social feed
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param feedClassNameId the feed class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entity social feed
	* @throws com.liferay.share.NoSuchEntitySocialFeedException if a entity social feed with the primary key could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed[] findByC_C_F_PrevAndNext(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK,
		long classNameId, long classPK, long feedClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException;

	/**
	* Removes all the entity social feeds where classNameId = &#63; and classPK = &#63; and feedClassNameId = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param feedClassNameId the feed class name ID
	*/
	public void removeByC_C_F(long classNameId, long classPK,
		long feedClassNameId);

	/**
	* Returns the number of entity social feeds where classNameId = &#63; and classPK = &#63; and feedClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param feedClassNameId the feed class name ID
	* @return the number of matching entity social feeds
	*/
	public int countByC_C_F(long classNameId, long classPK, long feedClassNameId);

	/**
	* Returns all the entity social feeds where classNameId = &#63; and feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @return the matching entity social feeds
	*/
	public java.util.List<com.liferay.share.model.EntitySocialFeed> findByC_F_F(
		long classNameId, long feedClassNameId, long feedClassPK);

	/**
	* Returns a range of all the entity social feeds where classNameId = &#63; and feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.share.model.impl.EntitySocialFeedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @param start the lower bound of the range of entity social feeds
	* @param end the upper bound of the range of entity social feeds (not inclusive)
	* @return the range of matching entity social feeds
	*/
	public java.util.List<com.liferay.share.model.EntitySocialFeed> findByC_F_F(
		long classNameId, long feedClassNameId, long feedClassPK, int start,
		int end);

	/**
	* Returns an ordered range of all the entity social feeds where classNameId = &#63; and feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.share.model.impl.EntitySocialFeedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @param start the lower bound of the range of entity social feeds
	* @param end the upper bound of the range of entity social feeds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entity social feeds
	*/
	public java.util.List<com.liferay.share.model.EntitySocialFeed> findByC_F_F(
		long classNameId, long feedClassNameId, long feedClassPK, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator);

	/**
	* Returns the first entity social feed in the ordered set where classNameId = &#63; and feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entity social feed
	* @throws com.liferay.share.NoSuchEntitySocialFeedException if a matching entity social feed could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed findByC_F_F_First(
		long classNameId, long feedClassNameId, long feedClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException;

	/**
	* Returns the first entity social feed in the ordered set where classNameId = &#63; and feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entity social feed, or <code>null</code> if a matching entity social feed could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed fetchByC_F_F_First(
		long classNameId, long feedClassNameId, long feedClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator);

	/**
	* Returns the last entity social feed in the ordered set where classNameId = &#63; and feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entity social feed
	* @throws com.liferay.share.NoSuchEntitySocialFeedException if a matching entity social feed could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed findByC_F_F_Last(
		long classNameId, long feedClassNameId, long feedClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException;

	/**
	* Returns the last entity social feed in the ordered set where classNameId = &#63; and feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entity social feed, or <code>null</code> if a matching entity social feed could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed fetchByC_F_F_Last(
		long classNameId, long feedClassNameId, long feedClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator);

	/**
	* Returns the entity social feeds before and after the current entity social feed in the ordered set where classNameId = &#63; and feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param entitySocialFeedPK the primary key of the current entity social feed
	* @param classNameId the class name ID
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entity social feed
	* @throws com.liferay.share.NoSuchEntitySocialFeedException if a entity social feed with the primary key could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed[] findByC_F_F_PrevAndNext(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK,
		long classNameId, long feedClassNameId, long feedClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException;

	/**
	* Removes all the entity social feeds where classNameId = &#63; and feedClassNameId = &#63; and feedClassPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	*/
	public void removeByC_F_F(long classNameId, long feedClassNameId,
		long feedClassPK);

	/**
	* Returns the number of entity social feeds where classNameId = &#63; and feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @return the number of matching entity social feeds
	*/
	public int countByC_F_F(long classNameId, long feedClassNameId,
		long feedClassPK);

	/**
	* Caches the entity social feed in the entity cache if it is enabled.
	*
	* @param entitySocialFeed the entity social feed
	*/
	public void cacheResult(
		com.liferay.share.model.EntitySocialFeed entitySocialFeed);

	/**
	* Caches the entity social feeds in the entity cache if it is enabled.
	*
	* @param entitySocialFeeds the entity social feeds
	*/
	public void cacheResult(
		java.util.List<com.liferay.share.model.EntitySocialFeed> entitySocialFeeds);

	/**
	* Creates a new entity social feed with the primary key. Does not add the entity social feed to the database.
	*
	* @param entitySocialFeedPK the primary key for the new entity social feed
	* @return the new entity social feed
	*/
	public com.liferay.share.model.EntitySocialFeed create(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK);

	/**
	* Removes the entity social feed with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entitySocialFeedPK the primary key of the entity social feed
	* @return the entity social feed that was removed
	* @throws com.liferay.share.NoSuchEntitySocialFeedException if a entity social feed with the primary key could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed remove(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK)
		throws com.liferay.share.NoSuchEntitySocialFeedException;

	public com.liferay.share.model.EntitySocialFeed updateImpl(
		com.liferay.share.model.EntitySocialFeed entitySocialFeed);

	/**
	* Returns the entity social feed with the primary key or throws a {@link com.liferay.share.NoSuchEntitySocialFeedException} if it could not be found.
	*
	* @param entitySocialFeedPK the primary key of the entity social feed
	* @return the entity social feed
	* @throws com.liferay.share.NoSuchEntitySocialFeedException if a entity social feed with the primary key could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed findByPrimaryKey(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK)
		throws com.liferay.share.NoSuchEntitySocialFeedException;

	/**
	* Returns the entity social feed with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entitySocialFeedPK the primary key of the entity social feed
	* @return the entity social feed, or <code>null</code> if a entity social feed with the primary key could not be found
	*/
	public com.liferay.share.model.EntitySocialFeed fetchByPrimaryKey(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK);

	@Override
	public java.util.Map<java.io.Serializable, com.liferay.share.model.EntitySocialFeed> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the entity social feeds.
	*
	* @return the entity social feeds
	*/
	public java.util.List<com.liferay.share.model.EntitySocialFeed> findAll();

	/**
	* Returns a range of all the entity social feeds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.share.model.impl.EntitySocialFeedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entity social feeds
	* @param end the upper bound of the range of entity social feeds (not inclusive)
	* @return the range of entity social feeds
	*/
	public java.util.List<com.liferay.share.model.EntitySocialFeed> findAll(
		int start, int end);

	/**
	* Returns an ordered range of all the entity social feeds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.share.model.impl.EntitySocialFeedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entity social feeds
	* @param end the upper bound of the range of entity social feeds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of entity social feeds
	*/
	public java.util.List<com.liferay.share.model.EntitySocialFeed> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator);

	/**
	* Removes all the entity social feeds from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of entity social feeds.
	*
	* @return the number of entity social feeds
	*/
	public int countAll();
}