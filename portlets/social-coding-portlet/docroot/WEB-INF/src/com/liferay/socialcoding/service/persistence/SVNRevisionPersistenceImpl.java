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

package com.liferay.socialcoding.service.persistence;

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

import com.liferay.socialcoding.NoSuchSVNRevisionException;
import com.liferay.socialcoding.model.SVNRevision;
import com.liferay.socialcoding.model.impl.SVNRevisionImpl;
import com.liferay.socialcoding.model.impl.SVNRevisionModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the s v n revision service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SVNRevisionPersistence
 * @see SVNRevisionUtil
 * @generated
 */
public class SVNRevisionPersistenceImpl extends BasePersistenceImpl<SVNRevision>
	implements SVNRevisionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SVNRevisionUtil} to access the s v n revision persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SVNRevisionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_SVNUSERID = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, SVNRevisionImpl.class,
			FINDER_CLASS_NAME_LIST, "findBySVNUserId",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_SVNUSERID = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countBySVNUserId",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_SVNREPOSITORYID = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, SVNRevisionImpl.class,
			FINDER_CLASS_NAME_LIST, "findBySVNRepositoryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_SVNREPOSITORYID = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countBySVNRepositoryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_SVNU_SVNR = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, SVNRevisionImpl.class,
			FINDER_CLASS_NAME_LIST, "findBySVNU_SVNR",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_SVNU_SVNR = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countBySVNU_SVNR",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, SVNRevisionImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the s v n revision in the entity cache if it is enabled.
	 *
	 * @param svnRevision the s v n revision
	 */
	public void cacheResult(SVNRevision svnRevision) {
		EntityCacheUtil.putResult(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionImpl.class, svnRevision.getPrimaryKey(), svnRevision);

		svnRevision.resetOriginalValues();
	}

	/**
	 * Caches the s v n revisions in the entity cache if it is enabled.
	 *
	 * @param svnRevisions the s v n revisions
	 */
	public void cacheResult(List<SVNRevision> svnRevisions) {
		for (SVNRevision svnRevision : svnRevisions) {
			if (EntityCacheUtil.getResult(
						SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
						SVNRevisionImpl.class, svnRevision.getPrimaryKey(), this) == null) {
				cacheResult(svnRevision);
			}
		}
	}

	/**
	 * Clears the cache for all s v n revisions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SVNRevisionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SVNRevisionImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the s v n revision.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SVNRevision svnRevision) {
		EntityCacheUtil.removeResult(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionImpl.class, svnRevision.getPrimaryKey());
	}

	/**
	 * Creates a new s v n revision with the primary key. Does not add the s v n revision to the database.
	 *
	 * @param svnRevisionId the primary key for the new s v n revision
	 * @return the new s v n revision
	 */
	public SVNRevision create(long svnRevisionId) {
		SVNRevision svnRevision = new SVNRevisionImpl();

		svnRevision.setNew(true);
		svnRevision.setPrimaryKey(svnRevisionId);

		return svnRevision;
	}

	/**
	 * Removes the s v n revision with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the s v n revision
	 * @return the s v n revision that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a s v n revision with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SVNRevision remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the s v n revision with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param svnRevisionId the primary key of the s v n revision
	 * @return the s v n revision that was removed
	 * @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SVNRevision remove(long svnRevisionId)
		throws NoSuchSVNRevisionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SVNRevision svnRevision = (SVNRevision)session.get(SVNRevisionImpl.class,
					Long.valueOf(svnRevisionId));

			if (svnRevision == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + svnRevisionId);
				}

				throw new NoSuchSVNRevisionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					svnRevisionId);
			}

			return svnRevisionPersistence.remove(svnRevision);
		}
		catch (NoSuchSVNRevisionException nsee) {
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
	 * Removes the s v n revision from the database. Also notifies the appropriate model listeners.
	 *
	 * @param svnRevision the s v n revision
	 * @return the s v n revision that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SVNRevision remove(SVNRevision svnRevision)
		throws SystemException {
		return super.remove(svnRevision);
	}

	@Override
	protected SVNRevision removeImpl(SVNRevision svnRevision)
		throws SystemException {
		svnRevision = toUnwrappedModel(svnRevision);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, svnRevision);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionImpl.class, svnRevision.getPrimaryKey());

		return svnRevision;
	}

	@Override
	public SVNRevision updateImpl(
		com.liferay.socialcoding.model.SVNRevision svnRevision, boolean merge)
		throws SystemException {
		svnRevision = toUnwrappedModel(svnRevision);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, svnRevision, merge);

			svnRevision.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionImpl.class, svnRevision.getPrimaryKey(), svnRevision);

		return svnRevision;
	}

	protected SVNRevision toUnwrappedModel(SVNRevision svnRevision) {
		if (svnRevision instanceof SVNRevisionImpl) {
			return svnRevision;
		}

		SVNRevisionImpl svnRevisionImpl = new SVNRevisionImpl();

		svnRevisionImpl.setNew(svnRevision.isNew());
		svnRevisionImpl.setPrimaryKey(svnRevision.getPrimaryKey());

		svnRevisionImpl.setSvnRevisionId(svnRevision.getSvnRevisionId());
		svnRevisionImpl.setSvnUserId(svnRevision.getSvnUserId());
		svnRevisionImpl.setCreateDate(svnRevision.getCreateDate());
		svnRevisionImpl.setSvnRepositoryId(svnRevision.getSvnRepositoryId());
		svnRevisionImpl.setRevisionNumber(svnRevision.getRevisionNumber());
		svnRevisionImpl.setComments(svnRevision.getComments());

		return svnRevisionImpl;
	}

	/**
	 * Returns the s v n revision with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the s v n revision
	 * @return the s v n revision
	 * @throws com.liferay.portal.NoSuchModelException if a s v n revision with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SVNRevision findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the s v n revision with the primary key or throws a {@link com.liferay.socialcoding.NoSuchSVNRevisionException} if it could not be found.
	 *
	 * @param svnRevisionId the primary key of the s v n revision
	 * @return the s v n revision
	 * @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SVNRevision findByPrimaryKey(long svnRevisionId)
		throws NoSuchSVNRevisionException, SystemException {
		SVNRevision svnRevision = fetchByPrimaryKey(svnRevisionId);

		if (svnRevision == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + svnRevisionId);
			}

			throw new NoSuchSVNRevisionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				svnRevisionId);
		}

		return svnRevision;
	}

	/**
	 * Returns the s v n revision with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the s v n revision
	 * @return the s v n revision, or <code>null</code> if a s v n revision with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SVNRevision fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the s v n revision with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param svnRevisionId the primary key of the s v n revision
	 * @return the s v n revision, or <code>null</code> if a s v n revision with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SVNRevision fetchByPrimaryKey(long svnRevisionId)
		throws SystemException {
		SVNRevision svnRevision = (SVNRevision)EntityCacheUtil.getResult(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
				SVNRevisionImpl.class, svnRevisionId, this);

		if (svnRevision == _nullSVNRevision) {
			return null;
		}

		if (svnRevision == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				svnRevision = (SVNRevision)session.get(SVNRevisionImpl.class,
						Long.valueOf(svnRevisionId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (svnRevision != null) {
					cacheResult(svnRevision);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
						SVNRevisionImpl.class, svnRevisionId, _nullSVNRevision);
				}

				closeSession(session);
			}
		}

		return svnRevision;
	}

	/**
	 * Returns all the s v n revisions where svnUserId = &#63;.
	 *
	 * @param svnUserId the svn user ID
	 * @return the matching s v n revisions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SVNRevision> findBySVNUserId(String svnUserId)
		throws SystemException {
		return findBySVNUserId(svnUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the s v n revisions where svnUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param svnUserId the svn user ID
	 * @param start the lower bound of the range of s v n revisions
	 * @param end the upper bound of the range of s v n revisions (not inclusive)
	 * @return the range of matching s v n revisions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SVNRevision> findBySVNUserId(String svnUserId, int start,
		int end) throws SystemException {
		return findBySVNUserId(svnUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the s v n revisions where svnUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param svnUserId the svn user ID
	 * @param start the lower bound of the range of s v n revisions
	 * @param end the upper bound of the range of s v n revisions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching s v n revisions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SVNRevision> findBySVNUserId(String svnUserId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				svnUserId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<SVNRevision> list = (List<SVNRevision>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_SVNUSERID,
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

			query.append(_SQL_SELECT_SVNREVISION_WHERE);

			if (svnUserId == null) {
				query.append(_FINDER_COLUMN_SVNUSERID_SVNUSERID_1);
			}
			else {
				if (svnUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SVNUSERID_SVNUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_SVNUSERID_SVNUSERID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(SVNRevisionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (svnUserId != null) {
					qPos.add(svnUserId);
				}

				list = (List<SVNRevision>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_SVNUSERID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_SVNUSERID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first s v n revision in the ordered set where svnUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param svnUserId the svn user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching s v n revision
	 * @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a matching s v n revision could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SVNRevision findBySVNUserId_First(String svnUserId,
		OrderByComparator orderByComparator)
		throws NoSuchSVNRevisionException, SystemException {
		List<SVNRevision> list = findBySVNUserId(svnUserId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("svnUserId=");
			msg.append(svnUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSVNRevisionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last s v n revision in the ordered set where svnUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param svnUserId the svn user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching s v n revision
	 * @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a matching s v n revision could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SVNRevision findBySVNUserId_Last(String svnUserId,
		OrderByComparator orderByComparator)
		throws NoSuchSVNRevisionException, SystemException {
		int count = countBySVNUserId(svnUserId);

		List<SVNRevision> list = findBySVNUserId(svnUserId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("svnUserId=");
			msg.append(svnUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSVNRevisionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the s v n revisions before and after the current s v n revision in the ordered set where svnUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param svnRevisionId the primary key of the current s v n revision
	 * @param svnUserId the svn user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next s v n revision
	 * @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SVNRevision[] findBySVNUserId_PrevAndNext(long svnRevisionId,
		String svnUserId, OrderByComparator orderByComparator)
		throws NoSuchSVNRevisionException, SystemException {
		SVNRevision svnRevision = findByPrimaryKey(svnRevisionId);

		Session session = null;

		try {
			session = openSession();

			SVNRevision[] array = new SVNRevisionImpl[3];

			array[0] = getBySVNUserId_PrevAndNext(session, svnRevision,
					svnUserId, orderByComparator, true);

			array[1] = svnRevision;

			array[2] = getBySVNUserId_PrevAndNext(session, svnRevision,
					svnUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SVNRevision getBySVNUserId_PrevAndNext(Session session,
		SVNRevision svnRevision, String svnUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SVNREVISION_WHERE);

		if (svnUserId == null) {
			query.append(_FINDER_COLUMN_SVNUSERID_SVNUSERID_1);
		}
		else {
			if (svnUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SVNUSERID_SVNUSERID_3);
			}
			else {
				query.append(_FINDER_COLUMN_SVNUSERID_SVNUSERID_2);
			}
		}

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
			query.append(SVNRevisionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (svnUserId != null) {
			qPos.add(svnUserId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(svnRevision);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SVNRevision> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the s v n revisions where svnRepositoryId = &#63;.
	 *
	 * @param svnRepositoryId the svn repository ID
	 * @return the matching s v n revisions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SVNRevision> findBySVNRepositoryId(long svnRepositoryId)
		throws SystemException {
		return findBySVNRepositoryId(svnRepositoryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the s v n revisions where svnRepositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param svnRepositoryId the svn repository ID
	 * @param start the lower bound of the range of s v n revisions
	 * @param end the upper bound of the range of s v n revisions (not inclusive)
	 * @return the range of matching s v n revisions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SVNRevision> findBySVNRepositoryId(long svnRepositoryId,
		int start, int end) throws SystemException {
		return findBySVNRepositoryId(svnRepositoryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the s v n revisions where svnRepositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param svnRepositoryId the svn repository ID
	 * @param start the lower bound of the range of s v n revisions
	 * @param end the upper bound of the range of s v n revisions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching s v n revisions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SVNRevision> findBySVNRepositoryId(long svnRepositoryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				svnRepositoryId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<SVNRevision> list = (List<SVNRevision>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_SVNREPOSITORYID,
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

			query.append(_SQL_SELECT_SVNREVISION_WHERE);

			query.append(_FINDER_COLUMN_SVNREPOSITORYID_SVNREPOSITORYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(SVNRevisionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(svnRepositoryId);

				list = (List<SVNRevision>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_SVNREPOSITORYID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_SVNREPOSITORYID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first s v n revision in the ordered set where svnRepositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param svnRepositoryId the svn repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching s v n revision
	 * @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a matching s v n revision could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SVNRevision findBySVNRepositoryId_First(long svnRepositoryId,
		OrderByComparator orderByComparator)
		throws NoSuchSVNRevisionException, SystemException {
		List<SVNRevision> list = findBySVNRepositoryId(svnRepositoryId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("svnRepositoryId=");
			msg.append(svnRepositoryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSVNRevisionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last s v n revision in the ordered set where svnRepositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param svnRepositoryId the svn repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching s v n revision
	 * @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a matching s v n revision could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SVNRevision findBySVNRepositoryId_Last(long svnRepositoryId,
		OrderByComparator orderByComparator)
		throws NoSuchSVNRevisionException, SystemException {
		int count = countBySVNRepositoryId(svnRepositoryId);

		List<SVNRevision> list = findBySVNRepositoryId(svnRepositoryId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("svnRepositoryId=");
			msg.append(svnRepositoryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSVNRevisionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the s v n revisions before and after the current s v n revision in the ordered set where svnRepositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param svnRevisionId the primary key of the current s v n revision
	 * @param svnRepositoryId the svn repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next s v n revision
	 * @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SVNRevision[] findBySVNRepositoryId_PrevAndNext(long svnRevisionId,
		long svnRepositoryId, OrderByComparator orderByComparator)
		throws NoSuchSVNRevisionException, SystemException {
		SVNRevision svnRevision = findByPrimaryKey(svnRevisionId);

		Session session = null;

		try {
			session = openSession();

			SVNRevision[] array = new SVNRevisionImpl[3];

			array[0] = getBySVNRepositoryId_PrevAndNext(session, svnRevision,
					svnRepositoryId, orderByComparator, true);

			array[1] = svnRevision;

			array[2] = getBySVNRepositoryId_PrevAndNext(session, svnRevision,
					svnRepositoryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SVNRevision getBySVNRepositoryId_PrevAndNext(Session session,
		SVNRevision svnRevision, long svnRepositoryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SVNREVISION_WHERE);

		query.append(_FINDER_COLUMN_SVNREPOSITORYID_SVNREPOSITORYID_2);

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
			query.append(SVNRevisionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(svnRepositoryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(svnRevision);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SVNRevision> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63;.
	 *
	 * @param svnUserId the svn user ID
	 * @param svnRepositoryId the svn repository ID
	 * @return the matching s v n revisions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SVNRevision> findBySVNU_SVNR(String svnUserId,
		long svnRepositoryId) throws SystemException {
		return findBySVNU_SVNR(svnUserId, svnRepositoryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param svnUserId the svn user ID
	 * @param svnRepositoryId the svn repository ID
	 * @param start the lower bound of the range of s v n revisions
	 * @param end the upper bound of the range of s v n revisions (not inclusive)
	 * @return the range of matching s v n revisions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SVNRevision> findBySVNU_SVNR(String svnUserId,
		long svnRepositoryId, int start, int end) throws SystemException {
		return findBySVNU_SVNR(svnUserId, svnRepositoryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param svnUserId the svn user ID
	 * @param svnRepositoryId the svn repository ID
	 * @param start the lower bound of the range of s v n revisions
	 * @param end the upper bound of the range of s v n revisions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching s v n revisions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SVNRevision> findBySVNU_SVNR(String svnUserId,
		long svnRepositoryId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				svnUserId, svnRepositoryId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<SVNRevision> list = (List<SVNRevision>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_SVNU_SVNR,
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

			query.append(_SQL_SELECT_SVNREVISION_WHERE);

			if (svnUserId == null) {
				query.append(_FINDER_COLUMN_SVNU_SVNR_SVNUSERID_1);
			}
			else {
				if (svnUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SVNU_SVNR_SVNUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_SVNU_SVNR_SVNUSERID_2);
				}
			}

			query.append(_FINDER_COLUMN_SVNU_SVNR_SVNREPOSITORYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(SVNRevisionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (svnUserId != null) {
					qPos.add(svnUserId);
				}

				qPos.add(svnRepositoryId);

				list = (List<SVNRevision>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_SVNU_SVNR,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_SVNU_SVNR,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first s v n revision in the ordered set where svnUserId = &#63; and svnRepositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param svnUserId the svn user ID
	 * @param svnRepositoryId the svn repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching s v n revision
	 * @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a matching s v n revision could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SVNRevision findBySVNU_SVNR_First(String svnUserId,
		long svnRepositoryId, OrderByComparator orderByComparator)
		throws NoSuchSVNRevisionException, SystemException {
		List<SVNRevision> list = findBySVNU_SVNR(svnUserId, svnRepositoryId, 0,
				1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("svnUserId=");
			msg.append(svnUserId);

			msg.append(", svnRepositoryId=");
			msg.append(svnRepositoryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSVNRevisionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last s v n revision in the ordered set where svnUserId = &#63; and svnRepositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param svnUserId the svn user ID
	 * @param svnRepositoryId the svn repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching s v n revision
	 * @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a matching s v n revision could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SVNRevision findBySVNU_SVNR_Last(String svnUserId,
		long svnRepositoryId, OrderByComparator orderByComparator)
		throws NoSuchSVNRevisionException, SystemException {
		int count = countBySVNU_SVNR(svnUserId, svnRepositoryId);

		List<SVNRevision> list = findBySVNU_SVNR(svnUserId, svnRepositoryId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("svnUserId=");
			msg.append(svnUserId);

			msg.append(", svnRepositoryId=");
			msg.append(svnRepositoryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSVNRevisionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the s v n revisions before and after the current s v n revision in the ordered set where svnUserId = &#63; and svnRepositoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param svnRevisionId the primary key of the current s v n revision
	 * @param svnUserId the svn user ID
	 * @param svnRepositoryId the svn repository ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next s v n revision
	 * @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SVNRevision[] findBySVNU_SVNR_PrevAndNext(long svnRevisionId,
		String svnUserId, long svnRepositoryId,
		OrderByComparator orderByComparator)
		throws NoSuchSVNRevisionException, SystemException {
		SVNRevision svnRevision = findByPrimaryKey(svnRevisionId);

		Session session = null;

		try {
			session = openSession();

			SVNRevision[] array = new SVNRevisionImpl[3];

			array[0] = getBySVNU_SVNR_PrevAndNext(session, svnRevision,
					svnUserId, svnRepositoryId, orderByComparator, true);

			array[1] = svnRevision;

			array[2] = getBySVNU_SVNR_PrevAndNext(session, svnRevision,
					svnUserId, svnRepositoryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SVNRevision getBySVNU_SVNR_PrevAndNext(Session session,
		SVNRevision svnRevision, String svnUserId, long svnRepositoryId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SVNREVISION_WHERE);

		if (svnUserId == null) {
			query.append(_FINDER_COLUMN_SVNU_SVNR_SVNUSERID_1);
		}
		else {
			if (svnUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SVNU_SVNR_SVNUSERID_3);
			}
			else {
				query.append(_FINDER_COLUMN_SVNU_SVNR_SVNUSERID_2);
			}
		}

		query.append(_FINDER_COLUMN_SVNU_SVNR_SVNREPOSITORYID_2);

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
			query.append(SVNRevisionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (svnUserId != null) {
			qPos.add(svnUserId);
		}

		qPos.add(svnRepositoryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(svnRevision);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SVNRevision> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the s v n revisions.
	 *
	 * @return the s v n revisions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SVNRevision> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the s v n revisions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of s v n revisions
	 * @param end the upper bound of the range of s v n revisions (not inclusive)
	 * @return the range of s v n revisions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SVNRevision> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the s v n revisions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of s v n revisions
	 * @param end the upper bound of the range of s v n revisions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of s v n revisions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SVNRevision> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<SVNRevision> list = (List<SVNRevision>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SVNREVISION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SVNREVISION.concat(SVNRevisionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<SVNRevision>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<SVNRevision>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the s v n revisions where svnUserId = &#63; from the database.
	 *
	 * @param svnUserId the svn user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBySVNUserId(String svnUserId) throws SystemException {
		for (SVNRevision svnRevision : findBySVNUserId(svnUserId)) {
			svnRevisionPersistence.remove(svnRevision);
		}
	}

	/**
	 * Removes all the s v n revisions where svnRepositoryId = &#63; from the database.
	 *
	 * @param svnRepositoryId the svn repository ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBySVNRepositoryId(long svnRepositoryId)
		throws SystemException {
		for (SVNRevision svnRevision : findBySVNRepositoryId(svnRepositoryId)) {
			svnRevisionPersistence.remove(svnRevision);
		}
	}

	/**
	 * Removes all the s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63; from the database.
	 *
	 * @param svnUserId the svn user ID
	 * @param svnRepositoryId the svn repository ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBySVNU_SVNR(String svnUserId, long svnRepositoryId)
		throws SystemException {
		for (SVNRevision svnRevision : findBySVNU_SVNR(svnUserId,
				svnRepositoryId)) {
			svnRevisionPersistence.remove(svnRevision);
		}
	}

	/**
	 * Removes all the s v n revisions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (SVNRevision svnRevision : findAll()) {
			svnRevisionPersistence.remove(svnRevision);
		}
	}

	/**
	 * Returns the number of s v n revisions where svnUserId = &#63;.
	 *
	 * @param svnUserId the svn user ID
	 * @return the number of matching s v n revisions
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySVNUserId(String svnUserId) throws SystemException {
		Object[] finderArgs = new Object[] { svnUserId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SVNUSERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SVNREVISION_WHERE);

			if (svnUserId == null) {
				query.append(_FINDER_COLUMN_SVNUSERID_SVNUSERID_1);
			}
			else {
				if (svnUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SVNUSERID_SVNUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_SVNUSERID_SVNUSERID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (svnUserId != null) {
					qPos.add(svnUserId);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SVNUSERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of s v n revisions where svnRepositoryId = &#63;.
	 *
	 * @param svnRepositoryId the svn repository ID
	 * @return the number of matching s v n revisions
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySVNRepositoryId(long svnRepositoryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { svnRepositoryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SVNREPOSITORYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SVNREVISION_WHERE);

			query.append(_FINDER_COLUMN_SVNREPOSITORYID_SVNREPOSITORYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(svnRepositoryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SVNREPOSITORYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63;.
	 *
	 * @param svnUserId the svn user ID
	 * @param svnRepositoryId the svn repository ID
	 * @return the number of matching s v n revisions
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySVNU_SVNR(String svnUserId, long svnRepositoryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { svnUserId, svnRepositoryId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SVNU_SVNR,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SVNREVISION_WHERE);

			if (svnUserId == null) {
				query.append(_FINDER_COLUMN_SVNU_SVNR_SVNUSERID_1);
			}
			else {
				if (svnUserId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SVNU_SVNR_SVNUSERID_3);
				}
				else {
					query.append(_FINDER_COLUMN_SVNU_SVNR_SVNUSERID_2);
				}
			}

			query.append(_FINDER_COLUMN_SVNU_SVNR_SVNREPOSITORYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (svnUserId != null) {
					qPos.add(svnUserId);
				}

				qPos.add(svnRepositoryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SVNU_SVNR,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of s v n revisions.
	 *
	 * @return the number of s v n revisions
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

				Query q = session.createQuery(_SQL_COUNT_SVNREVISION);

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
	 * Initializes the s v n revision persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.socialcoding.model.SVNRevision")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SVNRevision>> listenersList = new ArrayList<ModelListener<SVNRevision>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<SVNRevision>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SVNRevisionImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = JIRAActionPersistence.class)
	protected JIRAActionPersistence jiraActionPersistence;
	@BeanReference(type = JIRAChangeGroupPersistence.class)
	protected JIRAChangeGroupPersistence jiraChangeGroupPersistence;
	@BeanReference(type = JIRAChangeItemPersistence.class)
	protected JIRAChangeItemPersistence jiraChangeItemPersistence;
	@BeanReference(type = JIRAIssuePersistence.class)
	protected JIRAIssuePersistence jiraIssuePersistence;
	@BeanReference(type = SVNRepositoryPersistence.class)
	protected SVNRepositoryPersistence svnRepositoryPersistence;
	@BeanReference(type = SVNRevisionPersistence.class)
	protected SVNRevisionPersistence svnRevisionPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_SVNREVISION = "SELECT svnRevision FROM SVNRevision svnRevision";
	private static final String _SQL_SELECT_SVNREVISION_WHERE = "SELECT svnRevision FROM SVNRevision svnRevision WHERE ";
	private static final String _SQL_COUNT_SVNREVISION = "SELECT COUNT(svnRevision) FROM SVNRevision svnRevision";
	private static final String _SQL_COUNT_SVNREVISION_WHERE = "SELECT COUNT(svnRevision) FROM SVNRevision svnRevision WHERE ";
	private static final String _FINDER_COLUMN_SVNUSERID_SVNUSERID_1 = "svnRevision.svnUserId IS NULL";
	private static final String _FINDER_COLUMN_SVNUSERID_SVNUSERID_2 = "svnRevision.svnUserId = ?";
	private static final String _FINDER_COLUMN_SVNUSERID_SVNUSERID_3 = "(svnRevision.svnUserId IS NULL OR svnRevision.svnUserId = ?)";
	private static final String _FINDER_COLUMN_SVNREPOSITORYID_SVNREPOSITORYID_2 =
		"svnRevision.svnRepositoryId = ?";
	private static final String _FINDER_COLUMN_SVNU_SVNR_SVNUSERID_1 = "svnRevision.svnUserId IS NULL AND ";
	private static final String _FINDER_COLUMN_SVNU_SVNR_SVNUSERID_2 = "svnRevision.svnUserId = ? AND ";
	private static final String _FINDER_COLUMN_SVNU_SVNR_SVNUSERID_3 = "(svnRevision.svnUserId IS NULL OR svnRevision.svnUserId = ?) AND ";
	private static final String _FINDER_COLUMN_SVNU_SVNR_SVNREPOSITORYID_2 = "svnRevision.svnRepositoryId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "svnRevision.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SVNRevision exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SVNRevision exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SVNRevisionPersistenceImpl.class);
	private static SVNRevision _nullSVNRevision = new SVNRevisionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SVNRevision> toCacheModel() {
				return _nullSVNRevisionCacheModel;
			}
		};

	private static CacheModel<SVNRevision> _nullSVNRevisionCacheModel = new CacheModel<SVNRevision>() {
			public SVNRevision toEntityModel() {
				return _nullSVNRevision;
			}
		};
}