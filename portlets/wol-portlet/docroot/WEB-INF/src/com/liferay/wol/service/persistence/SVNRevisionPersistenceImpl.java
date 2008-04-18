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

package com.liferay.wol.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.DynamicQuery;
import com.liferay.portal.kernel.dao.DynamicQueryInitializer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringMaker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;

import com.liferay.portlet.service.BasePersistence;
import com.liferay.portlet.service.FinderCache;
import com.liferay.portlet.service.HibernateUtil;
import com.liferay.portlet.service.PropsUtil;

import com.liferay.util.dao.hibernate.QueryUtil;

import com.liferay.wol.NoSuchSVNRevisionException;
import com.liferay.wol.model.SVNRevision;
import com.liferay.wol.model.impl.SVNRevisionImpl;
import com.liferay.wol.model.impl.SVNRevisionModelImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="SVNRevisionPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SVNRevisionPersistenceImpl extends BasePersistence
	implements SVNRevisionPersistence {
	public SVNRevision create(long svnRevisionId) {
		SVNRevision svnRevision = new SVNRevisionImpl();

		svnRevision.setNew(true);
		svnRevision.setPrimaryKey(svnRevisionId);

		return svnRevision;
	}

	public SVNRevision remove(long svnRevisionId)
		throws NoSuchSVNRevisionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SVNRevision svnRevision = (SVNRevision)session.get(SVNRevisionImpl.class,
					new Long(svnRevisionId));

			if (svnRevision == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No SVNRevision exists with the primary key " +
						svnRevisionId);
				}

				throw new NoSuchSVNRevisionException(
					"No SVNRevision exists with the primary key " +
					svnRevisionId);
			}

			return remove(svnRevision);
		}
		catch (NoSuchSVNRevisionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public SVNRevision remove(SVNRevision svnRevision)
		throws SystemException {
		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(svnRevision);
			}
		}

		svnRevision = removeImpl(svnRevision);

		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(svnRevision);
			}
		}

		return svnRevision;
	}

	protected SVNRevision removeImpl(SVNRevision svnRevision)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			session.delete(svnRevision);

			session.flush();

			return svnRevision;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);

			FinderCache.clearCache(SVNRevision.class.getName());
		}
	}

	public SVNRevision update(SVNRevision svnRevision)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(SVNRevision svnRevision) method. Use update(SVNRevision svnRevision, boolean merge) instead.");
		}

		return update(svnRevision, false);
	}

	public SVNRevision update(SVNRevision svnRevision, boolean merge)
		throws SystemException {
		boolean isNew = svnRevision.isNew();

		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(svnRevision);
				}
				else {
					listener.onBeforeUpdate(svnRevision);
				}
			}
		}

		svnRevision = updateImpl(svnRevision, merge);

		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(svnRevision);
				}
				else {
					listener.onAfterUpdate(svnRevision);
				}
			}
		}

		return svnRevision;
	}

	public SVNRevision updateImpl(
		com.liferay.wol.model.SVNRevision svnRevision, boolean merge)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(svnRevision);
			}
			else {
				if (svnRevision.isNew()) {
					session.save(svnRevision);
				}
			}

			session.flush();

			svnRevision.setNew(false);

			return svnRevision;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);

			FinderCache.clearCache(SVNRevision.class.getName());
		}
	}

	public SVNRevision findByPrimaryKey(long svnRevisionId)
		throws NoSuchSVNRevisionException, SystemException {
		SVNRevision svnRevision = fetchByPrimaryKey(svnRevisionId);

		if (svnRevision == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No SVNRevision exists with the primary key " +
					svnRevisionId);
			}

			throw new NoSuchSVNRevisionException(
				"No SVNRevision exists with the primary key " + svnRevisionId);
		}

		return svnRevision;
	}

	public SVNRevision fetchByPrimaryKey(long svnRevisionId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (SVNRevision)session.get(SVNRevisionImpl.class,
				new Long(svnRevisionId));
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SVNRevision> findBySVNUserId(String svnUserId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SVNRevisionModelImpl.CACHE_ENABLED;
		String finderClassName = SVNRevision.class.getName();
		String finderMethodName = "findBySVNUserId";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { svnUserId };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.wol.model.SVNRevision WHERE ");

				if (svnUserId == null) {
					query.append("svnUserId IS NULL");
				}
				else {
					query.append("svnUserId = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("svnRepositoryId DESC, ");
				query.append("revisionNumber DESC");

				Query q = session.createQuery(query.toString());

				int queryPos = 0;

				if (svnUserId != null) {
					q.setString(queryPos++, svnUserId);
				}

				List<SVNRevision> list = q.list();

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<SVNRevision>)result;
		}
	}

	public List<SVNRevision> findBySVNUserId(String svnUserId, int begin,
		int end) throws SystemException {
		return findBySVNUserId(svnUserId, begin, end, null);
	}

	public List<SVNRevision> findBySVNUserId(String svnUserId, int begin,
		int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = SVNRevisionModelImpl.CACHE_ENABLED;
		String finderClassName = SVNRevision.class.getName();
		String finderMethodName = "findBySVNUserId";
		String[] finderParams = new String[] {
				String.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				svnUserId,
				
				String.valueOf(begin), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.wol.model.SVNRevision WHERE ");

				if (svnUserId == null) {
					query.append("svnUserId IS NULL");
				}
				else {
					query.append("svnUserId = ?");
				}

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("svnRepositoryId DESC, ");
					query.append("revisionNumber DESC");
				}

				Query q = session.createQuery(query.toString());

				int queryPos = 0;

				if (svnUserId != null) {
					q.setString(queryPos++, svnUserId);
				}

				List<SVNRevision> list = (List<SVNRevision>)QueryUtil.list(q,
						getDialect(), begin, end);

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<SVNRevision>)result;
		}
	}

	public SVNRevision findBySVNUserId_First(String svnUserId,
		OrderByComparator obc)
		throws NoSuchSVNRevisionException, SystemException {
		List<SVNRevision> list = findBySVNUserId(svnUserId, 0, 1, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No SVNRevision exists with the key {");

			msg.append("svnUserId=" + svnUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSVNRevisionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public SVNRevision findBySVNUserId_Last(String svnUserId,
		OrderByComparator obc)
		throws NoSuchSVNRevisionException, SystemException {
		int count = countBySVNUserId(svnUserId);

		List<SVNRevision> list = findBySVNUserId(svnUserId, count - 1, count,
				obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No SVNRevision exists with the key {");

			msg.append("svnUserId=" + svnUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSVNRevisionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public SVNRevision[] findBySVNUserId_PrevAndNext(long svnRevisionId,
		String svnUserId, OrderByComparator obc)
		throws NoSuchSVNRevisionException, SystemException {
		SVNRevision svnRevision = findByPrimaryKey(svnRevisionId);

		int count = countBySVNUserId(svnUserId);

		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();

			query.append("FROM com.liferay.wol.model.SVNRevision WHERE ");

			if (svnUserId == null) {
				query.append("svnUserId IS NULL");
			}
			else {
				query.append("svnUserId = ?");
			}

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("svnRepositoryId DESC, ");
				query.append("revisionNumber DESC");
			}

			Query q = session.createQuery(query.toString());

			int queryPos = 0;

			if (svnUserId != null) {
				q.setString(queryPos++, svnUserId);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					svnRevision);

			SVNRevision[] array = new SVNRevisionImpl[3];

			array[0] = (SVNRevision)objArray[0];
			array[1] = (SVNRevision)objArray[1];
			array[2] = (SVNRevision)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SVNRevision> findBySVNRepositoryId(long svnRepositoryId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SVNRevisionModelImpl.CACHE_ENABLED;
		String finderClassName = SVNRevision.class.getName();
		String finderMethodName = "findBySVNRepositoryId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(svnRepositoryId) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.wol.model.SVNRevision WHERE ");

				query.append("svnRepositoryId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("svnRepositoryId DESC, ");
				query.append("revisionNumber DESC");

				Query q = session.createQuery(query.toString());

				int queryPos = 0;

				q.setLong(queryPos++, svnRepositoryId);

				List<SVNRevision> list = q.list();

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<SVNRevision>)result;
		}
	}

	public List<SVNRevision> findBySVNRepositoryId(long svnRepositoryId,
		int begin, int end) throws SystemException {
		return findBySVNRepositoryId(svnRepositoryId, begin, end, null);
	}

	public List<SVNRevision> findBySVNRepositoryId(long svnRepositoryId,
		int begin, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = SVNRevisionModelImpl.CACHE_ENABLED;
		String finderClassName = SVNRevision.class.getName();
		String finderMethodName = "findBySVNRepositoryId";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(svnRepositoryId),
				
				String.valueOf(begin), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.wol.model.SVNRevision WHERE ");

				query.append("svnRepositoryId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("svnRepositoryId DESC, ");
					query.append("revisionNumber DESC");
				}

				Query q = session.createQuery(query.toString());

				int queryPos = 0;

				q.setLong(queryPos++, svnRepositoryId);

				List<SVNRevision> list = (List<SVNRevision>)QueryUtil.list(q,
						getDialect(), begin, end);

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<SVNRevision>)result;
		}
	}

	public SVNRevision findBySVNRepositoryId_First(long svnRepositoryId,
		OrderByComparator obc)
		throws NoSuchSVNRevisionException, SystemException {
		List<SVNRevision> list = findBySVNRepositoryId(svnRepositoryId, 0, 1,
				obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No SVNRevision exists with the key {");

			msg.append("svnRepositoryId=" + svnRepositoryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSVNRevisionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public SVNRevision findBySVNRepositoryId_Last(long svnRepositoryId,
		OrderByComparator obc)
		throws NoSuchSVNRevisionException, SystemException {
		int count = countBySVNRepositoryId(svnRepositoryId);

		List<SVNRevision> list = findBySVNRepositoryId(svnRepositoryId,
				count - 1, count, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No SVNRevision exists with the key {");

			msg.append("svnRepositoryId=" + svnRepositoryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSVNRevisionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public SVNRevision[] findBySVNRepositoryId_PrevAndNext(long svnRevisionId,
		long svnRepositoryId, OrderByComparator obc)
		throws NoSuchSVNRevisionException, SystemException {
		SVNRevision svnRevision = findByPrimaryKey(svnRevisionId);

		int count = countBySVNRepositoryId(svnRepositoryId);

		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();

			query.append("FROM com.liferay.wol.model.SVNRevision WHERE ");

			query.append("svnRepositoryId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("svnRepositoryId DESC, ");
				query.append("revisionNumber DESC");
			}

			Query q = session.createQuery(query.toString());

			int queryPos = 0;

			q.setLong(queryPos++, svnRepositoryId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					svnRevision);

			SVNRevision[] array = new SVNRevisionImpl[3];

			array[0] = (SVNRevision)objArray[0];
			array[1] = (SVNRevision)objArray[1];
			array[2] = (SVNRevision)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SVNRevision> findBySVNU_SVNR(String svnUserId,
		long svnRepositoryId) throws SystemException {
		boolean finderClassNameCacheEnabled = SVNRevisionModelImpl.CACHE_ENABLED;
		String finderClassName = SVNRevision.class.getName();
		String finderMethodName = "findBySVNU_SVNR";
		String[] finderParams = new String[] {
				String.class.getName(), Long.class.getName()
			};
		Object[] finderArgs = new Object[] { svnUserId, new Long(svnRepositoryId) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.wol.model.SVNRevision WHERE ");

				if (svnUserId == null) {
					query.append("svnUserId IS NULL");
				}
				else {
					query.append("svnUserId = ?");
				}

				query.append(" AND ");

				query.append("svnRepositoryId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("svnRepositoryId DESC, ");
				query.append("revisionNumber DESC");

				Query q = session.createQuery(query.toString());

				int queryPos = 0;

				if (svnUserId != null) {
					q.setString(queryPos++, svnUserId);
				}

				q.setLong(queryPos++, svnRepositoryId);

				List<SVNRevision> list = q.list();

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<SVNRevision>)result;
		}
	}

	public List<SVNRevision> findBySVNU_SVNR(String svnUserId,
		long svnRepositoryId, int begin, int end) throws SystemException {
		return findBySVNU_SVNR(svnUserId, svnRepositoryId, begin, end, null);
	}

	public List<SVNRevision> findBySVNU_SVNR(String svnUserId,
		long svnRepositoryId, int begin, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SVNRevisionModelImpl.CACHE_ENABLED;
		String finderClassName = SVNRevision.class.getName();
		String finderMethodName = "findBySVNU_SVNR";
		String[] finderParams = new String[] {
				String.class.getName(), Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				svnUserId, new Long(svnRepositoryId),
				
				String.valueOf(begin), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.wol.model.SVNRevision WHERE ");

				if (svnUserId == null) {
					query.append("svnUserId IS NULL");
				}
				else {
					query.append("svnUserId = ?");
				}

				query.append(" AND ");

				query.append("svnRepositoryId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("svnRepositoryId DESC, ");
					query.append("revisionNumber DESC");
				}

				Query q = session.createQuery(query.toString());

				int queryPos = 0;

				if (svnUserId != null) {
					q.setString(queryPos++, svnUserId);
				}

				q.setLong(queryPos++, svnRepositoryId);

				List<SVNRevision> list = (List<SVNRevision>)QueryUtil.list(q,
						getDialect(), begin, end);

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<SVNRevision>)result;
		}
	}

	public SVNRevision findBySVNU_SVNR_First(String svnUserId,
		long svnRepositoryId, OrderByComparator obc)
		throws NoSuchSVNRevisionException, SystemException {
		List<SVNRevision> list = findBySVNU_SVNR(svnUserId, svnRepositoryId, 0,
				1, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No SVNRevision exists with the key {");

			msg.append("svnUserId=" + svnUserId);

			msg.append(", ");
			msg.append("svnRepositoryId=" + svnRepositoryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSVNRevisionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public SVNRevision findBySVNU_SVNR_Last(String svnUserId,
		long svnRepositoryId, OrderByComparator obc)
		throws NoSuchSVNRevisionException, SystemException {
		int count = countBySVNU_SVNR(svnUserId, svnRepositoryId);

		List<SVNRevision> list = findBySVNU_SVNR(svnUserId, svnRepositoryId,
				count - 1, count, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No SVNRevision exists with the key {");

			msg.append("svnUserId=" + svnUserId);

			msg.append(", ");
			msg.append("svnRepositoryId=" + svnRepositoryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSVNRevisionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public SVNRevision[] findBySVNU_SVNR_PrevAndNext(long svnRevisionId,
		String svnUserId, long svnRepositoryId, OrderByComparator obc)
		throws NoSuchSVNRevisionException, SystemException {
		SVNRevision svnRevision = findByPrimaryKey(svnRevisionId);

		int count = countBySVNU_SVNR(svnUserId, svnRepositoryId);

		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();

			query.append("FROM com.liferay.wol.model.SVNRevision WHERE ");

			if (svnUserId == null) {
				query.append("svnUserId IS NULL");
			}
			else {
				query.append("svnUserId = ?");
			}

			query.append(" AND ");

			query.append("svnRepositoryId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("svnRepositoryId DESC, ");
				query.append("revisionNumber DESC");
			}

			Query q = session.createQuery(query.toString());

			int queryPos = 0;

			if (svnUserId != null) {
				q.setString(queryPos++, svnUserId);
			}

			q.setLong(queryPos++, svnRepositoryId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					svnRevision);

			SVNRevision[] array = new SVNRevisionImpl[3];

			array[0] = (SVNRevision)objArray[0];
			array[1] = (SVNRevision)objArray[1];
			array[2] = (SVNRevision)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SVNRevision> findWithDynamicQuery(
		DynamicQueryInitializer queryInitializer) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			DynamicQuery query = queryInitializer.initialize(session);

			return query.list();
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SVNRevision> findWithDynamicQuery(
		DynamicQueryInitializer queryInitializer, int begin, int end)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			DynamicQuery query = queryInitializer.initialize(session);

			query.setLimit(begin, end);

			return query.list();
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SVNRevision> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<SVNRevision> findAll(int begin, int end)
		throws SystemException {
		return findAll(begin, end, null);
	}

	public List<SVNRevision> findAll(int begin, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SVNRevisionModelImpl.CACHE_ENABLED;
		String finderClassName = SVNRevision.class.getName();
		String finderMethodName = "findAll";
		String[] finderParams = new String[] {
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				String.valueOf(begin), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.wol.model.SVNRevision ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("svnRepositoryId DESC, ");
					query.append("revisionNumber DESC");
				}

				Query q = session.createQuery(query.toString());

				List<SVNRevision> list = (List<SVNRevision>)QueryUtil.list(q,
						getDialect(), begin, end);

				if (obc == null) {
					Collections.sort(list);
				}

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<SVNRevision>)result;
		}
	}

	public void removeBySVNUserId(String svnUserId) throws SystemException {
		for (SVNRevision svnRevision : findBySVNUserId(svnUserId)) {
			remove(svnRevision);
		}
	}

	public void removeBySVNRepositoryId(long svnRepositoryId)
		throws SystemException {
		for (SVNRevision svnRevision : findBySVNRepositoryId(svnRepositoryId)) {
			remove(svnRevision);
		}
	}

	public void removeBySVNU_SVNR(String svnUserId, long svnRepositoryId)
		throws SystemException {
		for (SVNRevision svnRevision : findBySVNU_SVNR(svnUserId,
				svnRepositoryId)) {
			remove(svnRevision);
		}
	}

	public void removeAll() throws SystemException {
		for (SVNRevision svnRevision : findAll()) {
			remove(svnRevision);
		}
	}

	public int countBySVNUserId(String svnUserId) throws SystemException {
		boolean finderClassNameCacheEnabled = SVNRevisionModelImpl.CACHE_ENABLED;
		String finderClassName = SVNRevision.class.getName();
		String finderMethodName = "countBySVNUserId";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { svnUserId };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.wol.model.SVNRevision WHERE ");

				if (svnUserId == null) {
					query.append("svnUserId IS NULL");
				}
				else {
					query.append("svnUserId = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				int queryPos = 0;

				if (svnUserId != null) {
					q.setString(queryPos++, svnUserId);
				}

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public int countBySVNRepositoryId(long svnRepositoryId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SVNRevisionModelImpl.CACHE_ENABLED;
		String finderClassName = SVNRevision.class.getName();
		String finderMethodName = "countBySVNRepositoryId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(svnRepositoryId) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.wol.model.SVNRevision WHERE ");

				query.append("svnRepositoryId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				int queryPos = 0;

				q.setLong(queryPos++, svnRepositoryId);

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public int countBySVNU_SVNR(String svnUserId, long svnRepositoryId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SVNRevisionModelImpl.CACHE_ENABLED;
		String finderClassName = SVNRevision.class.getName();
		String finderMethodName = "countBySVNU_SVNR";
		String[] finderParams = new String[] {
				String.class.getName(), Long.class.getName()
			};
		Object[] finderArgs = new Object[] { svnUserId, new Long(svnRepositoryId) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.wol.model.SVNRevision WHERE ");

				if (svnUserId == null) {
					query.append("svnUserId IS NULL");
				}
				else {
					query.append("svnUserId = ?");
				}

				query.append(" AND ");

				query.append("svnRepositoryId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				int queryPos = 0;

				if (svnUserId != null) {
					q.setString(queryPos++, svnUserId);
				}

				q.setLong(queryPos++, svnRepositoryId);

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
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
		boolean finderClassNameCacheEnabled = SVNRevisionModelImpl.CACHE_ENABLED;
		String finderClassName = SVNRevision.class.getName();
		String finderMethodName = "countAll";
		String[] finderParams = new String[] {  };
		Object[] finderArgs = new Object[] {  };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(
						"SELECT COUNT(*) FROM com.liferay.wol.model.SVNRevision");

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	protected void initDao() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					PropsUtil.get(
						"value.object.listener.com.liferay.wol.model.SVNRevision")));

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
	}

	private static Log _log = LogFactory.getLog(SVNRevisionPersistenceImpl.class);
	private ModelListener[] _listeners;
}