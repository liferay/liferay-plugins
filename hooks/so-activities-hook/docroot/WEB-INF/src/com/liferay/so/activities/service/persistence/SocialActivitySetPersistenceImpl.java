/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.so.activities.service.persistence;

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
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.so.activities.NoSuchActivitySetException;
import com.liferay.so.activities.model.SocialActivitySet;
import com.liferay.so.activities.model.impl.SocialActivitySetImpl;
import com.liferay.so.activities.model.impl.SocialActivitySetModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the social activity set service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivitySetPersistence
 * @see SocialActivitySetUtil
 * @generated
 */
public class SocialActivitySetPersistenceImpl extends BasePersistenceImpl<SocialActivitySet>
	implements SocialActivitySetPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SocialActivitySetUtil} to access the social activity set persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SocialActivitySetImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetModelImpl.FINDER_CACHE_ENABLED,
			SocialActivitySetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetModelImpl.FINDER_CACHE_ENABLED,
			SocialActivitySetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			SocialActivitySetModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetModelImpl.FINDER_CACHE_ENABLED,
			SocialActivitySetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetModelImpl.FINDER_CACHE_ENABLED,
			SocialActivitySetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			SocialActivitySetModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U_T = new FinderPath(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetModelImpl.FINDER_CACHE_ENABLED,
			SocialActivitySetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_U_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_T = new FinderPath(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetModelImpl.FINDER_CACHE_ENABLED,
			SocialActivitySetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_U_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			SocialActivitySetModelImpl.GROUPID_COLUMN_BITMASK |
			SocialActivitySetModelImpl.USERID_COLUMN_BITMASK |
			SocialActivitySetModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_U_T = new FinderPath(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_T = new FinderPath(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetModelImpl.FINDER_CACHE_ENABLED,
			SocialActivitySetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T = new FinderPath(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetModelImpl.FINDER_CACHE_ENABLED,
			SocialActivitySetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			SocialActivitySetModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			SocialActivitySetModelImpl.CLASSPK_COLUMN_BITMASK |
			SocialActivitySetModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_T = new FinderPath(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U_C_T = new FinderPath(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetModelImpl.FINDER_CACHE_ENABLED,
			SocialActivitySetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_U_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_C_T =
		new FinderPath(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetModelImpl.FINDER_CACHE_ENABLED,
			SocialActivitySetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_U_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			SocialActivitySetModelImpl.GROUPID_COLUMN_BITMASK |
			SocialActivitySetModelImpl.USERID_COLUMN_BITMASK |
			SocialActivitySetModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			SocialActivitySetModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_U_C_T = new FinderPath(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_C_C_T = new FinderPath(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetModelImpl.FINDER_CACHE_ENABLED,
			SocialActivitySetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_C_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C_C_T =
		new FinderPath(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetModelImpl.FINDER_CACHE_ENABLED,
			SocialActivitySetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_C_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			SocialActivitySetModelImpl.USERID_COLUMN_BITMASK |
			SocialActivitySetModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			SocialActivitySetModelImpl.CLASSPK_COLUMN_BITMASK |
			SocialActivitySetModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_C_C_T = new FinderPath(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_C_C_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetModelImpl.FINDER_CACHE_ENABLED,
			SocialActivitySetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetModelImpl.FINDER_CACHE_ENABLED,
			SocialActivitySetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the social activity set in the entity cache if it is enabled.
	 *
	 * @param socialActivitySet the social activity set
	 */
	public void cacheResult(SocialActivitySet socialActivitySet) {
		EntityCacheUtil.putResult(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetImpl.class, socialActivitySet.getPrimaryKey(),
			socialActivitySet);

		socialActivitySet.resetOriginalValues();
	}

	/**
	 * Caches the social activity sets in the entity cache if it is enabled.
	 *
	 * @param socialActivitySets the social activity sets
	 */
	public void cacheResult(List<SocialActivitySet> socialActivitySets) {
		for (SocialActivitySet socialActivitySet : socialActivitySets) {
			if (EntityCacheUtil.getResult(
						SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
						SocialActivitySetImpl.class,
						socialActivitySet.getPrimaryKey()) == null) {
				cacheResult(socialActivitySet);
			}
			else {
				socialActivitySet.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all social activity sets.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SocialActivitySetImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SocialActivitySetImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the social activity set.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SocialActivitySet socialActivitySet) {
		EntityCacheUtil.removeResult(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetImpl.class, socialActivitySet.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SocialActivitySet> socialActivitySets) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SocialActivitySet socialActivitySet : socialActivitySets) {
			EntityCacheUtil.removeResult(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
				SocialActivitySetImpl.class, socialActivitySet.getPrimaryKey());
		}
	}

	/**
	 * Creates a new social activity set with the primary key. Does not add the social activity set to the database.
	 *
	 * @param activitySetId the primary key for the new social activity set
	 * @return the new social activity set
	 */
	public SocialActivitySet create(long activitySetId) {
		SocialActivitySet socialActivitySet = new SocialActivitySetImpl();

		socialActivitySet.setNew(true);
		socialActivitySet.setPrimaryKey(activitySetId);

		return socialActivitySet;
	}

	/**
	 * Removes the social activity set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param activitySetId the primary key of the social activity set
	 * @return the social activity set that was removed
	 * @throws com.liferay.so.activities.NoSuchActivitySetException if a social activity set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet remove(long activitySetId)
		throws NoSuchActivitySetException, SystemException {
		return remove(Long.valueOf(activitySetId));
	}

	/**
	 * Removes the social activity set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the social activity set
	 * @return the social activity set that was removed
	 * @throws com.liferay.so.activities.NoSuchActivitySetException if a social activity set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivitySet remove(Serializable primaryKey)
		throws NoSuchActivitySetException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SocialActivitySet socialActivitySet = (SocialActivitySet)session.get(SocialActivitySetImpl.class,
					primaryKey);

			if (socialActivitySet == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchActivitySetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(socialActivitySet);
		}
		catch (NoSuchActivitySetException nsee) {
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
	protected SocialActivitySet removeImpl(SocialActivitySet socialActivitySet)
		throws SystemException {
		socialActivitySet = toUnwrappedModel(socialActivitySet);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, socialActivitySet);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(socialActivitySet);

		return socialActivitySet;
	}

	@Override
	public SocialActivitySet updateImpl(
		com.liferay.so.activities.model.SocialActivitySet socialActivitySet,
		boolean merge) throws SystemException {
		socialActivitySet = toUnwrappedModel(socialActivitySet);

		boolean isNew = socialActivitySet.isNew();

		SocialActivitySetModelImpl socialActivitySetModelImpl = (SocialActivitySetModelImpl)socialActivitySet;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, socialActivitySet, merge);

			socialActivitySet.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SocialActivitySetModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((socialActivitySetModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(socialActivitySetModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] {
						Long.valueOf(socialActivitySetModelImpl.getGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((socialActivitySetModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(socialActivitySetModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(socialActivitySetModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((socialActivitySetModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(socialActivitySetModelImpl.getOriginalGroupId()),
						Long.valueOf(socialActivitySetModelImpl.getOriginalUserId()),
						Integer.valueOf(socialActivitySetModelImpl.getOriginalType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_T,
					args);

				args = new Object[] {
						Long.valueOf(socialActivitySetModelImpl.getGroupId()),
						Long.valueOf(socialActivitySetModelImpl.getUserId()),
						Integer.valueOf(socialActivitySetModelImpl.getType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_T,
					args);
			}

			if ((socialActivitySetModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(socialActivitySetModelImpl.getOriginalClassNameId()),
						Long.valueOf(socialActivitySetModelImpl.getOriginalClassPK()),
						Integer.valueOf(socialActivitySetModelImpl.getOriginalType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T,
					args);

				args = new Object[] {
						Long.valueOf(socialActivitySetModelImpl.getClassNameId()),
						Long.valueOf(socialActivitySetModelImpl.getClassPK()),
						Integer.valueOf(socialActivitySetModelImpl.getType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T,
					args);
			}

			if ((socialActivitySetModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_C_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(socialActivitySetModelImpl.getOriginalGroupId()),
						Long.valueOf(socialActivitySetModelImpl.getOriginalUserId()),
						Long.valueOf(socialActivitySetModelImpl.getOriginalClassNameId()),
						Integer.valueOf(socialActivitySetModelImpl.getOriginalType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U_C_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_C_T,
					args);

				args = new Object[] {
						Long.valueOf(socialActivitySetModelImpl.getGroupId()),
						Long.valueOf(socialActivitySetModelImpl.getUserId()),
						Long.valueOf(socialActivitySetModelImpl.getClassNameId()),
						Integer.valueOf(socialActivitySetModelImpl.getType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U_C_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_C_T,
					args);
			}

			if ((socialActivitySetModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C_C_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(socialActivitySetModelImpl.getOriginalUserId()),
						Long.valueOf(socialActivitySetModelImpl.getOriginalClassNameId()),
						Long.valueOf(socialActivitySetModelImpl.getOriginalClassPK()),
						Integer.valueOf(socialActivitySetModelImpl.getOriginalType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_C_C_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C_C_T,
					args);

				args = new Object[] {
						Long.valueOf(socialActivitySetModelImpl.getUserId()),
						Long.valueOf(socialActivitySetModelImpl.getClassNameId()),
						Long.valueOf(socialActivitySetModelImpl.getClassPK()),
						Integer.valueOf(socialActivitySetModelImpl.getType())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_C_C_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C_C_T,
					args);
			}
		}

		EntityCacheUtil.putResult(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
			SocialActivitySetImpl.class, socialActivitySet.getPrimaryKey(),
			socialActivitySet);

		return socialActivitySet;
	}

	protected SocialActivitySet toUnwrappedModel(
		SocialActivitySet socialActivitySet) {
		if (socialActivitySet instanceof SocialActivitySetImpl) {
			return socialActivitySet;
		}

		SocialActivitySetImpl socialActivitySetImpl = new SocialActivitySetImpl();

		socialActivitySetImpl.setNew(socialActivitySet.isNew());
		socialActivitySetImpl.setPrimaryKey(socialActivitySet.getPrimaryKey());

		socialActivitySetImpl.setActivitySetId(socialActivitySet.getActivitySetId());
		socialActivitySetImpl.setGroupId(socialActivitySet.getGroupId());
		socialActivitySetImpl.setCompanyId(socialActivitySet.getCompanyId());
		socialActivitySetImpl.setUserId(socialActivitySet.getUserId());
		socialActivitySetImpl.setCreateDate(socialActivitySet.getCreateDate());
		socialActivitySetImpl.setModifiedDate(socialActivitySet.getModifiedDate());
		socialActivitySetImpl.setClassNameId(socialActivitySet.getClassNameId());
		socialActivitySetImpl.setClassPK(socialActivitySet.getClassPK());
		socialActivitySetImpl.setType(socialActivitySet.getType());
		socialActivitySetImpl.setExtraData(socialActivitySet.getExtraData());
		socialActivitySetImpl.setActivityCount(socialActivitySet.getActivityCount());

		return socialActivitySetImpl;
	}

	/**
	 * Returns the social activity set with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the social activity set
	 * @return the social activity set
	 * @throws com.liferay.portal.NoSuchModelException if a social activity set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivitySet findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the social activity set with the primary key or throws a {@link com.liferay.so.activities.NoSuchActivitySetException} if it could not be found.
	 *
	 * @param activitySetId the primary key of the social activity set
	 * @return the social activity set
	 * @throws com.liferay.so.activities.NoSuchActivitySetException if a social activity set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet findByPrimaryKey(long activitySetId)
		throws NoSuchActivitySetException, SystemException {
		SocialActivitySet socialActivitySet = fetchByPrimaryKey(activitySetId);

		if (socialActivitySet == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + activitySetId);
			}

			throw new NoSuchActivitySetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				activitySetId);
		}

		return socialActivitySet;
	}

	/**
	 * Returns the social activity set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the social activity set
	 * @return the social activity set, or <code>null</code> if a social activity set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SocialActivitySet fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the social activity set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param activitySetId the primary key of the social activity set
	 * @return the social activity set, or <code>null</code> if a social activity set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet fetchByPrimaryKey(long activitySetId)
		throws SystemException {
		SocialActivitySet socialActivitySet = (SocialActivitySet)EntityCacheUtil.getResult(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
				SocialActivitySetImpl.class, activitySetId);

		if (socialActivitySet == _nullSocialActivitySet) {
			return null;
		}

		if (socialActivitySet == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				socialActivitySet = (SocialActivitySet)session.get(SocialActivitySetImpl.class,
						Long.valueOf(activitySetId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (socialActivitySet != null) {
					cacheResult(socialActivitySet);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(SocialActivitySetModelImpl.ENTITY_CACHE_ENABLED,
						SocialActivitySetImpl.class, activitySetId,
						_nullSocialActivitySet);
				}

				closeSession(session);
			}
		}

		return socialActivitySet;
	}

	/**
	 * Returns all the social activity sets where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivitySet> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social activity sets where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of social activity sets
	 * @param end the upper bound of the range of social activity sets (not inclusive)
	 * @return the range of matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivitySet> findByGroupId(long groupId, int start,
		int end) throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social activity sets where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of social activity sets
	 * @param end the upper bound of the range of social activity sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivitySet> findByGroupId(long groupId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<SocialActivitySet> list = (List<SocialActivitySet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SocialActivitySet socialActivitySet : list) {
				if ((groupId != socialActivitySet.getGroupId())) {
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

			query.append(_SQL_SELECT_SOCIALACTIVITYSET_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(SocialActivitySetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				list = (List<SocialActivitySet>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first social activity set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity set
	 * @throws com.liferay.so.activities.NoSuchActivitySetException if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchActivitySetException, SystemException {
		SocialActivitySet socialActivitySet = fetchByGroupId_First(groupId,
				orderByComparator);

		if (socialActivitySet != null) {
			return socialActivitySet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivitySetException(msg.toString());
	}

	/**
	 * Returns the first social activity set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity set, or <code>null</code> if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<SocialActivitySet> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social activity set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity set
	 * @throws com.liferay.so.activities.NoSuchActivitySetException if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchActivitySetException, SystemException {
		SocialActivitySet socialActivitySet = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (socialActivitySet != null) {
			return socialActivitySet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivitySetException(msg.toString());
	}

	/**
	 * Returns the last social activity set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity set, or <code>null</code> if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		List<SocialActivitySet> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social activity sets before and after the current social activity set in the ordered set where groupId = &#63;.
	 *
	 * @param activitySetId the primary key of the current social activity set
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social activity set
	 * @throws com.liferay.so.activities.NoSuchActivitySetException if a social activity set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet[] findByGroupId_PrevAndNext(long activitySetId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchActivitySetException, SystemException {
		SocialActivitySet socialActivitySet = findByPrimaryKey(activitySetId);

		Session session = null;

		try {
			session = openSession();

			SocialActivitySet[] array = new SocialActivitySetImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, socialActivitySet,
					groupId, orderByComparator, true);

			array[1] = socialActivitySet;

			array[2] = getByGroupId_PrevAndNext(session, socialActivitySet,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SocialActivitySet getByGroupId_PrevAndNext(Session session,
		SocialActivitySet socialActivitySet, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SOCIALACTIVITYSET_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			query.append(SocialActivitySetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(socialActivitySet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SocialActivitySet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the social activity sets where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivitySet> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social activity sets where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of social activity sets
	 * @param end the upper bound of the range of social activity sets (not inclusive)
	 * @return the range of matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivitySet> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social activity sets where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of social activity sets
	 * @param end the upper bound of the range of social activity sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivitySet> findByUserId(long userId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
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

		List<SocialActivitySet> list = (List<SocialActivitySet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SocialActivitySet socialActivitySet : list) {
				if ((userId != socialActivitySet.getUserId())) {
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

			query.append(_SQL_SELECT_SOCIALACTIVITYSET_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(SocialActivitySetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<SocialActivitySet>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first social activity set in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity set
	 * @throws com.liferay.so.activities.NoSuchActivitySetException if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchActivitySetException, SystemException {
		SocialActivitySet socialActivitySet = fetchByUserId_First(userId,
				orderByComparator);

		if (socialActivitySet != null) {
			return socialActivitySet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivitySetException(msg.toString());
	}

	/**
	 * Returns the first social activity set in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity set, or <code>null</code> if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<SocialActivitySet> list = findByUserId(userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social activity set in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity set
	 * @throws com.liferay.so.activities.NoSuchActivitySetException if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchActivitySetException, SystemException {
		SocialActivitySet socialActivitySet = fetchByUserId_Last(userId,
				orderByComparator);

		if (socialActivitySet != null) {
			return socialActivitySet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivitySetException(msg.toString());
	}

	/**
	 * Returns the last social activity set in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity set, or <code>null</code> if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		List<SocialActivitySet> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social activity sets before and after the current social activity set in the ordered set where userId = &#63;.
	 *
	 * @param activitySetId the primary key of the current social activity set
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social activity set
	 * @throws com.liferay.so.activities.NoSuchActivitySetException if a social activity set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet[] findByUserId_PrevAndNext(long activitySetId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchActivitySetException, SystemException {
		SocialActivitySet socialActivitySet = findByPrimaryKey(activitySetId);

		Session session = null;

		try {
			session = openSession();

			SocialActivitySet[] array = new SocialActivitySetImpl[3];

			array[0] = getByUserId_PrevAndNext(session, socialActivitySet,
					userId, orderByComparator, true);

			array[1] = socialActivitySet;

			array[2] = getByUserId_PrevAndNext(session, socialActivitySet,
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

	protected SocialActivitySet getByUserId_PrevAndNext(Session session,
		SocialActivitySet socialActivitySet, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SOCIALACTIVITYSET_WHERE);

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
			query.append(SocialActivitySetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(socialActivitySet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SocialActivitySet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the social activity sets where groupId = &#63; and userId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param type the type
	 * @return the matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivitySet> findByG_U_T(long groupId, long userId,
		int type) throws SystemException {
		return findByG_U_T(groupId, userId, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social activity sets where groupId = &#63; and userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param type the type
	 * @param start the lower bound of the range of social activity sets
	 * @param end the upper bound of the range of social activity sets (not inclusive)
	 * @return the range of matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivitySet> findByG_U_T(long groupId, long userId,
		int type, int start, int end) throws SystemException {
		return findByG_U_T(groupId, userId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social activity sets where groupId = &#63; and userId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param type the type
	 * @param start the lower bound of the range of social activity sets
	 * @param end the upper bound of the range of social activity sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivitySet> findByG_U_T(long groupId, long userId,
		int type, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_T;
			finderArgs = new Object[] { groupId, userId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U_T;
			finderArgs = new Object[] {
					groupId, userId, type,
					
					start, end, orderByComparator
				};
		}

		List<SocialActivitySet> list = (List<SocialActivitySet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SocialActivitySet socialActivitySet : list) {
				if ((groupId != socialActivitySet.getGroupId()) ||
						(userId != socialActivitySet.getUserId()) ||
						(type != socialActivitySet.getType())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_SOCIALACTIVITYSET_WHERE);

			query.append(_FINDER_COLUMN_G_U_T_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_T_USERID_2);

			query.append(_FINDER_COLUMN_G_U_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(SocialActivitySetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(type);

				list = (List<SocialActivitySet>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first social activity set in the ordered set where groupId = &#63; and userId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity set
	 * @throws com.liferay.so.activities.NoSuchActivitySetException if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet findByG_U_T_First(long groupId, long userId,
		int type, OrderByComparator orderByComparator)
		throws NoSuchActivitySetException, SystemException {
		SocialActivitySet socialActivitySet = fetchByG_U_T_First(groupId,
				userId, type, orderByComparator);

		if (socialActivitySet != null) {
			return socialActivitySet;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivitySetException(msg.toString());
	}

	/**
	 * Returns the first social activity set in the ordered set where groupId = &#63; and userId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity set, or <code>null</code> if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet fetchByG_U_T_First(long groupId, long userId,
		int type, OrderByComparator orderByComparator)
		throws SystemException {
		List<SocialActivitySet> list = findByG_U_T(groupId, userId, type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social activity set in the ordered set where groupId = &#63; and userId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity set
	 * @throws com.liferay.so.activities.NoSuchActivitySetException if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet findByG_U_T_Last(long groupId, long userId,
		int type, OrderByComparator orderByComparator)
		throws NoSuchActivitySetException, SystemException {
		SocialActivitySet socialActivitySet = fetchByG_U_T_Last(groupId,
				userId, type, orderByComparator);

		if (socialActivitySet != null) {
			return socialActivitySet;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivitySetException(msg.toString());
	}

	/**
	 * Returns the last social activity set in the ordered set where groupId = &#63; and userId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity set, or <code>null</code> if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet fetchByG_U_T_Last(long groupId, long userId,
		int type, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByG_U_T(groupId, userId, type);

		List<SocialActivitySet> list = findByG_U_T(groupId, userId, type,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social activity sets before and after the current social activity set in the ordered set where groupId = &#63; and userId = &#63; and type = &#63;.
	 *
	 * @param activitySetId the primary key of the current social activity set
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social activity set
	 * @throws com.liferay.so.activities.NoSuchActivitySetException if a social activity set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet[] findByG_U_T_PrevAndNext(long activitySetId,
		long groupId, long userId, int type, OrderByComparator orderByComparator)
		throws NoSuchActivitySetException, SystemException {
		SocialActivitySet socialActivitySet = findByPrimaryKey(activitySetId);

		Session session = null;

		try {
			session = openSession();

			SocialActivitySet[] array = new SocialActivitySetImpl[3];

			array[0] = getByG_U_T_PrevAndNext(session, socialActivitySet,
					groupId, userId, type, orderByComparator, true);

			array[1] = socialActivitySet;

			array[2] = getByG_U_T_PrevAndNext(session, socialActivitySet,
					groupId, userId, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SocialActivitySet getByG_U_T_PrevAndNext(Session session,
		SocialActivitySet socialActivitySet, long groupId, long userId,
		int type, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SOCIALACTIVITYSET_WHERE);

		query.append(_FINDER_COLUMN_G_U_T_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_T_USERID_2);

		query.append(_FINDER_COLUMN_G_U_T_TYPE_2);

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
			query.append(SocialActivitySetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(socialActivitySet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SocialActivitySet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the social activity sets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivitySet> findByC_C_T(long classNameId, long classPK,
		int type) throws SystemException {
		return findByC_C_T(classNameId, classPK, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social activity sets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param start the lower bound of the range of social activity sets
	 * @param end the upper bound of the range of social activity sets (not inclusive)
	 * @return the range of matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivitySet> findByC_C_T(long classNameId, long classPK,
		int type, int start, int end) throws SystemException {
		return findByC_C_T(classNameId, classPK, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the social activity sets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param start the lower bound of the range of social activity sets
	 * @param end the upper bound of the range of social activity sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivitySet> findByC_C_T(long classNameId, long classPK,
		int type, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_C_T;
			finderArgs = new Object[] { classNameId, classPK, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_C_T;
			finderArgs = new Object[] {
					classNameId, classPK, type,
					
					start, end, orderByComparator
				};
		}

		List<SocialActivitySet> list = (List<SocialActivitySet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SocialActivitySet socialActivitySet : list) {
				if ((classNameId != socialActivitySet.getClassNameId()) ||
						(classPK != socialActivitySet.getClassPK()) ||
						(type != socialActivitySet.getType())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_SOCIALACTIVITYSET_WHERE);

			query.append(_FINDER_COLUMN_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(SocialActivitySetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(type);

				list = (List<SocialActivitySet>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first social activity set in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity set
	 * @throws com.liferay.so.activities.NoSuchActivitySetException if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet findByC_C_T_First(long classNameId, long classPK,
		int type, OrderByComparator orderByComparator)
		throws NoSuchActivitySetException, SystemException {
		SocialActivitySet socialActivitySet = fetchByC_C_T_First(classNameId,
				classPK, type, orderByComparator);

		if (socialActivitySet != null) {
			return socialActivitySet;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivitySetException(msg.toString());
	}

	/**
	 * Returns the first social activity set in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity set, or <code>null</code> if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet fetchByC_C_T_First(long classNameId, long classPK,
		int type, OrderByComparator orderByComparator)
		throws SystemException {
		List<SocialActivitySet> list = findByC_C_T(classNameId, classPK, type,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social activity set in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity set
	 * @throws com.liferay.so.activities.NoSuchActivitySetException if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet findByC_C_T_Last(long classNameId, long classPK,
		int type, OrderByComparator orderByComparator)
		throws NoSuchActivitySetException, SystemException {
		SocialActivitySet socialActivitySet = fetchByC_C_T_Last(classNameId,
				classPK, type, orderByComparator);

		if (socialActivitySet != null) {
			return socialActivitySet;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivitySetException(msg.toString());
	}

	/**
	 * Returns the last social activity set in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity set, or <code>null</code> if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet fetchByC_C_T_Last(long classNameId, long classPK,
		int type, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByC_C_T(classNameId, classPK, type);

		List<SocialActivitySet> list = findByC_C_T(classNameId, classPK, type,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social activity sets before and after the current social activity set in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param activitySetId the primary key of the current social activity set
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social activity set
	 * @throws com.liferay.so.activities.NoSuchActivitySetException if a social activity set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet[] findByC_C_T_PrevAndNext(long activitySetId,
		long classNameId, long classPK, int type,
		OrderByComparator orderByComparator)
		throws NoSuchActivitySetException, SystemException {
		SocialActivitySet socialActivitySet = findByPrimaryKey(activitySetId);

		Session session = null;

		try {
			session = openSession();

			SocialActivitySet[] array = new SocialActivitySetImpl[3];

			array[0] = getByC_C_T_PrevAndNext(session, socialActivitySet,
					classNameId, classPK, type, orderByComparator, true);

			array[1] = socialActivitySet;

			array[2] = getByC_C_T_PrevAndNext(session, socialActivitySet,
					classNameId, classPK, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SocialActivitySet getByC_C_T_PrevAndNext(Session session,
		SocialActivitySet socialActivitySet, long classNameId, long classPK,
		int type, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SOCIALACTIVITYSET_WHERE);

		query.append(_FINDER_COLUMN_C_C_T_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_C_C_T_CLASSPK_2);

		query.append(_FINDER_COLUMN_C_C_T_TYPE_2);

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
			query.append(SocialActivitySetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(socialActivitySet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SocialActivitySet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the social activity sets where groupId = &#63; and userId = &#63; and classNameId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param type the type
	 * @return the matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivitySet> findByG_U_C_T(long groupId, long userId,
		long classNameId, int type) throws SystemException {
		return findByG_U_C_T(groupId, userId, classNameId, type,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social activity sets where groupId = &#63; and userId = &#63; and classNameId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param start the lower bound of the range of social activity sets
	 * @param end the upper bound of the range of social activity sets (not inclusive)
	 * @return the range of matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivitySet> findByG_U_C_T(long groupId, long userId,
		long classNameId, int type, int start, int end)
		throws SystemException {
		return findByG_U_C_T(groupId, userId, classNameId, type, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the social activity sets where groupId = &#63; and userId = &#63; and classNameId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param start the lower bound of the range of social activity sets
	 * @param end the upper bound of the range of social activity sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivitySet> findByG_U_C_T(long groupId, long userId,
		long classNameId, int type, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_C_T;
			finderArgs = new Object[] { groupId, userId, classNameId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U_C_T;
			finderArgs = new Object[] {
					groupId, userId, classNameId, type,
					
					start, end, orderByComparator
				};
		}

		List<SocialActivitySet> list = (List<SocialActivitySet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SocialActivitySet socialActivitySet : list) {
				if ((groupId != socialActivitySet.getGroupId()) ||
						(userId != socialActivitySet.getUserId()) ||
						(classNameId != socialActivitySet.getClassNameId()) ||
						(type != socialActivitySet.getType())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_SOCIALACTIVITYSET_WHERE);

			query.append(_FINDER_COLUMN_G_U_C_T_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_C_T_USERID_2);

			query.append(_FINDER_COLUMN_G_U_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_G_U_C_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(SocialActivitySetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(classNameId);

				qPos.add(type);

				list = (List<SocialActivitySet>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first social activity set in the ordered set where groupId = &#63; and userId = &#63; and classNameId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity set
	 * @throws com.liferay.so.activities.NoSuchActivitySetException if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet findByG_U_C_T_First(long groupId, long userId,
		long classNameId, int type, OrderByComparator orderByComparator)
		throws NoSuchActivitySetException, SystemException {
		SocialActivitySet socialActivitySet = fetchByG_U_C_T_First(groupId,
				userId, classNameId, type, orderByComparator);

		if (socialActivitySet != null) {
			return socialActivitySet;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivitySetException(msg.toString());
	}

	/**
	 * Returns the first social activity set in the ordered set where groupId = &#63; and userId = &#63; and classNameId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity set, or <code>null</code> if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet fetchByG_U_C_T_First(long groupId, long userId,
		long classNameId, int type, OrderByComparator orderByComparator)
		throws SystemException {
		List<SocialActivitySet> list = findByG_U_C_T(groupId, userId,
				classNameId, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social activity set in the ordered set where groupId = &#63; and userId = &#63; and classNameId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity set
	 * @throws com.liferay.so.activities.NoSuchActivitySetException if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet findByG_U_C_T_Last(long groupId, long userId,
		long classNameId, int type, OrderByComparator orderByComparator)
		throws NoSuchActivitySetException, SystemException {
		SocialActivitySet socialActivitySet = fetchByG_U_C_T_Last(groupId,
				userId, classNameId, type, orderByComparator);

		if (socialActivitySet != null) {
			return socialActivitySet;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivitySetException(msg.toString());
	}

	/**
	 * Returns the last social activity set in the ordered set where groupId = &#63; and userId = &#63; and classNameId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity set, or <code>null</code> if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet fetchByG_U_C_T_Last(long groupId, long userId,
		long classNameId, int type, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByG_U_C_T(groupId, userId, classNameId, type);

		List<SocialActivitySet> list = findByG_U_C_T(groupId, userId,
				classNameId, type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social activity sets before and after the current social activity set in the ordered set where groupId = &#63; and userId = &#63; and classNameId = &#63; and type = &#63;.
	 *
	 * @param activitySetId the primary key of the current social activity set
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social activity set
	 * @throws com.liferay.so.activities.NoSuchActivitySetException if a social activity set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet[] findByG_U_C_T_PrevAndNext(long activitySetId,
		long groupId, long userId, long classNameId, int type,
		OrderByComparator orderByComparator)
		throws NoSuchActivitySetException, SystemException {
		SocialActivitySet socialActivitySet = findByPrimaryKey(activitySetId);

		Session session = null;

		try {
			session = openSession();

			SocialActivitySet[] array = new SocialActivitySetImpl[3];

			array[0] = getByG_U_C_T_PrevAndNext(session, socialActivitySet,
					groupId, userId, classNameId, type, orderByComparator, true);

			array[1] = socialActivitySet;

			array[2] = getByG_U_C_T_PrevAndNext(session, socialActivitySet,
					groupId, userId, classNameId, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SocialActivitySet getByG_U_C_T_PrevAndNext(Session session,
		SocialActivitySet socialActivitySet, long groupId, long userId,
		long classNameId, int type, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SOCIALACTIVITYSET_WHERE);

		query.append(_FINDER_COLUMN_G_U_C_T_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_C_T_USERID_2);

		query.append(_FINDER_COLUMN_G_U_C_T_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_G_U_C_T_TYPE_2);

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
			query.append(SocialActivitySetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		qPos.add(classNameId);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(socialActivitySet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SocialActivitySet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the social activity sets where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivitySet> findByU_C_C_T(long userId, long classNameId,
		long classPK, int type) throws SystemException {
		return findByU_C_C_T(userId, classNameId, classPK, type,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social activity sets where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param start the lower bound of the range of social activity sets
	 * @param end the upper bound of the range of social activity sets (not inclusive)
	 * @return the range of matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivitySet> findByU_C_C_T(long userId, long classNameId,
		long classPK, int type, int start, int end) throws SystemException {
		return findByU_C_C_T(userId, classNameId, classPK, type, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the social activity sets where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param start the lower bound of the range of social activity sets
	 * @param end the upper bound of the range of social activity sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivitySet> findByU_C_C_T(long userId, long classNameId,
		long classPK, int type, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_C_C_T;
			finderArgs = new Object[] { userId, classNameId, classPK, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_C_C_T;
			finderArgs = new Object[] {
					userId, classNameId, classPK, type,
					
					start, end, orderByComparator
				};
		}

		List<SocialActivitySet> list = (List<SocialActivitySet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SocialActivitySet socialActivitySet : list) {
				if ((userId != socialActivitySet.getUserId()) ||
						(classNameId != socialActivitySet.getClassNameId()) ||
						(classPK != socialActivitySet.getClassPK()) ||
						(type != socialActivitySet.getType())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_SOCIALACTIVITYSET_WHERE);

			query.append(_FINDER_COLUMN_U_C_C_T_USERID_2);

			query.append(_FINDER_COLUMN_U_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_U_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_U_C_C_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(SocialActivitySetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(classNameId);

				qPos.add(classPK);

				qPos.add(type);

				list = (List<SocialActivitySet>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first social activity set in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity set
	 * @throws com.liferay.so.activities.NoSuchActivitySetException if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet findByU_C_C_T_First(long userId, long classNameId,
		long classPK, int type, OrderByComparator orderByComparator)
		throws NoSuchActivitySetException, SystemException {
		SocialActivitySet socialActivitySet = fetchByU_C_C_T_First(userId,
				classNameId, classPK, type, orderByComparator);

		if (socialActivitySet != null) {
			return socialActivitySet;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivitySetException(msg.toString());
	}

	/**
	 * Returns the first social activity set in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching social activity set, or <code>null</code> if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet fetchByU_C_C_T_First(long userId,
		long classNameId, long classPK, int type,
		OrderByComparator orderByComparator) throws SystemException {
		List<SocialActivitySet> list = findByU_C_C_T(userId, classNameId,
				classPK, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last social activity set in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity set
	 * @throws com.liferay.so.activities.NoSuchActivitySetException if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet findByU_C_C_T_Last(long userId, long classNameId,
		long classPK, int type, OrderByComparator orderByComparator)
		throws NoSuchActivitySetException, SystemException {
		SocialActivitySet socialActivitySet = fetchByU_C_C_T_Last(userId,
				classNameId, classPK, type, orderByComparator);

		if (socialActivitySet != null) {
			return socialActivitySet;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActivitySetException(msg.toString());
	}

	/**
	 * Returns the last social activity set in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching social activity set, or <code>null</code> if a matching social activity set could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet fetchByU_C_C_T_Last(long userId, long classNameId,
		long classPK, int type, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByU_C_C_T(userId, classNameId, classPK, type);

		List<SocialActivitySet> list = findByU_C_C_T(userId, classNameId,
				classPK, type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the social activity sets before and after the current social activity set in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param activitySetId the primary key of the current social activity set
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next social activity set
	 * @throws com.liferay.so.activities.NoSuchActivitySetException if a social activity set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SocialActivitySet[] findByU_C_C_T_PrevAndNext(long activitySetId,
		long userId, long classNameId, long classPK, int type,
		OrderByComparator orderByComparator)
		throws NoSuchActivitySetException, SystemException {
		SocialActivitySet socialActivitySet = findByPrimaryKey(activitySetId);

		Session session = null;

		try {
			session = openSession();

			SocialActivitySet[] array = new SocialActivitySetImpl[3];

			array[0] = getByU_C_C_T_PrevAndNext(session, socialActivitySet,
					userId, classNameId, classPK, type, orderByComparator, true);

			array[1] = socialActivitySet;

			array[2] = getByU_C_C_T_PrevAndNext(session, socialActivitySet,
					userId, classNameId, classPK, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SocialActivitySet getByU_C_C_T_PrevAndNext(Session session,
		SocialActivitySet socialActivitySet, long userId, long classNameId,
		long classPK, int type, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SOCIALACTIVITYSET_WHERE);

		query.append(_FINDER_COLUMN_U_C_C_T_USERID_2);

		query.append(_FINDER_COLUMN_U_C_C_T_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_U_C_C_T_CLASSPK_2);

		query.append(_FINDER_COLUMN_U_C_C_T_TYPE_2);

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
			query.append(SocialActivitySetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(classNameId);

		qPos.add(classPK);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(socialActivitySet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SocialActivitySet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the social activity sets.
	 *
	 * @return the social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivitySet> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the social activity sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of social activity sets
	 * @param end the upper bound of the range of social activity sets (not inclusive)
	 * @return the range of social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivitySet> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the social activity sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of social activity sets
	 * @param end the upper bound of the range of social activity sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public List<SocialActivitySet> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<SocialActivitySet> list = (List<SocialActivitySet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SOCIALACTIVITYSET);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SOCIALACTIVITYSET.concat(SocialActivitySetModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<SocialActivitySet>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<SocialActivitySet>)QueryUtil.list(q,
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
	 * Removes all the social activity sets where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGroupId(long groupId) throws SystemException {
		for (SocialActivitySet socialActivitySet : findByGroupId(groupId)) {
			remove(socialActivitySet);
		}
	}

	/**
	 * Removes all the social activity sets where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (SocialActivitySet socialActivitySet : findByUserId(userId)) {
			remove(socialActivitySet);
		}
	}

	/**
	 * Removes all the social activity sets where groupId = &#63; and userId = &#63; and type = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_U_T(long groupId, long userId, int type)
		throws SystemException {
		for (SocialActivitySet socialActivitySet : findByG_U_T(groupId, userId,
				type)) {
			remove(socialActivitySet);
		}
	}

	/**
	 * Removes all the social activity sets where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C_T(long classNameId, long classPK, int type)
		throws SystemException {
		for (SocialActivitySet socialActivitySet : findByC_C_T(classNameId,
				classPK, type)) {
			remove(socialActivitySet);
		}
	}

	/**
	 * Removes all the social activity sets where groupId = &#63; and userId = &#63; and classNameId = &#63; and type = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_U_C_T(long groupId, long userId, long classNameId,
		int type) throws SystemException {
		for (SocialActivitySet socialActivitySet : findByG_U_C_T(groupId,
				userId, classNameId, type)) {
			remove(socialActivitySet);
		}
	}

	/**
	 * Removes all the social activity sets where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_C_C_T(long userId, long classNameId, long classPK,
		int type) throws SystemException {
		for (SocialActivitySet socialActivitySet : findByU_C_C_T(userId,
				classNameId, classPK, type)) {
			remove(socialActivitySet);
		}
	}

	/**
	 * Removes all the social activity sets from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (SocialActivitySet socialActivitySet : findAll()) {
			remove(socialActivitySet);
		}
	}

	/**
	 * Returns the number of social activity sets where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGroupId(long groupId) throws SystemException {
		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SOCIALACTIVITYSET_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUPID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of social activity sets where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SOCIALACTIVITYSET_WHERE);

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
	 * Returns the number of social activity sets where groupId = &#63; and userId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param type the type
	 * @return the number of matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_U_T(long groupId, long userId, int type)
		throws SystemException {
		Object[] finderArgs = new Object[] { groupId, userId, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_U_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SOCIALACTIVITYSET_WHERE);

			query.append(_FINDER_COLUMN_G_U_T_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_T_USERID_2);

			query.append(_FINDER_COLUMN_G_U_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_U_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of social activity sets where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the number of matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C_T(long classNameId, long classPK, int type)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SOCIALACTIVITYSET_WHERE);

			query.append(_FINDER_COLUMN_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_C_C_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of social activity sets where groupId = &#63; and userId = &#63; and classNameId = &#63; and type = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param type the type
	 * @return the number of matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_U_C_T(long groupId, long userId, long classNameId,
		int type) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, userId, classNameId, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_U_C_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_SOCIALACTIVITYSET_WHERE);

			query.append(_FINDER_COLUMN_G_U_C_T_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_C_T_USERID_2);

			query.append(_FINDER_COLUMN_G_U_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_G_U_C_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(classNameId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_U_C_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of social activity sets where userId = &#63; and classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param userId the user ID
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param type the type
	 * @return the number of matching social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_C_C_T(long userId, long classNameId, long classPK,
		int type) throws SystemException {
		Object[] finderArgs = new Object[] { userId, classNameId, classPK, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_C_C_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_SOCIALACTIVITYSET_WHERE);

			query.append(_FINDER_COLUMN_U_C_C_T_USERID_2);

			query.append(_FINDER_COLUMN_U_C_C_T_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_U_C_C_T_CLASSPK_2);

			query.append(_FINDER_COLUMN_U_C_C_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(classNameId);

				qPos.add(classPK);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_C_C_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of social activity sets.
	 *
	 * @return the number of social activity sets
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SOCIALACTIVITYSET);

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
	 * Initializes the social activity set persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.so.activities.model.SocialActivitySet")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SocialActivitySet>> listenersList = new ArrayList<ModelListener<SocialActivitySet>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<SocialActivitySet>)InstanceFactory.newInstance(
							clazz.getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(SocialActivitySetImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = SocialActivityPersistence.class)
	protected SocialActivityPersistence socialActivityPersistence;
	@BeanReference(type = SocialActivitySetPersistence.class)
	protected SocialActivitySetPersistence socialActivitySetPersistence;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_SOCIALACTIVITYSET = "SELECT socialActivitySet FROM SocialActivitySet socialActivitySet";
	private static final String _SQL_SELECT_SOCIALACTIVITYSET_WHERE = "SELECT socialActivitySet FROM SocialActivitySet socialActivitySet WHERE ";
	private static final String _SQL_COUNT_SOCIALACTIVITYSET = "SELECT COUNT(socialActivitySet) FROM SocialActivitySet socialActivitySet";
	private static final String _SQL_COUNT_SOCIALACTIVITYSET_WHERE = "SELECT COUNT(socialActivitySet) FROM SocialActivitySet socialActivitySet WHERE ";
	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "socialActivitySet.groupId = ?";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "socialActivitySet.userId = ?";
	private static final String _FINDER_COLUMN_G_U_T_GROUPID_2 = "socialActivitySet.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_T_USERID_2 = "socialActivitySet.userId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_T_TYPE_2 = "socialActivitySet.type = ?";
	private static final String _FINDER_COLUMN_C_C_T_CLASSNAMEID_2 = "socialActivitySet.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_T_CLASSPK_2 = "socialActivitySet.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_T_TYPE_2 = "socialActivitySet.type = ?";
	private static final String _FINDER_COLUMN_G_U_C_T_GROUPID_2 = "socialActivitySet.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_C_T_USERID_2 = "socialActivitySet.userId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_C_T_CLASSNAMEID_2 = "socialActivitySet.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_C_T_TYPE_2 = "socialActivitySet.type = ?";
	private static final String _FINDER_COLUMN_U_C_C_T_USERID_2 = "socialActivitySet.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_C_C_T_CLASSNAMEID_2 = "socialActivitySet.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_U_C_C_T_CLASSPK_2 = "socialActivitySet.classPK = ? AND ";
	private static final String _FINDER_COLUMN_U_C_C_T_TYPE_2 = "socialActivitySet.type = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "socialActivitySet.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SocialActivitySet exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SocialActivitySet exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SocialActivitySetPersistenceImpl.class);
	private static SocialActivitySet _nullSocialActivitySet = new SocialActivitySetImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SocialActivitySet> toCacheModel() {
				return _nullSocialActivitySetCacheModel;
			}
		};

	private static CacheModel<SocialActivitySet> _nullSocialActivitySetCacheModel =
		new CacheModel<SocialActivitySet>() {
			public SocialActivitySet toEntityModel() {
				return _nullSocialActivitySet;
			}
		};
}