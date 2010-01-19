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

package com.liferay.opensocial.service.persistence;

import com.liferay.opensocial.NoSuchGadgetException;
import com.liferay.opensocial.model.OpenSocialGadget;
import com.liferay.opensocial.model.impl.OpenSocialGadgetImpl;
import com.liferay.opensocial.model.impl.OpenSocialGadgetModelImpl;

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
 * <a href="OpenSocialGadgetPersistenceImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public class OpenSocialGadgetPersistenceImpl extends BasePersistenceImpl<OpenSocialGadget>
	implements OpenSocialGadgetPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = OpenSocialGadgetImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(OpenSocialGadgetModelImpl.ENTITY_CACHE_ENABLED,
			OpenSocialGadgetModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_COMPANYID = new FinderPath(OpenSocialGadgetModelImpl.ENTITY_CACHE_ENABLED,
			OpenSocialGadgetModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(OpenSocialGadgetModelImpl.ENTITY_CACHE_ENABLED,
			OpenSocialGadgetModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(OpenSocialGadgetModelImpl.ENTITY_CACHE_ENABLED,
			OpenSocialGadgetModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OpenSocialGadgetModelImpl.ENTITY_CACHE_ENABLED,
			OpenSocialGadgetModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(OpenSocialGadget openSocialGadget) {
		EntityCacheUtil.putResult(OpenSocialGadgetModelImpl.ENTITY_CACHE_ENABLED,
			OpenSocialGadgetImpl.class, openSocialGadget.getPrimaryKey(),
			openSocialGadget);
	}

	public void cacheResult(List<OpenSocialGadget> openSocialGadgets) {
		for (OpenSocialGadget openSocialGadget : openSocialGadgets) {
			if (EntityCacheUtil.getResult(
						OpenSocialGadgetModelImpl.ENTITY_CACHE_ENABLED,
						OpenSocialGadgetImpl.class,
						openSocialGadget.getPrimaryKey(), this) == null) {
				cacheResult(openSocialGadget);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(OpenSocialGadgetImpl.class.getName());
		EntityCacheUtil.clearCache(OpenSocialGadgetImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public OpenSocialGadget create(long openSocialGadgetId) {
		OpenSocialGadget openSocialGadget = new OpenSocialGadgetImpl();

		openSocialGadget.setNew(true);
		openSocialGadget.setPrimaryKey(openSocialGadgetId);

		return openSocialGadget;
	}

	public OpenSocialGadget remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public OpenSocialGadget remove(long openSocialGadgetId)
		throws NoSuchGadgetException, SystemException {
		Session session = null;

		try {
			session = openSession();

			OpenSocialGadget openSocialGadget = (OpenSocialGadget)session.get(OpenSocialGadgetImpl.class,
					new Long(openSocialGadgetId));

			if (openSocialGadget == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						openSocialGadgetId);
				}

				throw new NoSuchGadgetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					openSocialGadgetId);
			}

			return remove(openSocialGadget);
		}
		catch (NoSuchGadgetException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public OpenSocialGadget remove(OpenSocialGadget openSocialGadget)
		throws SystemException {
		for (ModelListener<OpenSocialGadget> listener : listeners) {
			listener.onBeforeRemove(openSocialGadget);
		}

		openSocialGadget = removeImpl(openSocialGadget);

		for (ModelListener<OpenSocialGadget> listener : listeners) {
			listener.onAfterRemove(openSocialGadget);
		}

		return openSocialGadget;
	}

	protected OpenSocialGadget removeImpl(OpenSocialGadget openSocialGadget)
		throws SystemException {
		openSocialGadget = toUnwrappedModel(openSocialGadget);

		Session session = null;

		try {
			session = openSession();

			if (openSocialGadget.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(OpenSocialGadgetImpl.class,
						openSocialGadget.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(openSocialGadget);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(OpenSocialGadgetModelImpl.ENTITY_CACHE_ENABLED,
			OpenSocialGadgetImpl.class, openSocialGadget.getPrimaryKey());

		return openSocialGadget;
	}

	public OpenSocialGadget updateImpl(
		com.liferay.opensocial.model.OpenSocialGadget openSocialGadget,
		boolean merge) throws SystemException {
		openSocialGadget = toUnwrappedModel(openSocialGadget);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, openSocialGadget, merge);

			openSocialGadget.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(OpenSocialGadgetModelImpl.ENTITY_CACHE_ENABLED,
			OpenSocialGadgetImpl.class, openSocialGadget.getPrimaryKey(),
			openSocialGadget);

		return openSocialGadget;
	}

	protected OpenSocialGadget toUnwrappedModel(
		OpenSocialGadget openSocialGadget) {
		if (openSocialGadget instanceof OpenSocialGadgetImpl) {
			return openSocialGadget;
		}

		OpenSocialGadgetImpl openSocialGadgetImpl = new OpenSocialGadgetImpl();

		openSocialGadgetImpl.setNew(openSocialGadget.isNew());
		openSocialGadgetImpl.setPrimaryKey(openSocialGadget.getPrimaryKey());

		openSocialGadgetImpl.setOpenSocialGadgetId(openSocialGadget.getOpenSocialGadgetId());
		openSocialGadgetImpl.setCompanyId(openSocialGadget.getCompanyId());
		openSocialGadgetImpl.setCreateDate(openSocialGadget.getCreateDate());
		openSocialGadgetImpl.setModifiedDate(openSocialGadget.getModifiedDate());
		openSocialGadgetImpl.setName(openSocialGadget.getName());
		openSocialGadgetImpl.setUrl(openSocialGadget.getUrl());
		openSocialGadgetImpl.setXml(openSocialGadget.getXml());

		return openSocialGadgetImpl;
	}

	public OpenSocialGadget findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public OpenSocialGadget findByPrimaryKey(long openSocialGadgetId)
		throws NoSuchGadgetException, SystemException {
		OpenSocialGadget openSocialGadget = fetchByPrimaryKey(openSocialGadgetId);

		if (openSocialGadget == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					openSocialGadgetId);
			}

			throw new NoSuchGadgetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				openSocialGadgetId);
		}

		return openSocialGadget;
	}

	public OpenSocialGadget fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public OpenSocialGadget fetchByPrimaryKey(long openSocialGadgetId)
		throws SystemException {
		OpenSocialGadget openSocialGadget = (OpenSocialGadget)EntityCacheUtil.getResult(OpenSocialGadgetModelImpl.ENTITY_CACHE_ENABLED,
				OpenSocialGadgetImpl.class, openSocialGadgetId, this);

		if (openSocialGadget == null) {
			Session session = null;

			try {
				session = openSession();

				openSocialGadget = (OpenSocialGadget)session.get(OpenSocialGadgetImpl.class,
						new Long(openSocialGadgetId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (openSocialGadget != null) {
					cacheResult(openSocialGadget);
				}

				closeSession(session);
			}
		}

		return openSocialGadget;
	}

	public List<OpenSocialGadget> findByCompanyId(long companyId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId) };

		List<OpenSocialGadget> list = (List<OpenSocialGadget>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_SELECT_OPENSOCIALGADGET_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				query.append(OpenSocialGadgetModelImpl.ORDER_BY_JPQL);

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
					list = new ArrayList<OpenSocialGadget>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<OpenSocialGadget> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	public List<OpenSocialGadget> findByCompanyId(long companyId, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<OpenSocialGadget> list = (List<OpenSocialGadget>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
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

				query.append(_SQL_SELECT_OPENSOCIALGADGET_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				if (obc != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
				}

				else {
					query.append(OpenSocialGadgetModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<OpenSocialGadget>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<OpenSocialGadget>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public OpenSocialGadget findByCompanyId_First(long companyId,
		OrderByComparator obc) throws NoSuchGadgetException, SystemException {
		List<OpenSocialGadget> list = findByCompanyId(companyId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchGadgetException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public OpenSocialGadget findByCompanyId_Last(long companyId,
		OrderByComparator obc) throws NoSuchGadgetException, SystemException {
		int count = countByCompanyId(companyId);

		List<OpenSocialGadget> list = findByCompanyId(companyId, count - 1,
				count, obc);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchGadgetException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public OpenSocialGadget[] findByCompanyId_PrevAndNext(
		long openSocialGadgetId, long companyId, OrderByComparator obc)
		throws NoSuchGadgetException, SystemException {
		OpenSocialGadget openSocialGadget = findByPrimaryKey(openSocialGadgetId);

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

			query.append(_SQL_SELECT_OPENSOCIALGADGET_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (obc != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
			}

			else {
				query.append(OpenSocialGadgetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					openSocialGadget);

			OpenSocialGadget[] array = new OpenSocialGadgetImpl[3];

			array[0] = (OpenSocialGadget)objArray[0];
			array[1] = (OpenSocialGadget)objArray[1];
			array[2] = (OpenSocialGadget)objArray[2];

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

	public List<OpenSocialGadget> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<OpenSocialGadget> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<OpenSocialGadget> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<OpenSocialGadget> list = (List<OpenSocialGadget>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_OPENSOCIALGADGET);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);

					sql = query.toString();
				}

				else {
					sql = _SQL_SELECT_OPENSOCIALGADGET.concat(OpenSocialGadgetModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (obc == null) {
					list = (List<OpenSocialGadget>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<OpenSocialGadget>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<OpenSocialGadget>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByCompanyId(long companyId) throws SystemException {
		for (OpenSocialGadget openSocialGadget : findByCompanyId(companyId)) {
			remove(openSocialGadget);
		}
	}

	public void removeAll() throws SystemException {
		for (OpenSocialGadget openSocialGadget : findAll()) {
			remove(openSocialGadget);
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

				query.append(_SQL_COUNT_OPENSOCIALGADGET_WHERE);

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

				Query q = session.createQuery(_SQL_COUNT_OPENSOCIALGADGET);

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
						"value.object.listener.com.liferay.opensocial.model.OpenSocialGadget")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<OpenSocialGadget>> listenersList = new ArrayList<ModelListener<OpenSocialGadget>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<OpenSocialGadget>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.opensocial.service.persistence.OpenSocialGadgetPersistence")
	protected com.liferay.opensocial.service.persistence.OpenSocialGadgetPersistence openSocialGadgetPersistence;
	private static final String _SQL_SELECT_OPENSOCIALGADGET = "SELECT openSocialGadget FROM OpenSocialGadget openSocialGadget";
	private static final String _SQL_SELECT_OPENSOCIALGADGET_WHERE = "SELECT openSocialGadget FROM OpenSocialGadget openSocialGadget WHERE ";
	private static final String _SQL_COUNT_OPENSOCIALGADGET = "SELECT COUNT(openSocialGadget) FROM OpenSocialGadget openSocialGadget";
	private static final String _SQL_COUNT_OPENSOCIALGADGET_WHERE = "SELECT COUNT(openSocialGadget) FROM OpenSocialGadget openSocialGadget WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "openSocialGadget.companyId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "openSocialGadget.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OpenSocialGadget exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OpenSocialGadget exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(OpenSocialGadgetPersistenceImpl.class);
}