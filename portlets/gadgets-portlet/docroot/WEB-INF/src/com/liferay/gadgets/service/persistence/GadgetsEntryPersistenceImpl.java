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

package com.liferay.gadgets.service.persistence;

import com.liferay.gadgets.NoSuchEntryException;
import com.liferay.gadgets.model.GadgetsEntry;
import com.liferay.gadgets.model.impl.GadgetsEntryImpl;
import com.liferay.gadgets.model.impl.GadgetsEntryModelImpl;

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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="GadgetsEntryPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class GadgetsEntryPersistenceImpl extends BasePersistenceImpl<GadgetsEntry>
	implements GadgetsEntryPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = GadgetsEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(GadgetsEntryModelImpl.ENTITY_CACHE_ENABLED,
			GadgetsEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompanyId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_COMPANYID = new FinderPath(GadgetsEntryModelImpl.ENTITY_CACHE_ENABLED,
			GadgetsEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(GadgetsEntryModelImpl.ENTITY_CACHE_ENABLED,
			GadgetsEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByCompanyId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(GadgetsEntryModelImpl.ENTITY_CACHE_ENABLED,
			GadgetsEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GadgetsEntryModelImpl.ENTITY_CACHE_ENABLED,
			GadgetsEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(GadgetsEntry gadgetsEntry) {
		EntityCacheUtil.putResult(GadgetsEntryModelImpl.ENTITY_CACHE_ENABLED,
			GadgetsEntryImpl.class, gadgetsEntry.getPrimaryKey(), gadgetsEntry);
	}

	public void cacheResult(List<GadgetsEntry> gadgetsEntries) {
		for (GadgetsEntry gadgetsEntry : gadgetsEntries) {
			if (EntityCacheUtil.getResult(
						GadgetsEntryModelImpl.ENTITY_CACHE_ENABLED,
						GadgetsEntryImpl.class, gadgetsEntry.getPrimaryKey(),
						this) == null) {
				cacheResult(gadgetsEntry);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(GadgetsEntryImpl.class.getName());
		EntityCacheUtil.clearCache(GadgetsEntryImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public GadgetsEntry create(long gadgetsEntryId) {
		GadgetsEntry gadgetsEntry = new GadgetsEntryImpl();

		gadgetsEntry.setNew(true);
		gadgetsEntry.setPrimaryKey(gadgetsEntryId);

		return gadgetsEntry;
	}

	public GadgetsEntry remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public GadgetsEntry remove(long gadgetsEntryId)
		throws NoSuchEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			GadgetsEntry gadgetsEntry = (GadgetsEntry)session.get(GadgetsEntryImpl.class,
					new Long(gadgetsEntryId));

			if (gadgetsEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						gadgetsEntryId);
				}

				throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					gadgetsEntryId);
			}

			return remove(gadgetsEntry);
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

	public GadgetsEntry remove(GadgetsEntry gadgetsEntry)
		throws SystemException {
		for (ModelListener<GadgetsEntry> listener : listeners) {
			listener.onBeforeRemove(gadgetsEntry);
		}

		gadgetsEntry = removeImpl(gadgetsEntry);

		for (ModelListener<GadgetsEntry> listener : listeners) {
			listener.onAfterRemove(gadgetsEntry);
		}

		return gadgetsEntry;
	}

	protected GadgetsEntry removeImpl(GadgetsEntry gadgetsEntry)
		throws SystemException {
		gadgetsEntry = toUnwrappedModel(gadgetsEntry);

		Session session = null;

		try {
			session = openSession();

			if (gadgetsEntry.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(GadgetsEntryImpl.class,
						gadgetsEntry.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(gadgetsEntry);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(GadgetsEntryModelImpl.ENTITY_CACHE_ENABLED,
			GadgetsEntryImpl.class, gadgetsEntry.getPrimaryKey());

		return gadgetsEntry;
	}

	public GadgetsEntry updateImpl(
		com.liferay.gadgets.model.GadgetsEntry gadgetsEntry, boolean merge)
		throws SystemException {
		gadgetsEntry = toUnwrappedModel(gadgetsEntry);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, gadgetsEntry, merge);

			gadgetsEntry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(GadgetsEntryModelImpl.ENTITY_CACHE_ENABLED,
			GadgetsEntryImpl.class, gadgetsEntry.getPrimaryKey(), gadgetsEntry);

		return gadgetsEntry;
	}

	protected GadgetsEntry toUnwrappedModel(GadgetsEntry gadgetsEntry) {
		if (gadgetsEntry instanceof GadgetsEntryImpl) {
			return gadgetsEntry;
		}

		GadgetsEntryImpl gadgetsEntryImpl = new GadgetsEntryImpl();

		gadgetsEntryImpl.setNew(gadgetsEntry.isNew());
		gadgetsEntryImpl.setPrimaryKey(gadgetsEntry.getPrimaryKey());

		gadgetsEntryImpl.setGadgetsEntryId(gadgetsEntry.getGadgetsEntryId());
		gadgetsEntryImpl.setCompanyId(gadgetsEntry.getCompanyId());
		gadgetsEntryImpl.setCreateDate(gadgetsEntry.getCreateDate());
		gadgetsEntryImpl.setModifiedDate(gadgetsEntry.getModifiedDate());
		gadgetsEntryImpl.setName(gadgetsEntry.getName());
		gadgetsEntryImpl.setUrl(gadgetsEntry.getUrl());
		gadgetsEntryImpl.setXml(gadgetsEntry.getXml());

		return gadgetsEntryImpl;
	}

	public GadgetsEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public GadgetsEntry findByPrimaryKey(long gadgetsEntryId)
		throws NoSuchEntryException, SystemException {
		GadgetsEntry gadgetsEntry = fetchByPrimaryKey(gadgetsEntryId);

		if (gadgetsEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + gadgetsEntryId);
			}

			throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				gadgetsEntryId);
		}

		return gadgetsEntry;
	}

	public GadgetsEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public GadgetsEntry fetchByPrimaryKey(long gadgetsEntryId)
		throws SystemException {
		GadgetsEntry gadgetsEntry = (GadgetsEntry)EntityCacheUtil.getResult(GadgetsEntryModelImpl.ENTITY_CACHE_ENABLED,
				GadgetsEntryImpl.class, gadgetsEntryId, this);

		if (gadgetsEntry == null) {
			Session session = null;

			try {
				session = openSession();

				gadgetsEntry = (GadgetsEntry)session.get(GadgetsEntryImpl.class,
						new Long(gadgetsEntryId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (gadgetsEntry != null) {
					cacheResult(gadgetsEntry);
				}

				closeSession(session);
			}
		}

		return gadgetsEntry;
	}

	public List<GadgetsEntry> findByCompanyId(long companyId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId) };

		List<GadgetsEntry> list = (List<GadgetsEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_SELECT_GADGETSENTRY_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				query.append(GadgetsEntryModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<GadgetsEntry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<GadgetsEntry> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	public List<GadgetsEntry> findByCompanyId(long companyId, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<GadgetsEntry> list = (List<GadgetsEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
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

				query.append(_SQL_SELECT_GADGETSENTRY_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				if (obc != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
				}

				else {
					query.append(GadgetsEntryModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<GadgetsEntry>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<GadgetsEntry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public GadgetsEntry findByCompanyId_First(long companyId,
		OrderByComparator obc) throws NoSuchEntryException, SystemException {
		List<GadgetsEntry> list = findByCompanyId(companyId, 0, 1, obc);

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

	public GadgetsEntry findByCompanyId_Last(long companyId,
		OrderByComparator obc) throws NoSuchEntryException, SystemException {
		int count = countByCompanyId(companyId);

		List<GadgetsEntry> list = findByCompanyId(companyId, count - 1, count,
				obc);

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

	public GadgetsEntry[] findByCompanyId_PrevAndNext(long gadgetsEntryId,
		long companyId, OrderByComparator obc)
		throws NoSuchEntryException, SystemException {
		GadgetsEntry gadgetsEntry = findByPrimaryKey(gadgetsEntryId);

		int count = countByCompanyId(companyId);

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

			query.append(_SQL_SELECT_GADGETSENTRY_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (obc != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
			}

			else {
				query.append(GadgetsEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					gadgetsEntry);

			GadgetsEntry[] array = new GadgetsEntryImpl[3];

			array[0] = (GadgetsEntry)objArray[0];
			array[1] = (GadgetsEntry)objArray[1];
			array[2] = (GadgetsEntry)objArray[2];

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

	public List<GadgetsEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<GadgetsEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<GadgetsEntry> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<GadgetsEntry> list = (List<GadgetsEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_GADGETSENTRY);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);

					sql = query.toString();
				}

				else {
					sql = _SQL_SELECT_GADGETSENTRY.concat(GadgetsEntryModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (obc == null) {
					list = (List<GadgetsEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<GadgetsEntry>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<GadgetsEntry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByCompanyId(long companyId) throws SystemException {
		for (GadgetsEntry gadgetsEntry : findByCompanyId(companyId)) {
			remove(gadgetsEntry);
		}
	}

	public void removeAll() throws SystemException {
		for (GadgetsEntry gadgetsEntry : findAll()) {
			remove(gadgetsEntry);
		}
	}

	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_GADGETSENTRY_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				String sql = query.toString();

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

	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_GADGETSENTRY);

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
						"value.object.listener.com.liferay.gadgets.model.GadgetsEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<GadgetsEntry>> listenersList = new ArrayList<ModelListener<GadgetsEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<GadgetsEntry>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.gadgets.service.persistence.GadgetsEntryPersistence")
	protected com.liferay.gadgets.service.persistence.GadgetsEntryPersistence gadgetsEntryPersistence;
	private static final String _SQL_SELECT_GADGETSENTRY = "SELECT gadgetsEntry FROM GadgetsEntry gadgetsEntry";
	private static final String _SQL_SELECT_GADGETSENTRY_WHERE = "SELECT gadgetsEntry FROM GadgetsEntry gadgetsEntry WHERE ";
	private static final String _SQL_COUNT_GADGETSENTRY = "SELECT COUNT(gadgetsEntry) FROM GadgetsEntry gadgetsEntry";
	private static final String _SQL_COUNT_GADGETSENTRY_WHERE = "SELECT COUNT(gadgetsEntry) FROM GadgetsEntry gadgetsEntry WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "gadgetsEntry.companyId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "gadgetsEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GadgetsEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No GadgetsEntry exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(GadgetsEntryPersistenceImpl.class);
}