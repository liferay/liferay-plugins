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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.share.model.EntitySocialFeed;

import java.util.List;

/**
 * The persistence utility for the entity social feed service. This utility wraps {@link EntitySocialFeedPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntitySocialFeedPersistence
 * @see EntitySocialFeedPersistenceImpl
 * @generated
 */
@ProviderType
public class EntitySocialFeedUtil {
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
	public static void clearCache(EntitySocialFeed entitySocialFeed) {
		getPersistence().clearCache(entitySocialFeed);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<EntitySocialFeed> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<EntitySocialFeed> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<EntitySocialFeed> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<EntitySocialFeed> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static EntitySocialFeed update(EntitySocialFeed entitySocialFeed) {
		return getPersistence().update(entitySocialFeed);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static EntitySocialFeed update(EntitySocialFeed entitySocialFeed,
		ServiceContext serviceContext) {
		return getPersistence().update(entitySocialFeed, serviceContext);
	}

	/**
	* Returns all the entity social feeds where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching entity social feeds
	*/
	public static java.util.List<com.liferay.share.model.EntitySocialFeed> findByC_C(
		long classNameId, long classPK) {
		return getPersistence().findByC_C(classNameId, classPK);
	}

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
	public static java.util.List<com.liferay.share.model.EntitySocialFeed> findByC_C(
		long classNameId, long classPK, int start, int end) {
		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

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
	public static java.util.List<com.liferay.share.model.EntitySocialFeed> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator) {
		return getPersistence()
				   .findByC_C(classNameId, classPK, start, end,
			orderByComparator);
	}

	/**
	* Returns the first entity social feed in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entity social feed
	* @throws com.liferay.share.NoSuchEntitySocialFeedException if a matching entity social feed could not be found
	*/
	public static com.liferay.share.model.EntitySocialFeed findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException {
		return getPersistence()
				   .findByC_C_First(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the first entity social feed in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entity social feed, or <code>null</code> if a matching entity social feed could not be found
	*/
	public static com.liferay.share.model.EntitySocialFeed fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_First(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last entity social feed in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entity social feed
	* @throws com.liferay.share.NoSuchEntitySocialFeedException if a matching entity social feed could not be found
	*/
	public static com.liferay.share.model.EntitySocialFeed findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException {
		return getPersistence()
				   .findByC_C_Last(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last entity social feed in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entity social feed, or <code>null</code> if a matching entity social feed could not be found
	*/
	public static com.liferay.share.model.EntitySocialFeed fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_Last(classNameId, classPK, orderByComparator);
	}

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
	public static com.liferay.share.model.EntitySocialFeed[] findByC_C_PrevAndNext(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK,
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException {
		return getPersistence()
				   .findByC_C_PrevAndNext(entitySocialFeedPK, classNameId,
			classPK, orderByComparator);
	}

	/**
	* Removes all the entity social feeds where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	*/
	public static void removeByC_C(long classNameId, long classPK) {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	* Returns the number of entity social feeds where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching entity social feeds
	*/
	public static int countByC_C(long classNameId, long classPK) {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	* Returns all the entity social feeds where feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @return the matching entity social feeds
	*/
	public static java.util.List<com.liferay.share.model.EntitySocialFeed> findByF_F(
		long feedClassNameId, long feedClassPK) {
		return getPersistence().findByF_F(feedClassNameId, feedClassPK);
	}

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
	public static java.util.List<com.liferay.share.model.EntitySocialFeed> findByF_F(
		long feedClassNameId, long feedClassPK, int start, int end) {
		return getPersistence()
				   .findByF_F(feedClassNameId, feedClassPK, start, end);
	}

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
	public static java.util.List<com.liferay.share.model.EntitySocialFeed> findByF_F(
		long feedClassNameId, long feedClassPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator) {
		return getPersistence()
				   .findByF_F(feedClassNameId, feedClassPK, start, end,
			orderByComparator);
	}

	/**
	* Returns the first entity social feed in the ordered set where feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entity social feed
	* @throws com.liferay.share.NoSuchEntitySocialFeedException if a matching entity social feed could not be found
	*/
	public static com.liferay.share.model.EntitySocialFeed findByF_F_First(
		long feedClassNameId, long feedClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException {
		return getPersistence()
				   .findByF_F_First(feedClassNameId, feedClassPK,
			orderByComparator);
	}

	/**
	* Returns the first entity social feed in the ordered set where feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entity social feed, or <code>null</code> if a matching entity social feed could not be found
	*/
	public static com.liferay.share.model.EntitySocialFeed fetchByF_F_First(
		long feedClassNameId, long feedClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator) {
		return getPersistence()
				   .fetchByF_F_First(feedClassNameId, feedClassPK,
			orderByComparator);
	}

	/**
	* Returns the last entity social feed in the ordered set where feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entity social feed
	* @throws com.liferay.share.NoSuchEntitySocialFeedException if a matching entity social feed could not be found
	*/
	public static com.liferay.share.model.EntitySocialFeed findByF_F_Last(
		long feedClassNameId, long feedClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException {
		return getPersistence()
				   .findByF_F_Last(feedClassNameId, feedClassPK,
			orderByComparator);
	}

	/**
	* Returns the last entity social feed in the ordered set where feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entity social feed, or <code>null</code> if a matching entity social feed could not be found
	*/
	public static com.liferay.share.model.EntitySocialFeed fetchByF_F_Last(
		long feedClassNameId, long feedClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator) {
		return getPersistence()
				   .fetchByF_F_Last(feedClassNameId, feedClassPK,
			orderByComparator);
	}

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
	public static com.liferay.share.model.EntitySocialFeed[] findByF_F_PrevAndNext(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK,
		long feedClassNameId, long feedClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException {
		return getPersistence()
				   .findByF_F_PrevAndNext(entitySocialFeedPK, feedClassNameId,
			feedClassPK, orderByComparator);
	}

	/**
	* Removes all the entity social feeds where feedClassNameId = &#63; and feedClassPK = &#63; from the database.
	*
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	*/
	public static void removeByF_F(long feedClassNameId, long feedClassPK) {
		getPersistence().removeByF_F(feedClassNameId, feedClassPK);
	}

	/**
	* Returns the number of entity social feeds where feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @return the number of matching entity social feeds
	*/
	public static int countByF_F(long feedClassNameId, long feedClassPK) {
		return getPersistence().countByF_F(feedClassNameId, feedClassPK);
	}

	/**
	* Returns all the entity social feeds where classNameId = &#63; and classPK = &#63; and feedClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param feedClassNameId the feed class name ID
	* @return the matching entity social feeds
	*/
	public static java.util.List<com.liferay.share.model.EntitySocialFeed> findByC_C_F(
		long classNameId, long classPK, long feedClassNameId) {
		return getPersistence()
				   .findByC_C_F(classNameId, classPK, feedClassNameId);
	}

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
	public static java.util.List<com.liferay.share.model.EntitySocialFeed> findByC_C_F(
		long classNameId, long classPK, long feedClassNameId, int start, int end) {
		return getPersistence()
				   .findByC_C_F(classNameId, classPK, feedClassNameId, start,
			end);
	}

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
	public static java.util.List<com.liferay.share.model.EntitySocialFeed> findByC_C_F(
		long classNameId, long classPK, long feedClassNameId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator) {
		return getPersistence()
				   .findByC_C_F(classNameId, classPK, feedClassNameId, start,
			end, orderByComparator);
	}

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
	public static com.liferay.share.model.EntitySocialFeed findByC_C_F_First(
		long classNameId, long classPK, long feedClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException {
		return getPersistence()
				   .findByC_C_F_First(classNameId, classPK, feedClassNameId,
			orderByComparator);
	}

	/**
	* Returns the first entity social feed in the ordered set where classNameId = &#63; and classPK = &#63; and feedClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param feedClassNameId the feed class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entity social feed, or <code>null</code> if a matching entity social feed could not be found
	*/
	public static com.liferay.share.model.EntitySocialFeed fetchByC_C_F_First(
		long classNameId, long classPK, long feedClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_F_First(classNameId, classPK, feedClassNameId,
			orderByComparator);
	}

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
	public static com.liferay.share.model.EntitySocialFeed findByC_C_F_Last(
		long classNameId, long classPK, long feedClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException {
		return getPersistence()
				   .findByC_C_F_Last(classNameId, classPK, feedClassNameId,
			orderByComparator);
	}

	/**
	* Returns the last entity social feed in the ordered set where classNameId = &#63; and classPK = &#63; and feedClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param feedClassNameId the feed class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entity social feed, or <code>null</code> if a matching entity social feed could not be found
	*/
	public static com.liferay.share.model.EntitySocialFeed fetchByC_C_F_Last(
		long classNameId, long classPK, long feedClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_F_Last(classNameId, classPK, feedClassNameId,
			orderByComparator);
	}

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
	public static com.liferay.share.model.EntitySocialFeed[] findByC_C_F_PrevAndNext(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK,
		long classNameId, long classPK, long feedClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException {
		return getPersistence()
				   .findByC_C_F_PrevAndNext(entitySocialFeedPK, classNameId,
			classPK, feedClassNameId, orderByComparator);
	}

	/**
	* Removes all the entity social feeds where classNameId = &#63; and classPK = &#63; and feedClassNameId = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param feedClassNameId the feed class name ID
	*/
	public static void removeByC_C_F(long classNameId, long classPK,
		long feedClassNameId) {
		getPersistence().removeByC_C_F(classNameId, classPK, feedClassNameId);
	}

	/**
	* Returns the number of entity social feeds where classNameId = &#63; and classPK = &#63; and feedClassNameId = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param feedClassNameId the feed class name ID
	* @return the number of matching entity social feeds
	*/
	public static int countByC_C_F(long classNameId, long classPK,
		long feedClassNameId) {
		return getPersistence()
				   .countByC_C_F(classNameId, classPK, feedClassNameId);
	}

	/**
	* Returns all the entity social feeds where classNameId = &#63; and feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @return the matching entity social feeds
	*/
	public static java.util.List<com.liferay.share.model.EntitySocialFeed> findByC_F_F(
		long classNameId, long feedClassNameId, long feedClassPK) {
		return getPersistence()
				   .findByC_F_F(classNameId, feedClassNameId, feedClassPK);
	}

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
	public static java.util.List<com.liferay.share.model.EntitySocialFeed> findByC_F_F(
		long classNameId, long feedClassNameId, long feedClassPK, int start,
		int end) {
		return getPersistence()
				   .findByC_F_F(classNameId, feedClassNameId, feedClassPK,
			start, end);
	}

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
	public static java.util.List<com.liferay.share.model.EntitySocialFeed> findByC_F_F(
		long classNameId, long feedClassNameId, long feedClassPK, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator) {
		return getPersistence()
				   .findByC_F_F(classNameId, feedClassNameId, feedClassPK,
			start, end, orderByComparator);
	}

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
	public static com.liferay.share.model.EntitySocialFeed findByC_F_F_First(
		long classNameId, long feedClassNameId, long feedClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException {
		return getPersistence()
				   .findByC_F_F_First(classNameId, feedClassNameId,
			feedClassPK, orderByComparator);
	}

	/**
	* Returns the first entity social feed in the ordered set where classNameId = &#63; and feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entity social feed, or <code>null</code> if a matching entity social feed could not be found
	*/
	public static com.liferay.share.model.EntitySocialFeed fetchByC_F_F_First(
		long classNameId, long feedClassNameId, long feedClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator) {
		return getPersistence()
				   .fetchByC_F_F_First(classNameId, feedClassNameId,
			feedClassPK, orderByComparator);
	}

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
	public static com.liferay.share.model.EntitySocialFeed findByC_F_F_Last(
		long classNameId, long feedClassNameId, long feedClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException {
		return getPersistence()
				   .findByC_F_F_Last(classNameId, feedClassNameId, feedClassPK,
			orderByComparator);
	}

	/**
	* Returns the last entity social feed in the ordered set where classNameId = &#63; and feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entity social feed, or <code>null</code> if a matching entity social feed could not be found
	*/
	public static com.liferay.share.model.EntitySocialFeed fetchByC_F_F_Last(
		long classNameId, long feedClassNameId, long feedClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator) {
		return getPersistence()
				   .fetchByC_F_F_Last(classNameId, feedClassNameId,
			feedClassPK, orderByComparator);
	}

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
	public static com.liferay.share.model.EntitySocialFeed[] findByC_F_F_PrevAndNext(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK,
		long classNameId, long feedClassNameId, long feedClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator)
		throws com.liferay.share.NoSuchEntitySocialFeedException {
		return getPersistence()
				   .findByC_F_F_PrevAndNext(entitySocialFeedPK, classNameId,
			feedClassNameId, feedClassPK, orderByComparator);
	}

	/**
	* Removes all the entity social feeds where classNameId = &#63; and feedClassNameId = &#63; and feedClassPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	*/
	public static void removeByC_F_F(long classNameId, long feedClassNameId,
		long feedClassPK) {
		getPersistence().removeByC_F_F(classNameId, feedClassNameId, feedClassPK);
	}

	/**
	* Returns the number of entity social feeds where classNameId = &#63; and feedClassNameId = &#63; and feedClassPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param feedClassNameId the feed class name ID
	* @param feedClassPK the feed class p k
	* @return the number of matching entity social feeds
	*/
	public static int countByC_F_F(long classNameId, long feedClassNameId,
		long feedClassPK) {
		return getPersistence()
				   .countByC_F_F(classNameId, feedClassNameId, feedClassPK);
	}

	/**
	* Caches the entity social feed in the entity cache if it is enabled.
	*
	* @param entitySocialFeed the entity social feed
	*/
	public static void cacheResult(
		com.liferay.share.model.EntitySocialFeed entitySocialFeed) {
		getPersistence().cacheResult(entitySocialFeed);
	}

	/**
	* Caches the entity social feeds in the entity cache if it is enabled.
	*
	* @param entitySocialFeeds the entity social feeds
	*/
	public static void cacheResult(
		java.util.List<com.liferay.share.model.EntitySocialFeed> entitySocialFeeds) {
		getPersistence().cacheResult(entitySocialFeeds);
	}

	/**
	* Creates a new entity social feed with the primary key. Does not add the entity social feed to the database.
	*
	* @param entitySocialFeedPK the primary key for the new entity social feed
	* @return the new entity social feed
	*/
	public static com.liferay.share.model.EntitySocialFeed create(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK) {
		return getPersistence().create(entitySocialFeedPK);
	}

	/**
	* Removes the entity social feed with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entitySocialFeedPK the primary key of the entity social feed
	* @return the entity social feed that was removed
	* @throws com.liferay.share.NoSuchEntitySocialFeedException if a entity social feed with the primary key could not be found
	*/
	public static com.liferay.share.model.EntitySocialFeed remove(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK)
		throws com.liferay.share.NoSuchEntitySocialFeedException {
		return getPersistence().remove(entitySocialFeedPK);
	}

	public static com.liferay.share.model.EntitySocialFeed updateImpl(
		com.liferay.share.model.EntitySocialFeed entitySocialFeed) {
		return getPersistence().updateImpl(entitySocialFeed);
	}

	/**
	* Returns the entity social feed with the primary key or throws a {@link com.liferay.share.NoSuchEntitySocialFeedException} if it could not be found.
	*
	* @param entitySocialFeedPK the primary key of the entity social feed
	* @return the entity social feed
	* @throws com.liferay.share.NoSuchEntitySocialFeedException if a entity social feed with the primary key could not be found
	*/
	public static com.liferay.share.model.EntitySocialFeed findByPrimaryKey(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK)
		throws com.liferay.share.NoSuchEntitySocialFeedException {
		return getPersistence().findByPrimaryKey(entitySocialFeedPK);
	}

	/**
	* Returns the entity social feed with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entitySocialFeedPK the primary key of the entity social feed
	* @return the entity social feed, or <code>null</code> if a entity social feed with the primary key could not be found
	*/
	public static com.liferay.share.model.EntitySocialFeed fetchByPrimaryKey(
		com.liferay.share.service.persistence.EntitySocialFeedPK entitySocialFeedPK) {
		return getPersistence().fetchByPrimaryKey(entitySocialFeedPK);
	}

	public static java.util.Map<java.io.Serializable, com.liferay.share.model.EntitySocialFeed> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the entity social feeds.
	*
	* @return the entity social feeds
	*/
	public static java.util.List<com.liferay.share.model.EntitySocialFeed> findAll() {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.share.model.EntitySocialFeed> findAll(
		int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.share.model.EntitySocialFeed> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.share.model.EntitySocialFeed> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the entity social feeds from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of entity social feeds.
	*
	* @return the number of entity social feeds
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static EntitySocialFeedPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (EntitySocialFeedPersistence)PortletBeanLocatorUtil.locate(com.liferay.share.service.ClpSerializer.getServletContextName(),
					EntitySocialFeedPersistence.class.getName());

			ReferenceRegistry.registerReference(EntitySocialFeedUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(EntitySocialFeedPersistence persistence) {
	}

	private static EntitySocialFeedPersistence _persistence;
}