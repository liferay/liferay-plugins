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

package com.liferay.socialnetworking.service.persistence;

import com.liferay.portal.NoSuchModelException;
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
import com.liferay.portal.kernel.exception.SystemException;
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

import com.liferay.socialnetworking.NoSuchMeetupsEntryException;
import com.liferay.socialnetworking.model.MeetupsEntry;
import com.liferay.socialnetworking.model.impl.MeetupsEntryImpl;
import com.liferay.socialnetworking.model.impl.MeetupsEntryModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="MeetupsEntryPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class MeetupsEntryPersistenceImpl extends BasePersistenceImpl<MeetupsEntry>
	implements MeetupsEntryPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = MeetupsEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(MeetupsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompanyId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_COMPANYID = new FinderPath(MeetupsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(MeetupsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByCompanyId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(MeetupsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MeetupsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(MeetupsEntry meetupsEntry) {
		EntityCacheUtil.putResult(MeetupsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsEntryImpl.class, meetupsEntry.getPrimaryKey(), meetupsEntry);
	}

	public void cacheResult(List<MeetupsEntry> meetupsEntries) {
		for (MeetupsEntry meetupsEntry : meetupsEntries) {
			if (EntityCacheUtil.getResult(
						MeetupsEntryModelImpl.ENTITY_CACHE_ENABLED,
						MeetupsEntryImpl.class, meetupsEntry.getPrimaryKey(),
						this) == null) {
				cacheResult(meetupsEntry);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(MeetupsEntryImpl.class.getName());
		EntityCacheUtil.clearCache(MeetupsEntryImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public MeetupsEntry create(long meetupsEntryId) {
		MeetupsEntry meetupsEntry = new MeetupsEntryImpl();

		meetupsEntry.setNew(true);
		meetupsEntry.setPrimaryKey(meetupsEntryId);

		return meetupsEntry;
	}

	public MeetupsEntry remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public MeetupsEntry remove(long meetupsEntryId)
		throws NoSuchMeetupsEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			MeetupsEntry meetupsEntry = (MeetupsEntry)session.get(MeetupsEntryImpl.class,
					new Long(meetupsEntryId));

			if (meetupsEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						meetupsEntryId);
				}

				throw new NoSuchMeetupsEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					meetupsEntryId);
			}

			return remove(meetupsEntry);
		}
		catch (NoSuchMeetupsEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public MeetupsEntry remove(MeetupsEntry meetupsEntry)
		throws SystemException {
		for (ModelListener<MeetupsEntry> listener : listeners) {
			listener.onBeforeRemove(meetupsEntry);
		}

		meetupsEntry = removeImpl(meetupsEntry);

		for (ModelListener<MeetupsEntry> listener : listeners) {
			listener.onAfterRemove(meetupsEntry);
		}

		return meetupsEntry;
	}

	protected MeetupsEntry removeImpl(MeetupsEntry meetupsEntry)
		throws SystemException {
		meetupsEntry = toUnwrappedModel(meetupsEntry);

		Session session = null;

		try {
			session = openSession();

			if (meetupsEntry.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(MeetupsEntryImpl.class,
						meetupsEntry.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(meetupsEntry);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(MeetupsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsEntryImpl.class, meetupsEntry.getPrimaryKey());

		return meetupsEntry;
	}

	public MeetupsEntry updateImpl(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry,
		boolean merge) throws SystemException {
		meetupsEntry = toUnwrappedModel(meetupsEntry);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, meetupsEntry, merge);

			meetupsEntry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(MeetupsEntryModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsEntryImpl.class, meetupsEntry.getPrimaryKey(), meetupsEntry);

		return meetupsEntry;
	}

	protected MeetupsEntry toUnwrappedModel(MeetupsEntry meetupsEntry) {
		if (meetupsEntry instanceof MeetupsEntryImpl) {
			return meetupsEntry;
		}

		MeetupsEntryImpl meetupsEntryImpl = new MeetupsEntryImpl();

		meetupsEntryImpl.setNew(meetupsEntry.isNew());
		meetupsEntryImpl.setPrimaryKey(meetupsEntry.getPrimaryKey());

		meetupsEntryImpl.setMeetupsEntryId(meetupsEntry.getMeetupsEntryId());
		meetupsEntryImpl.setCompanyId(meetupsEntry.getCompanyId());
		meetupsEntryImpl.setUserId(meetupsEntry.getUserId());
		meetupsEntryImpl.setUserName(meetupsEntry.getUserName());
		meetupsEntryImpl.setCreateDate(meetupsEntry.getCreateDate());
		meetupsEntryImpl.setModifiedDate(meetupsEntry.getModifiedDate());
		meetupsEntryImpl.setTitle(meetupsEntry.getTitle());
		meetupsEntryImpl.setDescription(meetupsEntry.getDescription());
		meetupsEntryImpl.setStartDate(meetupsEntry.getStartDate());
		meetupsEntryImpl.setEndDate(meetupsEntry.getEndDate());
		meetupsEntryImpl.setTotalAttendees(meetupsEntry.getTotalAttendees());
		meetupsEntryImpl.setMaxAttendees(meetupsEntry.getMaxAttendees());
		meetupsEntryImpl.setPrice(meetupsEntry.getPrice());
		meetupsEntryImpl.setThumbnailId(meetupsEntry.getThumbnailId());

		return meetupsEntryImpl;
	}

	public MeetupsEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public MeetupsEntry findByPrimaryKey(long meetupsEntryId)
		throws NoSuchMeetupsEntryException, SystemException {
		MeetupsEntry meetupsEntry = fetchByPrimaryKey(meetupsEntryId);

		if (meetupsEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + meetupsEntryId);
			}

			throw new NoSuchMeetupsEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				meetupsEntryId);
		}

		return meetupsEntry;
	}

	public MeetupsEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public MeetupsEntry fetchByPrimaryKey(long meetupsEntryId)
		throws SystemException {
		MeetupsEntry meetupsEntry = (MeetupsEntry)EntityCacheUtil.getResult(MeetupsEntryModelImpl.ENTITY_CACHE_ENABLED,
				MeetupsEntryImpl.class, meetupsEntryId, this);

		if (meetupsEntry == null) {
			Session session = null;

			try {
				session = openSession();

				meetupsEntry = (MeetupsEntry)session.get(MeetupsEntryImpl.class,
						new Long(meetupsEntryId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (meetupsEntry != null) {
					cacheResult(meetupsEntry);
				}

				closeSession(session);
			}
		}

		return meetupsEntry;
	}

	public List<MeetupsEntry> findByCompanyId(long companyId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId) };

		List<MeetupsEntry> list = (List<MeetupsEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_SELECT_MEETUPSENTRY_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				query.append(MeetupsEntryModelImpl.ORDER_BY_JPQL);

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
					list = new ArrayList<MeetupsEntry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<MeetupsEntry> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	public List<MeetupsEntry> findByCompanyId(long companyId, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<MeetupsEntry> list = (List<MeetupsEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
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

				query.append(_SQL_SELECT_MEETUPSENTRY_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				if (obc != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
				}

				else {
					query.append(MeetupsEntryModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<MeetupsEntry>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<MeetupsEntry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public MeetupsEntry findByCompanyId_First(long companyId,
		OrderByComparator obc)
		throws NoSuchMeetupsEntryException, SystemException {
		List<MeetupsEntry> list = findByCompanyId(companyId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMeetupsEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public MeetupsEntry findByCompanyId_Last(long companyId,
		OrderByComparator obc)
		throws NoSuchMeetupsEntryException, SystemException {
		int count = countByCompanyId(companyId);

		List<MeetupsEntry> list = findByCompanyId(companyId, count - 1, count,
				obc);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMeetupsEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public MeetupsEntry[] findByCompanyId_PrevAndNext(long meetupsEntryId,
		long companyId, OrderByComparator obc)
		throws NoSuchMeetupsEntryException, SystemException {
		MeetupsEntry meetupsEntry = findByPrimaryKey(meetupsEntryId);

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

			query.append(_SQL_SELECT_MEETUPSENTRY_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (obc != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
			}

			else {
				query.append(MeetupsEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					meetupsEntry);

			MeetupsEntry[] array = new MeetupsEntryImpl[3];

			array[0] = (MeetupsEntry)objArray[0];
			array[1] = (MeetupsEntry)objArray[1];
			array[2] = (MeetupsEntry)objArray[2];

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

	public List<MeetupsEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<MeetupsEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<MeetupsEntry> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<MeetupsEntry> list = (List<MeetupsEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_MEETUPSENTRY);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);

					sql = query.toString();
				}

				else {
					sql = _SQL_SELECT_MEETUPSENTRY.concat(MeetupsEntryModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (obc == null) {
					list = (List<MeetupsEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<MeetupsEntry>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<MeetupsEntry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByCompanyId(long companyId) throws SystemException {
		for (MeetupsEntry meetupsEntry : findByCompanyId(companyId)) {
			remove(meetupsEntry);
		}
	}

	public void removeAll() throws SystemException {
		for (MeetupsEntry meetupsEntry : findAll()) {
			remove(meetupsEntry);
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

				query.append(_SQL_COUNT_MEETUPSENTRY_WHERE);

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

				Query q = session.createQuery(_SQL_COUNT_MEETUPSENTRY);

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
						"value.object.listener.com.liferay.socialnetworking.model.MeetupsEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<MeetupsEntry>> listenersList = new ArrayList<ModelListener<MeetupsEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<MeetupsEntry>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.socialnetworking.service.persistence.MeetupsEntryPersistence")
	protected com.liferay.socialnetworking.service.persistence.MeetupsEntryPersistence meetupsEntryPersistence;
	@BeanReference(name = "com.liferay.socialnetworking.service.persistence.MeetupsRegistrationPersistence")
	protected com.liferay.socialnetworking.service.persistence.MeetupsRegistrationPersistence meetupsRegistrationPersistence;
	@BeanReference(name = "com.liferay.socialnetworking.service.persistence.WallEntryPersistence")
	protected com.liferay.socialnetworking.service.persistence.WallEntryPersistence wallEntryPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.ResourcePersistence")
	protected com.liferay.portal.service.persistence.ResourcePersistence resourcePersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.UserPersistence")
	protected com.liferay.portal.service.persistence.UserPersistence userPersistence;
	private static final String _SQL_SELECT_MEETUPSENTRY = "SELECT meetupsEntry FROM MeetupsEntry meetupsEntry";
	private static final String _SQL_SELECT_MEETUPSENTRY_WHERE = "SELECT meetupsEntry FROM MeetupsEntry meetupsEntry WHERE ";
	private static final String _SQL_COUNT_MEETUPSENTRY = "SELECT COUNT(meetupsEntry) FROM MeetupsEntry meetupsEntry";
	private static final String _SQL_COUNT_MEETUPSENTRY_WHERE = "SELECT COUNT(meetupsEntry) FROM MeetupsEntry meetupsEntry WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "meetupsEntry.companyId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "meetupsEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MeetupsEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MeetupsEntry exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(MeetupsEntryPersistenceImpl.class);
}