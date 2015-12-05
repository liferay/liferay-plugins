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

package com.liferay.sync.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.persistence.CompanyProvider;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.sync.NoSuchDLObjectException;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.model.impl.SyncDLObjectImpl;
import com.liferay.sync.model.impl.SyncDLObjectModelImpl;
import com.liferay.sync.service.persistence.SyncDLObjectPersistence;

import java.io.Serializable;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the sync d l object service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLObjectPersistence
 * @see com.liferay.sync.service.persistence.SyncDLObjectUtil
 * @generated
 */
@ProviderType
public class SyncDLObjectPersistenceImpl extends BasePersistenceImpl<SyncDLObject>
	implements SyncDLObjectPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SyncDLObjectUtil} to access the sync d l object persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SyncDLObjectImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, SyncDLObjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, SyncDLObjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_M_R = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, SyncDLObjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByM_R",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_M_R = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByM_R",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63;.
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @return the matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByM_R(long modifiedTime, long repositoryId) {
		return findByM_R(modifiedTime, repositoryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @return the range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByM_R(long modifiedTime, long repositoryId,
		int start, int end) {
		return findByM_R(modifiedTime, repositoryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByM_R(long modifiedTime, long repositoryId,
		int start, int end, OrderByComparator<SyncDLObject> orderByComparator) {
		return findByM_R(modifiedTime, repositoryId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByM_R(long modifiedTime, long repositoryId,
		int start, int end, OrderByComparator<SyncDLObject> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_M_R;
		finderArgs = new Object[] {
				modifiedTime, repositoryId,
				
				start, end, orderByComparator
			};

		List<SyncDLObject> list = null;

		if (retrieveFromCache) {
			list = (List<SyncDLObject>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncDLObject syncDLObject : list) {
					if ((modifiedTime >= syncDLObject.getModifiedTime()) ||
							(repositoryId != syncDLObject.getRepositoryId())) {
						list = null;

						break;
					}
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

			query.append(_SQL_SELECT_SYNCDLOBJECT_WHERE);

			query.append(_FINDER_COLUMN_M_R_MODIFIEDTIME_2);

			query.append(_FINDER_COLUMN_M_R_REPOSITORYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncDLObjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(modifiedTime);

				qPos.add(repositoryId);

				if (!pagination) {
					list = (List<SyncDLObject>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncDLObject>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first sync d l object in the ordered set where modifiedTime &gt; &#63; and repositoryId = &#63;.
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync d l object
	 * @throws NoSuchDLObjectException if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject findByM_R_First(long modifiedTime, long repositoryId,
		OrderByComparator<SyncDLObject> orderByComparator)
		throws NoSuchDLObjectException {
		SyncDLObject syncDLObject = fetchByM_R_First(modifiedTime,
				repositoryId, orderByComparator);

		if (syncDLObject != null) {
			return syncDLObject;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedTime=");
		msg.append(modifiedTime);

		msg.append(", repositoryId=");
		msg.append(repositoryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDLObjectException(msg.toString());
	}

	/**
	 * Returns the first sync d l object in the ordered set where modifiedTime &gt; &#63; and repositoryId = &#63;.
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject fetchByM_R_First(long modifiedTime, long repositoryId,
		OrderByComparator<SyncDLObject> orderByComparator) {
		List<SyncDLObject> list = findByM_R(modifiedTime, repositoryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync d l object in the ordered set where modifiedTime &gt; &#63; and repositoryId = &#63;.
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync d l object
	 * @throws NoSuchDLObjectException if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject findByM_R_Last(long modifiedTime, long repositoryId,
		OrderByComparator<SyncDLObject> orderByComparator)
		throws NoSuchDLObjectException {
		SyncDLObject syncDLObject = fetchByM_R_Last(modifiedTime, repositoryId,
				orderByComparator);

		if (syncDLObject != null) {
			return syncDLObject;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedTime=");
		msg.append(modifiedTime);

		msg.append(", repositoryId=");
		msg.append(repositoryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDLObjectException(msg.toString());
	}

	/**
	 * Returns the last sync d l object in the ordered set where modifiedTime &gt; &#63; and repositoryId = &#63;.
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject fetchByM_R_Last(long modifiedTime, long repositoryId,
		OrderByComparator<SyncDLObject> orderByComparator) {
		int count = countByM_R(modifiedTime, repositoryId);

		if (count == 0) {
			return null;
		}

		List<SyncDLObject> list = findByM_R(modifiedTime, repositoryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync d l objects before and after the current sync d l object in the ordered set where modifiedTime &gt; &#63; and repositoryId = &#63;.
	 *
	 * @param syncDLObjectId the primary key of the current sync d l object
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync d l object
	 * @throws NoSuchDLObjectException if a sync d l object with the primary key could not be found
	 */
	@Override
	public SyncDLObject[] findByM_R_PrevAndNext(long syncDLObjectId,
		long modifiedTime, long repositoryId,
		OrderByComparator<SyncDLObject> orderByComparator)
		throws NoSuchDLObjectException {
		SyncDLObject syncDLObject = findByPrimaryKey(syncDLObjectId);

		Session session = null;

		try {
			session = openSession();

			SyncDLObject[] array = new SyncDLObjectImpl[3];

			array[0] = getByM_R_PrevAndNext(session, syncDLObject,
					modifiedTime, repositoryId, orderByComparator, true);

			array[1] = syncDLObject;

			array[2] = getByM_R_PrevAndNext(session, syncDLObject,
					modifiedTime, repositoryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SyncDLObject getByM_R_PrevAndNext(Session session,
		SyncDLObject syncDLObject, long modifiedTime, long repositoryId,
		OrderByComparator<SyncDLObject> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SYNCDLOBJECT_WHERE);

		query.append(_FINDER_COLUMN_M_R_MODIFIEDTIME_2);

		query.append(_FINDER_COLUMN_M_R_REPOSITORYID_2);

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
			query.append(SyncDLObjectModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(modifiedTime);

		qPos.add(repositoryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncDLObject);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncDLObject> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; from the database.
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 */
	@Override
	public void removeByM_R(long modifiedTime, long repositoryId) {
		for (SyncDLObject syncDLObject : findByM_R(modifiedTime, repositoryId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncDLObject);
		}
	}

	/**
	 * Returns the number of sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63;.
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @return the number of matching sync d l objects
	 */
	@Override
	public int countByM_R(long modifiedTime, long repositoryId) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_M_R;

		Object[] finderArgs = new Object[] { modifiedTime, repositoryId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SYNCDLOBJECT_WHERE);

			query.append(_FINDER_COLUMN_M_R_MODIFIEDTIME_2);

			query.append(_FINDER_COLUMN_M_R_REPOSITORYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(modifiedTime);

				qPos.add(repositoryId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_M_R_MODIFIEDTIME_2 = "syncDLObject.modifiedTime > ? AND ";
	private static final String _FINDER_COLUMN_M_R_REPOSITORYID_2 = "syncDLObject.repositoryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_P = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, SyncDLObjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByR_P",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_P = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, SyncDLObjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByR_P",
			new String[] { Long.class.getName(), Long.class.getName() },
			SyncDLObjectModelImpl.REPOSITORYID_COLUMN_BITMASK |
			SyncDLObjectModelImpl.PARENTFOLDERID_COLUMN_BITMASK |
			SyncDLObjectModelImpl.MODIFIEDTIME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_P = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_P",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63;.
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @return the matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByR_P(long repositoryId, long parentFolderId) {
		return findByR_P(repositoryId, parentFolderId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @return the range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByR_P(long repositoryId, long parentFolderId,
		int start, int end) {
		return findByR_P(repositoryId, parentFolderId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByR_P(long repositoryId, long parentFolderId,
		int start, int end, OrderByComparator<SyncDLObject> orderByComparator) {
		return findByR_P(repositoryId, parentFolderId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByR_P(long repositoryId, long parentFolderId,
		int start, int end, OrderByComparator<SyncDLObject> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_P;
			finderArgs = new Object[] { repositoryId, parentFolderId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_P;
			finderArgs = new Object[] {
					repositoryId, parentFolderId,
					
					start, end, orderByComparator
				};
		}

		List<SyncDLObject> list = null;

		if (retrieveFromCache) {
			list = (List<SyncDLObject>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncDLObject syncDLObject : list) {
					if ((repositoryId != syncDLObject.getRepositoryId()) ||
							(parentFolderId != syncDLObject.getParentFolderId())) {
						list = null;

						break;
					}
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

			query.append(_SQL_SELECT_SYNCDLOBJECT_WHERE);

			query.append(_FINDER_COLUMN_R_P_REPOSITORYID_2);

			query.append(_FINDER_COLUMN_R_P_PARENTFOLDERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncDLObjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(repositoryId);

				qPos.add(parentFolderId);

				if (!pagination) {
					list = (List<SyncDLObject>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncDLObject>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first sync d l object in the ordered set where repositoryId = &#63; and parentFolderId = &#63;.
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync d l object
	 * @throws NoSuchDLObjectException if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject findByR_P_First(long repositoryId, long parentFolderId,
		OrderByComparator<SyncDLObject> orderByComparator)
		throws NoSuchDLObjectException {
		SyncDLObject syncDLObject = fetchByR_P_First(repositoryId,
				parentFolderId, orderByComparator);

		if (syncDLObject != null) {
			return syncDLObject;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("repositoryId=");
		msg.append(repositoryId);

		msg.append(", parentFolderId=");
		msg.append(parentFolderId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDLObjectException(msg.toString());
	}

	/**
	 * Returns the first sync d l object in the ordered set where repositoryId = &#63; and parentFolderId = &#63;.
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject fetchByR_P_First(long repositoryId,
		long parentFolderId, OrderByComparator<SyncDLObject> orderByComparator) {
		List<SyncDLObject> list = findByR_P(repositoryId, parentFolderId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync d l object in the ordered set where repositoryId = &#63; and parentFolderId = &#63;.
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync d l object
	 * @throws NoSuchDLObjectException if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject findByR_P_Last(long repositoryId, long parentFolderId,
		OrderByComparator<SyncDLObject> orderByComparator)
		throws NoSuchDLObjectException {
		SyncDLObject syncDLObject = fetchByR_P_Last(repositoryId,
				parentFolderId, orderByComparator);

		if (syncDLObject != null) {
			return syncDLObject;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("repositoryId=");
		msg.append(repositoryId);

		msg.append(", parentFolderId=");
		msg.append(parentFolderId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDLObjectException(msg.toString());
	}

	/**
	 * Returns the last sync d l object in the ordered set where repositoryId = &#63; and parentFolderId = &#63;.
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject fetchByR_P_Last(long repositoryId, long parentFolderId,
		OrderByComparator<SyncDLObject> orderByComparator) {
		int count = countByR_P(repositoryId, parentFolderId);

		if (count == 0) {
			return null;
		}

		List<SyncDLObject> list = findByR_P(repositoryId, parentFolderId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync d l objects before and after the current sync d l object in the ordered set where repositoryId = &#63; and parentFolderId = &#63;.
	 *
	 * @param syncDLObjectId the primary key of the current sync d l object
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync d l object
	 * @throws NoSuchDLObjectException if a sync d l object with the primary key could not be found
	 */
	@Override
	public SyncDLObject[] findByR_P_PrevAndNext(long syncDLObjectId,
		long repositoryId, long parentFolderId,
		OrderByComparator<SyncDLObject> orderByComparator)
		throws NoSuchDLObjectException {
		SyncDLObject syncDLObject = findByPrimaryKey(syncDLObjectId);

		Session session = null;

		try {
			session = openSession();

			SyncDLObject[] array = new SyncDLObjectImpl[3];

			array[0] = getByR_P_PrevAndNext(session, syncDLObject,
					repositoryId, parentFolderId, orderByComparator, true);

			array[1] = syncDLObject;

			array[2] = getByR_P_PrevAndNext(session, syncDLObject,
					repositoryId, parentFolderId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SyncDLObject getByR_P_PrevAndNext(Session session,
		SyncDLObject syncDLObject, long repositoryId, long parentFolderId,
		OrderByComparator<SyncDLObject> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SYNCDLOBJECT_WHERE);

		query.append(_FINDER_COLUMN_R_P_REPOSITORYID_2);

		query.append(_FINDER_COLUMN_R_P_PARENTFOLDERID_2);

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
			query.append(SyncDLObjectModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(repositoryId);

		qPos.add(parentFolderId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncDLObject);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncDLObject> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63; from the database.
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 */
	@Override
	public void removeByR_P(long repositoryId, long parentFolderId) {
		for (SyncDLObject syncDLObject : findByR_P(repositoryId,
				parentFolderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncDLObject);
		}
	}

	/**
	 * Returns the number of sync d l objects where repositoryId = &#63; and parentFolderId = &#63;.
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @return the number of matching sync d l objects
	 */
	@Override
	public int countByR_P(long repositoryId, long parentFolderId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_R_P;

		Object[] finderArgs = new Object[] { repositoryId, parentFolderId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SYNCDLOBJECT_WHERE);

			query.append(_FINDER_COLUMN_R_P_REPOSITORYID_2);

			query.append(_FINDER_COLUMN_R_P_PARENTFOLDERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(repositoryId);

				qPos.add(parentFolderId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_R_P_REPOSITORYID_2 = "syncDLObject.repositoryId = ? AND ";
	private static final String _FINDER_COLUMN_R_P_PARENTFOLDERID_2 = "syncDLObject.parentFolderId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_T = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, SyncDLObjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByR_T",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_T = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, SyncDLObjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByR_T",
			new String[] { Long.class.getName(), String.class.getName() },
			SyncDLObjectModelImpl.REPOSITORYID_COLUMN_BITMASK |
			SyncDLObjectModelImpl.TYPE_COLUMN_BITMASK |
			SyncDLObjectModelImpl.MODIFIEDTIME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_T = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_T",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the sync d l objects where repositoryId = &#63; and type = &#63;.
	 *
	 * @param repositoryId the repository ID
	 * @param type the type
	 * @return the matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByR_T(long repositoryId, String type) {
		return findByR_T(repositoryId, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync d l objects where repositoryId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param repositoryId the repository ID
	 * @param type the type
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @return the range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByR_T(long repositoryId, String type,
		int start, int end) {
		return findByR_T(repositoryId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync d l objects where repositoryId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param repositoryId the repository ID
	 * @param type the type
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByR_T(long repositoryId, String type,
		int start, int end, OrderByComparator<SyncDLObject> orderByComparator) {
		return findByR_T(repositoryId, type, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync d l objects where repositoryId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param repositoryId the repository ID
	 * @param type the type
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByR_T(long repositoryId, String type,
		int start, int end, OrderByComparator<SyncDLObject> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_T;
			finderArgs = new Object[] { repositoryId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_T;
			finderArgs = new Object[] {
					repositoryId, type,
					
					start, end, orderByComparator
				};
		}

		List<SyncDLObject> list = null;

		if (retrieveFromCache) {
			list = (List<SyncDLObject>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncDLObject syncDLObject : list) {
					if ((repositoryId != syncDLObject.getRepositoryId()) ||
							!Validator.equals(type, syncDLObject.getType())) {
						list = null;

						break;
					}
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

			query.append(_SQL_SELECT_SYNCDLOBJECT_WHERE);

			query.append(_FINDER_COLUMN_R_T_REPOSITORYID_2);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_R_T_TYPE_1);
			}
			else if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_R_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_R_T_TYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncDLObjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(repositoryId);

				if (bindType) {
					qPos.add(type);
				}

				if (!pagination) {
					list = (List<SyncDLObject>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncDLObject>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first sync d l object in the ordered set where repositoryId = &#63; and type = &#63;.
	 *
	 * @param repositoryId the repository ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync d l object
	 * @throws NoSuchDLObjectException if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject findByR_T_First(long repositoryId, String type,
		OrderByComparator<SyncDLObject> orderByComparator)
		throws NoSuchDLObjectException {
		SyncDLObject syncDLObject = fetchByR_T_First(repositoryId, type,
				orderByComparator);

		if (syncDLObject != null) {
			return syncDLObject;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("repositoryId=");
		msg.append(repositoryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDLObjectException(msg.toString());
	}

	/**
	 * Returns the first sync d l object in the ordered set where repositoryId = &#63; and type = &#63;.
	 *
	 * @param repositoryId the repository ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject fetchByR_T_First(long repositoryId, String type,
		OrderByComparator<SyncDLObject> orderByComparator) {
		List<SyncDLObject> list = findByR_T(repositoryId, type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync d l object in the ordered set where repositoryId = &#63; and type = &#63;.
	 *
	 * @param repositoryId the repository ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync d l object
	 * @throws NoSuchDLObjectException if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject findByR_T_Last(long repositoryId, String type,
		OrderByComparator<SyncDLObject> orderByComparator)
		throws NoSuchDLObjectException {
		SyncDLObject syncDLObject = fetchByR_T_Last(repositoryId, type,
				orderByComparator);

		if (syncDLObject != null) {
			return syncDLObject;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("repositoryId=");
		msg.append(repositoryId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDLObjectException(msg.toString());
	}

	/**
	 * Returns the last sync d l object in the ordered set where repositoryId = &#63; and type = &#63;.
	 *
	 * @param repositoryId the repository ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject fetchByR_T_Last(long repositoryId, String type,
		OrderByComparator<SyncDLObject> orderByComparator) {
		int count = countByR_T(repositoryId, type);

		if (count == 0) {
			return null;
		}

		List<SyncDLObject> list = findByR_T(repositoryId, type, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync d l objects before and after the current sync d l object in the ordered set where repositoryId = &#63; and type = &#63;.
	 *
	 * @param syncDLObjectId the primary key of the current sync d l object
	 * @param repositoryId the repository ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync d l object
	 * @throws NoSuchDLObjectException if a sync d l object with the primary key could not be found
	 */
	@Override
	public SyncDLObject[] findByR_T_PrevAndNext(long syncDLObjectId,
		long repositoryId, String type,
		OrderByComparator<SyncDLObject> orderByComparator)
		throws NoSuchDLObjectException {
		SyncDLObject syncDLObject = findByPrimaryKey(syncDLObjectId);

		Session session = null;

		try {
			session = openSession();

			SyncDLObject[] array = new SyncDLObjectImpl[3];

			array[0] = getByR_T_PrevAndNext(session, syncDLObject,
					repositoryId, type, orderByComparator, true);

			array[1] = syncDLObject;

			array[2] = getByR_T_PrevAndNext(session, syncDLObject,
					repositoryId, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SyncDLObject getByR_T_PrevAndNext(Session session,
		SyncDLObject syncDLObject, long repositoryId, String type,
		OrderByComparator<SyncDLObject> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SYNCDLOBJECT_WHERE);

		query.append(_FINDER_COLUMN_R_T_REPOSITORYID_2);

		boolean bindType = false;

		if (type == null) {
			query.append(_FINDER_COLUMN_R_T_TYPE_1);
		}
		else if (type.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_R_T_TYPE_3);
		}
		else {
			bindType = true;

			query.append(_FINDER_COLUMN_R_T_TYPE_2);
		}

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
			query.append(SyncDLObjectModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(repositoryId);

		if (bindType) {
			qPos.add(type);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncDLObject);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncDLObject> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync d l objects where repositoryId = &#63; and type = &#63; from the database.
	 *
	 * @param repositoryId the repository ID
	 * @param type the type
	 */
	@Override
	public void removeByR_T(long repositoryId, String type) {
		for (SyncDLObject syncDLObject : findByR_T(repositoryId, type,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncDLObject);
		}
	}

	/**
	 * Returns the number of sync d l objects where repositoryId = &#63; and type = &#63;.
	 *
	 * @param repositoryId the repository ID
	 * @param type the type
	 * @return the number of matching sync d l objects
	 */
	@Override
	public int countByR_T(long repositoryId, String type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_R_T;

		Object[] finderArgs = new Object[] { repositoryId, type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SYNCDLOBJECT_WHERE);

			query.append(_FINDER_COLUMN_R_T_REPOSITORYID_2);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_R_T_TYPE_1);
			}
			else if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_R_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_R_T_TYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(repositoryId);

				if (bindType) {
					qPos.add(type);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_R_T_REPOSITORYID_2 = "syncDLObject.repositoryId = ? AND ";
	private static final String _FINDER_COLUMN_R_T_TYPE_1 = "syncDLObject.type IS NULL";
	private static final String _FINDER_COLUMN_R_T_TYPE_2 = "syncDLObject.type = ?";
	private static final String _FINDER_COLUMN_R_T_TYPE_3 = "(syncDLObject.type IS NULL OR syncDLObject.type = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_V_T = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, SyncDLObjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByV_T",
			new String[] {
				String.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_V_T = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, SyncDLObjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByV_T",
			new String[] { String.class.getName(), String.class.getName() },
			SyncDLObjectModelImpl.VERSION_COLUMN_BITMASK |
			SyncDLObjectModelImpl.TYPE_COLUMN_BITMASK |
			SyncDLObjectModelImpl.MODIFIEDTIME_COLUMN_BITMASK |
			SyncDLObjectModelImpl.REPOSITORYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_V_T = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByV_T",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns all the sync d l objects where version = &#63; and type = &#63;.
	 *
	 * @param version the version
	 * @param type the type
	 * @return the matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByV_T(String version, String type) {
		return findByV_T(version, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the sync d l objects where version = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param version the version
	 * @param type the type
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @return the range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByV_T(String version, String type, int start,
		int end) {
		return findByV_T(version, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync d l objects where version = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param version the version
	 * @param type the type
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByV_T(String version, String type, int start,
		int end, OrderByComparator<SyncDLObject> orderByComparator) {
		return findByV_T(version, type, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync d l objects where version = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param version the version
	 * @param type the type
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByV_T(String version, String type, int start,
		int end, OrderByComparator<SyncDLObject> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_V_T;
			finderArgs = new Object[] { version, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_V_T;
			finderArgs = new Object[] {
					version, type,
					
					start, end, orderByComparator
				};
		}

		List<SyncDLObject> list = null;

		if (retrieveFromCache) {
			list = (List<SyncDLObject>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncDLObject syncDLObject : list) {
					if (!Validator.equals(version, syncDLObject.getVersion()) ||
							!Validator.equals(type, syncDLObject.getType())) {
						list = null;

						break;
					}
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

			query.append(_SQL_SELECT_SYNCDLOBJECT_WHERE);

			boolean bindVersion = false;

			if (version == null) {
				query.append(_FINDER_COLUMN_V_T_VERSION_1);
			}
			else if (version.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_V_T_VERSION_3);
			}
			else {
				bindVersion = true;

				query.append(_FINDER_COLUMN_V_T_VERSION_2);
			}

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_V_T_TYPE_1);
			}
			else if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_V_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_V_T_TYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncDLObjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindVersion) {
					qPos.add(version);
				}

				if (bindType) {
					qPos.add(type);
				}

				if (!pagination) {
					list = (List<SyncDLObject>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncDLObject>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first sync d l object in the ordered set where version = &#63; and type = &#63;.
	 *
	 * @param version the version
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync d l object
	 * @throws NoSuchDLObjectException if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject findByV_T_First(String version, String type,
		OrderByComparator<SyncDLObject> orderByComparator)
		throws NoSuchDLObjectException {
		SyncDLObject syncDLObject = fetchByV_T_First(version, type,
				orderByComparator);

		if (syncDLObject != null) {
			return syncDLObject;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("version=");
		msg.append(version);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDLObjectException(msg.toString());
	}

	/**
	 * Returns the first sync d l object in the ordered set where version = &#63; and type = &#63;.
	 *
	 * @param version the version
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject fetchByV_T_First(String version, String type,
		OrderByComparator<SyncDLObject> orderByComparator) {
		List<SyncDLObject> list = findByV_T(version, type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync d l object in the ordered set where version = &#63; and type = &#63;.
	 *
	 * @param version the version
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync d l object
	 * @throws NoSuchDLObjectException if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject findByV_T_Last(String version, String type,
		OrderByComparator<SyncDLObject> orderByComparator)
		throws NoSuchDLObjectException {
		SyncDLObject syncDLObject = fetchByV_T_Last(version, type,
				orderByComparator);

		if (syncDLObject != null) {
			return syncDLObject;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("version=");
		msg.append(version);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDLObjectException(msg.toString());
	}

	/**
	 * Returns the last sync d l object in the ordered set where version = &#63; and type = &#63;.
	 *
	 * @param version the version
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject fetchByV_T_Last(String version, String type,
		OrderByComparator<SyncDLObject> orderByComparator) {
		int count = countByV_T(version, type);

		if (count == 0) {
			return null;
		}

		List<SyncDLObject> list = findByV_T(version, type, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync d l objects before and after the current sync d l object in the ordered set where version = &#63; and type = &#63;.
	 *
	 * @param syncDLObjectId the primary key of the current sync d l object
	 * @param version the version
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync d l object
	 * @throws NoSuchDLObjectException if a sync d l object with the primary key could not be found
	 */
	@Override
	public SyncDLObject[] findByV_T_PrevAndNext(long syncDLObjectId,
		String version, String type,
		OrderByComparator<SyncDLObject> orderByComparator)
		throws NoSuchDLObjectException {
		SyncDLObject syncDLObject = findByPrimaryKey(syncDLObjectId);

		Session session = null;

		try {
			session = openSession();

			SyncDLObject[] array = new SyncDLObjectImpl[3];

			array[0] = getByV_T_PrevAndNext(session, syncDLObject, version,
					type, orderByComparator, true);

			array[1] = syncDLObject;

			array[2] = getByV_T_PrevAndNext(session, syncDLObject, version,
					type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SyncDLObject getByV_T_PrevAndNext(Session session,
		SyncDLObject syncDLObject, String version, String type,
		OrderByComparator<SyncDLObject> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SYNCDLOBJECT_WHERE);

		boolean bindVersion = false;

		if (version == null) {
			query.append(_FINDER_COLUMN_V_T_VERSION_1);
		}
		else if (version.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_V_T_VERSION_3);
		}
		else {
			bindVersion = true;

			query.append(_FINDER_COLUMN_V_T_VERSION_2);
		}

		boolean bindType = false;

		if (type == null) {
			query.append(_FINDER_COLUMN_V_T_TYPE_1);
		}
		else if (type.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_V_T_TYPE_3);
		}
		else {
			bindType = true;

			query.append(_FINDER_COLUMN_V_T_TYPE_2);
		}

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
			query.append(SyncDLObjectModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindVersion) {
			qPos.add(version);
		}

		if (bindType) {
			qPos.add(type);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncDLObject);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncDLObject> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync d l objects where version = &#63; and type = &#63; from the database.
	 *
	 * @param version the version
	 * @param type the type
	 */
	@Override
	public void removeByV_T(String version, String type) {
		for (SyncDLObject syncDLObject : findByV_T(version, type,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncDLObject);
		}
	}

	/**
	 * Returns the number of sync d l objects where version = &#63; and type = &#63;.
	 *
	 * @param version the version
	 * @param type the type
	 * @return the number of matching sync d l objects
	 */
	@Override
	public int countByV_T(String version, String type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_V_T;

		Object[] finderArgs = new Object[] { version, type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SYNCDLOBJECT_WHERE);

			boolean bindVersion = false;

			if (version == null) {
				query.append(_FINDER_COLUMN_V_T_VERSION_1);
			}
			else if (version.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_V_T_VERSION_3);
			}
			else {
				bindVersion = true;

				query.append(_FINDER_COLUMN_V_T_VERSION_2);
			}

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_V_T_TYPE_1);
			}
			else if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_V_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_V_T_TYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindVersion) {
					qPos.add(version);
				}

				if (bindType) {
					qPos.add(type);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_V_T_VERSION_1 = "syncDLObject.version IS NULL AND ";
	private static final String _FINDER_COLUMN_V_T_VERSION_2 = "syncDLObject.version = ? AND ";
	private static final String _FINDER_COLUMN_V_T_VERSION_3 = "(syncDLObject.version IS NULL OR syncDLObject.version = '') AND ";
	private static final String _FINDER_COLUMN_V_T_TYPE_1 = "syncDLObject.type IS NULL";
	private static final String _FINDER_COLUMN_V_T_TYPE_2 = "syncDLObject.type = ?";
	private static final String _FINDER_COLUMN_V_T_TYPE_3 = "(syncDLObject.type IS NULL OR syncDLObject.type = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_T_T = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, SyncDLObjectImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByT_T",
			new String[] { String.class.getName(), Long.class.getName() },
			SyncDLObjectModelImpl.TYPE_COLUMN_BITMASK |
			SyncDLObjectModelImpl.TYPEPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_T_T = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByT_T",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the sync d l object where type = &#63; and typePK = &#63; or throws a {@link NoSuchDLObjectException} if it could not be found.
	 *
	 * @param type the type
	 * @param typePK the type p k
	 * @return the matching sync d l object
	 * @throws NoSuchDLObjectException if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject findByT_T(String type, long typePK)
		throws NoSuchDLObjectException {
		SyncDLObject syncDLObject = fetchByT_T(type, typePK);

		if (syncDLObject == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("type=");
			msg.append(type);

			msg.append(", typePK=");
			msg.append(typePK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchDLObjectException(msg.toString());
		}

		return syncDLObject;
	}

	/**
	 * Returns the sync d l object where type = &#63; and typePK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param type the type
	 * @param typePK the type p k
	 * @return the matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject fetchByT_T(String type, long typePK) {
		return fetchByT_T(type, typePK, true);
	}

	/**
	 * Returns the sync d l object where type = &#63; and typePK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param type the type
	 * @param typePK the type p k
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject fetchByT_T(String type, long typePK,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { type, typePK };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_T_T,
					finderArgs, this);
		}

		if (result instanceof SyncDLObject) {
			SyncDLObject syncDLObject = (SyncDLObject)result;

			if (!Validator.equals(type, syncDLObject.getType()) ||
					(typePK != syncDLObject.getTypePK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SYNCDLOBJECT_WHERE);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_T_T_TYPE_1);
			}
			else if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_T_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_T_T_TYPE_2);
			}

			query.append(_FINDER_COLUMN_T_T_TYPEPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindType) {
					qPos.add(type);
				}

				qPos.add(typePK);

				List<SyncDLObject> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_T_T, finderArgs,
						list);
				}
				else {
					SyncDLObject syncDLObject = list.get(0);

					result = syncDLObject;

					cacheResult(syncDLObject);

					if ((syncDLObject.getType() == null) ||
							!syncDLObject.getType().equals(type) ||
							(syncDLObject.getTypePK() != typePK)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_T_T,
							finderArgs, syncDLObject);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_T_T, finderArgs);

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
			return (SyncDLObject)result;
		}
	}

	/**
	 * Removes the sync d l object where type = &#63; and typePK = &#63; from the database.
	 *
	 * @param type the type
	 * @param typePK the type p k
	 * @return the sync d l object that was removed
	 */
	@Override
	public SyncDLObject removeByT_T(String type, long typePK)
		throws NoSuchDLObjectException {
		SyncDLObject syncDLObject = findByT_T(type, typePK);

		return remove(syncDLObject);
	}

	/**
	 * Returns the number of sync d l objects where type = &#63; and typePK = &#63;.
	 *
	 * @param type the type
	 * @param typePK the type p k
	 * @return the number of matching sync d l objects
	 */
	@Override
	public int countByT_T(String type, long typePK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_T_T;

		Object[] finderArgs = new Object[] { type, typePK };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SYNCDLOBJECT_WHERE);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_T_T_TYPE_1);
			}
			else if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_T_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_T_T_TYPE_2);
			}

			query.append(_FINDER_COLUMN_T_T_TYPEPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindType) {
					qPos.add(type);
				}

				qPos.add(typePK);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_T_T_TYPE_1 = "syncDLObject.type IS NULL AND ";
	private static final String _FINDER_COLUMN_T_T_TYPE_2 = "syncDLObject.type = ? AND ";
	private static final String _FINDER_COLUMN_T_T_TYPE_3 = "(syncDLObject.type IS NULL OR syncDLObject.type = '') AND ";
	private static final String _FINDER_COLUMN_T_T_TYPEPK_2 = "syncDLObject.typePK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_M_R_NOTE = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, SyncDLObjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByM_R_NotE",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_M_R_NOTE =
		new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByM_R_NotE",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63;.
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param event the event
	 * @return the matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByM_R_NotE(long modifiedTime,
		long repositoryId, String event) {
		return findByM_R_NotE(modifiedTime, repositoryId, event,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param event the event
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @return the range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByM_R_NotE(long modifiedTime,
		long repositoryId, String event, int start, int end) {
		return findByM_R_NotE(modifiedTime, repositoryId, event, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param event the event
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByM_R_NotE(long modifiedTime,
		long repositoryId, String event, int start, int end,
		OrderByComparator<SyncDLObject> orderByComparator) {
		return findByM_R_NotE(modifiedTime, repositoryId, event, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param event the event
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByM_R_NotE(long modifiedTime,
		long repositoryId, String event, int start, int end,
		OrderByComparator<SyncDLObject> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_M_R_NOTE;
		finderArgs = new Object[] {
				modifiedTime, repositoryId, event,
				
				start, end, orderByComparator
			};

		List<SyncDLObject> list = null;

		if (retrieveFromCache) {
			list = (List<SyncDLObject>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncDLObject syncDLObject : list) {
					if ((modifiedTime >= syncDLObject.getModifiedTime()) ||
							(repositoryId != syncDLObject.getRepositoryId()) ||
							Validator.equals(event, syncDLObject.getEvent())) {
						list = null;

						break;
					}
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

			query.append(_SQL_SELECT_SYNCDLOBJECT_WHERE);

			query.append(_FINDER_COLUMN_M_R_NOTE_MODIFIEDTIME_2);

			query.append(_FINDER_COLUMN_M_R_NOTE_REPOSITORYID_2);

			boolean bindEvent = false;

			if (event == null) {
				query.append(_FINDER_COLUMN_M_R_NOTE_EVENT_1);
			}
			else if (event.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_M_R_NOTE_EVENT_3);
			}
			else {
				bindEvent = true;

				query.append(_FINDER_COLUMN_M_R_NOTE_EVENT_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncDLObjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(modifiedTime);

				qPos.add(repositoryId);

				if (bindEvent) {
					qPos.add(event);
				}

				if (!pagination) {
					list = (List<SyncDLObject>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncDLObject>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first sync d l object in the ordered set where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63;.
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param event the event
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync d l object
	 * @throws NoSuchDLObjectException if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject findByM_R_NotE_First(long modifiedTime,
		long repositoryId, String event,
		OrderByComparator<SyncDLObject> orderByComparator)
		throws NoSuchDLObjectException {
		SyncDLObject syncDLObject = fetchByM_R_NotE_First(modifiedTime,
				repositoryId, event, orderByComparator);

		if (syncDLObject != null) {
			return syncDLObject;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedTime=");
		msg.append(modifiedTime);

		msg.append(", repositoryId=");
		msg.append(repositoryId);

		msg.append(", event=");
		msg.append(event);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDLObjectException(msg.toString());
	}

	/**
	 * Returns the first sync d l object in the ordered set where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63;.
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param event the event
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject fetchByM_R_NotE_First(long modifiedTime,
		long repositoryId, String event,
		OrderByComparator<SyncDLObject> orderByComparator) {
		List<SyncDLObject> list = findByM_R_NotE(modifiedTime, repositoryId,
				event, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync d l object in the ordered set where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63;.
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param event the event
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync d l object
	 * @throws NoSuchDLObjectException if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject findByM_R_NotE_Last(long modifiedTime,
		long repositoryId, String event,
		OrderByComparator<SyncDLObject> orderByComparator)
		throws NoSuchDLObjectException {
		SyncDLObject syncDLObject = fetchByM_R_NotE_Last(modifiedTime,
				repositoryId, event, orderByComparator);

		if (syncDLObject != null) {
			return syncDLObject;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedTime=");
		msg.append(modifiedTime);

		msg.append(", repositoryId=");
		msg.append(repositoryId);

		msg.append(", event=");
		msg.append(event);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDLObjectException(msg.toString());
	}

	/**
	 * Returns the last sync d l object in the ordered set where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63;.
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param event the event
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject fetchByM_R_NotE_Last(long modifiedTime,
		long repositoryId, String event,
		OrderByComparator<SyncDLObject> orderByComparator) {
		int count = countByM_R_NotE(modifiedTime, repositoryId, event);

		if (count == 0) {
			return null;
		}

		List<SyncDLObject> list = findByM_R_NotE(modifiedTime, repositoryId,
				event, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync d l objects before and after the current sync d l object in the ordered set where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63;.
	 *
	 * @param syncDLObjectId the primary key of the current sync d l object
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param event the event
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync d l object
	 * @throws NoSuchDLObjectException if a sync d l object with the primary key could not be found
	 */
	@Override
	public SyncDLObject[] findByM_R_NotE_PrevAndNext(long syncDLObjectId,
		long modifiedTime, long repositoryId, String event,
		OrderByComparator<SyncDLObject> orderByComparator)
		throws NoSuchDLObjectException {
		SyncDLObject syncDLObject = findByPrimaryKey(syncDLObjectId);

		Session session = null;

		try {
			session = openSession();

			SyncDLObject[] array = new SyncDLObjectImpl[3];

			array[0] = getByM_R_NotE_PrevAndNext(session, syncDLObject,
					modifiedTime, repositoryId, event, orderByComparator, true);

			array[1] = syncDLObject;

			array[2] = getByM_R_NotE_PrevAndNext(session, syncDLObject,
					modifiedTime, repositoryId, event, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SyncDLObject getByM_R_NotE_PrevAndNext(Session session,
		SyncDLObject syncDLObject, long modifiedTime, long repositoryId,
		String event, OrderByComparator<SyncDLObject> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SYNCDLOBJECT_WHERE);

		query.append(_FINDER_COLUMN_M_R_NOTE_MODIFIEDTIME_2);

		query.append(_FINDER_COLUMN_M_R_NOTE_REPOSITORYID_2);

		boolean bindEvent = false;

		if (event == null) {
			query.append(_FINDER_COLUMN_M_R_NOTE_EVENT_1);
		}
		else if (event.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_M_R_NOTE_EVENT_3);
		}
		else {
			bindEvent = true;

			query.append(_FINDER_COLUMN_M_R_NOTE_EVENT_2);
		}

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
			query.append(SyncDLObjectModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(modifiedTime);

		qPos.add(repositoryId);

		if (bindEvent) {
			qPos.add(event);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncDLObject);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncDLObject> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; all &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param events the events
	 * @return the matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByM_R_NotE(long modifiedTime,
		long repositoryId, String[] events) {
		return findByM_R_NotE(modifiedTime, repositoryId, events,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; all &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param events the events
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @return the range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByM_R_NotE(long modifiedTime,
		long repositoryId, String[] events, int start, int end) {
		return findByM_R_NotE(modifiedTime, repositoryId, events, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; all &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param events the events
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByM_R_NotE(long modifiedTime,
		long repositoryId, String[] events, int start, int end,
		OrderByComparator<SyncDLObject> orderByComparator) {
		return findByM_R_NotE(modifiedTime, repositoryId, events, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param event the event
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByM_R_NotE(long modifiedTime,
		long repositoryId, String[] events, int start, int end,
		OrderByComparator<SyncDLObject> orderByComparator,
		boolean retrieveFromCache) {
		if (events == null) {
			events = new String[0];
		}
		else if (events.length > 1) {
			events = ArrayUtil.distinct(events, NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(events, NULL_SAFE_STRING_COMPARATOR);
		}

		if (events.length == 1) {
			return findByM_R_NotE(modifiedTime, repositoryId, events[0], start,
				end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					modifiedTime, repositoryId, StringUtil.merge(events)
				};
		}
		else {
			finderArgs = new Object[] {
					modifiedTime, repositoryId, StringUtil.merge(events),
					
					start, end, orderByComparator
				};
		}

		List<SyncDLObject> list = null;

		if (retrieveFromCache) {
			list = (List<SyncDLObject>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_M_R_NOTE,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncDLObject syncDLObject : list) {
					if ((modifiedTime >= syncDLObject.getModifiedTime()) ||
							(repositoryId != syncDLObject.getRepositoryId()) ||
							!ArrayUtil.contains(events, syncDLObject.getEvent())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_SYNCDLOBJECT_WHERE);

			query.append(_FINDER_COLUMN_M_R_NOTE_MODIFIEDTIME_2);

			query.append(_FINDER_COLUMN_M_R_NOTE_REPOSITORYID_2);

			if (events.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < events.length; i++) {
					String event = events[i];

					if (event == null) {
						query.append(_FINDER_COLUMN_M_R_NOTE_EVENT_1);
					}
					else if (event.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_M_R_NOTE_EVENT_3);
					}
					else {
						query.append(_FINDER_COLUMN_M_R_NOTE_EVENT_2);
					}

					if ((i + 1) < events.length) {
						query.append(WHERE_AND);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncDLObjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(modifiedTime);

				qPos.add(repositoryId);

				for (String event : events) {
					if ((event != null) && !event.isEmpty()) {
						qPos.add(event);
					}
				}

				if (!pagination) {
					list = (List<SyncDLObject>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncDLObject>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_M_R_NOTE,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_M_R_NOTE,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63; from the database.
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param event the event
	 */
	@Override
	public void removeByM_R_NotE(long modifiedTime, long repositoryId,
		String event) {
		for (SyncDLObject syncDLObject : findByM_R_NotE(modifiedTime,
				repositoryId, event, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncDLObject);
		}
	}

	/**
	 * Returns the number of sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63;.
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param event the event
	 * @return the number of matching sync d l objects
	 */
	@Override
	public int countByM_R_NotE(long modifiedTime, long repositoryId,
		String event) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_M_R_NOTE;

		Object[] finderArgs = new Object[] { modifiedTime, repositoryId, event };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SYNCDLOBJECT_WHERE);

			query.append(_FINDER_COLUMN_M_R_NOTE_MODIFIEDTIME_2);

			query.append(_FINDER_COLUMN_M_R_NOTE_REPOSITORYID_2);

			boolean bindEvent = false;

			if (event == null) {
				query.append(_FINDER_COLUMN_M_R_NOTE_EVENT_1);
			}
			else if (event.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_M_R_NOTE_EVENT_3);
			}
			else {
				bindEvent = true;

				query.append(_FINDER_COLUMN_M_R_NOTE_EVENT_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(modifiedTime);

				qPos.add(repositoryId);

				if (bindEvent) {
					qPos.add(event);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; all &#63;.
	 *
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param events the events
	 * @return the number of matching sync d l objects
	 */
	@Override
	public int countByM_R_NotE(long modifiedTime, long repositoryId,
		String[] events) {
		if (events == null) {
			events = new String[0];
		}
		else if (events.length > 1) {
			events = ArrayUtil.distinct(events, NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(events, NULL_SAFE_STRING_COMPARATOR);
		}

		Object[] finderArgs = new Object[] {
				modifiedTime, repositoryId, StringUtil.merge(events)
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_M_R_NOTE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_SYNCDLOBJECT_WHERE);

			query.append(_FINDER_COLUMN_M_R_NOTE_MODIFIEDTIME_2);

			query.append(_FINDER_COLUMN_M_R_NOTE_REPOSITORYID_2);

			if (events.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < events.length; i++) {
					String event = events[i];

					if (event == null) {
						query.append(_FINDER_COLUMN_M_R_NOTE_EVENT_1);
					}
					else if (event.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_M_R_NOTE_EVENT_3);
					}
					else {
						query.append(_FINDER_COLUMN_M_R_NOTE_EVENT_2);
					}

					if ((i + 1) < events.length) {
						query.append(WHERE_AND);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(modifiedTime);

				qPos.add(repositoryId);

				for (String event : events) {
					if ((event != null) && !event.isEmpty()) {
						qPos.add(event);
					}
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_M_R_NOTE,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_M_R_NOTE,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_M_R_NOTE_MODIFIEDTIME_2 = "syncDLObject.modifiedTime > ? AND ";
	private static final String _FINDER_COLUMN_M_R_NOTE_REPOSITORYID_2 = "syncDLObject.repositoryId = ? AND ";
	private static final String _FINDER_COLUMN_M_R_NOTE_EVENT_1 = "syncDLObject.event IS NOT NULL";
	private static final String _FINDER_COLUMN_M_R_NOTE_EVENT_2 = "syncDLObject.event != ?";
	private static final String _FINDER_COLUMN_M_R_NOTE_EVENT_3 = "(syncDLObject.event IS NULL OR syncDLObject.event != '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_P_T = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, SyncDLObjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByR_P_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_P_T = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, SyncDLObjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByR_P_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			SyncDLObjectModelImpl.REPOSITORYID_COLUMN_BITMASK |
			SyncDLObjectModelImpl.PARENTFOLDERID_COLUMN_BITMASK |
			SyncDLObjectModelImpl.TYPE_COLUMN_BITMASK |
			SyncDLObjectModelImpl.MODIFIEDTIME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_P_T = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_P_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_P_T = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByR_P_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = &#63;.
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param type the type
	 * @return the matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByR_P_T(long repositoryId,
		long parentFolderId, String type) {
		return findByR_P_T(repositoryId, parentFolderId, type,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param type the type
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @return the range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByR_P_T(long repositoryId,
		long parentFolderId, String type, int start, int end) {
		return findByR_P_T(repositoryId, parentFolderId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param type the type
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByR_P_T(long repositoryId,
		long parentFolderId, String type, int start, int end,
		OrderByComparator<SyncDLObject> orderByComparator) {
		return findByR_P_T(repositoryId, parentFolderId, type, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param type the type
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByR_P_T(long repositoryId,
		long parentFolderId, String type, int start, int end,
		OrderByComparator<SyncDLObject> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_P_T;
			finderArgs = new Object[] { repositoryId, parentFolderId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_P_T;
			finderArgs = new Object[] {
					repositoryId, parentFolderId, type,
					
					start, end, orderByComparator
				};
		}

		List<SyncDLObject> list = null;

		if (retrieveFromCache) {
			list = (List<SyncDLObject>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncDLObject syncDLObject : list) {
					if ((repositoryId != syncDLObject.getRepositoryId()) ||
							(parentFolderId != syncDLObject.getParentFolderId()) ||
							!Validator.equals(type, syncDLObject.getType())) {
						list = null;

						break;
					}
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

			query.append(_SQL_SELECT_SYNCDLOBJECT_WHERE);

			query.append(_FINDER_COLUMN_R_P_T_REPOSITORYID_2);

			query.append(_FINDER_COLUMN_R_P_T_PARENTFOLDERID_2);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_R_P_T_TYPE_1);
			}
			else if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_R_P_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_R_P_T_TYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncDLObjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(repositoryId);

				qPos.add(parentFolderId);

				if (bindType) {
					qPos.add(type);
				}

				if (!pagination) {
					list = (List<SyncDLObject>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncDLObject>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first sync d l object in the ordered set where repositoryId = &#63; and parentFolderId = &#63; and type = &#63;.
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync d l object
	 * @throws NoSuchDLObjectException if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject findByR_P_T_First(long repositoryId,
		long parentFolderId, String type,
		OrderByComparator<SyncDLObject> orderByComparator)
		throws NoSuchDLObjectException {
		SyncDLObject syncDLObject = fetchByR_P_T_First(repositoryId,
				parentFolderId, type, orderByComparator);

		if (syncDLObject != null) {
			return syncDLObject;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("repositoryId=");
		msg.append(repositoryId);

		msg.append(", parentFolderId=");
		msg.append(parentFolderId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDLObjectException(msg.toString());
	}

	/**
	 * Returns the first sync d l object in the ordered set where repositoryId = &#63; and parentFolderId = &#63; and type = &#63;.
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject fetchByR_P_T_First(long repositoryId,
		long parentFolderId, String type,
		OrderByComparator<SyncDLObject> orderByComparator) {
		List<SyncDLObject> list = findByR_P_T(repositoryId, parentFolderId,
				type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync d l object in the ordered set where repositoryId = &#63; and parentFolderId = &#63; and type = &#63;.
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync d l object
	 * @throws NoSuchDLObjectException if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject findByR_P_T_Last(long repositoryId,
		long parentFolderId, String type,
		OrderByComparator<SyncDLObject> orderByComparator)
		throws NoSuchDLObjectException {
		SyncDLObject syncDLObject = fetchByR_P_T_Last(repositoryId,
				parentFolderId, type, orderByComparator);

		if (syncDLObject != null) {
			return syncDLObject;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("repositoryId=");
		msg.append(repositoryId);

		msg.append(", parentFolderId=");
		msg.append(parentFolderId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDLObjectException(msg.toString());
	}

	/**
	 * Returns the last sync d l object in the ordered set where repositoryId = &#63; and parentFolderId = &#63; and type = &#63;.
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	 */
	@Override
	public SyncDLObject fetchByR_P_T_Last(long repositoryId,
		long parentFolderId, String type,
		OrderByComparator<SyncDLObject> orderByComparator) {
		int count = countByR_P_T(repositoryId, parentFolderId, type);

		if (count == 0) {
			return null;
		}

		List<SyncDLObject> list = findByR_P_T(repositoryId, parentFolderId,
				type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync d l objects before and after the current sync d l object in the ordered set where repositoryId = &#63; and parentFolderId = &#63; and type = &#63;.
	 *
	 * @param syncDLObjectId the primary key of the current sync d l object
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync d l object
	 * @throws NoSuchDLObjectException if a sync d l object with the primary key could not be found
	 */
	@Override
	public SyncDLObject[] findByR_P_T_PrevAndNext(long syncDLObjectId,
		long repositoryId, long parentFolderId, String type,
		OrderByComparator<SyncDLObject> orderByComparator)
		throws NoSuchDLObjectException {
		SyncDLObject syncDLObject = findByPrimaryKey(syncDLObjectId);

		Session session = null;

		try {
			session = openSession();

			SyncDLObject[] array = new SyncDLObjectImpl[3];

			array[0] = getByR_P_T_PrevAndNext(session, syncDLObject,
					repositoryId, parentFolderId, type, orderByComparator, true);

			array[1] = syncDLObject;

			array[2] = getByR_P_T_PrevAndNext(session, syncDLObject,
					repositoryId, parentFolderId, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SyncDLObject getByR_P_T_PrevAndNext(Session session,
		SyncDLObject syncDLObject, long repositoryId, long parentFolderId,
		String type, OrderByComparator<SyncDLObject> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SYNCDLOBJECT_WHERE);

		query.append(_FINDER_COLUMN_R_P_T_REPOSITORYID_2);

		query.append(_FINDER_COLUMN_R_P_T_PARENTFOLDERID_2);

		boolean bindType = false;

		if (type == null) {
			query.append(_FINDER_COLUMN_R_P_T_TYPE_1);
		}
		else if (type.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_R_P_T_TYPE_3);
		}
		else {
			bindType = true;

			query.append(_FINDER_COLUMN_R_P_T_TYPE_2);
		}

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
			query.append(SyncDLObjectModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(repositoryId);

		qPos.add(parentFolderId);

		if (bindType) {
			qPos.add(type);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncDLObject);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncDLObject> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param types the types
	 * @return the matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByR_P_T(long repositoryId,
		long parentFolderId, String[] types) {
		return findByR_P_T(repositoryId, parentFolderId, types,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param types the types
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @return the range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByR_P_T(long repositoryId,
		long parentFolderId, String[] types, int start, int end) {
		return findByR_P_T(repositoryId, parentFolderId, types, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param types the types
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByR_P_T(long repositoryId,
		long parentFolderId, String[] types, int start, int end,
		OrderByComparator<SyncDLObject> orderByComparator) {
		return findByR_P_T(repositoryId, parentFolderId, types, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param type the type
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sync d l objects
	 */
	@Override
	public List<SyncDLObject> findByR_P_T(long repositoryId,
		long parentFolderId, String[] types, int start, int end,
		OrderByComparator<SyncDLObject> orderByComparator,
		boolean retrieveFromCache) {
		if (types == null) {
			types = new String[0];
		}
		else if (types.length > 1) {
			types = ArrayUtil.distinct(types, NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(types, NULL_SAFE_STRING_COMPARATOR);
		}

		if (types.length == 1) {
			return findByR_P_T(repositoryId, parentFolderId, types[0], start,
				end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					repositoryId, parentFolderId, StringUtil.merge(types)
				};
		}
		else {
			finderArgs = new Object[] {
					repositoryId, parentFolderId, StringUtil.merge(types),
					
					start, end, orderByComparator
				};
		}

		List<SyncDLObject> list = null;

		if (retrieveFromCache) {
			list = (List<SyncDLObject>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_P_T,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SyncDLObject syncDLObject : list) {
					if ((repositoryId != syncDLObject.getRepositoryId()) ||
							(parentFolderId != syncDLObject.getParentFolderId()) ||
							!ArrayUtil.contains(types, syncDLObject.getType())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_SYNCDLOBJECT_WHERE);

			query.append(_FINDER_COLUMN_R_P_T_REPOSITORYID_2);

			query.append(_FINDER_COLUMN_R_P_T_PARENTFOLDERID_2);

			if (types.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < types.length; i++) {
					String type = types[i];

					if (type == null) {
						query.append(_FINDER_COLUMN_R_P_T_TYPE_1);
					}
					else if (type.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_R_P_T_TYPE_3);
					}
					else {
						query.append(_FINDER_COLUMN_R_P_T_TYPE_2);
					}

					if ((i + 1) < types.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncDLObjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(repositoryId);

				qPos.add(parentFolderId);

				for (String type : types) {
					if ((type != null) && !type.isEmpty()) {
						qPos.add(type);
					}
				}

				if (!pagination) {
					list = (List<SyncDLObject>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncDLObject>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_P_T,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_P_T,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = &#63; from the database.
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param type the type
	 */
	@Override
	public void removeByR_P_T(long repositoryId, long parentFolderId,
		String type) {
		for (SyncDLObject syncDLObject : findByR_P_T(repositoryId,
				parentFolderId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncDLObject);
		}
	}

	/**
	 * Returns the number of sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = &#63;.
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param type the type
	 * @return the number of matching sync d l objects
	 */
	@Override
	public int countByR_P_T(long repositoryId, long parentFolderId, String type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_R_P_T;

		Object[] finderArgs = new Object[] { repositoryId, parentFolderId, type };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SYNCDLOBJECT_WHERE);

			query.append(_FINDER_COLUMN_R_P_T_REPOSITORYID_2);

			query.append(_FINDER_COLUMN_R_P_T_PARENTFOLDERID_2);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_R_P_T_TYPE_1);
			}
			else if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_R_P_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_R_P_T_TYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(repositoryId);

				qPos.add(parentFolderId);

				if (bindType) {
					qPos.add(type);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = any &#63;.
	 *
	 * @param repositoryId the repository ID
	 * @param parentFolderId the parent folder ID
	 * @param types the types
	 * @return the number of matching sync d l objects
	 */
	@Override
	public int countByR_P_T(long repositoryId, long parentFolderId,
		String[] types) {
		if (types == null) {
			types = new String[0];
		}
		else if (types.length > 1) {
			types = ArrayUtil.distinct(types, NULL_SAFE_STRING_COMPARATOR);

			Arrays.sort(types, NULL_SAFE_STRING_COMPARATOR);
		}

		Object[] finderArgs = new Object[] {
				repositoryId, parentFolderId, StringUtil.merge(types)
			};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_P_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_SYNCDLOBJECT_WHERE);

			query.append(_FINDER_COLUMN_R_P_T_REPOSITORYID_2);

			query.append(_FINDER_COLUMN_R_P_T_PARENTFOLDERID_2);

			if (types.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < types.length; i++) {
					String type = types[i];

					if (type == null) {
						query.append(_FINDER_COLUMN_R_P_T_TYPE_1);
					}
					else if (type.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_R_P_T_TYPE_3);
					}
					else {
						query.append(_FINDER_COLUMN_R_P_T_TYPE_2);
					}

					if ((i + 1) < types.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(repositoryId);

				qPos.add(parentFolderId);

				for (String type : types) {
					if ((type != null) && !type.isEmpty()) {
						qPos.add(type);
					}
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_P_T,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_P_T,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_R_P_T_REPOSITORYID_2 = "syncDLObject.repositoryId = ? AND ";
	private static final String _FINDER_COLUMN_R_P_T_PARENTFOLDERID_2 = "syncDLObject.parentFolderId = ? AND ";
	private static final String _FINDER_COLUMN_R_P_T_TYPE_1 = "syncDLObject.type IS NULL";
	private static final String _FINDER_COLUMN_R_P_T_TYPE_2 = "syncDLObject.type = ?";
	private static final String _FINDER_COLUMN_R_P_T_TYPE_3 = "(syncDLObject.type IS NULL OR syncDLObject.type = '')";

	public SyncDLObjectPersistenceImpl() {
		setModelClass(SyncDLObject.class);
	}

	/**
	 * Caches the sync d l object in the entity cache if it is enabled.
	 *
	 * @param syncDLObject the sync d l object
	 */
	@Override
	public void cacheResult(SyncDLObject syncDLObject) {
		entityCache.putResult(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectImpl.class, syncDLObject.getPrimaryKey(), syncDLObject);

		finderCache.putResult(FINDER_PATH_FETCH_BY_T_T,
			new Object[] { syncDLObject.getType(), syncDLObject.getTypePK() },
			syncDLObject);

		syncDLObject.resetOriginalValues();
	}

	/**
	 * Caches the sync d l objects in the entity cache if it is enabled.
	 *
	 * @param syncDLObjects the sync d l objects
	 */
	@Override
	public void cacheResult(List<SyncDLObject> syncDLObjects) {
		for (SyncDLObject syncDLObject : syncDLObjects) {
			if (entityCache.getResult(
						SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
						SyncDLObjectImpl.class, syncDLObject.getPrimaryKey()) == null) {
				cacheResult(syncDLObject);
			}
			else {
				syncDLObject.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all sync d l objects.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SyncDLObjectImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the sync d l object.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SyncDLObject syncDLObject) {
		entityCache.removeResult(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectImpl.class, syncDLObject.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SyncDLObjectModelImpl)syncDLObject);
	}

	@Override
	public void clearCache(List<SyncDLObject> syncDLObjects) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SyncDLObject syncDLObject : syncDLObjects) {
			entityCache.removeResult(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
				SyncDLObjectImpl.class, syncDLObject.getPrimaryKey());

			clearUniqueFindersCache((SyncDLObjectModelImpl)syncDLObject);
		}
	}

	protected void cacheUniqueFindersCache(
		SyncDLObjectModelImpl syncDLObjectModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					syncDLObjectModelImpl.getType(),
					syncDLObjectModelImpl.getTypePK()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_T_T, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_T_T, args,
				syncDLObjectModelImpl);
		}
		else {
			if ((syncDLObjectModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_T_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						syncDLObjectModelImpl.getType(),
						syncDLObjectModelImpl.getTypePK()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_T_T, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_T_T, args,
					syncDLObjectModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		SyncDLObjectModelImpl syncDLObjectModelImpl) {
		Object[] args = new Object[] {
				syncDLObjectModelImpl.getType(),
				syncDLObjectModelImpl.getTypePK()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_T_T, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_T_T, args);

		if ((syncDLObjectModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_T_T.getColumnBitmask()) != 0) {
			args = new Object[] {
					syncDLObjectModelImpl.getOriginalType(),
					syncDLObjectModelImpl.getOriginalTypePK()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_T_T, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_T_T, args);
		}
	}

	/**
	 * Creates a new sync d l object with the primary key. Does not add the sync d l object to the database.
	 *
	 * @param syncDLObjectId the primary key for the new sync d l object
	 * @return the new sync d l object
	 */
	@Override
	public SyncDLObject create(long syncDLObjectId) {
		SyncDLObject syncDLObject = new SyncDLObjectImpl();

		syncDLObject.setNew(true);
		syncDLObject.setPrimaryKey(syncDLObjectId);

		return syncDLObject;
	}

	/**
	 * Removes the sync d l object with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param syncDLObjectId the primary key of the sync d l object
	 * @return the sync d l object that was removed
	 * @throws NoSuchDLObjectException if a sync d l object with the primary key could not be found
	 */
	@Override
	public SyncDLObject remove(long syncDLObjectId)
		throws NoSuchDLObjectException {
		return remove((Serializable)syncDLObjectId);
	}

	/**
	 * Removes the sync d l object with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sync d l object
	 * @return the sync d l object that was removed
	 * @throws NoSuchDLObjectException if a sync d l object with the primary key could not be found
	 */
	@Override
	public SyncDLObject remove(Serializable primaryKey)
		throws NoSuchDLObjectException {
		Session session = null;

		try {
			session = openSession();

			SyncDLObject syncDLObject = (SyncDLObject)session.get(SyncDLObjectImpl.class,
					primaryKey);

			if (syncDLObject == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDLObjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(syncDLObject);
		}
		catch (NoSuchDLObjectException nsee) {
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
	protected SyncDLObject removeImpl(SyncDLObject syncDLObject) {
		syncDLObject = toUnwrappedModel(syncDLObject);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(syncDLObject)) {
				syncDLObject = (SyncDLObject)session.get(SyncDLObjectImpl.class,
						syncDLObject.getPrimaryKeyObj());
			}

			if (syncDLObject != null) {
				session.delete(syncDLObject);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (syncDLObject != null) {
			clearCache(syncDLObject);
		}

		return syncDLObject;
	}

	@Override
	public SyncDLObject updateImpl(SyncDLObject syncDLObject) {
		syncDLObject = toUnwrappedModel(syncDLObject);

		boolean isNew = syncDLObject.isNew();

		SyncDLObjectModelImpl syncDLObjectModelImpl = (SyncDLObjectModelImpl)syncDLObject;

		Session session = null;

		try {
			session = openSession();

			if (syncDLObject.isNew()) {
				session.save(syncDLObject);

				syncDLObject.setNew(false);
			}
			else {
				syncDLObject = (SyncDLObject)session.merge(syncDLObject);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SyncDLObjectModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((syncDLObjectModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						syncDLObjectModelImpl.getOriginalRepositoryId(),
						syncDLObjectModelImpl.getOriginalParentFolderId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_R_P, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_P,
					args);

				args = new Object[] {
						syncDLObjectModelImpl.getRepositoryId(),
						syncDLObjectModelImpl.getParentFolderId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_R_P, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_P,
					args);
			}

			if ((syncDLObjectModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						syncDLObjectModelImpl.getOriginalRepositoryId(),
						syncDLObjectModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_R_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_T,
					args);

				args = new Object[] {
						syncDLObjectModelImpl.getRepositoryId(),
						syncDLObjectModelImpl.getType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_R_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_T,
					args);
			}

			if ((syncDLObjectModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_V_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						syncDLObjectModelImpl.getOriginalVersion(),
						syncDLObjectModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_V_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_V_T,
					args);

				args = new Object[] {
						syncDLObjectModelImpl.getVersion(),
						syncDLObjectModelImpl.getType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_V_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_V_T,
					args);
			}

			if ((syncDLObjectModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_P_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						syncDLObjectModelImpl.getOriginalRepositoryId(),
						syncDLObjectModelImpl.getOriginalParentFolderId(),
						syncDLObjectModelImpl.getOriginalType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_R_P_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_P_T,
					args);

				args = new Object[] {
						syncDLObjectModelImpl.getRepositoryId(),
						syncDLObjectModelImpl.getParentFolderId(),
						syncDLObjectModelImpl.getType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_R_P_T, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_P_T,
					args);
			}
		}

		entityCache.putResult(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectImpl.class, syncDLObject.getPrimaryKey(), syncDLObject,
			false);

		clearUniqueFindersCache(syncDLObjectModelImpl);
		cacheUniqueFindersCache(syncDLObjectModelImpl, isNew);

		syncDLObject.resetOriginalValues();

		return syncDLObject;
	}

	protected SyncDLObject toUnwrappedModel(SyncDLObject syncDLObject) {
		if (syncDLObject instanceof SyncDLObjectImpl) {
			return syncDLObject;
		}

		SyncDLObjectImpl syncDLObjectImpl = new SyncDLObjectImpl();

		syncDLObjectImpl.setNew(syncDLObject.isNew());
		syncDLObjectImpl.setPrimaryKey(syncDLObject.getPrimaryKey());

		syncDLObjectImpl.setSyncDLObjectId(syncDLObject.getSyncDLObjectId());
		syncDLObjectImpl.setCompanyId(syncDLObject.getCompanyId());
		syncDLObjectImpl.setUserId(syncDLObject.getUserId());
		syncDLObjectImpl.setUserName(syncDLObject.getUserName());
		syncDLObjectImpl.setCreateTime(syncDLObject.getCreateTime());
		syncDLObjectImpl.setModifiedTime(syncDLObject.getModifiedTime());
		syncDLObjectImpl.setRepositoryId(syncDLObject.getRepositoryId());
		syncDLObjectImpl.setParentFolderId(syncDLObject.getParentFolderId());
		syncDLObjectImpl.setTreePath(syncDLObject.getTreePath());
		syncDLObjectImpl.setName(syncDLObject.getName());
		syncDLObjectImpl.setExtension(syncDLObject.getExtension());
		syncDLObjectImpl.setMimeType(syncDLObject.getMimeType());
		syncDLObjectImpl.setDescription(syncDLObject.getDescription());
		syncDLObjectImpl.setChangeLog(syncDLObject.getChangeLog());
		syncDLObjectImpl.setExtraSettings(syncDLObject.getExtraSettings());
		syncDLObjectImpl.setVersion(syncDLObject.getVersion());
		syncDLObjectImpl.setVersionId(syncDLObject.getVersionId());
		syncDLObjectImpl.setSize(syncDLObject.getSize());
		syncDLObjectImpl.setChecksum(syncDLObject.getChecksum());
		syncDLObjectImpl.setEvent(syncDLObject.getEvent());
		syncDLObjectImpl.setLastPermissionChangeDate(syncDLObject.getLastPermissionChangeDate());
		syncDLObjectImpl.setLockExpirationDate(syncDLObject.getLockExpirationDate());
		syncDLObjectImpl.setLockUserId(syncDLObject.getLockUserId());
		syncDLObjectImpl.setLockUserName(syncDLObject.getLockUserName());
		syncDLObjectImpl.setType(syncDLObject.getType());
		syncDLObjectImpl.setTypePK(syncDLObject.getTypePK());
		syncDLObjectImpl.setTypeUuid(syncDLObject.getTypeUuid());

		return syncDLObjectImpl;
	}

	/**
	 * Returns the sync d l object with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the sync d l object
	 * @return the sync d l object
	 * @throws NoSuchDLObjectException if a sync d l object with the primary key could not be found
	 */
	@Override
	public SyncDLObject findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDLObjectException {
		SyncDLObject syncDLObject = fetchByPrimaryKey(primaryKey);

		if (syncDLObject == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDLObjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return syncDLObject;
	}

	/**
	 * Returns the sync d l object with the primary key or throws a {@link NoSuchDLObjectException} if it could not be found.
	 *
	 * @param syncDLObjectId the primary key of the sync d l object
	 * @return the sync d l object
	 * @throws NoSuchDLObjectException if a sync d l object with the primary key could not be found
	 */
	@Override
	public SyncDLObject findByPrimaryKey(long syncDLObjectId)
		throws NoSuchDLObjectException {
		return findByPrimaryKey((Serializable)syncDLObjectId);
	}

	/**
	 * Returns the sync d l object with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sync d l object
	 * @return the sync d l object, or <code>null</code> if a sync d l object with the primary key could not be found
	 */
	@Override
	public SyncDLObject fetchByPrimaryKey(Serializable primaryKey) {
		SyncDLObject syncDLObject = (SyncDLObject)entityCache.getResult(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
				SyncDLObjectImpl.class, primaryKey);

		if (syncDLObject == _nullSyncDLObject) {
			return null;
		}

		if (syncDLObject == null) {
			Session session = null;

			try {
				session = openSession();

				syncDLObject = (SyncDLObject)session.get(SyncDLObjectImpl.class,
						primaryKey);

				if (syncDLObject != null) {
					cacheResult(syncDLObject);
				}
				else {
					entityCache.putResult(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
						SyncDLObjectImpl.class, primaryKey, _nullSyncDLObject);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
					SyncDLObjectImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return syncDLObject;
	}

	/**
	 * Returns the sync d l object with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param syncDLObjectId the primary key of the sync d l object
	 * @return the sync d l object, or <code>null</code> if a sync d l object with the primary key could not be found
	 */
	@Override
	public SyncDLObject fetchByPrimaryKey(long syncDLObjectId) {
		return fetchByPrimaryKey((Serializable)syncDLObjectId);
	}

	@Override
	public Map<Serializable, SyncDLObject> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SyncDLObject> map = new HashMap<Serializable, SyncDLObject>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SyncDLObject syncDLObject = fetchByPrimaryKey(primaryKey);

			if (syncDLObject != null) {
				map.put(primaryKey, syncDLObject);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			SyncDLObject syncDLObject = (SyncDLObject)entityCache.getResult(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
					SyncDLObjectImpl.class, primaryKey);

			if (syncDLObject == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, syncDLObject);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SYNCDLOBJECT_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (SyncDLObject syncDLObject : (List<SyncDLObject>)q.list()) {
				map.put(syncDLObject.getPrimaryKeyObj(), syncDLObject);

				cacheResult(syncDLObject);

				uncachedPrimaryKeys.remove(syncDLObject.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
					SyncDLObjectImpl.class, primaryKey, _nullSyncDLObject);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the sync d l objects.
	 *
	 * @return the sync d l objects
	 */
	@Override
	public List<SyncDLObject> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync d l objects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @return the range of sync d l objects
	 */
	@Override
	public List<SyncDLObject> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync d l objects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sync d l objects
	 */
	@Override
	public List<SyncDLObject> findAll(int start, int end,
		OrderByComparator<SyncDLObject> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sync d l objects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of sync d l objects
	 */
	@Override
	public List<SyncDLObject> findAll(int start, int end,
		OrderByComparator<SyncDLObject> orderByComparator,
		boolean retrieveFromCache) {
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

		List<SyncDLObject> list = null;

		if (retrieveFromCache) {
			list = (List<SyncDLObject>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SYNCDLOBJECT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SYNCDLOBJECT;

				if (pagination) {
					sql = sql.concat(SyncDLObjectModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SyncDLObject>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncDLObject>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the sync d l objects from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SyncDLObject syncDLObject : findAll()) {
			remove(syncDLObject);
		}
	}

	/**
	 * Returns the number of sync d l objects.
	 *
	 * @return the number of sync d l objects
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SYNCDLOBJECT);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SyncDLObjectModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sync d l object persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SyncDLObjectImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CompanyProvider.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_SYNCDLOBJECT = "SELECT syncDLObject FROM SyncDLObject syncDLObject";
	private static final String _SQL_SELECT_SYNCDLOBJECT_WHERE_PKS_IN = "SELECT syncDLObject FROM SyncDLObject syncDLObject WHERE syncDLObjectId IN (";
	private static final String _SQL_SELECT_SYNCDLOBJECT_WHERE = "SELECT syncDLObject FROM SyncDLObject syncDLObject WHERE ";
	private static final String _SQL_COUNT_SYNCDLOBJECT = "SELECT COUNT(syncDLObject) FROM SyncDLObject syncDLObject";
	private static final String _SQL_COUNT_SYNCDLOBJECT_WHERE = "SELECT COUNT(syncDLObject) FROM SyncDLObject syncDLObject WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "syncDLObject.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SyncDLObject exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SyncDLObject exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SyncDLObjectPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"size", "type"
			});
	private static final SyncDLObject _nullSyncDLObject = new SyncDLObjectImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SyncDLObject> toCacheModel() {
				return _nullSyncDLObjectCacheModel;
			}
		};

	private static final CacheModel<SyncDLObject> _nullSyncDLObjectCacheModel = new CacheModel<SyncDLObject>() {
			@Override
			public SyncDLObject toEntityModel() {
				return _nullSyncDLObject;
			}
		};
}