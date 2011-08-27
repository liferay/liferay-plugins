/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.sampleservicebuilder.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.asset.service.persistence.AssetEntryPersistence;
import com.liferay.portlet.asset.service.persistence.AssetTagPersistence;

import com.liferay.sampleservicebuilder.NoSuchFooException;
import com.liferay.sampleservicebuilder.model.Foo;
import com.liferay.sampleservicebuilder.model.impl.FooImpl;
import com.liferay.sampleservicebuilder.model.impl.FooModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the foo service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FooPersistence
 * @see FooUtil
 * @generated
 */
public class FooPersistenceImpl extends BasePersistenceImpl<Foo>
	implements FooPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link FooUtil} to access the foo persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = FooImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_UUID = new FinderPath(FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, FooImpl.class,
			FINDER_CLASS_NAME_LIST, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, FooImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_FIELD2 = new FinderPath(FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, FooImpl.class,
			FINDER_CLASS_NAME_LIST, "findByField2",
			new String[] {
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_FIELD2 = new FinderPath(FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByField2",
			new String[] { Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, FooImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FooModelImpl.ENTITY_CACHE_ENABLED,
			FooModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the foo in the entity cache if it is enabled.
	 *
	 * @param foo the foo
	 */
	public void cacheResult(Foo foo) {
		EntityCacheUtil.putResult(FooModelImpl.ENTITY_CACHE_ENABLED,
			FooImpl.class, foo.getPrimaryKey(), foo);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { foo.getUuid(), Long.valueOf(foo.getGroupId()) }, foo);

		foo.resetOriginalValues();
	}

	/**
	 * Caches the foos in the entity cache if it is enabled.
	 *
	 * @param foos the foos
	 */
	public void cacheResult(List<Foo> foos) {
		for (Foo foo : foos) {
			if (EntityCacheUtil.getResult(FooModelImpl.ENTITY_CACHE_ENABLED,
						FooImpl.class, foo.getPrimaryKey(), this) == null) {
				cacheResult(foo);
			}
		}
	}

	/**
	 * Clears the cache for all foos.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(FooImpl.class.getName());
		}

		EntityCacheUtil.clearCache(FooImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the foo.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Foo foo) {
		EntityCacheUtil.removeResult(FooModelImpl.ENTITY_CACHE_ENABLED,
			FooImpl.class, foo.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { foo.getUuid(), Long.valueOf(foo.getGroupId()) });
	}

	/**
	 * Creates a new foo with the primary key. Does not add the foo to the database.
	 *
	 * @param fooId the primary key for the new foo
	 * @return the new foo
	 */
	public Foo create(long fooId) {
		Foo foo = new FooImpl();

		foo.setNew(true);
		foo.setPrimaryKey(fooId);

		String uuid = PortalUUIDUtil.generate();

		foo.setUuid(uuid);

		return foo;
	}

	/**
	 * Removes the foo with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the foo
	 * @return the foo that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a foo with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Foo remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the foo with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fooId the primary key of the foo
	 * @return the foo that was removed
	 * @throws com.liferay.sampleservicebuilder.NoSuchFooException if a foo with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Foo remove(long fooId) throws NoSuchFooException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Foo foo = (Foo)session.get(FooImpl.class, Long.valueOf(fooId));

			if (foo == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + fooId);
				}

				throw new NoSuchFooException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					fooId);
			}

			return fooPersistence.remove(foo);
		}
		catch (NoSuchFooException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Removes the foo from the database. Also notifies the appropriate model listeners.
	 *
	 * @param foo the foo
	 * @return the foo that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Foo remove(Foo foo) throws SystemException {
		return super.remove(foo);
	}

	@Override
	protected Foo removeImpl(Foo foo) throws SystemException {
		foo = toUnwrappedModel(foo);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, foo);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		FooModelImpl fooModelImpl = (FooModelImpl)foo;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				fooModelImpl.getUuid(), Long.valueOf(fooModelImpl.getGroupId())
			});

		EntityCacheUtil.removeResult(FooModelImpl.ENTITY_CACHE_ENABLED,
			FooImpl.class, foo.getPrimaryKey());

		return foo;
	}

	@Override
	public Foo updateImpl(com.liferay.sampleservicebuilder.model.Foo foo,
		boolean merge) throws SystemException {
		foo = toUnwrappedModel(foo);

		boolean isNew = foo.isNew();

		FooModelImpl fooModelImpl = (FooModelImpl)foo;

		if (Validator.isNull(foo.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			foo.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, foo, merge);

			foo.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(FooModelImpl.ENTITY_CACHE_ENABLED,
			FooImpl.class, foo.getPrimaryKey(), foo);

		if (!isNew &&
				(!Validator.equals(foo.getUuid(), fooModelImpl.getOriginalUuid()) ||
				(foo.getGroupId() != fooModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					fooModelImpl.getOriginalUuid(),
					Long.valueOf(fooModelImpl.getOriginalGroupId())
				});
		}

		if (isNew ||
				(!Validator.equals(foo.getUuid(), fooModelImpl.getOriginalUuid()) ||
				(foo.getGroupId() != fooModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] { foo.getUuid(), Long.valueOf(foo.getGroupId()) },
				foo);
		}

		return foo;
	}

	protected Foo toUnwrappedModel(Foo foo) {
		if (foo instanceof FooImpl) {
			return foo;
		}

		FooImpl fooImpl = new FooImpl();

		fooImpl.setNew(foo.isNew());
		fooImpl.setPrimaryKey(foo.getPrimaryKey());

		fooImpl.setUuid(foo.getUuid());
		fooImpl.setFooId(foo.getFooId());
		fooImpl.setGroupId(foo.getGroupId());
		fooImpl.setCompanyId(foo.getCompanyId());
		fooImpl.setUserId(foo.getUserId());
		fooImpl.setUserName(foo.getUserName());
		fooImpl.setCreateDate(foo.getCreateDate());
		fooImpl.setModifiedDate(foo.getModifiedDate());
		fooImpl.setField1(foo.getField1());
		fooImpl.setField2(foo.isField2());
		fooImpl.setField3(foo.getField3());
		fooImpl.setField4(foo.getField4());
		fooImpl.setField5(foo.getField5());

		return fooImpl;
	}

	/**
	 * Returns the foo with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the foo
	 * @return the foo
	 * @throws com.liferay.portal.NoSuchModelException if a foo with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Foo findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the foo with the primary key or throws a {@link com.liferay.sampleservicebuilder.NoSuchFooException} if it could not be found.
	 *
	 * @param fooId the primary key of the foo
	 * @return the foo
	 * @throws com.liferay.sampleservicebuilder.NoSuchFooException if a foo with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Foo findByPrimaryKey(long fooId)
		throws NoSuchFooException, SystemException {
		Foo foo = fetchByPrimaryKey(fooId);

		if (foo == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + fooId);
			}

			throw new NoSuchFooException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				fooId);
		}

		return foo;
	}

	/**
	 * Returns the foo with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the foo
	 * @return the foo, or <code>null</code> if a foo with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Foo fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the foo with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fooId the primary key of the foo
	 * @return the foo, or <code>null</code> if a foo with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Foo fetchByPrimaryKey(long fooId) throws SystemException {
		Foo foo = (Foo)EntityCacheUtil.getResult(FooModelImpl.ENTITY_CACHE_ENABLED,
				FooImpl.class, fooId, this);

		if (foo == _nullFoo) {
			return null;
		}

		if (foo == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				foo = (Foo)session.get(FooImpl.class, Long.valueOf(fooId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (foo != null) {
					cacheResult(foo);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(FooModelImpl.ENTITY_CACHE_ENABLED,
						FooImpl.class, fooId, _nullFoo);
				}

				closeSession(session);
			}
		}

		return foo;
	}

	/**
	 * Returns all the foos where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching foos
	 * @throws SystemException if a system exception occurred
	 */
	public List<Foo> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the foos where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of foos
	 * @param end the upper bound of the range of foos (not inclusive)
	 * @return the range of matching foos
	 * @throws SystemException if a system exception occurred
	 */
	public List<Foo> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the foos where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of foos
	 * @param end the upper bound of the range of foos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching foos
	 * @throws SystemException if a system exception occurred
	 */
	public List<Foo> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				uuid,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Foo> list = (List<Foo>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_UUID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_FOO_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(FooModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<Foo>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_UUID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_UUID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first foo in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching foo
	 * @throws com.liferay.sampleservicebuilder.NoSuchFooException if a matching foo could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Foo findByUuid_First(String uuid, OrderByComparator orderByComparator)
		throws NoSuchFooException, SystemException {
		List<Foo> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFooException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last foo in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching foo
	 * @throws com.liferay.sampleservicebuilder.NoSuchFooException if a matching foo could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Foo findByUuid_Last(String uuid, OrderByComparator orderByComparator)
		throws NoSuchFooException, SystemException {
		int count = countByUuid(uuid);

		List<Foo> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFooException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the foos before and after the current foo in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param fooId the primary key of the current foo
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next foo
	 * @throws com.liferay.sampleservicebuilder.NoSuchFooException if a foo with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Foo[] findByUuid_PrevAndNext(long fooId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchFooException, SystemException {
		Foo foo = findByPrimaryKey(fooId);

		Session session = null;

		try {
			session = openSession();

			Foo[] array = new FooImpl[3];

			array[0] = getByUuid_PrevAndNext(session, foo, uuid,
					orderByComparator, true);

			array[1] = foo;

			array[2] = getByUuid_PrevAndNext(session, foo, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Foo getByUuid_PrevAndNext(Session session, Foo foo, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_FOO_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(FooModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(foo);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Foo> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the foo where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.sampleservicebuilder.NoSuchFooException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching foo
	 * @throws com.liferay.sampleservicebuilder.NoSuchFooException if a matching foo could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Foo findByUUID_G(String uuid, long groupId)
		throws NoSuchFooException, SystemException {
		Foo foo = fetchByUUID_G(uuid, groupId);

		if (foo == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchFooException(msg.toString());
		}

		return foo;
	}

	/**
	 * Returns the foo where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching foo, or <code>null</code> if a matching foo could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Foo fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the foo where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching foo, or <code>null</code> if a matching foo could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Foo fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_FOO_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_G_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			query.append(FooModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<Foo> list = q.list();

				result = list;

				Foo foo = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					foo = list.get(0);

					cacheResult(foo);

					if ((foo.getUuid() == null) || !foo.getUuid().equals(uuid) ||
							(foo.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, foo);
					}
				}

				return foo;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (Foo)result;
			}
		}
	}

	/**
	 * Returns all the foos where field2 = &#63;.
	 *
	 * @param field2 the field2
	 * @return the matching foos
	 * @throws SystemException if a system exception occurred
	 */
	public List<Foo> findByField2(boolean field2) throws SystemException {
		return findByField2(field2, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the foos where field2 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param field2 the field2
	 * @param start the lower bound of the range of foos
	 * @param end the upper bound of the range of foos (not inclusive)
	 * @return the range of matching foos
	 * @throws SystemException if a system exception occurred
	 */
	public List<Foo> findByField2(boolean field2, int start, int end)
		throws SystemException {
		return findByField2(field2, start, end, null);
	}

	/**
	 * Returns an ordered range of all the foos where field2 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param field2 the field2
	 * @param start the lower bound of the range of foos
	 * @param end the upper bound of the range of foos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching foos
	 * @throws SystemException if a system exception occurred
	 */
	public List<Foo> findByField2(boolean field2, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				field2,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Foo> list = (List<Foo>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_FIELD2,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_FOO_WHERE);

			query.append(_FINDER_COLUMN_FIELD2_FIELD2_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(FooModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(field2);

				list = (List<Foo>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_FIELD2,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_FIELD2,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first foo in the ordered set where field2 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param field2 the field2
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching foo
	 * @throws com.liferay.sampleservicebuilder.NoSuchFooException if a matching foo could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Foo findByField2_First(boolean field2,
		OrderByComparator orderByComparator)
		throws NoSuchFooException, SystemException {
		List<Foo> list = findByField2(field2, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("field2=");
			msg.append(field2);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFooException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last foo in the ordered set where field2 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param field2 the field2
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching foo
	 * @throws com.liferay.sampleservicebuilder.NoSuchFooException if a matching foo could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Foo findByField2_Last(boolean field2,
		OrderByComparator orderByComparator)
		throws NoSuchFooException, SystemException {
		int count = countByField2(field2);

		List<Foo> list = findByField2(field2, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("field2=");
			msg.append(field2);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFooException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the foos before and after the current foo in the ordered set where field2 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param fooId the primary key of the current foo
	 * @param field2 the field2
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next foo
	 * @throws com.liferay.sampleservicebuilder.NoSuchFooException if a foo with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Foo[] findByField2_PrevAndNext(long fooId, boolean field2,
		OrderByComparator orderByComparator)
		throws NoSuchFooException, SystemException {
		Foo foo = findByPrimaryKey(fooId);

		Session session = null;

		try {
			session = openSession();

			Foo[] array = new FooImpl[3];

			array[0] = getByField2_PrevAndNext(session, foo, field2,
					orderByComparator, true);

			array[1] = foo;

			array[2] = getByField2_PrevAndNext(session, foo, field2,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Foo getByField2_PrevAndNext(Session session, Foo foo,
		boolean field2, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_FOO_WHERE);

		query.append(_FINDER_COLUMN_FIELD2_FIELD2_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(FooModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(field2);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(foo);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Foo> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the foos.
	 *
	 * @return the foos
	 * @throws SystemException if a system exception occurred
	 */
	public List<Foo> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the foos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of foos
	 * @param end the upper bound of the range of foos (not inclusive)
	 * @return the range of foos
	 * @throws SystemException if a system exception occurred
	 */
	public List<Foo> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the foos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of foos
	 * @param end the upper bound of the range of foos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of foos
	 * @throws SystemException if a system exception occurred
	 */
	public List<Foo> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Foo> list = (List<Foo>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_FOO);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FOO.concat(FooModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Foo>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Foo>)QueryUtil.list(q, getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs,
						list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the foos where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (Foo foo : findByUuid(uuid)) {
			fooPersistence.remove(foo);
		}
	}

	/**
	 * Removes the foo where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUUID_G(String uuid, long groupId)
		throws NoSuchFooException, SystemException {
		Foo foo = findByUUID_G(uuid, groupId);

		fooPersistence.remove(foo);
	}

	/**
	 * Removes all the foos where field2 = &#63; from the database.
	 *
	 * @param field2 the field2
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByField2(boolean field2) throws SystemException {
		for (Foo foo : findByField2(field2)) {
			fooPersistence.remove(foo);
		}
	}

	/**
	 * Removes all the foos from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Foo foo : findAll()) {
			fooPersistence.remove(foo);
		}
	}

	/**
	 * Returns the number of foos where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching foos
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FOO_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of foos where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching foos
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FOO_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_G_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of foos where field2 = &#63;.
	 *
	 * @param field2 the field2
	 * @return the number of matching foos
	 * @throws SystemException if a system exception occurred
	 */
	public int countByField2(boolean field2) throws SystemException {
		Object[] finderArgs = new Object[] { field2 };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FIELD2,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FOO_WHERE);

			query.append(_FINDER_COLUMN_FIELD2_FIELD2_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(field2);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FIELD2,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of foos.
	 *
	 * @return the number of foos
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FOO);

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

	/**
	 * Initializes the foo persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.sampleservicebuilder.model.Foo")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Foo>> listenersList = new ArrayList<ModelListener<Foo>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Foo>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(FooImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = FooPersistence.class)
	protected FooPersistence fooPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = AssetEntryPersistence.class)
	protected AssetEntryPersistence assetEntryPersistence;
	@BeanReference(type = AssetTagPersistence.class)
	protected AssetTagPersistence assetTagPersistence;
	private static final String _SQL_SELECT_FOO = "SELECT foo FROM Foo foo";
	private static final String _SQL_SELECT_FOO_WHERE = "SELECT foo FROM Foo foo WHERE ";
	private static final String _SQL_COUNT_FOO = "SELECT COUNT(foo) FROM Foo foo";
	private static final String _SQL_COUNT_FOO_WHERE = "SELECT COUNT(foo) FROM Foo foo WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "foo.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "foo.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(foo.uuid IS NULL OR foo.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "foo.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "foo.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(foo.uuid IS NULL OR foo.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "foo.groupId = ?";
	private static final String _FINDER_COLUMN_FIELD2_FIELD2_2 = "foo.field2 = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "foo.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Foo exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Foo exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(FooPersistenceImpl.class);
	private static Foo _nullFoo = new FooImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Foo> toCacheModel() {
				return _nullFooCacheModel;
			}
		};

	private static CacheModel<Foo> _nullFooCacheModel = new CacheModel<Foo>() {
			public Foo toEntityModel() {
				return _nullFoo;
			}
		};
}