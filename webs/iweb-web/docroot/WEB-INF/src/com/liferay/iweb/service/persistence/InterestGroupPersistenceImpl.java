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

import com.liferay.iweb.NoSuchInterestGroupException;
import com.liferay.iweb.model.InterestGroup;
import com.liferay.iweb.model.impl.InterestGroupImpl;
import com.liferay.iweb.model.impl.InterestGroupModelImpl;

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
 * <a href="InterestGroupPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class InterestGroupPersistenceImpl extends BasePersistenceImpl
	implements InterestGroupPersistence, InitializingBean {
	public InterestGroup create(long cid) {
		InterestGroup interestGroup = new InterestGroupImpl();

		interestGroup.setNew(true);
		interestGroup.setPrimaryKey(cid);

		return interestGroup;
	}

	public InterestGroup remove(long cid)
		throws NoSuchInterestGroupException, SystemException {
		Session session = null;

		try {
			session = openSession();

			InterestGroup interestGroup = (InterestGroup)session.get(InterestGroupImpl.class,
					new Long(cid));

			if (interestGroup == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No InterestGroup exists with the primary key " +
						cid);
				}

				throw new NoSuchInterestGroupException(
					"No InterestGroup exists with the primary key " + cid);
			}

			return remove(interestGroup);
		}
		catch (NoSuchInterestGroupException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public InterestGroup remove(InterestGroup interestGroup)
		throws SystemException {
		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(interestGroup);
			}
		}

		interestGroup = removeImpl(interestGroup);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(interestGroup);
			}
		}

		return interestGroup;
	}

	protected InterestGroup removeImpl(InterestGroup interestGroup)
		throws SystemException {
		try {
			clearSemanticsFiles.clear(interestGroup.getPrimaryKey());
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

			session.delete(interestGroup);

			session.flush();

			return interestGroup;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(InterestGroup.class.getName());
		}
	}

	public InterestGroup update(InterestGroup interestGroup)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(InterestGroup interestGroup) method. Use update(InterestGroup interestGroup, boolean merge) instead.");
		}

		return update(interestGroup, false);
	}

	public InterestGroup update(InterestGroup interestGroup, boolean merge)
		throws SystemException {
		boolean isNew = interestGroup.isNew();

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(interestGroup);
				}
				else {
					listener.onBeforeUpdate(interestGroup);
				}
			}
		}

		interestGroup = updateImpl(interestGroup, merge);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(interestGroup);
				}
				else {
					listener.onAfterUpdate(interestGroup);
				}
			}
		}

		return interestGroup;
	}

	public InterestGroup updateImpl(
		com.liferay.iweb.model.InterestGroup interestGroup, boolean merge)
		throws SystemException {
		FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");

		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(interestGroup);
			}
			else {
				if (interestGroup.isNew()) {
					session.save(interestGroup);
				}
			}

			session.flush();

			interestGroup.setNew(false);

			return interestGroup;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(InterestGroup.class.getName());
		}
	}

	public InterestGroup findByPrimaryKey(long cid)
		throws NoSuchInterestGroupException, SystemException {
		InterestGroup interestGroup = fetchByPrimaryKey(cid);

		if (interestGroup == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No InterestGroup exists with the primary key " +
					cid);
			}

			throw new NoSuchInterestGroupException(
				"No InterestGroup exists with the primary key " + cid);
		}

		return interestGroup;
	}

	public InterestGroup fetchByPrimaryKey(long cid) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (InterestGroup)session.get(InterestGroupImpl.class,
				new Long(cid));
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

	public List<InterestGroup> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<InterestGroup> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<InterestGroup> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = InterestGroupModelImpl.CACHE_ENABLED;
		String finderClassName = InterestGroup.class.getName();
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

				query.append("FROM com.liferay.iweb.model.InterestGroup ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<InterestGroup> list = (List<InterestGroup>)QueryUtil.list(q,
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
			return (List<InterestGroup>)result;
		}
	}

	public void removeAll() throws SystemException {
		for (InterestGroup interestGroup : findAll()) {
			remove(interestGroup);
		}
	}

	public int countAll() throws SystemException {
		boolean finderClassNameCacheEnabled = InterestGroupModelImpl.CACHE_ENABLED;
		String finderClassName = InterestGroup.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.iweb.model.InterestGroup");

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

	public List<com.liferay.iweb.model.SemanticsFile> getSemanticsFiles(long pk)
		throws SystemException {
		return getSemanticsFiles(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<com.liferay.iweb.model.SemanticsFile> getSemanticsFiles(
		long pk, int start, int end) throws SystemException {
		return getSemanticsFiles(pk, start, end, null);
	}

	public List<com.liferay.iweb.model.SemanticsFile> getSemanticsFiles(
		long pk, int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = InterestGroupModelImpl.CACHE_ENABLED_INTERESTGROUPS_SEMANTICSFILES;

		String finderClassName = "InterestGroups_SemanticsFiles";

		String finderMethodName = "getSemanticsFiles";
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

				sb.append(_SQL_GETSEMANTICSFILES);

				if (obc != null) {
					sb.append("ORDER BY ");
					sb.append(obc.getOrderBy());
				}

				String sql = sb.toString();

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("IWEB_SemanticsFile",
					com.liferay.iweb.model.impl.SemanticsFileImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				List<com.liferay.iweb.model.SemanticsFile> list = (List<com.liferay.iweb.model.SemanticsFile>)QueryUtil.list(q,
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
			return (List<com.liferay.iweb.model.SemanticsFile>)result;
		}
	}

	public int getSemanticsFilesSize(long pk) throws SystemException {
		boolean finderClassNameCacheEnabled = InterestGroupModelImpl.CACHE_ENABLED_INTERESTGROUPS_SEMANTICSFILES;

		String finderClassName = "InterestGroups_SemanticsFiles";

		String finderMethodName = "getSemanticsFilesSize";
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

				SQLQuery q = session.createSQLQuery(_SQL_GETSEMANTICSFILESSIZE);

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

	public boolean containsSemanticsFile(long pk, String semanticsFilePK)
		throws SystemException {
		boolean finderClassNameCacheEnabled = InterestGroupModelImpl.CACHE_ENABLED_INTERESTGROUPS_SEMANTICSFILES;

		String finderClassName = "InterestGroups_SemanticsFiles";

		String finderMethodName = "containsSemanticsFiles";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				String.class.getName()
			};
		Object[] finderArgs = new Object[] { new Long(pk), semanticsFilePK };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			try {
				Boolean value = Boolean.valueOf(containsSemanticsFile.contains(
							pk, semanticsFilePK));

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

	public boolean containsSemanticsFiles(long pk) throws SystemException {
		if (getSemanticsFilesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void addSemanticsFile(long pk, String semanticsFilePK)
		throws SystemException {
		try {
			addSemanticsFile.add(pk, semanticsFilePK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
		}
	}

	public void addSemanticsFile(long pk,
		com.liferay.iweb.model.SemanticsFile semanticsFile)
		throws SystemException {
		try {
			addSemanticsFile.add(pk, semanticsFile.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
		}
	}

	public void addSemanticsFiles(long pk, String[] semanticsFilePKs)
		throws SystemException {
		try {
			for (String semanticsFilePK : semanticsFilePKs) {
				addSemanticsFile.add(pk, semanticsFilePK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
		}
	}

	public void addSemanticsFiles(long pk,
		List<com.liferay.iweb.model.SemanticsFile> semanticsFiles)
		throws SystemException {
		try {
			for (com.liferay.iweb.model.SemanticsFile semanticsFile : semanticsFiles) {
				addSemanticsFile.add(pk, semanticsFile.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
		}
	}

	public void clearSemanticsFiles(long pk) throws SystemException {
		try {
			clearSemanticsFiles.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
		}
	}

	public void removeSemanticsFile(long pk, String semanticsFilePK)
		throws SystemException {
		try {
			removeSemanticsFile.remove(pk, semanticsFilePK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
		}
	}

	public void removeSemanticsFile(long pk,
		com.liferay.iweb.model.SemanticsFile semanticsFile)
		throws SystemException {
		try {
			removeSemanticsFile.remove(pk, semanticsFile.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
		}
	}

	public void removeSemanticsFiles(long pk, String[] semanticsFilePKs)
		throws SystemException {
		try {
			for (String semanticsFilePK : semanticsFilePKs) {
				removeSemanticsFile.remove(pk, semanticsFilePK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
		}
	}

	public void removeSemanticsFiles(long pk,
		List<com.liferay.iweb.model.SemanticsFile> semanticsFiles)
		throws SystemException {
		try {
			for (com.liferay.iweb.model.SemanticsFile semanticsFile : semanticsFiles) {
				removeSemanticsFile.remove(pk, semanticsFile.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
		}
	}

	public void setSemanticsFiles(long pk, String[] semanticsFilePKs)
		throws SystemException {
		try {
			clearSemanticsFiles.clear(pk);

			for (String semanticsFilePK : semanticsFilePKs) {
				addSemanticsFile.add(pk, semanticsFilePK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("InterestGroups_SemanticsFiles");
		}
	}

	public void setSemanticsFiles(long pk,
		List<com.liferay.iweb.model.SemanticsFile> semanticsFiles)
		throws SystemException {
		try {
			clearSemanticsFiles.clear(pk);

			for (com.liferay.iweb.model.SemanticsFile semanticsFile : semanticsFiles) {
				addSemanticsFile.add(pk, semanticsFile.getPrimaryKey());
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
						"value.object.listener.com.liferay.iweb.model.InterestGroup")));

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

		containsSemanticsFile = new ContainsSemanticsFile(this);

		addSemanticsFile = new AddSemanticsFile(this);
		clearSemanticsFiles = new ClearSemanticsFiles(this);
		removeSemanticsFile = new RemoveSemanticsFile(this);
	}

	protected ContainsSemanticsFile containsSemanticsFile;
	protected AddSemanticsFile addSemanticsFile;
	protected ClearSemanticsFiles clearSemanticsFiles;
	protected RemoveSemanticsFile removeSemanticsFile;

	protected class ContainsSemanticsFile {
		protected ContainsSemanticsFile(
			InterestGroupPersistenceImpl persistenceImpl) {
			super();

			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSSEMANTICSFILE,
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

	protected class AddSemanticsFile {
		protected AddSemanticsFile(InterestGroupPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO InterestGroups_SemanticsFiles (cid, semanticsURI) VALUES (?, ?)",
					new int[] { Types.BIGINT, Types.VARCHAR });
			_persistenceImpl = persistenceImpl;
		}

		protected void add(long cid, String semanticsURI) {
			if (!_persistenceImpl.containsSemanticsFile.contains(cid,
						semanticsURI)) {
				_sqlUpdate.update(new Object[] { new Long(cid), semanticsURI });
			}
		}

		private SqlUpdate _sqlUpdate;
		private InterestGroupPersistenceImpl _persistenceImpl;
	}

	protected class ClearSemanticsFiles {
		protected ClearSemanticsFiles(
			InterestGroupPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM InterestGroups_SemanticsFiles WHERE cid = ?",
					new int[] { Types.BIGINT });
		}

		protected void clear(long cid) {
			_sqlUpdate.update(new Object[] { new Long(cid) });
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveSemanticsFile {
		protected RemoveSemanticsFile(
			InterestGroupPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM InterestGroups_SemanticsFiles WHERE cid = ? AND semanticsURI = ?",
					new int[] { Types.BIGINT, Types.VARCHAR });
		}

		protected void remove(long cid, String semanticsURI) {
			_sqlUpdate.update(new Object[] { new Long(cid), semanticsURI });
		}

		private SqlUpdate _sqlUpdate;
	}

	private static final String _SQL_GETSEMANTICSFILES = "SELECT {IWEB_SemanticsFile.*} FROM IWEB_SemanticsFile INNER JOIN InterestGroups_SemanticsFiles ON (InterestGroups_SemanticsFiles.semanticsURI = IWEB_SemanticsFile.semanticsURI) WHERE (InterestGroups_SemanticsFiles.cid = ?)";
	private static final String _SQL_GETSEMANTICSFILESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM InterestGroups_SemanticsFiles WHERE cid = ?";
	private static final String _SQL_CONTAINSSEMANTICSFILE = "SELECT COUNT(*) AS COUNT_VALUE FROM InterestGroups_SemanticsFiles WHERE cid = ? AND semanticsURI = ?";
	private static Log _log = LogFactoryUtil.getLog(InterestGroupPersistenceImpl.class);
	private ModelListener[] _listeners = new ModelListener[0];
}