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

package com.liferay.microblogs.service.persistence;

import com.liferay.microblogs.NoSuchEntryException;
import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.model.impl.MicroblogsEntryImpl;
import com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
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
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the microblogs entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MicroblogsEntryPersistence
 * @see MicroblogsEntryUtil
 * @generated
 */
public class MicroblogsEntryPersistenceImpl extends BasePersistenceImpl<MicroblogsEntry>
	implements MicroblogsEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MicroblogsEntryUtil} to access the microblogs entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MicroblogsEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			MicroblogsEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			MicroblogsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			MicroblogsEntryModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			MicroblogsEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			MicroblogsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			MicroblogsEntryModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_T = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			MicroblogsEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByU_T",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_T = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			MicroblogsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_T",
			new String[] { Long.class.getName(), Integer.class.getName() },
			MicroblogsEntryModelImpl.USERID_COLUMN_BITMASK |
			MicroblogsEntryModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_T = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_T",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_T_R = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			MicroblogsEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByT_R",
			new String[] {
				Integer.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_R = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			MicroblogsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByT_R",
			new String[] { Integer.class.getName(), Long.class.getName() },
			MicroblogsEntryModelImpl.TYPE_COLUMN_BITMASK |
			MicroblogsEntryModelImpl.RECEIVERUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_T_R = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByT_R",
			new String[] { Integer.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_T_RMEI = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			MicroblogsEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByT_RMEI",
			new String[] {
				Integer.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_RMEI =
		new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			MicroblogsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByT_RMEI",
			new String[] { Integer.class.getName(), Long.class.getName() },
			MicroblogsEntryModelImpl.TYPE_COLUMN_BITMASK |
			MicroblogsEntryModelImpl.RECEIVERMICROBLOGSENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_T_RMEI = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByT_RMEI",
			new String[] { Integer.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			MicroblogsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED,
			MicroblogsEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the microblogs entry in the entity cache if it is enabled.
	 *
	 * @param microblogsEntry the microblogs entry
	 */
	public void cacheResult(MicroblogsEntry microblogsEntry) {
		EntityCacheUtil.putResult(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryImpl.class, microblogsEntry.getPrimaryKey(),
			microblogsEntry);

		microblogsEntry.resetOriginalValues();
	}

	/**
	 * Caches the microblogs entries in the entity cache if it is enabled.
	 *
	 * @param microblogsEntries the microblogs entries
	 */
	public void cacheResult(List<MicroblogsEntry> microblogsEntries) {
		for (MicroblogsEntry microblogsEntry : microblogsEntries) {
			if (EntityCacheUtil.getResult(
						MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
						MicroblogsEntryImpl.class,
						microblogsEntry.getPrimaryKey()) == null) {
				cacheResult(microblogsEntry);
			}
			else {
				microblogsEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all microblogs entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(MicroblogsEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(MicroblogsEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the microblogs entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MicroblogsEntry microblogsEntry) {
		EntityCacheUtil.removeResult(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryImpl.class, microblogsEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MicroblogsEntry> microblogsEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MicroblogsEntry microblogsEntry : microblogsEntries) {
			EntityCacheUtil.removeResult(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
				MicroblogsEntryImpl.class, microblogsEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new microblogs entry with the primary key. Does not add the microblogs entry to the database.
	 *
	 * @param microblogsEntryId the primary key for the new microblogs entry
	 * @return the new microblogs entry
	 */
	public MicroblogsEntry create(long microblogsEntryId) {
		MicroblogsEntry microblogsEntry = new MicroblogsEntryImpl();

		microblogsEntry.setNew(true);
		microblogsEntry.setPrimaryKey(microblogsEntryId);

		return microblogsEntry;
	}

	/**
	 * Removes the microblogs entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param microblogsEntryId the primary key of the microblogs entry
	 * @return the microblogs entry that was removed
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry remove(long microblogsEntryId)
		throws NoSuchEntryException, SystemException {
		return remove(Long.valueOf(microblogsEntryId));
	}

	/**
	 * Removes the microblogs entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the microblogs entry
	 * @return the microblogs entry that was removed
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MicroblogsEntry remove(Serializable primaryKey)
		throws NoSuchEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry microblogsEntry = (MicroblogsEntry)session.get(MicroblogsEntryImpl.class,
					primaryKey);

			if (microblogsEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(microblogsEntry);
		}
		catch (NoSuchEntryException nsee) {
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
	protected MicroblogsEntry removeImpl(MicroblogsEntry microblogsEntry)
		throws SystemException {
		microblogsEntry = toUnwrappedModel(microblogsEntry);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, microblogsEntry);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(microblogsEntry);

		return microblogsEntry;
	}

	@Override
	public MicroblogsEntry updateImpl(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry,
		boolean merge) throws SystemException {
		microblogsEntry = toUnwrappedModel(microblogsEntry);

		boolean isNew = microblogsEntry.isNew();

		MicroblogsEntryModelImpl microblogsEntryModelImpl = (MicroblogsEntryModelImpl)microblogsEntry;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, microblogsEntry, merge);

			microblogsEntry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !MicroblogsEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((microblogsEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(microblogsEntryModelImpl.getOriginalCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] {
						Long.valueOf(microblogsEntryModelImpl.getCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((microblogsEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(microblogsEntryModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(microblogsEntryModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((microblogsEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(microblogsEntryModelImpl.getOriginalUserId()),
						Integer.valueOf(microblogsEntryModelImpl.getOriginalType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_T,
					args);

				args = new Object[] {
						Long.valueOf(microblogsEntryModelImpl.getUserId()),
						Integer.valueOf(microblogsEntryModelImpl.getType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_T,
					args);
			}

			if ((microblogsEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Integer.valueOf(microblogsEntryModelImpl.getOriginalType()),
						Long.valueOf(microblogsEntryModelImpl.getOriginalReceiverUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_T_R, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_R,
					args);

				args = new Object[] {
						Integer.valueOf(microblogsEntryModelImpl.getType()),
						Long.valueOf(microblogsEntryModelImpl.getReceiverUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_T_R, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_R,
					args);
			}

			if ((microblogsEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_RMEI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Integer.valueOf(microblogsEntryModelImpl.getOriginalType()),
						Long.valueOf(microblogsEntryModelImpl.getOriginalReceiverMicroblogsEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_T_RMEI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_RMEI,
					args);

				args = new Object[] {
						Integer.valueOf(microblogsEntryModelImpl.getType()),
						Long.valueOf(microblogsEntryModelImpl.getReceiverMicroblogsEntryId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_T_RMEI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_RMEI,
					args);
			}
		}

		EntityCacheUtil.putResult(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MicroblogsEntryImpl.class, microblogsEntry.getPrimaryKey(),
			microblogsEntry);

		return microblogsEntry;
	}

	protected MicroblogsEntry toUnwrappedModel(MicroblogsEntry microblogsEntry) {
		if (microblogsEntry instanceof MicroblogsEntryImpl) {
			return microblogsEntry;
		}

		MicroblogsEntryImpl microblogsEntryImpl = new MicroblogsEntryImpl();

		microblogsEntryImpl.setNew(microblogsEntry.isNew());
		microblogsEntryImpl.setPrimaryKey(microblogsEntry.getPrimaryKey());

		microblogsEntryImpl.setMicroblogsEntryId(microblogsEntry.getMicroblogsEntryId());
		microblogsEntryImpl.setCompanyId(microblogsEntry.getCompanyId());
		microblogsEntryImpl.setUserId(microblogsEntry.getUserId());
		microblogsEntryImpl.setUserName(microblogsEntry.getUserName());
		microblogsEntryImpl.setCreateDate(microblogsEntry.getCreateDate());
		microblogsEntryImpl.setModifiedDate(microblogsEntry.getModifiedDate());
		microblogsEntryImpl.setContent(microblogsEntry.getContent());
		microblogsEntryImpl.setType(microblogsEntry.getType());
		microblogsEntryImpl.setReceiverUserId(microblogsEntry.getReceiverUserId());
		microblogsEntryImpl.setReceiverMicroblogsEntryId(microblogsEntry.getReceiverMicroblogsEntryId());
		microblogsEntryImpl.setSocialRelationType(microblogsEntry.getSocialRelationType());

		return microblogsEntryImpl;
	}

	/**
	 * Returns the microblogs entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the microblogs entry
	 * @return the microblogs entry
	 * @throws com.liferay.portal.NoSuchModelException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MicroblogsEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the microblogs entry with the primary key or throws a {@link com.liferay.microblogs.NoSuchEntryException} if it could not be found.
	 *
	 * @param microblogsEntryId the primary key of the microblogs entry
	 * @return the microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByPrimaryKey(long microblogsEntryId)
		throws NoSuchEntryException, SystemException {
		MicroblogsEntry microblogsEntry = fetchByPrimaryKey(microblogsEntryId);

		if (microblogsEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + microblogsEntryId);
			}

			throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				microblogsEntryId);
		}

		return microblogsEntry;
	}

	/**
	 * Returns the microblogs entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the microblogs entry
	 * @return the microblogs entry, or <code>null</code> if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MicroblogsEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the microblogs entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param microblogsEntryId the primary key of the microblogs entry
	 * @return the microblogs entry, or <code>null</code> if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry fetchByPrimaryKey(long microblogsEntryId)
		throws SystemException {
		MicroblogsEntry microblogsEntry = (MicroblogsEntry)EntityCacheUtil.getResult(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
				MicroblogsEntryImpl.class, microblogsEntryId);

		if (microblogsEntry == _nullMicroblogsEntry) {
			return null;
		}

		if (microblogsEntry == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				microblogsEntry = (MicroblogsEntry)session.get(MicroblogsEntryImpl.class,
						Long.valueOf(microblogsEntryId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (microblogsEntry != null) {
					cacheResult(microblogsEntry);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(MicroblogsEntryModelImpl.ENTITY_CACHE_ENABLED,
						MicroblogsEntryImpl.class, microblogsEntryId,
						_nullMicroblogsEntry);
				}

				closeSession(session);
			}
		}

		return microblogsEntry;
	}

	/**
	 * Returns all the microblogs entries where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the microblogs entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @return the range of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the microblogs entries where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByCompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<MicroblogsEntry> list = (List<MicroblogsEntry>)FinderCacheUtil.getResult(finderPath,
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

			query.append(_SQL_SELECT_MICROBLOGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<MicroblogsEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first microblogs entry in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		List<MicroblogsEntry> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last microblogs entry in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		int count = countByCompanyId(companyId);

		List<MicroblogsEntry> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the microblogs entries before and after the current microblogs entry in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param microblogsEntryId the primary key of the current microblogs entry
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry[] findByCompanyId_PrevAndNext(
		long microblogsEntryId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		MicroblogsEntry microblogsEntry = findByPrimaryKey(microblogsEntryId);

		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry[] array = new MicroblogsEntryImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, microblogsEntry,
					companyId, orderByComparator, true);

			array[1] = microblogsEntry;

			array[2] = getByCompanyId_PrevAndNext(session, microblogsEntry,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MicroblogsEntry getByCompanyId_PrevAndNext(Session session,
		MicroblogsEntry microblogsEntry, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MICROBLOGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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
			query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(microblogsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MicroblogsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the microblogs entries that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByCompanyId(long companyId)
		throws SystemException {
		return filterFindByCompanyId(companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the microblogs entries that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @return the range of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByCompanyId(long companyId,
		int start, int end) throws SystemException {
		return filterFindByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the microblogs entries that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByCompanyId(long companyId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCompanyId(companyId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, MicroblogsEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, MicroblogsEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			return (List<MicroblogsEntry>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where companyId = &#63;.
	 *
	 * @param microblogsEntryId the primary key of the current microblogs entry
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry[] filterFindByCompanyId_PrevAndNext(
		long microblogsEntryId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCompanyId_PrevAndNext(microblogsEntryId, companyId,
				orderByComparator);
		}

		MicroblogsEntry microblogsEntry = findByPrimaryKey(microblogsEntryId);

		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry[] array = new MicroblogsEntryImpl[3];

			array[0] = filterGetByCompanyId_PrevAndNext(session,
					microblogsEntry, companyId, orderByComparator, true);

			array[1] = microblogsEntry;

			array[2] = filterGetByCompanyId_PrevAndNext(session,
					microblogsEntry, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MicroblogsEntry filterGetByCompanyId_PrevAndNext(
		Session session, MicroblogsEntry microblogsEntry, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, MicroblogsEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, MicroblogsEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(microblogsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MicroblogsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the microblogs entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the microblogs entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @return the range of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the microblogs entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<MicroblogsEntry> list = (List<MicroblogsEntry>)FinderCacheUtil.getResult(finderPath,
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

			query.append(_SQL_SELECT_MICROBLOGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<MicroblogsEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first microblogs entry in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		List<MicroblogsEntry> list = findByUserId(userId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last microblogs entry in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		int count = countByUserId(userId);

		List<MicroblogsEntry> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the microblogs entries before and after the current microblogs entry in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param microblogsEntryId the primary key of the current microblogs entry
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry[] findByUserId_PrevAndNext(long microblogsEntryId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		MicroblogsEntry microblogsEntry = findByPrimaryKey(microblogsEntryId);

		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry[] array = new MicroblogsEntryImpl[3];

			array[0] = getByUserId_PrevAndNext(session, microblogsEntry,
					userId, orderByComparator, true);

			array[1] = microblogsEntry;

			array[2] = getByUserId_PrevAndNext(session, microblogsEntry,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MicroblogsEntry getByUserId_PrevAndNext(Session session,
		MicroblogsEntry microblogsEntry, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MICROBLOGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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
			query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(microblogsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MicroblogsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the microblogs entries that the user has permission to view where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByUserId(long userId)
		throws SystemException {
		return filterFindByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the microblogs entries that the user has permission to view where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @return the range of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByUserId(long userId, int start,
		int end) throws SystemException {
		return filterFindByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the microblogs entries that the user has permissions to view where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByUserId(long userId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUserId(userId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, MicroblogsEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, MicroblogsEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			return (List<MicroblogsEntry>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where userId = &#63;.
	 *
	 * @param microblogsEntryId the primary key of the current microblogs entry
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry[] filterFindByUserId_PrevAndNext(
		long microblogsEntryId, long userId, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUserId_PrevAndNext(microblogsEntryId, userId,
				orderByComparator);
		}

		MicroblogsEntry microblogsEntry = findByPrimaryKey(microblogsEntryId);

		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry[] array = new MicroblogsEntryImpl[3];

			array[0] = filterGetByUserId_PrevAndNext(session, microblogsEntry,
					userId, orderByComparator, true);

			array[1] = microblogsEntry;

			array[2] = filterGetByUserId_PrevAndNext(session, microblogsEntry,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MicroblogsEntry filterGetByUserId_PrevAndNext(Session session,
		MicroblogsEntry microblogsEntry, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, MicroblogsEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, MicroblogsEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(microblogsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MicroblogsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the microblogs entries where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @return the matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByU_T(long userId, int type)
		throws SystemException {
		return findByU_T(userId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the microblogs entries where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @return the range of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByU_T(long userId, int type, int start,
		int end) throws SystemException {
		return findByU_T(userId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the microblogs entries where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByU_T(long userId, int type, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_T;
			finderArgs = new Object[] { userId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_T;
			finderArgs = new Object[] {
					userId, type,
					
					start, end, orderByComparator
				};
		}

		List<MicroblogsEntry> list = (List<MicroblogsEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_MICROBLOGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_T_USERID_2);

			query.append(_FINDER_COLUMN_U_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(type);

				list = (List<MicroblogsEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByU_T_First(long userId, int type,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		List<MicroblogsEntry> list = findByU_T(userId, type, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByU_T_Last(long userId, int type,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		int count = countByU_T(userId, type);

		List<MicroblogsEntry> list = findByU_T(userId, type, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the microblogs entries before and after the current microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param microblogsEntryId the primary key of the current microblogs entry
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry[] findByU_T_PrevAndNext(long microblogsEntryId,
		long userId, int type, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		MicroblogsEntry microblogsEntry = findByPrimaryKey(microblogsEntryId);

		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry[] array = new MicroblogsEntryImpl[3];

			array[0] = getByU_T_PrevAndNext(session, microblogsEntry, userId,
					type, orderByComparator, true);

			array[1] = microblogsEntry;

			array[2] = getByU_T_PrevAndNext(session, microblogsEntry, userId,
					type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MicroblogsEntry getByU_T_PrevAndNext(Session session,
		MicroblogsEntry microblogsEntry, long userId, int type,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MICROBLOGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_U_T_USERID_2);

		query.append(_FINDER_COLUMN_U_T_TYPE_2);

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
			query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(microblogsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MicroblogsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the microblogs entries that the user has permission to view where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @return the matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByU_T(long userId, int type)
		throws SystemException {
		return filterFindByU_T(userId, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the microblogs entries that the user has permission to view where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @return the range of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByU_T(long userId, int type,
		int start, int end) throws SystemException {
		return filterFindByU_T(userId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the microblogs entries that the user has permissions to view where userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByU_T(long userId, int type,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByU_T(userId, type, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_U_T_USERID_2);

		query.append(_FINDER_COLUMN_U_T_TYPE_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, MicroblogsEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, MicroblogsEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			qPos.add(type);

			return (List<MicroblogsEntry>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where userId = &#63; and type = &#63;.
	 *
	 * @param microblogsEntryId the primary key of the current microblogs entry
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry[] filterFindByU_T_PrevAndNext(
		long microblogsEntryId, long userId, int type,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByU_T_PrevAndNext(microblogsEntryId, userId, type,
				orderByComparator);
		}

		MicroblogsEntry microblogsEntry = findByPrimaryKey(microblogsEntryId);

		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry[] array = new MicroblogsEntryImpl[3];

			array[0] = filterGetByU_T_PrevAndNext(session, microblogsEntry,
					userId, type, orderByComparator, true);

			array[1] = microblogsEntry;

			array[2] = filterGetByU_T_PrevAndNext(session, microblogsEntry,
					userId, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MicroblogsEntry filterGetByU_T_PrevAndNext(Session session,
		MicroblogsEntry microblogsEntry, long userId, int type,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_U_T_USERID_2);

		query.append(_FINDER_COLUMN_U_T_TYPE_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, MicroblogsEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, MicroblogsEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(microblogsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MicroblogsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	 *
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @return the matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByT_R(int type, long receiverUserId)
		throws SystemException {
		return findByT_R(type, receiverUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @return the range of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByT_R(int type, long receiverUserId,
		int start, int end) throws SystemException {
		return findByT_R(type, receiverUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByT_R(int type, long receiverUserId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_R;
			finderArgs = new Object[] { type, receiverUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_T_R;
			finderArgs = new Object[] {
					type, receiverUserId,
					
					start, end, orderByComparator
				};
		}

		List<MicroblogsEntry> list = (List<MicroblogsEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_MICROBLOGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_T_R_TYPE_2);

			query.append(_FINDER_COLUMN_T_R_RECEIVERUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(type);

				qPos.add(receiverUserId);

				list = (List<MicroblogsEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first microblogs entry in the ordered set where type = &#63; and receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByT_R_First(int type, long receiverUserId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		List<MicroblogsEntry> list = findByT_R(type, receiverUserId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("type=");
			msg.append(type);

			msg.append(", receiverUserId=");
			msg.append(receiverUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last microblogs entry in the ordered set where type = &#63; and receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByT_R_Last(int type, long receiverUserId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		int count = countByT_R(type, receiverUserId);

		List<MicroblogsEntry> list = findByT_R(type, receiverUserId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("type=");
			msg.append(type);

			msg.append(", receiverUserId=");
			msg.append(receiverUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the microblogs entries before and after the current microblogs entry in the ordered set where type = &#63; and receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param microblogsEntryId the primary key of the current microblogs entry
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry[] findByT_R_PrevAndNext(long microblogsEntryId,
		int type, long receiverUserId, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		MicroblogsEntry microblogsEntry = findByPrimaryKey(microblogsEntryId);

		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry[] array = new MicroblogsEntryImpl[3];

			array[0] = getByT_R_PrevAndNext(session, microblogsEntry, type,
					receiverUserId, orderByComparator, true);

			array[1] = microblogsEntry;

			array[2] = getByT_R_PrevAndNext(session, microblogsEntry, type,
					receiverUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MicroblogsEntry getByT_R_PrevAndNext(Session session,
		MicroblogsEntry microblogsEntry, int type, long receiverUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MICROBLOGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_T_R_TYPE_2);

		query.append(_FINDER_COLUMN_T_R_RECEIVERUSERID_2);

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
			query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(type);

		qPos.add(receiverUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(microblogsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MicroblogsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the microblogs entries that the user has permission to view where type = &#63; and receiverUserId = &#63;.
	 *
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @return the matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByT_R(int type, long receiverUserId)
		throws SystemException {
		return filterFindByT_R(type, receiverUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the microblogs entries that the user has permission to view where type = &#63; and receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @return the range of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByT_R(int type, long receiverUserId,
		int start, int end) throws SystemException {
		return filterFindByT_R(type, receiverUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the microblogs entries that the user has permissions to view where type = &#63; and receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByT_R(int type, long receiverUserId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByT_R(type, receiverUserId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_T_R_TYPE_2);

		query.append(_FINDER_COLUMN_T_R_RECEIVERUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, MicroblogsEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, MicroblogsEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(type);

			qPos.add(receiverUserId);

			return (List<MicroblogsEntry>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where type = &#63; and receiverUserId = &#63;.
	 *
	 * @param microblogsEntryId the primary key of the current microblogs entry
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry[] filterFindByT_R_PrevAndNext(
		long microblogsEntryId, int type, long receiverUserId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByT_R_PrevAndNext(microblogsEntryId, type,
				receiverUserId, orderByComparator);
		}

		MicroblogsEntry microblogsEntry = findByPrimaryKey(microblogsEntryId);

		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry[] array = new MicroblogsEntryImpl[3];

			array[0] = filterGetByT_R_PrevAndNext(session, microblogsEntry,
					type, receiverUserId, orderByComparator, true);

			array[1] = microblogsEntry;

			array[2] = filterGetByT_R_PrevAndNext(session, microblogsEntry,
					type, receiverUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MicroblogsEntry filterGetByT_R_PrevAndNext(Session session,
		MicroblogsEntry microblogsEntry, int type, long receiverUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_T_R_TYPE_2);

		query.append(_FINDER_COLUMN_T_R_RECEIVERUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, MicroblogsEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, MicroblogsEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(type);

		qPos.add(receiverUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(microblogsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MicroblogsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the microblogs entries where type = &#63; and receiverMicroblogsEntryId = &#63;.
	 *
	 * @param type the type
	 * @param receiverMicroblogsEntryId the receiver microblogs entry ID
	 * @return the matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByT_RMEI(int type,
		long receiverMicroblogsEntryId) throws SystemException {
		return findByT_RMEI(type, receiverMicroblogsEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the microblogs entries where type = &#63; and receiverMicroblogsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param receiverMicroblogsEntryId the receiver microblogs entry ID
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @return the range of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByT_RMEI(int type,
		long receiverMicroblogsEntryId, int start, int end)
		throws SystemException {
		return findByT_RMEI(type, receiverMicroblogsEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the microblogs entries where type = &#63; and receiverMicroblogsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param receiverMicroblogsEntryId the receiver microblogs entry ID
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findByT_RMEI(int type,
		long receiverMicroblogsEntryId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_T_RMEI;
			finderArgs = new Object[] { type, receiverMicroblogsEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_T_RMEI;
			finderArgs = new Object[] {
					type, receiverMicroblogsEntryId,
					
					start, end, orderByComparator
				};
		}

		List<MicroblogsEntry> list = (List<MicroblogsEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_MICROBLOGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_T_RMEI_TYPE_2);

			query.append(_FINDER_COLUMN_T_RMEI_RECEIVERMICROBLOGSENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(type);

				qPos.add(receiverMicroblogsEntryId);

				list = (List<MicroblogsEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first microblogs entry in the ordered set where type = &#63; and receiverMicroblogsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param receiverMicroblogsEntryId the receiver microblogs entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByT_RMEI_First(int type,
		long receiverMicroblogsEntryId, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		List<MicroblogsEntry> list = findByT_RMEI(type,
				receiverMicroblogsEntryId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("type=");
			msg.append(type);

			msg.append(", receiverMicroblogsEntryId=");
			msg.append(receiverMicroblogsEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last microblogs entry in the ordered set where type = &#63; and receiverMicroblogsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param receiverMicroblogsEntryId the receiver microblogs entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry findByT_RMEI_Last(int type,
		long receiverMicroblogsEntryId, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		int count = countByT_RMEI(type, receiverMicroblogsEntryId);

		List<MicroblogsEntry> list = findByT_RMEI(type,
				receiverMicroblogsEntryId, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("type=");
			msg.append(type);

			msg.append(", receiverMicroblogsEntryId=");
			msg.append(receiverMicroblogsEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the microblogs entries before and after the current microblogs entry in the ordered set where type = &#63; and receiverMicroblogsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param microblogsEntryId the primary key of the current microblogs entry
	 * @param type the type
	 * @param receiverMicroblogsEntryId the receiver microblogs entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry[] findByT_RMEI_PrevAndNext(long microblogsEntryId,
		int type, long receiverMicroblogsEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		MicroblogsEntry microblogsEntry = findByPrimaryKey(microblogsEntryId);

		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry[] array = new MicroblogsEntryImpl[3];

			array[0] = getByT_RMEI_PrevAndNext(session, microblogsEntry, type,
					receiverMicroblogsEntryId, orderByComparator, true);

			array[1] = microblogsEntry;

			array[2] = getByT_RMEI_PrevAndNext(session, microblogsEntry, type,
					receiverMicroblogsEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MicroblogsEntry getByT_RMEI_PrevAndNext(Session session,
		MicroblogsEntry microblogsEntry, int type,
		long receiverMicroblogsEntryId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MICROBLOGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_T_RMEI_TYPE_2);

		query.append(_FINDER_COLUMN_T_RMEI_RECEIVERMICROBLOGSENTRYID_2);

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
			query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(type);

		qPos.add(receiverMicroblogsEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(microblogsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MicroblogsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the microblogs entries that the user has permission to view where type = &#63; and receiverMicroblogsEntryId = &#63;.
	 *
	 * @param type the type
	 * @param receiverMicroblogsEntryId the receiver microblogs entry ID
	 * @return the matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByT_RMEI(int type,
		long receiverMicroblogsEntryId) throws SystemException {
		return filterFindByT_RMEI(type, receiverMicroblogsEntryId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the microblogs entries that the user has permission to view where type = &#63; and receiverMicroblogsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param receiverMicroblogsEntryId the receiver microblogs entry ID
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @return the range of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByT_RMEI(int type,
		long receiverMicroblogsEntryId, int start, int end)
		throws SystemException {
		return filterFindByT_RMEI(type, receiverMicroblogsEntryId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the microblogs entries that the user has permissions to view where type = &#63; and receiverMicroblogsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param receiverMicroblogsEntryId the receiver microblogs entry ID
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> filterFindByT_RMEI(int type,
		long receiverMicroblogsEntryId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByT_RMEI(type, receiverMicroblogsEntryId, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_T_RMEI_TYPE_2);

		query.append(_FINDER_COLUMN_T_RMEI_RECEIVERMICROBLOGSENTRYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, MicroblogsEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, MicroblogsEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(type);

			qPos.add(receiverMicroblogsEntryId);

			return (List<MicroblogsEntry>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where type = &#63; and receiverMicroblogsEntryId = &#63;.
	 *
	 * @param microblogsEntryId the primary key of the current microblogs entry
	 * @param type the type
	 * @param receiverMicroblogsEntryId the receiver microblogs entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next microblogs entry
	 * @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public MicroblogsEntry[] filterFindByT_RMEI_PrevAndNext(
		long microblogsEntryId, int type, long receiverMicroblogsEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByT_RMEI_PrevAndNext(microblogsEntryId, type,
				receiverMicroblogsEntryId, orderByComparator);
		}

		MicroblogsEntry microblogsEntry = findByPrimaryKey(microblogsEntryId);

		Session session = null;

		try {
			session = openSession();

			MicroblogsEntry[] array = new MicroblogsEntryImpl[3];

			array[0] = filterGetByT_RMEI_PrevAndNext(session, microblogsEntry,
					type, receiverMicroblogsEntryId, orderByComparator, true);

			array[1] = microblogsEntry;

			array[2] = filterGetByT_RMEI_PrevAndNext(session, microblogsEntry,
					type, receiverMicroblogsEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MicroblogsEntry filterGetByT_RMEI_PrevAndNext(Session session,
		MicroblogsEntry microblogsEntry, int type,
		long receiverMicroblogsEntryId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_T_RMEI_TYPE_2);

		query.append(_FINDER_COLUMN_T_RMEI_RECEIVERMICROBLOGSENTRYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(MicroblogsEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, MicroblogsEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, MicroblogsEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(type);

		qPos.add(receiverMicroblogsEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(microblogsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MicroblogsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the microblogs entries.
	 *
	 * @return the microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the microblogs entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @return the range of microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the microblogs entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of microblogs entries
	 * @param end the upper bound of the range of microblogs entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<MicroblogsEntry> findAll(int start, int end,
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

		List<MicroblogsEntry> list = (List<MicroblogsEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_MICROBLOGSENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MICROBLOGSENTRY.concat(MicroblogsEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<MicroblogsEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<MicroblogsEntry>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the microblogs entries where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (MicroblogsEntry microblogsEntry : findByCompanyId(companyId)) {
			remove(microblogsEntry);
		}
	}

	/**
	 * Removes all the microblogs entries where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (MicroblogsEntry microblogsEntry : findByUserId(userId)) {
			remove(microblogsEntry);
		}
	}

	/**
	 * Removes all the microblogs entries where userId = &#63; and type = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_T(long userId, int type) throws SystemException {
		for (MicroblogsEntry microblogsEntry : findByU_T(userId, type)) {
			remove(microblogsEntry);
		}
	}

	/**
	 * Removes all the microblogs entries where type = &#63; and receiverUserId = &#63; from the database.
	 *
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByT_R(int type, long receiverUserId)
		throws SystemException {
		for (MicroblogsEntry microblogsEntry : findByT_R(type, receiverUserId)) {
			remove(microblogsEntry);
		}
	}

	/**
	 * Removes all the microblogs entries where type = &#63; and receiverMicroblogsEntryId = &#63; from the database.
	 *
	 * @param type the type
	 * @param receiverMicroblogsEntryId the receiver microblogs entry ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByT_RMEI(int type, long receiverMicroblogsEntryId)
		throws SystemException {
		for (MicroblogsEntry microblogsEntry : findByT_RMEI(type,
				receiverMicroblogsEntryId)) {
			remove(microblogsEntry);
		}
	}

	/**
	 * Removes all the microblogs entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (MicroblogsEntry microblogsEntry : findAll()) {
			remove(microblogsEntry);
		}
	}

	/**
	 * Returns the number of microblogs entries where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MICROBLOGSENTRY_WHERE);

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
	 * Returns the number of microblogs entries that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByCompanyId(long companyId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByCompanyId(companyId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_MICROBLOGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of microblogs entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MICROBLOGSENTRY_WHERE);

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
	 * Returns the number of microblogs entries that the user has permission to view where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByUserId(long userId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByUserId(userId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_MICROBLOGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of microblogs entries where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @return the number of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_T(long userId, int type) throws SystemException {
		Object[] finderArgs = new Object[] { userId, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MICROBLOGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_T_USERID_2);

			query.append(_FINDER_COLUMN_U_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(type);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_T, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of microblogs entries that the user has permission to view where userId = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param type the type
	 * @return the number of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByU_T(long userId, int type)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByU_T(userId, type);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_MICROBLOGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_U_T_USERID_2);

		query.append(_FINDER_COLUMN_U_T_TYPE_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			qPos.add(type);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of microblogs entries where type = &#63; and receiverUserId = &#63;.
	 *
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @return the number of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByT_R(int type, long receiverUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] { type, receiverUserId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_T_R,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MICROBLOGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_T_R_TYPE_2);

			query.append(_FINDER_COLUMN_T_R_RECEIVERUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(type);

				qPos.add(receiverUserId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_T_R, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of microblogs entries that the user has permission to view where type = &#63; and receiverUserId = &#63;.
	 *
	 * @param type the type
	 * @param receiverUserId the receiver user ID
	 * @return the number of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByT_R(int type, long receiverUserId)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByT_R(type, receiverUserId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_MICROBLOGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_T_R_TYPE_2);

		query.append(_FINDER_COLUMN_T_R_RECEIVERUSERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(type);

			qPos.add(receiverUserId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of microblogs entries where type = &#63; and receiverMicroblogsEntryId = &#63;.
	 *
	 * @param type the type
	 * @param receiverMicroblogsEntryId the receiver microblogs entry ID
	 * @return the number of matching microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByT_RMEI(int type, long receiverMicroblogsEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { type, receiverMicroblogsEntryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_T_RMEI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MICROBLOGSENTRY_WHERE);

			query.append(_FINDER_COLUMN_T_RMEI_TYPE_2);

			query.append(_FINDER_COLUMN_T_RMEI_RECEIVERMICROBLOGSENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(type);

				qPos.add(receiverMicroblogsEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_T_RMEI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of microblogs entries that the user has permission to view where type = &#63; and receiverMicroblogsEntryId = &#63;.
	 *
	 * @param type the type
	 * @param receiverMicroblogsEntryId the receiver microblogs entry ID
	 * @return the number of matching microblogs entries that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByT_RMEI(int type, long receiverMicroblogsEntryId)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByT_RMEI(type, receiverMicroblogsEntryId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_MICROBLOGSENTRY_WHERE);

		query.append(_FINDER_COLUMN_T_RMEI_TYPE_2);

		query.append(_FINDER_COLUMN_T_RMEI_RECEIVERMICROBLOGSENTRYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				MicroblogsEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(type);

			qPos.add(receiverMicroblogsEntryId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of microblogs entries.
	 *
	 * @return the number of microblogs entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MICROBLOGSENTRY);

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
	 * Initializes the microblogs entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.microblogs.model.MicroblogsEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<MicroblogsEntry>> listenersList = new ArrayList<ModelListener<MicroblogsEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<MicroblogsEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(MicroblogsEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = MicroblogsEntryPersistence.class)
	protected MicroblogsEntryPersistence microblogsEntryPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_MICROBLOGSENTRY = "SELECT microblogsEntry FROM MicroblogsEntry microblogsEntry";
	private static final String _SQL_SELECT_MICROBLOGSENTRY_WHERE = "SELECT microblogsEntry FROM MicroblogsEntry microblogsEntry WHERE ";
	private static final String _SQL_COUNT_MICROBLOGSENTRY = "SELECT COUNT(microblogsEntry) FROM MicroblogsEntry microblogsEntry";
	private static final String _SQL_COUNT_MICROBLOGSENTRY_WHERE = "SELECT COUNT(microblogsEntry) FROM MicroblogsEntry microblogsEntry WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "microblogsEntry.companyId = ?";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "microblogsEntry.userId = ?";
	private static final String _FINDER_COLUMN_U_T_USERID_2 = "microblogsEntry.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_T_TYPE_2 = "microblogsEntry.type = ?";
	private static final String _FINDER_COLUMN_T_R_TYPE_2 = "microblogsEntry.type = ? AND ";
	private static final String _FINDER_COLUMN_T_R_RECEIVERUSERID_2 = "microblogsEntry.receiverUserId = ?";
	private static final String _FINDER_COLUMN_T_RMEI_TYPE_2 = "microblogsEntry.type = ? AND ";
	private static final String _FINDER_COLUMN_T_RMEI_RECEIVERMICROBLOGSENTRYID_2 =
		"microblogsEntry.receiverMicroblogsEntryId = ?";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "microblogsEntry.microblogsEntryId";
	private static final String _FILTER_SQL_SELECT_MICROBLOGSENTRY_WHERE = "SELECT DISTINCT {microblogsEntry.*} FROM MicroblogsEntry microblogsEntry WHERE ";
	private static final String _FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {MicroblogsEntry.*} FROM (SELECT DISTINCT microblogsEntry.microblogsEntryId FROM MicroblogsEntry microblogsEntry WHERE ";
	private static final String _FILTER_SQL_SELECT_MICROBLOGSENTRY_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN MicroblogsEntry ON TEMP_TABLE.microblogsEntryId = MicroblogsEntry.microblogsEntryId";
	private static final String _FILTER_SQL_COUNT_MICROBLOGSENTRY_WHERE = "SELECT COUNT(DISTINCT microblogsEntry.microblogsEntryId) AS COUNT_VALUE FROM MicroblogsEntry microblogsEntry WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "microblogsEntry";
	private static final String _FILTER_ENTITY_TABLE = "MicroblogsEntry";
	private static final String _ORDER_BY_ENTITY_ALIAS = "microblogsEntry.";
	private static final String _ORDER_BY_ENTITY_TABLE = "MicroblogsEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MicroblogsEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MicroblogsEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(MicroblogsEntryPersistenceImpl.class);
	private static MicroblogsEntry _nullMicroblogsEntry = new MicroblogsEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<MicroblogsEntry> toCacheModel() {
				return _nullMicroblogsEntryCacheModel;
			}
		};

	private static CacheModel<MicroblogsEntry> _nullMicroblogsEntryCacheModel = new CacheModel<MicroblogsEntry>() {
			public MicroblogsEntry toEntityModel() {
				return _nullMicroblogsEntry;
			}
		};
}