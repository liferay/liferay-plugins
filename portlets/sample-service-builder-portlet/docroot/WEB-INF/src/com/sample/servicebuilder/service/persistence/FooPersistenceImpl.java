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

package com.sample.servicebuilder.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.DynamicQuery;
import com.liferay.portal.kernel.dao.DynamicQueryInitializer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringMaker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelListener;

import com.liferay.portlet.service.BasePersistence;
import com.liferay.portlet.service.FinderCache;
import com.liferay.portlet.service.HibernateUtil;
import com.liferay.portlet.service.PropsUtil;

import com.liferay.util.dao.hibernate.QueryUtil;

import com.sample.servicebuilder.NoSuchFooException;
import com.sample.servicebuilder.model.Foo;
import com.sample.servicebuilder.model.impl.FooImpl;
import com.sample.servicebuilder.model.impl.FooModelImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="FooPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FooPersistenceImpl extends BasePersistence
	implements FooPersistence {
	public Foo create(long fooId) {
		Foo foo = new FooImpl();

		foo.setNew(true);
		foo.setPrimaryKey(fooId);

		return foo;
	}

	public Foo remove(long fooId) throws NoSuchFooException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Foo foo = (Foo)session.get(FooImpl.class, new Long(fooId));

			if (foo == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No Foo exists with the primary key " + fooId);
				}

				throw new NoSuchFooException(
					"No Foo exists with the primary key " + fooId);
			}

			return remove(foo);
		}
		catch (NoSuchFooException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public Foo remove(Foo foo) throws SystemException {
		ModelListener listener = _getListener();

		if (listener != null) {
			listener.onBeforeRemove(foo);
		}

		foo = removeImpl(foo);

		if (listener != null) {
			listener.onAfterRemove(foo);
		}

		return foo;
	}

	protected Foo removeImpl(Foo foo) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			session.delete(foo);

			session.flush();

			return foo;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);

			FinderCache.clearCache(Foo.class.getName());
		}
	}

	public Foo update(Foo foo) throws SystemException {
		return update(foo, false);
	}

	public Foo update(Foo foo, boolean merge) throws SystemException {
		ModelListener listener = _getListener();

		boolean isNew = foo.isNew();

		if (listener != null) {
			if (isNew) {
				listener.onBeforeCreate(foo);
			}
			else {
				listener.onBeforeUpdate(foo);
			}
		}

		foo = updateImpl(foo, merge);

		if (listener != null) {
			if (isNew) {
				listener.onAfterCreate(foo);
			}
			else {
				listener.onAfterUpdate(foo);
			}
		}

		return foo;
	}

	public Foo updateImpl(com.sample.servicebuilder.model.Foo foo, boolean merge)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(foo);
			}
			else {
				if (foo.isNew()) {
					session.save(foo);
				}
			}

			session.flush();

			foo.setNew(false);

			return foo;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);

			FinderCache.clearCache(Foo.class.getName());
		}
	}

	public Foo findByPrimaryKey(long fooId)
		throws NoSuchFooException, SystemException {
		Foo foo = fetchByPrimaryKey(fooId);

		if (foo == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No Foo exists with the primary key " + fooId);
			}

			throw new NoSuchFooException("No Foo exists with the primary key " +
				fooId);
		}

		return foo;
	}

	public Foo fetchByPrimaryKey(long fooId) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (Foo)session.get(FooImpl.class, new Long(fooId));
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List findByField2(boolean field2) throws SystemException {
		boolean finderClassNameCacheEnabled = FooModelImpl.CACHE_ENABLED;
		String finderClassName = Foo.class.getName();
		String finderMethodName = "findByField2";
		String[] finderParams = new String[] { Boolean.class.getName() };
		Object[] finderArgs = new Object[] { Boolean.valueOf(field2) };

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

				query.append("FROM com.sample.servicebuilder.model.Foo WHERE ");

				query.append("field2 = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("field1 ASC");

				Query q = session.createQuery(query.toString());

				int queryPos = 0;

				q.setBoolean(queryPos++, field2);

				List list = q.list();

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
			return (List)result;
		}
	}

	public List findByField2(boolean field2, int begin, int end)
		throws SystemException {
		return findByField2(field2, begin, end, null);
	}

	public List findByField2(boolean field2, int begin, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = FooModelImpl.CACHE_ENABLED;
		String finderClassName = Foo.class.getName();
		String finderMethodName = "findByField2";
		String[] finderParams = new String[] {
				Boolean.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				Boolean.valueOf(field2),
				
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

				query.append("FROM com.sample.servicebuilder.model.Foo WHERE ");

				query.append("field2 = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("field1 ASC");
				}

				Query q = session.createQuery(query.toString());

				int queryPos = 0;

				q.setBoolean(queryPos++, field2);

				List list = QueryUtil.list(q, getDialect(), begin, end);

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
			return (List)result;
		}
	}

	public Foo findByField2_First(boolean field2, OrderByComparator obc)
		throws NoSuchFooException, SystemException {
		List list = findByField2(field2, 0, 1, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No Foo exists with the key {");

			msg.append("field2=" + field2);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFooException(msg.toString());
		}
		else {
			return (Foo)list.get(0);
		}
	}

	public Foo findByField2_Last(boolean field2, OrderByComparator obc)
		throws NoSuchFooException, SystemException {
		int count = countByField2(field2);

		List list = findByField2(field2, count - 1, count, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No Foo exists with the key {");

			msg.append("field2=" + field2);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFooException(msg.toString());
		}
		else {
			return (Foo)list.get(0);
		}
	}

	public Foo[] findByField2_PrevAndNext(long fooId, boolean field2,
		OrderByComparator obc) throws NoSuchFooException, SystemException {
		Foo foo = findByPrimaryKey(fooId);

		int count = countByField2(field2);

		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();

			query.append("FROM com.sample.servicebuilder.model.Foo WHERE ");

			query.append("field2 = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("field1 ASC");
			}

			Query q = session.createQuery(query.toString());

			int queryPos = 0;

			q.setBoolean(queryPos++, field2);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, foo);

			Foo[] array = new FooImpl[3];

			array[0] = (Foo)objArray[0];
			array[1] = (Foo)objArray[1];
			array[2] = (Foo)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List findWithDynamicQuery(DynamicQueryInitializer queryInitializer)
		throws SystemException {
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

	public List findWithDynamicQuery(DynamicQueryInitializer queryInitializer,
		int begin, int end) throws SystemException {
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

	public List findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List findAll(int begin, int end) throws SystemException {
		return findAll(begin, end, null);
	}

	public List findAll(int begin, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = FooModelImpl.CACHE_ENABLED;
		String finderClassName = Foo.class.getName();
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

				query.append("FROM com.sample.servicebuilder.model.Foo ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("field1 ASC");
				}

				Query q = session.createQuery(query.toString());

				List list = QueryUtil.list(q, getDialect(), begin, end);

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
			return (List)result;
		}
	}

	public void removeByField2(boolean field2) throws SystemException {
		Iterator itr = findByField2(field2).iterator();

		while (itr.hasNext()) {
			Foo foo = (Foo)itr.next();

			remove(foo);
		}
	}

	public void removeAll() throws SystemException {
		Iterator itr = findAll().iterator();

		while (itr.hasNext()) {
			remove((Foo)itr.next());
		}
	}

	public int countByField2(boolean field2) throws SystemException {
		boolean finderClassNameCacheEnabled = FooModelImpl.CACHE_ENABLED;
		String finderClassName = Foo.class.getName();
		String finderMethodName = "countByField2";
		String[] finderParams = new String[] { Boolean.class.getName() };
		Object[] finderArgs = new Object[] { Boolean.valueOf(field2) };

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
				query.append("FROM com.sample.servicebuilder.model.Foo WHERE ");

				query.append("field2 = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				int queryPos = 0;

				q.setBoolean(queryPos++, field2);

				Long count = null;

				Iterator itr = q.list().iterator();

				if (itr.hasNext()) {
					count = (Long)itr.next();
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
		boolean finderClassNameCacheEnabled = FooModelImpl.CACHE_ENABLED;
		String finderClassName = Foo.class.getName();
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
						"SELECT COUNT(*) FROM com.sample.servicebuilder.model.Foo");

				Long count = null;

				Iterator itr = q.list().iterator();

				if (itr.hasNext()) {
					count = (Long)itr.next();
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
	}

	private static ModelListener _getListener() {
		if (Validator.isNotNull(_LISTENER)) {
			try {
				return (ModelListener)Class.forName(_LISTENER).newInstance();
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		return null;
	}

	private static final String _LISTENER = GetterUtil.getString(PropsUtil.get(
				"value.object.listener.com.sample.servicebuilder.model.Foo"));
	private static Log _log = LogFactory.getLog(FooPersistenceImpl.class);
}