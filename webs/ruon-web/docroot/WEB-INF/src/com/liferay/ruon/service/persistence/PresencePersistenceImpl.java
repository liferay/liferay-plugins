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

package com.liferay.ruon.service.persistence;

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

import com.liferay.ruon.NoSuchPresenceException;
import com.liferay.ruon.model.Presence;
import com.liferay.ruon.model.impl.PresenceImpl;
import com.liferay.ruon.model.impl.PresenceModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="PresencePersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PresencePersistenceImpl extends BasePersistenceImpl
	implements PresencePersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = PresenceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_USERID = new FinderPath(PresenceModelImpl.ENTITY_CACHE_ENABLED,
			PresenceModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByUserId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_USERID = new FinderPath(PresenceModelImpl.ENTITY_CACHE_ENABLED,
			PresenceModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(PresenceModelImpl.ENTITY_CACHE_ENABLED,
			PresenceModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByUserId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_U_N = new FinderPath(PresenceModelImpl.ENTITY_CACHE_ENABLED,
			PresenceModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
			"fetchByU_N",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_U_N = new FinderPath(PresenceModelImpl.ENTITY_CACHE_ENABLED,
			PresenceModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByU_N",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_U_O = new FinderPath(PresenceModelImpl.ENTITY_CACHE_ENABLED,
			PresenceModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByU_O",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_U_O = new FinderPath(PresenceModelImpl.ENTITY_CACHE_ENABLED,
			PresenceModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByU_O",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_U_O = new FinderPath(PresenceModelImpl.ENTITY_CACHE_ENABLED,
			PresenceModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByU_O",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(PresenceModelImpl.ENTITY_CACHE_ENABLED,
			PresenceModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PresenceModelImpl.ENTITY_CACHE_ENABLED,
			PresenceModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(Presence presence) {
		EntityCacheUtil.putResult(PresenceModelImpl.ENTITY_CACHE_ENABLED,
			PresenceImpl.class, presence.getPrimaryKey(), presence);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_N,
			new Object[] {
				new Long(presence.getUserId()),
				new Long(presence.getNetworkId())
			}, presence);
	}

	public void cacheResult(List<Presence> presences) {
		for (Presence presence : presences) {
			if (EntityCacheUtil.getResult(
						PresenceModelImpl.ENTITY_CACHE_ENABLED,
						PresenceImpl.class, presence.getPrimaryKey(), this) == null) {
				cacheResult(presence);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(PresenceImpl.class.getName());
		EntityCacheUtil.clearCache(PresenceImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public Presence create(long presenceId) {
		Presence presence = new PresenceImpl();

		presence.setNew(true);
		presence.setPrimaryKey(presenceId);

		return presence;
	}

	public Presence remove(long presenceId)
		throws NoSuchPresenceException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Presence presence = (Presence)session.get(PresenceImpl.class,
					new Long(presenceId));

			if (presence == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No Presence exists with the primary key " +
						presenceId);
				}

				throw new NoSuchPresenceException(
					"No Presence exists with the primary key " + presenceId);
			}

			return remove(presence);
		}
		catch (NoSuchPresenceException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public Presence remove(Presence presence) throws SystemException {
		for (ModelListener<Presence> listener : listeners) {
			listener.onBeforeRemove(presence);
		}

		presence = removeImpl(presence);

		for (ModelListener<Presence> listener : listeners) {
			listener.onAfterRemove(presence);
		}

		return presence;
	}

	protected Presence removeImpl(Presence presence) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (presence.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(PresenceImpl.class,
						presence.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(presence);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		PresenceModelImpl presenceModelImpl = (PresenceModelImpl)presence;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_N,
			new Object[] {
				new Long(presenceModelImpl.getOriginalUserId()),
				new Long(presenceModelImpl.getOriginalNetworkId())
			});

		EntityCacheUtil.removeResult(PresenceModelImpl.ENTITY_CACHE_ENABLED,
			PresenceImpl.class, presence.getPrimaryKey());

		return presence;
	}

	public Presence update(Presence presence) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(Presence presence) method. Use update(Presence presence, boolean merge) instead.");
		}

		return update(presence, false);
	}

	public Presence update(Presence presence, boolean merge)
		throws SystemException {
		boolean isNew = presence.isNew();

		for (ModelListener<Presence> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(presence);
			}
			else {
				listener.onBeforeUpdate(presence);
			}
		}

		presence = updateImpl(presence, merge);

		for (ModelListener<Presence> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(presence);
			}
			else {
				listener.onAfterUpdate(presence);
			}
		}

		return presence;
	}

	public Presence updateImpl(com.liferay.ruon.model.Presence presence,
		boolean merge) throws SystemException {
		boolean isNew = presence.isNew();

		PresenceModelImpl presenceModelImpl = (PresenceModelImpl)presence;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, presence, merge);

			presence.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(PresenceModelImpl.ENTITY_CACHE_ENABLED,
			PresenceImpl.class, presence.getPrimaryKey(), presence);

		if (!isNew &&
				((presence.getUserId() != presenceModelImpl.getOriginalUserId()) ||
				(presence.getNetworkId() != presenceModelImpl.getOriginalNetworkId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_N,
				new Object[] {
					new Long(presenceModelImpl.getOriginalUserId()),
					new Long(presenceModelImpl.getOriginalNetworkId())
				});
		}

		if (isNew ||
				((presence.getUserId() != presenceModelImpl.getOriginalUserId()) ||
				(presence.getNetworkId() != presenceModelImpl.getOriginalNetworkId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_N,
				new Object[] {
					new Long(presence.getUserId()),
					new Long(presence.getNetworkId())
				}, presence);
		}

		return presence;
	}

	public Presence findByPrimaryKey(long presenceId)
		throws NoSuchPresenceException, SystemException {
		Presence presence = fetchByPrimaryKey(presenceId);

		if (presence == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No Presence exists with the primary key " +
					presenceId);
			}

			throw new NoSuchPresenceException(
				"No Presence exists with the primary key " + presenceId);
		}

		return presence;
	}

	public Presence fetchByPrimaryKey(long presenceId)
		throws SystemException {
		Presence presence = (Presence)EntityCacheUtil.getResult(PresenceModelImpl.ENTITY_CACHE_ENABLED,
				PresenceImpl.class, presenceId, this);

		if (presence == null) {
			Session session = null;

			try {
				session = openSession();

				presence = (Presence)session.get(PresenceImpl.class,
						new Long(presenceId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (presence != null) {
					cacheResult(presence);
				}

				closeSession(session);
			}
		}

		return presence;
	}

	public List<Presence> findByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(userId) };

		List<Presence> list = (List<Presence>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT presence FROM Presence presence WHERE ");

				query.append("presence.userId = ?");

				query.append(" ");

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
					list = new ArrayList<Presence>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_USERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<Presence> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	public List<Presence> findByUserId(long userId, int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(userId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<Presence> list = (List<Presence>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_USERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT presence FROM Presence presence WHERE ");

				query.append("presence.userId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("presence.");
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

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<Presence>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Presence>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_USERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public Presence findByUserId_First(long userId, OrderByComparator obc)
		throws NoSuchPresenceException, SystemException {
		List<Presence> list = findByUserId(userId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Presence exists with the key {");

			msg.append("userId=" + userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPresenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Presence findByUserId_Last(long userId, OrderByComparator obc)
		throws NoSuchPresenceException, SystemException {
		int count = countByUserId(userId);

		List<Presence> list = findByUserId(userId, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Presence exists with the key {");

			msg.append("userId=" + userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPresenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Presence[] findByUserId_PrevAndNext(long presenceId, long userId,
		OrderByComparator obc) throws NoSuchPresenceException, SystemException {
		Presence presence = findByPrimaryKey(presenceId);

		int count = countByUserId(userId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("SELECT presence FROM Presence presence WHERE ");

			query.append("presence.userId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("presence.");
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

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, presence);

			Presence[] array = new PresenceImpl[3];

			array[0] = (Presence)objArray[0];
			array[1] = (Presence)objArray[1];
			array[2] = (Presence)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public Presence findByU_N(long userId, long networkId)
		throws NoSuchPresenceException, SystemException {
		Presence presence = fetchByU_N(userId, networkId);

		if (presence == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Presence exists with the key {");

			msg.append("userId=" + userId);

			msg.append(", ");
			msg.append("networkId=" + networkId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchPresenceException(msg.toString());
		}

		return presence;
	}

	public Presence fetchByU_N(long userId, long networkId)
		throws SystemException {
		return fetchByU_N(userId, networkId, true);
	}

	public Presence fetchByU_N(long userId, long networkId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(userId), new Long(networkId) };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_U_N,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT presence FROM Presence presence WHERE ");

				query.append("presence.userId = ?");

				query.append(" AND ");

				query.append("presence.networkId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(networkId);

				List<Presence> list = q.list();

				result = list;

				Presence presence = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_N,
						finderArgs, list);
				}
				else {
					presence = list.get(0);

					cacheResult(presence);

					if ((presence.getUserId() != userId) ||
							(presence.getNetworkId() != networkId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_N,
							finderArgs, presence);
					}
				}

				return presence;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_N,
						finderArgs, new ArrayList<Presence>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List) {
				return null;
			}
			else {
				return (Presence)result;
			}
		}
	}

	public List<Presence> findByU_O(long userId, boolean online)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(userId), Boolean.valueOf(online)
			};

		List<Presence> list = (List<Presence>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_U_O,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT presence FROM Presence presence WHERE ");

				query.append("presence.userId = ?");

				query.append(" AND ");

				query.append("presence.online = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(online);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Presence>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_U_O, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<Presence> findByU_O(long userId, boolean online, int start,
		int end) throws SystemException {
		return findByU_O(userId, online, start, end, null);
	}

	public List<Presence> findByU_O(long userId, boolean online, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(userId), Boolean.valueOf(online),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<Presence> list = (List<Presence>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_U_O,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT presence FROM Presence presence WHERE ");

				query.append("presence.userId = ?");

				query.append(" AND ");

				query.append("presence.online = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("presence.");
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

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(online);

				list = (List<Presence>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Presence>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_U_O,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public Presence findByU_O_First(long userId, boolean online,
		OrderByComparator obc) throws NoSuchPresenceException, SystemException {
		List<Presence> list = findByU_O(userId, online, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Presence exists with the key {");

			msg.append("userId=" + userId);

			msg.append(", ");
			msg.append("online=" + online);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPresenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Presence findByU_O_Last(long userId, boolean online,
		OrderByComparator obc) throws NoSuchPresenceException, SystemException {
		int count = countByU_O(userId, online);

		List<Presence> list = findByU_O(userId, online, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Presence exists with the key {");

			msg.append("userId=" + userId);

			msg.append(", ");
			msg.append("online=" + online);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPresenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Presence[] findByU_O_PrevAndNext(long presenceId, long userId,
		boolean online, OrderByComparator obc)
		throws NoSuchPresenceException, SystemException {
		Presence presence = findByPrimaryKey(presenceId);

		int count = countByU_O(userId, online);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("SELECT presence FROM Presence presence WHERE ");

			query.append("presence.userId = ?");

			query.append(" AND ");

			query.append("presence.online = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("presence.");
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

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			qPos.add(online);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, presence);

			Presence[] array = new PresenceImpl[3];

			array[0] = (Presence)objArray[0];
			array[1] = (Presence)objArray[1];
			array[2] = (Presence)objArray[2];

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

	public List<Presence> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<Presence> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<Presence> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<Presence> list = (List<Presence>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT presence FROM Presence presence ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("presence.");
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

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<Presence>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Presence>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Presence>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByUserId(long userId) throws SystemException {
		for (Presence presence : findByUserId(userId)) {
			remove(presence);
		}
	}

	public void removeByU_N(long userId, long networkId)
		throws NoSuchPresenceException, SystemException {
		Presence presence = findByU_N(userId, networkId);

		remove(presence);
	}

	public void removeByU_O(long userId, boolean online)
		throws SystemException {
		for (Presence presence : findByU_O(userId, online)) {
			remove(presence);
		}
	}

	public void removeAll() throws SystemException {
		for (Presence presence : findAll()) {
			remove(presence);
		}
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

				query.append("SELECT COUNT(presence) ");
				query.append("FROM Presence presence WHERE ");

				query.append("presence.userId = ?");

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

	public int countByU_N(long userId, long networkId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(userId), new Long(networkId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_N,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(presence) ");
				query.append("FROM Presence presence WHERE ");

				query.append("presence.userId = ?");

				query.append(" AND ");

				query.append("presence.networkId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(networkId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_N, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByU_O(long userId, boolean online)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(userId), Boolean.valueOf(online)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_O,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(presence) ");
				query.append("FROM Presence presence WHERE ");

				query.append("presence.userId = ?");

				query.append(" AND ");

				query.append("presence.online = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(online);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_O, finderArgs,
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
						"SELECT COUNT(presence) FROM Presence presence");

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
						"value.object.listener.com.liferay.ruon.model.Presence")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Presence>> listenersList = new ArrayList<ModelListener<Presence>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Presence>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.ruon.service.persistence.NetworkPersistence.impl")
	protected com.liferay.ruon.service.persistence.NetworkPersistence networkPersistence;
	@BeanReference(name = "com.liferay.ruon.service.persistence.PresencePersistence.impl")
	protected com.liferay.ruon.service.persistence.PresencePersistence presencePersistence;
	private static Log _log = LogFactoryUtil.getLog(PresencePersistenceImpl.class);
}