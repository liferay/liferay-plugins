/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.mail.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.mail.NoSuchAttachmentException;
import com.liferay.mail.model.Attachment;
import com.liferay.mail.model.impl.AttachmentImpl;
import com.liferay.mail.model.impl.AttachmentModelImpl;
import com.liferay.mail.service.persistence.AttachmentPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the attachment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AttachmentPersistence
 * @see com.liferay.mail.service.persistence.AttachmentUtil
 * @generated
 */
@ProviderType
public class AttachmentPersistenceImpl extends BasePersistenceImpl<Attachment>
	implements AttachmentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AttachmentUtil} to access the attachment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AttachmentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AttachmentModelImpl.FINDER_CACHE_ENABLED, AttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AttachmentModelImpl.FINDER_CACHE_ENABLED, AttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MESSAGEID =
		new FinderPath(AttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AttachmentModelImpl.FINDER_CACHE_ENABLED, AttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMessageId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MESSAGEID =
		new FinderPath(AttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AttachmentModelImpl.FINDER_CACHE_ENABLED, AttachmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByMessageId",
			new String[] { Long.class.getName() },
			AttachmentModelImpl.MESSAGEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MESSAGEID = new FinderPath(AttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AttachmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMessageId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the attachments where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @return the matching attachments
	 */
	@Override
	public List<Attachment> findByMessageId(long messageId) {
		return findByMessageId(messageId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the attachments where messageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param messageId the message ID
	 * @param start the lower bound of the range of attachments
	 * @param end the upper bound of the range of attachments (not inclusive)
	 * @return the range of matching attachments
	 */
	@Override
	public List<Attachment> findByMessageId(long messageId, int start, int end) {
		return findByMessageId(messageId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the attachments where messageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param messageId the message ID
	 * @param start the lower bound of the range of attachments
	 * @param end the upper bound of the range of attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching attachments
	 */
	@Override
	public List<Attachment> findByMessageId(long messageId, int start, int end,
		OrderByComparator<Attachment> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MESSAGEID;
			finderArgs = new Object[] { messageId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MESSAGEID;
			finderArgs = new Object[] { messageId, start, end, orderByComparator };
		}

		List<Attachment> list = (List<Attachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Attachment attachment : list) {
				if ((messageId != attachment.getMessageId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AttachmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(messageId);

				if (!pagination) {
					list = (List<Attachment>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Attachment>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first attachment in the ordered set where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching attachment
	 * @throws NoSuchAttachmentException if a matching attachment could not be found
	 */
	@Override
	public Attachment findByMessageId_First(long messageId,
		OrderByComparator<Attachment> orderByComparator)
		throws NoSuchAttachmentException {
		Attachment attachment = fetchByMessageId_First(messageId,
				orderByComparator);

		if (attachment != null) {
			return attachment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("messageId=");
		msg.append(messageId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAttachmentException(msg.toString());
	}

	/**
	 * Returns the first attachment in the ordered set where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching attachment, or <code>null</code> if a matching attachment could not be found
	 */
	@Override
	public Attachment fetchByMessageId_First(long messageId,
		OrderByComparator<Attachment> orderByComparator) {
		List<Attachment> list = findByMessageId(messageId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last attachment in the ordered set where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching attachment
	 * @throws NoSuchAttachmentException if a matching attachment could not be found
	 */
	@Override
	public Attachment findByMessageId_Last(long messageId,
		OrderByComparator<Attachment> orderByComparator)
		throws NoSuchAttachmentException {
		Attachment attachment = fetchByMessageId_Last(messageId,
				orderByComparator);

		if (attachment != null) {
			return attachment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("messageId=");
		msg.append(messageId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAttachmentException(msg.toString());
	}

	/**
	 * Returns the last attachment in the ordered set where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching attachment, or <code>null</code> if a matching attachment could not be found
	 */
	@Override
	public Attachment fetchByMessageId_Last(long messageId,
		OrderByComparator<Attachment> orderByComparator) {
		int count = countByMessageId(messageId);

		if (count == 0) {
			return null;
		}

		List<Attachment> list = findByMessageId(messageId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the attachments before and after the current attachment in the ordered set where messageId = &#63;.
	 *
	 * @param attachmentId the primary key of the current attachment
	 * @param messageId the message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next attachment
	 * @throws NoSuchAttachmentException if a attachment with the primary key could not be found
	 */
	@Override
	public Attachment[] findByMessageId_PrevAndNext(long attachmentId,
		long messageId, OrderByComparator<Attachment> orderByComparator)
		throws NoSuchAttachmentException {
		Attachment attachment = findByPrimaryKey(attachmentId);

		Session session = null;

		try {
			session = openSession();

			Attachment[] array = new AttachmentImpl[3];

			array[0] = getByMessageId_PrevAndNext(session, attachment,
					messageId, orderByComparator, true);

			array[1] = attachment;

			array[2] = getByMessageId_PrevAndNext(session, attachment,
					messageId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Attachment getByMessageId_PrevAndNext(Session session,
		Attachment attachment, long messageId,
		OrderByComparator<Attachment> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ATTACHMENT_WHERE);

		query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
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

			String[] orderByFields = orderByComparator.getOrderByFields();

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
			query.append(AttachmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(messageId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(attachment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Attachment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the attachments where messageId = &#63; from the database.
	 *
	 * @param messageId the message ID
	 */
	@Override
	public void removeByMessageId(long messageId) {
		for (Attachment attachment : findByMessageId(messageId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(attachment);
		}
	}

	/**
	 * Returns the number of attachments where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @return the number of matching attachments
	 */
	@Override
	public int countByMessageId(long messageId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MESSAGEID;

		Object[] finderArgs = new Object[] { messageId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ATTACHMENT_WHERE);

			query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(messageId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_MESSAGEID_MESSAGEID_2 = "attachment.messageId = ?";

	public AttachmentPersistenceImpl() {
		setModelClass(Attachment.class);
	}

	/**
	 * Caches the attachment in the entity cache if it is enabled.
	 *
	 * @param attachment the attachment
	 */
	@Override
	public void cacheResult(Attachment attachment) {
		EntityCacheUtil.putResult(AttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AttachmentImpl.class, attachment.getPrimaryKey(), attachment);

		attachment.resetOriginalValues();
	}

	/**
	 * Caches the attachments in the entity cache if it is enabled.
	 *
	 * @param attachments the attachments
	 */
	@Override
	public void cacheResult(List<Attachment> attachments) {
		for (Attachment attachment : attachments) {
			if (EntityCacheUtil.getResult(
						AttachmentModelImpl.ENTITY_CACHE_ENABLED,
						AttachmentImpl.class, attachment.getPrimaryKey()) == null) {
				cacheResult(attachment);
			}
			else {
				attachment.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all attachments.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(AttachmentImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the attachment.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Attachment attachment) {
		EntityCacheUtil.removeResult(AttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AttachmentImpl.class, attachment.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Attachment> attachments) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Attachment attachment : attachments) {
			EntityCacheUtil.removeResult(AttachmentModelImpl.ENTITY_CACHE_ENABLED,
				AttachmentImpl.class, attachment.getPrimaryKey());
		}
	}

	/**
	 * Creates a new attachment with the primary key. Does not add the attachment to the database.
	 *
	 * @param attachmentId the primary key for the new attachment
	 * @return the new attachment
	 */
	@Override
	public Attachment create(long attachmentId) {
		Attachment attachment = new AttachmentImpl();

		attachment.setNew(true);
		attachment.setPrimaryKey(attachmentId);

		return attachment;
	}

	/**
	 * Removes the attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param attachmentId the primary key of the attachment
	 * @return the attachment that was removed
	 * @throws NoSuchAttachmentException if a attachment with the primary key could not be found
	 */
	@Override
	public Attachment remove(long attachmentId)
		throws NoSuchAttachmentException {
		return remove((Serializable)attachmentId);
	}

	/**
	 * Removes the attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the attachment
	 * @return the attachment that was removed
	 * @throws NoSuchAttachmentException if a attachment with the primary key could not be found
	 */
	@Override
	public Attachment remove(Serializable primaryKey)
		throws NoSuchAttachmentException {
		Session session = null;

		try {
			session = openSession();

			Attachment attachment = (Attachment)session.get(AttachmentImpl.class,
					primaryKey);

			if (attachment == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAttachmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(attachment);
		}
		catch (NoSuchAttachmentException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Attachment removeImpl(Attachment attachment) {
		attachment = toUnwrappedModel(attachment);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(attachment)) {
				attachment = (Attachment)session.get(AttachmentImpl.class,
						attachment.getPrimaryKeyObj());
			}

			if (attachment != null) {
				session.delete(attachment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (attachment != null) {
			clearCache(attachment);
		}

		return attachment;
	}

	@Override
	public Attachment updateImpl(Attachment attachment) {
		attachment = toUnwrappedModel(attachment);

		boolean isNew = attachment.isNew();

		AttachmentModelImpl attachmentModelImpl = (AttachmentModelImpl)attachment;

		Session session = null;

		try {
			session = openSession();

			if (attachment.isNew()) {
				session.save(attachment);

				attachment.setNew(false);
			}
			else {
				session.merge(attachment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AttachmentModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((attachmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MESSAGEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						attachmentModelImpl.getOriginalMessageId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MESSAGEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MESSAGEID,
					args);

				args = new Object[] { attachmentModelImpl.getMessageId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MESSAGEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MESSAGEID,
					args);
			}
		}

		EntityCacheUtil.putResult(AttachmentModelImpl.ENTITY_CACHE_ENABLED,
			AttachmentImpl.class, attachment.getPrimaryKey(), attachment, false);

		attachment.resetOriginalValues();

		return attachment;
	}

	protected Attachment toUnwrappedModel(Attachment attachment) {
		if (attachment instanceof AttachmentImpl) {
			return attachment;
		}

		AttachmentImpl attachmentImpl = new AttachmentImpl();

		attachmentImpl.setNew(attachment.isNew());
		attachmentImpl.setPrimaryKey(attachment.getPrimaryKey());

		attachmentImpl.setAttachmentId(attachment.getAttachmentId());
		attachmentImpl.setCompanyId(attachment.getCompanyId());
		attachmentImpl.setUserId(attachment.getUserId());
		attachmentImpl.setAccountId(attachment.getAccountId());
		attachmentImpl.setFolderId(attachment.getFolderId());
		attachmentImpl.setMessageId(attachment.getMessageId());
		attachmentImpl.setContentPath(attachment.getContentPath());
		attachmentImpl.setFileName(attachment.getFileName());
		attachmentImpl.setSize(attachment.getSize());

		return attachmentImpl;
	}

	/**
	 * Returns the attachment with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the attachment
	 * @return the attachment
	 * @throws NoSuchAttachmentException if a attachment with the primary key could not be found
	 */
	@Override
	public Attachment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAttachmentException {
		Attachment attachment = fetchByPrimaryKey(primaryKey);

		if (attachment == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAttachmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return attachment;
	}

	/**
	 * Returns the attachment with the primary key or throws a {@link NoSuchAttachmentException} if it could not be found.
	 *
	 * @param attachmentId the primary key of the attachment
	 * @return the attachment
	 * @throws NoSuchAttachmentException if a attachment with the primary key could not be found
	 */
	@Override
	public Attachment findByPrimaryKey(long attachmentId)
		throws NoSuchAttachmentException {
		return findByPrimaryKey((Serializable)attachmentId);
	}

	/**
	 * Returns the attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the attachment
	 * @return the attachment, or <code>null</code> if a attachment with the primary key could not be found
	 */
	@Override
	public Attachment fetchByPrimaryKey(Serializable primaryKey) {
		Attachment attachment = (Attachment)EntityCacheUtil.getResult(AttachmentModelImpl.ENTITY_CACHE_ENABLED,
				AttachmentImpl.class, primaryKey);

		if (attachment == _nullAttachment) {
			return null;
		}

		if (attachment == null) {
			Session session = null;

			try {
				session = openSession();

				attachment = (Attachment)session.get(AttachmentImpl.class,
						primaryKey);

				if (attachment != null) {
					cacheResult(attachment);
				}
				else {
					EntityCacheUtil.putResult(AttachmentModelImpl.ENTITY_CACHE_ENABLED,
						AttachmentImpl.class, primaryKey, _nullAttachment);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(AttachmentModelImpl.ENTITY_CACHE_ENABLED,
					AttachmentImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return attachment;
	}

	/**
	 * Returns the attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param attachmentId the primary key of the attachment
	 * @return the attachment, or <code>null</code> if a attachment with the primary key could not be found
	 */
	@Override
	public Attachment fetchByPrimaryKey(long attachmentId) {
		return fetchByPrimaryKey((Serializable)attachmentId);
	}

	@Override
	public Map<Serializable, Attachment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Attachment> map = new HashMap<Serializable, Attachment>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Attachment attachment = fetchByPrimaryKey(primaryKey);

			if (attachment != null) {
				map.put(primaryKey, attachment);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Attachment attachment = (Attachment)EntityCacheUtil.getResult(AttachmentModelImpl.ENTITY_CACHE_ENABLED,
					AttachmentImpl.class, primaryKey);

			if (attachment == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, attachment);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ATTACHMENT_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (Attachment attachment : (List<Attachment>)q.list()) {
				map.put(attachment.getPrimaryKeyObj(), attachment);

				cacheResult(attachment);

				uncachedPrimaryKeys.remove(attachment.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(AttachmentModelImpl.ENTITY_CACHE_ENABLED,
					AttachmentImpl.class, primaryKey, _nullAttachment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the attachments.
	 *
	 * @return the attachments
	 */
	@Override
	public List<Attachment> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of attachments
	 * @param end the upper bound of the range of attachments (not inclusive)
	 * @return the range of attachments
	 */
	@Override
	public List<Attachment> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of attachments
	 * @param end the upper bound of the range of attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of attachments
	 */
	@Override
	public List<Attachment> findAll(int start, int end,
		OrderByComparator<Attachment> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Attachment> list = (List<Attachment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ATTACHMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ATTACHMENT;

				if (pagination) {
					sql = sql.concat(AttachmentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Attachment>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Attachment>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the attachments from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Attachment attachment : findAll()) {
			remove(attachment);
		}
	}

	/**
	 * Returns the number of attachments.
	 *
	 * @return the number of attachments
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ATTACHMENT);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	/**
	 * Initializes the attachment persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(AttachmentImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_ATTACHMENT = "SELECT attachment FROM Attachment attachment";
	private static final String _SQL_SELECT_ATTACHMENT_WHERE_PKS_IN = "SELECT attachment FROM Attachment attachment WHERE attachmentId IN (";
	private static final String _SQL_SELECT_ATTACHMENT_WHERE = "SELECT attachment FROM Attachment attachment WHERE ";
	private static final String _SQL_COUNT_ATTACHMENT = "SELECT COUNT(attachment) FROM Attachment attachment";
	private static final String _SQL_COUNT_ATTACHMENT_WHERE = "SELECT COUNT(attachment) FROM Attachment attachment WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "attachment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Attachment exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Attachment exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AttachmentPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"size"
			});
	private static final Attachment _nullAttachment = new AttachmentImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Attachment> toCacheModel() {
				return _nullAttachmentCacheModel;
			}
		};

	private static final CacheModel<Attachment> _nullAttachmentCacheModel = new CacheModel<Attachment>() {
			@Override
			public Attachment toEntityModel() {
				return _nullAttachment;
			}
		};
}