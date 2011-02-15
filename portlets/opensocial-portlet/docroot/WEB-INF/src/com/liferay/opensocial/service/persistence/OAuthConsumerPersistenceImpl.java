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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
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
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_GADGETID = new FinderPath(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConsumerModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByGadgetId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_GADGETID = new FinderPath(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConsumerModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByGadgetId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_G_S = new FinderPath(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConsumerModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_S",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_G_S = new FinderPath(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConsumerModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByG_S",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConsumerModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConsumerModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the o auth consumer in the entity cache if it is enabled.
	 *
	 * @param oAuthConsumer the o auth consumer to cache
	 */
	public void cacheResult(OAuthConsumer oAuthConsumer) {
		EntityCacheUtil.putResult(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConsumerImpl.class, oAuthConsumer.getPrimaryKey(),
			oAuthConsumer);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_S,
			new Object[] {
				new Long(oAuthConsumer.getGadgetId()),
				
			oAuthConsumer.getServiceName()
			}, oAuthConsumer);
	}

	/**
	 * Caches the o auth consumers in the entity cache if it is enabled.
	 *
	 * @param oAuthConsumers the o auth consumers to cache
	 */
	public void cacheResult(List<OAuthConsumer> oAuthConsumers) {
		for (OAuthConsumer oAuthConsumer : oAuthConsumers) {
			if (EntityCacheUtil.getResult(
						OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
						OAuthConsumerImpl.class, oAuthConsumer.getPrimaryKey(),
						this) == null) {
				cacheResult(oAuthConsumer);
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
	public void clearCache() {
		CacheRegistryUtil.clear(OAuthConsumerImpl.class.getName());
		EntityCacheUtil.clearCache(OAuthConsumerImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the o auth consumer.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(OAuthConsumer oAuthConsumer) {
		EntityCacheUtil.removeResult(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConsumerImpl.class, oAuthConsumer.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_S,
			new Object[] {
				new Long(oAuthConsumer.getGadgetId()),
				
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
	 * @param primaryKey the primary key of the o auth consumer to remove
	 * @return the o auth consumer that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a o auth consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConsumer remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the o auth consumer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param oAuthConsumerId the primary key of the o auth consumer to remove
	 * @return the o auth consumer that was removed
	 * @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a o auth consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConsumer remove(long oAuthConsumerId)
		throws NoSuchOAuthConsumerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			OAuthConsumer oAuthConsumer = (OAuthConsumer)session.get(OAuthConsumerImpl.class,
					new Long(oAuthConsumerId));

			if (oAuthConsumer == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						oAuthConsumerId);
				}

				throw new NoSuchOAuthConsumerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					oAuthConsumerId);
			}

			return oAuthConsumerPersistence.remove(oAuthConsumer);
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

	/**
	 * Removes the o auth consumer from the database. Also notifies the appropriate model listeners.
	 *
	 * @param oAuthConsumer the o auth consumer to remove
	 * @return the o auth consumer that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConsumer remove(OAuthConsumer oAuthConsumer)
		throws SystemException {
		return super.remove(oAuthConsumer);
	}

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

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		OAuthConsumerModelImpl oAuthConsumerModelImpl = (OAuthConsumerModelImpl)oAuthConsumer;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_S,
			new Object[] {
				new Long(oAuthConsumerModelImpl.getGadgetId()),
				
			oAuthConsumerModelImpl.getServiceName()
			});

		EntityCacheUtil.removeResult(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConsumerImpl.class, oAuthConsumer.getPrimaryKey());

		return oAuthConsumer;
	}

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

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConsumerImpl.class, oAuthConsumer.getPrimaryKey(),
			oAuthConsumer);

		if (!isNew &&
				((oAuthConsumer.getGadgetId() != oAuthConsumerModelImpl.getOriginalGadgetId()) ||
				!Validator.equals(oAuthConsumer.getServiceName(),
					oAuthConsumerModelImpl.getOriginalServiceName()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_S,
				new Object[] {
					new Long(oAuthConsumerModelImpl.getOriginalGadgetId()),
					
				oAuthConsumerModelImpl.getOriginalServiceName()
				});
		}

		if (isNew ||
				((oAuthConsumer.getGadgetId() != oAuthConsumerModelImpl.getOriginalGadgetId()) ||
				!Validator.equals(oAuthConsumer.getServiceName(),
					oAuthConsumerModelImpl.getOriginalServiceName()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_S,
				new Object[] {
					new Long(oAuthConsumer.getGadgetId()),
					
				oAuthConsumer.getServiceName()
				}, oAuthConsumer);
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
		oAuthConsumerImpl.setGadgetId(oAuthConsumer.getGadgetId());
		oAuthConsumerImpl.setServiceName(oAuthConsumer.getServiceName());
		oAuthConsumerImpl.setConsumerKey(oAuthConsumer.getConsumerKey());
		oAuthConsumerImpl.setConsumerSecret(oAuthConsumer.getConsumerSecret());
		oAuthConsumerImpl.setKeyType(oAuthConsumer.getKeyType());

		return oAuthConsumerImpl;
	}

	/**
	 * Finds the o auth consumer with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the o auth consumer to find
	 * @return the o auth consumer
	 * @throws com.liferay.portal.NoSuchModelException if a o auth consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConsumer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the o auth consumer with the primary key or throws a {@link com.liferay.opensocial.NoSuchOAuthConsumerException} if it could not be found.
	 *
	 * @param oAuthConsumerId the primary key of the o auth consumer to find
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
	 * Finds the o auth consumer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the o auth consumer to find
	 * @return the o auth consumer, or <code>null</code> if a o auth consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConsumer fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the o auth consumer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param oAuthConsumerId the primary key of the o auth consumer to find
	 * @return the o auth consumer, or <code>null</code> if a o auth consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConsumer fetchByPrimaryKey(long oAuthConsumerId)
		throws SystemException {
		OAuthConsumer oAuthConsumer = (OAuthConsumer)EntityCacheUtil.getResult(OAuthConsumerModelImpl.ENTITY_CACHE_ENABLED,
				OAuthConsumerImpl.class, oAuthConsumerId, this);

		if (oAuthConsumer == null) {
			Session session = null;

			try {
				session = openSession();

				oAuthConsumer = (OAuthConsumer)session.get(OAuthConsumerImpl.class,
						new Long(oAuthConsumerId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (oAuthConsumer != null) {
					cacheResult(oAuthConsumer);
				}

				closeSession(session);
			}
		}

		return oAuthConsumer;
	}

	/**
	 * Finds all the o auth consumers where gadgetId = &#63;.
	 *
	 * @param gadgetId the gadget ID to search with
	 * @return the matching o auth consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthConsumer> findByGadgetId(long gadgetId)
		throws SystemException {
		return findByGadgetId(gadgetId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Finds a range of all the o auth consumers where gadgetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param gadgetId the gadget ID to search with
	 * @param start the lower bound of the range of o auth consumers to return
	 * @param end the upper bound of the range of o auth consumers to return (not inclusive)
	 * @return the range of matching o auth consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthConsumer> findByGadgetId(long gadgetId, int start, int end)
		throws SystemException {
		return findByGadgetId(gadgetId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the o auth consumers where gadgetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param gadgetId the gadget ID to search with
	 * @param start the lower bound of the range of o auth consumers to return
	 * @param end the upper bound of the range of o auth consumers to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching o auth consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthConsumer> findByGadgetId(long gadgetId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				gadgetId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<OAuthConsumer> list = (List<OAuthConsumer>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_GADGETID,
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

			query.append(_SQL_SELECT_OAUTHCONSUMER_WHERE);

			query.append(_FINDER_COLUMN_GADGETID_GADGETID_2);

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

				qPos.add(gadgetId);

				list = (List<OAuthConsumer>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_GADGETID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_GADGETID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first o auth consumer in the ordered set where gadgetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param gadgetId the gadget ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching o auth consumer
	 * @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a matching o auth consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConsumer findByGadgetId_First(long gadgetId,
		OrderByComparator orderByComparator)
		throws NoSuchOAuthConsumerException, SystemException {
		List<OAuthConsumer> list = findByGadgetId(gadgetId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("gadgetId=");
			msg.append(gadgetId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchOAuthConsumerException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last o auth consumer in the ordered set where gadgetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param gadgetId the gadget ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching o auth consumer
	 * @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a matching o auth consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConsumer findByGadgetId_Last(long gadgetId,
		OrderByComparator orderByComparator)
		throws NoSuchOAuthConsumerException, SystemException {
		int count = countByGadgetId(gadgetId);

		List<OAuthConsumer> list = findByGadgetId(gadgetId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("gadgetId=");
			msg.append(gadgetId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchOAuthConsumerException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the o auth consumers before and after the current o auth consumer in the ordered set where gadgetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param oAuthConsumerId the primary key of the current o auth consumer
	 * @param gadgetId the gadget ID to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next o auth consumer
	 * @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a o auth consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConsumer[] findByGadgetId_PrevAndNext(long oAuthConsumerId,
		long gadgetId, OrderByComparator orderByComparator)
		throws NoSuchOAuthConsumerException, SystemException {
		OAuthConsumer oAuthConsumer = findByPrimaryKey(oAuthConsumerId);

		Session session = null;

		try {
			session = openSession();

			OAuthConsumer[] array = new OAuthConsumerImpl[3];

			array[0] = getByGadgetId_PrevAndNext(session, oAuthConsumer,
					gadgetId, orderByComparator, true);

			array[1] = oAuthConsumer;

			array[2] = getByGadgetId_PrevAndNext(session, oAuthConsumer,
					gadgetId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OAuthConsumer getByGadgetId_PrevAndNext(Session session,
		OAuthConsumer oAuthConsumer, long gadgetId,
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

		query.append(_FINDER_COLUMN_GADGETID_GADGETID_2);

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
			query.append(OAuthConsumerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(gadgetId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(oAuthConsumer);

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
	 * Finds the o auth consumer where gadgetId = &#63; and serviceName = &#63; or throws a {@link com.liferay.opensocial.NoSuchOAuthConsumerException} if it could not be found.
	 *
	 * @param gadgetId the gadget ID to search with
	 * @param serviceName the service name to search with
	 * @return the matching o auth consumer
	 * @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a matching o auth consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConsumer findByG_S(long gadgetId, String serviceName)
		throws NoSuchOAuthConsumerException, SystemException {
		OAuthConsumer oAuthConsumer = fetchByG_S(gadgetId, serviceName);

		if (oAuthConsumer == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("gadgetId=");
			msg.append(gadgetId);

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
	 * Finds the o auth consumer where gadgetId = &#63; and serviceName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param gadgetId the gadget ID to search with
	 * @param serviceName the service name to search with
	 * @return the matching o auth consumer, or <code>null</code> if a matching o auth consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConsumer fetchByG_S(long gadgetId, String serviceName)
		throws SystemException {
		return fetchByG_S(gadgetId, serviceName, true);
	}

	/**
	 * Finds the o auth consumer where gadgetId = &#63; and serviceName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param gadgetId the gadget ID to search with
	 * @param serviceName the service name to search with
	 * @return the matching o auth consumer, or <code>null</code> if a matching o auth consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConsumer fetchByG_S(long gadgetId, String serviceName,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { gadgetId, serviceName };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_S,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_OAUTHCONSUMER_WHERE);

			query.append(_FINDER_COLUMN_G_S_GADGETID_2);

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

				qPos.add(gadgetId);

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

					if ((oAuthConsumer.getGadgetId() != gadgetId) ||
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
	 * Finds all the o auth consumers.
	 *
	 * @return the o auth consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthConsumer> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the o auth consumers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of o auth consumers to return
	 * @param end the upper bound of the range of o auth consumers to return (not inclusive)
	 * @return the range of o auth consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthConsumer> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the o auth consumers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of o auth consumers to return
	 * @param end the upper bound of the range of o auth consumers to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of o auth consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthConsumer> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<OAuthConsumer> list = (List<OAuthConsumer>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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
	 * Removes all the o auth consumers where gadgetId = &#63; from the database.
	 *
	 * @param gadgetId the gadget ID to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGadgetId(long gadgetId) throws SystemException {
		for (OAuthConsumer oAuthConsumer : findByGadgetId(gadgetId)) {
			oAuthConsumerPersistence.remove(oAuthConsumer);
		}
	}

	/**
	 * Removes the o auth consumer where gadgetId = &#63; and serviceName = &#63; from the database.
	 *
	 * @param gadgetId the gadget ID to search with
	 * @param serviceName the service name to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_S(long gadgetId, String serviceName)
		throws NoSuchOAuthConsumerException, SystemException {
		OAuthConsumer oAuthConsumer = findByG_S(gadgetId, serviceName);

		oAuthConsumerPersistence.remove(oAuthConsumer);
	}

	/**
	 * Removes all the o auth consumers from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (OAuthConsumer oAuthConsumer : findAll()) {
			oAuthConsumerPersistence.remove(oAuthConsumer);
		}
	}

	/**
	 * Counts all the o auth consumers where gadgetId = &#63;.
	 *
	 * @param gadgetId the gadget ID to search with
	 * @return the number of matching o auth consumers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGadgetId(long gadgetId) throws SystemException {
		Object[] finderArgs = new Object[] { gadgetId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GADGETID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OAUTHCONSUMER_WHERE);

			query.append(_FINDER_COLUMN_GADGETID_GADGETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(gadgetId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GADGETID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the o auth consumers where gadgetId = &#63; and serviceName = &#63;.
	 *
	 * @param gadgetId the gadget ID to search with
	 * @param serviceName the service name to search with
	 * @return the number of matching o auth consumers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_S(long gadgetId, String serviceName)
		throws SystemException {
		Object[] finderArgs = new Object[] { gadgetId, serviceName };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OAUTHCONSUMER_WHERE);

			query.append(_FINDER_COLUMN_G_S_GADGETID_2);

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

				qPos.add(gadgetId);

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
	 * Counts all the o auth consumers.
	 *
	 * @return the number of o auth consumers
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

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
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
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
	private static final String _FINDER_COLUMN_GADGETID_GADGETID_2 = "oAuthConsumer.gadgetId = ?";
	private static final String _FINDER_COLUMN_G_S_GADGETID_2 = "oAuthConsumer.gadgetId = ? AND ";
	private static final String _FINDER_COLUMN_G_S_SERVICENAME_1 = "oAuthConsumer.serviceName IS NULL";
	private static final String _FINDER_COLUMN_G_S_SERVICENAME_2 = "oAuthConsumer.serviceName = ?";
	private static final String _FINDER_COLUMN_G_S_SERVICENAME_3 = "(oAuthConsumer.serviceName IS NULL OR oAuthConsumer.serviceName = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "oAuthConsumer.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OAuthConsumer exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OAuthConsumer exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(OAuthConsumerPersistenceImpl.class);
}