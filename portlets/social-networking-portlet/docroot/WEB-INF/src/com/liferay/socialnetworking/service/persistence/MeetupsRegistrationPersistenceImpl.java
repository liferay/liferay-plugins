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

package com.liferay.socialnetworking.service.persistence;

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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;
import com.liferay.socialnetworking.model.MeetupsRegistration;
import com.liferay.socialnetworking.model.impl.MeetupsRegistrationImpl;
import com.liferay.socialnetworking.model.impl.MeetupsRegistrationModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the meetups registration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsRegistrationPersistence
 * @see MeetupsRegistrationUtil
 * @generated
 */
public class MeetupsRegistrationPersistenceImpl extends BasePersistenceImpl<MeetupsRegistration>
	implements MeetupsRegistrationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MeetupsRegistrationUtil} to access the meetups registration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MeetupsRegistrationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_MEETUPSENTRYID = new FinderPath(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED,
			MeetupsRegistrationImpl.class, FINDER_CLASS_NAME_LIST,
			"findByMeetupsEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_MEETUPSENTRYID = new FinderPath(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByMeetupsEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_U_ME = new FinderPath(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED,
			MeetupsRegistrationImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByU_ME",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_U_ME = new FinderPath(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByU_ME",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_ME_S = new FinderPath(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED,
			MeetupsRegistrationImpl.class, FINDER_CLASS_NAME_LIST,
			"findByME_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_ME_S = new FinderPath(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByME_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED,
			MeetupsRegistrationImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the meetups registration in the entity cache if it is enabled.
	 *
	 * @param meetupsRegistration the meetups registration
	 */
	public void cacheResult(MeetupsRegistration meetupsRegistration) {
		EntityCacheUtil.putResult(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationImpl.class, meetupsRegistration.getPrimaryKey(),
			meetupsRegistration);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_ME,
			new Object[] {
				Long.valueOf(meetupsRegistration.getUserId()),
				Long.valueOf(meetupsRegistration.getMeetupsEntryId())
			}, meetupsRegistration);

		meetupsRegistration.resetOriginalValues();
	}

	/**
	 * Caches the meetups registrations in the entity cache if it is enabled.
	 *
	 * @param meetupsRegistrations the meetups registrations
	 */
	public void cacheResult(List<MeetupsRegistration> meetupsRegistrations) {
		for (MeetupsRegistration meetupsRegistration : meetupsRegistrations) {
			if (EntityCacheUtil.getResult(
						MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
						MeetupsRegistrationImpl.class,
						meetupsRegistration.getPrimaryKey()) == null) {
				cacheResult(meetupsRegistration);
			}
		}
	}

	/**
	 * Clears the cache for all meetups registrations.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(MeetupsRegistrationImpl.class.getName());
		}

		EntityCacheUtil.clearCache(MeetupsRegistrationImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the meetups registration.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MeetupsRegistration meetupsRegistration) {
		EntityCacheUtil.removeResult(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationImpl.class, meetupsRegistration.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL, FINDER_ARGS_EMPTY);

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_ME,
			new Object[] {
				Long.valueOf(meetupsRegistration.getUserId()),
				Long.valueOf(meetupsRegistration.getMeetupsEntryId())
			});
	}

	/**
	 * Creates a new meetups registration with the primary key. Does not add the meetups registration to the database.
	 *
	 * @param meetupsRegistrationId the primary key for the new meetups registration
	 * @return the new meetups registration
	 */
	public MeetupsRegistration create(long meetupsRegistrationId) {
		MeetupsRegistration meetupsRegistration = new MeetupsRegistrationImpl();

		meetupsRegistration.setNew(true);
		meetupsRegistration.setPrimaryKey(meetupsRegistrationId);

		return meetupsRegistration;
	}

	/**
	 * Removes the meetups registration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the meetups registration
	 * @return the meetups registration that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a meetups registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetupsRegistration remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the meetups registration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param meetupsRegistrationId the primary key of the meetups registration
	 * @return the meetups registration that was removed
	 * @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MeetupsRegistration remove(long meetupsRegistrationId)
		throws NoSuchMeetupsRegistrationException, SystemException {
		Session session = null;

		try {
			session = openSession();

			MeetupsRegistration meetupsRegistration = (MeetupsRegistration)session.get(MeetupsRegistrationImpl.class,
					Long.valueOf(meetupsRegistrationId));

			if (meetupsRegistration == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						meetupsRegistrationId);
				}

				throw new NoSuchMeetupsRegistrationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					meetupsRegistrationId);
			}

			return meetupsRegistrationPersistence.remove(meetupsRegistration);
		}
		catch (NoSuchMeetupsRegistrationException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Removes the meetups registration from the database. Also notifies the appropriate model listeners.
	 *
	 * @param meetupsRegistration the meetups registration
	 * @return the meetups registration that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetupsRegistration remove(MeetupsRegistration meetupsRegistration)
		throws SystemException {
		return super.remove(meetupsRegistration);
	}

	@Override
	protected MeetupsRegistration removeImpl(
		MeetupsRegistration meetupsRegistration) throws SystemException {
		meetupsRegistration = toUnwrappedModel(meetupsRegistration);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, meetupsRegistration);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		MeetupsRegistrationModelImpl meetupsRegistrationModelImpl = (MeetupsRegistrationModelImpl)meetupsRegistration;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_ME,
			new Object[] {
				Long.valueOf(meetupsRegistrationModelImpl.getUserId()),
				Long.valueOf(meetupsRegistrationModelImpl.getMeetupsEntryId())
			});

		EntityCacheUtil.removeResult(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationImpl.class, meetupsRegistration.getPrimaryKey());

		return meetupsRegistration;
	}

	@Override
	public MeetupsRegistration updateImpl(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration,
		boolean merge) throws SystemException {
		meetupsRegistration = toUnwrappedModel(meetupsRegistration);

		boolean isNew = meetupsRegistration.isNew();

		MeetupsRegistrationModelImpl meetupsRegistrationModelImpl = (MeetupsRegistrationModelImpl)meetupsRegistration;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, meetupsRegistration, merge);

			meetupsRegistration.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationImpl.class, meetupsRegistration.getPrimaryKey(),
			meetupsRegistration);

		if (!isNew &&
				((meetupsRegistration.getUserId() != meetupsRegistrationModelImpl.getOriginalUserId()) ||
				(meetupsRegistration.getMeetupsEntryId() != meetupsRegistrationModelImpl.getOriginalMeetupsEntryId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_ME,
				new Object[] {
					Long.valueOf(
						meetupsRegistrationModelImpl.getOriginalUserId()),
					Long.valueOf(
						meetupsRegistrationModelImpl.getOriginalMeetupsEntryId())
				});
		}

		if (isNew ||
				((meetupsRegistration.getUserId() != meetupsRegistrationModelImpl.getOriginalUserId()) ||
				(meetupsRegistration.getMeetupsEntryId() != meetupsRegistrationModelImpl.getOriginalMeetupsEntryId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_ME,
				new Object[] {
					Long.valueOf(meetupsRegistration.getUserId()),
					Long.valueOf(meetupsRegistration.getMeetupsEntryId())
				}, meetupsRegistration);
		}

		return meetupsRegistration;
	}

	protected MeetupsRegistration toUnwrappedModel(
		MeetupsRegistration meetupsRegistration) {
		if (meetupsRegistration instanceof MeetupsRegistrationImpl) {
			return meetupsRegistration;
		}

		MeetupsRegistrationImpl meetupsRegistrationImpl = new MeetupsRegistrationImpl();

		meetupsRegistrationImpl.setNew(meetupsRegistration.isNew());
		meetupsRegistrationImpl.setPrimaryKey(meetupsRegistration.getPrimaryKey());

		meetupsRegistrationImpl.setMeetupsRegistrationId(meetupsRegistration.getMeetupsRegistrationId());
		meetupsRegistrationImpl.setCompanyId(meetupsRegistration.getCompanyId());
		meetupsRegistrationImpl.setUserId(meetupsRegistration.getUserId());
		meetupsRegistrationImpl.setUserName(meetupsRegistration.getUserName());
		meetupsRegistrationImpl.setCreateDate(meetupsRegistration.getCreateDate());
		meetupsRegistrationImpl.setModifiedDate(meetupsRegistration.getModifiedDate());
		meetupsRegistrationImpl.setMeetupsEntryId(meetupsRegistration.getMeetupsEntryId());
		meetupsRegistrationImpl.setStatus(meetupsRegistration.getStatus());
		meetupsRegistrationImpl.setComments(meetupsRegistration.getComments());

		return meetupsRegistrationImpl;
	}

	/**
	 * Returns the meetups registration with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the meetups registration
	 * @return the meetups registration
	 * @throws com.liferay.portal.NoSuchModelException if a meetups registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetupsRegistration findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the meetups registration with the primary key or throws a {@link com.liferay.socialnetworking.NoSuchMeetupsRegistrationException} if it could not be found.
	 *
	 * @param meetupsRegistrationId the primary key of the meetups registration
	 * @return the meetups registration
	 * @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MeetupsRegistration findByPrimaryKey(long meetupsRegistrationId)
		throws NoSuchMeetupsRegistrationException, SystemException {
		MeetupsRegistration meetupsRegistration = fetchByPrimaryKey(meetupsRegistrationId);

		if (meetupsRegistration == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					meetupsRegistrationId);
			}

			throw new NoSuchMeetupsRegistrationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				meetupsRegistrationId);
		}

		return meetupsRegistration;
	}

	/**
	 * Returns the meetups registration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the meetups registration
	 * @return the meetups registration, or <code>null</code> if a meetups registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetupsRegistration fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the meetups registration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param meetupsRegistrationId the primary key of the meetups registration
	 * @return the meetups registration, or <code>null</code> if a meetups registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MeetupsRegistration fetchByPrimaryKey(long meetupsRegistrationId)
		throws SystemException {
		MeetupsRegistration meetupsRegistration = (MeetupsRegistration)EntityCacheUtil.getResult(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
				MeetupsRegistrationImpl.class, meetupsRegistrationId);

		if (meetupsRegistration == _nullMeetupsRegistration) {
			return null;
		}

		if (meetupsRegistration == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				meetupsRegistration = (MeetupsRegistration)session.get(MeetupsRegistrationImpl.class,
						Long.valueOf(meetupsRegistrationId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (meetupsRegistration != null) {
					cacheResult(meetupsRegistration);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
						MeetupsRegistrationImpl.class, meetupsRegistrationId,
						_nullMeetupsRegistration);
				}

				closeSession(session);
			}
		}

		return meetupsRegistration;
	}

	/**
	 * Returns all the meetups registrations where meetupsEntryId = &#63;.
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @return the matching meetups registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<MeetupsRegistration> findByMeetupsEntryId(long meetupsEntryId)
		throws SystemException {
		return findByMeetupsEntryId(meetupsEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the meetups registrations where meetupsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param start the lower bound of the range of meetups registrations
	 * @param end the upper bound of the range of meetups registrations (not inclusive)
	 * @return the range of matching meetups registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<MeetupsRegistration> findByMeetupsEntryId(long meetupsEntryId,
		int start, int end) throws SystemException {
		return findByMeetupsEntryId(meetupsEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the meetups registrations where meetupsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param start the lower bound of the range of meetups registrations
	 * @param end the upper bound of the range of meetups registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching meetups registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<MeetupsRegistration> findByMeetupsEntryId(long meetupsEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				meetupsEntryId,
				
				start, end, orderByComparator
			};

		List<MeetupsRegistration> list = (List<MeetupsRegistration>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_MEETUPSENTRYID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MEETUPSREGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_MEETUPSENTRYID_MEETUPSENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(MeetupsRegistrationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupsEntryId);

				list = (List<MeetupsRegistration>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_MEETUPSENTRYID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_MEETUPSENTRYID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first meetups registration in the ordered set where meetupsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching meetups registration
	 * @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MeetupsRegistration findByMeetupsEntryId_First(long meetupsEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchMeetupsRegistrationException, SystemException {
		List<MeetupsRegistration> list = findByMeetupsEntryId(meetupsEntryId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("meetupsEntryId=");
			msg.append(meetupsEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMeetupsRegistrationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last meetups registration in the ordered set where meetupsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching meetups registration
	 * @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MeetupsRegistration findByMeetupsEntryId_Last(long meetupsEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchMeetupsRegistrationException, SystemException {
		int count = countByMeetupsEntryId(meetupsEntryId);

		List<MeetupsRegistration> list = findByMeetupsEntryId(meetupsEntryId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("meetupsEntryId=");
			msg.append(meetupsEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMeetupsRegistrationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the meetups registrations before and after the current meetups registration in the ordered set where meetupsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param meetupsRegistrationId the primary key of the current meetups registration
	 * @param meetupsEntryId the meetups entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next meetups registration
	 * @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MeetupsRegistration[] findByMeetupsEntryId_PrevAndNext(
		long meetupsRegistrationId, long meetupsEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchMeetupsRegistrationException, SystemException {
		MeetupsRegistration meetupsRegistration = findByPrimaryKey(meetupsRegistrationId);

		Session session = null;

		try {
			session = openSession();

			MeetupsRegistration[] array = new MeetupsRegistrationImpl[3];

			array[0] = getByMeetupsEntryId_PrevAndNext(session,
					meetupsRegistration, meetupsEntryId, orderByComparator, true);

			array[1] = meetupsRegistration;

			array[2] = getByMeetupsEntryId_PrevAndNext(session,
					meetupsRegistration, meetupsEntryId, orderByComparator,
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

	protected MeetupsRegistration getByMeetupsEntryId_PrevAndNext(
		Session session, MeetupsRegistration meetupsRegistration,
		long meetupsEntryId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MEETUPSREGISTRATION_WHERE);

		query.append(_FINDER_COLUMN_MEETUPSENTRYID_MEETUPSENTRYID_2);

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

		else {
			query.append(MeetupsRegistrationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(meetupsEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(meetupsRegistration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MeetupsRegistration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the meetups registration where userId = &#63; and meetupsEntryId = &#63; or throws a {@link com.liferay.socialnetworking.NoSuchMeetupsRegistrationException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param meetupsEntryId the meetups entry ID
	 * @return the matching meetups registration
	 * @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MeetupsRegistration findByU_ME(long userId, long meetupsEntryId)
		throws NoSuchMeetupsRegistrationException, SystemException {
		MeetupsRegistration meetupsRegistration = fetchByU_ME(userId,
				meetupsEntryId);

		if (meetupsRegistration == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", meetupsEntryId=");
			msg.append(meetupsEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchMeetupsRegistrationException(msg.toString());
		}

		return meetupsRegistration;
	}

	/**
	 * Returns the meetups registration where userId = &#63; and meetupsEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param meetupsEntryId the meetups entry ID
	 * @return the matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MeetupsRegistration fetchByU_ME(long userId, long meetupsEntryId)
		throws SystemException {
		return fetchByU_ME(userId, meetupsEntryId, true);
	}

	/**
	 * Returns the meetups registration where userId = &#63; and meetupsEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param meetupsEntryId the meetups entry ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching meetups registration, or <code>null</code> if a matching meetups registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MeetupsRegistration fetchByU_ME(long userId, long meetupsEntryId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { userId, meetupsEntryId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_U_ME,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_MEETUPSREGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_U_ME_USERID_2);

			query.append(_FINDER_COLUMN_U_ME_MEETUPSENTRYID_2);

			query.append(MeetupsRegistrationModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(meetupsEntryId);

				List<MeetupsRegistration> list = q.list();

				result = list;

				MeetupsRegistration meetupsRegistration = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_ME,
						finderArgs, list);
				}
				else {
					meetupsRegistration = list.get(0);

					cacheResult(meetupsRegistration);

					if ((meetupsRegistration.getUserId() != userId) ||
							(meetupsRegistration.getMeetupsEntryId() != meetupsEntryId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_ME,
							finderArgs, meetupsRegistration);
					}
				}

				return meetupsRegistration;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_ME,
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
				return (MeetupsRegistration)result;
			}
		}
	}

	/**
	 * Returns all the meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param status the status
	 * @return the matching meetups registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<MeetupsRegistration> findByME_S(long meetupsEntryId, int status)
		throws SystemException {
		return findByME_S(meetupsEntryId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param status the status
	 * @param start the lower bound of the range of meetups registrations
	 * @param end the upper bound of the range of meetups registrations (not inclusive)
	 * @return the range of matching meetups registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<MeetupsRegistration> findByME_S(long meetupsEntryId,
		int status, int start, int end) throws SystemException {
		return findByME_S(meetupsEntryId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param status the status
	 * @param start the lower bound of the range of meetups registrations
	 * @param end the upper bound of the range of meetups registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching meetups registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<MeetupsRegistration> findByME_S(long meetupsEntryId,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				meetupsEntryId, status,
				
				start, end, orderByComparator
			};

		List<MeetupsRegistration> list = (List<MeetupsRegistration>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ME_S,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_MEETUPSREGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_ME_S_MEETUPSENTRYID_2);

			query.append(_FINDER_COLUMN_ME_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(MeetupsRegistrationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupsEntryId);

				qPos.add(status);

				list = (List<MeetupsRegistration>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_ME_S,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ME_S,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching meetups registration
	 * @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MeetupsRegistration findByME_S_First(long meetupsEntryId,
		int status, OrderByComparator orderByComparator)
		throws NoSuchMeetupsRegistrationException, SystemException {
		List<MeetupsRegistration> list = findByME_S(meetupsEntryId, status, 0,
				1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("meetupsEntryId=");
			msg.append(meetupsEntryId);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMeetupsRegistrationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching meetups registration
	 * @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a matching meetups registration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MeetupsRegistration findByME_S_Last(long meetupsEntryId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchMeetupsRegistrationException, SystemException {
		int count = countByME_S(meetupsEntryId, status);

		List<MeetupsRegistration> list = findByME_S(meetupsEntryId, status,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("meetupsEntryId=");
			msg.append(meetupsEntryId);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMeetupsRegistrationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the meetups registrations before and after the current meetups registration in the ordered set where meetupsEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param meetupsRegistrationId the primary key of the current meetups registration
	 * @param meetupsEntryId the meetups entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next meetups registration
	 * @throws com.liferay.socialnetworking.NoSuchMeetupsRegistrationException if a meetups registration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MeetupsRegistration[] findByME_S_PrevAndNext(
		long meetupsRegistrationId, long meetupsEntryId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchMeetupsRegistrationException, SystemException {
		MeetupsRegistration meetupsRegistration = findByPrimaryKey(meetupsRegistrationId);

		Session session = null;

		try {
			session = openSession();

			MeetupsRegistration[] array = new MeetupsRegistrationImpl[3];

			array[0] = getByME_S_PrevAndNext(session, meetupsRegistration,
					meetupsEntryId, status, orderByComparator, true);

			array[1] = meetupsRegistration;

			array[2] = getByME_S_PrevAndNext(session, meetupsRegistration,
					meetupsEntryId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MeetupsRegistration getByME_S_PrevAndNext(Session session,
		MeetupsRegistration meetupsRegistration, long meetupsEntryId,
		int status, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MEETUPSREGISTRATION_WHERE);

		query.append(_FINDER_COLUMN_ME_S_MEETUPSENTRYID_2);

		query.append(_FINDER_COLUMN_ME_S_STATUS_2);

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

		else {
			query.append(MeetupsRegistrationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(meetupsEntryId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(meetupsRegistration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MeetupsRegistration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the meetups registrations.
	 *
	 * @return the meetups registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<MeetupsRegistration> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the meetups registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of meetups registrations
	 * @param end the upper bound of the range of meetups registrations (not inclusive)
	 * @return the range of meetups registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<MeetupsRegistration> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the meetups registrations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of meetups registrations
	 * @param end the upper bound of the range of meetups registrations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of meetups registrations
	 * @throws SystemException if a system exception occurred
	 */
	public List<MeetupsRegistration> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		List<MeetupsRegistration> list = (List<MeetupsRegistration>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_MEETUPSREGISTRATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MEETUPSREGISTRATION.concat(MeetupsRegistrationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<MeetupsRegistration>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<MeetupsRegistration>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the meetups registrations where meetupsEntryId = &#63; from the database.
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByMeetupsEntryId(long meetupsEntryId)
		throws SystemException {
		for (MeetupsRegistration meetupsRegistration : findByMeetupsEntryId(
				meetupsEntryId)) {
			meetupsRegistrationPersistence.remove(meetupsRegistration);
		}
	}

	/**
	 * Removes the meetups registration where userId = &#63; and meetupsEntryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param meetupsEntryId the meetups entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_ME(long userId, long meetupsEntryId)
		throws NoSuchMeetupsRegistrationException, SystemException {
		MeetupsRegistration meetupsRegistration = findByU_ME(userId,
				meetupsEntryId);

		meetupsRegistrationPersistence.remove(meetupsRegistration);
	}

	/**
	 * Removes all the meetups registrations where meetupsEntryId = &#63; and status = &#63; from the database.
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByME_S(long meetupsEntryId, int status)
		throws SystemException {
		for (MeetupsRegistration meetupsRegistration : findByME_S(
				meetupsEntryId, status)) {
			meetupsRegistrationPersistence.remove(meetupsRegistration);
		}
	}

	/**
	 * Removes all the meetups registrations from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (MeetupsRegistration meetupsRegistration : findAll()) {
			meetupsRegistrationPersistence.remove(meetupsRegistration);
		}
	}

	/**
	 * Returns the number of meetups registrations where meetupsEntryId = &#63;.
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @return the number of matching meetups registrations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMeetupsEntryId(long meetupsEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { meetupsEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MEETUPSENTRYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MEETUPSREGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_MEETUPSENTRYID_MEETUPSENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupsEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MEETUPSENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of meetups registrations where userId = &#63; and meetupsEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param meetupsEntryId the meetups entry ID
	 * @return the number of matching meetups registrations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_ME(long userId, long meetupsEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, meetupsEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_ME,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MEETUPSREGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_U_ME_USERID_2);

			query.append(_FINDER_COLUMN_U_ME_MEETUPSENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(meetupsEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_ME,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of meetups registrations where meetupsEntryId = &#63; and status = &#63;.
	 *
	 * @param meetupsEntryId the meetups entry ID
	 * @param status the status
	 * @return the number of matching meetups registrations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByME_S(long meetupsEntryId, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { meetupsEntryId, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ME_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MEETUPSREGISTRATION_WHERE);

			query.append(_FINDER_COLUMN_ME_S_MEETUPSENTRYID_2);

			query.append(_FINDER_COLUMN_ME_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupsEntryId);

				qPos.add(status);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ME_S,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of meetups registrations.
	 *
	 * @return the number of meetups registrations
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MEETUPSREGISTRATION);

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
	 * Initializes the meetups registration persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.socialnetworking.model.MeetupsRegistration")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<MeetupsRegistration>> listenersList = new ArrayList<ModelListener<MeetupsRegistration>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<MeetupsRegistration>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(MeetupsRegistrationImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = MeetupsEntryPersistence.class)
	protected MeetupsEntryPersistence meetupsEntryPersistence;
	@BeanReference(type = MeetupsRegistrationPersistence.class)
	protected MeetupsRegistrationPersistence meetupsRegistrationPersistence;
	@BeanReference(type = WallEntryPersistence.class)
	protected WallEntryPersistence wallEntryPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_MEETUPSREGISTRATION = "SELECT meetupsRegistration FROM MeetupsRegistration meetupsRegistration";
	private static final String _SQL_SELECT_MEETUPSREGISTRATION_WHERE = "SELECT meetupsRegistration FROM MeetupsRegistration meetupsRegistration WHERE ";
	private static final String _SQL_COUNT_MEETUPSREGISTRATION = "SELECT COUNT(meetupsRegistration) FROM MeetupsRegistration meetupsRegistration";
	private static final String _SQL_COUNT_MEETUPSREGISTRATION_WHERE = "SELECT COUNT(meetupsRegistration) FROM MeetupsRegistration meetupsRegistration WHERE ";
	private static final String _FINDER_COLUMN_MEETUPSENTRYID_MEETUPSENTRYID_2 = "meetupsRegistration.meetupsEntryId = ?";
	private static final String _FINDER_COLUMN_U_ME_USERID_2 = "meetupsRegistration.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_ME_MEETUPSENTRYID_2 = "meetupsRegistration.meetupsEntryId = ?";
	private static final String _FINDER_COLUMN_ME_S_MEETUPSENTRYID_2 = "meetupsRegistration.meetupsEntryId = ? AND ";
	private static final String _FINDER_COLUMN_ME_S_STATUS_2 = "meetupsRegistration.status = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "meetupsRegistration.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MeetupsRegistration exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MeetupsRegistration exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(MeetupsRegistrationPersistenceImpl.class);
	private static MeetupsRegistration _nullMeetupsRegistration = new MeetupsRegistrationImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<MeetupsRegistration> toCacheModel() {
				return _nullMeetupsRegistrationCacheModel;
			}
		};

	private static CacheModel<MeetupsRegistration> _nullMeetupsRegistrationCacheModel =
		new CacheModel<MeetupsRegistration>() {
			public MeetupsRegistration toEntityModel() {
				return _nullMeetupsRegistration;
			}
		};
}