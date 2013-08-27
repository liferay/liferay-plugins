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

package com.liferay.oauthlogin.service.persistence;

import com.liferay.oauthlogin.NoSuchOAuthConnectionException;
import com.liferay.oauthlogin.model.OAuthConnection;
import com.liferay.oauthlogin.model.impl.OAuthConnectionImpl;
import com.liferay.oauthlogin.model.impl.OAuthConnectionModelImpl;

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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the o auth connection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Andy Yang
 * @see OAuthConnectionPersistence
 * @see OAuthConnectionUtil
 * @generated
 */
public class OAuthConnectionPersistenceImpl extends BasePersistenceImpl<OAuthConnection>
	implements OAuthConnectionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OAuthConnectionUtil} to access the o auth connection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OAuthConnectionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConnectionModelImpl.FINDER_CACHE_ENABLED,
			OAuthConnectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConnectionModelImpl.FINDER_CACHE_ENABLED,
			OAuthConnectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConnectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ENABLED = new FinderPath(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConnectionModelImpl.FINDER_CACHE_ENABLED,
			OAuthConnectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByEnabled",
			new String[] {
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENABLED =
		new FinderPath(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConnectionModelImpl.FINDER_CACHE_ENABLED,
			OAuthConnectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEnabled",
			new String[] { Boolean.class.getName() },
			OAuthConnectionModelImpl.ENABLED_COLUMN_BITMASK |
			OAuthConnectionModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ENABLED = new FinderPath(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConnectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEnabled",
			new String[] { Boolean.class.getName() });

	/**
	 * Returns all the o auth connections where enabled = &#63;.
	 *
	 * @param enabled the enabled
	 * @return the matching o auth connections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OAuthConnection> findByEnabled(boolean enabled)
		throws SystemException {
		return findByEnabled(enabled, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the o auth connections where enabled = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauthlogin.model.impl.OAuthConnectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param enabled the enabled
	 * @param start the lower bound of the range of o auth connections
	 * @param end the upper bound of the range of o auth connections (not inclusive)
	 * @return the range of matching o auth connections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OAuthConnection> findByEnabled(boolean enabled, int start,
		int end) throws SystemException {
		return findByEnabled(enabled, start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth connections where enabled = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauthlogin.model.impl.OAuthConnectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param enabled the enabled
	 * @param start the lower bound of the range of o auth connections
	 * @param end the upper bound of the range of o auth connections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching o auth connections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OAuthConnection> findByEnabled(boolean enabled, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENABLED;
			finderArgs = new Object[] { enabled };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ENABLED;
			finderArgs = new Object[] { enabled, start, end, orderByComparator };
		}

		List<OAuthConnection> list = (List<OAuthConnection>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OAuthConnection oAuthConnection : list) {
				if ((enabled != oAuthConnection.getEnabled())) {
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

			query.append(_SQL_SELECT_OAUTHCONNECTION_WHERE);

			query.append(_FINDER_COLUMN_ENABLED_ENABLED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OAuthConnectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(enabled);

				if (!pagination) {
					list = (List<OAuthConnection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<OAuthConnection>(list);
				}
				else {
					list = (List<OAuthConnection>)QueryUtil.list(q,
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
	 * Returns the first o auth connection in the ordered set where enabled = &#63;.
	 *
	 * @param enabled the enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching o auth connection
	 * @throws com.liferay.oauthlogin.NoSuchOAuthConnectionException if a matching o auth connection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthConnection findByEnabled_First(boolean enabled,
		OrderByComparator orderByComparator)
		throws NoSuchOAuthConnectionException, SystemException {
		OAuthConnection oAuthConnection = fetchByEnabled_First(enabled,
				orderByComparator);

		if (oAuthConnection != null) {
			return oAuthConnection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("enabled=");
		msg.append(enabled);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOAuthConnectionException(msg.toString());
	}

	/**
	 * Returns the first o auth connection in the ordered set where enabled = &#63;.
	 *
	 * @param enabled the enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching o auth connection, or <code>null</code> if a matching o auth connection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthConnection fetchByEnabled_First(boolean enabled,
		OrderByComparator orderByComparator) throws SystemException {
		List<OAuthConnection> list = findByEnabled(enabled, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last o auth connection in the ordered set where enabled = &#63;.
	 *
	 * @param enabled the enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching o auth connection
	 * @throws com.liferay.oauthlogin.NoSuchOAuthConnectionException if a matching o auth connection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthConnection findByEnabled_Last(boolean enabled,
		OrderByComparator orderByComparator)
		throws NoSuchOAuthConnectionException, SystemException {
		OAuthConnection oAuthConnection = fetchByEnabled_Last(enabled,
				orderByComparator);

		if (oAuthConnection != null) {
			return oAuthConnection;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("enabled=");
		msg.append(enabled);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOAuthConnectionException(msg.toString());
	}

	/**
	 * Returns the last o auth connection in the ordered set where enabled = &#63;.
	 *
	 * @param enabled the enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching o auth connection, or <code>null</code> if a matching o auth connection could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthConnection fetchByEnabled_Last(boolean enabled,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByEnabled(enabled);

		List<OAuthConnection> list = findByEnabled(enabled, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the o auth connections before and after the current o auth connection in the ordered set where enabled = &#63;.
	 *
	 * @param oAuthConnectionId the primary key of the current o auth connection
	 * @param enabled the enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next o auth connection
	 * @throws com.liferay.oauthlogin.NoSuchOAuthConnectionException if a o auth connection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthConnection[] findByEnabled_PrevAndNext(long oAuthConnectionId,
		boolean enabled, OrderByComparator orderByComparator)
		throws NoSuchOAuthConnectionException, SystemException {
		OAuthConnection oAuthConnection = findByPrimaryKey(oAuthConnectionId);

		Session session = null;

		try {
			session = openSession();

			OAuthConnection[] array = new OAuthConnectionImpl[3];

			array[0] = getByEnabled_PrevAndNext(session, oAuthConnection,
					enabled, orderByComparator, true);

			array[1] = oAuthConnection;

			array[2] = getByEnabled_PrevAndNext(session, oAuthConnection,
					enabled, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OAuthConnection getByEnabled_PrevAndNext(Session session,
		OAuthConnection oAuthConnection, boolean enabled,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OAUTHCONNECTION_WHERE);

		query.append(_FINDER_COLUMN_ENABLED_ENABLED_2);

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
			query.append(OAuthConnectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(enabled);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(oAuthConnection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OAuthConnection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the o auth connections where enabled = &#63; from the database.
	 *
	 * @param enabled the enabled
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByEnabled(boolean enabled) throws SystemException {
		for (OAuthConnection oAuthConnection : findByEnabled(enabled,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(oAuthConnection);
		}
	}

	/**
	 * Returns the number of o auth connections where enabled = &#63;.
	 *
	 * @param enabled the enabled
	 * @return the number of matching o auth connections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByEnabled(boolean enabled) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ENABLED;

		Object[] finderArgs = new Object[] { enabled };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OAUTHCONNECTION_WHERE);

			query.append(_FINDER_COLUMN_ENABLED_ENABLED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(enabled);

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

	private static final String _FINDER_COLUMN_ENABLED_ENABLED_2 = "oAuthConnection.enabled = ?";

	/**
	 * Caches the o auth connection in the entity cache if it is enabled.
	 *
	 * @param oAuthConnection the o auth connection
	 */
	@Override
	public void cacheResult(OAuthConnection oAuthConnection) {
		EntityCacheUtil.putResult(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConnectionImpl.class, oAuthConnection.getPrimaryKey(),
			oAuthConnection);

		oAuthConnection.resetOriginalValues();
	}

	/**
	 * Caches the o auth connections in the entity cache if it is enabled.
	 *
	 * @param oAuthConnections the o auth connections
	 */
	@Override
	public void cacheResult(List<OAuthConnection> oAuthConnections) {
		for (OAuthConnection oAuthConnection : oAuthConnections) {
			if (EntityCacheUtil.getResult(
						OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
						OAuthConnectionImpl.class,
						oAuthConnection.getPrimaryKey()) == null) {
				cacheResult(oAuthConnection);
			}
			else {
				oAuthConnection.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all o auth connections.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(OAuthConnectionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(OAuthConnectionImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the o auth connection.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OAuthConnection oAuthConnection) {
		EntityCacheUtil.removeResult(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConnectionImpl.class, oAuthConnection.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<OAuthConnection> oAuthConnections) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OAuthConnection oAuthConnection : oAuthConnections) {
			EntityCacheUtil.removeResult(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
				OAuthConnectionImpl.class, oAuthConnection.getPrimaryKey());
		}
	}

	/**
	 * Creates a new o auth connection with the primary key. Does not add the o auth connection to the database.
	 *
	 * @param oAuthConnectionId the primary key for the new o auth connection
	 * @return the new o auth connection
	 */
	@Override
	public OAuthConnection create(long oAuthConnectionId) {
		OAuthConnection oAuthConnection = new OAuthConnectionImpl();

		oAuthConnection.setNew(true);
		oAuthConnection.setPrimaryKey(oAuthConnectionId);

		return oAuthConnection;
	}

	/**
	 * Removes the o auth connection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param oAuthConnectionId the primary key of the o auth connection
	 * @return the o auth connection that was removed
	 * @throws com.liferay.oauthlogin.NoSuchOAuthConnectionException if a o auth connection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthConnection remove(long oAuthConnectionId)
		throws NoSuchOAuthConnectionException, SystemException {
		return remove((Serializable)oAuthConnectionId);
	}

	/**
	 * Removes the o auth connection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the o auth connection
	 * @return the o auth connection that was removed
	 * @throws com.liferay.oauthlogin.NoSuchOAuthConnectionException if a o auth connection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthConnection remove(Serializable primaryKey)
		throws NoSuchOAuthConnectionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			OAuthConnection oAuthConnection = (OAuthConnection)session.get(OAuthConnectionImpl.class,
					primaryKey);

			if (oAuthConnection == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOAuthConnectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(oAuthConnection);
		}
		catch (NoSuchOAuthConnectionException nsee) {
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
	protected OAuthConnection removeImpl(OAuthConnection oAuthConnection)
		throws SystemException {
		oAuthConnection = toUnwrappedModel(oAuthConnection);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(oAuthConnection)) {
				oAuthConnection = (OAuthConnection)session.get(OAuthConnectionImpl.class,
						oAuthConnection.getPrimaryKeyObj());
			}

			if (oAuthConnection != null) {
				session.delete(oAuthConnection);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (oAuthConnection != null) {
			clearCache(oAuthConnection);
		}

		return oAuthConnection;
	}

	@Override
	public OAuthConnection updateImpl(
		com.liferay.oauthlogin.model.OAuthConnection oAuthConnection)
		throws SystemException {
		oAuthConnection = toUnwrappedModel(oAuthConnection);

		boolean isNew = oAuthConnection.isNew();

		OAuthConnectionModelImpl oAuthConnectionModelImpl = (OAuthConnectionModelImpl)oAuthConnection;

		Session session = null;

		try {
			session = openSession();

			if (oAuthConnection.isNew()) {
				session.save(oAuthConnection);

				oAuthConnection.setNew(false);
			}
			else {
				session.merge(oAuthConnection);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !OAuthConnectionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((oAuthConnectionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENABLED.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						oAuthConnectionModelImpl.getOriginalEnabled()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ENABLED, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENABLED,
					args);

				args = new Object[] { oAuthConnectionModelImpl.getEnabled() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ENABLED, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENABLED,
					args);
			}
		}

		EntityCacheUtil.putResult(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConnectionImpl.class, oAuthConnection.getPrimaryKey(),
			oAuthConnection);

		return oAuthConnection;
	}

	protected OAuthConnection toUnwrappedModel(OAuthConnection oAuthConnection) {
		if (oAuthConnection instanceof OAuthConnectionImpl) {
			return oAuthConnection;
		}

		OAuthConnectionImpl oAuthConnectionImpl = new OAuthConnectionImpl();

		oAuthConnectionImpl.setNew(oAuthConnection.isNew());
		oAuthConnectionImpl.setPrimaryKey(oAuthConnection.getPrimaryKey());

		oAuthConnectionImpl.setOAuthConnectionId(oAuthConnection.getOAuthConnectionId());
		oAuthConnectionImpl.setCompanyId(oAuthConnection.getCompanyId());
		oAuthConnectionImpl.setUserId(oAuthConnection.getUserId());
		oAuthConnectionImpl.setCreateDate(oAuthConnection.getCreateDate());
		oAuthConnectionImpl.setModifiedDate(oAuthConnection.getModifiedDate());
		oAuthConnectionImpl.setEnabled(oAuthConnection.isEnabled());
		oAuthConnectionImpl.setName(oAuthConnection.getName());
		oAuthConnectionImpl.setDescription(oAuthConnection.getDescription());
		oAuthConnectionImpl.setIconId(oAuthConnection.getIconId());
		oAuthConnectionImpl.setOAuthVersion(oAuthConnection.getOAuthVersion());
		oAuthConnectionImpl.setKey(oAuthConnection.getKey());
		oAuthConnectionImpl.setSecret(oAuthConnection.getSecret());
		oAuthConnectionImpl.setScope(oAuthConnection.getScope());
		oAuthConnectionImpl.setAuthorizeURL(oAuthConnection.getAuthorizeURL());
		oAuthConnectionImpl.setAccessTokenURL(oAuthConnection.getAccessTokenURL());
		oAuthConnectionImpl.setAccessTokenVerb(oAuthConnection.getAccessTokenVerb());
		oAuthConnectionImpl.setAccessTokenExtractorType(oAuthConnection.getAccessTokenExtractorType());
		oAuthConnectionImpl.setRequestTokenURL(oAuthConnection.getRequestTokenURL());
		oAuthConnectionImpl.setRequestTokenVerb(oAuthConnection.getRequestTokenVerb());
		oAuthConnectionImpl.setSignatureServiceType(oAuthConnection.getSignatureServiceType());
		oAuthConnectionImpl.setRedirectURL(oAuthConnection.getRedirectURL());
		oAuthConnectionImpl.setSocialAccountIdURL(oAuthConnection.getSocialAccountIdURL());
		oAuthConnectionImpl.setSocialAccountIdURLVerb(oAuthConnection.getSocialAccountIdURLVerb());
		oAuthConnectionImpl.setSocialAccountIdField(oAuthConnection.getSocialAccountIdField());
		oAuthConnectionImpl.setSocialAccountIdType(oAuthConnection.getSocialAccountIdType());
		oAuthConnectionImpl.setSocialAccountIdScript(oAuthConnection.getSocialAccountIdScript());

		return oAuthConnectionImpl;
	}

	/**
	 * Returns the o auth connection with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the o auth connection
	 * @return the o auth connection
	 * @throws com.liferay.oauthlogin.NoSuchOAuthConnectionException if a o auth connection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthConnection findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOAuthConnectionException, SystemException {
		OAuthConnection oAuthConnection = fetchByPrimaryKey(primaryKey);

		if (oAuthConnection == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOAuthConnectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return oAuthConnection;
	}

	/**
	 * Returns the o auth connection with the primary key or throws a {@link com.liferay.oauthlogin.NoSuchOAuthConnectionException} if it could not be found.
	 *
	 * @param oAuthConnectionId the primary key of the o auth connection
	 * @return the o auth connection
	 * @throws com.liferay.oauthlogin.NoSuchOAuthConnectionException if a o auth connection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthConnection findByPrimaryKey(long oAuthConnectionId)
		throws NoSuchOAuthConnectionException, SystemException {
		return findByPrimaryKey((Serializable)oAuthConnectionId);
	}

	/**
	 * Returns the o auth connection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the o auth connection
	 * @return the o auth connection, or <code>null</code> if a o auth connection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthConnection fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		OAuthConnection oAuthConnection = (OAuthConnection)EntityCacheUtil.getResult(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
				OAuthConnectionImpl.class, primaryKey);

		if (oAuthConnection == _nullOAuthConnection) {
			return null;
		}

		if (oAuthConnection == null) {
			Session session = null;

			try {
				session = openSession();

				oAuthConnection = (OAuthConnection)session.get(OAuthConnectionImpl.class,
						primaryKey);

				if (oAuthConnection != null) {
					cacheResult(oAuthConnection);
				}
				else {
					EntityCacheUtil.putResult(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
						OAuthConnectionImpl.class, primaryKey,
						_nullOAuthConnection);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
					OAuthConnectionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return oAuthConnection;
	}

	/**
	 * Returns the o auth connection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param oAuthConnectionId the primary key of the o auth connection
	 * @return the o auth connection, or <code>null</code> if a o auth connection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthConnection fetchByPrimaryKey(long oAuthConnectionId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)oAuthConnectionId);
	}

	/**
	 * Returns all the o auth connections.
	 *
	 * @return the o auth connections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OAuthConnection> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the o auth connections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauthlogin.model.impl.OAuthConnectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of o auth connections
	 * @param end the upper bound of the range of o auth connections (not inclusive)
	 * @return the range of o auth connections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OAuthConnection> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth connections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauthlogin.model.impl.OAuthConnectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of o auth connections
	 * @param end the upper bound of the range of o auth connections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of o auth connections
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OAuthConnection> findAll(int start, int end,
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

		List<OAuthConnection> list = (List<OAuthConnection>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_OAUTHCONNECTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OAUTHCONNECTION;

				if (pagination) {
					sql = sql.concat(OAuthConnectionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OAuthConnection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<OAuthConnection>(list);
				}
				else {
					list = (List<OAuthConnection>)QueryUtil.list(q,
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
	 * Removes all the o auth connections from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (OAuthConnection oAuthConnection : findAll()) {
			remove(oAuthConnection);
		}
	}

	/**
	 * Returns the number of o auth connections.
	 *
	 * @return the number of o auth connections
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

				Query q = session.createQuery(_SQL_COUNT_OAUTHCONNECTION);

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
	 * Initializes the o auth connection persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.oauthlogin.model.OAuthConnection")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<OAuthConnection>> listenersList = new ArrayList<ModelListener<OAuthConnection>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<OAuthConnection>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(OAuthConnectionImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_OAUTHCONNECTION = "SELECT oAuthConnection FROM OAuthConnection oAuthConnection";
	private static final String _SQL_SELECT_OAUTHCONNECTION_WHERE = "SELECT oAuthConnection FROM OAuthConnection oAuthConnection WHERE ";
	private static final String _SQL_COUNT_OAUTHCONNECTION = "SELECT COUNT(oAuthConnection) FROM OAuthConnection oAuthConnection";
	private static final String _SQL_COUNT_OAUTHCONNECTION_WHERE = "SELECT COUNT(oAuthConnection) FROM OAuthConnection oAuthConnection WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "oAuthConnection.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OAuthConnection exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OAuthConnection exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(OAuthConnectionPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"key"
			});
	private static OAuthConnection _nullOAuthConnection = new OAuthConnectionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<OAuthConnection> toCacheModel() {
				return _nullOAuthConnectionCacheModel;
			}
		};

	private static CacheModel<OAuthConnection> _nullOAuthConnectionCacheModel = new CacheModel<OAuthConnection>() {
			@Override
			public OAuthConnection toEntityModel() {
				return _nullOAuthConnection;
			}
		};
}