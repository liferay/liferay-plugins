/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.socialcoding.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
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
 * <a href="SVNRevisionPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class SVNRevisionPersistenceImpl extends BasePersistenceImpl<SVNRevision>
	implements SVNRevisionPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = SVNRevisionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_SVNUSERID = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findBySVNUserId", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_SVNUSERID = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findBySVNUserId",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_SVNUSERID = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countBySVNUserId", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_SVNREPOSITORYID = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findBySVNRepositoryId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_SVNREPOSITORYID = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findBySVNRepositoryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_SVNREPOSITORYID = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countBySVNRepositoryId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_SVNU_SVNR = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findBySVNU_SVNR",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_SVNU_SVNR = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findBySVNU_SVNR",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_SVNU_SVNR = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countBySVNU_SVNR",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(SVNRevision svnRevision) {
		EntityCacheUtil.putResult(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
			SVNRevisionImpl.class, svnRevision.getPrimaryKey(), svnRevision);
	}

	public void cacheResult(List<SVNRevision> svnRevisions) {
		for (SVNRevision svnRevision : svnRevisions) {
			if (EntityCacheUtil.getResult(
						SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
						SVNRevisionImpl.class, svnRevision.getPrimaryKey(), this) == null) {
				cacheResult(svnRevision);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(SVNRevisionImpl.class.getName());
		EntityCacheUtil.clearCache(SVNRevisionImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public SVNRevision create(long svnRevisionId) {
		SVNRevision svnRevision = new SVNRevisionImpl();

		svnRevision.setNew(true);
		svnRevision.setPrimaryKey(svnRevisionId);

		return svnRevision;
	}

	public SVNRevision remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public SVNRevision remove(long svnRevisionId)
		throws NoSuchSVNRevisionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SVNRevision svnRevision = (SVNRevision)session.get(SVNRevisionImpl.class,
					new Long(svnRevisionId));

			if (svnRevision == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + svnRevisionId);
				}

				throw new NoSuchSVNRevisionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					svnRevisionId);
			}

			return remove(svnRevision);
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

	public SVNRevision remove(SVNRevision svnRevision)
		throws SystemException {
		for (ModelListener<SVNRevision> listener : listeners) {
			listener.onBeforeRemove(svnRevision);
		}

		svnRevision = removeImpl(svnRevision);

		for (ModelListener<SVNRevision> listener : listeners) {
			listener.onAfterRemove(svnRevision);
		}

		return svnRevision;
	}

	protected SVNRevision removeImpl(SVNRevision svnRevision)
		throws SystemException {
		svnRevision = toUnwrappedModel(svnRevision);

		Session session = null;

		try {
			session = openSession();

			if (svnRevision.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(SVNRevisionImpl.class,
						svnRevision.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(svnRevision);

			session.flush();
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

	public SVNRevision findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

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

	public SVNRevision fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public SVNRevision fetchByPrimaryKey(long svnRevisionId)
		throws SystemException {
		SVNRevision svnRevision = (SVNRevision)EntityCacheUtil.getResult(SVNRevisionModelImpl.ENTITY_CACHE_ENABLED,
				SVNRevisionImpl.class, svnRevisionId, this);

		if (svnRevision == null) {
			Session session = null;

			try {
				session = openSession();

				svnRevision = (SVNRevision)session.get(SVNRevisionImpl.class,
						new Long(svnRevisionId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (svnRevision != null) {
					cacheResult(svnRevision);
				}

				closeSession(session);
			}
		}

		return svnRevision;
	}

	public List<SVNRevision> findBySVNUserId(String svnUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] { svnUserId };

		List<SVNRevision> list = (List<SVNRevision>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_SVNUSERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

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

				query.append(SVNRevisionModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (svnUserId != null) {
					qPos.add(svnUserId);
				}

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<SVNRevision>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_SVNUSERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<SVNRevision> findBySVNUserId(String svnUserId, int start,
		int end) throws SystemException {
		return findBySVNUserId(svnUserId, start, end, null);
	}

	public List<SVNRevision> findBySVNUserId(String svnUserId, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				svnUserId,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<SVNRevision> list = (List<SVNRevision>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_SVNUSERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (obc != null) {
					query = new StringBundler(3 +
							(obc.getOrderByFields().length * 3));
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

				if (obc != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
				}

				else {
					query.append(SVNRevisionModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

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
					list = new ArrayList<SVNRevision>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_SVNUSERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public SVNRevision findBySVNUserId_First(String svnUserId,
		OrderByComparator obc)
		throws NoSuchSVNRevisionException, SystemException {
		List<SVNRevision> list = findBySVNUserId(svnUserId, 0, 1, obc);

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

	public SVNRevision findBySVNUserId_Last(String svnUserId,
		OrderByComparator obc)
		throws NoSuchSVNRevisionException, SystemException {
		int count = countBySVNUserId(svnUserId);

		List<SVNRevision> list = findBySVNUserId(svnUserId, count - 1, count,
				obc);

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

	public SVNRevision[] findBySVNUserId_PrevAndNext(long svnRevisionId,
		String svnUserId, OrderByComparator obc)
		throws NoSuchSVNRevisionException, SystemException {
		SVNRevision svnRevision = findByPrimaryKey(svnRevisionId);

		int count = countBySVNUserId(svnUserId);

		Session session = null;

		try {
			session = openSession();

			StringBundler query = null;

			if (obc != null) {
				query = new StringBundler(3 +
						(obc.getOrderByFields().length * 3));
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

			if (obc != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
			}

			else {
				query.append(SVNRevisionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			if (svnUserId != null) {
				qPos.add(svnUserId);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					svnRevision);

			SVNRevision[] array = new SVNRevisionImpl[3];

			array[0] = (SVNRevision)objArray[0];
			array[1] = (SVNRevision)objArray[1];
			array[2] = (SVNRevision)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SVNRevision> findBySVNRepositoryId(long svnRepositoryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(svnRepositoryId) };

		List<SVNRevision> list = (List<SVNRevision>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_SVNREPOSITORYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_SELECT_SVNREVISION_WHERE);

				query.append(_FINDER_COLUMN_SVNREPOSITORYID_SVNREPOSITORYID_2);

				query.append(SVNRevisionModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(svnRepositoryId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<SVNRevision>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_SVNREPOSITORYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<SVNRevision> findBySVNRepositoryId(long svnRepositoryId,
		int start, int end) throws SystemException {
		return findBySVNRepositoryId(svnRepositoryId, start, end, null);
	}

	public List<SVNRevision> findBySVNRepositoryId(long svnRepositoryId,
		int start, int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(svnRepositoryId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<SVNRevision> list = (List<SVNRevision>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_SVNREPOSITORYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (obc != null) {
					query = new StringBundler(3 +
							(obc.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_SVNREVISION_WHERE);

				query.append(_FINDER_COLUMN_SVNREPOSITORYID_SVNREPOSITORYID_2);

				if (obc != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
				}

				else {
					query.append(SVNRevisionModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

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
					list = new ArrayList<SVNRevision>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_SVNREPOSITORYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public SVNRevision findBySVNRepositoryId_First(long svnRepositoryId,
		OrderByComparator obc)
		throws NoSuchSVNRevisionException, SystemException {
		List<SVNRevision> list = findBySVNRepositoryId(svnRepositoryId, 0, 1,
				obc);

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

	public SVNRevision findBySVNRepositoryId_Last(long svnRepositoryId,
		OrderByComparator obc)
		throws NoSuchSVNRevisionException, SystemException {
		int count = countBySVNRepositoryId(svnRepositoryId);

		List<SVNRevision> list = findBySVNRepositoryId(svnRepositoryId,
				count - 1, count, obc);

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

	public SVNRevision[] findBySVNRepositoryId_PrevAndNext(long svnRevisionId,
		long svnRepositoryId, OrderByComparator obc)
		throws NoSuchSVNRevisionException, SystemException {
		SVNRevision svnRevision = findByPrimaryKey(svnRevisionId);

		int count = countBySVNRepositoryId(svnRepositoryId);

		Session session = null;

		try {
			session = openSession();

			StringBundler query = null;

			if (obc != null) {
				query = new StringBundler(3 +
						(obc.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SVNREVISION_WHERE);

			query.append(_FINDER_COLUMN_SVNREPOSITORYID_SVNREPOSITORYID_2);

			if (obc != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
			}

			else {
				query.append(SVNRevisionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(svnRepositoryId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					svnRevision);

			SVNRevision[] array = new SVNRevisionImpl[3];

			array[0] = (SVNRevision)objArray[0];
			array[1] = (SVNRevision)objArray[1];
			array[2] = (SVNRevision)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SVNRevision> findBySVNU_SVNR(String svnUserId,
		long svnRepositoryId) throws SystemException {
		Object[] finderArgs = new Object[] { svnUserId, new Long(svnRepositoryId) };

		List<SVNRevision> list = (List<SVNRevision>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_SVNU_SVNR,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(4);

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

				query.append(SVNRevisionModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (svnUserId != null) {
					qPos.add(svnUserId);
				}

				qPos.add(svnRepositoryId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<SVNRevision>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_SVNU_SVNR,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<SVNRevision> findBySVNU_SVNR(String svnUserId,
		long svnRepositoryId, int start, int end) throws SystemException {
		return findBySVNU_SVNR(svnUserId, svnRepositoryId, start, end, null);
	}

	public List<SVNRevision> findBySVNU_SVNR(String svnUserId,
		long svnRepositoryId, int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				svnUserId, new Long(svnRepositoryId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<SVNRevision> list = (List<SVNRevision>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_SVNU_SVNR,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (obc != null) {
					query = new StringBundler(4 +
							(obc.getOrderByFields().length * 3));
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

				if (obc != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
				}

				else {
					query.append(SVNRevisionModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

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
					list = new ArrayList<SVNRevision>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_SVNU_SVNR,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public SVNRevision findBySVNU_SVNR_First(String svnUserId,
		long svnRepositoryId, OrderByComparator obc)
		throws NoSuchSVNRevisionException, SystemException {
		List<SVNRevision> list = findBySVNU_SVNR(svnUserId, svnRepositoryId, 0,
				1, obc);

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

	public SVNRevision findBySVNU_SVNR_Last(String svnUserId,
		long svnRepositoryId, OrderByComparator obc)
		throws NoSuchSVNRevisionException, SystemException {
		int count = countBySVNU_SVNR(svnUserId, svnRepositoryId);

		List<SVNRevision> list = findBySVNU_SVNR(svnUserId, svnRepositoryId,
				count - 1, count, obc);

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

	public SVNRevision[] findBySVNU_SVNR_PrevAndNext(long svnRevisionId,
		String svnUserId, long svnRepositoryId, OrderByComparator obc)
		throws NoSuchSVNRevisionException, SystemException {
		SVNRevision svnRevision = findByPrimaryKey(svnRevisionId);

		int count = countBySVNU_SVNR(svnUserId, svnRepositoryId);

		Session session = null;

		try {
			session = openSession();

			StringBundler query = null;

			if (obc != null) {
				query = new StringBundler(4 +
						(obc.getOrderByFields().length * 3));
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

			if (obc != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
			}

			else {
				query.append(SVNRevisionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			if (svnUserId != null) {
				qPos.add(svnUserId);
			}

			qPos.add(svnRepositoryId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					svnRevision);

			SVNRevision[] array = new SVNRevisionImpl[3];

			array[0] = (SVNRevision)objArray[0];
			array[1] = (SVNRevision)objArray[1];
			array[2] = (SVNRevision)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			dynamicQuery.compile(session);

			return dynamicQuery.list();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			dynamicQuery.setLimit(start, end);

			dynamicQuery.compile(session);

			return dynamicQuery.list();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SVNRevision> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<SVNRevision> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<SVNRevision> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<SVNRevision> list = (List<SVNRevision>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;
				String sql = null;

				if (obc != null) {
					query = new StringBundler(2 +
							(obc.getOrderByFields().length * 3));

					query.append(_SQL_SELECT_SVNREVISION);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);

					sql = query.toString();
				}

				else {
					sql = _SQL_SELECT_SVNREVISION.concat(SVNRevisionModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (obc == null) {
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
					list = new ArrayList<SVNRevision>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeBySVNUserId(String svnUserId) throws SystemException {
		for (SVNRevision svnRevision : findBySVNUserId(svnUserId)) {
			remove(svnRevision);
		}
	}

	public void removeBySVNRepositoryId(long svnRepositoryId)
		throws SystemException {
		for (SVNRevision svnRevision : findBySVNRepositoryId(svnRepositoryId)) {
			remove(svnRevision);
		}
	}

	public void removeBySVNU_SVNR(String svnUserId, long svnRepositoryId)
		throws SystemException {
		for (SVNRevision svnRevision : findBySVNU_SVNR(svnUserId,
				svnRepositoryId)) {
			remove(svnRevision);
		}
	}

	public void removeAll() throws SystemException {
		for (SVNRevision svnRevision : findAll()) {
			remove(svnRevision);
		}
	}

	public int countBySVNUserId(String svnUserId) throws SystemException {
		Object[] finderArgs = new Object[] { svnUserId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SVNUSERID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

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

	public int countBySVNRepositoryId(long svnRepositoryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(svnRepositoryId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SVNREPOSITORYID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_SVNREVISION_WHERE);

				query.append(_FINDER_COLUMN_SVNREPOSITORYID_SVNREPOSITORYID_2);

				String sql = query.toString();

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

	public int countBySVNU_SVNR(String svnUserId, long svnRepositoryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { svnUserId, new Long(svnRepositoryId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SVNU_SVNR,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

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

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.socialcoding.model.SVNRevision")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SVNRevision>> listenersList = new ArrayList<ModelListener<SVNRevision>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<SVNRevision>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.socialcoding.service.persistence.JIRAActionPersistence")
	protected com.liferay.socialcoding.service.persistence.JIRAActionPersistence jiraActionPersistence;
	@BeanReference(name = "com.liferay.socialcoding.service.persistence.JIRAChangeGroupPersistence")
	protected com.liferay.socialcoding.service.persistence.JIRAChangeGroupPersistence jiraChangeGroupPersistence;
	@BeanReference(name = "com.liferay.socialcoding.service.persistence.JIRAChangeItemPersistence")
	protected com.liferay.socialcoding.service.persistence.JIRAChangeItemPersistence jiraChangeItemPersistence;
	@BeanReference(name = "com.liferay.socialcoding.service.persistence.JIRAIssuePersistence")
	protected com.liferay.socialcoding.service.persistence.JIRAIssuePersistence jiraIssuePersistence;
	@BeanReference(name = "com.liferay.socialcoding.service.persistence.SVNRepositoryPersistence")
	protected com.liferay.socialcoding.service.persistence.SVNRepositoryPersistence svnRepositoryPersistence;
	@BeanReference(name = "com.liferay.socialcoding.service.persistence.SVNRevisionPersistence")
	protected com.liferay.socialcoding.service.persistence.SVNRevisionPersistence svnRevisionPersistence;
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
	private static Log _log = LogFactoryUtil.getLog(SVNRevisionPersistenceImpl.class);
}