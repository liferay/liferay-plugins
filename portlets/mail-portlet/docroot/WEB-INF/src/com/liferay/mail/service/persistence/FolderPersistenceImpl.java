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

import com.liferay.mail.NoSuchFolderException;
import com.liferay.mail.model.Folder;
import com.liferay.mail.model.impl.FolderImpl;
import com.liferay.mail.model.impl.FolderModelImpl;

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
 * <a href="FolderPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FolderPersistence
 * @see       FolderUtil
 * @generated
 */
public class FolderPersistenceImpl extends BasePersistenceImpl<Folder>
	implements FolderPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = FolderImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(Folder folder) {
		EntityCacheUtil.putResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderImpl.class, folder.getPrimaryKey(), folder);
	}

	public void cacheResult(List<Folder> folders) {
		for (Folder folder : folders) {
			if (EntityCacheUtil.getResult(
						FolderModelImpl.ENTITY_CACHE_ENABLED, FolderImpl.class,
						folder.getPrimaryKey(), this) == null) {
				cacheResult(folder);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(FolderImpl.class.getName());
		EntityCacheUtil.clearCache(FolderImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public Folder create(long folderId) {
		Folder folder = new FolderImpl();

		folder.setNew(true);
		folder.setPrimaryKey(folderId);

		return folder;
	}

	public Folder remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public Folder remove(long folderId)
		throws NoSuchFolderException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Folder folder = (Folder)session.get(FolderImpl.class,
					new Long(folderId));

			if (folder == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + folderId);
				}

				throw new NoSuchFolderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					folderId);
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

	public Folder remove(Folder folder) throws SystemException {
		for (ModelListener<Folder> listener : listeners) {
			listener.onBeforeRemove(folder);
		}

		folder = removeImpl(folder);

		for (ModelListener<Folder> listener : listeners) {
			listener.onAfterRemove(folder);
		}

		return folder;
	}

	protected Folder removeImpl(Folder folder) throws SystemException {
		folder = toUnwrappedModel(folder);

		Session session = null;

		try {
			session = openSession();

			if (folder.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(FolderImpl.class,
						folder.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(folder);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderImpl.class, folder.getPrimaryKey());

		return folder;
	}

	public Folder updateImpl(com.liferay.mail.model.Folder folder, boolean merge)
		throws SystemException {
		folder = toUnwrappedModel(folder);

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

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderImpl.class, folder.getPrimaryKey(), folder);

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

	public Folder findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

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

	public Folder fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public Folder fetchByPrimaryKey(long folderId) throws SystemException {
		Folder folder = (Folder)EntityCacheUtil.getResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
				FolderImpl.class, folderId, this);

		if (folder == null) {
			Session session = null;

			try {
				session = openSession();

				folder = (Folder)session.get(FolderImpl.class,
						new Long(folderId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (folder != null) {
					cacheResult(folder);
				}

				closeSession(session);
			}
		}

		return folder;
	}

	public List<Folder> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<Folder> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<Folder> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Folder> list = (List<Folder>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_FOLDER);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}

				else {
					sql = _SQL_SELECT_FOLDER.concat(FolderModelImpl.ORDER_BY_JPQL);
				}

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
					list = new ArrayList<Folder>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeAll() throws SystemException {
		for (Folder folder : findAll()) {
			remove(folder);
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
						"value.object.listener.com.liferay.mail.model.Folder")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Folder>> listenersList = new ArrayList<ModelListener<Folder>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Folder>)Class.forName(
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
	private static final String _SQL_SELECT_FOLDER = "SELECT folder FROM Folder folder";
	private static final String _SQL_COUNT_FOLDER = "SELECT COUNT(folder) FROM Folder folder";
	private static final String _ORDER_BY_ENTITY_ALIAS = "folder.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Folder exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(FolderPersistenceImpl.class);
}