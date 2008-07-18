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

package com.liferay.portal.iweb.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.iweb.NoSuchSemanticsException;
import com.liferay.portal.iweb.model.Semantics;
import com.liferay.portal.iweb.model.impl.SemanticsImpl;
import com.liferay.portal.iweb.model.impl.SemanticsModelImpl;
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
import com.liferay.portal.kernel.util.StringPool;
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
 * <a href="SemanticsPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SemanticsPersistenceImpl extends BasePersistenceImpl
	implements SemanticsPersistence {
	public Semantics create(String semanticsURI) {
		Semantics semantics = new SemanticsImpl();

		semantics.setNew(true);
		semantics.setPrimaryKey(semanticsURI);

		return semantics;
	}

	public Semantics remove(String semanticsURI)
		throws NoSuchSemanticsException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Semantics semantics = (Semantics)session.get(SemanticsImpl.class,
					semanticsURI);

			if (semantics == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No Semantics exists with the primary key " +
						semanticsURI);
				}

				throw new NoSuchSemanticsException(
					"No Semantics exists with the primary key " + semanticsURI);
			}

			return remove(semantics);
		}
		catch (NoSuchSemanticsException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public Semantics remove(Semantics semantics) throws SystemException {
		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(semantics);
			}
		}

		semantics = removeImpl(semantics);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(semantics);
			}
		}

		return semantics;
	}

	protected Semantics removeImpl(Semantics semantics)
		throws SystemException {
		try {
			clearCommunities.clear(semantics.getPrimaryKey());
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

			session.delete(semantics);

			session.flush();

			return semantics;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(Semantics.class.getName());
		}
	}

	public Semantics update(Semantics semantics) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(Semantics semantics) method. Use update(Semantics semantics, boolean merge) instead.");
		}

		return update(semantics, false);
	}

	public Semantics update(Semantics semantics, boolean merge)
		throws SystemException {
		boolean isNew = semantics.isNew();

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(semantics);
				}
				else {
					listener.onBeforeUpdate(semantics);
				}
			}
		}

		semantics = updateImpl(semantics, merge);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(semantics);
				}
				else {
					listener.onAfterUpdate(semantics);
				}
			}
		}

		return semantics;
	}

	public Semantics updateImpl(
		com.liferay.portal.iweb.model.Semantics semantics, boolean merge)
		throws SystemException {
		FinderCacheUtil.clearCache("Communities_Semantics");

		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(semantics);
			}
			else {
				if (semantics.isNew()) {
					session.save(semantics);
				}
			}

			session.flush();

			semantics.setNew(false);

			return semantics;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(Semantics.class.getName());
		}
	}

	public Semantics findByPrimaryKey(String semanticsURI)
		throws NoSuchSemanticsException, SystemException {
		Semantics semantics = fetchByPrimaryKey(semanticsURI);

		if (semantics == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No Semantics exists with the primary key " +
					semanticsURI);
			}

			throw new NoSuchSemanticsException(
				"No Semantics exists with the primary key " + semanticsURI);
		}

		return semantics;
	}

	public Semantics fetchByPrimaryKey(String semanticsURI)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (Semantics)session.get(SemanticsImpl.class, semanticsURI);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Semantics> findByCreatedInCommunity(long createdInCommunity)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsModelImpl.CACHE_ENABLED;
		String finderClassName = Semantics.class.getName();
		String finderMethodName = "findByCreatedInCommunity";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(createdInCommunity) };

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

				query.append(
					"FROM com.liferay.portal.iweb.model.Semantics WHERE ");

				query.append("createdInCommunity = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createdInCommunity);

				List<Semantics> list = q.list();

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
			return (List<Semantics>)result;
		}
	}

	public List<Semantics> findByCreatedInCommunity(long createdInCommunity,
		int start, int end) throws SystemException {
		return findByCreatedInCommunity(createdInCommunity, start, end, null);
	}

	public List<Semantics> findByCreatedInCommunity(long createdInCommunity,
		int start, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsModelImpl.CACHE_ENABLED;
		String finderClassName = Semantics.class.getName();
		String finderMethodName = "findByCreatedInCommunity";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(createdInCommunity),
				
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

				query.append(
					"FROM com.liferay.portal.iweb.model.Semantics WHERE ");

				query.append("createdInCommunity = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createdInCommunity);

				List<Semantics> list = (List<Semantics>)QueryUtil.list(q,
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
			return (List<Semantics>)result;
		}
	}

	public Semantics findByCreatedInCommunity_First(long createdInCommunity,
		OrderByComparator obc) throws NoSuchSemanticsException, SystemException {
		List<Semantics> list = findByCreatedInCommunity(createdInCommunity, 0,
				1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Semantics exists with the key {");

			msg.append("createdInCommunity=" + createdInCommunity);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSemanticsException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Semantics findByCreatedInCommunity_Last(long createdInCommunity,
		OrderByComparator obc) throws NoSuchSemanticsException, SystemException {
		int count = countByCreatedInCommunity(createdInCommunity);

		List<Semantics> list = findByCreatedInCommunity(createdInCommunity,
				count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Semantics exists with the key {");

			msg.append("createdInCommunity=" + createdInCommunity);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSemanticsException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Semantics[] findByCreatedInCommunity_PrevAndNext(
		String semanticsURI, long createdInCommunity, OrderByComparator obc)
		throws NoSuchSemanticsException, SystemException {
		Semantics semantics = findByPrimaryKey(semanticsURI);

		int count = countByCreatedInCommunity(createdInCommunity);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.portal.iweb.model.Semantics WHERE ");

			query.append("createdInCommunity = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(createdInCommunity);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					semantics);

			Semantics[] array = new SemanticsImpl[3];

			array[0] = (Semantics)objArray[0];
			array[1] = (Semantics)objArray[1];
			array[2] = (Semantics)objArray[2];

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

	public List<Semantics> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<Semantics> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<Semantics> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsModelImpl.CACHE_ENABLED;
		String finderClassName = Semantics.class.getName();
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

				query.append("FROM com.liferay.portal.iweb.model.Semantics ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<Semantics> list = (List<Semantics>)QueryUtil.list(q,
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
			return (List<Semantics>)result;
		}
	}

	public void removeByCreatedInCommunity(long createdInCommunity)
		throws SystemException {
		for (Semantics semantics : findByCreatedInCommunity(createdInCommunity)) {
			remove(semantics);
		}
	}

	public void removeAll() throws SystemException {
		for (Semantics semantics : findAll()) {
			remove(semantics);
		}
	}

	public int countByCreatedInCommunity(long createdInCommunity)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsModelImpl.CACHE_ENABLED;
		String finderClassName = Semantics.class.getName();
		String finderMethodName = "countByCreatedInCommunity";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(createdInCommunity) };

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

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.portal.iweb.model.Semantics WHERE ");

				query.append("createdInCommunity = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createdInCommunity);

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

	public int countAll() throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsModelImpl.CACHE_ENABLED;
		String finderClassName = Semantics.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.portal.iweb.model.Semantics");

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

	public List<com.liferay.portal.iweb.model.Community> getCommunities(
		String pk) throws SystemException {
		return getCommunities(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<com.liferay.portal.iweb.model.Community> getCommunities(
		String pk, int start, int end) throws SystemException {
		return getCommunities(pk, start, end, null);
	}

	public List<com.liferay.portal.iweb.model.Community> getCommunities(
		String pk, int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsModelImpl.CACHE_ENABLED_COMMUNITIES_SEMANTICS;

		String finderClassName = "Communities_Semantics";

		String finderMethodName = "getCommunities";
		String[] finderParams = new String[] {
				String.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				pk, String.valueOf(start), String.valueOf(end),
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

				sb.append(_SQL_GETCOMMUNITIES);

				if (obc != null) {
					sb.append("ORDER BY ");
					sb.append(obc.getOrderBy());
				}

				String sql = sb.toString();

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("IWEB_Community",
					com.liferay.portal.iweb.model.impl.CommunityImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				List<com.liferay.portal.iweb.model.Community> list = (List<com.liferay.portal.iweb.model.Community>)QueryUtil.list(q,
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
			return (List<com.liferay.portal.iweb.model.Community>)result;
		}
	}

	public int getCommunitiesSize(String pk) throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsModelImpl.CACHE_ENABLED_COMMUNITIES_SEMANTICS;

		String finderClassName = "Communities_Semantics";

		String finderMethodName = "getCommunitiesSize";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { pk };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETCOMMUNITIESSIZE);

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

	public boolean containsCommunity(String pk, long communityPK)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsModelImpl.CACHE_ENABLED_COMMUNITIES_SEMANTICS;

		String finderClassName = "Communities_Semantics";

		String finderMethodName = "containsCommunities";
		String[] finderParams = new String[] {
				String.class.getName(),
				
				Long.class.getName()
			};
		Object[] finderArgs = new Object[] { pk, new Long(communityPK) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			try {
				Boolean value = Boolean.valueOf(containsCommunity.contains(pk,
							communityPK));

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

	public boolean containsCommunities(String pk) throws SystemException {
		if (getCommunitiesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void addCommunity(String pk, long communityPK)
		throws SystemException {
		try {
			addCommunity.add(pk, communityPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}
	}

	public void addCommunity(String pk,
		com.liferay.portal.iweb.model.Community community)
		throws SystemException {
		try {
			addCommunity.add(pk, community.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}
	}

	public void addCommunities(String pk, long[] communityPKs)
		throws SystemException {
		try {
			for (long communityPK : communityPKs) {
				addCommunity.add(pk, communityPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}
	}

	public void addCommunities(String pk,
		List<com.liferay.portal.iweb.model.Community> communities)
		throws SystemException {
		try {
			for (com.liferay.portal.iweb.model.Community community : communities) {
				addCommunity.add(pk, community.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}
	}

	public void clearCommunities(String pk) throws SystemException {
		try {
			clearCommunities.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}
	}

	public void removeCommunity(String pk, long communityPK)
		throws SystemException {
		try {
			removeCommunity.remove(pk, communityPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}
	}

	public void removeCommunity(String pk,
		com.liferay.portal.iweb.model.Community community)
		throws SystemException {
		try {
			removeCommunity.remove(pk, community.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}
	}

	public void removeCommunities(String pk, long[] communityPKs)
		throws SystemException {
		try {
			for (long communityPK : communityPKs) {
				removeCommunity.remove(pk, communityPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}
	}

	public void removeCommunities(String pk,
		List<com.liferay.portal.iweb.model.Community> communities)
		throws SystemException {
		try {
			for (com.liferay.portal.iweb.model.Community community : communities) {
				removeCommunity.remove(pk, community.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}
	}

	public void setCommunities(String pk, long[] communityPKs)
		throws SystemException {
		try {
			clearCommunities.clear(pk);

			for (long communityPK : communityPKs) {
				addCommunity.add(pk, communityPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Communities_Semantics");
		}
	}

	public void setCommunities(String pk,
		List<com.liferay.portal.iweb.model.Community> communities)
		throws SystemException {
		try {
			clearCommunities.clear(pk);

			for (com.liferay.portal.iweb.model.Community community : communities) {
				addCommunity.add(pk, community.getPrimaryKey());
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
						"value.object.listener.com.liferay.portal.iweb.model.Semantics")));

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

		containsCommunity = new ContainsCommunity(this);

		addCommunity = new AddCommunity(this);
		clearCommunities = new ClearCommunities(this);
		removeCommunity = new RemoveCommunity(this);
	}

	protected ContainsCommunity containsCommunity;
	protected AddCommunity addCommunity;
	protected ClearCommunities clearCommunities;
	protected RemoveCommunity removeCommunity;

	protected class ContainsCommunity {
		protected ContainsCommunity(SemanticsPersistenceImpl persistenceImpl) {
			super();

			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSCOMMUNITY,
					new int[] { Types.VARCHAR, Types.BIGINT }, RowMapper.COUNT);
		}

		protected boolean contains(String semanticsURI, long cid) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						semanticsURI, new Long(cid)
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

	protected class AddCommunity {
		protected AddCommunity(SemanticsPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO Communities_Semantics (semanticsURI, cid) VALUES (?, ?)",
					new int[] { Types.VARCHAR, Types.BIGINT });
			_persistenceImpl = persistenceImpl;
		}

		protected void add(String semanticsURI, long cid) {
			if (!_persistenceImpl.containsCommunity.contains(semanticsURI, cid)) {
				_sqlUpdate.update(new Object[] { semanticsURI, new Long(cid) });
			}
		}

		private SqlUpdate _sqlUpdate;
		private SemanticsPersistenceImpl _persistenceImpl;
	}

	protected class ClearCommunities {
		protected ClearCommunities(SemanticsPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM Communities_Semantics WHERE semanticsURI = ?",
					new int[] { Types.VARCHAR });
		}

		protected void clear(String semanticsURI) {
			_sqlUpdate.update(new Object[] { semanticsURI });
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveCommunity {
		protected RemoveCommunity(SemanticsPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM Communities_Semantics WHERE semanticsURI = ? AND cid = ?",
					new int[] { Types.VARCHAR, Types.BIGINT });
		}

		protected void remove(String semanticsURI, long cid) {
			_sqlUpdate.update(new Object[] { semanticsURI, new Long(cid) });
		}

		private SqlUpdate _sqlUpdate;
	}

	private static final String _SQL_GETCOMMUNITIES = "SELECT {IWEB_Community.*} FROM IWEB_Community INNER JOIN Communities_Semantics ON (Communities_Semantics.cid = IWEB_Community.cid) WHERE (Communities_Semantics.semanticsURI = ?)";
	private static final String _SQL_GETCOMMUNITIESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Communities_Semantics WHERE semanticsURI = ?";
	private static final String _SQL_CONTAINSCOMMUNITY = "SELECT COUNT(*) AS COUNT_VALUE FROM Communities_Semantics WHERE semanticsURI = ? AND cid = ?";
	private static Log _log = LogFactory.getLog(SemanticsPersistenceImpl.class);
	private ModelListener[] _listeners = new ModelListener[0];
}