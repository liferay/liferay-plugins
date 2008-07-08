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

package com.liferay.wsrp.consumer.service.persistence;

/**
 * <a href="ProducerPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface ProducerPersistence {
	public com.liferay.wsrp.consumer.model.Producer create(long producerId);

	public com.liferay.wsrp.consumer.model.Producer remove(long producerId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.consumer.NoSuchProducerException;

	public com.liferay.wsrp.consumer.model.Producer remove(
		com.liferay.wsrp.consumer.model.Producer producer)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.consumer.model.Producer update(
		com.liferay.wsrp.consumer.model.Producer producer)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.consumer.model.Producer update(
		com.liferay.wsrp.consumer.model.Producer producer, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.consumer.model.Producer updateImpl(
		com.liferay.wsrp.consumer.model.Producer producer, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.consumer.model.Producer findByPrimaryKey(
		long producerId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.consumer.NoSuchProducerException;

	public com.liferay.wsrp.consumer.model.Producer fetchByPrimaryKey(
		long producerId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.consumer.model.Producer> findByUuid(
		java.lang.String uuid) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.consumer.model.Producer> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.consumer.model.Producer> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.consumer.model.Producer findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.consumer.NoSuchProducerException;

	public com.liferay.wsrp.consumer.model.Producer findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.consumer.NoSuchProducerException;

	public com.liferay.wsrp.consumer.model.Producer[] findByUuid_PrevAndNext(
		long producerId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.consumer.NoSuchProducerException;

	public com.liferay.wsrp.consumer.model.Producer findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.consumer.NoSuchProducerException;

	public com.liferay.wsrp.consumer.model.Producer fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.consumer.model.Producer> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.consumer.model.Producer> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.consumer.model.Producer> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.consumer.model.Producer> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.consumer.model.Producer> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.SystemException;

	public void removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.consumer.NoSuchProducerException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.SystemException;

	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;

	public void registerListener(
		com.liferay.portal.model.ModelListener listener);

	public void unregisterListener(
		com.liferay.portal.model.ModelListener listener);
}