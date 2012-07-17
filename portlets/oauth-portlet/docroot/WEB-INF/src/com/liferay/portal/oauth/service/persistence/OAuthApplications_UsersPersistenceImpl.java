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
import com.liferay.portal.oauth.NoSuchApplications_UsersException;
import com.liferay.portal.oauth.model.OAuthApplications_Users;
import com.liferay.portal.oauth.model.impl.OAuthApplications_UsersImpl;
import com.liferay.portal.oauth.model.impl.OAuthApplications_UsersModelImpl;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
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
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_A_A = new FinderPath(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersModelImpl.FINDER_CACHE_ENABLED,
			OAuthApplications_UsersImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByA_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_A = new FinderPath(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersModelImpl.FINDER_CACHE_ENABLED,
			OAuthApplications_UsersImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByA_A",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			OAuthApplications_UsersModelImpl.APPLICATIONID_COLUMN_BITMASK |
			OAuthApplications_UsersModelImpl.AUTHORIZED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_A_A = new FinderPath(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByA_A",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCESSTOKEN =
		new FinderPath(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersModelImpl.FINDER_CACHE_ENABLED,
			OAuthApplications_UsersImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccessToken",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCESSTOKEN =
		new FinderPath(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersModelImpl.FINDER_CACHE_ENABLED,
			OAuthApplications_UsersImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccessToken",
			new String[] { String.class.getName() },
			OAuthApplications_UsersModelImpl.ACCESSTOKEN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCESSTOKEN = new FinderPath(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccessToken",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPLICATIONID =
		new FinderPath(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersModelImpl.FINDER_CACHE_ENABLED,
			OAuthApplications_UsersImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByApplicationId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONID =
		new FinderPath(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersModelImpl.FINDER_CACHE_ENABLED,
			OAuthApplications_UsersImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByApplicationId",
			new String[] { Long.class.getName() },
			OAuthApplications_UsersModelImpl.APPLICATIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPLICATIONID = new FinderPath(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByApplicationId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersModelImpl.FINDER_CACHE_ENABLED,
			OAuthApplications_UsersImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersModelImpl.FINDER_CACHE_ENABLED,
			OAuthApplications_UsersImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			OAuthApplications_UsersModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(OAuthApplications_UsersModelImpl.ENTITY_CACHE_ENABLED,
			OAuthApplications_UsersModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
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

		else {
			if ((oAuthApplications_UsersModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(oAuthApplications_UsersModelImpl.getOriginalApplicationId()),
						Boolean.valueOf(oAuthApplications_UsersModelImpl.getOriginalAuthorized())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_A_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_A,
					args);

				args = new Object[] {
						Long.valueOf(oAuthApplications_UsersModelImpl.getApplicationId()),
						Boolean.valueOf(oAuthApplications_UsersModelImpl.getAuthorized())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_A_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_A,
					args);
			}

			if ((oAuthApplications_UsersModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCESSTOKEN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						oAuthApplications_UsersModelImpl.getOriginalAccessToken()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCESSTOKEN,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCESSTOKEN,
					args);

				args = new Object[] {
						oAuthApplications_UsersModelImpl.getAccessToken()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCESSTOKEN,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCESSTOKEN,
					args);
			}

			if ((oAuthApplications_UsersModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(oAuthApplications_UsersModelImpl.getOriginalApplicationId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPLICATIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONID,
					args);

				args = new Object[] {
						Long.valueOf(oAuthApplications_UsersModelImpl.getApplicationId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPLICATIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPLICATIONID,
					args);
			}

			if ((oAuthApplications_UsersModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(oAuthApplications_UsersModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(oAuthApplications_UsersModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}
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
	 * Returns all the o auth applications_ userses where applicationId = &#63; and authorized = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param authorized the authorized
	 * @return the matching o auth applications_ userses
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> findByA_A(long applicationId,
		boolean authorized) throws SystemException {
		return findByA_A(applicationId, authorized, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the o auth applications_ userses where applicationId = &#63; and authorized = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param authorized the authorized
	 * @param start the lower bound of the range of o auth applications_ userses
	 * @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	 * @return the range of matching o auth applications_ userses
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> findByA_A(long applicationId,
		boolean authorized, int start, int end) throws SystemException {
		return findByA_A(applicationId, authorized, start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth applications_ userses where applicationId = &#63; and authorized = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param authorized the authorized
	 * @param start the lower bound of the range of o auth applications_ userses
	 * @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching o auth applications_ userses
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> findByA_A(long applicationId,
		boolean authorized, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_A;
			finderArgs = new Object[] { applicationId, authorized };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_A_A;
			finderArgs = new Object[] {
					applicationId, authorized,
					
					start, end, orderByComparator
				};
		}

		List<OAuthApplications_Users> list = (List<OAuthApplications_Users>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OAuthApplications_Users oAuthApplications_Users : list) {
				if ((applicationId != oAuthApplications_Users.getApplicationId()) ||
						(authorized != oAuthApplications_Users.getAuthorized())) {
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
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_OAUTHAPPLICATIONS_USERS_WHERE);

			query.append(_FINDER_COLUMN_A_A_APPLICATIONID_2);

			query.append(_FINDER_COLUMN_A_A_AUTHORIZED_2);

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

				qPos.add(authorized);

				list = (List<OAuthApplications_Users>)QueryUtil.list(q,
						getDialect(), start, end);
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
	 * Returns the first o auth applications_ users in the ordered set where applicationId = &#63; and authorized = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param authorized the authorized
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching o auth applications_ users
	 * @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a matching o auth applications_ users could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users findByA_A_First(long applicationId,
		boolean authorized, OrderByComparator orderByComparator)
		throws NoSuchApplications_UsersException, SystemException {
		List<OAuthApplications_Users> list = findByA_A(applicationId,
				authorized, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("applicationId=");
			msg.append(applicationId);

			msg.append(", authorized=");
			msg.append(authorized);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchApplications_UsersException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last o auth applications_ users in the ordered set where applicationId = &#63; and authorized = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param authorized the authorized
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching o auth applications_ users
	 * @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a matching o auth applications_ users could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users findByA_A_Last(long applicationId,
		boolean authorized, OrderByComparator orderByComparator)
		throws NoSuchApplications_UsersException, SystemException {
		int count = countByA_A(applicationId, authorized);

		List<OAuthApplications_Users> list = findByA_A(applicationId,
				authorized, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("applicationId=");
			msg.append(applicationId);

			msg.append(", authorized=");
			msg.append(authorized);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchApplications_UsersException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the o auth applications_ userses before and after the current o auth applications_ users in the ordered set where applicationId = &#63; and authorized = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param oaauid the primary key of the current o auth applications_ users
	 * @param applicationId the application ID
	 * @param authorized the authorized
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next o auth applications_ users
	 * @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a o auth applications_ users with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users[] findByA_A_PrevAndNext(long oaauid,
		long applicationId, boolean authorized,
		OrderByComparator orderByComparator)
		throws NoSuchApplications_UsersException, SystemException {
		OAuthApplications_Users oAuthApplications_Users = findByPrimaryKey(oaauid);

		Session session = null;

		try {
			session = openSession();

			OAuthApplications_Users[] array = new OAuthApplications_UsersImpl[3];

			array[0] = getByA_A_PrevAndNext(session, oAuthApplications_Users,
					applicationId, authorized, orderByComparator, true);

			array[1] = oAuthApplications_Users;

			array[2] = getByA_A_PrevAndNext(session, oAuthApplications_Users,
					applicationId, authorized, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OAuthApplications_Users getByA_A_PrevAndNext(Session session,
		OAuthApplications_Users oAuthApplications_Users, long applicationId,
		boolean authorized, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OAUTHAPPLICATIONS_USERS_WHERE);

		query.append(_FINDER_COLUMN_A_A_APPLICATIONID_2);

		query.append(_FINDER_COLUMN_A_A_AUTHORIZED_2);

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

		qPos.add(authorized);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(oAuthApplications_Users);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OAuthApplications_Users> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the o auth applications_ userses that the user has permission to view where applicationId = &#63; and authorized = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param authorized the authorized
	 * @return the matching o auth applications_ userses that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> filterFindByA_A(long applicationId,
		boolean authorized) throws SystemException {
		return filterFindByA_A(applicationId, authorized, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the o auth applications_ userses that the user has permission to view where applicationId = &#63; and authorized = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param authorized the authorized
	 * @param start the lower bound of the range of o auth applications_ userses
	 * @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	 * @return the range of matching o auth applications_ userses that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> filterFindByA_A(long applicationId,
		boolean authorized, int start, int end) throws SystemException {
		return filterFindByA_A(applicationId, authorized, start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth applications_ userses that the user has permissions to view where applicationId = &#63; and authorized = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param authorized the authorized
	 * @param start the lower bound of the range of o auth applications_ userses
	 * @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching o auth applications_ userses that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> filterFindByA_A(long applicationId,
		boolean authorized, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByA_A(applicationId, authorized, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_A_A_APPLICATIONID_2);

		query.append(_FINDER_COLUMN_A_A_AUTHORIZED_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_NO_INLINE_DISTINCT_WHERE_2);
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
				OAuthApplications_Users.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS,
					OAuthApplications_UsersImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE,
					OAuthApplications_UsersImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(applicationId);

			qPos.add(authorized);

			return (List<OAuthApplications_Users>)QueryUtil.list(q,
				getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the o auth applications_ userses before and after the current o auth applications_ users in the ordered set of o auth applications_ userses that the user has permission to view where applicationId = &#63; and authorized = &#63;.
	 *
	 * @param oaauid the primary key of the current o auth applications_ users
	 * @param applicationId the application ID
	 * @param authorized the authorized
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next o auth applications_ users
	 * @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a o auth applications_ users with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users[] filterFindByA_A_PrevAndNext(long oaauid,
		long applicationId, boolean authorized,
		OrderByComparator orderByComparator)
		throws NoSuchApplications_UsersException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByA_A_PrevAndNext(oaauid, applicationId, authorized,
				orderByComparator);
		}

		OAuthApplications_Users oAuthApplications_Users = findByPrimaryKey(oaauid);

		Session session = null;

		try {
			session = openSession();

			OAuthApplications_Users[] array = new OAuthApplications_UsersImpl[3];

			array[0] = filterGetByA_A_PrevAndNext(session,
					oAuthApplications_Users, applicationId, authorized,
					orderByComparator, true);

			array[1] = oAuthApplications_Users;

			array[2] = filterGetByA_A_PrevAndNext(session,
					oAuthApplications_Users, applicationId, authorized,
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

	protected OAuthApplications_Users filterGetByA_A_PrevAndNext(
		Session session, OAuthApplications_Users oAuthApplications_Users,
		long applicationId, boolean authorized,
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
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_A_A_APPLICATIONID_2);

		query.append(_FINDER_COLUMN_A_A_AUTHORIZED_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_NO_INLINE_DISTINCT_WHERE_2);
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
				OAuthApplications_Users.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, OAuthApplications_UsersImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, OAuthApplications_UsersImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(applicationId);

		qPos.add(authorized);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(oAuthApplications_Users);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OAuthApplications_Users> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the o auth applications_ userses where accessToken = &#63;.
	 *
	 * @param accessToken the access token
	 * @return the matching o auth applications_ userses
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> findByAccessToken(String accessToken)
		throws SystemException {
		return findByAccessToken(accessToken, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the o auth applications_ userses where accessToken = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accessToken the access token
	 * @param start the lower bound of the range of o auth applications_ userses
	 * @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	 * @return the range of matching o auth applications_ userses
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> findByAccessToken(String accessToken,
		int start, int end) throws SystemException {
		return findByAccessToken(accessToken, start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth applications_ userses where accessToken = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accessToken the access token
	 * @param start the lower bound of the range of o auth applications_ userses
	 * @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching o auth applications_ userses
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> findByAccessToken(String accessToken,
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

		List<OAuthApplications_Users> list = (List<OAuthApplications_Users>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OAuthApplications_Users oAuthApplications_Users : list) {
				if (!Validator.equals(accessToken,
							oAuthApplications_Users.getAccessToken())) {
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

				list = (List<OAuthApplications_Users>)QueryUtil.list(q,
						getDialect(), start, end);
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
	 * Returns the first o auth applications_ users in the ordered set where accessToken = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accessToken the access token
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching o auth applications_ users
	 * @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a matching o auth applications_ users could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users findByAccessToken_First(String accessToken,
		OrderByComparator orderByComparator)
		throws NoSuchApplications_UsersException, SystemException {
		List<OAuthApplications_Users> list = findByAccessToken(accessToken, 0,
				1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accessToken=");
			msg.append(accessToken);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchApplications_UsersException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last o auth applications_ users in the ordered set where accessToken = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accessToken the access token
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching o auth applications_ users
	 * @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a matching o auth applications_ users could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users findByAccessToken_Last(String accessToken,
		OrderByComparator orderByComparator)
		throws NoSuchApplications_UsersException, SystemException {
		int count = countByAccessToken(accessToken);

		List<OAuthApplications_Users> list = findByAccessToken(accessToken,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accessToken=");
			msg.append(accessToken);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchApplications_UsersException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the o auth applications_ userses before and after the current o auth applications_ users in the ordered set where accessToken = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param oaauid the primary key of the current o auth applications_ users
	 * @param accessToken the access token
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next o auth applications_ users
	 * @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a o auth applications_ users with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users[] findByAccessToken_PrevAndNext(
		long oaauid, String accessToken, OrderByComparator orderByComparator)
		throws NoSuchApplications_UsersException, SystemException {
		OAuthApplications_Users oAuthApplications_Users = findByPrimaryKey(oaauid);

		Session session = null;

		try {
			session = openSession();

			OAuthApplications_Users[] array = new OAuthApplications_UsersImpl[3];

			array[0] = getByAccessToken_PrevAndNext(session,
					oAuthApplications_Users, accessToken, orderByComparator,
					true);

			array[1] = oAuthApplications_Users;

			array[2] = getByAccessToken_PrevAndNext(session,
					oAuthApplications_Users, accessToken, orderByComparator,
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

	protected OAuthApplications_Users getByAccessToken_PrevAndNext(
		Session session, OAuthApplications_Users oAuthApplications_Users,
		String accessToken, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

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
			Object[] values = orderByComparator.getOrderByConditionValues(oAuthApplications_Users);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OAuthApplications_Users> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the o auth applications_ userses that the user has permission to view where accessToken = &#63;.
	 *
	 * @param accessToken the access token
	 * @return the matching o auth applications_ userses that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> filterFindByAccessToken(
		String accessToken) throws SystemException {
		return filterFindByAccessToken(accessToken, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the o auth applications_ userses that the user has permission to view where accessToken = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accessToken the access token
	 * @param start the lower bound of the range of o auth applications_ userses
	 * @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	 * @return the range of matching o auth applications_ userses that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> filterFindByAccessToken(
		String accessToken, int start, int end) throws SystemException {
		return filterFindByAccessToken(accessToken, start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth applications_ userses that the user has permissions to view where accessToken = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accessToken the access token
	 * @param start the lower bound of the range of o auth applications_ userses
	 * @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching o auth applications_ userses that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> filterFindByAccessToken(
		String accessToken, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_NO_INLINE_DISTINCT_WHERE_1);
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
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_NO_INLINE_DISTINCT_WHERE_2);
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
				OAuthApplications_Users.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS,
					OAuthApplications_UsersImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE,
					OAuthApplications_UsersImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (accessToken != null) {
				qPos.add(accessToken);
			}

			return (List<OAuthApplications_Users>)QueryUtil.list(q,
				getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the o auth applications_ userses before and after the current o auth applications_ users in the ordered set of o auth applications_ userses that the user has permission to view where accessToken = &#63;.
	 *
	 * @param oaauid the primary key of the current o auth applications_ users
	 * @param accessToken the access token
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next o auth applications_ users
	 * @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a o auth applications_ users with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users[] filterFindByAccessToken_PrevAndNext(
		long oaauid, String accessToken, OrderByComparator orderByComparator)
		throws NoSuchApplications_UsersException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByAccessToken_PrevAndNext(oaauid, accessToken,
				orderByComparator);
		}

		OAuthApplications_Users oAuthApplications_Users = findByPrimaryKey(oaauid);

		Session session = null;

		try {
			session = openSession();

			OAuthApplications_Users[] array = new OAuthApplications_UsersImpl[3];

			array[0] = filterGetByAccessToken_PrevAndNext(session,
					oAuthApplications_Users, accessToken, orderByComparator,
					true);

			array[1] = oAuthApplications_Users;

			array[2] = filterGetByAccessToken_PrevAndNext(session,
					oAuthApplications_Users, accessToken, orderByComparator,
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

	protected OAuthApplications_Users filterGetByAccessToken_PrevAndNext(
		Session session, OAuthApplications_Users oAuthApplications_Users,
		String accessToken, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_NO_INLINE_DISTINCT_WHERE_1);
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
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_NO_INLINE_DISTINCT_WHERE_2);
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
				OAuthApplications_Users.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, OAuthApplications_UsersImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, OAuthApplications_UsersImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		if (accessToken != null) {
			qPos.add(accessToken);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(oAuthApplications_Users);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OAuthApplications_Users> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the o auth applications_ userses where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the matching o auth applications_ userses
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> findByApplicationId(long applicationId)
		throws SystemException {
		return findByApplicationId(applicationId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the o auth applications_ userses where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of o auth applications_ userses
	 * @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	 * @return the range of matching o auth applications_ userses
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> findByApplicationId(
		long applicationId, int start, int end) throws SystemException {
		return findByApplicationId(applicationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth applications_ userses where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of o auth applications_ userses
	 * @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching o auth applications_ userses
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> findByApplicationId(
		long applicationId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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

		List<OAuthApplications_Users> list = (List<OAuthApplications_Users>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OAuthApplications_Users oAuthApplications_Users : list) {
				if ((applicationId != oAuthApplications_Users.getApplicationId())) {
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

			query.append(_SQL_SELECT_OAUTHAPPLICATIONS_USERS_WHERE);

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

				list = (List<OAuthApplications_Users>)QueryUtil.list(q,
						getDialect(), start, end);
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
	 * Returns the first o auth applications_ users in the ordered set where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching o auth applications_ users
	 * @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a matching o auth applications_ users could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users findByApplicationId_First(
		long applicationId, OrderByComparator orderByComparator)
		throws NoSuchApplications_UsersException, SystemException {
		List<OAuthApplications_Users> list = findByApplicationId(applicationId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("applicationId=");
			msg.append(applicationId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchApplications_UsersException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last o auth applications_ users in the ordered set where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching o auth applications_ users
	 * @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a matching o auth applications_ users could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users findByApplicationId_Last(
		long applicationId, OrderByComparator orderByComparator)
		throws NoSuchApplications_UsersException, SystemException {
		int count = countByApplicationId(applicationId);

		List<OAuthApplications_Users> list = findByApplicationId(applicationId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("applicationId=");
			msg.append(applicationId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchApplications_UsersException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the o auth applications_ userses before and after the current o auth applications_ users in the ordered set where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param oaauid the primary key of the current o auth applications_ users
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next o auth applications_ users
	 * @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a o auth applications_ users with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users[] findByApplicationId_PrevAndNext(
		long oaauid, long applicationId, OrderByComparator orderByComparator)
		throws NoSuchApplications_UsersException, SystemException {
		OAuthApplications_Users oAuthApplications_Users = findByPrimaryKey(oaauid);

		Session session = null;

		try {
			session = openSession();

			OAuthApplications_Users[] array = new OAuthApplications_UsersImpl[3];

			array[0] = getByApplicationId_PrevAndNext(session,
					oAuthApplications_Users, applicationId, orderByComparator,
					true);

			array[1] = oAuthApplications_Users;

			array[2] = getByApplicationId_PrevAndNext(session,
					oAuthApplications_Users, applicationId, orderByComparator,
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

	protected OAuthApplications_Users getByApplicationId_PrevAndNext(
		Session session, OAuthApplications_Users oAuthApplications_Users,
		long applicationId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OAUTHAPPLICATIONS_USERS_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(oAuthApplications_Users);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OAuthApplications_Users> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the o auth applications_ userses that the user has permission to view where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the matching o auth applications_ userses that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> filterFindByApplicationId(
		long applicationId) throws SystemException {
		return filterFindByApplicationId(applicationId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the o auth applications_ userses that the user has permission to view where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of o auth applications_ userses
	 * @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	 * @return the range of matching o auth applications_ userses that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> filterFindByApplicationId(
		long applicationId, int start, int end) throws SystemException {
		return filterFindByApplicationId(applicationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth applications_ userses that the user has permissions to view where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of o auth applications_ userses
	 * @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching o auth applications_ userses that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> filterFindByApplicationId(
		long applicationId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_NO_INLINE_DISTINCT_WHERE_2);
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
				OAuthApplications_Users.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS,
					OAuthApplications_UsersImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE,
					OAuthApplications_UsersImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(applicationId);

			return (List<OAuthApplications_Users>)QueryUtil.list(q,
				getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the o auth applications_ userses before and after the current o auth applications_ users in the ordered set of o auth applications_ userses that the user has permission to view where applicationId = &#63;.
	 *
	 * @param oaauid the primary key of the current o auth applications_ users
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next o auth applications_ users
	 * @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a o auth applications_ users with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users[] filterFindByApplicationId_PrevAndNext(
		long oaauid, long applicationId, OrderByComparator orderByComparator)
		throws NoSuchApplications_UsersException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByApplicationId_PrevAndNext(oaauid, applicationId,
				orderByComparator);
		}

		OAuthApplications_Users oAuthApplications_Users = findByPrimaryKey(oaauid);

		Session session = null;

		try {
			session = openSession();

			OAuthApplications_Users[] array = new OAuthApplications_UsersImpl[3];

			array[0] = filterGetByApplicationId_PrevAndNext(session,
					oAuthApplications_Users, applicationId, orderByComparator,
					true);

			array[1] = oAuthApplications_Users;

			array[2] = filterGetByApplicationId_PrevAndNext(session,
					oAuthApplications_Users, applicationId, orderByComparator,
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

	protected OAuthApplications_Users filterGetByApplicationId_PrevAndNext(
		Session session, OAuthApplications_Users oAuthApplications_Users,
		long applicationId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_NO_INLINE_DISTINCT_WHERE_2);
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
				OAuthApplications_Users.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, OAuthApplications_UsersImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, OAuthApplications_UsersImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(applicationId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(oAuthApplications_Users);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OAuthApplications_Users> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the o auth applications_ userses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching o auth applications_ userses
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the o auth applications_ userses where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of o auth applications_ userses
	 * @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	 * @return the range of matching o auth applications_ userses
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> findByUserId(long userId, int start,
		int end) throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth applications_ userses where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of o auth applications_ userses
	 * @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching o auth applications_ userses
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> findByUserId(long userId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
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

		List<OAuthApplications_Users> list = (List<OAuthApplications_Users>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OAuthApplications_Users oAuthApplications_Users : list) {
				if ((userId != oAuthApplications_Users.getUserId())) {
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

			query.append(_SQL_SELECT_OAUTHAPPLICATIONS_USERS_WHERE);

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

				list = (List<OAuthApplications_Users>)QueryUtil.list(q,
						getDialect(), start, end);
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
	 * Returns the first o auth applications_ users in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching o auth applications_ users
	 * @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a matching o auth applications_ users could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchApplications_UsersException, SystemException {
		List<OAuthApplications_Users> list = findByUserId(userId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchApplications_UsersException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last o auth applications_ users in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching o auth applications_ users
	 * @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a matching o auth applications_ users could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchApplications_UsersException, SystemException {
		int count = countByUserId(userId);

		List<OAuthApplications_Users> list = findByUserId(userId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchApplications_UsersException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the o auth applications_ userses before and after the current o auth applications_ users in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param oaauid the primary key of the current o auth applications_ users
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next o auth applications_ users
	 * @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a o auth applications_ users with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users[] findByUserId_PrevAndNext(long oaauid,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchApplications_UsersException, SystemException {
		OAuthApplications_Users oAuthApplications_Users = findByPrimaryKey(oaauid);

		Session session = null;

		try {
			session = openSession();

			OAuthApplications_Users[] array = new OAuthApplications_UsersImpl[3];

			array[0] = getByUserId_PrevAndNext(session,
					oAuthApplications_Users, userId, orderByComparator, true);

			array[1] = oAuthApplications_Users;

			array[2] = getByUserId_PrevAndNext(session,
					oAuthApplications_Users, userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OAuthApplications_Users getByUserId_PrevAndNext(Session session,
		OAuthApplications_Users oAuthApplications_Users, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OAUTHAPPLICATIONS_USERS_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(oAuthApplications_Users);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OAuthApplications_Users> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the o auth applications_ userses that the user has permission to view where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching o auth applications_ userses that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> filterFindByUserId(long userId)
		throws SystemException {
		return filterFindByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the o auth applications_ userses that the user has permission to view where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of o auth applications_ userses
	 * @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	 * @return the range of matching o auth applications_ userses that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> filterFindByUserId(long userId,
		int start, int end) throws SystemException {
		return filterFindByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth applications_ userses that the user has permissions to view where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of o auth applications_ userses
	 * @param end the upper bound of the range of o auth applications_ userses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching o auth applications_ userses that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthApplications_Users> filterFindByUserId(long userId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
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
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_NO_INLINE_DISTINCT_WHERE_2);
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
				OAuthApplications_Users.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS,
					OAuthApplications_UsersImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE,
					OAuthApplications_UsersImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			return (List<OAuthApplications_Users>)QueryUtil.list(q,
				getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the o auth applications_ userses before and after the current o auth applications_ users in the ordered set of o auth applications_ userses that the user has permission to view where userId = &#63;.
	 *
	 * @param oaauid the primary key of the current o auth applications_ users
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next o auth applications_ users
	 * @throws com.liferay.portal.oauth.NoSuchApplications_UsersException if a o auth applications_ users with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthApplications_Users[] filterFindByUserId_PrevAndNext(
		long oaauid, long userId, OrderByComparator orderByComparator)
		throws NoSuchApplications_UsersException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUserId_PrevAndNext(oaauid, userId, orderByComparator);
		}

		OAuthApplications_Users oAuthApplications_Users = findByPrimaryKey(oaauid);

		Session session = null;

		try {
			session = openSession();

			OAuthApplications_Users[] array = new OAuthApplications_UsersImpl[3];

			array[0] = filterGetByUserId_PrevAndNext(session,
					oAuthApplications_Users, userId, orderByComparator, true);

			array[1] = oAuthApplications_Users;

			array[2] = filterGetByUserId_PrevAndNext(session,
					oAuthApplications_Users, userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OAuthApplications_Users filterGetByUserId_PrevAndNext(
		Session session, OAuthApplications_Users oAuthApplications_Users,
		long userId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_NO_INLINE_DISTINCT_WHERE_2);
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
				OAuthApplications_Users.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, OAuthApplications_UsersImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, OAuthApplications_UsersImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(oAuthApplications_Users);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OAuthApplications_Users> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
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
	 * Removes all the o auth applications_ userses where applicationId = &#63; and authorized = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 * @param authorized the authorized
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByA_A(long applicationId, boolean authorized)
		throws SystemException {
		for (OAuthApplications_Users oAuthApplications_Users : findByA_A(
				applicationId, authorized)) {
			remove(oAuthApplications_Users);
		}
	}

	/**
	 * Removes all the o auth applications_ userses where accessToken = &#63; from the database.
	 *
	 * @param accessToken the access token
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAccessToken(String accessToken)
		throws SystemException {
		for (OAuthApplications_Users oAuthApplications_Users : findByAccessToken(
				accessToken)) {
			remove(oAuthApplications_Users);
		}
	}

	/**
	 * Removes all the o auth applications_ userses where applicationId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByApplicationId(long applicationId)
		throws SystemException {
		for (OAuthApplications_Users oAuthApplications_Users : findByApplicationId(
				applicationId)) {
			remove(oAuthApplications_Users);
		}
	}

	/**
	 * Removes all the o auth applications_ userses where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (OAuthApplications_Users oAuthApplications_Users : findByUserId(
				userId)) {
			remove(oAuthApplications_Users);
		}
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
	 * Returns the number of o auth applications_ userses where applicationId = &#63; and authorized = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param authorized the authorized
	 * @return the number of matching o auth applications_ userses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByA_A(long applicationId, boolean authorized)
		throws SystemException {
		Object[] finderArgs = new Object[] { applicationId, authorized };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_A_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OAUTHAPPLICATIONS_USERS_WHERE);

			query.append(_FINDER_COLUMN_A_A_APPLICATIONID_2);

			query.append(_FINDER_COLUMN_A_A_AUTHORIZED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(applicationId);

				qPos.add(authorized);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_A_A, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of o auth applications_ userses that the user has permission to view where applicationId = &#63; and authorized = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param authorized the authorized
	 * @return the number of matching o auth applications_ userses that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByA_A(long applicationId, boolean authorized)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByA_A(applicationId, authorized);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_OAUTHAPPLICATIONS_USERS_WHERE);

		query.append(_FINDER_COLUMN_A_A_APPLICATIONID_2);

		query.append(_FINDER_COLUMN_A_A_AUTHORIZED_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				OAuthApplications_Users.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(applicationId);

			qPos.add(authorized);

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
	 * Returns the number of o auth applications_ userses that the user has permission to view where accessToken = &#63;.
	 *
	 * @param accessToken the access token
	 * @return the number of matching o auth applications_ userses that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByAccessToken(String accessToken)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByAccessToken(accessToken);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_OAUTHAPPLICATIONS_USERS_WHERE);

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
				OAuthApplications_Users.class.getName(),
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
	 * Returns the number of o auth applications_ userses where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the number of matching o auth applications_ userses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByApplicationId(long applicationId)
		throws SystemException {
		Object[] finderArgs = new Object[] { applicationId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_APPLICATIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OAUTHAPPLICATIONS_USERS_WHERE);

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
	 * Returns the number of o auth applications_ userses that the user has permission to view where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the number of matching o auth applications_ userses that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByApplicationId(long applicationId)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByApplicationId(applicationId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_OAUTHAPPLICATIONS_USERS_WHERE);

		query.append(_FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				OAuthApplications_Users.class.getName(),
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
	 * Returns the number of o auth applications_ userses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching o auth applications_ userses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OAUTHAPPLICATIONS_USERS_WHERE);

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
	 * Returns the number of o auth applications_ userses that the user has permission to view where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching o auth applications_ userses that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByUserId(long userId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByUserId(userId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_OAUTHAPPLICATIONS_USERS_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				OAuthApplications_Users.class.getName(),
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
	private static final String _FINDER_COLUMN_A_A_APPLICATIONID_2 = "oAuthApplications_Users.applicationId = ? AND ";
	private static final String _FINDER_COLUMN_A_A_AUTHORIZED_2 = "oAuthApplications_Users.authorized = ?";
	private static final String _FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_1 = "oAuthApplications_Users.accessToken IS NULL";
	private static final String _FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_2 = "oAuthApplications_Users.accessToken = ?";
	private static final String _FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_3 = "(oAuthApplications_Users.accessToken IS NULL OR oAuthApplications_Users.accessToken = ?)";
	private static final String _FINDER_COLUMN_APPLICATIONID_APPLICATIONID_2 = "oAuthApplications_Users.applicationId = ?";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "oAuthApplications_Users.userId = ?";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "oAuthApplications_Users.oaauid";
	private static final String _FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_WHERE =
		"SELECT DISTINCT {oAuthApplications_Users.*} FROM OAuth_OAuthApplications_Users oAuthApplications_Users WHERE ";
	private static final String _FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {OAuth_OAuthApplications_Users.*} FROM (SELECT DISTINCT oAuthApplications_Users.oaauid FROM OAuth_OAuthApplications_Users oAuthApplications_Users WHERE ";
	private static final String _FILTER_SQL_SELECT_OAUTHAPPLICATIONS_USERS_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN OAuth_OAuthApplications_Users ON TEMP_TABLE.oaauid = OAuth_OAuthApplications_Users.oaauid";
	private static final String _FILTER_SQL_COUNT_OAUTHAPPLICATIONS_USERS_WHERE = "SELECT COUNT(DISTINCT oAuthApplications_Users.oaauid) AS COUNT_VALUE FROM OAuth_OAuthApplications_Users oAuthApplications_Users WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "oAuthApplications_Users";
	private static final String _FILTER_ENTITY_TABLE = "OAuth_OAuthApplications_Users";
	private static final String _ORDER_BY_ENTITY_ALIAS = "oAuthApplications_Users.";
	private static final String _ORDER_BY_ENTITY_TABLE = "OAuth_OAuthApplications_Users.";
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