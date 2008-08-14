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

package com.liferay.knowledgebase.service.persistence;

import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.impl.KBArticleImpl;
import com.liferay.knowledgebase.model.impl.KBArticleModelImpl;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bean.InitializingBean;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="KBArticlePersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBArticlePersistenceImpl extends BasePersistenceImpl
	implements KBArticlePersistence, InitializingBean {
	public KBArticle create(long articleId) {
		KBArticle kbArticle = new KBArticleImpl();

		kbArticle.setNew(true);
		kbArticle.setPrimaryKey(articleId);

		String uuid = PortalUUIDUtil.generate();

		kbArticle.setUuid(uuid);

		return kbArticle;
	}

	public KBArticle remove(long articleId)
		throws NoSuchArticleException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KBArticle kbArticle = (KBArticle)session.get(KBArticleImpl.class,
					new Long(articleId));

			if (kbArticle == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No KBArticle exists with the primary key " +
						articleId);
				}

				throw new NoSuchArticleException(
					"No KBArticle exists with the primary key " + articleId);
			}

			return remove(kbArticle);
		}
		catch (NoSuchArticleException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public KBArticle remove(KBArticle kbArticle) throws SystemException {
		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(kbArticle);
			}
		}

		kbArticle = removeImpl(kbArticle);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(kbArticle);
			}
		}

		return kbArticle;
	}

	protected KBArticle removeImpl(KBArticle kbArticle)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			session.delete(kbArticle);

			session.flush();

			return kbArticle;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(KBArticle.class.getName());
		}
	}

	public KBArticle update(KBArticle kbArticle) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(KBArticle kbArticle) method. Use update(KBArticle kbArticle, boolean merge) instead.");
		}

		return update(kbArticle, false);
	}

	public KBArticle update(KBArticle kbArticle, boolean merge)
		throws SystemException {
		boolean isNew = kbArticle.isNew();

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(kbArticle);
				}
				else {
					listener.onBeforeUpdate(kbArticle);
				}
			}
		}

		kbArticle = updateImpl(kbArticle, merge);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(kbArticle);
				}
				else {
					listener.onAfterUpdate(kbArticle);
				}
			}
		}

		return kbArticle;
	}

	public KBArticle updateImpl(
		com.liferay.knowledgebase.model.KBArticle kbArticle, boolean merge)
		throws SystemException {
		if (Validator.isNull(kbArticle.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			kbArticle.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(kbArticle);
			}
			else {
				if (kbArticle.isNew()) {
					session.save(kbArticle);
				}
			}

			session.flush();

			kbArticle.setNew(false);

			return kbArticle;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(KBArticle.class.getName());
		}
	}

	public KBArticle findByPrimaryKey(long articleId)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByPrimaryKey(articleId);

		if (kbArticle == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No KBArticle exists with the primary key " +
					articleId);
			}

			throw new NoSuchArticleException(
				"No KBArticle exists with the primary key " + articleId);
		}

		return kbArticle;
	}

	public KBArticle fetchByPrimaryKey(long articleId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (KBArticle)session.get(KBArticleImpl.class,
				new Long(articleId));
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KBArticle> findByUuid(String uuid) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByUuid";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { uuid };

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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				if (uuid == null) {
					query.append("uuid_ IS NULL");
				}
				else {
					query.append("uuid_ = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				List<KBArticle> list = q.list();

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
			return (List<KBArticle>)result;
		}
	}

	public List<KBArticle> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	public List<KBArticle> findByUuid(String uuid, int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByUuid";
		String[] finderParams = new String[] {
				String.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				uuid,
				
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				if (uuid == null) {
					query.append("uuid_ IS NULL");
				}
				else {
					query.append("uuid_ = ?");
				}

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("title ASC, ");
					query.append("version ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				List<KBArticle> list = (List<KBArticle>)QueryUtil.list(q,
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
			return (List<KBArticle>)result;
		}
	}

	public KBArticle findByUuid_First(String uuid, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		List<KBArticle> list = findByUuid(uuid, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("uuid=" + uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle findByUuid_Last(String uuid, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		int count = countByUuid(uuid);

		List<KBArticle> list = findByUuid(uuid, count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("uuid=" + uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle[] findByUuid_PrevAndNext(long articleId, String uuid,
		OrderByComparator obc) throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(articleId);

		int count = countByUuid(uuid);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

			if (uuid == null) {
				query.append("uuid_ IS NULL");
			}
			else {
				query.append("uuid_ = ?");
			}

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			if (uuid != null) {
				qPos.add(uuid);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					kbArticle);

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = (KBArticle)objArray[0];
			array[1] = (KBArticle)objArray[1];
			array[2] = (KBArticle)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public KBArticle findByUUID_G(String uuid, long groupId)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByUUID_G(uuid, groupId);

		if (kbArticle == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("uuid=" + uuid);

			msg.append(", ");
			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchArticleException(msg.toString());
		}

		return kbArticle;
	}

	public KBArticle fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "fetchByUUID_G";
		String[] finderParams = new String[] {
				String.class.getName(), Long.class.getName()
			};
		Object[] finderArgs = new Object[] { uuid, new Long(groupId) };

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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				if (uuid == null) {
					query.append("uuid_ IS NULL");
				}
				else {
					query.append("uuid_ = ?");
				}

				query.append(" AND ");

				query.append("groupId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<KBArticle> list = q.list();

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				if (list.size() == 0) {
					return null;
				}
				else {
					return list.get(0);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			List<KBArticle> list = (List<KBArticle>)result;

			if (list.size() == 0) {
				return null;
			}
			else {
				return list.get(0);
			}
		}
	}

	public List<KBArticle> findByCompanyId(long companyId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByCompanyId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(companyId) };

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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("companyId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				List<KBArticle> list = q.list();

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
			return (List<KBArticle>)result;
		}
	}

	public List<KBArticle> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	public List<KBArticle> findByCompanyId(long companyId, int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByCompanyId";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(companyId),
				
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("companyId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("title ASC, ");
					query.append("version ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				List<KBArticle> list = (List<KBArticle>)QueryUtil.list(q,
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
			return (List<KBArticle>)result;
		}
	}

	public KBArticle findByCompanyId_First(long companyId, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		List<KBArticle> list = findByCompanyId(companyId, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle findByCompanyId_Last(long companyId, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		int count = countByCompanyId(companyId);

		List<KBArticle> list = findByCompanyId(companyId, count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle[] findByCompanyId_PrevAndNext(long articleId,
		long companyId, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(articleId);

		int count = countByCompanyId(companyId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

			query.append("companyId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					kbArticle);

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = (KBArticle)objArray[0];
			array[1] = (KBArticle)objArray[1];
			array[2] = (KBArticle)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KBArticle> findByGroupId(long groupId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByGroupId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(groupId) };

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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				List<KBArticle> list = q.list();

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
			return (List<KBArticle>)result;
		}
	}

	public List<KBArticle> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	public List<KBArticle> findByGroupId(long groupId, int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByGroupId";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId),
				
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("title ASC, ");
					query.append("version ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				List<KBArticle> list = (List<KBArticle>)QueryUtil.list(q,
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
			return (List<KBArticle>)result;
		}
	}

	public KBArticle findByGroupId_First(long groupId, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		List<KBArticle> list = findByGroupId(groupId, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle findByGroupId_Last(long groupId, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		int count = countByGroupId(groupId);

		List<KBArticle> list = findByGroupId(groupId, count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle[] findByGroupId_PrevAndNext(long articleId, long groupId,
		OrderByComparator obc) throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(articleId);

		int count = countByGroupId(groupId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

			query.append("groupId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					kbArticle);

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = (KBArticle)objArray[0];
			array[1] = (KBArticle)objArray[1];
			array[2] = (KBArticle)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KBArticle> findByParentResourcePrimKey(
		long parentResourcePrimKey) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByParentResourcePrimKey";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(parentResourcePrimKey) };

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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("parentResourcePrimKey = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

				List<KBArticle> list = q.list();

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
			return (List<KBArticle>)result;
		}
	}

	public List<KBArticle> findByParentResourcePrimKey(
		long parentResourcePrimKey, int start, int end)
		throws SystemException {
		return findByParentResourcePrimKey(parentResourcePrimKey, start, end,
			null);
	}

	public List<KBArticle> findByParentResourcePrimKey(
		long parentResourcePrimKey, int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByParentResourcePrimKey";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(parentResourcePrimKey),
				
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("parentResourcePrimKey = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("title ASC, ");
					query.append("version ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

				List<KBArticle> list = (List<KBArticle>)QueryUtil.list(q,
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
			return (List<KBArticle>)result;
		}
	}

	public KBArticle findByParentResourcePrimKey_First(
		long parentResourcePrimKey, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		List<KBArticle> list = findByParentResourcePrimKey(parentResourcePrimKey,
				0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("parentResourcePrimKey=" + parentResourcePrimKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle findByParentResourcePrimKey_Last(
		long parentResourcePrimKey, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		int count = countByParentResourcePrimKey(parentResourcePrimKey);

		List<KBArticle> list = findByParentResourcePrimKey(parentResourcePrimKey,
				count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("parentResourcePrimKey=" + parentResourcePrimKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle[] findByParentResourcePrimKey_PrevAndNext(long articleId,
		long parentResourcePrimKey, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(articleId);

		int count = countByParentResourcePrimKey(parentResourcePrimKey);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

			query.append("parentResourcePrimKey = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(parentResourcePrimKey);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					kbArticle);

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = (KBArticle)objArray[0];
			array[1] = (KBArticle)objArray[1];
			array[2] = (KBArticle)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KBArticle> findByResourcePrimKey(long resourcePrimKey)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByResourcePrimKey";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(resourcePrimKey) };

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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("resourcePrimKey = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				List<KBArticle> list = q.list();

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
			return (List<KBArticle>)result;
		}
	}

	public List<KBArticle> findByResourcePrimKey(long resourcePrimKey,
		int start, int end) throws SystemException {
		return findByResourcePrimKey(resourcePrimKey, start, end, null);
	}

	public List<KBArticle> findByResourcePrimKey(long resourcePrimKey,
		int start, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByResourcePrimKey";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(resourcePrimKey),
				
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("resourcePrimKey = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("title ASC, ");
					query.append("version ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				List<KBArticle> list = (List<KBArticle>)QueryUtil.list(q,
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
			return (List<KBArticle>)result;
		}
	}

	public KBArticle findByResourcePrimKey_First(long resourcePrimKey,
		OrderByComparator obc) throws NoSuchArticleException, SystemException {
		List<KBArticle> list = findByResourcePrimKey(resourcePrimKey, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("resourcePrimKey=" + resourcePrimKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle findByResourcePrimKey_Last(long resourcePrimKey,
		OrderByComparator obc) throws NoSuchArticleException, SystemException {
		int count = countByResourcePrimKey(resourcePrimKey);

		List<KBArticle> list = findByResourcePrimKey(resourcePrimKey,
				count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("resourcePrimKey=" + resourcePrimKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle[] findByResourcePrimKey_PrevAndNext(long articleId,
		long resourcePrimKey, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(articleId);

		int count = countByResourcePrimKey(resourcePrimKey);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

			query.append("resourcePrimKey = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					kbArticle);

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = (KBArticle)objArray[0];
			array[1] = (KBArticle)objArray[1];
			array[2] = (KBArticle)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KBArticle> findByC_D(long companyId, boolean draft)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByC_D";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(companyId), Boolean.valueOf(draft)
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("companyId = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(draft);

				List<KBArticle> list = q.list();

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
			return (List<KBArticle>)result;
		}
	}

	public List<KBArticle> findByC_D(long companyId, boolean draft, int start,
		int end) throws SystemException {
		return findByC_D(companyId, draft, start, end, null);
	}

	public List<KBArticle> findByC_D(long companyId, boolean draft, int start,
		int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByC_D";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(companyId), Boolean.valueOf(draft),
				
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("companyId = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("title ASC, ");
					query.append("version ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(draft);

				List<KBArticle> list = (List<KBArticle>)QueryUtil.list(q,
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
			return (List<KBArticle>)result;
		}
	}

	public KBArticle findByC_D_First(long companyId, boolean draft,
		OrderByComparator obc) throws NoSuchArticleException, SystemException {
		List<KBArticle> list = findByC_D(companyId, draft, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(", ");
			msg.append("draft=" + draft);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle findByC_D_Last(long companyId, boolean draft,
		OrderByComparator obc) throws NoSuchArticleException, SystemException {
		int count = countByC_D(companyId, draft);

		List<KBArticle> list = findByC_D(companyId, draft, count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(", ");
			msg.append("draft=" + draft);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle[] findByC_D_PrevAndNext(long articleId, long companyId,
		boolean draft, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(articleId);

		int count = countByC_D(companyId, draft);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

			query.append("companyId = ?");

			query.append(" AND ");

			query.append("draft = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			qPos.add(draft);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					kbArticle);

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = (KBArticle)objArray[0];
			array[1] = (KBArticle)objArray[1];
			array[2] = (KBArticle)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KBArticle> findByG_H_T(long groupId, boolean head,
		boolean template) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByG_H_T";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId), Boolean.valueOf(head),
				Boolean.valueOf(template)
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				query.append("head = ?");

				query.append(" AND ");

				query.append("template = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(head);

				qPos.add(template);

				List<KBArticle> list = q.list();

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
			return (List<KBArticle>)result;
		}
	}

	public List<KBArticle> findByG_H_T(long groupId, boolean head,
		boolean template, int start, int end) throws SystemException {
		return findByG_H_T(groupId, head, template, start, end, null);
	}

	public List<KBArticle> findByG_H_T(long groupId, boolean head,
		boolean template, int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByG_H_T";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId), Boolean.valueOf(head),
				Boolean.valueOf(template),
				
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				query.append("head = ?");

				query.append(" AND ");

				query.append("template = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("title ASC, ");
					query.append("version ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(head);

				qPos.add(template);

				List<KBArticle> list = (List<KBArticle>)QueryUtil.list(q,
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
			return (List<KBArticle>)result;
		}
	}

	public KBArticle findByG_H_T_First(long groupId, boolean head,
		boolean template, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		List<KBArticle> list = findByG_H_T(groupId, head, template, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(", ");
			msg.append("head=" + head);

			msg.append(", ");
			msg.append("template=" + template);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle findByG_H_T_Last(long groupId, boolean head,
		boolean template, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		int count = countByG_H_T(groupId, head, template);

		List<KBArticle> list = findByG_H_T(groupId, head, template, count - 1,
				count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(", ");
			msg.append("head=" + head);

			msg.append(", ");
			msg.append("template=" + template);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle[] findByG_H_T_PrevAndNext(long articleId, long groupId,
		boolean head, boolean template, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(articleId);

		int count = countByG_H_T(groupId, head, template);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

			query.append("groupId = ?");

			query.append(" AND ");

			query.append("head = ?");

			query.append(" AND ");

			query.append("template = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(head);

			qPos.add(template);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					kbArticle);

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = (KBArticle)objArray[0];
			array[1] = (KBArticle)objArray[1];
			array[2] = (KBArticle)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KBArticle> findByG_H_T_D(long groupId, boolean head,
		boolean template, boolean draft) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByG_H_T_D";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId), Boolean.valueOf(head),
				Boolean.valueOf(template), Boolean.valueOf(draft)
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				query.append("head = ?");

				query.append(" AND ");

				query.append("template = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(head);

				qPos.add(template);

				qPos.add(draft);

				List<KBArticle> list = q.list();

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
			return (List<KBArticle>)result;
		}
	}

	public List<KBArticle> findByG_H_T_D(long groupId, boolean head,
		boolean template, boolean draft, int start, int end)
		throws SystemException {
		return findByG_H_T_D(groupId, head, template, draft, start, end, null);
	}

	public List<KBArticle> findByG_H_T_D(long groupId, boolean head,
		boolean template, boolean draft, int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByG_H_T_D";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(), Boolean.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId), Boolean.valueOf(head),
				Boolean.valueOf(template), Boolean.valueOf(draft),
				
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				query.append("head = ?");

				query.append(" AND ");

				query.append("template = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("title ASC, ");
					query.append("version ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(head);

				qPos.add(template);

				qPos.add(draft);

				List<KBArticle> list = (List<KBArticle>)QueryUtil.list(q,
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
			return (List<KBArticle>)result;
		}
	}

	public KBArticle findByG_H_T_D_First(long groupId, boolean head,
		boolean template, boolean draft, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		List<KBArticle> list = findByG_H_T_D(groupId, head, template, draft, 0,
				1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(", ");
			msg.append("head=" + head);

			msg.append(", ");
			msg.append("template=" + template);

			msg.append(", ");
			msg.append("draft=" + draft);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle findByG_H_T_D_Last(long groupId, boolean head,
		boolean template, boolean draft, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		int count = countByG_H_T_D(groupId, head, template, draft);

		List<KBArticle> list = findByG_H_T_D(groupId, head, template, draft,
				count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(", ");
			msg.append("head=" + head);

			msg.append(", ");
			msg.append("template=" + template);

			msg.append(", ");
			msg.append("draft=" + draft);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle[] findByG_H_T_D_PrevAndNext(long articleId, long groupId,
		boolean head, boolean template, boolean draft, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(articleId);

		int count = countByG_H_T_D(groupId, head, template, draft);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

			query.append("groupId = ?");

			query.append(" AND ");

			query.append("head = ?");

			query.append(" AND ");

			query.append("template = ?");

			query.append(" AND ");

			query.append("draft = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(head);

			qPos.add(template);

			qPos.add(draft);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					kbArticle);

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = (KBArticle)objArray[0];
			array[1] = (KBArticle)objArray[1];
			array[2] = (KBArticle)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KBArticle> findByG_T(long groupId, String title)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByG_T";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName()
			};
		Object[] finderArgs = new Object[] { new Long(groupId), title };

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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				if (title == null) {
					query.append("title IS NULL");
				}
				else {
					query.append("title = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (title != null) {
					qPos.add(title);
				}

				List<KBArticle> list = q.list();

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
			return (List<KBArticle>)result;
		}
	}

	public List<KBArticle> findByG_T(long groupId, String title, int start,
		int end) throws SystemException {
		return findByG_T(groupId, title, start, end, null);
	}

	public List<KBArticle> findByG_T(long groupId, String title, int start,
		int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByG_T";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId),
				
				title,
				
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				if (title == null) {
					query.append("title IS NULL");
				}
				else {
					query.append("title = ?");
				}

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("title ASC, ");
					query.append("version ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (title != null) {
					qPos.add(title);
				}

				List<KBArticle> list = (List<KBArticle>)QueryUtil.list(q,
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
			return (List<KBArticle>)result;
		}
	}

	public KBArticle findByG_T_First(long groupId, String title,
		OrderByComparator obc) throws NoSuchArticleException, SystemException {
		List<KBArticle> list = findByG_T(groupId, title, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(", ");
			msg.append("title=" + title);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle findByG_T_Last(long groupId, String title,
		OrderByComparator obc) throws NoSuchArticleException, SystemException {
		int count = countByG_T(groupId, title);

		List<KBArticle> list = findByG_T(groupId, title, count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(", ");
			msg.append("title=" + title);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle[] findByG_T_PrevAndNext(long articleId, long groupId,
		String title, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(articleId);

		int count = countByG_T(groupId, title);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

			query.append("groupId = ?");

			query.append(" AND ");

			if (title == null) {
				query.append("title IS NULL");
			}
			else {
				query.append("title = ?");
			}

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (title != null) {
				qPos.add(title);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					kbArticle);

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = (KBArticle)objArray[0];
			array[1] = (KBArticle)objArray[1];
			array[2] = (KBArticle)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public KBArticle findByG_T_V(long groupId, String title, double version)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByG_T_V(groupId, title, version);

		if (kbArticle == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(", ");
			msg.append("title=" + title);

			msg.append(", ");
			msg.append("version=" + version);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchArticleException(msg.toString());
		}

		return kbArticle;
	}

	public KBArticle fetchByG_T_V(long groupId, String title, double version)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "fetchByG_T_V";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName(),
				Double.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId),
				
				title, new Double(version)
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				if (title == null) {
					query.append("title IS NULL");
				}
				else {
					query.append("title = ?");
				}

				query.append(" AND ");

				query.append("version = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (title != null) {
					qPos.add(title);
				}

				qPos.add(version);

				List<KBArticle> list = q.list();

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				if (list.size() == 0) {
					return null;
				}
				else {
					return list.get(0);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			List<KBArticle> list = (List<KBArticle>)result;

			if (list.size() == 0) {
				return null;
			}
			else {
				return list.get(0);
			}
		}
	}

	public KBArticle findByG_T_V_D(long groupId, String title, double version,
		boolean draft) throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByG_T_V_D(groupId, title, version, draft);

		if (kbArticle == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(", ");
			msg.append("title=" + title);

			msg.append(", ");
			msg.append("version=" + version);

			msg.append(", ");
			msg.append("draft=" + draft);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchArticleException(msg.toString());
		}

		return kbArticle;
	}

	public KBArticle fetchByG_T_V_D(long groupId, String title, double version,
		boolean draft) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "fetchByG_T_V_D";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName(),
				Double.class.getName(), Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId),
				
				title, new Double(version), Boolean.valueOf(draft)
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				if (title == null) {
					query.append("title IS NULL");
				}
				else {
					query.append("title = ?");
				}

				query.append(" AND ");

				query.append("version = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (title != null) {
					qPos.add(title);
				}

				qPos.add(version);

				qPos.add(draft);

				List<KBArticle> list = q.list();

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				if (list.size() == 0) {
					return null;
				}
				else {
					return list.get(0);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			List<KBArticle> list = (List<KBArticle>)result;

			if (list.size() == 0) {
				return null;
			}
			else {
				return list.get(0);
			}
		}
	}

	public List<KBArticle> findByG_T_H(long groupId, String title, boolean head)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByG_T_H";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId),
				
				title, Boolean.valueOf(head)
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				if (title == null) {
					query.append("title IS NULL");
				}
				else {
					query.append("title = ?");
				}

				query.append(" AND ");

				query.append("head = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (title != null) {
					qPos.add(title);
				}

				qPos.add(head);

				List<KBArticle> list = q.list();

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
			return (List<KBArticle>)result;
		}
	}

	public List<KBArticle> findByG_T_H(long groupId, String title,
		boolean head, int start, int end) throws SystemException {
		return findByG_T_H(groupId, title, head, start, end, null);
	}

	public List<KBArticle> findByG_T_H(long groupId, String title,
		boolean head, int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByG_T_H";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId),
				
				title, Boolean.valueOf(head),
				
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				if (title == null) {
					query.append("title IS NULL");
				}
				else {
					query.append("title = ?");
				}

				query.append(" AND ");

				query.append("head = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("title ASC, ");
					query.append("version ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (title != null) {
					qPos.add(title);
				}

				qPos.add(head);

				List<KBArticle> list = (List<KBArticle>)QueryUtil.list(q,
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
			return (List<KBArticle>)result;
		}
	}

	public KBArticle findByG_T_H_First(long groupId, String title,
		boolean head, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		List<KBArticle> list = findByG_T_H(groupId, title, head, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(", ");
			msg.append("title=" + title);

			msg.append(", ");
			msg.append("head=" + head);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle findByG_T_H_Last(long groupId, String title, boolean head,
		OrderByComparator obc) throws NoSuchArticleException, SystemException {
		int count = countByG_T_H(groupId, title, head);

		List<KBArticle> list = findByG_T_H(groupId, title, head, count - 1,
				count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(", ");
			msg.append("title=" + title);

			msg.append(", ");
			msg.append("head=" + head);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle[] findByG_T_H_PrevAndNext(long articleId, long groupId,
		String title, boolean head, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(articleId);

		int count = countByG_T_H(groupId, title, head);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

			query.append("groupId = ?");

			query.append(" AND ");

			if (title == null) {
				query.append("title IS NULL");
			}
			else {
				query.append("title = ?");
			}

			query.append(" AND ");

			query.append("head = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (title != null) {
				qPos.add(title);
			}

			qPos.add(head);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					kbArticle);

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = (KBArticle)objArray[0];
			array[1] = (KBArticle)objArray[1];
			array[2] = (KBArticle)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KBArticle> findByG_T_H_D(long groupId, String title,
		boolean head, boolean draft) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByG_T_H_D";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId),
				
				title, Boolean.valueOf(head), Boolean.valueOf(draft)
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				if (title == null) {
					query.append("title IS NULL");
				}
				else {
					query.append("title = ?");
				}

				query.append(" AND ");

				query.append("head = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (title != null) {
					qPos.add(title);
				}

				qPos.add(head);

				qPos.add(draft);

				List<KBArticle> list = q.list();

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
			return (List<KBArticle>)result;
		}
	}

	public List<KBArticle> findByG_T_H_D(long groupId, String title,
		boolean head, boolean draft, int start, int end)
		throws SystemException {
		return findByG_T_H_D(groupId, title, head, draft, start, end, null);
	}

	public List<KBArticle> findByG_T_H_D(long groupId, String title,
		boolean head, boolean draft, int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByG_T_H_D";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId),
				
				title, Boolean.valueOf(head), Boolean.valueOf(draft),
				
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				if (title == null) {
					query.append("title IS NULL");
				}
				else {
					query.append("title = ?");
				}

				query.append(" AND ");

				query.append("head = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("title ASC, ");
					query.append("version ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (title != null) {
					qPos.add(title);
				}

				qPos.add(head);

				qPos.add(draft);

				List<KBArticle> list = (List<KBArticle>)QueryUtil.list(q,
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
			return (List<KBArticle>)result;
		}
	}

	public KBArticle findByG_T_H_D_First(long groupId, String title,
		boolean head, boolean draft, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		List<KBArticle> list = findByG_T_H_D(groupId, title, head, draft, 0, 1,
				obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(", ");
			msg.append("title=" + title);

			msg.append(", ");
			msg.append("head=" + head);

			msg.append(", ");
			msg.append("draft=" + draft);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle findByG_T_H_D_Last(long groupId, String title,
		boolean head, boolean draft, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		int count = countByG_T_H_D(groupId, title, head, draft);

		List<KBArticle> list = findByG_T_H_D(groupId, title, head, draft,
				count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(", ");
			msg.append("title=" + title);

			msg.append(", ");
			msg.append("head=" + head);

			msg.append(", ");
			msg.append("draft=" + draft);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle[] findByG_T_H_D_PrevAndNext(long articleId, long groupId,
		String title, boolean head, boolean draft, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(articleId);

		int count = countByG_T_H_D(groupId, title, head, draft);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

			query.append("groupId = ?");

			query.append(" AND ");

			if (title == null) {
				query.append("title IS NULL");
			}
			else {
				query.append("title = ?");
			}

			query.append(" AND ");

			query.append("head = ?");

			query.append(" AND ");

			query.append("draft = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (title != null) {
				qPos.add(title);
			}

			qPos.add(head);

			qPos.add(draft);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					kbArticle);

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = (KBArticle)objArray[0];
			array[1] = (KBArticle)objArray[1];
			array[2] = (KBArticle)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KBArticle> findByP_D(long parentResourcePrimKey, boolean draft)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByP_D";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(parentResourcePrimKey), Boolean.valueOf(draft)
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("parentResourcePrimKey = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

				qPos.add(draft);

				List<KBArticle> list = q.list();

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
			return (List<KBArticle>)result;
		}
	}

	public List<KBArticle> findByP_D(long parentResourcePrimKey, boolean draft,
		int start, int end) throws SystemException {
		return findByP_D(parentResourcePrimKey, draft, start, end, null);
	}

	public List<KBArticle> findByP_D(long parentResourcePrimKey, boolean draft,
		int start, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByP_D";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(parentResourcePrimKey), Boolean.valueOf(draft),
				
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("parentResourcePrimKey = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("title ASC, ");
					query.append("version ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

				qPos.add(draft);

				List<KBArticle> list = (List<KBArticle>)QueryUtil.list(q,
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
			return (List<KBArticle>)result;
		}
	}

	public KBArticle findByP_D_First(long parentResourcePrimKey, boolean draft,
		OrderByComparator obc) throws NoSuchArticleException, SystemException {
		List<KBArticle> list = findByP_D(parentResourcePrimKey, draft, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("parentResourcePrimKey=" + parentResourcePrimKey);

			msg.append(", ");
			msg.append("draft=" + draft);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle findByP_D_Last(long parentResourcePrimKey, boolean draft,
		OrderByComparator obc) throws NoSuchArticleException, SystemException {
		int count = countByP_D(parentResourcePrimKey, draft);

		List<KBArticle> list = findByP_D(parentResourcePrimKey, draft,
				count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("parentResourcePrimKey=" + parentResourcePrimKey);

			msg.append(", ");
			msg.append("draft=" + draft);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle[] findByP_D_PrevAndNext(long articleId,
		long parentResourcePrimKey, boolean draft, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(articleId);

		int count = countByP_D(parentResourcePrimKey, draft);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

			query.append("parentResourcePrimKey = ?");

			query.append(" AND ");

			query.append("draft = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(parentResourcePrimKey);

			qPos.add(draft);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					kbArticle);

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = (KBArticle)objArray[0];
			array[1] = (KBArticle)objArray[1];
			array[2] = (KBArticle)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KBArticle> findByP_H(long parentResourcePrimKey, boolean head)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByP_H";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(parentResourcePrimKey), Boolean.valueOf(head)
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("parentResourcePrimKey = ?");

				query.append(" AND ");

				query.append("head = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

				qPos.add(head);

				List<KBArticle> list = q.list();

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
			return (List<KBArticle>)result;
		}
	}

	public List<KBArticle> findByP_H(long parentResourcePrimKey, boolean head,
		int start, int end) throws SystemException {
		return findByP_H(parentResourcePrimKey, head, start, end, null);
	}

	public List<KBArticle> findByP_H(long parentResourcePrimKey, boolean head,
		int start, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByP_H";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(parentResourcePrimKey), Boolean.valueOf(head),
				
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("parentResourcePrimKey = ?");

				query.append(" AND ");

				query.append("head = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("title ASC, ");
					query.append("version ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

				qPos.add(head);

				List<KBArticle> list = (List<KBArticle>)QueryUtil.list(q,
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
			return (List<KBArticle>)result;
		}
	}

	public KBArticle findByP_H_First(long parentResourcePrimKey, boolean head,
		OrderByComparator obc) throws NoSuchArticleException, SystemException {
		List<KBArticle> list = findByP_H(parentResourcePrimKey, head, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("parentResourcePrimKey=" + parentResourcePrimKey);

			msg.append(", ");
			msg.append("head=" + head);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle findByP_H_Last(long parentResourcePrimKey, boolean head,
		OrderByComparator obc) throws NoSuchArticleException, SystemException {
		int count = countByP_H(parentResourcePrimKey, head);

		List<KBArticle> list = findByP_H(parentResourcePrimKey, head,
				count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("parentResourcePrimKey=" + parentResourcePrimKey);

			msg.append(", ");
			msg.append("head=" + head);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle[] findByP_H_PrevAndNext(long articleId,
		long parentResourcePrimKey, boolean head, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(articleId);

		int count = countByP_H(parentResourcePrimKey, head);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

			query.append("parentResourcePrimKey = ?");

			query.append(" AND ");

			query.append("head = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(parentResourcePrimKey);

			qPos.add(head);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					kbArticle);

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = (KBArticle)objArray[0];
			array[1] = (KBArticle)objArray[1];
			array[2] = (KBArticle)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KBArticle> findByP_H_D(long parentResourcePrimKey,
		boolean head, boolean draft) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByP_H_D";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(parentResourcePrimKey), Boolean.valueOf(head),
				Boolean.valueOf(draft)
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("parentResourcePrimKey = ?");

				query.append(" AND ");

				query.append("head = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

				qPos.add(head);

				qPos.add(draft);

				List<KBArticle> list = q.list();

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
			return (List<KBArticle>)result;
		}
	}

	public List<KBArticle> findByP_H_D(long parentResourcePrimKey,
		boolean head, boolean draft, int start, int end)
		throws SystemException {
		return findByP_H_D(parentResourcePrimKey, head, draft, start, end, null);
	}

	public List<KBArticle> findByP_H_D(long parentResourcePrimKey,
		boolean head, boolean draft, int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByP_H_D";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(parentResourcePrimKey), Boolean.valueOf(head),
				Boolean.valueOf(draft),
				
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("parentResourcePrimKey = ?");

				query.append(" AND ");

				query.append("head = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("title ASC, ");
					query.append("version ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

				qPos.add(head);

				qPos.add(draft);

				List<KBArticle> list = (List<KBArticle>)QueryUtil.list(q,
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
			return (List<KBArticle>)result;
		}
	}

	public KBArticle findByP_H_D_First(long parentResourcePrimKey,
		boolean head, boolean draft, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		List<KBArticle> list = findByP_H_D(parentResourcePrimKey, head, draft,
				0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("parentResourcePrimKey=" + parentResourcePrimKey);

			msg.append(", ");
			msg.append("head=" + head);

			msg.append(", ");
			msg.append("draft=" + draft);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle findByP_H_D_Last(long parentResourcePrimKey, boolean head,
		boolean draft, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		int count = countByP_H_D(parentResourcePrimKey, head, draft);

		List<KBArticle> list = findByP_H_D(parentResourcePrimKey, head, draft,
				count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("parentResourcePrimKey=" + parentResourcePrimKey);

			msg.append(", ");
			msg.append("head=" + head);

			msg.append(", ");
			msg.append("draft=" + draft);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle[] findByP_H_D_PrevAndNext(long articleId,
		long parentResourcePrimKey, boolean head, boolean draft,
		OrderByComparator obc) throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(articleId);

		int count = countByP_H_D(parentResourcePrimKey, head, draft);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

			query.append("parentResourcePrimKey = ?");

			query.append(" AND ");

			query.append("head = ?");

			query.append(" AND ");

			query.append("draft = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(parentResourcePrimKey);

			qPos.add(head);

			qPos.add(draft);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					kbArticle);

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = (KBArticle)objArray[0];
			array[1] = (KBArticle)objArray[1];
			array[2] = (KBArticle)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KBArticle> findByR_D(long resourcePrimKey, boolean draft)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByR_D";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(resourcePrimKey), Boolean.valueOf(draft)
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("resourcePrimKey = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(draft);

				List<KBArticle> list = q.list();

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
			return (List<KBArticle>)result;
		}
	}

	public List<KBArticle> findByR_D(long resourcePrimKey, boolean draft,
		int start, int end) throws SystemException {
		return findByR_D(resourcePrimKey, draft, start, end, null);
	}

	public List<KBArticle> findByR_D(long resourcePrimKey, boolean draft,
		int start, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByR_D";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(resourcePrimKey), Boolean.valueOf(draft),
				
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("resourcePrimKey = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("title ASC, ");
					query.append("version ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(draft);

				List<KBArticle> list = (List<KBArticle>)QueryUtil.list(q,
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
			return (List<KBArticle>)result;
		}
	}

	public KBArticle findByR_D_First(long resourcePrimKey, boolean draft,
		OrderByComparator obc) throws NoSuchArticleException, SystemException {
		List<KBArticle> list = findByR_D(resourcePrimKey, draft, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("resourcePrimKey=" + resourcePrimKey);

			msg.append(", ");
			msg.append("draft=" + draft);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle findByR_D_Last(long resourcePrimKey, boolean draft,
		OrderByComparator obc) throws NoSuchArticleException, SystemException {
		int count = countByR_D(resourcePrimKey, draft);

		List<KBArticle> list = findByR_D(resourcePrimKey, draft, count - 1,
				count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("resourcePrimKey=" + resourcePrimKey);

			msg.append(", ");
			msg.append("draft=" + draft);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle[] findByR_D_PrevAndNext(long articleId,
		long resourcePrimKey, boolean draft, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(articleId);

		int count = countByR_D(resourcePrimKey, draft);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

			query.append("resourcePrimKey = ?");

			query.append(" AND ");

			query.append("draft = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			qPos.add(draft);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					kbArticle);

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = (KBArticle)objArray[0];
			array[1] = (KBArticle)objArray[1];
			array[2] = (KBArticle)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KBArticle> findByR_H(long resourcePrimKey, boolean head)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByR_H";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(resourcePrimKey), Boolean.valueOf(head)
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("resourcePrimKey = ?");

				query.append(" AND ");

				query.append("head = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(head);

				List<KBArticle> list = q.list();

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
			return (List<KBArticle>)result;
		}
	}

	public List<KBArticle> findByR_H(long resourcePrimKey, boolean head,
		int start, int end) throws SystemException {
		return findByR_H(resourcePrimKey, head, start, end, null);
	}

	public List<KBArticle> findByR_H(long resourcePrimKey, boolean head,
		int start, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByR_H";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(resourcePrimKey), Boolean.valueOf(head),
				
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("resourcePrimKey = ?");

				query.append(" AND ");

				query.append("head = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("title ASC, ");
					query.append("version ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(head);

				List<KBArticle> list = (List<KBArticle>)QueryUtil.list(q,
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
			return (List<KBArticle>)result;
		}
	}

	public KBArticle findByR_H_First(long resourcePrimKey, boolean head,
		OrderByComparator obc) throws NoSuchArticleException, SystemException {
		List<KBArticle> list = findByR_H(resourcePrimKey, head, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("resourcePrimKey=" + resourcePrimKey);

			msg.append(", ");
			msg.append("head=" + head);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle findByR_H_Last(long resourcePrimKey, boolean head,
		OrderByComparator obc) throws NoSuchArticleException, SystemException {
		int count = countByR_H(resourcePrimKey, head);

		List<KBArticle> list = findByR_H(resourcePrimKey, head, count - 1,
				count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("resourcePrimKey=" + resourcePrimKey);

			msg.append(", ");
			msg.append("head=" + head);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle[] findByR_H_PrevAndNext(long articleId,
		long resourcePrimKey, boolean head, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(articleId);

		int count = countByR_H(resourcePrimKey, head);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

			query.append("resourcePrimKey = ?");

			query.append(" AND ");

			query.append("head = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			qPos.add(head);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					kbArticle);

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = (KBArticle)objArray[0];
			array[1] = (KBArticle)objArray[1];
			array[2] = (KBArticle)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KBArticle> findByR_H_D(long resourcePrimKey, boolean head,
		boolean draft) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByR_H_D";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(resourcePrimKey), Boolean.valueOf(head),
				Boolean.valueOf(draft)
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("resourcePrimKey = ?");

				query.append(" AND ");

				query.append("head = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(head);

				qPos.add(draft);

				List<KBArticle> list = q.list();

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
			return (List<KBArticle>)result;
		}
	}

	public List<KBArticle> findByR_H_D(long resourcePrimKey, boolean head,
		boolean draft, int start, int end) throws SystemException {
		return findByR_H_D(resourcePrimKey, head, draft, start, end, null);
	}

	public List<KBArticle> findByR_H_D(long resourcePrimKey, boolean head,
		boolean draft, int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "findByR_H_D";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(resourcePrimKey), Boolean.valueOf(head),
				Boolean.valueOf(draft),
				
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("resourcePrimKey = ?");

				query.append(" AND ");

				query.append("head = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("title ASC, ");
					query.append("version ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(head);

				qPos.add(draft);

				List<KBArticle> list = (List<KBArticle>)QueryUtil.list(q,
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
			return (List<KBArticle>)result;
		}
	}

	public KBArticle findByR_H_D_First(long resourcePrimKey, boolean head,
		boolean draft, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		List<KBArticle> list = findByR_H_D(resourcePrimKey, head, draft, 0, 1,
				obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("resourcePrimKey=" + resourcePrimKey);

			msg.append(", ");
			msg.append("head=" + head);

			msg.append(", ");
			msg.append("draft=" + draft);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle findByR_H_D_Last(long resourcePrimKey, boolean head,
		boolean draft, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		int count = countByR_H_D(resourcePrimKey, head, draft);

		List<KBArticle> list = findByR_H_D(resourcePrimKey, head, draft,
				count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("resourcePrimKey=" + resourcePrimKey);

			msg.append(", ");
			msg.append("head=" + head);

			msg.append(", ");
			msg.append("draft=" + draft);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchArticleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public KBArticle[] findByR_H_D_PrevAndNext(long articleId,
		long resourcePrimKey, boolean head, boolean draft, OrderByComparator obc)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByPrimaryKey(articleId);

		int count = countByR_H_D(resourcePrimKey, head, draft);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

			query.append("resourcePrimKey = ?");

			query.append(" AND ");

			query.append("head = ?");

			query.append(" AND ");

			query.append("draft = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			qPos.add(head);

			qPos.add(draft);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					kbArticle);

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = (KBArticle)objArray[0];
			array[1] = (KBArticle)objArray[1];
			array[2] = (KBArticle)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public KBArticle findByR_V(long resourcePrimKey, double version)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByR_V(resourcePrimKey, version);

		if (kbArticle == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("resourcePrimKey=" + resourcePrimKey);

			msg.append(", ");
			msg.append("version=" + version);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchArticleException(msg.toString());
		}

		return kbArticle;
	}

	public KBArticle fetchByR_V(long resourcePrimKey, double version)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "fetchByR_V";
		String[] finderParams = new String[] {
				Long.class.getName(), Double.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(resourcePrimKey), new Double(version)
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("resourcePrimKey = ?");

				query.append(" AND ");

				query.append("version = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(version);

				List<KBArticle> list = q.list();

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				if (list.size() == 0) {
					return null;
				}
				else {
					return list.get(0);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			List<KBArticle> list = (List<KBArticle>)result;

			if (list.size() == 0) {
				return null;
			}
			else {
				return list.get(0);
			}
		}
	}

	public KBArticle findByR_V_D(long resourcePrimKey, double version,
		boolean draft) throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = fetchByR_V_D(resourcePrimKey, version, draft);

		if (kbArticle == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticle exists with the key {");

			msg.append("resourcePrimKey=" + resourcePrimKey);

			msg.append(", ");
			msg.append("version=" + version);

			msg.append(", ");
			msg.append("draft=" + draft);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchArticleException(msg.toString());
		}

		return kbArticle;
	}

	public KBArticle fetchByR_V_D(long resourcePrimKey, double version,
		boolean draft) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "fetchByR_V_D";
		String[] finderParams = new String[] {
				Long.class.getName(), Double.class.getName(),
				Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(resourcePrimKey), new Double(version),
				Boolean.valueOf(draft)
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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("resourcePrimKey = ?");

				query.append(" AND ");

				query.append("version = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("title ASC, ");
				query.append("version ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(version);

				qPos.add(draft);

				List<KBArticle> list = q.list();

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				if (list.size() == 0) {
					return null;
				}
				else {
					return list.get(0);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			List<KBArticle> list = (List<KBArticle>)result;

			if (list.size() == 0) {
				return null;
			}
			else {
				return list.get(0);
			}
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

	public List<KBArticle> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<KBArticle> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<KBArticle> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
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

				query.append("FROM com.liferay.knowledgebase.model.KBArticle ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("title ASC, ");
					query.append("version ASC");
				}

				Query q = session.createQuery(query.toString());

				List<KBArticle> list = (List<KBArticle>)QueryUtil.list(q,
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
			return (List<KBArticle>)result;
		}
	}

	public void removeByUuid(String uuid) throws SystemException {
		for (KBArticle kbArticle : findByUuid(uuid)) {
			remove(kbArticle);
		}
	}

	public void removeByUUID_G(String uuid, long groupId)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByUUID_G(uuid, groupId);

		remove(kbArticle);
	}

	public void removeByCompanyId(long companyId) throws SystemException {
		for (KBArticle kbArticle : findByCompanyId(companyId)) {
			remove(kbArticle);
		}
	}

	public void removeByGroupId(long groupId) throws SystemException {
		for (KBArticle kbArticle : findByGroupId(groupId)) {
			remove(kbArticle);
		}
	}

	public void removeByParentResourcePrimKey(long parentResourcePrimKey)
		throws SystemException {
		for (KBArticle kbArticle : findByParentResourcePrimKey(
				parentResourcePrimKey)) {
			remove(kbArticle);
		}
	}

	public void removeByResourcePrimKey(long resourcePrimKey)
		throws SystemException {
		for (KBArticle kbArticle : findByResourcePrimKey(resourcePrimKey)) {
			remove(kbArticle);
		}
	}

	public void removeByC_D(long companyId, boolean draft)
		throws SystemException {
		for (KBArticle kbArticle : findByC_D(companyId, draft)) {
			remove(kbArticle);
		}
	}

	public void removeByG_H_T(long groupId, boolean head, boolean template)
		throws SystemException {
		for (KBArticle kbArticle : findByG_H_T(groupId, head, template)) {
			remove(kbArticle);
		}
	}

	public void removeByG_H_T_D(long groupId, boolean head, boolean template,
		boolean draft) throws SystemException {
		for (KBArticle kbArticle : findByG_H_T_D(groupId, head, template, draft)) {
			remove(kbArticle);
		}
	}

	public void removeByG_T(long groupId, String title)
		throws SystemException {
		for (KBArticle kbArticle : findByG_T(groupId, title)) {
			remove(kbArticle);
		}
	}

	public void removeByG_T_V(long groupId, String title, double version)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByG_T_V(groupId, title, version);

		remove(kbArticle);
	}

	public void removeByG_T_V_D(long groupId, String title, double version,
		boolean draft) throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByG_T_V_D(groupId, title, version, draft);

		remove(kbArticle);
	}

	public void removeByG_T_H(long groupId, String title, boolean head)
		throws SystemException {
		for (KBArticle kbArticle : findByG_T_H(groupId, title, head)) {
			remove(kbArticle);
		}
	}

	public void removeByG_T_H_D(long groupId, String title, boolean head,
		boolean draft) throws SystemException {
		for (KBArticle kbArticle : findByG_T_H_D(groupId, title, head, draft)) {
			remove(kbArticle);
		}
	}

	public void removeByP_D(long parentResourcePrimKey, boolean draft)
		throws SystemException {
		for (KBArticle kbArticle : findByP_D(parentResourcePrimKey, draft)) {
			remove(kbArticle);
		}
	}

	public void removeByP_H(long parentResourcePrimKey, boolean head)
		throws SystemException {
		for (KBArticle kbArticle : findByP_H(parentResourcePrimKey, head)) {
			remove(kbArticle);
		}
	}

	public void removeByP_H_D(long parentResourcePrimKey, boolean head,
		boolean draft) throws SystemException {
		for (KBArticle kbArticle : findByP_H_D(parentResourcePrimKey, head,
				draft)) {
			remove(kbArticle);
		}
	}

	public void removeByR_D(long resourcePrimKey, boolean draft)
		throws SystemException {
		for (KBArticle kbArticle : findByR_D(resourcePrimKey, draft)) {
			remove(kbArticle);
		}
	}

	public void removeByR_H(long resourcePrimKey, boolean head)
		throws SystemException {
		for (KBArticle kbArticle : findByR_H(resourcePrimKey, head)) {
			remove(kbArticle);
		}
	}

	public void removeByR_H_D(long resourcePrimKey, boolean head, boolean draft)
		throws SystemException {
		for (KBArticle kbArticle : findByR_H_D(resourcePrimKey, head, draft)) {
			remove(kbArticle);
		}
	}

	public void removeByR_V(long resourcePrimKey, double version)
		throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByR_V(resourcePrimKey, version);

		remove(kbArticle);
	}

	public void removeByR_V_D(long resourcePrimKey, double version,
		boolean draft) throws NoSuchArticleException, SystemException {
		KBArticle kbArticle = findByR_V_D(resourcePrimKey, version, draft);

		remove(kbArticle);
	}

	public void removeAll() throws SystemException {
		for (KBArticle kbArticle : findAll()) {
			remove(kbArticle);
		}
	}

	public int countByUuid(String uuid) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByUuid";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { uuid };

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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				if (uuid == null) {
					query.append("uuid_ IS NULL");
				}
				else {
					query.append("uuid_ = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

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

	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByUUID_G";
		String[] finderParams = new String[] {
				String.class.getName(), Long.class.getName()
			};
		Object[] finderArgs = new Object[] { uuid, new Long(groupId) };

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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				if (uuid == null) {
					query.append("uuid_ IS NULL");
				}
				else {
					query.append("uuid_ = ?");
				}

				query.append(" AND ");

				query.append("groupId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

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

	public int countByCompanyId(long companyId) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByCompanyId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(companyId) };

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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("companyId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

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

	public int countByGroupId(long groupId) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByGroupId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(groupId) };

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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	public int countByParentResourcePrimKey(long parentResourcePrimKey)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByParentResourcePrimKey";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(parentResourcePrimKey) };

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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("parentResourcePrimKey = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

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

	public int countByResourcePrimKey(long resourcePrimKey)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByResourcePrimKey";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(resourcePrimKey) };

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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("resourcePrimKey = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

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

	public int countByC_D(long companyId, boolean draft)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByC_D";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(companyId), Boolean.valueOf(draft)
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

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("companyId = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(draft);

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

	public int countByG_H_T(long groupId, boolean head, boolean template)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByG_H_T";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId), Boolean.valueOf(head),
				Boolean.valueOf(template)
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

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				query.append("head = ?");

				query.append(" AND ");

				query.append("template = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(head);

				qPos.add(template);

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

	public int countByG_H_T_D(long groupId, boolean head, boolean template,
		boolean draft) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByG_H_T_D";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId), Boolean.valueOf(head),
				Boolean.valueOf(template), Boolean.valueOf(draft)
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

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				query.append("head = ?");

				query.append(" AND ");

				query.append("template = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(head);

				qPos.add(template);

				qPos.add(draft);

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

	public int countByG_T(long groupId, String title) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByG_T";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName()
			};
		Object[] finderArgs = new Object[] { new Long(groupId), title };

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
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				if (title == null) {
					query.append("title IS NULL");
				}
				else {
					query.append("title = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (title != null) {
					qPos.add(title);
				}

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

	public int countByG_T_V(long groupId, String title, double version)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByG_T_V";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName(),
				Double.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId),
				
				title, new Double(version)
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

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				if (title == null) {
					query.append("title IS NULL");
				}
				else {
					query.append("title = ?");
				}

				query.append(" AND ");

				query.append("version = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (title != null) {
					qPos.add(title);
				}

				qPos.add(version);

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

	public int countByG_T_V_D(long groupId, String title, double version,
		boolean draft) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByG_T_V_D";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName(),
				Double.class.getName(), Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId),
				
				title, new Double(version), Boolean.valueOf(draft)
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

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				if (title == null) {
					query.append("title IS NULL");
				}
				else {
					query.append("title = ?");
				}

				query.append(" AND ");

				query.append("version = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (title != null) {
					qPos.add(title);
				}

				qPos.add(version);

				qPos.add(draft);

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

	public int countByG_T_H(long groupId, String title, boolean head)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByG_T_H";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId),
				
				title, Boolean.valueOf(head)
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

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				if (title == null) {
					query.append("title IS NULL");
				}
				else {
					query.append("title = ?");
				}

				query.append(" AND ");

				query.append("head = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (title != null) {
					qPos.add(title);
				}

				qPos.add(head);

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

	public int countByG_T_H_D(long groupId, String title, boolean head,
		boolean draft) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByG_T_H_D";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(), Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId),
				
				title, Boolean.valueOf(head), Boolean.valueOf(draft)
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

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				if (title == null) {
					query.append("title IS NULL");
				}
				else {
					query.append("title = ?");
				}

				query.append(" AND ");

				query.append("head = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (title != null) {
					qPos.add(title);
				}

				qPos.add(head);

				qPos.add(draft);

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

	public int countByP_D(long parentResourcePrimKey, boolean draft)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByP_D";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(parentResourcePrimKey), Boolean.valueOf(draft)
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

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("parentResourcePrimKey = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

				qPos.add(draft);

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

	public int countByP_H(long parentResourcePrimKey, boolean head)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByP_H";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(parentResourcePrimKey), Boolean.valueOf(head)
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

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("parentResourcePrimKey = ?");

				query.append(" AND ");

				query.append("head = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

				qPos.add(head);

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

	public int countByP_H_D(long parentResourcePrimKey, boolean head,
		boolean draft) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByP_H_D";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(parentResourcePrimKey), Boolean.valueOf(head),
				Boolean.valueOf(draft)
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

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("parentResourcePrimKey = ?");

				query.append(" AND ");

				query.append("head = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

				qPos.add(head);

				qPos.add(draft);

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

	public int countByR_D(long resourcePrimKey, boolean draft)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByR_D";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(resourcePrimKey), Boolean.valueOf(draft)
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

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("resourcePrimKey = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(draft);

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

	public int countByR_H(long resourcePrimKey, boolean head)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByR_H";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(resourcePrimKey), Boolean.valueOf(head)
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

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("resourcePrimKey = ?");

				query.append(" AND ");

				query.append("head = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(head);

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

	public int countByR_H_D(long resourcePrimKey, boolean head, boolean draft)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByR_H_D";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(resourcePrimKey), Boolean.valueOf(head),
				Boolean.valueOf(draft)
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

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("resourcePrimKey = ?");

				query.append(" AND ");

				query.append("head = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(head);

				qPos.add(draft);

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

	public int countByR_V(long resourcePrimKey, double version)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByR_V";
		String[] finderParams = new String[] {
				Long.class.getName(), Double.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(resourcePrimKey), new Double(version)
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

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("resourcePrimKey = ?");

				query.append(" AND ");

				query.append("version = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(version);

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

	public int countByR_V_D(long resourcePrimKey, double version, boolean draft)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
		String finderMethodName = "countByR_V_D";
		String[] finderParams = new String[] {
				Long.class.getName(), Double.class.getName(),
				Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(resourcePrimKey), new Double(version),
				Boolean.valueOf(draft)
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

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.knowledgebase.model.KBArticle WHERE ");

				query.append("resourcePrimKey = ?");

				query.append(" AND ");

				query.append("version = ?");

				query.append(" AND ");

				query.append("draft = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(version);

				qPos.add(draft);

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
		boolean finderClassNameCacheEnabled = KBArticleModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticle.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.knowledgebase.model.KBArticle");

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
						"value.object.listener.com.liferay.knowledgebase.model.KBArticle")));

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

	private static Log _log = LogFactory.getLog(KBArticlePersistenceImpl.class);
	private ModelListener[] _listeners = new ModelListener[0];
}