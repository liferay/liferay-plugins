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

package com.liferay.pushnotifications.service.persistence.impl;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.pushnotifications.NoSuchDeviceException;
import com.liferay.pushnotifications.model.PushNotificationsDevice;
import com.liferay.pushnotifications.model.impl.PushNotificationsDeviceImpl;
import com.liferay.pushnotifications.model.impl.PushNotificationsDeviceModelImpl;
import com.liferay.pushnotifications.service.persistence.PushNotificationsDevicePersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the push notifications device service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Bruno Farache
 * @see PushNotificationsDevicePersistence
 * @see PushNotificationsDeviceUtil
 * @generated
 */
public class PushNotificationsDevicePersistenceImpl extends BasePersistenceImpl<PushNotificationsDevice>
	implements PushNotificationsDevicePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PushNotificationsDeviceUtil} to access the push notifications device persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PushNotificationsDeviceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsDeviceModelImpl.FINDER_CACHE_ENABLED,
			PushNotificationsDeviceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsDeviceModelImpl.FINDER_CACHE_ENABLED,
			PushNotificationsDeviceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsDeviceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public PushNotificationsDevicePersistenceImpl() {
		setModelClass(PushNotificationsDevice.class);
	}

	/**
	 * Caches the push notifications device in the entity cache if it is enabled.
	 *
	 * @param pushNotificationsDevice the push notifications device
	 */
	@Override
	public void cacheResult(PushNotificationsDevice pushNotificationsDevice) {
		EntityCacheUtil.putResult(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsDeviceImpl.class,
			pushNotificationsDevice.getPrimaryKey(), pushNotificationsDevice);

		pushNotificationsDevice.resetOriginalValues();
	}

	/**
	 * Caches the push notifications devices in the entity cache if it is enabled.
	 *
	 * @param pushNotificationsDevices the push notifications devices
	 */
	@Override
	public void cacheResult(
		List<PushNotificationsDevice> pushNotificationsDevices) {
		for (PushNotificationsDevice pushNotificationsDevice : pushNotificationsDevices) {
			if (EntityCacheUtil.getResult(
						PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
						PushNotificationsDeviceImpl.class,
						pushNotificationsDevice.getPrimaryKey()) == null) {
				cacheResult(pushNotificationsDevice);
			}
			else {
				pushNotificationsDevice.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all push notifications devices.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(PushNotificationsDeviceImpl.class.getName());
		}

		EntityCacheUtil.clearCache(PushNotificationsDeviceImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the push notifications device.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PushNotificationsDevice pushNotificationsDevice) {
		EntityCacheUtil.removeResult(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsDeviceImpl.class,
			pushNotificationsDevice.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<PushNotificationsDevice> pushNotificationsDevices) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PushNotificationsDevice pushNotificationsDevice : pushNotificationsDevices) {
			EntityCacheUtil.removeResult(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
				PushNotificationsDeviceImpl.class,
				pushNotificationsDevice.getPrimaryKey());
		}
	}

	/**
	 * Creates a new push notifications device with the primary key. Does not add the push notifications device to the database.
	 *
	 * @param pushNotificationsDeviceId the primary key for the new push notifications device
	 * @return the new push notifications device
	 */
	@Override
	public PushNotificationsDevice create(long pushNotificationsDeviceId) {
		PushNotificationsDevice pushNotificationsDevice = new PushNotificationsDeviceImpl();

		pushNotificationsDevice.setNew(true);
		pushNotificationsDevice.setPrimaryKey(pushNotificationsDeviceId);

		return pushNotificationsDevice;
	}

	/**
	 * Removes the push notifications device with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param pushNotificationsDeviceId the primary key of the push notifications device
	 * @return the push notifications device that was removed
	 * @throws com.liferay.pushnotifications.NoSuchDeviceException if a push notifications device with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PushNotificationsDevice remove(long pushNotificationsDeviceId)
		throws NoSuchDeviceException, SystemException {
		return remove((Serializable)pushNotificationsDeviceId);
	}

	/**
	 * Removes the push notifications device with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the push notifications device
	 * @return the push notifications device that was removed
	 * @throws com.liferay.pushnotifications.NoSuchDeviceException if a push notifications device with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PushNotificationsDevice remove(Serializable primaryKey)
		throws NoSuchDeviceException, SystemException {
		Session session = null;

		try {
			session = openSession();

			PushNotificationsDevice pushNotificationsDevice = (PushNotificationsDevice)session.get(PushNotificationsDeviceImpl.class,
					primaryKey);

			if (pushNotificationsDevice == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDeviceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(pushNotificationsDevice);
		}
		catch (NoSuchDeviceException nsee) {
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
	protected PushNotificationsDevice removeImpl(
		PushNotificationsDevice pushNotificationsDevice)
		throws SystemException {
		pushNotificationsDevice = toUnwrappedModel(pushNotificationsDevice);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(pushNotificationsDevice)) {
				pushNotificationsDevice = (PushNotificationsDevice)session.get(PushNotificationsDeviceImpl.class,
						pushNotificationsDevice.getPrimaryKeyObj());
			}

			if (pushNotificationsDevice != null) {
				session.delete(pushNotificationsDevice);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (pushNotificationsDevice != null) {
			clearCache(pushNotificationsDevice);
		}

		return pushNotificationsDevice;
	}

	@Override
	public PushNotificationsDevice updateImpl(
		com.liferay.pushnotifications.model.PushNotificationsDevice pushNotificationsDevice)
		throws SystemException {
		pushNotificationsDevice = toUnwrappedModel(pushNotificationsDevice);

		boolean isNew = pushNotificationsDevice.isNew();

		Session session = null;

		try {
			session = openSession();

			if (pushNotificationsDevice.isNew()) {
				session.save(pushNotificationsDevice);

				pushNotificationsDevice.setNew(false);
			}
			else {
				session.merge(pushNotificationsDevice);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsDeviceImpl.class,
			pushNotificationsDevice.getPrimaryKey(), pushNotificationsDevice,
			false);

		pushNotificationsDevice.resetOriginalValues();

		return pushNotificationsDevice;
	}

	protected PushNotificationsDevice toUnwrappedModel(
		PushNotificationsDevice pushNotificationsDevice) {
		if (pushNotificationsDevice instanceof PushNotificationsDeviceImpl) {
			return pushNotificationsDevice;
		}

		PushNotificationsDeviceImpl pushNotificationsDeviceImpl = new PushNotificationsDeviceImpl();

		pushNotificationsDeviceImpl.setNew(pushNotificationsDevice.isNew());
		pushNotificationsDeviceImpl.setPrimaryKey(pushNotificationsDevice.getPrimaryKey());

		pushNotificationsDeviceImpl.setPushNotificationsDeviceId(pushNotificationsDevice.getPushNotificationsDeviceId());
		pushNotificationsDeviceImpl.setUserId(pushNotificationsDevice.getUserId());
		pushNotificationsDeviceImpl.setCreateDate(pushNotificationsDevice.getCreateDate());
		pushNotificationsDeviceImpl.setPlatform(pushNotificationsDevice.getPlatform());
		pushNotificationsDeviceImpl.setToken(pushNotificationsDevice.getToken());

		return pushNotificationsDeviceImpl;
	}

	/**
	 * Returns the push notifications device with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the push notifications device
	 * @return the push notifications device
	 * @throws com.liferay.pushnotifications.NoSuchDeviceException if a push notifications device with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PushNotificationsDevice findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDeviceException, SystemException {
		PushNotificationsDevice pushNotificationsDevice = fetchByPrimaryKey(primaryKey);

		if (pushNotificationsDevice == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDeviceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return pushNotificationsDevice;
	}

	/**
	 * Returns the push notifications device with the primary key or throws a {@link com.liferay.pushnotifications.NoSuchDeviceException} if it could not be found.
	 *
	 * @param pushNotificationsDeviceId the primary key of the push notifications device
	 * @return the push notifications device
	 * @throws com.liferay.pushnotifications.NoSuchDeviceException if a push notifications device with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PushNotificationsDevice findByPrimaryKey(
		long pushNotificationsDeviceId)
		throws NoSuchDeviceException, SystemException {
		return findByPrimaryKey((Serializable)pushNotificationsDeviceId);
	}

	/**
	 * Returns the push notifications device with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the push notifications device
	 * @return the push notifications device, or <code>null</code> if a push notifications device with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PushNotificationsDevice fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		PushNotificationsDevice pushNotificationsDevice = (PushNotificationsDevice)EntityCacheUtil.getResult(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
				PushNotificationsDeviceImpl.class, primaryKey);

		if (pushNotificationsDevice == _nullPushNotificationsDevice) {
			return null;
		}

		if (pushNotificationsDevice == null) {
			Session session = null;

			try {
				session = openSession();

				pushNotificationsDevice = (PushNotificationsDevice)session.get(PushNotificationsDeviceImpl.class,
						primaryKey);

				if (pushNotificationsDevice != null) {
					cacheResult(pushNotificationsDevice);
				}
				else {
					EntityCacheUtil.putResult(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
						PushNotificationsDeviceImpl.class, primaryKey,
						_nullPushNotificationsDevice);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
					PushNotificationsDeviceImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return pushNotificationsDevice;
	}

	/**
	 * Returns the push notifications device with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param pushNotificationsDeviceId the primary key of the push notifications device
	 * @return the push notifications device, or <code>null</code> if a push notifications device with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PushNotificationsDevice fetchByPrimaryKey(
		long pushNotificationsDeviceId) throws SystemException {
		return fetchByPrimaryKey((Serializable)pushNotificationsDeviceId);
	}

	/**
	 * Returns all the push notifications devices.
	 *
	 * @return the push notifications devices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PushNotificationsDevice> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the push notifications devices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of push notifications devices
	 * @param end the upper bound of the range of push notifications devices (not inclusive)
	 * @return the range of push notifications devices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PushNotificationsDevice> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the push notifications devices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of push notifications devices
	 * @param end the upper bound of the range of push notifications devices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of push notifications devices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<PushNotificationsDevice> findAll(int start, int end,
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

		List<PushNotificationsDevice> list = (List<PushNotificationsDevice>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PUSHNOTIFICATIONSDEVICE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PUSHNOTIFICATIONSDEVICE;

				if (pagination) {
					sql = sql.concat(PushNotificationsDeviceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PushNotificationsDevice>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PushNotificationsDevice>)QueryUtil.list(q,
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
	 * Removes all the push notifications devices from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (PushNotificationsDevice pushNotificationsDevice : findAll()) {
			remove(pushNotificationsDevice);
		}
	}

	/**
	 * Returns the number of push notifications devices.
	 *
	 * @return the number of push notifications devices
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

				Query q = session.createQuery(_SQL_COUNT_PUSHNOTIFICATIONSDEVICE);

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

	/**
	 * Initializes the push notifications device persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.pushnotifications.model.PushNotificationsDevice")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<PushNotificationsDevice>> listenersList = new ArrayList<ModelListener<PushNotificationsDevice>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<PushNotificationsDevice>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(PushNotificationsDeviceImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_PUSHNOTIFICATIONSDEVICE = "SELECT pushNotificationsDevice FROM PushNotificationsDevice pushNotificationsDevice";
	private static final String _SQL_COUNT_PUSHNOTIFICATIONSDEVICE = "SELECT COUNT(pushNotificationsDevice) FROM PushNotificationsDevice pushNotificationsDevice";
	private static final String _ORDER_BY_ENTITY_ALIAS = "pushNotificationsDevice.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PushNotificationsDevice exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(PushNotificationsDevicePersistenceImpl.class);
	private static PushNotificationsDevice _nullPushNotificationsDevice = new PushNotificationsDeviceImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<PushNotificationsDevice> toCacheModel() {
				return _nullPushNotificationsDeviceCacheModel;
			}
		};

	private static CacheModel<PushNotificationsDevice> _nullPushNotificationsDeviceCacheModel =
		new CacheModel<PushNotificationsDevice>() {
			@Override
			public PushNotificationsDevice toEntityModel() {
				return _nullPushNotificationsDevice;
			}
		};
}