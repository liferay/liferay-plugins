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

package com.liferay.ipgeocoder.service.persistence;

/**
 * <a href="IPInfoPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface IPInfoPersistence {
	public com.liferay.ipgeocoder.model.IPInfo create(long ipInfoId);

	public com.liferay.ipgeocoder.model.IPInfo remove(long ipInfoId)
		throws com.liferay.portal.SystemException,
			com.liferay.ipgeocoder.NoSuchIPInfoException;

	public com.liferay.ipgeocoder.model.IPInfo remove(
		com.liferay.ipgeocoder.model.IPInfo ipInfo)
		throws com.liferay.portal.SystemException;

	public com.liferay.ipgeocoder.model.IPInfo update(
		com.liferay.ipgeocoder.model.IPInfo ipInfo)
		throws com.liferay.portal.SystemException;

	public com.liferay.ipgeocoder.model.IPInfo update(
		com.liferay.ipgeocoder.model.IPInfo ipInfo, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.ipgeocoder.model.IPInfo updateImpl(
		com.liferay.ipgeocoder.model.IPInfo ipInfo, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.ipgeocoder.model.IPInfo findByPrimaryKey(long ipInfoId)
		throws com.liferay.portal.SystemException,
			com.liferay.ipgeocoder.NoSuchIPInfoException;

	public com.liferay.ipgeocoder.model.IPInfo fetchByPrimaryKey(long ipInfoId)
		throws com.liferay.portal.SystemException;

	public com.liferay.ipgeocoder.model.IPInfo findByIpAddress(
		java.lang.String ipAddress)
		throws com.liferay.portal.SystemException,
			com.liferay.ipgeocoder.NoSuchIPInfoException;

	public com.liferay.ipgeocoder.model.IPInfo fetchByIpAddress(
		java.lang.String ipAddress) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.ipgeocoder.model.IPInfo> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.ipgeocoder.model.IPInfo> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.ipgeocoder.model.IPInfo> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.ipgeocoder.model.IPInfo> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.ipgeocoder.model.IPInfo> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByIpAddress(java.lang.String ipAddress)
		throws com.liferay.portal.SystemException,
			com.liferay.ipgeocoder.NoSuchIPInfoException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByIpAddress(java.lang.String ipAddress)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}