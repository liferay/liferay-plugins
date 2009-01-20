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

package com.liferay.iweb.service.persistence;

import com.liferay.iweb.NoSuchSemanticsFileException;
import com.liferay.iweb.model.SemanticsFile;
import com.liferay.iweb.model.impl.SemanticsFileImpl;
import com.liferay.iweb.model.impl.SemanticsFileModelImpl;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bean.InitializingBean;
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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="SemanticsFilePersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SemanticsFilePersistenceImpl extends BasePersistenceImpl
	implements SemanticsFilePersistence, InitializingBean {
	public SemanticsFile create(String semanticsURI) {
		SemanticsFile semanticsFile = new SemanticsFileImpl();

		semanticsFile.setNew(true);
		semanticsFile.setPrimaryKey(semanticsURI);

		return semanticsFile;
	}

	public SemanticsFile remove(String semanticsURI)
		throws NoSuchSemanticsFileException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SemanticsFile semanticsFile = (SemanticsFile)session.get(SemanticsFileImpl.class,
					semanticsURI);

			if (semanticsFile == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No SemanticsFile exists with the primary key " +
						semanticsURI);
				}

				throw new NoSuchSemanticsFileException(
					"No SemanticsFile exists with the primary key " +
					semanticsURI);
			}

			return remove(semanticsFile);
		}
		catch (NoSuchSemanticsFileException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public SemanticsFile remove(SemanticsFile semanticsFile)
		throws SystemException {
		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(semanticsFile);
			}
		}

		semanticsFile = removeImpl(semanticsFile);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(semanticsFile);
			}
		}

		return semanticsFile;
	}

	protected SemanticsFile removeImpl(SemanticsFile semanticsFile)
		throws SystemException {
		try {
			clearInterestGroups.clear(semanticsFile.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
		}

		Session session = null;

		try {
			session = openSession();

			session.delete(semanticsFile);

			session.flush();

			return semanticsFile;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(SemanticsFile.class.getName());
		}
	}

	public SemanticsFile update(SemanticsFile semanticsFile)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(SemanticsFile semanticsFile) method. Use update(SemanticsFile semanticsFile, boolean merge) instead.");
		}

		return update(semanticsFile, false);
	}

	public SemanticsFile update(SemanticsFile semanticsFile, boolean merge)
		throws SystemException {
		boolean isNew = semanticsFile.isNew();

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(semanticsFile);
				}
				else {
					listener.onBeforeUpdate(semanticsFile);
				}
			}
		}

		semanticsFile = updateImpl(semanticsFile, merge);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(semanticsFile);
				}
				else {
					listener.onAfterUpdate(semanticsFile);
				}
			}
		}

		return semanticsFile;
	}

	public SemanticsFile updateImpl(
		com.liferay.iweb.model.SemanticsFile semanticsFile, boolean merge)
		throws SystemException {
		FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");

		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(semanticsFile);
			}
			else {
				if (semanticsFile.isNew()) {
					session.save(semanticsFile);
				}
			}

			session.flush();

			semanticsFile.setNew(false);

			return semanticsFile;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(SemanticsFile.class.getName());
		}
	}

	public SemanticsFile findByPrimaryKey(String semanticsURI)
		throws NoSuchSemanticsFileException, SystemException {
		SemanticsFile semanticsFile = fetchByPrimaryKey(semanticsURI);

		if (semanticsFile == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No SemanticsFile exists with the primary key " +
					semanticsURI);
			}

			throw new NoSuchSemanticsFileException(
				"No SemanticsFile exists with the primary key " + semanticsURI);
		}

		return semanticsFile;
	}

	public SemanticsFile fetchByPrimaryKey(String semanticsURI)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (SemanticsFile)session.get(SemanticsFileImpl.class,
				semanticsURI);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<SemanticsFile> findByCreatedInInterestGroup(
		long createdInInterestGroup) throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsFileModelImpl.CACHE_ENABLED;
		String finderClassName = SemanticsFile.class.getName();
		String finderMethodName = "findByCreatedInInterestGroup";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(createdInInterestGroup) };

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

				query.append("FROM com.liferay.iweb.model.SemanticsFile WHERE ");

				query.append("createdInInterestGroup = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createdInInterestGroup);

				List<SemanticsFile> list = q.list();

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
			return (List<SemanticsFile>)result;
		}
	}

	public List<SemanticsFile> findByCreatedInInterestGroup(
		long createdInInterestGroup, int start, int end)
		throws SystemException {
		return findByCreatedInInterestGroup(createdInInterestGroup, start, end,
			null);
	}

	public List<SemanticsFile> findByCreatedInInterestGroup(
		long createdInInterestGroup, int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsFileModelImpl.CACHE_ENABLED;
		String finderClassName = SemanticsFile.class.getName();
		String finderMethodName = "findByCreatedInInterestGroup";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(createdInInterestGroup),
				
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

				query.append("FROM com.liferay.iweb.model.SemanticsFile WHERE ");

				query.append("createdInInterestGroup = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createdInInterestGroup);

				List<SemanticsFile> list = (List<SemanticsFile>)QueryUtil.list(q,
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
			return (List<SemanticsFile>)result;
		}
	}

	public SemanticsFile findByCreatedInInterestGroup_First(
		long createdInInterestGroup, OrderByComparator obc)
		throws NoSuchSemanticsFileException, SystemException {
		List<SemanticsFile> list = findByCreatedInInterestGroup(createdInInterestGroup,
				0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No SemanticsFile exists with the key {");

			msg.append("createdInInterestGroup=" + createdInInterestGroup);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSemanticsFileException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public SemanticsFile findByCreatedInInterestGroup_Last(
		long createdInInterestGroup, OrderByComparator obc)
		throws NoSuchSemanticsFileException, SystemException {
		int count = countByCreatedInInterestGroup(createdInInterestGroup);

		List<SemanticsFile> list = findByCreatedInInterestGroup(createdInInterestGroup,
				count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No SemanticsFile exists with the key {");

			msg.append("createdInInterestGroup=" + createdInInterestGroup);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSemanticsFileException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public SemanticsFile[] findByCreatedInInterestGroup_PrevAndNext(
		String semanticsURI, long createdInInterestGroup, OrderByComparator obc)
		throws NoSuchSemanticsFileException, SystemException {
		SemanticsFile semanticsFile = findByPrimaryKey(semanticsURI);

		int count = countByCreatedInInterestGroup(createdInInterestGroup);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.iweb.model.SemanticsFile WHERE ");

			query.append("createdInInterestGroup = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(createdInInterestGroup);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					semanticsFile);

			SemanticsFile[] array = new SemanticsFileImpl[3];

			array[0] = (SemanticsFile)objArray[0];
			array[1] = (SemanticsFile)objArray[1];
			array[2] = (SemanticsFile)objArray[2];

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

	public List<SemanticsFile> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<SemanticsFile> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<SemanticsFile> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsFileModelImpl.CACHE_ENABLED;
		String finderClassName = SemanticsFile.class.getName();
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

				query.append("FROM com.liferay.iweb.model.SemanticsFile ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<SemanticsFile> list = (List<SemanticsFile>)QueryUtil.list(q,
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
			return (List<SemanticsFile>)result;
		}
	}

	public void removeByCreatedInInterestGroup(long createdInInterestGroup)
		throws SystemException {
		for (SemanticsFile semanticsFile : findByCreatedInInterestGroup(
				createdInInterestGroup)) {
			remove(semanticsFile);
		}
	}

	public void removeAll() throws SystemException {
		for (SemanticsFile semanticsFile : findAll()) {
			remove(semanticsFile);
		}
	}

	public int countByCreatedInInterestGroup(long createdInInterestGroup)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsFileModelImpl.CACHE_ENABLED;
		String finderClassName = SemanticsFile.class.getName();
		String finderMethodName = "countByCreatedInInterestGroup";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(createdInInterestGroup) };

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
				query.append("FROM com.liferay.iweb.model.SemanticsFile WHERE ");

				query.append("createdInInterestGroup = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createdInInterestGroup);

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
		boolean finderClassNameCacheEnabled = SemanticsFileModelImpl.CACHE_ENABLED;
		String finderClassName = SemanticsFile.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.iweb.model.SemanticsFile");

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

	public List<com.liferay.iweb.model.InterestGroup> getInterestGroups(
		String pk) throws SystemException {
		return getInterestGroups(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<com.liferay.iweb.model.InterestGroup> getInterestGroups(
		String pk, int start, int end) throws SystemException {
		return getInterestGroups(pk, start, end, null);
	}

	public List<com.liferay.iweb.model.InterestGroup> getInterestGroups(
		String pk, int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsFileModelImpl.CACHE_ENABLED_INTERESTGROUPS_SEMANTICSFILES;

		String finderClassName = "InterestGroups_SemanticsFiles";

		String finderMethodName = "getInterestGroups";
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

				sb.append(_SQL_GETINTERESTGROUPS);

				if (obc != null) {
					sb.append("ORDER BY ");
					sb.append(obc.getOrderBy());
				}

				String sql = sb.toString();

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("IWEB_InterestGroup",
					com.liferay.iweb.model.impl.InterestGroupImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				List<com.liferay.iweb.model.InterestGroup> list = (List<com.liferay.iweb.model.InterestGroup>)QueryUtil.list(q,
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
			return (List<com.liferay.iweb.model.InterestGroup>)result;
		}
	}

	public int getInterestGroupsSize(String pk) throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsFileModelImpl.CACHE_ENABLED_INTERESTGROUPS_SEMANTICSFILES;

		String finderClassName = "InterestGroups_SemanticsFiles";

		String finderMethodName = "getInterestGroupsSize";
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

				SQLQuery q = session.createSQLQuery(_SQL_GETINTERESTGROUPSSIZE);

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

	public boolean containsInterestGroup(String pk, long interestGroupPK)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsFileModelImpl.CACHE_ENABLED_INTERESTGROUPS_SEMANTICSFILES;

		String finderClassName = "InterestGroups_SemanticsFiles";

		String finderMethodName = "containsInterestGroups";
		String[] finderParams = new String[] {
				String.class.getName(),
				
				Long.class.getName()
			};
		Object[] finderArgs = new Object[] { pk, new Long(interestGroupPK) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			try {
				Boolean value = Boolean.valueOf(containsInterestGroup.contains(
							pk, interestGroupPK));

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

	public boolean containsInterestGroups(String pk) throws SystemException {
		if (getInterestGroupsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void addInterestGroup(String pk, long interestGroupPK)
		throws SystemException {
		try {
			addInterestGroup.add(pk, interestGroupPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
		}
	}

	public void addInterestGroup(String pk,
		com.liferay.iweb.model.InterestGroup interestGroup)
		throws SystemException {
		try {
			addInterestGroup.add(pk, interestGroup.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
		}
	}

	public void addInterestGroups(String pk, long[] interestGroupPKs)
		throws SystemException {
		try {
			for (long interestGroupPK : interestGroupPKs) {
				addInterestGroup.add(pk, interestGroupPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
		}
	}

	public void addInterestGroups(String pk,
		List<com.liferay.iweb.model.InterestGroup> interestGroups)
		throws SystemException {
		try {
			for (com.liferay.iweb.model.InterestGroup interestGroup : interestGroups) {
				addInterestGroup.add(pk, interestGroup.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
		}
	}

	public void clearInterestGroups(String pk) throws SystemException {
		try {
			clearInterestGroups.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
		}
	}

	public void removeInterestGroup(String pk, long interestGroupPK)
		throws SystemException {
		try {
			removeInterestGroup.remove(pk, interestGroupPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
		}
	}

	public void removeInterestGroup(String pk,
		com.liferay.iweb.model.InterestGroup interestGroup)
		throws SystemException {
		try {
			removeInterestGroup.remove(pk, interestGroup.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
		}
	}

	public void removeInterestGroups(String pk, long[] interestGroupPKs)
		throws SystemException {
		try {
			for (long interestGroupPK : interestGroupPKs) {
				removeInterestGroup.remove(pk, interestGroupPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
		}
	}

	public void removeInterestGroups(String pk,
		List<com.liferay.iweb.model.InterestGroup> interestGroups)
		throws SystemException {
		try {
			for (com.liferay.iweb.model.InterestGroup interestGroup : interestGroups) {
				removeInterestGroup.remove(pk, interestGroup.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
		}
	}

	public void setInterestGroups(String pk, long[] interestGroupPKs)
		throws SystemException {
		try {
			clearInterestGroups.clear(pk);

			for (long interestGroupPK : interestGroupPKs) {
				addInterestGroup.add(pk, interestGroupPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
		}
	}

	public void setInterestGroups(String pk,
		List<com.liferay.iweb.model.InterestGroup> interestGroups)
		throws SystemException {
		try {
			clearInterestGroups.clear(pk);

			for (com.liferay.iweb.model.InterestGroup interestGroup : interestGroups) {
				addInterestGroup.add(pk, interestGroup.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
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

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.iweb.model.SemanticsFile")));

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

		containsInterestGroup = new ContainsInterestGroup(this);

		addInterestGroup = new AddInterestGroup(this);
		clearInterestGroups = new ClearInterestGroups(this);
		removeInterestGroup = new RemoveInterestGroup(this);
	}

	protected ContainsInterestGroup containsInterestGroup;
	protected AddInterestGroup addInterestGroup;
	protected ClearInterestGroups clearInterestGroups;
	protected RemoveInterestGroup removeInterestGroup;

	protected class ContainsInterestGroup {
		protected ContainsInterestGroup(
			SemanticsFilePersistenceImpl persistenceImpl) {
			super();

			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSINTERESTGROUP,
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

	protected class AddInterestGroup {
		protected AddInterestGroup(SemanticsFilePersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO InterestGroups_SemanticsFiles (semanticsURI, cid) VALUES (?, ?)",
					new int[] { Types.VARCHAR, Types.BIGINT });
			_persistenceImpl = persistenceImpl;
		}

		protected void add(String semanticsURI, long cid) {
			if (!_persistenceImpl.containsInterestGroup.contains(semanticsURI,
						cid)) {
				_sqlUpdate.update(new Object[] { semanticsURI, new Long(cid) });
			}
		}

		private SqlUpdate _sqlUpdate;
		private SemanticsFilePersistenceImpl _persistenceImpl;
	}

	protected class ClearInterestGroups {
		protected ClearInterestGroups(
			SemanticsFilePersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM InterestGroups_SemanticsFiles WHERE semanticsURI = ?",
					new int[] { Types.VARCHAR });
		}

		protected void clear(String semanticsURI) {
			_sqlUpdate.update(new Object[] { semanticsURI });
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveInterestGroup {
		protected RemoveInterestGroup(
			SemanticsFilePersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM InterestGroups_SemanticsFiles WHERE semanticsURI = ? AND cid = ?",
					new int[] { Types.VARCHAR, Types.BIGINT });
		}

		protected void remove(String semanticsURI, long cid) {
			_sqlUpdate.update(new Object[] { semanticsURI, new Long(cid) });
		}

		private SqlUpdate _sqlUpdate;
	}

	private static final String _SQL_GETINTERESTGROUPS = "SELECT {IWEB_InterestGroup.*} FROM IWEB_InterestGroup INNER JOIN InterestGroups_SemanticsFiles ON (InterestGroups_SemanticsFiles.cid = IWEB_InterestGroup.cid) WHERE (InterestGroups_SemanticsFiles.semanticsURI = ?)";
	private static final String _SQL_GETINTERESTGROUPSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM InterestGroups_SemanticsFiles WHERE semanticsURI = ?";
	private static final String _SQL_CONTAINSINTERESTGROUP = "SELECT COUNT(*) AS COUNT_VALUE FROM InterestGroups_SemanticsFiles WHERE semanticsURI = ? AND cid = ?";
	private static Log _log = LogFactoryUtil.getLog(SemanticsFilePersistenceImpl.class);
	private ModelListener[] _listeners = new ModelListener[0];
}