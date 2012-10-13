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
import com.liferay.portal.oauth.NoSuchApplicationException;
import com.liferay.portal.oauth.model.Application;
import com.liferay.portal.oauth.model.impl.ApplicationImpl;
import com.liferay.portal.oauth.model.impl.ApplicationModelImpl;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the application service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationPersistence
 * @see ApplicationUtil
 * @generated
 */
public class ApplicationPersistenceImpl extends BasePersistenceImpl<Application>
	implements ApplicationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ApplicationUtil} to access the application persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ApplicationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationModelImpl.FINDER_CACHE_ENABLED, ApplicationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationModelImpl.FINDER_CACHE_ENABLED, ApplicationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			ApplicationModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_CONSUMERKEY = new FinderPath(ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationModelImpl.FINDER_CACHE_ENABLED, ApplicationImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByConsumerKey",
			new String[] { String.class.getName() },
			ApplicationModelImpl.CONSUMERKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CONSUMERKEY = new FinderPath(ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByConsumerKey",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_N = new FinderPath(ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationModelImpl.FINDER_CACHE_ENABLED, ApplicationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_N",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_N = new FinderPath(ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_N",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_N = new FinderPath(ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationModelImpl.FINDER_CACHE_ENABLED, ApplicationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_N",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_N = new FinderPath(ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByU_N",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationModelImpl.FINDER_CACHE_ENABLED, ApplicationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationModelImpl.FINDER_CACHE_ENABLED, ApplicationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			ApplicationModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationModelImpl.FINDER_CACHE_ENABLED, ApplicationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationModelImpl.FINDER_CACHE_ENABLED, ApplicationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the application in the entity cache if it is enabled.
	 *
	 * @param application the application
	 */
	public void cacheResult(Application application) {
		EntityCacheUtil.putResult(ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationImpl.class, application.getPrimaryKey(), application);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONSUMERKEY,
			new Object[] { application.getConsumerKey() }, application);

		application.resetOriginalValues();
	}

	/**
	 * Caches the applications in the entity cache if it is enabled.
	 *
	 * @param applications the applications
	 */
	public void cacheResult(List<Application> applications) {
		for (Application application : applications) {
			if (EntityCacheUtil.getResult(
						ApplicationModelImpl.ENTITY_CACHE_ENABLED,
						ApplicationImpl.class, application.getPrimaryKey()) == null) {
				cacheResult(application);
			}
			else {
				application.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all applications.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ApplicationImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ApplicationImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the application.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Application application) {
		EntityCacheUtil.removeResult(ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationImpl.class, application.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(application);
	}

	@Override
	public void clearCache(List<Application> applications) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Application application : applications) {
			EntityCacheUtil.removeResult(ApplicationModelImpl.ENTITY_CACHE_ENABLED,
				ApplicationImpl.class, application.getPrimaryKey());

			clearUniqueFindersCache(application);
		}
	}

	protected void clearUniqueFindersCache(Application application) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONSUMERKEY,
			new Object[] { application.getConsumerKey() });
	}

	/**
	 * Creates a new application with the primary key. Does not add the application to the database.
	 *
	 * @param applicationId the primary key for the new application
	 * @return the new application
	 */
	public Application create(long applicationId) {
		Application application = new ApplicationImpl();

		application.setNew(true);
		application.setPrimaryKey(applicationId);

		return application;
	}

	/**
	 * Removes the application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param applicationId the primary key of the application
	 * @return the application that was removed
	 * @throws com.liferay.portal.oauth.NoSuchApplicationException if a application with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application remove(long applicationId)
		throws NoSuchApplicationException, SystemException {
		return remove(Long.valueOf(applicationId));
	}

	/**
	 * Removes the application with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the application
	 * @return the application that was removed
	 * @throws com.liferay.portal.oauth.NoSuchApplicationException if a application with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Application remove(Serializable primaryKey)
		throws NoSuchApplicationException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Application application = (Application)session.get(ApplicationImpl.class,
					primaryKey);

			if (application == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchApplicationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(application);
		}
		catch (NoSuchApplicationException nsee) {
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
	protected Application removeImpl(Application application)
		throws SystemException {
		application = toUnwrappedModel(application);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, application);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(application);

		return application;
	}

	@Override
	public Application updateImpl(
		com.liferay.portal.oauth.model.Application application, boolean merge)
		throws SystemException {
		application = toUnwrappedModel(application);

		boolean isNew = application.isNew();

		ApplicationModelImpl applicationModelImpl = (ApplicationModelImpl)application;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, application, merge);

			application.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ApplicationModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((applicationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(applicationModelImpl.getOriginalCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] {
						Long.valueOf(applicationModelImpl.getCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((applicationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(applicationModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(applicationModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}
		}

		EntityCacheUtil.putResult(ApplicationModelImpl.ENTITY_CACHE_ENABLED,
			ApplicationImpl.class, application.getPrimaryKey(), application);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONSUMERKEY,
				new Object[] { application.getConsumerKey() }, application);
		}
		else {
			if ((applicationModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_CONSUMERKEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						applicationModelImpl.getOriginalConsumerKey()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONSUMERKEY,
					args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONSUMERKEY,
					args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONSUMERKEY,
					new Object[] { application.getConsumerKey() }, application);
			}
		}

		return application;
	}

	protected Application toUnwrappedModel(Application application) {
		if (application instanceof ApplicationImpl) {
			return application;
		}

		ApplicationImpl applicationImpl = new ApplicationImpl();

		applicationImpl.setNew(application.isNew());
		applicationImpl.setPrimaryKey(application.getPrimaryKey());

		applicationImpl.setApplicationId(application.getApplicationId());
		applicationImpl.setCompanyId(application.getCompanyId());
		applicationImpl.setUserId(application.getUserId());
		applicationImpl.setUserName(application.getUserName());
		applicationImpl.setCreateDate(application.getCreateDate());
		applicationImpl.setModifiedDate(application.getModifiedDate());
		applicationImpl.setName(application.getName());
		applicationImpl.setDescription(application.getDescription());
		applicationImpl.setWebsite(application.getWebsite());
		applicationImpl.setCallBackURL(application.getCallBackURL());
		applicationImpl.setAccessLevel(application.getAccessLevel());
		applicationImpl.setConsumerKey(application.getConsumerKey());
		applicationImpl.setConsumerSecret(application.getConsumerSecret());
		applicationImpl.setLogoId(application.getLogoId());

		return applicationImpl;
	}

	/**
	 * Returns the application with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the application
	 * @return the application
	 * @throws com.liferay.portal.NoSuchModelException if a application with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Application findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the application with the primary key or throws a {@link com.liferay.portal.oauth.NoSuchApplicationException} if it could not be found.
	 *
	 * @param applicationId the primary key of the application
	 * @return the application
	 * @throws com.liferay.portal.oauth.NoSuchApplicationException if a application with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application findByPrimaryKey(long applicationId)
		throws NoSuchApplicationException, SystemException {
		Application application = fetchByPrimaryKey(applicationId);

		if (application == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + applicationId);
			}

			throw new NoSuchApplicationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				applicationId);
		}

		return application;
	}

	/**
	 * Returns the application with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the application
	 * @return the application, or <code>null</code> if a application with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Application fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the application with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param applicationId the primary key of the application
	 * @return the application, or <code>null</code> if a application with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application fetchByPrimaryKey(long applicationId)
		throws SystemException {
		Application application = (Application)EntityCacheUtil.getResult(ApplicationModelImpl.ENTITY_CACHE_ENABLED,
				ApplicationImpl.class, applicationId);

		if (application == _nullApplication) {
			return null;
		}

		if (application == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				application = (Application)session.get(ApplicationImpl.class,
						Long.valueOf(applicationId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (application != null) {
					cacheResult(application);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(ApplicationModelImpl.ENTITY_CACHE_ENABLED,
						ApplicationImpl.class, applicationId, _nullApplication);
				}

				closeSession(session);
			}
		}

		return application;
	}

	/**
	 * Returns all the applications where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching applications
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the applications where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @return the range of matching applications
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the applications where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching applications
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> findByCompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<Application> list = (List<Application>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Application application : list) {
				if ((companyId != application.getCompanyId())) {
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

			query.append(_SQL_SELECT_APPLICATION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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

				qPos.add(companyId);

				list = (List<Application>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first application in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching application
	 * @throws com.liferay.portal.oauth.NoSuchApplicationException if a matching application could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchApplicationException, SystemException {
		Application application = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (application != null) {
			return application;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchApplicationException(msg.toString());
	}

	/**
	 * Returns the first application in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching application, or <code>null</code> if a matching application could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application fetchByCompanyId_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Application> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last application in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching application
	 * @throws com.liferay.portal.oauth.NoSuchApplicationException if a matching application could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchApplicationException, SystemException {
		Application application = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (application != null) {
			return application;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchApplicationException(msg.toString());
	}

	/**
	 * Returns the last application in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching application, or <code>null</code> if a matching application could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application fetchByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCompanyId(companyId);

		List<Application> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the applications before and after the current application in the ordered set where companyId = &#63;.
	 *
	 * @param applicationId the primary key of the current application
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next application
	 * @throws com.liferay.portal.oauth.NoSuchApplicationException if a application with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application[] findByCompanyId_PrevAndNext(long applicationId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchApplicationException, SystemException {
		Application application = findByPrimaryKey(applicationId);

		Session session = null;

		try {
			session = openSession();

			Application[] array = new ApplicationImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, application,
					companyId, orderByComparator, true);

			array[1] = application;

			array[2] = getByCompanyId_PrevAndNext(session, application,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Application getByCompanyId_PrevAndNext(Session session,
		Application application, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPLICATION_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(application);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Application> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the applications that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching applications that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> filterFindByCompanyId(long companyId)
		throws SystemException {
		return filterFindByCompanyId(companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the applications that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @return the range of matching applications that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> filterFindByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return filterFindByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the applications that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching applications that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> filterFindByCompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCompanyId(companyId, start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_APPLICATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPLICATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPLICATION_NO_INLINE_DISTINCT_WHERE_2);
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
				Application.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ApplicationImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ApplicationImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			return (List<Application>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the applications before and after the current application in the ordered set of applications that the user has permission to view where companyId = &#63;.
	 *
	 * @param applicationId the primary key of the current application
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next application
	 * @throws com.liferay.portal.oauth.NoSuchApplicationException if a application with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application[] filterFindByCompanyId_PrevAndNext(long applicationId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchApplicationException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCompanyId_PrevAndNext(applicationId, companyId,
				orderByComparator);
		}

		Application application = findByPrimaryKey(applicationId);

		Session session = null;

		try {
			session = openSession();

			Application[] array = new ApplicationImpl[3];

			array[0] = filterGetByCompanyId_PrevAndNext(session, application,
					companyId, orderByComparator, true);

			array[1] = application;

			array[2] = filterGetByCompanyId_PrevAndNext(session, application,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Application filterGetByCompanyId_PrevAndNext(Session session,
		Application application, long companyId,
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
			query.append(_FILTER_SQL_SELECT_APPLICATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPLICATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPLICATION_NO_INLINE_DISTINCT_WHERE_2);
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
				Application.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ApplicationImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ApplicationImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(application);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Application> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the application where consumerKey = &#63; or throws a {@link com.liferay.portal.oauth.NoSuchApplicationException} if it could not be found.
	 *
	 * @param consumerKey the consumer key
	 * @return the matching application
	 * @throws com.liferay.portal.oauth.NoSuchApplicationException if a matching application could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application findByConsumerKey(String consumerKey)
		throws NoSuchApplicationException, SystemException {
		Application application = fetchByConsumerKey(consumerKey);

		if (application == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("consumerKey=");
			msg.append(consumerKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchApplicationException(msg.toString());
		}

		return application;
	}

	/**
	 * Returns the application where consumerKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param consumerKey the consumer key
	 * @return the matching application, or <code>null</code> if a matching application could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application fetchByConsumerKey(String consumerKey)
		throws SystemException {
		return fetchByConsumerKey(consumerKey, true);
	}

	/**
	 * Returns the application where consumerKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param consumerKey the consumer key
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching application, or <code>null</code> if a matching application could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application fetchByConsumerKey(String consumerKey,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { consumerKey };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CONSUMERKEY,
					finderArgs, this);
		}

		if (result instanceof Application) {
			Application application = (Application)result;

			if (!Validator.equals(consumerKey, application.getConsumerKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_APPLICATION_WHERE);

			if (consumerKey == null) {
				query.append(_FINDER_COLUMN_CONSUMERKEY_CONSUMERKEY_1);
			}
			else {
				if (consumerKey.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CONSUMERKEY_CONSUMERKEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_CONSUMERKEY_CONSUMERKEY_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (consumerKey != null) {
					qPos.add(consumerKey);
				}

				List<Application> list = q.list();

				result = list;

				Application application = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONSUMERKEY,
						finderArgs, list);
				}
				else {
					application = list.get(0);

					cacheResult(application);

					if ((application.getConsumerKey() == null) ||
							!application.getConsumerKey().equals(consumerKey)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONSUMERKEY,
							finderArgs, application);
					}
				}

				return application;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONSUMERKEY,
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
				return (Application)result;
			}
		}
	}

	/**
	 * Returns all the applications where companyId = &#63; and name LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching applications
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> findByC_N(long companyId, String name)
		throws SystemException {
		return findByC_N(companyId, name, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the applications where companyId = &#63; and name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @return the range of matching applications
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> findByC_N(long companyId, String name, int start,
		int end) throws SystemException {
		return findByC_N(companyId, name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the applications where companyId = &#63; and name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching applications
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> findByC_N(long companyId, String name, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_N;
		finderArgs = new Object[] { companyId, name, start, end, orderByComparator };

		List<Application> list = (List<Application>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Application application : list) {
				if ((companyId != application.getCompanyId()) ||
						!Validator.equals(name, application.getName())) {
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

			query.append(_SQL_SELECT_APPLICATION_WHERE);

			query.append(_FINDER_COLUMN_C_N_COMPANYID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_N_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_C_N_NAME_2);
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

				qPos.add(companyId);

				if (name != null) {
					qPos.add(name);
				}

				list = (List<Application>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first application in the ordered set where companyId = &#63; and name LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching application
	 * @throws com.liferay.portal.oauth.NoSuchApplicationException if a matching application could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application findByC_N_First(long companyId, String name,
		OrderByComparator orderByComparator)
		throws NoSuchApplicationException, SystemException {
		Application application = fetchByC_N_First(companyId, name,
				orderByComparator);

		if (application != null) {
			return application;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchApplicationException(msg.toString());
	}

	/**
	 * Returns the first application in the ordered set where companyId = &#63; and name LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching application, or <code>null</code> if a matching application could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application fetchByC_N_First(long companyId, String name,
		OrderByComparator orderByComparator) throws SystemException {
		List<Application> list = findByC_N(companyId, name, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last application in the ordered set where companyId = &#63; and name LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching application
	 * @throws com.liferay.portal.oauth.NoSuchApplicationException if a matching application could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application findByC_N_Last(long companyId, String name,
		OrderByComparator orderByComparator)
		throws NoSuchApplicationException, SystemException {
		Application application = fetchByC_N_Last(companyId, name,
				orderByComparator);

		if (application != null) {
			return application;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchApplicationException(msg.toString());
	}

	/**
	 * Returns the last application in the ordered set where companyId = &#63; and name LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching application, or <code>null</code> if a matching application could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application fetchByC_N_Last(long companyId, String name,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_N(companyId, name);

		List<Application> list = findByC_N(companyId, name, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the applications before and after the current application in the ordered set where companyId = &#63; and name LIKE &#63;.
	 *
	 * @param applicationId the primary key of the current application
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next application
	 * @throws com.liferay.portal.oauth.NoSuchApplicationException if a application with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application[] findByC_N_PrevAndNext(long applicationId,
		long companyId, String name, OrderByComparator orderByComparator)
		throws NoSuchApplicationException, SystemException {
		Application application = findByPrimaryKey(applicationId);

		Session session = null;

		try {
			session = openSession();

			Application[] array = new ApplicationImpl[3];

			array[0] = getByC_N_PrevAndNext(session, application, companyId,
					name, orderByComparator, true);

			array[1] = application;

			array[2] = getByC_N_PrevAndNext(session, application, companyId,
					name, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Application getByC_N_PrevAndNext(Session session,
		Application application, long companyId, String name,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPLICATION_WHERE);

		query.append(_FINDER_COLUMN_C_N_COMPANYID_2);

		if (name == null) {
			query.append(_FINDER_COLUMN_C_N_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_C_N_NAME_2);
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

		qPos.add(companyId);

		if (name != null) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(application);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Application> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the applications that the user has permission to view where companyId = &#63; and name LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching applications that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> filterFindByC_N(long companyId, String name)
		throws SystemException {
		return filterFindByC_N(companyId, name, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the applications that the user has permission to view where companyId = &#63; and name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @return the range of matching applications that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> filterFindByC_N(long companyId, String name,
		int start, int end) throws SystemException {
		return filterFindByC_N(companyId, name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the applications that the user has permissions to view where companyId = &#63; and name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching applications that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> filterFindByC_N(long companyId, String name,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByC_N(companyId, name, start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_APPLICATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPLICATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_C_N_COMPANYID_2);

		if (name == null) {
			query.append(_FINDER_COLUMN_C_N_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_C_N_NAME_2);
			}
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPLICATION_NO_INLINE_DISTINCT_WHERE_2);
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
				Application.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ApplicationImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ApplicationImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (name != null) {
				qPos.add(name);
			}

			return (List<Application>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the applications before and after the current application in the ordered set of applications that the user has permission to view where companyId = &#63; and name LIKE &#63;.
	 *
	 * @param applicationId the primary key of the current application
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next application
	 * @throws com.liferay.portal.oauth.NoSuchApplicationException if a application with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application[] filterFindByC_N_PrevAndNext(long applicationId,
		long companyId, String name, OrderByComparator orderByComparator)
		throws NoSuchApplicationException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByC_N_PrevAndNext(applicationId, companyId, name,
				orderByComparator);
		}

		Application application = findByPrimaryKey(applicationId);

		Session session = null;

		try {
			session = openSession();

			Application[] array = new ApplicationImpl[3];

			array[0] = filterGetByC_N_PrevAndNext(session, application,
					companyId, name, orderByComparator, true);

			array[1] = application;

			array[2] = filterGetByC_N_PrevAndNext(session, application,
					companyId, name, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Application filterGetByC_N_PrevAndNext(Session session,
		Application application, long companyId, String name,
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
			query.append(_FILTER_SQL_SELECT_APPLICATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPLICATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_C_N_COMPANYID_2);

		if (name == null) {
			query.append(_FINDER_COLUMN_C_N_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_C_N_NAME_2);
			}
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPLICATION_NO_INLINE_DISTINCT_WHERE_2);
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
				Application.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ApplicationImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ApplicationImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (name != null) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(application);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Application> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the applications where userId = &#63; and name LIKE &#63;.
	 *
	 * @param userId the user ID
	 * @param name the name
	 * @return the matching applications
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> findByU_N(long userId, String name)
		throws SystemException {
		return findByU_N(userId, name, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the applications where userId = &#63; and name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param name the name
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @return the range of matching applications
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> findByU_N(long userId, String name, int start,
		int end) throws SystemException {
		return findByU_N(userId, name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the applications where userId = &#63; and name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param name the name
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching applications
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> findByU_N(long userId, String name, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_N;
		finderArgs = new Object[] { userId, name, start, end, orderByComparator };

		List<Application> list = (List<Application>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Application application : list) {
				if ((userId != application.getUserId()) ||
						!Validator.equals(name, application.getName())) {
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

			query.append(_SQL_SELECT_APPLICATION_WHERE);

			query.append(_FINDER_COLUMN_U_N_USERID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_U_N_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_U_N_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_U_N_NAME_2);
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

				qPos.add(userId);

				if (name != null) {
					qPos.add(name);
				}

				list = (List<Application>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first application in the ordered set where userId = &#63; and name LIKE &#63;.
	 *
	 * @param userId the user ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching application
	 * @throws com.liferay.portal.oauth.NoSuchApplicationException if a matching application could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application findByU_N_First(long userId, String name,
		OrderByComparator orderByComparator)
		throws NoSuchApplicationException, SystemException {
		Application application = fetchByU_N_First(userId, name,
				orderByComparator);

		if (application != null) {
			return application;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchApplicationException(msg.toString());
	}

	/**
	 * Returns the first application in the ordered set where userId = &#63; and name LIKE &#63;.
	 *
	 * @param userId the user ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching application, or <code>null</code> if a matching application could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application fetchByU_N_First(long userId, String name,
		OrderByComparator orderByComparator) throws SystemException {
		List<Application> list = findByU_N(userId, name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last application in the ordered set where userId = &#63; and name LIKE &#63;.
	 *
	 * @param userId the user ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching application
	 * @throws com.liferay.portal.oauth.NoSuchApplicationException if a matching application could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application findByU_N_Last(long userId, String name,
		OrderByComparator orderByComparator)
		throws NoSuchApplicationException, SystemException {
		Application application = fetchByU_N_Last(userId, name,
				orderByComparator);

		if (application != null) {
			return application;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchApplicationException(msg.toString());
	}

	/**
	 * Returns the last application in the ordered set where userId = &#63; and name LIKE &#63;.
	 *
	 * @param userId the user ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching application, or <code>null</code> if a matching application could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application fetchByU_N_Last(long userId, String name,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByU_N(userId, name);

		List<Application> list = findByU_N(userId, name, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the applications before and after the current application in the ordered set where userId = &#63; and name LIKE &#63;.
	 *
	 * @param applicationId the primary key of the current application
	 * @param userId the user ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next application
	 * @throws com.liferay.portal.oauth.NoSuchApplicationException if a application with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application[] findByU_N_PrevAndNext(long applicationId, long userId,
		String name, OrderByComparator orderByComparator)
		throws NoSuchApplicationException, SystemException {
		Application application = findByPrimaryKey(applicationId);

		Session session = null;

		try {
			session = openSession();

			Application[] array = new ApplicationImpl[3];

			array[0] = getByU_N_PrevAndNext(session, application, userId, name,
					orderByComparator, true);

			array[1] = application;

			array[2] = getByU_N_PrevAndNext(session, application, userId, name,
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

	protected Application getByU_N_PrevAndNext(Session session,
		Application application, long userId, String name,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPLICATION_WHERE);

		query.append(_FINDER_COLUMN_U_N_USERID_2);

		if (name == null) {
			query.append(_FINDER_COLUMN_U_N_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_N_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_U_N_NAME_2);
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

		qPos.add(userId);

		if (name != null) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(application);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Application> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the applications that the user has permission to view where userId = &#63; and name LIKE &#63;.
	 *
	 * @param userId the user ID
	 * @param name the name
	 * @return the matching applications that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> filterFindByU_N(long userId, String name)
		throws SystemException {
		return filterFindByU_N(userId, name, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the applications that the user has permission to view where userId = &#63; and name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param name the name
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @return the range of matching applications that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> filterFindByU_N(long userId, String name,
		int start, int end) throws SystemException {
		return filterFindByU_N(userId, name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the applications that the user has permissions to view where userId = &#63; and name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param name the name
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching applications that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> filterFindByU_N(long userId, String name,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByU_N(userId, name, start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_APPLICATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPLICATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_U_N_USERID_2);

		if (name == null) {
			query.append(_FINDER_COLUMN_U_N_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_N_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_U_N_NAME_2);
			}
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPLICATION_NO_INLINE_DISTINCT_WHERE_2);
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
				Application.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ApplicationImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ApplicationImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			if (name != null) {
				qPos.add(name);
			}

			return (List<Application>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the applications before and after the current application in the ordered set of applications that the user has permission to view where userId = &#63; and name LIKE &#63;.
	 *
	 * @param applicationId the primary key of the current application
	 * @param userId the user ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next application
	 * @throws com.liferay.portal.oauth.NoSuchApplicationException if a application with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application[] filterFindByU_N_PrevAndNext(long applicationId,
		long userId, String name, OrderByComparator orderByComparator)
		throws NoSuchApplicationException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByU_N_PrevAndNext(applicationId, userId, name,
				orderByComparator);
		}

		Application application = findByPrimaryKey(applicationId);

		Session session = null;

		try {
			session = openSession();

			Application[] array = new ApplicationImpl[3];

			array[0] = filterGetByU_N_PrevAndNext(session, application, userId,
					name, orderByComparator, true);

			array[1] = application;

			array[2] = filterGetByU_N_PrevAndNext(session, application, userId,
					name, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Application filterGetByU_N_PrevAndNext(Session session,
		Application application, long userId, String name,
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
			query.append(_FILTER_SQL_SELECT_APPLICATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPLICATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_U_N_USERID_2);

		if (name == null) {
			query.append(_FINDER_COLUMN_U_N_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_N_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_U_N_NAME_2);
			}
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPLICATION_NO_INLINE_DISTINCT_WHERE_2);
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
				Application.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ApplicationImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ApplicationImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (name != null) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(application);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Application> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the applications where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching applications
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the applications where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @return the range of matching applications
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the applications where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching applications
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> findByUserId(long userId, int start, int end,
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

		List<Application> list = (List<Application>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Application application : list) {
				if ((userId != application.getUserId())) {
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

			query.append(_SQL_SELECT_APPLICATION_WHERE);

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

				list = (List<Application>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first application in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching application
	 * @throws com.liferay.portal.oauth.NoSuchApplicationException if a matching application could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchApplicationException, SystemException {
		Application application = fetchByUserId_First(userId, orderByComparator);

		if (application != null) {
			return application;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchApplicationException(msg.toString());
	}

	/**
	 * Returns the first application in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching application, or <code>null</code> if a matching application could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Application> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last application in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching application
	 * @throws com.liferay.portal.oauth.NoSuchApplicationException if a matching application could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchApplicationException, SystemException {
		Application application = fetchByUserId_Last(userId, orderByComparator);

		if (application != null) {
			return application;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchApplicationException(msg.toString());
	}

	/**
	 * Returns the last application in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching application, or <code>null</code> if a matching application could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		List<Application> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the applications before and after the current application in the ordered set where userId = &#63;.
	 *
	 * @param applicationId the primary key of the current application
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next application
	 * @throws com.liferay.portal.oauth.NoSuchApplicationException if a application with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application[] findByUserId_PrevAndNext(long applicationId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchApplicationException, SystemException {
		Application application = findByPrimaryKey(applicationId);

		Session session = null;

		try {
			session = openSession();

			Application[] array = new ApplicationImpl[3];

			array[0] = getByUserId_PrevAndNext(session, application, userId,
					orderByComparator, true);

			array[1] = application;

			array[2] = getByUserId_PrevAndNext(session, application, userId,
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

	protected Application getByUserId_PrevAndNext(Session session,
		Application application, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_APPLICATION_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(application);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Application> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the applications that the user has permission to view where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching applications that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> filterFindByUserId(long userId)
		throws SystemException {
		return filterFindByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the applications that the user has permission to view where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @return the range of matching applications that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> filterFindByUserId(long userId, int start, int end)
		throws SystemException {
		return filterFindByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the applications that the user has permissions to view where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching applications that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> filterFindByUserId(long userId, int start,
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
			query.append(_FILTER_SQL_SELECT_APPLICATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPLICATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPLICATION_NO_INLINE_DISTINCT_WHERE_2);
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
				Application.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, ApplicationImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, ApplicationImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			return (List<Application>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the applications before and after the current application in the ordered set of applications that the user has permission to view where userId = &#63;.
	 *
	 * @param applicationId the primary key of the current application
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next application
	 * @throws com.liferay.portal.oauth.NoSuchApplicationException if a application with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Application[] filterFindByUserId_PrevAndNext(long applicationId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchApplicationException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUserId_PrevAndNext(applicationId, userId,
				orderByComparator);
		}

		Application application = findByPrimaryKey(applicationId);

		Session session = null;

		try {
			session = openSession();

			Application[] array = new ApplicationImpl[3];

			array[0] = filterGetByUserId_PrevAndNext(session, application,
					userId, orderByComparator, true);

			array[1] = application;

			array[2] = filterGetByUserId_PrevAndNext(session, application,
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

	protected Application filterGetByUserId_PrevAndNext(Session session,
		Application application, long userId,
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
			query.append(_FILTER_SQL_SELECT_APPLICATION_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_APPLICATION_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_APPLICATION_NO_INLINE_DISTINCT_WHERE_2);
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
				Application.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, ApplicationImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, ApplicationImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(application);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Application> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the applications.
	 *
	 * @return the applications
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @return the range of applications
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the applications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of applications
	 * @param end the upper bound of the range of applications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of applications
	 * @throws SystemException if a system exception occurred
	 */
	public List<Application> findAll(int start, int end,
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

		List<Application> list = (List<Application>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_APPLICATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_APPLICATION;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Application>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Application>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the applications where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (Application application : findByCompanyId(companyId)) {
			remove(application);
		}
	}

	/**
	 * Removes the application where consumerKey = &#63; from the database.
	 *
	 * @param consumerKey the consumer key
	 * @return the application that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public Application removeByConsumerKey(String consumerKey)
		throws NoSuchApplicationException, SystemException {
		Application application = findByConsumerKey(consumerKey);

		return remove(application);
	}

	/**
	 * Removes all the applications where companyId = &#63; and name LIKE &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_N(long companyId, String name)
		throws SystemException {
		for (Application application : findByC_N(companyId, name)) {
			remove(application);
		}
	}

	/**
	 * Removes all the applications where userId = &#63; and name LIKE &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param name the name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_N(long userId, String name) throws SystemException {
		for (Application application : findByU_N(userId, name)) {
			remove(application);
		}
	}

	/**
	 * Removes all the applications where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (Application application : findByUserId(userId)) {
			remove(application);
		}
	}

	/**
	 * Removes all the applications from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Application application : findAll()) {
			remove(application);
		}
	}

	/**
	 * Returns the number of applications where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching applications
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPLICATION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of applications that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching applications that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByCompanyId(long companyId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByCompanyId(companyId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_APPLICATION_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Application.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

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
	 * Returns the number of applications where consumerKey = &#63;.
	 *
	 * @param consumerKey the consumer key
	 * @return the number of matching applications
	 * @throws SystemException if a system exception occurred
	 */
	public int countByConsumerKey(String consumerKey) throws SystemException {
		Object[] finderArgs = new Object[] { consumerKey };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CONSUMERKEY,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPLICATION_WHERE);

			if (consumerKey == null) {
				query.append(_FINDER_COLUMN_CONSUMERKEY_CONSUMERKEY_1);
			}
			else {
				if (consumerKey.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CONSUMERKEY_CONSUMERKEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_CONSUMERKEY_CONSUMERKEY_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (consumerKey != null) {
					qPos.add(consumerKey);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONSUMERKEY,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of applications where companyId = &#63; and name LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the number of matching applications
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_N(long companyId, String name)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_N,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APPLICATION_WHERE);

			query.append(_FINDER_COLUMN_C_N_COMPANYID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_N_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_C_N_NAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (name != null) {
					qPos.add(name);
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_N,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of applications that the user has permission to view where companyId = &#63; and name LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the number of matching applications that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByC_N(long companyId, String name)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByC_N(companyId, name);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_APPLICATION_WHERE);

		query.append(_FINDER_COLUMN_C_N_COMPANYID_2);

		if (name == null) {
			query.append(_FINDER_COLUMN_C_N_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_C_N_NAME_2);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Application.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (name != null) {
				qPos.add(name);
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
	 * Returns the number of applications where userId = &#63; and name LIKE &#63;.
	 *
	 * @param userId the user ID
	 * @param name the name
	 * @return the number of matching applications
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_N(long userId, String name) throws SystemException {
		Object[] finderArgs = new Object[] { userId, name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_N,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_APPLICATION_WHERE);

			query.append(_FINDER_COLUMN_U_N_USERID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_U_N_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_U_N_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_U_N_NAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (name != null) {
					qPos.add(name);
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

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_N,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of applications that the user has permission to view where userId = &#63; and name LIKE &#63;.
	 *
	 * @param userId the user ID
	 * @param name the name
	 * @return the number of matching applications that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByU_N(long userId, String name)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByU_N(userId, name);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_APPLICATION_WHERE);

		query.append(_FINDER_COLUMN_U_N_USERID_2);

		if (name == null) {
			query.append(_FINDER_COLUMN_U_N_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_N_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_U_N_NAME_2);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Application.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			if (name != null) {
				qPos.add(name);
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
	 * Returns the number of applications where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching applications
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_APPLICATION_WHERE);

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
	 * Returns the number of applications that the user has permission to view where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching applications that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByUserId(long userId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByUserId(userId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_APPLICATION_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Application.class.getName(),
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
	 * Returns the number of applications.
	 *
	 * @return the number of applications
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_APPLICATION);

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
	 * Initializes the application persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.oauth.model.Application")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Application>> listenersList = new ArrayList<ModelListener<Application>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Application>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ApplicationImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = ApplicationPersistence.class)
	protected ApplicationPersistence applicationPersistence;
	@BeanReference(type = ApplicationUserPersistence.class)
	protected ApplicationUserPersistence applicationUserPersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_APPLICATION = "SELECT application FROM Application application";
	private static final String _SQL_SELECT_APPLICATION_WHERE = "SELECT application FROM Application application WHERE ";
	private static final String _SQL_COUNT_APPLICATION = "SELECT COUNT(application) FROM Application application";
	private static final String _SQL_COUNT_APPLICATION_WHERE = "SELECT COUNT(application) FROM Application application WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "application.companyId = ?";
	private static final String _FINDER_COLUMN_CONSUMERKEY_CONSUMERKEY_1 = "application.consumerKey IS NULL";
	private static final String _FINDER_COLUMN_CONSUMERKEY_CONSUMERKEY_2 = "application.consumerKey = ?";
	private static final String _FINDER_COLUMN_CONSUMERKEY_CONSUMERKEY_3 = "(application.consumerKey IS NULL OR application.consumerKey = ?)";
	private static final String _FINDER_COLUMN_C_N_COMPANYID_2 = "application.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_N_NAME_1 = "application.name LIKE NULL";
	private static final String _FINDER_COLUMN_C_N_NAME_2 = "application.name LIKE ?";
	private static final String _FINDER_COLUMN_C_N_NAME_3 = "(application.name IS NULL OR application.name LIKE ?)";
	private static final String _FINDER_COLUMN_U_N_USERID_2 = "application.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_N_NAME_1 = "application.name LIKE NULL";
	private static final String _FINDER_COLUMN_U_N_NAME_2 = "application.name LIKE ?";
	private static final String _FINDER_COLUMN_U_N_NAME_3 = "(application.name IS NULL OR application.name LIKE ?)";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "application.userId = ?";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "application.applicationId";
	private static final String _FILTER_SQL_SELECT_APPLICATION_WHERE = "SELECT DISTINCT {application.*} FROM OAuth_Application application WHERE ";
	private static final String _FILTER_SQL_SELECT_APPLICATION_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {OAuth_Application.*} FROM (SELECT DISTINCT application.applicationId FROM OAuth_Application application WHERE ";
	private static final String _FILTER_SQL_SELECT_APPLICATION_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN OAuth_Application ON TEMP_TABLE.applicationId = OAuth_Application.applicationId";
	private static final String _FILTER_SQL_COUNT_APPLICATION_WHERE = "SELECT COUNT(DISTINCT application.applicationId) AS COUNT_VALUE FROM OAuth_Application application WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "application";
	private static final String _FILTER_ENTITY_TABLE = "OAuth_Application";
	private static final String _ORDER_BY_ENTITY_ALIAS = "application.";
	private static final String _ORDER_BY_ENTITY_TABLE = "OAuth_Application.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Application exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Application exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ApplicationPersistenceImpl.class);
	private static Application _nullApplication = new ApplicationImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Application> toCacheModel() {
				return _nullApplicationCacheModel;
			}
		};

	private static CacheModel<Application> _nullApplicationCacheModel = new CacheModel<Application>() {
			public Application toEntityModel() {
				return _nullApplication;
			}
		};
}