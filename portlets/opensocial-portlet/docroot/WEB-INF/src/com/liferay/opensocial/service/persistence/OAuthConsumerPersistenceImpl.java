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

package com.liferay.opensocial.service.persistence;

import com.liferay.opensocial.NoSuchOAuthConsumerException;
import com.liferay.opensocial.model.OAuthConsumer;
import com.liferay.opensocial.model.impl.OAuthConsumerImpl;
import com.liferay.opensocial.model.impl.OAuthConsumerModelImpl;

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
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the o auth consumer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OAuthConsumerPersistence
 * @see OAuthConsumerUtil
 * @generated
 */
public class OAuthConsumerPersistenceImpl extends BasePersistenceImpl<OAuthConsumer>
	implements OAuthConsumerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OAuthConsumerUtil} to access the o auth consumer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OAuthConsumerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GADGETKEY =
		new FinderPath(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConsumerModelImpl.FINDER_CACHE_ENABLED,
			OAuthConsumerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGadgetKey",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GADGETKEY =
		new FinderPath(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConsumerModelImpl.FINDER_CACHE_ENABLED,
			OAuthConsumerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGadgetKey", new String[] { String.class.getName() },
			OAuthConsumerModelImpl.GADGETKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GADGETKEY = new FinderPath(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConsumerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGadgetKey",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_G_S = new FinderPath(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConsumerModelImpl.FINDER_CACHE_ENABLED,
			OAuthConsumerImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByG_S",
			new String[] { String.class.getName(), String.class.getName() },
			OAuthConsumerModelImpl.GADGETKEY_COLUMN_BITMASK |
			OAuthConsumerModelImpl.SERVICENAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_S = new FinderPath(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConsumerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_S",
			new String[] { String.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConsumerModelImpl.FINDER_CACHE_ENABLED,
			OAuthConsumerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConsumerModelImpl.FINDER_CACHE_ENABLED,
			OAuthConsumerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConsumerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the o auth consumer in the entity cache if it is enabled.
	 *
	 * @param oAuthConsumer the o auth consumer
	 */
	public void cacheResult(OAuthConsumer oAuthConsumer) {
		EntityCacheUtil.putResult(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConsumerImpl.class, oAuthConsumer.getPrimaryKey(),
			oAuthConsumer);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_S,
			new Object[] {
				oAuthConsumer.getGadgetKey(),
				
			oAuthConsumer.getServiceName()
			}, oAuthConsumer);

		oAuthConsumer.resetOriginalValues();
	}

	/**
	 * Caches the o auth consumers in the entity cache if it is enabled.
	 *
	 * @param oAuthConsumers the o auth consumers
	 */
	public void cacheResult(List<OAuthConsumer> oAuthConsumers) {
		for (OAuthConsumer oAuthConsumer : oAuthConsumers) {
			if (EntityCacheUtil.getResult(
						OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
						OAuthConsumerImpl.class, oAuthConsumer.getPrimaryKey()) == null) {
				cacheResult(oAuthConsumer);
			}
			else {
				oAuthConsumer.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all o auth consumers.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(OAuthConsumerImpl.class.getName());
		}

		EntityCacheUtil.clearCache(OAuthConsumerImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the o auth consumer.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OAuthConsumer oAuthConsumer) {
		EntityCacheUtil.removeResult(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConsumerImpl.class, oAuthConsumer.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(oAuthConsumer);
	}

	@Override
	public void clearCache(List<OAuthConsumer> oAuthConsumers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OAuthConsumer oAuthConsumer : oAuthConsumers) {
			EntityCacheUtil.removeResult(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
				OAuthConsumerImpl.class, oAuthConsumer.getPrimaryKey());

			clearUniqueFindersCache(oAuthConsumer);
		}
	}

	protected void clearUniqueFindersCache(OAuthConsumer oAuthConsumer) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_S,
			new Object[] {
				oAuthConsumer.getGadgetKey(),
				
			oAuthConsumer.getServiceName()
			});
	}

	/**
	 * Creates a new o auth consumer with the primary key. Does not add the o auth consumer to the database.
	 *
	 * @param oAuthConsumerId the primary key for the new o auth consumer
	 * @return the new o auth consumer
	 */
	public OAuthConsumer create(long oAuthConsumerId) {
		OAuthConsumer oAuthConsumer = new OAuthConsumerImpl();

		oAuthConsumer.setNew(true);
		oAuthConsumer.setPrimaryKey(oAuthConsumerId);

		return oAuthConsumer;
	}

	/**
	 * Removes the o auth consumer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param oAuthConsumerId the primary key of the o auth consumer
	 * @return the o auth consumer that was removed
	 * @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a o auth consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConsumer remove(long oAuthConsumerId)
		throws NoSuchOAuthConsumerException, SystemException {
		return remove(Long.valueOf(oAuthConsumerId));
	}

	/**
	 * Removes the o auth consumer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the o auth consumer
	 * @return the o auth consumer that was removed
	 * @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a o auth consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthConsumer remove(Serializable primaryKey)
		throws NoSuchOAuthConsumerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			OAuthConsumer oAuthConsumer = (OAuthConsumer)session.get(OAuthConsumerImpl.class,
					primaryKey);

			if (oAuthConsumer == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOAuthConsumerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(oAuthConsumer);
		}
		catch (NoSuchOAuthConsumerException nsee) {
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
	protected OAuthConsumer removeImpl(OAuthConsumer oAuthConsumer)
		throws SystemException {
		oAuthConsumer = toUnwrappedModel(oAuthConsumer);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, oAuthConsumer);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(oAuthConsumer);

		return oAuthConsumer;
	}

	@Override
	public OAuthConsumer updateImpl(
		com.liferay.opensocial.model.OAuthConsumer oAuthConsumer, boolean merge)
		throws SystemException {
		oAuthConsumer = toUnwrappedModel(oAuthConsumer);

		boolean isNew = oAuthConsumer.isNew();

		OAuthConsumerModelImpl oAuthConsumerModelImpl = (OAuthConsumerModelImpl)oAuthConsumer;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, oAuthConsumer, merge);

			oAuthConsumer.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !OAuthConsumerModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((oAuthConsumerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GADGETKEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						oAuthConsumerModelImpl.getOriginalGadgetKey()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GADGETKEY,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GADGETKEY,
					args);

				args = new Object[] { oAuthConsumerModelImpl.getGadgetKey() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GADGETKEY,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GADGETKEY,
					args);
			}
		}

		EntityCacheUtil.putResult(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConsumerImpl.class, oAuthConsumer.getPrimaryKey(),
			oAuthConsumer);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_S,
				new Object[] {
					oAuthConsumer.getGadgetKey(),
					
				oAuthConsumer.getServiceName()
				}, oAuthConsumer);
		}
		else {
			if ((oAuthConsumerModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_G_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						oAuthConsumerModelImpl.getOriginalGadgetKey(),
						
						oAuthConsumerModelImpl.getOriginalServiceName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_S, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_S,
					new Object[] {
						oAuthConsumer.getGadgetKey(),
						
					oAuthConsumer.getServiceName()
					}, oAuthConsumer);
			}
		}

		return oAuthConsumer;
	}

	protected OAuthConsumer toUnwrappedModel(OAuthConsumer oAuthConsumer) {
		if (oAuthConsumer instanceof OAuthConsumerImpl) {
			return oAuthConsumer;
		}

		OAuthConsumerImpl oAuthConsumerImpl = new OAuthConsumerImpl();

		oAuthConsumerImpl.setNew(oAuthConsumer.isNew());
		oAuthConsumerImpl.setPrimaryKey(oAuthConsumer.getPrimaryKey());

		oAuthConsumerImpl.setOAuthConsumerId(oAuthConsumer.getOAuthConsumerId());
		oAuthConsumerImpl.setCompanyId(oAuthConsumer.getCompanyId());
		oAuthConsumerImpl.setCreateDate(oAuthConsumer.getCreateDate());
		oAuthConsumerImpl.setModifiedDate(oAuthConsumer.getModifiedDate());
		oAuthConsumerImpl.setGadgetKey(oAuthConsumer.getGadgetKey());
		oAuthConsumerImpl.setServiceName(oAuthConsumer.getServiceName());
		oAuthConsumerImpl.setConsumerKey(oAuthConsumer.getConsumerKey());
		oAuthConsumerImpl.setConsumerSecret(oAuthConsumer.getConsumerSecret());
		oAuthConsumerImpl.setKeyType(oAuthConsumer.getKeyType());

		return oAuthConsumerImpl;
	}

	/**
	 * Returns the o auth consumer with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the o auth consumer
	 * @return the o auth consumer
	 * @throws com.liferay.portal.NoSuchModelException if a o auth consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthConsumer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the o auth consumer with the primary key or throws a {@link com.liferay.opensocial.NoSuchOAuthConsumerException} if it could not be found.
	 *
	 * @param oAuthConsumerId the primary key of the o auth consumer
	 * @return the o auth consumer
	 * @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a o auth consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConsumer findByPrimaryKey(long oAuthConsumerId)
		throws NoSuchOAuthConsumerException, SystemException {
		OAuthConsumer oAuthConsumer = fetchByPrimaryKey(oAuthConsumerId);

		if (oAuthConsumer == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + oAuthConsumerId);
			}

			throw new NoSuchOAuthConsumerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				oAuthConsumerId);
		}

		return oAuthConsumer;
	}

	/**
	 * Returns the o auth consumer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the o auth consumer
	 * @return the o auth consumer, or <code>null</code> if a o auth consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthConsumer fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the o auth consumer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param oAuthConsumerId the primary key of the o auth consumer
	 * @return the o auth consumer, or <code>null</code> if a o auth consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConsumer fetchByPrimaryKey(long oAuthConsumerId)
		throws SystemException {
		OAuthConsumer oAuthConsumer = (OAuthConsumer)EntityCacheUtil.getResult(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
				OAuthConsumerImpl.class, oAuthConsumerId);

		if (oAuthConsumer == _nullOAuthConsumer) {
			return null;
		}

		if (oAuthConsumer == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				oAuthConsumer = (OAuthConsumer)session.get(OAuthConsumerImpl.class,
						Long.valueOf(oAuthConsumerId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (oAuthConsumer != null) {
					cacheResult(oAuthConsumer);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
						OAuthConsumerImpl.class, oAuthConsumerId,
						_nullOAuthConsumer);
				}

				closeSession(session);
			}
		}

		return oAuthConsumer;
	}

	/**
	 * Returns all the o auth consumers where gadgetKey = &#63;.
	 *
	 * @param gadgetKey the gadget key
	 * @return the matching o auth consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthConsumer> findByGadgetKey(String gadgetKey)
		throws SystemException {
		return findByGadgetKey(gadgetKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the o auth consumers where gadgetKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param gadgetKey the gadget key
	 * @param start the lower bound of the range of o auth consumers
	 * @param end the upper bound of the range of o auth consumers (not inclusive)
	 * @return the range of matching o auth consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthConsumer> findByGadgetKey(String gadgetKey, int start,
		int end) throws SystemException {
		return findByGadgetKey(gadgetKey, start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth consumers where gadgetKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param gadgetKey the gadget key
	 * @param start the lower bound of the range of o auth consumers
	 * @param end the upper bound of the range of o auth consumers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching o auth consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthConsumer> findByGadgetKey(String gadgetKey, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GADGETKEY;
			finderArgs = new Object[] { gadgetKey };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GADGETKEY;
			finderArgs = new Object[] { gadgetKey, start, end, orderByComparator };
		}

		List<OAuthConsumer> list = (List<OAuthConsumer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OAuthConsumer oAuthConsumer : list) {
				if (!Validator.equals(gadgetKey, oAuthConsumer.getGadgetKey())) {
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

			query.append(_SQL_SELECT_OAUTHCONSUMER_WHERE);

			if (gadgetKey == null) {
				query.append(_FINDER_COLUMN_GADGETKEY_GADGETKEY_1);
			}
			else {
				if (gadgetKey.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_GADGETKEY_GADGETKEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_GADGETKEY_GADGETKEY_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(OAuthConsumerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (gadgetKey != null) {
					qPos.add(gadgetKey);
				}

				list = (List<OAuthConsumer>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first o auth consumer in the ordered set where gadgetKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param gadgetKey the gadget key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching o auth consumer
	 * @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a matching o auth consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConsumer findByGadgetKey_First(String gadgetKey,
		OrderByComparator orderByComparator)
		throws NoSuchOAuthConsumerException, SystemException {
		List<OAuthConsumer> list = findByGadgetKey(gadgetKey, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("gadgetKey=");
			msg.append(gadgetKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchOAuthConsumerException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last o auth consumer in the ordered set where gadgetKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param gadgetKey the gadget key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching o auth consumer
	 * @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a matching o auth consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConsumer findByGadgetKey_Last(String gadgetKey,
		OrderByComparator orderByComparator)
		throws NoSuchOAuthConsumerException, SystemException {
		int count = countByGadgetKey(gadgetKey);

		List<OAuthConsumer> list = findByGadgetKey(gadgetKey, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("gadgetKey=");
			msg.append(gadgetKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchOAuthConsumerException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the o auth consumers before and after the current o auth consumer in the ordered set where gadgetKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param oAuthConsumerId the primary key of the current o auth consumer
	 * @param gadgetKey the gadget key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next o auth consumer
	 * @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a o auth consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConsumer[] findByGadgetKey_PrevAndNext(long oAuthConsumerId,
		String gadgetKey, OrderByComparator orderByComparator)
		throws NoSuchOAuthConsumerException, SystemException {
		OAuthConsumer oAuthConsumer = findByPrimaryKey(oAuthConsumerId);

		Session session = null;

		try {
			session = openSession();

			OAuthConsumer[] array = new OAuthConsumerImpl[3];

			array[0] = getByGadgetKey_PrevAndNext(session, oAuthConsumer,
					gadgetKey, orderByComparator, true);

			array[1] = oAuthConsumer;

			array[2] = getByGadgetKey_PrevAndNext(session, oAuthConsumer,
					gadgetKey, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OAuthConsumer getByGadgetKey_PrevAndNext(Session session,
		OAuthConsumer oAuthConsumer, String gadgetKey,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OAUTHCONSUMER_WHERE);

		if (gadgetKey == null) {
			query.append(_FINDER_COLUMN_GADGETKEY_GADGETKEY_1);
		}
		else {
			if (gadgetKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GADGETKEY_GADGETKEY_3);
			}
			else {
				query.append(_FINDER_COLUMN_GADGETKEY_GADGETKEY_2);
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

		else {
			query.append(OAuthConsumerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (gadgetKey != null) {
			qPos.add(gadgetKey);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(oAuthConsumer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OAuthConsumer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the o auth consumer where gadgetKey = &#63; and serviceName = &#63; or throws a {@link com.liferay.opensocial.NoSuchOAuthConsumerException} if it could not be found.
	 *
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @return the matching o auth consumer
	 * @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a matching o auth consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConsumer findByG_S(String gadgetKey, String serviceName)
		throws NoSuchOAuthConsumerException, SystemException {
		OAuthConsumer oAuthConsumer = fetchByG_S(gadgetKey, serviceName);

		if (oAuthConsumer == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("gadgetKey=");
			msg.append(gadgetKey);

			msg.append(", serviceName=");
			msg.append(serviceName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchOAuthConsumerException(msg.toString());
		}

		return oAuthConsumer;
	}

	/**
	 * Returns the o auth consumer where gadgetKey = &#63; and serviceName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @return the matching o auth consumer, or <code>null</code> if a matching o auth consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConsumer fetchByG_S(String gadgetKey, String serviceName)
		throws SystemException {
		return fetchByG_S(gadgetKey, serviceName, true);
	}

	/**
	 * Returns the o auth consumer where gadgetKey = &#63; and serviceName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching o auth consumer, or <code>null</code> if a matching o auth consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConsumer fetchByG_S(String gadgetKey, String serviceName,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { gadgetKey, serviceName };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_S,
					finderArgs, this);
		}

		if (result instanceof OAuthConsumer) {
			OAuthConsumer oAuthConsumer = (OAuthConsumer)result;

			if (!Validator.equals(gadgetKey, oAuthConsumer.getGadgetKey()) ||
					!Validator.equals(serviceName,
						oAuthConsumer.getServiceName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_OAUTHCONSUMER_WHERE);

			if (gadgetKey == null) {
				query.append(_FINDER_COLUMN_G_S_GADGETKEY_1);
			}
			else {
				if (gadgetKey.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_S_GADGETKEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_S_GADGETKEY_2);
				}
			}

			if (serviceName == null) {
				query.append(_FINDER_COLUMN_G_S_SERVICENAME_1);
			}
			else {
				if (serviceName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_S_SERVICENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_S_SERVICENAME_2);
				}
			}

			query.append(OAuthConsumerModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (gadgetKey != null) {
					qPos.add(gadgetKey);
				}

				if (serviceName != null) {
					qPos.add(serviceName);
				}

				List<OAuthConsumer> list = q.list();

				result = list;

				OAuthConsumer oAuthConsumer = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_S,
						finderArgs, list);
				}
				else {
					oAuthConsumer = list.get(0);

					cacheResult(oAuthConsumer);

					if ((oAuthConsumer.getGadgetKey() == null) ||
							!oAuthConsumer.getGadgetKey().equals(gadgetKey) ||
							(oAuthConsumer.getServiceName() == null) ||
							!oAuthConsumer.getServiceName().equals(serviceName)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_S,
							finderArgs, oAuthConsumer);
					}
				}

				return oAuthConsumer;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_S,
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
				return (OAuthConsumer)result;
			}
		}
	}

	/**
	 * Returns all the o auth consumers.
	 *
	 * @return the o auth consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthConsumer> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the o auth consumers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of o auth consumers
	 * @param end the upper bound of the range of o auth consumers (not inclusive)
	 * @return the range of o auth consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthConsumer> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth consumers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of o auth consumers
	 * @param end the upper bound of the range of o auth consumers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of o auth consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthConsumer> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<OAuthConsumer> list = (List<OAuthConsumer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_OAUTHCONSUMER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OAUTHCONSUMER.concat(OAuthConsumerModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<OAuthConsumer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<OAuthConsumer>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the o auth consumers where gadgetKey = &#63; from the database.
	 *
	 * @param gadgetKey the gadget key
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGadgetKey(String gadgetKey) throws SystemException {
		for (OAuthConsumer oAuthConsumer : findByGadgetKey(gadgetKey)) {
			remove(oAuthConsumer);
		}
	}

	/**
	 * Removes the o auth consumer where gadgetKey = &#63; and serviceName = &#63; from the database.
	 *
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_S(String gadgetKey, String serviceName)
		throws NoSuchOAuthConsumerException, SystemException {
		OAuthConsumer oAuthConsumer = findByG_S(gadgetKey, serviceName);

		remove(oAuthConsumer);
	}

	/**
	 * Removes all the o auth consumers from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (OAuthConsumer oAuthConsumer : findAll()) {
			remove(oAuthConsumer);
		}
	}

	/**
	 * Returns the number of o auth consumers where gadgetKey = &#63;.
	 *
	 * @param gadgetKey the gadget key
	 * @return the number of matching o auth consumers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGadgetKey(String gadgetKey) throws SystemException {
		Object[] finderArgs = new Object[] { gadgetKey };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GADGETKEY,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OAUTHCONSUMER_WHERE);

			if (gadgetKey == null) {
				query.append(_FINDER_COLUMN_GADGETKEY_GADGETKEY_1);
			}
			else {
				if (gadgetKey.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_GADGETKEY_GADGETKEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_GADGETKEY_GADGETKEY_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (gadgetKey != null) {
					qPos.add(gadgetKey);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GADGETKEY,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of o auth consumers where gadgetKey = &#63; and serviceName = &#63;.
	 *
	 * @param gadgetKey the gadget key
	 * @param serviceName the service name
	 * @return the number of matching o auth consumers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_S(String gadgetKey, String serviceName)
		throws SystemException {
		Object[] finderArgs = new Object[] { gadgetKey, serviceName };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OAUTHCONSUMER_WHERE);

			if (gadgetKey == null) {
				query.append(_FINDER_COLUMN_G_S_GADGETKEY_1);
			}
			else {
				if (gadgetKey.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_S_GADGETKEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_S_GADGETKEY_2);
				}
			}

			if (serviceName == null) {
				query.append(_FINDER_COLUMN_G_S_SERVICENAME_1);
			}
			else {
				if (serviceName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_S_SERVICENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_S_SERVICENAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (gadgetKey != null) {
					qPos.add(gadgetKey);
				}

				if (serviceName != null) {
					qPos.add(serviceName);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_S, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of o auth consumers.
	 *
	 * @return the number of o auth consumers
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OAUTHCONSUMER);

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
	 * Initializes the o auth consumer persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.opensocial.model.OAuthConsumer")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<OAuthConsumer>> listenersList = new ArrayList<ModelListener<OAuthConsumer>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<OAuthConsumer>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(OAuthConsumerImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = GadgetPersistence.class)
	protected GadgetPersistence gadgetPersistence;
	@BeanReference(type = OAuthConsumerPersistence.class)
	protected OAuthConsumerPersistence oAuthConsumerPersistence;
	@BeanReference(type = OAuthTokenPersistence.class)
	protected OAuthTokenPersistence oAuthTokenPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_OAUTHCONSUMER = "SELECT oAuthConsumer FROM OAuthConsumer oAuthConsumer";
	private static final String _SQL_SELECT_OAUTHCONSUMER_WHERE = "SELECT oAuthConsumer FROM OAuthConsumer oAuthConsumer WHERE ";
	private static final String _SQL_COUNT_OAUTHCONSUMER = "SELECT COUNT(oAuthConsumer) FROM OAuthConsumer oAuthConsumer";
	private static final String _SQL_COUNT_OAUTHCONSUMER_WHERE = "SELECT COUNT(oAuthConsumer) FROM OAuthConsumer oAuthConsumer WHERE ";
	private static final String _FINDER_COLUMN_GADGETKEY_GADGETKEY_1 = "oAuthConsumer.gadgetKey IS NULL";
	private static final String _FINDER_COLUMN_GADGETKEY_GADGETKEY_2 = "oAuthConsumer.gadgetKey = ?";
	private static final String _FINDER_COLUMN_GADGETKEY_GADGETKEY_3 = "(oAuthConsumer.gadgetKey IS NULL OR oAuthConsumer.gadgetKey = ?)";
	private static final String _FINDER_COLUMN_G_S_GADGETKEY_1 = "oAuthConsumer.gadgetKey IS NULL AND ";
	private static final String _FINDER_COLUMN_G_S_GADGETKEY_2 = "oAuthConsumer.gadgetKey = ? AND ";
	private static final String _FINDER_COLUMN_G_S_GADGETKEY_3 = "(oAuthConsumer.gadgetKey IS NULL OR oAuthConsumer.gadgetKey = ?) AND ";
	private static final String _FINDER_COLUMN_G_S_SERVICENAME_1 = "oAuthConsumer.serviceName IS NULL";
	private static final String _FINDER_COLUMN_G_S_SERVICENAME_2 = "oAuthConsumer.serviceName = ?";
	private static final String _FINDER_COLUMN_G_S_SERVICENAME_3 = "(oAuthConsumer.serviceName IS NULL OR oAuthConsumer.serviceName = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "oAuthConsumer.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OAuthConsumer exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OAuthConsumer exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(OAuthConsumerPersistenceImpl.class);
	private static OAuthConsumer _nullOAuthConsumer = new OAuthConsumerImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<OAuthConsumer> toCacheModel() {
				return _nullOAuthConsumerCacheModel;
			}
		};

	private static CacheModel<OAuthConsumer> _nullOAuthConsumerCacheModel = new CacheModel<OAuthConsumer>() {
			public OAuthConsumer toEntityModel() {
				return _nullOAuthConsumer;
			}
		};
}