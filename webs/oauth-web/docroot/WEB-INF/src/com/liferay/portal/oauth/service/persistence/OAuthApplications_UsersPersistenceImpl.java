/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.oauth.service.persistence;

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
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.oauth.NoSuchApplications_UsersException;
import com.liferay.portal.oauth.model.OAuthApplications_Users;
import com.liferay.portal.oauth.model.impl.OAuthApplications_UsersImpl;
import com.liferay.portal.oauth.model.impl.OAuthApplications_UsersModelImpl;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the o auth applications_ users service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OAuthApplications_UsersPersistence
 * @see OAuthApplications_UsersUtil
 * @generated
 */
public class OAuthApplications_UsersPersistenceImpl extends BasePersistenceImpl<OAuthApplications_Users>
	implements OAuthApplications_UsersPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OAuthApplications_UsersUtil} to access the o auth applications_ users persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OAuthApplications_UsersImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_FETCH_BY_A_U = new FinderPath(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersModelImpl.FINDER_CACHE_ENABLED,
			OAuthApplications_UsersImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByA_U",
			new String[] { Long.class.getName(), Long.class.getName() },
			OAuthApplications_UsersModelImpl.APPLICATIONID_COLUMN_BITMASK |
			OAuthApplications_UsersModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_A_U = new FinderPath(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByA_U",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_ACCESSTOKEN = new FinderPath(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersModelImpl.FINDER_CACHE_ENABLED,
			OAuthApplications_UsersImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByAccessToken", new String[] { String.class.getName() },
			OAuthApplications_UsersModelImpl.ACCESSTOKEN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCESSTOKEN = new FinderPath(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccessToken",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersModelImpl.FINDER_CACHE_ENABLED,
			OAuthApplications_UsersImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersModelImpl.FINDER_CACHE_ENABLED,
			OAuthApplications_UsersImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the o auth applications_ users in the entity cache if it is enabled.
	 *
	 * @param oAuthApplications_Users the o auth applications_ users
	 */
	public void cacheResult(OAuthApplications_Users oAuthApplications_Users) {
		EntityCacheUtil.putResult(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersImpl.class,
			oAuthApplications_Users.getPrimaryKey(), oAuthApplications_Users);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_U,
			new Object[] {
				Long.valueOf(oAuthApplications_Users.getApplicationId()),
				Long.valueOf(oAuthApplications_Users.getUserId())
			}, oAuthApplications_Users);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ACCESSTOKEN,
			new Object[] { oAuthApplications_Users.getAccessToken() },
			oAuthApplications_Users);

		oAuthApplications_Users.resetOriginalValues();
	}

	/**
	 * Caches the o auth applications_ userses in the entity cache if it is enabled.
	 *
	 * @param oAuthApplications_Userses the o auth applications_ userses
	 */
	public void cacheResult(
		List<OAuthApplications_Users> oAuthApplications_Userses) {
		for (OAuthApplications_Users oAuthApplications_Users : oAuthApplications_Userses) {
			if (EntityCacheUtil.getResult(
						OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
						OAuthApplications_UsersImpl.class,
						oAuthApplications_Users.getPrimaryKey()) == null) {
				cacheResult(oAuthApplications_Users);
			}
			else {
				oAuthApplications_Users.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all o auth applications_ userses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(OAuthApplications_UsersImpl.class.getName());
		}

		EntityCacheUtil.clearCache(OAuthApplications_UsersImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the o auth applications_ users.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OAuthApplications_Users oAuthApplications_Users) {
		EntityCacheUtil.removeResult(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersImpl.class,
			oAuthApplications_Users.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(oAuthApplications_Users);
	}

	@Override
	public void clearCache(
		List<OAuthApplications_Users> oAuthApplications_Userses) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OAuthApplications_Users oAuthApplications_Users : oAuthApplications_Userses) {
			EntityCacheUtil.removeResult(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
				OAuthApplications_UsersImpl.class,
				oAuthApplications_Users.getPrimaryKey());

			clearUniqueFindersCache(oAuthApplications_Users);
		}
	}

	protected void clearUniqueFindersCache(
		OAuthApplications_Users oAuthApplications_Users) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_U,
			new Object[] {
				Long.valueOf(oAuthApplications_Users.getApplicationId()),
				Long.valueOf(oAuthApplications_Users.getUserId())
			});

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ACCESSTOKEN,
			new Object[] { oAuthApplications_Users.getAccessToken() });
	}

	/**
	 * Creates a new o auth applications_ users with the primary key. Does not add the o auth applications_ users to the database.
	 *
	 * @param oaauid the primary key for the new o auth applications_ users
	 * @return the new o auth applications_ users
	 */
	public OAuthApplications_Users create(long oaauid) {
		OAuthApplications_Users oAuthApplications_Users = new OAuthApplications_UsersImpl();

		oAuthApplications_Users.setNew(true);
		oAuthApplications_Users.setPrimaryKey(oaauid);

		return oAuthApplications_Users;
	}

	/**
	 * Removes the o auth applications_ users with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param oaauid the primary key of the o auth applications_ users
	 * @return the o auth applications_ users that was removed
	 * @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a o auth applications_ users with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users remove(long oaauid)
		throws NoSuchApplications_UsersException, SystemException {
		return remove(Long.valueOf(oaauid));
	}

	/**
	 * Removes the o auth applications_ users with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the o auth applications_ users
	 * @return the o auth applications_ users that was removed
	 * @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a o auth applications_ users with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthApplications_Users remove(Serializable primaryKey)
		throws NoSuchApplications_UsersException, SystemException {
		Session session = null;

		try {
			session = openSession();

			OAuthApplications_Users oAuthApplications_Users = (OAuthApplications_Users)session.get(OAuthApplications_UsersImpl.class,
					primaryKey);

			if (oAuthApplications_Users == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchApplications_UsersException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(oAuthApplications_Users);
		}
		catch (NoSuchApplications_UsersException nsee) {
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
	protected OAuthApplications_Users removeImpl(
		OAuthApplications_Users oAuthApplications_Users)
		throws SystemException {
		oAuthApplications_Users = toUnwrappedModel(oAuthApplications_Users);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, oAuthApplications_Users);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(oAuthApplications_Users);

		return oAuthApplications_Users;
	}

	@Override
	public OAuthApplications_Users updateImpl(
		com.liferay.portal.oauth.model.OAuthApplications_Users oAuthApplications_Users,
		boolean merge) throws SystemException {
		oAuthApplications_Users = toUnwrappedModel(oAuthApplications_Users);

		boolean isNew = oAuthApplications_Users.isNew();

		OAuthApplications_UsersModelImpl oAuthApplications_UsersModelImpl = (OAuthApplications_UsersModelImpl)oAuthApplications_Users;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, oAuthApplications_Users, merge);

			oAuthApplications_Users.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !OAuthApplications_UsersModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersImpl.class,
			oAuthApplications_Users.getPrimaryKey(), oAuthApplications_Users);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_U,
				new Object[] {
					Long.valueOf(oAuthApplications_Users.getApplicationId()),
					Long.valueOf(oAuthApplications_Users.getUserId())
				}, oAuthApplications_Users);

			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ACCESSTOKEN,
				new Object[] { oAuthApplications_Users.getAccessToken() },
				oAuthApplications_Users);
		}
		else {
			if ((oAuthApplications_UsersModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_A_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(oAuthApplications_UsersModelImpl.getOriginalApplicationId()),
						Long.valueOf(oAuthApplications_UsersModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_A_U, args);
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_U, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_U,
					new Object[] {
						Long.valueOf(oAuthApplications_Users.getApplicationId()),
						Long.valueOf(oAuthApplications_Users.getUserId())
					}, oAuthApplications_Users);
			}

			if ((oAuthApplications_UsersModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_ACCESSTOKEN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						oAuthApplications_UsersModelImpl.getOriginalAccessToken()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCESSTOKEN,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ACCESSTOKEN,
					args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ACCESSTOKEN,
					new Object[] { oAuthApplications_Users.getAccessToken() },
					oAuthApplications_Users);
			}
		}

		return oAuthApplications_Users;
	}

	protected OAuthApplications_Users toUnwrappedModel(
		OAuthApplications_Users oAuthApplications_Users) {
		if (oAuthApplications_Users instanceof OAuthApplications_UsersImpl) {
			return oAuthApplications_Users;
		}

		OAuthApplications_UsersImpl oAuthApplications_UsersImpl = new OAuthApplications_UsersImpl();

		oAuthApplications_UsersImpl.setNew(oAuthApplications_Users.isNew());
		oAuthApplications_UsersImpl.setPrimaryKey(oAuthApplications_Users.getPrimaryKey());

		oAuthApplications_UsersImpl.setOaauid(oAuthApplications_Users.getOaauid());
		oAuthApplications_UsersImpl.setAccessToken(oAuthApplications_Users.getAccessToken());
		oAuthApplications_UsersImpl.setAccessSecret(oAuthApplications_Users.getAccessSecret());
		oAuthApplications_UsersImpl.setApplicationId(oAuthApplications_Users.getApplicationId());
		oAuthApplications_UsersImpl.setAuthorized(oAuthApplications_Users.isAuthorized());
		oAuthApplications_UsersImpl.setUserId(oAuthApplications_Users.getUserId());

		return oAuthApplications_UsersImpl;
	}

	/**
	 * Returns the o auth applications_ users with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the o auth applications_ users
	 * @return the o auth applications_ users
	 * @throws com.liferay.portal.NoSuchModelException if a o auth applications_ users with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthApplications_Users findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the o auth applications_ users with the primary key or throws a {@link com.liferay.portal.oauth.NoSuchApplications_UsersException} if it could not be found.
	 *
	 * @param oaauid the primary key of the o auth applications_ users
	 * @return the o auth applications_ users
	 * @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a o auth applications_ users with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users findByPrimaryKey(long oaauid)
		throws NoSuchApplications_UsersException, SystemException {
		OAuthApplications_Users oAuthApplications_Users = fetchByPrimaryKey(oaauid);

		if (oAuthApplications_Users == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + oaauid);
			}

			throw new NoSuchApplications_UsersException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				oaauid);
		}

		return oAuthApplications_Users;
	}

	/**
	 * Returns the o auth applications_ users with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the o auth applications_ users
	 * @return the o auth applications_ users, or <code>null</code> if a o auth applications_ users with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthApplications_Users fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the o auth applications_ users with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param oaauid the primary key of the o auth applications_ users
	 * @return the o auth applications_ users, or <code>null</code> if a o auth applications_ users with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users fetchByPrimaryKey(long oaauid)
		throws SystemException {
		OAuthApplications_Users oAuthApplications_Users = (OAuthApplications_Users)EntityCacheUtil.getResult(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
				OAuthApplications_UsersImpl.class, oaauid);

		if (oAuthApplications_Users == _nullOAuthApplications_Users) {
			return null;
		}

		if (oAuthApplications_Users == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				oAuthApplications_Users = (OAuthApplications_Users)session.get(OAuthApplications_UsersImpl.class,
						Long.valueOf(oaauid));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (oAuthApplications_Users != null) {
					cacheResult(oAuthApplications_Users);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
						OAuthApplications_UsersImpl.class, oaauid,
						_nullOAuthApplications_Users);
				}

				closeSession(session);
			}
		}

		return oAuthApplications_Users;
	}

	/**
	 * Returns the o auth applications_ users where applicationId = &#63; and userId = &#63; or throws a {@link com.liferay.portal.oauth.NoSuchApplications_UsersException} if it could not be found.
	 *
	 * @param applicationId the application ID
	 * @param userId the user ID
	 * @return the matching o auth applications_ users
	 * @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a matching o auth applications_ users could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users findByA_U(long applicationId, long userId)
		throws NoSuchApplications_UsersException, SystemException {
		OAuthApplications_Users oAuthApplications_Users = fetchByA_U(applicationId,
				userId);

		if (oAuthApplications_Users == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("applicationId=");
			msg.append(applicationId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchApplications_UsersException(msg.toString());
		}

		return oAuthApplications_Users;
	}

	/**
	 * Returns the o auth applications_ users where applicationId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param applicationId the application ID
	 * @param userId the user ID
	 * @return the matching o auth applications_ users, or <code>null</code> if a matching o auth applications_ users could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users fetchByA_U(long applicationId, long userId)
		throws SystemException {
		return fetchByA_U(applicationId, userId, true);
	}

	/**
	 * Returns the o auth applications_ users where applicationId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param applicationId the application ID
	 * @param userId the user ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching o auth applications_ users, or <code>null</code> if a matching o auth applications_ users could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users fetchByA_U(long applicationId, long userId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { applicationId, userId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_A_U,
					finderArgs, this);
		}

		if (result instanceof OAuthApplications_Users) {
			OAuthApplications_Users oAuthApplications_Users = (OAuthApplications_Users)result;

			if ((applicationId != oAuthApplications_Users.getApplicationId()) ||
					(userId != oAuthApplications_Users.getUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_OAUTHAPPLICATIONS_USERS_WHERE);

			query.append(_FINDER_COLUMN_A_U_APPLICATIONID_2);

			query.append(_FINDER_COLUMN_A_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(applicationId);

				qPos.add(userId);

				List<OAuthApplications_Users> list = q.list();

				result = list;

				OAuthApplications_Users oAuthApplications_Users = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_U,
						finderArgs, list);
				}
				else {
					oAuthApplications_Users = list.get(0);

					cacheResult(oAuthApplications_Users);

					if ((oAuthApplications_Users.getApplicationId() != applicationId) ||
							(oAuthApplications_Users.getUserId() != userId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_U,
							finderArgs, oAuthApplications_Users);
					}
				}

				return oAuthApplications_Users;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_U,
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
				return (OAuthApplications_Users)result;
			}
		}
	}

	/**
	 * Returns the o auth applications_ users where accessToken = &#63; or throws a {@link com.liferay.portal.oauth.NoSuchApplications_UsersException} if it could not be found.
	 *
	 * @param accessToken the access token
	 * @return the matching o auth applications_ users
	 * @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a matching o auth applications_ users could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users findByAccessToken(String accessToken)
		throws NoSuchApplications_UsersException, SystemException {
		OAuthApplications_Users oAuthApplications_Users = fetchByAccessToken(accessToken);

		if (oAuthApplications_Users == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accessToken=");
			msg.append(accessToken);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchApplications_UsersException(msg.toString());
		}

		return oAuthApplications_Users;
	}

	/**
	 * Returns the o auth applications_ users where accessToken = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accessToken the access token
	 * @return the matching o auth applications_ users, or <code>null</code> if a matching o auth applications_ users could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users fetchByAccessToken(String accessToken)
		throws SystemException {
		return fetchByAccessToken(accessToken, true);
	}

	/**
	 * Returns the o auth applications_ users where accessToken = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accessToken the access token
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching o auth applications_ users, or <code>null</code> if a matching o auth applications_ users could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users fetchByAccessToken(String accessToken,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { accessToken };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_ACCESSTOKEN,
					finderArgs, this);
		}

		if (result instanceof OAuthApplications_Users) {
			OAuthApplications_Users oAuthApplications_Users = (OAuthApplications_Users)result;

			if (!Validator.equals(accessToken,
						oAuthApplications_Users.getAccessToken())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_OAUTHAPPLICATIONS_USERS_WHERE);

			if (accessToken == null) {
				query.append(_FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_1);
			}
			else {
				if (accessToken.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_3);
				}
				else {
					query.append(_FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (accessToken != null) {
					qPos.add(accessToken);
				}

				List<OAuthApplications_Users> list = q.list();

				result = list;

				OAuthApplications_Users oAuthApplications_Users = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ACCESSTOKEN,
						finderArgs, list);
				}
				else {
					oAuthApplications_Users = list.get(0);

					cacheResult(oAuthApplications_Users);

					if ((oAuthApplications_Users.getAccessToken() == null) ||
							!oAuthApplications_Users.getAccessToken()
														.equals(accessToken)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ACCESSTOKEN,
							finderArgs, oAuthApplications_Users);
					}
				}

				return oAuthApplications_Users;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ACCESSTOKEN,
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
				return (OAuthApplications_Users)result;
			}
		}
	}

	/**
	 * Returns all the o auth applications_ userses.
	 *
	 * @return the o auth applications_ userses
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the o auth applications_ userses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of o auth applications_ userses
	 * @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	 * @return the range of o auth applications_ userses
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth applications_ userses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of o auth applications_ userses
	 * @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of o auth applications_ userses
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<OAuthApplications_Users> list = (List<OAuthApplications_Users>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_OAUTHAPPLICATIONS_USERS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OAUTHAPPLICATIONS_USERS;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<OAuthApplications_Users>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<OAuthApplications_Users>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes the o auth applications_ users where applicationId = &#63; and userId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 * @param userId the user ID
	 * @return the o auth applications_ users that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users removeByA_U(long applicationId, long userId)
		throws NoSuchApplications_UsersException, SystemException {
		OAuthApplications_Users oAuthApplications_Users = findByA_U(applicationId,
				userId);

		return remove(oAuthApplications_Users);
	}

	/**
	 * Removes the o auth applications_ users where accessToken = &#63; from the database.
	 *
	 * @param accessToken the access token
	 * @return the o auth applications_ users that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users removeByAccessToken(String accessToken)
		throws NoSuchApplications_UsersException, SystemException {
		OAuthApplications_Users oAuthApplications_Users = findByAccessToken(accessToken);

		return remove(oAuthApplications_Users);
	}

	/**
	 * Removes all the o auth applications_ userses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (OAuthApplications_Users oAuthApplications_Users : findAll()) {
			remove(oAuthApplications_Users);
		}
	}

	/**
	 * Returns the number of o auth applications_ userses where applicationId = &#63; and userId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param userId the user ID
	 * @return the number of matching o auth applications_ userses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByA_U(long applicationId, long userId)
		throws SystemException {
		Object[] finderArgs = new Object[] { applicationId, userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_A_U,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OAUTHAPPLICATIONS_USERS_WHERE);

			query.append(_FINDER_COLUMN_A_U_APPLICATIONID_2);

			query.append(_FINDER_COLUMN_A_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(applicationId);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_A_U, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of o auth applications_ userses where accessToken = &#63;.
	 *
	 * @param accessToken the access token
	 * @return the number of matching o auth applications_ userses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAccessToken(String accessToken) throws SystemException {
		Object[] finderArgs = new Object[] { accessToken };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACCESSTOKEN,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OAUTHAPPLICATIONS_USERS_WHERE);

			if (accessToken == null) {
				query.append(_FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_1);
			}
			else {
				if (accessToken.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_3);
				}
				else {
					query.append(_FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (accessToken != null) {
					qPos.add(accessToken);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACCESSTOKEN,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of o auth applications_ userses.
	 *
	 * @return the number of o auth applications_ userses
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OAUTHAPPLICATIONS_USERS);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the o auth applications_ users persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.oauth.model.OAuthApplications_Users")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<OAuthApplications_Users>> listenersList = new ArrayList<ModelListener<OAuthApplications_Users>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<OAuthApplications_Users>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(OAuthApplications_UsersImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = OAuthApplicationPersistence.class)
	protected OAuthApplicationPersistence oAuthApplicationPersistence;
	@BeanReference(type = OAuthApplications_UsersPersistence.class)
	protected OAuthApplications_UsersPersistence oAuthApplications_UsersPersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_OAUTHAPPLICATIONS_USERS = "SELECT oAuthApplications_Users FROM OAuthApplications_Users oAuthApplications_Users";
	private static final String _SQL_SELECT_OAUTHAPPLICATIONS_USERS_WHERE = "SELECT oAuthApplications_Users FROM OAuthApplications_Users oAuthApplications_Users WHERE ";
	private static final String _SQL_COUNT_OAUTHAPPLICATIONS_USERS = "SELECT COUNT(oAuthApplications_Users) FROM OAuthApplications_Users oAuthApplications_Users";
	private static final String _SQL_COUNT_OAUTHAPPLICATIONS_USERS_WHERE = "SELECT COUNT(oAuthApplications_Users) FROM OAuthApplications_Users oAuthApplications_Users WHERE ";
	private static final String _FINDER_COLUMN_A_U_APPLICATIONID_2 = "oAuthApplications_Users.applicationId = ? AND ";
	private static final String _FINDER_COLUMN_A_U_USERID_2 = "oAuthApplications_Users.userId = ?";
	private static final String _FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_1 = "oAuthApplications_Users.accessToken IS NULL";
	private static final String _FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_2 = "oAuthApplications_Users.accessToken = ?";
	private static final String _FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_3 = "(oAuthApplications_Users.accessToken IS NULL OR oAuthApplications_Users.accessToken = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "oAuthApplications_Users.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OAuthApplications_Users exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OAuthApplications_Users exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(OAuthApplications_UsersPersistenceImpl.class);
	private static OAuthApplications_Users _nullOAuthApplications_Users = new OAuthApplications_UsersImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<OAuthApplications_Users> toCacheModel() {
				return _nullOAuthApplications_UsersCacheModel;
			}
		};

	private static CacheModel<OAuthApplications_Users> _nullOAuthApplications_UsersCacheModel =
		new CacheModel<OAuthApplications_Users>() {
			public OAuthApplications_Users toEntityModel() {
				return _nullOAuthApplications_Users;
			}
		};
}