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

package com.liferay.mail.service.persistence;

import com.liferay.mail.NoSuchAccountException;
import com.liferay.mail.model.Account;
import com.liferay.mail.model.impl.AccountImpl;
import com.liferay.mail.model.impl.AccountModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
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
 * <a href="AccountPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountPersistence
 * @see       AccountUtil
 * @generated
 */
public class AccountPersistenceImpl extends BasePersistenceImpl<Account>
	implements AccountPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = AccountImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(AccountModelImpl.ENTITY_CACHE_ENABLED,
			AccountModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccountModelImpl.ENTITY_CACHE_ENABLED,
			AccountModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(Account account) {
		EntityCacheUtil.putResult(AccountModelImpl.ENTITY_CACHE_ENABLED,
			AccountImpl.class, account.getPrimaryKey(), account);
	}

	public void cacheResult(List<Account> accounts) {
		for (Account account : accounts) {
			if (EntityCacheUtil.getResult(
						AccountModelImpl.ENTITY_CACHE_ENABLED,
						AccountImpl.class, account.getPrimaryKey(), this) == null) {
				cacheResult(account);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(AccountImpl.class.getName());
		EntityCacheUtil.clearCache(AccountImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public Account create(long accountId) {
		Account account = new AccountImpl();

		account.setNew(true);
		account.setPrimaryKey(accountId);

		return account;
	}

	public Account remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public Account remove(long accountId)
		throws NoSuchAccountException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Account account = (Account)session.get(AccountImpl.class,
					new Long(accountId));

			if (account == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + accountId);
				}

				throw new NoSuchAccountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					accountId);
			}

			return remove(account);
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

	public Account remove(Account account) throws SystemException {
		for (ModelListener<Account> listener : listeners) {
			listener.onBeforeRemove(account);
		}

		account = removeImpl(account);

		for (ModelListener<Account> listener : listeners) {
			listener.onAfterRemove(account);
		}

		return account;
	}

	protected Account removeImpl(Account account) throws SystemException {
		account = toUnwrappedModel(account);

		Session session = null;

		try {
			session = openSession();

			if (account.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(AccountImpl.class,
						account.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(account);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(AccountModelImpl.ENTITY_CACHE_ENABLED,
			AccountImpl.class, account.getPrimaryKey());

		return account;
	}

	public Account updateImpl(com.liferay.mail.model.Account account,
		boolean merge) throws SystemException {
		account = toUnwrappedModel(account);

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

	public Account findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

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

	public Account fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public Account fetchByPrimaryKey(long accountId) throws SystemException {
		Account account = (Account)EntityCacheUtil.getResult(AccountModelImpl.ENTITY_CACHE_ENABLED,
				AccountImpl.class, accountId, this);

		if (account == null) {
			Session session = null;

			try {
				session = openSession();

				account = (Account)session.get(AccountImpl.class,
						new Long(accountId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (account != null) {
					cacheResult(account);
				}

				closeSession(session);
			}
		}

		return account;
	}

	public List<Account> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<Account> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<Account> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Account> list = (List<Account>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

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
					list = new ArrayList<Account>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeAll() throws SystemException {
		for (Account account : findAll()) {
			remove(account);
		}
	}

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

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.mail.model.Account")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Account>> listenersList = new ArrayList<ModelListener<Account>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Account>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
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
	private static final String _SQL_COUNT_ACCOUNT = "SELECT COUNT(account) FROM Account account";
	private static final String _ORDER_BY_ENTITY_ALIAS = "account.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Account exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(AccountPersistenceImpl.class);
}