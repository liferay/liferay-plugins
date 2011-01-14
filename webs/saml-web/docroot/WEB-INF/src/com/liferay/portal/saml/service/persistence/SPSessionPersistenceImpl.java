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
import com.liferay.portal.saml.NoSuchSPSessionException;
import com.liferay.portal.saml.model.SPSession;
import com.liferay.portal.saml.model.impl.SPSessionImpl;
import com.liferay.portal.saml.model.impl.SPSessionModelImpl;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the s p session service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SPSessionPersistence
 * @see SPSessionUtil
 * @generated
 */
public class SPSessionPersistenceImpl extends BasePersistenceImpl<SPSession>
	implements SPSessionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SPSessionUtil} to access the s p session persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SPSessionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_SSOSESSIONID = new FinderPath(SPSessionModelImpl.ENTITY_CACHE_ENABLED,
			SPSessionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findBySSOSessionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_SSOSESSIONID = new FinderPath(SPSessionModelImpl.ENTITY_CACHE_ENABLED,
			SPSessionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countBySSOSessionId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_S_I = new FinderPath(SPSessionModelImpl.ENTITY_CACHE_ENABLED,
			SPSessionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
			"fetchByS_I",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_S_I = new FinderPath(SPSessionModelImpl.ENTITY_CACHE_ENABLED,
			SPSessionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByS_I",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(SPSessionModelImpl.ENTITY_CACHE_ENABLED,
			SPSessionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SPSessionModelImpl.ENTITY_CACHE_ENABLED,
			SPSessionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	/**
	 * Caches the s p session in the entity cache if it is enabled.
	 *
	 * @param spSession the s p session to cache
	 */
	public void cacheResult(SPSession spSession) {
		EntityCacheUtil.putResult(SPSessionModelImpl.ENTITY_CACHE_ENABLED,
			SPSessionImpl.class, spSession.getPrimaryKey(), spSession);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_S_I,
			new Object[] {
				new Long(spSession.getSsoSessionId()),
				
			spSession.getIssuer()
			}, spSession);
	}

	/**
	 * Caches the s p sessions in the entity cache if it is enabled.
	 *
	 * @param spSessions the s p sessions to cache
	 */
	public void cacheResult(List<SPSession> spSessions) {
		for (SPSession spSession : spSessions) {
			if (EntityCacheUtil.getResult(
						SPSessionModelImpl.ENTITY_CACHE_ENABLED,
						SPSessionImpl.class, spSession.getPrimaryKey(), this) == null) {
				cacheResult(spSession);
			}
		}
	}

	/**
	 * Clears the cache for all s p sessions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(SPSessionImpl.class.getName());
		EntityCacheUtil.clearCache(SPSessionImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the s p session.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(SPSession spSession) {
		EntityCacheUtil.removeResult(SPSessionModelImpl.ENTITY_CACHE_ENABLED,
			SPSessionImpl.class, spSession.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_S_I,
			new Object[] {
				new Long(spSession.getSsoSessionId()),
				
			spSession.getIssuer()
			});
	}

	/**
	 * Creates a new s p session with the primary key. Does not add the s p session to the database.
	 *
	 * @param spSessionId the primary key for the new s p session
	 * @return the new s p session
	 */
	public SPSession create(long spSessionId) {
		SPSession spSession = new SPSessionImpl();

		spSession.setNew(true);
		spSession.setPrimaryKey(spSessionId);

		return spSession;
	}

	/**
	 * Removes the s p session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the s p session to remove
	 * @return the s p session that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a s p session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SPSession remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the s p session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param spSessionId the primary key of the s p session to remove
	 * @return the s p session that was removed
	 * @throws com.liferay.portal.saml.NoSuchSPSessionException if a s p session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SPSession remove(long spSessionId)
		throws NoSuchSPSessionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SPSession spSession = (SPSession)session.get(SPSessionImpl.class,
					new Long(spSessionId));

			if (spSession == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + spSessionId);
				}

				throw new NoSuchSPSessionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					spSessionId);
			}

			return spSessionPersistence.remove(spSession);
		}
		catch (NoSuchSPSessionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SPSession removeImpl(SPSession spSession)
		throws SystemException {
		spSession = toUnwrappedModel(spSession);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, spSession);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		SPSessionModelImpl spSessionModelImpl = (SPSessionModelImpl)spSession;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_S_I,
			new Object[] {
				new Long(spSessionModelImpl.getOriginalSsoSessionId()),
				
			spSessionModelImpl.getOriginalIssuer()
			});

		EntityCacheUtil.removeResult(SPSessionModelImpl.ENTITY_CACHE_ENABLED,
			SPSessionImpl.class, spSession.getPrimaryKey());

		return spSession;
	}

	public SPSession updateImpl(
		com.liferay.portal.saml.model.SPSession spSession, boolean merge)
		throws SystemException {
		spSession = toUnwrappedModel(spSession);

		boolean isNew = spSession.isNew();

		SPSessionModelImpl spSessionModelImpl = (SPSessionModelImpl)spSession;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, spSession, merge);

			spSession.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(SPSessionModelImpl.ENTITY_CACHE_ENABLED,
			SPSessionImpl.class, spSession.getPrimaryKey(), spSession);

		if (!isNew &&
				((spSession.getSsoSessionId() != spSessionModelImpl.getOriginalSsoSessionId()) ||
				!Validator.equals(spSession.getIssuer(),
					spSessionModelImpl.getOriginalIssuer()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_S_I,
				new Object[] {
					new Long(spSessionModelImpl.getOriginalSsoSessionId()),
					
				spSessionModelImpl.getOriginalIssuer()
				});
		}

		if (isNew ||
				((spSession.getSsoSessionId() != spSessionModelImpl.getOriginalSsoSessionId()) ||
				!Validator.equals(spSession.getIssuer(),
					spSessionModelImpl.getOriginalIssuer()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_S_I,
				new Object[] {
					new Long(spSession.getSsoSessionId()),
					
				spSession.getIssuer()
				}, spSession);
		}

		return spSession;
	}

	protected SPSession toUnwrappedModel(SPSession spSession) {
		if (spSession instanceof SPSessionImpl) {
			return spSession;
		}

		SPSessionImpl spSessionImpl = new SPSessionImpl();

		spSessionImpl.setNew(spSession.isNew());
		spSessionImpl.setPrimaryKey(spSession.getPrimaryKey());

		spSessionImpl.setSpSessionId(spSession.getSpSessionId());
		spSessionImpl.setCompanyId(spSession.getCompanyId());
		spSessionImpl.setUserId(spSession.getUserId());
		spSessionImpl.setCreateDate(spSession.getCreateDate());
		spSessionImpl.setSsoSessionId(spSession.getSsoSessionId());
		spSessionImpl.setIssuer(spSession.getIssuer());

		return spSessionImpl;
	}

	/**
	 * Finds the s p session with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the s p session to find
	 * @return the s p session
	 * @throws com.liferay.portal.NoSuchModelException if a s p session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SPSession findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the s p session with the primary key or throws a {@link com.liferay.portal.saml.NoSuchSPSessionException} if it could not be found.
	 *
	 * @param spSessionId the primary key of the s p session to find
	 * @return the s p session
	 * @throws com.liferay.portal.saml.NoSuchSPSessionException if a s p session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SPSession findByPrimaryKey(long spSessionId)
		throws NoSuchSPSessionException, SystemException {
		SPSession spSession = fetchByPrimaryKey(spSessionId);

		if (spSession == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + spSessionId);
			}

			throw new NoSuchSPSessionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				spSessionId);
		}

		return spSession;
	}

	/**
	 * Finds the s p session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the s p session to find
	 * @return the s p session, or <code>null</code> if a s p session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SPSession fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the s p session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param spSessionId the primary key of the s p session to find
	 * @return the s p session, or <code>null</code> if a s p session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SPSession fetchByPrimaryKey(long spSessionId)
		throws SystemException {
		SPSession spSession = (SPSession)EntityCacheUtil.getResult(SPSessionModelImpl.ENTITY_CACHE_ENABLED,
				SPSessionImpl.class, spSessionId, this);

		if (spSession == null) {
			Session session = null;

			try {
				session = openSession();

				spSession = (SPSession)session.get(SPSessionImpl.class,
						new Long(spSessionId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (spSession != null) {
					cacheResult(spSession);
				}

				closeSession(session);
			}
		}

		return spSession;
	}

	/**
	 * Finds all the s p sessions where ssoSessionId = &#63;.
	 *
	 * @param ssoSessionId the sso session ID to search with
	 * @return the matching s p sessions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SPSession> findBySSOSessionId(long ssoSessionId)
		throws SystemException {
		return findBySSOSessionId(ssoSessionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the s p sessions where ssoSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ssoSessionId the sso session ID to search with
	 * @param start the lower bound of the range of s p sessions to return
	 * @param end the upper bound of the range of s p sessions to return (not inclusive)
	 * @return the range of matching s p sessions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SPSession> findBySSOSessionId(long ssoSessionId, int start,
		int end) throws SystemException {
		return findBySSOSessionId(ssoSessionId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the s p sessions where ssoSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ssoSessionId the sso session ID to search with
	 * @param start the lower bound of the range of s p sessions to return
	 * @param end the upper bound of the range of s p sessions to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching s p sessions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SPSession> findBySSOSessionId(long ssoSessionId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				ssoSessionId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<SPSession> list = (List<SPSession>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_SSOSESSIONID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_SPSESSION_WHERE);

			query.append(_FINDER_COLUMN_SSOSESSIONID_SSOSESSIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ssoSessionId);

				list = (List<SPSession>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_SSOSESSIONID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_SSOSESSIONID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first s p session in the ordered set where ssoSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ssoSessionId the sso session ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching s p session
	 * @throws com.liferay.portal.saml.NoSuchSPSessionException if a matching s p session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SPSession findBySSOSessionId_First(long ssoSessionId,
		OrderByComparator orderByComparator)
		throws NoSuchSPSessionException, SystemException {
		List<SPSession> list = findBySSOSessionId(ssoSessionId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ssoSessionId=");
			msg.append(ssoSessionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSPSessionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last s p session in the ordered set where ssoSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param ssoSessionId the sso session ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching s p session
	 * @throws com.liferay.portal.saml.NoSuchSPSessionException if a matching s p session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SPSession findBySSOSessionId_Last(long ssoSessionId,
		OrderByComparator orderByComparator)
		throws NoSuchSPSessionException, SystemException {
		int count = countBySSOSessionId(ssoSessionId);

		List<SPSession> list = findBySSOSessionId(ssoSessionId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ssoSessionId=");
			msg.append(ssoSessionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSPSessionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the s p sessions before and after the current s p session in the ordered set where ssoSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param spSessionId the primary key of the current s p session
	 * @param ssoSessionId the sso session ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next s p session
	 * @throws com.liferay.portal.saml.NoSuchSPSessionException if a s p session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SPSession[] findBySSOSessionId_PrevAndNext(long spSessionId,
		long ssoSessionId, OrderByComparator orderByComparator)
		throws NoSuchSPSessionException, SystemException {
		SPSession spSession = findByPrimaryKey(spSessionId);

		Session session = null;

		try {
			session = openSession();

			SPSession[] array = new SPSessionImpl[3];

			array[0] = getBySSOSessionId_PrevAndNext(session, spSession,
					ssoSessionId, orderByComparator, true);

			array[1] = spSession;

			array[2] = getBySSOSessionId_PrevAndNext(session, spSession,
					ssoSessionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SPSession getBySSOSessionId_PrevAndNext(Session session,
		SPSession spSession, long ssoSessionId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SPSESSION_WHERE);

		query.append(_FINDER_COLUMN_SSOSESSIONID_SSOSESSIONID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ssoSessionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(spSession);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SPSession> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds the s p session where ssoSessionId = &#63; and issuer = &#63; or throws a {@link com.liferay.portal.saml.NoSuchSPSessionException} if it could not be found.
	 *
	 * @param ssoSessionId the sso session ID to search with
	 * @param issuer the issuer to search with
	 * @return the matching s p session
	 * @throws com.liferay.portal.saml.NoSuchSPSessionException if a matching s p session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SPSession findByS_I(long ssoSessionId, String issuer)
		throws NoSuchSPSessionException, SystemException {
		SPSession spSession = fetchByS_I(ssoSessionId, issuer);

		if (spSession == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ssoSessionId=");
			msg.append(ssoSessionId);

			msg.append(", issuer=");
			msg.append(issuer);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchSPSessionException(msg.toString());
		}

		return spSession;
	}

	/**
	 * Finds the s p session where ssoSessionId = &#63; and issuer = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ssoSessionId the sso session ID to search with
	 * @param issuer the issuer to search with
	 * @return the matching s p session, or <code>null</code> if a matching s p session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SPSession fetchByS_I(long ssoSessionId, String issuer)
		throws SystemException {
		return fetchByS_I(ssoSessionId, issuer, true);
	}

	/**
	 * Finds the s p session where ssoSessionId = &#63; and issuer = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ssoSessionId the sso session ID to search with
	 * @param issuer the issuer to search with
	 * @return the matching s p session, or <code>null</code> if a matching s p session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SPSession fetchByS_I(long ssoSessionId, String issuer,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { ssoSessionId, issuer };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_S_I,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SPSESSION_WHERE);

			query.append(_FINDER_COLUMN_S_I_SSOSESSIONID_2);

			if (issuer == null) {
				query.append(_FINDER_COLUMN_S_I_ISSUER_1);
			}
			else {
				if (issuer.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_S_I_ISSUER_3);
				}
				else {
					query.append(_FINDER_COLUMN_S_I_ISSUER_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ssoSessionId);

				if (issuer != null) {
					qPos.add(issuer);
				}

				List<SPSession> list = q.list();

				result = list;

				SPSession spSession = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_S_I,
						finderArgs, list);
				}
				else {
					spSession = list.get(0);

					cacheResult(spSession);

					if ((spSession.getSsoSessionId() != ssoSessionId) ||
							(spSession.getIssuer() == null) ||
							!spSession.getIssuer().equals(issuer)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_S_I,
							finderArgs, spSession);
					}
				}

				return spSession;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_S_I,
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
				return (SPSession)result;
			}
		}
	}

	/**
	 * Finds all the s p sessions.
	 *
	 * @return the s p sessions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SPSession> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the s p sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of s p sessions to return
	 * @param end the upper bound of the range of s p sessions to return (not inclusive)
	 * @return the range of s p sessions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SPSession> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the s p sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of s p sessions to return
	 * @param end the upper bound of the range of s p sessions to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of s p sessions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SPSession> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<SPSession> list = (List<SPSession>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SPSESSION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SPSESSION;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<SPSession>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<SPSession>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the s p sessions where ssoSessionId = &#63; from the database.
	 *
	 * @param ssoSessionId the sso session ID to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBySSOSessionId(long ssoSessionId)
		throws SystemException {
		for (SPSession spSession : findBySSOSessionId(ssoSessionId)) {
			spSessionPersistence.remove(spSession);
		}
	}

	/**
	 * Removes the s p session where ssoSessionId = &#63; and issuer = &#63; from the database.
	 *
	 * @param ssoSessionId the sso session ID to search with
	 * @param issuer the issuer to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByS_I(long ssoSessionId, String issuer)
		throws NoSuchSPSessionException, SystemException {
		SPSession spSession = findByS_I(ssoSessionId, issuer);

		spSessionPersistence.remove(spSession);
	}

	/**
	 * Removes all the s p sessions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (SPSession spSession : findAll()) {
			spSessionPersistence.remove(spSession);
		}
	}

	/**
	 * Counts all the s p sessions where ssoSessionId = &#63;.
	 *
	 * @param ssoSessionId the sso session ID to search with
	 * @return the number of matching s p sessions
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySSOSessionId(long ssoSessionId) throws SystemException {
		Object[] finderArgs = new Object[] { ssoSessionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SSOSESSIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SPSESSION_WHERE);

			query.append(_FINDER_COLUMN_SSOSESSIONID_SSOSESSIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ssoSessionId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SSOSESSIONID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the s p sessions where ssoSessionId = &#63; and issuer = &#63;.
	 *
	 * @param ssoSessionId the sso session ID to search with
	 * @param issuer the issuer to search with
	 * @return the number of matching s p sessions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByS_I(long ssoSessionId, String issuer)
		throws SystemException {
		Object[] finderArgs = new Object[] { ssoSessionId, issuer };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_S_I,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SPSESSION_WHERE);

			query.append(_FINDER_COLUMN_S_I_SSOSESSIONID_2);

			if (issuer == null) {
				query.append(_FINDER_COLUMN_S_I_ISSUER_1);
			}
			else {
				if (issuer.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_S_I_ISSUER_3);
				}
				else {
					query.append(_FINDER_COLUMN_S_I_ISSUER_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ssoSessionId);

				if (issuer != null) {
					qPos.add(issuer);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_S_I, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the s p sessions.
	 *
	 * @return the number of s p sessions
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

				Query q = session.createQuery(_SQL_COUNT_SPSESSION);

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
	 * Initializes the s p session persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.saml.model.SPSession")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SPSession>> listenersList = new ArrayList<ModelListener<SPSession>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<SPSession>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SPSessionImpl.class.getName());
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
	private static final String _SQL_SELECT_SPSESSION = "SELECT spSession FROM SPSession spSession";
	private static final String _SQL_SELECT_SPSESSION_WHERE = "SELECT spSession FROM SPSession spSession WHERE ";
	private static final String _SQL_COUNT_SPSESSION = "SELECT COUNT(spSession) FROM SPSession spSession";
	private static final String _SQL_COUNT_SPSESSION_WHERE = "SELECT COUNT(spSession) FROM SPSession spSession WHERE ";
	private static final String _FINDER_COLUMN_SSOSESSIONID_SSOSESSIONID_2 = "spSession.ssoSessionId = ?";
	private static final String _FINDER_COLUMN_S_I_SSOSESSIONID_2 = "spSession.ssoSessionId = ? AND ";
	private static final String _FINDER_COLUMN_S_I_ISSUER_1 = "spSession.issuer IS NULL";
	private static final String _FINDER_COLUMN_S_I_ISSUER_2 = "spSession.issuer = ?";
	private static final String _FINDER_COLUMN_S_I_ISSUER_3 = "(spSession.issuer IS NULL OR spSession.issuer = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "spSession.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SPSession exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SPSession exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(SPSessionPersistenceImpl.class);
}