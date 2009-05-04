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

package com.liferay.chat.service.persistence;

import com.liferay.chat.NoSuchEntryException;
import com.liferay.chat.model.Entry;
import com.liferay.chat.model.impl.EntryImpl;
import com.liferay.chat.model.impl.EntryModelImpl;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="EntryPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class EntryPersistenceImpl extends BasePersistenceImpl
	implements EntryPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = EntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_CREATEDATE = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCreateDate", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_CREATEDATE = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCreateDate",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_CREATEDATE = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByCreateDate", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_FROMUSERID = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByFromUserId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_FROMUSERID = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByFromUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_FROMUSERID = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByFromUserId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_TOUSERID = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByToUserId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_TOUSERID = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByToUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_TOUSERID = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByToUserId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_C_F = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByC_F",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_C_F = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByC_F",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_F = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByC_F",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_C_T = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByC_T",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_C_T = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByC_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_T = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByC_T",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_C_F_T = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByC_F_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_C_F_T = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByC_F_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_F_T = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByC_F_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_BY_F_T_C = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByF_T_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_F_T_C = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByF_T_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_F_T_C = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByF_T_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(Entry entry) {
		EntityCacheUtil.putResult(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryImpl.class, entry.getPrimaryKey(), entry);
	}

	public void cacheResult(List<Entry> entries) {
		for (Entry entry : entries) {
			if (EntityCacheUtil.getResult(EntryModelImpl.ENTITY_CACHE_ENABLED,
						EntryImpl.class, entry.getPrimaryKey(), this) == null) {
				cacheResult(entry);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(EntryImpl.class.getName());
		EntityCacheUtil.clearCache(EntryImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public Entry create(long entryId) {
		Entry entry = new EntryImpl();

		entry.setNew(true);
		entry.setPrimaryKey(entryId);

		return entry;
	}

	public Entry remove(long entryId)
		throws NoSuchEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Entry entry = (Entry)session.get(EntryImpl.class, new Long(entryId));

			if (entry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No Entry exists with the primary key " +
						entryId);
				}

				throw new NoSuchEntryException(
					"No Entry exists with the primary key " + entryId);
			}

			return remove(entry);
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

	public Entry remove(Entry entry) throws SystemException {
		for (ModelListener<Entry> listener : listeners) {
			listener.onBeforeRemove(entry);
		}

		entry = removeImpl(entry);

		for (ModelListener<Entry> listener : listeners) {
			listener.onAfterRemove(entry);
		}

		return entry;
	}

	protected Entry removeImpl(Entry entry) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (entry.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(EntryImpl.class,
						entry.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(entry);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryImpl.class, entry.getPrimaryKey());

		return entry;
	}

	public Entry update(Entry entry) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(Entry entry) method. Use update(Entry entry, boolean merge) instead.");
		}

		return update(entry, false);
	}

	public Entry update(Entry entry, boolean merge) throws SystemException {
		boolean isNew = entry.isNew();

		for (ModelListener<Entry> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(entry);
			}
			else {
				listener.onBeforeUpdate(entry);
			}
		}

		entry = updateImpl(entry, merge);

		for (ModelListener<Entry> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(entry);
			}
			else {
				listener.onAfterUpdate(entry);
			}
		}

		return entry;
	}

	public Entry updateImpl(com.liferay.chat.model.Entry entry, boolean merge)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, entry, merge);

			entry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryImpl.class, entry.getPrimaryKey(), entry);

		return entry;
	}

	public Entry findByPrimaryKey(long entryId)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByPrimaryKey(entryId);

		if (entry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No Entry exists with the primary key " + entryId);
			}

			throw new NoSuchEntryException(
				"No Entry exists with the primary key " + entryId);
		}

		return entry;
	}

	public Entry fetchByPrimaryKey(long entryId) throws SystemException {
		Entry entry = (Entry)EntityCacheUtil.getResult(EntryModelImpl.ENTITY_CACHE_ENABLED,
				EntryImpl.class, entryId, this);

		if (entry == null) {
			Session session = null;

			try {
				session = openSession();

				entry = (Entry)session.get(EntryImpl.class, new Long(entryId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (entry != null) {
					cacheResult(entry);
				}

				closeSession(session);
			}
		}

		return entry;
	}

	public List<Entry> findByCreateDate(long createDate)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(createDate) };

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_CREATEDATE,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("createDate = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Entry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_CREATEDATE,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<Entry> findByCreateDate(long createDate, int start, int end)
		throws SystemException {
		return findByCreateDate(createDate, start, end, null);
	}

	public List<Entry> findByCreateDate(long createDate, int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(createDate),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_CREATEDATE,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("createDate = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				list = (List<Entry>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Entry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_CREATEDATE,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public Entry findByCreateDate_First(long createDate, OrderByComparator obc)
		throws NoSuchEntryException, SystemException {
		List<Entry> list = findByCreateDate(createDate, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Entry exists with the key {");

			msg.append("createDate=" + createDate);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Entry findByCreateDate_Last(long createDate, OrderByComparator obc)
		throws NoSuchEntryException, SystemException {
		int count = countByCreateDate(createDate);

		List<Entry> list = findByCreateDate(createDate, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Entry exists with the key {");

			msg.append("createDate=" + createDate);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Entry[] findByCreateDate_PrevAndNext(long entryId, long createDate,
		OrderByComparator obc) throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		int count = countByCreateDate(createDate);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.chat.model.Entry WHERE ");

			query.append("createDate = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(createDate);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, entry);

			Entry[] array = new EntryImpl[3];

			array[0] = (Entry)objArray[0];
			array[1] = (Entry)objArray[1];
			array[2] = (Entry)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Entry> findByFromUserId(long fromUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(fromUserId) };

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_FROMUSERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("fromUserId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fromUserId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Entry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_FROMUSERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<Entry> findByFromUserId(long fromUserId, int start, int end)
		throws SystemException {
		return findByFromUserId(fromUserId, start, end, null);
	}

	public List<Entry> findByFromUserId(long fromUserId, int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(fromUserId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_FROMUSERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("fromUserId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fromUserId);

				list = (List<Entry>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Entry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_FROMUSERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public Entry findByFromUserId_First(long fromUserId, OrderByComparator obc)
		throws NoSuchEntryException, SystemException {
		List<Entry> list = findByFromUserId(fromUserId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Entry exists with the key {");

			msg.append("fromUserId=" + fromUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Entry findByFromUserId_Last(long fromUserId, OrderByComparator obc)
		throws NoSuchEntryException, SystemException {
		int count = countByFromUserId(fromUserId);

		List<Entry> list = findByFromUserId(fromUserId, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Entry exists with the key {");

			msg.append("fromUserId=" + fromUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Entry[] findByFromUserId_PrevAndNext(long entryId, long fromUserId,
		OrderByComparator obc) throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		int count = countByFromUserId(fromUserId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.chat.model.Entry WHERE ");

			query.append("fromUserId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(fromUserId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, entry);

			Entry[] array = new EntryImpl[3];

			array[0] = (Entry)objArray[0];
			array[1] = (Entry)objArray[1];
			array[2] = (Entry)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Entry> findByToUserId(long toUserId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(toUserId) };

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_TOUSERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("toUserId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(toUserId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Entry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_TOUSERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<Entry> findByToUserId(long toUserId, int start, int end)
		throws SystemException {
		return findByToUserId(toUserId, start, end, null);
	}

	public List<Entry> findByToUserId(long toUserId, int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(toUserId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_TOUSERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("toUserId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(toUserId);

				list = (List<Entry>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Entry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_TOUSERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public Entry findByToUserId_First(long toUserId, OrderByComparator obc)
		throws NoSuchEntryException, SystemException {
		List<Entry> list = findByToUserId(toUserId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Entry exists with the key {");

			msg.append("toUserId=" + toUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Entry findByToUserId_Last(long toUserId, OrderByComparator obc)
		throws NoSuchEntryException, SystemException {
		int count = countByToUserId(toUserId);

		List<Entry> list = findByToUserId(toUserId, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Entry exists with the key {");

			msg.append("toUserId=" + toUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Entry[] findByToUserId_PrevAndNext(long entryId, long toUserId,
		OrderByComparator obc) throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		int count = countByToUserId(toUserId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.chat.model.Entry WHERE ");

			query.append("toUserId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(toUserId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, entry);

			Entry[] array = new EntryImpl[3];

			array[0] = (Entry)objArray[0];
			array[1] = (Entry)objArray[1];
			array[2] = (Entry)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Entry> findByC_F(long createDate, long fromUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(createDate), new Long(fromUserId)
			};

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_F,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("createDate = ?");

				query.append(" AND ");

				query.append("fromUserId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				qPos.add(fromUserId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Entry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_F, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<Entry> findByC_F(long createDate, long fromUserId, int start,
		int end) throws SystemException {
		return findByC_F(createDate, fromUserId, start, end, null);
	}

	public List<Entry> findByC_F(long createDate, long fromUserId, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(createDate), new Long(fromUserId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_C_F,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("createDate = ?");

				query.append(" AND ");

				query.append("fromUserId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				qPos.add(fromUserId);

				list = (List<Entry>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Entry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_C_F,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public Entry findByC_F_First(long createDate, long fromUserId,
		OrderByComparator obc) throws NoSuchEntryException, SystemException {
		List<Entry> list = findByC_F(createDate, fromUserId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Entry exists with the key {");

			msg.append("createDate=" + createDate);

			msg.append(", ");
			msg.append("fromUserId=" + fromUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Entry findByC_F_Last(long createDate, long fromUserId,
		OrderByComparator obc) throws NoSuchEntryException, SystemException {
		int count = countByC_F(createDate, fromUserId);

		List<Entry> list = findByC_F(createDate, fromUserId, count - 1, count,
				obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Entry exists with the key {");

			msg.append("createDate=" + createDate);

			msg.append(", ");
			msg.append("fromUserId=" + fromUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Entry[] findByC_F_PrevAndNext(long entryId, long createDate,
		long fromUserId, OrderByComparator obc)
		throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		int count = countByC_F(createDate, fromUserId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.chat.model.Entry WHERE ");

			query.append("createDate = ?");

			query.append(" AND ");

			query.append("fromUserId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(createDate);

			qPos.add(fromUserId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, entry);

			Entry[] array = new EntryImpl[3];

			array[0] = (Entry)objArray[0];
			array[1] = (Entry)objArray[1];
			array[2] = (Entry)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Entry> findByC_T(long createDate, long toUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(createDate), new Long(toUserId)
			};

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_T,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("createDate = ?");

				query.append(" AND ");

				query.append("toUserId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				qPos.add(toUserId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Entry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_T, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<Entry> findByC_T(long createDate, long toUserId, int start,
		int end) throws SystemException {
		return findByC_T(createDate, toUserId, start, end, null);
	}

	public List<Entry> findByC_T(long createDate, long toUserId, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(createDate), new Long(toUserId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_C_T,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("createDate = ?");

				query.append(" AND ");

				query.append("toUserId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				qPos.add(toUserId);

				list = (List<Entry>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Entry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_C_T,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public Entry findByC_T_First(long createDate, long toUserId,
		OrderByComparator obc) throws NoSuchEntryException, SystemException {
		List<Entry> list = findByC_T(createDate, toUserId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Entry exists with the key {");

			msg.append("createDate=" + createDate);

			msg.append(", ");
			msg.append("toUserId=" + toUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Entry findByC_T_Last(long createDate, long toUserId,
		OrderByComparator obc) throws NoSuchEntryException, SystemException {
		int count = countByC_T(createDate, toUserId);

		List<Entry> list = findByC_T(createDate, toUserId, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Entry exists with the key {");

			msg.append("createDate=" + createDate);

			msg.append(", ");
			msg.append("toUserId=" + toUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Entry[] findByC_T_PrevAndNext(long entryId, long createDate,
		long toUserId, OrderByComparator obc)
		throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		int count = countByC_T(createDate, toUserId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.chat.model.Entry WHERE ");

			query.append("createDate = ?");

			query.append(" AND ");

			query.append("toUserId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(createDate);

			qPos.add(toUserId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, entry);

			Entry[] array = new EntryImpl[3];

			array[0] = (Entry)objArray[0];
			array[1] = (Entry)objArray[1];
			array[2] = (Entry)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Entry> findByC_F_T(long createDate, long fromUserId,
		long toUserId) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(createDate), new Long(fromUserId), new Long(toUserId)
			};

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_F_T,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("createDate = ?");

				query.append(" AND ");

				query.append("fromUserId = ?");

				query.append(" AND ");

				query.append("toUserId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				qPos.add(fromUserId);

				qPos.add(toUserId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Entry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_F_T,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<Entry> findByC_F_T(long createDate, long fromUserId,
		long toUserId, int start, int end) throws SystemException {
		return findByC_F_T(createDate, fromUserId, toUserId, start, end, null);
	}

	public List<Entry> findByC_F_T(long createDate, long fromUserId,
		long toUserId, int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(createDate), new Long(fromUserId), new Long(toUserId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_C_F_T,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("createDate = ?");

				query.append(" AND ");

				query.append("fromUserId = ?");

				query.append(" AND ");

				query.append("toUserId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				qPos.add(fromUserId);

				qPos.add(toUserId);

				list = (List<Entry>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Entry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_C_F_T,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public Entry findByC_F_T_First(long createDate, long fromUserId,
		long toUserId, OrderByComparator obc)
		throws NoSuchEntryException, SystemException {
		List<Entry> list = findByC_F_T(createDate, fromUserId, toUserId, 0, 1,
				obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Entry exists with the key {");

			msg.append("createDate=" + createDate);

			msg.append(", ");
			msg.append("fromUserId=" + fromUserId);

			msg.append(", ");
			msg.append("toUserId=" + toUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Entry findByC_F_T_Last(long createDate, long fromUserId,
		long toUserId, OrderByComparator obc)
		throws NoSuchEntryException, SystemException {
		int count = countByC_F_T(createDate, fromUserId, toUserId);

		List<Entry> list = findByC_F_T(createDate, fromUserId, toUserId,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Entry exists with the key {");

			msg.append("createDate=" + createDate);

			msg.append(", ");
			msg.append("fromUserId=" + fromUserId);

			msg.append(", ");
			msg.append("toUserId=" + toUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Entry[] findByC_F_T_PrevAndNext(long entryId, long createDate,
		long fromUserId, long toUserId, OrderByComparator obc)
		throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		int count = countByC_F_T(createDate, fromUserId, toUserId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.chat.model.Entry WHERE ");

			query.append("createDate = ?");

			query.append(" AND ");

			query.append("fromUserId = ?");

			query.append(" AND ");

			query.append("toUserId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(createDate);

			qPos.add(fromUserId);

			qPos.add(toUserId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, entry);

			Entry[] array = new EntryImpl[3];

			array[0] = (Entry)objArray[0];
			array[1] = (Entry)objArray[1];
			array[2] = (Entry)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Entry> findByF_T_C(long fromUserId, long toUserId,
		String content) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(fromUserId), new Long(toUserId),
				
				content
			};

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_F_T_C,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("fromUserId = ?");

				query.append(" AND ");

				query.append("toUserId = ?");

				query.append(" AND ");

				if (content == null) {
					query.append("content IS NULL");
				}
				else {
					query.append("content = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fromUserId);

				qPos.add(toUserId);

				if (content != null) {
					qPos.add(content);
				}

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Entry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_F_T_C,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<Entry> findByF_T_C(long fromUserId, long toUserId,
		String content, int start, int end) throws SystemException {
		return findByF_T_C(fromUserId, toUserId, content, start, end, null);
	}

	public List<Entry> findByF_T_C(long fromUserId, long toUserId,
		String content, int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(fromUserId), new Long(toUserId),
				
				content,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_F_T_C,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("fromUserId = ?");

				query.append(" AND ");

				query.append("toUserId = ?");

				query.append(" AND ");

				if (content == null) {
					query.append("content IS NULL");
				}
				else {
					query.append("content = ?");
				}

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fromUserId);

				qPos.add(toUserId);

				if (content != null) {
					qPos.add(content);
				}

				list = (List<Entry>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Entry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_F_T_C,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public Entry findByF_T_C_First(long fromUserId, long toUserId,
		String content, OrderByComparator obc)
		throws NoSuchEntryException, SystemException {
		List<Entry> list = findByF_T_C(fromUserId, toUserId, content, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Entry exists with the key {");

			msg.append("fromUserId=" + fromUserId);

			msg.append(", ");
			msg.append("toUserId=" + toUserId);

			msg.append(", ");
			msg.append("content=" + content);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Entry findByF_T_C_Last(long fromUserId, long toUserId,
		String content, OrderByComparator obc)
		throws NoSuchEntryException, SystemException {
		int count = countByF_T_C(fromUserId, toUserId, content);

		List<Entry> list = findByF_T_C(fromUserId, toUserId, content,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Entry exists with the key {");

			msg.append("fromUserId=" + fromUserId);

			msg.append(", ");
			msg.append("toUserId=" + toUserId);

			msg.append(", ");
			msg.append("content=" + content);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Entry[] findByF_T_C_PrevAndNext(long entryId, long fromUserId,
		long toUserId, String content, OrderByComparator obc)
		throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		int count = countByF_T_C(fromUserId, toUserId, content);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.chat.model.Entry WHERE ");

			query.append("fromUserId = ?");

			query.append(" AND ");

			query.append("toUserId = ?");

			query.append(" AND ");

			if (content == null) {
				query.append("content IS NULL");
			}
			else {
				query.append("content = ?");
			}

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(fromUserId);

			qPos.add(toUserId);

			if (content != null) {
				qPos.add(content);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, entry);

			Entry[] array = new EntryImpl[3];

			array[0] = (Entry)objArray[0];
			array[1] = (Entry)objArray[1];
			array[2] = (Entry)objArray[2];

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

	public List<Entry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<Entry> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<Entry> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.chat.model.Entry ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Entry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByCreateDate(long createDate) throws SystemException {
		for (Entry entry : findByCreateDate(createDate)) {
			remove(entry);
		}
	}

	public void removeByFromUserId(long fromUserId) throws SystemException {
		for (Entry entry : findByFromUserId(fromUserId)) {
			remove(entry);
		}
	}

	public void removeByToUserId(long toUserId) throws SystemException {
		for (Entry entry : findByToUserId(toUserId)) {
			remove(entry);
		}
	}

	public void removeByC_F(long createDate, long fromUserId)
		throws SystemException {
		for (Entry entry : findByC_F(createDate, fromUserId)) {
			remove(entry);
		}
	}

	public void removeByC_T(long createDate, long toUserId)
		throws SystemException {
		for (Entry entry : findByC_T(createDate, toUserId)) {
			remove(entry);
		}
	}

	public void removeByC_F_T(long createDate, long fromUserId, long toUserId)
		throws SystemException {
		for (Entry entry : findByC_F_T(createDate, fromUserId, toUserId)) {
			remove(entry);
		}
	}

	public void removeByF_T_C(long fromUserId, long toUserId, String content)
		throws SystemException {
		for (Entry entry : findByF_T_C(fromUserId, toUserId, content)) {
			remove(entry);
		}
	}

	public void removeAll() throws SystemException {
		for (Entry entry : findAll()) {
			remove(entry);
		}
	}

	public int countByCreateDate(long createDate) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(createDate) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CREATEDATE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("createDate = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CREATEDATE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByFromUserId(long fromUserId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(fromUserId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FROMUSERID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("fromUserId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fromUserId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FROMUSERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByToUserId(long toUserId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(toUserId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TOUSERID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("toUserId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(toUserId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TOUSERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByC_F(long createDate, long fromUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(createDate), new Long(fromUserId)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_F,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("createDate = ?");

				query.append(" AND ");

				query.append("fromUserId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				qPos.add(fromUserId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_F, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByC_T(long createDate, long toUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(createDate), new Long(toUserId)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_T,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("createDate = ?");

				query.append(" AND ");

				query.append("toUserId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				qPos.add(toUserId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_T, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByC_F_T(long createDate, long fromUserId, long toUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(createDate), new Long(fromUserId), new Long(toUserId)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_F_T,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("createDate = ?");

				query.append(" AND ");

				query.append("fromUserId = ?");

				query.append(" AND ");

				query.append("toUserId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				qPos.add(fromUserId);

				qPos.add(toUserId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_F_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByF_T_C(long fromUserId, long toUserId, String content)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(fromUserId), new Long(toUserId),
				
				content
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_F_T_C,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("fromUserId = ?");

				query.append(" AND ");

				query.append("toUserId = ?");

				query.append(" AND ");

				if (content == null) {
					query.append("content IS NULL");
				}
				else {
					query.append("content = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fromUserId);

				qPos.add(toUserId);

				if (content != null) {
					qPos.add(content);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_F_T_C,
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
						"SELECT COUNT(*) FROM com.liferay.chat.model.Entry");

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
						"value.object.listener.com.liferay.chat.model.Entry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Entry>> listenersList = new ArrayList<ModelListener<Entry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Entry>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.chat.service.persistence.EntryPersistence.impl")
	protected com.liferay.chat.service.persistence.EntryPersistence entryPersistence;
	@BeanReference(name = "com.liferay.chat.service.persistence.StatusPersistence.impl")
	protected com.liferay.chat.service.persistence.StatusPersistence statusPersistence;
	private static Log _log = LogFactoryUtil.getLog(EntryPersistenceImpl.class);
}