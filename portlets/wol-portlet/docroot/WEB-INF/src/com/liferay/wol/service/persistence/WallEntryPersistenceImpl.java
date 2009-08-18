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

import com.liferay.wol.NoSuchWallEntryException;
import com.liferay.wol.model.WallEntry;
import com.liferay.wol.model.impl.WallEntryImpl;
import com.liferay.wol.model.impl.WallEntryModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="WallEntryPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WallEntryPersistenceImpl extends BasePersistenceImpl
	implements WallEntryPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = WallEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_GROUPID = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByGroupId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_GROUPID = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByGroupId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_USERID = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByUserId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_USERID = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByUserId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_G_U = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByG_U",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_G_U = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByG_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_G_U = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByG_U",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(WallEntry wallEntry) {
		EntityCacheUtil.putResult(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryImpl.class, wallEntry.getPrimaryKey(), wallEntry);
	}

	public void cacheResult(List<WallEntry> wallEntries) {
		for (WallEntry wallEntry : wallEntries) {
			if (EntityCacheUtil.getResult(
						WallEntryModelImpl.ENTITY_CACHE_ENABLED,
						WallEntryImpl.class, wallEntry.getPrimaryKey(), this) == null) {
				cacheResult(wallEntry);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(WallEntryImpl.class.getName());
		EntityCacheUtil.clearCache(WallEntryImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public WallEntry create(long wallEntryId) {
		WallEntry wallEntry = new WallEntryImpl();

		wallEntry.setNew(true);
		wallEntry.setPrimaryKey(wallEntryId);

		return wallEntry;
	}

	public WallEntry remove(long wallEntryId)
		throws NoSuchWallEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WallEntry wallEntry = (WallEntry)session.get(WallEntryImpl.class,
					new Long(wallEntryId));

			if (wallEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No WallEntry exists with the primary key " +
						wallEntryId);
				}

				throw new NoSuchWallEntryException(
					"No WallEntry exists with the primary key " + wallEntryId);
			}

			return remove(wallEntry);
		}
		catch (NoSuchWallEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public WallEntry remove(WallEntry wallEntry) throws SystemException {
		for (ModelListener<WallEntry> listener : listeners) {
			listener.onBeforeRemove(wallEntry);
		}

		wallEntry = removeImpl(wallEntry);

		for (ModelListener<WallEntry> listener : listeners) {
			listener.onAfterRemove(wallEntry);
		}

		return wallEntry;
	}

	protected WallEntry removeImpl(WallEntry wallEntry)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (wallEntry.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(WallEntryImpl.class,
						wallEntry.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(wallEntry);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryImpl.class, wallEntry.getPrimaryKey());

		return wallEntry;
	}

	public WallEntry update(WallEntry wallEntry) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(WallEntry wallEntry) method. Use update(WallEntry wallEntry, boolean merge) instead.");
		}

		return update(wallEntry, false);
	}

	public WallEntry update(WallEntry wallEntry, boolean merge)
		throws SystemException {
		boolean isNew = wallEntry.isNew();

		for (ModelListener<WallEntry> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(wallEntry);
			}
			else {
				listener.onBeforeUpdate(wallEntry);
			}
		}

		wallEntry = updateImpl(wallEntry, merge);

		for (ModelListener<WallEntry> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(wallEntry);
			}
			else {
				listener.onAfterUpdate(wallEntry);
			}
		}

		return wallEntry;
	}

	public WallEntry updateImpl(com.liferay.wol.model.WallEntry wallEntry,
		boolean merge) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, wallEntry, merge);

			wallEntry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryImpl.class, wallEntry.getPrimaryKey(), wallEntry);

		return wallEntry;
	}

	public WallEntry findByPrimaryKey(long wallEntryId)
		throws NoSuchWallEntryException, SystemException {
		WallEntry wallEntry = fetchByPrimaryKey(wallEntryId);

		if (wallEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No WallEntry exists with the primary key " +
					wallEntryId);
			}

			throw new NoSuchWallEntryException(
				"No WallEntry exists with the primary key " + wallEntryId);
		}

		return wallEntry;
	}

	public WallEntry fetchByPrimaryKey(long wallEntryId)
		throws SystemException {
		WallEntry wallEntry = (WallEntry)EntityCacheUtil.getResult(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
				WallEntryImpl.class, wallEntryId, this);

		if (wallEntry == null) {
			Session session = null;

			try {
				session = openSession();

				wallEntry = (WallEntry)session.get(WallEntryImpl.class,
						new Long(wallEntryId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (wallEntry != null) {
					cacheResult(wallEntry);
				}

				closeSession(session);
			}
		}

		return wallEntry;
	}

	public List<WallEntry> findByGroupId(long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(groupId) };

		List<WallEntry> list = (List<WallEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_GROUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT wallEntry FROM WallEntry wallEntry WHERE ");

				query.append("wallEntry.groupId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("wallEntry.createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WallEntry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_GROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WallEntry> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	public List<WallEntry> findByGroupId(long groupId, int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(groupId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WallEntry> list = (List<WallEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_GROUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT wallEntry FROM WallEntry wallEntry WHERE ");

				query.append("wallEntry.groupId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("wallEntry.");
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

					query.append("wallEntry.createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				list = (List<WallEntry>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WallEntry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_GROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WallEntry findByGroupId_First(long groupId, OrderByComparator obc)
		throws NoSuchWallEntryException, SystemException {
		List<WallEntry> list = findByGroupId(groupId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WallEntry exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWallEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WallEntry findByGroupId_Last(long groupId, OrderByComparator obc)
		throws NoSuchWallEntryException, SystemException {
		int count = countByGroupId(groupId);

		List<WallEntry> list = findByGroupId(groupId, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WallEntry exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWallEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WallEntry[] findByGroupId_PrevAndNext(long wallEntryId,
		long groupId, OrderByComparator obc)
		throws NoSuchWallEntryException, SystemException {
		WallEntry wallEntry = findByPrimaryKey(wallEntryId);

		int count = countByGroupId(groupId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("SELECT wallEntry FROM WallEntry wallEntry WHERE ");

			query.append("wallEntry.groupId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("wallEntry.");
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

				query.append("wallEntry.createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					wallEntry);

			WallEntry[] array = new WallEntryImpl[3];

			array[0] = (WallEntry)objArray[0];
			array[1] = (WallEntry)objArray[1];
			array[2] = (WallEntry)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<WallEntry> findByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(userId) };

		List<WallEntry> list = (List<WallEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT wallEntry FROM WallEntry wallEntry WHERE ");

				query.append("wallEntry.userId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("wallEntry.createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WallEntry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_USERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WallEntry> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	public List<WallEntry> findByUserId(long userId, int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(userId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WallEntry> list = (List<WallEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_USERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT wallEntry FROM WallEntry wallEntry WHERE ");

				query.append("wallEntry.userId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("wallEntry.");
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

					query.append("wallEntry.createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<WallEntry>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WallEntry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_USERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WallEntry findByUserId_First(long userId, OrderByComparator obc)
		throws NoSuchWallEntryException, SystemException {
		List<WallEntry> list = findByUserId(userId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WallEntry exists with the key {");

			msg.append("userId=" + userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWallEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WallEntry findByUserId_Last(long userId, OrderByComparator obc)
		throws NoSuchWallEntryException, SystemException {
		int count = countByUserId(userId);

		List<WallEntry> list = findByUserId(userId, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WallEntry exists with the key {");

			msg.append("userId=" + userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWallEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WallEntry[] findByUserId_PrevAndNext(long wallEntryId, long userId,
		OrderByComparator obc) throws NoSuchWallEntryException, SystemException {
		WallEntry wallEntry = findByPrimaryKey(wallEntryId);

		int count = countByUserId(userId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("SELECT wallEntry FROM WallEntry wallEntry WHERE ");

			query.append("wallEntry.userId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("wallEntry.");
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

				query.append("wallEntry.createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					wallEntry);

			WallEntry[] array = new WallEntryImpl[3];

			array[0] = (WallEntry)objArray[0];
			array[1] = (WallEntry)objArray[1];
			array[2] = (WallEntry)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<WallEntry> findByG_U(long groupId, long userId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(groupId), new Long(userId) };

		List<WallEntry> list = (List<WallEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_G_U,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT wallEntry FROM WallEntry wallEntry WHERE ");

				query.append("wallEntry.groupId = ?");

				query.append(" AND ");

				query.append("wallEntry.userId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("wallEntry.createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WallEntry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_G_U, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WallEntry> findByG_U(long groupId, long userId, int start,
		int end) throws SystemException {
		return findByG_U(groupId, userId, start, end, null);
	}

	public List<WallEntry> findByG_U(long groupId, long userId, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(groupId), new Long(userId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WallEntry> list = (List<WallEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_G_U,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT wallEntry FROM WallEntry wallEntry WHERE ");

				query.append("wallEntry.groupId = ?");

				query.append(" AND ");

				query.append("wallEntry.userId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("wallEntry.");
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

					query.append("wallEntry.createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				list = (List<WallEntry>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WallEntry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_G_U,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WallEntry findByG_U_First(long groupId, long userId,
		OrderByComparator obc) throws NoSuchWallEntryException, SystemException {
		List<WallEntry> list = findByG_U(groupId, userId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WallEntry exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(", ");
			msg.append("userId=" + userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWallEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WallEntry findByG_U_Last(long groupId, long userId,
		OrderByComparator obc) throws NoSuchWallEntryException, SystemException {
		int count = countByG_U(groupId, userId);

		List<WallEntry> list = findByG_U(groupId, userId, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WallEntry exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(", ");
			msg.append("userId=" + userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWallEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WallEntry[] findByG_U_PrevAndNext(long wallEntryId, long groupId,
		long userId, OrderByComparator obc)
		throws NoSuchWallEntryException, SystemException {
		WallEntry wallEntry = findByPrimaryKey(wallEntryId);

		int count = countByG_U(groupId, userId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("SELECT wallEntry FROM WallEntry wallEntry WHERE ");

			query.append("wallEntry.groupId = ?");

			query.append(" AND ");

			query.append("wallEntry.userId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("wallEntry.");
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

				query.append("wallEntry.createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(userId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					wallEntry);

			WallEntry[] array = new WallEntryImpl[3];

			array[0] = (WallEntry)objArray[0];
			array[1] = (WallEntry)objArray[1];
			array[2] = (WallEntry)objArray[2];

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

	public List<WallEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<WallEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<WallEntry> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WallEntry> list = (List<WallEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT wallEntry FROM WallEntry wallEntry ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("wallEntry.");
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

					query.append("wallEntry.createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<WallEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WallEntry>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WallEntry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByGroupId(long groupId) throws SystemException {
		for (WallEntry wallEntry : findByGroupId(groupId)) {
			remove(wallEntry);
		}
	}

	public void removeByUserId(long userId) throws SystemException {
		for (WallEntry wallEntry : findByUserId(userId)) {
			remove(wallEntry);
		}
	}

	public void removeByG_U(long groupId, long userId)
		throws SystemException {
		for (WallEntry wallEntry : findByG_U(groupId, userId)) {
			remove(wallEntry);
		}
	}

	public void removeAll() throws SystemException {
		for (WallEntry wallEntry : findAll()) {
			remove(wallEntry);
		}
	}

	public int countByGroupId(long groupId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(groupId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(wallEntry) ");
				query.append("FROM WallEntry wallEntry WHERE ");

				query.append("wallEntry.groupId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

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

	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(userId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(wallEntry) ");
				query.append("FROM WallEntry wallEntry WHERE ");

				query.append("wallEntry.userId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

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

	public int countByG_U(long groupId, long userId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(groupId), new Long(userId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_U,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(wallEntry) ");
				query.append("FROM WallEntry wallEntry WHERE ");

				query.append("wallEntry.groupId = ?");

				query.append(" AND ");

				query.append("wallEntry.userId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_U, finderArgs,
					count);

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
						"SELECT COUNT(wallEntry) FROM WallEntry wallEntry");

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
						"value.object.listener.com.liferay.wol.model.WallEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WallEntry>> listenersList = new ArrayList<ModelListener<WallEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WallEntry>)Class.forName(
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
	private static Log _log = LogFactoryUtil.getLog(WallEntryPersistenceImpl.class);
}