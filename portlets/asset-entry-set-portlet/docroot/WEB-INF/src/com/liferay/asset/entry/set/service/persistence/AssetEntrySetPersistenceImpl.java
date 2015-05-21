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

import com.liferay.asset.entry.set.NoSuchAssetEntrySetException;
import com.liferay.asset.entry.set.model.AssetEntrySet;
import com.liferay.asset.entry.set.model.impl.AssetEntrySetImpl;
import com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl;

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
 * The persistence implementation for the asset entry set service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetPersistence
 * @see AssetEntrySetUtil
 * @generated
 */
public class AssetEntrySetPersistenceImpl extends BasePersistenceImpl<AssetEntrySet>
	implements AssetEntrySetPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetEntrySetUtil} to access the asset entry set persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetEntrySetImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetModelImpl.FINDER_CACHE_ENABLED,
			AssetEntrySetImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetModelImpl.FINDER_CACHE_ENABLED,
			AssetEntrySetImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTASSETENTRYSETID =
		new FinderPath(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetModelImpl.FINDER_CACHE_ENABLED,
			AssetEntrySetImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByParentAssetEntrySetId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTASSETENTRYSETID =
		new FinderPath(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetModelImpl.FINDER_CACHE_ENABLED,
			AssetEntrySetImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByParentAssetEntrySetId",
			new String[] { Long.class.getName() },
			AssetEntrySetModelImpl.PARENTASSETENTRYSETID_COLUMN_BITMASK |
			AssetEntrySetModelImpl.CREATETIME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PARENTASSETENTRYSETID = new FinderPath(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByParentAssetEntrySetId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the asset entry sets where parentAssetEntrySetId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @return the matching asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySet> findByParentAssetEntrySetId(
		long parentAssetEntrySetId) throws SystemException {
		return findByParentAssetEntrySetId(parentAssetEntrySetId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset entry sets where parentAssetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @return the range of matching asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySet> findByParentAssetEntrySetId(
		long parentAssetEntrySetId, int start, int end)
		throws SystemException {
		return findByParentAssetEntrySetId(parentAssetEntrySetId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the asset entry sets where parentAssetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySet> findByParentAssetEntrySetId(
		long parentAssetEntrySetId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTASSETENTRYSETID;
			finderArgs = new Object[] { parentAssetEntrySetId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTASSETENTRYSETID;
			finderArgs = new Object[] {
					parentAssetEntrySetId,
					
					start, end, orderByComparator
				};
		}

		List<AssetEntrySet> list = (List<AssetEntrySet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetEntrySet assetEntrySet : list) {
				if ((parentAssetEntrySetId != assetEntrySet.getParentAssetEntrySetId())) {
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

			query.append(_SQL_SELECT_ASSETENTRYSET_WHERE);

			query.append(_FINDER_COLUMN_PARENTASSETENTRYSETID_PARENTASSETENTRYSETID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetEntrySetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentAssetEntrySetId);

				if (!pagination) {
					list = (List<AssetEntrySet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<AssetEntrySet>(list);
				}
				else {
					list = (List<AssetEntrySet>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset entry set in the ordered set where parentAssetEntrySetId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set
	 * @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet findByParentAssetEntrySetId_First(
		long parentAssetEntrySetId, OrderByComparator orderByComparator)
		throws NoSuchAssetEntrySetException, SystemException {
		AssetEntrySet assetEntrySet = fetchByParentAssetEntrySetId_First(parentAssetEntrySetId,
				orderByComparator);

		if (assetEntrySet != null) {
			return assetEntrySet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentAssetEntrySetId=");
		msg.append(parentAssetEntrySetId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetEntrySetException(msg.toString());
	}

	/**
	 * Returns the first asset entry set in the ordered set where parentAssetEntrySetId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet fetchByParentAssetEntrySetId_First(
		long parentAssetEntrySetId, OrderByComparator orderByComparator)
		throws SystemException {
		List<AssetEntrySet> list = findByParentAssetEntrySetId(parentAssetEntrySetId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset entry set in the ordered set where parentAssetEntrySetId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set
	 * @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet findByParentAssetEntrySetId_Last(
		long parentAssetEntrySetId, OrderByComparator orderByComparator)
		throws NoSuchAssetEntrySetException, SystemException {
		AssetEntrySet assetEntrySet = fetchByParentAssetEntrySetId_Last(parentAssetEntrySetId,
				orderByComparator);

		if (assetEntrySet != null) {
			return assetEntrySet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentAssetEntrySetId=");
		msg.append(parentAssetEntrySetId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetEntrySetException(msg.toString());
	}

	/**
	 * Returns the last asset entry set in the ordered set where parentAssetEntrySetId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet fetchByParentAssetEntrySetId_Last(
		long parentAssetEntrySetId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByParentAssetEntrySetId(parentAssetEntrySetId);

		if (count == 0) {
			return null;
		}

		List<AssetEntrySet> list = findByParentAssetEntrySetId(parentAssetEntrySetId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset entry sets before and after the current asset entry set in the ordered set where parentAssetEntrySetId = &#63;.
	 *
	 * @param assetEntrySetId the primary key of the current asset entry set
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry set
	 * @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet[] findByParentAssetEntrySetId_PrevAndNext(
		long assetEntrySetId, long parentAssetEntrySetId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetEntrySetException, SystemException {
		AssetEntrySet assetEntrySet = findByPrimaryKey(assetEntrySetId);

		Session session = null;

		try {
			session = openSession();

			AssetEntrySet[] array = new AssetEntrySetImpl[3];

			array[0] = getByParentAssetEntrySetId_PrevAndNext(session,
					assetEntrySet, parentAssetEntrySetId, orderByComparator,
					true);

			array[1] = assetEntrySet;

			array[2] = getByParentAssetEntrySetId_PrevAndNext(session,
					assetEntrySet, parentAssetEntrySetId, orderByComparator,
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

	protected AssetEntrySet getByParentAssetEntrySetId_PrevAndNext(
		Session session, AssetEntrySet assetEntrySet,
		long parentAssetEntrySetId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETENTRYSET_WHERE);

		query.append(_FINDER_COLUMN_PARENTASSETENTRYSETID_PARENTASSETENTRYSETID_2);

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
			query.append(AssetEntrySetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentAssetEntrySetId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetEntrySet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetEntrySet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset entry sets where parentAssetEntrySetId = &#63; from the database.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByParentAssetEntrySetId(long parentAssetEntrySetId)
		throws SystemException {
		for (AssetEntrySet assetEntrySet : findByParentAssetEntrySetId(
				parentAssetEntrySetId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(assetEntrySet);
		}
	}

	/**
	 * Returns the number of asset entry sets where parentAssetEntrySetId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @return the number of matching asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByParentAssetEntrySetId(long parentAssetEntrySetId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PARENTASSETENTRYSETID;

		Object[] finderArgs = new Object[] { parentAssetEntrySetId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASSETENTRYSET_WHERE);

			query.append(_FINDER_COLUMN_PARENTASSETENTRYSETID_PARENTASSETENTRYSETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentAssetEntrySetId);

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

	private static final String _FINDER_COLUMN_PARENTASSETENTRYSETID_PARENTASSETENTRYSETID_2 =
		"assetEntrySet.parentAssetEntrySetId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GTCT_PAESI =
		new FinderPath(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetModelImpl.FINDER_CACHE_ENABLED,
			AssetEntrySetImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGtCT_PAESI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTCT_PAESI =
		new FinderPath(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByGtCT_PAESI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @return the matching asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySet> findByGtCT_PAESI(long createTime,
		long parentAssetEntrySetId) throws SystemException {
		return findByGtCT_PAESI(createTime, parentAssetEntrySetId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @return the range of matching asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySet> findByGtCT_PAESI(long createTime,
		long parentAssetEntrySetId, int start, int end)
		throws SystemException {
		return findByGtCT_PAESI(createTime, parentAssetEntrySetId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySet> findByGtCT_PAESI(long createTime,
		long parentAssetEntrySetId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GTCT_PAESI;
		finderArgs = new Object[] {
				createTime, parentAssetEntrySetId,
				
				start, end, orderByComparator
			};

		List<AssetEntrySet> list = (List<AssetEntrySet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetEntrySet assetEntrySet : list) {
				if ((createTime >= assetEntrySet.getCreateTime()) ||
						(parentAssetEntrySetId != assetEntrySet.getParentAssetEntrySetId())) {
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

			query.append(_SQL_SELECT_ASSETENTRYSET_WHERE);

			query.append(_FINDER_COLUMN_GTCT_PAESI_CREATETIME_2);

			query.append(_FINDER_COLUMN_GTCT_PAESI_PARENTASSETENTRYSETID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetEntrySetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createTime);

				qPos.add(parentAssetEntrySetId);

				if (!pagination) {
					list = (List<AssetEntrySet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<AssetEntrySet>(list);
				}
				else {
					list = (List<AssetEntrySet>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset entry set in the ordered set where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set
	 * @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet findByGtCT_PAESI_First(long createTime,
		long parentAssetEntrySetId, OrderByComparator orderByComparator)
		throws NoSuchAssetEntrySetException, SystemException {
		AssetEntrySet assetEntrySet = fetchByGtCT_PAESI_First(createTime,
				parentAssetEntrySetId, orderByComparator);

		if (assetEntrySet != null) {
			return assetEntrySet;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createTime=");
		msg.append(createTime);

		msg.append(", parentAssetEntrySetId=");
		msg.append(parentAssetEntrySetId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetEntrySetException(msg.toString());
	}

	/**
	 * Returns the first asset entry set in the ordered set where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet fetchByGtCT_PAESI_First(long createTime,
		long parentAssetEntrySetId, OrderByComparator orderByComparator)
		throws SystemException {
		List<AssetEntrySet> list = findByGtCT_PAESI(createTime,
				parentAssetEntrySetId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset entry set in the ordered set where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set
	 * @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet findByGtCT_PAESI_Last(long createTime,
		long parentAssetEntrySetId, OrderByComparator orderByComparator)
		throws NoSuchAssetEntrySetException, SystemException {
		AssetEntrySet assetEntrySet = fetchByGtCT_PAESI_Last(createTime,
				parentAssetEntrySetId, orderByComparator);

		if (assetEntrySet != null) {
			return assetEntrySet;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createTime=");
		msg.append(createTime);

		msg.append(", parentAssetEntrySetId=");
		msg.append(parentAssetEntrySetId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetEntrySetException(msg.toString());
	}

	/**
	 * Returns the last asset entry set in the ordered set where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet fetchByGtCT_PAESI_Last(long createTime,
		long parentAssetEntrySetId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByGtCT_PAESI(createTime, parentAssetEntrySetId);

		if (count == 0) {
			return null;
		}

		List<AssetEntrySet> list = findByGtCT_PAESI(createTime,
				parentAssetEntrySetId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset entry sets before and after the current asset entry set in the ordered set where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param assetEntrySetId the primary key of the current asset entry set
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry set
	 * @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet[] findByGtCT_PAESI_PrevAndNext(long assetEntrySetId,
		long createTime, long parentAssetEntrySetId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetEntrySetException, SystemException {
		AssetEntrySet assetEntrySet = findByPrimaryKey(assetEntrySetId);

		Session session = null;

		try {
			session = openSession();

			AssetEntrySet[] array = new AssetEntrySetImpl[3];

			array[0] = getByGtCT_PAESI_PrevAndNext(session, assetEntrySet,
					createTime, parentAssetEntrySetId, orderByComparator, true);

			array[1] = assetEntrySet;

			array[2] = getByGtCT_PAESI_PrevAndNext(session, assetEntrySet,
					createTime, parentAssetEntrySetId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetEntrySet getByGtCT_PAESI_PrevAndNext(Session session,
		AssetEntrySet assetEntrySet, long createTime,
		long parentAssetEntrySetId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETENTRYSET_WHERE);

		query.append(_FINDER_COLUMN_GTCT_PAESI_CREATETIME_2);

		query.append(_FINDER_COLUMN_GTCT_PAESI_PARENTASSETENTRYSETID_2);

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
			query.append(AssetEntrySetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(createTime);

		qPos.add(parentAssetEntrySetId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetEntrySet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetEntrySet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63; from the database.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGtCT_PAESI(long createTime, long parentAssetEntrySetId)
		throws SystemException {
		for (AssetEntrySet assetEntrySet : findByGtCT_PAESI(createTime,
				parentAssetEntrySetId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(assetEntrySet);
		}
	}

	/**
	 * Returns the number of asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @return the number of matching asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGtCT_PAESI(long createTime, long parentAssetEntrySetId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_GTCT_PAESI;

		Object[] finderArgs = new Object[] { createTime, parentAssetEntrySetId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETENTRYSET_WHERE);

			query.append(_FINDER_COLUMN_GTCT_PAESI_CREATETIME_2);

			query.append(_FINDER_COLUMN_GTCT_PAESI_PARENTASSETENTRYSETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createTime);

				qPos.add(parentAssetEntrySetId);

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

	private static final String _FINDER_COLUMN_GTCT_PAESI_CREATETIME_2 = "assetEntrySet.createTime > ? AND ";
	private static final String _FINDER_COLUMN_GTCT_PAESI_PARENTASSETENTRYSETID_2 =
		"assetEntrySet.parentAssetEntrySetId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LTCT_PAESI =
		new FinderPath(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetModelImpl.FINDER_CACHE_ENABLED,
			AssetEntrySetImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLtCT_PAESI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_LTCT_PAESI =
		new FinderPath(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLtCT_PAESI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @return the matching asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySet> findByLtCT_PAESI(long createTime,
		long parentAssetEntrySetId) throws SystemException {
		return findByLtCT_PAESI(createTime, parentAssetEntrySetId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @return the range of matching asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySet> findByLtCT_PAESI(long createTime,
		long parentAssetEntrySetId, int start, int end)
		throws SystemException {
		return findByLtCT_PAESI(createTime, parentAssetEntrySetId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySet> findByLtCT_PAESI(long createTime,
		long parentAssetEntrySetId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LTCT_PAESI;
		finderArgs = new Object[] {
				createTime, parentAssetEntrySetId,
				
				start, end, orderByComparator
			};

		List<AssetEntrySet> list = (List<AssetEntrySet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetEntrySet assetEntrySet : list) {
				if ((createTime < assetEntrySet.getCreateTime()) ||
						(parentAssetEntrySetId != assetEntrySet.getParentAssetEntrySetId())) {
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

			query.append(_SQL_SELECT_ASSETENTRYSET_WHERE);

			query.append(_FINDER_COLUMN_LTCT_PAESI_CREATETIME_2);

			query.append(_FINDER_COLUMN_LTCT_PAESI_PARENTASSETENTRYSETID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetEntrySetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createTime);

				qPos.add(parentAssetEntrySetId);

				if (!pagination) {
					list = (List<AssetEntrySet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<AssetEntrySet>(list);
				}
				else {
					list = (List<AssetEntrySet>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset entry set in the ordered set where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set
	 * @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet findByLtCT_PAESI_First(long createTime,
		long parentAssetEntrySetId, OrderByComparator orderByComparator)
		throws NoSuchAssetEntrySetException, SystemException {
		AssetEntrySet assetEntrySet = fetchByLtCT_PAESI_First(createTime,
				parentAssetEntrySetId, orderByComparator);

		if (assetEntrySet != null) {
			return assetEntrySet;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createTime=");
		msg.append(createTime);

		msg.append(", parentAssetEntrySetId=");
		msg.append(parentAssetEntrySetId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetEntrySetException(msg.toString());
	}

	/**
	 * Returns the first asset entry set in the ordered set where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet fetchByLtCT_PAESI_First(long createTime,
		long parentAssetEntrySetId, OrderByComparator orderByComparator)
		throws SystemException {
		List<AssetEntrySet> list = findByLtCT_PAESI(createTime,
				parentAssetEntrySetId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset entry set in the ordered set where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set
	 * @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet findByLtCT_PAESI_Last(long createTime,
		long parentAssetEntrySetId, OrderByComparator orderByComparator)
		throws NoSuchAssetEntrySetException, SystemException {
		AssetEntrySet assetEntrySet = fetchByLtCT_PAESI_Last(createTime,
				parentAssetEntrySetId, orderByComparator);

		if (assetEntrySet != null) {
			return assetEntrySet;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createTime=");
		msg.append(createTime);

		msg.append(", parentAssetEntrySetId=");
		msg.append(parentAssetEntrySetId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetEntrySetException(msg.toString());
	}

	/**
	 * Returns the last asset entry set in the ordered set where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet fetchByLtCT_PAESI_Last(long createTime,
		long parentAssetEntrySetId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByLtCT_PAESI(createTime, parentAssetEntrySetId);

		if (count == 0) {
			return null;
		}

		List<AssetEntrySet> list = findByLtCT_PAESI(createTime,
				parentAssetEntrySetId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset entry sets before and after the current asset entry set in the ordered set where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param assetEntrySetId the primary key of the current asset entry set
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry set
	 * @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet[] findByLtCT_PAESI_PrevAndNext(long assetEntrySetId,
		long createTime, long parentAssetEntrySetId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetEntrySetException, SystemException {
		AssetEntrySet assetEntrySet = findByPrimaryKey(assetEntrySetId);

		Session session = null;

		try {
			session = openSession();

			AssetEntrySet[] array = new AssetEntrySetImpl[3];

			array[0] = getByLtCT_PAESI_PrevAndNext(session, assetEntrySet,
					createTime, parentAssetEntrySetId, orderByComparator, true);

			array[1] = assetEntrySet;

			array[2] = getByLtCT_PAESI_PrevAndNext(session, assetEntrySet,
					createTime, parentAssetEntrySetId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetEntrySet getByLtCT_PAESI_PrevAndNext(Session session,
		AssetEntrySet assetEntrySet, long createTime,
		long parentAssetEntrySetId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETENTRYSET_WHERE);

		query.append(_FINDER_COLUMN_LTCT_PAESI_CREATETIME_2);

		query.append(_FINDER_COLUMN_LTCT_PAESI_PARENTASSETENTRYSETID_2);

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
			query.append(AssetEntrySetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(createTime);

		qPos.add(parentAssetEntrySetId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetEntrySet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetEntrySet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63; from the database.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByLtCT_PAESI(long createTime, long parentAssetEntrySetId)
		throws SystemException {
		for (AssetEntrySet assetEntrySet : findByLtCT_PAESI(createTime,
				parentAssetEntrySetId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(assetEntrySet);
		}
	}

	/**
	 * Returns the number of asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @return the number of matching asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByLtCT_PAESI(long createTime, long parentAssetEntrySetId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_LTCT_PAESI;

		Object[] finderArgs = new Object[] { createTime, parentAssetEntrySetId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETENTRYSET_WHERE);

			query.append(_FINDER_COLUMN_LTCT_PAESI_CREATETIME_2);

			query.append(_FINDER_COLUMN_LTCT_PAESI_PARENTASSETENTRYSETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createTime);

				qPos.add(parentAssetEntrySetId);

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

	private static final String _FINDER_COLUMN_LTCT_PAESI_CREATETIME_2 = "assetEntrySet.createTime <= ? AND ";
	private static final String _FINDER_COLUMN_LTCT_PAESI_PARENTASSETENTRYSETID_2 =
		"assetEntrySet.parentAssetEntrySetId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PAESI_CCNI =
		new FinderPath(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetModelImpl.FINDER_CACHE_ENABLED,
			AssetEntrySetImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPAESI_CCNI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAESI_CCNI =
		new FinderPath(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetModelImpl.FINDER_CACHE_ENABLED,
			AssetEntrySetImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPAESI_CCNI",
			new String[] { Long.class.getName(), Long.class.getName() },
			AssetEntrySetModelImpl.PARENTASSETENTRYSETID_COLUMN_BITMASK |
			AssetEntrySetModelImpl.CREATORCLASSNAMEID_COLUMN_BITMASK |
			AssetEntrySetModelImpl.CREATETIME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PAESI_CCNI = new FinderPath(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPAESI_CCNI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @return the matching asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySet> findByPAESI_CCNI(long parentAssetEntrySetId,
		long creatorClassNameId) throws SystemException {
		return findByPAESI_CCNI(parentAssetEntrySetId, creatorClassNameId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @return the range of matching asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySet> findByPAESI_CCNI(long parentAssetEntrySetId,
		long creatorClassNameId, int start, int end) throws SystemException {
		return findByPAESI_CCNI(parentAssetEntrySetId, creatorClassNameId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySet> findByPAESI_CCNI(long parentAssetEntrySetId,
		long creatorClassNameId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAESI_CCNI;
			finderArgs = new Object[] { parentAssetEntrySetId, creatorClassNameId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PAESI_CCNI;
			finderArgs = new Object[] {
					parentAssetEntrySetId, creatorClassNameId,
					
					start, end, orderByComparator
				};
		}

		List<AssetEntrySet> list = (List<AssetEntrySet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AssetEntrySet assetEntrySet : list) {
				if ((parentAssetEntrySetId != assetEntrySet.getParentAssetEntrySetId()) ||
						(creatorClassNameId != assetEntrySet.getCreatorClassNameId())) {
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

			query.append(_SQL_SELECT_ASSETENTRYSET_WHERE);

			query.append(_FINDER_COLUMN_PAESI_CCNI_PARENTASSETENTRYSETID_2);

			query.append(_FINDER_COLUMN_PAESI_CCNI_CREATORCLASSNAMEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AssetEntrySetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentAssetEntrySetId);

				qPos.add(creatorClassNameId);

				if (!pagination) {
					list = (List<AssetEntrySet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<AssetEntrySet>(list);
				}
				else {
					list = (List<AssetEntrySet>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first asset entry set in the ordered set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set
	 * @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet findByPAESI_CCNI_First(long parentAssetEntrySetId,
		long creatorClassNameId, OrderByComparator orderByComparator)
		throws NoSuchAssetEntrySetException, SystemException {
		AssetEntrySet assetEntrySet = fetchByPAESI_CCNI_First(parentAssetEntrySetId,
				creatorClassNameId, orderByComparator);

		if (assetEntrySet != null) {
			return assetEntrySet;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentAssetEntrySetId=");
		msg.append(parentAssetEntrySetId);

		msg.append(", creatorClassNameId=");
		msg.append(creatorClassNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetEntrySetException(msg.toString());
	}

	/**
	 * Returns the first asset entry set in the ordered set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet fetchByPAESI_CCNI_First(long parentAssetEntrySetId,
		long creatorClassNameId, OrderByComparator orderByComparator)
		throws SystemException {
		List<AssetEntrySet> list = findByPAESI_CCNI(parentAssetEntrySetId,
				creatorClassNameId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset entry set in the ordered set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set
	 * @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet findByPAESI_CCNI_Last(long parentAssetEntrySetId,
		long creatorClassNameId, OrderByComparator orderByComparator)
		throws NoSuchAssetEntrySetException, SystemException {
		AssetEntrySet assetEntrySet = fetchByPAESI_CCNI_Last(parentAssetEntrySetId,
				creatorClassNameId, orderByComparator);

		if (assetEntrySet != null) {
			return assetEntrySet;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentAssetEntrySetId=");
		msg.append(parentAssetEntrySetId);

		msg.append(", creatorClassNameId=");
		msg.append(creatorClassNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAssetEntrySetException(msg.toString());
	}

	/**
	 * Returns the last asset entry set in the ordered set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet fetchByPAESI_CCNI_Last(long parentAssetEntrySetId,
		long creatorClassNameId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByPAESI_CCNI(parentAssetEntrySetId, creatorClassNameId);

		if (count == 0) {
			return null;
		}

		List<AssetEntrySet> list = findByPAESI_CCNI(parentAssetEntrySetId,
				creatorClassNameId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the asset entry sets before and after the current asset entry set in the ordered set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * @param assetEntrySetId the primary key of the current asset entry set
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry set
	 * @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet[] findByPAESI_CCNI_PrevAndNext(long assetEntrySetId,
		long parentAssetEntrySetId, long creatorClassNameId,
		OrderByComparator orderByComparator)
		throws NoSuchAssetEntrySetException, SystemException {
		AssetEntrySet assetEntrySet = findByPrimaryKey(assetEntrySetId);

		Session session = null;

		try {
			session = openSession();

			AssetEntrySet[] array = new AssetEntrySetImpl[3];

			array[0] = getByPAESI_CCNI_PrevAndNext(session, assetEntrySet,
					parentAssetEntrySetId, creatorClassNameId,
					orderByComparator, true);

			array[1] = assetEntrySet;

			array[2] = getByPAESI_CCNI_PrevAndNext(session, assetEntrySet,
					parentAssetEntrySetId, creatorClassNameId,
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

	protected AssetEntrySet getByPAESI_CCNI_PrevAndNext(Session session,
		AssetEntrySet assetEntrySet, long parentAssetEntrySetId,
		long creatorClassNameId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASSETENTRYSET_WHERE);

		query.append(_FINDER_COLUMN_PAESI_CCNI_PARENTASSETENTRYSETID_2);

		query.append(_FINDER_COLUMN_PAESI_CCNI_CREATORCLASSNAMEID_2);

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
			query.append(AssetEntrySetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentAssetEntrySetId);

		qPos.add(creatorClassNameId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(assetEntrySet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AssetEntrySet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; from the database.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByPAESI_CCNI(long parentAssetEntrySetId,
		long creatorClassNameId) throws SystemException {
		for (AssetEntrySet assetEntrySet : findByPAESI_CCNI(
				parentAssetEntrySetId, creatorClassNameId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(assetEntrySet);
		}
	}

	/**
	 * Returns the number of asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @return the number of matching asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByPAESI_CCNI(long parentAssetEntrySetId,
		long creatorClassNameId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PAESI_CCNI;

		Object[] finderArgs = new Object[] {
				parentAssetEntrySetId, creatorClassNameId
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASSETENTRYSET_WHERE);

			query.append(_FINDER_COLUMN_PAESI_CCNI_PARENTASSETENTRYSETID_2);

			query.append(_FINDER_COLUMN_PAESI_CCNI_CREATORCLASSNAMEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentAssetEntrySetId);

				qPos.add(creatorClassNameId);

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

	private static final String _FINDER_COLUMN_PAESI_CCNI_PARENTASSETENTRYSETID_2 =
		"assetEntrySet.parentAssetEntrySetId = ? AND ";
	private static final String _FINDER_COLUMN_PAESI_CCNI_CREATORCLASSNAMEID_2 = "assetEntrySet.creatorClassNameId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_PAESI_CCNI_CCPK = new FinderPath(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetModelImpl.FINDER_CACHE_ENABLED,
			AssetEntrySetImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByPAESI_CCNI_CCPK",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			AssetEntrySetModelImpl.PARENTASSETENTRYSETID_COLUMN_BITMASK |
			AssetEntrySetModelImpl.CREATORCLASSNAMEID_COLUMN_BITMASK |
			AssetEntrySetModelImpl.CREATORCLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PAESI_CCNI_CCPK = new FinderPath(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPAESI_CCNI_CCPK",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the asset entry set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; and creatorClassPK = &#63; or throws a {@link com.liferay.asset.entry.set.NoSuchAssetEntrySetException} if it could not be found.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param creatorClassPK the creator class p k
	 * @return the matching asset entry set
	 * @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a matching asset entry set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet findByPAESI_CCNI_CCPK(long parentAssetEntrySetId,
		long creatorClassNameId, long creatorClassPK)
		throws NoSuchAssetEntrySetException, SystemException {
		AssetEntrySet assetEntrySet = fetchByPAESI_CCNI_CCPK(parentAssetEntrySetId,
				creatorClassNameId, creatorClassPK);

		if (assetEntrySet == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("parentAssetEntrySetId=");
			msg.append(parentAssetEntrySetId);

			msg.append(", creatorClassNameId=");
			msg.append(creatorClassNameId);

			msg.append(", creatorClassPK=");
			msg.append(creatorClassPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAssetEntrySetException(msg.toString());
		}

		return assetEntrySet;
	}

	/**
	 * Returns the asset entry set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; and creatorClassPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param creatorClassPK the creator class p k
	 * @return the matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet fetchByPAESI_CCNI_CCPK(long parentAssetEntrySetId,
		long creatorClassNameId, long creatorClassPK) throws SystemException {
		return fetchByPAESI_CCNI_CCPK(parentAssetEntrySetId,
			creatorClassNameId, creatorClassPK, true);
	}

	/**
	 * Returns the asset entry set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; and creatorClassPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param creatorClassPK the creator class p k
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet fetchByPAESI_CCNI_CCPK(long parentAssetEntrySetId,
		long creatorClassNameId, long creatorClassPK, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				parentAssetEntrySetId, creatorClassNameId, creatorClassPK
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PAESI_CCNI_CCPK,
					finderArgs, this);
		}

		if (result instanceof AssetEntrySet) {
			AssetEntrySet assetEntrySet = (AssetEntrySet)result;

			if ((parentAssetEntrySetId != assetEntrySet.getParentAssetEntrySetId()) ||
					(creatorClassNameId != assetEntrySet.getCreatorClassNameId()) ||
					(creatorClassPK != assetEntrySet.getCreatorClassPK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_ASSETENTRYSET_WHERE);

			query.append(_FINDER_COLUMN_PAESI_CCNI_CCPK_PARENTASSETENTRYSETID_2);

			query.append(_FINDER_COLUMN_PAESI_CCNI_CCPK_CREATORCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_PAESI_CCNI_CCPK_CREATORCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentAssetEntrySetId);

				qPos.add(creatorClassNameId);

				qPos.add(creatorClassPK);

				List<AssetEntrySet> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PAESI_CCNI_CCPK,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"AssetEntrySetPersistenceImpl.fetchByPAESI_CCNI_CCPK(long, long, long, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					AssetEntrySet assetEntrySet = list.get(0);

					result = assetEntrySet;

					cacheResult(assetEntrySet);

					if ((assetEntrySet.getParentAssetEntrySetId() != parentAssetEntrySetId) ||
							(assetEntrySet.getCreatorClassNameId() != creatorClassNameId) ||
							(assetEntrySet.getCreatorClassPK() != creatorClassPK)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PAESI_CCNI_CCPK,
							finderArgs, assetEntrySet);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PAESI_CCNI_CCPK,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (AssetEntrySet)result;
		}
	}

	/**
	 * Removes the asset entry set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; and creatorClassPK = &#63; from the database.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param creatorClassPK the creator class p k
	 * @return the asset entry set that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet removeByPAESI_CCNI_CCPK(long parentAssetEntrySetId,
		long creatorClassNameId, long creatorClassPK)
		throws NoSuchAssetEntrySetException, SystemException {
		AssetEntrySet assetEntrySet = findByPAESI_CCNI_CCPK(parentAssetEntrySetId,
				creatorClassNameId, creatorClassPK);

		return remove(assetEntrySet);
	}

	/**
	 * Returns the number of asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; and creatorClassPK = &#63;.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID
	 * @param creatorClassNameId the creator class name ID
	 * @param creatorClassPK the creator class p k
	 * @return the number of matching asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByPAESI_CCNI_CCPK(long parentAssetEntrySetId,
		long creatorClassNameId, long creatorClassPK) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PAESI_CCNI_CCPK;

		Object[] finderArgs = new Object[] {
				parentAssetEntrySetId, creatorClassNameId, creatorClassPK
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASSETENTRYSET_WHERE);

			query.append(_FINDER_COLUMN_PAESI_CCNI_CCPK_PARENTASSETENTRYSETID_2);

			query.append(_FINDER_COLUMN_PAESI_CCNI_CCPK_CREATORCLASSNAMEID_2);

			query.append(_FINDER_COLUMN_PAESI_CCNI_CCPK_CREATORCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentAssetEntrySetId);

				qPos.add(creatorClassNameId);

				qPos.add(creatorClassPK);

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

	private static final String _FINDER_COLUMN_PAESI_CCNI_CCPK_PARENTASSETENTRYSETID_2 =
		"assetEntrySet.parentAssetEntrySetId = ? AND ";
	private static final String _FINDER_COLUMN_PAESI_CCNI_CCPK_CREATORCLASSNAMEID_2 =
		"assetEntrySet.creatorClassNameId = ? AND ";
	private static final String _FINDER_COLUMN_PAESI_CCNI_CCPK_CREATORCLASSPK_2 = "assetEntrySet.creatorClassPK = ?";

	public AssetEntrySetPersistenceImpl() {
		setModelClass(AssetEntrySet.class);
	}

	/**
	 * Caches the asset entry set in the entity cache if it is enabled.
	 *
	 * @param assetEntrySet the asset entry set
	 */
	@Override
	public void cacheResult(AssetEntrySet assetEntrySet) {
		EntityCacheUtil.putResult(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetImpl.class, assetEntrySet.getPrimaryKey(),
			assetEntrySet);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PAESI_CCNI_CCPK,
			new Object[] {
				assetEntrySet.getParentAssetEntrySetId(),
				assetEntrySet.getCreatorClassNameId(),
				assetEntrySet.getCreatorClassPK()
			}, assetEntrySet);

		assetEntrySet.resetOriginalValues();
	}

	/**
	 * Caches the asset entry sets in the entity cache if it is enabled.
	 *
	 * @param assetEntrySets the asset entry sets
	 */
	@Override
	public void cacheResult(List<AssetEntrySet> assetEntrySets) {
		for (AssetEntrySet assetEntrySet : assetEntrySets) {
			if (EntityCacheUtil.getResult(
						AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
						AssetEntrySetImpl.class, assetEntrySet.getPrimaryKey()) == null) {
				cacheResult(assetEntrySet);
			}
			else {
				assetEntrySet.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset entry sets.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AssetEntrySetImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AssetEntrySetImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset entry set.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetEntrySet assetEntrySet) {
		EntityCacheUtil.removeResult(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetImpl.class, assetEntrySet.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(assetEntrySet);
	}

	@Override
	public void clearCache(List<AssetEntrySet> assetEntrySets) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetEntrySet assetEntrySet : assetEntrySets) {
			EntityCacheUtil.removeResult(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
				AssetEntrySetImpl.class, assetEntrySet.getPrimaryKey());

			clearUniqueFindersCache(assetEntrySet);
		}
	}

	protected void cacheUniqueFindersCache(AssetEntrySet assetEntrySet) {
		if (assetEntrySet.isNew()) {
			Object[] args = new Object[] {
					assetEntrySet.getParentAssetEntrySetId(),
					assetEntrySet.getCreatorClassNameId(),
					assetEntrySet.getCreatorClassPK()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PAESI_CCNI_CCPK,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PAESI_CCNI_CCPK,
				args, assetEntrySet);
		}
		else {
			AssetEntrySetModelImpl assetEntrySetModelImpl = (AssetEntrySetModelImpl)assetEntrySet;

			if ((assetEntrySetModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_PAESI_CCNI_CCPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetEntrySet.getParentAssetEntrySetId(),
						assetEntrySet.getCreatorClassNameId(),
						assetEntrySet.getCreatorClassPK()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PAESI_CCNI_CCPK,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PAESI_CCNI_CCPK,
					args, assetEntrySet);
			}
		}
	}

	protected void clearUniqueFindersCache(AssetEntrySet assetEntrySet) {
		AssetEntrySetModelImpl assetEntrySetModelImpl = (AssetEntrySetModelImpl)assetEntrySet;

		Object[] args = new Object[] {
				assetEntrySet.getParentAssetEntrySetId(),
				assetEntrySet.getCreatorClassNameId(),
				assetEntrySet.getCreatorClassPK()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PAESI_CCNI_CCPK, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PAESI_CCNI_CCPK, args);

		if ((assetEntrySetModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_PAESI_CCNI_CCPK.getColumnBitmask()) != 0) {
			args = new Object[] {
					assetEntrySetModelImpl.getOriginalParentAssetEntrySetId(),
					assetEntrySetModelImpl.getOriginalCreatorClassNameId(),
					assetEntrySetModelImpl.getOriginalCreatorClassPK()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PAESI_CCNI_CCPK,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PAESI_CCNI_CCPK,
				args);
		}
	}

	/**
	 * Creates a new asset entry set with the primary key. Does not add the asset entry set to the database.
	 *
	 * @param assetEntrySetId the primary key for the new asset entry set
	 * @return the new asset entry set
	 */
	@Override
	public AssetEntrySet create(long assetEntrySetId) {
		AssetEntrySet assetEntrySet = new AssetEntrySetImpl();

		assetEntrySet.setNew(true);
		assetEntrySet.setPrimaryKey(assetEntrySetId);

		return assetEntrySet;
	}

	/**
	 * Removes the asset entry set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntrySetId the primary key of the asset entry set
	 * @return the asset entry set that was removed
	 * @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet remove(long assetEntrySetId)
		throws NoSuchAssetEntrySetException, SystemException {
		return remove((Serializable)assetEntrySetId);
	}

	/**
	 * Removes the asset entry set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset entry set
	 * @return the asset entry set that was removed
	 * @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet remove(Serializable primaryKey)
		throws NoSuchAssetEntrySetException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssetEntrySet assetEntrySet = (AssetEntrySet)session.get(AssetEntrySetImpl.class,
					primaryKey);

			if (assetEntrySet == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAssetEntrySetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetEntrySet);
		}
		catch (NoSuchAssetEntrySetException nsee) {
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
	protected AssetEntrySet removeImpl(AssetEntrySet assetEntrySet)
		throws SystemException {
		assetEntrySet = toUnwrappedModel(assetEntrySet);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(assetEntrySet)) {
				assetEntrySet = (AssetEntrySet)session.get(AssetEntrySetImpl.class,
						assetEntrySet.getPrimaryKeyObj());
			}

			if (assetEntrySet != null) {
				session.delete(assetEntrySet);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (assetEntrySet != null) {
			clearCache(assetEntrySet);
		}

		return assetEntrySet;
	}

	@Override
	public AssetEntrySet updateImpl(
		com.liferay.asset.entry.set.model.AssetEntrySet assetEntrySet)
		throws SystemException {
		assetEntrySet = toUnwrappedModel(assetEntrySet);

		boolean isNew = assetEntrySet.isNew();

		AssetEntrySetModelImpl assetEntrySetModelImpl = (AssetEntrySetModelImpl)assetEntrySet;

		Session session = null;

		try {
			session = openSession();

			if (assetEntrySet.isNew()) {
				session.save(assetEntrySet);

				assetEntrySet.setNew(false);
			}
			else {
				session.merge(assetEntrySet);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AssetEntrySetModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((assetEntrySetModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTASSETENTRYSETID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetEntrySetModelImpl.getOriginalParentAssetEntrySetId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTASSETENTRYSETID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTASSETENTRYSETID,
					args);

				args = new Object[] {
						assetEntrySetModelImpl.getParentAssetEntrySetId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTASSETENTRYSETID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTASSETENTRYSETID,
					args);
			}

			if ((assetEntrySetModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAESI_CCNI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						assetEntrySetModelImpl.getOriginalParentAssetEntrySetId(),
						assetEntrySetModelImpl.getOriginalCreatorClassNameId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PAESI_CCNI,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAESI_CCNI,
					args);

				args = new Object[] {
						assetEntrySetModelImpl.getParentAssetEntrySetId(),
						assetEntrySetModelImpl.getCreatorClassNameId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PAESI_CCNI,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PAESI_CCNI,
					args);
			}
		}

		EntityCacheUtil.putResult(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetImpl.class, assetEntrySet.getPrimaryKey(),
			assetEntrySet);

		clearUniqueFindersCache(assetEntrySet);
		cacheUniqueFindersCache(assetEntrySet);

		return assetEntrySet;
	}

	protected AssetEntrySet toUnwrappedModel(AssetEntrySet assetEntrySet) {
		if (assetEntrySet instanceof AssetEntrySetImpl) {
			return assetEntrySet;
		}

		AssetEntrySetImpl assetEntrySetImpl = new AssetEntrySetImpl();

		assetEntrySetImpl.setNew(assetEntrySet.isNew());
		assetEntrySetImpl.setPrimaryKey(assetEntrySet.getPrimaryKey());

		assetEntrySetImpl.setAssetEntrySetId(assetEntrySet.getAssetEntrySetId());
		assetEntrySetImpl.setCompanyId(assetEntrySet.getCompanyId());
		assetEntrySetImpl.setUserId(assetEntrySet.getUserId());
		assetEntrySetImpl.setCreateTime(assetEntrySet.getCreateTime());
		assetEntrySetImpl.setModifiedTime(assetEntrySet.getModifiedTime());
		assetEntrySetImpl.setAssetEntryId(assetEntrySet.getAssetEntryId());
		assetEntrySetImpl.setParentAssetEntrySetId(assetEntrySet.getParentAssetEntrySetId());
		assetEntrySetImpl.setCreatorClassNameId(assetEntrySet.getCreatorClassNameId());
		assetEntrySetImpl.setCreatorClassPK(assetEntrySet.getCreatorClassPK());
		assetEntrySetImpl.setCreatorName(assetEntrySet.getCreatorName());
		assetEntrySetImpl.setPayload(assetEntrySet.getPayload());
		assetEntrySetImpl.setChildAssetEntrySetsCount(assetEntrySet.getChildAssetEntrySetsCount());
		assetEntrySetImpl.setAssetEntrySetLikesCount(assetEntrySet.getAssetEntrySetLikesCount());
		assetEntrySetImpl.setPrivateAssetEntrySet(assetEntrySet.isPrivateAssetEntrySet());

		return assetEntrySetImpl;
	}

	/**
	 * Returns the asset entry set with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset entry set
	 * @return the asset entry set
	 * @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAssetEntrySetException, SystemException {
		AssetEntrySet assetEntrySet = fetchByPrimaryKey(primaryKey);

		if (assetEntrySet == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAssetEntrySetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return assetEntrySet;
	}

	/**
	 * Returns the asset entry set with the primary key or throws a {@link com.liferay.asset.entry.set.NoSuchAssetEntrySetException} if it could not be found.
	 *
	 * @param assetEntrySetId the primary key of the asset entry set
	 * @return the asset entry set
	 * @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet findByPrimaryKey(long assetEntrySetId)
		throws NoSuchAssetEntrySetException, SystemException {
		return findByPrimaryKey((Serializable)assetEntrySetId);
	}

	/**
	 * Returns the asset entry set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset entry set
	 * @return the asset entry set, or <code>null</code> if a asset entry set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		AssetEntrySet assetEntrySet = (AssetEntrySet)EntityCacheUtil.getResult(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
				AssetEntrySetImpl.class, primaryKey);

		if (assetEntrySet == _nullAssetEntrySet) {
			return null;
		}

		if (assetEntrySet == null) {
			Session session = null;

			try {
				session = openSession();

				assetEntrySet = (AssetEntrySet)session.get(AssetEntrySetImpl.class,
						primaryKey);

				if (assetEntrySet != null) {
					cacheResult(assetEntrySet);
				}
				else {
					EntityCacheUtil.putResult(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
						AssetEntrySetImpl.class, primaryKey, _nullAssetEntrySet);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
					AssetEntrySetImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return assetEntrySet;
	}

	/**
	 * Returns the asset entry set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetEntrySetId the primary key of the asset entry set
	 * @return the asset entry set, or <code>null</code> if a asset entry set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet fetchByPrimaryKey(long assetEntrySetId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)assetEntrySetId);
	}

	/**
	 * Returns all the asset entry sets.
	 *
	 * @return the asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySet> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset entry sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @return the range of asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySet> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset entry sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySet> findAll(int start, int end,
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

		List<AssetEntrySet> list = (List<AssetEntrySet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ASSETENTRYSET);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETENTRYSET;

				if (pagination) {
					sql = sql.concat(AssetEntrySetModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AssetEntrySet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<AssetEntrySet>(list);
				}
				else {
					list = (List<AssetEntrySet>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the asset entry sets from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (AssetEntrySet assetEntrySet : findAll()) {
			remove(assetEntrySet);
		}
	}

	/**
	 * Returns the number of asset entry sets.
	 *
	 * @return the number of asset entry sets
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

				Query q = session.createQuery(_SQL_COUNT_ASSETENTRYSET);

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
	 * Initializes the asset entry set persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.asset.entry.set.model.AssetEntrySet")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AssetEntrySet>> listenersList = new ArrayList<ModelListener<AssetEntrySet>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<AssetEntrySet>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AssetEntrySetImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_ASSETENTRYSET = "SELECT assetEntrySet FROM AssetEntrySet assetEntrySet";
	private static final String _SQL_SELECT_ASSETENTRYSET_WHERE = "SELECT assetEntrySet FROM AssetEntrySet assetEntrySet WHERE ";
	private static final String _SQL_COUNT_ASSETENTRYSET = "SELECT COUNT(assetEntrySet) FROM AssetEntrySet assetEntrySet";
	private static final String _SQL_COUNT_ASSETENTRYSET_WHERE = "SELECT COUNT(assetEntrySet) FROM AssetEntrySet assetEntrySet WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetEntrySet.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetEntrySet exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AssetEntrySet exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AssetEntrySetPersistenceImpl.class);
	private static AssetEntrySet _nullAssetEntrySet = new AssetEntrySetImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AssetEntrySet> toCacheModel() {
				return _nullAssetEntrySetCacheModel;
			}
		};

	private static CacheModel<AssetEntrySet> _nullAssetEntrySetCacheModel = new CacheModel<AssetEntrySet>() {
			@Override
			public AssetEntrySet toEntityModel() {
				return _nullAssetEntrySet;
			}
		};
}