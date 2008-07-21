/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.iweb.service.persistence;

import com.liferay.iweb.NoSuchCommunityException;
import com.liferay.iweb.model.Community;
import com.liferay.iweb.model.impl.CommunityImpl;
import com.liferay.iweb.model.impl.CommunityModelImpl;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="CommunityPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class CommunityPersistenceImpl extends BasePersistenceImpl
	implements CommunityPersistence {
	public Community create(long cid) {
		Community community = new CommunityImpl();

		community.setNew(true);
		community.setPrimaryKey(cid);

		return community;
	}

	public Community remove(long cid)
		throws NoSuchCommunityException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Community community = (Community)session.get(CommunityImpl.class,
					new Long(cid));

			if (community == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No Community exists with the primary key " +
						cid);
				}

				throw new NoSuchCommunityException(
					"No Community exists with the primary key " + cid);
			}

			return remove(community);
		}
		catch (NoSuchCommunityException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public Community remove(Community community) throws SystemException {
		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(community);
			}
		}

		community = removeImpl(community);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(community);
			}
		}

		return community;
	}

	protected Community removeImpl(Community community)
		throws SystemException {
		try {
			clearSemanticss.clear(community.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}

		Session session = null;

		try {
			session = openSession();

			session.delete(community);

			session.flush();

			return community;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(Community.class.getName());
		}
	}

	public Community update(Community community) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(Community community) method. Use update(Community community, boolean merge) instead.");
		}

		return update(community, false);
	}

	public Community update(Community community, boolean merge)
		throws SystemException {
		boolean isNew = community.isNew();

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(community);
				}
				else {
					listener.onBeforeUpdate(community);
				}
			}
		}

		community = updateImpl(community, merge);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(community);
				}
				else {
					listener.onAfterUpdate(community);
				}
			}
		}

		return community;
	}

	public Community updateImpl(com.liferay.iweb.model.Community community,
		boolean merge) throws SystemException {
		FinderCacheUtil.clearCache("Communities_Semantics");

		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(community);
			}
			else {
				if (community.isNew()) {
					session.save(community);
				}
			}

			session.flush();

			community.setNew(false);

			return community;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(Community.class.getName());
		}
	}

	public Community findByPrimaryKey(long cid)
		throws NoSuchCommunityException, SystemException {
		Community community = fetchByPrimaryKey(cid);

		if (community == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No Community exists with the primary key " + cid);
			}

			throw new NoSuchCommunityException(
				"No Community exists with the primary key " + cid);
		}

		return community;
	}

	public Community fetchByPrimaryKey(long cid) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (Community)session.get(CommunityImpl.class, new Long(cid));
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

	public List<Community> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<Community> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<Community> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = CommunityModelImpl.CACHE_ENABLED;
		String finderClassName = Community.class.getName();
		String finderMethodName = "findAll";
		String[] finderParams = new String[] {
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.iweb.model.Community ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<Community> list = (List<Community>)QueryUtil.list(q,
						getDialect(), start, end);

				if (obc == null) {
					Collections.sort(list);
				}

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<Community>)result;
		}
	}

	public void removeAll() throws SystemException {
		for (Community community : findAll()) {
			remove(community);
		}
	}

	public int countAll() throws SystemException {
		boolean finderClassNameCacheEnabled = CommunityModelImpl.CACHE_ENABLED;
		String finderClassName = Community.class.getName();
		String finderMethodName = "countAll";
		String[] finderParams = new String[] {  };
		Object[] finderArgs = new Object[] {  };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(
						"SELECT COUNT(*) FROM com.liferay.iweb.model.Community");

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public List<com.liferay.iweb.model.Semantics> getSemanticss(long pk)
		throws SystemException {
		return getSemanticss(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<com.liferay.iweb.model.Semantics> getSemanticss(long pk,
		int start, int end) throws SystemException {
		return getSemanticss(pk, start, end, null);
	}

	public List<com.liferay.iweb.model.Semantics> getSemanticss(long pk,
		int start, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = CommunityModelImpl.CACHE_ENABLED_COMMUNITIES_SEMANTICS;

		String finderClassName = "Communities_Semantics";

		String finderMethodName = "getSemanticss";
		String[] finderParams = new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(pk), String.valueOf(start), String.valueOf(end),
				String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder sb = new StringBuilder();

				sb.append(_SQL_GETSEMANTICSS);

				if (obc != null) {
					sb.append("ORDER BY ");
					sb.append(obc.getOrderBy());
				}

				String sql = sb.toString();

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("IWEB_Semantics",
					com.liferay.iweb.model.impl.SemanticsImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				List<com.liferay.iweb.model.Semantics> list = (List<com.liferay.iweb.model.Semantics>)QueryUtil.list(q,
						getDialect(), start, end);

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<com.liferay.iweb.model.Semantics>)result;
		}
	}

	public int getSemanticssSize(long pk) throws SystemException {
		boolean finderClassNameCacheEnabled = CommunityModelImpl.CACHE_ENABLED_COMMUNITIES_SEMANTICS;

		String finderClassName = "Communities_Semantics";

		String finderMethodName = "getSemanticssSize";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(pk) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETSEMANTICSSSIZE);

				q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public boolean containsSemantics(long pk, String semanticsPK)
		throws SystemException {
		boolean finderClassNameCacheEnabled = CommunityModelImpl.CACHE_ENABLED_COMMUNITIES_SEMANTICS;

		String finderClassName = "Communities_Semantics";

		String finderMethodName = "containsSemanticss";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				String.class.getName()
			};
		Object[] finderArgs = new Object[] { new Long(pk), semanticsPK };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			try {
				Boolean value = Boolean.valueOf(containsSemantics.contains(pk,
							semanticsPK));

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, value);

				return value.booleanValue();
			}
			catch (Exception e) {
				throw processException(e);
			}
		}
		else {
			return ((Boolean)result).booleanValue();
		}
	}

	public boolean containsSemanticss(long pk) throws SystemException {
		if (getSemanticssSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void addSemantics(long pk, String semanticsPK)
		throws SystemException {
		try {
			addSemantics.add(pk, semanticsPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}
	}

	public void addSemantics(long pk, com.liferay.iweb.model.Semantics semantics)
		throws SystemException {
		try {
			addSemantics.add(pk, semantics.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}
	}

	public void addSemanticss(long pk, String[] semanticsPKs)
		throws SystemException {
		try {
			for (String semanticsPK : semanticsPKs) {
				addSemantics.add(pk, semanticsPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}
	}

	public void addSemanticss(long pk,
		List<com.liferay.iweb.model.Semantics> semanticss)
		throws SystemException {
		try {
			for (com.liferay.iweb.model.Semantics semantics : semanticss) {
				addSemantics.add(pk, semantics.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}
	}

	public void clearSemanticss(long pk) throws SystemException {
		try {
			clearSemanticss.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}
	}

	public void removeSemantics(long pk, String semanticsPK)
		throws SystemException {
		try {
			removeSemantics.remove(pk, semanticsPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}
	}

	public void removeSemantics(long pk,
		com.liferay.iweb.model.Semantics semantics) throws SystemException {
		try {
			removeSemantics.remove(pk, semantics.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}
	}

	public void removeSemanticss(long pk, String[] semanticsPKs)
		throws SystemException {
		try {
			for (String semanticsPK : semanticsPKs) {
				removeSemantics.remove(pk, semanticsPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}
	}

	public void removeSemanticss(long pk,
		List<com.liferay.iweb.model.Semantics> semanticss)
		throws SystemException {
		try {
			for (com.liferay.iweb.model.Semantics semantics : semanticss) {
				removeSemantics.remove(pk, semantics.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}
	}

	public void setSemanticss(long pk, String[] semanticsPKs)
		throws SystemException {
		try {
			clearSemanticss.clear(pk);

			for (String semanticsPK : semanticsPKs) {
				addSemantics.add(pk, semanticsPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}
	}

	public void setSemanticss(long pk,
		List<com.liferay.iweb.model.Semantics> semanticss)
		throws SystemException {
		try {
			clearSemanticss.clear(pk);

			for (com.liferay.iweb.model.Semantics semantics : semanticss) {
				addSemantics.add(pk, semantics.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}
	}

	public void registerListener(ModelListener listener) {
		List<ModelListener> listeners = ListUtil.fromArray(_listeners);

		listeners.add(listener);

		_listeners = listeners.toArray(new ModelListener[listeners.size()]);
	}

	public void unregisterListener(ModelListener listener) {
		List<ModelListener> listeners = ListUtil.fromArray(_listeners);

		listeners.remove(listener);

		_listeners = listeners.toArray(new ModelListener[listeners.size()]);
	}

	protected void init() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.iweb.model.Community")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener> listeners = new ArrayList<ModelListener>();

				for (String listenerClassName : listenerClassNames) {
					listeners.add((ModelListener)Class.forName(
							listenerClassName).newInstance());
				}

				_listeners = listeners.toArray(new ModelListener[listeners.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsSemantics = new ContainsSemantics(this);

		addSemantics = new AddSemantics(this);
		clearSemanticss = new ClearSemanticss(this);
		removeSemantics = new RemoveSemantics(this);
	}

	protected ContainsSemantics containsSemantics;
	protected AddSemantics addSemantics;
	protected ClearSemanticss clearSemanticss;
	protected RemoveSemantics removeSemantics;

	protected class ContainsSemantics {
		protected ContainsSemantics(CommunityPersistenceImpl persistenceImpl) {
			super();

			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSSEMANTICS,
					new int[] { Types.BIGINT, Types.VARCHAR }, RowMapper.COUNT);
		}

		protected boolean contains(long cid, String semanticsURI) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(cid), semanticsURI
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery _mappingSqlQuery;
	}

	protected class AddSemantics {
		protected AddSemantics(CommunityPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO Communities_Semantics (cid, semanticsURI) VALUES (?, ?)",
					new int[] { Types.BIGINT, Types.VARCHAR });
			_persistenceImpl = persistenceImpl;
		}

		protected void add(long cid, String semanticsURI) {
			if (!_persistenceImpl.containsSemantics.contains(cid, semanticsURI)) {
				_sqlUpdate.update(new Object[] { new Long(cid), semanticsURI });
			}
		}

		private SqlUpdate _sqlUpdate;
		private CommunityPersistenceImpl _persistenceImpl;
	}

	protected class ClearSemanticss {
		protected ClearSemanticss(CommunityPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM Communities_Semantics WHERE cid = ?",
					new int[] { Types.BIGINT });
		}

		protected void clear(long cid) {
			_sqlUpdate.update(new Object[] { new Long(cid) });
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveSemantics {
		protected RemoveSemantics(CommunityPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM Communities_Semantics WHERE cid = ? AND semanticsURI = ?",
					new int[] { Types.BIGINT, Types.VARCHAR });
		}

		protected void remove(long cid, String semanticsURI) {
			_sqlUpdate.update(new Object[] { new Long(cid), semanticsURI });
		}

		private SqlUpdate _sqlUpdate;
	}

	private static final String _SQL_GETSEMANTICSS = "SELECT {IWEB_Semantics.*} FROM IWEB_Semantics INNER JOIN Communities_Semantics ON (Communities_Semantics.semanticsURI = IWEB_Semantics.semanticsURI) WHERE (Communities_Semantics.cid = ?)";
	private static final String _SQL_GETSEMANTICSSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Communities_Semantics WHERE cid = ?";
	private static final String _SQL_CONTAINSSEMANTICS = "SELECT COUNT(*) AS COUNT_VALUE FROM Communities_Semantics WHERE cid = ? AND semanticsURI = ?";
	private static Log _log = LogFactory.getLog(CommunityPersistenceImpl.class);
	private ModelListener[] _listeners = new ModelListener[0];
}