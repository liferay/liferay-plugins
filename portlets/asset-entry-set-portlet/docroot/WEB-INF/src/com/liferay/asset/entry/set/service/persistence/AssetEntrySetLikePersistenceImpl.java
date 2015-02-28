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

package com.liferay.asset.entry.set.service.persistence;

import com.liferay.asset.entry.set.NoSuchLikeException;
import com.liferay.asset.entry.set.model.AssetEntrySetLike;
import com.liferay.asset.entry.set.model.impl.AssetEntrySetLikeImpl;
import com.liferay.asset.entry.set.model.impl.AssetEntrySetLikeModelImpl;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the asset entry set like service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetLikePersistence
 * @see AssetEntrySetLikeUtil
 * @generated
 */
public class AssetEntrySetLikePersistenceImpl extends BasePersistenceImpl<AssetEntrySetLike>
	implements AssetEntrySetLikePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetEntrySetLikeUtil} to access the asset entry set like persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetEntrySetLikeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetEntrySetLikeModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetLikeModelImpl.FINDER_CACHE_ENABLED,
			AssetEntrySetLikeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetEntrySetLikeModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetLikeModelImpl.FINDER_CACHE_ENABLED,
			AssetEntrySetLikeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetEntrySetLikeModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetLikeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETENTRYSETID =
		new FinderPath(AssetEntrySetLikeModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetLikeModelImpl.FINDER_CACHE_ENABLED,
			AssetEntrySetLikeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAssetEntrySetId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYSETID =
		new FinderPath(AssetEntrySetLikeModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetLikeModelImpl.FINDER_CACHE_ENABLED,
			AssetEntrySetLikeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAssetEntrySetId",
			new String[] { Long.class.getName() },
			AssetEntrySetLikeModelImpl.ASSETENTRYSETID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSETENTRYSETID = new FinderPath(AssetEntrySetLikeModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetLikeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByAssetEntrySetId", new String[] { Long.class.getName() });

	/**
	 * Returns all the asset entry set likes where assetEntrySetId = &#63;.
	 *
	 * @param assetEntrySetId the asset entry set ID
	 * @return the matching asset entry set likes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySetLike> findByAssetEntrySetId(long assetEntrySetId)
		throws SystemException {
		return findByAssetEntrySetId(assetEntrySetId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset entry set likes where assetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetLikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetEntrySetId the asset entry set ID
	 * @param start the lower bound of the range of asset entry set likes
	 * @param end the upper bound of the range of asset entry set likes (not inclusive)
	 * @return the range of matching asset entry set likes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySetLike> findByAssetEntrySetId(long assetEntrySetId,
		int start, int end) throws SystemException {
		return findByAssetEntrySetId(assetEntrySetId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset entry set likes where assetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetLikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetEntrySetId the asset entry set ID
	 * @param start the lower bound of the range of asset entry set likes
	 * @param end the upper bound of the range of asset entry set likes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry set likes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySetLike> findByAssetEntrySetId(long assetEntrySetId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYSETID;
			finderArgs = new Object[] { assetEntrySetId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETENTRYSETID;
			finderArgs = new Object[] {
					assetEntrySetId,
					
					start, end, orderByComparator
				};
		}

		List<AssetEntrySetLike> list = (List<AssetEntrySetLike>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetEntrySetLike assetEntrySetLike : list) {
				if ((assetEntrySetId != assetEntrySetLike.getAssetEntrySetId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ASSETENTRYSETLIKE_WHERE);

			query.append(_FINDER_COLUMN_ASSETENTRYSETID_ASSETENTRYSETID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetEntrySetLikeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetEntrySetId);

				if (!pagination) {
					list = (List<AssetEntrySetLike>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<AssetEntrySetLike>(list);
				}
				else {
					list = (List<AssetEntrySetLike>)QueryUtil.list(q,
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
	 * Returns the first asset entry set like in the ordered set where assetEntrySetId = &#63;.
	 *
	 * @param assetEntrySetId the asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set like
	 * @throws com.liferay.asset.entry.set.NoSuchLikeException if a matching asset entry set like could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySetLike findByAssetEntrySetId_First(long assetEntrySetId,
		OrderByComparator orderByComparator)
		throws NoSuchLikeException, SystemException {
		AssetEntrySetLike assetEntrySetLike = fetchByAssetEntrySetId_First(assetEntrySetId,
				orderByComparator);

		if (assetEntrySetLike != null) {
			return assetEntrySetLike;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetEntrySetId=");
		msg.append(assetEntrySetId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLikeException(msg.toString());
	}

	/**
	 * Returns the first asset entry set like in the ordered set where assetEntrySetId = &#63;.
	 *
	 * @param assetEntrySetId the asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set like, or <code>null</code> if a matching asset entry set like could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySetLike fetchByAssetEntrySetId_First(
		long assetEntrySetId, OrderByComparator orderByComparator)
		throws SystemException {
		List<AssetEntrySetLike> list = findByAssetEntrySetId(assetEntrySetId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset entry set like in the ordered set where assetEntrySetId = &#63;.
	 *
	 * @param assetEntrySetId the asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set like
	 * @throws com.liferay.asset.entry.set.NoSuchLikeException if a matching asset entry set like could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySetLike findByAssetEntrySetId_Last(long assetEntrySetId,
		OrderByComparator orderByComparator)
		throws NoSuchLikeException, SystemException {
		AssetEntrySetLike assetEntrySetLike = fetchByAssetEntrySetId_Last(assetEntrySetId,
				orderByComparator);

		if (assetEntrySetLike != null) {
			return assetEntrySetLike;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetEntrySetId=");
		msg.append(assetEntrySetId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLikeException(msg.toString());
	}

	/**
	 * Returns the last asset entry set like in the ordered set where assetEntrySetId = &#63;.
	 *
	 * @param assetEntrySetId the asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set like, or <code>null</code> if a matching asset entry set like could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySetLike fetchByAssetEntrySetId_Last(long assetEntrySetId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAssetEntrySetId(assetEntrySetId);

		if (count == 0) {
			return null;
		}

		List<AssetEntrySetLike> list = findByAssetEntrySetId(assetEntrySetId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset entry set likes before and after the current asset entry set like in the ordered set where assetEntrySetId = &#63;.
	 *
	 * @param assetEntrySetLikePK the primary key of the current asset entry set like
	 * @param assetEntrySetId the asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry set like
	 * @throws com.liferay.asset.entry.set.NoSuchLikeException if a asset entry set like with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySetLike[] findByAssetEntrySetId_PrevAndNext(
		AssetEntrySetLikePK assetEntrySetLikePK, long assetEntrySetId,
		OrderByComparator orderByComparator)
		throws NoSuchLikeException, SystemException {
		AssetEntrySetLike assetEntrySetLike = findByPrimaryKey(assetEntrySetLikePK);

		Session session = null;

		try {
			session = openSession();

			AssetEntrySetLike[] array = new AssetEntrySetLikeImpl[3];

			array[0] = getByAssetEntrySetId_PrevAndNext(session,
					assetEntrySetLike, assetEntrySetId, orderByComparator, true);

			array[1] = assetEntrySetLike;

			array[2] = getByAssetEntrySetId_PrevAndNext(session,
					assetEntrySetLike, assetEntrySetId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetEntrySetLike getByAssetEntrySetId_PrevAndNext(
		Session session, AssetEntrySetLike assetEntrySetLike,
		long assetEntrySetId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETENTRYSETLIKE_WHERE);

		query.append(_FINDER_COLUMN_ASSETENTRYSETID_ASSETENTRYSETID_2);

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
			query.append(AssetEntrySetLikeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(assetEntrySetId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetEntrySetLike);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetEntrySetLike> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset entry set likes where assetEntrySetId = &#63; from the database.
	 *
	 * @param assetEntrySetId the asset entry set ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByAssetEntrySetId(long assetEntrySetId)
		throws SystemException {
		for (AssetEntrySetLike assetEntrySetLike : findByAssetEntrySetId(
				assetEntrySetId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(assetEntrySetLike);
		}
	}

	/**
	 * Returns the number of asset entry set likes where assetEntrySetId = &#63;.
	 *
	 * @param assetEntrySetId the asset entry set ID
	 * @return the number of matching asset entry set likes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByAssetEntrySetId(long assetEntrySetId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ASSETENTRYSETID;

		Object[] finderArgs = new Object[] { assetEntrySetId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETENTRYSETLIKE_WHERE);

			query.append(_FINDER_COLUMN_ASSETENTRYSETID_ASSETENTRYSETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetEntrySetId);

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

	private static final String _FINDER_COLUMN_ASSETENTRYSETID_ASSETENTRYSETID_2 =
		"assetEntrySetLike.id.assetEntrySetId = ?";

	public AssetEntrySetLikePersistenceImpl() {
		setModelClass(AssetEntrySetLike.class);
	}

	/**
	 * Caches the asset entry set like in the entity cache if it is enabled.
	 *
	 * @param assetEntrySetLike the asset entry set like
	 */
	@Override
	public void cacheResult(AssetEntrySetLike assetEntrySetLike) {
		EntityCacheUtil.putResult(AssetEntrySetLikeModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetLikeImpl.class, assetEntrySetLike.getPrimaryKey(),
			assetEntrySetLike);

		assetEntrySetLike.resetOriginalValues();
	}

	/**
	 * Caches the asset entry set likes in the entity cache if it is enabled.
	 *
	 * @param assetEntrySetLikes the asset entry set likes
	 */
	@Override
	public void cacheResult(List<AssetEntrySetLike> assetEntrySetLikes) {
		for (AssetEntrySetLike assetEntrySetLike : assetEntrySetLikes) {
			if (EntityCacheUtil.getResult(
						AssetEntrySetLikeModelImpl.ENTITY_CACHE_ENABLED,
						AssetEntrySetLikeImpl.class,
						assetEntrySetLike.getPrimaryKey()) == null) {
				cacheResult(assetEntrySetLike);
			}
			else {
				assetEntrySetLike.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset entry set likes.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AssetEntrySetLikeImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AssetEntrySetLikeImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset entry set like.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetEntrySetLike assetEntrySetLike) {
		EntityCacheUtil.removeResult(AssetEntrySetLikeModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetLikeImpl.class, assetEntrySetLike.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AssetEntrySetLike> assetEntrySetLikes) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetEntrySetLike assetEntrySetLike : assetEntrySetLikes) {
			EntityCacheUtil.removeResult(AssetEntrySetLikeModelImpl.ENTITY_CACHE_ENABLED,
				AssetEntrySetLikeImpl.class, assetEntrySetLike.getPrimaryKey());
		}
	}

	/**
	 * Creates a new asset entry set like with the primary key. Does not add the asset entry set like to the database.
	 *
	 * @param assetEntrySetLikePK the primary key for the new asset entry set like
	 * @return the new asset entry set like
	 */
	@Override
	public AssetEntrySetLike create(AssetEntrySetLikePK assetEntrySetLikePK) {
		AssetEntrySetLike assetEntrySetLike = new AssetEntrySetLikeImpl();

		assetEntrySetLike.setNew(true);
		assetEntrySetLike.setPrimaryKey(assetEntrySetLikePK);

		return assetEntrySetLike;
	}

	/**
	 * Removes the asset entry set like with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntrySetLikePK the primary key of the asset entry set like
	 * @return the asset entry set like that was removed
	 * @throws com.liferay.asset.entry.set.NoSuchLikeException if a asset entry set like with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySetLike remove(AssetEntrySetLikePK assetEntrySetLikePK)
		throws NoSuchLikeException, SystemException {
		return remove((Serializable)assetEntrySetLikePK);
	}

	/**
	 * Removes the asset entry set like with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset entry set like
	 * @return the asset entry set like that was removed
	 * @throws com.liferay.asset.entry.set.NoSuchLikeException if a asset entry set like with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySetLike remove(Serializable primaryKey)
		throws NoSuchLikeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssetEntrySetLike assetEntrySetLike = (AssetEntrySetLike)session.get(AssetEntrySetLikeImpl.class,
					primaryKey);

			if (assetEntrySetLike == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLikeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetEntrySetLike);
		}
		catch (NoSuchLikeException nsee) {
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
	protected AssetEntrySetLike removeImpl(AssetEntrySetLike assetEntrySetLike)
		throws SystemException {
		assetEntrySetLike = toUnwrappedModel(assetEntrySetLike);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(assetEntrySetLike)) {
				assetEntrySetLike = (AssetEntrySetLike)session.get(AssetEntrySetLikeImpl.class,
						assetEntrySetLike.getPrimaryKeyObj());
			}

			if (assetEntrySetLike != null) {
				session.delete(assetEntrySetLike);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (assetEntrySetLike != null) {
			clearCache(assetEntrySetLike);
		}

		return assetEntrySetLike;
	}

	@Override
	public AssetEntrySetLike updateImpl(
		com.liferay.asset.entry.set.model.AssetEntrySetLike assetEntrySetLike)
		throws SystemException {
		assetEntrySetLike = toUnwrappedModel(assetEntrySetLike);

		boolean isNew = assetEntrySetLike.isNew();

		AssetEntrySetLikeModelImpl assetEntrySetLikeModelImpl = (AssetEntrySetLikeModelImpl)assetEntrySetLike;

		Session session = null;

		try {
			session = openSession();

			if (assetEntrySetLike.isNew()) {
				session.save(assetEntrySetLike);

				assetEntrySetLike.setNew(false);
			}
			else {
				session.merge(assetEntrySetLike);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AssetEntrySetLikeModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((assetEntrySetLikeModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYSETID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetEntrySetLikeModelImpl.getOriginalAssetEntrySetId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSETENTRYSETID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYSETID,
					args);

				args = new Object[] {
						assetEntrySetLikeModelImpl.getAssetEntrySetId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSETENTRYSETID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYSETID,
					args);
			}
		}

		EntityCacheUtil.putResult(AssetEntrySetLikeModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetLikeImpl.class, assetEntrySetLike.getPrimaryKey(),
			assetEntrySetLike);

		return assetEntrySetLike;
	}

	protected AssetEntrySetLike toUnwrappedModel(
		AssetEntrySetLike assetEntrySetLike) {
		if (assetEntrySetLike instanceof AssetEntrySetLikeImpl) {
			return assetEntrySetLike;
		}

		AssetEntrySetLikeImpl assetEntrySetLikeImpl = new AssetEntrySetLikeImpl();

		assetEntrySetLikeImpl.setNew(assetEntrySetLike.isNew());
		assetEntrySetLikeImpl.setPrimaryKey(assetEntrySetLike.getPrimaryKey());

		assetEntrySetLikeImpl.setAssetEntrySetId(assetEntrySetLike.getAssetEntrySetId());
		assetEntrySetLikeImpl.setClassNameId(assetEntrySetLike.getClassNameId());
		assetEntrySetLikeImpl.setClassPK(assetEntrySetLike.getClassPK());

		return assetEntrySetLikeImpl;
	}

	/**
	 * Returns the asset entry set like with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset entry set like
	 * @return the asset entry set like
	 * @throws com.liferay.asset.entry.set.NoSuchLikeException if a asset entry set like with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySetLike findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLikeException, SystemException {
		AssetEntrySetLike assetEntrySetLike = fetchByPrimaryKey(primaryKey);

		if (assetEntrySetLike == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLikeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return assetEntrySetLike;
	}

	/**
	 * Returns the asset entry set like with the primary key or throws a {@link com.liferay.asset.entry.set.NoSuchLikeException} if it could not be found.
	 *
	 * @param assetEntrySetLikePK the primary key of the asset entry set like
	 * @return the asset entry set like
	 * @throws com.liferay.asset.entry.set.NoSuchLikeException if a asset entry set like with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySetLike findByPrimaryKey(
		AssetEntrySetLikePK assetEntrySetLikePK)
		throws NoSuchLikeException, SystemException {
		return findByPrimaryKey((Serializable)assetEntrySetLikePK);
	}

	/**
	 * Returns the asset entry set like with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset entry set like
	 * @return the asset entry set like, or <code>null</code> if a asset entry set like with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySetLike fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		AssetEntrySetLike assetEntrySetLike = (AssetEntrySetLike)EntityCacheUtil.getResult(AssetEntrySetLikeModelImpl.ENTITY_CACHE_ENABLED,
				AssetEntrySetLikeImpl.class, primaryKey);

		if (assetEntrySetLike == _nullAssetEntrySetLike) {
			return null;
		}

		if (assetEntrySetLike == null) {
			Session session = null;

			try {
				session = openSession();

				assetEntrySetLike = (AssetEntrySetLike)session.get(AssetEntrySetLikeImpl.class,
						primaryKey);

				if (assetEntrySetLike != null) {
					cacheResult(assetEntrySetLike);
				}
				else {
					EntityCacheUtil.putResult(AssetEntrySetLikeModelImpl.ENTITY_CACHE_ENABLED,
						AssetEntrySetLikeImpl.class, primaryKey,
						_nullAssetEntrySetLike);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(AssetEntrySetLikeModelImpl.ENTITY_CACHE_ENABLED,
					AssetEntrySetLikeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return assetEntrySetLike;
	}

	/**
	 * Returns the asset entry set like with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetEntrySetLikePK the primary key of the asset entry set like
	 * @return the asset entry set like, or <code>null</code> if a asset entry set like with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySetLike fetchByPrimaryKey(
		AssetEntrySetLikePK assetEntrySetLikePK) throws SystemException {
		return fetchByPrimaryKey((Serializable)assetEntrySetLikePK);
	}

	/**
	 * Returns all the asset entry set likes.
	 *
	 * @return the asset entry set likes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySetLike> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset entry set likes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetLikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry set likes
	 * @param end the upper bound of the range of asset entry set likes (not inclusive)
	 * @return the range of asset entry set likes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySetLike> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset entry set likes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetLikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry set likes
	 * @param end the upper bound of the range of asset entry set likes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset entry set likes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySetLike> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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

		List<AssetEntrySetLike> list = (List<AssetEntrySetLike>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ASSETENTRYSETLIKE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETENTRYSETLIKE;

				if (pagination) {
					sql = sql.concat(AssetEntrySetLikeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AssetEntrySetLike>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<AssetEntrySetLike>(list);
				}
				else {
					list = (List<AssetEntrySetLike>)QueryUtil.list(q,
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
	 * Removes all the asset entry set likes from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (AssetEntrySetLike assetEntrySetLike : findAll()) {
			remove(assetEntrySetLike);
		}
	}

	/**
	 * Returns the number of asset entry set likes.
	 *
	 * @return the number of asset entry set likes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETENTRYSETLIKE);

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
	 * Initializes the asset entry set like persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.asset.entry.set.model.AssetEntrySetLike")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AssetEntrySetLike>> listenersList = new ArrayList<ModelListener<AssetEntrySetLike>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<AssetEntrySetLike>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(AssetEntrySetLikeImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_ASSETENTRYSETLIKE = "SELECT assetEntrySetLike FROM AssetEntrySetLike assetEntrySetLike";
	private static final String _SQL_SELECT_ASSETENTRYSETLIKE_WHERE = "SELECT assetEntrySetLike FROM AssetEntrySetLike assetEntrySetLike WHERE ";
	private static final String _SQL_COUNT_ASSETENTRYSETLIKE = "SELECT COUNT(assetEntrySetLike) FROM AssetEntrySetLike assetEntrySetLike";
	private static final String _SQL_COUNT_ASSETENTRYSETLIKE_WHERE = "SELECT COUNT(assetEntrySetLike) FROM AssetEntrySetLike assetEntrySetLike WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetEntrySetLike.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetEntrySetLike exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetEntrySetLike exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AssetEntrySetLikePersistenceImpl.class);
	private static AssetEntrySetLike _nullAssetEntrySetLike = new AssetEntrySetLikeImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AssetEntrySetLike> toCacheModel() {
				return _nullAssetEntrySetLikeCacheModel;
			}
		};

	private static CacheModel<AssetEntrySetLike> _nullAssetEntrySetLikeCacheModel =
		new CacheModel<AssetEntrySetLike>() {
			@Override
			public AssetEntrySetLike toEntityModel() {
				return _nullAssetEntrySetLike;
			}
		};
}