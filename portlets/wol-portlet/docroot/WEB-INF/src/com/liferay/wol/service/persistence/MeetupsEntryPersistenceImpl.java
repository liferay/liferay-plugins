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

import com.liferay.wol.NoSuchMeetupsEntryException;
import com.liferay.wol.model.MeetupsEntry;
import com.liferay.wol.model.impl.MeetupsEntryImpl;
import com.liferay.wol.model.impl.MeetupsEntryModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="MeetupsEntryPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class MeetupsEntryPersistenceImpl extends BasePersistenceImpl
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

	public MeetupsEntry remove(long meetupsEntryId)
		throws NoSuchMeetupsEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			MeetupsEntry meetupsEntry = (MeetupsEntry)session.get(MeetupsEntryImpl.class,
					new Long(meetupsEntryId));

			if (meetupsEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No MeetupsEntry exists with the primary key " +
						meetupsEntryId);
				}

				throw new NoSuchMeetupsEntryException(
					"No MeetupsEntry exists with the primary key " +
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

	public MeetupsEntry update(MeetupsEntry meetupsEntry)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(MeetupsEntry meetupsEntry) method. Use update(MeetupsEntry meetupsEntry, boolean merge) instead.");
		}

		return update(meetupsEntry, false);
	}

	public MeetupsEntry update(MeetupsEntry meetupsEntry, boolean merge)
		throws SystemException {
		boolean isNew = meetupsEntry.isNew();

		for (ModelListener<MeetupsEntry> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(meetupsEntry);
			}
			else {
				listener.onBeforeUpdate(meetupsEntry);
			}
		}

		meetupsEntry = updateImpl(meetupsEntry, merge);

		for (ModelListener<MeetupsEntry> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(meetupsEntry);
			}
			else {
				listener.onAfterUpdate(meetupsEntry);
			}
		}

		return meetupsEntry;
	}

	public MeetupsEntry updateImpl(
		com.liferay.wol.model.MeetupsEntry meetupsEntry, boolean merge)
		throws SystemException {
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

	public MeetupsEntry findByPrimaryKey(long meetupsEntryId)
		throws NoSuchMeetupsEntryException, SystemException {
		MeetupsEntry meetupsEntry = fetchByPrimaryKey(meetupsEntryId);

		if (meetupsEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No MeetupsEntry exists with the primary key " +
					meetupsEntryId);
			}

			throw new NoSuchMeetupsEntryException(
				"No MeetupsEntry exists with the primary key " +
				meetupsEntryId);
		}

		return meetupsEntry;
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

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT meetupsEntry FROM MeetupsEntry meetupsEntry WHERE ");

				query.append("meetupsEntry.companyId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("meetupsEntry.startDate DESC");

				Query q = session.createQuery(query.toString());

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

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT meetupsEntry FROM MeetupsEntry meetupsEntry WHERE ");

				query.append("meetupsEntry.companyId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("meetupsEntry.");
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

					query.append("meetupsEntry.startDate DESC");
				}

				Query q = session.createQuery(query.toString());

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
			StringBuilder msg = new StringBuilder();

			msg.append("No MeetupsEntry exists with the key {");

			msg.append("companyId=" + companyId);

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
			StringBuilder msg = new StringBuilder();

			msg.append("No MeetupsEntry exists with the key {");

			msg.append("companyId=" + companyId);

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

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT meetupsEntry FROM MeetupsEntry meetupsEntry WHERE ");

			query.append("meetupsEntry.companyId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("meetupsEntry.");
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

				query.append("meetupsEntry.startDate DESC");
			}

			Query q = session.createQuery(query.toString());

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

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT meetupsEntry FROM MeetupsEntry meetupsEntry ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("meetupsEntry.");
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

					query.append("meetupsEntry.startDate DESC");
				}

				Query q = session.createQuery(query.toString());

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

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(meetupsEntry) ");
				query.append("FROM MeetupsEntry meetupsEntry WHERE ");

				query.append("meetupsEntry.companyId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

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

				Query q = session.createQuery(
						"SELECT COUNT(meetupsEntry) FROM MeetupsEntry meetupsEntry");

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
						"value.object.listener.com.liferay.wol.model.MeetupsEntry")));

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
	private static Log _log = LogFactoryUtil.getLog(MeetupsEntryPersistenceImpl.class);
}