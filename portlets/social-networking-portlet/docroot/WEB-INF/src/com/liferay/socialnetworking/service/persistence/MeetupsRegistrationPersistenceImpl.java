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

package com.liferay.socialnetworking.service.persistence;

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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;
import com.liferay.socialnetworking.model.MeetupsRegistration;
import com.liferay.socialnetworking.model.impl.MeetupsRegistrationImpl;
import com.liferay.socialnetworking.model.impl.MeetupsRegistrationModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="MeetupsRegistrationPersistenceImpl.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class MeetupsRegistrationPersistenceImpl extends BasePersistenceImpl<MeetupsRegistration>
	implements MeetupsRegistrationPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = MeetupsRegistrationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_MEETUPSENTRYID = new FinderPath(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByMeetupsEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_MEETUPSENTRYID = new FinderPath(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByMeetupsEntryId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_MEETUPSENTRYID = new FinderPath(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByMeetupsEntryId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_U_ME = new FinderPath(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByU_ME",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_U_ME = new FinderPath(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByU_ME",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_ME_S = new FinderPath(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByME_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_ME_S = new FinderPath(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByME_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_ME_S = new FinderPath(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByME_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(MeetupsRegistration meetupsRegistration) {
		EntityCacheUtil.putResult(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationImpl.class, meetupsRegistration.getPrimaryKey(),
			meetupsRegistration);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_ME,
			new Object[] {
				new Long(meetupsRegistration.getUserId()),
				new Long(meetupsRegistration.getMeetupsEntryId())
			}, meetupsRegistration);
	}

	public void cacheResult(List<MeetupsRegistration> meetupsRegistrations) {
		for (MeetupsRegistration meetupsRegistration : meetupsRegistrations) {
			if (EntityCacheUtil.getResult(
						MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
						MeetupsRegistrationImpl.class,
						meetupsRegistration.getPrimaryKey(), this) == null) {
				cacheResult(meetupsRegistration);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(MeetupsRegistrationImpl.class.getName());
		EntityCacheUtil.clearCache(MeetupsRegistrationImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public MeetupsRegistration create(long meetupsRegistrationId) {
		MeetupsRegistration meetupsRegistration = new MeetupsRegistrationImpl();

		meetupsRegistration.setNew(true);
		meetupsRegistration.setPrimaryKey(meetupsRegistrationId);

		return meetupsRegistration;
	}

	public MeetupsRegistration remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public MeetupsRegistration remove(long meetupsRegistrationId)
		throws NoSuchMeetupsRegistrationException, SystemException {
		Session session = null;

		try {
			session = openSession();

			MeetupsRegistration meetupsRegistration = (MeetupsRegistration)session.get(MeetupsRegistrationImpl.class,
					new Long(meetupsRegistrationId));

			if (meetupsRegistration == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"No MeetupsRegistration exists with the primary key " +
						meetupsRegistrationId);
				}

				throw new NoSuchMeetupsRegistrationException(
					"No MeetupsRegistration exists with the primary key " +
					meetupsRegistrationId);
			}

			return remove(meetupsRegistration);
		}
		catch (NoSuchMeetupsRegistrationException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public MeetupsRegistration remove(MeetupsRegistration meetupsRegistration)
		throws SystemException {
		for (ModelListener<MeetupsRegistration> listener : listeners) {
			listener.onBeforeRemove(meetupsRegistration);
		}

		meetupsRegistration = removeImpl(meetupsRegistration);

		for (ModelListener<MeetupsRegistration> listener : listeners) {
			listener.onAfterRemove(meetupsRegistration);
		}

		return meetupsRegistration;
	}

	protected MeetupsRegistration removeImpl(
		MeetupsRegistration meetupsRegistration) throws SystemException {
		meetupsRegistration = toUnwrappedModel(meetupsRegistration);

		Session session = null;

		try {
			session = openSession();

			if (meetupsRegistration.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(MeetupsRegistrationImpl.class,
						meetupsRegistration.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(meetupsRegistration);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		MeetupsRegistrationModelImpl meetupsRegistrationModelImpl = (MeetupsRegistrationModelImpl)meetupsRegistration;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_ME,
			new Object[] {
				new Long(meetupsRegistrationModelImpl.getOriginalUserId()),
				new Long(meetupsRegistrationModelImpl.getOriginalMeetupsEntryId())
			});

		EntityCacheUtil.removeResult(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationImpl.class, meetupsRegistration.getPrimaryKey());

		return meetupsRegistration;
	}

	public MeetupsRegistration updateImpl(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration,
		boolean merge) throws SystemException {
		meetupsRegistration = toUnwrappedModel(meetupsRegistration);

		boolean isNew = meetupsRegistration.isNew();

		MeetupsRegistrationModelImpl meetupsRegistrationModelImpl = (MeetupsRegistrationModelImpl)meetupsRegistration;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, meetupsRegistration, merge);

			meetupsRegistration.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			MeetupsRegistrationImpl.class, meetupsRegistration.getPrimaryKey(),
			meetupsRegistration);

		if (!isNew &&
				((meetupsRegistration.getUserId() != meetupsRegistrationModelImpl.getOriginalUserId()) ||
				(meetupsRegistration.getMeetupsEntryId() != meetupsRegistrationModelImpl.getOriginalMeetupsEntryId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_ME,
				new Object[] {
					new Long(meetupsRegistrationModelImpl.getOriginalUserId()),
					new Long(meetupsRegistrationModelImpl.getOriginalMeetupsEntryId())
				});
		}

		if (isNew ||
				((meetupsRegistration.getUserId() != meetupsRegistrationModelImpl.getOriginalUserId()) ||
				(meetupsRegistration.getMeetupsEntryId() != meetupsRegistrationModelImpl.getOriginalMeetupsEntryId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_ME,
				new Object[] {
					new Long(meetupsRegistration.getUserId()),
					new Long(meetupsRegistration.getMeetupsEntryId())
				}, meetupsRegistration);
		}

		return meetupsRegistration;
	}

	protected MeetupsRegistration toUnwrappedModel(
		MeetupsRegistration meetupsRegistration) {
		if (meetupsRegistration instanceof MeetupsRegistrationImpl) {
			return meetupsRegistration;
		}

		MeetupsRegistrationImpl meetupsRegistrationImpl = new MeetupsRegistrationImpl();

		meetupsRegistrationImpl.setNew(meetupsRegistration.isNew());
		meetupsRegistrationImpl.setPrimaryKey(meetupsRegistration.getPrimaryKey());

		meetupsRegistrationImpl.setMeetupsRegistrationId(meetupsRegistration.getMeetupsRegistrationId());
		meetupsRegistrationImpl.setCompanyId(meetupsRegistration.getCompanyId());
		meetupsRegistrationImpl.setUserId(meetupsRegistration.getUserId());
		meetupsRegistrationImpl.setUserName(meetupsRegistration.getUserName());
		meetupsRegistrationImpl.setCreateDate(meetupsRegistration.getCreateDate());
		meetupsRegistrationImpl.setModifiedDate(meetupsRegistration.getModifiedDate());
		meetupsRegistrationImpl.setMeetupsEntryId(meetupsRegistration.getMeetupsEntryId());
		meetupsRegistrationImpl.setStatus(meetupsRegistration.getStatus());
		meetupsRegistrationImpl.setComments(meetupsRegistration.getComments());

		return meetupsRegistrationImpl;
	}

	public MeetupsRegistration findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public MeetupsRegistration findByPrimaryKey(long meetupsRegistrationId)
		throws NoSuchMeetupsRegistrationException, SystemException {
		MeetupsRegistration meetupsRegistration = fetchByPrimaryKey(meetupsRegistrationId);

		if (meetupsRegistration == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No MeetupsRegistration exists with the primary key " +
					meetupsRegistrationId);
			}

			throw new NoSuchMeetupsRegistrationException(
				"No MeetupsRegistration exists with the primary key " +
				meetupsRegistrationId);
		}

		return meetupsRegistration;
	}

	public MeetupsRegistration fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public MeetupsRegistration fetchByPrimaryKey(long meetupsRegistrationId)
		throws SystemException {
		MeetupsRegistration meetupsRegistration = (MeetupsRegistration)EntityCacheUtil.getResult(MeetupsRegistrationModelImpl.ENTITY_CACHE_ENABLED,
				MeetupsRegistrationImpl.class, meetupsRegistrationId, this);

		if (meetupsRegistration == null) {
			Session session = null;

			try {
				session = openSession();

				meetupsRegistration = (MeetupsRegistration)session.get(MeetupsRegistrationImpl.class,
						new Long(meetupsRegistrationId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (meetupsRegistration != null) {
					cacheResult(meetupsRegistration);
				}

				closeSession(session);
			}
		}

		return meetupsRegistration;
	}

	public List<MeetupsRegistration> findByMeetupsEntryId(long meetupsEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(meetupsEntryId) };

		List<MeetupsRegistration> list = (List<MeetupsRegistration>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_MEETUPSENTRYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT meetupsRegistration FROM MeetupsRegistration meetupsRegistration WHERE ");

				query.append("meetupsRegistration.meetupsEntryId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("meetupsRegistration.modifiedDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupsEntryId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<MeetupsRegistration>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_MEETUPSENTRYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<MeetupsRegistration> findByMeetupsEntryId(long meetupsEntryId,
		int start, int end) throws SystemException {
		return findByMeetupsEntryId(meetupsEntryId, start, end, null);
	}

	public List<MeetupsRegistration> findByMeetupsEntryId(long meetupsEntryId,
		int start, int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(meetupsEntryId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<MeetupsRegistration> list = (List<MeetupsRegistration>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_MEETUPSENTRYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT meetupsRegistration FROM MeetupsRegistration meetupsRegistration WHERE ");

				query.append("meetupsRegistration.meetupsEntryId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("meetupsRegistration.");
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

					query.append("meetupsRegistration.modifiedDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupsEntryId);

				list = (List<MeetupsRegistration>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<MeetupsRegistration>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_MEETUPSENTRYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public MeetupsRegistration findByMeetupsEntryId_First(long meetupsEntryId,
		OrderByComparator obc)
		throws NoSuchMeetupsRegistrationException, SystemException {
		List<MeetupsRegistration> list = findByMeetupsEntryId(meetupsEntryId,
				0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No MeetupsRegistration exists with the key {");

			msg.append("meetupsEntryId=" + meetupsEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMeetupsRegistrationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public MeetupsRegistration findByMeetupsEntryId_Last(long meetupsEntryId,
		OrderByComparator obc)
		throws NoSuchMeetupsRegistrationException, SystemException {
		int count = countByMeetupsEntryId(meetupsEntryId);

		List<MeetupsRegistration> list = findByMeetupsEntryId(meetupsEntryId,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No MeetupsRegistration exists with the key {");

			msg.append("meetupsEntryId=" + meetupsEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMeetupsRegistrationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public MeetupsRegistration[] findByMeetupsEntryId_PrevAndNext(
		long meetupsRegistrationId, long meetupsEntryId, OrderByComparator obc)
		throws NoSuchMeetupsRegistrationException, SystemException {
		MeetupsRegistration meetupsRegistration = findByPrimaryKey(meetupsRegistrationId);

		int count = countByMeetupsEntryId(meetupsEntryId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT meetupsRegistration FROM MeetupsRegistration meetupsRegistration WHERE ");

			query.append("meetupsRegistration.meetupsEntryId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("meetupsRegistration.");
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

				query.append("meetupsRegistration.modifiedDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(meetupsEntryId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					meetupsRegistration);

			MeetupsRegistration[] array = new MeetupsRegistrationImpl[3];

			array[0] = (MeetupsRegistration)objArray[0];
			array[1] = (MeetupsRegistration)objArray[1];
			array[2] = (MeetupsRegistration)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public MeetupsRegistration findByU_ME(long userId, long meetupsEntryId)
		throws NoSuchMeetupsRegistrationException, SystemException {
		MeetupsRegistration meetupsRegistration = fetchByU_ME(userId,
				meetupsEntryId);

		if (meetupsRegistration == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No MeetupsRegistration exists with the key {");

			msg.append("userId=" + userId);

			msg.append(", ");
			msg.append("meetupsEntryId=" + meetupsEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchMeetupsRegistrationException(msg.toString());
		}

		return meetupsRegistration;
	}

	public MeetupsRegistration fetchByU_ME(long userId, long meetupsEntryId)
		throws SystemException {
		return fetchByU_ME(userId, meetupsEntryId, true);
	}

	public MeetupsRegistration fetchByU_ME(long userId, long meetupsEntryId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(userId), new Long(meetupsEntryId)
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_U_ME,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT meetupsRegistration FROM MeetupsRegistration meetupsRegistration WHERE ");

				query.append("meetupsRegistration.userId = ?");

				query.append(" AND ");

				query.append("meetupsRegistration.meetupsEntryId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("meetupsRegistration.modifiedDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(meetupsEntryId);

				List<MeetupsRegistration> list = q.list();

				result = list;

				MeetupsRegistration meetupsRegistration = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_ME,
						finderArgs, list);
				}
				else {
					meetupsRegistration = list.get(0);

					cacheResult(meetupsRegistration);

					if ((meetupsRegistration.getUserId() != userId) ||
							(meetupsRegistration.getMeetupsEntryId() != meetupsEntryId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_ME,
							finderArgs, meetupsRegistration);
					}
				}

				return meetupsRegistration;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_ME,
						finderArgs, new ArrayList<MeetupsRegistration>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (MeetupsRegistration)result;
			}
		}
	}

	public List<MeetupsRegistration> findByME_S(long meetupsEntryId, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(meetupsEntryId), new Integer(status)
			};

		List<MeetupsRegistration> list = (List<MeetupsRegistration>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ME_S,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT meetupsRegistration FROM MeetupsRegistration meetupsRegistration WHERE ");

				query.append("meetupsRegistration.meetupsEntryId = ?");

				query.append(" AND ");

				query.append("meetupsRegistration.status = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("meetupsRegistration.modifiedDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupsEntryId);

				qPos.add(status);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<MeetupsRegistration>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ME_S, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<MeetupsRegistration> findByME_S(long meetupsEntryId,
		int status, int start, int end) throws SystemException {
		return findByME_S(meetupsEntryId, status, start, end, null);
	}

	public List<MeetupsRegistration> findByME_S(long meetupsEntryId,
		int status, int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(meetupsEntryId), new Integer(status),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<MeetupsRegistration> list = (List<MeetupsRegistration>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_ME_S,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT meetupsRegistration FROM MeetupsRegistration meetupsRegistration WHERE ");

				query.append("meetupsRegistration.meetupsEntryId = ?");

				query.append(" AND ");

				query.append("meetupsRegistration.status = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("meetupsRegistration.");
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

					query.append("meetupsRegistration.modifiedDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupsEntryId);

				qPos.add(status);

				list = (List<MeetupsRegistration>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<MeetupsRegistration>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_ME_S,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public MeetupsRegistration findByME_S_First(long meetupsEntryId,
		int status, OrderByComparator obc)
		throws NoSuchMeetupsRegistrationException, SystemException {
		List<MeetupsRegistration> list = findByME_S(meetupsEntryId, status, 0,
				1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No MeetupsRegistration exists with the key {");

			msg.append("meetupsEntryId=" + meetupsEntryId);

			msg.append(", ");
			msg.append("status=" + status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMeetupsRegistrationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public MeetupsRegistration findByME_S_Last(long meetupsEntryId, int status,
		OrderByComparator obc)
		throws NoSuchMeetupsRegistrationException, SystemException {
		int count = countByME_S(meetupsEntryId, status);

		List<MeetupsRegistration> list = findByME_S(meetupsEntryId, status,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No MeetupsRegistration exists with the key {");

			msg.append("meetupsEntryId=" + meetupsEntryId);

			msg.append(", ");
			msg.append("status=" + status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMeetupsRegistrationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public MeetupsRegistration[] findByME_S_PrevAndNext(
		long meetupsRegistrationId, long meetupsEntryId, int status,
		OrderByComparator obc)
		throws NoSuchMeetupsRegistrationException, SystemException {
		MeetupsRegistration meetupsRegistration = findByPrimaryKey(meetupsRegistrationId);

		int count = countByME_S(meetupsEntryId, status);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT meetupsRegistration FROM MeetupsRegistration meetupsRegistration WHERE ");

			query.append("meetupsRegistration.meetupsEntryId = ?");

			query.append(" AND ");

			query.append("meetupsRegistration.status = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("meetupsRegistration.");
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

				query.append("meetupsRegistration.modifiedDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(meetupsEntryId);

			qPos.add(status);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					meetupsRegistration);

			MeetupsRegistration[] array = new MeetupsRegistrationImpl[3];

			array[0] = (MeetupsRegistration)objArray[0];
			array[1] = (MeetupsRegistration)objArray[1];
			array[2] = (MeetupsRegistration)objArray[2];

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

	public List<MeetupsRegistration> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<MeetupsRegistration> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<MeetupsRegistration> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<MeetupsRegistration> list = (List<MeetupsRegistration>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT meetupsRegistration FROM MeetupsRegistration meetupsRegistration ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("meetupsRegistration.");
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

					query.append("meetupsRegistration.modifiedDate DESC");
				}

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<MeetupsRegistration>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<MeetupsRegistration>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<MeetupsRegistration>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByMeetupsEntryId(long meetupsEntryId)
		throws SystemException {
		for (MeetupsRegistration meetupsRegistration : findByMeetupsEntryId(
				meetupsEntryId)) {
			remove(meetupsRegistration);
		}
	}

	public void removeByU_ME(long userId, long meetupsEntryId)
		throws NoSuchMeetupsRegistrationException, SystemException {
		MeetupsRegistration meetupsRegistration = findByU_ME(userId,
				meetupsEntryId);

		remove(meetupsRegistration);
	}

	public void removeByME_S(long meetupsEntryId, int status)
		throws SystemException {
		for (MeetupsRegistration meetupsRegistration : findByME_S(
				meetupsEntryId, status)) {
			remove(meetupsRegistration);
		}
	}

	public void removeAll() throws SystemException {
		for (MeetupsRegistration meetupsRegistration : findAll()) {
			remove(meetupsRegistration);
		}
	}

	public int countByMeetupsEntryId(long meetupsEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(meetupsEntryId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MEETUPSENTRYID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(meetupsRegistration) ");
				query.append(
					"FROM MeetupsRegistration meetupsRegistration WHERE ");

				query.append("meetupsRegistration.meetupsEntryId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupsEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MEETUPSENTRYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByU_ME(long userId, long meetupsEntryId)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(userId), new Long(meetupsEntryId)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_ME,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(meetupsRegistration) ");
				query.append(
					"FROM MeetupsRegistration meetupsRegistration WHERE ");

				query.append("meetupsRegistration.userId = ?");

				query.append(" AND ");

				query.append("meetupsRegistration.meetupsEntryId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(meetupsEntryId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_ME,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByME_S(long meetupsEntryId, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(meetupsEntryId), new Integer(status)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ME_S,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(meetupsRegistration) ");
				query.append(
					"FROM MeetupsRegistration meetupsRegistration WHERE ");

				query.append("meetupsRegistration.meetupsEntryId = ?");

				query.append(" AND ");

				query.append("meetupsRegistration.status = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupsEntryId);

				qPos.add(status);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ME_S,
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
						"SELECT COUNT(meetupsRegistration) FROM MeetupsRegistration meetupsRegistration");

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
						"value.object.listener.com.liferay.socialnetworking.model.MeetupsRegistration")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<MeetupsRegistration>> listenersList = new ArrayList<ModelListener<MeetupsRegistration>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<MeetupsRegistration>)Class.forName(
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
	private static Log _log = LogFactoryUtil.getLog(MeetupsRegistrationPersistenceImpl.class);
}