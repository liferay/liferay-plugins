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

package com.liferay.mail.service.persistence;

import com.liferay.mail.NoSuchAccountException;
import com.liferay.mail.model.Account;
import com.liferay.mail.model.impl.AccountImpl;
import com.liferay.mail.model.impl.AccountModelImpl;

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
 * The persistence implementation for the account service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountPersistence
 * @see AccountUtil
 * @generated
 */
public class AccountPersistenceImpl extends BasePersistenceImpl<Account>
	implements AccountPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccountUtil} to access the account persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccountImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_USERID = new FinderPath(AccountModelImpl.ENTITY_CACHE_ENABLED,
			AccountModelImpl.FINDER_CACHE_ENABLED, AccountImpl.class,
			FINDER_CLASS_NAME_LIST, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(AccountModelImpl.ENTITY_CACHE_ENABLED,
			AccountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_U_A = new FinderPath(AccountModelImpl.ENTITY_CACHE_ENABLED,
			AccountModelImpl.FINDER_CACHE_ENABLED, AccountImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByU_A",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_U_A = new FinderPath(AccountModelImpl.ENTITY_CACHE_ENABLED,
			AccountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByU_A",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(AccountModelImpl.ENTITY_CACHE_ENABLED,
			AccountModelImpl.FINDER_CACHE_ENABLED, AccountImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccountModelImpl.ENTITY_CACHE_ENABLED,
			AccountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the account in the entity cache if it is enabled.
	 *
	 * @param account the account
	 */
	public void cacheResult(Account account) {
		EntityCacheUtil.putResult(AccountModelImpl.ENTITY_CACHE_ENABLED,
			AccountImpl.class, account.getPrimaryKey(), account);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_A,
			new Object[] { Long.valueOf(account.getUserId()), account.getAddress() },
			account);

		account.resetOriginalValues();
	}

	/**
	 * Caches the accounts in the entity cache if it is enabled.
	 *
	 * @param accounts the accounts
	 */
	public void cacheResult(List<Account> accounts) {
		for (Account account : accounts) {
			if (EntityCacheUtil.getResult(
						AccountModelImpl.ENTITY_CACHE_ENABLED,
						AccountImpl.class, account.getPrimaryKey(), this) == null) {
				cacheResult(account);
			}
		}
	}

	/**
	 * Clears the cache for all accounts.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AccountImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AccountImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the account.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Account account) {
		EntityCacheUtil.removeResult(AccountModelImpl.ENTITY_CACHE_ENABLED,
			AccountImpl.class, account.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_A,
			new Object[] { Long.valueOf(account.getUserId()), account.getAddress() });
	}

	/**
	 * Creates a new account with the primary key. Does not add the account to the database.
	 *
	 * @param accountId the primary key for the new account
	 * @return the new account
	 */
	public Account create(long accountId) {
		Account account = new AccountImpl();

		account.setNew(true);
		account.setPrimaryKey(accountId);

		return account;
	}

	/**
	 * Removes the account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account
	 * @return the account that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Account remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountId the primary key of the account
	 * @return the account that was removed
	 * @throws com.liferay.mail.NoSuchAccountException if a account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Account remove(long accountId)
		throws NoSuchAccountException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Account account = (Account)session.get(AccountImpl.class,
					Long.valueOf(accountId));

			if (account == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + accountId);
				}

				throw new NoSuchAccountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					accountId);
			}

			return accountPersistence.remove(account);
		}
		catch (NoSuchAccountException nsee) {
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
	 * Removes the account from the database. Also notifies the appropriate model listeners.
	 *
	 * @param account the account
	 * @return the account that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Account remove(Account account) throws SystemException {
		return super.remove(account);
	}

	@Override
	protected Account removeImpl(Account account) throws SystemException {
		account = toUnwrappedModel(account);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, account);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		AccountModelImpl accountModelImpl = (AccountModelImpl)account;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_A,
			new Object[] {
				Long.valueOf(accountModelImpl.getUserId()),
				
			accountModelImpl.getAddress()
			});

		EntityCacheUtil.removeResult(AccountModelImpl.ENTITY_CACHE_ENABLED,
			AccountImpl.class, account.getPrimaryKey());

		return account;
	}

	@Override
	public Account updateImpl(com.liferay.mail.model.Account account,
		boolean merge) throws SystemException {
		account = toUnwrappedModel(account);

		boolean isNew = account.isNew();

		AccountModelImpl accountModelImpl = (AccountModelImpl)account;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, account, merge);

			account.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(AccountModelImpl.ENTITY_CACHE_ENABLED,
			AccountImpl.class, account.getPrimaryKey(), account);

		if (!isNew &&
				((account.getUserId() != accountModelImpl.getOriginalUserId()) ||
				!Validator.equals(account.getAddress(),
					accountModelImpl.getOriginalAddress()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_A,
				new Object[] {
					Long.valueOf(accountModelImpl.getOriginalUserId()),
					
				accountModelImpl.getOriginalAddress()
				});
		}

		if (isNew ||
				((account.getUserId() != accountModelImpl.getOriginalUserId()) ||
				!Validator.equals(account.getAddress(),
					accountModelImpl.getOriginalAddress()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_A,
				new Object[] {
					Long.valueOf(account.getUserId()),
					
				account.getAddress()
				}, account);
		}

		return account;
	}

	protected Account toUnwrappedModel(Account account) {
		if (account instanceof AccountImpl) {
			return account;
		}

		AccountImpl accountImpl = new AccountImpl();

		accountImpl.setNew(account.isNew());
		accountImpl.setPrimaryKey(account.getPrimaryKey());

		accountImpl.setAccountId(account.getAccountId());
		accountImpl.setCompanyId(account.getCompanyId());
		accountImpl.setUserId(account.getUserId());
		accountImpl.setUserName(account.getUserName());
		accountImpl.setCreateDate(account.getCreateDate());
		accountImpl.setModifiedDate(account.getModifiedDate());
		accountImpl.setAddress(account.getAddress());
		accountImpl.setPersonalName(account.getPersonalName());
		accountImpl.setProtocol(account.getProtocol());
		accountImpl.setIncomingHostName(account.getIncomingHostName());
		accountImpl.setIncomingPort(account.getIncomingPort());
		accountImpl.setIncomingSecure(account.isIncomingSecure());
		accountImpl.setOutgoingHostName(account.getOutgoingHostName());
		accountImpl.setOutgoingPort(account.getOutgoingPort());
		accountImpl.setOutgoingSecure(account.isOutgoingSecure());
		accountImpl.setLogin(account.getLogin());
		accountImpl.setPassword(account.getPassword());
		accountImpl.setSavePassword(account.isSavePassword());
		accountImpl.setSignature(account.getSignature());
		accountImpl.setUseSignature(account.isUseSignature());
		accountImpl.setFolderPrefix(account.getFolderPrefix());
		accountImpl.setInboxFolderId(account.getInboxFolderId());
		accountImpl.setDraftFolderId(account.getDraftFolderId());
		accountImpl.setSentFolderId(account.getSentFolderId());
		accountImpl.setTrashFolderId(account.getTrashFolderId());
		accountImpl.setDefaultSender(account.isDefaultSender());

		return accountImpl;
	}

	/**
	 * Returns the account with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the account
	 * @return the account
	 * @throws com.liferay.portal.NoSuchModelException if a account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Account findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the account with the primary key or throws a {@link com.liferay.mail.NoSuchAccountException} if it could not be found.
	 *
	 * @param accountId the primary key of the account
	 * @return the account
	 * @throws com.liferay.mail.NoSuchAccountException if a account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Account findByPrimaryKey(long accountId)
		throws NoSuchAccountException, SystemException {
		Account account = fetchByPrimaryKey(accountId);

		if (account == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + accountId);
			}

			throw new NoSuchAccountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				accountId);
		}

		return account;
	}

	/**
	 * Returns the account with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account
	 * @return the account, or <code>null</code> if a account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Account fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the account with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountId the primary key of the account
	 * @return the account, or <code>null</code> if a account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Account fetchByPrimaryKey(long accountId) throws SystemException {
		Account account = (Account)EntityCacheUtil.getResult(AccountModelImpl.ENTITY_CACHE_ENABLED,
				AccountImpl.class, accountId, this);

		if (account == _nullAccount) {
			return null;
		}

		if (account == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				account = (Account)session.get(AccountImpl.class,
						Long.valueOf(accountId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (account != null) {
					cacheResult(account);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AccountModelImpl.ENTITY_CACHE_ENABLED,
						AccountImpl.class, accountId, _nullAccount);
				}

				closeSession(session);
			}
		}

		return account;
	}

	/**
	 * Returns all the accounts where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<Account> findByUserId(long userId) throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the accounts where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @return the range of matching accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<Account> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the accounts where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<Account> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				userId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Account> list = (List<Account>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERID,
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

			query.append(_SQL_SELECT_ACCOUNT_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AccountModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<Account>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_USERID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_USERID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first account in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account
	 * @throws com.liferay.mail.NoSuchAccountException if a matching account could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Account findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountException, SystemException {
		List<Account> list = findByUserId(userId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchAccountException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last account in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account
	 * @throws com.liferay.mail.NoSuchAccountException if a matching account could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Account findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountException, SystemException {
		int count = countByUserId(userId);

		List<Account> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchAccountException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the accounts before and after the current account in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountId the primary key of the current account
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account
	 * @throws com.liferay.mail.NoSuchAccountException if a account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Account[] findByUserId_PrevAndNext(long accountId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountException, SystemException {
		Account account = findByPrimaryKey(accountId);

		Session session = null;

		try {
			session = openSession();

			Account[] array = new AccountImpl[3];

			array[0] = getByUserId_PrevAndNext(session, account, userId,
					orderByComparator, true);

			array[1] = account;

			array[2] = getByUserId_PrevAndNext(session, account, userId,
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

	protected Account getByUserId_PrevAndNext(Session session, Account account,
		long userId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACCOUNT_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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
			query.append(AccountModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(account);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Account> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the account where userId = &#63; and address = &#63; or throws a {@link com.liferay.mail.NoSuchAccountException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param address the address
	 * @return the matching account
	 * @throws com.liferay.mail.NoSuchAccountException if a matching account could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Account findByU_A(long userId, String address)
		throws NoSuchAccountException, SystemException {
		Account account = fetchByU_A(userId, address);

		if (account == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", address=");
			msg.append(address);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAccountException(msg.toString());
		}

		return account;
	}

	/**
	 * Returns the account where userId = &#63; and address = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param address the address
	 * @return the matching account, or <code>null</code> if a matching account could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Account fetchByU_A(long userId, String address)
		throws SystemException {
		return fetchByU_A(userId, address, true);
	}

	/**
	 * Returns the account where userId = &#63; and address = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param address the address
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching account, or <code>null</code> if a matching account could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Account fetchByU_A(long userId, String address,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { userId, address };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_U_A,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ACCOUNT_WHERE);

			query.append(_FINDER_COLUMN_U_A_USERID_2);

			if (address == null) {
				query.append(_FINDER_COLUMN_U_A_ADDRESS_1);
			}
			else {
				if (address.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_U_A_ADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_U_A_ADDRESS_2);
				}
			}

			query.append(AccountModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (address != null) {
					qPos.add(address);
				}

				List<Account> list = q.list();

				result = list;

				Account account = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_A,
						finderArgs, list);
				}
				else {
					account = list.get(0);

					cacheResult(account);

					if ((account.getUserId() != userId) ||
							(account.getAddress() == null) ||
							!account.getAddress().equals(address)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_A,
							finderArgs, account);
					}
				}

				return account;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_A,
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
				return (Account)result;
			}
		}
	}

	/**
	 * Returns all the accounts.
	 *
	 * @return the accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<Account> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @return the range of accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<Account> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<Account> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Account> list = (List<Account>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ACCOUNT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNT.concat(AccountModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Account>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Account>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the accounts where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (Account account : findByUserId(userId)) {
			accountPersistence.remove(account);
		}
	}

	/**
	 * Removes the account where userId = &#63; and address = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param address the address
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_A(long userId, String address)
		throws NoSuchAccountException, SystemException {
		Account account = findByU_A(userId, address);

		accountPersistence.remove(account);
	}

	/**
	 * Removes all the accounts from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Account account : findAll()) {
			accountPersistence.remove(account);
		}
	}

	/**
	 * Returns the number of accounts where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching accounts
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACCOUNT_WHERE);

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
	 * Returns the number of accounts where userId = &#63; and address = &#63;.
	 *
	 * @param userId the user ID
	 * @param address the address
	 * @return the number of matching accounts
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_A(long userId, String address)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, address };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ACCOUNT_WHERE);

			query.append(_FINDER_COLUMN_U_A_USERID_2);

			if (address == null) {
				query.append(_FINDER_COLUMN_U_A_ADDRESS_1);
			}
			else {
				if (address.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_U_A_ADDRESS_3);
				}
				else {
					query.append(_FINDER_COLUMN_U_A_ADDRESS_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (address != null) {
					qPos.add(address);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_A, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of accounts.
	 *
	 * @return the number of accounts
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

				Query q = session.createQuery(_SQL_COUNT_ACCOUNT);

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
	 * Initializes the account persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.mail.model.Account")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Account>> listenersList = new ArrayList<ModelListener<Account>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Account>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AccountImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = AccountPersistence.class)
	protected AccountPersistence accountPersistence;
	@BeanReference(type = AttachmentPersistence.class)
	protected AttachmentPersistence attachmentPersistence;
	@BeanReference(type = FolderPersistence.class)
	protected FolderPersistence folderPersistence;
	@BeanReference(type = MessagePersistence.class)
	protected MessagePersistence messagePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_ACCOUNT = "SELECT account FROM Account account";
	private static final String _SQL_SELECT_ACCOUNT_WHERE = "SELECT account FROM Account account WHERE ";
	private static final String _SQL_COUNT_ACCOUNT = "SELECT COUNT(account) FROM Account account";
	private static final String _SQL_COUNT_ACCOUNT_WHERE = "SELECT COUNT(account) FROM Account account WHERE ";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "account.userId = ?";
	private static final String _FINDER_COLUMN_U_A_USERID_2 = "account.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_A_ADDRESS_1 = "account.address IS NULL";
	private static final String _FINDER_COLUMN_U_A_ADDRESS_2 = "account.address = ?";
	private static final String _FINDER_COLUMN_U_A_ADDRESS_3 = "(account.address IS NULL OR account.address = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "account.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Account exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Account exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AccountPersistenceImpl.class);
	private static Account _nullAccount = new AccountImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Account> toCacheModel() {
				return _nullAccountCacheModel;
			}
		};

	private static CacheModel<Account> _nullAccountCacheModel = new CacheModel<Account>() {
			public Account toEntityModel() {
				return _nullAccount;
			}
		};
}