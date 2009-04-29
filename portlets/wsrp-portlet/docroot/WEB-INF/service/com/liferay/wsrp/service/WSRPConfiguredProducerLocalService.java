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

package com.liferay.wsrp.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;

/**
 * <a href="WSRPConfiguredProducerLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface WSRPConfiguredProducerLocalService {
	public com.liferay.wsrp.model.WSRPConfiguredProducer addWSRPConfiguredProducer(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPConfiguredProducer createWSRPConfiguredProducer(
		long configuredProducerId);

	public void deleteWSRPConfiguredProducer(long configuredProducerId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteWSRPConfiguredProducer(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.wsrp.model.WSRPConfiguredProducer getWSRPConfiguredProducer(
		long configuredProducerId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer> getWSRPConfiguredProducers(
		int start, int end) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWSRPConfiguredProducersCount()
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPConfiguredProducer updateWSRPConfiguredProducer(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPConfiguredProducer updateWSRPConfiguredProducer(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer,
		boolean merge) throws com.liferay.portal.SystemException;

	public void addConfiguredProducer(java.lang.String name,
		java.lang.String portalId, java.lang.String namespace,
		java.lang.String producerURL, java.lang.String producerVersion,
		java.lang.String producerMarkupURL, int status,
		java.lang.String registrationData,
		java.lang.String registrationContext,
		java.lang.String serviceDescription,
		java.lang.String userCategoryMapping,
		java.lang.String customUserProfile,
		java.lang.String identityPropagationType,
		java.lang.String lifetimeTerminationTime, long sdLastModified,
		int entityVersion) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.wsrp.model.WSRPConfiguredProducer getConfiguredProducer(
		long configuredProducer)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer> getConfiguredProducers(
		java.lang.String portalId, java.lang.String namespace)
		throws com.liferay.portal.SystemException;
}