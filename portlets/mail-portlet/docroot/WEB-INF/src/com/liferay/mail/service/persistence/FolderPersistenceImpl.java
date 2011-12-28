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

package com.liferay.mail.service.persistence;

import com.liferay.mail.NoSuchFolderException;
import com.liferay.mail.model.Folder;
import com.liferay.mail.model.impl.FolderImpl;
import com.liferay.mail.model.impl.FolderModelImpl;

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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the folder service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FolderPersistence
 * @see FolderUtil
 * @generated
 */
public class FolderPersistenceImpl extends BasePersistenceImpl<Folder>
	implements FolderPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link FolderUtil} to access the folder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = FolderImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTID =
		new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, FolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID =
		new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, FolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountId",
			new String[] { Long.class.getName() },
			FolderModelImpl.ACCOUNTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTID = new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_A_F = new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, FolderImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByA_F",
			new String[] { Long.class.getName(), String.class.getName() },
			FolderModelImpl.ACCOUNTID_COLUMN_BITMASK |
			FolderModelImpl.FULLNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_A_F = new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByA_F",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, FolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, FolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the folder in the entity cache if it is enabled.
	 *
	 * @param folder the folder
	 */
	public void cacheResult(Folder folder) {
		EntityCacheUtil.putResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderImpl.class, folder.getPrimaryKey(), folder);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_F,
			new Object[] {
				Long.valueOf(folder.getAccountId()),
				
			folder.getFullName()
			}, folder);

		folder.resetOriginalValues();
	}

	/**
	 * Caches the folders in the entity cache if it is enabled.
	 *
	 * @param folders the folders
	 */
	public void cacheResult(List<Folder> folders) {
		for (Folder folder : folders) {
			if (EntityCacheUtil.getResult(
						FolderModelImpl.ENTITY_CACHE_ENABLED, FolderImpl.class,
						folder.getPrimaryKey()) == null) {
				cacheResult(folder);
			}
			else {
				folder.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all folders.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(FolderImpl.class.getName());
		}

		EntityCacheUtil.clearCache(FolderImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the folder.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Folder folder) {
		EntityCacheUtil.removeResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderImpl.class, folder.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(folder);
	}

	@Override
	public void clearCache(List<Folder> folders) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Folder folder : folders) {
			EntityCacheUtil.removeResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
				FolderImpl.class, folder.getPrimaryKey());

			clearUniqueFindersCache(folder);
		}
	}

	protected void clearUniqueFindersCache(Folder folder) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_F,
			new Object[] {
				Long.valueOf(folder.getAccountId()),
				
			folder.getFullName()
			});
	}

	/**
	 * Creates a new folder with the primary key. Does not add the folder to the database.
	 *
	 * @param folderId the primary key for the new folder
	 * @return the new folder
	 */
	public Folder create(long folderId) {
		Folder folder = new FolderImpl();

		folder.setNew(true);
		folder.setPrimaryKey(folderId);

		return folder;
	}

	/**
	 * Removes the folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param folderId the primary key of the folder
	 * @return the folder that was removed
	 * @throws com.liferay.mail.NoSuchFolderException if a folder with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder remove(long folderId)
		throws NoSuchFolderException, SystemException {
		return remove(Long.valueOf(folderId));
	}

	/**
	 * Removes the folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the folder
	 * @return the folder that was removed
	 * @throws com.liferay.mail.NoSuchFolderException if a folder with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Folder remove(Serializable primaryKey)
		throws NoSuchFolderException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Folder folder = (Folder)session.get(FolderImpl.class, primaryKey);

			if (folder == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFolderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(folder);
		}
		catch (NoSuchFolderException nsee) {
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
	protected Folder removeImpl(Folder folder) throws SystemException {
		folder = toUnwrappedModel(folder);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, folder);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(folder);

		return folder;
	}

	@Override
	public Folder updateImpl(com.liferay.mail.model.Folder folder, boolean merge)
		throws SystemException {
		folder = toUnwrappedModel(folder);

		boolean isNew = folder.isNew();

		FolderModelImpl folderModelImpl = (FolderModelImpl)folder;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, folder, merge);

			folder.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !FolderModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((folderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(folderModelImpl.getOriginalAccountId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID,
					args);

				args = new Object[] { Long.valueOf(folderModelImpl.getAccountId()) };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID,
					args);
			}
		}

		EntityCacheUtil.putResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderImpl.class, folder.getPrimaryKey(), folder);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_F,
				new Object[] {
					Long.valueOf(folder.getAccountId()),
					
				folder.getFullName()
				}, folder);
		}
		else {
			if ((folderModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_A_F.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(folderModelImpl.getOriginalAccountId()),
						
						folderModelImpl.getOriginalFullName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_A_F, args);
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_F, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_F,
					new Object[] {
						Long.valueOf(folder.getAccountId()),
						
					folder.getFullName()
					}, folder);
			}
		}

		return folder;
	}

	protected Folder toUnwrappedModel(Folder folder) {
		if (folder instanceof FolderImpl) {
			return folder;
		}

		FolderImpl folderImpl = new FolderImpl();

		folderImpl.setNew(folder.isNew());
		folderImpl.setPrimaryKey(folder.getPrimaryKey());

		folderImpl.setFolderId(folder.getFolderId());
		folderImpl.setCompanyId(folder.getCompanyId());
		folderImpl.setUserId(folder.getUserId());
		folderImpl.setUserName(folder.getUserName());
		folderImpl.setCreateDate(folder.getCreateDate());
		folderImpl.setModifiedDate(folder.getModifiedDate());
		folderImpl.setAccountId(folder.getAccountId());
		folderImpl.setFullName(folder.getFullName());
		folderImpl.setDisplayName(folder.getDisplayName());
		folderImpl.setRemoteMessageCount(folder.getRemoteMessageCount());

		return folderImpl;
	}

	/**
	 * Returns the folder with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the folder
	 * @return the folder
	 * @throws com.liferay.portal.NoSuchModelException if a folder with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Folder findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the folder with the primary key or throws a {@link com.liferay.mail.NoSuchFolderException} if it could not be found.
	 *
	 * @param folderId the primary key of the folder
	 * @return the folder
	 * @throws com.liferay.mail.NoSuchFolderException if a folder with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder findByPrimaryKey(long folderId)
		throws NoSuchFolderException, SystemException {
		Folder folder = fetchByPrimaryKey(folderId);

		if (folder == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + folderId);
			}

			throw new NoSuchFolderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				folderId);
		}

		return folder;
	}

	/**
	 * Returns the folder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the folder
	 * @return the folder, or <code>null</code> if a folder with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Folder fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the folder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param folderId the primary key of the folder
	 * @return the folder, or <code>null</code> if a folder with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder fetchByPrimaryKey(long folderId) throws SystemException {
		Folder folder = (Folder)EntityCacheUtil.getResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
				FolderImpl.class, folderId);

		if (folder == _nullFolder) {
			return null;
		}

		if (folder == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				folder = (Folder)session.get(FolderImpl.class,
						Long.valueOf(folderId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (folder != null) {
					cacheResult(folder);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
						FolderImpl.class, folderId, _nullFolder);
				}

				closeSession(session);
			}
		}

		return folder;
	}

	/**
	 * Returns all the folders where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching folders
	 * @throws SystemException if a system exception occurred
	 */
	public List<Folder> findByAccountId(long accountId)
		throws SystemException {
		return findByAccountId(accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the folders where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of folders
	 * @param end the upper bound of the range of folders (not inclusive)
	 * @return the range of matching folders
	 * @throws SystemException if a system exception occurred
	 */
	public List<Folder> findByAccountId(long accountId, int start, int end)
		throws SystemException {
		return findByAccountId(accountId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the folders where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of folders
	 * @param end the upper bound of the range of folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching folders
	 * @throws SystemException if a system exception occurred
	 */
	public List<Folder> findByAccountId(long accountId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID;
			finderArgs = new Object[] { accountId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTID;
			finderArgs = new Object[] { accountId, start, end, orderByComparator };
		}

		List<Folder> list = (List<Folder>)FinderCacheUtil.getResult(finderPath,
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

			query.append(_SQL_SELECT_FOLDER_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(FolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountId);

				list = (List<Folder>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first folder in the ordered set where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching folder
	 * @throws com.liferay.mail.NoSuchFolderException if a matching folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder findByAccountId_First(long accountId,
		OrderByComparator orderByComparator)
		throws NoSuchFolderException, SystemException {
		List<Folder> list = findByAccountId(accountId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountId=");
			msg.append(accountId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFolderException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last folder in the ordered set where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching folder
	 * @throws com.liferay.mail.NoSuchFolderException if a matching folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder findByAccountId_Last(long accountId,
		OrderByComparator orderByComparator)
		throws NoSuchFolderException, SystemException {
		int count = countByAccountId(accountId);

		List<Folder> list = findByAccountId(accountId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountId=");
			msg.append(accountId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFolderException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the folders before and after the current folder in the ordered set where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param folderId the primary key of the current folder
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next folder
	 * @throws com.liferay.mail.NoSuchFolderException if a folder with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder[] findByAccountId_PrevAndNext(long folderId, long accountId,
		OrderByComparator orderByComparator)
		throws NoSuchFolderException, SystemException {
		Folder folder = findByPrimaryKey(folderId);

		Session session = null;

		try {
			session = openSession();

			Folder[] array = new FolderImpl[3];

			array[0] = getByAccountId_PrevAndNext(session, folder, accountId,
					orderByComparator, true);

			array[1] = folder;

			array[2] = getByAccountId_PrevAndNext(session, folder, accountId,
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

	protected Folder getByAccountId_PrevAndNext(Session session, Folder folder,
		long accountId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_FOLDER_WHERE);

		query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

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
			query.append(FolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(folder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Folder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the folder where accountId = &#63; and fullName = &#63; or throws a {@link com.liferay.mail.NoSuchFolderException} if it could not be found.
	 *
	 * @param accountId the account ID
	 * @param fullName the full name
	 * @return the matching folder
	 * @throws com.liferay.mail.NoSuchFolderException if a matching folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder findByA_F(long accountId, String fullName)
		throws NoSuchFolderException, SystemException {
		Folder folder = fetchByA_F(accountId, fullName);

		if (folder == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountId=");
			msg.append(accountId);

			msg.append(", fullName=");
			msg.append(fullName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchFolderException(msg.toString());
		}

		return folder;
	}

	/**
	 * Returns the folder where accountId = &#63; and fullName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountId the account ID
	 * @param fullName the full name
	 * @return the matching folder, or <code>null</code> if a matching folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder fetchByA_F(long accountId, String fullName)
		throws SystemException {
		return fetchByA_F(accountId, fullName, true);
	}

	/**
	 * Returns the folder where accountId = &#63; and fullName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountId the account ID
	 * @param fullName the full name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching folder, or <code>null</code> if a matching folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder fetchByA_F(long accountId, String fullName,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { accountId, fullName };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_A_F,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_FOLDER_WHERE);

			query.append(_FINDER_COLUMN_A_F_ACCOUNTID_2);

			if (fullName == null) {
				query.append(_FINDER_COLUMN_A_F_FULLNAME_1);
			}
			else {
				if (fullName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_A_F_FULLNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_A_F_FULLNAME_2);
				}
			}

			query.append(FolderModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountId);

				if (fullName != null) {
					qPos.add(fullName);
				}

				List<Folder> list = q.list();

				result = list;

				Folder folder = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_F,
						finderArgs, list);
				}
				else {
					folder = list.get(0);

					cacheResult(folder);

					if ((folder.getAccountId() != accountId) ||
							(folder.getFullName() == null) ||
							!folder.getFullName().equals(fullName)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_F,
							finderArgs, folder);
					}
				}

				return folder;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_F,
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
				return (Folder)result;
			}
		}
	}

	/**
	 * Returns all the folders.
	 *
	 * @return the folders
	 * @throws SystemException if a system exception occurred
	 */
	public List<Folder> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of folders
	 * @param end the upper bound of the range of folders (not inclusive)
	 * @return the range of folders
	 * @throws SystemException if a system exception occurred
	 */
	public List<Folder> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of folders
	 * @param end the upper bound of the range of folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of folders
	 * @throws SystemException if a system exception occurred
	 */
	public List<Folder> findAll(int start, int end,
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

		List<Folder> list = (List<Folder>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_FOLDER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FOLDER.concat(FolderModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Folder>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Folder>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the folders where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAccountId(long accountId) throws SystemException {
		for (Folder folder : findByAccountId(accountId)) {
			remove(folder);
		}
	}

	/**
	 * Removes the folder where accountId = &#63; and fullName = &#63; from the database.
	 *
	 * @param accountId the account ID
	 * @param fullName the full name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByA_F(long accountId, String fullName)
		throws NoSuchFolderException, SystemException {
		Folder folder = findByA_F(accountId, fullName);

		remove(folder);
	}

	/**
	 * Removes all the folders from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Folder folder : findAll()) {
			remove(folder);
		}
	}

	/**
	 * Returns the number of folders where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching folders
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAccountId(long accountId) throws SystemException {
		Object[] finderArgs = new Object[] { accountId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACCOUNTID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FOLDER_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACCOUNTID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of folders where accountId = &#63; and fullName = &#63;.
	 *
	 * @param accountId the account ID
	 * @param fullName the full name
	 * @return the number of matching folders
	 * @throws SystemException if a system exception occurred
	 */
	public int countByA_F(long accountId, String fullName)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountId, fullName };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_A_F,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FOLDER_WHERE);

			query.append(_FINDER_COLUMN_A_F_ACCOUNTID_2);

			if (fullName == null) {
				query.append(_FINDER_COLUMN_A_F_FULLNAME_1);
			}
			else {
				if (fullName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_A_F_FULLNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_A_F_FULLNAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountId);

				if (fullName != null) {
					qPos.add(fullName);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_A_F, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of folders.
	 *
	 * @return the number of folders
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FOLDER);

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
	 * Initializes the folder persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.mail.model.Folder")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Folder>> listenersList = new ArrayList<ModelListener<Folder>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Folder>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(FolderImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
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
	private static final String _SQL_SELECT_FOLDER = "SELECT folder FROM Folder folder";
	private static final String _SQL_SELECT_FOLDER_WHERE = "SELECT folder FROM Folder folder WHERE ";
	private static final String _SQL_COUNT_FOLDER = "SELECT COUNT(folder) FROM Folder folder";
	private static final String _SQL_COUNT_FOLDER_WHERE = "SELECT COUNT(folder) FROM Folder folder WHERE ";
	private static final String _FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2 = "folder.accountId = ?";
	private static final String _FINDER_COLUMN_A_F_ACCOUNTID_2 = "folder.accountId = ? AND ";
	private static final String _FINDER_COLUMN_A_F_FULLNAME_1 = "folder.fullName IS NULL";
	private static final String _FINDER_COLUMN_A_F_FULLNAME_2 = "folder.fullName = ?";
	private static final String _FINDER_COLUMN_A_F_FULLNAME_3 = "(folder.fullName IS NULL OR folder.fullName = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "folder.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Folder exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Folder exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(FolderPersistenceImpl.class);
	private static Folder _nullFolder = new FolderImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Folder> toCacheModel() {
				return _nullFolderCacheModel;
			}
		};

	private static CacheModel<Folder> _nullFolderCacheModel = new CacheModel<Folder>() {
			public Folder toEntityModel() {
				return _nullFolder;
			}
		};
}