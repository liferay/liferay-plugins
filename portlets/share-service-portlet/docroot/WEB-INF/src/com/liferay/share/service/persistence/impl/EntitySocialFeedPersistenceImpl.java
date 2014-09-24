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

package com.liferay.share.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.share.NoSuchEntitySocialFeedException;
import com.liferay.share.model.EntitySocialFeed;
import com.liferay.share.model.impl.EntitySocialFeedImpl;
import com.liferay.share.model.impl.EntitySocialFeedModelImpl;
import com.liferay.share.service.persistence.EntitySocialFeedPK;
import com.liferay.share.service.persistence.EntitySocialFeedPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the entity social feed service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntitySocialFeedPersistence
 * @see EntitySocialFeedUtil
 * @generated
 */
@ProviderType
public class EntitySocialFeedPersistenceImpl extends BasePersistenceImpl<EntitySocialFeed>
	implements EntitySocialFeedPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EntitySocialFeedUtil} to access the entity social feed persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EntitySocialFeedImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
			EntitySocialFeedModelImpl.FINDER_CACHE_ENABLED,
			EntitySocialFeedImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
			EntitySocialFeedModelImpl.FINDER_CACHE_ENABLED,
			EntitySocialFeedImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
			EntitySocialFeedModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C = new FinderPath(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
			EntitySocialFeedModelImpl.FINDER_CACHE_ENABLED,
			EntitySocialFeedImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C = new FinderPath(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
			EntitySocialFeedModelImpl.FINDER_CACHE_ENABLED,
			EntitySocialFeedImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			EntitySocialFeedModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			EntitySocialFeedModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
			EntitySocialFeedModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the entity social feeds where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching entity social feeds
	 */
	@Override
	public List<EntitySocialFeed> findByC_C(long classNameId, long classPK) {
		return findByC_C(classNameId, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<EntitySocialFeed> findByC_C(long classNameId, long classPK,
		int start, int end) {
		return findByC_C(classNameId, classPK, start, end, null);
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
	@Override
	public List<EntitySocialFeed> findByC_C(long classNameId, long classPK,
		int start, int end,
		OrderByComparator<EntitySocialFeed> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C;
			finderArgs = new Object[] { classNameId, classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C;
			finderArgs = new Object[] {
					classNameId, classPK,
					
					start, end, orderByComparator
				};
		}

		List<EntitySocialFeed> list = (List<EntitySocialFeed>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (EntitySocialFeed entitySocialFeed : list) {
				if ((classNameId != entitySocialFeed.getClassNameId()) ||
						(classPK != entitySocialFeed.getClassPK())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ENTITYSOCIALFEED_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntitySocialFeedModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				if (!pagination) {
					list = (List<EntitySocialFeed>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EntitySocialFeed>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public EntitySocialFeed findByC_C_First(long classNameId, long classPK,
		OrderByComparator<EntitySocialFeed> orderByComparator)
		throws NoSuchEntitySocialFeedException {
		EntitySocialFeed entitySocialFeed = fetchByC_C_First(classNameId,
				classPK, orderByComparator);

		if (entitySocialFeed != null) {
			return entitySocialFeed;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntitySocialFeedException(msg.toString());
	}

	/**
	 * Returns the first entity social feed in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entity social feed, or <code>null</code> if a matching entity social feed could not be found
	 */
	@Override
	public EntitySocialFeed fetchByC_C_First(long classNameId, long classPK,
		OrderByComparator<EntitySocialFeed> orderByComparator) {
		List<EntitySocialFeed> list = findByC_C(classNameId, classPK, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public EntitySocialFeed findByC_C_Last(long classNameId, long classPK,
		OrderByComparator<EntitySocialFeed> orderByComparator)
		throws NoSuchEntitySocialFeedException {
		EntitySocialFeed entitySocialFeed = fetchByC_C_Last(classNameId,
				classPK, orderByComparator);

		if (entitySocialFeed != null) {
			return entitySocialFeed;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntitySocialFeedException(msg.toString());
	}

	/**
	 * Returns the last entity social feed in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entity social feed, or <code>null</code> if a matching entity social feed could not be found
	 */
	@Override
	public EntitySocialFeed fetchByC_C_Last(long classNameId, long classPK,
		OrderByComparator<EntitySocialFeed> orderByComparator) {
		int count = countByC_C(classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<EntitySocialFeed> list = findByC_C(classNameId, classPK,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public EntitySocialFeed[] findByC_C_PrevAndNext(
		EntitySocialFeedPK entitySocialFeedPK, long classNameId, long classPK,
		OrderByComparator<EntitySocialFeed> orderByComparator)
		throws NoSuchEntitySocialFeedException {
		EntitySocialFeed entitySocialFeed = findByPrimaryKey(entitySocialFeedPK);

		Session session = null;

		try {
			session = openSession();

			EntitySocialFeed[] array = new EntitySocialFeedImpl[3];

			array[0] = getByC_C_PrevAndNext(session, entitySocialFeed,
					classNameId, classPK, orderByComparator, true);

			array[1] = entitySocialFeed;

			array[2] = getByC_C_PrevAndNext(session, entitySocialFeed,
					classNameId, classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EntitySocialFeed getByC_C_PrevAndNext(Session session,
		EntitySocialFeed entitySocialFeed, long classNameId, long classPK,
		OrderByComparator<EntitySocialFeed> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTITYSOCIALFEED_WHERE);

		query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(EntitySocialFeedModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entitySocialFeed);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EntitySocialFeed> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entity social feeds where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 */
	@Override
	public void removeByC_C(long classNameId, long classPK) {
		for (EntitySocialFeed entitySocialFeed : findByC_C(classNameId,
				classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(entitySocialFeed);
		}
	}

	/**
	 * Returns the number of entity social feeds where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching entity social feeds
	 */
	@Override
	public int countByC_C(long classNameId, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C;

		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ENTITYSOCIALFEED_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "entitySocialFeed.id.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "entitySocialFeed.id.classPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_F = new FinderPath(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
			EntitySocialFeedModelImpl.FINDER_CACHE_ENABLED,
			EntitySocialFeedImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByF_F",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_F = new FinderPath(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
			EntitySocialFeedModelImpl.FINDER_CACHE_ENABLED,
			EntitySocialFeedImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_F",
			new String[] { Long.class.getName(), Long.class.getName() },
			EntitySocialFeedModelImpl.FEEDCLASSNAMEID_COLUMN_BITMASK |
			EntitySocialFeedModelImpl.FEEDCLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_F = new FinderPath(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
			EntitySocialFeedModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_F",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the entity social feeds where feedClassNameId = &#63; and feedClassPK = &#63;.
	 *
	 * @param feedClassNameId the feed class name ID
	 * @param feedClassPK the feed class p k
	 * @return the matching entity social feeds
	 */
	@Override
	public List<EntitySocialFeed> findByF_F(long feedClassNameId,
		long feedClassPK) {
		return findByF_F(feedClassNameId, feedClassPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<EntitySocialFeed> findByF_F(long feedClassNameId,
		long feedClassPK, int start, int end) {
		return findByF_F(feedClassNameId, feedClassPK, start, end, null);
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
	@Override
	public List<EntitySocialFeed> findByF_F(long feedClassNameId,
		long feedClassPK, int start, int end,
		OrderByComparator<EntitySocialFeed> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_F;
			finderArgs = new Object[] { feedClassNameId, feedClassPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_F;
			finderArgs = new Object[] {
					feedClassNameId, feedClassPK,
					
					start, end, orderByComparator
				};
		}

		List<EntitySocialFeed> list = (List<EntitySocialFeed>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (EntitySocialFeed entitySocialFeed : list) {
				if ((feedClassNameId != entitySocialFeed.getFeedClassNameId()) ||
						(feedClassPK != entitySocialFeed.getFeedClassPK())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ENTITYSOCIALFEED_WHERE);

			query.append(_FINDER_COLUMN_F_F_FEEDCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_F_F_FEEDCLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntitySocialFeedModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(feedClassNameId);

				qPos.add(feedClassPK);

				if (!pagination) {
					list = (List<EntitySocialFeed>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EntitySocialFeed>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public EntitySocialFeed findByF_F_First(long feedClassNameId,
		long feedClassPK, OrderByComparator<EntitySocialFeed> orderByComparator)
		throws NoSuchEntitySocialFeedException {
		EntitySocialFeed entitySocialFeed = fetchByF_F_First(feedClassNameId,
				feedClassPK, orderByComparator);

		if (entitySocialFeed != null) {
			return entitySocialFeed;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("feedClassNameId=");
		msg.append(feedClassNameId);

		msg.append(", feedClassPK=");
		msg.append(feedClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntitySocialFeedException(msg.toString());
	}

	/**
	 * Returns the first entity social feed in the ordered set where feedClassNameId = &#63; and feedClassPK = &#63;.
	 *
	 * @param feedClassNameId the feed class name ID
	 * @param feedClassPK the feed class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entity social feed, or <code>null</code> if a matching entity social feed could not be found
	 */
	@Override
	public EntitySocialFeed fetchByF_F_First(long feedClassNameId,
		long feedClassPK, OrderByComparator<EntitySocialFeed> orderByComparator) {
		List<EntitySocialFeed> list = findByF_F(feedClassNameId, feedClassPK,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public EntitySocialFeed findByF_F_Last(long feedClassNameId,
		long feedClassPK, OrderByComparator<EntitySocialFeed> orderByComparator)
		throws NoSuchEntitySocialFeedException {
		EntitySocialFeed entitySocialFeed = fetchByF_F_Last(feedClassNameId,
				feedClassPK, orderByComparator);

		if (entitySocialFeed != null) {
			return entitySocialFeed;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("feedClassNameId=");
		msg.append(feedClassNameId);

		msg.append(", feedClassPK=");
		msg.append(feedClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntitySocialFeedException(msg.toString());
	}

	/**
	 * Returns the last entity social feed in the ordered set where feedClassNameId = &#63; and feedClassPK = &#63;.
	 *
	 * @param feedClassNameId the feed class name ID
	 * @param feedClassPK the feed class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entity social feed, or <code>null</code> if a matching entity social feed could not be found
	 */
	@Override
	public EntitySocialFeed fetchByF_F_Last(long feedClassNameId,
		long feedClassPK, OrderByComparator<EntitySocialFeed> orderByComparator) {
		int count = countByF_F(feedClassNameId, feedClassPK);

		if (count == 0) {
			return null;
		}

		List<EntitySocialFeed> list = findByF_F(feedClassNameId, feedClassPK,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public EntitySocialFeed[] findByF_F_PrevAndNext(
		EntitySocialFeedPK entitySocialFeedPK, long feedClassNameId,
		long feedClassPK, OrderByComparator<EntitySocialFeed> orderByComparator)
		throws NoSuchEntitySocialFeedException {
		EntitySocialFeed entitySocialFeed = findByPrimaryKey(entitySocialFeedPK);

		Session session = null;

		try {
			session = openSession();

			EntitySocialFeed[] array = new EntitySocialFeedImpl[3];

			array[0] = getByF_F_PrevAndNext(session, entitySocialFeed,
					feedClassNameId, feedClassPK, orderByComparator, true);

			array[1] = entitySocialFeed;

			array[2] = getByF_F_PrevAndNext(session, entitySocialFeed,
					feedClassNameId, feedClassPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EntitySocialFeed getByF_F_PrevAndNext(Session session,
		EntitySocialFeed entitySocialFeed, long feedClassNameId,
		long feedClassPK,
		OrderByComparator<EntitySocialFeed> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTITYSOCIALFEED_WHERE);

		query.append(_FINDER_COLUMN_F_F_FEEDCLASSNAMEID_2);

		query.append(_FINDER_COLUMN_F_F_FEEDCLASSPK_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(EntitySocialFeedModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(feedClassNameId);

		qPos.add(feedClassPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entitySocialFeed);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EntitySocialFeed> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entity social feeds where feedClassNameId = &#63; and feedClassPK = &#63; from the database.
	 *
	 * @param feedClassNameId the feed class name ID
	 * @param feedClassPK the feed class p k
	 */
	@Override
	public void removeByF_F(long feedClassNameId, long feedClassPK) {
		for (EntitySocialFeed entitySocialFeed : findByF_F(feedClassNameId,
				feedClassPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(entitySocialFeed);
		}
	}

	/**
	 * Returns the number of entity social feeds where feedClassNameId = &#63; and feedClassPK = &#63;.
	 *
	 * @param feedClassNameId the feed class name ID
	 * @param feedClassPK the feed class p k
	 * @return the number of matching entity social feeds
	 */
	@Override
	public int countByF_F(long feedClassNameId, long feedClassPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_F;

		Object[] finderArgs = new Object[] { feedClassNameId, feedClassPK };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ENTITYSOCIALFEED_WHERE);

			query.append(_FINDER_COLUMN_F_F_FEEDCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_F_F_FEEDCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(feedClassNameId);

				qPos.add(feedClassPK);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_F_F_FEEDCLASSNAMEID_2 = "entitySocialFeed.id.feedClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_F_F_FEEDCLASSPK_2 = "entitySocialFeed.id.feedClassPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_F = new FinderPath(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
			EntitySocialFeedModelImpl.FINDER_CACHE_ENABLED,
			EntitySocialFeedImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_C_F",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_F = new FinderPath(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
			EntitySocialFeedModelImpl.FINDER_CACHE_ENABLED,
			EntitySocialFeedImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_F",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			EntitySocialFeedModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			EntitySocialFeedModelImpl.CLASSPK_COLUMN_BITMASK |
			EntitySocialFeedModelImpl.FEEDCLASSNAMEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_F = new FinderPath(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
			EntitySocialFeedModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_F",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the entity social feeds where classNameId = &#63; and classPK = &#63; and feedClassNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param feedClassNameId the feed class name ID
	 * @return the matching entity social feeds
	 */
	@Override
	public List<EntitySocialFeed> findByC_C_F(long classNameId, long classPK,
		long feedClassNameId) {
		return findByC_C_F(classNameId, classPK, feedClassNameId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<EntitySocialFeed> findByC_C_F(long classNameId, long classPK,
		long feedClassNameId, int start, int end) {
		return findByC_C_F(classNameId, classPK, feedClassNameId, start, end,
			null);
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
	@Override
	public List<EntitySocialFeed> findByC_C_F(long classNameId, long classPK,
		long feedClassNameId, int start, int end,
		OrderByComparator<EntitySocialFeed> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_F;
			finderArgs = new Object[] { classNameId, classPK, feedClassNameId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_F;
			finderArgs = new Object[] {
					classNameId, classPK, feedClassNameId,
					
					start, end, orderByComparator
				};
		}

		List<EntitySocialFeed> list = (List<EntitySocialFeed>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (EntitySocialFeed entitySocialFeed : list) {
				if ((classNameId != entitySocialFeed.getClassNameId()) ||
						(classPK != entitySocialFeed.getClassPK()) ||
						(feedClassNameId != entitySocialFeed.getFeedClassNameId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ENTITYSOCIALFEED_WHERE);

			query.append(_FINDER_COLUMN_C_C_F_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_F_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_F_FEEDCLASSNAMEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntitySocialFeedModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(feedClassNameId);

				if (!pagination) {
					list = (List<EntitySocialFeed>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EntitySocialFeed>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public EntitySocialFeed findByC_C_F_First(long classNameId, long classPK,
		long feedClassNameId,
		OrderByComparator<EntitySocialFeed> orderByComparator)
		throws NoSuchEntitySocialFeedException {
		EntitySocialFeed entitySocialFeed = fetchByC_C_F_First(classNameId,
				classPK, feedClassNameId, orderByComparator);

		if (entitySocialFeed != null) {
			return entitySocialFeed;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", feedClassNameId=");
		msg.append(feedClassNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntitySocialFeedException(msg.toString());
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
	@Override
	public EntitySocialFeed fetchByC_C_F_First(long classNameId, long classPK,
		long feedClassNameId,
		OrderByComparator<EntitySocialFeed> orderByComparator) {
		List<EntitySocialFeed> list = findByC_C_F(classNameId, classPK,
				feedClassNameId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public EntitySocialFeed findByC_C_F_Last(long classNameId, long classPK,
		long feedClassNameId,
		OrderByComparator<EntitySocialFeed> orderByComparator)
		throws NoSuchEntitySocialFeedException {
		EntitySocialFeed entitySocialFeed = fetchByC_C_F_Last(classNameId,
				classPK, feedClassNameId, orderByComparator);

		if (entitySocialFeed != null) {
			return entitySocialFeed;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", feedClassNameId=");
		msg.append(feedClassNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntitySocialFeedException(msg.toString());
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
	@Override
	public EntitySocialFeed fetchByC_C_F_Last(long classNameId, long classPK,
		long feedClassNameId,
		OrderByComparator<EntitySocialFeed> orderByComparator) {
		int count = countByC_C_F(classNameId, classPK, feedClassNameId);

		if (count == 0) {
			return null;
		}

		List<EntitySocialFeed> list = findByC_C_F(classNameId, classPK,
				feedClassNameId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public EntitySocialFeed[] findByC_C_F_PrevAndNext(
		EntitySocialFeedPK entitySocialFeedPK, long classNameId, long classPK,
		long feedClassNameId,
		OrderByComparator<EntitySocialFeed> orderByComparator)
		throws NoSuchEntitySocialFeedException {
		EntitySocialFeed entitySocialFeed = findByPrimaryKey(entitySocialFeedPK);

		Session session = null;

		try {
			session = openSession();

			EntitySocialFeed[] array = new EntitySocialFeedImpl[3];

			array[0] = getByC_C_F_PrevAndNext(session, entitySocialFeed,
					classNameId, classPK, feedClassNameId, orderByComparator,
					true);

			array[1] = entitySocialFeed;

			array[2] = getByC_C_F_PrevAndNext(session, entitySocialFeed,
					classNameId, classPK, feedClassNameId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EntitySocialFeed getByC_C_F_PrevAndNext(Session session,
		EntitySocialFeed entitySocialFeed, long classNameId, long classPK,
		long feedClassNameId,
		OrderByComparator<EntitySocialFeed> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTITYSOCIALFEED_WHERE);

		query.append(_FINDER_COLUMN_C_C_F_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_F_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_F_FEEDCLASSNAMEID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(EntitySocialFeedModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(feedClassNameId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entitySocialFeed);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EntitySocialFeed> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entity social feeds where classNameId = &#63; and classPK = &#63; and feedClassNameId = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param feedClassNameId the feed class name ID
	 */
	@Override
	public void removeByC_C_F(long classNameId, long classPK,
		long feedClassNameId) {
		for (EntitySocialFeed entitySocialFeed : findByC_C_F(classNameId,
				classPK, feedClassNameId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(entitySocialFeed);
		}
	}

	/**
	 * Returns the number of entity social feeds where classNameId = &#63; and classPK = &#63; and feedClassNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param feedClassNameId the feed class name ID
	 * @return the number of matching entity social feeds
	 */
	@Override
	public int countByC_C_F(long classNameId, long classPK, long feedClassNameId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C_F;

		Object[] finderArgs = new Object[] { classNameId, classPK, feedClassNameId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ENTITYSOCIALFEED_WHERE);

			query.append(_FINDER_COLUMN_C_C_F_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_F_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_F_FEEDCLASSNAMEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(feedClassNameId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_C_F_CLASSNAMEID_2 = "entitySocialFeed.id.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_F_CLASSPK_2 = "entitySocialFeed.id.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_F_FEEDCLASSNAMEID_2 = "entitySocialFeed.id.feedClassNameId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_F_F = new FinderPath(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
			EntitySocialFeedModelImpl.FINDER_CACHE_ENABLED,
			EntitySocialFeedImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_F_F",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_F_F = new FinderPath(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
			EntitySocialFeedModelImpl.FINDER_CACHE_ENABLED,
			EntitySocialFeedImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_F_F",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			EntitySocialFeedModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			EntitySocialFeedModelImpl.FEEDCLASSNAMEID_COLUMN_BITMASK |
			EntitySocialFeedModelImpl.FEEDCLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_F_F = new FinderPath(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
			EntitySocialFeedModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_F_F",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the entity social feeds where classNameId = &#63; and feedClassNameId = &#63; and feedClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param feedClassNameId the feed class name ID
	 * @param feedClassPK the feed class p k
	 * @return the matching entity social feeds
	 */
	@Override
	public List<EntitySocialFeed> findByC_F_F(long classNameId,
		long feedClassNameId, long feedClassPK) {
		return findByC_F_F(classNameId, feedClassNameId, feedClassPK,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<EntitySocialFeed> findByC_F_F(long classNameId,
		long feedClassNameId, long feedClassPK, int start, int end) {
		return findByC_F_F(classNameId, feedClassNameId, feedClassPK, start,
			end, null);
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
	@Override
	public List<EntitySocialFeed> findByC_F_F(long classNameId,
		long feedClassNameId, long feedClassPK, int start, int end,
		OrderByComparator<EntitySocialFeed> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_F_F;
			finderArgs = new Object[] { classNameId, feedClassNameId, feedClassPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_F_F;
			finderArgs = new Object[] {
					classNameId, feedClassNameId, feedClassPK,
					
					start, end, orderByComparator
				};
		}

		List<EntitySocialFeed> list = (List<EntitySocialFeed>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (EntitySocialFeed entitySocialFeed : list) {
				if ((classNameId != entitySocialFeed.getClassNameId()) ||
						(feedClassNameId != entitySocialFeed.getFeedClassNameId()) ||
						(feedClassPK != entitySocialFeed.getFeedClassPK())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ENTITYSOCIALFEED_WHERE);

			query.append(_FINDER_COLUMN_C_F_F_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_F_F_FEEDCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_F_F_FEEDCLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntitySocialFeedModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(feedClassNameId);

				qPos.add(feedClassPK);

				if (!pagination) {
					list = (List<EntitySocialFeed>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EntitySocialFeed>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public EntitySocialFeed findByC_F_F_First(long classNameId,
		long feedClassNameId, long feedClassPK,
		OrderByComparator<EntitySocialFeed> orderByComparator)
		throws NoSuchEntitySocialFeedException {
		EntitySocialFeed entitySocialFeed = fetchByC_F_F_First(classNameId,
				feedClassNameId, feedClassPK, orderByComparator);

		if (entitySocialFeed != null) {
			return entitySocialFeed;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", feedClassNameId=");
		msg.append(feedClassNameId);

		msg.append(", feedClassPK=");
		msg.append(feedClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntitySocialFeedException(msg.toString());
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
	@Override
	public EntitySocialFeed fetchByC_F_F_First(long classNameId,
		long feedClassNameId, long feedClassPK,
		OrderByComparator<EntitySocialFeed> orderByComparator) {
		List<EntitySocialFeed> list = findByC_F_F(classNameId, feedClassNameId,
				feedClassPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public EntitySocialFeed findByC_F_F_Last(long classNameId,
		long feedClassNameId, long feedClassPK,
		OrderByComparator<EntitySocialFeed> orderByComparator)
		throws NoSuchEntitySocialFeedException {
		EntitySocialFeed entitySocialFeed = fetchByC_F_F_Last(classNameId,
				feedClassNameId, feedClassPK, orderByComparator);

		if (entitySocialFeed != null) {
			return entitySocialFeed;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", feedClassNameId=");
		msg.append(feedClassNameId);

		msg.append(", feedClassPK=");
		msg.append(feedClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntitySocialFeedException(msg.toString());
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
	@Override
	public EntitySocialFeed fetchByC_F_F_Last(long classNameId,
		long feedClassNameId, long feedClassPK,
		OrderByComparator<EntitySocialFeed> orderByComparator) {
		int count = countByC_F_F(classNameId, feedClassNameId, feedClassPK);

		if (count == 0) {
			return null;
		}

		List<EntitySocialFeed> list = findByC_F_F(classNameId, feedClassNameId,
				feedClassPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public EntitySocialFeed[] findByC_F_F_PrevAndNext(
		EntitySocialFeedPK entitySocialFeedPK, long classNameId,
		long feedClassNameId, long feedClassPK,
		OrderByComparator<EntitySocialFeed> orderByComparator)
		throws NoSuchEntitySocialFeedException {
		EntitySocialFeed entitySocialFeed = findByPrimaryKey(entitySocialFeedPK);

		Session session = null;

		try {
			session = openSession();

			EntitySocialFeed[] array = new EntitySocialFeedImpl[3];

			array[0] = getByC_F_F_PrevAndNext(session, entitySocialFeed,
					classNameId, feedClassNameId, feedClassPK,
					orderByComparator, true);

			array[1] = entitySocialFeed;

			array[2] = getByC_F_F_PrevAndNext(session, entitySocialFeed,
					classNameId, feedClassNameId, feedClassPK,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EntitySocialFeed getByC_F_F_PrevAndNext(Session session,
		EntitySocialFeed entitySocialFeed, long classNameId,
		long feedClassNameId, long feedClassPK,
		OrderByComparator<EntitySocialFeed> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTITYSOCIALFEED_WHERE);

		query.append(_FINDER_COLUMN_C_F_F_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_F_F_FEEDCLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_F_F_FEEDCLASSPK_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(EntitySocialFeedModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(feedClassNameId);

		qPos.add(feedClassPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entitySocialFeed);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EntitySocialFeed> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entity social feeds where classNameId = &#63; and feedClassNameId = &#63; and feedClassPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param feedClassNameId the feed class name ID
	 * @param feedClassPK the feed class p k
	 */
	@Override
	public void removeByC_F_F(long classNameId, long feedClassNameId,
		long feedClassPK) {
		for (EntitySocialFeed entitySocialFeed : findByC_F_F(classNameId,
				feedClassNameId, feedClassPK, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(entitySocialFeed);
		}
	}

	/**
	 * Returns the number of entity social feeds where classNameId = &#63; and feedClassNameId = &#63; and feedClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param feedClassNameId the feed class name ID
	 * @param feedClassPK the feed class p k
	 * @return the number of matching entity social feeds
	 */
	@Override
	public int countByC_F_F(long classNameId, long feedClassNameId,
		long feedClassPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_F_F;

		Object[] finderArgs = new Object[] {
				classNameId, feedClassNameId, feedClassPK
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ENTITYSOCIALFEED_WHERE);

			query.append(_FINDER_COLUMN_C_F_F_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_F_F_FEEDCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_F_F_FEEDCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(feedClassNameId);

				qPos.add(feedClassPK);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_F_F_CLASSNAMEID_2 = "entitySocialFeed.id.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_F_F_FEEDCLASSNAMEID_2 = "entitySocialFeed.id.feedClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_F_F_FEEDCLASSPK_2 = "entitySocialFeed.id.feedClassPK = ?";

	public EntitySocialFeedPersistenceImpl() {
		setModelClass(EntitySocialFeed.class);
	}

	/**
	 * Caches the entity social feed in the entity cache if it is enabled.
	 *
	 * @param entitySocialFeed the entity social feed
	 */
	@Override
	public void cacheResult(EntitySocialFeed entitySocialFeed) {
		EntityCacheUtil.putResult(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
			EntitySocialFeedImpl.class, entitySocialFeed.getPrimaryKey(),
			entitySocialFeed);

		entitySocialFeed.resetOriginalValues();
	}

	/**
	 * Caches the entity social feeds in the entity cache if it is enabled.
	 *
	 * @param entitySocialFeeds the entity social feeds
	 */
	@Override
	public void cacheResult(List<EntitySocialFeed> entitySocialFeeds) {
		for (EntitySocialFeed entitySocialFeed : entitySocialFeeds) {
			if (EntityCacheUtil.getResult(
						EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
						EntitySocialFeedImpl.class,
						entitySocialFeed.getPrimaryKey()) == null) {
				cacheResult(entitySocialFeed);
			}
			else {
				entitySocialFeed.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all entity social feeds.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(EntitySocialFeedImpl.class.getName());
		}

		EntityCacheUtil.clearCache(EntitySocialFeedImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the entity social feed.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EntitySocialFeed entitySocialFeed) {
		EntityCacheUtil.removeResult(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
			EntitySocialFeedImpl.class, entitySocialFeed.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<EntitySocialFeed> entitySocialFeeds) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (EntitySocialFeed entitySocialFeed : entitySocialFeeds) {
			EntityCacheUtil.removeResult(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
				EntitySocialFeedImpl.class, entitySocialFeed.getPrimaryKey());
		}
	}

	/**
	 * Creates a new entity social feed with the primary key. Does not add the entity social feed to the database.
	 *
	 * @param entitySocialFeedPK the primary key for the new entity social feed
	 * @return the new entity social feed
	 */
	@Override
	public EntitySocialFeed create(EntitySocialFeedPK entitySocialFeedPK) {
		EntitySocialFeed entitySocialFeed = new EntitySocialFeedImpl();

		entitySocialFeed.setNew(true);
		entitySocialFeed.setPrimaryKey(entitySocialFeedPK);

		return entitySocialFeed;
	}

	/**
	 * Removes the entity social feed with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entitySocialFeedPK the primary key of the entity social feed
	 * @return the entity social feed that was removed
	 * @throws com.liferay.share.NoSuchEntitySocialFeedException if a entity social feed with the primary key could not be found
	 */
	@Override
	public EntitySocialFeed remove(EntitySocialFeedPK entitySocialFeedPK)
		throws NoSuchEntitySocialFeedException {
		return remove((Serializable)entitySocialFeedPK);
	}

	/**
	 * Removes the entity social feed with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the entity social feed
	 * @return the entity social feed that was removed
	 * @throws com.liferay.share.NoSuchEntitySocialFeedException if a entity social feed with the primary key could not be found
	 */
	@Override
	public EntitySocialFeed remove(Serializable primaryKey)
		throws NoSuchEntitySocialFeedException {
		Session session = null;

		try {
			session = openSession();

			EntitySocialFeed entitySocialFeed = (EntitySocialFeed)session.get(EntitySocialFeedImpl.class,
					primaryKey);

			if (entitySocialFeed == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntitySocialFeedException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(entitySocialFeed);
		}
		catch (NoSuchEntitySocialFeedException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected EntitySocialFeed removeImpl(EntitySocialFeed entitySocialFeed) {
		entitySocialFeed = toUnwrappedModel(entitySocialFeed);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(entitySocialFeed)) {
				entitySocialFeed = (EntitySocialFeed)session.get(EntitySocialFeedImpl.class,
						entitySocialFeed.getPrimaryKeyObj());
			}

			if (entitySocialFeed != null) {
				session.delete(entitySocialFeed);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (entitySocialFeed != null) {
			clearCache(entitySocialFeed);
		}

		return entitySocialFeed;
	}

	@Override
	public EntitySocialFeed updateImpl(
		com.liferay.share.model.EntitySocialFeed entitySocialFeed) {
		entitySocialFeed = toUnwrappedModel(entitySocialFeed);

		boolean isNew = entitySocialFeed.isNew();

		EntitySocialFeedModelImpl entitySocialFeedModelImpl = (EntitySocialFeedModelImpl)entitySocialFeed;

		Session session = null;

		try {
			session = openSession();

			if (entitySocialFeed.isNew()) {
				session.save(entitySocialFeed);

				entitySocialFeed.setNew(false);
			}
			else {
				session.merge(entitySocialFeed);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !EntitySocialFeedModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((entitySocialFeedModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						entitySocialFeedModelImpl.getOriginalClassNameId(),
						entitySocialFeedModelImpl.getOriginalClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);

				args = new Object[] {
						entitySocialFeedModelImpl.getClassNameId(),
						entitySocialFeedModelImpl.getClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C,
					args);
			}

			if ((entitySocialFeedModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_F.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						entitySocialFeedModelImpl.getOriginalFeedClassNameId(),
						entitySocialFeedModelImpl.getOriginalFeedClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_F_F, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_F,
					args);

				args = new Object[] {
						entitySocialFeedModelImpl.getFeedClassNameId(),
						entitySocialFeedModelImpl.getFeedClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_F_F, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_F,
					args);
			}

			if ((entitySocialFeedModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_F.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						entitySocialFeedModelImpl.getOriginalClassNameId(),
						entitySocialFeedModelImpl.getOriginalClassPK(),
						entitySocialFeedModelImpl.getOriginalFeedClassNameId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_F, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_F,
					args);

				args = new Object[] {
						entitySocialFeedModelImpl.getClassNameId(),
						entitySocialFeedModelImpl.getClassPK(),
						entitySocialFeedModelImpl.getFeedClassNameId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_F, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_F,
					args);
			}

			if ((entitySocialFeedModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_F_F.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						entitySocialFeedModelImpl.getOriginalClassNameId(),
						entitySocialFeedModelImpl.getOriginalFeedClassNameId(),
						entitySocialFeedModelImpl.getOriginalFeedClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_F_F, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_F_F,
					args);

				args = new Object[] {
						entitySocialFeedModelImpl.getClassNameId(),
						entitySocialFeedModelImpl.getFeedClassNameId(),
						entitySocialFeedModelImpl.getFeedClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_F_F, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_F_F,
					args);
			}
		}

		EntityCacheUtil.putResult(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
			EntitySocialFeedImpl.class, entitySocialFeed.getPrimaryKey(),
			entitySocialFeed, false);

		entitySocialFeed.resetOriginalValues();

		return entitySocialFeed;
	}

	protected EntitySocialFeed toUnwrappedModel(
		EntitySocialFeed entitySocialFeed) {
		if (entitySocialFeed instanceof EntitySocialFeedImpl) {
			return entitySocialFeed;
		}

		EntitySocialFeedImpl entitySocialFeedImpl = new EntitySocialFeedImpl();

		entitySocialFeedImpl.setNew(entitySocialFeed.isNew());
		entitySocialFeedImpl.setPrimaryKey(entitySocialFeed.getPrimaryKey());

		entitySocialFeedImpl.setClassNameId(entitySocialFeed.getClassNameId());
		entitySocialFeedImpl.setClassPK(entitySocialFeed.getClassPK());
		entitySocialFeedImpl.setFeedClassNameId(entitySocialFeed.getFeedClassNameId());
		entitySocialFeedImpl.setFeedClassPK(entitySocialFeed.getFeedClassPK());

		return entitySocialFeedImpl;
	}

	/**
	 * Returns the entity social feed with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the entity social feed
	 * @return the entity social feed
	 * @throws com.liferay.share.NoSuchEntitySocialFeedException if a entity social feed with the primary key could not be found
	 */
	@Override
	public EntitySocialFeed findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntitySocialFeedException {
		EntitySocialFeed entitySocialFeed = fetchByPrimaryKey(primaryKey);

		if (entitySocialFeed == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntitySocialFeedException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return entitySocialFeed;
	}

	/**
	 * Returns the entity social feed with the primary key or throws a {@link com.liferay.share.NoSuchEntitySocialFeedException} if it could not be found.
	 *
	 * @param entitySocialFeedPK the primary key of the entity social feed
	 * @return the entity social feed
	 * @throws com.liferay.share.NoSuchEntitySocialFeedException if a entity social feed with the primary key could not be found
	 */
	@Override
	public EntitySocialFeed findByPrimaryKey(
		EntitySocialFeedPK entitySocialFeedPK)
		throws NoSuchEntitySocialFeedException {
		return findByPrimaryKey((Serializable)entitySocialFeedPK);
	}

	/**
	 * Returns the entity social feed with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the entity social feed
	 * @return the entity social feed, or <code>null</code> if a entity social feed with the primary key could not be found
	 */
	@Override
	public EntitySocialFeed fetchByPrimaryKey(Serializable primaryKey) {
		EntitySocialFeed entitySocialFeed = (EntitySocialFeed)EntityCacheUtil.getResult(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
				EntitySocialFeedImpl.class, primaryKey);

		if (entitySocialFeed == _nullEntitySocialFeed) {
			return null;
		}

		if (entitySocialFeed == null) {
			Session session = null;

			try {
				session = openSession();

				entitySocialFeed = (EntitySocialFeed)session.get(EntitySocialFeedImpl.class,
						primaryKey);

				if (entitySocialFeed != null) {
					cacheResult(entitySocialFeed);
				}
				else {
					EntityCacheUtil.putResult(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
						EntitySocialFeedImpl.class, primaryKey,
						_nullEntitySocialFeed);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(EntitySocialFeedModelImpl.ENTITY_CACHE_ENABLED,
					EntitySocialFeedImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return entitySocialFeed;
	}

	/**
	 * Returns the entity social feed with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entitySocialFeedPK the primary key of the entity social feed
	 * @return the entity social feed, or <code>null</code> if a entity social feed with the primary key could not be found
	 */
	@Override
	public EntitySocialFeed fetchByPrimaryKey(
		EntitySocialFeedPK entitySocialFeedPK) {
		return fetchByPrimaryKey((Serializable)entitySocialFeedPK);
	}

	@Override
	public Map<Serializable, EntitySocialFeed> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, EntitySocialFeed> map = new HashMap<Serializable, EntitySocialFeed>();

		for (Serializable primaryKey : primaryKeys) {
			EntitySocialFeed entitySocialFeed = fetchByPrimaryKey(primaryKey);

			if (entitySocialFeed != null) {
				map.put(primaryKey, entitySocialFeed);
			}
		}

		return map;
	}

	/**
	 * Returns all the entity social feeds.
	 *
	 * @return the entity social feeds
	 */
	@Override
	public List<EntitySocialFeed> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<EntitySocialFeed> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<EntitySocialFeed> findAll(int start, int end,
		OrderByComparator<EntitySocialFeed> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<EntitySocialFeed> list = (List<EntitySocialFeed>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ENTITYSOCIALFEED);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ENTITYSOCIALFEED;

				if (pagination) {
					sql = sql.concat(EntitySocialFeedModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<EntitySocialFeed>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EntitySocialFeed>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the entity social feeds from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (EntitySocialFeed entitySocialFeed : findAll()) {
			remove(entitySocialFeed);
		}
	}

	/**
	 * Returns the number of entity social feeds.
	 *
	 * @return the number of entity social feeds
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ENTITYSOCIALFEED);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the entity social feed persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(EntitySocialFeedImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_ENTITYSOCIALFEED = "SELECT entitySocialFeed FROM EntitySocialFeed entitySocialFeed";
	private static final String _SQL_SELECT_ENTITYSOCIALFEED_WHERE = "SELECT entitySocialFeed FROM EntitySocialFeed entitySocialFeed WHERE ";
	private static final String _SQL_COUNT_ENTITYSOCIALFEED = "SELECT COUNT(entitySocialFeed) FROM EntitySocialFeed entitySocialFeed";
	private static final String _SQL_COUNT_ENTITYSOCIALFEED_WHERE = "SELECT COUNT(entitySocialFeed) FROM EntitySocialFeed entitySocialFeed WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "entitySocialFeed.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No EntitySocialFeed exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No EntitySocialFeed exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static final Log _log = LogFactoryUtil.getLog(EntitySocialFeedPersistenceImpl.class);
	private static final EntitySocialFeed _nullEntitySocialFeed = new EntitySocialFeedImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<EntitySocialFeed> toCacheModel() {
				return _nullEntitySocialFeedCacheModel;
			}
		};

	private static final CacheModel<EntitySocialFeed> _nullEntitySocialFeedCacheModel =
		new CacheModel<EntitySocialFeed>() {
			@Override
			public EntitySocialFeed toEntityModel() {
				return _nullEntitySocialFeed;
			}
		};
}