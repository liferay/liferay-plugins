/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.mail.service.persistence;

import com.liferay.mail.NoSuchMessageException;
import com.liferay.mail.model.Message;
import com.liferay.mail.model.impl.MessageImpl;
import com.liferay.mail.model.impl.MessageModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="MessagePersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessagePersistence
 * @see       MessageUtil
 * @generated
 */
public class MessagePersistenceImpl extends BasePersistenceImpl<Message>
	implements MessagePersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = MessageImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(MessageModelImpl.ENTITY_CACHE_ENABLED,
			MessageModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MessageModelImpl.ENTITY_CACHE_ENABLED,
			MessageModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(Message message) {
		EntityCacheUtil.putResult(MessageModelImpl.ENTITY_CACHE_ENABLED,
			MessageImpl.class, message.getPrimaryKey(), message);
	}

	public void cacheResult(List<Message> messages) {
		for (Message message : messages) {
			if (EntityCacheUtil.getResult(
						MessageModelImpl.ENTITY_CACHE_ENABLED,
						MessageImpl.class, message.getPrimaryKey(), this) == null) {
				cacheResult(message);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(MessageImpl.class.getName());
		EntityCacheUtil.clearCache(MessageImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public Message create(long messageId) {
		Message message = new MessageImpl();

		message.setNew(true);
		message.setPrimaryKey(messageId);

		return message;
	}

	public Message remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public Message remove(long messageId)
		throws NoSuchMessageException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Message message = (Message)session.get(MessageImpl.class,
					new Long(messageId));

			if (message == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + messageId);
				}

				throw new NoSuchMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					messageId);
			}

			return remove(message);
		}
		catch (NoSuchMessageException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public Message remove(Message message) throws SystemException {
		for (ModelListener<Message> listener : listeners) {
			listener.onBeforeRemove(message);
		}

		message = removeImpl(message);

		for (ModelListener<Message> listener : listeners) {
			listener.onAfterRemove(message);
		}

		return message;
	}

	protected Message removeImpl(Message message) throws SystemException {
		message = toUnwrappedModel(message);

		Session session = null;

		try {
			session = openSession();

			if (message.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(MessageImpl.class,
						message.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(message);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(MessageModelImpl.ENTITY_CACHE_ENABLED,
			MessageImpl.class, message.getPrimaryKey());

		return message;
	}

	public Message updateImpl(com.liferay.mail.model.Message message,
		boolean merge) throws SystemException {
		message = toUnwrappedModel(message);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, message, merge);

			message.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(MessageModelImpl.ENTITY_CACHE_ENABLED,
			MessageImpl.class, message.getPrimaryKey(), message);

		return message;
	}

	protected Message toUnwrappedModel(Message message) {
		if (message instanceof MessageImpl) {
			return message;
		}

		MessageImpl messageImpl = new MessageImpl();

		messageImpl.setNew(message.isNew());
		messageImpl.setPrimaryKey(message.getPrimaryKey());

		messageImpl.setMessageId(message.getMessageId());
		messageImpl.setCompanyId(message.getCompanyId());
		messageImpl.setUserId(message.getUserId());
		messageImpl.setUserName(message.getUserName());
		messageImpl.setCreateDate(message.getCreateDate());
		messageImpl.setModifiedDate(message.getModifiedDate());
		messageImpl.setAccountId(message.getAccountId());
		messageImpl.setFolderId(message.getFolderId());
		messageImpl.setSender(message.getSender());
		messageImpl.setRecipientsTo(message.getRecipientsTo());
		messageImpl.setRecipientsCc(message.getRecipientsCc());
		messageImpl.setRecipientsBcc(message.getRecipientsBcc());
		messageImpl.setSentDate(message.getSentDate());
		messageImpl.setSubject(message.getSubject());
		messageImpl.setPreview(message.getPreview());
		messageImpl.setBody(message.getBody());
		messageImpl.setFlags(message.getFlags());
		messageImpl.setSize(message.getSize());
		messageImpl.setRemoteMessageId(message.getRemoteMessageId());

		return messageImpl;
	}

	public Message findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public Message findByPrimaryKey(long messageId)
		throws NoSuchMessageException, SystemException {
		Message message = fetchByPrimaryKey(messageId);

		if (message == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + messageId);
			}

			throw new NoSuchMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				messageId);
		}

		return message;
	}

	public Message fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public Message fetchByPrimaryKey(long messageId) throws SystemException {
		Message message = (Message)EntityCacheUtil.getResult(MessageModelImpl.ENTITY_CACHE_ENABLED,
				MessageImpl.class, messageId, this);

		if (message == null) {
			Session session = null;

			try {
				session = openSession();

				message = (Message)session.get(MessageImpl.class,
						new Long(messageId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (message != null) {
					cacheResult(message);
				}

				closeSession(session);
			}
		}

		return message;
	}

	public List<Message> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<Message> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<Message> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Message> list = (List<Message>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;
				String sql = null;

				if (orderByComparator != null) {
					query = new StringBundler(2 +
							(orderByComparator.getOrderByFields().length * 3));

					query.append(_SQL_SELECT_MESSAGE);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}

				else {
					sql = _SQL_SELECT_MESSAGE.concat(MessageModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Message>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Message>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Message>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeAll() throws SystemException {
		for (Message message : findAll()) {
			remove(message);
		}
	}

	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MESSAGE);

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
						"value.object.listener.com.liferay.mail.model.Message")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Message>> listenersList = new ArrayList<ModelListener<Message>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Message>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(type = AccountPersistence.class)
	protected AccountPersistence accountPersistence;
	@BeanReference(type = AttachmentPersistence.class)
	protected AttachmentPersistence attachmentPersistence;
	@BeanReference(type = FolderPersistence.class)
	protected FolderPersistence folderPersistence;
	@BeanReference(type = MessagePersistence.class)
	protected MessagePersistence messagePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_MESSAGE = "SELECT message FROM Message message";
	private static final String _SQL_COUNT_MESSAGE = "SELECT COUNT(message) FROM Message message";
	private static final String _ORDER_BY_ENTITY_ALIAS = "message.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Message exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(MessagePersistenceImpl.class);
}