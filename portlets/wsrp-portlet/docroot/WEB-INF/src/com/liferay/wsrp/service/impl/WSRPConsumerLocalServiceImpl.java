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

package com.liferay.wsrp.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.wsrp.WSRPConsumerNameException;
import com.liferay.wsrp.WSRPConsumerWSDLException;
import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.service.base.WSRPConsumerLocalServiceBaseImpl;
import com.liferay.wsrp.util.WSRPConsumerManagerFactory;

import java.util.Date;
import java.util.List;

/**
 * <a href="WSRPConsumerLocalServiceImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConsumerLocalServiceImpl
	extends WSRPConsumerLocalServiceBaseImpl {

	public WSRPConsumer addWSRPConsumer(long companyId, String name, String url)
		throws PortalException, SystemException {

		String wsdl = getWSDL(url);
		Date now = new Date();

		validate(name);

		long wsrpConsumerId = CounterLocalServiceUtil.increment();

		WSRPConsumer wsrpConsumer = wsrpConsumerPersistence.create(
			wsrpConsumerId);

		wsrpConsumer.setCompanyId(companyId);
		wsrpConsumer.setCreateDate(now);
		wsrpConsumer.setModifiedDate(now);
		wsrpConsumer.setName(name);
		wsrpConsumer.setUrl(url);
		wsrpConsumer.setWsdl(wsdl);

		wsrpConsumerPersistence.update(wsrpConsumer, false);

		return wsrpConsumer;
	}

	public void deleteWSRPConsumer(long wsrpConsumerId)
		throws PortalException, SystemException {

		WSRPConsumer wsrpConsumer = wsrpConsumerPersistence.findByPrimaryKey(
			wsrpConsumerId);

		deleteWSRPConsumer(wsrpConsumer);
	}

	public void deleteWSRPConsumer(WSRPConsumer wsrpConsumer)
		throws PortalException, SystemException {

		wsrpConsumerPortletLocalService.deleteWSRPConsumerPortlets(
			wsrpConsumer.getWsrpConsumerId());

		wsrpConsumerPersistence.remove(wsrpConsumer);
	}

	public List<WSRPConsumer> getWSRPConsumers(
			long companyId, int start, int end)
		throws SystemException {

		return wsrpConsumerPersistence.findByCompanyId(companyId, start, end);
	}

	public int getWSRPConsumers(long companyId) throws SystemException {
		return wsrpConsumerPersistence.countByCompanyId(companyId);
	}

	public WSRPConsumer updateWSRPConsumer(
			long wsrpConsumerId, String name, String url)
		throws PortalException, SystemException {

		String wsdl = getWSDL(url);

		validate(name);

		WSRPConsumer wsrpConsumer = wsrpConsumerPersistence.findByPrimaryKey(
			wsrpConsumerId);

		wsrpConsumer.setModifiedDate(new Date());
		wsrpConsumer.setName(name);
		wsrpConsumer.setUrl(url);
		wsrpConsumer.setWsdl(wsdl);

		wsrpConsumerPersistence.update(wsrpConsumer, false);

		return wsrpConsumer;
	}

	protected String getWSDL(String url) throws PortalException {
		try {
			String wsdl = HttpUtil.URLtoString(url);

			WSRPConsumerManagerFactory.getWSRPConsumerManager(wsdl);

			return wsdl;
		}
		catch (Exception e) {
			throw new WSRPConsumerWSDLException(e);
		}
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new WSRPConsumerNameException();
		}
	}

}