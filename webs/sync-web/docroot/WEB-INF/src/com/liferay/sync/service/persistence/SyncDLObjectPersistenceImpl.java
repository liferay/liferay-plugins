/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.sync.service.persistence;

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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.sync.NoSuchDLObjectException;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.model.impl.SyncDLObjectImpl;
import com.liferay.sync.model.impl.SyncDLObjectModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
 * @see SyncDLObjectUtil
 * @generated
 */
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
	public static final FinderPath FINDER_PATH_FETCH_BY_TYPEPK = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, SyncDLObjectImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByTypePK",
			new String[] { Long.class.getName() },
			SyncDLObjectModelImpl.TYPEPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TYPEPK = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTypePK",
			new String[] { Long.class.getName() });

	/**
	 * Returns the sync d l object where typePK = &#63; or throws a {@link com.liferay.sync.NoSuchDLObjectException} if it could not be found.
	 *
	 * @param typePK the type p k
	 * @return the matching sync d l object
	 * @throws com.liferay.sync.NoSuchDLObjectException if a matching sync d l object could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDLObject findByTypePK(long typePK)
		throws NoSuchDLObjectException, SystemException {
		SyncDLObject syncDLObject = fetchByTypePK(typePK);

		if (syncDLObject == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("typePK=");
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
	 * Returns the sync d l object where typePK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param typePK the type p k
	 * @return the matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDLObject fetchByTypePK(long typePK) throws SystemException {
		return fetchByTypePK(typePK, true);
	}

	/**
	 * Returns the sync d l object where typePK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param typePK the type p k
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDLObject fetchByTypePK(long typePK, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { typePK };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_TYPEPK,
					finderArgs, this);
		}

		if (result instanceof SyncDLObject) {
			SyncDLObject syncDLObject = (SyncDLObject)result;

			if ((typePK != syncDLObject.getTypePK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SYNCDLOBJECT_WHERE);

			query.append(_FINDER_COLUMN_TYPEPK_TYPEPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(typePK);

				List<SyncDLObject> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TYPEPK,
						finderArgs, list);
				}
				else {
					SyncDLObject syncDLObject = list.get(0);

					result = syncDLObject;

					cacheResult(syncDLObject);

					if ((syncDLObject.getTypePK() != typePK)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TYPEPK,
							finderArgs, syncDLObject);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TYPEPK,
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
			return (SyncDLObject)result;
		}
	}

	/**
	 * Removes the sync d l object where typePK = &#63; from the database.
	 *
	 * @param typePK the type p k
	 * @return the sync d l object that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDLObject removeByTypePK(long typePK)
		throws NoSuchDLObjectException, SystemException {
		SyncDLObject syncDLObject = findByTypePK(typePK);

		return remove(syncDLObject);
	}

	/**
	 * Returns the number of sync d l objects where typePK = &#63;.
	 *
	 * @param typePK the type p k
	 * @return the number of matching sync d l objects
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByTypePK(long typePK) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TYPEPK;

		Object[] finderArgs = new Object[] { typePK };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYNCDLOBJECT_WHERE);

			query.append(_FINDER_COLUMN_TYPEPK_TYPEPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(typePK);

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

	private static final String _FINDER_COLUMN_TYPEPK_TYPEPK_2 = "syncDLObject.typePK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_M_R = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, SyncDLObjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_M_R",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_M_R = new FinderPath(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_M_R",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the sync d l objects where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @return the matching sync d l objects
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SyncDLObject> findByC_M_R(long companyId, long modifiedTime,
		long repositoryId) throws SystemException {
		return findByC_M_R(companyId, modifiedTime, repositoryId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync d l objects where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @return the range of matching sync d l objects
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SyncDLObject> findByC_M_R(long companyId, long modifiedTime,
		long repositoryId, int start, int end) throws SystemException {
		return findByC_M_R(companyId, modifiedTime, repositoryId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the sync d l objects where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync d l objects
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SyncDLObject> findByC_M_R(long companyId, long modifiedTime,
		long repositoryId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_M_R;
		finderArgs = new Object[] {
				companyId, modifiedTime, repositoryId,
				
				start, end, orderByComparator
			};

		List<SyncDLObject> list = (List<SyncDLObject>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SyncDLObject syncDLObject : list) {
				if ((companyId != syncDLObject.getCompanyId()) ||
						(modifiedTime >= syncDLObject.getModifiedTime()) ||
						(repositoryId != syncDLObject.getRepositoryId())) {
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

			query.append(_SQL_SELECT_SYNCDLOBJECT_WHERE);

			query.append(_FINDER_COLUMN_C_M_R_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_M_R_MODIFIEDTIME_2);

			query.append(_FINDER_COLUMN_C_M_R_REPOSITORYID_2);

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

				qPos.add(companyId);

				qPos.add(modifiedTime);

				qPos.add(repositoryId);

				if (!pagination) {
					list = (List<SyncDLObject>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SyncDLObject>(list);
				}
				else {
					list = (List<SyncDLObject>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync d l object in the ordered set where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync d l object
	 * @throws com.liferay.sync.NoSuchDLObjectException if a matching sync d l object could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDLObject findByC_M_R_First(long companyId, long modifiedTime,
		long repositoryId, OrderByComparator orderByComparator)
		throws NoSuchDLObjectException, SystemException {
		SyncDLObject syncDLObject = fetchByC_M_R_First(companyId, modifiedTime,
				repositoryId, orderByComparator);

		if (syncDLObject != null) {
			return syncDLObject;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", modifiedTime=");
		msg.append(modifiedTime);

		msg.append(", repositoryId=");
		msg.append(repositoryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDLObjectException(msg.toString());
	}

	/**
	 * Returns the first sync d l object in the ordered set where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDLObject fetchByC_M_R_First(long companyId, long modifiedTime,
		long repositoryId, OrderByComparator orderByComparator)
		throws SystemException {
		List<SyncDLObject> list = findByC_M_R(companyId, modifiedTime,
				repositoryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync d l object in the ordered set where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync d l object
	 * @throws com.liferay.sync.NoSuchDLObjectException if a matching sync d l object could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDLObject findByC_M_R_Last(long companyId, long modifiedTime,
		long repositoryId, OrderByComparator orderByComparator)
		throws NoSuchDLObjectException, SystemException {
		SyncDLObject syncDLObject = fetchByC_M_R_Last(companyId, modifiedTime,
				repositoryId, orderByComparator);

		if (syncDLObject != null) {
			return syncDLObject;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", modifiedTime=");
		msg.append(modifiedTime);

		msg.append(", repositoryId=");
		msg.append(repositoryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDLObjectException(msg.toString());
	}

	/**
	 * Returns the last sync d l object in the ordered set where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDLObject fetchByC_M_R_Last(long companyId, long modifiedTime,
		long repositoryId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByC_M_R(companyId, modifiedTime, repositoryId);

		if (count == 0) {
			return null;
		}

		List<SyncDLObject> list = findByC_M_R(companyId, modifiedTime,
				repositoryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync d l objects before and after the current sync d l object in the ordered set where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63;.
	 *
	 * @param objectId the primary key of the current sync d l object
	 * @param companyId the company ID
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync d l object
	 * @throws com.liferay.sync.NoSuchDLObjectException if a sync d l object with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDLObject[] findByC_M_R_PrevAndNext(long objectId,
		long companyId, long modifiedTime, long repositoryId,
		OrderByComparator orderByComparator)
		throws NoSuchDLObjectException, SystemException {
		SyncDLObject syncDLObject = findByPrimaryKey(objectId);

		Session session = null;

		try {
			session = openSession();

			SyncDLObject[] array = new SyncDLObjectImpl[3];

			array[0] = getByC_M_R_PrevAndNext(session, syncDLObject, companyId,
					modifiedTime, repositoryId, orderByComparator, true);

			array[1] = syncDLObject;

			array[2] = getByC_M_R_PrevAndNext(session, syncDLObject, companyId,
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

	protected SyncDLObject getByC_M_R_PrevAndNext(Session session,
		SyncDLObject syncDLObject, long companyId, long modifiedTime,
		long repositoryId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SYNCDLOBJECT_WHERE);

		query.append(_FINDER_COLUMN_C_M_R_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_M_R_MODIFIEDTIME_2);

		query.append(_FINDER_COLUMN_C_M_R_REPOSITORYID_2);

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

		qPos.add(companyId);

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
	 * Removes all the sync d l objects where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByC_M_R(long companyId, long modifiedTime,
		long repositoryId) throws SystemException {
		for (SyncDLObject syncDLObject : findByC_M_R(companyId, modifiedTime,
				repositoryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncDLObject);
		}
	}

	/**
	 * Returns the number of sync d l objects where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param modifiedTime the modified time
	 * @param repositoryId the repository ID
	 * @return the number of matching sync d l objects
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_M_R(long companyId, long modifiedTime, long repositoryId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_M_R;

		Object[] finderArgs = new Object[] { companyId, modifiedTime, repositoryId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SYNCDLOBJECT_WHERE);

			query.append(_FINDER_COLUMN_C_M_R_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_M_R_MODIFIEDTIME_2);

			query.append(_FINDER_COLUMN_C_M_R_REPOSITORYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(modifiedTime);

				qPos.add(repositoryId);

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

	private static final String _FINDER_COLUMN_C_M_R_COMPANYID_2 = "syncDLObject.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_M_R_MODIFIEDTIME_2 = "syncDLObject.modifiedTime > ? AND ";
	private static final String _FINDER_COLUMN_C_M_R_REPOSITORYID_2 = "syncDLObject.repositoryId = ?";

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
		EntityCacheUtil.putResult(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectImpl.class, syncDLObject.getPrimaryKey(), syncDLObject);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TYPEPK,
			new Object[] { syncDLObject.getTypePK() }, syncDLObject);

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
			if (EntityCacheUtil.getResult(
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
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SyncDLObjectImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SyncDLObjectImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the sync d l object.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SyncDLObject syncDLObject) {
		EntityCacheUtil.removeResult(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectImpl.class, syncDLObject.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(syncDLObject);
	}

	@Override
	public void clearCache(List<SyncDLObject> syncDLObjects) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SyncDLObject syncDLObject : syncDLObjects) {
			EntityCacheUtil.removeResult(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
				SyncDLObjectImpl.class, syncDLObject.getPrimaryKey());

			clearUniqueFindersCache(syncDLObject);
		}
	}

	protected void cacheUniqueFindersCache(SyncDLObject syncDLObject) {
		if (syncDLObject.isNew()) {
			Object[] args = new Object[] { syncDLObject.getTypePK() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TYPEPK, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TYPEPK, args,
				syncDLObject);
		}
		else {
			SyncDLObjectModelImpl syncDLObjectModelImpl = (SyncDLObjectModelImpl)syncDLObject;

			if ((syncDLObjectModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_TYPEPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { syncDLObject.getTypePK() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TYPEPK, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TYPEPK, args,
					syncDLObject);
			}
		}
	}

	protected void clearUniqueFindersCache(SyncDLObject syncDLObject) {
		SyncDLObjectModelImpl syncDLObjectModelImpl = (SyncDLObjectModelImpl)syncDLObject;

		Object[] args = new Object[] { syncDLObject.getTypePK() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPEPK, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TYPEPK, args);

		if ((syncDLObjectModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_TYPEPK.getColumnBitmask()) != 0) {
			args = new Object[] { syncDLObjectModelImpl.getOriginalTypePK() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPEPK, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TYPEPK, args);
		}
	}

	/**
	 * Creates a new sync d l object with the primary key. Does not add the sync d l object to the database.
	 *
	 * @param objectId the primary key for the new sync d l object
	 * @return the new sync d l object
	 */
	@Override
	public SyncDLObject create(long objectId) {
		SyncDLObject syncDLObject = new SyncDLObjectImpl();

		syncDLObject.setNew(true);
		syncDLObject.setPrimaryKey(objectId);

		return syncDLObject;
	}

	/**
	 * Removes the sync d l object with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param objectId the primary key of the sync d l object
	 * @return the sync d l object that was removed
	 * @throws com.liferay.sync.NoSuchDLObjectException if a sync d l object with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDLObject remove(long objectId)
		throws NoSuchDLObjectException, SystemException {
		return remove((Serializable)objectId);
	}

	/**
	 * Removes the sync d l object with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sync d l object
	 * @return the sync d l object that was removed
	 * @throws com.liferay.sync.NoSuchDLObjectException if a sync d l object with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDLObject remove(Serializable primaryKey)
		throws NoSuchDLObjectException, SystemException {
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
	protected SyncDLObject removeImpl(SyncDLObject syncDLObject)
		throws SystemException {
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
	public SyncDLObject updateImpl(
		com.liferay.sync.model.SyncDLObject syncDLObject)
		throws SystemException {
		syncDLObject = toUnwrappedModel(syncDLObject);

		boolean isNew = syncDLObject.isNew();

		Session session = null;

		try {
			session = openSession();

			if (syncDLObject.isNew()) {
				session.save(syncDLObject);

				syncDLObject.setNew(false);
			}
			else {
				session.merge(syncDLObject);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SyncDLObjectModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLObjectImpl.class, syncDLObject.getPrimaryKey(), syncDLObject,
			false);

		clearUniqueFindersCache(syncDLObject);
		cacheUniqueFindersCache(syncDLObject);

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

		syncDLObjectImpl.setObjectId(syncDLObject.getObjectId());
		syncDLObjectImpl.setCompanyId(syncDLObject.getCompanyId());
		syncDLObjectImpl.setCreateTime(syncDLObject.getCreateTime());
		syncDLObjectImpl.setModifiedTime(syncDLObject.getModifiedTime());
		syncDLObjectImpl.setRepositoryId(syncDLObject.getRepositoryId());
		syncDLObjectImpl.setParentFolderId(syncDLObject.getParentFolderId());
		syncDLObjectImpl.setName(syncDLObject.getName());
		syncDLObjectImpl.setExtension(syncDLObject.getExtension());
		syncDLObjectImpl.setMimeType(syncDLObject.getMimeType());
		syncDLObjectImpl.setDescription(syncDLObject.getDescription());
		syncDLObjectImpl.setChangeLog(syncDLObject.getChangeLog());
		syncDLObjectImpl.setExtraSettings(syncDLObject.getExtraSettings());
		syncDLObjectImpl.setVersion(syncDLObject.getVersion());
		syncDLObjectImpl.setSize(syncDLObject.getSize());
		syncDLObjectImpl.setChecksum(syncDLObject.getChecksum());
		syncDLObjectImpl.setEvent(syncDLObject.getEvent());
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
	 * @throws com.liferay.sync.NoSuchDLObjectException if a sync d l object with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDLObject findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDLObjectException, SystemException {
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
	 * Returns the sync d l object with the primary key or throws a {@link com.liferay.sync.NoSuchDLObjectException} if it could not be found.
	 *
	 * @param objectId the primary key of the sync d l object
	 * @return the sync d l object
	 * @throws com.liferay.sync.NoSuchDLObjectException if a sync d l object with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDLObject findByPrimaryKey(long objectId)
		throws NoSuchDLObjectException, SystemException {
		return findByPrimaryKey((Serializable)objectId);
	}

	/**
	 * Returns the sync d l object with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sync d l object
	 * @return the sync d l object, or <code>null</code> if a sync d l object with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDLObject fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		SyncDLObject syncDLObject = (SyncDLObject)EntityCacheUtil.getResult(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
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
					EntityCacheUtil.putResult(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
						SyncDLObjectImpl.class, primaryKey, _nullSyncDLObject);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SyncDLObjectModelImpl.ENTITY_CACHE_ENABLED,
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
	 * @param objectId the primary key of the sync d l object
	 * @return the sync d l object, or <code>null</code> if a sync d l object with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDLObject fetchByPrimaryKey(long objectId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)objectId);
	}

	/**
	 * Returns all the sync d l objects.
	 *
	 * @return the sync d l objects
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SyncDLObject> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync d l objects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @return the range of sync d l objects
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SyncDLObject> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync d l objects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync d l objects
	 * @param end the upper bound of the range of sync d l objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sync d l objects
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SyncDLObject> findAll(int start, int end,
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

		List<SyncDLObject> list = (List<SyncDLObject>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

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

					list = new UnmodifiableList<SyncDLObject>(list);
				}
				else {
					list = (List<SyncDLObject>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the sync d l objects from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (SyncDLObject syncDLObject : findAll()) {
			remove(syncDLObject);
		}
	}

	/**
	 * Returns the number of sync d l objects.
	 *
	 * @return the number of sync d l objects
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

				Query q = session.createQuery(_SQL_COUNT_SYNCDLOBJECT);

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

	@Override
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	/**
	 * Initializes the sync d l object persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.sync.model.SyncDLObject")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SyncDLObject>> listenersList = new ArrayList<ModelListener<SyncDLObject>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<SyncDLObject>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SyncDLObjectImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SYNCDLOBJECT = "SELECT syncDLObject FROM SyncDLObject syncDLObject";
	private static final String _SQL_SELECT_SYNCDLOBJECT_WHERE = "SELECT syncDLObject FROM SyncDLObject syncDLObject WHERE ";
	private static final String _SQL_COUNT_SYNCDLOBJECT = "SELECT COUNT(syncDLObject) FROM SyncDLObject syncDLObject";
	private static final String _SQL_COUNT_SYNCDLOBJECT_WHERE = "SELECT COUNT(syncDLObject) FROM SyncDLObject syncDLObject WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "syncDLObject.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SyncDLObject exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SyncDLObject exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SyncDLObjectPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"size", "type"
			});
	private static SyncDLObject _nullSyncDLObject = new SyncDLObjectImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SyncDLObject> toCacheModel() {
				return _nullSyncDLObjectCacheModel;
			}
		};

	private static CacheModel<SyncDLObject> _nullSyncDLObjectCacheModel = new CacheModel<SyncDLObject>() {
			@Override
			public SyncDLObject toEntityModel() {
				return _nullSyncDLObject;
			}
		};
}