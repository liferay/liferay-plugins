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
import com.liferay.portal.kernel.dao.orm.SQLQuery;
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
import com.liferay.portal.oauth.NoSuchApplicationUserException;
import com.liferay.portal.oauth.model.ApplicationUser;
import com.liferay.portal.oauth.model.impl.ApplicationUserImpl;
import com.liferay.portal.oauth.model.impl.ApplicationUserModelImpl;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the application user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationUserPersistence
 * @see ApplicationUserUtil
 * @generated
 */
public class ApplicationUserPersistenceImpl extends BasePersistenceImpl<ApplicationUser>
	implements ApplicationUserPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ApplicationUserUtil} to access the application user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ApplicationUserImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCESSTOKEN =
		new FinderPath(ApplicationUserModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationUserModelImpl.FINDER_CACHE_ENABLED,
			ApplicationUserImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAccessToken",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCESSTOKEN =
		new FinderPath(ApplicationUserModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationUserModelImpl.FINDER_CACHE_ENABLED,
			ApplicationUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccessToken",
			new String[] { String.class.getName() },
			ApplicationUserModelImpl.ACCESSTOKEN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCESSTOKEN = new FinderPath(ApplicationUserModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccessToken",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPLICATIONID =
		new FinderPath(ApplicationUserModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationUserModelImpl.FINDER_CACHE_ENABLED,
			ApplicationUserImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByApplicationId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONID =
		new FinderPath(ApplicationUserModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationUserModelImpl.FINDER_CACHE_ENABLED,
			ApplicationUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByApplicationId",
			new String[] { Long.class.getName() },
			ApplicationUserModelImpl.APPLICATIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPLICATIONID = new FinderPath(ApplicationUserModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByApplicationId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(ApplicationUserModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationUserModelImpl.FINDER_CACHE_ENABLED,
			ApplicationUserImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(ApplicationUserModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationUserModelImpl.FINDER_CACHE_ENABLED,
			ApplicationUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			ApplicationUserModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(ApplicationUserModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_U_AP = new FinderPath(ApplicationUserModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationUserModelImpl.FINDER_CACHE_ENABLED,
			ApplicationUserImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByU_AP",
			new String[] { Long.class.getName(), Long.class.getName() },
			ApplicationUserModelImpl.USERID_COLUMN_BITMASK |
			ApplicationUserModelImpl.APPLICATIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_AP = new FinderPath(ApplicationUserModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_AP",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ApplicationUserModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationUserModelImpl.FINDER_CACHE_ENABLED,
			ApplicationUserImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ApplicationUserModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationUserModelImpl.FINDER_CACHE_ENABLED,
			ApplicationUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ApplicationUserModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the application user in the entity cache if it is enabled.
	 *
	 * @param applicationUser the application user
	 */
	public void cacheResult(ApplicationUser applicationUser) {
		EntityCacheUtil.putResult(ApplicationUserModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationUserImpl.class, applicationUser.getPrimaryKey(),
			applicationUser);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_AP,
			new Object[] {
				Long.valueOf(applicationUser.getUserId()),
				Long.valueOf(applicationUser.getApplicationId())
			}, applicationUser);

		applicationUser.resetOriginalValues();
	}

	/**
	 * Caches the application users in the entity cache if it is enabled.
	 *
	 * @param applicationUsers the application users
	 */
	public void cacheResult(List<ApplicationUser> applicationUsers) {
		for (ApplicationUser applicationUser : applicationUsers) {
			if (EntityCacheUtil.getResult(
						ApplicationUserModelImpl.ENTITY_CACHE_ENABLED,
						ApplicationUserImpl.class,
						applicationUser.getPrimaryKey()) == null) {
				cacheResult(applicationUser);
			}
			else {
				applicationUser.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all application users.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ApplicationUserImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ApplicationUserImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the application user.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ApplicationUser applicationUser) {
		EntityCacheUtil.removeResult(ApplicationUserModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationUserImpl.class, applicationUser.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(applicationUser);
	}

	@Override
	public void clearCache(List<ApplicationUser> applicationUsers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ApplicationUser applicationUser : applicationUsers) {
			EntityCacheUtil.removeResult(ApplicationUserModelImpl.ENTITY_CACHE_ENABLED,
				ApplicationUserImpl.class, applicationUser.getPrimaryKey());

			clearUniqueFindersCache(applicationUser);
		}
	}

	protected void clearUniqueFindersCache(ApplicationUser applicationUser) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_AP,
			new Object[] {
				Long.valueOf(applicationUser.getUserId()),
				Long.valueOf(applicationUser.getApplicationId())
			});
	}

	/**
	 * Creates a new application user with the primary key. Does not add the application user to the database.
	 *
	 * @param oaauId the primary key for the new application user
	 * @return the new application user
	 */
	public ApplicationUser create(long oaauId) {
		ApplicationUser applicationUser = new ApplicationUserImpl();

		applicationUser.setNew(true);
		applicationUser.setPrimaryKey(oaauId);

		return applicationUser;
	}

	/**
	 * Removes the application user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param oaauId the primary key of the application user
	 * @return the application user that was removed
	 * @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a application user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser remove(long oaauId)
		throws NoSuchApplicationUserException, SystemException {
		return remove(Long.valueOf(oaauId));
	}

	/**
	 * Removes the application user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the application user
	 * @return the application user that was removed
	 * @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a application user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ApplicationUser remove(Serializable primaryKey)
		throws NoSuchApplicationUserException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ApplicationUser applicationUser = (ApplicationUser)session.get(ApplicationUserImpl.class,
					primaryKey);

			if (applicationUser == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchApplicationUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(applicationUser);
		}
		catch (NoSuchApplicationUserException nsee) {
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
	protected ApplicationUser removeImpl(ApplicationUser applicationUser)
		throws SystemException {
		applicationUser = toUnwrappedModel(applicationUser);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, applicationUser);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(applicationUser);

		return applicationUser;
	}

	@Override
	public ApplicationUser updateImpl(
		com.liferay.portal.oauth.model.ApplicationUser applicationUser,
		boolean merge) throws SystemException {
		applicationUser = toUnwrappedModel(applicationUser);

		boolean isNew = applicationUser.isNew();

		ApplicationUserModelImpl applicationUserModelImpl = (ApplicationUserModelImpl)applicationUser;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, applicationUser, merge);

			applicationUser.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ApplicationUserModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((applicationUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCESSTOKEN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						applicationUserModelImpl.getOriginalAccessToken()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCESSTOKEN,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCESSTOKEN,
					args);

				args = new Object[] { applicationUserModelImpl.getAccessToken() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCESSTOKEN,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCESSTOKEN,
					args);
			}

			if ((applicationUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(applicationUserModelImpl.getOriginalApplicationId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPLICATIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONID,
					args);

				args = new Object[] {
						Long.valueOf(applicationUserModelImpl.getApplicationId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPLICATIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONID,
					args);
			}

			if ((applicationUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(applicationUserModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(applicationUserModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}
		}

		EntityCacheUtil.putResult(ApplicationUserModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationUserImpl.class, applicationUser.getPrimaryKey(),
			applicationUser);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_AP,
				new Object[] {
					Long.valueOf(applicationUser.getUserId()),
					Long.valueOf(applicationUser.getApplicationId())
				}, applicationUser);
		}
		else {
			if ((applicationUserModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_U_AP.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(applicationUserModelImpl.getOriginalUserId()),
						Long.valueOf(applicationUserModelImpl.getOriginalApplicationId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_AP, args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_AP, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_AP,
					new Object[] {
						Long.valueOf(applicationUser.getUserId()),
						Long.valueOf(applicationUser.getApplicationId())
					}, applicationUser);
			}
		}

		return applicationUser;
	}

	protected ApplicationUser toUnwrappedModel(ApplicationUser applicationUser) {
		if (applicationUser instanceof ApplicationUserImpl) {
			return applicationUser;
		}

		ApplicationUserImpl applicationUserImpl = new ApplicationUserImpl();

		applicationUserImpl.setNew(applicationUser.isNew());
		applicationUserImpl.setPrimaryKey(applicationUser.getPrimaryKey());

		applicationUserImpl.setOaauId(applicationUser.getOaauId());
		applicationUserImpl.setUserId(applicationUser.getUserId());
		applicationUserImpl.setApplicationId(applicationUser.getApplicationId());
		applicationUserImpl.setAccessToken(applicationUser.getAccessToken());
		applicationUserImpl.setAccessSecret(applicationUser.getAccessSecret());

		return applicationUserImpl;
	}

	/**
	 * Returns the application user with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the application user
	 * @return the application user
	 * @throws com.liferay.portal.NoSuchModelException if a application user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ApplicationUser findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the application user with the primary key or throws a {@link com.liferay.portal.oauth.NoSuchApplicationUserException} if it could not be found.
	 *
	 * @param oaauId the primary key of the application user
	 * @return the application user
	 * @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a application user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser findByPrimaryKey(long oaauId)
		throws NoSuchApplicationUserException, SystemException {
		ApplicationUser applicationUser = fetchByPrimaryKey(oaauId);

		if (applicationUser == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + oaauId);
			}

			throw new NoSuchApplicationUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				oaauId);
		}

		return applicationUser;
	}

	/**
	 * Returns the application user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the application user
	 * @return the application user, or <code>null</code> if a application user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ApplicationUser fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the application user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param oaauId the primary key of the application user
	 * @return the application user, or <code>null</code> if a application user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser fetchByPrimaryKey(long oaauId)
		throws SystemException {
		ApplicationUser applicationUser = (ApplicationUser)EntityCacheUtil.getResult(ApplicationUserModelImpl.ENTITY_CACHE_ENABLED,
				ApplicationUserImpl.class, oaauId);

		if (applicationUser == _nullApplicationUser) {
			return null;
		}

		if (applicationUser == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				applicationUser = (ApplicationUser)session.get(ApplicationUserImpl.class,
						Long.valueOf(oaauId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (applicationUser != null) {
					cacheResult(applicationUser);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(ApplicationUserModelImpl.ENTITY_CACHE_ENABLED,
						ApplicationUserImpl.class, oaauId, _nullApplicationUser);
				}

				closeSession(session);
			}
		}

		return applicationUser;
	}

	/**
	 * Returns all the application users where accessToken = &#63;.
	 *
	 * @param accessToken the access token
	 * @return the matching application users
	 * @throws SystemException if a system exception occurred
	 */
	public List<ApplicationUser> findByAccessToken(String accessToken)
		throws SystemException {
		return findByAccessToken(accessToken, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the application users where accessToken = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accessToken the access token
	 * @param start the lower bound of the range of application users
	 * @param end the upper bound of the range of application users (not inclusive)
	 * @return the range of matching application users
	 * @throws SystemException if a system exception occurred
	 */
	public List<ApplicationUser> findByAccessToken(String accessToken,
		int start, int end) throws SystemException {
		return findByAccessToken(accessToken, start, end, null);
	}

	/**
	 * Returns an ordered range of all the application users where accessToken = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accessToken the access token
	 * @param start the lower bound of the range of application users
	 * @param end the upper bound of the range of application users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching application users
	 * @throws SystemException if a system exception occurred
	 */
	public List<ApplicationUser> findByAccessToken(String accessToken,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCESSTOKEN;
			finderArgs = new Object[] { accessToken };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCESSTOKEN;
			finderArgs = new Object[] { accessToken, start, end, orderByComparator };
		}

		List<ApplicationUser> list = (List<ApplicationUser>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ApplicationUser applicationUser : list) {
				if (!Validator.equals(accessToken,
							applicationUser.getAccessToken())) {
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
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_APPLICATIONUSER_WHERE);

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

				if (accessToken != null) {
					qPos.add(accessToken);
				}

				list = (List<ApplicationUser>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first application user in the ordered set where accessToken = &#63;.
	 *
	 * @param accessToken the access token
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching application user
	 * @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a matching application user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser findByAccessToken_First(String accessToken,
		OrderByComparator orderByComparator)
		throws NoSuchApplicationUserException, SystemException {
		ApplicationUser applicationUser = fetchByAccessToken_First(accessToken,
				orderByComparator);

		if (applicationUser != null) {
			return applicationUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accessToken=");
		msg.append(accessToken);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchApplicationUserException(msg.toString());
	}

	/**
	 * Returns the first application user in the ordered set where accessToken = &#63;.
	 *
	 * @param accessToken the access token
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching application user, or <code>null</code> if a matching application user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser fetchByAccessToken_First(String accessToken,
		OrderByComparator orderByComparator) throws SystemException {
		List<ApplicationUser> list = findByAccessToken(accessToken, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last application user in the ordered set where accessToken = &#63;.
	 *
	 * @param accessToken the access token
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching application user
	 * @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a matching application user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser findByAccessToken_Last(String accessToken,
		OrderByComparator orderByComparator)
		throws NoSuchApplicationUserException, SystemException {
		ApplicationUser applicationUser = fetchByAccessToken_Last(accessToken,
				orderByComparator);

		if (applicationUser != null) {
			return applicationUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accessToken=");
		msg.append(accessToken);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchApplicationUserException(msg.toString());
	}

	/**
	 * Returns the last application user in the ordered set where accessToken = &#63;.
	 *
	 * @param accessToken the access token
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching application user, or <code>null</code> if a matching application user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser fetchByAccessToken_Last(String accessToken,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAccessToken(accessToken);

		List<ApplicationUser> list = findByAccessToken(accessToken, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the application users before and after the current application user in the ordered set where accessToken = &#63;.
	 *
	 * @param oaauId the primary key of the current application user
	 * @param accessToken the access token
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next application user
	 * @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a application user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser[] findByAccessToken_PrevAndNext(long oaauId,
		String accessToken, OrderByComparator orderByComparator)
		throws NoSuchApplicationUserException, SystemException {
		ApplicationUser applicationUser = findByPrimaryKey(oaauId);

		Session session = null;

		try {
			session = openSession();

			ApplicationUser[] array = new ApplicationUserImpl[3];

			array[0] = getByAccessToken_PrevAndNext(session, applicationUser,
					accessToken, orderByComparator, true);

			array[1] = applicationUser;

			array[2] = getByAccessToken_PrevAndNext(session, applicationUser,
					accessToken, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ApplicationUser getByAccessToken_PrevAndNext(Session session,
		ApplicationUser applicationUser, String accessToken,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPLICATIONUSER_WHERE);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (accessToken != null) {
			qPos.add(accessToken);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(applicationUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ApplicationUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the application users that the user has permission to view where accessToken = &#63;.
	 *
	 * @param accessToken the access token
	 * @return the matching application users that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<ApplicationUser> filterFindByAccessToken(String accessToken)
		throws SystemException {
		return filterFindByAccessToken(accessToken, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the application users that the user has permission to view where accessToken = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accessToken the access token
	 * @param start the lower bound of the range of application users
	 * @param end the upper bound of the range of application users (not inclusive)
	 * @return the range of matching application users that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<ApplicationUser> filterFindByAccessToken(String accessToken,
		int start, int end) throws SystemException {
		return filterFindByAccessToken(accessToken, start, end, null);
	}

	/**
	 * Returns an ordered range of all the application users that the user has permissions to view where accessToken = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accessToken the access token
	 * @param start the lower bound of the range of application users
	 * @param end the upper bound of the range of application users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching application users that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<ApplicationUser> filterFindByAccessToken(String accessToken,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByAccessToken(accessToken, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(2);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPLICATIONUSER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPLICATIONUSER_NO_INLINE_DISTINCT_WHERE_1);
		}

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

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPLICATIONUSER_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ApplicationUser.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ApplicationUserImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ApplicationUserImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (accessToken != null) {
				qPos.add(accessToken);
			}

			return (List<ApplicationUser>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the application users before and after the current application user in the ordered set of application users that the user has permission to view where accessToken = &#63;.
	 *
	 * @param oaauId the primary key of the current application user
	 * @param accessToken the access token
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next application user
	 * @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a application user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser[] filterFindByAccessToken_PrevAndNext(long oaauId,
		String accessToken, OrderByComparator orderByComparator)
		throws NoSuchApplicationUserException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByAccessToken_PrevAndNext(oaauId, accessToken,
				orderByComparator);
		}

		ApplicationUser applicationUser = findByPrimaryKey(oaauId);

		Session session = null;

		try {
			session = openSession();

			ApplicationUser[] array = new ApplicationUserImpl[3];

			array[0] = filterGetByAccessToken_PrevAndNext(session,
					applicationUser, accessToken, orderByComparator, true);

			array[1] = applicationUser;

			array[2] = filterGetByAccessToken_PrevAndNext(session,
					applicationUser, accessToken, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ApplicationUser filterGetByAccessToken_PrevAndNext(
		Session session, ApplicationUser applicationUser, String accessToken,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPLICATIONUSER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPLICATIONUSER_NO_INLINE_DISTINCT_WHERE_1);
		}

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

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPLICATIONUSER_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ApplicationUser.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ApplicationUserImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ApplicationUserImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		if (accessToken != null) {
			qPos.add(accessToken);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(applicationUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ApplicationUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the application users where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the matching application users
	 * @throws SystemException if a system exception occurred
	 */
	public List<ApplicationUser> findByApplicationId(long applicationId)
		throws SystemException {
		return findByApplicationId(applicationId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the application users where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of application users
	 * @param end the upper bound of the range of application users (not inclusive)
	 * @return the range of matching application users
	 * @throws SystemException if a system exception occurred
	 */
	public List<ApplicationUser> findByApplicationId(long applicationId,
		int start, int end) throws SystemException {
		return findByApplicationId(applicationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the application users where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of application users
	 * @param end the upper bound of the range of application users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching application users
	 * @throws SystemException if a system exception occurred
	 */
	public List<ApplicationUser> findByApplicationId(long applicationId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONID;
			finderArgs = new Object[] { applicationId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_APPLICATIONID;
			finderArgs = new Object[] {
					applicationId,
					
					start, end, orderByComparator
				};
		}

		List<ApplicationUser> list = (List<ApplicationUser>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ApplicationUser applicationUser : list) {
				if ((applicationId != applicationUser.getApplicationId())) {
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
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_APPLICATIONUSER_WHERE);

			query.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2);

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

				qPos.add(applicationId);

				list = (List<ApplicationUser>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first application user in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching application user
	 * @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a matching application user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser findByApplicationId_First(long applicationId,
		OrderByComparator orderByComparator)
		throws NoSuchApplicationUserException, SystemException {
		ApplicationUser applicationUser = fetchByApplicationId_First(applicationId,
				orderByComparator);

		if (applicationUser != null) {
			return applicationUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("applicationId=");
		msg.append(applicationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchApplicationUserException(msg.toString());
	}

	/**
	 * Returns the first application user in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching application user, or <code>null</code> if a matching application user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser fetchByApplicationId_First(long applicationId,
		OrderByComparator orderByComparator) throws SystemException {
		List<ApplicationUser> list = findByApplicationId(applicationId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last application user in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching application user
	 * @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a matching application user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser findByApplicationId_Last(long applicationId,
		OrderByComparator orderByComparator)
		throws NoSuchApplicationUserException, SystemException {
		ApplicationUser applicationUser = fetchByApplicationId_Last(applicationId,
				orderByComparator);

		if (applicationUser != null) {
			return applicationUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("applicationId=");
		msg.append(applicationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchApplicationUserException(msg.toString());
	}

	/**
	 * Returns the last application user in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching application user, or <code>null</code> if a matching application user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser fetchByApplicationId_Last(long applicationId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByApplicationId(applicationId);

		List<ApplicationUser> list = findByApplicationId(applicationId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the application users before and after the current application user in the ordered set where applicationId = &#63;.
	 *
	 * @param oaauId the primary key of the current application user
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next application user
	 * @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a application user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser[] findByApplicationId_PrevAndNext(long oaauId,
		long applicationId, OrderByComparator orderByComparator)
		throws NoSuchApplicationUserException, SystemException {
		ApplicationUser applicationUser = findByPrimaryKey(oaauId);

		Session session = null;

		try {
			session = openSession();

			ApplicationUser[] array = new ApplicationUserImpl[3];

			array[0] = getByApplicationId_PrevAndNext(session, applicationUser,
					applicationId, orderByComparator, true);

			array[1] = applicationUser;

			array[2] = getByApplicationId_PrevAndNext(session, applicationUser,
					applicationId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ApplicationUser getByApplicationId_PrevAndNext(Session session,
		ApplicationUser applicationUser, long applicationId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPLICATIONUSER_WHERE);

		query.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(applicationId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(applicationUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ApplicationUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the application users that the user has permission to view where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the matching application users that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<ApplicationUser> filterFindByApplicationId(long applicationId)
		throws SystemException {
		return filterFindByApplicationId(applicationId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the application users that the user has permission to view where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of application users
	 * @param end the upper bound of the range of application users (not inclusive)
	 * @return the range of matching application users that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<ApplicationUser> filterFindByApplicationId(long applicationId,
		int start, int end) throws SystemException {
		return filterFindByApplicationId(applicationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the application users that the user has permissions to view where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of application users
	 * @param end the upper bound of the range of application users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching application users that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<ApplicationUser> filterFindByApplicationId(long applicationId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByApplicationId(applicationId, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(2);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPLICATIONUSER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPLICATIONUSER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPLICATIONUSER_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ApplicationUser.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ApplicationUserImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ApplicationUserImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(applicationId);

			return (List<ApplicationUser>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the application users before and after the current application user in the ordered set of application users that the user has permission to view where applicationId = &#63;.
	 *
	 * @param oaauId the primary key of the current application user
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next application user
	 * @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a application user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser[] filterFindByApplicationId_PrevAndNext(
		long oaauId, long applicationId, OrderByComparator orderByComparator)
		throws NoSuchApplicationUserException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByApplicationId_PrevAndNext(oaauId, applicationId,
				orderByComparator);
		}

		ApplicationUser applicationUser = findByPrimaryKey(oaauId);

		Session session = null;

		try {
			session = openSession();

			ApplicationUser[] array = new ApplicationUserImpl[3];

			array[0] = filterGetByApplicationId_PrevAndNext(session,
					applicationUser, applicationId, orderByComparator, true);

			array[1] = applicationUser;

			array[2] = filterGetByApplicationId_PrevAndNext(session,
					applicationUser, applicationId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ApplicationUser filterGetByApplicationId_PrevAndNext(
		Session session, ApplicationUser applicationUser, long applicationId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPLICATIONUSER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPLICATIONUSER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPLICATIONUSER_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ApplicationUser.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ApplicationUserImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ApplicationUserImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(applicationId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(applicationUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ApplicationUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the application users where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching application users
	 * @throws SystemException if a system exception occurred
	 */
	public List<ApplicationUser> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the application users where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of application users
	 * @param end the upper bound of the range of application users (not inclusive)
	 * @return the range of matching application users
	 * @throws SystemException if a system exception occurred
	 */
	public List<ApplicationUser> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the application users where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of application users
	 * @param end the upper bound of the range of application users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching application users
	 * @throws SystemException if a system exception occurred
	 */
	public List<ApplicationUser> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<ApplicationUser> list = (List<ApplicationUser>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ApplicationUser applicationUser : list) {
				if ((userId != applicationUser.getUserId())) {
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
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_APPLICATIONUSER_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

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

				qPos.add(userId);

				list = (List<ApplicationUser>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first application user in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching application user
	 * @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a matching application user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchApplicationUserException, SystemException {
		ApplicationUser applicationUser = fetchByUserId_First(userId,
				orderByComparator);

		if (applicationUser != null) {
			return applicationUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchApplicationUserException(msg.toString());
	}

	/**
	 * Returns the first application user in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching application user, or <code>null</code> if a matching application user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<ApplicationUser> list = findByUserId(userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last application user in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching application user
	 * @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a matching application user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchApplicationUserException, SystemException {
		ApplicationUser applicationUser = fetchByUserId_Last(userId,
				orderByComparator);

		if (applicationUser != null) {
			return applicationUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchApplicationUserException(msg.toString());
	}

	/**
	 * Returns the last application user in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching application user, or <code>null</code> if a matching application user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		List<ApplicationUser> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the application users before and after the current application user in the ordered set where userId = &#63;.
	 *
	 * @param oaauId the primary key of the current application user
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next application user
	 * @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a application user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser[] findByUserId_PrevAndNext(long oaauId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchApplicationUserException, SystemException {
		ApplicationUser applicationUser = findByPrimaryKey(oaauId);

		Session session = null;

		try {
			session = openSession();

			ApplicationUser[] array = new ApplicationUserImpl[3];

			array[0] = getByUserId_PrevAndNext(session, applicationUser,
					userId, orderByComparator, true);

			array[1] = applicationUser;

			array[2] = getByUserId_PrevAndNext(session, applicationUser,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ApplicationUser getByUserId_PrevAndNext(Session session,
		ApplicationUser applicationUser, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPLICATIONUSER_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(applicationUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ApplicationUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the application users that the user has permission to view where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching application users that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<ApplicationUser> filterFindByUserId(long userId)
		throws SystemException {
		return filterFindByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the application users that the user has permission to view where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of application users
	 * @param end the upper bound of the range of application users (not inclusive)
	 * @return the range of matching application users that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<ApplicationUser> filterFindByUserId(long userId, int start,
		int end) throws SystemException {
		return filterFindByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the application users that the user has permissions to view where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of application users
	 * @param end the upper bound of the range of application users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching application users that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<ApplicationUser> filterFindByUserId(long userId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUserId(userId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(2);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPLICATIONUSER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPLICATIONUSER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPLICATIONUSER_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ApplicationUser.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ApplicationUserImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ApplicationUserImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			return (List<ApplicationUser>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the application users before and after the current application user in the ordered set of application users that the user has permission to view where userId = &#63;.
	 *
	 * @param oaauId the primary key of the current application user
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next application user
	 * @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a application user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser[] filterFindByUserId_PrevAndNext(long oaauId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchApplicationUserException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUserId_PrevAndNext(oaauId, userId, orderByComparator);
		}

		ApplicationUser applicationUser = findByPrimaryKey(oaauId);

		Session session = null;

		try {
			session = openSession();

			ApplicationUser[] array = new ApplicationUserImpl[3];

			array[0] = filterGetByUserId_PrevAndNext(session, applicationUser,
					userId, orderByComparator, true);

			array[1] = applicationUser;

			array[2] = filterGetByUserId_PrevAndNext(session, applicationUser,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ApplicationUser filterGetByUserId_PrevAndNext(Session session,
		ApplicationUser applicationUser, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPLICATIONUSER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPLICATIONUSER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPLICATIONUSER_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ApplicationUser.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ApplicationUserImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ApplicationUserImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(applicationUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ApplicationUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the application user where userId = &#63; and applicationId = &#63; or throws a {@link com.liferay.portal.oauth.NoSuchApplicationUserException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param applicationId the application ID
	 * @return the matching application user
	 * @throws com.liferay.portal.oauth.NoSuchApplicationUserException if a matching application user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser findByU_AP(long userId, long applicationId)
		throws NoSuchApplicationUserException, SystemException {
		ApplicationUser applicationUser = fetchByU_AP(userId, applicationId);

		if (applicationUser == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", applicationId=");
			msg.append(applicationId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchApplicationUserException(msg.toString());
		}

		return applicationUser;
	}

	/**
	 * Returns the application user where userId = &#63; and applicationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param applicationId the application ID
	 * @return the matching application user, or <code>null</code> if a matching application user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser fetchByU_AP(long userId, long applicationId)
		throws SystemException {
		return fetchByU_AP(userId, applicationId, true);
	}

	/**
	 * Returns the application user where userId = &#63; and applicationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param applicationId the application ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching application user, or <code>null</code> if a matching application user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser fetchByU_AP(long userId, long applicationId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { userId, applicationId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_U_AP,
					finderArgs, this);
		}

		if (result instanceof ApplicationUser) {
			ApplicationUser applicationUser = (ApplicationUser)result;

			if ((userId != applicationUser.getUserId()) ||
					(applicationId != applicationUser.getApplicationId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_APPLICATIONUSER_WHERE);

			query.append(_FINDER_COLUMN_U_AP_USERID_2);

			query.append(_FINDER_COLUMN_U_AP_APPLICATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(applicationId);

				List<ApplicationUser> list = q.list();

				result = list;

				ApplicationUser applicationUser = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_AP,
						finderArgs, list);
				}
				else {
					applicationUser = list.get(0);

					cacheResult(applicationUser);

					if ((applicationUser.getUserId() != userId) ||
							(applicationUser.getApplicationId() != applicationId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_AP,
							finderArgs, applicationUser);
					}
				}

				return applicationUser;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_AP,
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
				return (ApplicationUser)result;
			}
		}
	}

	/**
	 * Returns all the application users.
	 *
	 * @return the application users
	 * @throws SystemException if a system exception occurred
	 */
	public List<ApplicationUser> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the application users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of application users
	 * @param end the upper bound of the range of application users (not inclusive)
	 * @return the range of application users
	 * @throws SystemException if a system exception occurred
	 */
	public List<ApplicationUser> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the application users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of application users
	 * @param end the upper bound of the range of application users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of application users
	 * @throws SystemException if a system exception occurred
	 */
	public List<ApplicationUser> findAll(int start, int end,
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

		List<ApplicationUser> list = (List<ApplicationUser>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_APPLICATIONUSER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_APPLICATIONUSER;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<ApplicationUser>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<ApplicationUser>)QueryUtil.list(q,
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
	 * Removes all the application users where accessToken = &#63; from the database.
	 *
	 * @param accessToken the access token
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAccessToken(String accessToken)
		throws SystemException {
		for (ApplicationUser applicationUser : findByAccessToken(accessToken)) {
			remove(applicationUser);
		}
	}

	/**
	 * Removes all the application users where applicationId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByApplicationId(long applicationId)
		throws SystemException {
		for (ApplicationUser applicationUser : findByApplicationId(
				applicationId)) {
			remove(applicationUser);
		}
	}

	/**
	 * Removes all the application users where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (ApplicationUser applicationUser : findByUserId(userId)) {
			remove(applicationUser);
		}
	}

	/**
	 * Removes the application user where userId = &#63; and applicationId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param applicationId the application ID
	 * @return the application user that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public ApplicationUser removeByU_AP(long userId, long applicationId)
		throws NoSuchApplicationUserException, SystemException {
		ApplicationUser applicationUser = findByU_AP(userId, applicationId);

		return remove(applicationUser);
	}

	/**
	 * Removes all the application users from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (ApplicationUser applicationUser : findAll()) {
			remove(applicationUser);
		}
	}

	/**
	 * Returns the number of application users where accessToken = &#63;.
	 *
	 * @param accessToken the access token
	 * @return the number of matching application users
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAccessToken(String accessToken) throws SystemException {
		Object[] finderArgs = new Object[] { accessToken };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACCESSTOKEN,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPLICATIONUSER_WHERE);

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
	 * Returns the number of application users that the user has permission to view where accessToken = &#63;.
	 *
	 * @param accessToken the access token
	 * @return the number of matching application users that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByAccessToken(String accessToken)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByAccessToken(accessToken);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_APPLICATIONUSER_WHERE);

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

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ApplicationUser.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (accessToken != null) {
				qPos.add(accessToken);
			}

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of application users where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the number of matching application users
	 * @throws SystemException if a system exception occurred
	 */
	public int countByApplicationId(long applicationId)
		throws SystemException {
		Object[] finderArgs = new Object[] { applicationId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_APPLICATIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPLICATIONUSER_WHERE);

			query.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(applicationId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_APPLICATIONID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of application users that the user has permission to view where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the number of matching application users that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByApplicationId(long applicationId)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByApplicationId(applicationId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_APPLICATIONUSER_WHERE);

		query.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ApplicationUser.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(applicationId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of application users where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching application users
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPLICATIONUSER_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of application users that the user has permission to view where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching application users that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByUserId(long userId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByUserId(userId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_APPLICATIONUSER_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				ApplicationUser.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of application users where userId = &#63; and applicationId = &#63;.
	 *
	 * @param userId the user ID
	 * @param applicationId the application ID
	 * @return the number of matching application users
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_AP(long userId, long applicationId)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, applicationId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_AP,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APPLICATIONUSER_WHERE);

			query.append(_FINDER_COLUMN_U_AP_USERID_2);

			query.append(_FINDER_COLUMN_U_AP_APPLICATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(applicationId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_AP,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of application users.
	 *
	 * @return the number of application users
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_APPLICATIONUSER);

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
	 * Initializes the application user persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.oauth.model.ApplicationUser")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ApplicationUser>> listenersList = new ArrayList<ModelListener<ApplicationUser>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ApplicationUser>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ApplicationUserImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = ApplicationPersistence.class)
	protected ApplicationPersistence applicationPersistence;
	@BeanReference(type = ApplicationUserPersistence.class)
	protected ApplicationUserPersistence applicationUserPersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_APPLICATIONUSER = "SELECT applicationUser FROM ApplicationUser applicationUser";
	private static final String _SQL_SELECT_APPLICATIONUSER_WHERE = "SELECT applicationUser FROM ApplicationUser applicationUser WHERE ";
	private static final String _SQL_COUNT_APPLICATIONUSER = "SELECT COUNT(applicationUser) FROM ApplicationUser applicationUser";
	private static final String _SQL_COUNT_APPLICATIONUSER_WHERE = "SELECT COUNT(applicationUser) FROM ApplicationUser applicationUser WHERE ";
	private static final String _FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_1 = "applicationUser.accessToken IS NULL";
	private static final String _FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_2 = "applicationUser.accessToken = ?";
	private static final String _FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_3 = "(applicationUser.accessToken IS NULL OR applicationUser.accessToken = ?)";
	private static final String _FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2 = "applicationUser.applicationId = ?";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "applicationUser.userId = ?";
	private static final String _FINDER_COLUMN_U_AP_USERID_2 = "applicationUser.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_AP_APPLICATIONID_2 = "applicationUser.applicationId = ?";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "applicationUser.oaauId";
	private static final String _FILTER_SQL_SELECT_APPLICATIONUSER_WHERE = "SELECT DISTINCT {applicationUser.*} FROM OAuth_ApplicationUser applicationUser WHERE ";
	private static final String _FILTER_SQL_SELECT_APPLICATIONUSER_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {OAuth_ApplicationUser.*} FROM (SELECT DISTINCT applicationUser.oaauId FROM OAuth_ApplicationUser applicationUser WHERE ";
	private static final String _FILTER_SQL_SELECT_APPLICATIONUSER_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN OAuth_ApplicationUser ON TEMP_TABLE.oaauId = OAuth_ApplicationUser.oaauId";
	private static final String _FILTER_SQL_COUNT_APPLICATIONUSER_WHERE = "SELECT COUNT(DISTINCT applicationUser.oaauId) AS COUNT_VALUE FROM OAuth_ApplicationUser applicationUser WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "applicationUser";
	private static final String _FILTER_ENTITY_TABLE = "OAuth_ApplicationUser";
	private static final String _ORDER_BY_ENTITY_ALIAS = "applicationUser.";
	private static final String _ORDER_BY_ENTITY_TABLE = "OAuth_ApplicationUser.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ApplicationUser exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ApplicationUser exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ApplicationUserPersistenceImpl.class);
	private static ApplicationUser _nullApplicationUser = new ApplicationUserImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ApplicationUser> toCacheModel() {
				return _nullApplicationUserCacheModel;
			}
		};

	private static CacheModel<ApplicationUser> _nullApplicationUserCacheModel = new CacheModel<ApplicationUser>() {
			public ApplicationUser toEntityModel() {
				return _nullApplicationUser;
			}
		};
}