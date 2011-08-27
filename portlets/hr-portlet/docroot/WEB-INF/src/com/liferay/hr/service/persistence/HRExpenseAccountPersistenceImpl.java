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

import com.liferay.hr.NoSuchExpenseAccountException;
import com.liferay.hr.model.HRExpenseAccount;
import com.liferay.hr.model.impl.HRExpenseAccountImpl;
import com.liferay.hr.model.impl.HRExpenseAccountModelImpl;

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
 * The persistence implementation for the h r expense account service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRExpenseAccountPersistence
 * @see HRExpenseAccountUtil
 * @generated
 */
public class HRExpenseAccountPersistenceImpl extends BasePersistenceImpl<HRExpenseAccount>
	implements HRExpenseAccountPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRExpenseAccountUtil} to access the h r expense account persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRExpenseAccountImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_N = new FinderPath(HRExpenseAccountModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseAccountModelImpl.FINDER_CACHE_ENABLED,
			HRExpenseAccountImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByG_N",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_G_N = new FinderPath(HRExpenseAccountModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseAccountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByG_N",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRExpenseAccountModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseAccountModelImpl.FINDER_CACHE_ENABLED,
			HRExpenseAccountImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRExpenseAccountModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseAccountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r expense account in the entity cache if it is enabled.
	 *
	 * @param hrExpenseAccount the h r expense account
	 */
	public void cacheResult(HRExpenseAccount hrExpenseAccount) {
		EntityCacheUtil.putResult(HRExpenseAccountModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseAccountImpl.class, hrExpenseAccount.getPrimaryKey(),
			hrExpenseAccount);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_N,
			new Object[] {
				Long.valueOf(hrExpenseAccount.getGroupId()),
				
			hrExpenseAccount.getName()
			}, hrExpenseAccount);

		hrExpenseAccount.resetOriginalValues();
	}

	/**
	 * Caches the h r expense accounts in the entity cache if it is enabled.
	 *
	 * @param hrExpenseAccounts the h r expense accounts
	 */
	public void cacheResult(List<HRExpenseAccount> hrExpenseAccounts) {
		for (HRExpenseAccount hrExpenseAccount : hrExpenseAccounts) {
			if (EntityCacheUtil.getResult(
						HRExpenseAccountModelImpl.ENTITY_CACHE_ENABLED,
						HRExpenseAccountImpl.class,
						hrExpenseAccount.getPrimaryKey(), this) == null) {
				cacheResult(hrExpenseAccount);
			}
		}
	}

	/**
	 * Clears the cache for all h r expense accounts.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRExpenseAccountImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRExpenseAccountImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r expense account.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRExpenseAccount hrExpenseAccount) {
		EntityCacheUtil.removeResult(HRExpenseAccountModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseAccountImpl.class, hrExpenseAccount.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_N,
			new Object[] {
				Long.valueOf(hrExpenseAccount.getGroupId()),
				
			hrExpenseAccount.getName()
			});
	}

	/**
	 * Creates a new h r expense account with the primary key. Does not add the h r expense account to the database.
	 *
	 * @param hrExpenseAccountId the primary key for the new h r expense account
	 * @return the new h r expense account
	 */
	public HRExpenseAccount create(long hrExpenseAccountId) {
		HRExpenseAccount hrExpenseAccount = new HRExpenseAccountImpl();

		hrExpenseAccount.setNew(true);
		hrExpenseAccount.setPrimaryKey(hrExpenseAccountId);

		return hrExpenseAccount;
	}

	/**
	 * Removes the h r expense account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r expense account
	 * @return the h r expense account that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r expense account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRExpenseAccount remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r expense account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrExpenseAccountId the primary key of the h r expense account
	 * @return the h r expense account that was removed
	 * @throws com.liferay.hr.NoSuchExpenseAccountException if a h r expense account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseAccount remove(long hrExpenseAccountId)
		throws NoSuchExpenseAccountException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRExpenseAccount hrExpenseAccount = (HRExpenseAccount)session.get(HRExpenseAccountImpl.class,
					Long.valueOf(hrExpenseAccountId));

			if (hrExpenseAccount == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrExpenseAccountId);
				}

				throw new NoSuchExpenseAccountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrExpenseAccountId);
			}

			return hrExpenseAccountPersistence.remove(hrExpenseAccount);
		}
		catch (NoSuchExpenseAccountException nsee) {
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
	 * Removes the h r expense account from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrExpenseAccount the h r expense account
	 * @return the h r expense account that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRExpenseAccount remove(HRExpenseAccount hrExpenseAccount)
		throws SystemException {
		return super.remove(hrExpenseAccount);
	}

	@Override
	protected HRExpenseAccount removeImpl(HRExpenseAccount hrExpenseAccount)
		throws SystemException {
		hrExpenseAccount = toUnwrappedModel(hrExpenseAccount);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrExpenseAccount);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		HRExpenseAccountModelImpl hrExpenseAccountModelImpl = (HRExpenseAccountModelImpl)hrExpenseAccount;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_N,
			new Object[] {
				Long.valueOf(hrExpenseAccountModelImpl.getGroupId()),
				
			hrExpenseAccountModelImpl.getName()
			});

		EntityCacheUtil.removeResult(HRExpenseAccountModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseAccountImpl.class, hrExpenseAccount.getPrimaryKey());

		return hrExpenseAccount;
	}

	@Override
	public HRExpenseAccount updateImpl(
		com.liferay.hr.model.HRExpenseAccount hrExpenseAccount, boolean merge)
		throws SystemException {
		hrExpenseAccount = toUnwrappedModel(hrExpenseAccount);

		boolean isNew = hrExpenseAccount.isNew();

		HRExpenseAccountModelImpl hrExpenseAccountModelImpl = (HRExpenseAccountModelImpl)hrExpenseAccount;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrExpenseAccount, merge);

			hrExpenseAccount.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRExpenseAccountModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseAccountImpl.class, hrExpenseAccount.getPrimaryKey(),
			hrExpenseAccount);

		if (!isNew &&
				((hrExpenseAccount.getGroupId() != hrExpenseAccountModelImpl.getOriginalGroupId()) ||
				!Validator.equals(hrExpenseAccount.getName(),
					hrExpenseAccountModelImpl.getOriginalName()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_N,
				new Object[] {
					Long.valueOf(hrExpenseAccountModelImpl.getOriginalGroupId()),
					
				hrExpenseAccountModelImpl.getOriginalName()
				});
		}

		if (isNew ||
				((hrExpenseAccount.getGroupId() != hrExpenseAccountModelImpl.getOriginalGroupId()) ||
				!Validator.equals(hrExpenseAccount.getName(),
					hrExpenseAccountModelImpl.getOriginalName()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_N,
				new Object[] {
					Long.valueOf(hrExpenseAccount.getGroupId()),
					
				hrExpenseAccount.getName()
				}, hrExpenseAccount);
		}

		return hrExpenseAccount;
	}

	protected HRExpenseAccount toUnwrappedModel(
		HRExpenseAccount hrExpenseAccount) {
		if (hrExpenseAccount instanceof HRExpenseAccountImpl) {
			return hrExpenseAccount;
		}

		HRExpenseAccountImpl hrExpenseAccountImpl = new HRExpenseAccountImpl();

		hrExpenseAccountImpl.setNew(hrExpenseAccount.isNew());
		hrExpenseAccountImpl.setPrimaryKey(hrExpenseAccount.getPrimaryKey());

		hrExpenseAccountImpl.setHrExpenseAccountId(hrExpenseAccount.getHrExpenseAccountId());
		hrExpenseAccountImpl.setGroupId(hrExpenseAccount.getGroupId());
		hrExpenseAccountImpl.setCompanyId(hrExpenseAccount.getCompanyId());
		hrExpenseAccountImpl.setUserId(hrExpenseAccount.getUserId());
		hrExpenseAccountImpl.setUserName(hrExpenseAccount.getUserName());
		hrExpenseAccountImpl.setCreateDate(hrExpenseAccount.getCreateDate());
		hrExpenseAccountImpl.setModifiedDate(hrExpenseAccount.getModifiedDate());
		hrExpenseAccountImpl.setName(hrExpenseAccount.getName());
		hrExpenseAccountImpl.setDescription(hrExpenseAccount.getDescription());

		return hrExpenseAccountImpl;
	}

	/**
	 * Returns the h r expense account with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r expense account
	 * @return the h r expense account
	 * @throws com.liferay.portal.NoSuchModelException if a h r expense account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRExpenseAccount findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r expense account with the primary key or throws a {@link com.liferay.hr.NoSuchExpenseAccountException} if it could not be found.
	 *
	 * @param hrExpenseAccountId the primary key of the h r expense account
	 * @return the h r expense account
	 * @throws com.liferay.hr.NoSuchExpenseAccountException if a h r expense account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseAccount findByPrimaryKey(long hrExpenseAccountId)
		throws NoSuchExpenseAccountException, SystemException {
		HRExpenseAccount hrExpenseAccount = fetchByPrimaryKey(hrExpenseAccountId);

		if (hrExpenseAccount == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrExpenseAccountId);
			}

			throw new NoSuchExpenseAccountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrExpenseAccountId);
		}

		return hrExpenseAccount;
	}

	/**
	 * Returns the h r expense account with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r expense account
	 * @return the h r expense account, or <code>null</code> if a h r expense account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRExpenseAccount fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r expense account with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrExpenseAccountId the primary key of the h r expense account
	 * @return the h r expense account, or <code>null</code> if a h r expense account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseAccount fetchByPrimaryKey(long hrExpenseAccountId)
		throws SystemException {
		HRExpenseAccount hrExpenseAccount = (HRExpenseAccount)EntityCacheUtil.getResult(HRExpenseAccountModelImpl.ENTITY_CACHE_ENABLED,
				HRExpenseAccountImpl.class, hrExpenseAccountId, this);

		if (hrExpenseAccount == _nullHRExpenseAccount) {
			return null;
		}

		if (hrExpenseAccount == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrExpenseAccount = (HRExpenseAccount)session.get(HRExpenseAccountImpl.class,
						Long.valueOf(hrExpenseAccountId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrExpenseAccount != null) {
					cacheResult(hrExpenseAccount);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRExpenseAccountModelImpl.ENTITY_CACHE_ENABLED,
						HRExpenseAccountImpl.class, hrExpenseAccountId,
						_nullHRExpenseAccount);
				}

				closeSession(session);
			}
		}

		return hrExpenseAccount;
	}

	/**
	 * Returns the h r expense account where groupId = &#63; and name = &#63; or throws a {@link com.liferay.hr.NoSuchExpenseAccountException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the matching h r expense account
	 * @throws com.liferay.hr.NoSuchExpenseAccountException if a matching h r expense account could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseAccount findByG_N(long groupId, String name)
		throws NoSuchExpenseAccountException, SystemException {
		HRExpenseAccount hrExpenseAccount = fetchByG_N(groupId, name);

		if (hrExpenseAccount == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchExpenseAccountException(msg.toString());
		}

		return hrExpenseAccount;
	}

	/**
	 * Returns the h r expense account where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the matching h r expense account, or <code>null</code> if a matching h r expense account could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseAccount fetchByG_N(long groupId, String name)
		throws SystemException {
		return fetchByG_N(groupId, name, true);
	}

	/**
	 * Returns the h r expense account where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching h r expense account, or <code>null</code> if a matching h r expense account could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseAccount fetchByG_N(long groupId, String name,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, name };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_N,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_HREXPENSEACCOUNT_WHERE);

			query.append(_FINDER_COLUMN_G_N_GROUPID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_G_N_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_N_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_N_NAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (name != null) {
					qPos.add(name);
				}

				List<HRExpenseAccount> list = q.list();

				result = list;

				HRExpenseAccount hrExpenseAccount = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_N,
						finderArgs, list);
				}
				else {
					hrExpenseAccount = list.get(0);

					cacheResult(hrExpenseAccount);

					if ((hrExpenseAccount.getGroupId() != groupId) ||
							(hrExpenseAccount.getName() == null) ||
							!hrExpenseAccount.getName().equals(name)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_N,
							finderArgs, hrExpenseAccount);
					}
				}

				return hrExpenseAccount;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_N,
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
				return (HRExpenseAccount)result;
			}
		}
	}

	/**
	 * Returns all the h r expense accounts.
	 *
	 * @return the h r expense accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRExpenseAccount> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r expense accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r expense accounts
	 * @param end the upper bound of the range of h r expense accounts (not inclusive)
	 * @return the range of h r expense accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRExpenseAccount> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r expense accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r expense accounts
	 * @param end the upper bound of the range of h r expense accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r expense accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRExpenseAccount> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRExpenseAccount> list = (List<HRExpenseAccount>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HREXPENSEACCOUNT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HREXPENSEACCOUNT;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRExpenseAccount>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRExpenseAccount>)QueryUtil.list(q,
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
	 * Removes the h r expense account where groupId = &#63; and name = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_N(long groupId, String name)
		throws NoSuchExpenseAccountException, SystemException {
		HRExpenseAccount hrExpenseAccount = findByG_N(groupId, name);

		hrExpenseAccountPersistence.remove(hrExpenseAccount);
	}

	/**
	 * Removes all the h r expense accounts from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRExpenseAccount hrExpenseAccount : findAll()) {
			hrExpenseAccountPersistence.remove(hrExpenseAccount);
		}
	}

	/**
	 * Returns the number of h r expense accounts where groupId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the number of matching h r expense accounts
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_N(long groupId, String name) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_N,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_HREXPENSEACCOUNT_WHERE);

			query.append(_FINDER_COLUMN_G_N_GROUPID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_G_N_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_N_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_N_NAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_N, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of h r expense accounts.
	 *
	 * @return the number of h r expense accounts
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

				Query q = session.createQuery(_SQL_COUNT_HREXPENSEACCOUNT);

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
	 * Initializes the h r expense account persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRExpenseAccount")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRExpenseAccount>> listenersList = new ArrayList<ModelListener<HRExpenseAccount>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRExpenseAccount>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRExpenseAccountImpl.class.getName());
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
	private static final String _SQL_SELECT_HREXPENSEACCOUNT = "SELECT hrExpenseAccount FROM HRExpenseAccount hrExpenseAccount";
	private static final String _SQL_SELECT_HREXPENSEACCOUNT_WHERE = "SELECT hrExpenseAccount FROM HRExpenseAccount hrExpenseAccount WHERE ";
	private static final String _SQL_COUNT_HREXPENSEACCOUNT = "SELECT COUNT(hrExpenseAccount) FROM HRExpenseAccount hrExpenseAccount";
	private static final String _SQL_COUNT_HREXPENSEACCOUNT_WHERE = "SELECT COUNT(hrExpenseAccount) FROM HRExpenseAccount hrExpenseAccount WHERE ";
	private static final String _FINDER_COLUMN_G_N_GROUPID_2 = "hrExpenseAccount.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_N_NAME_1 = "hrExpenseAccount.name IS NULL";
	private static final String _FINDER_COLUMN_G_N_NAME_2 = "hrExpenseAccount.name = ?";
	private static final String _FINDER_COLUMN_G_N_NAME_3 = "(hrExpenseAccount.name IS NULL OR hrExpenseAccount.name = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrExpenseAccount.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRExpenseAccount exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No HRExpenseAccount exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRExpenseAccountPersistenceImpl.class);
	private static HRExpenseAccount _nullHRExpenseAccount = new HRExpenseAccountImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRExpenseAccount> toCacheModel() {
				return _nullHRExpenseAccountCacheModel;
			}
		};

	private static CacheModel<HRExpenseAccount> _nullHRExpenseAccountCacheModel = new CacheModel<HRExpenseAccount>() {
			public HRExpenseAccount toEntityModel() {
				return _nullHRExpenseAccount;
			}
		};
}