/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.saml.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.saml.NoSuchSSOSessionException;
import com.liferay.portal.saml.model.SSOSession;
import com.liferay.portal.saml.model.impl.SSOSessionImpl;
import com.liferay.portal.saml.model.impl.SSOSessionModelImpl;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the s s o session service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SSOSessionPersistence
 * @see SSOSessionUtil
 * @generated
 */
public class SSOSessionPersistenceImpl extends BasePersistenceImpl<SSOSession>
	implements SSOSessionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SSOSessionUtil} to access the s s o session persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SSOSessionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FETCH_BY_KEY = new FinderPath(SSOSessionModelImpl.ENTITY_CACHE_ENABLED,
			SSOSessionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
			"fetchByKey", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_KEY = new FinderPath(SSOSessionModelImpl.ENTITY_CACHE_ENABLED,
			SSOSessionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByKey", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(SSOSessionModelImpl.ENTITY_CACHE_ENABLED,
			SSOSessionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SSOSessionModelImpl.ENTITY_CACHE_ENABLED,
			SSOSessionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	/**
	 * Caches the s s o session in the entity cache if it is enabled.
	 *
	 * @param ssoSession the s s o session to cache
	 */
	public void cacheResult(SSOSession ssoSession) {
		EntityCacheUtil.putResult(SSOSessionModelImpl.ENTITY_CACHE_ENABLED,
			SSOSessionImpl.class, ssoSession.getPrimaryKey(), ssoSession);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
			new Object[] { ssoSession.getKey() }, ssoSession);
	}

	/**
	 * Caches the s s o sessions in the entity cache if it is enabled.
	 *
	 * @param ssoSessions the s s o sessions to cache
	 */
	public void cacheResult(List<SSOSession> ssoSessions) {
		for (SSOSession ssoSession : ssoSessions) {
			if (EntityCacheUtil.getResult(
						SSOSessionModelImpl.ENTITY_CACHE_ENABLED,
						SSOSessionImpl.class, ssoSession.getPrimaryKey(), this) == null) {
				cacheResult(ssoSession);
			}
		}
	}

	/**
	 * Clears the cache for all s s o sessions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(SSOSessionImpl.class.getName());
		EntityCacheUtil.clearCache(SSOSessionImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the s s o session.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(SSOSession ssoSession) {
		EntityCacheUtil.removeResult(SSOSessionModelImpl.ENTITY_CACHE_ENABLED,
			SSOSessionImpl.class, ssoSession.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KEY,
			new Object[] { ssoSession.getKey() });
	}

	/**
	 * Creates a new s s o session with the primary key. Does not add the s s o session to the database.
	 *
	 * @param ssoSessionId the primary key for the new s s o session
	 * @return the new s s o session
	 */
	public SSOSession create(long ssoSessionId) {
		SSOSession ssoSession = new SSOSessionImpl();

		ssoSession.setNew(true);
		ssoSession.setPrimaryKey(ssoSessionId);

		return ssoSession;
	}

	/**
	 * Removes the s s o session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the s s o session to remove
	 * @return the s s o session that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a s s o session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SSOSession remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the s s o session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ssoSessionId the primary key of the s s o session to remove
	 * @return the s s o session that was removed
	 * @throws com.liferay.portal.saml.NoSuchSSOSessionException if a s s o session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SSOSession remove(long ssoSessionId)
		throws NoSuchSSOSessionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SSOSession ssoSession = (SSOSession)session.get(SSOSessionImpl.class,
					new Long(ssoSessionId));

			if (ssoSession == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + ssoSessionId);
				}

				throw new NoSuchSSOSessionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					ssoSessionId);
			}

			return ssoSessionPersistence.remove(ssoSession);
		}
		catch (NoSuchSSOSessionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SSOSession removeImpl(SSOSession ssoSession)
		throws SystemException {
		ssoSession = toUnwrappedModel(ssoSession);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, ssoSession);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		SSOSessionModelImpl ssoSessionModelImpl = (SSOSessionModelImpl)ssoSession;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KEY,
			new Object[] { ssoSessionModelImpl.getOriginalKey() });

		EntityCacheUtil.removeResult(SSOSessionModelImpl.ENTITY_CACHE_ENABLED,
			SSOSessionImpl.class, ssoSession.getPrimaryKey());

		return ssoSession;
	}

	public SSOSession updateImpl(
		com.liferay.portal.saml.model.SSOSession ssoSession, boolean merge)
		throws SystemException {
		ssoSession = toUnwrappedModel(ssoSession);

		boolean isNew = ssoSession.isNew();

		SSOSessionModelImpl ssoSessionModelImpl = (SSOSessionModelImpl)ssoSession;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, ssoSession, merge);

			ssoSession.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(SSOSessionModelImpl.ENTITY_CACHE_ENABLED,
			SSOSessionImpl.class, ssoSession.getPrimaryKey(), ssoSession);

		if (!isNew &&
				(!Validator.equals(ssoSession.getKey(),
					ssoSessionModelImpl.getOriginalKey()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KEY,
				new Object[] { ssoSessionModelImpl.getOriginalKey() });
		}

		if (isNew ||
				(!Validator.equals(ssoSession.getKey(),
					ssoSessionModelImpl.getOriginalKey()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
				new Object[] { ssoSession.getKey() }, ssoSession);
		}

		return ssoSession;
	}

	protected SSOSession toUnwrappedModel(SSOSession ssoSession) {
		if (ssoSession instanceof SSOSessionImpl) {
			return ssoSession;
		}

		SSOSessionImpl ssoSessionImpl = new SSOSessionImpl();

		ssoSessionImpl.setNew(ssoSession.isNew());
		ssoSessionImpl.setPrimaryKey(ssoSession.getPrimaryKey());

		ssoSessionImpl.setSsoSessionId(ssoSession.getSsoSessionId());
		ssoSessionImpl.setCompanyId(ssoSession.getCompanyId());
		ssoSessionImpl.setUserId(ssoSession.getUserId());
		ssoSessionImpl.setCreateDate(ssoSession.getCreateDate());
		ssoSessionImpl.setLastActiveDate(ssoSession.getLastActiveDate());
		ssoSessionImpl.setKey(ssoSession.getKey());

		return ssoSessionImpl;
	}

	/**
	 * Finds the s s o session with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the s s o session to find
	 * @return the s s o session
	 * @throws com.liferay.portal.NoSuchModelException if a s s o session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SSOSession findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the s s o session with the primary key or throws a {@link com.liferay.portal.saml.NoSuchSSOSessionException} if it could not be found.
	 *
	 * @param ssoSessionId the primary key of the s s o session to find
	 * @return the s s o session
	 * @throws com.liferay.portal.saml.NoSuchSSOSessionException if a s s o session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SSOSession findByPrimaryKey(long ssoSessionId)
		throws NoSuchSSOSessionException, SystemException {
		SSOSession ssoSession = fetchByPrimaryKey(ssoSessionId);

		if (ssoSession == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + ssoSessionId);
			}

			throw new NoSuchSSOSessionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				ssoSessionId);
		}

		return ssoSession;
	}

	/**
	 * Finds the s s o session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the s s o session to find
	 * @return the s s o session, or <code>null</code> if a s s o session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SSOSession fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the s s o session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ssoSessionId the primary key of the s s o session to find
	 * @return the s s o session, or <code>null</code> if a s s o session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SSOSession fetchByPrimaryKey(long ssoSessionId)
		throws SystemException {
		SSOSession ssoSession = (SSOSession)EntityCacheUtil.getResult(SSOSessionModelImpl.ENTITY_CACHE_ENABLED,
				SSOSessionImpl.class, ssoSessionId, this);

		if (ssoSession == null) {
			Session session = null;

			try {
				session = openSession();

				ssoSession = (SSOSession)session.get(SSOSessionImpl.class,
						new Long(ssoSessionId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (ssoSession != null) {
					cacheResult(ssoSession);
				}

				closeSession(session);
			}
		}

		return ssoSession;
	}

	/**
	 * Finds the s s o session where key = &#63; or throws a {@link com.liferay.portal.saml.NoSuchSSOSessionException} if it could not be found.
	 *
	 * @param key the key to search with
	 * @return the matching s s o session
	 * @throws com.liferay.portal.saml.NoSuchSSOSessionException if a matching s s o session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SSOSession findByKey(String key)
		throws NoSuchSSOSessionException, SystemException {
		SSOSession ssoSession = fetchByKey(key);

		if (ssoSession == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("key=");
			msg.append(key);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchSSOSessionException(msg.toString());
		}

		return ssoSession;
	}

	/**
	 * Finds the s s o session where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param key the key to search with
	 * @return the matching s s o session, or <code>null</code> if a matching s s o session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SSOSession fetchByKey(String key) throws SystemException {
		return fetchByKey(key, true);
	}

	/**
	 * Finds the s s o session where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param key the key to search with
	 * @return the matching s s o session, or <code>null</code> if a matching s s o session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SSOSession fetchByKey(String key, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { key };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_KEY,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_SSOSESSION_WHERE);

			if (key == null) {
				query.append(_FINDER_COLUMN_KEY_KEY_1);
			}
			else {
				if (key.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KEY_KEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_KEY_KEY_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (key != null) {
					qPos.add(key);
				}

				List<SSOSession> list = q.list();

				result = list;

				SSOSession ssoSession = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
						finderArgs, list);
				}
				else {
					ssoSession = list.get(0);

					cacheResult(ssoSession);

					if ((ssoSession.getKey() == null) ||
							!ssoSession.getKey().equals(key)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KEY,
							finderArgs, ssoSession);
					}
				}

				return ssoSession;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KEY,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (SSOSession)result;
			}
		}
	}

	/**
	 * Finds all the s s o sessions.
	 *
	 * @return the s s o sessions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SSOSession> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the s s o sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of s s o sessions to return
	 * @param end the upper bound of the range of s s o sessions to return (not inclusive)
	 * @return the range of s s o sessions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SSOSession> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the s s o sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of s s o sessions to return
	 * @param end the upper bound of the range of s s o sessions to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of s s o sessions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SSOSession> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<SSOSession> list = (List<SSOSession>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SSOSESSION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SSOSESSION;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<SSOSession>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<SSOSession>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs,
						list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes the s s o session where key = &#63; from the database.
	 *
	 * @param key the key to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKey(String key)
		throws NoSuchSSOSessionException, SystemException {
		SSOSession ssoSession = findByKey(key);

		ssoSessionPersistence.remove(ssoSession);
	}

	/**
	 * Removes all the s s o sessions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (SSOSession ssoSession : findAll()) {
			ssoSessionPersistence.remove(ssoSession);
		}
	}

	/**
	 * Counts all the s s o sessions where key = &#63;.
	 *
	 * @param key the key to search with
	 * @return the number of matching s s o sessions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKey(String key) throws SystemException {
		Object[] finderArgs = new Object[] { key };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KEY,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SSOSESSION_WHERE);

			if (key == null) {
				query.append(_FINDER_COLUMN_KEY_KEY_1);
			}
			else {
				if (key.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KEY_KEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_KEY_KEY_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (key != null) {
					qPos.add(key);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KEY, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the s s o sessions.
	 *
	 * @return the number of s s o sessions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SSOSESSION);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the s s o session persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.saml.model.SSOSession")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SSOSession>> listenersList = new ArrayList<ModelListener<SSOSession>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<SSOSession>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(SSOSessionImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = SPSessionPersistence.class)
	protected SPSessionPersistence spSessionPersistence;
	@BeanReference(type = SSOSessionPersistence.class)
	protected SSOSessionPersistence ssoSessionPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_SSOSESSION = "SELECT ssoSession FROM SSOSession ssoSession";
	private static final String _SQL_SELECT_SSOSESSION_WHERE = "SELECT ssoSession FROM SSOSession ssoSession WHERE ";
	private static final String _SQL_COUNT_SSOSESSION = "SELECT COUNT(ssoSession) FROM SSOSession ssoSession";
	private static final String _SQL_COUNT_SSOSESSION_WHERE = "SELECT COUNT(ssoSession) FROM SSOSession ssoSession WHERE ";
	private static final String _FINDER_COLUMN_KEY_KEY_1 = "ssoSession.key IS NULL";
	private static final String _FINDER_COLUMN_KEY_KEY_2 = "ssoSession.key = ?";
	private static final String _FINDER_COLUMN_KEY_KEY_3 = "(ssoSession.key IS NULL OR ssoSession.key = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ssoSession.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SSOSession exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SSOSession exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(SSOSessionPersistenceImpl.class);
}