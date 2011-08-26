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

package com.liferay.hr.service.persistence;

import com.liferay.hr.NoSuchExpenseCurrencyConversionException;
import com.liferay.hr.model.HRExpenseCurrencyConversion;
import com.liferay.hr.model.impl.HRExpenseCurrencyConversionImpl;
import com.liferay.hr.model.impl.HRExpenseCurrencyConversionModelImpl;

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
import com.liferay.portal.kernel.util.CalendarUtil;
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
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the h r expense currency conversion service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRExpenseCurrencyConversionPersistence
 * @see HRExpenseCurrencyConversionUtil
 * @generated
 */
public class HRExpenseCurrencyConversionPersistenceImpl
	extends BasePersistenceImpl<HRExpenseCurrencyConversion>
	implements HRExpenseCurrencyConversionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRExpenseCurrencyConversionUtil} to access the h r expense currency conversion persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRExpenseCurrencyConversionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_F_T_C = new FinderPath(HRExpenseCurrencyConversionModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseCurrencyConversionModelImpl.FINDER_CACHE_ENABLED,
			HRExpenseCurrencyConversionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_F_T_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Date.class.getName()
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_G_F_T_C = new FinderPath(HRExpenseCurrencyConversionModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseCurrencyConversionModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST, "countByG_F_T_C",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Date.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRExpenseCurrencyConversionModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseCurrencyConversionModelImpl.FINDER_CACHE_ENABLED,
			HRExpenseCurrencyConversionImpl.class, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRExpenseCurrencyConversionModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseCurrencyConversionModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r expense currency conversion in the entity cache if it is enabled.
	 *
	 * @param hrExpenseCurrencyConversion the h r expense currency conversion
	 */
	public void cacheResult(
		HRExpenseCurrencyConversion hrExpenseCurrencyConversion) {
		EntityCacheUtil.putResult(HRExpenseCurrencyConversionModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseCurrencyConversionImpl.class,
			hrExpenseCurrencyConversion.getPrimaryKey(),
			hrExpenseCurrencyConversion);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_F_T_C,
			new Object[] {
				Long.valueOf(hrExpenseCurrencyConversion.getGroupId()),
				Long.valueOf(
					hrExpenseCurrencyConversion.getFromHRExpenseCurrencyId()),
				Long.valueOf(
					hrExpenseCurrencyConversion.getToHRExpenseCurrencyId()),
				
			hrExpenseCurrencyConversion.getConversionDate()
			}, hrExpenseCurrencyConversion);

		hrExpenseCurrencyConversion.resetOriginalValues();
	}

	/**
	 * Caches the h r expense currency conversions in the entity cache if it is enabled.
	 *
	 * @param hrExpenseCurrencyConversions the h r expense currency conversions
	 */
	public void cacheResult(
		List<HRExpenseCurrencyConversion> hrExpenseCurrencyConversions) {
		for (HRExpenseCurrencyConversion hrExpenseCurrencyConversion : hrExpenseCurrencyConversions) {
			if (EntityCacheUtil.getResult(
						HRExpenseCurrencyConversionModelImpl.ENTITY_CACHE_ENABLED,
						HRExpenseCurrencyConversionImpl.class,
						hrExpenseCurrencyConversion.getPrimaryKey(), this) == null) {
				cacheResult(hrExpenseCurrencyConversion);
			}
		}
	}

	/**
	 * Clears the cache for all h r expense currency conversions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRExpenseCurrencyConversionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRExpenseCurrencyConversionImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r expense currency conversion.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		HRExpenseCurrencyConversion hrExpenseCurrencyConversion) {
		EntityCacheUtil.removeResult(HRExpenseCurrencyConversionModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseCurrencyConversionImpl.class,
			hrExpenseCurrencyConversion.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_F_T_C,
			new Object[] {
				Long.valueOf(hrExpenseCurrencyConversion.getGroupId()),
				Long.valueOf(
					hrExpenseCurrencyConversion.getFromHRExpenseCurrencyId()),
				Long.valueOf(
					hrExpenseCurrencyConversion.getToHRExpenseCurrencyId()),
				
			hrExpenseCurrencyConversion.getConversionDate()
			});
	}

	/**
	 * Creates a new h r expense currency conversion with the primary key. Does not add the h r expense currency conversion to the database.
	 *
	 * @param hrExpenseCurrencyConversionId the primary key for the new h r expense currency conversion
	 * @return the new h r expense currency conversion
	 */
	public HRExpenseCurrencyConversion create(
		long hrExpenseCurrencyConversionId) {
		HRExpenseCurrencyConversion hrExpenseCurrencyConversion = new HRExpenseCurrencyConversionImpl();

		hrExpenseCurrencyConversion.setNew(true);
		hrExpenseCurrencyConversion.setPrimaryKey(hrExpenseCurrencyConversionId);

		return hrExpenseCurrencyConversion;
	}

	/**
	 * Removes the h r expense currency conversion with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r expense currency conversion
	 * @return the h r expense currency conversion that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r expense currency conversion with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRExpenseCurrencyConversion remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r expense currency conversion with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrExpenseCurrencyConversionId the primary key of the h r expense currency conversion
	 * @return the h r expense currency conversion that was removed
	 * @throws com.liferay.hr.NoSuchExpenseCurrencyConversionException if a h r expense currency conversion with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseCurrencyConversion remove(
		long hrExpenseCurrencyConversionId)
		throws NoSuchExpenseCurrencyConversionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRExpenseCurrencyConversion hrExpenseCurrencyConversion = (HRExpenseCurrencyConversion)session.get(HRExpenseCurrencyConversionImpl.class,
					Long.valueOf(hrExpenseCurrencyConversionId));

			if (hrExpenseCurrencyConversion == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrExpenseCurrencyConversionId);
				}

				throw new NoSuchExpenseCurrencyConversionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrExpenseCurrencyConversionId);
			}

			return hrExpenseCurrencyConversionPersistence.remove(hrExpenseCurrencyConversion);
		}
		catch (NoSuchExpenseCurrencyConversionException nsee) {
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
	 * Removes the h r expense currency conversion from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrExpenseCurrencyConversion the h r expense currency conversion
	 * @return the h r expense currency conversion that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRExpenseCurrencyConversion remove(
		HRExpenseCurrencyConversion hrExpenseCurrencyConversion)
		throws SystemException {
		return super.remove(hrExpenseCurrencyConversion);
	}

	@Override
	protected HRExpenseCurrencyConversion removeImpl(
		HRExpenseCurrencyConversion hrExpenseCurrencyConversion)
		throws SystemException {
		hrExpenseCurrencyConversion = toUnwrappedModel(hrExpenseCurrencyConversion);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrExpenseCurrencyConversion);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		HRExpenseCurrencyConversionModelImpl hrExpenseCurrencyConversionModelImpl =
			(HRExpenseCurrencyConversionModelImpl)hrExpenseCurrencyConversion;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_F_T_C,
			new Object[] {
				Long.valueOf(hrExpenseCurrencyConversionModelImpl.getGroupId()),
				Long.valueOf(
					hrExpenseCurrencyConversionModelImpl.getFromHRExpenseCurrencyId()),
				Long.valueOf(
					hrExpenseCurrencyConversionModelImpl.getToHRExpenseCurrencyId()),
				
			hrExpenseCurrencyConversionModelImpl.getConversionDate()
			});

		EntityCacheUtil.removeResult(HRExpenseCurrencyConversionModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseCurrencyConversionImpl.class,
			hrExpenseCurrencyConversion.getPrimaryKey());

		return hrExpenseCurrencyConversion;
	}

	@Override
	public HRExpenseCurrencyConversion updateImpl(
		com.liferay.hr.model.HRExpenseCurrencyConversion hrExpenseCurrencyConversion,
		boolean merge) throws SystemException {
		hrExpenseCurrencyConversion = toUnwrappedModel(hrExpenseCurrencyConversion);

		boolean isNew = hrExpenseCurrencyConversion.isNew();

		HRExpenseCurrencyConversionModelImpl hrExpenseCurrencyConversionModelImpl =
			(HRExpenseCurrencyConversionModelImpl)hrExpenseCurrencyConversion;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrExpenseCurrencyConversion, merge);

			hrExpenseCurrencyConversion.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRExpenseCurrencyConversionModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseCurrencyConversionImpl.class,
			hrExpenseCurrencyConversion.getPrimaryKey(),
			hrExpenseCurrencyConversion);

		if (!isNew &&
				((hrExpenseCurrencyConversion.getGroupId() != hrExpenseCurrencyConversionModelImpl.getOriginalGroupId()) ||
				(hrExpenseCurrencyConversion.getFromHRExpenseCurrencyId() != hrExpenseCurrencyConversionModelImpl.getOriginalFromHRExpenseCurrencyId()) ||
				(hrExpenseCurrencyConversion.getToHRExpenseCurrencyId() != hrExpenseCurrencyConversionModelImpl.getOriginalToHRExpenseCurrencyId()) ||
				!Validator.equals(
					hrExpenseCurrencyConversion.getConversionDate(),
					hrExpenseCurrencyConversionModelImpl.getOriginalConversionDate()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_F_T_C,
				new Object[] {
					Long.valueOf(
						hrExpenseCurrencyConversionModelImpl.getOriginalGroupId()),
					Long.valueOf(
						hrExpenseCurrencyConversionModelImpl.getOriginalFromHRExpenseCurrencyId()),
					Long.valueOf(
						hrExpenseCurrencyConversionModelImpl.getOriginalToHRExpenseCurrencyId()),
					
				hrExpenseCurrencyConversionModelImpl.getOriginalConversionDate()
				});
		}

		if (isNew ||
				((hrExpenseCurrencyConversion.getGroupId() != hrExpenseCurrencyConversionModelImpl.getOriginalGroupId()) ||
				(hrExpenseCurrencyConversion.getFromHRExpenseCurrencyId() != hrExpenseCurrencyConversionModelImpl.getOriginalFromHRExpenseCurrencyId()) ||
				(hrExpenseCurrencyConversion.getToHRExpenseCurrencyId() != hrExpenseCurrencyConversionModelImpl.getOriginalToHRExpenseCurrencyId()) ||
				!Validator.equals(
					hrExpenseCurrencyConversion.getConversionDate(),
					hrExpenseCurrencyConversionModelImpl.getOriginalConversionDate()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_F_T_C,
				new Object[] {
					Long.valueOf(hrExpenseCurrencyConversion.getGroupId()),
					Long.valueOf(
						hrExpenseCurrencyConversion.getFromHRExpenseCurrencyId()),
					Long.valueOf(
						hrExpenseCurrencyConversion.getToHRExpenseCurrencyId()),
					
				hrExpenseCurrencyConversion.getConversionDate()
				}, hrExpenseCurrencyConversion);
		}

		return hrExpenseCurrencyConversion;
	}

	protected HRExpenseCurrencyConversion toUnwrappedModel(
		HRExpenseCurrencyConversion hrExpenseCurrencyConversion) {
		if (hrExpenseCurrencyConversion instanceof HRExpenseCurrencyConversionImpl) {
			return hrExpenseCurrencyConversion;
		}

		HRExpenseCurrencyConversionImpl hrExpenseCurrencyConversionImpl = new HRExpenseCurrencyConversionImpl();

		hrExpenseCurrencyConversionImpl.setNew(hrExpenseCurrencyConversion.isNew());
		hrExpenseCurrencyConversionImpl.setPrimaryKey(hrExpenseCurrencyConversion.getPrimaryKey());

		hrExpenseCurrencyConversionImpl.setHrExpenseCurrencyConversionId(hrExpenseCurrencyConversion.getHrExpenseCurrencyConversionId());
		hrExpenseCurrencyConversionImpl.setGroupId(hrExpenseCurrencyConversion.getGroupId());
		hrExpenseCurrencyConversionImpl.setCompanyId(hrExpenseCurrencyConversion.getCompanyId());
		hrExpenseCurrencyConversionImpl.setUserId(hrExpenseCurrencyConversion.getUserId());
		hrExpenseCurrencyConversionImpl.setUserName(hrExpenseCurrencyConversion.getUserName());
		hrExpenseCurrencyConversionImpl.setCreateDate(hrExpenseCurrencyConversion.getCreateDate());
		hrExpenseCurrencyConversionImpl.setModifiedDate(hrExpenseCurrencyConversion.getModifiedDate());
		hrExpenseCurrencyConversionImpl.setFromHRExpenseCurrencyId(hrExpenseCurrencyConversion.getFromHRExpenseCurrencyId());
		hrExpenseCurrencyConversionImpl.setToHRExpenseCurrencyId(hrExpenseCurrencyConversion.getToHRExpenseCurrencyId());
		hrExpenseCurrencyConversionImpl.setConversionDate(hrExpenseCurrencyConversion.getConversionDate());
		hrExpenseCurrencyConversionImpl.setConversionValue(hrExpenseCurrencyConversion.getConversionValue());

		return hrExpenseCurrencyConversionImpl;
	}

	/**
	 * Returns the h r expense currency conversion with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r expense currency conversion
	 * @return the h r expense currency conversion
	 * @throws com.liferay.portal.NoSuchModelException if a h r expense currency conversion with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRExpenseCurrencyConversion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r expense currency conversion with the primary key or throws a {@link com.liferay.hr.NoSuchExpenseCurrencyConversionException} if it could not be found.
	 *
	 * @param hrExpenseCurrencyConversionId the primary key of the h r expense currency conversion
	 * @return the h r expense currency conversion
	 * @throws com.liferay.hr.NoSuchExpenseCurrencyConversionException if a h r expense currency conversion with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseCurrencyConversion findByPrimaryKey(
		long hrExpenseCurrencyConversionId)
		throws NoSuchExpenseCurrencyConversionException, SystemException {
		HRExpenseCurrencyConversion hrExpenseCurrencyConversion = fetchByPrimaryKey(hrExpenseCurrencyConversionId);

		if (hrExpenseCurrencyConversion == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrExpenseCurrencyConversionId);
			}

			throw new NoSuchExpenseCurrencyConversionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrExpenseCurrencyConversionId);
		}

		return hrExpenseCurrencyConversion;
	}

	/**
	 * Returns the h r expense currency conversion with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r expense currency conversion
	 * @return the h r expense currency conversion, or <code>null</code> if a h r expense currency conversion with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRExpenseCurrencyConversion fetchByPrimaryKey(
		Serializable primaryKey) throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r expense currency conversion with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrExpenseCurrencyConversionId the primary key of the h r expense currency conversion
	 * @return the h r expense currency conversion, or <code>null</code> if a h r expense currency conversion with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseCurrencyConversion fetchByPrimaryKey(
		long hrExpenseCurrencyConversionId) throws SystemException {
		HRExpenseCurrencyConversion hrExpenseCurrencyConversion = (HRExpenseCurrencyConversion)EntityCacheUtil.getResult(HRExpenseCurrencyConversionModelImpl.ENTITY_CACHE_ENABLED,
				HRExpenseCurrencyConversionImpl.class,
				hrExpenseCurrencyConversionId, this);

		if (hrExpenseCurrencyConversion == _nullHRExpenseCurrencyConversion) {
			return null;
		}

		if (hrExpenseCurrencyConversion == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrExpenseCurrencyConversion = (HRExpenseCurrencyConversion)session.get(HRExpenseCurrencyConversionImpl.class,
						Long.valueOf(hrExpenseCurrencyConversionId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrExpenseCurrencyConversion != null) {
					cacheResult(hrExpenseCurrencyConversion);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRExpenseCurrencyConversionModelImpl.ENTITY_CACHE_ENABLED,
						HRExpenseCurrencyConversionImpl.class,
						hrExpenseCurrencyConversionId,
						_nullHRExpenseCurrencyConversion);
				}

				closeSession(session);
			}
		}

		return hrExpenseCurrencyConversion;
	}

	/**
	 * Returns the h r expense currency conversion where groupId = &#63; and fromHRExpenseCurrencyId = &#63; and toHRExpenseCurrencyId = &#63; and conversionDate &lt; &#63; or throws a {@link com.liferay.hr.NoSuchExpenseCurrencyConversionException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param fromHRExpenseCurrencyId the from h r expense currency ID
	 * @param toHRExpenseCurrencyId the to h r expense currency ID
	 * @param conversionDate the conversion date
	 * @return the matching h r expense currency conversion
	 * @throws com.liferay.hr.NoSuchExpenseCurrencyConversionException if a matching h r expense currency conversion could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseCurrencyConversion findByG_F_T_C(long groupId,
		long fromHRExpenseCurrencyId, long toHRExpenseCurrencyId,
		Date conversionDate)
		throws NoSuchExpenseCurrencyConversionException, SystemException {
		HRExpenseCurrencyConversion hrExpenseCurrencyConversion = fetchByG_F_T_C(groupId,
				fromHRExpenseCurrencyId, toHRExpenseCurrencyId, conversionDate);

		if (hrExpenseCurrencyConversion == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", fromHRExpenseCurrencyId=");
			msg.append(fromHRExpenseCurrencyId);

			msg.append(", toHRExpenseCurrencyId=");
			msg.append(toHRExpenseCurrencyId);

			msg.append(", conversionDate=");
			msg.append(conversionDate);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchExpenseCurrencyConversionException(msg.toString());
		}

		return hrExpenseCurrencyConversion;
	}

	/**
	 * Returns the h r expense currency conversion where groupId = &#63; and fromHRExpenseCurrencyId = &#63; and toHRExpenseCurrencyId = &#63; and conversionDate &lt; &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param fromHRExpenseCurrencyId the from h r expense currency ID
	 * @param toHRExpenseCurrencyId the to h r expense currency ID
	 * @param conversionDate the conversion date
	 * @return the matching h r expense currency conversion, or <code>null</code> if a matching h r expense currency conversion could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseCurrencyConversion fetchByG_F_T_C(long groupId,
		long fromHRExpenseCurrencyId, long toHRExpenseCurrencyId,
		Date conversionDate) throws SystemException {
		return fetchByG_F_T_C(groupId, fromHRExpenseCurrencyId,
			toHRExpenseCurrencyId, conversionDate, true);
	}

	/**
	 * Returns the h r expense currency conversion where groupId = &#63; and fromHRExpenseCurrencyId = &#63; and toHRExpenseCurrencyId = &#63; and conversionDate &lt; &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param fromHRExpenseCurrencyId the from h r expense currency ID
	 * @param toHRExpenseCurrencyId the to h r expense currency ID
	 * @param conversionDate the conversion date
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching h r expense currency conversion, or <code>null</code> if a matching h r expense currency conversion could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseCurrencyConversion fetchByG_F_T_C(long groupId,
		long fromHRExpenseCurrencyId, long toHRExpenseCurrencyId,
		Date conversionDate, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, fromHRExpenseCurrencyId, toHRExpenseCurrencyId,
				conversionDate
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_F_T_C,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_HREXPENSECURRENCYCONVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_F_T_C_GROUPID_2);

			query.append(_FINDER_COLUMN_G_F_T_C_FROMHREXPENSECURRENCYID_2);

			query.append(_FINDER_COLUMN_G_F_T_C_TOHREXPENSECURRENCYID_2);

			if (conversionDate == null) {
				query.append(_FINDER_COLUMN_G_F_T_C_CONVERSIONDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_G_F_T_C_CONVERSIONDATE_2);
			}

			query.append(HRExpenseCurrencyConversionModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(fromHRExpenseCurrencyId);

				qPos.add(toHRExpenseCurrencyId);

				if (conversionDate != null) {
					qPos.add(CalendarUtil.getTimestamp(conversionDate));
				}

				List<HRExpenseCurrencyConversion> list = q.list();

				result = list;

				HRExpenseCurrencyConversion hrExpenseCurrencyConversion = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_F_T_C,
						finderArgs, list);
				}
				else {
					hrExpenseCurrencyConversion = list.get(0);

					cacheResult(hrExpenseCurrencyConversion);

					if ((hrExpenseCurrencyConversion.getGroupId() != groupId) ||
							(hrExpenseCurrencyConversion.getFromHRExpenseCurrencyId() != fromHRExpenseCurrencyId) ||
							(hrExpenseCurrencyConversion.getToHRExpenseCurrencyId() != toHRExpenseCurrencyId) ||
							(hrExpenseCurrencyConversion.getConversionDate() == null) ||
							!hrExpenseCurrencyConversion.getConversionDate()
															.equals(conversionDate)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_F_T_C,
							finderArgs, hrExpenseCurrencyConversion);
					}
				}

				return hrExpenseCurrencyConversion;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_F_T_C,
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
				return (HRExpenseCurrencyConversion)result;
			}
		}
	}

	/**
	 * Returns all the h r expense currency conversions.
	 *
	 * @return the h r expense currency conversions
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRExpenseCurrencyConversion> findAll()
		throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r expense currency conversions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r expense currency conversions
	 * @param end the upper bound of the range of h r expense currency conversions (not inclusive)
	 * @return the range of h r expense currency conversions
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRExpenseCurrencyConversion> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r expense currency conversions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r expense currency conversions
	 * @param end the upper bound of the range of h r expense currency conversions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r expense currency conversions
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRExpenseCurrencyConversion> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRExpenseCurrencyConversion> list = (List<HRExpenseCurrencyConversion>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HREXPENSECURRENCYCONVERSION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HREXPENSECURRENCYCONVERSION.concat(HRExpenseCurrencyConversionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRExpenseCurrencyConversion>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRExpenseCurrencyConversion>)QueryUtil.list(q,
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
	 * Removes the h r expense currency conversion where groupId = &#63; and fromHRExpenseCurrencyId = &#63; and toHRExpenseCurrencyId = &#63; and conversionDate &lt; &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param fromHRExpenseCurrencyId the from h r expense currency ID
	 * @param toHRExpenseCurrencyId the to h r expense currency ID
	 * @param conversionDate the conversion date
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_F_T_C(long groupId, long fromHRExpenseCurrencyId,
		long toHRExpenseCurrencyId, Date conversionDate)
		throws NoSuchExpenseCurrencyConversionException, SystemException {
		HRExpenseCurrencyConversion hrExpenseCurrencyConversion = findByG_F_T_C(groupId,
				fromHRExpenseCurrencyId, toHRExpenseCurrencyId, conversionDate);

		hrExpenseCurrencyConversionPersistence.remove(hrExpenseCurrencyConversion);
	}

	/**
	 * Removes all the h r expense currency conversions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRExpenseCurrencyConversion hrExpenseCurrencyConversion : findAll()) {
			hrExpenseCurrencyConversionPersistence.remove(hrExpenseCurrencyConversion);
		}
	}

	/**
	 * Returns the number of h r expense currency conversions where groupId = &#63; and fromHRExpenseCurrencyId = &#63; and toHRExpenseCurrencyId = &#63; and conversionDate &lt; &#63;.
	 *
	 * @param groupId the group ID
	 * @param fromHRExpenseCurrencyId the from h r expense currency ID
	 * @param toHRExpenseCurrencyId the to h r expense currency ID
	 * @param conversionDate the conversion date
	 * @return the number of matching h r expense currency conversions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_F_T_C(long groupId, long fromHRExpenseCurrencyId,
		long toHRExpenseCurrencyId, Date conversionDate)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, fromHRExpenseCurrencyId, toHRExpenseCurrencyId,
				conversionDate
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_F_T_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_HREXPENSECURRENCYCONVERSION_WHERE);

			query.append(_FINDER_COLUMN_G_F_T_C_GROUPID_2);

			query.append(_FINDER_COLUMN_G_F_T_C_FROMHREXPENSECURRENCYID_2);

			query.append(_FINDER_COLUMN_G_F_T_C_TOHREXPENSECURRENCYID_2);

			if (conversionDate == null) {
				query.append(_FINDER_COLUMN_G_F_T_C_CONVERSIONDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_G_F_T_C_CONVERSIONDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(fromHRExpenseCurrencyId);

				qPos.add(toHRExpenseCurrencyId);

				if (conversionDate != null) {
					qPos.add(CalendarUtil.getTimestamp(conversionDate));
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_F_T_C,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of h r expense currency conversions.
	 *
	 * @return the number of h r expense currency conversions
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

				Query q = session.createQuery(_SQL_COUNT_HREXPENSECURRENCYCONVERSION);

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
	 * Initializes the h r expense currency conversion persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRExpenseCurrencyConversion")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRExpenseCurrencyConversion>> listenersList = new ArrayList<ModelListener<HRExpenseCurrencyConversion>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRExpenseCurrencyConversion>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRExpenseCurrencyConversionImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = HRAssetPersistence.class)
	protected HRAssetPersistence hrAssetPersistence;
	@BeanReference(type = HRAssetCheckoutPersistence.class)
	protected HRAssetCheckoutPersistence hrAssetCheckoutPersistence;
	@BeanReference(type = HRAssetDefinitionPersistence.class)
	protected HRAssetDefinitionPersistence hrAssetDefinitionPersistence;
	@BeanReference(type = HRAssetProductPersistence.class)
	protected HRAssetProductPersistence hrAssetProductPersistence;
	@BeanReference(type = HRAssetTypePersistence.class)
	protected HRAssetTypePersistence hrAssetTypePersistence;
	@BeanReference(type = HRAssetVendorPersistence.class)
	protected HRAssetVendorPersistence hrAssetVendorPersistence;
	@BeanReference(type = HRBillabilityPersistence.class)
	protected HRBillabilityPersistence hrBillabilityPersistence;
	@BeanReference(type = HRBranchPersistence.class)
	protected HRBranchPersistence hrBranchPersistence;
	@BeanReference(type = HRClientPersistence.class)
	protected HRClientPersistence hrClientPersistence;
	@BeanReference(type = HREmploymentTypePersistence.class)
	protected HREmploymentTypePersistence hrEmploymentTypePersistence;
	@BeanReference(type = HRExpensePersistence.class)
	protected HRExpensePersistence hrExpensePersistence;
	@BeanReference(type = HRExpenseAccountPersistence.class)
	protected HRExpenseAccountPersistence hrExpenseAccountPersistence;
	@BeanReference(type = HRExpenseCurrencyPersistence.class)
	protected HRExpenseCurrencyPersistence hrExpenseCurrencyPersistence;
	@BeanReference(type = HRExpenseCurrencyConversionPersistence.class)
	protected HRExpenseCurrencyConversionPersistence hrExpenseCurrencyConversionPersistence;
	@BeanReference(type = HRExpenseTypePersistence.class)
	protected HRExpenseTypePersistence hrExpenseTypePersistence;
	@BeanReference(type = HRHolidayPersistence.class)
	protected HRHolidayPersistence hrHolidayPersistence;
	@BeanReference(type = HRJobTitlePersistence.class)
	protected HRJobTitlePersistence hrJobTitlePersistence;
	@BeanReference(type = HROfficePersistence.class)
	protected HROfficePersistence hrOfficePersistence;
	@BeanReference(type = HRProjectPersistence.class)
	protected HRProjectPersistence hrProjectPersistence;
	@BeanReference(type = HRProjectBillingRatePersistence.class)
	protected HRProjectBillingRatePersistence hrProjectBillingRatePersistence;
	@BeanReference(type = HRProjectRolePersistence.class)
	protected HRProjectRolePersistence hrProjectRolePersistence;
	@BeanReference(type = HRProjectStatusPersistence.class)
	protected HRProjectStatusPersistence hrProjectStatusPersistence;
	@BeanReference(type = HRTaskPersistence.class)
	protected HRTaskPersistence hrTaskPersistence;
	@BeanReference(type = HRTaskStatusPersistence.class)
	protected HRTaskStatusPersistence hrTaskStatusPersistence;
	@BeanReference(type = HRTerminationTypePersistence.class)
	protected HRTerminationTypePersistence hrTerminationTypePersistence;
	@BeanReference(type = HRTimeOffPersistence.class)
	protected HRTimeOffPersistence hrTimeOffPersistence;
	@BeanReference(type = HRTimeOffFrequencyTypePersistence.class)
	protected HRTimeOffFrequencyTypePersistence hrTimeOffFrequencyTypePersistence;
	@BeanReference(type = HRTimeOffPolicyPersistence.class)
	protected HRTimeOffPolicyPersistence hrTimeOffPolicyPersistence;
	@BeanReference(type = HRTimeOffTypePersistence.class)
	protected HRTimeOffTypePersistence hrTimeOffTypePersistence;
	@BeanReference(type = HRTimeSheetPersistence.class)
	protected HRTimeSheetPersistence hrTimeSheetPersistence;
	@BeanReference(type = HRTimeSheetDayPersistence.class)
	protected HRTimeSheetDayPersistence hrTimeSheetDayPersistence;
	@BeanReference(type = HRTimeSheetHoursPerDayPersistence.class)
	protected HRTimeSheetHoursPerDayPersistence hrTimeSheetHoursPerDayPersistence;
	@BeanReference(type = HRUserPersistence.class)
	protected HRUserPersistence hrUserPersistence;
	@BeanReference(type = HRUserHistoryPersistence.class)
	protected HRUserHistoryPersistence hrUserHistoryPersistence;
	@BeanReference(type = HRUserProjectPersistence.class)
	protected HRUserProjectPersistence hrUserProjectPersistence;
	@BeanReference(type = HRUserTaskPersistence.class)
	protected HRUserTaskPersistence hrUserTaskPersistence;
	@BeanReference(type = HRUserTimeOffPersistence.class)
	protected HRUserTimeOffPersistence hrUserTimeOffPersistence;
	@BeanReference(type = HRWageTypePersistence.class)
	protected HRWageTypePersistence hrWageTypePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_HREXPENSECURRENCYCONVERSION = "SELECT hrExpenseCurrencyConversion FROM HRExpenseCurrencyConversion hrExpenseCurrencyConversion";
	private static final String _SQL_SELECT_HREXPENSECURRENCYCONVERSION_WHERE = "SELECT hrExpenseCurrencyConversion FROM HRExpenseCurrencyConversion hrExpenseCurrencyConversion WHERE ";
	private static final String _SQL_COUNT_HREXPENSECURRENCYCONVERSION = "SELECT COUNT(hrExpenseCurrencyConversion) FROM HRExpenseCurrencyConversion hrExpenseCurrencyConversion";
	private static final String _SQL_COUNT_HREXPENSECURRENCYCONVERSION_WHERE = "SELECT COUNT(hrExpenseCurrencyConversion) FROM HRExpenseCurrencyConversion hrExpenseCurrencyConversion WHERE ";
	private static final String _FINDER_COLUMN_G_F_T_C_GROUPID_2 = "hrExpenseCurrencyConversion.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_F_T_C_FROMHREXPENSECURRENCYID_2 =
		"hrExpenseCurrencyConversion.fromHRExpenseCurrencyId = ? AND ";
	private static final String _FINDER_COLUMN_G_F_T_C_TOHREXPENSECURRENCYID_2 = "hrExpenseCurrencyConversion.toHRExpenseCurrencyId = ? AND ";
	private static final String _FINDER_COLUMN_G_F_T_C_CONVERSIONDATE_1 = "hrExpenseCurrencyConversion.conversionDate < NULL";
	private static final String _FINDER_COLUMN_G_F_T_C_CONVERSIONDATE_2 = "hrExpenseCurrencyConversion.conversionDate < ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrExpenseCurrencyConversion.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRExpenseCurrencyConversion exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No HRExpenseCurrencyConversion exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRExpenseCurrencyConversionPersistenceImpl.class);
	private static HRExpenseCurrencyConversion _nullHRExpenseCurrencyConversion = new HRExpenseCurrencyConversionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRExpenseCurrencyConversion> toCacheModel() {
				return _nullHRExpenseCurrencyConversionCacheModel;
			}
		};

	private static CacheModel<HRExpenseCurrencyConversion> _nullHRExpenseCurrencyConversionCacheModel =
		new CacheModel<HRExpenseCurrencyConversion>() {
			public HRExpenseCurrencyConversion toEntityModel() {
				return _nullHRExpenseCurrencyConversion;
			}
		};
}