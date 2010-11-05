/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

import com.liferay.opensocial.NoSuchGadgetException;
import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.model.impl.GadgetImpl;
import com.liferay.opensocial.model.impl.GadgetModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.annotation.BeanReference;
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
 * The persistence implementation for the gadget service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GadgetPersistence
 * @see GadgetUtil
 * @generated
 */
public class GadgetPersistenceImpl extends BasePersistenceImpl<Gadget>
	implements GadgetPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link GadgetUtil} to access the gadget persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = GadgetImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(GadgetModelImpl.ENTITY_CACHE_ENABLED,
			GadgetModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(GadgetModelImpl.ENTITY_CACHE_ENABLED,
			GadgetModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByCompanyId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(GadgetModelImpl.ENTITY_CACHE_ENABLED,
			GadgetModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GadgetModelImpl.ENTITY_CACHE_ENABLED,
			GadgetModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	/**
	 * Caches the gadget in the entity cache if it is enabled.
	 *
	 * @param gadget the gadget to cache
	 */
	public void cacheResult(Gadget gadget) {
		EntityCacheUtil.putResult(GadgetModelImpl.ENTITY_CACHE_ENABLED,
			GadgetImpl.class, gadget.getPrimaryKey(), gadget);
	}

	/**
	 * Caches the gadgets in the entity cache if it is enabled.
	 *
	 * @param gadgets the gadgets to cache
	 */
	public void cacheResult(List<Gadget> gadgets) {
		for (Gadget gadget : gadgets) {
			if (EntityCacheUtil.getResult(
						GadgetModelImpl.ENTITY_CACHE_ENABLED, GadgetImpl.class,
						gadget.getPrimaryKey(), this) == null) {
				cacheResult(gadget);
			}
		}
	}

	/**
	 * Clears the cache for all gadgets.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(GadgetImpl.class.getName());
		EntityCacheUtil.clearCache(GadgetImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the gadget.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(Gadget gadget) {
		EntityCacheUtil.removeResult(GadgetModelImpl.ENTITY_CACHE_ENABLED,
			GadgetImpl.class, gadget.getPrimaryKey());
	}

	/**
	 * Creates a new gadget with the primary key. Does not add the gadget to the database.
	 *
	 * @param gadgetId the primary key for the new gadget
	 * @return the new gadget
	 */
	public Gadget create(long gadgetId) {
		Gadget gadget = new GadgetImpl();

		gadget.setNew(true);
		gadget.setPrimaryKey(gadgetId);

		return gadget;
	}

	/**
	 * Removes the gadget with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the gadget to remove
	 * @return the gadget that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a gadget with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the gadget with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gadgetId the primary key of the gadget to remove
	 * @return the gadget that was removed
	 * @throws com.liferay.opensocial.NoSuchGadgetException if a gadget with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget remove(long gadgetId)
		throws NoSuchGadgetException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Gadget gadget = (Gadget)session.get(GadgetImpl.class,
					new Long(gadgetId));

			if (gadget == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + gadgetId);
				}

				throw new NoSuchGadgetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					gadgetId);
			}

			return remove(gadget);
		}
		catch (NoSuchGadgetException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Gadget removeImpl(Gadget gadget) throws SystemException {
		gadget = toUnwrappedModel(gadget);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, gadget);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(GadgetModelImpl.ENTITY_CACHE_ENABLED,
			GadgetImpl.class, gadget.getPrimaryKey());

		return gadget;
	}

	public Gadget updateImpl(com.liferay.opensocial.model.Gadget gadget,
		boolean merge) throws SystemException {
		gadget = toUnwrappedModel(gadget);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, gadget, merge);

			gadget.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(GadgetModelImpl.ENTITY_CACHE_ENABLED,
			GadgetImpl.class, gadget.getPrimaryKey(), gadget);

		return gadget;
	}

	protected Gadget toUnwrappedModel(Gadget gadget) {
		if (gadget instanceof GadgetImpl) {
			return gadget;
		}

		GadgetImpl gadgetImpl = new GadgetImpl();

		gadgetImpl.setNew(gadget.isNew());
		gadgetImpl.setPrimaryKey(gadget.getPrimaryKey());

		gadgetImpl.setGadgetId(gadget.getGadgetId());
		gadgetImpl.setCompanyId(gadget.getCompanyId());
		gadgetImpl.setCreateDate(gadget.getCreateDate());
		gadgetImpl.setModifiedDate(gadget.getModifiedDate());
		gadgetImpl.setName(gadget.getName());
		gadgetImpl.setUrl(gadget.getUrl());

		return gadgetImpl;
	}

	/**
	 * Finds the gadget with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the gadget to find
	 * @return the gadget
	 * @throws com.liferay.portal.NoSuchModelException if a gadget with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the gadget with the primary key or throws a {@link com.liferay.opensocial.NoSuchGadgetException} if it could not be found.
	 *
	 * @param gadgetId the primary key of the gadget to find
	 * @return the gadget
	 * @throws com.liferay.opensocial.NoSuchGadgetException if a gadget with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget findByPrimaryKey(long gadgetId)
		throws NoSuchGadgetException, SystemException {
		Gadget gadget = fetchByPrimaryKey(gadgetId);

		if (gadget == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + gadgetId);
			}

			throw new NoSuchGadgetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				gadgetId);
		}

		return gadget;
	}

	/**
	 * Finds the gadget with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the gadget to find
	 * @return the gadget, or <code>null</code> if a gadget with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the gadget with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param gadgetId the primary key of the gadget to find
	 * @return the gadget, or <code>null</code> if a gadget with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget fetchByPrimaryKey(long gadgetId) throws SystemException {
		Gadget gadget = (Gadget)EntityCacheUtil.getResult(GadgetModelImpl.ENTITY_CACHE_ENABLED,
				GadgetImpl.class, gadgetId, this);

		if (gadget == null) {
			Session session = null;

			try {
				session = openSession();

				gadget = (Gadget)session.get(GadgetImpl.class,
						new Long(gadgetId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (gadget != null) {
					cacheResult(gadget);
				}

				closeSession(session);
			}
		}

		return gadget;
	}

	/**
	 * Finds all the gadgets where companyId = &#63;.
	 *
	 * @param companyId the company id to search with
	 * @return the matching gadgets
	 * @throws SystemException if a system exception occurred
	 */
	public List<Gadget> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Finds a range of all the gadgets where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param start the lower bound of the range of gadgets to return
	 * @param end the upper bound of the range of gadgets to return (not inclusive)
	 * @return the range of matching gadgets
	 * @throws SystemException if a system exception occurred
	 */
	public List<Gadget> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the gadgets where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param start the lower bound of the range of gadgets to return
	 * @param end the upper bound of the range of gadgets to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching gadgets
	 * @throws SystemException if a system exception occurred
	 */
	public List<Gadget> findByCompanyId(long companyId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Gadget> list = (List<Gadget>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
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

			query.append(_SQL_SELECT_GADGET_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(GadgetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<Gadget>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_COMPANYID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first gadget in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching gadget
	 * @throws com.liferay.opensocial.NoSuchGadgetException if a matching gadget could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchGadgetException, SystemException {
		List<Gadget> list = findByCompanyId(companyId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchGadgetException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last gadget in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching gadget
	 * @throws com.liferay.opensocial.NoSuchGadgetException if a matching gadget could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchGadgetException, SystemException {
		int count = countByCompanyId(companyId);

		List<Gadget> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchGadgetException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the gadgets before and after the current gadget in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param gadgetId the primary key of the current gadget
	 * @param companyId the company id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next gadget
	 * @throws com.liferay.opensocial.NoSuchGadgetException if a gadget with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget[] findByCompanyId_PrevAndNext(long gadgetId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchGadgetException, SystemException {
		Gadget gadget = findByPrimaryKey(gadgetId);

		Session session = null;

		try {
			session = openSession();

			Gadget[] array = new GadgetImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, gadget, companyId,
					orderByComparator, true);

			array[1] = gadget;

			array[2] = getByCompanyId_PrevAndNext(session, gadget, companyId,
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

	protected Gadget getByCompanyId_PrevAndNext(Session session, Gadget gadget,
		long companyId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_GADGET_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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
			query.append(GadgetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(gadget);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Gadget> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the gadgets.
	 *
	 * @return the gadgets
	 * @throws SystemException if a system exception occurred
	 */
	public List<Gadget> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the gadgets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of gadgets to return
	 * @param end the upper bound of the range of gadgets to return (not inclusive)
	 * @return the range of gadgets
	 * @throws SystemException if a system exception occurred
	 */
	public List<Gadget> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the gadgets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of gadgets to return
	 * @param end the upper bound of the range of gadgets to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of gadgets
	 * @throws SystemException if a system exception occurred
	 */
	public List<Gadget> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Gadget> list = (List<Gadget>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_GADGET);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_GADGET.concat(GadgetModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Gadget>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Gadget>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the gadgets where companyId = &#63; from the database.
	 *
	 * @param companyId the company id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (Gadget gadget : findByCompanyId(companyId)) {
			remove(gadget);
		}
	}

	/**
	 * Removes all the gadgets from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Gadget gadget : findAll()) {
			remove(gadget);
		}
	}

	/**
	 * Counts all the gadgets where companyId = &#63;.
	 *
	 * @param companyId the company id to search with
	 * @return the number of matching gadgets
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_GADGET_WHERE);

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
	 * Counts all the gadgets.
	 *
	 * @return the number of gadgets
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

				Query q = session.createQuery(_SQL_COUNT_GADGET);

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
	 * Initializes the gadget persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.opensocial.model.Gadget")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Gadget>> listenersList = new ArrayList<ModelListener<Gadget>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Gadget>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(GadgetImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = GadgetPersistence.class)
	protected GadgetPersistence gadgetPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_GADGET = "SELECT gadget FROM Gadget gadget";
	private static final String _SQL_SELECT_GADGET_WHERE = "SELECT gadget FROM Gadget gadget WHERE ";
	private static final String _SQL_COUNT_GADGET = "SELECT COUNT(gadget) FROM Gadget gadget";
	private static final String _SQL_COUNT_GADGET_WHERE = "SELECT COUNT(gadget) FROM Gadget gadget WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "gadget.companyId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "gadget.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Gadget exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Gadget exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(GadgetPersistenceImpl.class);
}