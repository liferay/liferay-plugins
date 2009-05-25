/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.wol.service.persistence;

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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.wol.NoSuchSVNRevisionException;
import com.liferay.wol.model.SVNRevision;
import com.liferay.wol.model.impl.SVNRevisionImpl;
import com.liferay.wol.model.impl.SVNRevisionModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="SVNRevisionPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SVNRevisionPersistenceImpl extends BasePersistenceImpl
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

	public SVNRevision remove(long svnRevisionId)
		throws NoSuchSVNRevisionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SVNRevision svnRevision = (SVNRevision)session.get(SVNRevisionImpl.class,
					new Long(svnRevisionId));

			if (svnRevision == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No SVNRevision exists with the primary key " +
						svnRevisionId);
				}

				throw new NoSuchSVNRevisionException(
					"No SVNRevision exists with the primary key " +
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

	public SVNRevision update(SVNRevision svnRevision)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(SVNRevision svnRevision) method. Use update(SVNRevision svnRevision, boolean merge) instead.");
		}

		return update(svnRevision, false);
	}

	public SVNRevision update(SVNRevision svnRevision, boolean merge)
		throws SystemException {
		boolean isNew = svnRevision.isNew();

		for (ModelListener<SVNRevision> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(svnRevision);
			}
			else {
				listener.onBeforeUpdate(svnRevision);
			}
		}

		svnRevision = updateImpl(svnRevision, merge);

		for (ModelListener<SVNRevision> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(svnRevision);
			}
			else {
				listener.onAfterUpdate(svnRevision);
			}
		}

		return svnRevision;
	}

	public SVNRevision updateImpl(
		com.liferay.wol.model.SVNRevision svnRevision, boolean merge)
		throws SystemException {
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

	public SVNRevision findByPrimaryKey(long svnRevisionId)
		throws NoSuchSVNRevisionException, SystemException {
		SVNRevision svnRevision = fetchByPrimaryKey(svnRevisionId);

		if (svnRevision == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No SVNRevision exists with the primary key " +
					svnRevisionId);
			}

			throw new NoSuchSVNRevisionException(
				"No SVNRevision exists with the primary key " + svnRevisionId);
		}

		return svnRevision;
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

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT svnRevision FROM SVNRevision svnRevision WHERE ");

				if (svnUserId == null) {
					query.append("svnRevision.svnUserId IS NULL");
				}
				else {
					query.append("svnRevision.svnUserId = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("svnRevision.revisionNumber DESC");

				Query q = session.createQuery(query.toString());

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

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT svnRevision FROM SVNRevision svnRevision WHERE ");

				if (svnUserId == null) {
					query.append("svnRevision.svnUserId IS NULL");
				}
				else {
					query.append("svnRevision.svnUserId = ?");
				}

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("svnRevision.");
						query.append(orderByFields[i]);

						if (obc.isAscending()) {
							query.append(" ASC");
						}
						else {
							query.append(" DESC");
						}

						if ((i + 1) < orderByFields.length) {
							query.append(", ");
						}
					}
				}

				else {
					query.append("ORDER BY ");

					query.append("svnRevision.revisionNumber DESC");
				}

				Query q = session.createQuery(query.toString());

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
			StringBuilder msg = new StringBuilder();

			msg.append("No SVNRevision exists with the key {");

			msg.append("svnUserId=" + svnUserId);

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
			StringBuilder msg = new StringBuilder();

			msg.append("No SVNRevision exists with the key {");

			msg.append("svnUserId=" + svnUserId);

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

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT svnRevision FROM SVNRevision svnRevision WHERE ");

			if (svnUserId == null) {
				query.append("svnRevision.svnUserId IS NULL");
			}
			else {
				query.append("svnRevision.svnUserId = ?");
			}

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("svnRevision.");
					query.append(orderByFields[i]);

					if (obc.isAscending()) {
						query.append(" ASC");
					}
					else {
						query.append(" DESC");
					}

					if ((i + 1) < orderByFields.length) {
						query.append(", ");
					}
				}
			}

			else {
				query.append("ORDER BY ");

				query.append("svnRevision.revisionNumber DESC");
			}

			Query q = session.createQuery(query.toString());

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

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT svnRevision FROM SVNRevision svnRevision WHERE ");

				query.append("svnRevision.svnRepositoryId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("svnRevision.revisionNumber DESC");

				Query q = session.createQuery(query.toString());

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

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT svnRevision FROM SVNRevision svnRevision WHERE ");

				query.append("svnRevision.svnRepositoryId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("svnRevision.");
						query.append(orderByFields[i]);

						if (obc.isAscending()) {
							query.append(" ASC");
						}
						else {
							query.append(" DESC");
						}

						if ((i + 1) < orderByFields.length) {
							query.append(", ");
						}
					}
				}

				else {
					query.append("ORDER BY ");

					query.append("svnRevision.revisionNumber DESC");
				}

				Query q = session.createQuery(query.toString());

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
			StringBuilder msg = new StringBuilder();

			msg.append("No SVNRevision exists with the key {");

			msg.append("svnRepositoryId=" + svnRepositoryId);

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
			StringBuilder msg = new StringBuilder();

			msg.append("No SVNRevision exists with the key {");

			msg.append("svnRepositoryId=" + svnRepositoryId);

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

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT svnRevision FROM SVNRevision svnRevision WHERE ");

			query.append("svnRevision.svnRepositoryId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("svnRevision.");
					query.append(orderByFields[i]);

					if (obc.isAscending()) {
						query.append(" ASC");
					}
					else {
						query.append(" DESC");
					}

					if ((i + 1) < orderByFields.length) {
						query.append(", ");
					}
				}
			}

			else {
				query.append("ORDER BY ");

				query.append("svnRevision.revisionNumber DESC");
			}

			Query q = session.createQuery(query.toString());

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

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT svnRevision FROM SVNRevision svnRevision WHERE ");

				if (svnUserId == null) {
					query.append("svnRevision.svnUserId IS NULL");
				}
				else {
					query.append("svnRevision.svnUserId = ?");
				}

				query.append(" AND ");

				query.append("svnRevision.svnRepositoryId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("svnRevision.revisionNumber DESC");

				Query q = session.createQuery(query.toString());

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

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT svnRevision FROM SVNRevision svnRevision WHERE ");

				if (svnUserId == null) {
					query.append("svnRevision.svnUserId IS NULL");
				}
				else {
					query.append("svnRevision.svnUserId = ?");
				}

				query.append(" AND ");

				query.append("svnRevision.svnRepositoryId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("svnRevision.");
						query.append(orderByFields[i]);

						if (obc.isAscending()) {
							query.append(" ASC");
						}
						else {
							query.append(" DESC");
						}

						if ((i + 1) < orderByFields.length) {
							query.append(", ");
						}
					}
				}

				else {
					query.append("ORDER BY ");

					query.append("svnRevision.revisionNumber DESC");
				}

				Query q = session.createQuery(query.toString());

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
			StringBuilder msg = new StringBuilder();

			msg.append("No SVNRevision exists with the key {");

			msg.append("svnUserId=" + svnUserId);

			msg.append(", ");
			msg.append("svnRepositoryId=" + svnRepositoryId);

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
			StringBuilder msg = new StringBuilder();

			msg.append("No SVNRevision exists with the key {");

			msg.append("svnUserId=" + svnUserId);

			msg.append(", ");
			msg.append("svnRepositoryId=" + svnRepositoryId);

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

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT svnRevision FROM SVNRevision svnRevision WHERE ");

			if (svnUserId == null) {
				query.append("svnRevision.svnUserId IS NULL");
			}
			else {
				query.append("svnRevision.svnUserId = ?");
			}

			query.append(" AND ");

			query.append("svnRevision.svnRepositoryId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("svnRevision.");
					query.append(orderByFields[i]);

					if (obc.isAscending()) {
						query.append(" ASC");
					}
					else {
						query.append(" DESC");
					}

					if ((i + 1) < orderByFields.length) {
						query.append(", ");
					}
				}
			}

			else {
				query.append("ORDER BY ");

				query.append("svnRevision.revisionNumber DESC");
			}

			Query q = session.createQuery(query.toString());

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

				StringBuilder query = new StringBuilder();

				query.append("SELECT svnRevision FROM SVNRevision svnRevision ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("svnRevision.");
						query.append(orderByFields[i]);

						if (obc.isAscending()) {
							query.append(" ASC");
						}
						else {
							query.append(" DESC");
						}

						if ((i + 1) < orderByFields.length) {
							query.append(", ");
						}
					}
				}

				else {
					query.append("ORDER BY ");

					query.append("svnRevision.revisionNumber DESC");
				}

				Query q = session.createQuery(query.toString());

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

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(svnRevision) ");
				query.append("FROM SVNRevision svnRevision WHERE ");

				if (svnUserId == null) {
					query.append("svnRevision.svnUserId IS NULL");
				}
				else {
					query.append("svnRevision.svnUserId = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

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

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(svnRevision) ");
				query.append("FROM SVNRevision svnRevision WHERE ");

				query.append("svnRevision.svnRepositoryId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

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

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(svnRevision) ");
				query.append("FROM SVNRevision svnRevision WHERE ");

				if (svnUserId == null) {
					query.append("svnRevision.svnUserId IS NULL");
				}
				else {
					query.append("svnRevision.svnUserId = ?");
				}

				query.append(" AND ");

				query.append("svnRevision.svnRepositoryId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

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

				Query q = session.createQuery(
						"SELECT COUNT(svnRevision) FROM SVNRevision svnRevision");

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
						"value.object.listener.com.liferay.wol.model.SVNRevision")));

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

	@BeanReference(name = "com.liferay.wol.service.persistence.JIRAActionPersistence.impl")
	protected com.liferay.wol.service.persistence.JIRAActionPersistence jiraActionPersistence;
	@BeanReference(name = "com.liferay.wol.service.persistence.JIRAChangeGroupPersistence.impl")
	protected com.liferay.wol.service.persistence.JIRAChangeGroupPersistence jiraChangeGroupPersistence;
	@BeanReference(name = "com.liferay.wol.service.persistence.JIRAChangeItemPersistence.impl")
	protected com.liferay.wol.service.persistence.JIRAChangeItemPersistence jiraChangeItemPersistence;
	@BeanReference(name = "com.liferay.wol.service.persistence.JIRAIssuePersistence.impl")
	protected com.liferay.wol.service.persistence.JIRAIssuePersistence jiraIssuePersistence;
	@BeanReference(name = "com.liferay.wol.service.persistence.MeetupsEntryPersistence.impl")
	protected com.liferay.wol.service.persistence.MeetupsEntryPersistence meetupsEntryPersistence;
	@BeanReference(name = "com.liferay.wol.service.persistence.MeetupsRegistrationPersistence.impl")
	protected com.liferay.wol.service.persistence.MeetupsRegistrationPersistence meetupsRegistrationPersistence;
	@BeanReference(name = "com.liferay.wol.service.persistence.SVNRepositoryPersistence.impl")
	protected com.liferay.wol.service.persistence.SVNRepositoryPersistence svnRepositoryPersistence;
	@BeanReference(name = "com.liferay.wol.service.persistence.SVNRevisionPersistence.impl")
	protected com.liferay.wol.service.persistence.SVNRevisionPersistence svnRevisionPersistence;
	@BeanReference(name = "com.liferay.wol.service.persistence.WallEntryPersistence.impl")
	protected com.liferay.wol.service.persistence.WallEntryPersistence wallEntryPersistence;
	private static Log _log = LogFactoryUtil.getLog(SVNRevisionPersistenceImpl.class);
}